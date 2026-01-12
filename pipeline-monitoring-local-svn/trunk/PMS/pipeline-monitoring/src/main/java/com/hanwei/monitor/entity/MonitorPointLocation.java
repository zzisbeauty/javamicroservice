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
 * @Description: 监测点位信息表
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Data
@TableName("monitor_point_location_info")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="MonitorPointLocation对象", description="监测点位信息表")
public class MonitorPointLocation {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    @NotEmpty(message = "主键ID不能为空")
    @ExcelProperty("主键ID")
	private String id;
	/**编号（点位唯一业务编号）*/
    @Schema(description = "编号（点位唯一业务编号）")
    @NotEmpty(message = "编号（点位唯一业务编号）不能为空")
    @ExcelProperty("编号（点位唯一业务编号）")
	private String code;
	/**厂家名称*/
    @Schema(description = "厂家名称")
    @NotEmpty(message = "厂家名称不能为空")
    @ExcelProperty("厂家名称")
	private String manufacturerName;
	/**部门编码*/
    @Schema(description = "部门编码")
    @NotEmpty(message = "部门编码不能为空")
    @ExcelProperty("部门编码")
	private String departmentCode;
	/**状态（INT2，如：0-停用, 1-启用）*/
    @Schema(description = "状态（INT2，如：0-停用, 1-启用）")
    @NotEmpty(message = "状态（INT2，如：0-停用, 1-启用）不能为空")
    @ExcelProperty("状态（INT2，如：0-停用, 1-启用）")
	private Integer status;
	/**类型（INT2，如：1-视频, 2-传感器, 3-环境监测）*/
    @Schema(description = "类型（INT2，如：1-视频, 2-传感器, 3-环境监测）")
    @NotEmpty(message = "类型（INT2，如：1-视频, 2-传感器, 3-环境监测）不能为空")
    @ExcelProperty("类型（INT2，如：1-视频, 2-传感器, 3-环境监测）")
	private Integer type;
	/**经度（WGS84坐标系，数值类型）*/
    @Schema(description = "经度（WGS84坐标系，数值类型）")
    @NotEmpty(message = "经度（WGS84坐标系，数值类型）不能为空")
    @ExcelProperty("经度（WGS84坐标系，数值类型）")
	private java.math.BigDecimal longitude;
	/**纬度（WGS84坐标系，数值类型）*/
    @Schema(description = "纬度（WGS84坐标系，数值类型）")
    @NotEmpty(message = "纬度（WGS84坐标系，数值类型）不能为空")
    @ExcelProperty("纬度（WGS84坐标系，数值类型）")
	private java.math.BigDecimal latitude;
	/**图片（点位示意图或现场照片URL）*/
    @Schema(description = "图片（点位示意图或现场照片URL）")
    @NotEmpty(message = "图片（点位示意图或现场照片URL）不能为空")
    @ExcelProperty("图片（点位示意图或现场照片URL）")
	private String imageUrl;
	/**详细地址*/
    @Schema(description = "详细地址")
    @NotEmpty(message = "详细地址不能为空")
    @ExcelProperty("详细地址")
	private String addressDetail;
	/**区域编码（如行政区划代码）*/
    @Schema(description = "区域编码（如行政区划代码）")
    @NotEmpty(message = "区域编码（如行政区划代码）不能为空")
    @ExcelProperty("区域编码（如行政区划代码）")
	private String areaCode;
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
