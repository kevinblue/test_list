package com.tenwa.business.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.tenwa.kernal.annotation.FieldName;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2013-7-8 上午11:44:42 类说明
 */
@Entity
@Table(name = "T_LOGS")
@FieldName(name = "系统日志")
public class SystemLog {
	// 枚举类型
	public enum Type {
		SYSTEM, BUSINESS, ERROR, ENTITY,MENU;
	};

	public enum Level {
		DEBUG, INFO, WARN, ERROR, FATAL;
	}
	
	public enum TerminateType {
		WEB,APP
	}
	
	public enum Model {
		SESSION, MENUCLASSONE, MENUCLASSTWO, ADD, QUERY,MODIFY,DELETE,EXCEPTION;
	}
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32, name = "ID_")
	private String id;

	@FieldName(name = "客户端类型")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10, name = "TERMINATE_TYPE_")
	private TerminateType terminatetype;
	
	@FieldName(name = "日志类型")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10, name = "TYPE_")
	private Type type;

	@FieldName(name = "日志等级")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10, name = "LEVEL_")
	private Level level;

	@FieldName(name = "日志时间")
	@Column(name = "TIME_", length = 22)
	private String time;
	
	@FieldName(name = "连接IP地址")
	@Column(name = "IP_ADD", length = 254)
	private String ipAdd;

	@FieldName(name = "URL地址")
	@Column(name = "URL_", length = 254)
	private String url;

	
	@FieldName(name = "操作模块")
	@Column(name = "OPERATE_MODEL", length = 254)
	private String operateModel;
	
	
	@FieldName(name = "操作名称")
	@Column(name = "OPERATE_NAME", length = 254)
	private String operateName;

	@FieldName(name = "操作人")
	@ManyToOne
	@JoinColumn(name = "OPERATE_USER")
	private User operateUser;

	@FieldName(name = "日志内容")
	@Lob 
	@org.hibernate.annotations.Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@Column(name = "CONTENT_")
	private String content;

	@FieldName(name = "备注")
	@Lob 
	@org.hibernate.annotations.Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@Column(name = "REMARK_")
	private String remark;

	public String getId() {
		return id;
	}

	public Type getType() {
		return type;
	}

	public Level getLevel() {
		return level;
	}

	public String getTime() {
		return time;
	}

	public String getIpAdd() {
		return ipAdd;
	}

	public String getUrl() {
		return url;
	}

	public String getOperateName() {
		return operateName;
	}

	public User getOperateUser() {
		return operateUser;
	}

	public String getContent() {
		return content;
	}

	public String getRemark() {
		return remark;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setIpAdd(String ipAdd) {
		this.ipAdd = ipAdd;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public void setOperateUser(User operateUser) {
		this.operateUser = operateUser;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperateModel() {
		return operateModel;
	}

	public void setOperateModel(String operateModel) {
		this.operateModel = operateModel;
	}

	public TerminateType getTerminatetype() {
		return terminatetype;
	}

	public void setTerminatetype(TerminateType terminatetype) {
		this.terminatetype = terminatetype;
	}

	
	
}
