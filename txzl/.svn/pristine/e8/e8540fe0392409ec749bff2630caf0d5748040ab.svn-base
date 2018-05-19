package com.tenwa.leasing.entity.finacial;

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

@Entity
@FieldName(name = "提款信息")
@Table(name = "LOAN_ATM_PLAN")
public class LoanAtmPlan {
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
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "币种")
	@JoinColumn(name = "CURRENCY")
	private DictionaryData currency;

	@FieldName(name="应付金额")
	@Column(name="PLAN_MONEY",precision=22,scale=2)
	private BigDecimal planMoney;

	@FieldName(name="应付日期")
	@Column(name="PLAN_DATE",length=20)
	private String planDate;


	@FieldName(name="备注")
	@Column(name="MEMO",length=1000)
	private String memo;
	
	@ManyToOne
	@FieldName(name = "创建人")
	@JoinColumn(name = "CREATOR_")
	private User creator;

	@FieldName(name = "创建时间")
	@Column(name = "CREATE_DATE", length = 20)
	private String createDate;

	@ManyToOne
	@FieldName(name = "修改人")
	@JoinColumn(name = "MODIFICATOR_")
	private User modificator;

	@FieldName(name = "修改时间")
	@Column(name = "MODIFY_DATE", length = 20)
	private String modifyDate;
	

	@FieldName(name="提款银行")
	@Column(name="ACC_BANK")
	private String accBank;

	@FieldName(name="提款账户")
	@Column(name="ACC_NAME")
	private String accName;
	
	@FieldName(name="提款帐号")
	@Column(name="ACC_NUMBER")
	private String accNumber;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public LoanAccount getLoanid() {
		return loanid;
	}

	public void setLoanid(LoanAccount loanid) {
		this.loanid = loanid;
	}



	public DictionaryData getCurrency() {
		return currency;
	}

	public void setCurrency(DictionaryData currency) {
		this.currency = currency;
	}

	public BigDecimal getPlanMoney() {
		return planMoney;
	}

	public void setPlanMoney(BigDecimal planMoney) {
		this.planMoney = planMoney;
	}

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}



	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public String getAccBank() {
		return accBank;
	}

	public void setAccBank(String accBank) {
		this.accBank = accBank;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}

}
