package com.hanwei.statistics.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.statistics.entity.Monthly;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 月度汇聚信息表
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Mapper
public interface MonthlyMapper extends MPJBaseMapper<Monthly> {

}
