package com.tenwa.business.entity.base;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2013-7-8 上午11:44:42 类说明
 */
@Entity
@Table(name = "BASE_DOCUMENT_SAVED_INFOS")
@FieldName(name = "树转表结构存储")
public class BaseDocumentSavedInfo {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 200, name = "ID_")
	private String id;
	
	@FieldName(name="树状迭代json")
	@Lob 
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@Column(name="TREE_TABLE_JSON_",nullable=false)
	private String treeTableJson;
	
	@FieldName(name="树状列数据json")
	@Lob 
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@Column(name="TREE_DATA_MAPPING_",nullable=false)
	private String treeDataMapping;
	
	@FieldName(name="保存的数据json")
	@Lob 
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@Column(name="VALUES_MAPPING_",nullable=false)
	private String valuesMapping;
	
	@FieldName(name="保存的数据json")
	@Lob 
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@Column(name="SCORE_MAPPING_")
	private String scoreMapping;
	
	@FieldName(name="最大的树状层次")
	@Column(name = "max_Tree_Level_",nullable=false)
	private Integer maxTreeLevel;
	
	@FieldName(name="最大的列数量")
	@Column(name = "MAX_TREE_DATA_COUNT_",nullable=false)
	private Integer maxTreeDataCount;
	
	@FieldName(name="存储关键字")
	@Column(name = "SAVED_DATA_KEY_",nullable=false)
	private String savedDataKey;
	
	@FieldName(name="存储关键字附加1")
	@Column(name = "SAVED_DATA_KEY_1")
	private String savedDataKey1;
	
	@FieldName(name="存储关键字附加2")
	@Column(name = "SAVED_DATA_KEY_2")
	private String savedDataKey2;
	
	@FieldName(name="存储关键字附加3")
	@Column(name = "SAVED_DATA_KEY_3")
	private String savedDataKey3;
	
	@FieldName(name="存储关键字附加4")
	@Column(name = "SAVED_DATA_KEY_4")
	private String savedDataKey4;
	
	@FieldName(name="存储关键字附加5")
	@Column(name = "SAVED_DATA_KEY_5")
	private String savedDataKey5;
	
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

	public String getSavedDataKey() {
		return savedDataKey;
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

	public void setId(String id) {
		this.id = id;
	}

	public void setSavedDataKey(String savedDataKey) {
		this.savedDataKey = savedDataKey;
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

	public String getSavedDataKey1() {
		return savedDataKey1;
	}

	public String getSavedDataKey2() {
		return savedDataKey2;
	}

	public String getSavedDataKey3() {
		return savedDataKey3;
	}

	public String getSavedDataKey4() {
		return savedDataKey4;
	}

	public String getSavedDataKey5() {
		return savedDataKey5;
	}

	public void setSavedDataKey1(String savedDataKey1) {
		this.savedDataKey1 = savedDataKey1;
	}

	public void setSavedDataKey2(String savedDataKey2) {
		this.savedDataKey2 = savedDataKey2;
	}

	public void setSavedDataKey3(String savedDataKey3) {
		this.savedDataKey3 = savedDataKey3;
	}

	public void setSavedDataKey4(String savedDataKey4) {
		this.savedDataKey4 = savedDataKey4;
	}

	public void setSavedDataKey5(String savedDataKey5) {
		this.savedDataKey5 = savedDataKey5;
	}

	public String getTreeTableJson() {
		return treeTableJson;
	}

	public String getTreeDataMapping() {
		return treeDataMapping;
	}

	public String getValuesMapping() {
		return valuesMapping;
	}

	public Integer getMaxTreeLevel() {
		return maxTreeLevel;
	}

	public Integer getMaxTreeDataCount() {
		return maxTreeDataCount;
	}

	public void setTreeTableJson(String treeTableJson) {
		this.treeTableJson = treeTableJson;
	}

	public void setTreeDataMapping(String treeDataMapping) {
		this.treeDataMapping = treeDataMapping;
	}

	public void setValuesMapping(String valuesMapping) {
		this.valuesMapping = valuesMapping;
	}

	public void setMaxTreeLevel(Integer maxTreeLevel) {
		this.maxTreeLevel = maxTreeLevel;
	}

	public void setMaxTreeDataCount(Integer maxTreeDataCount) {
		this.maxTreeDataCount = maxTreeDataCount;
	}

	public String getScoreMapping() {
		return scoreMapping;
	}

	public void setScoreMapping(String scoreMapping) {
		this.scoreMapping = scoreMapping;
	}
	
	
}
