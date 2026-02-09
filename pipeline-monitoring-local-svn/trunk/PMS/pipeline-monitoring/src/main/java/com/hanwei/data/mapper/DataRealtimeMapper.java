package com.hanwei.data.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.data.entity.DataRealtime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 实时数据
 * @Author: hanwei
 * @Date:   2026-01-22
 * @Version: V1.0
 */
@Mapper
public interface DataRealtimeMapper extends MPJBaseMapper<DataRealtime> {

}
