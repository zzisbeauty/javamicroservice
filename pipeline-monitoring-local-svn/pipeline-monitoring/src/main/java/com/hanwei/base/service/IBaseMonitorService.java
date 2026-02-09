package com.hanwei.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hanwei.base.entity.BaseMonitor;
import com.hanwei.base.entity.dto.BaseMonitorDto;
import com.hanwei.base.entity.dto.BaseMonitorInput;
import com.hanwei.core.common.api.vo.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hanwei.data.entity.vo.MonitorRealtimeVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * @Description: 监测点
 * @Author: hanwei
 * @Date:   2026-01-22
 * @Version: V1.0
 */
public interface IBaseMonitorService extends IService<BaseMonitor> {

    /**
     * 管网监测系统-监测点-获取监测点及其关系
     * @param dto
     * @param pageNo
     * @param pageSize
     * @return
     */

    IPage<?> queryMonitorRelate(BaseMonitorDto dto, Integer pageNo, Integer pageSize);

/**
 * 管网监测系统-监测点-添加监测点及其关系
 * @param input
 */
    void addMonitorRelate(BaseMonitorInput input);

    /**
     * 管网监测系统-监测点-删除监测点主从关系
     *
     * @param id
     * @return
     */
    Result<?> deleteMonitorRelate(String id);
    /**
     * 管网监测系统-监测点-编辑监测点及其关系
     *
     * @param input
     * @return
     */
    Result<?> editMonitorRelate(BaseMonitorInput input);
    /**
     * 管网监测系统-监测点-批量删除监测点主从关系
     *
     * @param ids
     * @return
     */
    Result<?> deleteMonitorRelates(List<String> ids);

    /**
     * 管网监测系统-监测点-监测点导入
     * @param file
     * @return
     */

    Result<?> importData(MultipartFile file);


    /**
     * 数据-实时数据查询-列表查询
     *
     * @param monitorId
     * @param authorityType 责权类型
     * @return
     */
    List <MonitorRealtimeVo>  queryMonitorRealtimeListVo(String monitorId,Integer authorityType,Integer type);
}
