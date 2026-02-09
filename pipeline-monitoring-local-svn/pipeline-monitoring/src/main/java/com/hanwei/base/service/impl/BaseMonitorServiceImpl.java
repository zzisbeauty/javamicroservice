package com.hanwei.base.service.impl;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.hanwei.base.entity.BaseMonitorRelate;
import com.hanwei.base.entity.dto.BaseMonitorDto;
import com.hanwei.base.entity.dto.BaseMonitorInput;
import com.hanwei.base.entity.vo.BaseMonitorVo;
import com.hanwei.base.service.IBaseMonitorRelateService;
import com.hanwei.base.service.IBaseMonitorResponsibilityService;
import com.hanwei.core.common.api.CommonAPI;
import com.hanwei.core.common.api.vo.LoginUser;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.base.entity.BaseMonitor;
import com.hanwei.base.mapper.BaseMonitorMapper;
import com.hanwei.base.service.IBaseMonitorService;
import com.hanwei.data.entity.DataRealtime;
import com.hanwei.data.entity.vo.DataRealtimeVo;
import com.hanwei.data.entity.vo.MonitorRealtimeVo;
import com.hanwei.base.entity.BaseMonitorResponsibility;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @Description: 监测点
 * @Author: hanwei
 * @Date:   2026-01-22
 * @Version: V1.0
 */
@Service
@RequiredArgsConstructor
public class BaseMonitorServiceImpl extends ServiceImpl<BaseMonitorMapper, BaseMonitor> implements IBaseMonitorService {

    @Value("${excel.batchSaveCount}")
    private Integer BATCH_SAVE_COUNT;

    private final CommonAPI commonApi;
    //采集器关联   用之前先注入
    private final IBaseMonitorRelateService baseMonitorRelateService;
    private final IBaseMonitorResponsibilityService  baseMonitorResponsibilityService;

    @Autowired
    BaseMonitorMapper baseMonitorMapper;

    /**
     *
     * @param dto
     * @param pageNo
     * @param pageSize
     * @return
     */
    /**
     * 传的数据库实体类，前端自定义查询
     * 不需要复制字段值过去；BeanUtils.copyProperties(dto, baseMonitor);
     * 不需要做非空判断,传入直接匹配
     */
    @Override
    public IPage<?> queryMonitorRelate(BaseMonitorDto dto, Integer pageNo, Integer pageSize) {
        // 1. 分页查询监测点
        Page<BaseMonitor> page = new Page<>(pageNo, pageSize);
        LambdaQueryWrapper<BaseMonitor> wrapper = new LambdaQueryWrapper<>();
        //添加判断条件
        if(StringUtils.isNotBlank(dto.getId())){
            wrapper.eq(BaseMonitor::getId, dto.getId());
        }
        if(StringUtils.isNotBlank(dto.getName())){
            wrapper.like(BaseMonitor::getName, dto.getName());
        }
        if(StringUtils.isNotBlank(dto.getStatus())){
            wrapper.eq(BaseMonitor::getStatus, dto.getStatus());
        }

        IPage<BaseMonitor> monitorPage = this.page(page, wrapper);

        // 2. 获取监测点ID集合
        List<String> monitorIds = monitorPage.getRecords().stream()
                .map(BaseMonitor::getId)
                .collect(Collectors.toList());

        // 3. 批量查询关联关系
        List<BaseMonitorRelate> relates;
        if(!monitorIds.isEmpty()){
            relates = baseMonitorRelateService.list(
                    new LambdaQueryWrapper<BaseMonitorRelate>()
                            .in(BaseMonitorRelate::getMonitorPointId, monitorIds)
            );
        } else {
            relates = Collections.emptyList();
        }

        // 4. 组装返回对象
        List<BaseMonitorVo> voList = monitorPage.getRecords().stream().map(monitor -> {
            BaseMonitorVo vo = new BaseMonitorVo();
            BeanUtils.copyProperties(monitor, vo);
            // 将对应的关联关系放入集合
            vo.setRelates(
                    relates.stream()
                            .filter(r -> r.getMonitorPointId().equals(monitor.getId()))
                            .collect(Collectors.toList())
            );
            return vo;
        }).collect(Collectors.toList());

        // 5. 返回分页结果
        Page<BaseMonitorVo> resultPage = new Page<>(pageNo, pageSize);
        resultPage.setRecords(voList);
        resultPage.setTotal(monitorPage.getTotal());
        return resultPage;
    }



