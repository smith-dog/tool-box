package com.hunter.auto.mail.client.entity;

import lombok.Data;

import java.util.List;

/**
 * @Auther: smith-dog
 * @Date: 2018/6/16 17:18
 * @Description:
 */
@Data
public class Mail {
    private String from;
    private List<String> to;
    private List<String> cc;
    private String subject;
    private String content;
    private String[] fileList;

    public Mail(List<String> to, String subject, String content) {
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public Mail(List<String> to, String subject, String content, String[] fileList) {
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.fileList = fileList;
    }

    public Mail(List<String> to, List<String> cc, String subject, String content, String[] fileList) {
        this.to = to;
        this.cc = cc;
        this.subject = subject;
        this.content = content;
        this.fileList = fileList;
    }

    public Mail(String from, List<String> to, List<String> cc, String subject, String content, String[] fileList) {
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.subject = subject;
        this.content = content;
        this.fileList = fileList;
    }
}
