package com.hanwei.sim.entity;

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
 * @Description: SIM卡管理器
 * @Author: hanwei
 * @Date:   2026-01-04
 * @Version: V1.0
 */
@Data
@TableName("sim_info")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="SimInfo对象", description="SIM卡管理器")
public class SimInfo {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    @NotEmpty(message = "主键ID不能为空")
    @ExcelProperty("主键ID")
	private Integer id;
	/**注册信息：物理卡号(ICCID)*/
    @Schema(description = "注册信息：物理卡号(ICCID)")
    @NotEmpty(message = "注册信息：物理卡号(ICCID)不能为空")
    @ExcelProperty("注册信息：物理卡号(ICCID)")
	private String iccid;
	/**注册信息：卡号/手机号*/
    @Schema(description = "注册信息：卡号/手机号")
    @NotEmpty(message = "注册信息：卡号/手机号不能为空")
    @ExcelProperty("注册信息：卡号/手机号")
	private String msisdn;
	/**注册信息：运营商*/
    @Schema(description = "注册信息：运营商")
    @NotEmpty(message = "注册信息：运营商不能为空")
    @ExcelProperty("注册信息：运营商")
	private String operator;
	/**注册信息：套餐名称*/
    @Schema(description = "注册信息：套餐名称")
    @NotEmpty(message = "注册信息：套餐名称不能为空")
    @ExcelProperty("注册信息：套餐名称")
	private String planName;
	/**状态更新：active-在用, inactive-停机, scrapped-报废*/
    @Schema(description = "状态更新：active-在用, inactive-停机, scrapped-报废")
    @NotEmpty(message = "状态更新：active-在用, inactive-停机, scrapped-报废不能为空")
    @ExcelProperty("状态更新：active-在用, inactive-停机, scrapped-报废")
	private String status;
	/**临期报警：套餐到期时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @Schema(description = "临期报警：套餐到期时间")
    @NotEmpty(message = "临期报警：套餐到期时间不能为空")
    @ExcelProperty("临期报警：套餐到期时间")
	private java.util.Date expiryDate;
	/**activeTime*/
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @Schema(description = "activeTime")
    @NotEmpty(message = "activeTime不能为空")
    @ExcelProperty("activeTime")
	private java.util.Date activeTime;
	/**删除记录：逻辑删除标记(0-正常, 1-已删除)*/
    @Schema(description = "删除记录：逻辑删除标记(0-正常, 1-已删除)")
    @NotEmpty(message = "删除记录：逻辑删除标记(0-正常, 1-已删除)不能为空")
    @ExcelProperty("删除记录：逻辑删除标记(0-正常, 1-已删除)")
	private Integer isDeleted;
	/**remark*/
    @Schema(description = "remark")
    @NotEmpty(message = "remark不能为空")
    @ExcelProperty("remark")
	private Object remark;
	/**多租户：租户ID*/
    @Schema(description = "多租户：租户ID")
    @NotEmpty(message = "多租户：租户ID不能为空")
    @ExcelProperty("多租户：租户ID")
	private String tenementId;
	/**createTime*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "createTime")
    @NotEmpty(message = "createTime不能为空")
    @ExcelProperty("createTime")
	private java.util.Date createTime;
	/**createBy*/
    @Schema(description = "createBy")
    @NotEmpty(message = "createBy不能为空")
    @ExcelProperty("createBy")
	private String createBy;
	/**审计：修改时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "审计：修改时间")
    @NotEmpty(message = "审计：修改时间不能为空")
    @ExcelProperty("审计：修改时间")
	private java.util.Date updateTime;
	/**updateBy*/
    @Schema(description = "updateBy")
    @NotEmpty(message = "updateBy不能为空")
    @ExcelProperty("updateBy")
	private String updateBy;
}
