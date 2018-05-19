package com.tenwa.business.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
 * 项目名称：    系统名称
 * 包名：              
 * 文件名：         MenuGroup.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-3-5-上午09:21:34
 * Copyright：2013XX公司-版权所有
 **/

/**
 * 类名称：     MenuGroup
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-3-5 上午09:21:34
 * 修改备注：
 * @version 1.0.0
 **/
@Entity
@Table(name="T_MENUS_DEPTS")
public class MenuDepartment implements Serializable{
	private static final long serialVersionUID = -8578387391539777426L;
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String       id;
	@ManyToOne(cascade = 
	{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	,fetch=FetchType.LAZY) 
	@JoinColumn(name="MENU_ID_",nullable=false)
    private Menu         menu;
	@ManyToOne(cascade = 
	{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	,fetch=FetchType.LAZY) 
	@JoinColumn(name="DEPT_ID_",nullable=false)
    private Department   dept;
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
	public Menu getMenu() {
		return menu;
	}
	public Department getDept() {
		return dept;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public void setDept(Department dept) {
		this.dept = dept;
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
