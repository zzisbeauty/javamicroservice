package com.hanwei.video.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.video.entity.VideoFollowInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 视频监控关注信息
 * @Author: hanwei
 * @Date:   2026-01-20
 * @Version: V1.0
 */
@Mapper
public interface VideoFollowInfoMapper extends MPJBaseMapper<VideoFollowInfo> {

}
