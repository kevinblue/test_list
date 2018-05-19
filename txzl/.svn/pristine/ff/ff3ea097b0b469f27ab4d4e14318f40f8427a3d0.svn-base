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
import org.hibernate.annotations.Type;
import org.json.JSONArray;
import org.json.JSONObject;

import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.kernal.utils.StringUtil;


 /**
 * 项目名称：    系统名称
 * 包名：              
 * 文件名：         Menu.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-3-4-下午05:52:19
 * Copyright：2013XX公司-版权所有
 **/

/**
 * 类名称：     Menu
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-3-4 下午05:52:19
 * 修改备注：
 * @version 1.0.0
 **/
@Entity
@Table(name="T_ACTIONS")
public class Action implements Serializable,Comparable<Action>{
	
	private static final long serialVersionUID = 987190043840421550L;

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id ;
	
	@Column(nullable=false,name="NAME_")
	private String name;
	@Column(nullable=false,name="CODE_",unique=true)
	private String code;
	
	@Column(name="URL_")
	private String url;
	
	@Column(name="ICON_")
	private String icon;
	
	@Column(name="ICONCLOSE_")
	private String iconClose;
	
	@Column(name="ICONOPEN_")
	private String iconOpen;
	
	@Column(name="DESCRIPTION_")
	private String description;
	
	@Column(nullable=false,name="POSITION_")
	private int position;
	
	@Column(name="IS_RELATIVED_PATH_",nullable=false,length=1)
	/*@Type(type = "org.hibernate.type.YesNoType")*/
	private Boolean isRelativedPath;
	
