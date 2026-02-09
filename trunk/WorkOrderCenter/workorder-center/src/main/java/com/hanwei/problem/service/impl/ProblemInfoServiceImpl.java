package com.hanwei.problem.service.impl;

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
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.operateflow.service.IOperateFlowService;
import com.hanwei.problem.entity.ProblemInfo;
import com.hanwei.problem.mapper.ProblemInfoMapper;
import com.hanwei.problem.service.IProblemInfoService;
import com.hanwei.problem.service.IProblemWrokOrderRelationService;
import com.hanwei.workorder.entity.WorkOrder;
import com.hanwei.workorder.service.IWorkOrderService;
import com.hanwei.workorderpost.entity.Participant;
import com.hanwei.workorderpost.service.IParticipantService;
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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import com.hanwei.problem.entity.ProblemQueryBO;

/**
 * @Description: 问题信息管理表
 * @Author: hanwei
 * @Date:   2026-01-05
 * @Version: V1.0
 */
@Service
@Slf4j
public class ProblemInfoServiceImpl extends ServiceImpl<ProblemInfoMapper, ProblemInfo> implements IProblemInfoService {

    @Value("${excel.batchSaveCount}")
    private Integer BATCH_SAVE_COUNT;

    @Autowired
    private IOperateFlowService operateFlowService;

    @Autowired
    private IAttachmentService attachmentService;

    @Autowired
    private IWorkOrderService workOrderService;

    @Autowired
    private IProblemWrokOrderRelationService problemWorkOrderRelationService;

    @Autowired
    private IParticipantService participantService;
    // ============== 配套新增问题以及附件促存储使用

    @Override
    public String createProblemWithAttachments(ProblemInfo problemInfo, String attachIdsStr) {
        try {
            // 1. 生成问题编号
            String problemNumber = generateProblemNumber();
            problemInfo.setProblemNumber(problemNumber);

            // 2. 保存问题信息
            boolean saved = this.save(problemInfo);
            if (!saved) {
                throw new RuntimeException("问题保存失败");
            }

            String problemId = problemInfo.getId();

            // 3. 处理附件信息 - 只设置必要字段
            if (attachIdsStr != null && !attachIdsStr.trim().isEmpty()) {
                String[] attachIdArray = attachIdsStr.split(",");
                for (String attachId : attachIdArray) {
                    if (attachId != null && !attachId.trim().isEmpty()) {
                        Attachment attachment = new Attachment();
                        // 只设置三个必填字段
                        attachment.setProcessId(problemId);  // 关联问题ID
                        attachment.setFileUrl(attachId.trim());  // 存储传入的URL
                        // id会自动生成，其他字段不设置

                        boolean attachmentSaved = attachmentService.save(attachment);
                        if (!attachmentSaved) {
                            log.error("附件保存失败: {}", attachId.trim());
                        }
                    }
                }
            }

            return problemId;

        } catch (Exception e) {
            log.error("创建问题失败: " + e.getMessage(), e);
            throw new RuntimeException("创建问题失败: " + e.getMessage());
        }
    }

