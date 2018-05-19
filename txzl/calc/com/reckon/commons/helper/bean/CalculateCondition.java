package com.reckon.commons.helper.bean;

import java.math.BigDecimal;

import com.reckon.commons.base.Condition;
import com.reckon.commons.helper.Scale;
import com.reckon.commons.util.DateTools;

public class CalculateCondition {

	private String startDate = null; // 起租日期
	private String leaseAmtDate = null; // 设备款支付日期
	private String firstPlanDate = null; // 第一期租金支付日
	private String secondPlanDate = null; // 第二期租金支付日

	private int incomeTimes = 12; // 总还租期数
	private int issueNumber = 1; // 每期几个月
	private int grace = 0; // 宽限期数，只收利息，由于本金不变所以利息相同
	private int periodType = 1; // 支付时机：期初0/期末1

	private BigDecimal leaseMoney = BigDecimal.ZERO;// 融资款
	private BigDecimal yearRate = BigDecimal.ZERO; // 租赁年利率
	private BigDecimal endMoney = BigDecimal.ZERO; // 设备残值，期末余值

	public CalculateCondition(Condition cb) throws Exception {
		this.startDate = cb.getStartDate();// 起租日期，计算最后一期的还款日期
		this.leaseAmtDate = cb.getLeaseAmtDate(); // 设备款支付日期
		this.firstPlanDate = cb.getFirstPlanDate(); // 第1期租金支付日
		this.secondPlanDate = cb.getSecondPlanDate(); // 第2期租金支付日

		this.incomeTimes = cb.getIncomeNumber(); // 还租期数
		this.issueNumber = Integer.parseInt(cb.getIssueNumber().toString()); // 每期几个月
		this.grace = cb.getGrace(); // 宽限期数，只收利息，由于本金不变所以利息相同
		this.periodType = Integer.parseInt(cb.getPeriodType().toString().replace("period_type_", "")); // 支付时机：期初0/期末1

		this.leaseMoney = cb.getLeaseMoney(); // 融资款
		this.yearRate = cb.getYearRate().divide(new BigDecimal(100), Scale.RATE_SCALE, BigDecimal.ROUND_HALF_UP); // 租赁年利率
		this.endMoney = cb.getEquipEndValue(); // 设备残值，期末余值
	}

	public int getLeaseTerm() {
		return (this.incomeTimes + this.grace) * this.issueNumber;
	}

	public int getYearIncomeTimes() {
		int issueNumber = this.getIssueNumber();// 每期几个月
		return 12 / issueNumber;// 每年期数
	}

	/**
	 * 获得每天利率，根据年利率等值计算，默认一年360天
	 * 
	 * @param condition
	 * @return
	 */
	public BigDecimal getDayRate() {
		BigDecimal yearRate = this.getYearRate();// 年利率
		BigDecimal dayRate = yearRate.divide(new BigDecimal(360), Scale.RATE_SCALE, BigDecimal.ROUND_HALF_UP);
		return dayRate;
	}

	/**
	 * 同一Condition下，换一个yearRate来计算天利率
	 * 
	 * @param condition
	 * @return
	 */
	public BigDecimal getDayRate(BigDecimal yearRate) {
		BigDecimal dayRate = yearRate.divide(new BigDecimal(360), Scale.RATE_SCALE, BigDecimal.ROUND_HALF_UP);
		return dayRate;
	}

	/**
	 * 获得每天利率，根据年利率等值计算，默认一年360天
	 * 
	 * @param condition
	 * @return
	 */
	public BigDecimal getMonthRate() {
		BigDecimal yearRate = this.getYearRate();// 年利率
		BigDecimal dayRate = yearRate.divide(new BigDecimal(12), Scale.RATE_SCALE, BigDecimal.ROUND_HALF_UP);
		return dayRate;
	}

	/**
	 * 同一Condition下，换一个yearRate来计算天利率
	 * 
	 * @param condition
	 * @return
	 */
	public BigDecimal getMonthRate(BigDecimal yearRate) {
		BigDecimal dayRate = yearRate.divide(new BigDecimal(12), Scale.RATE_SCALE, BigDecimal.ROUND_HALF_UP);
		return dayRate;
	}

	/**
	 * 合同本身的期利率
	 * 
	 * @return
	 */
	public BigDecimal getIssueRate() {
		BigDecimal yearRate = this.getYearRate();// 年利率
		int yearIncomeTimes = getYearIncomeTimes();// 每年期数
		return yearRate.divide(new BigDecimal(yearIncomeTimes), 20, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 同一Condition下，换一个yearRate来计算期利率
	 * 
	 * @param yearRate
	 * @return
	 */
	public BigDecimal getIssueRate(BigDecimal yearRate) {
		int yearIncomeTimes = getYearIncomeTimes();// 每年期数
		return yearRate.divide(new BigDecimal(yearIncomeTimes), 20, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal getBeforeInterest() {
		long dateDiff = DateTools.getDateDiff(leaseAmtDate, startDate);
		BigDecimal dayRate = this.getDayRate();
		return this.getLeaseMoney().multiply(dayRate).multiply(new BigDecimal(dateDiff));
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public BigDecimal getLeaseMoney() {
		return leaseMoney;
	}

	public void setLeaseMoney(BigDecimal leaseMoney) {
		this.leaseMoney = leaseMoney;
	}

	public BigDecimal getYearRate() {
		return yearRate;
	}

	public void setYearRate(BigDecimal yearRate) {
		this.yearRate = yearRate;
	}

	public int getGrace() {
		return grace;
	}

	public void setGrace(int grace) {
		this.grace = grace;
	}

	public BigDecimal getEndMoney() {
		if (endMoney == null) {
			endMoney = BigDecimal.ZERO;
		}
		return endMoney;
	}

	public void setEndMoney(BigDecimal endMoney) {
		this.endMoney = endMoney;
	}

	public int getPeriodType() {
		return periodType;
	}

	public void setPeriodType(int periodType) {
		this.periodType = periodType;
	}

	public String getLeaseAmtDate() {
		return leaseAmtDate;
	}

	public void setLeaseAmtDate(String leaseAmtDate) {
		this.leaseAmtDate = leaseAmtDate;
	}

	public String getFirstPlanDate() {
		return firstPlanDate;
	}

	public void setFirstPlanDate(String firstPlanDate) {
		this.firstPlanDate = firstPlanDate;
	}

	public int getIncomeTimes() {
		return incomeTimes;
	}

	public void setIncomeTimes(int incomeTimes) {
		this.incomeTimes = incomeTimes;
	}

	public int getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(int issueNumber) {
		this.issueNumber = issueNumber;
	}

	public String getSecondPlanDate() {
		return secondPlanDate;
	}

	public void setSecondPlanDate(String secondPlanDate) {
		this.secondPlanDate = secondPlanDate;
	}
}
