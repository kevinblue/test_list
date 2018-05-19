package com.tenwa.leasing.entity.contract;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.cust.CustInfo;


/**
 * 
 * @author 孙传良
 * @date 2013-3-5下午04:53:55
 * @info 合同担保方式
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "合同担保方式变更历史表")
@Table(name="CONTRACT_GUARANTEE_METHOD_HIS")
public class ContractGuaranteeMethodHis {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="起租合同变更表ID")
	@ManyToOne(targetEntity=OnhireContractChangeInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="ONHIRE_CONTRACT_CHANGE_ID")
	private OnhireContractChangeInfo onhireContractChangeId;
	
	@FieldName(name="流程实例ID")
	@Column(name="DOC_ID")
	private String docID;
	
	@ManyToOne
	@FieldName(name="担保人")
	@JoinColumn(name="ASSUROR_")
	private CustInfo assuror;

	@ManyToOne
	@FieldName(name="担保类型")
	@JoinColumn(name="ASSURE_METHOD")
	private DictionaryData assureMethod;

	@ManyToOne
	@FieldName(name="担保关系")
	@JoinColumn(name="ASSURE_RELATION")
	private DictionaryData assureRelation;

	@FieldName(name="备注")
	@Column(name="CGMNOTE")
	private String cgmnote;
	
	@ManyToOne
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name="创建时间")
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

	public void setId(String id) {
		this.id = id;
	}

	public OnhireContractChangeInfo getOnhireContractChangeId() {
		return onhireContractChangeId;
	}

	public void setOnhireContractChangeId(
			OnhireContractChangeInfo onhireContractChangeId) {
		this.onhireContractChangeId = onhireContractChangeId;
	}

	public String getDocID() {
		return docID;
	}

	public void setDocID(String docID) {
		this.docID = docID;
	}

	public CustInfo getAssuror() {
		return assuror;
	}

	public void setAssuror(CustInfo assuror) {
		this.assuror = assuror;
	}

	public DictionaryData getAssureMethod() {
		return assureMethod;
	}

	public void setAssureMethod(DictionaryData assureMethod) {
		this.assureMethod = assureMethod;
	}

	public DictionaryData getAssureRelation() {
		return assureRelation;
	}

	public void setAssureRelation(DictionaryData assureRelation) {
		this.assureRelation = assureRelation;
	}

	public String getCgmnote() {
		return cgmnote;
	}

	public void setCgmnote(String cgmnote) {
		this.cgmnote = cgmnote;
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
