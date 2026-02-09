package com.hanwei.base.entity.vo;

import lombok.Data;

@Data
public class DataRealtimeVo {
    /**
     * 主键
     */
    private String id;

    /**
     * 采集时间
     */
    private java.util.Date collectTime;
    /**
     * 上传时间
     */
    private java.util.Date uploadTime;
    /**
     * 监测值
     */
    private java.math.BigDecimal value;
    /**
     * 单位
     */
    private String unit;
    /**
     * 备注
     */
    private String remark;

 }
