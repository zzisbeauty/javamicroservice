package com.hanwei.base.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.base.entity.BaseMonitorRelate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 监测点关联信息
 * @Author: hanwei
 * @Date:   2026-01-23
 * @Version: V1.0
 */
@Mapper
public interface BaseMonitorRelateMapper extends MPJBaseMapper<BaseMonitorRelate> {

}
