package com.hanwei.videomanager.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.videomanager.entity.VideoFollow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 视频关注信息
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Mapper
public interface VideoFollowMapper extends MPJBaseMapper<VideoFollow> {

}
