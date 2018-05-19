
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.entity
 * 文件名：         LoginInfo.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-9-9-下午07:07:47
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


 /**
 * 类名称：     LoginInfo
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-9-9 下午07:07:47
 * 修改备注：
 * @version 1.0.0
 **/
@Entity
@Table(name = "T_LOGIN_INFO")
public class LoginInfo 
{
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32, name = "ID_")
    private String id;
	@JoinColumn(name = "LOGIN_USER_ID_")
	@ManyToOne(fetch = FetchType.LAZY)
    private User   loginUser;
	@Column(length =100, name = "SESSION_ID_")
	private String sessionId ;
	@Column(length =100, name = "REMOVED_SESSION_ID_")
	private String removedSessionId ;
    @Column(length =30, name = "LOGIN_TIME_")
    private String loginTime ;
    @Column(length = 30, name = "EXPIRE_TIME_")
    private String expireTime ;
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public String getId() {
		return id;
	}
	public User getLoginUser() {
		return loginUser;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setLoginUser(User loginUser) {
		this.loginUser = loginUser;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public String getRemovedSessionId() {
		return removedSessionId;
	}
	public void setRemovedSessionId(String removedSessionId) {
		this.removedSessionId = removedSessionId;
	}
}
