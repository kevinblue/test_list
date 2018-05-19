package com.tenwa.leasing.entity.finacial;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OrderBy;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.file.BaseFile;

@Entity
@FieldName(name = "租金计划（利息）")
@Table(name = "LOAN_RENT_PLAN_TO_INTEREST")
public class LoanRentPlanToInterest {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	@FieldName(name="标识符")
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "借款合同号")
	@JoinColumn(name = "LOAN_ID")
	private LoanAccount loanid;
	
	@FieldName(name="摘要")
	@Column(name="PLAN_LIST" , length = 500)
	private String planList;
	
	@FieldName(name="起息日")
	@Column(name="PLAN_DATE",length=255)
	private String planDate;
	
	@FieldName(name="止息日")
	@Column(name="YPAY_DATE",length=255)
	private String ypaydate;

	@FieldName(name="余额")
	@Column(name="BALANCE",length=100)
	private String balance;
	
	@FieldName(name="利率")
	@Column(name="INTEREST_RATE",length=50)
	private String interestrate;
	
	@FieldName(name="利息费用")
	@Column(name="INTEREST",precision=22,scale=2)
	private BigDecimal interest;

	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "币种")
	@JoinColumn(name = "CURRENCY")
	private DictionaryData currency;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "是否支付利息")
	@JoinColumn(name = "PAY_INTEREST")
	private DictionaryData payinterest;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "是否开发票")
	@JoinColumn(name = "OPEN_INVOICE" )
	private DictionaryData openinvoice;
    	
	@FieldName(name = "上传文件名")
	@ManyToOne(targetEntity = BaseFile.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "UP_ID")
	private BaseFile upLoadId;
	
	@ManyToOne
	@FieldName(name = "创建人")
	@JoinColumn(name = "CREATOR_")
	private User creator;

	@FieldName(name = "创建时间")
	@Column(name = "CREATE_DATE", length = 255)
	private String createDate;

	@ManyToOne
	@FieldName(name = "修改人")
	@JoinColumn(name = "MODIFICATOR_")
	private User modificator;

	@FieldName(name = "修改时间")
	@Column(name = "MODIFY_DATE", length = 255)
	private String modifyDate;
	
	@Transient
	@FieldName(name="当期租金余额")
	private BigDecimal rentOverage;
	
	@Transient
	@FieldName(name="当期本金余额")
	private BigDecimal corpusOverage;
	
	@Transient
	@FieldName(name="当期利息余额")
	private BigDecimal interestOverage;
	
	@FieldName(name = "租金实收")
	@OneToMany(mappedBy = "planId", fetch = FetchType.LAZY)
	@OrderBy(clause = "HIRE_DATE asc")
	private Set<LoanRentIncomeToInterest> loanRentIncomeToInterest = new HashSet<LoanRentIncomeToInterest>();
    
	
	
	@FieldName(name="备注")
	@Column(name="MENO",length=1000)
	private String meno;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getPlanList() {
		return planList;
	}

	public void setPlanList(String planList) {
		this.planList = planList;
	}



	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}
		
	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public DictionaryData getCurrency() {
		return currency;
	}

	public void setCurrency(DictionaryData currency) {
		this.currency = currency;
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

	public BigDecimal getRentOverage() {
		return rentOverage;
	}

	public void setRentOverage(BigDecimal rentOverage) {
		this.rentOverage = rentOverage;
	}

	public BigDecimal getCorpusOverage() {
		return corpusOverage;
	}

	public void setCorpusOverage(BigDecimal corpusOverage) {
		this.corpusOverage = corpusOverage;
	}

	public BigDecimal getInterestOverage() {
		return interestOverage;
	}

	public void setInterestOverage(BigDecimal interestOverage) {
		this.interestOverage = interestOverage;
	}

	public LoanAccount getLoanid() {
		return loanid;
	}

	public void setLoanid(LoanAccount loanid) {
		this.loanid = loanid;
	}



	public Set<LoanRentIncomeToInterest> getLoanRentIncomeToInterest() {
		return loanRentIncomeToInterest;
	}

	public void setLoanRentIncomeToInterest(Set<LoanRentIncomeToInterest> loanRentIncomeToInterest) {
		this.loanRentIncomeToInterest = loanRentIncomeToInterest;
	}

	public String getMeno() {
		return meno;
	}

	public void setMeno(String meno) {
		this.meno = meno;
	}
    
	
	public BaseFile getUpLoadId() {
		return upLoadId;
	}

	public void setUpLoadId(BaseFile upLoadId) {
		this.upLoadId = upLoadId;
	}
	public DictionaryData getPayinterest() {
		return payinterest;
	}

	public void setPayinterest(DictionaryData payinterest) {
		this.payinterest = payinterest;
	}

	public DictionaryData getOpeninvoice() {
		return openinvoice;
	}

	public void setOpeninvoice(DictionaryData openinvoice) {
		this.openinvoice = openinvoice;
	}

	
	public String getYpaydate() {
		return ypaydate;
	}

	public void setYpaydate(String ypaydate) {
		this.ypaydate = ypaydate;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getInterestrate() {
		return interestrate;
	}

	public void setInterestrate(String interestrate) {
		this.interestrate = interestrate;
	}

	
}
