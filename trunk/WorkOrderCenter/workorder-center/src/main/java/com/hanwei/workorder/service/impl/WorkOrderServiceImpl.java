package com.hanwei.workorder.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.ListUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.attachment.entity.Attachment;
import com.hanwei.attachment.service.IAttachmentService;
import com.hanwei.core.base.QueryGenerator;
import com.hanwei.core.common.api.CommonAPI;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.operateflow.service.IOperateFlowService;
import com.hanwei.workorder.entity.WorkOrder;
import com.hanwei.workorder.mapper.WorkOrderMapper;
import com.hanwei.workorder.service.IWorkOrderService;
import com.hanwei.workorderpost.entity.Participant;
import com.hanwei.workorderpost.entity.UrgeInfo;
import com.hanwei.workorderpost.service.IParticipantService;
import com.hanwei.workorderpost.service.IUrgeInfoService;
import jakarta.servlet.ServletOutputStream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 工单信息表
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Service
@Slf4j
public class WorkOrderServiceImpl extends ServiceImpl<WorkOrderMapper, WorkOrder> implements IWorkOrderService {

    @Value("${excel.batchSaveCount}")
    private Integer BATCH_SAVE_COUNT;

    @Autowired
    private CommonAPI commonApi;

    @Autowired
    private IOperateFlowService operateFlowService;

    @Autowired
    private IParticipantService participantService;

    @Autowired
    private IAttachmentService attachmentService;


    @Autowired
    private IUrgeInfoService urgeInfoService;





    // ==========================// ==========================// ========================== 新增工单的操作

    @Override
    public String createWorkOrderWithAttachments(WorkOrder workOrder, String attachIdsStr,
                                                 String creatorCode, String creatorName) {
        try {
            // 1. 生成工单编号（TN+年月日+5位随机序列）
            String workOrderNumber = generateWorkOrderNumber();
            workOrder.setWorkOrderNumber(workOrderNumber);

            // 2. 设置默认值
            if (workOrder.getCreateBy() == null || workOrder.getCreateBy().trim().isEmpty()) {
                workOrder.setCreateBy(creatorCode != null ? creatorCode : "system");
            }
            if (workOrder.getTenementId() == null || workOrder.getTenementId().trim().isEmpty()) {
                workOrder.setTenementId("default");
            }

            // 3. 保存工单信息
            boolean saved = this.save(workOrder);
            if (!saved) {
                throw new RuntimeException("工单保存失败");
            }

            String workOrderId = workOrder.getId();
            log.info("工单保存成功，工单ID: {}", workOrderId);

            // 4. 处理附件信息
            if (attachIdsStr != null && !attachIdsStr.trim().isEmpty()) {
                String[] attachIdArray = attachIdsStr.split(",");
                for (String attachId : attachIdArray) {
                    if (attachId != null && !attachId.trim().isEmpty()) {
                        Attachment attachment = new Attachment();
                        attachment.setProcessId(workOrderId);
                        attachment.setFileUrl(attachId.trim());

                        boolean attachmentSaved = attachmentService.save(attachment);
                        if (attachmentSaved) {
                            log.info("附件保存成功: {}", attachId.trim());
                        } else {
                            log.error("附件保存失败: {}", attachId.trim());
                        }
                    }
                }
            }

            // 5. 记录工单创建操作流程
            operateFlowService.recordWorkOrderOperation(
                    workOrderId,
                    "新建工单",
                    creatorName,
                    creatorCode,
                    null,
                    workOrder.getStatus(),
                    "工单创建"
            );

            // 6. 记录参与人信息（创建者作为处理人）
            // participantService.addParticipant(workOrderId, creatorCode, creatorName, "处理人", true);
            Participant participant = new Participant();
            participant.setWorkOrderId(workOrderId);
            participant.setUserCode(creatorCode);
            participant.setUserName(creatorName);
            participant.setRole("处理人");
            participant.setIsActive(true);
            participantService.save(participant);

            return workOrderId;

        } catch (Exception e) {
            log.error("创建工单失败: " + e.getMessage(), e);
            throw new RuntimeException("创建工单失败: " + e.getMessage());
        }
    }

