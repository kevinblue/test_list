package com.reckon.commons.bean;

import java.math.BigDecimal;

/**
 * 
 * @author 孟海阳
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 调息央行记录与合同相关联信息的载体
 */
public class TransRateInfo {

	private int startList; // 调息开始期项

	private String startDate; // 调息开始时间

	private BigDecimal newYearRate; // 调整后利率

	private boolean finish = false;

	public TransRateInfo(int startList, String startDate, BigDecimal newYearRate) {
		this.startList = startList;
		this.newYearRate = newYearRate;
		this.startDate = startDate;
	}

	public int getStartList() {
		return startList;
	}

	public void setStartList(int startList) {
		this.startList = startList;
	}

	public BigDecimal getNewYearRate() {
		return newYearRate;
	}

	public void setNewYearRate(BigDecimal newYearRate) {
		this.newYearRate = newYearRate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	@Override
	public String toString() {
		String result = "{";
		result += "startList:\"" + startList + "\",";
		result += "newYearRate:\"" + newYearRate + "\",";
		result += "adjustDate:\"" + startDate + "\",";
		result += "finish:\"" + finish + "\"}";
		return result;
	}
}
