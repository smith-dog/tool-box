package com.hunter.hrpc.server;

import com.hunter.hrpc.registry.ServiceRegistry;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Auther: smith-dog
 * @Date: 2018/7/5 20:27
 * @Description:
 */
public class HRpcServer implements ApplicationContextAware {

    private String serverAddress;

    private ServiceRegistry serviceRegistry;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
