package com.example.service_hi.user.utils.email;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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

import com.sun.mail.util.MailSSLSocketFactory;
/**
 * 邮件发送工具
 * @author APATHY
 *2020.04.03
 */

public class SendEmail {

    public static void getEmail(String userEmail,String content,String filePath,String fileName) {
        /*收件人，content文本内容，发送文件的path，文件名*/
    	try {
            
            //设置发件人
            String from = "3133678711@qq.com";
            
            //设置收件人
            String to = userEmail;
            
            //设置邮件发送的服务器，这里为QQ邮件服务器
            String host = "smtp.qq.com";
            
            //获取系统属性
            Properties properties = System.getProperties();
            
            //SSL加密
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);
            
            //设置系统属性
            properties.setProperty("mail.smtp.host", host);
            properties.put("mail.smtp.auth", "true");
            
            //获取发送邮件会话、获取第三方登录授权码
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                	 //qq邮箱授权码
                    return new PasswordAuthentication(from, "gyuxcdjlpqyodcej");
                }
            });
            
            Message message = new MimeMessage(session);
            
            //防止邮件被当然垃圾邮件处理，披上Outlook的马甲
            message.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");
            
            message.setFrom(new InternetAddress(from));
            
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            
            //邮件标题
            message.setSubject("This is the NAS Email!");
            
            BodyPart bodyPart = new MimeBodyPart();
            
            bodyPart.setText("ApathyJuly发送了邮件给你");
            
            Multipart multipart = new MimeMultipart();
            
            multipart.addBodyPart(bodyPart);
            if(filePath!=null) {
            	//发送附件/文件
                bodyPart = new MimeBodyPart();
                String fileNamepath = filePath;
                DataSource dataSource = new FileDataSource(fileNamepath);
                bodyPart.setDataHandler(new DataHandler(dataSource));
                bodyPart.setFileName(fileName);
                multipart.addBodyPart(bodyPart);
                message.setContent(multipart);
            };
             
            
            /*发送content文本内容*/
            message.setText(content);
            /*发送*/
            Transport.send(message);
            System.out.println("mail transports successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
