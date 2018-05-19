package com.tenwa.leasing.entity.fund;

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
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.file.BaseFile;

/**
 * 
 * @author 孙传良
 * @date 2013-3-7下午02:43:02
 * @info 租金回笼实际
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "租金实收表")
@Table(name="CONTRACT_FUND_RENT_INCOME")
public class ContractFundRentInCome {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;

	@FieldName(name="合同编号")
	@JoinColumn(name="CONTRACT_ID")
	@ManyToOne
	private ContractInfo contractId;
	
	@FieldName(name="租金计划")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PLAN_ID")
	private ContractFundRentPlan planId;
	
	@FieldName(name="租金计划(财务租金计划)")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PLAN_ID_FINANCE")
	private FinanceFundRentPlan planIdFinance;
	
	@FieldName(name="租金计划(财务租金计划临时表)")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PLAN_ID_FINANCE_TEMP")
	private FinanceFundRentPlanTemp planIdFinanceTemp;
	
	@FieldName(name="报价编号")
	@Column(name="DOC_ID")
	private String docId;
	
	
	@FieldName(name="网银编号")
	@ManyToOne(targetEntity=FundEbankData.class,fetch=FetchType.LAZY)
	@JoinColumn(name="EBANK_NUMBER")
	private FundEbankData ebankNumber ;
	
	@FieldName(name="计划期项")
	@Column(name="PLAN_LIST")
	private Integer planList;

	@FieldName(name="回笼期项")
	@Column(name="HIRE_LIST")
	private Integer hireList;

	@FieldName(name="回笼类型")
	@JoinColumn(name="HIRE_TYPE")
	@ManyToOne
	private DictionaryData hireType;

	@FieldName(name="结算方式")
	@JoinColumn(name="BALANCE_MODE")
	@ManyToOne
	private DictionaryData balanceMode;
	
	@FieldName(name="回笼日期")
	@Column(name="HIRE_DATE",length=20)
	private String hireDate;

	@FieldName(name="单据号")
	@Column(name="INVOICE_NO")
	private String invoiceNo;

	@FieldName(name="货币类型")
	@JoinColumn(name="CURRENCY")
	@ManyToOne
	private DictionaryData currency;
	
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

	@FieldName(name="租金调整")
	@Column(name="RENT_ADJUST",precision=22,scale=2)
	private BigDecimal rentAdjust;

	@FieldName(name="本金调整")
	@Column(name="CORPUS_ADJUST",precision=22,scale=2)
	private BigDecimal corpusAdjust;

	@FieldName(name="租息调整")
	@Column(name="INTEREST_ADJUST",precision=22,scale=2)
	private BigDecimal interestAdjust;

	@FieldName(name="罚息调整")
	@Column(name="PENALTY_ADJUST",precision=22,scale=2)
	private BigDecimal penaltyAdjust;

	@FieldName(name="付款来源")
	@Column(name="HIRE_SOURCE")
	private String hireSource;

	@FieldName(name="付款人")
	@Column(name="HIRE_OBJECT")
	private String hireObject;

	@FieldName(name="付款人银行")
	@Column(name="HIRE_BANK")
	private String hireBank;

	@FieldName(name="付款人帐户")
	@Column(name="HIRE_ACCOUNT")
	private String hireAccount;
	
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

	@FieldName(name="是否代付")
	@Column(name="INSTEAD_FLAG")
	private Integer insteadFlag;

	@FieldName(name="备注")
	@Column(name="MEMO")
	private String memo;

	@ManyToOne
	@FieldName(name="上传ID")
	@JoinColumn(name="UP_ID")
	private BaseFile upId;
	
	@FieldName(name="上传日期")
	@Column(name="UPLOAD_DATE",length=20)
	private String uploadDate;
	
	@FieldName(name="上传人")
	@JoinColumn(name="UPLOAD_CREATOR")
	@ManyToOne
	private User uploadCreator;

	@FieldName(name="开票人")
	@JoinColumn(name="INVOICE_PERSON")
	@ManyToOne
	private User invoicePerson;

	@FieldName(name="开票时间")
	@Column(name="INVOICE_DATE",length=20)
	private String invoiceDate;

	@FieldName(name="是否红冲或被红冲记录,0或空为正常核销记录，1是被红冲，-1 是红冲记录")
	@Column(name="ROLL_BACK",length=2)
	private String   rollBack;
	
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

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
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

	public BigDecimal getRentAdjust() {
		return rentAdjust;
	}

	public void setRentAdjust(BigDecimal rentAdjust) {
		this.rentAdjust = rentAdjust;
	}

	public BigDecimal getCorpusAdjust() {
		return corpusAdjust;
	}

	public void setCorpusAdjust(BigDecimal corpusAdjust) {
		this.corpusAdjust = corpusAdjust;
	}

	public BigDecimal getInterestAdjust() {
		return interestAdjust;
	}

	public void setInterestAdjust(BigDecimal interestAdjust) {
		this.interestAdjust = interestAdjust;
	}

	public BigDecimal getPenaltyAdjust() {
		return penaltyAdjust;
	}

	public void setPenaltyAdjust(BigDecimal penaltyAdjust) {
		this.penaltyAdjust = penaltyAdjust;
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

	public Integer getInsteadFlag() {
		return insteadFlag;
	}

	public void setInsteadFlag(Integer insteadFlag) {
		this.insteadFlag = insteadFlag;
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

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public User getUploadCreator() {
		return uploadCreator;
	}

	public void setUploadCreator(User uploadCreator) {
		this.uploadCreator = uploadCreator;
	}

	public User getInvoicePerson() {
		return invoicePerson;
	}

	public void setInvoicePerson(User invoicePerson) {
		this.invoicePerson = invoicePerson;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
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

	public ContractFundRentPlan getPlanId() {
		return planId;
	}

	public void setPlanId(ContractFundRentPlan planId) {
		this.planId = planId;
	}

	public FundEbankData getEbankNumber() {
		return ebankNumber;
	}

	public void setEbankNumber(FundEbankData ebankNumber) {
		this.ebankNumber = ebankNumber;
	}

	public String getRollBack() {
		return rollBack;
	}

	public void setRollBack(String rollBack) {
		this.rollBack = rollBack;
	}

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}

	public String getHireAccount() {
		return hireAccount;
	}

	public void setHireAccount(String hireAccount) {
		this.hireAccount = hireAccount;
	}
}
