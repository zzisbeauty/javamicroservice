package com.hanwei.problem.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.problem.entity.ProblemInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 问题信息管理表
 * @Author: hanwei
 * @Date:   2026-01-05
 * @Version: V1.0
 */
@Mapper
public interface ProblemInfoMapper extends MPJBaseMapper<ProblemInfo> {

}
