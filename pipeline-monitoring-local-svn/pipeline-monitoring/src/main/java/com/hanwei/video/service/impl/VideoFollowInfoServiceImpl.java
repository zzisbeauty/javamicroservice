package com.hanwei.video.service.impl;


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
import com.hanwei.core.base.QueryGenerator;
import com.hanwei.core.common.api.CommonAPI;
import com.hanwei.core.common.api.vo.LoginUser;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.video.entity.VideoFollowInfo;
import com.hanwei.video.entity.VideoInfo;
import com.hanwei.video.entity.dto.VideoFollowInfoDto;
import com.hanwei.video.entity.vo.VideoFollowInfoVo;
import com.hanwei.video.mapper.VideoFollowInfoMapper;
import com.hanwei.video.service.IVideoFollowInfoService;
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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;


/**
 * @Description: 视频监控关注信息
 * @Author: hanwei
 * @Date:   2026-01-20
 * @Version: V1.0
 */
@Service
@RequiredArgsConstructor
public class VideoFollowInfoServiceImpl extends ServiceImpl<VideoFollowInfoMapper, VideoFollowInfo> implements IVideoFollowInfoService {

    @Value("${excel.batchSaveCount}")
    private Integer BATCH_SAVE_COUNT;

    private final CommonAPI commonApi;

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param videoFollowInfo
     */
    @Override
    public void exportData(ServletOutputStream outputStream, Map paramMap, VideoFollowInfo videoFollowInfo) {
        QueryWrapper<VideoFollowInfo> queryWrapper = QueryGenerator.initQueryWrapper(videoFollowInfo, paramMap);
        List<VideoFollowInfo> list = list(queryWrapper);
        EasyExcel.write(outputStream, VideoFollowInfo.class).sheet("视频监控关注信息").doWrite(list);
    }

    /**
     * 如果网关不知道直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param videoFollowInfo
     */
    @Override
    public String exportDataToBase64(Map paramMap, VideoFollowInfo videoFollowInfo) {
        QueryWrapper<VideoFollowInfo> queryWrapper = QueryGenerator.initQueryWrapper(videoFollowInfo, paramMap);
        List<VideoFollowInfo> list = list(queryWrapper);
        //字典值转换
//        List<JSON> listJson = commonApi.translateResultByDict(list);
//        List<VideoFollowInfo> result = listJson.stream().map(e -> JSON.toJavaObject(e,VideoFollowInfo.class)).collect(Collectors.toList());
        String excelContent = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        EasyExcel.write(outputStream, VideoFollowInfo.class).sheet("视频监控关注信息").doWrite(list);
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
            EasyExcel.read(file.getInputStream(), VideoFollowInfo.class, new ReadListener<VideoFollowInfo>() {

                private List<VideoFollowInfo> cachedDataList = new ArrayList<>();

                /**
                 * 每次读取都会调用
                 * @param data
                 * @param context
                 */
                @SneakyThrows
                @Override
                public void invoke(VideoFollowInfo data, AnalysisContext context) {
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
            ClassPathResource classPathResource = new ClassPathResource("template/VideoFollowInfo.xlsx");
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
 * 视频关注信息列表
 * @param dto
 * @param pageNo
 * @param pageSize
 * @return
 */
    @Override
    public IPage<?> videoFollowInfoVoPageList(VideoFollowInfoDto dto, Integer pageNo, Integer pageSize) {
        //创建查询对象
        MPJLambdaWrapper<VideoFollowInfo> wrapper = new MPJLambdaWrapper<>();
        //拿用户信息id
        LoginUser sysUser = commonApi.getLoginUser();
        String id=sysUser.getId();
        //取当前登录用户关注信息
        wrapper.selectAll(VideoFollowInfo.class)
               // .eq(VideoFollowInfo::getUserId, id)
                //映射  字段名称一样不需要映射
//                .selectAs(VideoInfo::getCameraCode, VideoFollowInfoVo::getCameraCode)
//                .selectAs(VideoInfo::getCameraType, VideoFollowInfoVo::getCameraType)
//                .selectAs(VideoInfo::getVideoStreamType, VideoFollowInfoVo::getVideoStreamType)
//                .selectAs(VideoInfo::getPushStreamUrl, VideoFollowInfoVo::getPushStreamUrl)
//                .selectAs(VideoInfo::getStatus, VideoFollowInfoVo::getStatus)
                //关联拿视频信息
                .leftJoin(VideoInfo.class, VideoInfo::getId, VideoFollowInfo::getVideoId)
                //如果不为空
//              .eq(!dto.getVideoname().isBlank(), VideoInfo::getVideoName, dto.getVideoname())
//                .and(consumer ->
//                        consumer.like(!dto.getKeyword().isBlank(), LargeUser::getName, dto.getKeyword())
//                                .or()
//                                .like(!dto.getKeyword().isBlank(), District::getName, dto.getKeyword()));
        ;
//        if(!dto.getDistrictId().equals("")){
//            wrapper.eq(LargeUser::getDistrictId, dto.getDistrictId());
//        }

        if(!dto.getVideoname().isBlank()){
            wrapper.like(VideoInfo::getVideoName, dto.getVideoname());
        }


        Page<VideoFollowInfoVo> page = new Page<>(pageNo, pageSize);
        return this.getBaseMapper().selectJoinPage(page, VideoFollowInfoVo.class, wrapper);
    }



}
