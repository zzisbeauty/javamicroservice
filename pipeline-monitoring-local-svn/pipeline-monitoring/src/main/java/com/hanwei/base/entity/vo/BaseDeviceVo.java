package com.hanwei.base.entity.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.common.ApiEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 设备信息
 * @Author: hanwei
 * @Date:   2026-01-22
 * @Version: V1.0
 */
@Data
public class BaseDeviceVo {

	/**设备id*/
    @Schema(description = "设备id")
    @ApiParameter(name = "id", description = "设备id")
	private String id;
	/**设备名称*/
    @Schema(description = "设备名称")
    @ApiParameter(name = "deviceName", description = "设备名称")
	private String deviceName;
	/**设备序列号*/
    @Schema(description = "设备序列号")
    @ApiParameter(name = "deviceSerialNumber", description = "设备序列号")
	private String deviceSerialNumber;
	/**采集器序列号*/
    @Schema(description = "采集器序列号")
    @ApiParameter(name = "collectorSerialNumber", description = "采集器序列号")
	private String collectorSerialNumber;
	/**设备类型（1流量、2压力、3水质）*/
    @Schema(description = "设备类型（1流量、2压力、3水质）")
    @ApiParameter(name = "deviceType", description = "设备类型（1流量、2压力、3水质）")
	private Integer deviceType;
	/**sim卡信息*/
    @Schema(description = "sim卡信息")
    @ApiParameter(name = "sim", description = "sim卡信息")
	private String sim;
	/**x坐标*/
    @Schema(description = "x坐标")
    @ApiParameter(name = "coordinateX", description = "x坐标")
	private java.math.BigDecimal coordinateX;
	/**y坐标*/
    @Schema(description = "y坐标")
    @ApiParameter(name = "coordinateY", description = "y坐标")
	private java.math.BigDecimal coordinateY;
	/**口径*/
    @Schema(description = "口径")
    @ApiParameter(name = "caliber", description = "口径")
	private String caliber;
	/**厂家*/
    @Schema(description = "厂家")
    @ApiParameter(name = "factory", description = "厂家")
	private String factory;
	/**备注*/
    @Schema(description = "备注")
    @ApiParameter(name = "remark", description = "备注")
	private String remark;
	/**创建人*/
    @Schema(description = "创建人")
    @ApiParameter(name = "createBy", description = "创建人")
	private String createBy;
	/**创建时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    @ApiParameter(name = "createTime", description = "创建时间")
	private java.util.Date createTime;

	/**采集频率*/
    @Schema(description = "采集频率")
    @ApiParameter(name = "collectFrequency", description = "采集频率")
	private Integer collectFrequency;
	/**上传频率*/
    @Schema(description = "上传频率")
    @ApiParameter(name = "uploadFrequency", description = "上传频率")
	private Integer uploadFrequency;
	/**供电方式*/
    @Schema(description = "供电方式")
    @ApiParameter(name = "powerSupplyMode", description = "供电方式")
	private String powerSupplyMode;
	/**运行状态（0在线、1离线、2报警）*/
    @Schema(description = "运行状态（0在线、1离线、2报警）")
    @ApiParameter(name = "runStatus", description = "运行状态（0在线、1离线、2报警）")
	private Integer runStatus;
	/**最小流量Q1*/
    @Schema(description = "最小流量Q1")
    @ApiParameter(name = "q1", description = "最小流量Q1")
	private java.math.BigDecimal q1;
	/**分解流量Q2*/
    @Schema(description = "分解流量Q2")
    @ApiParameter(name = "q2 ", description = "分解流量Q2")
	private java.math.BigDecimal q2 ;
	/**常用流量Q3*/
    @Schema(description = "常用流量Q3")
    @ApiParameter(name = "q3", description = "常用流量Q3")
	private java.math.BigDecimal q3;
	/**过载流量Q4*/
    @Schema(description = "过载流量Q4")
    @ApiParameter(name = "q4", description = "过载流量Q4")
	private java.math.BigDecimal q4;
	/**最小量程*/
    @Schema(description = "最小量程")
    @ApiParameter(name = "minRange", description = "最小量程")
	private java.math.BigDecimal minRange;
	/**最大量程*/
    @Schema(description = "最大量程")
    @ApiParameter(name = "maxRange", description = "最大量程")
	private java.math.BigDecimal maxRange;
	/**状态（0启用、1禁用）*/
    @Schema(description = "状态（0启用、1禁用）")
    @ApiParameter(name = "status", description = "状态（0启用、1禁用）")
	private Integer status;

    /**最新数据时间*/
    @Schema(description = "最新数据时间")
    @ApiParameter(name = "lastDataTime", description = "最新数据时间")
    private java.util.Date lastDataTime;
}
