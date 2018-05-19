package com.tenwa.business.entity.base;

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

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.json.JSONArray;
import org.json.JSONObject;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.kernal.utils.StringUtil;


/**
 * 
 * @author 刘洪广
 * @date 2013-6-24下午02:14:33
 * @info
 * @Copyright 
 * 
 */
@Entity
@FieldName(name = "文档配置表")
@Table(name="BASE_DOCUMENT_CONFIG")
public class BaseDocumentConfig {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=255,name="ID_")
    private String id;
	
	@FieldName(name="资料名称")
	@Column(name="NAME_",nullable=false)
	private String name;
	
	@FieldName(name="资料code")
	@Column(name="CODE_",nullable=false,unique=true)
	private String code;
	
	@FieldName(name="资料描述")
	@Column(name="DESCRIPTION_")
	private String description; 
	
	@FieldName(name="排序")
	@Column(nullable=false,name="POSITION_")
	private int position; 
	 
	@FieldName(name="是否统计")
	@Column(name="IS_STATISTIC_",columnDefinition="INT DEFAULT 0",nullable=false)
	private Integer isStatistic; 
	
	@FieldName(name="宽度")
	@Column(name="WIDTH_",nullable=true)
	private String width ; 
	
	@FieldName(name="高度")
	@Column(name="HEIGHT_",nullable=true)
	private String height ; 
	
	@FieldName(name="禁用/启用")
	@Column(name="ENABLED_")
	//@Type(type = "org.hibernate.type.YesNoType")
	private Boolean enabled;
	
	@JsonBackReference
	@ManyToOne(targetEntity=BaseDocumentConfig.class,cascade = 
	{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	,fetch=FetchType.LAZY) 
	@JoinColumn(name="PID_")
	@FieldName(name="父亲节点")
	private BaseDocumentConfig parentBaseDocumentConfig;
	
	@JsonManagedReference
	@OneToMany(mappedBy="parentBaseDocumentConfig",fetch = FetchType.LAZY)
	@OrderBy(value = "position ASC")
	@FieldName(name="子集合")
	private Set<BaseDocumentConfig> childrenBaseDocumentConfigs = new HashSet<BaseDocumentConfig>();
	
	@OneToMany(mappedBy="belongDocument",fetch=FetchType.LAZY)
	@OrderBy(value = "position ASC")
	@FieldName(name="子项集合")
	private Set<BaseDocumentConfigData> BaseDocumentConfigDatas = new HashSet<BaseDocumentConfigData>();
	
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	@ManyToOne
	private User creator;
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE_", length=20)	
	private String createDate;
	
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR_")
	@ManyToOne
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE_", length=20)	
	private String modifyDate;

	@Transient
	private Map<String,String> attributes = null;
	
	public boolean isHasChildren()
	{
		return this.childrenBaseDocumentConfigs.size()>=1;
	}
	public String getDefaultIcon()
	{
		return "home.gif";
	}
	@Transient
	public Map<String, String> getAttributes() {
		Map<String,String> nodeAttributes = new HashMap<String,String>();
		BaseDocumentConfig parentBaseDocumentConfig = this.getParentBaseDocumentConfig();
		String pid = parentBaseDocumentConfig == null?"-1":parentBaseDocumentConfig.getId();
		nodeAttributes.put("pid", pid);
		String parentOrgRealName = parentBaseDocumentConfig == null? "":parentBaseDocumentConfig.getName();
		nodeAttributes.put("parentDictionaryRealName", parentOrgRealName);
		nodeAttributes.put("code", this.getCode());
		nodeAttributes.put("position", StringUtil.nullToString(this.getPosition()));
		nodeAttributes.put("description", StringUtil.getJsonString(this.getDescription()));
		nodeAttributes.put("isStatistic",this.getIsStatistic()+"");
		nodeAttributes.put("enabled", this.getEnabled().toString());
		nodeAttributes.put("type", "baseDocumentConfig");
		
		if(null != this.attributes){
			nodeAttributes.putAll(this.attributes);
		}
		return nodeAttributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	public String getArributesJsonString(BaseDocumentConfig node)
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
	
	public void toJsonString(BaseDocumentConfig node,StringBuffer str_sb)
	{
		Map<String,String> attributes = new HashMap<String,String>();
		String parentOrgRealName = node.getParentBaseDocumentConfig() == null? "":node.getParentBaseDocumentConfig().getName();
		
		attributes.put("parentDocumentRealName", parentOrgRealName);
		attributes.put("code", node.getCode());
		attributes.put("name", node.getName());
		node.setAttributes(attributes);
		
		String pid = node.getParentBaseDocumentConfig() == null?"DOCUMNET":node.getParentBaseDocumentConfig().getId();
		str_sb.append("{");
		str_sb.append("\"id\":"+"\""+StringUtil.nullToString(node.getId())+"\"");
		str_sb.append(",\"pid\":"+"\""+StringUtil.nullToString(pid)+"\"");
		str_sb.append(",\"name\":"+"\""+StringUtil.nullToString(node.getName())+"\"");
		str_sb.append(",\"url\":"+"\""+StringUtil.nullToString("")+"\"");
		str_sb.append(",\"icon\":"+"\""+StringUtil.nullToString(node.getDefaultIcon())+"\"");
		str_sb.append(",\"iconClose\":"+"\""+StringUtil.nullToString(node.getDefaultIcon())+"\"");
		str_sb.append(",\"iconOpen\":"+"\""+StringUtil.nullToString(node.getDefaultIcon())+"\"");
		str_sb.append(",\"description\":"+"\""+StringUtil.nullToString(node.getDescription()).replaceAll("\r", "\\\\r").replaceAll("\n","\\\\n")+"\"");
		str_sb.append(",\"position\":"+"\""+StringUtil.nullToString(node.getPosition())+"\"");
		str_sb.append(",\"authorities\":"+"\""+StringUtil.nullToString("")+"\"");
		str_sb.append(",\"attributes\":"+this.getArributesJsonString(node));
		str_sb.append(",\"children\":[");
		if(node.isHasChildren())
		{
			Set<BaseDocumentConfig> childrenNodes = node.getChildrenBaseDocumentConfigs();
			int index= 0;
			for(BaseDocumentConfig mn : childrenNodes)
			{
		    	if(0 < index++)
		    	{
		    		str_sb.append(",");
		    	}
		    	toJsonString(mn,str_sb);
			}
		}
		str_sb.append("]");
		
		//对于子项目的Json
		str_sb.append(",\"colunm\":[");
		if(!node.isHasChildren())
		{
			
			Set<BaseDocumentConfigData> columns = node.getBaseDocumentConfigDatas();
			int index= 0;
			for(BaseDocumentConfigData mn : columns)
			{
		    	if(0 < index++)
		    	{
		    		str_sb.append(",");
		    	}
		    	try {
					mn.toJsonString(mn,str_sb);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public BaseDocumentConfig getParentBaseDocumentConfig() {
		return parentBaseDocumentConfig;
	}

	public void setParentBaseDocumentConfig(BaseDocumentConfig parentBaseDocumentConfig) {
		this.parentBaseDocumentConfig = parentBaseDocumentConfig;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public User getModificator() {
		return modificator;
	}

	public void setModificator(User modificator) {
		this.modificator = modificator;
	}

	public String getModifyDate() {
		return modifyDate;
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
	public Set<BaseDocumentConfig> getChildrenBaseDocumentConfigs() {
		return childrenBaseDocumentConfigs;
	}
	public Set<BaseDocumentConfigData> getBaseDocumentConfigDatas() {
		return BaseDocumentConfigDatas;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setChildrenBaseDocumentConfigs(
			Set<BaseDocumentConfig> childrenBaseDocumentConfigs) {
		this.childrenBaseDocumentConfigs = childrenBaseDocumentConfigs;
	}
	
	public void setBaseDocumentConfigDatas(
			Set<BaseDocumentConfigData> baseDocumentConfigDatas) {
		BaseDocumentConfigDatas = baseDocumentConfigDatas;
	}
	@Transient
	public JSONArray getChildrenJsonArray(boolean isInit) throws Exception{
		JSONArray jsonArray = new JSONArray();
		if(isInit){
			JSONObject deptJsonObj = this.getJsonObjectBaseDocumentConfig(this,isInit,null);
			deptJsonObj.put("children", this.getChildrenJsonArray(!isInit));
			jsonArray.put(deptJsonObj);
		}
		else
		{
			for(BaseDocumentConfig baseDocumentConfig : this.getChildrenBaseDocumentConfigs()){
				String state = baseDocumentConfig.isHasChildren() ? "closed" : "opened";
				JSONObject deptJsonObj = this.getJsonObjectBaseDocumentConfig(baseDocumentConfig,isInit,state);
				jsonArray.put(deptJsonObj);
			}
		}
		return jsonArray;
	}
	@Transient
	public JSONObject getJsonObjectBaseDocumentConfig(BaseDocumentConfig baseDocumentConfig,boolean isInit,String state) throws Exception{
		String currentState = isInit ? "opened" : state;
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", baseDocumentConfig.getId());
		jsonObj.put("text", baseDocumentConfig.getName()+" [ "+baseDocumentConfig.getCode()+" ] ");
		jsonObj.put("iconCls", "icon-home");
		jsonObj.put("state", currentState);
		JSONObject atrributesJsonObj = new JSONObject(this.getArributesJsonString(baseDocumentConfig));
		atrributesJsonObj.put("name", baseDocumentConfig.getName());
		atrributesJsonObj.put("code", baseDocumentConfig.getCode());
		atrributesJsonObj.put("description", baseDocumentConfig.getDescription());
		atrributesJsonObj.put("width", baseDocumentConfig.getWidth());
		atrributesJsonObj.put("height", baseDocumentConfig.getHeight());
		atrributesJsonObj.put("isStatistic", baseDocumentConfig.getIsStatistic()+"");
		atrributesJsonObj.put("enabled", baseDocumentConfig.getEnabled()+"");
		atrributesJsonObj.put("position", ("0".equals(baseDocumentConfig.getId())) ? -1 :(baseDocumentConfig.getPosition()));
		jsonObj.put("attributes",atrributesJsonObj);
		return jsonObj;
	}
	public void setIsStatistic(Integer isStatistic) {
		this.isStatistic = isStatistic;
	}
	public Integer getIsStatistic() {
		return isStatistic;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	
}
