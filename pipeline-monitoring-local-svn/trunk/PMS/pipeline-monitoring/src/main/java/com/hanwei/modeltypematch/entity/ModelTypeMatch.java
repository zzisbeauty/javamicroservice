package com.hanwei.modeltypematch.entity;

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
 * @Description: 型号匹配记录
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Data
@TableName("model_matching_record")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="ModelTypeMatch对象", description="型号匹配记录")
public class ModelTypeMatch {

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
	/**最小量程（设备量程下限）*/
    @Schema(description = "最小量程（设备量程下限）")
    @NotEmpty(message = "最小量程（设备量程下限）不能为空")
    @ExcelProperty("最小量程（设备量程下限）")
	private java.math.BigDecimal minRange;
	/**最大量程（设备量程上限）*/
    @Schema(description = "最大量程（设备量程上限）")
    @NotEmpty(message = "最大量程（设备量程上限）不能为空")
    @ExcelProperty("最大量程（设备量程上限）")
	private java.math.BigDecimal maxRange;
	/**30天小于最小量程占比（单位：%，如 12.50 表示 12.50%）*/
    @Schema(description = "30天小于最小量程占比（单位：%，如 12.50 表示 12.50%）")
    @NotEmpty(message = "30天小于最小量程占比（单位：%，如 12.50 表示 12.50%）不能为空")
    @ExcelProperty("30天小于最小量程占比（单位：%，如 12.50 表示 12.50%）")
	private java.math.BigDecimal belowMinRatio30d;
	/**30天大于最大量程占比（单位：%，如 5.75 表示 5.75%）*/
    @Schema(description = "30天大于最大量程占比（单位：%，如 5.75 表示 5.75%）")
    @NotEmpty(message = "30天大于最大量程占比（单位：%，如 5.75 表示 5.75%）不能为空")
    @ExcelProperty("30天大于最大量程占比（单位：%，如 5.75 表示 5.75%）")
	private java.math.BigDecimal aboveMaxRatio30d;
	/**30天平均流量*/
    @Schema(description = "30天平均流量")
    @NotEmpty(message = "30天平均流量不能为空")
    @ExcelProperty("30天平均流量")
	private java.math.BigDecimal avgFlow30d;
	/**分析结果（如：匹配良好／量程不足／设备异常）*/
    @Schema(description = "分析结果（如：匹配良好／量程不足／设备异常）")
    @NotEmpty(message = "分析结果（如：匹配良好／量程不足／设备异常）不能为空")
    @ExcelProperty("分析结果（如：匹配良好／量程不足／设备异常）")
	private String analysisResult;
	/**分析日期（精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "分析日期（精确到秒）")
    @NotEmpty(message = "分析日期（精确到秒）不能为空")
    @ExcelProperty("分析日期（精确到秒）")
	private java.util.Date analysisDate;
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
