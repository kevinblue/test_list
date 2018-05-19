
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.entity.notice
 * 文件名：         MessageNotice.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-8-8-上午10:05:03
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.entity.notice;

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
import org.hibernate.annotations.Type;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;


 /**
 * 类名称：     MessageNotice
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-8-8 上午10:05:03
 * 修改备注：
 * @version 1.0.0
 **/
@Entity
@Table(name="T_MESSAGES_NOTICES")
public class MessageNotice 
{
	@FieldName(name="主键标识")
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id ;
	
	@FieldName(name="消息主题")
	@Column(name="TITLE_",nullable=false)
	private String title;
	
	@FieldName(name="消息内容")
	@Lob 
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@Column(name="CONTENT_",nullable=false)
	private String content;
	
	@FieldName(name="消息描述")
	@Column(name="DESCRIPTION_")
	private String description;
	
	@FieldName(name="是否已读：0未读，1已读")
	private Integer hasRead;
	
	@FieldName(name="提醒开始时间")
	@Column(name="START_DATE_", length=20)	
	private String startDate;
	
	@FieldName(name="提醒截止时间")
	@Column(name="END_DATE_", length=20)	
	private String endDate;
	
	@FieldName(name="提醒人")
	@JoinColumn(name="RECEIVER_")
	@ManyToOne(fetch=FetchType.LAZY)
	private User receiver;
	
	@FieldName(name="消息分类")
	@Enumerated(EnumType.STRING)
	@Column(name="MEASSAGE_NOTICE_TYPE_",nullable = false)
	private MeassageNoticeType meassageNoticeType;
	
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	@ManyToOne(fetch=FetchType.LAZY)
	private User creator;
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE_", length=20)	
	private String createDate;
	
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR_")
	@ManyToOne(fetch=FetchType.LAZY)
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE_", length=20)	
	private String modifyDate;

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getDescription() {
		return description;
	}

	public Integer getHasRead() {
		return hasRead;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public User getReceiver() {
		return receiver;
	}

	public MeassageNoticeType getMeassageNoticeType() {
		return meassageNoticeType;
	}

	public User getCreator() {
		return creator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public User getModificator() {
		return modificator;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setHasRead(Integer hasRead) {
		this.hasRead = hasRead;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public void setMeassageNoticeType(MeassageNoticeType meassageNoticeType) {
		this.meassageNoticeType = meassageNoticeType;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setModificator(User modificator) {
		this.modificator = modificator;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
}
