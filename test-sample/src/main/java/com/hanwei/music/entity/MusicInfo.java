package com.hanwei.music.entity;

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
 * @Description: 音乐专辑管理器
 * @Author: hanwei
 * @Date:   2026-01-04
 * @Version: V1.0
 */
@Data
@TableName("music_info")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="MusicInfo对象", description="音乐专辑管理器")
public class MusicInfo {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    @NotEmpty(message = "id不能为空")
    @ExcelProperty("id")
	private Integer id;
	/**title*/
    @Schema(description = "title")
    @NotEmpty(message = "title不能为空")
    @ExcelProperty("title")
	private String title;
	/**artist*/
    @Schema(description = "artist")
    @NotEmpty(message = "artist不能为空")
    @ExcelProperty("artist")
	private String artist;
	/**tenementId*/
    @Schema(description = "tenementId")
    @NotEmpty(message = "tenementId不能为空")
    @ExcelProperty("tenementId")
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
	/**updateTime*/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "updateTime")
    @NotEmpty(message = "updateTime不能为空")
    @ExcelProperty("updateTime")
	private java.util.Date updateTime;
	/**updateBy*/
    @Schema(description = "updateBy")
    @NotEmpty(message = "updateBy不能为空")
    @ExcelProperty("updateBy")
	private String updateBy;
}
