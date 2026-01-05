package com.hanwei.music.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.music.entity.MusicInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 音乐专辑管理器
 * @Author: hanwei
 * @Date:   2026-01-04
 * @Version: V1.0
 */
@Mapper
public interface MusicInfoMapper extends MPJBaseMapper<MusicInfo> {

}
