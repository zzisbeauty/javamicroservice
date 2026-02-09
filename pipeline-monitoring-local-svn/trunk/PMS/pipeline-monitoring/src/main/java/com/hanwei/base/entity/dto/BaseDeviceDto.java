package com.hanwei.base.entity.dto;

import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.common.ApiEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


    /**
     * @Description: 设备信息传参
     * @Author: slx
     * @Date:   2026-01-22
     * @Version: V1.0
     */
    @Data
    @Schema(title="设备信息", description="设备信息Dto")
    public class BaseDeviceDto {


        @Schema(description = "设备类型")
        private int  deviceType;
        @Schema(description = "运行状态")
        private int  runStatus;
        @Schema(description = "设备序列号")
        private String serialNumber;

    }

