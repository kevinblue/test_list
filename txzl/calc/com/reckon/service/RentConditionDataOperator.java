package com.reckon.service;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.proj.ProjInfo;

/**
 * 
 * @author 徐云龙
 * @date 2014-1-11上午19:18:32
 * @info 
 * @Copyright 
 * Tenwa
 */
public interface RentConditionDataOperator {
	/**
	 * @info 保存或更新项目层，交易结构，租金计划，现金流，应收应付计划
	  * @param project 项目
	  * @param variablesMap 前台数据
	  * @param conditionString 交易结构的前缀
	  * @param rentstr  租金计划的多行域
	  * @param rentFlowStr 现金流多行域
	  * @param financeStr 财务租金计划多行域
	  * @param financeFlowStr 财务现金流多行域
	  * @param fundStr 应收应付多行域
	  * @param putStr 投放计划
	  * @param specialStr 分段规则
	 * @return void
	 * @throws Exception
	 */
     void saveProjectContionAndRentAndFundToDatabase(ProjInfo project,Map<String,String> variablesMap,String conditionString,String rentstr,String rentFlowStr,String financeStr,String financeFlowStr,String fundStr,String putStr,String specialStr,String gracestr) throws Exception;
     /**
 	 * @info 加载数据到前台，交易结构，租金计划，现金流，应收应付计划到项目层
 	  * @param project 项目
 	  * @param variablesMap 前台数据
 	  * @param conditionString 交易结构的前缀
 	  * @param rentstr  租金计划的多行域
 	  * @param rentFlowStr 现金流多行域
 	  * @param financeStr 财务租金计划多行域
 	  * @param financeFlowStr 财务现金流多行域
 	  * @param fundStr 应收应付多行域
 	 * @return void
 	 * @throws Exception
 	 */
     void LoadProjectContionAndRentAndFundToMap(ProjInfo project,Map<String,String> variablesMap,String conditionString,String rentstr,String rentFlowStr,String financeStr,String financeFlowStr,String fundStr,String putStr,String specialStr,String gracestr) throws Exception;
     /**
  	 * @info 加载数据到前台，交易结构，租金计划，现金流，应收应付计划从项目层到合同层
  	 *  @param contractid 合同号
  	  * @param project 项目
  	  * @param variablesMap 前台数据
  	  * @param conditionString 交易结构的前缀
  	  * @param rentstr  租金计划的多行域
  	  * @param rentFlowStr 现金流多行域
  	  * @param financeStr 财务租金计划多行域
  	  * @param financeFlowStr 财务现金流多行域
  	  * @param fundStr 应收应付多行域
  	 * @return void
  	 * @throws Exception
  	 */
     void LoadProjectContionAndRentAndFundToContractMap(String contractid,ProjInfo project,Map<String,String> variablesMap,String conditionString,String rentstr,String rentFlowStr,String financeStr,String financeFlowStr,String fundStr,String putStr,String specialStr,String gracestr) throws Exception;
    
     /**
      * 参数控制流程结束后保存
      * 租金计划存              contract_fund_rent_plan_before 
      * 财务租金计划存    contract_fina_rent_plan_before 
      * */
     void saveOrLoadRentAndFinalBefore(ContractInfo contractInfo,Map<String,String> variablesMap,String conditionString,String rentstr,String rentFlowStr,String financeStr,String financeFlowStr,String fundStr,String putStr,String specialStr,String  grace,String type) throws Exception;
   
     
     
     /**
  	 * @info 加载数据到前台，交易结构，租金计划，现金流，应收应付计划到项目层
  	  * @param project 项目
  	  * @param variablesMap 前台数据
  	  * @param conditionString 交易结构的前缀
  	  * @param rentstr  租金计划的多行域
  	  * @param rentFlowStr 现金流多行域
  	  * @param financeStr 财务租金计划多行域
  	  * @param financeFlowStr 财务现金流多行域
  	  * @param fundStr 应收应付多行域
  	 * @return void
  	 * @throws Exception
  	 */
     void saveContractContionAndRentAndFundToDatabase(ContractInfo contractInfo,Map<String,String> variablesMap,String conditionString,String rentstr,String rentFlowStr,String financeStr,String financeFlowStr,String fundStr,String putStr,String specialStr,String grace) throws Exception;
     /**
   	 * @info 加载数据到前台，交易结构，租金计划，现金流，应收应付计划从项目层到合同层
   	  * @param ContractInfo 合同
   	  * @param variablesMap 前台数据
   	  * @param conditionString 交易结构的前缀
   	  * @param rentstr  租金计划的多行域
   	  * @param rentFlowStr 现金流多行域
   	  * @param financeStr 财务租金计划多行域
   	  * @param financeFlowStr 财务现金流多行域
   	  * @param fundStr 应收应付多行域
   	 * @return void
   	 * @throws Exception
   	 */
     void LoadContractContionAndRentAndFundToMap(ContractInfo contractInfo,Map<String,String> variablesMap,String conditionString,String rentstr,String rentFlowStr,String financeStr,String financeFlowStr,String fundStr,String putStr,String graceStr,String specialStr) throws Exception;
     
