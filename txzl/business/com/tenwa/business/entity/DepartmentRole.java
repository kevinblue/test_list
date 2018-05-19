package com.tenwa.business.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.jbpm.entity.WorkflowStartDepartmentRole;
import com.tenwa.jbpm.entity.WorkflowViewDepartmentRole;
import com.tenwa.kernal.annotation.FieldName;

 /**
 * 项目名称：    系统名称
 * 包名：              
 * 文件名：         DeparmentRole.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-3-4-下午05:53:57
 * Copyright：2013XX公司-版权所有
 **/

/**
 * 类名称：     DeparmentRole
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-3-4 下午05:53:57
 * 修改备注：
 * @version 1.0.0
 **/
@Entity
@Table(name="T_DEPTS_ROLES")
public class DepartmentRole implements Serializable
{	
	private static final long serialVersionUID = -5971699582987669872L;
@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String     id   ;
    //部门
	@ManyToOne(cascade = 
	{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	,fetch=FetchType.LAZY) 
	@JoinColumn(name="DEPT_ID_",nullable=false)
    private Department dept ;
	//角色
	@ManyToOne(cascade = 
	{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	,fetch=FetchType.LAZY) 
    @JoinColumn(name="ROLE_ID_",nullable=false)
    private Role       role ; 
	
	@OneToMany(mappedBy="deptRole",fetch=FetchType.LAZY) 
	private Set<UserDepartmentRole> userDeptRoles = new HashSet<UserDepartmentRole>();
	@OneToMany(mappedBy="deptRole",fetch=FetchType.LAZY) 
	private Set<MenuDepartmentRole> menuDeptRoles = new HashSet<MenuDepartmentRole>();
	@OneToMany(mappedBy="deptRole",fetch=FetchType.LAZY) 
	private Set<ResourceDepartmentRole> resourceDeptRoles = new HashSet<ResourceDepartmentRole>();
	@OneToMany(mappedBy="deptRole",fetch=FetchType.LAZY) 
	private Set<ActionDepartmentRole> actionDeptRoles = new HashSet<ActionDepartmentRole>();
	@OneToMany(mappedBy="deptRole",fetch=FetchType.LAZY) 
	private Set<WorkflowStartDepartmentRole> workflowStartDeptRoles = new HashSet<WorkflowStartDepartmentRole>();
	@OneToMany(mappedBy="deptRole",fetch=FetchType.LAZY) 
	private Set<WorkflowViewDepartmentRole> workflowViewDeptRoles = new HashSet<WorkflowViewDepartmentRole>();
	
	public Set<WorkflowStartDepartmentRole> getWorkflowStartDeptRoles() {
		return workflowStartDeptRoles;
	}
	public Set<WorkflowViewDepartmentRole> getWorkflowViewDeptRoles() {
		return workflowViewDeptRoles;
	}
	public void setWorkflowStartDeptRoles(
			Set<WorkflowStartDepartmentRole> workflowStartDeptRoles) {
		this.workflowStartDeptRoles = workflowStartDeptRoles;
	}
	public void setWorkflowViewDeptRoles(
			Set<WorkflowViewDepartmentRole> workflowViewDeptRoles) {
		this.workflowViewDeptRoles = workflowViewDeptRoles;
	}
	public Set<ResourceDepartmentRole> getResourceDeptRoles() {
		return resourceDeptRoles;
	}
	public Set<ActionDepartmentRole> getActionDeptRoles() {
		return actionDeptRoles;
	}
	public void setResourceDeptRoles(Set<ResourceDepartmentRole> resourceDeptRoles) {
		this.resourceDeptRoles = resourceDeptRoles;
	}
	public void setActionDeptRoles(Set<ActionDepartmentRole> actionDeptRoles) {
		this.actionDeptRoles = actionDeptRoles;
	}
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
	public Department getDept() {
		return dept;
	}
	public Role getRole() {
		return role;
	}
	public Set<UserDepartmentRole> getUserDeptRoles() {
		return userDeptRoles;
	}
	public Set<MenuDepartmentRole> getMenuDeptRoles() {
		return menuDeptRoles;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public void setUserDeptRoles(Set<UserDepartmentRole> userDeptRoles) {
		this.userDeptRoles = userDeptRoles;
	}
	public void setMenuDeptRoles(Set<MenuDepartmentRole> menuDeptRoles) {
		this.menuDeptRoles = menuDeptRoles;
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