    /**
     * 添加监测点及其主从关系
     *
     * @param input
     */
    @Override
    @Transactional(rollbackFor = Exception.class) //事务回滚
    public void addMonitorRelate(BaseMonitorInput input) {

        // 1. Input → Entity
        BaseMonitor monitor = new BaseMonitor();
        BeanUtils.copyProperties(input, monitor);

        // 2. 保存监测点
        this.save(monitor);
        String monitorId = monitor.getId();

        // 3. 保存指标
        List<String> propertyCodeList = input.getPropertyCodeList();
        if (propertyCodeList != null && !propertyCodeList.isEmpty()) {

            List<BaseMonitorRelate> propertyList = propertyCodeList.stream()
                    .map(code -> {
                        BaseMonitorRelate p = new BaseMonitorRelate();
                        p.setMonitorPointId(monitorId);
                        p.setPropertyCode(code);
                        return p;
                    }).toList();

            baseMonitorRelateService.saveBatch(propertyList); // 使用注入的服务进行保存
        }
    }

    /**
     * 删除监测点及其主从关系
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> deleteMonitorRelate(String id) {

        // 删除从表（指标）
        Boolean flag = baseMonitorRelateService.remove(
                new LambdaQueryWrapper<BaseMonitorRelate>()
                        .eq(BaseMonitorRelate::getMonitorPointId, id)
        );

        if (flag) {
            // 删除主表（监测点）
            this.removeById(id);
            return Result.OK("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }

    /**
     * 修改监测点及其主从关系
     *
     * @param input
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> editMonitorRelate(BaseMonitorInput input) {

        if (input.getId() == null) {
            return Result.error("监测点ID不能为空");
        }

        // 更新主表
        BaseMonitor monitor = new BaseMonitor();
        BeanUtils.copyProperties(input, monitor);
        this.updateById(monitor);

        String monitorId = input.getId();

        // 删除旧指标
        boolean flag = baseMonitorRelateService.remove(
                new LambdaQueryWrapper<BaseMonitorRelate>()
                        .eq(BaseMonitorRelate::getMonitorPointId, monitorId)
        );
        if (flag) {
            // 插入新指标
            List<String> propertyCodeList = input.getPropertyCodeList();
            if (propertyCodeList != null && !propertyCodeList.isEmpty()) {
                List<BaseMonitorRelate> propertyList = propertyCodeList.stream()
                        .map(code -> {
                            BaseMonitorRelate p = new BaseMonitorRelate();
                            p.setMonitorPointId(monitorId);
                            p.setPropertyCode(code);
                            return p;
                        }).toList();

                baseMonitorRelateService.saveBatch(propertyList);
                return Result.OK("修改成功");
            } else {
                return Result.error("修改失败");
            }
        } else {
            return Result.error("修改失败");
        }
    }
    /**
     * 批量删除监测点及其主从关系
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> deleteMonitorRelates(List<String> ids) {

        // 删除所有指标
     Boolean flag=   baseMonitorRelateService.remove(
                new LambdaQueryWrapper<BaseMonitorRelate>()
                        .in(BaseMonitorRelate::getMonitorPointId, ids)
        );
     if(flag){
    // 删除所有监测点
    this.removeByIds(ids);
    return Result.OK("删除成功");
     }else {
    return Result.error("删除失败");
     }

    }

    @Override
    @Transactional(rollbackFor = Exception.class) //事务回滚
    public Result<?> importData(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return Result.error("文件不能为空");
        }
        try {
            EasyExcel.read(file.getInputStream(), BaseMonitor.class, new ReadListener<BaseMonitor>() {

                private List<BaseMonitor> cachedDataList = new ArrayList<>();

                /**
                 * 每次读取都会调用
                 * @param data
                 * @param context
                 */
                @SneakyThrows
                @Override
                public void invoke(BaseMonitor data, AnalysisContext context) {
                    //---------------处理字典转义
//                    String text = commonApi.translateDictTextToKey("risk_place_type",data.getRiskType());
//                    if(StringUtils.isEmpty(text)){
//                        throw new ImportException("数据第" + context.readRowHolder().getRowIndex()+ "行存在未配置得字典类型，数据类型:" + data.getRiskType());
//                    }
//                    data.setRiskType(text);
                    //---------------处理字典转义
                    cachedDataList.add(data);
                    if (cachedDataList.size() >= BATCH_SAVE_COUNT) {
                        saveData();
                        // 存储完成清理 list
                        cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_SAVE_COUNT);
                    }
                }

                /**
                 * 所有数据解析完成后才会调用
                 */
                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    saveData();
                }

                /**
                 * 加上存储数据库
                 */
                private void saveData() {
                    saveBatch(cachedDataList);
                }

            }).sheet().doRead();
        } catch (Exception e) {
            return Result.error("导入失败", e.getMessage());
        }
        return Result.OK("导入成功");
    }


    /**
     * 实时数据
     *
     * @param monitorId
     * @param authorityType
     * @return
     */
    @Override
    public List<MonitorRealtimeVo> queryMonitorRealtimeListVo(String monitorId, Integer authorityType,Integer type) {
        //拿用户信息id
        LoginUser sysUser = commonApi.getLoginUser();
        String id=sysUser.getId();
        MPJLambdaWrapper<BaseMonitor> wrapper = new MPJLambdaWrapper<>();
        wrapper
                .selectAs(BaseMonitor::getId, "monitor_id")
                .selectAs(BaseMonitor::getCode, "monitor_code")
                .selectAs(BaseMonitor::getName, "monitor_name")
                .selectAs(DataRealtime::getId, "realtime_id")
                .select(DataRealtime::getCollectTime)
                .select(DataRealtime::getUploadTime)
                .select(DataRealtime::getValue)
                .select(DataRealtime::getUnit)
                .select(DataRealtime::getRemark)
                .leftJoin(BaseMonitorRelate.class, BaseMonitorRelate::getMonitorPointId, BaseMonitor::getId)
                .leftJoin(DataRealtime.class, DataRealtime::getDataId, BaseMonitorRelate::getDataId);
               // .innerJoin(BaseMonitorResponsibility.class, BaseMonitorResponsibility::getPointId, BaseMonitor::getId);


        if (org.apache.commons.lang3.StringUtils.isNotBlank(monitorId)) {
            wrapper.eq(BaseMonitor::getId, monitorId);
        }
        //监测点类型
        if(type != null){
            wrapper.eq(BaseMonitor::getType, type);
        }
        //责权类型
        if (authorityType != null) {

            wrapper.innerJoin(BaseMonitorResponsibility.class, BaseMonitorResponsibility::getPointId, BaseMonitor::getId)
            .eq(BaseMonitorResponsibility::getAuthorityType, authorityType)
            .eq(BaseMonitorResponsibility::getResponsiblePerson, id);
        }

        wrapper.orderByDesc(DataRealtime::getCollectTime);

        List<Map<String, Object>> rows = this.getBaseMapper().selectMaps(wrapper);

        if (rows == null || rows.isEmpty()) {
            return Collections.emptyList();
        }

        //按监测点组装
        Map<String, MonitorRealtimeVo> resultMap = new LinkedHashMap<>();
        // 监测点 -> 已加入的 realtimeId 集合
        Map<String, Set<String>> realtimeIdMap = new HashMap<>();

        for (Map<String, Object> row : rows) {

            String monitorIdKey =
                    row.get("monitor_id") == null ? null : row.get("monitor_id").toString();

            if (monitorIdKey == null) {
                continue;
            }

            MonitorRealtimeVo parent = resultMap.computeIfAbsent(monitorIdKey, k -> {
                MonitorRealtimeVo vo = new MonitorRealtimeVo();
                vo.setMonitorCode(
                        row.get("monitor_code") == null ? null : row.get("monitor_code").toString());
                vo.setMonitorName(
                        row.get("monitor_name") == null ? null : row.get("monitor_name").toString());
                vo.setRealtimeList(new ArrayList<>());
                return vo;
            });
            //  初始化该监测点的去重容器
            Set<String> realtimeIdSet =
                    realtimeIdMap.computeIfAbsent(monitorIdKey, k -> new HashSet<>());

            //可能没有实时数据（LEFT JOIN）
            if (row.get("realtime_id") != null) {

                String realtimeId = row.get("realtime_id").toString();

                // 已存在则跳过
                if (!realtimeIdSet.add(realtimeId)) {
                    continue;
                }
                DataRealtimeVo item = new DataRealtimeVo();
                item.setId(row.get("realtime_id").toString());
                item.setCollectTime((Date) row.get("collect_time"));
                item.setUploadTime((Date) row.get("upload_time"));
                item.setValue((BigDecimal) row.get("value"));
                item.setUnit(
                        row.get("unit") == null ? null : row.get("unit").toString());
                item.setRemark(
                        row.get("remark") == null ? null : row.get("remark").toString());

                parent.getRealtimeList().add(item);
            }
        }

        return new ArrayList<>(resultMap.values());
    }


}





