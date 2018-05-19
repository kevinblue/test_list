package com.reckon.commons.rentmodify.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.reckon.commons.base.Condition;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.helper.PMTCalculateUtil;
import com.reckon.commons.helper.Scale;
import com.reckon.commons.helper.bean.CalculateCondition;

public class EvenRentModifyServiceImpl {

	private static Logger logger = Logger.getLogger(EvenCorpusModifyServiceImpl.class);

	/**
	 * @param cb
	 * @param rentPlanList
	 *            租金计划，在此基础上修改
	 * @param startList
	 *            均摊开始期项
	 * @return
	 * @throws Exception
	 */
	public static <R extends RentPlan, K extends Condition> String rentModify(K cb, List<R> rentPlanList, int startList) throws Exception {
		logger.info("等额租金在直接修改租金计划时，只允许修改租金...开始...");
		CalculateCondition condition = new CalculateCondition(cb);
		BigDecimal leaseMoney = condition.getLeaseMoney();
		int grace = condition.getGrace();
		int periodType = condition.getPeriodType();

		List<BigDecimal> adjust = new ArrayList<BigDecimal>();

		BigDecimal businessCorpusOverage = leaseMoney;
		BigDecimal equipEndValue = condition.getEndMoney();
		BigDecimal rate = condition.getIssueRate();
		for (int i = 0; i < rentPlanList.size(); i++) {
			RentPlan rentPlan = rentPlanList.get(i);
			if (rentPlan.getRentList() >= startList) {// 起租后，当前日期之前的计划将不能被调整
				BigDecimal adjustRent = rentPlan.getAdjustRent();
				if (adjustRent != null) {
					adjust.add(adjustRent);
				} else {
					adjust.add(null);
				}
			} else {
				businessCorpusOverage = businessCorpusOverage.subtract(rentPlan.getBusinessCorpus());
			}
		}

		BigDecimal noAdjustRentNew = PMTCalculateUtil.getPMT(rate, rentPlanList.size(), businessCorpusOverage, equipEndValue, periodType, adjust);
		for (int i = 0; i < rentPlanList.size(); i++) {
			RentPlan bean = rentPlanList.get(i);
			if (bean.getRentList() >= startList) {// 起租后，当前日期之前的计划将不能被调整
				BigDecimal newRent = bean.getRent();
				BigDecimal adjustRent = bean.getAdjustRent();
				if (adjustRent != null) {
					newRent = adjustRent;
				} else {
					newRent = noAdjustRentNew;
				}

				BigDecimal issueRate = condition.getIssueRate();
				BigDecimal newInterest = businessCorpusOverage.multiply(issueRate).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
				if (i == grace && periodType == 0) {
					newInterest = BigDecimal.ZERO;
				}
				BigDecimal newCorpus = newRent.subtract(newInterest);
				businessCorpusOverage = businessCorpusOverage.subtract(newCorpus);

				if (bean.getRentList() == rentPlanList.size()) {
					businessCorpusOverage = businessCorpusOverage.subtract(equipEndValue);
					newCorpus = newCorpus.add(businessCorpusOverage);
					newInterest = newRent.subtract(newCorpus);
					businessCorpusOverage = equipEndValue;
				}

				bean.setRent(newRent);
				bean.setBusinessCorpus(newCorpus);
				bean.setBusinessInterest(newInterest);
				bean.setBusinessCorpusOverage(businessCorpusOverage);

				bean.setOverageRent(newRent);
				bean.setOverageInterest(newInterest);
				bean.setOverageCorpus(newCorpus);
			}
		}
		return "修改成功";
	}

	/**
	 * @param cb
	 * @param rentPlanList
	 *            租金计划，在此基础上修改
	 * @param startList
	 *            再融资开始期项，从该期开始租金设置为0
	 * @param endList
	 *            再融资结束期项，租金设置为0从该期结束。
	 * @param averageStartList
	 *            多出的租金从该期开始均摊
	 * @param averageEndList
	 *            多出的租金从该期结束均摊
	 * @return
	 * @throws Exception
	 */
	public static <R extends RentPlan, K extends Condition> String rentModify(K cb, List<R> rentPlanList, int startList, int endList, int averageStartList, int averageEndList) throws Exception {
		// 确认调整范围之起始值
		int realStartList = Math.min(startList, endList);
		realStartList = Math.min(realStartList, averageStartList);
		realStartList = Math.min(realStartList, averageEndList);

		// 确认调整范围之最后值
		int realEndList = Math.max(startList, endList);
		realEndList = Math.max(realEndList, averageStartList);
		realEndList = Math.max(realEndList, averageEndList);

		// 确定起租本金，租赁期限，还有利率和期末余值
		CalculateCondition condition = new CalculateCondition(cb);
		BigDecimal pv = condition.getLeaseMoney();
		if (realStartList > 1) {
			pv = rentPlanList.get(realStartList - 2).getBusinessCorpusOverage();
		}
		BigDecimal fv = rentPlanList.get(realEndList - 1).getBusinessCorpusOverage();
		BigDecimal rate = condition.getIssueRate();
		int nper = realEndList - realStartList + 1;

		// 确定已知租金的期项
		Map<Integer, BigDecimal> adjust = new HashMap<Integer, BigDecimal>();
		for (int i = realStartList; i <= realEndList; i++) {
			if (i >= Math.min(startList, endList) && i <= Math.max(startList, endList)) {
				adjust.put(i - realStartList + 1, BigDecimal.ZERO);// 归零部分
			} else if (i < Math.min(averageStartList, averageEndList) || i > Math.max(averageStartList, averageEndList)) {
				adjust.put(i - realStartList + 1, rentPlanList.get(i - 1).getRent());// 原始值部分
			} else {
				// 未知租金部分
			}
		}

		// 计算未知期项的租金
		BigDecimal newRent = PMTCalculateUtil.getPMT(rate, nper, pv, fv, 1, adjust);
		for (int i = realStartList; i <= realEndList; i++) {
			RentPlan bean = rentPlanList.get(i - 1);
			BigDecimal yz = adjust.get(i - realStartList + 1);
			BigDecimal rentTemp = (yz != null ? yz : newRent);
			BigDecimal interestTemp = pv.multiply(rate).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
			BigDecimal corpusTemp = rentTemp.subtract(interestTemp);

			pv = pv.subtract(corpusTemp);

			if (i == realEndList) {
				pv = pv.subtract(fv);
				corpusTemp = corpusTemp.add(pv);
				interestTemp = rentTemp.subtract(corpusTemp);
				pv = fv;
			}

			bean.setRent(rentTemp);
			bean.setBusinessCorpus(corpusTemp);
			bean.setBusinessInterest(interestTemp);
			bean.setBusinessCorpusOverage(pv);

			bean.setOverageRent(rentTemp);
			if(rentTemp.compareTo(BigDecimal.ZERO) == 0){
				bean.setOverageCorpus(BigDecimal.ZERO);
				bean.setOverageInterest(BigDecimal.ZERO);
			} else {
				bean.setOverageCorpus(corpusTemp);
				bean.setOverageInterest(interestTemp);
			}
		}
		return "修改成功";
	}
}
