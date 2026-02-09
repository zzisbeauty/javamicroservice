package com.hanwei.data.service;

import com.hanwei.data.entity.DataRealtime;
import com.hanwei.core.common.api.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.data.entity.vo.DataRealtimeVo;
import com.hanwei.data.entity.vo.MonitorRealtimeVo;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * @Description: 实时数据
 * @Author: hanwei
 * @Date:   2026-01-22
 * @Version: V1.0
 */
public interface IDataRealtimeService extends IService<DataRealtime> {

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param dataRealtime
     */
    void exportData(ServletOutputStream outputStream, Map paramMap, DataRealtime dataRealtime);

    /**
     * 如果不支持直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param dataRealtime
     * @return String
     */
    String exportDataToBase64(Map paramMap, DataRealtime dataRealtime);

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
