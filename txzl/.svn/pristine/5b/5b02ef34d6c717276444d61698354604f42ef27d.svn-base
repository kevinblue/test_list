package com.reckon.commons.rentcalculate.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.commons.base.Condition;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.helper.FinacesPlanCalculator;
import com.reckon.commons.helper.RentCalculateUtil;
import com.reckon.commons.helper.bean.CalculateCondition;

public class EvenCorpusCaleServiceImpl {

	private static Logger logger = Logger.getLogger(EvenRentCalcServiceImpl.class);

	public static <T extends RentPlan> List<T> rentCalculate(Condition cb, Class<T> clazs) throws Exception {
		logger.info("等额本金测算开始....");
		CalculateCondition condition = new CalculateCondition(cb);
		List<T> rentPlanList = RentCalculateUtil.calculateForSameCorpus(condition, clazs);
		FinacesPlanCalculator.processFinacesRentPlan(condition, rentPlanList);
		return rentPlanList;
	}
}
