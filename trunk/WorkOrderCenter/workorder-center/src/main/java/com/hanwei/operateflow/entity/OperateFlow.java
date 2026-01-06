package com.hanwei.operateflow.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotEmpty;

/**
 * @Description: 操作流程记录信息
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Data
@TableName("operation_flow_log")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="OperateFlow对象", description="操作流程记录信息")
public class OperateFlow {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    @NotEmpty(message = "主键ID不能为空")
    @ExcelProperty("主键ID")
	private String id;
	/**处理时间（精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "处理时间（精确到秒）")
    @NotEmpty(message = "处理时间（精确到秒）不能为空")
    @ExcelProperty("处理时间（精确到秒）")
	private java.util.Date handledAt;
	/**流程ID（关联问题或工单ID）*/
    @Schema(description = "流程ID（关联问题或工单ID）")
    @NotEmpty(message = "流程ID（关联问题或工单ID）不能为空")
    @ExcelProperty("流程ID（关联问题或工单ID）")
	private String processId;
	/**类型（tag：问题／工单）*/
    @Schema(description = "类型（tag：问题／工单）")
    @NotEmpty(message = "类型（tag：问题／工单）不能为空")
    @ExcelProperty("类型（tag：问题／工单）")
	private String type;
	/**处理人姓名*/
    @Schema(description = "处理人姓名")
    @NotEmpty(message = "处理人姓名不能为空")
    @ExcelProperty("处理人姓名")
	private String handlerName;
	/**处理人编号*/
    @Schema(description = "处理人编号")
    @NotEmpty(message = "处理人编号不能为空")
    @ExcelProperty("处理人编号")
	private String handlerCode;
	/**操作类型（如：创建、分配、处理、关闭、催办等）*/
    @Schema(description = "操作类型（如：创建、分配、处理、关闭、催办等）")
    @NotEmpty(message = "操作类型（如：创建、分配、处理、关闭、催办等）不能为空")
    @ExcelProperty("操作类型（如：创建、分配、处理、关闭、催办等）")
	private String operationType;
	/**处理前状态（文本，如：待处理）*/
    @Schema(description = "处理前状态（文本，如：待处理）")
    @NotEmpty(message = "处理前状态（文本，如：待处理）不能为空")
    @ExcelProperty("处理前状态（文本，如：待处理）")
	private String statusBefore;
	/**处理后状态（文本，如：已解决）*/
    @Schema(description = "处理后状态（文本，如：已解决）")
    @NotEmpty(message = "处理后状态（文本，如：已解决）不能为空")
    @ExcelProperty("处理后状态（文本，如：已解决）")
	private String statusAfter;
	/**处理备注*/
    @Schema(description = "处理备注")
    @NotEmpty(message = "处理备注不能为空")
    @ExcelProperty("处理备注")
	private Object remark;
	/**处理耗时（单位：秒）*/
    @Schema(description = "处理耗时（单位：秒）")
    @NotEmpty(message = "处理耗时（单位：秒）不能为空")
    @ExcelProperty("处理耗时（单位：秒）")
	private Integer handlingDurationSeconds;
	/**节点序号（流程步骤顺序）*/
    @Schema(description = "节点序号（流程步骤顺序）")
    @NotEmpty(message = "节点序号（流程步骤顺序）不能为空")
    @ExcelProperty("节点序号（流程步骤顺序）")
	private Integer nodeSequence;
	/**创建时间（精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间（精确到秒）")
    @NotEmpty(message = "创建时间（精确到秒）不能为空")
    @ExcelProperty("创建时间（精确到秒）")
	private java.util.Date createTime;
	/**创建人ID*/
    @Schema(description = "创建人ID")
    @NotEmpty(message = "创建人ID不能为空")
    @ExcelProperty("创建人ID")
	private String createBy;
	/**更新时间（精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间（精确到秒）")
    @NotEmpty(message = "更新时间（精确到秒）不能为空")
    @ExcelProperty("更新时间（精确到秒）")
	private java.util.Date updateTime;
	/**更新人ID*/
    @Schema(description = "更新人ID")
    @NotEmpty(message = "更新人ID不能为空")
    @ExcelProperty("更新人ID")
	private String updateBy;
	/**租户ID*/
    @Schema(description = "租户ID")
    @NotEmpty(message = "租户ID不能为空")
    @ExcelProperty("租户ID")
	private String tenementId;
}
