package com.hanwei.scene.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.scene.entity.SceneBase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 场景基础信息
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Mapper
public interface SceneBaseMapper extends MPJBaseMapper<SceneBase> {

}
