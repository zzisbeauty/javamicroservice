package com.hanwei.monitor.entity;

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
 * @Description: 监测点责权信息
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Data
@TableName("monitor_point_authority_info")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="MonitorPointAuthority对象", description="监测点责权信息")
public class MonitorPointAuthority {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    @NotEmpty(message = "主键ID不能为空")
    @ExcelProperty("主键ID")
	private String id;
	/**点位ID*/
    @Schema(description = "点位ID")
    @NotEmpty(message = "点位ID不能为空")
    @ExcelProperty("点位ID")
	private String pointId;
	/**责权类型（INT2，如：1-管理, 2-维护, 3-监管）*/
    @Schema(description = "责权类型（INT2，如：1-管理, 2-维护, 3-监管）")
    @NotEmpty(message = "责权类型（INT2，如：1-管理, 2-维护, 3-监管）不能为空")
    @ExcelProperty("责权类型（INT2，如：1-管理, 2-维护, 3-监管）")
	private Integer authorityType;
	/**单位（责任单位名称）*/
    @Schema(description = "单位（责任单位名称）")
    @NotEmpty(message = "单位（责任单位名称）不能为空")
    @ExcelProperty("单位（责任单位名称）")
	private String unit;
	/**职位（如：负责人、技术员）*/
    @Schema(description = "职位（如：负责人、技术员）")
    @NotEmpty(message = "职位（如：负责人、技术员）不能为空")
    @ExcelProperty("职位（如：负责人、技术员）")
	private String position;
	/**厂家名称*/
    @Schema(description = "厂家名称")
    @NotEmpty(message = "厂家名称不能为空")
    @ExcelProperty("厂家名称")
	private String manufacturerName;
	/**电话（联系电话）*/
    @Schema(description = "电话（联系电话）")
    @NotEmpty(message = "电话（联系电话）不能为空")
    @ExcelProperty("电话（联系电话）")
	private String phone;
	/**排序（数值越小越靠前）*/
    @Schema(description = "排序（数值越小越靠前）")
    @NotEmpty(message = "排序（数值越小越靠前）不能为空")
    @ExcelProperty("排序（数值越小越靠前）")
	private Integer sortOrder;
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
