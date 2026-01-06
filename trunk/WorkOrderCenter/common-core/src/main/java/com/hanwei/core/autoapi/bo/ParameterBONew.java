package com.hanwei.core.autoapi.bo;

import lombok.Data;

/**
 * @author zht
 * @version : [v1.0]
 * @description : [参数BO 新网关用]
 * @createTime : [2025/1/23 15:14]
 */
@Data
public class ParameterBONew {
    /**
     * 名称
     */
    private String key;

    /**
     * 描述
     */
    private String describe;

    /**
     * 是否必填
     */
    private Integer isRequired;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 备注
     */
    private String remark;
}
