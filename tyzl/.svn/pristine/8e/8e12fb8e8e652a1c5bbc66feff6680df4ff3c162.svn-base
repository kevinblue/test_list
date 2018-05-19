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
 * @author Toybaby
 * @date 2014-12-11  15:01
 * @info 合同中途中止结算信息
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "合同中途中止结算信息")
@Table(name="CONTRACT_TERMINATE_INFO")
public class ContractTerminateInfo {

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
	@Column(name="AGREE_TERMINATE_DATE", length=20)
	private String agreeTerminateDate;
	
	@FieldName(name="其他应收")
	@Column(name="OTHER_RECEIVABLES",precision=22,scale=2)
	private BigDecimal otherReceivables;
	
	
	@FieldName(name="其他应退")
	@Column(name="OTHER_RETURN",precision=22,scale=2)
	private BigDecimal otherReturn;
	
	@FieldName(name="已到期租金")
	@Column(name="EXPIRED_RENT",precision=22,scale=2)
	private BigDecimal expiredRent;
	
	@FieldName(name="逾期罚息")
	@Column(name="OVERDUE_PENALTY",precision=22,scale=2)
	private BigDecimal overduePenalty;
	
	@FieldName(name="未到期本金")
	@Column(name="NO_DUE_CORPUS",precision=22,scale=2)
	private BigDecimal noDueCorpus;
	
	
	@FieldName(name="违约手续费")
	@Column(name="BREACH_FEE",precision=22,scale=2)
	private BigDecimal breachFee;
	
	@FieldName(name="商定罚息")
	@Column(name="AGREE_PENALTY",precision=22,scale=2)
	private BigDecimal agreepenalty;
	
	@FieldName(name="商定利息")
	@Column(name="AGREE_INTEREST",precision=22,scale=2)
	private BigDecimal agreeInterest;
	
	@FieldName(name="合同债权总计")
	@Column(name="TOTAL_CREDIT",precision=22,scale=2)
	private BigDecimal totalCredit;

	
	@FieldName(name="终止原因")
	@Column(name="TERMINATE_REASON",length=2000)
	private String terminateReason;
	
	@FieldName(name="备注")
	@Column(name="TERMINATE_MEMO",length=2000)
	private String terminateMemo;
	
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

	public String getAgreeTerminateDate() {
		return agreeTerminateDate;
	}

	public void setAgreeTerminateDate(String agreeTerminateDate) {
		this.agreeTerminateDate = agreeTerminateDate;
	}

	public BigDecimal getOtherReceivables() {
		return otherReceivables;
	}

	public void setOtherReceivables(BigDecimal otherReceivables) {
		this.otherReceivables = otherReceivables;
	}

	public BigDecimal getOtherReturn() {
		return otherReturn;
	}

	public void setOtherReturn(BigDecimal otherReturn) {
		this.otherReturn = otherReturn;
	}

	public BigDecimal getExpiredRent() {
		return expiredRent;
	}

	public void setExpiredRent(BigDecimal expiredRent) {
		this.expiredRent = expiredRent;
	}

	public BigDecimal getOverduePenalty() {
		return overduePenalty;
	}

	public void setOverduePenalty(BigDecimal overduePenalty) {
		this.overduePenalty = overduePenalty;
	}

	public BigDecimal getNoDueCorpus() {
		return noDueCorpus;
	}

	public void setNoDueCorpus(BigDecimal noDueCorpus) {
		this.noDueCorpus = noDueCorpus;
	}

	public BigDecimal getBreachFee() {
		return breachFee;
	}

	public void setBreachFee(BigDecimal breachFee) {
		this.breachFee = breachFee;
	}

	public BigDecimal getAgreepenalty() {
		return agreepenalty;
	}

	public void setAgreepenalty(BigDecimal agreepenalty) {
		this.agreepenalty = agreepenalty;
	}

	public BigDecimal getAgreeInterest() {
		return agreeInterest;
	}

	public void setAgreeInterest(BigDecimal agreeInterest) {
		this.agreeInterest = agreeInterest;
	}

	public BigDecimal getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(BigDecimal totalCredit) {
		this.totalCredit = totalCredit;
	}

	public String getTerminateReason() {
		return terminateReason;
	}

	public void setTerminateReason(String terminateReason) {
		this.terminateReason = terminateReason;
	}

	public String getTerminateMemo() {
		return terminateMemo;
	}

	public void setTerminateMemo(String terminateMemo) {
		this.terminateMemo = terminateMemo;
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