    /**
     * 生成工单编号（TN+年月日+5位随机序列）
     */
    private String generateWorkOrderNumber() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        Random random = new Random();
        int sequence = random.nextInt(100000);
        return "TN" + dateStr + String.format("%05d", sequence);
    }


    // ==========================// ==========================// ========================== 工单派送和抄送动作


    @Override
    public boolean assignWorkOrder(String workOrderId, String assigneeCode, String assigneeName) {
        try {
            // 1. 查询工单信息
            WorkOrder workOrder = this.getById(workOrderId);
            if (workOrder == null) {
                throw new RuntimeException("工单不存在");
            }

            // 2. 更新工单处理人
            workOrder.setCurrentHandler(assigneeName);
            workOrder.setCurrentUserCode(assigneeCode);  // 使用数据库字段名
            boolean updated = this.updateById(workOrder);
            if (!updated) {
                throw new RuntimeException("工单更新失败");
            }

            // 3. 检查并添加参与人记录（避免重复）
            QueryWrapper<Participant> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("work_order_id", workOrderId)  // 使用数据库字段名
                    .eq("user_code", assigneeCode)      // 使用数据库字段名
                    .eq("user_name", assigneeName)      // 使用数据库字段名
                    .eq("role", "处理人");

            Participant existingParticipant = participantService.getOne(queryWrapper);
            if (existingParticipant == null) {
                // 创建新的参与人记录
                Participant assignee = Participant.builder()
                        .workOrderId(workOrderId)           // 使用实体字段名
                        .userCode(assigneeCode)              // 使用实体字段名
                        .userName(assigneeName)              // 使用实体字段名
                        .role("处理人")                      // 使用实体字段名
                        .isActive(true)                      // 使用实体字段名
                        .build();
                participantService.save(assignee);
            }

            // 4. 记录操作流程
            operateFlowService.recordWorkOrderAssignOperation(
                    workOrderId,
                    "派送工单",
                    workOrder.getCreateBy(),
                    workOrder.getCreateBy(),
                    assigneeCode,
                    assigneeName,
                    workOrder.getStatus(),
                    workOrder.getStatus(),
                    "工单派送给" + assigneeName
            );

            return true;

        } catch (Exception e) {
            log.error("派送工单失败: " + e.getMessage(), e);
            return false;
        }
    }



    @Override
    public boolean copyWorkOrder(String workOrderId, List<String> copyToCodes, List<String> copyToNames) {
        try {
            // 1. 查询工单信息
            WorkOrder workOrder = this.getById(workOrderId);
            if (workOrder == null) {
                throw new RuntimeException("工单不存在");
            }

            // 2. 为每个抄送人创建参与人记录和操作记录
            for (int i = 0; i < copyToCodes.size(); i++) {
                String copyToCode = copyToCodes.get(i);
                String copyToName = copyToNames.get(i);

                // 检查并添加参与人记录（避免重复）
                QueryWrapper<Participant> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("work_order_id", workOrderId)
                        .eq("user_code", copyToCode)
                        .eq("user_name", copyToName)
                        .eq("role", "抄送人");

                Participant existingParticipant = participantService.getOne(queryWrapper);
                if (existingParticipant == null) {
                    Participant copyTo = Participant.builder()
                            .workOrderId(workOrderId)
                            .userCode(copyToCode)
                            .userName(copyToName)
                            .role("抄送人")
                            .isActive(false)  // 抄送人只有催办权限
                            .build();
                    participantService.save(copyTo);
                }

                // 为每个抄送人单独记录操作流程
                operateFlowService.recordWorkOrderCopyOperation(
                        workOrderId,
                        "抄送工单",
                        workOrder.getCreateBy(),
                        workOrder.getCreateBy(),
                        copyToCode,
                        copyToName,
                        workOrder.getStatus(),
                        workOrder.getStatus(),
                        "工单抄送给" + copyToName
                );
            }

            return true;

        } catch (Exception e) {
            log.error("抄送工单失败: " + e.getMessage(), e);
            return false;
        }
    }


    // ========== 编辑工单

    @Override
    public boolean editWorkOrderWithRecord(WorkOrder workOrder, String editorCode, String editorName) {
        try {
            // 1. 查询原工单信息
            WorkOrder originalWorkOrder = this.getById(workOrder.getId());
            if (originalWorkOrder == null) {
                throw new RuntimeException("工单不存在");
            }

            // 2. 更新工单信息
            boolean updated = this.updateById(workOrder);
            if (!updated) {
                throw new RuntimeException("工单更新失败");
            }

            // 3. 记录操作流程
            operateFlowService.recordWorkOrderEditOperation(
                    workOrder.getId(),
                    "编辑工单",
                    editorCode,
                    editorName,
                    originalWorkOrder.getStatus(),
                    workOrder.getStatus(),
                    "编辑工单信息"
            );

            return true;

        } catch (Exception e) {
            log.error("编辑工单失败: " + e.getMessage(), e);
            throw new RuntimeException("编辑工单失败: " + e.getMessage());
        }
    }



    @Override
    public boolean urgeWorkOrder(String workOrderId, String urgerCode, String urgerName, String urgeContent) {
        try {
            // 1. 查询工单信息
            WorkOrder workOrder = this.getById(workOrderId);
            if (workOrder == null) {
                throw new RuntimeException("工单不存在");
            }

            // 2. 验证催办权限 - 验证催办人是否为该工单的参与人（抄送人或处理人）
            QueryWrapper<Participant> urgerWrapper = new QueryWrapper<>();
            urgerWrapper.eq("work_order_id", workOrderId)
                    .eq("user_code", urgerCode)
                    .in("role", Arrays.asList("抄送人", "处理人"));

            List<Participant> urgerParticipants = participantService.list(urgerWrapper);
            if (urgerParticipants.isEmpty()) {
                throw new RuntimeException("您没有催办权限，只有该工单的参与人可以催办");
            }

            // 3. 从工单表获取被催办人信息（当前执行人）
            String assigneeCode = workOrder.getCurrentUserCode();
            String assigneeName = workOrder.getCurrentHandler();

            if (StringUtils.isEmpty(assigneeCode) || StringUtils.isEmpty(assigneeName)) {
                throw new RuntimeException("该工单没有当前执行人，无法催办");
            }

            // 4. 创建催办记录
            UrgeInfo urgeInfo = UrgeInfo.builder()
                    .workOrderId(workOrderId)
                    .workOrderNumber(workOrder.getWorkOrderNumber())
                    .urgeAt(new Date())
                    .urgeContent(urgeContent)
                    .assigneeCode(assigneeCode)  // 被催办人编号
                    .assigneeName(assigneeName)  // 被催办人姓名
                    .isRead(false)
                    .createTime(new Date())
                    .createBy(urgerCode)
                    .updateTime(new Date())
                    .updateBy(urgerCode)
                    .build();

            boolean urgeSaved = urgeInfoService.save(urgeInfo);
            if (!urgeSaved) {
                throw new RuntimeException("催办记录保存失败");
            }

            // 5. 记录操作流程
            operateFlowService.recordWorkOrderUrgeOperation(
                    workOrderId,
                    "催办工单",
                    urgerCode,
                    urgerName,
                    assigneeCode,
                    assigneeName,
                    workOrder.getStatus(),
                    workOrder.getStatus(),
                    urgeContent
            );

            return true;

        } catch (Exception e) {
            log.error("催办工单失败: " + e.getMessage(), e);
            throw new RuntimeException("催办工单失败: " + e.getMessage());
        }
    }




    @Override
    public IPage<WorkOrder> getMyWorkOrders(String userCode, Integer pageNo, Integer pageSize) {
        try {
            // 1. 从 participant_info 表查询用户涉及的所有工单ID
            QueryWrapper<Participant> participantWrapper = new QueryWrapper<>();
            participantWrapper.eq("user_code", userCode)
                    .select("work_order_id");  // 只查询工单ID字段

            List<Participant> participants = participantService.list(participantWrapper);

            // 提取所有工单ID
            List<String> workOrderIds = participants.stream()
                    .map(Participant::getWorkOrderId)
                    .distinct()
                    .collect(Collectors.toList());

            if (workOrderIds.isEmpty()) {
                // 如果没有参与任何工单，返回空结果
                return new Page<>(pageNo, pageSize);
            }

            // 2. 根据工单ID列表查询工单详细信息
            Page<WorkOrder> page = new Page<>(pageNo, pageSize);
            QueryWrapper<WorkOrder> workOrderWrapper = new QueryWrapper<>();
            workOrderWrapper.in("id", workOrderIds)
                    .orderByDesc("create_time");

            return this.page(page, workOrderWrapper);

        } catch (Exception e) {
            log.error("查询我的工单失败: " + e.getMessage(), e);
            throw new RuntimeException("查询我的工单失败: " + e.getMessage());
        }
    }




    @Override
    public void exportMyWorkOrdersToCsv(String userCode, ServletOutputStream outputStream) {
        try {
            // 1. 调用我的工单查询方法获取数据
            List<WorkOrder> workOrders = getMyWorkOrders(userCode, 1, Integer.MAX_VALUE).getRecords();

            // 2. 排除Object类型字段，避免转换错误
            Set<String> excludeColumnFiledNames = new HashSet<>();
            excludeColumnFiledNames.add("workOrderContent");
            excludeColumnFiledNames.add("isFollowUp");

            // 3. 使用EasyExcel导出CSV
            EasyExcel.write(outputStream, WorkOrder.class)
                    .excludeColumnFiledNames(excludeColumnFiledNames)
                    .excelType(ExcelTypeEnum.CSV)
                    .sheet("我的工单")
                    .doWrite(workOrders);

        } catch (Exception e) {
            log.error("导出我的工单CSV失败: " + e.getMessage(), e);
            throw new RuntimeException("导出失败: " + e.getMessage());
        }
    }

    // =========================================================================================


    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param workOrder
     */
    @Override
    public void exportData(ServletOutputStream outputStream, Map paramMap, WorkOrder workOrder) {
        QueryWrapper<WorkOrder> queryWrapper = QueryGenerator.initQueryWrapper(workOrder, paramMap);
        List<WorkOrder> list = list(queryWrapper);
        EasyExcel.write(outputStream, WorkOrder.class).sheet("工单信息表").doWrite(list);
    }

    /**
     * 如果网关不知道直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param workOrder
     */
    @Override
    public String exportDataToBase64(Map paramMap, WorkOrder workOrder) {
        QueryWrapper<WorkOrder> queryWrapper = QueryGenerator.initQueryWrapper(workOrder, paramMap);
        List<WorkOrder> list = list(queryWrapper);
        //字典值转换
        //List<JSON> listJson = commonApi.translateResultByDict(list);
        //List<WorkOrder> result = listJson.stream().map(e -> JSON.toJavaObject(e,WorkOrder.class)).collect(Collectors.toList());
        String excelContent = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        EasyExcel.write(outputStream, WorkOrder.class).sheet("工单信息表").doWrite(list);
        excelContent = Base64.getEncoder().encodeToString(outputStream.toByteArray());
        return excelContent;
    }

    /**
     * 如果网关不知道直接获取文件流，转为base64后返回前端
     *
     * @param file
     */
    @Override
    public Result<?> importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), WorkOrder.class, new ReadListener<WorkOrder>() {

                private List<WorkOrder> cachedDataList = new ArrayList<>();

                /**
                 * 每次读取都会调用
                 * @param data
                 * @param context
                 */
                @SneakyThrows
                @Override
                public void invoke(WorkOrder data, AnalysisContext context) {
                    //---------------处理字典转义
//                    String text = commonApi.translateDictTextToKey("risk_place_type",data.getRiskType());
//                    if(StringUtils.isEmpty(text)){
//                        throw new ImportException("数据第" + context.readRowHolder().getRowIndex()+ "行存在未配置得字典类型，数据类型:" + data.getRiskType());
//                    }
//                    data.setRiskType(text);
                    //---------------处理字典转义
                    cachedDataList.add(data);
                    if (cachedDataList.size() >= BATCH_SAVE_COUNT) {
                        saveData();
                        // 存储完成清理 list
                        cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_SAVE_COUNT);
                    }
                }

                /**
                 * 所有数据解析完成后才会调用
                 */
                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    saveData();
                }

                /**
                 * 加上存储数据库
                 */
                private void saveData() {
                    saveBatch(cachedDataList);
                }

            }).sheet().doRead();
        } catch (Exception e) {
            return Result.error("导入失败", e.getMessage());
        }
        return Result.OK("导入成功");
    }

    /**
     * 下载导入模板
     * @return
     */
    @Override
    public String getImportTemplate() {
        String excelContent = null;
        try {
            // 模版文件
            ClassPathResource classPathResource = new ClassPathResource("template/WorkOrder.xlsx");
            // 方式一：路径
            String templateFileName = classPathResource.getFile().getPath();

            if (StringUtils.isNotBlank(templateFileName)) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ExcelWriter excelWriter = EasyExcel.write(outputStream).withTemplate(templateFileName).excelType(ExcelTypeEnum.XLSX).autoCloseStream(Boolean.FALSE).build();
                excelWriter.finish();
                excelContent = Base64.getEncoder().encodeToString(outputStream.toByteArray());
            }
            return excelContent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return excelContent;
    }






}
