
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.entity
 * 文件名：         Email.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-10-23-上午09:50:43
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.entity;

import java.util.List;

import javax.mail.internet.MimeUtility;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;


 /**
 * 类名称：     Email
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-10-23 上午09:50:44
 * 修改备注：
 * @version 1.0.0
 **/

public class Email {
	// 发送邮件的邮箱地址服务器 
    private String host; 
    // 这个是你的邮箱用户名以及邮件显示发送者邮箱 
    private String sendEmail ; 
    // 你的邮箱密码 
    private String sendPassword ; 
    //邮件发送者显示姓名 
    private String sendName ; 
     //待接收的邮箱 
    private String mail_to ; 
    //邮件标题 
    private String mail_subject; 
    //邮件内容 
    private String mail_body ; 
    //用于保存发送附件的文件名的集合   
    private List<String> file ; 
    
	public static void main(String[] args) throws Exception {
		//附件，可以定义多个附件对象
	   EmailAttachment attachment = new EmailAttachment();
	   attachment.setPath("E:\\流程状态抽取.sql");
	   attachment.setDisposition(EmailAttachment.ATTACHMENT);
	   attachment.setDescription("Picture of tracywindy");
	   attachment.setName(MimeUtility.encodeText("流程状态抽取.sql"));
	   
       HtmlEmail email = new HtmlEmail();
       //发送简单邮件   
       email.setHostName("smtp.126.com");   
          
       //需要邮件发送服务器验证,用户名/密码   
       email.setAuthentication("tracywindy", "liuhongguang");  
       email.setFrom("tracywindy@126.com","刘飞顶顶顶");
       email.addTo("tracywindy@126.com", "测试好么"); 
       //设置主题的字符集为UTF-8   
       email.setCharset("UTF-8");    
       email.setSubject("测试邮件主题");   
       
       email.attach(attachment);
       email.setHtmlMsg("<a href='http://www.baidu.com'>百度</a>");
       email.send();
       /*email.buildMimeMessage();   
       //设置内容的字符集为UTF-8,先buildMimeMessage才能设置内容文本   
       email.getMimeMessage().setText("测试邮件内容","UTF-8");   
       email.sendMimeMessage(); */ 
	}

}
