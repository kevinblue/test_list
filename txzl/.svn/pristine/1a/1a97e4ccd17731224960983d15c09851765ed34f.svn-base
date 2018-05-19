package com.reckon.commons.rentadjust.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.commons.base.Condition;
import com.reckon.commons.base.RentPlan;

public class RentAdjustForTQHKService {

	private static Logger logger = Logger.getLogger(RentAdjustForTQHKService.class);

	/**
	 * 中途终止，提前还款
	 * 
	 * @param condition
	 *            商务报价
	 * @param rentPlan
	 *            租金计划
	 * @param startList
	 *            开始期项
	 * @param endDate
	 *            结束日期
	 * @return
	 */
	public static String rentAdjust(Condition condition, List<RentPlan> rentPlan, int startList, int endList, boolean isDateChange) {
		try {

			int realStartList = Math.min(startList, endList);
			int realEndList = Math.max(startList, endList);

			List<RentPlan> allTemp = new ArrayList<RentPlan>(rentPlan);
			Collections.sort(allTemp);
			rentPlan.clear();

			RentPlan start = allTemp.get(realStartList - 1);// 合并后的一期
			List<String> planDateList = new ArrayList<String>();// 日期顺延

			for (RentPlan bean : allTemp) {
				int rentList = bean.getRentList();
				if (realStartList < rentList && rentList <= realEndList) {
					BigDecimal bbc = bean.getBusinessCorpus();
					BigDecimal boc = bean.getOverageCorpus();
					start.setRent(start.getRent().add(bbc));
					start.setBusinessCorpus(start.getBusinessCorpus().add(bbc));
					start.setBusinessCorpusOverage(start.getBusinessCorpusOverage().subtract(bbc));
					start.setOverageCorpus(start.getOverageCorpus().add(boc));
					start.setOverageRent(start.getOverageRent().add(boc));
				} else {
					rentPlan.add(bean);
				}
				planDateList.add(bean.getPlanDate());// 日期顺延
			}

			for (int i = realStartList; i < rentPlan.size(); i++) {
				rentPlan.get(i).setRentList(i + 1);// 期数重排
				if (isDateChange) {
					rentPlan.get(i).setPlanDate(planDateList.get(i));// 日期顺延
				}
			}
			return "success";
		} catch (Exception e) {
			logger.error("租金计划调整出错。", e);
			e.printStackTrace();
			return "error";
		}
	}
}
