package com.reckon.service;

import java.util.Map;

/**
 * 租金计划相关数据入历史表
 * 未实现--因为基本上实体都是类似的所以不想写死代码一个类一个类的copy 等到后面有时间用反射什么的机制再写
 * 目前先copy之前的代码改改先
 * @author 孙传良
 * @date 2013-5-26上午10:38:37
 * @info
 * @Copyright 
 * Tenwa
 */
public interface ConditionDataToHisService {

	/**
	 * 把项目租金测算的数据入历史表
	 * @param projId 项目表 ID
	 * @param docId  
	 * @param hisType 历史表类型
	 * @param datas   数据
	 * @param conditioinKey map数据中的key--商务条件
	 * @param rentPlanKey   map数据中的key--租金计划
	 * @param finaPlanKey   map数据中的key--会计
	 * @param rentCashKey   map数据中的key--现金流
	 * @param finaCashKey   map数据中的key--会计
	 * @throws Exception
	 */
	public void  saveProjConditionDataToHis(String projId,String docId,String hisType,Map<String, String> datas,String conditioinKey,String rentPlanKey,String finaPlanKey,String rentCashKey,String finaCashKey)throws Exception ;
	
	/**
	 * 把项目租金测算的数据入历史表
	 * @param contractId 项目表 ID
	 * @param docId  
	 * @param hisType 历史表类型
	 * @param datas   数据
	 * @param conditioinKey map数据中的key--商务条件
	 * @param rentPlanKey   map数据中的key--租金计划
	 * @param finaPlanKey   map数据中的key--会计
	 * @param rentCashKey   map数据中的key--现金流
	 * @param finaCashKey   map数据中的key--会计
	 * @throws Exception
	 */
	public void  saveContractConditionDataToHis(String contractId,String docId,String hisType,Map<String, String> datas,String conditioinKey,String rentPlanKey,String finaPlanKey,String rentCashKey,String finaCashKey)throws Exception ;
	
}
