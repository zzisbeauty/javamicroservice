package com.hanwei.attachment.mapper;

import org.apache.ibatis.annotations.Param;
import com.hanwei.attachment.entity.Attachment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.github.yulichang.base.MPJBaseMapper;

/**
 * @Description: 附件信息记录
 * @Author: hanwei
 * @Date:   2026-01-06
 * @Version: V1.0
 */
@Mapper
public interface AttachmentMapper extends MPJBaseMapper<Attachment> {

}
