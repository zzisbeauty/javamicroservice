package com.hanwei.base.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class BaseMonitorInput {
    @Schema(description = "id")
    private String id;
    @Schema(description = "监测点名称")
    private String name;
    @Schema(description = "监测点编号")
    private String code;
    @Schema(description = "x坐标")
    private java.math.BigDecimal coordinateX;
    @Schema(description = "y坐标")
    private java.math.BigDecimal coordinateY;
    @Schema(description = "监测点地址")
    private String address;
    @Schema(description = "采集器编号")
    private String collectorCode;
    @Schema(description = "状态")
    private String status;
    @Schema(description = "备注")
    private String remark;
    @Schema(description = "场景id")
    private String sceneId;
    @Schema(description = "采集指标")
    private List<String> propertyCodeList;
}
