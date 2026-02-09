package com.hanwei.data.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class MonitorRealtimeVo {
    /** 监测点编号 */
    private String monitorCode;

    /** 监测点名称 */
    private String monitorName;

    /** 实时数据集合 */
    private List<DataRealtimeVo> realtimeList;
}
