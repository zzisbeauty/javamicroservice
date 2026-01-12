package com.hanwei.relation.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.relation.entity.Relation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 关联信息
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Mapper
public interface RelationMapper extends MPJBaseMapper<Relation> {

}
