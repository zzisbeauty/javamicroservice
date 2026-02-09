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
 * @Description: 监测点关联信息
 * @Author: hanwei
 * @Date:   2026-01-23
 * @Version: V1.0
 */
@Data
@TableName("base_monitor_relate")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="BaseMonitorRelate对象", description="监测点关联信息")
public class BaseMonitorRelate {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    @NotEmpty(message = "主键ID不能为空")
    @ExcelProperty("主键ID")
    @ApiParameter(name = "id", description = "主键ID", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String id;
	/**监测点ID（关联监测点位信息）*/
    @Schema(description = "监测点ID（关联监测点位信息）")
    @NotEmpty(message = "监测点ID（关联监测点位信息）不能为空")
    @ExcelProperty("监测点ID（关联监测点位信息）")
    @ApiParameter(name = "monitorPointId", description = "监测点ID（关联监测点位信息）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String monitorPointId;
	/**场景ID（关联场景基础信息）*/
    @Schema(description = "场景ID（关联场景基础信息）")
    @NotEmpty(message = "场景ID（关联场景基础信息）不能为空")
    @ExcelProperty("场景ID（关联场景基础信息）")
    @ApiParameter(name = "sceneId", description = "场景ID（关联场景基础信息）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String sceneId;
	/**数据ID（关联原始数据记录）*/
    @Schema(description = "数据ID（关联原始数据记录）")
    @NotEmpty(message = "数据ID（关联原始数据记录）不能为空")
    @ExcelProperty("数据ID（关联原始数据记录）")
    @ApiParameter(name = "dataId", description = "数据ID（关联原始数据记录）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String dataId;
	/**设备ID*/
    @Schema(description = "设备ID")
    @NotEmpty(message = "设备ID不能为空")
    @ExcelProperty("设备ID")
    @ApiParameter(name = "deviceId", description = "设备ID", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String deviceId;
	/**指标ID（如：温度、压力、流量等）*/
    @Schema(description = "指标ID（如：温度、压力、流量等）")
    @NotEmpty(message = "指标ID（如：温度、压力、流量等）不能为空")
    @ExcelProperty("指标ID（如：温度、压力、流量等）")
    @ApiParameter(name = "metricId", description = "指标ID（如：温度、压力、流量等）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String metricId;
	/**设备序列号*/
    @Schema(description = "设备序列号")
    @NotEmpty(message = "设备序列号不能为空")
    @ExcelProperty("设备序列号")
    @ApiParameter(name = "deviceSerialNumber", description = "设备序列号", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String deviceSerialNumber;
	/**状态（INT2，如：0-无效, 1-有效, 2-停用）*/
    @Schema(description = "状态（INT2，如：0-无效, 1-有效, 2-停用）")
    @NotEmpty(message = "状态（INT2，如：0-无效, 1-有效, 2-停用）不能为空")
    @ExcelProperty("状态（INT2，如：0-无效, 1-有效, 2-停用）")
    @ApiParameter(name = "status", description = "状态（INT2，如：0-无效, 1-有效, 2-停用）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Integer status;
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
	/**设备名称*/
    @Schema(description = "设备名称")
    @NotEmpty(message = "设备名称不能为空")
    @ExcelProperty("设备名称")
    @ApiParameter(name = "deviceName", description = "设备名称", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String deviceName;


    /**属性编码*/
    @Schema(description = "属性编码")
    @NotEmpty(message = "属性编码")
    @ExcelProperty("属性编码")
    @ApiParameter(name = "propertyCode", description = "属性编码", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private String propertyCode;



}
