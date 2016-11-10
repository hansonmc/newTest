package com.jlc.api.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;


/**
 * <p>Title: Email</p>
 * <p>Description: </p>
 * <p>Company: jyd </p> 
 * @author zhangzhigang
 * @date Mar 14, 2014
 */
public class Email implements Runnable {
    
    protected Logger log = Logger.getLogger(Email.class);

    public void run() {
        send();
    }
    
    private String fromAddress;
    private String toAddress;
    private String subject;
    private String content;
    
    /**
     * 
     * @param fromAddress  发件人邮箱
     * @param toAddress 收件人邮箱
     * @param subject   主题
     * @param content   内容
     */
    public Email(String fromAddress, String toAddress,
        String subject, String content){
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.subject = subject;
        this.content = content;
    }
    
    /**
     * 构造之后调用此方法执行发送操作
     * 
     * @author zzg16 
     * @date Mar 14, 2014 2:10:05 PM
     */
    public void send() {
        if(sendHtmlContent(fromAddress, toAddress, subject, content)){
            log.info("to "+toAddress+" 发送成功！");
        }else{
            log.info("to "+toAddress+" 发送失败！");
        }
    }
 
    private static final String mailServerHost;
    private static final String mailServerPort;
    private static final String validate;
    private static final String username;
    private static final String password;
    private static Properties pro;
    
    static {
        PropertiesUtil p = new PropertiesUtil("config-common.properties");
        mailServerHost = p.readProperty("mail.smtp.host");
        mailServerPort = p.readProperty("mail.smtp.port");
        validate = p.readProperty("mail.smtp.auth");
        username = p.readProperty("mail.smtp.username");
        password = p.readProperty("mail.smtp.password");
    }
    
    private boolean sendHtmlContent(String fromAddress, String toAddress,
        String subject, String content) {
        try {
            // 判断是否需要身份认证
            MyAuthenricator authenticator = null;
            if (StringUtils.equals(validate, "true")) {
                authenticator = new MyAuthenricator(username, password);

            }
            // 根据邮件会话属性和密码验证器构造一个发送邮件的session
            Session sendMailSession = Session.getInstance(Email.getProperties(),
                authenticator);
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(fromAddress);
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            Address to = new InternetAddress(toAddress);
            // Message.RecipientType.TO属性表示接收者的类型为TO
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // 设置邮件消息的主题  金联储平台账号激活
            // javamail决定用什么方式来编码，编码内容的字符集是系统字符集
            mailMessage.setSubject(MimeUtility.encodeText("金联储平台账号激活"));
            //使用指定的base64方式编码,并指定编码内容的字符集是gb2312 
            mailMessage.setSubject(MimeUtility.encodeText("金联储平台账号激活","gb2312","B"));
            //subject =  new String(subject.toString().getBytes("iso8859-1"),"utf-8"); 
            //mailMessage.setSubject(subject);
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容
            html.setContent(content, "text/html; charset=utf8");
            mainPart.addBodyPart(html);
            // 将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(mainPart);
            // 发送邮件
            Transport.send(mailMessage);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    /**
     * 获得邮件会话属性
     * 
     * @return
     * 
     * @author zzg16 
     * @date Mar 14, 2014 11:32:07 AM
     */
    private static Properties getProperties() {
        if (pro == null) {
            pro = new Properties();
            pro.put("mail.smtp.host", mailServerHost);
            pro.put("mail.smtp.port", mailServerPort);
            pro.put("mail.smtp.auth", validate);
            return pro;
        }
        return pro;
    }
    
    static class MyAuthenricator extends Authenticator {
        String user = "";
        String pass = "";

        public MyAuthenricator(String user, String pass) {
            this.user = user;
            this.pass = pass;
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, pass);
        }
    }  
}

