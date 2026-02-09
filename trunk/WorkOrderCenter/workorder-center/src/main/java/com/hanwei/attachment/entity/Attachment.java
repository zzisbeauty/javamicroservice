package com.hanwei.attachment.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 附件信息记录
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Data
@TableName("attachment_info")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="Attachment对象", description="附件信息记录")
public class Attachment {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    @NotEmpty(message = "主键ID不能为空")
    @ExcelProperty("主键ID")
	private String id;
	/**流程ID*/
    @Schema(description = "流程ID")
    @NotEmpty(message = "流程ID不能为空")
    @ExcelProperty("流程ID")
	private String processId;
	/**文件名称*/
    @Schema(description = "文件名称")
    @ExcelProperty("文件名称")
	private String fileName;
	/**文件类型（如：IMAGE, PDF, DOC, VIDEO 等）*/
    @Schema(description = "文件类型（如：IMAGE, PDF, DOC, VIDEO 等）")
    @ExcelProperty("文件类型（如：IMAGE, PDF, DOC, VIDEO 等）")
	private String fileType;
	/**文件URL*/
    @Schema(description = "文件URL")
    @NotEmpty(message = "文件URL不能为空")
    @ExcelProperty("文件URL")
	private String fileUrl;
	/**创建时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
	private java.util.Date createTime;
	/**创建人ID*/
    @Schema(description = "创建人ID")
    @ExcelProperty("创建人ID")
	private String createBy;
	/**更新时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
	private java.util.Date updateTime;
	/**更新人ID*/
    @Schema(description = "更新人ID")
    @ExcelProperty("更新人ID")
	private String updateBy;
	/**租户ID*/
    @Schema(description = "租户ID")
    @ExcelProperty("租户ID")
	private String tenementId;
}
