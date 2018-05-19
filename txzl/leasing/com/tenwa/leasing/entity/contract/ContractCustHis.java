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
 * @date 2013-7-29下午05:59:15
 * @info 合同承租人变更历史表
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "合同承租人变更历史表")
@Table(name="CONTRACT_CUST_HIS")
public class ContractCustHis {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="合同编号")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;

	@FieldName(name="流程实例ID")
	@Column(name="flow_unid")
	private String flowUnid;
	
	@ManyToOne
	@FieldName(name="承租人")
	@JoinColumn(name="CUST_ID")
	private CustInfo custID;

	@ManyToOne
	@FieldName(name="修改状态")
	@JoinColumn(name="MOD_STATUS")	
	private DictionaryData modStatus;
	
	@ManyToOne
	@FieldName(name="修改原因")
	@JoinColumn(name="MOD_REASON")
	private DictionaryData modReason;
	
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

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	

	public String getFlowUnid() {
		return flowUnid;
	}

	public void setFlowUnid(String flowUnid) {
		this.flowUnid = flowUnid;
	}

	public CustInfo getCustID() {
		return custID;
	}

	public void setCustID(CustInfo custID) {
		this.custID = custID;
	}

	public DictionaryData getModStatus() {
		return modStatus;
	}

	public void setModStatus(DictionaryData modStatus) {
		this.modStatus = modStatus;
	}

	public DictionaryData getModReason() {
		return modReason;
	}

	public void setModReason(DictionaryData modReason) {
		this.modReason = modReason;
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
