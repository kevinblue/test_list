package com.tenwa.leasing.entity.contract;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

/**
 * 
 * @author 孙传良
 * @date 2013-3-5下午02:24:22
 * @info 合同非正常结束信息
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "合同结束信息")
@Table(name="CONTRACT_END_INFO")
public class ContractEndCondition {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="合同编号")
	@OneToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;

	@FieldName(name="资产所有权")
	@JoinColumn(name="OWNERSHIP_")
	private String ownership;

	@FieldName(name="约定结束日期")
	@Column(name="AGREE_DATE", length=20)
	private String agreeDate;
	
	@FieldName(name="违约手续费")
	@Column(name="BREACH_FEE",precision=22,scale=2)
	private BigDecimal breachFee;
	
	@FieldName(name="商定违约手续费")
	@Column(name="AGREE_BREACH_FEE",precision=22,scale=2)
	private BigDecimal agreeBreachFee;
	
	@FieldName(name="未到期本金")
	@Column(name="NO_DUE_CORPUS",precision=22,scale=2)
	private BigDecimal noDueCorpus;
	
	@FieldName(name="名义货价")
	@Column(name="NOMINAL_PRICE",precision=22,scale=2)
	private BigDecimal nominalPrice;
	
	@FieldName(name="保证金")
	@Column(name="MARGIN",precision=22,scale=2)
	private BigDecimal margin;
	
	@FieldName(name="逾期本金")
	@Column(name="OVERDUE_CORPUS",precision=22,scale=2)
	private BigDecimal overdueCorpus;
	
	@FieldName(name="逾期利息")
	@Column(name="OVERDUE_INTEREST",precision=22,scale=2)
	private BigDecimal overdueInterest;
	
	@FieldName(name="逾期罚息")
	@Column(name="OVERDUE_PENALTY",precision=22,scale=2)
	private BigDecimal overduePenalty;
	
	@FieldName(name="商定租金")
	@Column(name="AGREE_RENT",precision=22,scale=2)
	private BigDecimal agreeRent;
	
	@FieldName(name="合同债权总计")
	@Column(name="TOTAL_CREDIT",precision=22,scale=2)
	private BigDecimal totalCredit;

	@FieldName(name="商定债权总计")
	@Column(name="AGREE_TOTAL_CREDIT",precision=22,scale=2)
	private BigDecimal agreeTotalCredit;

	@FieldName(name="合同结束转移价格(德银)")
	@Column(name="END_PRICE",precision=22,scale=2)
	private BigDecimal endPrice;
	
	@FieldName(name="合同结束接收方名称(德银)")
	@Column(name="END_CUST",length=100)
	private String endCust;
	
	@FieldName(name="合同结束接收时间(德银)")
	@Column(name="END_DATE",length=20)
	private String endDate;
	
	@FieldName(name="合同结束备注(德银)")
	@Column(name="END_MEMO",length=2000)
	private String endMemo;
	
	@FieldName(name="安全性总结(德银)")
	@Column(name="SAFETY_MEMO",length=2000)
	private String safetyMemo;
	
	@FieldName(name="收益性总结(德银)")
	@Column(name="INCOME_MEMO",length=2000)
	private String incomeMemo;
	
	@FieldName(name="流动性总结(德银)")
	@Column(name="FLOW_MEMO",length=2000)
	private String flowMemo;
	
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

	public String getOwnership() {
		return ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getAgreeDate() {
		return agreeDate;
	}

	public void setAgreeDate(String agreeDate) {
		this.agreeDate = agreeDate;
	}

	public BigDecimal getBreachFee() {
		return breachFee;
	}

	public void setBreachFee(BigDecimal breachFee) {
		this.breachFee = breachFee;
	}

	public BigDecimal getAgreeBreachFee() {
		return agreeBreachFee;
	}

	public void setAgreeBreachFee(BigDecimal agreeBreachFee) {
		this.agreeBreachFee = agreeBreachFee;
	}

	public BigDecimal getNoDueCorpus() {
		return noDueCorpus;
	}

	public void setNoDueCorpus(BigDecimal noDueCorpus) {
		this.noDueCorpus = noDueCorpus;
	}

	public BigDecimal getNominalPrice() {
		return nominalPrice;
	}

	public void setNominalPrice(BigDecimal nominalPrice) {
		this.nominalPrice = nominalPrice;
	}

	public BigDecimal getMargin() {
		return margin;
	}

	public void setMargin(BigDecimal margin) {
		this.margin = margin;
	}

	public BigDecimal getOverdueCorpus() {
		return overdueCorpus;
	}

	public void setOverdueCorpus(BigDecimal overdueCorpus) {
		this.overdueCorpus = overdueCorpus;
	}

	public BigDecimal getOverdueInterest() {
		return overdueInterest;
	}

	public void setOverdueInterest(BigDecimal overdueInterest) {
		this.overdueInterest = overdueInterest;
	}

	public BigDecimal getOverduePenalty() {
		return overduePenalty;
	}

	public void setOverduePenalty(BigDecimal overduePenalty) {
		this.overduePenalty = overduePenalty;
	}

	public BigDecimal getAgreeRent() {
		return agreeRent;
	}

	public void setAgreeRent(BigDecimal agreeRent) {
		this.agreeRent = agreeRent;
	}

	public BigDecimal getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(BigDecimal totalCredit) {
		this.totalCredit = totalCredit;
	}

	public BigDecimal getAgreeTotalCredit() {
		return agreeTotalCredit;
	}

	public void setAgreeTotalCredit(BigDecimal agreeTotalCredit) {
		this.agreeTotalCredit = agreeTotalCredit;
	}

	public BigDecimal getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(BigDecimal endPrice) {
		this.endPrice = endPrice;
	}

	public String getEndCust() {
		return endCust;
	}

	public void setEndCust(String endCust) {
		this.endCust = endCust;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndMemo() {
		return endMemo;
	}

	public void setEndMemo(String endMemo) {
		this.endMemo = endMemo;
	}

	public String getSafetyMemo() {
		return safetyMemo;
	}

	public void setSafetyMemo(String safetyMemo) {
		this.safetyMemo = safetyMemo;
	}

	public String getIncomeMemo() {
		return incomeMemo;
	}

	public void setIncomeMemo(String incomeMemo) {
		this.incomeMemo = incomeMemo;
	}

	public String getFlowMemo() {
		return flowMemo;
	}

	public void setFlowMemo(String flowMemo) {
		this.flowMemo = flowMemo;
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
