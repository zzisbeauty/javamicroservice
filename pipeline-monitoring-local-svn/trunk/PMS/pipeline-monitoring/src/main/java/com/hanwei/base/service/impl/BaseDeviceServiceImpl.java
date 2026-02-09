package com.hanwei.base.service.impl;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.ListUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.hanwei.base.entity.dto.BaseDeviceDto;
import com.hanwei.base.entity.vo.BaseDeviceVo;
import com.hanwei.core.base.QueryGenerator;
import com.hanwei.core.common.api.CommonAPI;
import com.hanwei.core.common.api.vo.LoginUser;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.base.entity.BaseDevice;
import com.hanwei.base.mapper.BaseDeviceMapper;
import com.hanwei.base.service.IBaseDeviceService;
import com.hanwei.core.util.DateUtils;
import com.hanwei.data.entity.DataRealtime;
import com.hanwei.video.entity.VideoFollowInfo;
import com.hanwei.video.entity.VideoInfo;
import com.hanwei.video.entity.dto.VideoFollowInfoDto;
import com.hanwei.video.entity.vo.VideoFollowInfoVo;
import jakarta.servlet.ServletOutputStream;
import lombok.SneakyThrows;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;


/**
 * @Description: 设备信息
 * @Author: hanwei
 * @Date:   2026-01-22
 * @Version: V1.0
 */
@Service
@RequiredArgsConstructor
public class BaseDeviceServiceImpl extends ServiceImpl<BaseDeviceMapper, BaseDevice> implements IBaseDeviceService {

    @Value("${excel.batchSaveCount}")
    private Integer BATCH_SAVE_COUNT;

    private final CommonAPI commonApi;

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param baseDevice
     */
    @Override
    public void exportData(ServletOutputStream outputStream, Map paramMap, BaseDevice baseDevice) {
        QueryWrapper<BaseDevice> queryWrapper = QueryGenerator.initQueryWrapper(baseDevice, paramMap);
        List<BaseDevice> list = list(queryWrapper);
        EasyExcel.write(outputStream, BaseDevice.class).sheet("设备信息").doWrite(list);
    }

    /**
     * 如果网关不知道直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param baseDevice
     */
    @Override
    public String exportDataToBase64(Map paramMap, BaseDevice baseDevice) {
        QueryWrapper<BaseDevice> queryWrapper = QueryGenerator.initQueryWrapper(baseDevice, paramMap);
        List<BaseDevice> list = list(queryWrapper);
        //字典值转换
//        List<JSON> listJson = commonApi.translateResultByDict(list);
//        List<BaseDevice> result = listJson.stream().map(e -> JSON.toJavaObject(e,BaseDevice.class)).collect(Collectors.toList());
        String excelContent = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        EasyExcel.write(outputStream, BaseDevice.class).sheet("设备信息").doWrite(list);
        excelContent = Base64.getEncoder().encodeToString(outputStream.toByteArray());
        return excelContent;
    }

    /**
     * 如果网关不知道直接获取文件流，转为base64后返回前端
     *
     * @param file
     */
    @Override
    public Result<?> importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), BaseDevice.class, new ReadListener<BaseDevice>() {

                private List<BaseDevice> cachedDataList = new ArrayList<>();

                /**
                 * 每次读取都会调用
                 * @param data
                 * @param context
                 */
                @SneakyThrows
                @Override
                public void invoke(BaseDevice data, AnalysisContext context) {
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
     * 下载导入模板
     * @return
     */
    @Override
    public String getImportTemplate() {
        String excelContent = null;
        try {
            // 模版文件
            ClassPathResource classPathResource = new ClassPathResource("template/BaseDevice.xlsx");
            // 方式一：路径
            String templateFileName = classPathResource.getFile().getPath();

            if (StringUtils.isNotBlank(templateFileName)) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ExcelWriter excelWriter = EasyExcel.write(outputStream).withTemplate(templateFileName).excelType(ExcelTypeEnum.XLSX).autoCloseStream(Boolean.FALSE).build();
                excelWriter.finish();
                excelContent = Base64.getEncoder().encodeToString(outputStream.toByteArray());
            }
            return excelContent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return excelContent;
    }

    /**
     * 获取设备信息
     * @param dto
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public IPage<BaseDeviceVo> baseDeviceVoPageList(BaseDeviceDto dto, Integer pageNo, Integer pageSize) {
        // 创建查询对象
        MPJLambdaWrapper<BaseDevice> wrapper = new MPJLambdaWrapper<>();

        wrapper.selectAll(BaseDevice.class)
                .selectAs(DataRealtime::getUploadTime, BaseDeviceVo::getLastDataTime)
                .leftJoin(DataRealtime.class, DataRealtime::getDeviceId, BaseDevice::getId);


        if (dto != null) {
            // deviceType 默认 0 表示不筛选
            if (dto.getDeviceType() != 0) {
                wrapper.eq(BaseDevice::getDeviceType, dto.getDeviceType());
            }

            // runStatus 默认 0 表示不筛选
            if (dto.getRunStatus() != 0) {
                wrapper.eq(BaseDevice::getRunStatus, dto.getRunStatus());
            }

            if (StringUtils.isNotBlank(dto.getSerialNumber())) {
                wrapper.like(BaseDevice::getDeviceSerialNumber, dto.getSerialNumber());
            }
        }


        // 分页对象
        Page<BaseDeviceVo> page = new Page<>(pageNo, pageSize);

        // 执行分页查询
        return this.getBaseMapper().selectJoinPage(page, BaseDeviceVo.class, wrapper);
    }




}
