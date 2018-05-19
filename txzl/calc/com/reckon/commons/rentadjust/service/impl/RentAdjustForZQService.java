package com.reckon.commons.rentadjust.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.commons.base.Condition;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.commons.helper.RentCalculateUtil;
import com.reckon.commons.helper.Type;
import com.reckon.commons.helper.bean.CalculateCondition;

public class RentAdjustForZQService {

	private static Logger logger = Logger.getLogger(RentAdjustForZQService.class);

	/**
	 * 展期调整
	 * 
	 * @param condition 商务报价
	 * @param rentPlan 租金计划
	 * @param startList 开始期项
	 * @param endList 结束期项
	 * @param extensionNumber
	 * @return
	 */
	public static String rentAdjust(Condition condition, List<RentPlan> rentPlan, int startList, int endList, int extensionNumber) {
		try {
			Collections.sort(rentPlan);
			if(startList > 0 && endList >= startList && endList - 1 <= rentPlan.size()){
				BigDecimal newLeaseMoney = startList == 1 ? condition.getLeaseMoney() : rentPlan.get(startList - 2).getBusinessCorpusOverage();
				BigDecimal newEndLeaseMoney = rentPlan.get(endList - 1).getBusinessCorpusOverage();
				CalculateCondition cc = new CalculateCondition(condition);
				cc.setLeaseMoney(newLeaseMoney);
				cc.setEndMoney(newEndLeaseMoney);
				cc.setIncomeTimes(extensionNumber);
				cc.setStartDate(rentPlan.get(startList - 1).getPlanDate());
				cc.setFirstPlanDate(rentPlan.get(endList - 1).getPlanDate());
				cc.setGrace(0);
				List<? extends RentPlan> newAddRentPlan = RentCalculateUtil.calculateForSameRent(cc, rentPlan.get(0).getClass());
				for (int i = 0; i < newAddRentPlan.size(); i++) {
					RentPlan rp = newAddRentPlan.get(i);
					rp.setRentType(Type.RELEASING);
					if (i < endList - startList + 1) {
						rentPlan.set(i + startList - 1, rp);
					} else {
						rentPlan.add(i + startList - 1, rp);
					}
				}
				
				CalculateCondition calculateCondition = new CalculateCondition(condition);
				calculateCondition.setGrace(0);
				calculateCondition.setIncomeTimes(rentPlan.size());
				List<? extends RentPlan> newRentPlanModel = RentCalculateUtil.getEmptyPlanModel(calculateCondition, rentPlan.get(0).getClass());
				for (int i = 0; i < newRentPlanModel.size(); i++) {
					rentPlan.get(i).setRentList(newRentPlanModel.get(i).getRentList());
					rentPlan.get(i).setPlanDate(newRentPlanModel.get(i).getPlanDate());
					rentPlan.get(i).setStartDate(newRentPlanModel.get(i).getStartDate());
					rentPlan.get(i).setEndDate(newRentPlanModel.get(i).getEndDate());
				}
				RentCalculateHelper.fillOtherInfoOfRentPlan(condition, rentPlan);
			}
			return "success";
		} catch (Exception e) {
			logger.error("租金计划调整出错。", e);
			e.printStackTrace();
			return "error";
		}
	}
}
