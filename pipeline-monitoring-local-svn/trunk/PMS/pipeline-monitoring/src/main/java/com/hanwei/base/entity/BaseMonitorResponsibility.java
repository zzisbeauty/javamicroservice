package com.hanwei.base.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.common.ApiEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotEmpty;

/**
 * @Description: 监测点责权信息
 * @Author: hanwei
 * @Date:   2026-02-06
 * @Version: V1.0
 */
@Data
@TableName("base_monitor_responsibility")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="BaseMonitorResponsibility对象", description="监测点责权信息")
public class BaseMonitorResponsibility {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    @NotEmpty(message = "主键ID不能为空")
    @ExcelProperty("主键ID")
    @ApiParameter(name = "id", description = "主键ID", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String id;
	/**点位ID*/
    @Schema(description = "点位ID")
    @NotEmpty(message = "点位ID不能为空")
    @ExcelProperty("点位ID")
    @ApiParameter(name = "pointId", description = "点位ID", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String pointId;
	/**责权类型（INT2，如：1-管理, 2-维护, 3-监管）*/
    @Schema(description = "责权类型（INT2，如：1-管理, 2-维护, 3-监管）")
    @NotEmpty(message = "责权类型（INT2，如：1-管理, 2-维护, 3-监管）不能为空")
    @ExcelProperty("责权类型（INT2，如：1-管理, 2-维护, 3-监管）")
    @ApiParameter(name = "authorityType", description = "责权类型（INT2，如：1-管理, 2-维护, 3-监管）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Integer authorityType;
	/**单位（责任单位名称）*/
    @Schema(description = "单位（责任单位名称）")
    @NotEmpty(message = "单位（责任单位名称）不能为空")
    @ExcelProperty("单位（责任单位名称）")
    @ApiParameter(name = "unit", description = "单位（责任单位名称）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String unit;
	/**职位（如：负责人、技术员）*/
    @Schema(description = "职位（如：负责人、技术员）")
    @NotEmpty(message = "职位（如：负责人、技术员）不能为空")
    @ExcelProperty("职位（如：负责人、技术员）")
    @ApiParameter(name = "position", description = "职位（如：负责人、技术员）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String position;
	/**厂家名称*/
    @Schema(description = "厂家名称")
    @NotEmpty(message = "厂家名称不能为空")
    @ExcelProperty("厂家名称")
    @ApiParameter(name = "manufacturerName", description = "厂家名称", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String manufacturerName;
	/**电话（联系电话）*/
    @Schema(description = "电话（联系电话）")
    @NotEmpty(message = "电话（联系电话）不能为空")
    @ExcelProperty("电话（联系电话）")
    @ApiParameter(name = "phone", description = "电话（联系电话）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String phone;
	/**排序（数值越小越靠前）*/
    @Schema(description = "排序（数值越小越靠前）")
    @NotEmpty(message = "排序（数值越小越靠前）不能为空")
    @ExcelProperty("排序（数值越小越靠前）")
    @ApiParameter(name = "sortOrder", description = "排序（数值越小越靠前）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Integer sortOrder;
	/**创建时间（精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间（精确到秒）")
    @NotEmpty(message = "创建时间（精确到秒）不能为空")
    @ExcelProperty("创建时间（精确到秒）")
    @ApiParameter(name = "createTime", description = "创建时间（精确到秒）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.util.Date createTime;
	/**创建人ID*/
    @Schema(description = "创建人ID")
    @NotEmpty(message = "创建人ID不能为空")
    @ExcelProperty("创建人ID")
    @ApiParameter(name = "createBy", description = "创建人ID", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String createBy;
	/**更新时间（精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间（精确到秒）")
    @NotEmpty(message = "更新时间（精确到秒）不能为空")
    @ExcelProperty("更新时间（精确到秒）")
    @ApiParameter(name = "updateTime", description = "更新时间（精确到秒）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.util.Date updateTime;
	/**更新人ID*/
    @Schema(description = "更新人ID")
    @NotEmpty(message = "更新人ID不能为空")
    @ExcelProperty("更新人ID")
    @ApiParameter(name = "updateBy", description = "更新人ID", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String updateBy;
	/**租户ID*/
    @Schema(description = "租户ID")
    @NotEmpty(message = "租户ID不能为空")
    @ExcelProperty("租户ID")
    @ApiParameter(name = "tenementId", description = "租户ID", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String tenementId;
    /**责任人id*/
    @Schema(description = "责任人id")
    @NotEmpty(message = "责任人id不能为空")
    @ExcelProperty("责任人id")
    @ApiParameter(name = "responsiblePerson", description = "责任人id", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private String responsiblePerson;
}
