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
 * 文件名：         UserDepartmentRole.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-3-4-下午05:56:37
 * Copyright：2013XX公司-版权所有
 **/

/**
 * 类名称：     UserDepartmentRole
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-3-4 下午05:56:37
 * 修改备注：
 * @version 1.0.0
 **/
@Entity
@Table(name="T_ACTIONS_DEPTROLES")
public class ActionDepartmentRole implements Serializable{
	private static final long serialVersionUID = 809975903808256963L;
		@Id
	    @GeneratedValue(generator = "paymentableGenerator")     
	    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
	    @Column(length=32,name="ID_")
	    private String id ;
		@ManyToOne(cascade = 
		{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
		,fetch=FetchType.LAZY) 
		@JoinColumn(name="ACTION_ID_",nullable=false)
        private Action           action;
		@ManyToOne(cascade = 
		{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
		,fetch=FetchType.LAZY) 
		@JoinColumn(name="DEPTROLE_ID_",nullable=false)
        private DepartmentRole deptRole;
		
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
		public String getId() {
			return id;
		}
		public Action getAction() {
			return action;
		}
		public DepartmentRole getDeptRole() {
			return deptRole;
		}
		public void setId(String id) {
			this.id = id;
		}
		public void setAction(Action action) {
			this.action = action;
		}
		public void setDeptRole(DepartmentRole deptRole) {
			this.deptRole = deptRole;
		}
		
		
}
