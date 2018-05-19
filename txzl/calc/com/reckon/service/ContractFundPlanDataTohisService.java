package com.reckon.service;

import java.util.Map;

public interface ContractFundPlanDataTohisService {
	/**
	 * 把合同租金测算的资金计划数据入历史表
	 * @param contractId 合同表 ID
	 * @param docId  
	 * @param hisType 历史表类型
	 * @param datas   数据
	 * @param conditioinKey map数据中的key--商务条件
	 * @param fundFundPlanKey   map数据中的key--资金计划
	 */
	public void  saveContractConditionDataToHis(String contractId,String docId,String hisType,Map<String, String> datas,String conditioinKey,String fundFundPlanKey)throws Exception ;
	
}
