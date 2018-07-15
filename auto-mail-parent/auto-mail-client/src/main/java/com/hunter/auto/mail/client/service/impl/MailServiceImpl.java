package com.hunter.auto.mail.client.service.impl;

import com.hunter.auto.mail.client.entity.Subject;
import com.hunter.auto.mail.client.properties.MailProperties;
import com.hunter.auto.mail.client.service.MailService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.*;

/**
 * @Auther: smith-dog
 * @Date: 2018/6/16 15:50
 * @Description:
 */
@Service
public class MailServiceImpl implements MailService {

    //注入MailSender
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailProperties mailProperties;

    //发送邮件的模板引擎
    @Autowired
    private FreeMarkerConfigurer configurer;

    private static Random random = new Random();

    @Override
    @Scheduled(cron="0 0 15 * * 5")
    public void sendMail() {
            MimeMessage message = null;
            System.getProperties().setProperty("mail.mime.splitlongparameters", "false");
            try {
                message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                helper.setFrom(mailProperties.getFrom());
                helper.setTo(mailProperties.getTo().stream().toArray(String[]::new));
                helper.setCc(mailProperties.getCc().stream().toArray(String[]::new));
                helper.setSubject("云平台部_纪炜煜_" + generateSubject());

                Map<String, Object> model = new HashMap<>();
                //model.put("params", "hahahaha");
                model.put("subject", generateSubject());
                model.put("date", LocalDateTime.now().toString());
                model.put("keyword", "my report never late");
                model.put("weather", "137826142@qq.com");
                model.put("content", generateContent());
                model.put("email", "137826142");
                try {
                    Template template = configurer.getConfiguration().getTemplate("message.ftl");
                    String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
                    helper.setText(text, true);


                    ClassPathResource header = new ClassPathResource("images/header.jpg");
                    ClassPathResource pic1 = new ClassPathResource("images/pic/pic1.jpg");
                    ClassPathResource pic4 = new ClassPathResource("images/pic/pic4.gif");
                    ClassPathResource pic5 = new ClassPathResource("images/pic/pic5.gif");
                    List<ClassPathResource> picList = new ArrayList<>();
                    picList.add(pic1);
                    picList.add(pic4);
                    picList.add(pic5);

                    ClassPathResource weixin = new ClassPathResource("images/weixin.png");
                    ClassPathResource qq = new ClassPathResource("images/qq.png");
                    helper.addInline("header.jpg", header);
                    helper.addInline("pic1.jpg", picList.get(random.nextInt(picList.size())));
                    helper.addInline("weixin.png", qq);
                    helper.addInline("qq.png", weixin);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TemplateException e) {
                    e.printStackTrace();
                }

                //注意项目路径问题，自动补用项目路径
                //String s = "E:\\杂项";
                File attachment = new File(mailProperties.getPath());
                //fileCopy(attachment,attachmentCopy);

                FileSystemResource file = new FileSystemResource(new File(mailProperties.getPath().trim()));

                //加入邮件
                helper.addAttachment("云平台部_纪炜煜_" + generateSubject() +".xlsx", file);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        mailSender.send(message);

    }

    public void receiver() {

        String protocol = "pop3";//使用pop3协议
        boolean isSSL = true;//使用SSL加密
        String host = "pop.qq.com";//QQ邮箱的pop3服务器
        int port = 995;//端口
        String username = "******@qq.com";//用户名
        String password = "******";//密码

        /*
         *Properties是一个属性对象，用来创建Session对象
         */
        Properties props = new Properties();
        props.put("mail.pop3.ssl.enable", isSSL);
        props.put("mail.pop3.host", host);
        props.put("mail.pop3.port", port);
        /*
         *Session类定义了一个基本的邮件对话。
         */
        Session session = Session.getDefaultInstance(props);

        /*
         * Store类实现特定邮件协议上的读、写、监视、查找等操作。
         * 通过Store类可以访问Folder类。
         * Folder类用于分级组织邮件，并提供照Message格式访问email的能力。
         */
        Store store = null;
        Folder folder = null;
        try {
            store = session.getStore(protocol);
            store.connect(username, password);

            folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);//在这一步，收件箱所有邮件将被下载到本地

            int size = folder.getMessageCount();//获取邮件数目
            Message message = folder.getMessage(size);//取得最新的那个邮件

            //解析邮件内容
            String from = message.getFrom()[0].toString();
            String subject = message.getSubject();
            Date date = message.getSentDate();

            System.out.println("From: " + from);
            System.out.println("Subject: " + subject);
            System.out.println("Date: " + date);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (folder != null) {
                    folder.close(false);
                }
                if (store != null) {
                    store.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("接收完毕！");
    }

    /**
     * @description: 获取主题
     * @param: []
     * @return: void
     * @author: hunter
     * @date: 2018/6/18 11:34
     */
    public String generateSubject() {
        Subject subject = mailProperties.getSubject();
        LocalDate localDate = LocalDate.now();

        if (StringUtils.isEmpty(subject.getSubjectYear())) {
            subject.setSubjectYear(String.valueOf(localDate.getYear()));
        }

        if (StringUtils.isEmpty(subject.getSubjectMonth())) {
            subject.setSubjectMonth(String.valueOf(localDate.getMonth().getValue()));
        }

        if (StringUtils.isEmpty(subject.getSubjectWeekOfMonth())) {
            subject.setSubjectWeekOfMonth(String.valueOf(localDate.get(ChronoField.ALIGNED_WEEK_OF_MONTH)));
        }

        return subject.toString();
    }


    /**
     * @description: 生成内容
     * @param: []
     * @return: java.lang.String
     * @author: hunter
     * @date: 2018/6/18 13:41
     */
    public String generateContent() {
        LocalDate localDate = LocalDate.now();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dear all:" + "\n");
        stringBuilder.append("    ");
        stringBuilder.append(mailProperties.getContent() + "\n");
        stringBuilder.append(localDate);
        return stringBuilder.toString();
    }


    public void fileCopy(File fileFrom, File fileTo) {

        try{
            FileInputStream fileInputStream = new FileInputStream(fileFrom);
            FileOutputStream fileOutputStream = new FileOutputStream(fileTo);
            byte[] buffer = new byte[1024];
            while (fileInputStream.read(buffer) > 0) {
                fileOutputStream.write(buffer);
            }
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

