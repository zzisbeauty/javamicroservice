package com.hanwei.base.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BaseMonitorDto {
    @Schema(description = "监测点id")
    private String id;
    @Schema(description = "监测点名称")
    private String name;
    @Schema(description = "状态")
    private String status;
}
