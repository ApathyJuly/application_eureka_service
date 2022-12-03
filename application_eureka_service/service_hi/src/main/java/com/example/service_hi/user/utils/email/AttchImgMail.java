package com.example.service_hi.user.utils.email;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.mail.util.MailSSLSocketFactory;

public class AttchImgMail {
// JavaMail需要Properties来创建一个session对象。它将寻找字符串"mail.smtp.host"，属性值就是发送邮件的主机.
	public static void main(String[] args) throws Exception {
		String senderNick = "五月蔷薇";
		 // 收件人电子邮箱
		 String to = "2897250580@qq.com";
	        // 发件人电子邮箱
	        String from = "3133678711@qq.com";


	        // 指定发送邮件的主机为 smtp.qq.com
	        String host = "smtp.qq.com";  //QQ 邮件服务器

	        // 获取系统属性
	        Properties properties = System.getProperties();
	        
	        // 设置邮件服务器
	        properties.setProperty("mail.smtp.host", host);

	        properties.put("mail.smtp.auth", "true");
	        MailSSLSocketFactory sf = new MailSSLSocketFactory();
	        sf.setTrustAllHosts(true);
	        properties.put("mail.smtp.ssl.enable", "true");
	        properties.put("mail.smtp.ssl.socketFactory", sf);
	        // 获取默认session对象
	        Session session = Session.getDefaultInstance(properties,new Authenticator(){
	            public PasswordAuthentication getPasswordAuthentication()
	            {
	                return new PasswordAuthentication("3133678711@qq.com", "xxxxx"); //发件人邮件用户名、生成授权码
	            }
	        });
	        try{
	            // 创建默认的 MimeMessage 对象
	            MimeMessage message = new MimeMessage(session);
	            
	            // Set From: 头部头字段
	            message.setFrom(new InternetAddress(from,"五月蔷薇店"));


	            // Set To: 头部头字段
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	            //添加图片
	            // 5. 创建图片"节点"
	            MimeBodyPart image = new MimeBodyPart();
	            // 读取本地文件
	            DataHandler dh = new DataHandler(new FileDataSource("F:\\3.jpg"));
	            // 将图片数据添加到"节点"
	            image.setDataHandler(dh);
	            // 为"节点"设置一个唯一编号（在文本"节点"将引用该ID）
	            image.setContentID("mailTestPic");    
	         // 6. 创建文本"节点"
	            MimeBodyPart text = new MimeBodyPart();
	            // 这里添加图片的方式是将整个图片包含到邮件内容中, 实际上也可以以 http 链接的形式添加网络图片
	            text.setContent("<span style=\"color:red;\">正文</span><br/><img src='cid:mailTestPic'/>", "text/html;charset=UTF-8");
	             
	            // 7. （文本+图片）设置 文本 和 图片"节点"的关系（将 文本 和 图片"节点"合成一个混合"节点"）
	            MimeMultipart mm_text_image = new MimeMultipart("related");
	            mm_text_image.addBodyPart(text);
	            mm_text_image.addBodyPart(image);
	            mm_text_image.setSubType("mixed");    // 关联关系
	             
	            // 8. 将 文本+图片 的混合"节点"封装成一个普通"节点"
	            // 最终添加到邮件的 Content 是由多个 BodyPart 组成的 Multipart, 所以我们需要的是 BodyPart,
	            // 上面的 mailTestPic 并非 BodyPart, 所有要把 mm_text_image 封装成一个 BodyPart
	            MimeBodyPart text_image = new MimeBodyPart();
	            text_image.setContent(mm_text_image);
	            // 10. 设置（文本+图片）和 附件 的关系（合成一个大的混合"节点" / Multipart ）
	            MimeMultipart mm = new MimeMultipart();
	            mm.addBodyPart(text_image);
	            mm.setSubType("related");    
	            // 11. 设置整个邮件的关系（将最终的混合"节点"作为邮件的内容添加到邮件对象）
	            message.setContent(mm);
	            //设置邮件的发送时间,默认立即发送
	            message.setSentDate(new Date());
	            
	          /*  Tianqiyubao tian=new Tianqiyubao();
	            tian.tianqi();*/


	         // Set Subject: 头部头字段
	            message.setSubject("欢迎来选购");
	            // 设置消息体
	           // message.setText(tian.weather+"\n"+tian.location+"\t"+"pm25:"+tian.pm25+"\n"+tian.jianyi+"\n"+tian.shushi+"\n"+tian.yundong+"\n"+tian.ziwx);
	           // message.setText("嗨咯这是正文");  // 发送消息
	           
	            Transport.send(message);
	            
	            System.out.println("Sent message successfully....from runoob.com");
	        }catch (Exception mex) {
	            mex.printStackTrace();
	        }

	}
}
