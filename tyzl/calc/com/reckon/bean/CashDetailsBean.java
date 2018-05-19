package com.reckon.bean;

import java.util.*;

import org.hsqldb.DatabaseURL;

import com.tenwa.kernal.utils.DateUtil;
/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-3
 * @desc  ( 现金流明细bean)
 */
public class CashDetailsBean implements Comparable{

	private String id;
	private String docId;
	private int onhire_id = 0;//欧力士中该字段被借用按格式补0时的现金流的排序字段
	
	private String contractId = ""; // 合同号
	private String planDate = ""; // 日期
	
	private String fundIn = "0"; // 流入量
	private String fundInDetails = ""; // 流入量清单
	
	private String fundOut = "0"; // 流出量
	private String fundOutDetails = ""; // 流出量清单
	
	private String netFlow = "0"; // 净流量
	private String quotId = "0"; // 报价编号
	
	private String modificator;
	private String creator;
	private String createDate;
	private String modifyDate;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getQuotId() {
		return quotId;
	}

	public void setQuotId(String quotId) {
		this.quotId = quotId;
	}

	public CashDetailsBean() {
		super();
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getFundIn() {
		return fundIn;
	}

	public void setFundIn(String fundIn) {
		this.fundIn = fundIn;
	}

	public String getFundInDetails() {
		return fundInDetails;
	}

	public void setFundInDetails(String fundInDetails) {
		this.fundInDetails = fundInDetails;
	}

	public String getFundOut() {
		return fundOut;
	}

	public void setFundOut(String fundOut) {
		this.fundOut = fundOut;
	}

	public String getFundOutDetails() {
		return fundOutDetails;
	}

	public void setFundOutDetails(String fundOutDetails) {
		this.fundOutDetails = fundOutDetails;
	}

	public String getNetFlow() {
		return netFlow;
	}

	public void setNetFlow(String netFlow) {
		this.netFlow = netFlow;
	}

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setModificator(String modificator) {
		this.modificator = modificator;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModificator() {
		return modificator;
	}

	public String getCreator() {
		return creator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}
	
	/**
	 * <p>GET欧力士中该字段被借用按格式补0时的排序字段。</p>
	 * @author sea
	 * @return
	 */
	public int getOnhire_id() {
		return onhire_id;
	}

	/**
	 * <p>SET欧力士中该字段被借用按格式补0时的排序字段。</p>
	 * @author sea
	 * @return
	 */
	public void setOnhire_id(int onhireId) {
		onhire_id = onhireId;
	}
	
	
	/*
	@Override
	 public int compareTo(Object o){
		if (o instanceof CashDetailsBean) {
			CashDetailsBean s = (CashDetailsBean) o;
            if (s.getOnhire_id() > this.onhire_id) {
                return -1;
            } else if (s.getOnhire_id() == this.onhire_id) {
                return 0;
            } else {
                return 1;
            }
        } else {
            throw new RuntimeException("不同对象之间无法比较");
        }
	 }*/
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof CashDetailsBean){
			CashDetailsBean s = (CashDetailsBean) o;
			Date thisDate = DateUtil.getTimeByFormat(this.getPlanDate(), "yyyy-MM");
			Date compareDate = DateUtil.getTimeByFormat(s.getPlanDate(), "yyyy-MM");
			if(thisDate.getTime() - compareDate.getTime() > 0 ){
				return 1 ;
			}else if(thisDate.getTime() - compareDate.getTime() == 0 ){
				return 0;
			}else{
				return -1;
			}
		}else{
			throw new RuntimeException("不同对象之间无法比较");
		}
	}

}
