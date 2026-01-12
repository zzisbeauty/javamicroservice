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
 * @Description: 实时数据信息表
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Data
@TableName("realtime_data_info")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="RealTimeData对象", description="实时数据信息表")
public class RealTimeData {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    @NotEmpty(message = "主键ID不能为空")
    @ExcelProperty("主键ID")
	private String id;
	/**数据ID（业务唯一标识，如传感器数据编号）*/
    @Schema(description = "数据ID（业务唯一标识，如传感器数据编号）")
    @NotEmpty(message = "数据ID（业务唯一标识，如传感器数据编号）不能为空")
    @ExcelProperty("数据ID（业务唯一标识，如传感器数据编号）")
	private String dataId;
	/**采集时间（设备实际采集数据的时间，精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "采集时间（设备实际采集数据的时间，精确到秒）")
    @NotEmpty(message = "采集时间（设备实际采集数据的时间，精确到秒）不能为空")
    @ExcelProperty("采集时间（设备实际采集数据的时间，精确到秒）")
	private java.util.Date collectTime;
	/**上传时间（数据上传至服务器的时间，精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "上传时间（数据上传至服务器的时间，精确到秒）")
    @NotEmpty(message = "上传时间（数据上传至服务器的时间，精确到秒）不能为空")
    @ExcelProperty("上传时间（数据上传至服务器的时间，精确到秒）")
	private java.util.Date uploadTime;
	/**数值（如：123.456）*/
    @Schema(description = "数值（如：123.456）")
    @NotEmpty(message = "数值（如：123.456）不能为空")
    @ExcelProperty("数值（如：123.456）")
	private java.math.BigDecimal value;
	/**单位（如：m³/h、kPa、℃、% 等）*/
    @Schema(description = "单位（如：m³/h、kPa、℃、% 等）")
    @NotEmpty(message = "单位（如：m³/h、kPa、℃、% 等）不能为空")
    @ExcelProperty("单位（如：m³/h、kPa、℃、% 等）")
	private String unit;
	/**数据描述（对数据的简要说明）*/
    @Schema(description = "数据描述（对数据的简要说明）")
    @NotEmpty(message = "数据描述（对数据的简要说明）不能为空")
    @ExcelProperty("数据描述（对数据的简要说明）")
	private String dataDescription;
	/**创建时间（记录入库时间，精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间（记录入库时间，精确到秒）")
    @NotEmpty(message = "创建时间（记录入库时间，精确到秒）不能为空")
    @ExcelProperty("创建时间（记录入库时间，精确到秒）")
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
