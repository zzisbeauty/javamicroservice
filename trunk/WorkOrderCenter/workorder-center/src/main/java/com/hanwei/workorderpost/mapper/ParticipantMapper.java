package com.hanwei.workorderpost.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.workorderpost.entity.Participant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 工单后处理记录-参与人信息
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Mapper
public interface ParticipantMapper extends MPJBaseMapper<Participant> {

}
