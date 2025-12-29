package com.hanwei.dubbo.facade.user;


import com.hanwei.dubbo.facade.vo.common.dto.ResultDTO;

public interface ISysUserServiceDubbo {
    ResultDTO<?> getUserInfo(String ticket);
}

