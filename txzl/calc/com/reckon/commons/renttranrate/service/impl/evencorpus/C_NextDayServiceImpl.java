package com.reckon.commons.renttranrate.service.impl.evencorpus;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.commons.base.Condition;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.bean.TransRateInfo;
import com.reckon.commons.helper.RentTranRateHelper;

/**
 * @author MHY QQ:648020894
 */
public class C_NextDayServiceImpl {
	
	private static Logger logger = Logger.getLogger(C_NextListServiceImpl.class);

	/**
	 * 
	 * ( 次日调息理类)
	 * 
	 * @param cb
	 *            原商务条件
	 * @param icb
	 *            调整计划信息
	 * @param tcb
	 *            需要操作哪些表
	 * @return
	 * @throws Exception
	 */
	public static void doTransRate(Condition cb, List<? extends RentPlan> rentPlanList, TransRateInfo icb) throws Exception {
		logger.info("pmt 次日调息.....");

		int startList = icb.getStartList();
		BigDecimal newYearRate = icb.getNewYearRate();
		// 处理第一期的利息增量
		BigDecimal newAddInterest = RentTranRateHelper.calFirstNewAddInterest(cb, rentPlanList, icb);
		
		// 当期利息刷新加上变化的利息
		BigDecimal transFirstInterest = rentPlanList.get(startList - 1).getBusinessInterest();
		transFirstInterest = transFirstInterest.add(newAddInterest);
		BigDecimal transFirstCorpus = rentPlanList.get(startList - 1).getBusinessCorpus();
		BigDecimal transFirstRent = transFirstCorpus.add(transFirstInterest);
		
		// 赋值给租金计划
		rentPlanList.get(startList - 1).setRent(transFirstRent);
		rentPlanList.get(startList - 1).setBusinessInterest(transFirstInterest);
		rentPlanList.get(startList - 1).setYearRate(newYearRate);
		
		processPmtTranRateNextList(cb, rentPlanList, icb);

		logger.info("pmt 次日调息结束.....");
	}

	/**
	 * 调次期的方法调息
	 */
	private static void processPmtTranRateNextList(Condition cb, List<? extends RentPlan> rentPlanList, TransRateInfo icb) throws Exception {
		TransRateInfo icbNew = new TransRateInfo(icb.getStartList() + 1, icb.getStartDate(), icb.getNewYearRate());
		C_NextListServiceImpl.doTransRate(cb, rentPlanList, icbNew);
	}
}
