package com.hanwei.monitor.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.monitor.entity.MonitorPointFollow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 监测点关注信息
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Mapper
public interface MonitorPointFollowMapper extends MPJBaseMapper<MonitorPointFollow> {

}
