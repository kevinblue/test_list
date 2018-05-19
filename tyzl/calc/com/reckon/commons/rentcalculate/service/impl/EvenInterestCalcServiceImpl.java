package com.reckon.commons.rentcalculate.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.commons.base.Condition;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.helper.FinacesPlanCalculator;
import com.reckon.commons.helper.RentCalculateUtil;
import com.reckon.commons.helper.bean.CalculateCondition;

public class EvenInterestCalcServiceImpl {

	private static Logger logger = Logger.getLogger(EvenInterestCalcServiceImpl.class);

	public static <T extends RentPlan> List<T> rentCalculate(Condition cb, Class<T> clazs) throws Exception {
		logger.info("等额利息法测算开始....");
		CalculateCondition condition = new CalculateCondition(cb);
		List<T> rentPlanList = RentCalculateUtil.calculateForSameInterest(condition, clazs);
		FinacesPlanCalculator.processFinacesRentPlan(condition, rentPlanList);
		return rentPlanList;
	}
}
