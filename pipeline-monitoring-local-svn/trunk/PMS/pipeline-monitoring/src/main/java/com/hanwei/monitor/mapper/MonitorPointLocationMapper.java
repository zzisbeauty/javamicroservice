package com.hanwei.monitor.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.monitor.entity.MonitorPointLocation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 监测点位信息表
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Mapper
public interface MonitorPointLocationMapper extends MPJBaseMapper<MonitorPointLocation> {

}
