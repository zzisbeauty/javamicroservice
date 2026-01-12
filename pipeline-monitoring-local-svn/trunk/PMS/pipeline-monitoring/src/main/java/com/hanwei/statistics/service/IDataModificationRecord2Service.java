package com.hanwei.statistics.service;

import com.hanwei.statistics.entity.DataModificationRecord2;
import com.hanwei.core.common.api.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


/**
 * @Description: 数据修改记录表2
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
public interface IDataModificationRecord2Service extends IService<DataModificationRecord2> {

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param dataModificationRecord2
     */
    void exportData(ServletOutputStream outputStream, Map paramMap, DataModificationRecord2 dataModificationRecord2);

    /**
     * 如果不支持直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param dataModificationRecord2
     * @return String
     */
    String exportDataToBase64(Map paramMap, DataModificationRecord2 dataModificationRecord2);

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
