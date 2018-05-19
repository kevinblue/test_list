package com.reckon.bean;

import java.util.ArrayList;
import java.util.List;

import com.reckon.bean.RentPlan;

/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-2-17
 * @desc ( 租金计划传值载体bean)
 */
public class FundRentPlanBean {

	// 以下属性用于添加时用
	private String yearRate = "0"; // 年利率

	private List<String> planDateList = new ArrayList<String>(); // 租金计划日期
	private List<String> yearRateList = new ArrayList<String>();// 年利率
	private List<String> rentList = new ArrayList<String>(); // 租金列表
	private List<String> allRemainRentList = new ArrayList<String>(); // 剩余租金列表

	private List<String> corpusBusinessList = new ArrayList<String>(); // 本金列表
	private List<String> interestBusinessList = new ArrayList<String>(); // 利息列表
	private List<String> corpusOverageBusinessList = new ArrayList<String>(); // 本金余额列表
	private List<String> cautionmoneyRemainList = new ArrayList<String>(); //保证金退还(保理测算)列表

	private List<String> corpusFinacList = new ArrayList<String>(); // 财务本金列表
	private List<String> interestFinacList = new ArrayList<String>(); // 财务利息列表
	private List<String> corpusOverageFinacList = new ArrayList<String>(); // 财务本金余额列表
	private List<String> handlingChargeList = new ArrayList<String>(); // 手续费均摊列表
	

	private List<String> column_1 = new ArrayList<String>();// 老的财务本金
	private List<String> column_2 = new ArrayList<String>();// 老的财务利息

	private List<String> rentAdjustList = new ArrayList<String>(); // 租金调整值列表

	private String businessId;
	private String docId;

	private String custId;
	private String projId;
	private String contractId;

	private String creator = "";
	private String createDate = "";
	private String modifyDate = "";
	private String modificator = "";

	private String quotId = "";
	private String status = "";
	private String onHireId = "";
	private String projOrCont = "";// 合同号或项目号

	public List<RentPlanBean> convertToRentPlanList() {
		List<RentPlanBean> result = new ArrayList<RentPlanBean>();
		for (int i = 0; i < rentList.size(); i++) {
			RentPlanBean temp = new RentPlanBean();
			temp.setDocId(docId);
			temp.setCustId(custId);
			temp.setProjId(projId);
			temp.setContractId(contractId);

			temp.setRentList("" + (i + 1));
			temp.setRent(rentList.get(i));
			temp.setYearRate(yearRateList.get(i));
			temp.setPlanDate(planDateList.get(i));
			temp.setAdjustRent(rentAdjustList.get(i));

			temp.setFinanceInterest(interestFinacList.get(i));
			temp.setFinanceCorpus(corpusFinacList.get(i));
			temp.setFinanceCorpusOverage(corpusOverageFinacList.get(i));
			temp.setBusinessCorpusOverage(corpusOverageBusinessList.get(i));
			temp.setBusinessCorpus(corpusBusinessList.get(i));
			temp.setBusinessInterest(interestBusinessList.get(i));

			result.add(temp);
		}
		return result;
	}

