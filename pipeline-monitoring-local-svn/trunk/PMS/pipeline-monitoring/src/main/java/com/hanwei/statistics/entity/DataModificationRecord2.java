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
 * @Description: 数据修改记录表2
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Data
@TableName("data_modification_record_2")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="DataModificationRecord2对象", description="数据修改记录表2")
public class DataModificationRecord2 {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    @NotEmpty(message = "主键ID不能为空")
    @ExcelProperty("主键ID")
	private String id;
	/**设备序列号*/
    @Schema(description = "设备序列号")
    @NotEmpty(message = "设备序列号不能为空")
    @ExcelProperty("设备序列号")
	private String deviceSerialNumber;
	/**指标编号（如：flow_rate、pressure 等）*/
    @Schema(description = "指标编号（如：flow_rate、pressure 等）")
    @NotEmpty(message = "指标编号（如：flow_rate、pressure 等）不能为空")
    @ExcelProperty("指标编号（如：flow_rate、pressure 等）")
	private String metricCode;
	/**数据描述（对数据的简要说明）*/
    @Schema(description = "数据描述（对数据的简要说明）")
    @NotEmpty(message = "数据描述（对数据的简要说明）不能为空")
    @ExcelProperty("数据描述（对数据的简要说明）")
	private String dataDescription;
	/**原始数据（修改前的数值）*/
    @Schema(description = "原始数据（修改前的数值）")
    @NotEmpty(message = "原始数据（修改前的数值）不能为空")
    @ExcelProperty("原始数据（修改前的数值）")
	private java.math.BigDecimal originalValue;
	/**原始采集时间（原始数据的采集时间，精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "原始采集时间（原始数据的采集时间，精确到秒）")
    @NotEmpty(message = "原始采集时间（原始数据的采集时间，精确到秒）不能为空")
    @ExcelProperty("原始采集时间（原始数据的采集时间，精确到秒）")
	private java.util.Date originalCollectTime;
	/**修改后数据（修正后的数值）*/
    @Schema(description = "修改后数据（修正后的数值）")
    @NotEmpty(message = "修改后数据（修正后的数值）不能为空")
    @ExcelProperty("修改后数据（修正后的数值）")
	private java.math.BigDecimal modifiedValue;
	/**是否最终值（true-最终确认值，false-临时修正值）*/
    @Schema(description = "是否最终值（true-最终确认值，false-临时修正值）")
    @NotEmpty(message = "是否最终值（true-最终确认值，false-临时修正值）不能为空")
    @ExcelProperty("是否最终值（true-最终确认值，false-临时修正值）")
	private Object isFinalValue;
	/**修改时间（本次修改操作的时间，精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "修改时间（本次修改操作的时间，精确到秒）")
    @NotEmpty(message = "修改时间（本次修改操作的时间，精确到秒）不能为空")
    @ExcelProperty("修改时间（本次修改操作的时间，精确到秒）")
	private java.util.Date modificationTime;
	/**创建时间（记录入库时间，精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间（记录入库时间，精确到秒）")
    @NotEmpty(message = "创建时间（记录入库时间，精确到秒）不能为空")
    @ExcelProperty("创建时间（记录入库时间，精确到秒）")
	private java.util.Date createTime;
	/**创建人ID（执行修改操作的人）*/
    @Schema(description = "创建人ID（执行修改操作的人）")
    @NotEmpty(message = "创建人ID（执行修改操作的人）不能为空")
    @ExcelProperty("创建人ID（执行修改操作的人）")
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
