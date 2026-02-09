package com.hanwei.base.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.base.entity.BaseDevice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 设备信息
 * @Author: hanwei
 * @Date:   2026-01-22
 * @Version: V1.0
 */
@Mapper
public interface BaseDeviceMapper extends MPJBaseMapper<BaseDevice> {

}
