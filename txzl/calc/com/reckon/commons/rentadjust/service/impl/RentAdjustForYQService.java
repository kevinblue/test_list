package com.reckon.commons.rentadjust.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.commons.base.Condition;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.helper.ObjectConvertUtils;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.commons.helper.RentCalculateUtil;
import com.reckon.commons.helper.Scale;
import com.reckon.commons.helper.Type;
import com.reckon.commons.helper.bean.CalculateCondition;

public class RentAdjustForYQService {

	private static Logger logger = Logger.getLogger(RentAdjustForYQService.class);

	/**
	 * 延期调整，只能在未还款的期次上调整，部分还款也不行
	 * 
	 * @param condition 商务条件
	 * @param rentPlan 租金计划
	 * @param startList 延期开始期项
	 * @param delayNumber 延期期数
	 * @return
	 */
	public static String rentAdjust(Condition condition, List<RentPlan> rentPlan, int startList, int delayNumber, boolean isInterest) {
		try {
			Collections.sort(rentPlan);
			RentPlan current = rentPlan.get(startList - 1);
			CalculateCondition cc = new CalculateCondition(condition);
			
			RentPlan temp = ObjectConvertUtils.convertObject(current.getClass(), current);
			temp.setId(null);
			BigDecimal businessCorpusOverage = temp.getBusinessCorpusOverage().add(temp.getBusinessCorpus());
			BigDecimal newInterest = BigDecimal.ZERO;
			if(isInterest){
				newInterest = businessCorpusOverage.multiply(cc.getIssueRate()).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
				temp.setStatus("未核销");
			} else {
				temp.setStatus("已核销");
			}
			
			temp.setRent(newInterest);
			temp.setBusinessCorpus(BigDecimal.ZERO);
			temp.setBusinessInterest(newInterest);
			
			temp.setOverageRent(newInterest);
			temp.setOverageCorpus(BigDecimal.ZERO);
			temp.setOverageInterest(newInterest);
			
			temp.setBusinessCorpusOverage(businessCorpusOverage);
			temp.setYearRate(condition.getYearRate());
			temp.setRentType(Type.DELAY);
			
			for (int i = 0; i < delayNumber; i++) {
				RentPlan newRentPlan = ObjectConvertUtils.convertObject(current.getClass(), temp);
				rentPlan.add(startList - 1, newRentPlan);
			}
			
			cc.setGrace(0);
			cc.setIncomeTimes(rentPlan.size());
			List<? extends RentPlan> newRentPlanModel = RentCalculateUtil.getEmptyPlanModel(cc, current.getClass());
			for (int i = 0; i < newRentPlanModel.size(); i++) {
				rentPlan.get(i).setRentList(newRentPlanModel.get(i).getRentList());
				rentPlan.get(i).setPlanDate(newRentPlanModel.get(i).getPlanDate());
				rentPlan.get(i).setStartDate(newRentPlanModel.get(i).getStartDate());
				rentPlan.get(i).setEndDate(newRentPlanModel.get(i).getEndDate());
			}
			RentCalculateHelper.fillOtherInfoOfRentPlan(condition, rentPlan);
			return "success";
		} catch (Exception e) {
			logger.error("租金计划调整出错。", e);
			e.printStackTrace();
			return "error";
		}
	}
}
