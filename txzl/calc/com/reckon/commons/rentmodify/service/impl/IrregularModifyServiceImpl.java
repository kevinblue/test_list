package com.reckon.commons.rentmodify.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.commons.base.Condition;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.helper.bean.CalculateCondition;

public class IrregularModifyServiceImpl {

	private static Logger logger = Logger.getLogger(EvenCorpusModifyServiceImpl.class);

	public static <R extends RentPlan, K extends Condition> String rentModify(K cb, List<R> rentPlanList, int startList) throws Exception {
		logger.info("不规则租金计划修改开始：......");
		CalculateCondition condition = new CalculateCondition(cb);
		BigDecimal leaseMoney = condition.getLeaseMoney();
		for (int i = 0; i < rentPlanList.size(); i++) {
			RentPlan rentPlan = rentPlanList.get(i);
			BigDecimal newRent = rentPlan.getRent();
			BigDecimal newCorpus = rentPlan.getBusinessCorpus();
			BigDecimal newInterest = rentPlan.getBusinessInterest();

			if (rentPlan.getRentList() >= startList) {
				if (rentPlan.getAdjustRent() != null && rentPlan.getAdjustInterest() != null && rentPlan.getAdjustCorpus() != null) {
					logger.info("本金租金利息皆已知，不做其他额外校验，直接修正租金计划");
					newRent = rentPlan.getAdjustRent();
					newInterest = rentPlan.getAdjustInterest();
					newCorpus = rentPlan.getAdjustCorpus();
				} else if (rentPlan.getAdjustRent() != null && rentPlan.getAdjustCorpus() != null) {
					logger.info("本金本金已知，利息未知，修正利息");
					newRent = rentPlan.getAdjustRent();
					newCorpus = rentPlan.getAdjustCorpus();
					newInterest = newRent.subtract(newCorpus);
				} else if (rentPlan.getAdjustRent() != null && rentPlan.getAdjustInterest() != null) {
					logger.info("租金利息已知，本金未知，修正本金");
					newRent = rentPlan.getAdjustRent();
					newInterest = rentPlan.getAdjustInterest();
					newCorpus = newRent.subtract(newCorpus);
				} else if (rentPlan.getAdjustInterest() != null && rentPlan.getAdjustCorpus() != null) {
					logger.info("本金利息已知，租金未知，修正租金");
					newCorpus = rentPlan.getAdjustCorpus();
					newInterest = rentPlan.getAdjustInterest();
					newRent = newCorpus.add(newInterest);
				} else if (rentPlan.getAdjustRent() != null) {
					logger.info("只制定了租金，利息不变，本金修正");
					newRent = rentPlan.getAdjustRent();
					newCorpus = newRent.subtract(newInterest);
				} else if (rentPlan.getAdjustInterest() != null) {
					logger.info("只制定了利息，本金不变，租金修正");
					newInterest = rentPlan.getAdjustInterest();
					newRent = newCorpus.add(newInterest);
				} else if (rentPlan.getAdjustCorpus() != null) {
					logger.info("只制定了本金，利息不变，租金修正");
					newCorpus = rentPlan.getAdjustCorpus();
					newRent = newInterest.add(newCorpus);
				}
				logger.info("租金：[" + newRent.toString() + "]本金：[" + newCorpus.toString() + "]利息：[" + newInterest.toString() + "]");

				rentPlan.setRent(newRent);
				rentPlan.setBusinessInterest(newInterest);
				rentPlan.setBusinessCorpus(newCorpus);
				BigDecimal newCorpusOverage = leaseMoney.subtract(newCorpus);
				rentPlan.setBusinessCorpusOverage(newCorpusOverage);
			} else {
				leaseMoney = leaseMoney.subtract(newCorpus);
			}
		}
		return "修改成功";
	}
	
	public static <R extends RentPlan, K extends Condition> String rentModify(K cb, List<R> rentPlanList, int startList, int endList, int averageStartList, int averageEndList) throws Exception {
		
		return null;
	}
}
