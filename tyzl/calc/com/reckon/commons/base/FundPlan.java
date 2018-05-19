package com.reckon.commons.base;

import java.io.Serializable;
import java.math.BigDecimal;

import com.tenwa.business.entity.DictionaryData;

/**
 * 
 * @author 孟海阳
 * @info 用于租金测算时的资金计划类
 * Tenwa
 */
public interface FundPlan extends Serializable, Comparable<FundPlan>{
	
	public String getId();
	public void setId(String id);
	
	public String getDocId();
	public void setDocId(String docid);
	
	public String getCustId();
	public void setCustId(String custId);

	public String getProjId();
	public void setProjId(String projId);

	public String getContractId();
	public void setContractId(String contractId);

	public String getEquipId();
	public void setEquipId(String equipId);
	
	public String getConditionId();
	public void setConditionId(String condition);
	
	public String getPaymentId();
	public void setPaymentId(String paymentId);

	public DictionaryData getFeeType();
	public void setFeeType(DictionaryData feeType);
	
	public DictionaryData getPayType();
	public void setPayType(DictionaryData payType);
	
	public String getPlanDate();
	public void setPlanDate(String planDate);
	
	public BigDecimal getPlanMoney();
	public void setPlanMoney(BigDecimal planMoney);
	
	public String getPayObj();
	public void setPayObj(String payObj);
	
	public int compareTo(FundPlan o);
}