    /** 生成问题编号（QN+年月日+5位随机序列） */
    private String generateProblemNumber() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());

        // 生成5位随机数字
        Random random = new Random();
        int randomNum = random.nextInt(100000); // 0-99999
        String sequence = String.format("%05d", randomNum);

        return "QN" + dateStr + sequence;
    }

    /** 根据文件ID确定文件类型 */
    private String determineFileType(String fileId) {
        if (fileId == null) {
            return "DOCUMENT";
        }

        String lowerId = fileId.toLowerCase();
        if (lowerId.endsWith(".jpg") || lowerId.endsWith(".jpeg") || lowerId.endsWith(".png") || lowerId.endsWith(".gif")) {
            return "IMAGE";
        } else if (lowerId.endsWith(".pdf")) {
            return "PDF";
        } else if (lowerId.endsWith(".doc") || lowerId.endsWith(".docx")) {
            return "DOC";
        } else if (lowerId.endsWith(".xls") || lowerId.endsWith(".xlsx")) {
            return "EXCEL";
        } else if (lowerId.endsWith(".mp4") || lowerId.endsWith(".avi") || lowerId.endsWith(".mov")) {
            return "VIDEO";
        } else {
            return "DOCUMENT";
        }
    }


    // ==============  编辑问题、保证附件新增（如有的话） 以及 操作记录的更新
    @Override
    public boolean editProblemWithAttachments(ProblemInfo problemInfo, String attachIdsStr) {
        try {
            // 1. 查询原问题信息
            ProblemInfo originalProblem = this.getById(problemInfo.getId());
            if (originalProblem == null) {
                throw new RuntimeException("问题不存在");
            }

            // 2. 更新问题信息
            boolean updateResult = this.updateById(problemInfo);
            if (!updateResult) {
                throw new RuntimeException("问题更新失败");
            }

            // 3. 处理新增附件
            if (attachIdsStr != null && !attachIdsStr.trim().isEmpty()) {
                String[] attachIdArray = attachIdsStr.split(",");
                for (String attachId : attachIdArray) {
                    if (attachId != null && !attachId.trim().isEmpty()) {
                        Attachment attachment = new Attachment();
                        attachment.setProcessId(problemInfo.getId());
                        attachment.setFileUrl(attachId.trim());
                        attachmentService.save(attachment);
                    }
                }
            }

            // 4. 记录操作流程
            operateFlowService.recordProblemEditOperation(
                    problemInfo.getId(),
                    "编辑问题",
                    originalProblem.getCreateBy(),
                    problemInfo.getUpdateBy(),
                    originalProblem.getStatus(),
                    problemInfo.getStatus(),
                    "编辑问题信息"
            );

            return true;

        } catch (Exception e) {
            log.error("编辑问题失败: " + e.getMessage(), e);
            throw new RuntimeException("编辑问题失败: " + e.getMessage());
        }
    }



    // =====================// =====================// ===================== 删除问题记录


    @Override
    public boolean deleteProblemWithRecord(String problemId) {
        try {
            // 1. 查询问题信息
            ProblemInfo problemInfo = this.getById(problemId);
            if (problemInfo == null) {
                throw new RuntimeException("问题不存在");
            }

            // 2. 记录删除操作
            operateFlowService.recordProblemDeleteOperation(
                    problemId,
                    "删除问题",
                    "system",
                    "system",
                    problemInfo.getStatus(),
                    "已删除",
                    "问题被删除"
            );

            // 3. 删除问题
            return this.removeById(problemId);
        } catch (Exception e) {
            log.error("删除问题失败: " + e.getMessage(), e);
            throw new RuntimeException("删除问题失败: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteProblemsWithRecord(List<String> problemIds) {
        try {
            boolean allSuccess = true;

            for (String problemId : problemIds) {
                // 查询问题信息
                ProblemInfo problemInfo = this.getById(problemId);
                if (problemInfo == null) {
                    continue;
                }

                // 记录删除操作流程
                operateFlowService.recordProblemEditOperation(
                        problemId,
                        "删除问题",
                        "system",
                        "system",
                        problemInfo.getStatus(),
                        "已删除",
                        "批量删除问题"
                );

                // 删除问题
                boolean deleted = this.removeById(problemId);
                if (!deleted) {
                    allSuccess = false;
                }
            }

            return allSuccess;

        } catch (Exception e) {
            log.error("批量删除问题失败: " + e.getMessage(), e);
            return false;
        }
    }


    // =====================// =====================// ===================== 条件查询带分页


    @Override
    public IPage<ProblemInfo> queryProblemsByConditions(ProblemQueryBO queryParams, Integer pageNo, Integer pageSize) {
        try {
            // 创建分页对象
            Page<ProblemInfo> page = new Page<ProblemInfo>(pageNo, pageSize);

            // 创建查询条件构造器
            QueryWrapper<ProblemInfo> queryWrapper = new QueryWrapper<>();

            // 状态筛选
            if (StringUtils.isNotBlank(queryParams.getStatus())) {
                queryWrapper.eq("status", queryParams.getStatus());
            }

            // 问题大类筛选
            if (StringUtils.isNotBlank(queryParams.getProblemCategoryId())) {
                queryWrapper.eq("problem_category_id", queryParams.getProblemCategoryId());
            }

            // 时间范围筛选
            if (StringUtils.isNotBlank(queryParams.getStartTime())) {
                queryWrapper.ge("create_time", queryParams.getStartTime());
            }
            if (StringUtils.isNotBlank(queryParams.getEndTime())) {
                queryWrapper.le("create_time", queryParams.getEndTime());
            }

            // 提出人模糊查询
            if (StringUtils.isNotBlank(queryParams.getProposerName())) {
                queryWrapper.like("proposer_name", queryParams.getProposerName());
            }

            // 按创建时间倒序排列
            queryWrapper.orderByDesc("create_time");

            // 执行分页查询
            return this.page(page, queryWrapper);

        } catch (Exception e) {
            log.error("查询问题列表失败: " + e.getMessage(), e);
            throw new RuntimeException("查询问题列表失败: " + e.getMessage());
        }
    }
//    @Override
//    public IPage<ProblemInfo> queryProblemsByConditions(Map<String, Object> queryParams,
//                                                        Integer pageNo, Integer pageSize) {
//        try {
//            // 创建分页对象
//            Page<ProblemInfo> page = new Page<ProblemInfo>(pageNo, pageSize);
//            // 创建查询条件构造器
//            QueryWrapper<ProblemInfo> queryWrapper = new QueryWrapper<>();
//            // 状态筛选
//            String status = (String) queryParams.get("status");
//            if (StringUtils.isNotBlank(status)) {
//                queryWrapper.eq("status", status);
//            }
//            // 问题类型筛选
//            String problemCategoryId = (String) queryParams.get("problemCategoryId");
//            if (StringUtils.isNotBlank(problemCategoryId)) {
//                queryWrapper.eq("problem_category_id", problemCategoryId);
//            }
//            // 时间范围筛选
//            String startTime = (String) queryParams.get("startTime");
//            String endTime = (String) queryParams.get("endTime");
//            if (StringUtils.isNotBlank(startTime)) {
//                queryWrapper.ge("create_time", startTime);
//            }
//            if (StringUtils.isNotBlank(endTime)) {
//                queryWrapper.le("create_time", endTime);
//            }
//            // 提出人模糊查询
//            String proposerName = (String) queryParams.get("proposerName");
//            if (StringUtils.isNotBlank(proposerName)) {
//                queryWrapper.like("proposer_name", proposerName);
//            }
//            // 按创建时间倒序排列
//            queryWrapper.orderByDesc("create_time");
//            // 执行分页查询
//            return this.page(page, queryWrapper);
//        } catch (Exception e) {
//            log.error("查询问题列表失败: " + e.getMessage(), e);
//            throw new RuntimeException("查询问题列表失败: " + e.getMessage());
//        }
//    }



    // =========================== 问题转工单修改
    @Override
    public String transferToWorkOrder(String problemId) {
        try {
            // 1. 查询问题信息
            ProblemInfo problemInfo = this.getById(problemId);
            if (problemInfo == null) {
                throw new RuntimeException("问题不存在========");
            }

            // 检查是否已生成工单
            if ("1".equals(problemInfo.getIsWorkOrderGenerated())) {
                throw new RuntimeException("该问题已生成工单");
            }

            // 2. 创建工单信息
            WorkOrder workOrder = new WorkOrder();

            // 复制对应字段
            workOrder.setProposerName(problemInfo.getProposerName());
            workOrder.setSourceSystem(problemInfo.getSourceSystem());
            workOrder.setWorkOrderCategoryId(problemInfo.getProblemCategoryId());
            workOrder.setWorkOrderSubCategoryId(problemInfo.getProblemSubCategoryId());
            workOrder.setWorkOrderTitle(problemInfo.getProblemTitle());
            workOrder.setWorkOrderContent(problemInfo.getProblemContent());
            workOrder.setPriority(problemInfo.getPriority());
            workOrder.setLongitude(problemInfo.getLongitude());
            workOrder.setLatitude(problemInfo.getLatitude());

            // 设置工单特有字段的默认值
            workOrder.setParentWorkOrderId("");
            workOrder.setWorkOrderType("内部");
            workOrder.setDeviceSerialNumber("");
            workOrder.setStatus("待定");
            workOrder.setIsFollowUp("0");
            workOrder.setCurrentHandler("");
            workOrder.setCurrentDepartment("");
            workOrder.setWorkOrderCost(BigDecimal.ZERO);
            workOrder.setResolvedAt(null);
            workOrder.setClosedAt(null);
            workOrder.setDueAt(null);

            // 设置审计字段
            workOrder.setCreateBy(problemInfo.getCreateBy());
            workOrder.setUpdateBy(problemInfo.getUpdateBy());
            workOrder.setTenementId(problemInfo.getTenementId());

            // 保存工单
            boolean workOrderSaved = workOrderService.save(workOrder);
            if (!workOrderSaved) {
                throw new RuntimeException("工单创建失败");
            }

            // 保存问题和工单关联关系到工单表
            String workOrderId = workOrder.getId();
            boolean relationSaved = problemWorkOrderRelationService.saveProblemWorkOrderRelation(
                    problemId,
                    problemInfo.getProblemNumber(),
                    workOrderId,
                    workOrder.getWorkOrderNumber()
            );
            if (!relationSaved) {
                log.warn("问题工单关联关系保存失败，但工单已创建成功，问题ID: {}, 工单ID: {}", problemId, workOrderId);
            }


            // 添加参与人信息
            Participant participant = Participant.builder()
                    .workOrderId(workOrderId)
                    .userCode(problemInfo.getCreateBy())
                    .userName(problemInfo.getCreateBy())
                    .role("处理人")
                    .isActive(true)
                    .createBy(problemInfo.getCreateBy())
                    .updateBy(problemInfo.getUpdateBy())
                    .tenementId(problemInfo.getTenementId())
                    .build();

            boolean participantSaved = participantService.save(participant);
            if (!participantSaved) {
                log.warn("参与人信息保存失败，但工单已创建成功，问题ID: {}, 工单ID: {}", problemId, workOrderId);
            } else {
                log.info("参与人信息保存成功，问题ID: {}, 工单ID: {}, 用户: {}", problemId, workOrderId, problemInfo.getCreateBy());
            }




            // 记录工单创建操作流程
            operateFlowService.recordWorkOrderOperation(
                    workOrder.getId(),
                    "新建（问题转工单）",
                    problemInfo.getCreateBy(),
                    problemInfo.getCreateBy(),
                    problemInfo.getStatus(),
                    workOrder.getStatus(),
                    "问题ID: " + problemId
            );

            // 3. 更新问题状态
            problemInfo.setIsWorkOrderGenerated("1");
            boolean problemUpdated = this.updateById(problemInfo);
            if (!problemUpdated) {
                throw new RuntimeException("问题状态更新失败");
            }

            return workOrder.getId();

        } catch (Exception e) {
            throw new RuntimeException("升级失败: " + e.getMessage());
        }
    }


    @Override
    public IPage<ProblemInfo> getProblemsByProposer(String proposerName, Integer pageNo, Integer pageSize) {
        try {
            Page<ProblemInfo> page = new Page<>(pageNo, pageSize);

            // 构建查询条件
            QueryWrapper<ProblemInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("proposer_name", proposerName)
                    .orderByDesc("create_time");

            return this.page(page, queryWrapper);

        } catch (Exception e) {
            log.error("查询问题列表失败: " + e.getMessage(), e);
            throw new RuntimeException("查询失败: " + e.getMessage());
        }
    }



    @Override
    public void exportProblemsByProposerToCsv(String proposerName, ServletOutputStream outputStream) {
        try {
            // 1. 调用我的问题查询方法获取数据
            List<ProblemInfo> problems = getProblemsByProposer(proposerName, 1, Integer.MAX_VALUE).getRecords();

            // 2. 排除Object类型字段，避免转换错误
            Set<String> excludeColumnFiledNames = new HashSet<>();
            excludeColumnFiledNames.add("problemContent");
            excludeColumnFiledNames.add("isWorkOrderGenerated");

            // 3. 使用EasyExcel导出CSV
            EasyExcel.write(outputStream, ProblemInfo.class)
                    .excludeColumnFiledNames(excludeColumnFiledNames)
                    .excelType(ExcelTypeEnum.CSV)
                    .sheet("我的问题")
                    .doWrite(problems);

        } catch (Exception e) {
            log.error("导出我的问题CSV失败: " + e.getMessage(), e);
            throw new RuntimeException("导出失败: " + e.getMessage());
        }
    }



    @Override
    public String createProblemWithAttachmentsAndOperation(ProblemInfo problemInfo, String attachIds) {
        // 调用service层完成问题新增、附件表中存储附件
        String problemId = createProblemWithAttachments(problemInfo, attachIds);

        // 在问题创建完成后，记录问题操作流程
        if (problemId != null && !problemId.trim().isEmpty()) {
            try {
                operateFlowService.recordProblemOperation(
                        problemId,                    // 问题ID作为流程ID
                        "创建问题",                    // 操作类型
                        problemInfo.getProposerName(), // 处理人姓名
                        problemInfo.getProposerName(), // 处理人编号
                        "",                          // 处理前状态
                        problemInfo.getStatus(),     // 处理后状态
                        "问题创建成功"                // 备注
                );
            } catch (Exception e) {
                log.warn("问题操作记录失败，但问题已创建成功，问题ID: {}", problemId);
                // 不影响主流程，只记录警告
            }
        }

        return problemId;
    }


    @Override
    public boolean editProblemWithAttachmentsAndOperation(ProblemInfo problemInfo, String attachIds) {
        try {
            // 1. 查询原问题信息
            ProblemInfo originalProblem = this.getById(problemInfo.getId());
            if (originalProblem == null) {
                throw new RuntimeException("问题不存在");
            }

            // 2. 调用现有的编辑方法（保留原有逻辑）
            boolean result = editProblemWithAttachments(problemInfo, attachIds);
            if (!result) {
                throw new RuntimeException("问题编辑失败");
            }

            // 3. 添加操作记录（新增功能）
            try {
                operateFlowService.recordProblemEditOperation(
                        problemInfo.getId(),
                        "编辑问题",
                        originalProblem.getCreateBy() != null ? originalProblem.getCreateBy() : "system",
                        problemInfo.getUpdateBy() != null ? problemInfo.getUpdateBy() : "system",
                        originalProblem.getStatus(),
                        problemInfo.getStatus(),
                        "编辑问题信息"
                );
            } catch (Exception e) {
                log.warn("操作记录添加失败，但编辑已成功: {}", e.getMessage());
                // 不影响主流程，只记录警告
            }

            return true;

        } catch (Exception e) {
            log.error("编辑问题失败: " + e.getMessage(), e);
            throw new RuntimeException("编辑问题失败: " + e.getMessage());
        }
    }

    // ===========================================// ===========================================


    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param problemInfo
     */
    @Override
    public void exportData(ServletOutputStream outputStream, Map paramMap, ProblemInfo problemInfo) {
        QueryWrapper<ProblemInfo> queryWrapper = QueryGenerator.initQueryWrapper(problemInfo, paramMap);
        List<ProblemInfo> list = list(queryWrapper);
        EasyExcel.write(outputStream, ProblemInfo.class).sheet("问题信息管理表").doWrite(list);
    }

    /**
     * 如果网关不知道直接获取文件流，转为 base64 后返回前端
     *
     * @param paramMap
     * @param problemInfo
     */
    @Override
    public String exportDataToBase64(Map paramMap, ProblemInfo problemInfo) {
        QueryWrapper<ProblemInfo> queryWrapper = QueryGenerator.initQueryWrapper(problemInfo, paramMap);
        List<ProblemInfo> list = list(queryWrapper);
        //字典值转换
        //List<JSON> listJson = commonApi.translateResultByDict(list);
        //List<ProblemInfo> result = listJson.stream().map(e -> JSON.toJavaObject(e,ProblemInfo.class)).collect(Collectors.toList());
        String excelContent = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        EasyExcel.write(outputStream, ProblemInfo.class).sheet("问题信息管理表").doWrite(list);
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
            EasyExcel.read(file.getInputStream(), ProblemInfo.class, new ReadListener<ProblemInfo>() {

                private List<ProblemInfo> cachedDataList = new ArrayList<>();

                /**
                 * 每次读取都会调用
                 * @param data
                 * @param context
                 */
                @SneakyThrows
                @Override
                public void invoke(ProblemInfo data, AnalysisContext context) {
                    /**
                    //---------------处理字典转义
                    String text = commonApi.translateDictTextToKey("risk_place_type",data.getRiskType());
                    if(StringUtils.isEmpty(text)){
                        throw new ImportException("数据第" + context.readRowHolder().getRowIndex()+ "行存在未配置得字典类型，数据类型:" + data.getRiskType());
                    }
                    data.setRiskType(text);
                    //---------------处理字典转义
                     */
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
            ClassPathResource classPathResource = new ClassPathResource("template/ProblemInfo.xlsx");
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
