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

import com.tenwa.business.entity.SystemLog.TerminateType;
import com.tenwa.kernal.annotation.FieldName;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2013-7-8 上午11:44:42 类说明
 */
@Entity
@Table(name = "T_LOGS_ENTITY")
@FieldName(name = "数据操作日志")
public class EntityLog {
	// 枚举类型
	public enum EntityOpeType {
		INSERT, UPDATE, DELETE,QUERY;
	};
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32, name = "ID_")
	private String id;

	@FieldName(name = "操作类型")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10, name = "ENTITY_OPE_TYPE")
	private EntityOpeType opeType;

	@FieldName(name = "日志时间")
	@Column(name = "TIME_", length = 22)
	private String time;
	
	
	@FieldName(name = "操作实体名")
	@Column(name = "OPERATE_ENTITY", length = 254)
	private String operateEntity;
	
	@FieldName(name = "操作表名")
	@Column(name = "OPERATE_TABLE", length = 254)
	private String operateTable;
	
	
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
	
	@FieldName(name = "插入前数据内容")
	@Lob 
	@org.hibernate.annotations.Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@Column(name = "BEFORE_CONTENT_")
	private String beforeContent;

	@FieldName(name = "备注")
	@Lob 
	@org.hibernate.annotations.Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@Column(name = "REMARK_")
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public EntityOpeType getOpeType() {
		return opeType;
	}

	public void setOpeType(EntityOpeType opeType) {
		this.opeType = opeType;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getOperateEntity() {
		return operateEntity;
	}

	public void setOperateEntity(String operateEntity) {
		this.operateEntity = operateEntity;
	}

	public String getOperateTable() {
		return operateTable;
	}

	public void setOperateTable(String operateTable) {
		this.operateTable = operateTable;
	}

	public User getOperateUser() {
		return operateUser;
	}

	public void setOperateUser(User operateUser) {
		this.operateUser = operateUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBeforeContent() {
		return beforeContent;
	}

	public void setBeforeContent(String beforeContent) {
		this.beforeContent = beforeContent;
	}

	
}
