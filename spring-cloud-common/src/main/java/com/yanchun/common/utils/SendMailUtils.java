package com.yanchun.common.utils;



import com.yanchun.common.config.MailSenderConfig;
import com.yanchun.common.constant.MailConstants;
import com.yanchun.common.model.Mail;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * 发送邮件工具类
 * @Author quyanchun
 * @Date 2018/11/16
 */
public class SendMailUtils {

    /**
     * ssl加密发送邮件
     * @param mail
     * @throws Exception
     */
    public static void sendMailBySSL(Mail mail) throws Exception{

        try {

           MimeMessage msg = new MimeMessage(MailSenderConfig.session);
            //设置发件人
            msg.setFrom(new InternetAddress(MailConstants.sender));
            //设置收件人,to为收件人,cc为抄送,bcc为密送
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getRecipient(), false));
//            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(mail.getRecipient(), false));
//            msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(mail.getRecipient(), false));
            msg.setSubject(mail.getTitle());
            //设置邮件消息
//            msg.setText(mail.getContent());//文本内容
            msg.setContent(mail.getContent(), "text/html;charset = gbk");
            //设置发送的日期
            msg.setSentDate(new Date());
            //调用Transport的send方法去发送邮件
            Transport.send(msg);
        } catch (Exception e) {
           throw e;
        }
    }
    /**
     * 发送邮件
     * @param mail
     * @throws Exception
     */
    public static void sendMail(Mail mail) throws Exception{

        try {
            JavaMailSenderImpl javaMailSender = MailSenderConfig.javaMailSender;
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            messageHelper.setFrom(mail.getSender());
            messageHelper.setTo(mail.getRecipient());
            messageHelper.setSubject(mail.getTitle());
            messageHelper.setText(mail.getContent(),true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw e;
        }
    }
}
