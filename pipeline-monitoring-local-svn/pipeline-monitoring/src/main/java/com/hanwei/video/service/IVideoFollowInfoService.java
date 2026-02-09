package com.hanwei.video.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hanwei.video.entity.VideoFollowInfo;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.video.entity.dto.VideoFollowInfoDto;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


/**
 * @Description: 视频监控关注信息
 * @Author: hanwei
 * @Date:   2026-01-20
 * @Version: V1.0
 */
public interface IVideoFollowInfoService extends IService<VideoFollowInfo> {

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param videoFollowInfo
     */
    void exportData(ServletOutputStream outputStream, Map paramMap, VideoFollowInfo videoFollowInfo);

    /**
     * 如果不支持直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param videoFollowInfo
     * @return String
     */
    String exportDataToBase64(Map paramMap, VideoFollowInfo videoFollowInfo);

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

    /**
     * 获取视频关注信息列表分页
     *
     * @param dto
     * @param pageNo
     * @param pageSize
     * @return
     */
    IPage<?> videoFollowInfoVoPageList(VideoFollowInfoDto dto, Integer pageNo, Integer pageSize);
}


