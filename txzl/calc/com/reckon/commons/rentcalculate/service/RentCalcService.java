package com.reckon.commons.rentcalculate.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.commons.base.CashDetail;
import com.reckon.commons.base.Condition;
import com.reckon.commons.base.FundPlan;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.bean.PlanContext;
import com.reckon.commons.helper.CashDetailCalculator;
import com.reckon.commons.helper.FinacesPlanCalculator;
import com.reckon.commons.helper.FundPlanCalculator;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.commons.helper.RentCalculateUtil;
import com.reckon.commons.helper.bean.CalculateCondition;
import com.reckon.commons.rentcalculate.service.impl.EvenCorpusCaleServiceImpl;
import com.reckon.commons.rentcalculate.service.impl.EvenInterestCalcServiceImpl;
import com.reckon.commons.rentcalculate.service.impl.EvenRentCalcServiceImpl;
import com.reckon.commons.rentcalculate.service.impl.IrregularRentCalServiceImpl;

public class RentCalcService {

	private static Logger logger = Logger.getLogger(EvenRentCalcServiceImpl.class);

	public static <T extends RentPlan> List<T> rentCalculateForEvenCorpus(Condition cb, Class<T> clazs) throws Exception {
		logger.info("等额本金测算开始....");
		CalculateCondition condition = new CalculateCondition(cb);
		List<T> rentPlanList = RentCalculateUtil.calculateForSameCorpus(condition, clazs);
		FinacesPlanCalculator.processFinacesRentPlan(condition, rentPlanList);
		RentCalculateHelper.fillOtherInfoOfRentPlan(cb, rentPlanList);
		return rentPlanList;
	}

	public static <T extends RentPlan> List<T> rentCalculateForEvenInterest(Condition cb, Class<T> clazs) throws Exception {
		logger.info("等额利息法测算开始....");
		CalculateCondition condition = new CalculateCondition(cb);
		List<T> rentPlanList = RentCalculateUtil.calculateForSameInterest(condition, clazs);
		FinacesPlanCalculator.processFinacesRentPlan(condition, rentPlanList);
		RentCalculateHelper.fillOtherInfoOfRentPlan(cb, rentPlanList);
		return rentPlanList;
	}

	public static <T extends RentPlan> List<T> rentCalculateForEvenRent(Condition cb, Class<T> clazs) throws Exception {
		logger.info("等额租金测算开始....");
		CalculateCondition condition = new CalculateCondition(cb);
		List<T> rentPlanList = RentCalculateUtil.calculateForSameRent(condition, clazs);
		FinacesPlanCalculator.processFinacesRentPlan(condition, rentPlanList);
		RentCalculateHelper.fillOtherInfoOfRentPlan(cb, rentPlanList);
		return rentPlanList;
	}

	public static <T extends RentPlan> List<T> rentCalculateForIrregularRent(Condition cb, Class<T> clazs) throws Exception {
		logger.info("不规则测算开始....");
		CalculateCondition condition = new CalculateCondition(cb);
		List<T> rentPlanList = RentCalculateUtil.calculateForSameRent(condition, clazs);
		FinacesPlanCalculator.processFinacesRentPlan(condition, rentPlanList);
		RentCalculateHelper.fillOtherInfoOfRentPlan(cb, rentPlanList);
		return rentPlanList;
	}

	public static <T extends RentPlan> List<T> rentCalculate(Condition cb, Class<T> clazs) throws Exception {
		List<T> rentPlan = new ArrayList<T>();
		if ("even_rent".equals(cb.getSettleMethod().toString())) {
			rentPlan = EvenRentCalcServiceImpl.rentCalculate(cb, clazs);
		} else if ("even_interest".equals(cb.getSettleMethod().toString())) {
			rentPlan = EvenInterestCalcServiceImpl.rentCalculate(cb, clazs);
		} else if ("even_corpus".equals(cb.getSettleMethod().toString())) {
			rentPlan = EvenCorpusCaleServiceImpl.rentCalculate(cb, clazs);
		} else if ("irregular_rent".equals(cb.getSettleMethod().toString())) {
			rentPlan = IrregularRentCalServiceImpl.rentCalculate(cb, clazs);
		}
		RentCalculateHelper.fillOtherInfoOfRentPlan(cb, rentPlan);
		return rentPlan;
	}

	public static <R extends RentPlan, F extends FundPlan, C extends CashDetail> PlanContext rentCalculate(Condition condition, Class<R> rclazs, Class<F> fclazs, Class<C> cclazs) throws Exception {
		logger.info("租金测算开始");

		// 创建租金计划
		List<R> rentPlanList = RentCalcService.rentCalculate(condition, rclazs);

		// 创建资金收付计划
		List<F> fundPlanList = FundPlanCalculator.createBusinessFundPlan(condition, fclazs);

		// 创建现金流
		List<C> cashDetail = CashDetailCalculator.createCashFlowList(fundPlanList, rentPlanList, cclazs);

		// 计算planIRR
		BigDecimal planIrr = RentCalculateHelper.getPlanIrrFromRentPlan(condition, rentPlanList);
		condition.setPlanIrr(planIrr);

		// 计算IRR
		BigDecimal irr = RentCalculateHelper.getRealIrrFromCashDetail(condition, cashDetail);
		condition.setIrr(irr);

		// 计算月租金总额
		BigDecimal rentTotal = RentCalculateHelper.getRentTotal(rentPlanList);
		condition.setRentTotal(rentTotal);
		
		// 租金总额：月租金总额加上首付款
		BigDecimal cleanRentTotal = rentTotal.add(condition.getFirstPayment());
		condition.setCleanRentTotal(cleanRentTotal);

		// 计算利息总额
		BigDecimal interestTotal = RentCalculateHelper.getInterestTotal(rentPlanList);
		condition.setInterestTotal(interestTotal);

		// 计算付款总额
		BigDecimal paymentTotal = rentTotal.add(condition.getFirstPayment());
		paymentTotal = paymentTotal.add(condition.getHandlingChargeMoney());
		paymentTotal = paymentTotal.add(condition.getManagementMoney());
		condition.setPaymentTotal(paymentTotal);
		
		// 每期租金
		condition.setIssueRent(rentPlanList.get(0).getRent());

		PlanContext planContext = new PlanContext();
		planContext.setCondition(condition);
		planContext.setRentPlan(rentPlanList);
		planContext.setFundPlan(fundPlanList);
		planContext.setCashFlow(cashDetail);
		return planContext;
	}
}
