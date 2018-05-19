package com.tenwa.leasing.service.overdue;

import java.util.Map;

import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;

public interface FundOverCommService {

	public void loadCustColumnInfo(CustInfo custInfo,Map<String, String> variablesMap) throws Exception;
	/** 合同信息 */
	public void loadFundOverContractInfo(ContractInfo contractInfo,Map<String, String> variablesMap) throws Exception;
	public void loadFundOverContractInfo(CustInfo custInfo,Map<String, String> variablesMap) throws Exception;
	/** 业务人员记录信息 */
	public void loadFundOveDunningRecord(ContractInfo contractInfo,Map<String, String> variablesMap) throws Exception;
	public void loadFundOveDunningRecord(CustInfo custInfo,Map<String, String> variablesMap) throws Exception;
	
	/**催收人员记录信息*/
	public void loadFundOveCSRecord(ContractInfo contractInfo,Map<String, String> variablesMap) throws Exception;
	public void loadFundOveCSRecord(CustInfo custInfo,Map<String, String> variablesMap) throws Exception;
	/* 法务申请信息 */
	public void loadLawInfoDetail(Map<String, String> variablesMap)throws Exception;
}
