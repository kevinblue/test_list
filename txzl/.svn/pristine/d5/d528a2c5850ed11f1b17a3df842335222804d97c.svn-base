
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.email
 * 文件名：         EmailBase.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-12-2-上午11:52:27
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.entity.email;
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
 * 类名称：     EmailBase
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-12-2 上午11:52:27
 * 修改备注：
 * @version 1.0.0
 **/
@FieldName(name="邮箱主机信息")
@Entity
@Table(name="T_EMAIL_CONFIG")
public class EmailConfig {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id ;
	
	@FieldName(name="邮箱主机")
	@Column(name="HOST_",nullable=false)
	private String  host;
	
	@FieldName(name="邮箱端口号")
	@Column(name="PORT_",nullable=false)
	private Integer port;
	
	@FieldName(name="邮箱发送者")
	@Column(name="FROM_USER_",nullable=false)
	private String  fromUser;
	
	@FieldName(name="邮箱发送者密码")
	@Column(name="FROM_USER_PASSWORD_",nullable=false)
	private String  fromUserPassword;
	
	@FieldName(name="邮箱发送者邮件地址")
	@Column(name="FROM_USER_EMAIL_ADDRESS_",nullable=false)
	private String  fromUserEmailAddress;
	
	@FieldName(name="邮箱发送者真实身份")
	@Column(name="FROM_USER_REAL_NAME_")
	private String  fromUserRealName;
	
	@FieldName(name = "是否配置发送邮件")
	@Column(name = "IS_SEND", length =6)
	private String issend;
	
	@FieldName(name = "允许发送短信的主机IP")
	@Column(name = "ALLOW_SEND_IPS", length = 400)
	private String allowSendIps;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODIFICATOR_")
	@FieldName(name="修改人")
	private User modificator;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR_")
	@FieldName(name="创建人")
	private User creator;

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
	public void setId(String id) {
		this.id = id;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getFromUser() {
		return fromUser;
	}
	public String getFromUserPassword() {
		return fromUserPassword;
	}
	public String getFromUserEmailAddress() {
		return fromUserEmailAddress;
	}
	public String getFromUserRealName() {
		return fromUserRealName;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public void setFromUserPassword(String fromUserPassword) {
		this.fromUserPassword = fromUserPassword;
	}
	public void setFromUserEmailAddress(String fromUserEmailAddress) {
		this.fromUserEmailAddress = fromUserEmailAddress;
	}
	public void setFromUserRealName(String fromUserRealName) {
		this.fromUserRealName = fromUserRealName;
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
