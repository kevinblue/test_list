package com.tenwa.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.kernal.annotation.FieldName;


/**
 * 
 * @author 孙传良
 * @date 2013-8-2上午11:36:40
 * @info 设备类型
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "设备管理")
@Table(name="EQUIP_MODEL")
public class EquipModel {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id;
	
	@ManyToOne
	@FieldName(name="设备分类")
	@JoinColumn(name="EQUIP_CLASS")
	private DictionaryData equipClass;
	
	@ManyToOne
	@FieldName(name="设备类别")
	@JoinColumn(name="EQUIP_TYPE")
	private DictionaryData equipType;
	
	@FieldName(name="型号")
	@Column(name="MODEL_",length=20)
	private String model;
	
	@FieldName(name="计量单位")
	@Column(name="UNIT_",length=20)
	private String unit;
	
	@FieldName(name="生产厂商")
	@Column(name="MANUFACTURER",length=200)
	private String manufacturer;
	
	@FieldName(name="启用/禁用(0/1)[S]")
	@Column(name="ENABLE_",length=2)
	private String enable;
	
	@ManyToOne
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE", length=20)	
	private String createDate;
	
	@ManyToOne
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR")
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE", length=20)	
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DictionaryData getEquipClass() {
		return equipClass;
	}

	public void setEquipClass(DictionaryData equipClass) {
		this.equipClass = equipClass;
	}

	public DictionaryData getEquipType() {
		return equipType;
	}

	public void setEquipType(DictionaryData equipType) {
		this.equipType = equipType;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
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
}
