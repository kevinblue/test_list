package com.reckon.commons.rentadjust.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.commons.base.Condition;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.helper.Scale;
import com.reckon.commons.helper.Type;
import com.reckon.commons.util.DateTools;

public class RentAdjustForStopService {

	private static Logger logger = Logger.getLogger(RentAdjustForStopService.class);

	/**
	 * 中途终止，提前还款
	 * 
	 * @param condition 商务报价
	 * @param rentPlan 租金计划
	 * @param startList 开始期项
	 * @param endDate 结束日期
	 * @return
	 */
	public static String rentAdjust(Condition condition, List<RentPlan> rentPlan, String endDate) {
		try {
			List<RentPlan> allTemp = new ArrayList<RentPlan>(rentPlan);
			rentPlan.clear();
			Collections.sort(allTemp);
			
			RentPlan current = null;// 要修改的那一期
			RentPlan last = null;// 要修改的那一期的上一期
			for (RentPlan bean : allTemp) {
				rentPlan.add(bean);
				long diff = DateTools.getDateDiff(bean.getPlanDate(), endDate);
				if(diff > 0){
					last = bean;
				} else if(diff == 0){
					last = bean;
					current = bean;
					break;
				} else {
					current = bean;
					break;
				}
			}
			
			BigDecimal corpusOverage = BigDecimal.ZERO;// 计算期之后的期次中本金未还的量
			BigDecimal allYHInterest = BigDecimal.ZERO;// 计算日以后所有的已还利息
			for (RentPlan bean : allTemp) {
				long diff = DateTools.getDateDiff(bean.getPlanDate(), endDate);
				if(diff <= 0){
					corpusOverage = corpusOverage.add(bean.getOverageCorpus());
					// allYHInterest = bean.getBusinessInterest().subtract(bean.getOverageInterest());
				}
			}
			
			if(current != null && last != null){
				if(current.getRentList() == last.getRentList()){ // 不需要重算利息
					current.setOverageCorpus(corpusOverage);
					current.setOverageRent(current.getOverageInterest().add(corpusOverage));
					
					current.setBusinessCorpus(current.getBusinessCorpusOverage().add(current.getBusinessCorpus()));
					current.setRent(current.getBusinessCorpus().add(current.getBusinessInterest()));
					
					if(current.getOverageRent().compareTo(BigDecimal.ZERO) == 0){
						current.setStatus("已核销");// 退回到上一级，增加还款，剩余全部本金
					} else if(current.getOverageRent().compareTo(BigDecimal.ZERO) > 0){
						current.setStatus("部分核销");// 退回到上一级，增加还款，剩余全部本金
					} else {
						current.setStatus("未核销");// 退回到上一级，增加还款，剩余全部本金
					}
					current.setRentType(Type.STOP);
				} else {
					// 本期新利息计算
					long diff = DateTools.getDateDiff(last.getPlanDate(), endDate);
					
					// 未还本金 = 本期未还本金 + 本期剩余总本金
					BigDecimal newInterest = new BigDecimal(diff).multiply(corpusOverage).multiply(current.getYearRate());
					newInterest = newInterest.divide(new BigDecimal(36000), Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
					
					// 未还利息 = 新利息 - 已还利息
					BigDecimal overageInterest = newInterest.subtract(allYHInterest);
					
					// 利息还多了，抵消本金
					if(overageInterest.compareTo(BigDecimal.ZERO) < 0){
						corpusOverage = corpusOverage.add(overageInterest);
						overageInterest = BigDecimal.ZERO;
					}
					
					current.setOverageInterest(overageInterest);
					current.setOverageCorpus(corpusOverage);
					current.setOverageRent(overageInterest.add(corpusOverage));
					
					current.setBusinessInterest(overageInterest);
					current.setBusinessCorpus(corpusOverage);
					current.setRent(overageInterest.add(corpusOverage));
					
					current.setStatus("未核销");
					current.setRentType(Type.STOP);
				}
				
				current.setBusinessCorpusOverage(BigDecimal.ZERO);
				current.setPlanDate(endDate);
				current.setStartDate(endDate);
				current.setEndDate(endDate);
			}
			return "success";
		} catch (Exception e) {
			logger.error("租金计划调整出错。", e);
			e.printStackTrace();
			return "error";
		}
	}
}
