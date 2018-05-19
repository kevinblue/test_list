package com.reckon.commons.rentmodify.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.commons.base.Condition;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.helper.Scale;
import com.reckon.commons.helper.bean.CalculateCondition;

public class EvenInterestModifyServiceImpl {

	private static Logger logger = Logger.getLogger(EvenCorpusModifyServiceImpl.class);

	public static <R extends RentPlan,K extends Condition> String rentModify(K cb, List<R> rentPlanList, int startList) throws Exception {
		logger.info("均息法可以同时指定本金和利息，也可以同时指定租金和本金，还可以同时制定租金和利息。但不能三者同时指定");
		logger.info("均息法可以同时指定本金，利息和租金时优先考虑租金和利息的组合。");
		CalculateCondition condition = new CalculateCondition(cb);
		BigDecimal leaseMoney = condition.getLeaseMoney();
		int[] adjusList = rentModify(condition, rentPlanList, startList);
		int noAdjustCount = 0;
		for (int i = 0; i < rentPlanList.size(); i++) {
			adjusList[i] = 0;// 默认本金未修改
			RentPlan rentPlan = rentPlanList.get(i);
			if (rentPlan.getRentList() >= startList) {// 起租后，当前日期之前的计划将不能被调整
				if (adjusList[i] == 1) {
					leaseMoney = leaseMoney.subtract(rentPlan.getBusinessCorpus());
				} else {
					noAdjustCount++;
				}
			} else {
				leaseMoney = leaseMoney.subtract(rentPlan.getBusinessCorpus());
			}
		}

		// 没有发生调整的期数的平均后的本金
		int grace = condition.getGrace();
		int periodType = condition.getPeriodType();
		BigDecimal noAdjustCorpus = leaseMoney.divide(new BigDecimal(noAdjustCount), 20, BigDecimal.ROUND_HALF_UP);
		BigDecimal issueRate = condition.getIssueRate();
		for (int i = 0; i < rentPlanList.size(); i++) {
			RentPlan rentPlan = rentPlanList.get(i);
			if (rentPlan.getRentList() >= startList) {// 起租后，当前日期之前的计划将不能被调整
				BigDecimal newInterest = leaseMoney.multiply(issueRate);
				if(i == grace && periodType == 0){
					newInterest = BigDecimal.ZERO;
				}
				BigDecimal newRent = noAdjustCorpus.add(newInterest);
				
				rentPlan.setRent(newRent.setScale(Scale.RENT_SCALE, BigDecimal.ROUND_HALF_UP));
				rentPlan.setBusinessInterest(newInterest.setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP));
				rentPlan.setBusinessCorpus(noAdjustCorpus.setScale(Scale.CORPUS_SCALE, BigDecimal.ROUND_HALF_UP));
			}
		}

		//修改剩余本金项
		BigDecimal corpusOverage = condition.getLeaseMoney();
		for (int i = 0; i < rentPlanList.size(); i++) {
			RentPlan rentPlan = rentPlanList.get(i);
			BigDecimal corpus = rentPlan.getBusinessCorpus();
			corpusOverage = corpusOverage.subtract(corpus);
			rentPlan.setBusinessCorpusOverage(corpusOverage.setScale(Scale.CORPUS_SCALE, BigDecimal.ROUND_HALF_UP));
		}
		return "修改成功";
	}

	
	/**
	 * 
	 * 根据用户提交的租金计划修改值，修正租金计划，并记录本金发生变化的期项返回
	 * 返回后，rentChargeCal方法将重新均分剩余本金到未发生本金变化的气象上去
	 * 
	 * @param condition
	 * @param rentPlanList
	 * @param isOnhire
	 * @return
	 * @throws Exception
	 */
	protected static <R extends RentPlan> int[] rentModify(CalculateCondition condition, List<R> rentPlanList, int startList) throws Exception {
		int [] adjusList = new int[rentPlanList.size()];
		BigDecimal leaseMoney = condition.getLeaseMoney();
		for (int i = 0; i < rentPlanList.size(); i++) {
			adjusList[i] = 0;// 默认本金未修改
			RentPlan rentPlan = rentPlanList.get(i);
			if (rentPlan.getRentList() >= startList) {// 起租后，当前日期之前的计划将不能被调整
				BigDecimal newRent = rentPlan.getRent();
				BigDecimal newCorpus = rentPlan.getBusinessCorpus();
				BigDecimal newInterest = rentPlan.getBusinessInterest();

				if (rentPlan.getAdjustRent() != null) {
					// 只制定了租金，利息不变，租金本金
					BigDecimal adjustRent = rentPlan.getAdjustRent();
					newCorpus = adjustRent.subtract(newInterest);
					newRent = adjustRent;
					adjusList[i] = 1;
				} else if (rentPlan.getAdjustInterest() != null) {
					// 只制定了利息，本金不变，租金修正
					BigDecimal adjustInterest = rentPlan.getAdjustInterest();
					newRent = newCorpus.add(adjustInterest);
					newInterest = adjustInterest;
				} else if (rentPlan.getAdjustCorpus() != null) {
					// 只制定了本金，利息不变，租金修正
					BigDecimal adjustCorpus = rentPlan.getAdjustCorpus();
					newRent = newInterest.add(adjustCorpus);
					newCorpus = adjustCorpus;
					adjusList[i] = 1;
				}

				rentPlan.setRent(newRent);
				rentPlan.setBusinessInterest(newInterest);
				rentPlan.setBusinessCorpus(newCorpus);

				BigDecimal newCorpusOverage = leaseMoney.subtract(newCorpus);
				rentPlan.setBusinessCorpusOverage(newCorpusOverage);
			}
		}
		return adjusList;
	}
	
	public static <R extends RentPlan, K extends Condition> String rentModify(K cb, List<R> rentPlanList, int startList, int endList, int averageStartList, int averageEndList) throws Exception {
		
		return null;
	}
}
