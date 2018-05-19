/**
 * 项目名称：    系统名称
 * 包名：              com.email
 * 文件名：         SendEmail.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-12-2-下午12:22:02
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.email;

import java.net.URL;
import java.util.List;

import javax.mail.internet.MimeUtility;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;

import com.tenwa.business.entity.email.EmailConfig;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.FileUtil;
import com.tenwa.kernal.utils.WebUtil;

/**
 * 类名称： SendEmail 类描述： 创建人： Administrator 修改人： Administrator 修改时间：2013-12-2
 * 下午12:22:02 修改备注：
 * 
 * @version 1.0.0
 **/

public class SendEmailUtil {
	
	/**
	 * @method sendMessage 发送简单邮件，不会对内容进行html解析，没有附件
	 * 
	 * @param mailToAddress
	 *            邮件接收地址
	 * @param mailSubject
	 *            邮件主题
	 * @param mailContent
	 *            邮件内容
	 * @throws Exception
	 * @returnType void
	 * @exception
	 * @since 1.0.0
	 **/
	public static String sendSimpleMessage(String mailToAddress, String mailSubject, String mailContent) throws Exception {
		return sendMessage(mailToAddress, null, mailSubject, mailContent, false, null, null, null, null, null, null);
	}
	
	/**
	 * @method sendMessage 发送简单邮件，不会对内容进行html解析，没有附件
	 * 
	 * @param mailToAddress
	 *            邮件接收地址
	 * @param mailSubject
	 *            邮件主题
	 * @param mailContent
	 *            邮件内容
	 * @param emailAttachmentsFullPath
	 *            附件完整路径,以英文分号(;)分隔
	 * @throws Exception
	 * @returnType void
	 * @exception
	 * @since 1.0.0
	 **/
	public static String sendSimpleMessage(String mailToAddress, String mailSubject, String mailContent, String emailAttachmentsFullPath) throws Exception {
		return sendMessage(mailToAddress, null, mailSubject, mailContent, false, null, null, null, null, emailAttachmentsFullPath, null);
	}
	
	/**
	 * @method sendMessage 发送邮件
	 * 
	 * @param mailToAddress
	 *            邮件接收地址
	 * @param mailSubject
	 *            邮件主题
	 * @param mailContent
	 *            邮件内容
	 * @param isHtmlContent
	 *            是否需要html解析内容
	 * @param emailAttachmentsFullPath
	 *            附件完整路径,以英文分号(;)分隔
	 * @throws Exception
	 * @returnType void
	 * @exception
	 * @since 1.0.0
	 **/
	public static String sendMessage(String mailToAddress, String mailSubject, String mailContent, boolean isHtmlContent, String emailAttachmentsFullPath) throws Exception {
		return sendMessage(mailToAddress, null, mailSubject, mailContent, isHtmlContent, null, null, null, null, emailAttachmentsFullPath, null);
	}

