package com.hanwei.workorder.service;

import com.hanwei.workorder.entity.WorkOrder;
import com.hanwei.core.common.api.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.ServletOutputStream;
import org.springframework.web.multipart.MultipartFile;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.Map;
import java.util.List;

/**
 * @Description: 工单信息表
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
public interface IWorkOrderService extends IService<WorkOrder> {

    /**
     * 新增工单（含附件管理和操作记录）
     *
     * @param workOrder 工单信息
     * @param attachIdsStr 附件ID字符串（逗号分隔）
     * @param creatorCode 创建人编号
     * @param creatorName 创建人姓名
     * @return 工单ID
     */
    String createWorkOrderWithAttachments(WorkOrder workOrder, String attachIdsStr,
                                          String creatorCode, String creatorName);


    /**
     * 工单派送
     *
     * @param workOrderId 工单ID
     * @param assigneeCode 被派送人编号
     * @param assigneeName 被派送人姓名
     * @return 是否派送成功
     */
    boolean assignWorkOrder(String workOrderId, String assigneeCode, String assigneeName);

    /**
     * 工单抄送
     *
     * @param workOrderId 工单ID
     * @param copyToCodes 抄送人编号列表
     * @param copyToNames 抄送人姓名列表
     * @return 是否抄送成功
     */
    boolean copyWorkOrder(String workOrderId, List<String> copyToCodes, List<String> copyToNames);


    // ============ 编辑工单

    /**
     * 编辑工单（含操作记录）
     *
     * @param workOrder 工单信息
     * @param editorCode 编辑人编号
     * @param editorName 编辑人姓名
     * @return 是否编辑成功
     */
    boolean editWorkOrderWithRecord(WorkOrder workOrder, String editorCode, String editorName);



    /**
     * 工单催办
     *
     * @param workOrderId 工单ID
     * @param urgerCode 催办人编号
     * @param urgerName 催办人姓名
     * @param urgeContent 催办内容
     * @return 是否催办成功
     */
    boolean urgeWorkOrder(String workOrderId, String urgerCode, String urgerName, String urgeContent);



    /**
     * 查询我的工单（创建的、抄送的、派发的）
     *
     * @param userCode 用户编号
     * @param pageNo 页码
     * @param pageSize 页大小
     * @return 工单列表
     */
    IPage<WorkOrder> getMyWorkOrders(String userCode, Integer pageNo, Integer pageSize);



    /**
     * 导出我的工单为CSV
     *
     * @param userCode 用户编号
     * @param outputStream 输出流
     */
    void exportMyWorkOrdersToCsv(String userCode, ServletOutputStream outputStream);


    // ==============================================================================


    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param workOrder
     */
    void exportData(ServletOutputStream outputStream, Map paramMap, WorkOrder workOrder);

    /**
     * 如果不支持直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param workOrder
     * @return String
     */
    String exportDataToBase64(Map paramMap, WorkOrder workOrder);

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
