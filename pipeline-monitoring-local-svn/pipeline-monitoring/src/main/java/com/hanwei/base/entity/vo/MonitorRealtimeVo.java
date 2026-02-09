package com.hanwei.base.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class MonitorRealtimeVo {
    /** 监测点编号 */
    private String monitorCode;

    /** 监测点名称 */
    private String monitorName;

    /** 实时数据集合 */
    private List<DataRealtimeVo> realtimeList;
    /** 实时数据ID集合 */
    @JsonIgnore
    private Set<String> realtimeIdSet;
}
