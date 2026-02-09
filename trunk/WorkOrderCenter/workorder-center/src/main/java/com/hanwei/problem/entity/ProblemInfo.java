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
 * @Description: 问题信息管理表
 * @Author: hanwei
 * @Date:   2026-01-05
 * @Version: V1.0
 */
@Data
@TableName("problem_info")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="ProblemInfo对象", description="问题信息管理表")
public class ProblemInfo {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    @NotEmpty(message = "主键ID不能为空")
    @ExcelProperty("主键ID")
	private java.lang.String id;
	/**问题编号（格式：QN＋年月日＋5位序列，如 QN2025010500001）*/
    @Schema(description = "问题编号（格式：QN＋年月日＋5位序列，如 QN2025010500001）")
    @NotEmpty(message = "问题编号（格式：QN＋年月日＋5位序列，如 QN2025010500001）不能为空")
    @ExcelProperty("问题编号（格式：QN＋年月日＋5位序列，如 QN2025010500001）")
	private java.lang.String problemNumber;
	/**提出人名称*/
    @Schema(description = "提出人名称")
    @NotEmpty(message = "提出人名称不能为空")
    @ExcelProperty("提出人名称")
	private java.lang.String proposerName;
	/**来源系统*/
    @Schema(description = "来源系统")
    @NotEmpty(message = "来源系统不能为空")
    @ExcelProperty("来源系统")
	private java.lang.String sourceSystem;
	/**问题大类编号（支持树形结构）*/
    @Schema(description = "问题大类编号（支持树形结构）")
    @NotEmpty(message = "问题大类编号（支持树形结构）不能为空")
    @ExcelProperty("问题大类编号（支持树形结构）")
	private java.lang.String problemCategoryId;
	/**问题小类编号（支持树形结构）*/
    @Schema(description = "问题小类编号（支持树形结构）")
    @NotEmpty(message = "问题小类编号（支持树形结构）不能为空")
    @ExcelProperty("问题小类编号（支持树形结构）")
	private java.lang.String problemSubCategoryId;
	/**问题标题*/
    @Schema(description = "问题标题")
    @NotEmpty(message = "问题标题不能为空")
    @ExcelProperty("问题标题")
	private java.lang.String problemTitle;
	/**问题内容*/
    @Schema(description = "问题内容")
    @NotEmpty(message = "问题内容不能为空")
    @ExcelProperty("问题内容")
	private java.lang.Object problemContent;
	/**优先级（紧急／正常）*/
    @Schema(description = "优先级（紧急／正常）")
    @NotEmpty(message = "优先级（紧急／正常）不能为空")
    @ExcelProperty("优先级（紧急／正常）")
	private java.lang.String priority;
	/**问题状态（开启／关闭）*/
    @Schema(description = "问题状态（开启／关闭）")
    @NotEmpty(message = "问题状态（开启／关闭）不能为空")
    @ExcelProperty("问题状态（开启／关闭）")
	private java.lang.String status;

    /**是否生成工单（true-已生成，false-未生成）*/
    @Schema(description = "是否生成工单（1-已生成，0-未生成）")
    @ExcelProperty("是否生成工单（1-已生成，0-未生成）")
    private java.lang.String isWorkOrderGenerated;

    /**经度*/
    @Schema(description = "经度")
    @NotEmpty(message = "经度不能为空")
    @ExcelProperty("经度")
    private java.lang.String longitude;

    /**纬度*/
    @Schema(description = "纬度")
    @NotEmpty(message = "纬度不能为空")
    @ExcelProperty("纬度")
	private java.lang.String latitude;

//    /**创建时间*/
//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
//    @Schema(description = "创建时间")
//    @NotEmpty(message = "创建时间不能为空")
//    @ExcelProperty("创建时间")
//	private java.util.Date createTime;
    /**创建时间*/
    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private java.util.Date createTime;
	/**创建人ID*   @NotEmpty(message = "创建人ID不能为空")*/
    @Schema(description = "创建人ID")
    @ExcelProperty("创建人ID")
	private java.lang.String createBy;
	/**更新时间   @NotEmpty(message = "更新时间不能为空")*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
	private java.util.Date updateTime;
	/**更新人ID  @NotEmpty(message = "更新人ID不能为空") */
    @Schema(description = "更新人ID")
    @ExcelProperty("更新人ID")
	private java.lang.String updateBy;
	/**租户ID  @NotEmpty(message = "租户ID不能为空")*/
    @Schema(description = "租户ID")
    @ExcelProperty("租户ID")
	private java.lang.String tenementId;

    /**附件ID列表（逗号分隔）  ------ 没用，但是保留*/
    @Schema(description = "附件ID列表（逗号分隔）")
    @ExcelProperty("附件ID列表")
    private java.lang.String attachIds;
}
