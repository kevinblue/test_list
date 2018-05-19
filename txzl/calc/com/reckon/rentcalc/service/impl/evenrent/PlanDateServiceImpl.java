package com.reckon.rentcalc.service.impl.evenrent;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.nio.cs.ext.Big5;

import com.reckon.bean.ConditionBean;
import com.reckon.calculation.utils.RentCalculateUtil;
import com.reckon.entity.contract.reckon.fund.ContractFundRentPlanBefore;
import com.reckon.service.FundFundChargePlanService;
import com.reckon.util.DateUtils;
import com.reckon.util.PlanDateTools;
import com.reckon.util.tools.DateTools;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;



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
		String lastPlanDate = cb.getLastPlanDate();
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
		if(null != lastPlanDate && lastPlanDate.length() > 0 && planDateList.size() > 0){
			planDateList.set(planDateList.size() - 1, lastPlanDate);
		}
		return planDateList;
	}
	
	public List<String> getPlanDateListIrr(ConditionBean cb) throws Exception{
		String contractId = cb.getContractId();
		String sql = "select cc.second_plan_date,cc.first_plan_date,cc.start_date,ci.id from contract_condition cc left join contract_info ci on ci.id = cc.contract_id where ci.contract_id = ? or ci.id = ?";
		TableService service = (TableService) WebUtil
				.getApplicationContext().getBean("tableService");
		List<Map<String, Object>> contractInfo = service.queryListBySql(sql, contractId,contractId);
		Map<String, Object> planInfo =  contractInfo.get(0);
		String id = planInfo.get("id").toString();
		ContractInfo ci = new ContractInfo();
		ci.setId(id);
		String hql ="from ContractFundRentPlanBefore where contractId = ? order by rentList asc";
		List<ContractFundRentPlanBefore> plans =  service.findResultsByHSQL(hql, ci);
		List<String> oldPlanDateList = new ArrayList<String>();
		int k = 1;
		String firstPlanDate = cb.getFirstPlanDate();// 计划第1期日期
		String secondPlanDate = cb.getSecondPlanDate();// 计划第2期日期
		String newDate = "";
		String oldDate = "";
		String oldDate1 = "";
		String oldDate2 = "";
		for(ContractFundRentPlanBefore plan : plans){
			oldPlanDateList.add(plan.getPlanDate());
			if(k == 1){
				oldDate1 = plan.getPlanDate();
			}
			if(k == 2){
				oldDate2 = plan.getPlanDate();
			}
			k++;
		}
		//判断现第二期租金支付日期和原租金支付日期之间间隔的月份差和天数差
		String startDate = cb.getStartDate();// 起租日期，计算最后一期的还款日期
		
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
		
		if(cb.getSecondPlanDate() != null && cb.getSecondPlanDate().length() > 0){
			newDate = secondPlanDate;
			oldDate = oldDate2;
		}else if(cb.getFirstPlanDate() != null && cb.getFirstPlanDate().length() > 0){
			newDate = firstPlanDate;
			oldDate = oldDate1;
		}else{
			newDate = startDate;
			oldDate = oldDate1;
		}
		Integer monthDiff = 12*DateUtils.getDateDiffYear(newDate, oldDate) + DateUtils.getDateDiffMonth(newDate, oldDate);
		Integer dayDiff = DateUtils.getDateDiffDay(newDate, oldDate);
		for(int i = j ;i < oldPlanDateList.size();i++){
			String oldPlanDate = oldPlanDateList.get(i);
			String newPlanDate = DateUtil.addDate(oldPlanDate, DateUtil.TIME_MONTH, monthDiff);
			Integer day = Integer.parseInt(DateUtils.getDateDay(oldPlanDate))+dayDiff;
			Integer lastDay = Integer.parseInt(DateUtils.getLastDayOfMonth(DateUtils.getDateYear(newPlanDate), DateUtils.getDateMonth(newPlanDate))) ;
			if(day >= lastDay){
				day = lastDay;
			}
			DecimalFormat df = new DecimalFormat("00");
			newPlanDate  = DateUtils.getDateYear(newPlanDate)+"-"+df.format(new BigDecimal(DateUtils.getDateMonth(newPlanDate))) +"-"+df.format(new BigDecimal(day));
			planDateList.add(newPlanDate);
		}
		return planDateList;
	}
	
}
