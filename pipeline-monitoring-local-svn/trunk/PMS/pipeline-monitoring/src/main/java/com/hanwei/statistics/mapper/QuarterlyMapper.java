package com.hanwei.statistics.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.statistics.entity.Quarterly;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 季度汇聚信息表
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Mapper
public interface QuarterlyMapper extends MPJBaseMapper<Quarterly> {

}
