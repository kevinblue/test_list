
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.entity.message
 * 文件名：         MessageBase.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-12-2-下午01:49:29
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.entity.message;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;


 /**
 * 类名称：     MessageBase
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-12-2 下午01:49:29
 * 修改备注：
 * @version 1.0.0
 **/

@FieldName(name="短信配置")
@Entity
@Table(name="T_MESSAGE_CONFIG")
public class MessageConfig {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id ;
	@FieldName(name="发送信息主机")
	@Column(name="HOST_",nullable=false)
	private String  host;
	@FieldName(name="发送信息端口号")
	@Column(name="PORT_",nullable=false)
	private Integer port;
	//发送用户
	@FieldName(name="发送信息的用户")
	@Column(name="SENDER_",nullable=false)
	private String sender;
	//发送密码
	@FieldName(name="发送人认证密码")
	@Column(name="SENDER_PASSWORD_")
	private String senderPassword;
	
	//配置形如http://{host}:{port}/sendSms.cgi
	@FieldName(name="发送信息的url")
	@Column(name="SEND_MESSAGE_URL_",nullable=false)
	private String sendMessageUrl ;
	//发送的参数信息"sender={sender}&receiver={receiverMobilePhone}&msg={msg}&password={senderPassword}"; 
	@FieldName(name="发送时候的参数信息")
	@Column(name="SEND_PARAMS_URL_",nullable=false)
	private String sendParamsUrl;
	//子码subcode
	@FieldName(name="发送人的子码")
	@Column(name="SUB_CODE_")
	private String subCode ;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODIFICATOR_")
	@FieldName(name="修改人")
	private User modificator;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR_")
	@FieldName(name="创建人")
	private User creator;

	@FieldName(name = "是否通过该配置发送短信")
	@Column(name = "IS_SEND", length = 6)
	private String issend;
	
	@FieldName(name = "允许发送短信的主机IP")
	@Column(name = "ALLOW_SEND_IPS", length = 400)
	private String allowSendIps;
	
	@Column(name = "CREATE_DATE", length = 40)
	@FieldName(name="创建时间")
	private String createDate;

	@Column(name = "MODIFY_DATE", length = 40)
	@FieldName(name="修改时间")
	private String modifyDate;
	
	
	public String getIssend() {
		return issend;
	}
	public void setIssend(String issend) {
		this.issend = issend;
	}
	public String getAllowSendIps() {
		return allowSendIps;
	}
	public void setAllowSendIps(String allowSendIps) {
		this.allowSendIps = allowSendIps;
	}
	public String getId() {
		return id;
	}
	public String getHost() {
		return host;
	}

	public Integer getPort() {
		return port;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getSendMessageUrl() {
		return sendMessageUrl;
	}
	public String getSender() {
		return sender;
	}
	public String getSenderPassword() {
		return senderPassword;
	}
	public String getSubCode() {
		return subCode;
	}
	public String getSendParamsUrl() {
		return sendParamsUrl;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setSendMessageUrl(String sendMessageUrl) {
		this.sendMessageUrl = sendMessageUrl;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public void setSenderPassword(String senderPassword) {
		this.senderPassword = senderPassword;
	}
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}
	public void setSendParamsUrl(String sendParamsUrl) {
		this.sendParamsUrl = sendParamsUrl;
	}
	public User getModificator() {
		return modificator;
	}
	public void setModificator(User modificator) {
		this.modificator = modificator;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
}
