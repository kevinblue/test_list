package com.reckon.commons.bean;

import java.math.BigDecimal;

public class PBOCInfo {
	
	private String id;
	private String startDate;

	private BigDecimal baseRateSixMonth;
	private BigDecimal baseRateOnYear;
	private BigDecimal baseRateThreeYear;
	private BigDecimal baseRateFiveYear;
	private BigDecimal baseRateAboveFiveYear;

	private BigDecimal floatRateSixMonth;
	private BigDecimal floatRateOnYear;
	private BigDecimal floatRateThreeYear;
	private BigDecimal floatRateFiveYear;
	private BigDecimal floatRateAboveFiveYear;
	
	public BigDecimal getBaseRate(int leaseTerm){
		if (leaseTerm <= 6) {// 六个月以内
			return this.baseRateSixMonth;
		} else if (leaseTerm <= 12) {// 一年以内
			return this.baseRateOnYear;
		} else if (leaseTerm <= 36) {// 二年到三年之间时
			return this.baseRateThreeYear;
		} else if (leaseTerm <= 60) {// 四，五年之间时
			return this.baseRateFiveYear;
		} else {// 五年以上时
			return this.baseRateAboveFiveYear;
		}
	}
	
	public BigDecimal getFloatRate(int leaseTerm){
		if (leaseTerm <= 6) {// 六个月以内
			return this.floatRateSixMonth;
		} else if (leaseTerm <= 12) {// 一年以内
			return this.floatRateOnYear;
		} else if (leaseTerm <= 36) {// 二年到三年之间时
			return this.floatRateThreeYear;
		} else if (leaseTerm <= 60) {// 四，五年之间时
			return this.floatRateFiveYear;
		} else {// 五年以上时
			return this.floatRateAboveFiveYear;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public BigDecimal getBaseRateSixMonth() {
		return baseRateSixMonth;
	}

	public void setBaseRateSixMonth(BigDecimal baseRateSixMonth) {
		this.baseRateSixMonth = baseRateSixMonth;
	}

	public BigDecimal getBaseRateOnYear() {
		return baseRateOnYear;
	}

	public void setBaseRateOnYear(BigDecimal baseRateOnYear) {
		this.baseRateOnYear = baseRateOnYear;
	}

	public BigDecimal getBaseRateThreeYear() {
		return baseRateThreeYear;
	}

	public void setBaseRateThreeYear(BigDecimal baseRateThreeYear) {
		this.baseRateThreeYear = baseRateThreeYear;
	}

	public BigDecimal getBaseRateFiveYear() {
		return baseRateFiveYear;
	}

	public void setBaseRateFiveYear(BigDecimal baseRateFiveYear) {
		this.baseRateFiveYear = baseRateFiveYear;
	}

	public BigDecimal getBaseRateAboveFiveYear() {
		return baseRateAboveFiveYear;
	}

	public void setBaseRateAboveFiveYear(BigDecimal baseRateAboveFiveYear) {
		this.baseRateAboveFiveYear = baseRateAboveFiveYear;
	}

	public BigDecimal getFloatRateSixMonth() {
		return floatRateSixMonth;
	}

	public void setFloatRateSixMonth(BigDecimal floatRateSixMonth) {
		this.floatRateSixMonth = floatRateSixMonth;
	}

	public BigDecimal getFloatRateOnYear() {
		return floatRateOnYear;
	}

	public void setFloatRateOnYear(BigDecimal floatRateOnYear) {
		this.floatRateOnYear = floatRateOnYear;
	}

	public BigDecimal getFloatRateThreeYear() {
		return floatRateThreeYear;
	}

	public void setFloatRateThreeYear(BigDecimal floatRateThreeYear) {
		this.floatRateThreeYear = floatRateThreeYear;
	}

	public BigDecimal getFloatRateFiveYear() {
		return floatRateFiveYear;
	}

	public void setFloatRateFiveYear(BigDecimal floatRateFiveYear) {
		this.floatRateFiveYear = floatRateFiveYear;
	}

	public BigDecimal getFloatRateAboveFiveYear() {
		return floatRateAboveFiveYear;
	}

	public void setFloatRateAboveFiveYear(BigDecimal floatRateAboveFiveYear) {
		this.floatRateAboveFiveYear = floatRateAboveFiveYear;
	}
}