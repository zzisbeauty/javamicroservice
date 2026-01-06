package com.hanwei.attachment.service;

import com.hanwei.attachment.entity.Attachment;
import com.hanwei.core.common.api.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


/**
 * @Description: 附件信息记录
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
public interface IAttachmentService extends IService<Attachment> {

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param attachment
     */
    void exportData(ServletOutputStream outputStream, Map paramMap, Attachment attachment);

    /**
     * 如果不支持直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param attachment
     * @return String
     */
    String exportDataToBase64(Map paramMap, Attachment attachment);

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
