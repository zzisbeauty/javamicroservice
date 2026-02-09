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
 * @Description: 采集器
 * @Author: hanwei
 * @Date:   2026-01-23
 * @Version: V1.0
 */
@Data
@TableName("base_collector")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="BaseCollector对象", description="采集器")
public class BaseCollector {

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键")
    @NotEmpty(message = "主键不能为空")
    @ExcelProperty("主键")
    @ApiParameter(name = "id", description = "主键", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String id;
	/**采集器序列号*/
    @Schema(description = "采集器序列号")
    @NotEmpty(message = "采集器序列号不能为空")
    @ExcelProperty("采集器序列号")
    @ApiParameter(name = "collectorSerialNumber", description = "采集器序列号", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String collectorSerialNumber;
	/**采集器类型*/
    @Schema(description = "采集器类型")
    @NotEmpty(message = "采集器类型不能为空")
    @ExcelProperty("采集器类型")
    @ApiParameter(name = "collectorType", description = "采集器类型", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String collectorType;
	/**供电方式*/
    @Schema(description = "供电方式")
    @NotEmpty(message = "供电方式不能为空")
    @ExcelProperty("供电方式")
    @ApiParameter(name = "powerSupplyMode", description = "供电方式", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String powerSupplyMode;
	/**运行状态*/
    @Schema(description = "运行状态")
    @NotEmpty(message = "运行状态不能为空")
    @ExcelProperty("运行状态")
    @ApiParameter(name = "runStatus", description = "运行状态", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Integer runStatus;
	/**采集频率*/
    @Schema(description = "采集频率")
    @NotEmpty(message = "采集频率不能为空")
    @ExcelProperty("采集频率")
    @ApiParameter(name = "collectFrequency", description = "采集频率", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Integer collectFrequency;
	/**上传频率*/
    @Schema(description = "上传频率")
    @NotEmpty(message = "上传频率不能为空")
    @ExcelProperty("上传频率")
    @ApiParameter(name = "uploadFrequency", description = "上传频率", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Integer uploadFrequency;
	/**安装日期*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @Schema(description = "安装日期")
    @NotEmpty(message = "安装日期不能为空")
    @ExcelProperty("安装日期")
    @ApiParameter(name = "installTime", description = "安装日期", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.util.Date installTime;
	/**厂家*/
    @Schema(description = "厂家")
    @NotEmpty(message = "厂家不能为空")
    @ExcelProperty("厂家")
    @ApiParameter(name = "factory", description = "厂家", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String factory;
	/**联系人*/
    @Schema(description = "联系人")
    @NotEmpty(message = "联系人不能为空")
    @ExcelProperty("联系人")
    @ApiParameter(name = "contactPerson", description = "联系人", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String contactPerson;
	/**联系电话*/
    @Schema(description = "联系电话")
    @NotEmpty(message = "联系电话不能为空")
    @ExcelProperty("联系电话")
    @ApiParameter(name = "contactPhone", description = "联系电话", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String contactPhone;
	/**创建人*/
    @Schema(description = "创建人")
    @NotEmpty(message = "创建人不能为空")
    @ExcelProperty("创建人")
    @ApiParameter(name = "createBy", description = "创建人", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String createBy;
	/**创建时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    @NotEmpty(message = "创建时间不能为空")
    @ExcelProperty("创建时间")
    @ApiParameter(name = "createTime", description = "创建时间", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.util.Date createTime;
	/**修改人*/
    @Schema(description = "修改人")
    @NotEmpty(message = "修改人不能为空")
    @ExcelProperty("修改人")
    @ApiParameter(name = "updateBy", description = "修改人", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String updateBy;
	/**修改时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "修改时间")
    @NotEmpty(message = "修改时间不能为空")
    @ExcelProperty("修改时间")
    @ApiParameter(name = "updateTime", description = "修改时间", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.util.Date updateTime;
	/**租户ID*/
    @Schema(description = "租户ID")
    @NotEmpty(message = "租户ID不能为空")
    @ExcelProperty("租户ID")
    @ApiParameter(name = "tenementId", description = "租户ID", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String tenementId;
	/**设备id*/
    @Schema(description = "设备id")
    @NotEmpty(message = "设备id不能为空")
    @ExcelProperty("设备id")
    @ApiParameter(name = "deviceId", description = "设备id", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String deviceId;
	/**监测点id*/
    @Schema(description = "监测点id")
    @NotEmpty(message = "监测点id不能为空")
    @ExcelProperty("监测点id")
    @ApiParameter(name = "monitorId", description = "监测点id", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String monitorId;
	/**状态（0启用、1禁用）*/
    @Schema(description = "状态（0启用、1禁用）")
    @NotEmpty(message = "状态（0启用、1禁用）不能为空")
    @ExcelProperty("状态（0启用、1禁用）")
    @ApiParameter(name = "status", description = "状态（0启用、1禁用）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Integer status;
}