	@ManyToOne(targetEntity=Action.class,cascade = 
	{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	,fetch=FetchType.LAZY) 
	@JoinColumn(name="PID_")
	private Action parentAction;
	
	@OneToMany(mappedBy="parentAction",fetch = FetchType.LAZY)
	@OrderBy(value = "position ASC")
	private Set<Action> childrenActions = new HashSet<Action>();
	
	@Transient
	private Map<String,String> attributes = null;
	
	@OneToMany(mappedBy="action",fetch=FetchType.LAZY) 
	private Set<ActionDepartment> actionDepts = new HashSet<ActionDepartment>();
	
	@OneToMany(mappedBy="action",fetch=FetchType.LAZY) 
	private Set<ActionDepartmentRole> actionDeptRoles = new HashSet<ActionDepartmentRole>();
	
	@OneToMany(mappedBy="action",fetch=FetchType.LAZY) 
	private Set<ActionGroup> actionGroups = new HashSet<ActionGroup>();
	
	@OneToMany(mappedBy="action",fetch=FetchType.LAZY) 
	private Set<UserAction> userActions = new HashSet<UserAction>();
	
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
	
	@Override
	public int compareTo(Action action) {
		return this.id.compareTo(action.getId());
	}


	public String getId() {
		return id;
	}
    

	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getName() {
		return name;
	}


	public String getUrl() {
		return url;
	}


	public String getIcon() {
		return icon;
	}


	public String getIconClose() {
		return iconClose;
	}


	public String getIconOpen() {
		return iconOpen;
	}


	public String getDescription() {
		return description;
	}


	public int getPosition() {
		return position;
	}


	public Action getParentAction() {
		return parentAction;
	}


	public Set<Action> getChildrenActions() {
		return childrenActions;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}


	public void setIconClose(String iconClose) {
		this.iconClose = iconClose;
	}


	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setPosition(int position) {
		this.position = position;
	}


	public void setParentAction(Action parentAction) {
		this.parentAction = parentAction;
	}


	public void setChildrenActions(Set<Action> childrenActions) {
		this.childrenActions = childrenActions;
	}


	public Set<ActionDepartment> getActionDepts() {
		return actionDepts;
	}


	public Set<ActionDepartmentRole> getActionDeptRoles() {
		return actionDeptRoles;
	}


	public Set<ActionGroup> getActionGroups() {
		return actionGroups;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setActionDepts(Set<ActionDepartment> actionDepts) {
		this.actionDepts = actionDepts;
	}


	public void setActionDeptRoles(Set<ActionDepartmentRole> actionDeptRoles) {
		this.actionDeptRoles = actionDeptRoles;
	}


	public void setActionGroups(Set<ActionGroup> actionGroups) {
		this.actionGroups = actionGroups;
	}
	
	public Set<UserAction> getUserActions() {
		return userActions;
	}


	public void setUserActions(Set<UserAction> userActions) {
		this.userActions = userActions;
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

	public Boolean getIsRelativedPath() {
		return isRelativedPath;
	}

	public void setIsRelativedPath(Boolean isRelativedPath) {
		this.isRelativedPath = isRelativedPath;
	}

	public Map<String, String> getAttributes() {
		Map<String,String> nodeAttributes = new HashMap<String,String>();
		String pid = this.getParentAction() == null?"-1":this.getParentAction().getId();
		nodeAttributes.put("pid", pid);
		nodeAttributes.put("code", this.getCode());
		nodeAttributes.put("position", StringUtil.nullToString(this.getPosition()));
		nodeAttributes.put("description", StringUtil.getJsonString(this.getDescription()));
		nodeAttributes.put("icon",StringUtil.nullToString(this.getIcon()));
		nodeAttributes.put("type", "action");
		nodeAttributes.put("isRelativedPath",(( null == this.isRelativedPath)? Boolean.TRUE : this.isRelativedPath).toString());
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
		return this.childrenActions.size()>=1;
	}
	public JSONArray getChildrenWithUsersJsonArray(boolean isInit,String state) throws Exception{
		JSONArray jsonArray = new JSONArray();
		if(isInit){
			JSONObject deptJsonObj = this.getJsonObjectAction(this,isInit,null);
			deptJsonObj.put("children", this.getChildrenWithUsersJsonArray(!isInit,state));
			jsonArray.put(deptJsonObj);
		}
		else
		{
			for(Action action : this.getChildrenActions()){
				JSONObject deptJsonObj = this.getJsonObjectAction(action,isInit,null);
				jsonArray.put(deptJsonObj);
			}
			for(UserAction useraction : this.getUserActions()){
				JSONObject userJsonObj =this.getJsonObjectUser(useraction);
				if(null != state){
					userJsonObj.put("state", state);
				}
				jsonArray.put(userJsonObj);
			}
		}
		return jsonArray;
	}
	public JSONArray getChildrenJsonArray(boolean isInit) throws Exception{
		JSONArray jsonArray = new JSONArray();
		if(isInit){
			JSONObject deptJsonObj = this.getJsonObjectAction(this,isInit,null);
			deptJsonObj.put("children", this.getChildrenJsonArray(!isInit));
			jsonArray.put(deptJsonObj);
		}
		else
		{
			for(Action action : this.getChildrenActions()){
				String state = action.isHasChildren() ? "closed" : "opened";
				JSONObject deptJsonObj = this.getJsonObjectAction(action,isInit,state);
				jsonArray.put(deptJsonObj);
			}
		}
		return jsonArray;
	}
	public JSONObject getJsonObjectAction(Action action,boolean isInit,String state) throws Exception{
		String currentState = isInit ? "opened" : state;
		if(null != state){
			currentState = state;
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", action.getId());
		jsonObj.put("text", action.getName()+" [ "+action.getCode()+" ] ");
		try{
			String icon = action.getIcon();
			jsonObj.put("iconCls", "icon-"+icon.substring(0,icon.lastIndexOf(".")));
		}catch(Exception e){
			jsonObj.put("iconCls", "");
		}
		
		jsonObj.put("state", currentState);
		JSONObject atrributesJsonObj = new JSONObject(this.getArributesJsonString(action));
		atrributesJsonObj.put("url", action.getUrl());
		atrributesJsonObj.put("code", action.getCode());
		atrributesJsonObj.put("name", action.getName());
		atrributesJsonObj.put("description", action.getDescription());
		atrributesJsonObj.put("position", ("0".equals(action.getId())) ? -1 :(action.getPosition()));
		jsonObj.put("attributes",atrributesJsonObj);
		return jsonObj;
	}
	public JSONObject getJsonObjectUser(UserAction userAction) throws Exception{
		String currentState =  "opened";
		User user = userAction.getUser();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", user.getId());
		jsonObj.put("text", user.getRealname()+"("+user.getUsername()+")");
		jsonObj.put("iconCls", "icon-users-black");
		jsonObj.put("state", currentState);
		JSONObject atrributesJsonObj = new JSONObject();
		atrributesJsonObj.put("type", "user");
		atrributesJsonObj.put("userActionId", userAction.getId());
		atrributesJsonObj.put("actionId", userAction.getAction().getId());
		jsonObj.put("attributes", atrributesJsonObj);
		return jsonObj;
	}
	public String getArributesJsonString(Action node)
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
	public void toJsonString(Action node,StringBuffer str_sb)
	{
		String pid = node.getParentAction() == null?"0":node.getParentAction().getId();
		str_sb.append("{");
		str_sb.append("\"id\":"+"\""+StringUtil.nullToString(node.getId())+"\"");
		str_sb.append(",\"pid\":"+"\""+StringUtil.nullToString(pid)+"\"");
		str_sb.append(",\"name\":"+"\""+StringUtil.nullToString(node.getName())+"\"");
		str_sb.append(",\"url\":"+"\""+StringUtil.nullToString(node.getUrl())+"\"");
		str_sb.append(",\"icon\":"+"\""+StringUtil.nullToString(node.getIcon())+"\"");
		str_sb.append(",\"iconClose\":"+"\""+StringUtil.nullToString(node.getIconClose())+"\"");
		str_sb.append(",\"iconOpen\":"+"\""+StringUtil.nullToString(node.getIconOpen())+"\"");
		str_sb.append(",\"description\":"+"\""+StringUtil.nullToString(node.getDescription()).replaceAll("\r", "\\\\r").replaceAll("\n","\\\\n")+"\"");
		str_sb.append(",\"position\":"+"\""+StringUtil.nullToString(node.getPosition())+"\"");
		str_sb.append(",\"authorities\":\"ROLE_USER\"");
		str_sb.append(",\"attributes\":"+this.getArributesJsonString(node));
		str_sb.append(",\"children\":[");
		if(node.isHasChildren())
		{
			Set<Action> childrenNodes = node.getChildrenActions();
			int index= 0;
			for(Action mn : childrenNodes)
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
}
