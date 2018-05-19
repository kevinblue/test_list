package com.tenwa.leasing.entity.finacial;

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






import com.reckon.calculation.utils.Scale;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.file.BaseFile;
import com.tenwa.leasing.entity.proj.ProjInfo;

/**
 * 
 * @author LYF
 * @date 2016-9-23下午09:33:10
 * @info 借款台帐
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "借款台帐")
@Table(name="LOAN_ACCOUNT")
public class LoanAccount {
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	

	@FieldName(name="借款单位")
	@Column(name="LOAN_UNIT",length=50)
	private String loanUnit ;
	
	
	@FieldName(name="借款类别")
	@Column(name="LOAN_TYPE", length=50)
	private String loanType;
	
	@FieldName(name="借款合同编号")
	@Column(name="LOAN_CONTRACT_ID",length=50)
	private String loanContractID;
	

	@FieldName(name="借款本金")
	@Column(name="CORPUS",precision = 22, scale = 2)
	private BigDecimal corpus;
	

	@FieldName(name="借款期限")
	@Column(name="LOAN_TERM", precision = 22,scale = Scale.DEFAULT)
	private BigDecimal loanTerm;
	
	
	@FieldName(name="借款期限")
	@Column(name="LOAN_TERM_NEW", length=20)
	private String loanTermNew;
	
	@FieldName(name="合同利率")
	@Column(name="YEAR_RATE", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal yearRate;
	
	@FieldName(name="当前执行利率")
	@Column(name="NOW_RATE", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal nowRate;
	
	@FieldName(name="保证金")
	@Column(name="CAUTION_MONEY", precision = 22,scale = Scale.DEFAULT)
	private BigDecimal cautionMoney; 
	
	@ManyToOne
	@FieldName(name="还款方式")
	@JoinColumn(name="PAYMENT_TYPE")
	private DictionaryData paymentType;
	
	@FieldName(name="借款日")
	@Column(name="LOAN_DATE", length=20)	
	private String loanDate;
	
	@FieldName(name="到期日")
	@Column(name="EXPIRE_DATE", length=20)	
	private String expireDate;
	
	@FieldName(name="付款日")
	@Column(name="PAY_DATE", length=20)	
	private String payDate;
	
	@FieldName(name = "借款项目")
	@OneToOne(targetEntity = ContractInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTRACT_ID")
	private ContractInfo contractId;
	

	@FieldName(name="担保抵押情况")
	@Column(name="MORTGAGE_DETAIL")
	private String mortgageDetail;
	
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

	public String getLoanUnit() {
		return loanUnit;
	}

	public void setLoanUnit(String loanUnit) {
		this.loanUnit = loanUnit;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getLoanContractID() {
		return loanContractID;
	}

	public void setLoanContractID(String loanContractID) {
		this.loanContractID = loanContractID;
	}

	public BigDecimal getCorpus() {
		return corpus;
	}

	public void setCorpus(BigDecimal corpus) {
		this.corpus = corpus;
	}

	

	public BigDecimal getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(BigDecimal loanTerm) {
		this.loanTerm = loanTerm;
	}

	public BigDecimal getYearRate() {
		return yearRate;
	}

	public void setYearRate(BigDecimal yearRate) {
		this.yearRate = yearRate;
	}

	public BigDecimal getCautionMoney() {
		return cautionMoney;
	}

	public void setCautionMoney(BigDecimal cautionMoney) {
		this.cautionMoney = cautionMoney;
	}

	public DictionaryData getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(DictionaryData paymentType) {
		this.paymentType = paymentType;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public String getMortgageDetail() {
		return mortgageDetail;
	}

	public void setMortgageDetail(String mortgageDetail) {
		this.mortgageDetail = mortgageDetail;
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

	public BigDecimal getNowRate() {
		return nowRate;
	}

	public void setNowRate(BigDecimal nowRate) {
		this.nowRate = nowRate;
	}

	public String getLoanTermNew() {
		return loanTermNew;
	}

	public void setLoanTermNew(String loanTermNew) {
		this.loanTermNew = loanTermNew;
	}
	
}
