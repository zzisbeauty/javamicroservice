package com.hanwei.statistics.entity;

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
 * @Description: 月度汇聚信息表
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Data
@TableName("monthly_aggregation_info")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="Monthly对象", description="月度汇聚信息表")
public class Monthly {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    @NotEmpty(message = "主键ID不能为空")
    @ExcelProperty("主键ID")
	private String id;
	/**汇聚时间（如 2025-01-01 00:00:00 表示 1月起始，精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "汇聚时间（如 2025-01-01 00:00:00 表示 1月起始，精确到秒）")
    @NotEmpty(message = "汇聚时间（如 2025-01-01 00:00:00 表示 1月起始，精确到秒）不能为空")
    @ExcelProperty("汇聚时间（如 2025-01-01 00:00:00 表示 1月起始，精确到秒）")
	private java.util.Date aggregationTime;
	/**设备序列号*/
    @Schema(description = "设备序列号")
    @NotEmpty(message = "设备序列号不能为空")
    @ExcelProperty("设备序列号")
	private String deviceSerialNumber;
	/**指标信息（如：流量、压力、温度等指标编码）*/
    @Schema(description = "指标信息（如：流量、压力、温度等指标编码）")
    @NotEmpty(message = "指标信息（如：流量、压力、温度等指标编码）不能为空")
    @ExcelProperty("指标信息（如：流量、压力、温度等指标编码）")
	private String metricInfo;
	/**最大值*/
    @Schema(description = "最大值")
    @NotEmpty(message = "最大值不能为空")
    @ExcelProperty("最大值")
	private java.math.BigDecimal maxValue;
	/**最大值时间（精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "最大值时间（精确到秒）")
    @NotEmpty(message = "最大值时间（精确到秒）不能为空")
    @ExcelProperty("最大值时间（精确到秒）")
	private java.util.Date maxValueTime;
	/**最小值*/
    @Schema(description = "最小值")
    @NotEmpty(message = "最小值不能为空")
    @ExcelProperty("最小值")
	private java.math.BigDecimal minValue;
	/**最小值时间（精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "最小值时间（精确到秒）")
    @NotEmpty(message = "最小值时间（精确到秒）不能为空")
    @ExcelProperty("最小值时间（精确到秒）")
	private java.util.Date minValueTime;
	/**平均值*/
    @Schema(description = "平均值")
    @NotEmpty(message = "平均值不能为空")
    @ExcelProperty("平均值")
	private java.math.BigDecimal avgValue;
	/**和值*/
    @Schema(description = "和值")
    @NotEmpty(message = "和值不能为空")
    @ExcelProperty("和值")
	private java.math.BigDecimal sumValue;
	/**最初值（月度内第一条记录的值）*/
    @Schema(description = "最初值（月度内第一条记录的值）")
    @NotEmpty(message = "最初值（月度内第一条记录的值）不能为空")
    @ExcelProperty("最初值（月度内第一条记录的值）")
	private java.math.BigDecimal firstValue;
	/**最初值时间（精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "最初值时间（精确到秒）")
    @NotEmpty(message = "最初值时间（精确到秒）不能为空")
    @ExcelProperty("最初值时间（精确到秒）")
	private java.util.Date firstValueTime;
	/**最末值（月度内最后一条记录的值）*/
    @Schema(description = "最末值（月度内最后一条记录的值）")
    @NotEmpty(message = "最末值（月度内最后一条记录的值）不能为空")
    @ExcelProperty("最末值（月度内最后一条记录的值）")
	private java.math.BigDecimal lastValue;
	/**最末值时间（精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "最末值时间（精确到秒）")
    @NotEmpty(message = "最末值时间（精确到秒）不能为空")
    @ExcelProperty("最末值时间（精确到秒）")
	private java.util.Date lastValueTime;
	/**汇聚条数（原始数据记录数量）*/
    @Schema(description = "汇聚条数（原始数据记录数量）")
    @NotEmpty(message = "汇聚条数（原始数据记录数量）不能为空")
    @ExcelProperty("汇聚条数（原始数据记录数量）")
	private Integer recordCount;
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
