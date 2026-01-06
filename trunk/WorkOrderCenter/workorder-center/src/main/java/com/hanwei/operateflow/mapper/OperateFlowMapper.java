package com.hanwei.operateflow.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.operateflow.entity.OperateFlow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 操作流程记录信息
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Mapper
public interface OperateFlowMapper extends MPJBaseMapper<OperateFlow> {

}
