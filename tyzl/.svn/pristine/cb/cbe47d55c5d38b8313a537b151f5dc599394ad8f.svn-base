
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.entity
 * 文件名：         DeployedWorkflowReject.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-5-25-下午05:49:29
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.jbpm.entity;

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

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;


 /**
 * 类名称：     DeployedWorkflowReject
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-5-25 下午05:49:29
 * 修改备注：
 * @version 1.0.0
 **/
@Entity
@Table(name="T_WORKFLOWDESIGNERS_REJECTS")
public class WorkflowDesignerReject 
{
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id ;
	@ManyToOne(cascade = 
	{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	,fetch=FetchType.LAZY) 
	@JoinColumn(name="SOURCE_DESIGNER_ID_",nullable=false)
    private JbpmWorkflowDesigner    sourceJbpmWorkflowDesigner;
	@ManyToOne(cascade = 
	{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	,fetch=FetchType.LAZY) 
	@JoinColumn(name="REJECT_DESIGNER_ID_",nullable=false)
    private JbpmWorkflowDesigner   rejectJbpmWorkflowDesigner;
	@Column(name="QUERY_CONDITIONS_",length=2000)
	private String queryConditions;
	@Column(name="KEYS_")
	private String keys;
	@Column(name="PARAMS_")
	private String params;
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
	public void setSourceJbpmWorkflowDesigner(JbpmWorkflowDesigner sourceJbpmWorkflowDesigner) {
		this.sourceJbpmWorkflowDesigner = sourceJbpmWorkflowDesigner;
	}
	public JbpmWorkflowDesigner getSourceJbpmWorkflowDesigner() {
		return sourceJbpmWorkflowDesigner;
	}
	public void setRejectJbpmWorkflowDesigner(JbpmWorkflowDesigner rejectJbpmWorkflowDesigner) {
		this.rejectJbpmWorkflowDesigner = rejectJbpmWorkflowDesigner;
	}
	public JbpmWorkflowDesigner getRejectJbpmWorkflowDesigner() {
		return rejectJbpmWorkflowDesigner;
	}
	public String getKeys() {
		return keys;
	}
	public String getParams() {
		return params;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setKeys(String keys) {
		this.keys = keys;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getQueryConditions() {
		return queryConditions;
	}
	public void setQueryConditions(String queryConditions) {
		this.queryConditions = queryConditions;
	}
	
}
