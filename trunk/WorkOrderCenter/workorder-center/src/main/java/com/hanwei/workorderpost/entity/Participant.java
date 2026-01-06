package com.hanwei.workorderpost.entity;

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
 * @Description: 工单后处理记录-参与人信息
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Data
@TableName("participant_info")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="Participant对象", description="工单后处理记录-参与人信息")
public class Participant {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    @NotEmpty(message = "主键ID不能为空")
    @ExcelProperty("主键ID")
	private String id;
	/**工单ID*/
    @Schema(description = "工单ID")
    @NotEmpty(message = "工单ID不能为空")
    @ExcelProperty("工单ID")
	private String workOrderId;
	/**用户编号*/
    @Schema(description = "用户编号")
    @NotEmpty(message = "用户编号不能为空")
    @ExcelProperty("用户编号")
	private String userCode;
	/**用户姓名*/
    @Schema(description = "用户姓名")
    @NotEmpty(message = "用户姓名不能为空")
    @ExcelProperty("用户姓名")
	private String userName;
	/**角色（处理人／抄送人／查看）*/
    @Schema(description = "角色（处理人／抄送人／查看）")
    @NotEmpty(message = "角色（处理人／抄送人／查看）不能为空")
    @ExcelProperty("角色（处理人／抄送人／查看）")
	private String role;
	/**是否active（用于处理操作权限：true-有权限，false-无权限）*/
    @Schema(description = "是否active（用于处理操作权限：true-有权限，false-无权限）")
    @NotEmpty(message = "是否active（用于处理操作权限：true-有权限，false-无权限）不能为空")
    @ExcelProperty("是否active（用于处理操作权限：true-有权限，false-无权限）")
	private Object isActive;
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
