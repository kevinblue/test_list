package com.reckon.entity.contract.reckon.fund;

import java.math.BigDecimal;

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
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.file.BaseFile;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;

/**
 * 
 * @author 孙传良
 * @date 2013-3-7下午03:10:52
 * @info 租金回笼实际(临时)  卡扣核销时需要临时表
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "租金回笼实际(临时)")
@Table(name="CONTRACT_FUND_RENT_INCOME_TEMP")
public class ContractFundRentInComeTemp {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;

	@ManyToOne
	@FieldName(name="合同编号")
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name="租金计划")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PLAN_ID")
	private ContractFundRentPlan planId;
	
	@FieldName(name="流程实例ID")
	@Column(name="DOC_ID")
	private String docId;
	
	@FieldName(name="计划期项")
	@Column(name="PLAN_LIST")
	private Integer planList;

	@FieldName(name="回笼期项")
	@Column(name="HIRE_LIST")
	private Integer hireList;

	@ManyToOne
	@FieldName(name="回笼类型")
	@JoinColumn(name="HIRE_TYPE")
	private DictionaryData hireType;

	@ManyToOne
	@FieldName(name="结算方式")
	@JoinColumn(name="BALANCE_MODE")
	private DictionaryData balanceMode;
	
	@ManyToOne
	@FieldName(name="货币类型")
	@JoinColumn(name="CURRENCY")
	private DictionaryData currency;
	
	@FieldName(name="回笼日期")
	@Column(name="HIRE_DATE",length=20)
	private String hireDate;

	@FieldName(name="回笼租金")
	@Column(name="RENT",precision=22,scale=2)
	private BigDecimal rent;

	@FieldName(name="回笼本金")
	@Column(name="CORPUS",precision=22,scale=2)
	private BigDecimal corpus;

	@FieldName(name="回笼租息")
	@Column(name="INTEREST",precision=22,scale=2)
	private BigDecimal interest;

	@FieldName(name="回笼罚息")
	@Column(name="PENALTY",precision=22,scale=2)
	private BigDecimal penalty;

	@FieldName(name="付款来源")
	@Column(name="HIRE_SOURCE")
	private String hireSource;

	@FieldName(name="付款人")
	@Column(name="HIRE_OBJECT")
	private String hireObject;

	@FieldName(name="付款人银行")
	@Column(name="HIRE_BANK")
	private String hireBank;

	@FieldName(name="付款人帐号")
	@Column(name="HIRE_NUMBER")
	private String hireNumber;

	@FieldName(name="本方银行")
	@Column(name="OWN_BANK")
	private String ownBank;

	@FieldName(name="本方银行账户")
	@Column(name="OWN_ACCOUNT")
	private String ownAccount;

	@FieldName(name="本方银行帐号")
	@Column(name="OWN_NUMBER")
	private String ownNumber;

	@FieldName(name="会计处理日")
	@Column(name="ACCOUNTING_DATE",length=20)
	private String accountingDate;

	@FieldName(name="备注")
	@Column(name="MEMO")
	private String memo;

	@ManyToOne
	@FieldName(name="上传ID")
	@JoinColumn(name="UP_ID")
	private BaseFile upId;
	
	@FieldName(name="租金款项来源")
	@Column(name="PID",length=50)
	private String PID;
	
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

	public ContractFundRentPlan getPlanId() {
		return planId;
	}

	public void setPlanId(ContractFundRentPlan planId) {
		this.planId = planId;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public Integer getPlanList() {
		return planList;
	}

	public void setPlanList(Integer planList) {
		this.planList = planList;
	}

	public Integer getHireList() {
		return hireList;
	}

	public void setHireList(Integer hireList) {
		this.hireList = hireList;
	}

	public DictionaryData getHireType() {
		return hireType;
	}

	public void setHireType(DictionaryData hireType) {
		this.hireType = hireType;
	}

	public DictionaryData getBalanceMode() {
		return balanceMode;
	}

	public void setBalanceMode(DictionaryData balanceMode) {
		this.balanceMode = balanceMode;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public BigDecimal getRent() {
		return rent;
	}

	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}

	public DictionaryData getCurrency() {
		return currency;
	}

	public void setCurrency(DictionaryData currency) {
		this.currency = currency;
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

	public String getHireSource() {
		return hireSource;
	}

	public void setHireSource(String hireSource) {
		this.hireSource = hireSource;
	}

	public String getHireObject() {
		return hireObject;
	}

	public void setHireObject(String hireObject) {
		this.hireObject = hireObject;
	}

	public String getHireBank() {
		return hireBank;
	}

	public void setHireBank(String hireBank) {
		this.hireBank = hireBank;
	}

	public String getHireNumber() {
		return hireNumber;
	}

	public void setHireNumber(String hireNumber) {
		this.hireNumber = hireNumber;
	}

	public String getOwnBank() {
		return ownBank;
	}

	public void setOwnBank(String ownBank) {
		this.ownBank = ownBank;
	}

	public String getOwnAccount() {
		return ownAccount;
	}

	public void setOwnAccount(String ownAccount) {
		this.ownAccount = ownAccount;
	}

	public String getOwnNumber() {
		return ownNumber;
	}

	public void setOwnNumber(String ownNumber) {
		this.ownNumber = ownNumber;
	}

	public String getAccountingDate() {
		return accountingDate;
	}

	public void setAccountingDate(String accountingDate) {
		this.accountingDate = accountingDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public BaseFile getUpId() {
		return upId;
	}

	public void setUpId(BaseFile upId) {
		this.upId = upId;
	}

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
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
