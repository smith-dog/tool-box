package com.hunter.auto.mail.client.properties;

import com.hunter.auto.mail.client.entity.Subject;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @version 1.0.0
 * @author: hunter
 * @date: 2018/6/18 00:07
 * @description: 配置类
 */
@Component
@Data
@ConfigurationProperties(prefix = "mail")
public class MailProperties {

    private String from;

    private List<String> to;

    private List<String> cc;

    private String path;

    private String tail;

    private Subject subject;

    private String content;
}