     /**
    	 * @info 保存交易结构，租金计划，现金流，财务计划，财务现金流并保存历史
    	  * @param ContractInfo 合同
    	  * @param variablesMap 前台数据
    	  * @param docId 流程ID
    	  * @param hisType 保存类型
    	  * @param conditionString 交易结构的前缀
    	  * @param rentstr  租金计划的多行域
    	  * @param rentFlowStr 现金流多行域
    	  * @param financeStr 财务租金计划多行域
    	  * @param financeFlowStr 财务现金流多行域
    	 * @return void
    	 * @throws Exception
    	 */
     void updateContractConditionDataAndSaveDatatoHis(ContractInfo contractInfo,Map<String,String> variablesMap,String docId,String hisType,String conditionString,String rentstr,String rentFlowStr,String financeStr,String financeFlowStr,String fundFundStr) throws Exception;
     /**
 	 * @info 保存资金计划并保存历史
 	  * @param ContractInfo 合同
 	  * @param variablesMap 前台数据
 	  * @param docId 流程ID
 	  * @param hisType 保存类型
	  * @param fundStr 应收应付多行域
 	  * @return void
 	  * @throws Exception
 	 */
     void updateContractFundDataAndSaveDatatoHis(ContractInfo contractInfo,Map<String,String> variablesMap,String docId,String hisType,String fundStrOne,String fundStrTwo) throws Exception;
     void updateContractFundPutDataAndSaveDatatoHis(ContractInfo contractInfo,Map<String,String> variablesMap,String docId,String hisType,String fundStrOne,String fundStrTwo) throws Exception;
     void updateContractBeforeInterestDataAndSaveDatatoHis(ContractInfo contractInfo,Map<String,String> variablesMap,String docId,String hisType,String fundStrOne,String fundStrTwo) throws Exception;
       
 	/**
 	 * <p>根据项目or合同信息在流程结束时在已知租金法情况下，将已知租金法对应的多行域值存入到特定的一张表中。</p>
 	 * @author sea
 	 * @param variablesMap 封装后‘已知租金法多行域值’及整个租金测算jsp页面中的所有值的集合
 	 * @param contractInfo 合同对象，主要使用UUID
 	 * @param projInfo 项目对象，主要使用UUID
 	 * @throws Exception
 	 */
 	public void addKnowingRentData(Map<String,String> variablesMap,ContractInfo contractInfo,ProjInfo projInfo) throws Exception;
 	
	/**
	 * <p>根据项目or合同信息在流程开始时在已知租金法情况下，将已知租金法对应的多行域值JSON字符串取出并返回至前台jsp使用。</p>
	 * @author sea
	 * @param variablesMap 封装后‘已知租金法多行域值’及整个租金测算jsp页面中的所有值的集合
	 * @param contractInfo 合同对象，主要使用UUID
	 * @param projInfo 项目对象，主要使用UUID
	 * @throws DataAccessException
	 * @throws Exception
	 */
	public void secrhKnowingRentJsons(Map<String, String> variablesMap,ContractInfo contractInfo,ProjInfo projInfo);
	
	/**
	 * 财务收入折现使用的加载方法
	 * @param contractInfo
	 * @param variablesMap
	 * @param conditionString
	 * @param rentstr
	 * @param rentFlowStr
	 * @param financeStr
	 * @param financeFlowStr
	 * @param fundStr
	 * @param putStr
	 * @param specialStr
	 * @param grace
	 * @param type
	 * @throws Exception
	 */
	public void saveOrLoadRentAndFinalForFinance(ContractInfo contractInfo,Map<String, String> variablesMap, String conditionString,String rentstr, String rentFlowStr, String financeStr,String financeFlowStr, String fundStr, String putStr,String specialStr, String grace, String type) throws Exception;
	/**
	 * 加载财务使用的资金收付计划
	 * @param contractInfo
	 * @param variablesMap
	 * @param conditionString
	 * @param rentstr
	 * @param rentFlowStr
	 * @param financeStr
	 * @param financeFlowStr
	 * @param fundStr
	 * @param putStr
	 * @param grace
	 * @param specialStr
	 * @throws Exception
	 */
	public void LoadContractFundFundPlanForFinance(ContractInfo contractInfo,Map<String, String> variablesMap, String conditionString,String rentstr, String rentFlowStr, String financeStr,String financeFlowStr, String fundStr, String putStr, String grace,String specialStr) throws Exception;
	
}
