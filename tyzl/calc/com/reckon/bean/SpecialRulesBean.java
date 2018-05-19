package com.reckon.bean;

import java.math.BigDecimal;

public class SpecialRulesBean {
	private Integer startList = 0;//开始期项
	private Integer endList = 0;//截止期项
	private String regular_settlemethod;//计算方式
	private BigDecimal rate;//本金占总本金比例
	private String regular_months;//间隔月数
	public Integer getStartList() {
		return startList;
	}
	public void setStartList(Integer startList) {
		this.startList = startList;
	}
	public Integer getEndList() {
		return endList;
	}
	public void setEndList(Integer endList) {
		this.endList = endList;
	}
	public String getRegular_settlemethod() {
		return regular_settlemethod;
	}
	public void setRegular_settlemethod(String regular_settlemethod) {
		this.regular_settlemethod = regular_settlemethod;
	}
	public String getRegular_months() {
		return regular_months;
	}
	public void setRegular_months(String regular_months) {
		this.regular_months = regular_months;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	
}
