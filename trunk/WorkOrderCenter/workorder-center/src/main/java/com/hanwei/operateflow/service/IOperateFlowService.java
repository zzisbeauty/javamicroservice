package com.hanwei.operateflow.service;

import com.hanwei.operateflow.entity.OperateFlow;
import com.hanwei.core.common.api.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


/**
 * @Description: 操作流程记录信息
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
public interface IOperateFlowService extends IService<OperateFlow> {


    /**
     * 记录问题操作流程
     *
     * @param problemId 问题ID
     * @param operationType 操作类型
     * @param handlerName 处理人姓名
     * @param handlerCode 处理人编号
     * @param statusBefore 处理前状态
     * @param statusAfter 处理后状态
     * @param remark 备注
     */
    void recordProblemOperation(String problemId, String operationType,
                                String handlerName, String handlerCode,
                                String statusBefore, String statusAfter,
                                String remark);



    /**
     * 记录问题编辑操作流程
     *
     * @param problemId 问题ID
     * @param operationType 操作类型
     * @param handlerName 处理人姓名
     * @param handlerCode 处理人编号
     * @param statusBefore 处理前状态
     * @param statusAfter 处理后状态
     * @param remark 备注
     */
    void recordProblemEditOperation(String problemId, String operationType,
                                    String handlerName, String handlerCode,
                                    String statusBefore, String statusAfter,
                                    String remark);



    /**
     * 记录问题删除操作流程
     *
     * @param problemId 问题ID
     * @param operationType 操作类型
     * @param handlerName 处理人姓名
     * @param handlerCode 处理人编号
     * @param statusBefore 处理前状态
     * @param statusAfter 处理后状态
     * @param remark 备注
     */
    void recordProblemDeleteOperation(String problemId, String operationType,
                                      String handlerName, String handlerCode,
                                      String statusBefore, String statusAfter,
                                      String remark);





    /**
     * 记录工单操作流程 - 专门在工单初次被创建时调用
     *
     * @param workOrderId 工单ID
     * @param operationType 操作类型
     * @param handlerName 处理人姓名
     * @param handlerCode 处理人编号
     * @param statusBefore 处理前状态
     * @param statusAfter 处理后状态
     * @param remark 备注
     */
    void recordWorkOrderOperation(String workOrderId, String operationType,
                                  String handlerName, String handlerCode,
                                  String statusBefore, String statusAfter,
                                  String remark);




    /**
     * 记录工单编辑/删除操作流程  -  专门在工单被编辑、删除时调用
     *
     * @param workOrderId 工单ID
     * @param operationType 操作类型
     * @param handlerName 处理人姓名
     * @param handlerCode 处理人编号
     * @param statusBefore 处理前状态
     * @param statusAfter 处理后状态
     * @param remark 备注
     */
    void recordWorkOrderEditOperation(String workOrderId, String operationType,
                                      String handlerName, String handlerCode,
                                      String statusBefore, String statusAfter,
                                      String remark);





    /**
     * 记录工单派送操作流程
     */
    void recordWorkOrderAssignOperation(String workOrderId, String operationType,
                                        String assignerCode, String assignerName,
                                        String assigneeCode, String assigneeName,
                                        String statusBefore, String statusAfter,
                                        String remark);



    /**
     * 记录工单抄送操作流程
     */
    void recordWorkOrderCopyOperation(String workOrderId, String operationType,
                                      String copierCode, String copierName,
                                      String copyToCode, String copyToName,
                                      String statusBefore, String statusAfter,
                                      String remark);




    /**
     * 记录工单催办操作流程
     *
     * @param workOrderId 工单ID
     * @param operationType 操作类型
     * @param urgerCode 催办人编号
     * @param urgerName 催办人姓名
     * @param assigneeCode 被催办人编号
     * @param assigneeName 被催办人姓名
     * @param statusBefore 处理前状态
     * @param statusAfter 处理后状态
     * @param remark 备注
     */
    void recordWorkOrderUrgeOperation(String workOrderId, String operationType,
                                      String urgerCode, String urgerName,
                                      String assigneeCode, String assigneeName,
                                      String statusBefore, String statusAfter,
                                      String remark);














    // ============================// ============================// ============================



    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param operateFlow
     */
    void exportData(ServletOutputStream outputStream, Map paramMap, OperateFlow operateFlow);

    /**
     * 如果不支持直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param operateFlow
     * @return String
     */
    String exportDataToBase64(Map paramMap, OperateFlow operateFlow);

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
