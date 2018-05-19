
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.entity.notice
 * 文件名：         Notice.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-7-30-上午09:59:38
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.entity.notice;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * 类名称：     Notice
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-7-30 上午09:59:38
 * 修改备注：
 * @version 1.0.0
 **/
@Entity
@Table(name="T_NOTICES")
public class Notice 
{
	@FieldName(name="主键标识")
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id ;
	
	@FieldName(name="公告标题")
	@Column(name="TITLE_",nullable=false)
	private String title;
	
	@FieldName(name="公告内容")
	@Lob 
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@Column(name="CONTENT_",nullable=false)
	private String content;
	@FieldName(name="公告描述")
	@Column(name="DESCRIPTION_")
	private String description;
	@FieldName(name="发布时间")
	@Column(name="PUBLISH_DATE_", length=20,nullable=false)	
	private String publishDate;
	@FieldName(name="截止时间")
	@Column(name="DEADLINE_DATE_", length=20,nullable=false)	
	private String deadlineDate;
	
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

	public String getPublishDate() {
		return publishDate;
	}

	public String getDeadlineDate() {
		return deadlineDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public void setDeadlineDate(String deadlineDate) {
		this.deadlineDate = deadlineDate;
	}
    
}
