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
 * @Description: 视频监控
 * @Author: hanwei
 * @Date:   2026-01-20
 * @Version: V1.0
 */
@Data
@TableName("video_info")
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ExcelIgnoreUnannotated
@ColumnWidth(25)
@Schema(title="VideoInfo对象", description="视频监控")
public class VideoInfo {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键ID")
    @NotEmpty(message = "主键ID不能为空")
    @ExcelProperty("主键ID")
    @ApiParameter(name = "id", description = "主键ID", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String id;
	/**监测点ID*/
    @Schema(description = "监测点ID")
    @NotEmpty(message = "监测点ID不能为空")
    @ExcelProperty("监测点ID")
    @ApiParameter(name = "monitorPointId", description = "监测点ID", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String monitorPointId;
	/**相机编号*/
    @Schema(description = "相机编号")
    @NotEmpty(message = "相机编号不能为空")
    @ExcelProperty("相机编号")
    @ApiParameter(name = "cameraCode", description = "相机编号", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String cameraCode;
	/**相机类型（如：枪机、球机、全景等）*/
    @Schema(description = "相机类型（如：枪机、球机、全景等）")
    @NotEmpty(message = "相机类型（如：枪机、球机、全景等）不能为空")
    @ExcelProperty("相机类型（如：枪机、球机、全景等）")
    @ApiParameter(name = "cameraType", description = "相机类型（如：枪机、球机、全景等）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String cameraType;
	/**视频流类型（如：RTMP、HLS、FLV等）*/
    @Schema(description = "视频流类型（如：RTMP、HLS、FLV等）")
    @NotEmpty(message = "视频流类型（如：RTMP、HLS、FLV等）不能为空")
    @ExcelProperty("视频流类型（如：RTMP、HLS、FLV等）")
    @ApiParameter(name = "videoStreamType", description = "视频流类型（如：RTMP、HLS、FLV等）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String videoStreamType;
	/**推流地址（完整URL）*/
    @Schema(description = "推流地址（完整URL）")
    @NotEmpty(message = "推流地址（完整URL）不能为空")
    @ExcelProperty("推流地址（完整URL）")
    @ApiParameter(name = "pushStreamUrl", description = "推流地址（完整URL）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String pushStreamUrl;
	/**经度（WGS84坐标系）*/
    @Schema(description = "经度（WGS84坐标系）")
    @NotEmpty(message = "经度（WGS84坐标系）不能为空")
    @ExcelProperty("经度（WGS84坐标系）")
    @ApiParameter(name = "longitude", description = "经度（WGS84坐标系）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.math.BigDecimal longitude;
	/**纬度（WGS84坐标系）*/
    @Schema(description = "纬度（WGS84坐标系）")
    @NotEmpty(message = "纬度（WGS84坐标系）不能为空")
    @ExcelProperty("纬度（WGS84坐标系）")
    @ApiParameter(name = "latitude", description = "纬度（WGS84坐标系）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.math.BigDecimal latitude;
	/**安装位置（如：A栋3楼走廊）*/
    @Schema(description = "安装位置（如：A栋3楼走廊）")
    @NotEmpty(message = "安装位置（如：A栋3楼走廊）不能为空")
    @ExcelProperty("安装位置（如：A栋3楼走廊）")
    @ApiParameter(name = "installLocation", description = "安装位置（如：A栋3楼走廊）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private String installLocation;
	/**海拔高度（单位：米，精确到厘米）*/
    @Schema(description = "海拔高度（单位：米，精确到厘米）")
    @NotEmpty(message = "海拔高度（单位：米，精确到厘米）不能为空")
    @ExcelProperty("海拔高度（单位：米，精确到厘米）")
    @ApiParameter(name = "altitudeMeters", description = "海拔高度（单位：米，精确到厘米）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private java.math.BigDecimal altitudeMeters;
	/**像素（如：1920x1080 可存为总像素数 2073600，或前端解析）*/
    @Schema(description = "像素（如：1920x1080 可存为总像素数 2073600，或前端解析）")
    @NotEmpty(message = "像素（如：1920x1080 可存为总像素数 2073600，或前端解析）不能为空")
    @ExcelProperty("像素（如：1920x1080 可存为总像素数 2073600，或前端解析）")
    @ApiParameter(name = "resolutionPixels", description = "像素（如：1920x1080 可存为总像素数 2073600，或前端解析）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
	private Integer resolutionPixels;
	/**状态（如：在线／离线／维护中）*/
    @Schema(description = "状态（如：在线／离线／维护中）")
    @NotEmpty(message = "状态（如：在线／离线／维护中）不能为空")
    @ExcelProperty("状态（如：在线／离线／维护中）")
    @ApiParameter(name = "status", description = "状态（如：在线／离线／维护中）", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
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

    /**租户ID*/
    @Schema(description = "视频名称")
    @NotEmpty(message = "租户ID不能为空")
    @ExcelProperty("租户ID")
    @ApiParameter(name = "tenementId", description = "租户ID", demovalue = "1", location = ApiEnum.PARAMETER_LOCATION_BODY)
    private String videoName;
}
