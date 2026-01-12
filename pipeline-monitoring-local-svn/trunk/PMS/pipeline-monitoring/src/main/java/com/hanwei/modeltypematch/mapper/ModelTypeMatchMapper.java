package com.hanwei.modeltypematch.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.modeltypematch.entity.ModelTypeMatch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 型号匹配记录
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Mapper
public interface ModelTypeMatchMapper extends MPJBaseMapper<ModelTypeMatch> {

}
