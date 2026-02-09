package com.hanwei.workorderpost.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 工单后处理记录-催办信息
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Data
@TableName("urge_info")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="UrgeInfo对象", description="工单后处理记录-催办信息")
public class UrgeInfo {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    @NotEmpty(message = "主键ID不能为空")
    @ExcelProperty("主键ID")
	private String id;
	/**工单ID*/
    @Schema(description = "工单ID")
    @NotEmpty(message = "工单ID不能为空")
    @ExcelProperty("工单ID")
	private String workOrderId;
	/**工单编号*/
    @Schema(description = "工单编号")
    @NotEmpty(message = "工单编号不能为空")
    @ExcelProperty("工单编号")
	private String workOrderNumber;
	/**操作节点ID（可选，标识流程中哪个节点被催办）*/
    @Schema(description = "操作节点ID（可选，标识流程中哪个节点被催办）")
    @NotEmpty(message = "操作节点ID（可选，标识流程中哪个节点被催办）不能为空")
    @ExcelProperty("操作节点ID（可选，标识流程中哪个节点被催办）")
	private String operationNodeId;
	/**催办时间（精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "催办时间（精确到秒）")
    @NotEmpty(message = "催办时间（精确到秒）不能为空")
    @ExcelProperty("催办时间（精确到秒）")
	private java.util.Date urgeAt;
	/**催办内容（如：请尽快处理）*/
    @Schema(description = "催办内容（如：请尽快处理）")
    @NotEmpty(message = "催办内容（如：请尽快处理）不能为空")
    @ExcelProperty("催办内容（如：请尽快处理）")
	private String urgeContent;
	/**被催办人编号*/
    @Schema(description = "被催办人编号")
    @NotEmpty(message = "被催办人编号不能为空")
    @ExcelProperty("被催办人编号")
	private String assigneeCode;
	/**被催办人姓名*/
    @Schema(description = "被催办人姓名")
    @NotEmpty(message = "被催办人姓名不能为空")
    @ExcelProperty("被催办人姓名")
	private String assigneeName;
	/**是否已读（true-已读，false-未读）*/
    @Schema(description = "是否已读（true-已读，false-未读）")
    @NotEmpty(message = "是否已读（true-已读，false-未读）不能为空")
    @ExcelProperty("是否已读（true-已读，false-未读）")
	private Object isRead;
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
