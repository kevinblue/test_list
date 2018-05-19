package com.reckon.renttranrate.service;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.reckon.service.ConditionDataToHisService;




public interface AjustInterestService {
	/*
	 * # 调息开始
	 * 1.将央行调息记录临时表表的数据copy到央行调息记录正式表
	 * 2.将租金计划临时表copy到租金计划历史表
	 * 3.将现金流临时表copy到现金流历史表
	 * 4.将交易结构临时表copy到交易就够历史表
	 */
	//1.将央行调息记录临时表表的数据copy到央行调息记录正式表
	public void updateCopyFundAdjustInterestContractTempToRecord(Map<String,String> model) throws Exception;
	
	//2.将租金计划临时表copy到租金计划历史表
	public void updateCopyFundRentPlanFromTempToHisAfter(Map<String,String> model) throws Exception;
	
	//3.将现金流临时表copy到现金流历史表
	public void updateCopyFundRentCashFromTempToHis(Map<String,String> model) throws Exception;
	
	//4.将交易结构临时表copy到交易就够历史表
	public void updateCopyConditionFromTempToHis(Map<String,String> model) throws Exception;
	
	public void updateCopyTxAllTempToHis(Map<String,String> model,ConditionDataToHisService conditionDataToHisService)throws Exception;
	
	/**
	 * 调息流程开始时:
	 * <p>租金计划正式表-->租金计划HIS表 ,状态为前。</p>
	 * @author sea
	 * @param model 只用docid 例如:model.get("docid")
	 * @throws Exception
	 */
	public void updateCopyFundRentPlanFromTempToHisBefore(Map<String, String> model) throws Exception;
	
	//1.央行调息记录表（调息前）-->到央行调息临时表FUND_ADJUST_INTEREST_CONTRACT to FUND_ADJUST_INTEREST_C_TEMP
	public void updateCopyFundAdjustInterestContractFromHisToTemp(Map<String,String> model) throws Exception;

	//2.租金计划his表（调息前）-->租金计划temp表 
	public void updateCopyFundRentPlanFromHisToTemp(Map<String,String> model) throws Exception;
	//X.资金计划his表（调息前）-->资金计划temp表 
		public void updateCopyFundFundPlanFromHisToTemp(Map<String,String> model) throws Exception;
	//3.现金流his表（调息前）-->现金流temp表
	public void updateCopyFundRentCashFromHisToTemp(Map<String,String> model)throws Exception;
	
	//	4.交易结构his表（调息前）-->交易结构temp表
	public void updateCopyConditionFromHisToTemp(Map<String,String> model)throws Exception;
	
//	 * #.撤销回滚
//	 * 1.删除 央行调息临时表FUND_ADJUST_INTEREST_C_TEMP
	public void removeFundAdjustInterestCTemp(Map<String,String> model)throws Exception;
//	 * 2.删除租金计划temp表 
	public void removeFundRentPlanFromHisToTemp(Map<String,String> model)throws Exception;
//	 * 3.删除现金流temp表
	public void removeFundRentCashFromHisToTemp(Map<String,String> model)throws Exception;
//	 * 4.删除交易结构temp表
	public void removeConditionFromHisToTemp(Map<String,String> model)throws Exception;
	
	
//	 * #.回滚流程结束
//	 * 1.更新租金计划表成temp表（调息前）数据
	public void updateFundRentPlan(Map<String,String> model)throws Exception;
//	 * 2.更新现金流表成temp表（调息前）数据
	public void updateFundRentCash(Map<String,String> model)throws Exception;
//	 * 3.交易结构表成temp表（调息前）数据
	public void updateCondition(Map<String,String> model)throws Exception;
//	 * 4.更新记录状态？记录状态 ‘已回滚’
	public void updateStatusTo(Map<String,String> model)throws Exception;
	
	//将央行信息记录表 里面 调息的记录回滚
	public void updateFundAdjustInterestContract(Map<String,String> model)throws Exception;

	
	/**
	 * 调息流程结束调用END方法时:
	 * <p>租金计划temp表-->租金计划正式表 。</p>
	 * <p>jdbc批量模式更新，一个流程号下存在N个合同调息，则批量将这些合同对应的租金计划根据临时表中的数据更新到正式表中。</p>
	 * @author sea
	 * @param model 只用docid 例如:model.get("docid")
	 * @throws Exception
	 */
	public void updateCopyFundRentPlanFromTempToOfficial(Map<String, String> model) throws Exception;
	
	
	
	/**
	 * 
	 * <p>调息流程开始时:租金计划正式表-->租金计划HIS表 ,状态为前。</p>
	 * <p>调息流程算法结束后:租金计划临时表-->租金计划HIS表 ,状态为前。</p>
	 * @author sea
	 * @param model 只用docid 例如:model.get("docid")
	 * @throws Exception
	 */
	public void updateCopyFundRentPlanFromTempToHisBeforeAndAfter(Map<String, String> model,String rentTabname) throws Exception;
	
	
	/**
	 * <p>执行同时删除调息HIS记录表前后的调息记录。</p>
	 * <p>该方法主要用于欧力士调息流程废弃时使用。</p>
	 * <p>【注意】：一个流程下存在多个合同号本次调息的数据，则同时删除。</p>
	 * @author sea
	 * @param docid 文档号
	 * @return
	 * @throws Exception 
	 * @throws DataAccessException 
	 */
	public void deleteHisAllSqlOfTx(String docid) throws DataAccessException, Exception;
	
	/**
	 * <p>执行同时删除调息HIS记录表前后的调息记录。</p>
	 * <p>该方法主要用于欧力士的调息撤销时使用。</p>
	 * @author sea
	 * @param docId 文档号
	 * @param contractId 合同号 contract_info中的uuid
	 * @return
	 * @throws Exception 
	 * @throws DataAccessException 
	 */
	public void deleteHisAllSqlOfTx(String docId,String contractId) throws DataAccessException, Exception;

	/**
	 * 起租前调息结束之后，将资金收付计划临时、正式入历史表，作为调息前后数据
	 * @param model
	 * @throws Exception
	 */
	public void updateCopyFundFundPlanFromTempToHisBeforeAndAfter(Map<String, String> model)throws Exception;
	
	/**
	 * 起租前调息结束之后，将资金收付计划临时入正式表
	 * @param model
	 * @throws Exception
	 */
	public void updateCopyFundFundPlanFromTempToOfficial(Map<String, String> model) throws Exception;

	public void removeFundFundPlanFromHisToTemp(Map<String, String> model)throws Exception;
}
