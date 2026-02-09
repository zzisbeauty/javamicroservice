package com.hanwei.video.entity.dto;

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
 * @Description: 视频监控关注信息查询传参
 * @Author: slx
 * @Date:   2026-01-20
 * @Version: V1.0
 */
@Data
@Schema(title="视频监控关注信息", description="视频监控关注信息Dto")
public class VideoFollowInfoDto {


    @Schema(description = "视频名称")
    @ApiParameter(name = "videoname", description = "视频名称", demovalue = "大门口", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String videoname;

}
