package com.hunter.auto.mail.client.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @Auther: smith-dog
 * @Date: 2018/6/16 16:13
 * @Description:
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MailServiceImplTest {

    @Autowired
    MailServiceImpl mailService;

    @Test
    public void sendMail() {
        mailService.sendMail();
    }
}