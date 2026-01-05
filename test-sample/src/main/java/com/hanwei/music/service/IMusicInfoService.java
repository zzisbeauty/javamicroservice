package com.hanwei.music.service;

import com.hanwei.music.entity.MusicInfo;
import com.hanwei.core.common.api.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


/**
 * @Description: 音乐专辑管理器
 * @Author: hanwei
 * @Date:   2026-01-04
 * @Version: V1.0
 */
public interface IMusicInfoService extends IService<MusicInfo> {

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param musicInfo
     */
    void exportData(ServletOutputStream outputStream, Map paramMap, MusicInfo musicInfo);

    /**
     * 如果不支持直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param musicInfo
     * @return String
     */
    String exportDataToBase64(Map paramMap, MusicInfo musicInfo);

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
