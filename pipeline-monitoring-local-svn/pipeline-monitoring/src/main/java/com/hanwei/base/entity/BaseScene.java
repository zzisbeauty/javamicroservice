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
 * @Description: 场景表
 * @Author: hanwei
 * @Date:   2026-01-26
 * @Version: V1.0
 */
@Data
@TableName("base_scene")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="BaseScene对象", description="场景表")
public class BaseScene {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    @NotEmpty(message = "主键ID不能为空")
    @ExcelProperty("主键ID")
    @ApiParameter(name = "id", description = "主键ID", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String id;
	/**编号（场景唯一业务编号）*/
    @Schema(description = "编号（场景唯一业务编号）")
    @NotEmpty(message = "编号（场景唯一业务编号）不能为空")
    @ExcelProperty("编号（场景唯一业务编号）")
    @ApiParameter(name = "code", description = "编号（场景唯一业务编号）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String code;
	/**场景名称*/
    @Schema(description = "场景名称")
    @NotEmpty(message = "场景名称不能为空")
    @ExcelProperty("场景名称")
    @ApiParameter(name = "sceneName", description = "场景名称", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String sceneName;
	/**备注*/
    @Schema(description = "备注")
    @NotEmpty(message = "备注不能为空")
    @ExcelProperty("备注")
    @ApiParameter(name = "remark", description = "备注", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String remark;
	/**状态（如：启用／停用／测试中）*/
    @Schema(description = "状态（如：启用／停用／测试中）")
    @NotEmpty(message = "状态（如：启用／停用／测试中）不能为空")
    @ExcelProperty("状态（如：启用／停用／测试中）")
    @ApiParameter(name = "status", description = "状态（如：启用／停用／测试中）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String status;
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
}
