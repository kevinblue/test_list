package com.reckon.rentcalc.service.impl.pub;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.SpecialRulesBean;
import com.reckon.calculation.condition.CalculationConditionImpl;
import com.reckon.calculation.utils.RentCalculateUtil;
import com.reckon.calculation.vo.CalculationCondition;
import com.reckon.calculation.vo.RentPlanInfo;


/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-3
 * @desc  ( 租金计划支付日期处理类)
 */
public class PlanDateServiceImpl {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 
	 * 租金时间计划安排
	 * 
	 * @param cb
	 * @param rentListSize
	 * @return
	 */
	public List<String> getPlanDateList(ConditionBean cb, String rentListSize) {
		try {
			CalculationCondition condition = new CalculationConditionImpl();
			condition.copyConditionBeanValues(cb);
			List<RentPlanInfo> rentPlanList = RentCalculateUtil.getEmptyPlanModel(condition);
			List<String> dateList = new ArrayList<String>();
			for(RentPlanInfo rpl : rentPlanList){
				dateList.add(dateFormat.format(rpl.getEndDate()));
			}
			return dateList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<String> getPlanDateListForSpecial(ConditionBean cb, String rentListSize,List<SpecialRulesBean> srb,int ... startList) {
		try {
			CalculationCondition condition = new CalculationConditionImpl();
			condition.copyConditionBeanValues(cb);
			List<RentPlanInfo> rentPlanList = RentCalculateUtil.getEmptyPlanModelForSpecial(condition,srb);
			List<String> dateList = new ArrayList<String>();
			for(RentPlanInfo rpl : rentPlanList){
				dateList.add(dateFormat.format(rpl.getEndDate()));
			}
			return dateList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
