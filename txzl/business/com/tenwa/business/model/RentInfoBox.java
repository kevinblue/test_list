package com.tenwa.business.model;
import java.util.ArrayList;
import java.util.List;
public class RentInfoBox {
	List<String> planDate = new ArrayList<String>();
	List<String> rent = new ArrayList<String>();
	List<String> corpus = new ArrayList<String>();
	List<String> interest = new ArrayList<String>();
	List<String> corpusOverge = new ArrayList<String>();
	
	List<RentCashBean> rentDetails = new ArrayList<RentCashBean>();
//	List<RentInshareBean> lRentInshare = new ArrayList<RentInshareBean>();// Jaffe
																			// 2012-8-13
	String irr = "";
	public List<String> getPlanDate() {
		return planDate;
	}
	public void setPlanDate(List<String> planDate) {
		this.planDate = planDate;
	}
	public List<String> getRent() {
		return rent;
	}
	public void setRent(List<String> rent) {
		this.rent = rent;
	}
	public List<String> getCorpus() {
		return corpus;
	}
	public void setCorpus(List<String> corpus) {
		this.corpus = corpus;
	}
	public List<String> getInterest() {
		return interest;
	}
	public void setInterest(List<String> interest) {
		this.interest = interest;
	}
	public List<String> getCorpusOverge() {
		return corpusOverge;
	}
	public void setCorpusOverge(List<String> corpusOverge) {
		this.corpusOverge = corpusOverge;
	}
	public List<RentCashBean> getRentDetails() {
		return rentDetails;
	}
	public void setRentDetails(List<RentCashBean> rentDetails) {
		this.rentDetails = rentDetails;
	}
	public String getIrr() {
		return irr;
	}
	public void setIrr(String irr) {
		this.irr = irr;
	}
	
    
}