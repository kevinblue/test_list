package com.reckon.commons.bean;

import java.util.ArrayList;
import java.util.List;

import com.reckon.commons.base.CashDetail;
import com.reckon.commons.base.Condition;
import com.reckon.commons.base.FundPlan;
import com.reckon.commons.base.RentPlan;

public class PlanContext {

	private Condition condition;

	private List<? extends RentPlan> rentPlan;

	private List<? extends FundPlan> fundPlan;

	private List<? extends CashDetail> cashFlow;

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public List<? extends FundPlan> getFundPlan() {
		fundPlan = (fundPlan != null ? fundPlan : new ArrayList<FundPlan>());
		return fundPlan;
	}

	public <T extends FundPlan> void setFundPlan(List<T> fundPlan) {
		if(fundPlan != null){
			this.fundPlan = fundPlan;
		}
	}

	public List<? extends RentPlan> getRentPlan() {
		rentPlan = (rentPlan != null ? rentPlan : new ArrayList<RentPlan>());
		return rentPlan;
	}

	public <T extends RentPlan> void setRentPlan(List<T> rentPlan) {
		if(rentPlan != null){
			this.rentPlan = rentPlan;
		}
	}

	public List<? extends CashDetail> getCashFlow() {
		cashFlow = (cashFlow != null ? cashFlow : new ArrayList<CashDetail>());
		return cashFlow;
	}

	public <T extends CashDetail> void setCashFlow(List<T> cashFlow) {
		if(cashFlow != null){
			this.cashFlow = cashFlow;
		}
	}
}
