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

import com.tenwa.kernal.annotation.FieldName;


/**
 * @author 孙传良
 * @date 2013-8-6下午03:36:27
 * @info
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "系统内消息接收组(公告和上报)")
@Table(name="BASE_MESSAGE_TO_GROUP")
public class BaseMessageToGroup {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="消息ID")
	@ManyToOne(targetEntity=BaseMessage.class,fetch=FetchType.LAZY)
	@JoinColumn(name="MSG_ID")
	private BaseMessage msgID;
	
	@ManyToOne
	@FieldName(name="组类型[人员/群组/部门/角色/区域]")
	@JoinColumn(name="MSG_GROUP")
	private DictionaryData msgGroup;
	
	@FieldName(name="组ID")
	@Column(name="GOUP_ID", length=100)	
	private String goupID;
	
	@ManyToOne
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE", length=20)	
	private String createDate;
	
	@ManyToOne
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE", length=20)	
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BaseMessage getMsgID() {
		return msgID;
	}

	public void setMsgID(BaseMessage msgID) {
		this.msgID = msgID;
	}

	public DictionaryData getMsgGroup() {
		return msgGroup;
	}

	public void setMsgGroup(DictionaryData msgGroup) {
		this.msgGroup = msgGroup;
	}

	public String getGoupID() {
		return goupID;
	}

	public void setGoupID(String goupID) {
		this.goupID = goupID;
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

	public User getModificator() {
		return modificator;
	}

	public void setModificator(User modificator) {
		this.modificator = modificator;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
