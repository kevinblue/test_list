package com.reckon.commons.rentmodify.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.commons.base.Condition;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.helper.Scale;
import com.reckon.commons.helper.bean.CalculateCondition;

/**
 * 
 * @author SCLICX
 * @version 1.0
 * @copyright (C) Tenwa 2011
 * @date Dec 1, 2011
 * @desc 处理变更的时候的财务租金计划和现金流
 */
public class EvenCorpusModifyServiceImpl {

	private static Logger logger = Logger.getLogger(EvenCorpusModifyServiceImpl.class);

	public static <R extends RentPlan, K extends Condition> String rentModify(K cb, List<R> rentPlanList, int startList) throws Exception {
		logger.info("等额本金在直接修改租金计划时，不论是否起租，因为本金已知。");
		logger.info("等额本金在直接修改租金计划时只允许修改本金和利息，不允许修改租金，因为修改后没有方法可以计算本金利息...开始...");
		CalculateCondition condition = new CalculateCondition(cb);
		BigDecimal leaseMoney = condition.getLeaseMoney();
		int grace = condition.getGrace();
		int periodType = condition.getPeriodType();

		for (RentPlan rentPlan : rentPlanList) {
			BigDecimal issueRate = condition.getIssueRate(rentPlan.getYearRate());
			BigDecimal interest = leaseMoney.multiply(issueRate);
			BigDecimal newInterest = interest;
			if (rentPlan.getAdjustInterest() != null) {
				newInterest = rentPlan.getAdjustInterest();
			} else {
				// 期初首期无利息
				if (rentPlan.getRentList() == grace + 1 && periodType == 0) {
					newInterest = BigDecimal.ZERO;
				}
			}
			BigDecimal newCorpus = rentPlan.getBusinessCorpus();
			if (rentPlan.getAdjustCorpus() != null) {
				newCorpus = rentPlan.getAdjustCorpus();
			}
			BigDecimal newRent = newCorpus.add(newInterest);

			rentPlan.setRent(newRent.setScale(Scale.RENT_SCALE, BigDecimal.ROUND_HALF_UP));
			rentPlan.setBusinessInterest(newInterest.setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP));
			rentPlan.setBusinessCorpus(newCorpus.setScale(Scale.CORPUS_SCALE, BigDecimal.ROUND_HALF_UP));

			BigDecimal newCorpusOverage = leaseMoney.subtract(newCorpus);
			rentPlan.setBusinessCorpusOverage(newCorpusOverage.setScale(Scale.CORPUS_SCALE, BigDecimal.ROUND_HALF_UP));
		}
		return "修改成功";
	}
	
	public static <R extends RentPlan, K extends Condition> String rentModify(K cb, List<R> rentPlanList, int startList, int endList, int averageStartList, int averageEndList) throws Exception {
		
		return null;
	}
}
