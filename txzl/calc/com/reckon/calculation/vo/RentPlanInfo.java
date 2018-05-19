package com.reckon.calculation.vo;

import java.math.BigDecimal;
import java.util.Date;

public class RentPlanInfo {
	
	private String businessId;//业务ID，此份数据的唯一标识
	
	private int id;//期数
	private Date startDate;//日期
	private Date endDate;//日期
	private BigDecimal rent = BigDecimal.ZERO;//租金
	
	private BigDecimal financeCorpus = BigDecimal.ZERO;//财务本金
	private BigDecimal financeInterest = BigDecimal.ZERO;//财务利息
	private BigDecimal financeRemain = BigDecimal.ZERO;//财务本金余额，计息本金
	
	private BigDecimal businessCorpus = BigDecimal.ZERO;//业务本金
	private BigDecimal businessInterest = BigDecimal.ZERO;//业务利息
	private BigDecimal businessRemain = BigDecimal.ZERO;//业务本金余额，计息本金
	
	private BigDecimal corpusAdjust = BigDecimal.ZERO;//本金调整额
	private BigDecimal interestAdjust = BigDecimal.ZERO;//利息调整额
	private BigDecimal rentAdjust = BigDecimal.ZERO;//租金调整额
	
	private BigDecimal issueRate = BigDecimal.ZERO;//本期计息利率，业务的

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getRent() {
		return rent;
	}

	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}

	public BigDecimal getFinanceCorpus() {
		return financeCorpus;
	}

	public void setFinanceCorpus(BigDecimal financeCorpus) {
		this.financeCorpus = financeCorpus;
	}

	public BigDecimal getFinanceInterest() {
		return financeInterest;
	}

	public void setFinanceInterest(BigDecimal financeInterest) {
		this.financeInterest = financeInterest;
	}

	public BigDecimal getBusinessCorpus() {
		return businessCorpus;
	}

	public void setBusinessCorpus(BigDecimal businessCorpus) {
		this.businessCorpus = businessCorpus;
	}

	public BigDecimal getBusinessInterest() {
		return businessInterest;
	}

	public void setBusinessInterest(BigDecimal businessInterest) {
		this.businessInterest = businessInterest;
	}

	public BigDecimal getCorpusAdjust() {
		return corpusAdjust;
	}

	public void setCorpusAdjust(BigDecimal corpusAdjust) {
		this.corpusAdjust = corpusAdjust;
	}

	public BigDecimal getInterestAdjust() {
		return interestAdjust;
	}

	public void setInterestAdjust(BigDecimal interestAdjust) {
		this.interestAdjust = interestAdjust;
	}

	public BigDecimal getRentAdjust() {
		return rentAdjust;
	}

	public void setRentAdjust(BigDecimal rentAdjust) {
		this.rentAdjust = rentAdjust;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public BigDecimal getFinanceRemain() {
		return financeRemain;
	}

	public void setFinanceRemain(BigDecimal financeRemin) {
		this.financeRemain = financeRemin;
	}

	public BigDecimal getBusinessRemain() {
		return businessRemain;
	}

	public void setBusinessRemain(BigDecimal businessRemin) {
		this.businessRemain = businessRemin;
	}

	public BigDecimal getIssueRate() {
		return issueRate;
	}

	public void setIssueRate(BigDecimal issueRate) {
		this.issueRate = issueRate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
