package com.hunter.auto.mail.client.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Auther: smith-dog
 * @Date: 2018/6/16 17:18
 * @Description:
 */
@Data
@Builder
public class Mail {
    private String from;
    private List<String> to;
    private List<String> cc;
    private String subject;
    private String content;
    private String[] fileList;
}
