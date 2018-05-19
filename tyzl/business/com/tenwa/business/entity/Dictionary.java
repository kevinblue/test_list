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

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.json.JSONArray;
import org.json.JSONObject;

import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.kernal.utils.StringUtil;

@Entity
@Table(name="T_DICTS")
public class Dictionary implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=200,name="ID_")
    private String id;
	@Column(name="NAME_",nullable=false,length=100)
	private String name;
	@Column(name="CODE_",nullable=false,unique=true,length=50)
	private String code;
	@Column(name="DESCRIPTION_")
	private String description; 
	@Column(nullable=false,name="POSITION_")
	private int position; 
	
	@JsonBackReference
	@ManyToOne(targetEntity=Dictionary.class,cascade = 
	{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	,fetch=FetchType.LAZY) 
	@JoinColumn(name="PID_")
	private Dictionary parentDictionary;
	
	@JsonManagedReference
	@OneToMany(mappedBy="parentDictionary",fetch = FetchType.LAZY)
	@OrderBy(value = "position ASC")
	@Where(clause="ENABLED_ = 1")
	private Set<Dictionary> childrenDictionarys = new HashSet<Dictionary>();
	
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
	@JsonManagedReference
	
	@OneToMany(mappedBy="belongDictionary",fetch=FetchType.LAZY)
	@OrderBy(value = "position ASC")
	@Where(clause="ENABLED_ = 1")
	private Set<DictionaryData> datas = new HashSet<DictionaryData>();
	
	@Transient
	//预留字段
	private Map<String,String> attributes = null;
	
	@Column(name="ENABLED_",columnDefinition="INT DEFAULT 1",length=1,nullable=false)
	private Boolean enabled;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Dictionary getParentDictionary() {
		return parentDictionary;
	}
	public void setParentDictionary(Dictionary parentDictionary) {
		this.parentDictionary = parentDictionary;
	}
	public Set<Dictionary> getChildrenDictionarys() {
		return childrenDictionarys;
	}
	public void setChildrenDictionarys(Set<Dictionary> childrenDictionarys) {
		this.childrenDictionarys = childrenDictionarys;
	}
	
	public Map<String, String> getAttributes() {
		Map<String,String> nodeAttributes = new HashMap<String,String>();
		String pid = this.getParentDictionary() == null?"-1":this.getParentDictionary().getId();
		nodeAttributes.put("pid", pid);
		String parentOrgRealName = this.getParentDictionary() == null? "":this.getParentDictionary().getName();
		nodeAttributes.put("parentDictionaryRealName", parentOrgRealName);
		nodeAttributes.put("code", this.getCode());
		nodeAttributes.put("position", StringUtil.nullToString(this.getPosition()));
		nodeAttributes.put("description", StringUtil.getJsonString(this.getDescription()));
		nodeAttributes.put("type", "dict");
		
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
		return this.childrenDictionarys.size()>=1;
	}
	public String getDefaultIcon()
	{
		return "home.gif";
	}
	public String getArributesJsonString(Dictionary node)
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
	public void toJsonString(Dictionary node,StringBuffer str_sb)
	{
		Map<String,String> attributes = new HashMap<String,String>();
		String parentOrgRealName = node.getParentDictionary() == null? "":node.getParentDictionary().getName();
		
		attributes.put("parentDictionaryRealName", parentOrgRealName);
		attributes.put("code", node.getCode());
		
		node.setAttributes(attributes);
		
		String pid = node.getParentDictionary() == null?"0":node.getParentDictionary().getId();
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
			Set<Dictionary> childrenNodes = node.getChildrenDictionarys();
			int index= 0;
			for(Dictionary mn : childrenNodes)
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
	
	 /**
	 * @param datas the datas to set
	 **/
	
	public void setDatas(Set<DictionaryData> datas) {
		this.datas = datas;
	}
	
	 /**
	 * datas
	 * @return the datas
	 * @since 1.0.0
	 **/
	
	public Set<DictionaryData> getDatas() {
		return datas;
	}
	
	 /**
	 * @param code the code to set
	 **/
	
	public void setCode(String code) {
		this.code = code;
	}
	
	 /**
	 * code
	 * @return the code
	 * @since 1.0.0
	 **/
	
	public String getCode() {
		return code;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	@Transient
	public JSONArray getChildrenJsonArray(boolean isInit) throws Exception{
		JSONArray jsonArray = new JSONArray();
		if(isInit){
			JSONObject deptJsonObj = this.getJsonObjectDictionary(this,isInit,null);
			deptJsonObj.put("children", this.getChildrenJsonArray(!isInit));
			jsonArray.put(deptJsonObj);
		}
		else
		{
			for(Dictionary dictionary : this.getChildrenDictionarys()){
				String state = dictionary.isHasChildren() ? "closed" : "opened";
				JSONObject deptJsonObj = this.getJsonObjectDictionary(dictionary,isInit,state);
				jsonArray.put(deptJsonObj);
			}
		}
		return jsonArray;
	}
	@Transient
	public JSONObject getJsonObjectDictionary(Dictionary dictionary,boolean isInit,String state) throws Exception{
		String currentState = isInit ? "opened" : state;
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", dictionary.getId());
		jsonObj.put("text", dictionary.getName()+" [ "+dictionary.getCode()+" ] ");
		jsonObj.put("iconCls", "icon-home");
		jsonObj.put("state", currentState);
		JSONObject atrributesJsonObj = new JSONObject(this.getArributesJsonString(dictionary));
		atrributesJsonObj.put("name", dictionary.getName());
		atrributesJsonObj.put("code", dictionary.getCode());
		atrributesJsonObj.put("description", dictionary.getDescription());
		atrributesJsonObj.put("enabled",String.valueOf(dictionary.getEnabled()));
		atrributesJsonObj.put("position", ("0".equals(dictionary.getId())) ? -1 :(dictionary.getPosition()));
		jsonObj.put("attributes",atrributesJsonObj);
		return jsonObj;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Boolean getEnabled() {
		return enabled;
	}
}
