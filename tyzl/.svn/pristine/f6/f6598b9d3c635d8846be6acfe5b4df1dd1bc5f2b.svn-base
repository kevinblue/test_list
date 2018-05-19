package com.reckon.commons.renttranrate.service.impl.eveninterest;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.commons.base.Condition;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.bean.TransRateInfo;
import com.reckon.commons.helper.Scale;

/**
 * @author MHY QQ:648020894
 */
public class I_NextListServiceImpl {
	
	private static Logger logger = Logger.getLogger(I_NextListServiceImpl.class);


	/**
	 * 
	 *  次期的均息法调息
	 * 
	 * @param cb
	 * @param icb
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public static void doTransRate(Condition cb, List<? extends RentPlan> rentPlanList, TransRateInfo icb) throws Exception {
		logger.info("均息法，次期利率调整开始！");
		int startIndex = icb.getStartList() - 1;// 调息起始期在list中的位置
		BigDecimal newYearRate = icb.getNewYearRate();
		// 本金不变，利息租金变化
		for (int i = startIndex; i < rentPlanList.size(); i++) {
			RentPlan rp = rentPlanList.get(i);
			BigDecimal oldYearRate = rp.getYearRate();
			
			BigDecimal newInterest = rp.getBusinessInterest().multiply(newYearRate).divide(oldYearRate, Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
			BigDecimal newRent = rp.getBusinessCorpus().add(newInterest);
			
			rp.setBusinessInterest(newInterest);
			rp.setRent(newRent);
			rp.setOverageRent(newRent);
			rp.setOverageInterest(newInterest);
			rp.setYearRate(newYearRate);
		}
		logger.info("均息法，次期利率调整结束！");
	}
}
