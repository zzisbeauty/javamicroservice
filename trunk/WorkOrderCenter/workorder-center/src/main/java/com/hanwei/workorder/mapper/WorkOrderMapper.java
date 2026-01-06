package com.hanwei.workorder.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.workorder.entity.WorkOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 工单信息表
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Mapper
public interface WorkOrderMapper extends MPJBaseMapper<WorkOrder> {

}
