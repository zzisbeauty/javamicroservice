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
 * @Description: 设备信息
 * @Author: hanwei
 * @Date:   2026-01-22
 * @Version: V1.0
 */
@Data
@TableName("base_device")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="BaseDevice对象", description="设备信息")
public class BaseDevice {

	/**设备id*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "设备id")
    @NotEmpty(message = "设备id不能为空")
    @ExcelProperty("设备id")
    @ApiParameter(name = "id", description = "设备id", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String id;
	/**设备名称*/
    @Schema(description = "设备名称")
    @NotEmpty(message = "设备名称不能为空")
    @ExcelProperty("设备名称")
    @ApiParameter(name = "deviceName", description = "设备名称", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String deviceName;
	/**设备序列号*/
    @Schema(description = "设备序列号")
    @NotEmpty(message = "设备序列号不能为空")
    @ExcelProperty("设备序列号")
    @ApiParameter(name = "deviceSerialNumber", description = "设备序列号", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String deviceSerialNumber;
	/**采集器序列号*/
    @Schema(description = "采集器序列号")
    @NotEmpty(message = "采集器序列号不能为空")
    @ExcelProperty("采集器序列号")
    @ApiParameter(name = "collectorSerialNumber", description = "采集器序列号", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String collectorSerialNumber;
	/**设备类型（1流量、2压力、3水质）*/
    @Schema(description = "设备类型（1流量、2压力、3水质）")
    @NotEmpty(message = "设备类型（1流量、2压力、3水质）不能为空")
    @ExcelProperty("设备类型（1流量、2压力、3水质）")
    @ApiParameter(name = "deviceType", description = "设备类型（1流量、2压力、3水质）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Integer deviceType;
	/**sim卡信息*/
    @Schema(description = "sim卡信息")
    @NotEmpty(message = "sim卡信息不能为空")
    @ExcelProperty("sim卡信息")
    @ApiParameter(name = "sim", description = "sim卡信息", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String sim;
	/**x坐标*/
    @Schema(description = "x坐标")
    @NotEmpty(message = "x坐标不能为空")
    @ExcelProperty("x坐标")
    @ApiParameter(name = "coordinateX", description = "x坐标", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.math.BigDecimal coordinateX;
	/**y坐标*/
    @Schema(description = "y坐标")
    @NotEmpty(message = "y坐标不能为空")
    @ExcelProperty("y坐标")
    @ApiParameter(name = "coordinateY", description = "y坐标", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.math.BigDecimal coordinateY;
	/**口径*/
    @Schema(description = "口径")
    @NotEmpty(message = "口径不能为空")
    @ExcelProperty("口径")
    @ApiParameter(name = "caliber", description = "口径", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String caliber;
	/**厂家*/
    @Schema(description = "厂家")
    @NotEmpty(message = "厂家不能为空")
    @ExcelProperty("厂家")
    @ApiParameter(name = "factory", description = "厂家", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String factory;
	/**备注*/
    @Schema(description = "备注")
    @NotEmpty(message = "备注不能为空")
    @ExcelProperty("备注")
    @ApiParameter(name = "remark", description = "备注", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String remark;
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
	/**供电方式*/
    @Schema(description = "供电方式")
    @NotEmpty(message = "供电方式不能为空")
    @ExcelProperty("供电方式")
    @ApiParameter(name = "powerSupplyMode", description = "供电方式", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String powerSupplyMode;
	/**运行状态（0在线、1离线、2报警）*/
    @Schema(description = "运行状态（0在线、1离线、2报警）")
    @NotEmpty(message = "运行状态（0在线、1离线、2报警）不能为空")
    @ExcelProperty("运行状态（0在线、1离线、2报警）")
    @ApiParameter(name = "runStatus", description = "运行状态（0在线、1离线、2报警）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Integer runStatus;
	/**最小流量Q1*/
    @Schema(description = "最小流量Q1")
    @NotEmpty(message = "最小流量Q1不能为空")
    @ExcelProperty("最小流量Q1")
    @ApiParameter(name = "q1", description = "最小流量Q1", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.math.BigDecimal q1;
	/**分解流量Q2*/
    @Schema(description = "分解流量Q2")
    @NotEmpty(message = "分解流量Q2不能为空")
    @ExcelProperty("分解流量Q2")
    @ApiParameter(name = "q2", description = "分解流量Q2", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.math.BigDecimal q2 ;
	/**常用流量Q3*/
    @Schema(description = "常用流量Q3")
    @NotEmpty(message = "常用流量Q3不能为空")
    @ExcelProperty("常用流量Q3")
    @ApiParameter(name = "q3", description = "常用流量Q3", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.math.BigDecimal q3;
	/**过载流量Q4*/
    @Schema(description = "过载流量Q4")
    @NotEmpty(message = "过载流量Q4不能为空")
    @ExcelProperty("过载流量Q4")
    @ApiParameter(name = "q4", description = "过载流量Q4", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.math.BigDecimal q4;
	/**最小量程*/
    @Schema(description = "最小量程")
    @NotEmpty(message = "最小量程不能为空")
    @ExcelProperty("最小量程")
    @ApiParameter(name = "minRange", description = "最小量程", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.math.BigDecimal minRange;
	/**最大量程*/
    @Schema(description = "最大量程")
    @NotEmpty(message = "最大量程不能为空")
    @ExcelProperty("最大量程")
    @ApiParameter(name = "maxRange", description = "最大量程", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.math.BigDecimal maxRange;
	/**状态（0启用、1禁用）*/
    @Schema(description = "状态（0启用、1禁用）")
    @NotEmpty(message = "状态（0启用、1禁用）不能为空")
    @ExcelProperty("状态（0启用、1禁用）")
    @ApiParameter(name = "status", description = "状态（0启用、1禁用）", demovalue = "0", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Integer status;
}
