package com.hanwei.dubbo.facade.user;


import com.hanwei.dubbo.facade.vo.common.dto.ResultDTO;
import org.xnio.Result;

public interface ISysUserServiceDubbo {
    Result<?> getUserInfo(String ticket);
}

