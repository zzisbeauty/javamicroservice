package com.hanwei.video.service;

import com.hanwei.video.entity.VideoInfo;
import com.hanwei.core.common.api.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


/**
 * @Description: 视频监控
 * @Author: hanwei
 * @Date:   2026-01-20
 * @Version: V1.0
 */
public interface IVideoInfoService extends IService<VideoInfo> {

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param videoInfo
     */
    void exportData(ServletOutputStream outputStream, Map paramMap, VideoInfo videoInfo);

    /**
     * 如果不支持直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param videoInfo
     * @return String
     */
    String exportDataToBase64(Map paramMap, VideoInfo videoInfo);

    /**
     * 通过excel导入数据
     *
     * @param file
     * @return
     */
    Result<?> importData(MultipartFile file);

    /**
     * 下载导入模板
     * @return
     */
    String getImportTemplate();
}
