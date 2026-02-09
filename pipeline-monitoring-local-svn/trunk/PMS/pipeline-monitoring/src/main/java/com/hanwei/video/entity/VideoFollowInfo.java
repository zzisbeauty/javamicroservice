package com.hanwei.video.entity;

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
 * @Description: 视频监控关注信息
 * @Author: hanwei
 * @Date:   2026-01-20
 * @Version: V1.0
 */
@Data
@TableName("video_follow_info")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="VideoFollowInfo对象", description="视频监控关注信息")
public class VideoFollowInfo {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    @NotEmpty(message = "主键ID不能为空")
    @ExcelProperty("主键ID")
    @ApiParameter(name = "id", description = "主键ID", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String id;
	/**用户ID（系统用户主键）*/
    @Schema(description = "用户ID（系统用户主键）")
    @NotEmpty(message = "用户ID（系统用户主键）不能为空")
    @ExcelProperty("用户ID（系统用户主键）")
    @ApiParameter(name = "userId", description = "用户ID（系统用户主键）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String userId;
	/**用户编号（业务编号，如工号）*/
    @Schema(description = "用户编号（业务编号，如工号）")
    @NotEmpty(message = "用户编号（业务编号，如工号）不能为空")
    @ExcelProperty("用户编号（业务编号，如工号）")
    @ApiParameter(name = "userCode", description = "用户编号（业务编号，如工号）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String userCode;
	/**视频ID*/
    @Schema(description = "视频ID")
    @NotEmpty(message = "视频ID不能为空")
    @ExcelProperty("视频ID")
    @ApiParameter(name = "videoId", description = "视频ID", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String videoId;
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
