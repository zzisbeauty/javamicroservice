package com.hanwei.statistics.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.statistics.entity.RealTimeData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 实时数据信息表
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Mapper
public interface RealTimeDataMapper extends MPJBaseMapper<RealTimeData> {

}
