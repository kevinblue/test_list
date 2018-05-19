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

import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.json.JSONArray;
import org.json.JSONObject;

import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.kernal.utils.StringUtil;


 /**
 * 项目名称：    系统名称
 * 包名：              
 * 文件名：         Resource.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-3-4-下午05:52:19
 * Copyright：2013XX公司-版权所有
 **/

/**
 * 类名称：     Resource
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-3-4 下午05:52:19
 * 修改备注：
 * @version 1.0.0
 **/
@Entity
@Table(name="T_RESOURCES")
public class Resource implements Serializable,Comparable<Resource>{
	
	private static final long serialVersionUID = 9121394718611479592L;

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id ;
	
	@Column(nullable=false,name="NAME_")
	private String name;
	@Column(nullable=false,name="CODE_",unique=true)
	private String code;
	
	@Column(name="URL_",unique=true)
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
	
	@ManyToOne(targetEntity=Resource.class,cascade = 
	{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	,fetch=FetchType.LAZY) 
	@JoinColumn(name="PID_")
	private Resource parentResource;
	
	@OneToMany(mappedBy="parentResource",fetch = FetchType.LAZY)
	@OrderBy(value = "position ASC")
	private Set<Resource> childrenResources = new HashSet<Resource>();
	
	@Transient
	private Map<String,String> attributes = null;
	
	@OneToMany(mappedBy="resource",fetch=FetchType.LAZY) 
	private Set<ResourceDepartment> resourceDepts = new HashSet<ResourceDepartment>();
	
	@OneToMany(mappedBy="resource",fetch=FetchType.LAZY) 
	private Set<ResourceDepartmentRole> resourceDeptRoles = new HashSet<ResourceDepartmentRole>();
	
	@OneToMany(mappedBy="resource",fetch=FetchType.LAZY) 
	private Set<ResourceGroup> resourceGroups = new HashSet<ResourceGroup>();
	
	@OneToMany(mappedBy="resource",fetch=FetchType.LAZY) 
	private Set<UserResource> userResources = new HashSet<UserResource>();
	
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
	public int compareTo(Resource resource) {
		return this.id.compareTo(resource.getId());
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


	public Resource getParentResource() {
		return parentResource;
	}


	public Set<Resource> getChildrenResources() {
		return childrenResources;
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


	public void setParentResource(Resource parentResource) {
		this.parentResource = parentResource;
	}


	public void setChildrenResources(Set<Resource> childrenResources) {
		this.childrenResources = childrenResources;
	}


	public Set<ResourceDepartment> getResourceDepts() {
		return resourceDepts;
	}


	public Set<ResourceDepartmentRole> getResourceDeptRoles() {
		return resourceDeptRoles;
	}


	public Set<ResourceGroup> getResourceGroups() {
		return resourceGroups;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setResourceDepts(Set<ResourceDepartment> resourceDepts) {
		this.resourceDepts = resourceDepts;
	}


	public void setResourceDeptRoles(Set<ResourceDepartmentRole> resourceDeptRoles) {
		this.resourceDeptRoles = resourceDeptRoles;
	}


	public void setResourceGroups(Set<ResourceGroup> resourceGroups) {
		this.resourceGroups = resourceGroups;
	}
	
	public Set<UserResource> getUserResources() {
		return userResources;
	}


	public void setUserResources(Set<UserResource> userResources) {
		this.userResources = userResources;
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
		String pid = this.getParentResource() == null?"-1":this.getParentResource().getId();
		nodeAttributes.put("pid", pid);
		nodeAttributes.put("code", this.getCode());
		nodeAttributes.put("position", StringUtil.nullToString(this.getPosition()));
		nodeAttributes.put("description", StringUtil.getJsonString(this.getDescription()));
		nodeAttributes.put("icon",StringUtil.nullToString(this.getIcon()));
		nodeAttributes.put("type", "resource");
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
		return this.childrenResources.size()>=1;
	}
	public JSONArray getChildrenWithUsersJsonArray(boolean isInit,String state) throws Exception{
		JSONArray jsonArray = new JSONArray();
		if(isInit){
			JSONObject deptJsonObj = this.getJsonObjectResource(this,isInit,null);
			deptJsonObj.put("children", this.getChildrenWithUsersJsonArray(!isInit,state));
			jsonArray.put(deptJsonObj);
		}
		else
		{
			for(Resource resource : this.getChildrenResources()){
				JSONObject deptJsonObj = this.getJsonObjectResource(resource,isInit,null);
				jsonArray.put(deptJsonObj);
			}
			for(UserResource userresource : this.getUserResources()){
				JSONObject userJsonObj =this.getJsonObjectUser(userresource);
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
			JSONObject deptJsonObj = this.getJsonObjectResource(this,isInit,null);
			deptJsonObj.put("children", this.getChildrenJsonArray(!isInit));
			jsonArray.put(deptJsonObj);
		}
		else
		{
			for(Resource resource : this.getChildrenResources()){
				String state = resource.isHasChildren() ? "closed" : "opened";
				JSONObject deptJsonObj = this.getJsonObjectResource(resource,isInit,state);
				jsonArray.put(deptJsonObj);
			}
		}
		return jsonArray;
	}
	public JSONObject getJsonObjectResource(Resource resource,boolean isInit,String state) throws Exception{
		String currentState = isInit ? "opened" : state;
		if(null != state){
			currentState = state;
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", resource.getId());
		String url = StringUtil.nullToString(resource.getUrl());
		String attachUrl = "";
		if(!url.isEmpty()){
			attachUrl = " [<font color='red'> "+url+"</font> ]";
		}
		jsonObj.put("text", resource.getName()+" [ "+resource.getCode()+" ]"+attachUrl);
		try{
			String icon = resource.getIcon();
			jsonObj.put("iconCls", "icon-"+icon.substring(0,icon.lastIndexOf(".")));
		}catch(Exception e){
			jsonObj.put("iconCls", "");
		}
		
		jsonObj.put("state", currentState);
		JSONObject atrributesJsonObj = new JSONObject(this.getArributesJsonString(resource));
		atrributesJsonObj.put("url", resource.getUrl());
		atrributesJsonObj.put("name", resource.getName());
		atrributesJsonObj.put("code", resource.getCode());
		atrributesJsonObj.put("description", resource.getDescription());
		atrributesJsonObj.put("position", ("0".equals(resource.getId())) ? -1 :(resource.getPosition()));
		jsonObj.put("attributes",atrributesJsonObj);
		return jsonObj;
	}
	public JSONObject getJsonObjectUser(UserResource userResource) throws Exception{
		String currentState =  "opened";
		User user = userResource.getUser();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", user.getId());
		jsonObj.put("text", user.getRealname()+"("+user.getUsername()+")");
		jsonObj.put("iconCls", "icon-users-black");
		jsonObj.put("state", currentState);
		JSONObject atrributesJsonObj = new JSONObject();
		atrributesJsonObj.put("type", "user");
		atrributesJsonObj.put("userResourceId", userResource.getId());
		atrributesJsonObj.put("resourceId", userResource.getResource().getId());
		jsonObj.put("attributes", atrributesJsonObj);
		return jsonObj;
	}
	public String getArributesJsonString(Resource node)
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
	public void toJsonString(Resource node,StringBuffer str_sb)
	{
		String pid = node.getParentResource() == null?"0":node.getParentResource().getId();
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
			Set<Resource> childrenNodes = node.getChildrenResources();
			int index= 0;
			for(Resource mn : childrenNodes)
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
