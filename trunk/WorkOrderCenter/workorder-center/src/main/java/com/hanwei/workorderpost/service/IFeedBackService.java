package com.hanwei.workorderpost.service;

import com.hanwei.workorderpost.entity.FeedBack;
import com.hanwei.core.common.api.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


/**
 * @Description: 工单后处理记录-回访信息
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
public interface IFeedBackService extends IService<FeedBack> {

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param feedBack
     */
    void exportData(ServletOutputStream outputStream, Map paramMap, FeedBack feedBack);

    /**
     * 如果不支持直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param feedBack
     * @return String
     */
    String exportDataToBase64(Map paramMap, FeedBack feedBack);

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
