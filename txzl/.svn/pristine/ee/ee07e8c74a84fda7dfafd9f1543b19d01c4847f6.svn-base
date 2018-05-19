package com.tenwa.leasing.entity.fund;

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

/**
 * 
 * @author ykl
 * @date 2017-3-2下午05:51:06
 * @info 财务收入折现
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "财务收入折现临时表")
@Table(name="FINANCE_INCOME_DISCOUNT_TEMP")
public class FinanceIncomeDiscountTemp {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="合同号")
	@Column(name="CONTRACT_ID")
	private String contractId;
	
	@FieldName(name="流程编号")
	@Column(name="DOC_ID")
	private String docId;
	
	@FieldName(name="每期现金流")
	@Column(name="CASH_DETAIL")
	private String cashDetail;
	
	@FieldName(name="融资收入(含税)")
	@Column(name="FINANCE_INCOME_WITH_TAX")
	private String financeIncomeWithTax;
	
	@FieldName(name="融资收入(不含税)")
	@Column(name="FINANCE_INCOME_WITHOUT_TAX")
	private String financeIncomeWithoutTax;
	
	@FieldName(name="投资净额减少额")
	@Column(name="INVESTMENT_DECREASE")
	private String investmentDecrease;
	
	@FieldName(name="投资净额余额")
	@Column(name="OVER_INVESTMENT_DECREASE")
	private String overInvestmentDecrease;
	
	@FieldName(name="剩余应收款")
	@Column(name="OVER_DUE_ACCOUNTS")
	private String overDueAccounts;
	
	@FieldName(name="未确认融资收益")
	@Column(name="UNCONFIRMED_PROFIT")
	private String unconfirmedProfit;
	
	@FieldName(name="记账日期")
	@Column(name="ACOUNT_DATE")
	private String acountDate;
	
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

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
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

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getCashDetail() {
		return cashDetail;
	}

	public void setCashDetail(String cashDetail) {
		this.cashDetail = cashDetail;
	}

	public String getFinanceIncomeWithTax() {
		return financeIncomeWithTax;
	}

	public void setFinanceIncomeWithTax(String financeIncomeWithTax) {
		this.financeIncomeWithTax = financeIncomeWithTax;
	}

	public String getFinanceIncomeWithoutTax() {
		return financeIncomeWithoutTax;
	}

	public void setFinanceIncomeWithoutTax(String financeIncomeWithoutTax) {
		this.financeIncomeWithoutTax = financeIncomeWithoutTax;
	}

	public String getInvestmentDecrease() {
		return investmentDecrease;
	}

	public void setInvestmentDecrease(String investmentDecrease) {
		this.investmentDecrease = investmentDecrease;
	}

	public String getOverInvestmentDecrease() {
		return overInvestmentDecrease;
	}

	public void setOverInvestmentDecrease(String overInvestmentDecrease) {
		this.overInvestmentDecrease = overInvestmentDecrease;
	}

	public String getOverDueAccounts() {
		return overDueAccounts;
	}

	public void setOverDueAccounts(String overDueAccounts) {
		this.overDueAccounts = overDueAccounts;
	}

	public String getUnconfirmedProfit() {
		return unconfirmedProfit;
	}

	public void setUnconfirmedProfit(String unconfirmedProfit) {
		this.unconfirmedProfit = unconfirmedProfit;
	}

	public String getAcountDate() {
		return acountDate;
	}

	public void setAcountDate(String acountDate) {
		this.acountDate = acountDate;
	}
	
}
