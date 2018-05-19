package com.reckon.commons.base;

import java.io.Serializable;
import java.math.BigDecimal;

import com.tenwa.business.entity.User;

/**
 * 
 * @author 孟海阳
 * @info 用于租金测算时的租金计划类
 * Tenwa
 */
public interface RentPlan extends Serializable, Comparable<RentPlan> {
	
	public String getId();
	public void setId(String id);

	public String getDocId();
	public void setDocId(String docId);
	
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
	
	public Integer getRentList();
	public void setRentList(Integer rentList);
	
	public String getPlanDate();
	public void setPlanDate(String planDate);
	
	public String getStartDate();
	public void setStartDate(String startDate);
	
	public String getEndDate();
	public void setEndDate(String endDate);
	
	public BigDecimal getRent();
	public void setRent(BigDecimal rent);
	
	public BigDecimal getAdjustRent();
	public void setAdjustRent(BigDecimal adjustRent);
	
	public BigDecimal getAdjustCorpus();
	public void setAdjustCorpus(BigDecimal adjustCorpus);
	
	public BigDecimal getAdjustInterest();
	public void setAdjustInterest(BigDecimal adjustInterest);
	
	public BigDecimal getOverageRent();
	public void setOverageRent(BigDecimal overageRent);
	
	public BigDecimal getOverageCorpus();
	public void setOverageCorpus(BigDecimal overageCorpus);
	
	public BigDecimal getOverageInterest();
	public void setOverageInterest(BigDecimal overageInterest);
	
	public BigDecimal getFinanceCorpus();
	public void setFinanceCorpus(BigDecimal financeCorpus);
	
	public BigDecimal getFinanceInterest();
	public void setFinanceInterest(BigDecimal financeInterest);
	
	public BigDecimal getFinanceCorpusOverage();
	public void setFinanceCorpusOverage(BigDecimal financeCorpusOverage);
	
	public BigDecimal getBusinessCorpus();
	public void setBusinessCorpus(BigDecimal businessCorpus);
	
	public BigDecimal getBusinessInterest();
	public void setBusinessInterest(BigDecimal businessInterest);
	
	public BigDecimal getBusinessCorpusOverage();
	public void setBusinessCorpusOverage(BigDecimal businessCorpusOverage);
	
	public BigDecimal getAllRemainRent();
	public void setAllRemainRent(BigDecimal allRemainRent);
	
	public BigDecimal getAllRemainCorpus();
	public void setAllRemainCorpus(BigDecimal allRemainCorpus);
	
	public BigDecimal getAllRemainInterest();
	public void setAllRemainInterest(BigDecimal allRemainInterest);
	
	public BigDecimal getYearRate();
	public void setYearRate(BigDecimal yearRate);
	
	public BigDecimal getPenalty();
	public void setPenalty(BigDecimal penalty);
	
	public BigDecimal getPenaltyOverage();
	public void setPenaltyOverage(BigDecimal penaltyOverage);
	
	public String getStatus();
	public void setStatus(String status);
	
	public String getRemark();
	public void setRemark(String remark);
	
	public User getCreator();
	public void setCreator(User creator);
	
	public String getCreateDate();
	public void setCreateDate(String createDate);
	
	public User getModificator();
	public void setModificator(User modificator);
	
	public String getModifyDate();
	public void setModifyDate(String modifyDate);
	
	public String getRentType();
	public void setRentType(String rentType);
	
	public int compareTo(RentPlan o);
}