package com.hanwei.problem.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.problem.entity.ProblemWrokOrderRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 问题工单关联信息
 * @Author: hanwei
 * @Date:   2026-01-05
 * @Version: V1.0
 */
@Mapper
public interface ProblemWrokOrderRelationMapper extends MPJBaseMapper<ProblemWrokOrderRelation> {

}
