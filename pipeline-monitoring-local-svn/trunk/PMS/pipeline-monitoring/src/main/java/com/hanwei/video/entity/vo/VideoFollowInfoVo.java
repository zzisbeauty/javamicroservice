package com.hanwei.video.entity.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.common.ApiEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 视频监控关注信息
 * @Author: hanwei
 * @Date:   2026-01-20
 * @Version: V1.0
 */
@Data
public class VideoFollowInfoVo {

	/**主键ID*/
    @Schema(description = "主键ID")
    @ApiParameter(name = "id", description = "主键ID")
	private String id;
	/**用户ID（系统用户主键）*/
    @Schema(description = "用户ID（系统用户主键）")
    @ApiParameter(name = "userId", description = "用户ID（系统用户主键）")
	private String userId;
	/**用户编号（业务编号，如工号）*/
    @Schema(description = "用户编号（业务编号，如工号）")
    @ApiParameter(name = "userCode", description = "用户编号（业务编号，如工号）")
	private String userCode;
	/**视频ID*/
    @Schema(description = "视频ID")
    @NotEmpty(message = "视频ID不能为空")
    @ExcelProperty("视频ID")
    @ApiParameter(name = "videoId", description = "视频ID")
	private String videoId;


    /**相机编号*/
    @Schema(description = "相机编号")

    @ApiParameter(name = "cameraCode", description = "相机编号")
    private String cameraCode;
    /**相机类型（如：枪机、球机、全景等）*/
    @Schema(description = "相机类型（如：枪机、球机、全景等）")
    @ApiParameter(name = "cameraType", description = "相机类型（如：枪机、球机、全景等）")
    private String cameraType;
    /**视频流类型（如：RTMP、HLS、FLV等）*/
    @Schema(description = "视频流类型（如：RTMP、HLS、FLV等）")
    @ApiParameter(name = "videoStreamType", description = "视频流类型（如：RTMP、HLS、FLV等）")
    private String videoStreamType;
    /**推流地址（完整URL）*/
    @Schema(description = "推流地址（完整URL）")
    @ApiParameter(name = "pushStreamUrl", description = "推流地址（完整URL）")
    private String pushStreamUrl;
    /**状态（如：在线／离线／维护中）*/
    @Schema(description = "状态（如：在线／离线／维护中）")
    @ApiParameter(name = "status", description = "状态（如：在线／离线／维护中）")
    private String status;


}
