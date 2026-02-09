package com.hanwei.problem.service;

import com.hanwei.problem.entity.ProblemWrokOrderRelation;
import com.hanwei.core.common.api.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


/**
 * @Description: 问题工单关联信息
 * @Author: hanwei
 * @Date:   2026-01-05
 * @Version: V1.0
 */
public interface IProblemWrokOrderRelationService extends IService<ProblemWrokOrderRelation> {
    /**
     * 保存问题工单关联关系
     *
     * @param problemId 问题ID
     * @param problemNumber 问题编号
     * @param workOrderId 工单ID
     * @param workOrderNumber 工单编号
     * @return 是否保存成功
     */
    boolean saveProblemWorkOrderRelation(String problemId, String problemNumber,
                                         String workOrderId, String workOrderNumber);


    // ============================================================================

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param problemWrokOrderRelation
     */
    void exportData(ServletOutputStream outputStream, Map paramMap, ProblemWrokOrderRelation problemWrokOrderRelation);

    /**
     * 如果不支持直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param problemWrokOrderRelation
     * @return String
     */
    String exportDataToBase64(Map paramMap, ProblemWrokOrderRelation problemWrokOrderRelation);

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
