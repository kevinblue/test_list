package com.reckon.rentcalc.service;

import java.util.Hashtable;
import java.util.List;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.entity.contract.reckon.condition.FundPutPlan;
import com.reckon.entity.contract.reckon.condition.SpecialRulesBean;


/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-2
 * @desc  ( 租金测算方式接口)
 */
public interface RentCalcService {
	/**
	 * 
	 *  (  不同租金测算实现接口)
	 * 
	 * @param cb
	 * @param calType
	 * <pre>流程类型 如果在后续测测试中没用到可以删除  quoted_price  
	 * 	如果是多次起租而且是合并计算,则要把是否合并计算值设置为是 onHire_more_process</pre>
	 * @param userName
	 * @param obj
	 * @return
	 * @throws Exception
	 */

	public Hashtable<String, Object> rentCal(ConditionBean cb, String calType, String userName, String[] rentAdjustList, int startList,List<FundPlanBean> fundPlanList,List<FundPutPlan> fpps,List<SpecialRulesBean> srb,List<String> ...planList) throws Exception;

	/**
	 * 
	 *  (  起租时要实现的方法)
	 * 
	 * @param cb
	 * @param calType
	 * @param userName
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Hashtable<String, Object> rentCalOnHire(ConditionBean cb, String calType, String userName, String[] rentAdjustList,List<FundPlanBean> fundPlanList) throws Exception;

}
