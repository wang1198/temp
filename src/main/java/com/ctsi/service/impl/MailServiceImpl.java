package com.ctsi.service.impl;

import com.ctsi.common.MyAuthenticator;
import com.ctsi.domain.MailSenderInfo;
import com.ctsi.service.MailService;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

/**
 * ClassName MailServiceImpl
 * Description //TODO
 * Author tongliwei
 * Date 2019/5/15 18:32
 * Version v1.0
 **/
@Service
public class MailServiceImpl implements MailService {
    /**
     * 以文本格式发送邮件
     */
    @Override
    public boolean sendTextMail(MailSenderInfo info){
        // 判断是否需要身份认证
        MyAuthenticator authenticator = null;
        Properties pro = info.getProperties();
        if (info.isValidate()) {
            // 如果需要身份认证，则创建一个密码验证器
            authenticator = new MyAuthenticator(info.getUserName(), info.getPassword());
        }
        try {
            // 根据邮件会话属性和密码验证器构造一个发送邮件的session
            Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(info.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            Address to = new InternetAddress(info.getToAddress());
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // 设置邮件消息的主题
            mailMessage.setSubject(info.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // 设置邮件消息的主要内容
            String mailContent = info.getContent();
            mailMessage.setText(mailContent);
            // 发送邮件
            Transport.send(mailMessage);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * 以HTML格式发送邮件
     */
    @Override
    public boolean sendHtmlMail(MailSenderInfo info){
        // 判断是否需要身份认证
        MyAuthenticator authenticator = null;
        Properties pro = info.getProperties();
        //如果需要身份认证，则创建一个密码验证器
        if (info.isValidate()) {
            authenticator = new MyAuthenticator(info.getUserName(), info.getPassword());
        }
        try {
            // 根据邮件会话属性和密码验证器构造一个发送邮件的session
            Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(info.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            Address to = new InternetAddress(info.getToAddress());
            // Message.RecipientType.TO属性表示接收者的类型为TO
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // 设置邮件消息的主题
            mailMessage.setSubject(info.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容
            html.setContent(info.getContent(), "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
            // 将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(mainPart);
            // 发送邮件
            Transport.send(mailMessage);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
