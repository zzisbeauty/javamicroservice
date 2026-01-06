package com.hanwei.workorder.entity;

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
 * @Description: 工单信息表
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Data
@TableName("work_order_info")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="WorkOrder对象", description="工单信息表")
public class WorkOrder {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    @NotEmpty(message = "主键ID不能为空")
    @ExcelProperty("主键ID")
	private String id;
	/**工单编号（格式：TN＋年月日＋5位序列，如 TN2025010500001）*/
    @Schema(description = "工单编号（格式：TN＋年月日＋5位序列，如 TN2025010500001）")
    @NotEmpty(message = "工单编号（格式：TN＋年月日＋5位序列，如 TN2025010500001）不能为空")
    @ExcelProperty("工单编号（格式：TN＋年月日＋5位序列，如 TN2025010500001）")
	private String workOrderNumber;
	/**父工单ID（支持工单拆分）*/
    @Schema(description = "父工单ID（支持工单拆分）")
    @NotEmpty(message = "父工单ID（支持工单拆分）不能为空")
    @ExcelProperty("父工单ID（支持工单拆分）")
	private String parentWorkOrderId;
	/**提出人名称*/
    @Schema(description = "提出人名称")
    @NotEmpty(message = "提出人名称不能为空")
    @ExcelProperty("提出人名称")
	private String proposerName;
	/**工单类型（内部／委外）*/
    @Schema(description = "工单类型（内部／委外）")
    @NotEmpty(message = "工单类型（内部／委外）不能为空")
    @ExcelProperty("工单类型（内部／委外）")
	private String workOrderType;
	/**来源系统*/
    @Schema(description = "来源系统")
    @NotEmpty(message = "来源系统不能为空")
    @ExcelProperty("来源系统")
	private String sourceSystem;
	/**工单大类编号（支持树形结构）*/
    @Schema(description = "工单大类编号（支持树形结构）")
    @NotEmpty(message = "工单大类编号（支持树形结构）不能为空")
    @ExcelProperty("工单大类编号（支持树形结构）")
	private String workOrderCategoryId;
	/**工单小类编号（支持树形结构）*/
    @Schema(description = "工单小类编号（支持树形结构）")
    @NotEmpty(message = "工单小类编号（支持树形结构）不能为空")
    @ExcelProperty("工单小类编号（支持树形结构）")
	private String workOrderSubCategoryId;
	/**工单标题*/
    @Schema(description = "工单标题")
    @NotEmpty(message = "工单标题不能为空")
    @ExcelProperty("工单标题")
	private String workOrderTitle;
	/**工单内容*/
    @Schema(description = "工单内容")
    @NotEmpty(message = "工单内容不能为空")
    @ExcelProperty("工单内容")
	private Object workOrderContent;
	/**设备序列号（冗余字段，便于快速查询）*/
    @Schema(description = "设备序列号（冗余字段，便于快速查询）")
    @NotEmpty(message = "设备序列号（冗余字段，便于快速查询）不能为空")
    @ExcelProperty("设备序列号（冗余字段，便于快速查询）")
	private String deviceSerialNumber;
	/**优先级（紧急／正常）*/
    @Schema(description = "优先级（紧急／正常）")
    @NotEmpty(message = "优先级（紧急／正常）不能为空")
    @ExcelProperty("优先级（紧急／正常）")
	private String priority;
	/**工单状态（待定／处理中／挂起／已解决／关闭）*/
    @Schema(description = "工单状态（待定／处理中／挂起／已解决／关闭）")
    @NotEmpty(message = "工单状态（待定／处理中／挂起／已解决／关闭）不能为空")
    @ExcelProperty("工单状态（待定／处理中／挂起／已解决／关闭）")
	private String status;
	/**是否回访（true-需回访，false-无需回访）*/
    @Schema(description = "是否回访（true-需回访，false-无需回访）")
    @NotEmpty(message = "是否回访（true-需回访，false-无需回访）不能为空")
    @ExcelProperty("是否回访（true-需回访，false-无需回访）")
	private Object isFollowUp;
	/**当前处理人*/
    @Schema(description = "当前处理人")
    @NotEmpty(message = "当前处理人不能为空")
    @ExcelProperty("当前处理人")
	private String currentHandler;
	/**当前处理部门*/
    @Schema(description = "当前处理部门")
    @NotEmpty(message = "当前处理部门不能为空")
    @ExcelProperty("当前处理部门")
	private String currentDepartment;
	/**工单开销（单位：元）*/
    @Schema(description = "工单开销（单位：元）")
    @NotEmpty(message = "工单开销（单位：元）不能为空")
    @ExcelProperty("工单开销（单位：元）")
	private java.math.BigDecimal workOrderCost;
	/**解决时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "解决时间")
    @NotEmpty(message = "解决时间不能为空")
    @ExcelProperty("解决时间")
	private java.util.Date resolvedAt;
	/**关闭时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "关闭时间")
    @NotEmpty(message = "关闭时间不能为空")
    @ExcelProperty("关闭时间")
	private java.util.Date closedAt;
	/**到期时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "到期时间")
    @NotEmpty(message = "到期时间不能为空")
    @ExcelProperty("到期时间")
	private java.util.Date dueAt;
	/**经度*/
    @Schema(description = "经度")
    @NotEmpty(message = "经度不能为空")
    @ExcelProperty("经度")
	private java.math.BigDecimal longitude;
	/**纬度*/
    @Schema(description = "纬度")
    @NotEmpty(message = "纬度不能为空")
    @ExcelProperty("纬度")
	private java.math.BigDecimal latitude;
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
	private String createBy;
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
	private String updateBy;
	/**租户ID*/
    @Schema(description = "租户ID")
    @NotEmpty(message = "租户ID不能为空")
    @ExcelProperty("租户ID")
	private String tenementId;
}
