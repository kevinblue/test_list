package com.reckon.service;

import java.util.Map;

import com.reckon.bean.ConditionBean;


public interface FundFundChargePlanService {
	
	/**
	 * 
	 * <p>资金收付计划产生主入口！</p>
	 * <pre>
	 * 修改顺序：
	 * 1.2011史鸿飞第一版。
	 * 2.2012孙传良第二版。
	 * 3.2013孟海洋第三版。
	 * 4.2014谢永伦格式规整增加注释及补充修改。
	 * 5.2014 12  张闯在第三版的基础上进行修订
	 * </pre>
	 * @author sea
	 * <p>2014-11-12</p>
	 */
	public Map<String, Object> createFundChargePlan(ConditionBean condition) throws Exception;
	
	
	
	/**
	 * 根据商务条件获取资金收付计划
	 * @param modelData 商务条件Map
	 * @param endDate 
	 * @param ownInfo 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> createFundPlan(ConditionBean condition,  String ownName) throws Exception;
}
