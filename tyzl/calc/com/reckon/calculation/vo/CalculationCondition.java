package com.reckon.calculation.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.reckon.bean.ConditionBean;


public interface CalculationCondition {
	
	public String getId();

	public Date getStartDate();
	public Date getEndDate();
	public Date getLeaseAmtDate();
	public Date getFirstPlanDate();
	public Date getSecondPlanDate();

	public int getIncomeTimes();
	public int getLeaseTerm();
	public int getGrace();
	public int getPeriodType();
	public int getIssueRateReCal();
	public int getIssueNumber();
	public int getIsCalBeforeInterest();
	public CalculateType getCalculateType();
	
	public BigDecimal getLeaseAmt();
	public BigDecimal getYearRate();
	public BigDecimal getCleanLeaseMoney();
	public BigDecimal getNominalPrice();
	public BigDecimal getEquipEndValue();
	public BigDecimal getCautionMoney();
	public BigDecimal getHandlingChargeMoney();
	public BigDecimal getInsureMoney();
	public BigDecimal getManagementMoney();
	public BigDecimal getReturnAmt();
	public BigDecimal getCustCautionMoney();
	public BigDecimal getSupplierCautionMoney();
	public BigDecimal getExpectRent();
	public BigDecimal getGpsMoney();
	public BigDecimal getConsultingMoney();
	public BigDecimal getBeforeInterest();
	public BigDecimal getOtherIncome();
	public BigDecimal getOtherExpenditure();
	public BigDecimal getEquipAmt();
	public BigDecimal getFirstPayment();
	public BigDecimal getCautionDeductionMoney();
	public BigDecimal getExpectRentDeduction();

	public BigDecimal getIrr();
	public BigDecimal getPlanIrr();
	public BigDecimal getEndIrr();
	
	public BigDecimal getDefaultIssueRate();
	public BigDecimal getIssueRate();
	public BigDecimal getDayRate();
	public int getYearIncomeTimes();
	
	public void setLeaseAmt(BigDecimal newLeaseAmt);//租金调整时需要重设本金
	public void setFirstPlanDate(Date firstPlanDate);//租金调整时需要重设本金
	public void setStartDate(Date startDate);//租金调整时需要重设本金
	public void setGrace(int grace);//租金调整时需要重设本金
	public void setPeriodType(int PeriodType);//租金调整时需要重设本金
	public void setYearRate(BigDecimal yearRate);//租金调整时需要重设本金

	public void copyConditionBeanValues(ConditionBean cb) throws Exception;
}
