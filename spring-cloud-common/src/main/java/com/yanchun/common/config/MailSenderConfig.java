package com.yanchun.common.config;


import com.yanchun.common.constant.MailConstants;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * @Author quyanchun
 * @Date 2018/11/16
 */
public class MailSenderConfig {
    final static String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    public static JavaMailSenderImpl javaMailSender;
    public static Session session;
    static{
        Init();
    }
    public  static void  Init(){

        Properties props = new Properties();
        //邮箱的发送服务器地址
        props.setProperty("mail.smtp.host", MailConstants.host);
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", MailConstants.port+"");
        props.setProperty("mail.smtp.socketFactory.port", MailConstants.port+"");
        props.put("mail.smtp.auth", "true");
        //获取到邮箱会话,利用匿名内部类的方式,将发送者邮箱用户名和密码授权给jvm
        session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailConstants.username, MailConstants.password);
            }
        });

        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
        javaMailSenderImpl.setHost(MailConstants.host);
        javaMailSenderImpl.setUsername(MailConstants.username);
        javaMailSenderImpl.setPassword(MailConstants.password);
        javaMailSenderImpl.setPort(MailConstants.port);
        javaMailSender=javaMailSenderImpl;
    }
}