	public static FundRentPlanBean convertRentPlanListToBean(List<RentPlan> rentPlanList) {
		FundRentPlanBean frpf = new FundRentPlanBean();
		for (RentPlan rentPlanTemp : rentPlanList) {
			frpf.getRentList().add(rentPlanTemp.getRent() == null ? "0" : rentPlanTemp.getRent().toString());
			frpf.getAllRemainRentList().add(rentPlanTemp.getAllRemainRent() == null ? "0" : rentPlanTemp.getAllRemainRent().toString());
			frpf.getPlanDateList().add(rentPlanTemp.getPlanDate());
			frpf.getRentAdjustList().add(rentPlanTemp.getRentAdjust() == null ? "" : rentPlanTemp.getRentAdjust().toString());
			frpf.getCorpusFinacList().add(rentPlanTemp.getCorpus() == null ? "0" : rentPlanTemp.getCorpus().toString());
			frpf.getInterestFinacList().add(rentPlanTemp.getInterest() == null ? "0" : rentPlanTemp.getInterest().toString());
			frpf.getCorpusBusinessList().add(rentPlanTemp.getCorpusBusiness() == null ? "0" : rentPlanTemp.getCorpusBusiness().toString());
			frpf.getInterestBusinessList().add(rentPlanTemp.getInterestBusiness() == null ? "0" : rentPlanTemp.getInterestBusiness().toString());
			frpf.getColumn_1().add(rentPlanTemp.getCorpusBusiness() == null ? "0" : rentPlanTemp.getCorpusBusiness().toString());
			frpf.getColumn_2().add(rentPlanTemp.getInterestBusiness() == null ? "0" : rentPlanTemp.getInterestBusiness().toString());
			frpf.getYearRateList().add(rentPlanTemp.getYearRate() == null ? "0" : rentPlanTemp.getYearRate().toString());
			frpf.getCorpusOverageBusinessList().add(rentPlanTemp.getCorpusOverage() == null ? "0" : rentPlanTemp.getCorpusOverage().toString());
		}
		return frpf;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getYearRate() {
		return yearRate;
	}

	public void setYearRate(String yearRate) {
		this.yearRate = yearRate;
	}

	public List<String> getPlanDateList() {
		return planDateList;
	}

	public void setPlanDateList(List<String> planDateList) {
		this.planDateList = planDateList;
	}

	public List<String> getRentList() {
		return rentList;
	}

	public void setRentList(List<String> rentList) {
		this.rentList = rentList;
	}

	public List<String> getCorpusBusinessList() {
		return corpusBusinessList;
	}

	public void setCorpusBusinessList(List<String> corpusList) {
		this.corpusBusinessList = corpusList;
	}

	public List<String> getCorpusOverageBusinessList() {
		return corpusOverageBusinessList;
	}

	public void setCorpusOverageBusinessList(List<String> corpusOverageList) {
		this.corpusOverageBusinessList = corpusOverageList;
	}

	public List<String> getInterestBusinessList() {
		return interestBusinessList;
	}

	public void setInterestBusinessList(List<String> interestList) {
		this.interestBusinessList = interestList;
	}

	public List<String> getRentAdjustList() {
		return rentAdjustList;
	}

	public void setRentAdjustList(List<String> rentAdjustList) {
		this.rentAdjustList = rentAdjustList;
	}

	public List<String> getCorpusFinacList() {
		return corpusFinacList;
	}

	public void setCorpusFinacList(List<String> corpusFinacList) {
		this.corpusFinacList = corpusFinacList;
	}

	public List<String> getCorpusOverageFinacList() {
		return corpusOverageFinacList;
	}

	public void setCorpusOverageFinacList(List<String> corpusOverageFinacList) {
		this.corpusOverageFinacList = corpusOverageFinacList;
	}

	public List<String> getInterestFinacList() {
		return interestFinacList;
	}

	public void setInterestFinacList(List<String> interestFinacList) {
		this.interestFinacList = interestFinacList;
	}

	public List<String> getYearRateList() {
		return yearRateList;
	}

	public void setYearRateList(List<String> yearRateList) {
		this.yearRateList = yearRateList;
	}

	public List<String> getColumn_1() {
		return column_1;
	}

	public void setColumn_1(List<String> column_1) {
		this.column_1 = column_1;
	}

	public List<String> getColumn_2() {
		return column_2;
	}

	public void setColumn_2(List<String> column_2) {
		this.column_2 = column_2;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModificator() {
		return modificator;
	}

	public void setModificator(String modificator) {
		this.modificator = modificator;
	}

	public String getQuotId() {
		return quotId;
	}

	public void setQuotId(String quotId) {
		this.quotId = quotId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOnHireId() {
		return onHireId;
	}

	public void setOnHireId(String onHireId) {
		this.onHireId = onHireId;
	}

	public String getProjOrCont() {
		return projOrCont;
	}

	public void setProjOrCont(String projOrCont) {
		this.projOrCont = projOrCont;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public List<String> getAllRemainRentList() {
		return allRemainRentList;
	}

	public void setAllRemainRentList(List<String> allRemainRentList) {
		this.allRemainRentList = allRemainRentList;
	}

	public List<String> getCautionmoneyRemainList() {
		return cautionmoneyRemainList;
	}

	public void setCautionmoneyRemainList(List<String> cautionmoneyRemainList) {
		this.cautionmoneyRemainList = cautionmoneyRemainList;
	}

	public List<String> getHandlingChargeList() {
		return handlingChargeList;
	}

	public void setHandlingChargeList(List<String> handlingChargeList) {
		this.handlingChargeList = handlingChargeList;
	}
	
	
}
