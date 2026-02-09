package com.hanwei.system.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanwei.core.annotation.Dict;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotEmpty;

/**
 * @Description: 字典明细表表
 * @Author: hanwei
 * @Date:   2025-05-13
 * @Version: V1.0
 */
@Data
@TableName("sys_dict_item")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="DictItem对象", description="字典明细表表")
public class DictItem {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
	private java.lang.String id;
	/**dictId*/
    @Schema(description = "dictId")
    @NotEmpty(message = "dictId不能为空")
    @ExcelProperty("dictId")
    @Dict(dictTable ="sys_dict",dicCode = "id",dicText = "dictName")
	private java.lang.String dictId;
	/**itemText*/
    @Schema(description = "itemText")
    @NotEmpty(message = "itemText不能为空")
    @ExcelProperty("itemText")
	private java.lang.String itemText;
	/**itemValue*/
    @Schema(description = "itemValue")
    @NotEmpty(message = "itemValue不能为空")
    @ExcelProperty("itemValue")
	private java.lang.String itemValue;
	/**description*/
    @Schema(description = "description")
    @ExcelProperty("description")
	private java.lang.String description;
	/**sortOrder*/
    @Schema(description = "sortOrder")
    @NotEmpty(message = "sortOrder不能为空")
    @ExcelProperty("sortOrder")
	private java.lang.Integer sortOrder;
	/**status*/
    @Schema(description = "status")
    @NotEmpty(message = "status不能为空")
    @ExcelProperty("status")
    @Dict(dicCode = "until_status")
	private Integer status;
	/**createBy*/
    @Schema(description = "createBy")
    @ExcelProperty("createBy")
	private java.lang.String createBy;
	/**createTime*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "createTime")
    @ExcelProperty("createTime")
	private java.util.Date createTime;
	/**updateBy*/
    @Schema(description = "updateBy")
    @ExcelProperty("updateBy")
	private java.lang.String updateBy;
	/**updateTime*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "updateTime")
    @ExcelProperty("updateTime")
	private java.util.Date updateTime;
}
