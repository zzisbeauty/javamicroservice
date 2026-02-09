package com.hanwei.base.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.interfaces.MPJBaseJoin;
import com.hanwei.base.entity.vo.BaseMonitorVo;
import org.apache.ibatis.annotations.Param;
import com.hanwei.base.entity.BaseMonitor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 监测点
 * @Author: hanwei
 * @Date:   2026-01-22
 * @Version: V1.0
 */
@Mapper
public interface BaseMonitorMapper extends MPJBaseMapper<BaseMonitor> {

}
