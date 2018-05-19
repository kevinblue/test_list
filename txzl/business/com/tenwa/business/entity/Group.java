package com.tenwa.business.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
import org.json.JSONObject;

import com.tenwa.jbpm.entity.WorkflowStartGroup;
import com.tenwa.jbpm.entity.WorkflowViewDepartment;
import com.tenwa.jbpm.entity.WorkflowViewGroup;
import com.tenwa.kernal.annotation.FieldName;

 /**
 * 项目名称：    系统名称
 * 包名：              
 * 文件名：         Group.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-3-4-下午05:52:10
 * Copyright：2013XX公司-版权所有
 **/

/**
 * 类名称：     Group
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-3-4 下午05:52:10
 * 修改备注：
 * @version 1.0.0
 **/
@Entity
@Table(name="T_GROUPS")
public class Group implements Serializable{
	
	private static final long serialVersionUID = -8466098671746373479L;

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id ;
	
	@Column(nullable=false,name="NAME_",unique=true)
	private String name;
	@Column(nullable=false,name="CODE_",unique=true)
	private String code;
	@Column(name="DESCRIPTION_")
	private String description;
	@Column(name="Enabled_",nullable=false,length=1)
	private Boolean enabled;
	
	@OneToMany(mappedBy="group",fetch=FetchType.LAZY) 
	private Set<UserGroup> userGroups = new HashSet<UserGroup>();
	@OneToMany(mappedBy="group",fetch=FetchType.LAZY) 
	private Set<MenuGroup> menuGroups = new HashSet<MenuGroup>();
	@OneToMany(mappedBy="group",fetch=FetchType.LAZY) 
	private Set<ResourceGroup> resourceGroups = new HashSet<ResourceGroup>();
	@OneToMany(mappedBy="group",fetch=FetchType.LAZY) 
	private Set<ActionGroup> actionGroups = new HashSet<ActionGroup>();
	@OneToMany(mappedBy="group",fetch=FetchType.LAZY) 
	private Set<WorkflowStartGroup> workflowStartGroups = new HashSet<WorkflowStartGroup>();
	@OneToMany(mappedBy="group",fetch=FetchType.LAZY) 
	private Set<WorkflowViewGroup> workflowViewGroups = new HashSet<WorkflowViewGroup>();
	
	public boolean isGroupCheckedWorkflowView(Group group,String workflowViewId) throws Exception{
		boolean isChecked = false;
		for(WorkflowViewGroup workflowViewGroup : group.getWorkflowViewGroups()){
			String currentWorkflowViewId = workflowViewGroup.getWorkflowView().getId();
			if(workflowViewId.equals(currentWorkflowViewId)){
				isChecked = true;
				break;
			}
		}
		return isChecked;
	}
	
	public Set<WorkflowStartGroup> getWorkflowStartGroups() {
		return workflowStartGroups;
	}
	public Set<WorkflowViewGroup> getWorkflowViewGroups() {
		return workflowViewGroups;
	}
	public void setWorkflowStartGroups(Set<WorkflowStartGroup> workflowStartGroups) {
		this.workflowStartGroups = workflowStartGroups;
	}
	public void setWorkflowViewGroups(Set<WorkflowViewGroup> workflowViewGroups) {
		this.workflowViewGroups = workflowViewGroups;
	}
	public Set<ResourceGroup> getResourceGroups() {
		return resourceGroups;
	}
	public Set<ActionGroup> getActionGroups() {
		return actionGroups;
	}
	public void setResourceGroups(Set<ResourceGroup> resourceGroups) {
		this.resourceGroups = resourceGroups;
	}
	public void setActionGroups(Set<ActionGroup> actionGroups) {
		this.actionGroups = actionGroups;
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
	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}
	public Set<MenuGroup> getMenuGroups() {
		return menuGroups;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setUserGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}
	public void setMenuGroups(Set<MenuGroup> menuGroups) {
		this.menuGroups = menuGroups;
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
	public String getName() {
		return name;
	}
	public String getCode() {
		return code;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public JSONObject getJsonObjectGroup() throws Exception {
		String currentState = "opened";
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", getId());
		jsonObj.put("text", getName() + "（" + getCode() + "）");
		jsonObj.put("iconCls", "icon-contact");
		jsonObj.put("state", currentState);
		JSONObject attributesJsonObj = new JSONObject();
		attributesJsonObj.put("type", "group");
		attributesJsonObj.put("userEntityClassName", "UserGroup");
		attributesJsonObj.put("deptEntityClassName", "DepartmentGroup");
		attributesJsonObj.put("menuEntityClassName", "MenuGroup");
		attributesJsonObj.put("resourceEntityClassName", "ResourceGroup");
		attributesJsonObj.put("actionEntityClassName", "ActionGroup");
		attributesJsonObj.put("workflowStartEntityClassName", "WorkflowStartGroup");
		attributesJsonObj.put("workflowViewEntityClassName", "WorkflowViewGroup");
		attributesJsonObj.put("groupId", getId());
		jsonObj.put("attributes", attributesJsonObj);
		return jsonObj;
	}
}
