package com.hanwei.base.entity.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanwei.base.entity.BaseMonitorRelate;
import com.hanwei.core.annotation.ApiParameter;
import com.hanwei.core.common.ApiEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Data
public class BaseMonitorVo {
    /**主键ID*/
    private String id;
    /**编号*/
    private String code;
    /**监测点名称*/
    private String name;
    /**类型（1流量、2压力、3水质、4流量压力、5流量水质、6压力水质）*/
    private Integer type;
    /**x坐标*/
    private java.math.BigDecimal coordinateX;
    /**y坐标*/
    private java.math.BigDecimal coordinateY;
    /**图片（点位示意图或现场照片URL）*/
    private String imageUrl;
    /**详细地址*/
    private String address;
    /**区域编码（如行政区划代码）*/
    private String areaCode;
    /**创建时间（精确到秒）*/
    private java.util.Date createTime;
    /**创建人ID*/
    private String createBy;
    /**更新时间（精确到秒）*/
    private java.util.Date updateTime;
    /**更新人ID*/
    private String updateBy;
    /**租户ID*/
    private String tenementId;
    /**状态（0启用、1禁用）*/
    private Integer status;
    /**备注*/
    private String remark;
    /**运行状态（0在线、1离线、2报警）*/
    private Integer runStatus;
    /**采集器编号*/
    private String collectorCode;
    /**级别*/
    private Integer level;
    /**场景id*/
    private String sceneId;
    /**属性编码*/
    private String propertyCode;

    /**关联信息*/
    private List<BaseMonitorRelate> relates;




}
