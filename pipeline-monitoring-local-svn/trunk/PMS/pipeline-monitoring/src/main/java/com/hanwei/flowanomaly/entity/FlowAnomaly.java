package com.hanwei.flowanomaly.entity;

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
 * @Description: 流量异常分析记录
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Data
@TableName("flow_anomaly_record")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="FlowAnomaly对象", description="流量异常分析记录")
public class FlowAnomaly {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    @NotEmpty(message = "主键ID不能为空")
    @ExcelProperty("主键ID")
	private String id;
	/**监测点ID*/
    @Schema(description = "监测点ID")
    @NotEmpty(message = "监测点ID不能为空")
    @ExcelProperty("监测点ID")
	private String monitorPointId;
	/**监测点名称*/
    @Schema(description = "监测点名称")
    @NotEmpty(message = "监测点名称不能为空")
    @ExcelProperty("监测点名称")
	private String monitorPointName;
	/**设备ID*/
    @Schema(description = "设备ID")
    @NotEmpty(message = "设备ID不能为空")
    @ExcelProperty("设备ID")
	private String deviceId;
	/**设备型号*/
    @Schema(description = "设备型号")
    @NotEmpty(message = "设备型号不能为空")
    @ExcelProperty("设备型号")
	private String deviceModel;
	/**厂家*/
    @Schema(description = "厂家")
    @NotEmpty(message = "厂家不能为空")
    @ExcelProperty("厂家")
	private String manufacturer;
	/**异常类型（如：突增／突降／持续高位／数据缺失）*/
    @Schema(description = "异常类型（如：突增／突降／持续高位／数据缺失）")
    @NotEmpty(message = "异常类型（如：突增／突降／持续高位／数据缺失）不能为空")
    @ExcelProperty("异常类型（如：突增／突降／持续高位／数据缺失）")
	private String anomalyType;
	/**异常时间（精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "异常时间（精确到秒）")
    @NotEmpty(message = "异常时间（精确到秒）不能为空")
    @ExcelProperty("异常时间（精确到秒）")
	private java.util.Date anomalyTime;
	/**异常内容（详细描述异常情况）*/
    @Schema(description = "异常内容（详细描述异常情况）")
    @NotEmpty(message = "异常内容（详细描述异常情况）不能为空")
    @ExcelProperty("异常内容（详细描述异常情况）")
	private Object anomalyContent;
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
