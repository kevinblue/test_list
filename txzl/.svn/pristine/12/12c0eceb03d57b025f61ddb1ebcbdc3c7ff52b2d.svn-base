package com.reckon.bean;

import java.util.List;

/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-21
 * 调息央行记录与合同相关联信息的载体
 */
public class InterContBean {

	private String id; //
	private String contractId; // 合同编号
	private String adjustId; // 调息id
	private int startList; // 调息开始期项
	private String oldYearRate; // 调整前利率
	private String newYearRate; // 调整后利率
	private List<String> newYearRateList; // 调整后利率集合
	private String creator; // 登记人

	private String oldIrr; // 旧的合同irr
	private String newIrr; // 新合同irr
	private String oldPlanIrr; // 旧会计irr
	private String newPlanIrr; // 新会计irr
	private String adjustDate; // 调息开始时间
	private String docId; // docId

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getAdjustDate() {
		return adjustDate;
	}

	public void setAdjustDate(String adjustDate) {
		this.adjustDate = adjustDate;
	}

	public InterContBean() {
		super();
	}

	public String getAdjustId() {
		return adjustId;
	}

	public void setAdjustId(String adjustId) {
		this.adjustId = adjustId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNewIrr() {
		return newIrr.equals("") ? "0" : newIrr;
	}

	public void setNewIrr(String newIrr) {
		this.newIrr = newIrr;
	}

	public String getNewPlanIrr() {
		return newPlanIrr;
	}

	public void setNewPlanIrr(String newPlanIrr) {
		this.newPlanIrr = newPlanIrr;
	}

	public String getOldIrr() {
		return oldIrr;
	}

	public void setOldIrr(String oldIrr) {
		this.oldIrr = oldIrr;
	}

	public String getOldPlanIrr() {
		return oldPlanIrr;
	}

	public void setOldPlanIrr(String oldPlanIrr) {
		this.oldPlanIrr = oldPlanIrr;
	}
	/**
	  * <p>GET调整后利率。</p>
	  * @author sea
	  * @return
	 */
	public String getNewYearRate() {
		return newYearRate;
	}

	/**
	 * <p>SET调整后利率。</p>
	 * @author sea
	 * @return
	 */
	public void setNewYearRate(String newYearRate) {
		this.newYearRate = newYearRate;
	}

	public String getOldYearRate() {
		return oldYearRate;
	}

	public void setOldYearRate(String oldYearRate) {
		this.oldYearRate = oldYearRate;
	}

	public int getStartList() {
		return startList;
	}

	public void setStartList(int startList) {
		this.startList = startList;
	}

	@Override
	public String toString() {
		String result = "{";
		result += "id:\"" + id + "\",";
		result += "contractId:\"" + contractId + "\",";
		result += "adjustId:\"" + adjustId + "\",";
		result += "startList:\"" + startList + "\",";
		result += "oldYearRate:\"" + oldYearRate + "\",";
		result += "newYearRate:\"" + newYearRate + "\",";
		result += "creator:\"" + creator + "\",";
		result += "oldIrr:\"" + oldIrr + "\",";
		result += "newIrr:\"" + newIrr + "\",";
		result += "oldPlanIrr:\"" + oldPlanIrr + "\",";
		result += "newPlanIrr:\"" + newPlanIrr + "\",";
		result += "adjustDate:\"" + adjustDate + "\",";
		result += "docId:\"" + docId + "\"}";
		return result;
	}

	public List<String> getNewYearRateList() {
		return newYearRateList;
	}

	public void setNewYearRateList(List<String> newYearRateList) {
		this.newYearRateList = newYearRateList;
	}
}
