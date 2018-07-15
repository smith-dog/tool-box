package com.hunter.hrpc.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: smith-dog
 * @Date: 2018/7/5 20:32
 * @Description:
 */
@Component
@Data
@ConfigurationProperties(prefix = "HRpc")
public class HRpcProperties {
    private int zkSessionTimeout;

    private String zkDataPath;
}
