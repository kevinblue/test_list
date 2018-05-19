package com.reckon.commons.renttranrate.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.commons.base.Condition;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.bean.TransRateInfo;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.commons.renttranrate.service.impl.evencorpus.C_NextDayServiceImpl;
import com.reckon.commons.renttranrate.service.impl.evencorpus.C_NextListServiceImpl;
import com.reckon.commons.renttranrate.service.impl.evencorpus.C_NextMonthServiceImpl;
import com.reckon.commons.renttranrate.service.impl.evencorpus.C_NextYearServiceImpl;
import com.reckon.commons.renttranrate.service.impl.eveninterest.I_NextDayServiceImpl;
import com.reckon.commons.renttranrate.service.impl.eveninterest.I_NextListServiceImpl;
import com.reckon.commons.renttranrate.service.impl.eveninterest.I_NextMonthServiceImpl;
import com.reckon.commons.renttranrate.service.impl.eveninterest.I_NextYearServiceImpl;
import com.reckon.commons.renttranrate.service.impl.evenrent.R_NextDayServiceImpl;
import com.reckon.commons.renttranrate.service.impl.evenrent.R_NextListServiceImpl;
import com.reckon.commons.renttranrate.service.impl.evenrent.R_NextMonthServiceImpl;
import com.reckon.commons.renttranrate.service.impl.evenrent.R_NextYearServiceImpl;

/**
 * @author MHY QQ:648020894
 */
public class TransRateService {

	private static Logger logger = Logger.getLogger(TransRateService.class);
	
	private static void afterTransRate(Condition condition, List<RentPlan> rentPlan, TransRateInfo icb){
		// 填充残租金，残利息，残本金
		RentCalculateHelper.fillRentPlanAllRemain(rentPlan);
		
		// 计算planIRR
		BigDecimal planIrr = RentCalculateHelper.getPlanIrrFromRentPlan(condition, rentPlan);
		condition.setPlanIrr(planIrr);

		// 计算租金总额
		BigDecimal rentTotal = RentCalculateHelper.getRentTotal(rentPlan);
		condition.setRentTotal(rentTotal);

		// 计算利息总额
		BigDecimal interestTotal = RentCalculateHelper.getInterestTotal(rentPlan);
		condition.setInterestTotal(interestTotal);
		
		icb.setFinish(true);
	}
	
	
	public static TransRateInfo doTransRateNextDay(Condition condition, List<RentPlan> rentPlan, int startList, String startDate, BigDecimal yearRate) throws Exception {
		logger.info("NextDay合同：" + condition.getContractId() + "调息开始FROM->" + startList);
		Collections.sort(rentPlan);
		TransRateInfo icb = new TransRateInfo(startList, startDate, yearRate);
		if("even_corpus".equals(condition.getSettleMethod().toString())){
			C_NextDayServiceImpl.doTransRate(condition, rentPlan, icb);
		} else if("even_interest".equals(condition.getSettleMethod().toString())){
			I_NextDayServiceImpl.doTransRate(condition, rentPlan, icb);
		} else if("even_rent".equals(condition.getSettleMethod().toString())){
			R_NextDayServiceImpl.doTransRate(condition, rentPlan, icb);
		} else {
			logger.info("NextDay合同：" + condition.getContractId() + "调息意外结束。");
			return icb;
		}
		afterTransRate(condition, rentPlan, icb);
		logger.info("NextDay合同：" + condition.getContractId() + "调息正常结束。");
		return icb;
	}
	
	
	public static TransRateInfo doTransRateNextList(Condition condition, List<RentPlan> rentPlan, int startList, String startDate, BigDecimal yearRate) throws Exception {
		logger.info("NextList合同：" + condition.getContractId() + "调息开始FROM->" + startList);
		Collections.sort(rentPlan);
		TransRateInfo icb = new TransRateInfo(startList, startDate, yearRate);
		if("even_corpus".equals(condition.getSettleMethod().toString())){
			C_NextListServiceImpl.doTransRate(condition, rentPlan, icb);
		} else if("even_interest".equals(condition.getSettleMethod().toString())){
			I_NextListServiceImpl.doTransRate(condition, rentPlan, icb);
		} else if("even_rent".equals(condition.getSettleMethod().toString())){
			R_NextListServiceImpl.doTransRate(condition, rentPlan, icb);
		} else {
			logger.info("NextList合同：" + condition.getContractId() + "调息意外结束。");
			return icb;
		}
		afterTransRate(condition, rentPlan, icb);
		logger.info("NextList合同：" + condition.getContractId() + "调息正常结束。");
		return icb;
	}
	
	
	public static TransRateInfo doTransRateNextMonth(Condition condition, List<RentPlan> rentPlan, int startList, String startDate, BigDecimal yearRate) throws Exception {
		logger.info("NextMonth合同：" + condition.getContractId() + "调息开始FROM->" + startList);
		Collections.sort(rentPlan);
		TransRateInfo icb = new TransRateInfo(startList, startDate, yearRate);
		if("even_corpus".equals(condition.getSettleMethod().toString())){
			C_NextMonthServiceImpl.doTransRate(condition, rentPlan, icb);
		} else if("even_interest".equals(condition.getSettleMethod().toString())){
			I_NextMonthServiceImpl.doTransRate(condition, rentPlan, icb);
		} else if("even_rent".equals(condition.getSettleMethod().toString())){
			R_NextMonthServiceImpl.doTransRate(condition, rentPlan, icb);
		} else {
			logger.info("NextMonth合同：" + condition.getContractId() + "调息意外结束。");
			return icb;
		}
		afterTransRate(condition, rentPlan, icb);
		logger.info("NextMonth合同：" + condition.getContractId() + "调息正常结束。");
		return icb;
	}
	
	
	public static TransRateInfo doTransRateNextYear(Condition condition, List<RentPlan> rentPlan, int startList, String startDate, BigDecimal yearRate) throws Exception {
		logger.info("NextYear合同：" + condition.getContractId() + "调息开始FROM->" + startList);
		Collections.sort(rentPlan);
		TransRateInfo icb = new TransRateInfo(startList, startDate, yearRate);
		if("even_corpus".equals(condition.getSettleMethod().toString())){
			C_NextYearServiceImpl.doTransRate(condition, rentPlan, icb);
		} else if("even_interest".equals(condition.getSettleMethod().toString())){
			I_NextYearServiceImpl.doTransRate(condition, rentPlan, icb);
		} else if("even_rent".equals(condition.getSettleMethod().toString())){
			R_NextYearServiceImpl.doTransRate(condition, rentPlan, icb);
		} else {
			logger.info("NextYear合同：" + condition.getContractId() + "调息意外结束。");
			return icb;
		}
		afterTransRate(condition, rentPlan, icb);
		logger.info("NextYear合同：" + condition.getContractId() + "调息正常结束。");
		return icb;
	}
}
