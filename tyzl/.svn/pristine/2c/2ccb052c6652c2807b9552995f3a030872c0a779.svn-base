package com.reckon.commons.renttranrate.service.impl.evenrent;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.commons.base.Condition;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.bean.TransRateInfo;
import com.reckon.commons.helper.RentTranRateHelper;
import com.reckon.commons.helper.bean.CalculateCondition;

/**
 * @author MHY QQ:648020894
 */
public class R_NextListServiceImpl {
	
	private static Logger logger = Logger.getLogger(R_NextListServiceImpl.class);


	/**
	 * 
	 *  次期的等额租金调息方法
	 * 
	 * @param cb
	 * @param icb
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public static void doTransRate(Condition cb, List<? extends RentPlan> rentPlanList, TransRateInfo icb) throws Exception {
		logger.info("等额租金，次期利率调整开始！");
		int grace = cb.getGrace();
		int startIndex = icb.getStartList() - 1;
		BigDecimal newYearRate = icb.getNewYearRate();
		CalculateCondition condition = new CalculateCondition(cb);
		BigDecimal newIssueRate = condition.getIssueRate(newYearRate);
		BigDecimal corpusRemain = cb.getLeaseMoney();
		for(int i = 0; i < startIndex; i++){
			corpusRemain = corpusRemain.subtract(rentPlanList.get(i).getBusinessCorpus());
		}
		BigDecimal coefficient = RentTranRateHelper.getRentFloatCoefficient(rentPlanList, startIndex, newIssueRate, corpusRemain);
		for(int i = startIndex; i < rentPlanList.size(); i++){
			RentPlan rp = rentPlanList.get(i);
			BigDecimal newRent = rp.getRent();
			BigDecimal newCorpus = rp.getBusinessCorpus();
			BigDecimal newInterest = rp.getBusinessInterest();
			BigDecimal newCorpusOverage = rp.getBusinessCorpusOverage();
			if(i < grace){
				newInterest = corpusRemain.multiply(newIssueRate);
				newRent = newCorpus.add(newInterest);
			} else {
				newRent = rp.getRent().multiply(coefficient);
				newInterest = corpusRemain.multiply(newIssueRate);
				newCorpus = newRent.subtract(newInterest);
				newCorpusOverage = corpusRemain.subtract(newCorpus);
			}
			
			rp.setRent(newRent);
			rp.setBusinessCorpus(newCorpus);
			rp.setBusinessInterest(newInterest);
			rp.setOverageRent(newRent);
			rp.setOverageCorpus(newCorpus);
			rp.setOverageInterest(newInterest);
			rp.setBusinessCorpusOverage(newCorpusOverage);
			rp.setYearRate(newYearRate);
		}
		logger.info("等额租金，次期利率调整开始！");
	}
}
