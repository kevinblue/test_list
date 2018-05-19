package com.reckon.commons.rentmodify.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import com.reckon.commons.base.Condition;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.commons.rentmodify.service.impl.EvenCorpusModifyServiceImpl;
import com.reckon.commons.rentmodify.service.impl.EvenInterestModifyServiceImpl;
import com.reckon.commons.rentmodify.service.impl.EvenRentModifyServiceImpl;
import com.reckon.commons.rentmodify.service.impl.IrregularModifyServiceImpl;

public class RentModifyService {
	
	/**
	 * 租金计划调整接口
	 * <ul>
	 * <li>可以指定每期的租金</li>
	 * <li>此方法是在指定租金后对租金计划重新计算</li>
	 * <li>并且重新计算现金流</li>
	 * <li>资金收付计划不变</li>
	 * </ul>
	 * 
	 * @param cb
	 * @param rentAdjustList
	 * @param isOnhire
	 * @return
	 * @throws Exception
	 */
	public static <R extends RentPlan,K extends Condition> void rentModify(K condition, List<R> rentPlan) throws Exception{
		Collections.sort(rentPlan);
		//开始调整租金计划
		int startList = rentPlan.size();
		for (int i = rentPlan.size() - 1; i >= 0; i--) {
			RentPlan bean = rentPlan.get(i);
			if("未核销".equals(bean.getStatus())){
				startList = bean.getRentList();
			} else {
				break;
			}
		}
		rentModify(condition, rentPlan, startList);
	}
	
	
	/**
	 * 租金计划调整接口
	 * <ul>
	 * <li>可以指定每期的租金</li>
	 * <li>此方法是在指定租金后对租金计划重新计算</li>
	 * <li>并且重新计算现金流</li>
	 * <li>资金收付计划不变</li>
	 * </ul>
	 * 
	 * @param cb
	 * @param rentAdjustList
	 * @param isOnhire
	 * @return
	 * @throws Exception
	 */
	public static <R extends RentPlan,K extends Condition> void rentModify(K condition, List<R> rentPlan, int startList) throws Exception{
		
		Collections.sort(rentPlan);
		
		if ("even_rent".equals(condition.getSettleMethod().toString())) {
			EvenRentModifyServiceImpl.rentModify(condition, rentPlan, startList);
		} else if ("even_interest".equals(condition.getSettleMethod().toString())) {
			EvenInterestModifyServiceImpl.rentModify(condition, rentPlan, startList);
		} else if ("even_corpus".equals(condition.getSettleMethod().toString())) {
			EvenCorpusModifyServiceImpl.rentModify(condition, rentPlan, startList);
		} else if ("irregular_rent".equals(condition.getSettleMethod().toString())) {
			IrregularModifyServiceImpl.rentModify(condition, rentPlan, startList);
		}

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
	}
	
	
	/**
	 * 租金计划调整接口，从startList开始到endList结束租金设置为0，然后均摊到averageStartList至averageEndList
	 * <ul>
	 * <li>此方法是在指定租金后对租金计划重新计算</li>
	 * <li>并且重新计算现金流</li>
	 * <li>资金收付计划不变</li>
	 * </ul>
	 * 
	 * @param condition
	 * @param rentPlan
	 * @param startList 从此期开始租金设置为0
	 * @param endList 到此期结束设置为0
	 * @param averageStartList 均摊开始期项
	 * @param averageEndList 均摊结束期项
	 * @throws Exception
	 */
	public static <R extends RentPlan,K extends Condition> void rentModify(K condition, List<R> rentPlan, int startList, int endList, int averageStartList, int averageEndList) throws Exception{
		
		Collections.sort(rentPlan);
		
		if ("even_rent".equals(condition.getSettleMethod().toString())) {
			EvenRentModifyServiceImpl.rentModify(condition, rentPlan, startList, endList, averageStartList, averageEndList);
		} else if ("even_interest".equals(condition.getSettleMethod().toString())) {
			EvenInterestModifyServiceImpl.rentModify(condition, rentPlan, startList, endList, averageStartList, averageEndList);
		} else if ("even_corpus".equals(condition.getSettleMethod().toString())) {
			EvenCorpusModifyServiceImpl.rentModify(condition, rentPlan, startList, endList, averageStartList, averageEndList);
		} else if ("irregular_rent".equals(condition.getSettleMethod().toString())) {
			IrregularModifyServiceImpl.rentModify(condition, rentPlan, startList, endList, averageStartList, averageEndList);
		}
		
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
	}
}
