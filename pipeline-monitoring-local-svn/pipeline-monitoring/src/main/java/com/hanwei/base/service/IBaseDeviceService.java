package com.hanwei.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hanwei.base.entity.BaseDevice;
import com.hanwei.base.entity.dto.BaseDeviceDto;
import com.hanwei.core.common.api.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


/**
 * @Description: 设备信息
 * @Author: hanwei
 * @Date:   2026-01-22
 * @Version: V1.0
 */
public interface IBaseDeviceService extends IService<BaseDevice> {

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param baseDevice
     */
    void exportData(ServletOutputStream outputStream, Map paramMap, BaseDevice baseDevice);

    /**
     * 如果不支持直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param baseDevice
     * @return String
     */
    String exportDataToBase64(Map paramMap, BaseDevice baseDevice);

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
     * 获取设备信息
     *
     * @param dto
     * @param pageNo
     * @param pageSize
     * @return
     */
    IPage<?> baseDeviceVoPageList(BaseDeviceDto dto, Integer pageNo, Integer pageSize);
}
