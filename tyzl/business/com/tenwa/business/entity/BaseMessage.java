package com.tenwa.business.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@FieldName(name = "系统内消息(公告和上报)")
@Table(name="BASE_MESSAGE")
public class BaseMessage {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@ManyToOne
	@FieldName(name="消息类型[公告/上报]")
	@JoinColumn(name="MSG_TYPE")
	private DictionaryData msgType;
	
	@FieldName(name="主题")
	@Column(name="MSG_TITLE", length=100)	
	private String msgTitle;
	
	@FieldName(name="正文")
	@Lob
	@Column(name="SUBJECT_")	
	private String msgText;
	
	@ManyToOne
	@FieldName(name="发布人")
	@JoinColumn(name="fromUser")	
	private User fromUser;
	
	@FieldName(name="发布时间")
	@Column(name="SEND_DATE", length=20)	
	private String sendDate;

	@FieldName(name="接收人类型")
	@Column(name="TO_USER_TYPE", length=100)	
	private String toUserType;
	
	@FieldName(name="消息接收人")
	@OneToMany(mappedBy="msgID",fetch=FetchType.LAZY) 
	private Set<BaseMessageToUser> toUser = new HashSet<BaseMessageToUser>();
	
	@FieldName(name="消息接收人")
	@OneToMany(mappedBy="msgID",fetch=FetchType.LAZY) 
	private Set<BaseMessageToGroup> toGroup = new HashSet<BaseMessageToGroup>();
	
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

	public DictionaryData getMsgType() {
		return msgType;
	}

	public void setMsgType(DictionaryData msgType) {
		this.msgType = msgType;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgText() {
		return msgText;
	}

	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getToUserType() {
		return toUserType;
	}

	public void setToUserType(String toUserType) {
		this.toUserType = toUserType;
	}

	public Set<BaseMessageToUser> getToUser() {
		return toUser;
	}

	public void setToUser(Set<BaseMessageToUser> toUser) {
		this.toUser = toUser;
	}

	public Set<BaseMessageToGroup> getToGroup() {
		return toGroup;
	}

	public void setToGroup(Set<BaseMessageToGroup> toGroup) {
		this.toGroup = toGroup;
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