	/**
	 * @method sendMessage 发送邮件
	 * 
	 * @param mailToAddress
	 *            邮件接收地址
	 * @param mailToRealName
	 *            邮件接收人身份
	 * @param mailSubject
	 *            邮件主题
	 * @param mailContent
	 *            邮件内容
	 * @param isHtmlContent
	 *            是否需要html解析内容
	 * @param bccs
	 *            密送人地址,以英文分号(;)分隔
	 * @param bccRealNames
	 *            密送人地址真实身份,以英文分号(;)分隔
	 * @param ccs
	 *            抄送人地址,以英文分号(;)分隔
	 * @param bccRealNames
	 *            抄送人真实身份,以英文分号(;)分隔
	 * @param emailAttachmentsFullPath
	 *            附件完整路径,以英文分号(;)分隔
	 * @param emailAttachmentsNames
	 *            附件显示,以英文分号(;)分隔
	 * @throws Exception
	 * @returnType void
	 * @exception
	 * @since 1.0.0
	 **/
	public static String sendMessage(String mailToAddress, String mailToRealName, String mailSubject, String mailContent, boolean isHtmlContent, String ccs, String ccsRealNames, String bccs, String bccRealNames, String emailAttachmentsFullPath, String emailAttachmentsNames) throws Exception {
		BaseService baseService = (BaseService) WebUtil.getApplicationContext().getBean("baseService");
		List<EmailConfig> emailConfigList = baseService.findEntities(EmailConfig.class);
		EmailConfig emailConfig = null;
		if (0 == emailConfigList.size()) {
			emailConfig = new EmailConfig();
			emailConfig.setHost("mail.yuexiu.com");
			emailConfig.setPort(25);
			emailConfig.setFromUser("xiaoe.test");
			emailConfig.setFromUserPassword("Xe123456");
			emailConfig.setFromUserEmailAddress("xiaoe.test@yuexiu.com");
			emailConfig.setFromUserRealName("邮件管理员");
			baseService.saveEntity(emailConfig);
		} else {
			emailConfig = emailConfigList.get(0);
		}
		HtmlEmail email = new HtmlEmail();
		// 设置主题的字符集为UTF-8
		email.setCharset("UTF-8");
		// 发送简单邮件
		email.setHostName(emailConfig.getHost());
		// 需要邮件发送服务器验证,用户名/密码
		email.setAuthentication(emailConfig.getFromUser(), emailConfig.getFromUserKey());
		String fromUserEmailAddress = emailConfig.getFromUserEmailAddress();
		String fromUserRealName = emailConfig.getFromUserRealName();
		if (null != fromUserRealName) {
			email.setFrom(fromUserEmailAddress, fromUserRealName);
		} else {
			email.setFrom(fromUserEmailAddress);
		}
		if (null != mailToRealName) {
			email.addTo(mailToAddress, mailToRealName);
		} else {
			email.addTo(mailToAddress);
		}
		if (null != mailSubject) {
			email.setSubject(mailSubject);
		}
		if (isHtmlContent) {
			email.setHtmlMsg(mailContent);
		} else {
			email.setMsg(mailContent);
		}
		// 抄送
		if (null != ccs) {
			String[] ccsArr = ccs.replaceAll("\\s", "").split(";");
			String[] ccsRealNameArr = new String[0];
			if (null != ccsRealNames) {
				ccsRealNameArr = ccsRealNames.replaceAll("\\s", "").split(";");
			}
			for (int i = 0; i < ccsArr.length; i++) {
				String cc = ccsArr[i];
				if (null == cc)
					continue;
				String ccRealName = null;
				try {
					ccRealName = ccsRealNameArr[i];
				} catch (Exception e) {
					ccRealName = null;
				}
				if (null != ccRealName) {
					email.addCc(cc, ccRealName);
				} else {
					email.addCc(cc);
				}
			}
		}
		// 密送
		if (null != bccs) {
			String[] bccsArr = bccs.replaceAll("\\s", "").split(";");
			String[] bccsRealNameArr = new String[0];
			if (null != bccRealNames) {
				bccsRealNameArr = bccRealNames.replaceAll("\\s", "").split(";");
			}
			for (int i = 0; i < bccsArr.length; i++) {
				String bcc = bccsArr[i];
				if (null == bcc)
					continue;
				String bccRealName = null;
				try {
					bccRealName = bccsRealNameArr[i];
				} catch (Exception e) {
					bccRealName = null;
				}
				if (null != bccRealName) {
					email.addBcc(bcc, bccRealName);
				} else {
					email.addBcc(bcc);
				}
			}
		}
		// 添加附件
		if (null != emailAttachmentsFullPath) {
			String[] emailAttachmentsFullPathArr = emailAttachmentsFullPath.replaceAll("\\s", "").split(";");
			String[] emailAttachmentsNameArr = new String[0];
			if (null != emailAttachmentsNames) {
				emailAttachmentsNameArr = emailAttachmentsNames.replaceAll("\\s", "").split(";");
			}
			for (int i = 0; i < emailAttachmentsFullPathArr.length; i++) {
				String attachFullPath = emailAttachmentsFullPathArr[i];
				if (null == attachFullPath)
					continue;
				// 附件，可以定义多个附件对象
				EmailAttachment attachment = new EmailAttachment();
				if (attachFullPath.toLowerCase().startsWith("ftp:")||attachFullPath.toLowerCase().startsWith("file:")||attachFullPath.toLowerCase().startsWith("http:")||attachFullPath.toLowerCase().startsWith("https:")) {
					attachment.setURL(new URL(attachFullPath));
				} else {
					attachFullPath = FileUtil.getFilePathString(attachFullPath);
					attachment.setPath(attachFullPath);
				}
				attachment.setDisposition(EmailAttachment.ATTACHMENT);
				String fileName = null;
				try {
					fileName = emailAttachmentsNameArr[i];
				} catch (Exception e) {
					fileName = null;
				}
				if (null == fileName) {
					int lastIndexOfFileSeparator = attachFullPath.lastIndexOf("/");
					if (lastIndexOfFileSeparator > -1) {
						fileName = attachFullPath.substring(lastIndexOfFileSeparator + 1);
						attachment.setName(MimeUtility.encodeText(fileName));
					}
				} else {
					attachment.setName(MimeUtility.encodeText(fileName));
				}

				email.attach(attachment);
			}
		}
		return email.send();
	}
}
