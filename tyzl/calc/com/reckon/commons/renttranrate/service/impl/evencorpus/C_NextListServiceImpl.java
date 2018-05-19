package com.reckon.commons.renttranrate.service.impl.evencorpus;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.commons.base.Condition;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.bean.TransRateInfo;
import com.reckon.commons.helper.bean.CalculateCondition;

/**
 * @author MHY QQ:648020894
 */
public class C_NextListServiceImpl {
	
	private static Logger logger = Logger.getLogger(C_NextListServiceImpl.class);
	
	/**
	 * 次期的等额本金调息方法
	 * 
	 * @param cb
	 * @param icb
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public static void doTransRate(Condition cb, List<? extends RentPlan> rentPlanList, TransRateInfo icb) throws Exception {
		logger.info("等额本金，次期利率调整开始！");
		int startIndex = icb.getStartList() - 1;// 调息起始期在list中的位置
		BigDecimal newYearRate = icb.getNewYearRate();
		CalculateCondition condition = new CalculateCondition(cb);
		
		BigDecimal corpusRemain = cb.getLeaseMoney();
		for(int i = 0; i < startIndex; i++){
			corpusRemain = corpusRemain.subtract(rentPlanList.get(i).getBusinessCorpus());
		}
		
		// 本金不变，利息调整
		for (int i = startIndex; i < rentPlanList.size(); i++) {
			BigDecimal newIssueRate = condition.getIssueRate(newYearRate);
			RentPlan rp = rentPlanList.get(i);
			BigDecimal newInterest = rp.getBusinessCorpusOverage().multiply(newIssueRate);
			BigDecimal newRent = rp.getBusinessCorpus().add(newInterest);
			rp.setBusinessInterest(newInterest);
			rp.setRent(newRent);
			rp.setOverageRent(newRent);
			rp.setOverageInterest(newInterest);
			rp.setYearRate(newYearRate);
		}
		logger.info("等额本金，次期利率调整结束！");
	}
}
