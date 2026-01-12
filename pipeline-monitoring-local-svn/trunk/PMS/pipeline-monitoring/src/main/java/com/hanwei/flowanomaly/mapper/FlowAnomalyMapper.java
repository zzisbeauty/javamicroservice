package com.hanwei.flowanomaly.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.flowanomaly.entity.FlowAnomaly;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 流量异常分析记录
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Mapper
public interface FlowAnomalyMapper extends MPJBaseMapper<FlowAnomaly> {

}
