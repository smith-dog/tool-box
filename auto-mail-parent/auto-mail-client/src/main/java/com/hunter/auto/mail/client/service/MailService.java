package com.hunter.auto.mail.client.service;


import org.springframework.mail.SimpleMailMessage;

import java.util.List;

/**
 * @Auther: smith-dog
 * @Date: 2018/6/16 15:50
 * @Description: 邮件服务类
 */
public interface MailService {
    /**
     *
     * @descrition 发送带附件的邮件
     * @author smith-dog
     * @date 2018/6/16 16:54
     * @param
     * @return
     */
    void sendMail();
}
