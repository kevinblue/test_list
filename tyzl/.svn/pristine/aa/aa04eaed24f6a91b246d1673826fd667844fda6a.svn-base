package com.tenwa.business.entity.base;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.json.JSONObject;

import com.tenwa.business.entity.DictionaryData;
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
@FieldName(name = "文档配置子项列")
@Table(name="BASE_DOCUMENT_COLUMN_CONFIG")
public class BaseDocumentConfigData {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=255,name="ID_")
    private String id;
	
	@FieldName(name="排序")
	@Column(nullable=false,name="POSITION_")
	private int position; 
	
	@FieldName(name="子项名称")
	@Column(name="NAME_",nullable=false)
	private String name;
	
	@FieldName(name="子项code")
	@Column(name="CODE_",nullable=false)
	private String code;
	
	@FieldName(name="子项对应值")
	@Column(name="VALUE_")
	private String value;
	
	@FieldName(name="子项描述")
	@Column(name="DESCRIPTION_")
	private String description; 
	
	@FieldName(name="宽度")
	@Column(name="WIDTH_",nullable=true)
	private String width ; 
	
	@FieldName(name="高度")
	@Column(name="HEIGHT_",nullable=true)
	private String height ; 
	
	@FieldName(name="是否自动算")
	@Column(name="AUTO_COMPUTER")
	private String autocomputer ; 
	
	@FieldName(name="子项类别")
	@JoinColumn(name="TYPE_")
	@ManyToOne
	private DictionaryData type;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID" )
	@FieldName(name="对应规则")
    private BaseRole role;
	
	@FieldName(name="禁用/启用")
	@Column(name="ENABLED_")
	/*@Type(type = "org.hibernate.type.YesNoType")*/
	private Boolean enabled; 
	
	
	@FieldName(name = "是否默认选中")
	@Column(name = "IS_SELECTED")
	private String isSelected;
	
	@FieldName(name = "是否必填")
	@Column(name = "IS_REQUIRE")
	private String isRequire;
	
	@JsonBackReference
	@ManyToOne(targetEntity=BaseDocumentConfig.class,cascade = 
	{CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}
	,fetch=FetchType.EAGER) 
	@JoinColumn(name="PID_",nullable=false)
	private BaseDocumentConfig belongDocument ;
	
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
	public JSONObject getJsonObjectDocumentColumn(BaseDocumentConfigData docColumn) throws Exception{
		String currentState =  "closed";
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", docColumn.getId());
		jsonObj.put("text", docColumn.getName()+"（"+docColumn.getCode()+"）");
		jsonObj.put("iconCls", "icon-home");
		jsonObj.put("state", currentState);
		return jsonObj;
	}
	
	
	//Json转换函数
	public void toJsonString(BaseDocumentConfigData node,StringBuffer str_sb)
	{
		str_sb.append("{");
		str_sb.append("\"id\":"+"\""+StringUtil.nullToString(node.getId())+"\"");
		str_sb.append(",\"name\":"+"\""+StringUtil.nullToString(node.getName())+"\"");
		str_sb.append(",\"code\":"+"\""+StringUtil.nullToString(node.getCode())+"\"");
		str_sb.append(",\"type\":"+"\""+StringUtil.nullToString(node.getType().getName())+"\"");
		str_sb.append(",\"value\":"+"\""+StringUtil.nullToString(node.getValue())+"\"");
		str_sb.append("}");
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
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


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DictionaryData getType() {
		return type;
	}

	public void setType(DictionaryData type) {
		this.type = type;
	}

	public Boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	public BaseDocumentConfig getBelongDocument() {
		return belongDocument;
	}

	public void setBelongDocument(BaseDocumentConfig belongDocument) {
		this.belongDocument = belongDocument;
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


	public String getIsSelected() {
		return isSelected;
	}


	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}


	public String getIsRequire() {
		return isRequire;
	}


	public void setIsRequire(String isRequire) {
		this.isRequire = isRequire;
	}


	public String getAutocomputer() {
		return autocomputer;
	}


	public void setAutocomputer(String autocomputer) {
		this.autocomputer = autocomputer;
	}


	public BaseRole getRole() {
		return role;
	}


	public void setRole(BaseRole role) {
		this.role = role;
	}
	
	
}
