package com.hanwei.data.entity;

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
 * @Description: 实时数据
 * @Author: hanwei
 * @Date:   2026-01-22
 * @Version: V1.0
 */
@Data
@TableName("data_realtime")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="DataRealtime对象", description="实时数据")
public class DataRealtime {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    @NotEmpty(message = "主键ID不能为空")
    @ExcelProperty("主键ID")
    @ApiParameter(name = "id", description = "主键ID", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String id;
	/**数据ID（业务唯一标识，如传感器数据编号）*/
    @Schema(description = "数据ID（业务唯一标识，如传感器数据编号）")
    @NotEmpty(message = "数据ID（业务唯一标识，如传感器数据编号）不能为空")
    @ExcelProperty("数据ID（业务唯一标识，如传感器数据编号）")
    @ApiParameter(name = "dataId", description = "数据ID（业务唯一标识，如传感器数据编号）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String dataId;
	/**采集时间（设备实际采集数据的时间，精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "采集时间（设备实际采集数据的时间，精确到秒）")
    @NotEmpty(message = "采集时间（设备实际采集数据的时间，精确到秒）不能为空")
    @ExcelProperty("采集时间（设备实际采集数据的时间，精确到秒）")
    @ApiParameter(name = "collectTime", description = "采集时间（设备实际采集数据的时间，精确到秒）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.util.Date collectTime;
	/**上传时间（数据上传至服务器的时间，精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "上传时间（数据上传至服务器的时间，精确到秒）")
    @NotEmpty(message = "上传时间（数据上传至服务器的时间，精确到秒）不能为空")
    @ExcelProperty("上传时间（数据上传至服务器的时间，精确到秒）")
    @ApiParameter(name = "uploadTime", description = "上传时间（数据上传至服务器的时间，精确到秒）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.util.Date uploadTime;
	/**数值（如：123.456）*/
    @Schema(description = "数值（如：123.456）")
    @NotEmpty(message = "数值（如：123.456）不能为空")
    @ExcelProperty("数值（如：123.456）")
    @ApiParameter(name = "value", description = "数值（如：123.456）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.math.BigDecimal value;
	/**单位（如：m³/h、kPa、℃、% 等）*/
    @Schema(description = "单位（如：m³/h、kPa、℃、% 等）")
    @NotEmpty(message = "单位（如：m³/h、kPa、℃、% 等）不能为空")
    @ExcelProperty("单位（如：m³/h、kPa、℃、% 等）")
    @ApiParameter(name = "unit", description = "单位（如：m³/h、kPa、℃、% 等）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String unit;
	/**数据描述（对数据的简要说明）*/
    @Schema(description = "数据描述（对数据的简要说明）")
    @NotEmpty(message = "数据描述（对数据的简要说明）不能为空")
    @ExcelProperty("数据描述（对数据的简要说明）")
    @ApiParameter(name = "remark", description = "数据描述（对数据的简要说明）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String remark;
	/**创建时间（记录入库时间，精确到秒）*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间（记录入库时间，精确到秒）")
    @NotEmpty(message = "创建时间（记录入库时间，精确到秒）不能为空")
    @ExcelProperty("创建时间（记录入库时间，精确到秒）")
    @ApiParameter(name = "createTime", description = "创建时间（记录入库时间，精确到秒）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
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
	/**设备id*/
    @Schema(description = "设备id")
    @NotEmpty(message = "设备id不能为空")
    @ExcelProperty("设备id")
    @ApiParameter(name = "deviceId", description = "设备id", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String deviceId;
}
