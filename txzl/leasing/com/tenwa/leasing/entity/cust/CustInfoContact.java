package com.tenwa.leasing.entity.cust;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;


/**
 * 
 * @author 孙传良
 * @date 2013-7-28下午03:24:13
 * @info 走访调研信息
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "走访调研信息")
@Table(name = "CUST_CONTACT")
public class CustInfoContact {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	@FieldName(name="标识符")
	private String id;

	@ManyToOne
	@FieldName(name="客户ID")
	@JoinColumn(name = "CUST_ID")
	private CustInfo custId;
	
	@FieldName(name="沟通类型")
	@Column(name="COMMUNICAT_TYPE", length=50)
	private String communicatType;
	
	@FieldName(name="交往时间")
	@Column(name="CONTACT_DATE", length=50)
	private String contactDate;
	
	@FieldName(name="联系人")
	@Column(name="CONTACT_PERSON", length=50)
	private String contactPerson;

	@FieldName(name="联系人电话")
	@Column(name="CONTACT_PHONE", length=100)
	private String contactPhone;

	@FieldName(name="联系地点")
	@Column(name="CONTACT_ADD", length=200)
	private String contactAdd;
	
	@ManyToOne
	@FieldName(name="记录类型[D]")
	@JoinColumn(name="CONTACT_TYPE")
	private DictionaryData contactType;
	
	@FieldName(name="记录内容")
	@Column(name="CCMEMO", length=2000)
	private String ccmemo;
	
	@ManyToOne
	@FieldName(name="登记人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name="登记时间")
	@Column(name="CREATE_DATE", length=20)
	private String createDate;
	
	@ManyToOne
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE", length=20)
	private String modifyDate;

	public String getId() {
		return id;
	}

	public CustInfo getCustId() {
		return custId;
	}

	public DictionaryData getContactType() {
		return contactType;
	}

	public String getContactDate() {
		return contactDate;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public String getContactPhone() {
		return contactPhone;
	}


	public String getContactAdd() {
		return contactAdd;
	}

	public String getCcmemo() {
		return ccmemo;
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

	public void setCustId(CustInfo custId) {
		this.custId = custId;
	}

	public void setContactType(DictionaryData contactType) {
		this.contactType = contactType;
	}

	public void setContactDate(String contactDate) {
		this.contactDate = contactDate;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public void setContactAdd(String contactAdd) {
		this.contactAdd = contactAdd;
	}


	public void setCcmemo(String ccmemo) {
		this.ccmemo = ccmemo;
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

	public String getCommunicatType() {
		return communicatType;
	}

	public void setCommunicatType(String communicatType) {
		this.communicatType = communicatType;
	}
	
	
}
