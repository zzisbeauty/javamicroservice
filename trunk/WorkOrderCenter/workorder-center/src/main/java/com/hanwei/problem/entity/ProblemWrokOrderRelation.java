package com.hanwei.problem.entity;

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
 * @Description: 问题工单关联信息
 * @Author: hanwei
 * @Date:   2026-01-05
 * @Version: V1.0
 */
@Data
@TableName("problem_work_order_relation")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="ProblemWrokOrderRelation对象", description="问题工单关联信息")
public class ProblemWrokOrderRelation {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    @NotEmpty(message = "主键ID不能为空")
    @ExcelProperty("主键ID")
	private java.lang.String id;
	/**问题ID*/
    @Schema(description = "问题ID")
    @NotEmpty(message = "问题ID不能为空")
    @ExcelProperty("问题ID")
	private java.lang.String problemId;
	/**问题编号*/
    @Schema(description = "问题编号")
    @NotEmpty(message = "问题编号不能为空")
    @ExcelProperty("问题编号")
	private java.lang.String problemNumber;
	/**工单ID*/
    @Schema(description = "工单ID")
    @NotEmpty(message = "工单ID不能为空")
    @ExcelProperty("工单ID")
	private java.lang.String workOrderId;
	/**工单编号*/
    @Schema(description = "工单编号")
    @NotEmpty(message = "工单编号不能为空")
    @ExcelProperty("工单编号")
	private java.lang.String workOrderNumber;
	/**创建时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    @NotEmpty(message = "创建时间不能为空")
    @ExcelProperty("创建时间")
	private java.util.Date createTime;
	/**创建人ID*/
    @Schema(description = "创建人ID")
    @NotEmpty(message = "创建人ID不能为空")
    @ExcelProperty("创建人ID")
	private java.lang.String createBy;
	/**更新时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    @NotEmpty(message = "更新时间不能为空")
    @ExcelProperty("更新时间")
	private java.util.Date updateTime;
	/**更新人ID*/
    @Schema(description = "更新人ID")
    @NotEmpty(message = "更新人ID不能为空")
    @ExcelProperty("更新人ID")
	private java.lang.String updateBy;
	/**租户ID*/
    @Schema(description = "租户ID")
    @NotEmpty(message = "租户ID不能为空")
    @ExcelProperty("租户ID")
	private java.lang.String tenementId;
}
