package com.hanwei.base.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
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
 * @Description: 监测点
 * @Author: hanwei
 * @Date:   2026-01-22
 * @Version: V1.0
 */
@Data
@TableName("base_monitor")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="BaseMonitor对象", description="监测点")
public class BaseMonitor {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    @NotEmpty(message = "主键ID不能为空")
    @ExcelIgnore
    @ApiParameter(name = "id", description = "主键ID", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String id;
	/**编号*/
    @Schema(description = "监测点编号")
    @NotEmpty(message = "监测点编号不能为空")
    @ExcelProperty("监测点编号")
    @ApiParameter(name = "code", description = "监测点编号", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String code;
	/**监测点名称*/
    @Schema(description = "监测点名称")
    @NotEmpty(message = "监测点名称不能为空")
    @ExcelProperty("监测点名称")
    @ExcelIgnore//导入导出同时都取消，不支持单个设置
    @ApiParameter(name = "name", description = "监测点名称", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String name;
	/**类型（1流量、2压力、3水质、4流量压力、5流量水质、6压力水质）*/
    @Schema(description = "类型（1流量、2压力、3水质、4流量压力、5流量水质、6压力水质）")
    @NotEmpty(message = "类型（1流量、2压力、3水质、4流量压力、5流量水质、6压力水质）不能为空")
    @ExcelProperty("类型（1流量、2压力、3水质、4流量压力、5流量水质、6压力水质）")
    @ApiParameter(name = "type", description = "类型（1流量、2压力、3水质、4流量压力、5流量水质、6压力水质）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Integer type;
	/**x坐标*/
    @Schema(description = "x坐标")
    @ExcelIgnore
    @ApiParameter(name = "coordinateX", description = "x坐标", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.math.BigDecimal coordinateX;
	/**y坐标*/
    @Schema(description = "y坐标")
    @ExcelIgnore
    @ApiParameter(name = "coordinateY", description = "y坐标", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.math.BigDecimal coordinateY;
	/**图片（点位示意图或现场照片URL）*/
    @Schema(description = "图片（点位示意图或现场照片URL）")
    @ApiParameter(name = "imageUrl", description = "图片（点位示意图或现场照片URL）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String imageUrl;
	/**详细地址*/
    @Schema(description = "详细地址")
    @ExcelProperty("详细地址")
    @ApiParameter(name = "address", description = "详细地址", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String address;
	/**区域编码（如行政区划代码）*/
    @Schema(description = "区域编码（如行政区划代码）")
    @ExcelIgnore
    @ApiParameter(name = "areaCode", description = "区域编码（如行政区划代码）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String areaCode;
	/**创建时间（精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间（精确到秒）")
    @ExcelIgnore
    @ApiParameter(name = "createTime", description = "创建时间（精确到秒）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.util.Date createTime;
	/**创建人ID*/
    @Schema(description = "创建人ID")
    @ExcelIgnore
    @ApiParameter(name = "createBy", description = "创建人ID", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String createBy;
	/**更新时间（精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ExcelIgnore
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间（精确到秒）")
    @ApiParameter(name = "updateTime", description = "更新时间（精确到秒）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.util.Date updateTime;
	/**更新人ID*/
    @Schema(description = "更新人ID")
    @ExcelIgnore
    @ApiParameter(name = "updateBy", description = "更新人ID", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String updateBy;
	/**租户ID*/
    @Schema(description = "租户ID")
    @ExcelIgnore
    @ApiParameter(name = "tenementId", description = "租户ID", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String tenementId;
	/**状态（0启用、1禁用）*/
    @Schema(description = "状态（0启用、1禁用）")
    @NotEmpty(message = "状态（0启用、1禁用）不能为空")
    @ExcelProperty("状态（0启用、1禁用）")
    @ApiParameter(name = "status", description = "状态（0启用、1禁用）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Integer status;
	/**备注*/
    @Schema(description = "备注")
    @ExcelProperty("备注")
    @ApiParameter(name = "remark", description = "备注", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String remark;
	/**运行状态（0在线、1离线、2报警）*/
    @Schema(description = "运行状态（0在线、1离线、2报警）")
    @NotEmpty(message = "运行状态（0在线、1离线、2报警）不能为空")
    @ExcelProperty("运行状态（0在线、1离线、2报警）")
    @ApiParameter(name = "runStatus", description = "运行状态（0在线、1离线、2报警）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Integer runStatus;

    /**采集器编号*/
    @Schema(description = "采集器编号")
    @NotEmpty(message = "采集器编号")
    @ExcelProperty("采集器编号")
    @ApiParameter(name = "collectorCode", description = "采集器编号", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private String collectorCode;

    /**级别*/
    @Schema(description = "级别（1一级、2二级、3三级）")
    @NotEmpty(message = "级别（1一级、2二级、3三级）")
    @ExcelProperty("级别（1一级、2二级、3三级）")
    @ApiParameter(name = "level", description = "级别（1一级、2二级、3三级）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private Integer level;



}
