package com.reckon.rentcalc.service.impl.pub;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.reckon.bean.ConditionBean;
import com.reckon.calculation.condition.CalculationConditionImpl;
import com.reckon.calculation.utils.RentCalculateUtil;
import com.reckon.calculation.vo.CalculationCondition;
import com.reckon.calculation.vo.RentPlanInfo;
import com.reckon.entity.contract.reckon.condition.SpecialRulesBean;


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
	/**
	 * 租前息 收付日期
	 * @param cb
	 * @param rentListSize
	 * @return
	 */
	public List<String> getBeforeInterestDateList(ConditionBean cb) {
		try {
			List<RentPlanInfo> rentPlanList = RentCalculateUtil.getBeforeInterestDateModel(cb);
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
	/**
	 * 不等距还租日 按照第二期租金支付日变化天数  计算后期还租日
	 * @param cb
	 * @param planDateList
	 * @return
	 */
	public List<String> getPlanDateListBDJ(ConditionBean cb) {
		try {
			List<Date> rentPlanList = RentCalculateUtil.getBDJPlanModel(cb);
			List<String> dateList = new ArrayList<String>();
			for(Date rpl : rentPlanList){
				dateList.add(dateFormat.format(rpl));
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
