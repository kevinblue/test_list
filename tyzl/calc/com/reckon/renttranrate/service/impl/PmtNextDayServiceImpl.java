package com.reckon.renttranrate.service.impl;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.InterContBean;
import com.reckon.commons.helper.Scale;
import com.reckon.renttranrate.service.TransRateHelper;
import com.reckon.renttranrate.service.TransRateService;


/**
 * @author MHY QQ:648020894
 */
public class PmtNextDayServiceImpl implements TransRateService {
	static Logger logger = Logger.getLogger(PmtNextListServiceImpl.class);

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
	public void processPmtTranRate(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb) throws Exception {
		logger.info("pmt 次日调息.....");
		// 开始调息期数
		int startList = icb.getStartList();

		// 处理第一期的利息增量
		BigDecimal newAddInterest = TransRateHelper.calFirstNewAddInterest(cb, oldRentPlanContext, icb);
		logger.info("处理第一期的利息增量=" + newAddInterest.toString());
		System.out.println("处理第一期的利息增量=" + newAddInterest.toString());
		// 当期利息刷新加上变化的利息
		String transFirstInterest = oldRentPlanContext.getInterestBusinessList().get(startList - 1);
		transFirstInterest = new BigDecimal(transFirstInterest).add(newAddInterest).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP).toString();
		String transFirstCorpus = oldRentPlanContext.getCorpusBusinessList().get(startList - 1);
		String transFirstRent = new BigDecimal(transFirstCorpus).add(new BigDecimal(transFirstInterest)).toString();
		
		// 赋值给租金计划
		oldRentPlanContext.getRentList().set(startList - 1, transFirstRent);
		oldRentPlanContext.getInterestBusinessList().set(startList - 1, transFirstInterest);
		oldRentPlanContext.getYearRateList().set(startList - 1, icb.getNewYearRate());
		
		// 加一期调用次期的算法
		int newStartList = icb.getStartList() + 1;
		icb.setStartList(newStartList);
		processPmtTranRateNextList(cb, oldRentPlanContext, icb);
		icb.setStartList(startList);// 还原开始日期

		logger.info("pmt 次日调息结束.....");
	}

	/**
	 * 调次期的方法调息
	 */
	private void processPmtTranRateNextList(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb) throws Exception {
		PmtNextListServiceImpl tranRateService = new PmtNextListServiceImpl();
		tranRateService.processPmtTranRate(cb, oldRentPlanContext, icb);
	}
}
