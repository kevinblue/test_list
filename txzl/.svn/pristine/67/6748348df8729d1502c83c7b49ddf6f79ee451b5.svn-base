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
import com.tenwa.leasing.entity.file.BaseFile;

@Entity
@FieldName(name = "融资费信息")
@Table(name = "LOAN_FEET_INCOME")
public class LoanFeeIncome {
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
	@FieldName(name = "融资费ID")
	@JoinColumn(name = "PLAN_ID")
	private LoanFeePlan planId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "币种")
	@JoinColumn(name = "CURRENCY")
	private DictionaryData currency;

	@FieldName(name="实付金额")
	@Column(name="FACT_MONEY",precision=22,scale=2)
	private BigDecimal factMoney;

	@FieldName(name="实付日期")
	@Column(name="FACT_DATE",length=20)
	private String factDate;

	@FieldName(name="调整金额")
	@Column(name="ADJUST_MONEY",precision=22,scale=2)
	private BigDecimal adjustMoney;
	
	@FieldName(name="是否红冲或被红冲记录")
	@Column(name="ROLL_BACK",length=2)
	private String rollBack;
    
	@FieldName(name = "上传文件名")
	@ManyToOne(targetEntity = BaseFile.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "UP_ID")
	private BaseFile upLoadId;
	
	
	

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

	public LoanFeePlan getPlanId() {
		return planId;
	}

	public void setPlanId(LoanFeePlan planId) {
		this.planId = planId;
	}

	public DictionaryData getCurrency() {
		return currency;
	}

	public void setCurrency(DictionaryData currency) {
		this.currency = currency;
	}

	public BigDecimal getFactMoney() {
		return factMoney;
	}

	public void setFactMoney(BigDecimal factMoney) {
		this.factMoney = factMoney;
	}

	public String getFactDate() {
		return factDate;
	}

	public void setFactDate(String factDate) {
		this.factDate = factDate;
	}

	public BigDecimal getAdjustMoney() {
		return adjustMoney;
	}

	public void setAdjustMoney(BigDecimal adjustMoney) {
		this.adjustMoney = adjustMoney;
	}

	public String getRollBack() {
		return rollBack;
	}

	public void setRollBack(String rollBack) {
		this.rollBack = rollBack;
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
	public BaseFile getUpLoadId() {
		return upLoadId;
	}

	public void setUpLoadId(BaseFile upLoadId) {
		this.upLoadId = upLoadId;
	}

}
