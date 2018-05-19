package com.tenwa.business.entity.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.kernal.annotation.FieldName;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2013-7-8 上午11:44:42 类说明
 */
@Entity
@Table(name = "BASE_DOCUMENT_SAVED_DATAS")
@FieldName(name = "树转表数据存储")
public class BaseDocumentSavedData {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 200, name = "ID_")
	private String id;
	
	@FieldName(name="对应树状结构")
	@JoinColumn(name="SAVED_INFO_ID_",nullable=false)
	@ManyToOne(fetch=FetchType.LAZY)
	private BaseDocumentSavedInfo savedInfo;
	
	@FieldName(name="对应数据目录")
	@JoinColumn(name="DICT_ID_",nullable=false)
	@ManyToOne(fetch=FetchType.LAZY)
	private BaseDocumentConfig dict;
	
	@FieldName(name="对应数据项")
	@JoinColumn(name="COLUMN_ID_",nullable=false)
	@ManyToOne(fetch=FetchType.LAZY)
	private BaseDocumentConfigData column;
	
	@FieldName(name="存储的数据")
	@Column(name = "SAVED_DATA_",length=4000)
	private String savedData;

	public String getId() {
		return id;
	}

	public String getSavedData() {
		return savedData;
	}
   
	public void setId(String id) {
		this.id = id;
	}

	public void setSavedData(String savedData) {
		this.savedData = savedData;
	}

	public BaseDocumentConfig getDict() {
		return dict;
	}

	public void setDict(BaseDocumentConfig dict) {
		this.dict = dict;
	}

	public BaseDocumentConfigData getColumn() {
		return column;
	}

	public void setColumn(BaseDocumentConfigData column) {
		this.column = column;
	}

	public BaseDocumentSavedInfo getSavedInfo() {
		return savedInfo;
	}

	public void setSavedInfo(BaseDocumentSavedInfo savedInfo) {
		this.savedInfo = savedInfo;
	}
}
