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
 * @Description: 工单后处理记录-回访信息
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Data
@TableName("feedback_info")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="FeedBack对象", description="工单后处理记录-回访信息")
public class FeedBack {

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
	/**回访方式（如：电话、短信、上门等）*/
    @Schema(description = "回访方式（如：电话、短信、上门等）")
    @NotEmpty(message = "回访方式（如：电话、短信、上门等）不能为空")
    @ExcelProperty("回访方式（如：电话、短信、上门等）")
	private String feedbackMethod;
	/**满意度（如：非常满意/满意/一般/不满意，或 5星制）*/
    @Schema(description = "满意度（如：非常满意/满意/一般/不满意，或 5星制）")
    @NotEmpty(message = "满意度（如：非常满意/满意/一般/不满意，或 5星制）不能为空")
    @ExcelProperty("满意度（如：非常满意/满意/一般/不满意，或 5星制）")
	private String satisfactionLevel;
	/**不满意原因*/
    @Schema(description = "不满意原因")
    @NotEmpty(message = "不满意原因不能为空")
    @ExcelProperty("不满意原因")
	private String dissatisfactionReason;
	/**备注*/
    @Schema(description = "备注")
    @NotEmpty(message = "备注不能为空")
    @ExcelProperty("备注")
	private Object remark;
	/**回访时间（精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "回访时间（精确到秒）")
    @NotEmpty(message = "回访时间（精确到秒）不能为空")
    @ExcelProperty("回访时间（精确到秒）")
	private java.util.Date feedbackAt;
	/**回访人编号*/
    @Schema(description = "回访人编号")
    @NotEmpty(message = "回访人编号不能为空")
    @ExcelProperty("回访人编号")
	private String feedbackByCode;
	/**回访人姓名*/
    @Schema(description = "回访人姓名")
    @NotEmpty(message = "回访人姓名不能为空")
    @ExcelProperty("回访人姓名")
	private String feedbackByName;
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
