package com.hanwei.operateflow.service.impl;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.ListUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanwei.core.base.QueryGenerator;
import com.hanwei.core.common.api.vo.Result;
import com.hanwei.operateflow.entity.OperateFlow;
import com.hanwei.operateflow.mapper.OperateFlowMapper;
import com.hanwei.operateflow.service.IOperateFlowService;
import jakarta.servlet.ServletOutputStream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.*;



/**
 * @Description: 操作流程记录信息
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Service
@Slf4j
public class OperateFlowServiceImpl extends ServiceImpl<OperateFlowMapper, OperateFlow> implements IOperateFlowService {

    @Value("${excel.batchSaveCount}")
    private Integer BATCH_SAVE_COUNT;

    /**
     * 以下方法需要支持直接访问文件流（网关允许）
     *
     * @param outputStream
     * @param paramMap
     * @param operateFlow
     */
    @Override
    public void exportData(ServletOutputStream outputStream, Map paramMap, OperateFlow operateFlow) {
        QueryWrapper<OperateFlow> queryWrapper = QueryGenerator.initQueryWrapper(operateFlow, paramMap);
        List<OperateFlow> list = list(queryWrapper);
        EasyExcel.write(outputStream, OperateFlow.class).sheet("操作流程记录信息").doWrite(list);
    }

    /**
     * 如果网关不知道直接获取文件流，转为base64后返回前端
     *
     * @param paramMap
     * @param operateFlow
     */
    @Override
    public String exportDataToBase64(Map paramMap, OperateFlow operateFlow) {
        QueryWrapper<OperateFlow> queryWrapper = QueryGenerator.initQueryWrapper(operateFlow, paramMap);
        List<OperateFlow> list = list(queryWrapper);
        //字典值转换
        // List<JSON> listJson = commonApi.translateResultByDict(list);
        // List<OperateFlow> result = listJson.stream().map(e -> JSON.toJavaObject(e,OperateFlow.class)).collect(Collectors.toList());
        String excelContent = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        EasyExcel.write(outputStream, OperateFlow.class).sheet("操作流程记录信息").doWrite(list);
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
            EasyExcel.read(file.getInputStream(), OperateFlow.class, new ReadListener<OperateFlow>() {
                private List<OperateFlow> cachedDataList = new ArrayList<>();
                /**
                 * 每次读取都会调用
                 * @param data
                 * @param context
                 */
                @SneakyThrows
                @Override
                public void invoke(OperateFlow data, AnalysisContext context) {
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
            ClassPathResource classPathResource = new ClassPathResource("template/OperateFlow.xlsx");
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


    // ======================================================================================================


    // ====================// ====================// ==================== 配套问题新增后的操作记录的更新

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
    @Override
    public void recordProblemOperation(String problemId, String operationType,
                                       String handlerName, String handlerCode,
                                       String statusBefore, String statusAfter, String remark) {
        try {
            OperateFlow operateFlow = new OperateFlow();
            operateFlow.setProcessId(problemId);  // 问题ID作为流程ID
            operateFlow.setType("问题");  // 类型明确为"问题"
            operateFlow.setOperationType(operationType);
            operateFlow.setHandlerName(handlerName);
            operateFlow.setHandlerCode(handlerCode);
            operateFlow.setStatusBefore(statusBefore);
            operateFlow.setStatusAfter(statusAfter);
            operateFlow.setRemark(remark);
            operateFlow.setNodeSequence(0);  // 问题创建时节点序号为0
            operateFlow.setPid(null);  // 创建时pid为空

            boolean saved = this.save(operateFlow);
            if (saved) {
                log.info("记录问题操作流程成功，问题ID: {}, 操作类型: {}, 节点序号: {}",
                        problemId, operationType, operateFlow.getNodeSequence());
            } else {
                log.error("保存问题操作流程失败");
            }

        } catch (Exception e) {
            log.error("记录问题操作流程失败: {}", e.getMessage(), e);
        }
    }


    // ====================// ====================// ==================== 记录问题编辑操作流程

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
    @Override
    public void recordProblemEditOperation(String problemId, String operationType,
                                           String handlerName, String handlerCode,
                                           String statusBefore, String statusAfter, String remark) {
        try {
            // 查询该问题已有的操作记录数量，确定节点序号
            QueryWrapper<OperateFlow> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("process_id", problemId);
            // 移除 ORDER BY 子句，避免PostgreSQL语法错误
            long existingCountLong = this.count(queryWrapper);
            int existingCount = (int) existingCountLong;

            // 设置节点序号（编辑操作从1开始）
            int nodeSequence = existingCount;

            // 查询上一个操作的ID作为pid
            String pid = null;
            if (existingCount > 0) {
                QueryWrapper<OperateFlow> lastQueryWrapper = new QueryWrapper<>();
                lastQueryWrapper.eq("process_id", problemId);
                lastQueryWrapper.orderByDesc("node_sequence");
                lastQueryWrapper.last("LIMIT 1");
                OperateFlow lastOperateFlow = this.getOne(lastQueryWrapper);
                if (lastOperateFlow != null) {
                    pid = lastOperateFlow.getId();
                }
            }

            // 创建操作记录
            OperateFlow operateFlow = new OperateFlow();
            operateFlow.setProcessId(problemId);
            operateFlow.setType("问题");
            operateFlow.setHandlerName(handlerName);
            operateFlow.setHandlerCode(handlerCode);
            operateFlow.setOperationType(operationType);
            operateFlow.setStatusBefore(statusBefore);
            operateFlow.setStatusAfter(statusAfter);
            operateFlow.setRemark(remark);
            operateFlow.setNodeSequence(nodeSequence);
            operateFlow.setPid(pid);

            // 设置审计字段
            operateFlow.setHandledAt(new java.util.Date());
            operateFlow.setCreateTime(new java.util.Date());
            operateFlow.setUpdateTime(new java.util.Date());
            operateFlow.setCreateBy(handlerCode);
            operateFlow.setUpdateBy(handlerCode);
            operateFlow.setTenementId("default");
            operateFlow.setHandlingDurationSeconds(0);

            boolean saved = this.save(operateFlow);
            if (saved) {
                log.info("记录问题编辑操作流程成功，问题ID: {}, 操作类型: {}, 节点序号: {}",
                        problemId, operationType, operateFlow.getNodeSequence());
            } else {
                log.error("保存问题编辑操作流程失败");
            }

        } catch (Exception e) {
            log.error("记录问题编辑操作流程失败: " + e.getMessage(), e);
        }
    }


    // ============================== 删除问题信息
    @Override
    public void recordProblemDeleteOperation(String problemId, String operationType,
                                             String handlerName, String handlerCode,
                                             String statusBefore, String statusAfter, String remark) {
        try {
            // 查询该问题已有的操作记录数量，确定节点序号
            QueryWrapper<OperateFlow> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("process_id", problemId);
            long existingCountLong = this.count(queryWrapper);
            int nodeSequence = (int) existingCountLong;

            // 获取上一个操作的ID作为pid
            String pid = null;
            if (nodeSequence > 0) {
                QueryWrapper<OperateFlow> lastQueryWrapper = new QueryWrapper<>();
                lastQueryWrapper.eq("process_id", problemId);
                lastQueryWrapper.orderByDesc("node_sequence");
                lastQueryWrapper.last("LIMIT 1");
                OperateFlow lastOperation = this.getOne(lastQueryWrapper);
                if (lastOperation != null) {
                    pid = lastOperation.getId();
                }
            }

            // 创建操作记录
            OperateFlow operateFlow = new OperateFlow();
            operateFlow.setProcessId(problemId);
            operateFlow.setType("问题");
            operateFlow.setHandlerName(handlerName);
            operateFlow.setHandlerCode(handlerCode);
            operateFlow.setOperationType(operationType);
            operateFlow.setStatusBefore(statusBefore);
            operateFlow.setStatusAfter(statusAfter);
            operateFlow.setRemark(remark);
            operateFlow.setNodeSequence(nodeSequence);
            operateFlow.setPid(pid);

            boolean saved = this.save(operateFlow);
            if (saved) {
                log.info("记录问题删除操作流程成功，问题ID: {}, 操作类型: {}, 节点序号: {}",
                        problemId, operationType, operateFlow.getNodeSequence());
            } else {
                log.error("保存问题删除操作流程失败");
            }

        } catch (Exception e) {
            log.error("记录问题删除操作流程失败: " + e.getMessage(), e);
        }
    }




    // ========================================// ========================================

    /**
     * 记录工单操作流程 - 工单首次创建时调用；
     *
     * @param workOrderId 工单ID
     * @param operationType 操作类型
     * @param handlerName 处理人姓名
     * @param handlerCode 处理人编号
     * @param statusBefore 处理前状态
     * @param statusAfter 处理后状态
     * @param remark 备注
     */
    public void recordWorkOrderOperation(String workOrderId, String operationType,
                                         String handlerName, String handlerCode,
                                         String statusBefore, String statusAfter, String remark) {
        try {
            // 查询该工单已有的操作记录数量，确定节点序号
            QueryWrapper<OperateFlow> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("process_id", workOrderId);
            // queryWrapper.orderByDesc("node_sequence");
            long existingCountLong = this.count(queryWrapper);
            int existingCount = (int) existingCountLong; // 修复类型转换

            // 获取最后一个操作的ID作为当前操作的PID
            String pid = null;
            if (existingCount > 0) {
                QueryWrapper<OperateFlow> lastQuery = new QueryWrapper<>();
                lastQuery.eq("process_id", workOrderId)
                        .orderByDesc("node_sequence")
                        .last("LIMIT 1");
                OperateFlow lastOperation = this.getOne(lastQuery);
                if (lastOperation != null) {
                    pid = lastOperation.getId();
                }
            }

            // 创建操作记录
            OperateFlow operateFlow = new OperateFlow();
            operateFlow.setId(null); // 主键ID，系统自动生成
            operateFlow.setHandledAt(new java.util.Date());
            operateFlow.setProcessId(workOrderId); // 流程ID设置为工单ID
            operateFlow.setType("工单");
            operateFlow.setHandlerName(handlerName);
            operateFlow.setHandlerCode(handlerCode);
            operateFlow.setOperationType(operationType);
            operateFlow.setStatusBefore(statusBefore);
            operateFlow.setStatusAfter(statusAfter);
            operateFlow.setRemark(remark);
            operateFlow.setHandlingDurationSeconds(0);
            operateFlow.setNodeSequence(existingCount); // 节点序号
            operateFlow.setPid(pid); // 父节点ID

            // 保存操作记录
            boolean saved = this.save(operateFlow);
            if (saved) {
                log.info("记录工单操作流程成功，工单ID: {}, 操作类型: {}, 节点序号: {}",
                        workOrderId, operationType, operateFlow.getNodeSequence());
            } else {
                log.error("保存工单操作流程失败");
            }

        } catch (Exception e) {
            log.error("记录工单操作流程失败: {}", e.getMessage(), e);
        }
    }




    @Override
    public void recordWorkOrderAssignOperation(String workOrderId, String operationType,
                                               String assignerCode, String assignerName,
                                               String assigneeCode, String assigneeName,
                                               String statusBefore, String statusAfter, String remark) {
        try {
            // 查询该工单已有的操作记录数量，确定节点序号
            QueryWrapper<OperateFlow> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("process_id", workOrderId);
            long existingCountLong = this.count(queryWrapper);
            int nodeSequence = (int) existingCountLong;

            // 查询上一个操作记录ID作为pid
            String pid = null;
            if (existingCountLong > 0) {
                QueryWrapper<OperateFlow> lastQueryWrapper = new QueryWrapper<>();
                lastQueryWrapper.eq("process_id", workOrderId);
                lastQueryWrapper.orderByDesc("node_sequence");
                lastQueryWrapper.last("LIMIT 1");
                OperateFlow lastRecord = this.getOne(lastQueryWrapper);
                if (lastRecord != null) {
                    pid = lastRecord.getId();
                }
            }

            // 创建操作流程记录
            OperateFlow operateFlow = OperateFlow.builder()
                    .processId(workOrderId)
                    .type("工单")
                    .handlerName(assigneeName)  // 记录被派送人姓名
                    .handlerCode(assigneeCode)  // 记录被派送人编号
                    .operationType(operationType)
                    .statusBefore(statusBefore)
                    .statusAfter(statusAfter)
                    .remark(remark + " (派送人:" + assignerName + ")")
                    .nodeSequence(nodeSequence)
                    .pid(pid)
                    .handledAt(new java.util.Date())
                    .build();

            boolean saved = this.save(operateFlow);
            if (saved) {
                log.info("工单派送操作流程记录成功，工单ID: {}, 被派送人: {}", workOrderId, assigneeName);
            } else {
                log.error("保存工单派送操作流程失败");
            }

        } catch (Exception e) {
            log.error("记录工单派送操作流程失败: " + e.getMessage(), e);
        }
    }




    @Override
    public void recordWorkOrderCopyOperation(String workOrderId, String operationType,
                                             String copierCode, String copierName,
                                             String copyToCode, String copyToName,
                                             String statusBefore, String statusAfter, String remark) {
        try {
            // 查询该工单已有的操作记录数量，确定节点序号
            QueryWrapper<OperateFlow> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("process_id", workOrderId);
            long existingCountLong = this.count(queryWrapper);
            int nodeSequence = (int) existingCountLong;

            // 查询上一个操作记录，获取pid
            String pid = null;
            if (nodeSequence > 0) {
                QueryWrapper<OperateFlow> lastQueryWrapper = new QueryWrapper<>();
                lastQueryWrapper.eq("process_id", workOrderId);
                lastQueryWrapper.orderByDesc("node_sequence");
                lastQueryWrapper.last("LIMIT 1");
                OperateFlow lastOperation = this.getOne(lastQueryWrapper);
                if (lastOperation != null) {
                    pid = lastOperation.getId();
                }
            }

            // 创建操作流程记录
            OperateFlow operateFlow = OperateFlow.builder()
                    .processId(workOrderId)
                    .type("工单")
                    .handlerName(copyToName)  // 记录被抄送人姓名
                    .handlerCode(copyToCode)  // 记录被抄送人编号
                    .operationType(operationType)
                    .statusBefore(statusBefore)
                    .statusAfter(statusAfter)
                    .remark(remark + " (抄送操作人: " + copierName + ")")
                    .handlingDurationSeconds(0)
                    .nodeSequence(nodeSequence)
                    .pid(pid)
                    .handledAt(new Date())
                    .createTime(new Date())
                    .createBy("system")
                    .updateTime(new Date())
                    .updateBy("system")
                    .tenementId("default")
                    .build();

            boolean saved = this.save(operateFlow);
            if (saved) {
                log.info("工单抄送操作流程记录成功，工单ID: {}, 被抄送人: {}", workOrderId, copyToName);
            } else {
                log.error("保存工单抄送操作流程失败");
            }

        } catch (Exception e) {
            log.error("记录工单抄送操作流程失败: " + e.getMessage(), e);
        }
    }






    /**
     * 记录工单编辑/删除操作流程
     *
     * @param workOrderId 工单ID
     * @param operationType 操作类型
     * @param handlerName 处理人姓名
     * @param handlerCode 处理人编号
     * @param statusBefore 处理前状态
     * @param statusAfter 处理后状态
     * @param remark 备注
     */
    public void recordWorkOrderEditOperation(String workOrderId, String operationType,
                                             String handlerName, String handlerCode,
                                             String statusBefore, String statusAfter, String remark) {
        try {
            // 查询该工单已有的操作记录数量，确定节点序号
            QueryWrapper<OperateFlow> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("process_id", workOrderId);
            long existingCountLong = this.count(queryWrapper);
            int nodeSequence = (int) existingCountLong;

            // 获取上一个操作记录的ID作为pid（编辑/删除时使用）
            String pid = null;
            if (nodeSequence > 0) {
                // 查询最新的操作记录作为父节点
                QueryWrapper<OperateFlow> lastRecordQuery = new QueryWrapper<>();
                lastRecordQuery.eq("process_id", workOrderId);
                lastRecordQuery.orderByDesc("create_time");
                lastRecordQuery.last("LIMIT 1");
                OperateFlow lastRecord = this.getOne(lastRecordQuery);
                if (lastRecord != null) {
                    pid = lastRecord.getId();
                }
            }

            // 创建操作流程记录
            OperateFlow operateFlow = new OperateFlow();
            operateFlow.setId(null); // 主键ID，系统自动生成
            operateFlow.setHandledAt(new java.util.Date()); // 处理时间
            operateFlow.setProcessId(workOrderId); // 流程ID（工单ID）
            operateFlow.setType("工单"); // 类型
            operateFlow.setHandlerName(handlerName); // 处理人姓名
            operateFlow.setHandlerCode(handlerCode); // 处理人编号
            operateFlow.setOperationType(operationType); // 操作类型
            operateFlow.setStatusBefore(statusBefore); // 处理前状态
            operateFlow.setStatusAfter(statusAfter); // 处理后状态
            operateFlow.setRemark(remark); // 备注
            operateFlow.setHandlingDurationSeconds(0); // 处理耗时
            operateFlow.setNodeSequence(nodeSequence); // 节点序号
            operateFlow.setPid(pid); // 父节点ID

            // 保存操作记录
            boolean saved = this.save(operateFlow);
            if (saved) {
                log.info("记录工单编辑/删除操作流程成功，工单ID: {}, 操作类型: {}, 节点序号: {}",
                        workOrderId, operationType, operateFlow.getNodeSequence());
            } else {
                log.error("保存工单编辑/删除操作流程失败");
            }

        } catch (Exception e) {
            log.error("记录工单编辑/删除操作流程失败: {}", e.getMessage(), e);
        }
    }



    @Override
    public void recordWorkOrderUrgeOperation(String workOrderId, String operationType,
                                             String urgerCode, String urgerName,
                                             String assigneeCode, String assigneeName,
                                             String statusBefore, String statusAfter, String remark) {
        try {
            // 查询该工单已有的操作记录数量，确定节点序号
            QueryWrapper<OperateFlow> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("process_id", workOrderId);
            long existingCountLong = this.count(queryWrapper);
            int nodeSequence = (int) existingCountLong;

            // 查询上一个操作记录ID作为pid
            OperateFlow lastOperation = this.getOne(queryWrapper.orderByDesc("node_sequence"));
            String pid = lastOperation != null ? lastOperation.getId() : null;

            // 创建催办操作记录
            OperateFlow operateFlow = OperateFlow.builder()
                    .handledAt(new Date())
                    .processId(workOrderId)
                    .type("工单")
                    .handlerName(assigneeName)  // 记录被催办人信息
                    .handlerCode(assigneeCode)
                    .operationType(operationType)
                    .statusBefore(statusBefore)
                    .statusAfter(statusAfter)
                    .remark(remark + " (催办人: " + urgerName + ")")
                    .handlingDurationSeconds(0)
                    .nodeSequence(nodeSequence)
                    .pid(pid)
                    .createTime(new Date())
                    .createBy(urgerCode)
                    .updateTime(new Date())
                    .updateBy(urgerCode)
                    .tenementId("")
                    .build();

            boolean saved = this.save(operateFlow);
            if (!saved) {
                log.error("保存工单催办操作流程失败");
            }

        } catch (Exception e) {
            log.error("记录工单催办操作流程失败: " + e.getMessage(), e);
        }
    }



}
