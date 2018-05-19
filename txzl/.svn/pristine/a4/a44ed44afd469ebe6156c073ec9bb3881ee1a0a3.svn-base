package com.reckon.commons.base;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author 孟海阳
 * @info 用于租金测算时的现金流类
 * Tenwa
 */
public interface CashDetail extends Serializable, Comparable<CashDetail> {
	
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
	public void setConditionId(String conditionId);

	public String getPlanDate();
	public void setPlanDate(String planDate);
	
	public BigDecimal getFundIn();
	public void setFundIn(BigDecimal fundIn);
	
	public String getFundInDetails();
	public void setFundInDetails(String fundInDetails);
	
	public BigDecimal getFundOut();
	public void setFundOut(BigDecimal fundOut);
	
	public String getFundOutDetails();
	public void setFundOutDetails(String fundOutDetails);
	
	public BigDecimal getNetFlow();
	public void setNetFlow(BigDecimal netFlow);
	
	public int compareTo(CashDetail o);
}
