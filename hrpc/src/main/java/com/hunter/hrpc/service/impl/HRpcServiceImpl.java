package com.hunter.hrpc.service.impl;

import com.hunter.hrpc.service.HRpcService;

/**
 * @Auther: smith-dog
 * @Date: 2018/7/5 20:08
 * @Description:
 */
@com.hunter.hrpc.annotations.HRpcService(HRpcService.class) // 指定远程接口
public class HRpcServiceImpl implements HRpcService {

    @Override
    public String hello(String name) {
        return "Hello! " + name;
    }
}
