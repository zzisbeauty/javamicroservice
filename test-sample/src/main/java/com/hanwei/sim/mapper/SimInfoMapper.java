package com.hanwei.sim.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.sim.entity.SimInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: SIM卡管理器
 * @Author: hanwei
 * @Date:   2026-01-04
 * @Version: V1.0
 */
@Mapper
public interface SimInfoMapper extends MPJBaseMapper<SimInfo> {

}
