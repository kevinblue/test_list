package com.tenwa.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name="T_DATE_DEPTROLES")
public class DateDepartmentRole implements Serializable{
	
	private static final long serialVersionUID = -114948410102261865L;
		@Id
	    @GeneratedValue(generator = "paymentableGenerator")     
	    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
	    @Column(length=32,name="ID_")
	    private String id ;
		
		@OneToOne  
		@JoinColumn(name="DEPTROLE_ID_",nullable=false)
        private DepartmentRole deptRole;
			
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "MAY_VIEW_TYPE")
		@FieldName(name="可查看数据")
		private DictionaryData mayViewType;
		 
		
		@FieldName(name="可查看部门")
		@Column(name="VIEW_DEPT", length=2000)	
		private String viewDept;
		 
		
		@FieldName(name="可查看部门名称")
		@Column(name="VIEW_DEPT_NAME", length=2000)	
		private String viewDeptName;
		
		@FieldName(name="权限列")
		@Column(name="CONDTION_COLUMN")	
		private String condtionColumn;
		
		@FieldName(name="备注")
		@Column(name="MEMO")	
		private String memo;
		
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
		 
		public DepartmentRole getDeptRole() {
			return deptRole;
		}
		public void setId(String id) {
			this.id = id;
		}
		 
		public void setDeptRole(DepartmentRole deptRole) {
			this.deptRole = deptRole;
		}
		
		
		public String getCondtionColumn() {
			return condtionColumn;
		}
		public void setCondtionColumn(String condtionColumn) {
			this.condtionColumn = condtionColumn;
		}
		public DictionaryData getMayViewType() {
			return mayViewType;
		}
		public void setMayViewType(DictionaryData mayViewType) {
			this.mayViewType = mayViewType;
		}
		public String getMemo() {
			return memo;
		}
		public void setMemo(String memo) {
			this.memo = memo;
		}
		public String getViewDept() {
			return viewDept;
		}
		public void setViewDept(String viewDept) {
			this.viewDept = viewDept;
		}
		public String getViewDeptName() {
			return viewDeptName;
		}
		public void setViewDeptName(String viewDeptName) {
			this.viewDeptName = viewDeptName;
		}
		
		
}
