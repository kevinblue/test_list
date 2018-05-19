package com.tenwa.leasing.controller.voucher;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
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
import com.tenwa.leasing.entity.fund.ContractFundFundCharge;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;
import com.tenwa.leasing.entity.fund.ContractFundRentInCome;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;

/**
 * 
 * @author SEA
 * @date 2013-7-28下午03:23:41
 * @info 月末调整生成记录
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "月末调整凭证信息")
@Table(name="VOUCHER_MONTH_END_ADJUST")
public class VoucherMonthendAdjust {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@ManyToOne
	@FieldName(name="月末调整保证金记录")
	@JoinColumn(name="CAUTION_MONEY_ID")
	private ContractFundFundPlan cautionMoneyId;
	
	@ManyToOne
	@FieldName(name="租金实收凭证记录")
	@JoinColumn(name="RENT_INMCOME_ID")
	private ContractFundRentInCome rentIncomeId;
	
	@FieldName(name = "凭证,开票类型")
	@Column(name="INCOME_TYPE")
	private String incomeType;
	
	@ManyToOne
	@FieldName(name="租金计划开票凭证记录")
	@JoinColumn(name="RENT_PLAN_ID")
	private ContractFundRentPlan rentPlanId;
	
	@ManyToOne
	@FieldName(name="资金实收开票凭证记录")
	@JoinColumn(name="FUND_INCOME_ID")
	private ContractFundFundCharge fundIncomeId;
	
	@ManyToOne
	@FieldName(name="资金计划开票凭证记录")
	@JoinColumn(name="FUND_PLAN_ID")
	private ContractFundFundPlan fundPlanId;
	
	@ManyToOne
	@FieldName(name="确认利息记录(拆分)")
	@JoinColumn(name="TAXES_ID")
	private ContractFundRentPlan taxesId;
	
	@ManyToOne
	@FieldName(name="确认租金")
	@JoinColumn(name="RENT_ID")
	private ContractFundRentPlan rentId;
	
	@FieldName(name = "发票开票记录")
	@Column(name="BILL_RECORD")
	private String billrecord;
 
	@ManyToOne
	@FieldName(name = "登记人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name = "登记时间")
	@Column(name="CREATE_DATE", length=20)
	private String createDate;
	
	@ManyToOne
	@FieldName(name = "更新人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name = "更新日期")
	@Column(name="MODIFY_DATE", length=20)
	private String modifyDate;
	
	@FieldName(name = "凭证类型")
	@Column(name="VOUCHER_TYPE", length=20)
	private String voucherType;
	
	@FieldName(name = "计提月份")
	@Column(name="MONTH", length=20)
	private String month;
	
	@FieldName(name = "利息金额")
	@Column(name="MONEY", length=20)
	private BigDecimal money;
	
	@FieldName(name = "租金的税金额")
	@Column(name="TAX", length=20)
	private BigDecimal tax;
	
	@FieldName(name = "是否生成凭证")
	@Column(name="IS_HAPPEN", length=20)
	private String isHappen;
	
	public String getBillrecord() {
		return billrecord;
	}

	public void setBillrecord(String billrecord) {
		this.billrecord = billrecord;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ContractFundFundPlan getCautionMoneyId() {
		return cautionMoneyId;
	}

	public void setCautionMoneyId(ContractFundFundPlan cautionMoneyId) {
		this.cautionMoneyId = cautionMoneyId;
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

	public ContractFundRentPlan getTaxesId() {
		return taxesId;
	}

	public void setTaxesId(ContractFundRentPlan taxesId) {
		this.taxesId = taxesId;
	}

	public String getVoucherType() {
		return voucherType;
	}

	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getIsHappen() {
		return isHappen;
	}

	public void setIsHappen(String isHappen) {
		this.isHappen = isHappen;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public ContractFundRentInCome getRentIncomeId() {
		return rentIncomeId;
	}

	public void setRentIncomeId(ContractFundRentInCome rentIncomeId) {
		this.rentIncomeId = rentIncomeId;
	}

	public ContractFundRentPlan getRentPlanId() {
		return rentPlanId;
	}

	public void setRentPlanId(ContractFundRentPlan rentPlanId) {
		this.rentPlanId = rentPlanId;
	}

	public ContractFundFundCharge getFundIncomeId() {
		return fundIncomeId;
	}

	public void setFundIncomeId(ContractFundFundCharge fundIncomeId) {
		this.fundIncomeId = fundIncomeId;
	}

	public ContractFundFundPlan getFundPlanId() {
		return fundPlanId;
	}

	public void setFundPlanId(ContractFundFundPlan fundPlanId) {
		this.fundPlanId = fundPlanId;
	}

	public ContractFundRentPlan getRentId() {
		return rentId;
	}

	public void setRentId(ContractFundRentPlan rentId) {
		this.rentId = rentId;
	}

	public String getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}
	
	
}
