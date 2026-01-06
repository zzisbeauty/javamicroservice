package com.hanwei.workorderpost.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.workorderpost.entity.FeedBack;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 工单后处理记录-回访信息
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Mapper
public interface FeedBackMapper extends MPJBaseMapper<FeedBack> {

}
