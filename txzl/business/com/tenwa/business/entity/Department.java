package com.tenwa.business.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.json.JSONArray;
import org.json.JSONObject;

import com.tenwa.jbpm.entity.UserWorkflowStart;
import com.tenwa.jbpm.entity.UserWorkflowView;
import com.tenwa.jbpm.entity.WorkflowStartDepartment;
import com.tenwa.jbpm.entity.WorkflowStartDepartmentRole;
import com.tenwa.jbpm.entity.WorkflowViewDepartment;
import com.tenwa.jbpm.entity.WorkflowViewDepartmentRole;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.kernal.utils.StringUtil;

 /**
 * 项目名称：    系统名称
 * 包名：              
 * 文件名：         Department.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-3-4-下午05:51:52
 * Copyright：2013XX公司-版权所有
 **/

/**
 * 类名称：     Department
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-3-4 下午05:51:52
 * 修改备注：
 * @version 1.0.0
 **/
@Entity
@Table(name="T_DEPTS")
public class Department implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id ;
	
	@Column(name="NAME_",nullable=false,unique=true) 
	private String name;
	@Column(name="CODE_",nullable=false,unique=true) 
	private String code;
	@Column(name="DESCRIPTION_") 
	private String description; 
	@Column(name="POSITION_",nullable=false)
	private int position; 
	@Column(name="PROP_ONE_")
	private String propOne; 
	@Column(name="PROP_TWO_")
	private String propTwo; 
	@Column(name="PROP_THREE_")
	private String propThree; 
	@Column(name="PROP_FOUR_")
	private String propFour; 
	@Column(name="PROP_Five_")
	private String propFive; 
	
	@ManyToOne(cascade = 
	{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	,fetch=FetchType.LAZY) 
	@JoinColumn(name="PID_")
	private Department parentDept;
	
	@OneToMany(mappedBy="parentDept",fetch = FetchType.LAZY)
	@OrderBy(value = "position ASC")
	private Set<Department> childrenDepts = new HashSet<Department>();
	
	
//	@OneToMany(mappedBy="dealerDept",fetch = FetchType.LAZY)
//	private Set<DealerDeptInfo> dealerDeptInfos = new HashSet<DealerDeptInfo>();
	
	@ManyToOne(targetEntity=User.class,cascade = 
	{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	,fetch=FetchType.LAZY) 
	@JoinColumn(name="MANAGER_USER_ID_",nullable=true)
	private User manager;
	
	@OneToMany(mappedBy="dept",fetch=FetchType.LAZY) 
	@OrderBy(value = "user ASC")
	private Set<UserDepartment> userDepts = new HashSet<UserDepartment>();
	@OneToMany(mappedBy="dept",fetch=FetchType.LAZY) 
	@OrderBy(value = "role ASC")
	private Set<DepartmentRole> deptRoles = new HashSet<DepartmentRole>();
	@OneToMany(mappedBy="dept",fetch=FetchType.LAZY) 
	private Set<MenuDepartment> menuDepts = new HashSet<MenuDepartment>();
	@OneToMany(mappedBy="dept",fetch=FetchType.LAZY) 
	private Set<ResourceDepartment> resourceDepts = new HashSet<ResourceDepartment>();
	@OneToMany(mappedBy="dept",fetch=FetchType.LAZY) 
	private Set<ActionDepartment> actionDepts = new HashSet<ActionDepartment>();
	@OneToMany(mappedBy="dept",fetch=FetchType.LAZY) 
	private Set<WorkflowStartDepartment> workflowStartDepts = new HashSet<WorkflowStartDepartment>();
	@OneToMany(mappedBy="dept",fetch=FetchType.LAZY) 
	private Set<WorkflowViewDepartment> workflowViewDepts = new HashSet<WorkflowViewDepartment>();
	
	public Set<WorkflowStartDepartment> getWorkflowStartDepts() {
		return workflowStartDepts;
	}
	public Set<WorkflowViewDepartment> getWorkflowViewDepts() {
		return workflowViewDepts;
	}
	public void setWorkflowStartDepts(
			Set<WorkflowStartDepartment> workflowStartDepts) {
		this.workflowStartDepts = workflowStartDepts;
	}
	public void setWorkflowViewDepts(Set<WorkflowViewDepartment> workflowViewDepts) {
		this.workflowViewDepts = workflowViewDepts;
	}
	public Set<ResourceDepartment> getResourceDepts() {
		return resourceDepts;
	}
	public Set<ActionDepartment> getActionDepts() {
		return actionDepts;
	}
	public void setResourceDepts(Set<ResourceDepartment> resourceDepts) {
		this.resourceDepts = resourceDepts;
	}
	public void setActionDepts(Set<ActionDepartment> actionDepts) {
		this.actionDepts = actionDepts;
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
	
	@Transient
	private Map<String,String> attributes = null;
	
	public String getId() {
		return id;
	}
	public Set<UserDepartment> getUserDepts() {
		return userDepts;
	}
	public Set<DepartmentRole> getDeptRoles() {
		return deptRoles;
	}
	public Set<MenuDepartment> getMenuDepts() {
		return menuDepts;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setUserDepts(Set<UserDepartment> userDepts) {
		this.userDepts = userDepts;
	}
	public void setDeptRoles(Set<DepartmentRole> deptRoles) {
		this.deptRoles = deptRoles;
	}
	public void setMenuDepts(Set<MenuDepartment> menuDepts) {
		this.menuDepts = menuDepts;
	}
	public String getName() {
		return name;
	}
	public String getCode() {
		return code;
	}
	public String getDescription() {
		return description;
	}
	public int getPosition() {
		return position;
	}
	public Department getParentDept() {
		return parentDept;
	}
	public Set<Department> getChildrenDepts() {
		return childrenDepts;
	}
	public User getManager() {
		return manager;
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
	public void setName(String name) {
		this.name = name;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public void setParentDept(Department parentDept) {
		this.parentDept = parentDept;
	}
	public void setChildrenDepts(Set<Department> childrenDepts) {
		this.childrenDepts = childrenDepts;
	}
	public void setManager(User manager) {
		this.manager = manager;
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

	public String getPropOne() {
		return propOne;
	}
	public void setPropOne(String propOne) {
		this.propOne = propOne;
	}
	public String getPropTwo() {
		return propTwo;
	}
	public void setPropTwo(String propTwo) {
		this.propTwo = propTwo;
	}
	public String getPropThree() {
		return propThree;
	}
	public void setPropThree(String propThree) {
		this.propThree = propThree;
	}
	public String getPropFour() {
		return propFour;
	}
	public void setPropFour(String propFour) {
		this.propFour = propFour;
	}
	public String getPropFive() {
		return propFive;
	}
	public void setPropFive(String propFive) {
		this.propFive = propFive;
	}
	public Map<String, String> getAttributes() {
		Map<String,String> nodeAttributes = new HashMap<String,String>();
		String pid = this.getParentDept() == null?"-1":this.getParentDept().getId();
		nodeAttributes.put("pid", pid);
		nodeAttributes.put("code", this.getCode());
		nodeAttributes.put("position", StringUtil.nullToString(this.getPosition()));
		nodeAttributes.put("description", StringUtil.getJsonString(this.getDescription()));
		nodeAttributes.put("type", "dept");
		nodeAttributes.put("entityClassName", "MenuDepartment");
		nodeAttributes.put("managerUserName", "");
		nodeAttributes.put("managerUserId", "");
		nodeAttributes.put("managerUserRealName", "");
		
		if(null != this.getManager()){
			nodeAttributes.put("managerUserName", this.getManager().getUsername());
			nodeAttributes.put("managerUserId", this.getManager().getId());
			nodeAttributes.put("managerUserRealName", this.getManager().getRealname());
		}
		if(null != this.attributes){
			nodeAttributes.putAll(this.attributes);
		}
		return nodeAttributes;
	}
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	public boolean isHasChildren()
	{
		return this.childrenDepts.size()>=1;
	}
	public boolean isDeptCheckedMenu(Department dept,String menuId) throws Exception{
		boolean isChecked = false;
		for(MenuDepartment menuDept : dept.getMenuDepts()){
			String currentMenuId = menuDept.getMenu().getId();
			if(menuId.equals(currentMenuId)){
				isChecked = true;
				break;
			}
		}
		return isChecked;
	}
	public boolean isDeptCheckedResource(Department dept,String resourceId) throws Exception{
		boolean isChecked = false;
		for(ResourceDepartment resourceDept : dept.getResourceDepts()){
			String currentResourceId = resourceDept.getResource().getId();
			if(resourceId.equals(currentResourceId)){
				isChecked = true;
				break;
			}
		}
		return isChecked;
	}
	public boolean isDeptCheckedAction(Department dept,String actionId) throws Exception{
		boolean isChecked = false;
		for(ActionDepartment actipnDept : dept.getActionDepts()){
			String currentActionId = actipnDept.getAction().getId();
			if(actionId.equals(currentActionId)){
				isChecked = true;
				break;
			}
		}
		return isChecked;
	}
	public boolean isDeptCheckedWorkflowStart(Department dept,String workflowStartId) throws Exception{
		boolean isChecked = false;
		for(WorkflowStartDepartment workflowStartDept : dept.getWorkflowStartDepts()){
			String currentWorkflowStartId = workflowStartDept.getWorkflowStart().getId();
			if(workflowStartId.equals(currentWorkflowStartId)){
				isChecked = true;
				break;
			}
		}
		return isChecked;
	}
	public boolean isDeptCheckedWorkflowView(Department dept,String workflowViewId) throws Exception{
		boolean isChecked = false;
		for(WorkflowViewDepartment workflowViewDept : dept.getWorkflowViewDepts()){
			String currentWorkflowViewId = workflowViewDept.getWorkflowView().getId();
			if(workflowViewId.equals(currentWorkflowViewId)){
				isChecked = true;
				break;
			}
		}
		return isChecked;
	}
	public boolean isDeptCheckedUser(Department dept,String userId) throws Exception{
		boolean isChecked = false;
		for(UserDepartment userDept : dept.getUserDepts()){
			String currentUserId = userDept.getUser().getId();
			if(userId.equals(currentUserId)){
				isChecked = true;
				break;
			}
		}
		return isChecked;
	}
	public boolean isDeptRoleCheckedMenu(DepartmentRole deptRole,String menuId) throws Exception{
		boolean isChecked = false;
		for(MenuDepartmentRole menuDeptRole : deptRole.getMenuDeptRoles()){
			String currentMenuId = menuDeptRole.getMenu().getId();
			if(menuId.equals(currentMenuId)){
				isChecked = true;
				break;
			}
		}
		return isChecked;
	}
	public boolean isDeptRoleCheckedResource(DepartmentRole deptRole,String resourceId) throws Exception{
		boolean isChecked = false;
		for(ResourceDepartmentRole menuDeptRole : deptRole.getResourceDeptRoles()){
			String currentResourceId = menuDeptRole.getResource().getId();
			if(resourceId.equals(currentResourceId)){
				isChecked = true;
				break;
			}
		}
		return isChecked;
	}
	public boolean isDeptRoleCheckedAction(DepartmentRole deptRole,String actionId) throws Exception{
		boolean isChecked = false;
		for(ActionDepartmentRole menuDeptRole : deptRole.getActionDeptRoles()){
			String currentActionId = menuDeptRole.getAction().getId();
			if(actionId.equals(currentActionId)){
				isChecked = true;
				break;
			}
		}
		return isChecked;
	}
	public boolean isDeptRoleCheckedWorkflowStart(DepartmentRole deptRole,String workflowStartId) throws Exception{
		boolean isChecked = false;
		for(WorkflowStartDepartmentRole menuDeptRole : deptRole.getWorkflowStartDeptRoles()){
			String currentWorkflowStartId= menuDeptRole.getWorkflowStart().getId();
			if(workflowStartId.equals(currentWorkflowStartId)){
				isChecked = true;
				break;
			}
		}
		return isChecked;
	}
	public boolean isDeptRoleCheckedWorkflowView(DepartmentRole deptRole,String workflowViewId) throws Exception{
		boolean isChecked = false;
		for(WorkflowViewDepartmentRole menuDeptRole : deptRole.getWorkflowViewDeptRoles()){
			String currentWorkflowViewId= menuDeptRole.getWorkflowView().getId();
			if(workflowViewId.equals(currentWorkflowViewId)){
				isChecked = true;
				break;
			}
		}
		return isChecked;
	}
	public boolean isDeptRoleCheckedUser(DepartmentRole deptRole,String userId) throws Exception{
		boolean isChecked = false;
		for(UserDepartmentRole userDeptRole : deptRole.getUserDeptRoles()){
			String currentUserId = userDeptRole.getUser().getId();
			if(userId.equals(currentUserId)){
				isChecked = true;
				break;
			}
		}
		return isChecked;
	}
	public boolean isUserCheckedMenu(User user,String menuId) throws Exception{
		boolean isChecked = false;
		for(UserMenu userMenu : user.getUserMenus()){
			String currentMenuId = userMenu.getMenu().getId();
			if(menuId.equals(currentMenuId)){
				isChecked = true;
				break;
			}
		}
		return isChecked;
	}
	public boolean isUserCheckedResource(User user,String resourceId) throws Exception{
		boolean isChecked = false;
		for(UserResource userResource : user.getUserResources()){
			String currentResourceId = userResource.getResource().getId();
			if(resourceId.equals(currentResourceId)){
				isChecked = true;
				break;
			}
		}
		return isChecked;
	}
	public boolean isUserCheckedAction(User user,String actionId) throws Exception{
		boolean isChecked = false;
		for(UserAction userAction : user.getUserActions()){
			String currentActionId = userAction.getAction().getId();
			if(actionId.equals(currentActionId)){
				isChecked = true;
				break;
			}
		}
		return isChecked;
	}
	public boolean isUserCheckedWorkflowStart(User user,String workflowStartId) throws Exception{
		boolean isChecked = false;
		for(UserWorkflowStart userWorkflowStart : user.getUserWorkflowStarts()){
			String currentWorkflowStartId = userWorkflowStart.getWorkflowStart().getId();
			if(workflowStartId.equals(currentWorkflowStartId)){
				isChecked = true;
				break;
			}
		}
		return isChecked;
	}
	public boolean isUserCheckedWorkflowView(User user,String workflowViewId) throws Exception{
		boolean isChecked = false;
		for(UserWorkflowView userWorkflowView : user.getUserWorkflowViews()){
			String currentWorkflowViewId = userWorkflowView.getWorkflowView().getId();
			if(workflowViewId.equals(currentWorkflowViewId)){
				isChecked = true;
				break;
			}
		}
		return isChecked;
	}
	
	public JSONArray getChildrenWithAllMenuJsonArray(boolean isInit,String menuId,String state) throws Exception{
		JSONArray jsonArray = new JSONArray();
		if(isInit){
			JSONObject deptJsonObj = this.getJsonObjectDept(this,isInit,null);
			deptJsonObj.put("children", this.getChildrenWithAllMenuJsonArray(!isInit,menuId,state));
			if(null!=menuId){
				deptJsonObj.put("checked", this.isDeptCheckedMenu(this, menuId));
			}
			
			jsonArray.put(deptJsonObj);
		}
		else
		{
			for(Department dept : this.getChildrenDepts()){
				JSONObject deptJsonObj = this.getJsonObjectDept(dept,isInit,null);
				if(null!=menuId){
					deptJsonObj.put("checked", this.isDeptCheckedMenu(dept, menuId));
				}
				jsonArray.put(deptJsonObj);
			}
			for(DepartmentRole deptRole : this.getDeptRoles()){
				JSONObject roleJsonObj =this.getJsonObjectRole(deptRole);
				if(null != state){
					roleJsonObj.put("state", state);
				}
				if(null!=menuId){
					roleJsonObj.put("checked", this.isDeptRoleCheckedMenu(deptRole, menuId));
				}
				jsonArray.put(roleJsonObj);
			}
			for(UserDepartment userDept : this.getUserDepts()){
				JSONObject userJsonObj =this.getJsonObjectUser(userDept);
				if(null != state){
					userJsonObj.put("state", state);
				}
				if(null!=menuId){
					userJsonObj.put("checked", this.isUserCheckedMenu(userDept.getUser(), menuId));
				}
				jsonArray.put(userJsonObj);
			}
		}
		return jsonArray;
	}
	public JSONArray getChildrenWithAllResourceJsonArray(boolean isInit,String resourceId,String state) throws Exception{
		JSONArray jsonArray = new JSONArray();
		if(isInit){
			JSONObject deptJsonObj = this.getJsonObjectDept(this,isInit,null);
			deptJsonObj.put("children", this.getChildrenWithAllResourceJsonArray(!isInit,resourceId,state));
			if(null!=resourceId){
				deptJsonObj.put("checked", this.isDeptCheckedResource(this, resourceId));
			}
			
			jsonArray.put(deptJsonObj);
		}
		else
		{
			for(Department dept : this.getChildrenDepts()){
				JSONObject deptJsonObj = this.getJsonObjectDept(dept,isInit,null);
				if(null!=resourceId){
					deptJsonObj.put("checked", this.isDeptCheckedResource(dept, resourceId));
				}
				jsonArray.put(deptJsonObj);
			}
			for(DepartmentRole deptRole : this.getDeptRoles()){
				JSONObject roleJsonObj =this.getJsonObjectRole(deptRole);
				if(null != state){
					roleJsonObj.put("state", state);
				}
				if(null!=resourceId){
					roleJsonObj.put("checked", this.isDeptRoleCheckedResource(deptRole, resourceId));
				}
				jsonArray.put(roleJsonObj);
			}
			for(UserDepartment userDept : this.getUserDepts()){
				JSONObject userJsonObj =this.getJsonObjectUser(userDept);
				if(null != state){
					userJsonObj.put("state", state);
				}
				if(null!=resourceId){
					userJsonObj.put("checked", this.isUserCheckedResource(userDept.getUser(), resourceId));
				}
				jsonArray.put(userJsonObj);
			}
		}
		return jsonArray;
	}
	public JSONArray getChildrenWithAllActionJsonArray(boolean isInit,String actionId,String state) throws Exception{
		JSONArray jsonArray = new JSONArray();
		if(isInit){
			JSONObject deptJsonObj = this.getJsonObjectDept(this,isInit,null);
			deptJsonObj.put("children", this.getChildrenWithAllActionJsonArray(!isInit,actionId,state));
			if(null!=actionId){
				deptJsonObj.put("checked", this.isDeptCheckedAction(this, actionId));
			}
			
			jsonArray.put(deptJsonObj);
		}
		else
		{
			for(Department dept : this.getChildrenDepts()){
				JSONObject deptJsonObj = this.getJsonObjectDept(dept,isInit,null);
				if(null!=actionId){
					deptJsonObj.put("checked", this.isDeptCheckedAction(dept, actionId));
				}
				jsonArray.put(deptJsonObj);
			}
			for(DepartmentRole deptRole : this.getDeptRoles()){
				JSONObject roleJsonObj =this.getJsonObjectRole(deptRole);
				if(null != state){
					roleJsonObj.put("state", state);
				}
				if(null!=actionId){
					roleJsonObj.put("checked", this.isDeptRoleCheckedAction(deptRole, actionId));
				}
				jsonArray.put(roleJsonObj);
			}
			for(UserDepartment userDept : this.getUserDepts()){
				JSONObject userJsonObj =this.getJsonObjectUser(userDept);
				if(null != state){
					userJsonObj.put("state", state);
				}
				if(null!=actionId){
					userJsonObj.put("checked", this.isUserCheckedAction(userDept.getUser(), actionId));
				}
				jsonArray.put(userJsonObj);
			}
		}
		return jsonArray;
	}
	public JSONArray getChildrenWithAllWorkflowStartJsonArray(boolean isInit,String workflowStartId,String state) throws Exception{
		JSONArray jsonArray = new JSONArray();
		if(isInit){
			JSONObject deptJsonObj = this.getJsonObjectDept(this,isInit,null);
			deptJsonObj.put("children", this.getChildrenWithAllWorkflowStartJsonArray(!isInit,workflowStartId,state));
			if(null!=workflowStartId){
				deptJsonObj.put("checked", this.isDeptCheckedWorkflowStart(this, workflowStartId));
			}
			
			jsonArray.put(deptJsonObj);
		}
		else
		{
			for(Department dept : this.getChildrenDepts()){
				JSONObject deptJsonObj = this.getJsonObjectDept(dept,isInit,null);
				if(null!=workflowStartId){
					deptJsonObj.put("checked", this.isDeptCheckedWorkflowStart(dept, workflowStartId));
				}
				jsonArray.put(deptJsonObj);
			}
			for(DepartmentRole deptRole : this.getDeptRoles()){
				JSONObject roleJsonObj =this.getJsonObjectRole(deptRole);
				if(null != state){
					roleJsonObj.put("state", state);
				}
				if(null!=workflowStartId){
					roleJsonObj.put("checked", this.isDeptRoleCheckedWorkflowStart(deptRole, workflowStartId));
				}
				jsonArray.put(roleJsonObj);
			}
			for(UserDepartment userDept : this.getUserDepts()){
				JSONObject userJsonObj =this.getJsonObjectUser(userDept);
				if(null != state){
					userJsonObj.put("state", state);
				}
				if(null!=workflowStartId){
					userJsonObj.put("checked", this.isUserCheckedWorkflowStart(userDept.getUser(), workflowStartId));
				}
				jsonArray.put(userJsonObj);
			}
		}
		return jsonArray;
	}
	public JSONArray getChildrenWithAllWorkflowViewJsonArray(boolean isInit,String workflowViewId,String state) throws Exception{
		JSONArray jsonArray = new JSONArray();
		if(isInit){
			JSONObject deptJsonObj = this.getJsonObjectDept(this,isInit,null);
			deptJsonObj.put("children", this.getChildrenWithAllWorkflowViewJsonArray(!isInit,workflowViewId,state));
			if(null!=workflowViewId){
				deptJsonObj.put("checked", this.isDeptCheckedWorkflowView(this, workflowViewId));
			}
			
			jsonArray.put(deptJsonObj);
		}
		else
		{
			for(Department dept : this.getChildrenDepts()){
				JSONObject deptJsonObj = this.getJsonObjectDept(dept,isInit,null);
				if(null!=workflowViewId){
					deptJsonObj.put("checked", this.isDeptCheckedWorkflowView(dept, workflowViewId));
				}
				jsonArray.put(deptJsonObj);
			}
			for(DepartmentRole deptRole : this.getDeptRoles()){
				JSONObject roleJsonObj =this.getJsonObjectRole(deptRole);
				if(null != state){
					roleJsonObj.put("state", state);
				}
				if(null!=workflowViewId){
					roleJsonObj.put("checked", this.isDeptRoleCheckedWorkflowView(deptRole, workflowViewId));
				}
				jsonArray.put(roleJsonObj);
			}
			for(UserDepartment userDept : this.getUserDepts()){
				JSONObject userJsonObj =this.getJsonObjectUser(userDept);
				if(null != state){
					userJsonObj.put("state", state);
				}
				if(null!=workflowViewId){
					userJsonObj.put("checked", this.isUserCheckedWorkflowView(userDept.getUser(), workflowViewId));
				}
				jsonArray.put(userJsonObj);
			}
		}
		return jsonArray;
	}
	public JSONArray getChildrenWithAllUserJsonArray(boolean isInit,String userId,String state) throws Exception{
		JSONArray jsonArray = new JSONArray();
		if(isInit){
			JSONObject deptJsonObj = this.getJsonObjectDept(this,isInit,null);
			deptJsonObj.put("children", this.getChildrenWithAllUserJsonArray(!isInit,userId,state));
			if(null!=userId){
				deptJsonObj.put("checked", this.isDeptCheckedUser(this, userId));
			}
			
			jsonArray.put(deptJsonObj);
		}
		else
		{
			for(Department dept : this.getChildrenDepts()){
				JSONObject deptJsonObj = this.getJsonObjectDept(dept,isInit,null);
				if(null!=userId){
					deptJsonObj.put("checked", this.isDeptCheckedUser(dept, userId));
				}
				jsonArray.put(deptJsonObj);
			}
			for(DepartmentRole deptRole : this.getDeptRoles()){
				JSONObject roleJsonObj =this.getJsonObjectRole(deptRole);
				if(null != state){
					roleJsonObj.put("state", state);
				}
				if(null!=userId){
					roleJsonObj.put("checked", this.isDeptRoleCheckedUser(deptRole, userId));
				}
				jsonArray.put(roleJsonObj);
			}
		}
		return jsonArray;
	}

	public JSONArray getChildrenWithUsersJsonArray(boolean isInit,String state,String selectedUserIds) throws Exception{
		JSONArray jsonArray = new JSONArray();
		if(isInit){
			JSONObject deptJsonObj = this.getJsonObjectDept(this,isInit,null);
			deptJsonObj.put("children", this.getChildrenWithUsersJsonArray(!isInit,state,selectedUserIds));
			jsonArray.put(deptJsonObj);
		}
		else
		{
			for(Department dept : this.getChildrenDepts()){
				JSONObject deptJsonObj = this.getJsonObjectDept(dept,isInit,null);
				jsonArray.put(deptJsonObj);
			}
			for(UserDepartment userDept : this.getUserDepts()){
				String currentUserId = userDept.getUser().getId();
				boolean isPassed = true;
				if(null != selectedUserIds){
					for(String selectedUserId : selectedUserIds.split(",")){
						if(currentUserId.equals(selectedUserId)){
							isPassed = false;
						}
					}
				}
				
				if(isPassed){
					JSONObject userJsonObj =this.getJsonObjectUser(userDept);
					if(null != state){
						userJsonObj.put("state", state);
					}
					jsonArray.put(userJsonObj);
				}
			}
		}
		return jsonArray;
	}
	public JSONArray getChildrenWithRolesJsonArray(boolean isInit,String selectedUserIds) throws Exception{
		JSONArray jsonArray = new JSONArray();
		if(isInit){
			JSONObject deptJsonObj = this.getJsonObjectDept(this,isInit,null);
			deptJsonObj.put("children", this.getChildrenWithRolesJsonArray(!isInit,selectedUserIds));
			jsonArray.put(deptJsonObj);
		}
		else
		{
			for(Department dept : this.getChildrenDepts()){
				JSONObject deptJsonObj = this.getJsonObjectDept(dept,isInit,null);
				jsonArray.put(deptJsonObj);
			}
			for(DepartmentRole deptRole : this.getDeptRoles()){
				String currentUserId = deptRole.getId();
				boolean isPassed = true;
				if(null != selectedUserIds){
					for(String selectedUserId : selectedUserIds.split(",")){
						if(currentUserId.equals(selectedUserId)){
							isPassed = false;
						}
					}
				}
				
				if(isPassed){
					JSONObject userJsonObj =this.getJsonObjectRole(deptRole);
					jsonArray.put(userJsonObj);
				}
			}
		}
		return jsonArray;
	}
	public JSONArray getChildrenJsonArray(boolean isInit) throws Exception{
		JSONArray jsonArray = new JSONArray();
		if(isInit){
			JSONObject deptJsonObj = this.getJsonObjectDept(this,isInit,null);
			deptJsonObj.put("children", this.getChildrenJsonArray(!isInit));
			jsonArray.put(deptJsonObj);
		}
		else
		{
			for(Department dept : this.getChildrenDepts()){
				String state = dept.isHasChildren() ? "closed" : "opened";
				JSONObject deptJsonObj = this.getJsonObjectDept(dept,isInit,state);
				jsonArray.put(deptJsonObj);
			}
		}
		return jsonArray;
	}
	public JSONObject getJsonObjectDept(Department dept,boolean isInit,String state) throws Exception{
		String currentState = isInit ? "opened" : "closed";//(!isInit && dept.isHasChildren()) ? "closed" : "opened";
//		if( (0 == dept.getUserDepts().size())&&( 0 == dept.getChildrenDepts().size() ) ){
//			currentState = "opened";
//		}
		if(null != state){
			currentState = state;
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", dept.getId());
		jsonObj.put("text", dept.getName()+"（"+dept.getCode()+"）");
		jsonObj.put("iconCls", "icon-home");
		jsonObj.put("state", currentState);
		JSONObject atrributesJsonObj = new JSONObject(this.getArributesJsonString(dept));
		atrributesJsonObj.put("deptId", dept.getId());
		atrributesJsonObj.put("type", "dept");
		atrributesJsonObj.put("userEntityClassName","UserDepartment");
		atrributesJsonObj.put("deptEntityClassName","Department");
		atrributesJsonObj.put("menuEntityClassName","MenuDepartment");
		atrributesJsonObj.put("resourceEntityClassName","ResourceDepartment");
		atrributesJsonObj.put("actionEntityClassName","ActionDepartment");
		atrributesJsonObj.put("workflowStartEntityClassName","WorkflowStartDepartment");
		atrributesJsonObj.put("workflowViewEntityClassName","WorkflowViewDepartment");
		atrributesJsonObj.put("propOne",StringUtil.nullToString(dept.getPropOne()));
		atrributesJsonObj.put("propTwo",StringUtil.nullToString(dept.getPropTwo()));
		atrributesJsonObj.put("propThree",StringUtil.nullToString(dept.getPropThree()));
		atrributesJsonObj.put("propFour",StringUtil.nullToString(dept.getPropFour()));
		atrributesJsonObj.put("propFive",StringUtil.nullToString(dept.getPropFive()));
		jsonObj.put("attributes",atrributesJsonObj);
		return jsonObj;
	}
	public JSONObject getJsonObjectUser(UserDepartment userDept) throws Exception{
		String currentState =  "opened";
		User user = userDept.getUser();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", user.getId());
		jsonObj.put("text", user.getRealname()+"（"+user.getUsername()+"）");
		jsonObj.put("iconCls", "icon-user");
		jsonObj.put("state", currentState);
		JSONObject atrributesJsonObj = new JSONObject();
		atrributesJsonObj.put("type", "user");
		atrributesJsonObj.put("userEntityClassName","User");
		atrributesJsonObj.put("deptEntityClassName","UserDepartment");
		atrributesJsonObj.put("menuEntityClassName","UserMenu");
		atrributesJsonObj.put("resourceEntityClassName","UserResource");
		atrributesJsonObj.put("actionEntityClassName","UserAction");
		atrributesJsonObj.put("workflowStartEntityClassName","UserWorkflowStart");
		atrributesJsonObj.put("workflowViewEntityClassName","UserWorkflowView");
		atrributesJsonObj.put("userDeptId", userDept.getId());
		atrributesJsonObj.put("deptId", userDept.getDept().getId());
		atrributesJsonObj.put("userId", user.getId());
		jsonObj.put("attributes", atrributesJsonObj);
		return jsonObj;
	}
	public JSONObject getJsonObjectRole(DepartmentRole deptRole) throws Exception{
		String currentState =  "opened";
		Role role = deptRole.getRole();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", deptRole.getId());
		jsonObj.put("text", role.getName()+"（"+role.getCode()+"）");
		jsonObj.put("iconCls", "icon-contact");
		jsonObj.put("state", currentState);
		JSONObject atrributesJsonObj = new JSONObject();
		atrributesJsonObj.put("type", "deptRole");
		atrributesJsonObj.put("userEntityClassName","UserDepartmentRole");
		atrributesJsonObj.put("deptEntityClassName","DepartmentRole");
		atrributesJsonObj.put("menuEntityClassName","MenuDepartmentRole");
		atrributesJsonObj.put("resourceEntityClassName","ResourceDepartmentRole");
		atrributesJsonObj.put("actionEntityClassName","ActionDepartmentRole");
		atrributesJsonObj.put("workflowStartEntityClassName","WorkflowStartDepartmentRole");
		atrributesJsonObj.put("workflowViewEntityClassName","WorkflowViewDepartmentRole");
		atrributesJsonObj.put("deptRoleId", deptRole.getId());
		atrributesJsonObj.put("deptId", deptRole.getDept().getId());
		atrributesJsonObj.put("roleId", role.getId());
		jsonObj.put("attributes", atrributesJsonObj);
		return jsonObj;
	}
	public String getArributesJsonString(Department node)
	{
		Map<String,String> nodeAttributes = node.getAttributes();

		StringBuffer  str_sb = new StringBuffer("{");
		int index= 0;
		if(null != nodeAttributes)
		{
			
			for(String key:nodeAttributes.keySet())
			{
				String value = StringUtil.nullToString(nodeAttributes.get(key));
				if( 1 != ++index)
				{
					str_sb.append(",");
				}
				str_sb.append("\"")
				      .append(key)
					  .append("\":\"")
					  .append(StringUtil.getJsonString(value))
					  .append("\"");
				
			}
		}
		str_sb.append("}");
		return str_sb.toString();
	}
	public void toJsonString(Department node,StringBuffer str_sb)
	{
		String pid = node.getAttributes().get("pid");
		String description = node.getAttributes().get("description");
		String position = node.getAttributes().get("position");
		//node.getParentDept() == null?"0":node.getParentDept().getId();
		str_sb.append("{");
		str_sb.append("\"id\":"+"\""+StringUtil.nullToString(node.getId())+"\"");
		str_sb.append(",\"pid\":"+"\""+pid+"\"");
		str_sb.append(",\"name\":"+"\""+StringUtil.nullToString(node.getName())+"\"");
		str_sb.append(",\"description\":"+"\""+description+"\"");
		str_sb.append(",\"position\":"+"\""+position+"\"");
		str_sb.append(",\"authorities\":\"ROLE_USER\"");
		str_sb.append(",\"attributes\":"+this.getArributesJsonString(node));
		str_sb.append(",\"children\":[");
		if(node.isHasChildren())
		{
			Set<Department> childrenNodes = node.getChildrenDepts();
			int index= 0;
			for(Department mn : childrenNodes)
			{
		    	if(0 < index++)
		    	{
		    		str_sb.append(",");
		    	}
		    	toJsonString(mn,str_sb);
			}
		}
		str_sb.append("]");
		str_sb.append("}");
	}
	public String toString()
	{
		StringBuffer str_sb = new StringBuffer("");
		toJsonString(this,str_sb);
		return str_sb.toString();
	}
//	public void setDealerDeptInfos(Set<DealerDeptInfo> dealerDeptInfos) {
//		this.dealerDeptInfos = dealerDeptInfos;
//	}
//	public Set<DealerDeptInfo> getDealerDeptInfos() {
//		return dealerDeptInfos;
//	}
	//////修改新的全局样式配置
		public JSONArray getChildrenWithAllMenuJsonArray(String menuId) throws Exception{
		JSONArray jsonArray = new JSONArray();
		JSONObject deptJsonObj = this.getJsonObjectDept(this,true,"open");
		deptJsonObj.put("children", new JSONArray());
		this.getChildrenWithAllMenuJsonArrayRecursion(menuId,deptJsonObj);
		if(null!=menuId){
			deptJsonObj.put("checked", this.isDeptCheckedMenu(this, menuId));
		}
		jsonArray.put(deptJsonObj);
		return jsonArray;
	}
	public void getChildrenWithAllMenuJsonArrayRecursion(String menuId,JSONObject parentDeptJsonObj) throws Exception{
	{
		    JSONArray jsonArray = parentDeptJsonObj.getJSONArray("children");
			for(Department dept : this.getChildrenDepts()){
				//JSONObject 
				JSONObject deptJsonObj  = this.getJsonObjectDept(dept,false,null);
				if(null!=menuId){
					deptJsonObj.put("checked", this.isDeptCheckedMenu(dept, menuId));
				}
				deptJsonObj.put("children", new JSONArray());
				dept.getChildrenWithAllMenuJsonArrayRecursion(menuId,deptJsonObj);
				jsonArray.put(deptJsonObj);
			}
			String state = null;
			for(DepartmentRole deptRole : this.getDeptRoles()){
				JSONObject roleJsonObj =this.getJsonObjectRole(deptRole);
				if(null != state){
					roleJsonObj.put("state", state);
				}
				if(null!=menuId){
					roleJsonObj.put("checked", this.isDeptRoleCheckedMenu(deptRole, menuId));
				}
				jsonArray.put(roleJsonObj);
			}
			for(UserDepartment userDept : this.getUserDepts()){
				JSONObject userJsonObj =this.getJsonObjectUser(userDept);
				if(null != state){
					userJsonObj.put("state", state);
				}
				if(null!=menuId){
					userJsonObj.put("checked", this.isUserCheckedMenu(userDept.getUser(), menuId));
				}
				jsonArray.put(userJsonObj);
			}
			parentDeptJsonObj.put("state","opened");
		}
	}
	public JSONArray getChildrenWithAllResourceJsonArray(String resourceId) throws Exception{
		JSONArray jsonArray = new JSONArray();
		JSONObject deptJsonObj = this.getJsonObjectDept(this,true,"open");
		deptJsonObj.put("children", new JSONArray());
		this.getChildrenWithAllResourceJsonArrayRecursion(resourceId,deptJsonObj);
		if(null!=resourceId){
			deptJsonObj.put("checked", this.isDeptCheckedResource(this, resourceId));
		}
		jsonArray.put(deptJsonObj);
		return jsonArray;
	}
	public void getChildrenWithAllResourceJsonArrayRecursion(String resourceId,JSONObject parentDeptJsonObj) throws Exception{
	{
		    JSONArray jsonArray = parentDeptJsonObj.getJSONArray("children");
			for(Department dept : this.getChildrenDepts()){
				//JSONObject 
				JSONObject deptJsonObj  = this.getJsonObjectDept(dept,false,null);
				if(null!=resourceId){
					deptJsonObj.put("checked", this.isDeptCheckedResource(dept, resourceId));
				}
				deptJsonObj.put("children", new JSONArray());
				dept.getChildrenWithAllResourceJsonArrayRecursion(resourceId,deptJsonObj);
				jsonArray.put(deptJsonObj);
			}
			String state = null;
			for(DepartmentRole deptRole : this.getDeptRoles()){
				JSONObject roleJsonObj =this.getJsonObjectRole(deptRole);
				if(null != state){
					roleJsonObj.put("state", state);
				}
				if(null!=resourceId){
					roleJsonObj.put("checked", this.isDeptRoleCheckedResource(deptRole, resourceId));
				}
				jsonArray.put(roleJsonObj);
			}
			for(UserDepartment userDept : this.getUserDepts()){
				JSONObject userJsonObj =this.getJsonObjectUser(userDept);
				if(null != state){
					userJsonObj.put("state", state);
				}
				if(null!=resourceId){
					userJsonObj.put("checked", this.isUserCheckedResource(userDept.getUser(), resourceId));
				}
				jsonArray.put(userJsonObj);
			}
			parentDeptJsonObj.put("state","opened");
		}
	}
	////resource finish
	///Action
	public JSONArray getChildrenWithAllActionJsonArray(String actionId) throws Exception{
		JSONArray jsonArray = new JSONArray();
		JSONObject deptJsonObj = this.getJsonObjectDept(this,true,"open");
		deptJsonObj.put("children", new JSONArray());
		this.getChildrenWithAllActionJsonArrayRecursion(actionId,deptJsonObj);
		if(null!=actionId){
			deptJsonObj.put("checked", this.isDeptCheckedAction(this, actionId));
		}
		jsonArray.put(deptJsonObj);
		return jsonArray;
	}
	public void getChildrenWithAllActionJsonArrayRecursion(String actionId,JSONObject parentDeptJsonObj) throws Exception{
	{
		    JSONArray jsonArray = parentDeptJsonObj.getJSONArray("children");
			for(Department dept : this.getChildrenDepts()){
				//JSONObject 
				JSONObject deptJsonObj  = this.getJsonObjectDept(dept,false,null);
				if(null!=actionId){
					deptJsonObj.put("checked", this.isDeptCheckedAction(dept, actionId));
				}
				deptJsonObj.put("children", new JSONArray());
				dept.getChildrenWithAllActionJsonArrayRecursion(actionId,deptJsonObj);
				jsonArray.put(deptJsonObj);
			}
			String state = null;
			for(DepartmentRole deptRole : this.getDeptRoles()){
				JSONObject roleJsonObj =this.getJsonObjectRole(deptRole);
				if(null != state){
					roleJsonObj.put("state", state);
				}
				if(null!=actionId){
					roleJsonObj.put("checked", this.isDeptRoleCheckedAction(deptRole, actionId));
				}
				jsonArray.put(roleJsonObj);
			}
			for(UserDepartment userDept : this.getUserDepts()){
				JSONObject userJsonObj =this.getJsonObjectUser(userDept);
				if(null != state){
					userJsonObj.put("state", state);
				}
				if(null!=actionId){
					userJsonObj.put("checked", this.isUserCheckedAction(userDept.getUser(), actionId));
				}
				jsonArray.put(userJsonObj);
			}
			parentDeptJsonObj.put("state","opened");
		}
	}
	////action finish
    ////workflow start
	public JSONArray getChildrenWithAllWorkflowStartJsonArray(String workflowStartId) throws Exception{
		JSONArray jsonArray = new JSONArray();
		JSONObject deptJsonObj = this.getJsonObjectDept(this,true,"open");
		deptJsonObj.put("children", new JSONArray());
		this.getChildrenWithAllWorkflowStartJsonArrayRecursion(workflowStartId,deptJsonObj);
		if(null!=workflowStartId){
			deptJsonObj.put("checked", this.isDeptCheckedWorkflowStart(this, workflowStartId));
		}
		jsonArray.put(deptJsonObj);
		return jsonArray;
	}
	public void getChildrenWithAllWorkflowStartJsonArrayRecursion(String workflowStartId,JSONObject parentDeptJsonObj) throws Exception{
	{
		    JSONArray jsonArray = parentDeptJsonObj.getJSONArray("children");
			for(Department dept : this.getChildrenDepts()){
				//JSONObject 
				JSONObject deptJsonObj  = this.getJsonObjectDept(dept,false,null);
				if(null!=workflowStartId){
					deptJsonObj.put("checked", this.isDeptCheckedWorkflowStart(dept, workflowStartId));
				}
				deptJsonObj.put("children", new JSONArray());
				dept.getChildrenWithAllWorkflowStartJsonArrayRecursion(workflowStartId,deptJsonObj);
				jsonArray.put(deptJsonObj);
			}
			String state = null;
			for(DepartmentRole deptRole : this.getDeptRoles()){
				JSONObject roleJsonObj =this.getJsonObjectRole(deptRole);
				if(null != state){
					roleJsonObj.put("state", state);
				}
				if(null!=workflowStartId){
					roleJsonObj.put("checked", this.isDeptRoleCheckedWorkflowStart(deptRole, workflowStartId));
				}
				jsonArray.put(roleJsonObj);
			}
			for(UserDepartment userDept : this.getUserDepts()){
				JSONObject userJsonObj =this.getJsonObjectUser(userDept);
				if(null != state){
					userJsonObj.put("state", state);
				}
				if(null!=workflowStartId){
					userJsonObj.put("checked", this.isUserCheckedWorkflowStart(userDept.getUser(), workflowStartId));
				}
				jsonArray.put(userJsonObj);
			}
			parentDeptJsonObj.put("state","opened");
		}
	}
	/////workflowStart finish
	////workflowView 
	public JSONArray getChildrenWithAllWorkflowViewJsonArray(String workflowViewId) throws Exception{
		JSONArray jsonArray = new JSONArray();
		JSONObject deptJsonObj = this.getJsonObjectDept(this,true,"open");
		deptJsonObj.put("children", new JSONArray());
		this.getChildrenWithAllWorkflowViewJsonArrayRecursion(workflowViewId,deptJsonObj);
		if(null!=workflowViewId){
			deptJsonObj.put("checked", this.isDeptCheckedWorkflowView(this, workflowViewId));
		}
		jsonArray.put(deptJsonObj);
		return jsonArray;
	}
	public void getChildrenWithAllWorkflowViewJsonArrayRecursion(String workflowViewId,JSONObject parentDeptJsonObj) throws Exception{
	{
		    JSONArray jsonArray = parentDeptJsonObj.getJSONArray("children");
			for(Department dept : this.getChildrenDepts()){
				//JSONObject 
				JSONObject deptJsonObj  = this.getJsonObjectDept(dept,false,null);
				if(null!=workflowViewId){
					deptJsonObj.put("checked", this.isDeptCheckedWorkflowView(dept, workflowViewId));
				}
				deptJsonObj.put("children", new JSONArray());
				dept.getChildrenWithAllWorkflowViewJsonArrayRecursion(workflowViewId,deptJsonObj);
				jsonArray.put(deptJsonObj);
			}
			String state = null;
			for(DepartmentRole deptRole : this.getDeptRoles()){
				JSONObject roleJsonObj =this.getJsonObjectRole(deptRole);
				if(null != state){
					roleJsonObj.put("state", state);
				}
				if(null!=workflowViewId){
					roleJsonObj.put("checked", this.isDeptRoleCheckedWorkflowView(deptRole, workflowViewId));
				}
				jsonArray.put(roleJsonObj);
			}
			for(UserDepartment userDept : this.getUserDepts()){
				JSONObject userJsonObj =this.getJsonObjectUser(userDept);
				if(null != state){
					userJsonObj.put("state", state);
				}
				if(null!=workflowViewId){
					userJsonObj.put("checked", this.isUserCheckedWorkflowView(userDept.getUser(), workflowViewId));
				}
				jsonArray.put(userJsonObj);
			}
			parentDeptJsonObj.put("state","opened");
		}
	}
	/////workflowView finish
	////user permission
	public JSONArray getChildrenWithAllDeptRoleJsonArray(String userId) throws Exception{
		JSONArray jsonArray = new JSONArray();
		JSONObject deptJsonObj = this.getJsonObjectDept(this,true,"open");
		deptJsonObj.put("children", new JSONArray());
		Set<Department> tempChildrenDepts = this.getChildrenDepts();
		Set<DepartmentRole> tempDeptRoles = this.getDeptRoles();
		if((tempDeptRoles.size()>0)||(tempChildrenDepts.size()>0)){
			this.getChildrenWithAllDeptRoleJsonArrayRecursion(userId,deptJsonObj,tempChildrenDepts,tempDeptRoles);
			jsonArray.put(deptJsonObj);
		}
		//this.getChildrenWithAllDeptRoleJsonArrayRecursion(userId,deptJsonObj);
		//jsonArray.put(deptJsonObj);
		return jsonArray;
	}
	public void getChildrenWithAllDeptRoleJsonArrayRecursion(String userId,JSONObject parentDeptJsonObj,Set<Department> childrenDepts,Set<DepartmentRole> deptRoles) throws Exception{
	{
		    JSONArray jsonArray = parentDeptJsonObj.getJSONArray("children");
			for(Department dept : childrenDepts){
				//JSONObject 
				JSONObject deptJsonObj  = this.getJsonObjectDept(dept,false,null);
				deptJsonObj.put("children", new JSONArray());
				Set<Department> tempChildrenDepts = dept.getChildrenDepts();
				Set<DepartmentRole> tempDeptRoles = dept.getDeptRoles();
				if(null != userId){
					if((tempChildrenDepts.size()>0)||(tempDeptRoles.size()>0)){
						dept.getChildrenWithAllDeptRoleJsonArrayRecursion(userId,deptJsonObj,tempChildrenDepts,tempDeptRoles);
						jsonArray.put(deptJsonObj);
					}
				}else{
					dept.getChildrenWithAllDeptRoleJsonArrayRecursion(userId,deptJsonObj,tempChildrenDepts,tempDeptRoles);
					jsonArray.put(deptJsonObj);
				}
			}
			String state = null;
			for(DepartmentRole deptRole : deptRoles){
				JSONObject roleJsonObj =this.getJsonObjectRole(deptRole);
				if(null != state){
					roleJsonObj.put("state", state);
				}
				if(null!=userId){
					roleJsonObj.put("checked", this.isDeptRoleCheckedUser(deptRole, userId));
				}
				jsonArray.put(roleJsonObj);
			}
			parentDeptJsonObj.put("state","opened");
		}
	}
	///user permission finish
	
	public JSONObject getJsonObjectUser(UserDepartment userDept,String queryText) throws Exception{
		String currentState =  "open";
		User user = userDept.getUser();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", user.getId());
		jsonObj.put("text", user.getRealname()+"（"+user.getUsername()+"）");
		jsonObj.put("iconCls", "icon-users");
		jsonObj.put("state", currentState);
		JSONObject atrributesJsonObj = new JSONObject();
		atrributesJsonObj.put("type", "user");
		atrributesJsonObj.put("userEntityClassName","User");
		atrributesJsonObj.put("deptEntityClassName","UserDepartment");
		atrributesJsonObj.put("menuEntityClassName","UserMenu");
		atrributesJsonObj.put("resourceEntityClassName","UserResource");
		atrributesJsonObj.put("actionEntityClassName","UserAction");
		atrributesJsonObj.put("workflowStartEntityClassName","UserWorkflowStart");
		atrributesJsonObj.put("workflowViewEntityClassName","UserWorkflowView");
		atrributesJsonObj.put("userDeptId", userDept.getId());
		atrributesJsonObj.put("deptId", userDept.getDept().getId());
		atrributesJsonObj.put("userId", user.getId());
		jsonObj.put("attributes", atrributesJsonObj);
		return jsonObj;
	}
}
