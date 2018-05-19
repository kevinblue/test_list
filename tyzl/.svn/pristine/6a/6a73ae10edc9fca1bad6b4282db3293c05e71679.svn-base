package com.reckon.entity.contract.reckon.fund;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;


/**
 * 
 * @author 徐云龙
 * @date 2013-10-29下午04:42:11
 * @info 合同租金罚息历史表
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "合同租金罚息历史表")
@Table(name="CONTRACT_RENT_PENALTY_HIS")
public class ContractFundRentPenaltyPlanHis {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
   
	@FieldName(name="合同编号")
	@JoinColumn(name="CONTRACT_ID")
	@ManyToOne
	private ContractInfo contractId;
	
	@FieldName(name="期项")
	@Column(name="RENT_LIST")
	private Integer rentList;
	
	@FieldName(name="承付日期")
	@Column(name="PLAN_DATE",length=20)
	private String   planDate;
	
	@FieldName(name="租金")
	@Column(name="RENT",precision=22,scale=2)
	private BigDecimal rent;
	
	@FieldName(name="财务本金")
	@Column(name="CORPUS",precision=22,scale=2)
	private BigDecimal corpus;
	
	@FieldName(name="租息")
	@Column(name="INTEREST",precision=22,scale=2)
	private BigDecimal interest;
	
	@FieldName(name="罚息")
	@Column(name="PENALTY",precision=22,scale=2)
	private BigDecimal penalty;
	
	@FieldName(name="罚息余额")
	@Column(name="PENALTY_OVERAGE",precision=22,scale=2)
	private BigDecimal penaltyOverage;
	
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

	public Integer getRentList() {
		return rentList;
	}

	public void setRentList(Integer rentList) {
		this.rentList = rentList;
	}

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public BigDecimal getRent() {
		return rent;
	}

	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}

	public BigDecimal getCorpus() {
		return corpus;
	}

	public void setCorpus(BigDecimal corpus) {
		this.corpus = corpus;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getPenalty() {
		return penalty;
	}

	public void setPenalty(BigDecimal penalty) {
		this.penalty = penalty;
	}

	public BigDecimal getPenaltyOverage() {
		return penaltyOverage;
	}

	public void setPenaltyOverage(BigDecimal penaltyOverage) {
		this.penaltyOverage = penaltyOverage;
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
