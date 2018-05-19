package com.reckon.calculation.vo;

import java.math.BigDecimal;
import java.util.Date;

public class CashFlow {
	private int id;// 编号
	private String businessId;// 业务编号，或者商务条件编号，合同编号等

	private Date date;// 日期，月份

	private BigDecimal in = new BigDecimal(0);// 流入量
	private BigDecimal out = new BigDecimal(0);// 流出量
	private BigDecimal netFlow = new BigDecimal(0);// 净流量

	private String inDetail = "";// 流入量明细信息，没有格式要求，纯粹解释
	private String outDetail = "";// 流出量明细，没有格式要求，纯粹解释
	
	/**
	 * 增加一笔收入
	 * @param newIn 收入值
	 * @param inName 收入名称
	 */
	public void addIn(BigDecimal newIn, String inName){
		if(newIn != null && newIn.doubleValue() > 0){
			in = in.add(newIn);
			netFlow = netFlow.add(newIn);
			inDetail = inDetail + "[" + inName + "=" + newIn.toString() + "]";
		}
	}
	
	/**
	 * 增加一笔支出
	 * @param newOut 支出值
	 * @param inName 支出名称
	 */
	public void addOut(BigDecimal newOut, String inName){
		if(newOut != null && newOut.doubleValue() > 0){
			out = in.add(newOut);
			netFlow = netFlow.subtract(newOut);
			outDetail = outDetail + "[" + inName + "=" + newOut.toString() + "]";
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getIn() {
		return in;
	}

	public void setIn(BigDecimal in) {
		this.in = in;
	}

	public BigDecimal getOut() {
		return out;
	}

	public void setOut(BigDecimal out) {
		this.out = out;
	}

	public BigDecimal getNetFlow() {
		return netFlow;
	}

	public String getInDetail() {
		return inDetail;
	}

	public String getOutDetail() {
		return outDetail;
	}
}
