package com.reckon.rentcalc.service.impl.evenrent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.reckon.bean.ConditionBean;
import com.reckon.calculation.utils.RentCalculateUtil;
import com.reckon.util.PlanDateTools;
import com.reckon.util.tools.DateTools;
import com.tenwa.kernal.utils.DateUtil;



/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-3
 * @desc  ( 租金计划支付日期处理类)
 */
public class PlanDateServiceImpl {

	/**
	 * 
	 *  (  调用工具类，进行租金计划时间的计算)
	 * 
	 * @param cb
	 * @param rentListSize
	 * @return
	 */
	public List<String> getPlanDateList(ConditionBean cb, String rentListSize) throws Exception{
		String startDate = cb.getStartDate();// 起租日期，计算最后一期的还款日期
		String firstPlanDate = cb.getFirstPlanDate();// 计划第1期日期
		String secondPlanDate = cb.getSecondPlanDate();// 计划第2期日期
		List<String> planDateList = new ArrayList<String>();
		planDateList.add(startDate);// 必须有起始日期
		int j = 1;//如果有第二期租金支付日期，循环次数减少一次
		if(firstPlanDate != null){
			planDateList.set(0,firstPlanDate);
			if(secondPlanDate != null){
				planDateList.add(secondPlanDate);
				j = 2;
			}
		} 
		int incomeTimes = cb.getIncomeNumber();// 期数
		int grace = cb.getGrace();// 宽限期
		int issueNumber = 12/Integer.parseInt(cb.getIncomeNumberYear());// 每期几个月
		// 计算结束租期日子
		Calendar calendar = Calendar.getInstance();// 推算日期的日历
		Date reference = DateUtil.getTimeByFormat(planDateList.get(planDateList.size()-1), "yyyy-MM-dd");
		for (int i = 1; i < grace + incomeTimes - (j-1); i++) {
			calendar.setTime(reference);
			calendar.add(Calendar.MONTH, issueNumber * i);
			Date tempDate = calendar.getTime();
			while(RentCalculateUtil.getDiffMonth(reference, tempDate) > issueNumber * i){
				calendar.add(Calendar.DAY_OF_MONTH, -1);
				tempDate = calendar.getTime();
			}
			planDateList.add(DateUtil.getSystemTimeByFormat(tempDate, "yyyy-MM-dd") );
		}
		return planDateList;
	}
	
}
