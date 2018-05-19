package com.reckon.commons.rentadjust.service;

import java.math.BigDecimal;
import java.util.List;

import com.reckon.commons.base.Condition;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.helper.FinacesPlanCalculator;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.commons.helper.bean.CalculateCondition;
import com.reckon.commons.rentadjust.service.impl.RentAdjustForStopService;
import com.reckon.commons.rentadjust.service.impl.RentAdjustForTQHKService;
import com.reckon.commons.rentadjust.service.impl.RentAdjustForYQService;
import com.reckon.commons.rentadjust.service.impl.RentAdjustForZQService;

public class RentAdjustService {

	public static void rentAdjustForYQ(Condition condition, List<RentPlan> rentPlan, int startList, int delayNumber, boolean isInterest) throws Exception {
		RentAdjustForYQService.rentAdjust(condition, rentPlan, startList, delayNumber, isInterest);
		CalculateCondition cc = new CalculateCondition(condition);
		FinacesPlanCalculator.processFinacesRentPlan(cc, rentPlan);
		RentCalculateHelper.fillOtherInfoOfRentPlan(condition, rentPlan);
		rentCalculate(condition, rentPlan);
	}

	public static void rentAdjustForZQ(Condition condition, List<RentPlan> rentPlan, int startList, int endList, int extensionNumber) throws Exception {
		RentAdjustForZQService.rentAdjust(condition, rentPlan, startList, endList, extensionNumber);
		CalculateCondition cc = new CalculateCondition(condition);
		FinacesPlanCalculator.processFinacesRentPlan(cc, rentPlan);
		RentCalculateHelper.fillOtherInfoOfRentPlan(condition, rentPlan);
		rentCalculate(condition, rentPlan);
	}

	public static void rentAdjustForTQHK(Condition condition, List<RentPlan> rentPlan, int startList, int endList, boolean isDateChange) throws Exception {
		RentAdjustForTQHKService.rentAdjust(condition, rentPlan, startList, endList, isDateChange);
		CalculateCondition cc = new CalculateCondition(condition);
		FinacesPlanCalculator.processFinacesRentPlan(cc, rentPlan);
		RentCalculateHelper.fillOtherInfoOfRentPlan(condition, rentPlan);
		rentCalculate(condition, rentPlan);
	}

	public static void rentAdjustForStop(Condition condition, List<RentPlan> rentPlan, String endDate) throws Exception {
		RentAdjustForStopService.rentAdjust(condition, rentPlan, endDate);
		CalculateCondition cc = new CalculateCondition(condition);
		FinacesPlanCalculator.processFinacesRentPlan(cc, rentPlan);
		RentCalculateHelper.fillOtherInfoOfRentPlan(condition, rentPlan);
		rentCalculate(condition, rentPlan);
	}

	private static <R extends RentPlan> void rentCalculate(Condition condition, List<R> rentPlanList) throws Exception {

		// 计算planIRR
		BigDecimal planIrr = RentCalculateHelper.getPlanIrrFromRentPlan(condition, rentPlanList);
		condition.setPlanIrr(planIrr);

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
		BigDecimal paymentTotal = cleanRentTotal.add(condition.getHandlingChargeMoney());
		condition.setPaymentTotal(paymentTotal);
	}
}
