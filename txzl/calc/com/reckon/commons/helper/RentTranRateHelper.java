package com.reckon.commons.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.commons.base.Condition;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.bean.PBOCInfo;
import com.reckon.commons.bean.TransRateInfo;
import com.reckon.commons.helper.bean.CalculateCondition;
import com.reckon.commons.util.DateTools;

/**
 * @author MHY QQ:648020894
 */
public class RentTranRateHelper {

	private static Logger logger = Logger.getLogger(RentTranRateHelper.class);

	/**
	 * 计算出该合同的新利率
	 * 
	 * @param floatType
	 *            调息方式
	 * @param leaseRerm
	 *            期限
	 * @param oldYearRate
	 *            旧利率
	 * @param adjustValue
	 *            调整值
	 * @param adjust
	 *            央行利率
	 * @return
	 */
	public static BigDecimal getNewRate(Condition condition, BigDecimal oldYearRate, PBOCInfo adjust) {
		int leaseTerm = condition.getLeaseTerm();
		BigDecimal newYearRate = oldYearRate;
		BigDecimal floatPoint = adjust.getFloatRate(leaseTerm);// 基准利率上涨点，原来是5调倒6，则上张点是6-5=1
		if ("proportion".equals(condition.getRateFloatType().toString())) {
			// 按央行利率浮动时
			BigDecimal newBaseRate = adjust.getBaseRate(leaseTerm);// 基准利率
			BigDecimal oldBaseRate = newBaseRate.subtract(floatPoint);
			newYearRate = oldYearRate.multiply(newBaseRate);
			newYearRate = newYearRate.divide(oldBaseRate, Scale.RATE_SCALE, BigDecimal.ROUND_HALF_UP);
		} else if ("add".equals(condition.getRateFloatType().toString())) {
			// 按央利率加点时
			newYearRate = oldYearRate.add(floatPoint);
		} else if ("fixed".equals(condition.getRateFloatType().toString())) {
			// 固定利率
			newYearRate = oldYearRate.add(condition.getRateFloatAmt());
		}
		return newYearRate;
	}

	/**
	 * 
	 * 等比上调的比例，等额租金法，非宽限期
	 * 
	 * @param rentPlanList
	 *            租金计划列表 R1...Rs...Rn
	 * @param startList
	 *            起始期数 1...s...n
	 * @param issueRate
	 *            新每期利率i
	 * @param corpusRemain
	 *            剩余本金Cr
	 * @return 上浮系数x
	 */
	public static BigDecimal getRentFloatCoefficient(List<? extends RentPlan> rentPlanList, int startIndex, BigDecimal newIssueRate, BigDecimal corpusRemain) {
		BigDecimal one = BigDecimal.ONE;
		BigDecimal daoRate = one.divide(one.add(newIssueRate), 20, BigDecimal.ROUND_HALF_UP);
		BigDecimal fenMu = BigDecimal.ZERO;
		for (int i = startIndex; i < rentPlanList.size(); i++) {
			BigDecimal issueRent = rentPlanList.get(i).getRent();
			BigDecimal issueCorpus = issueRent.multiply(daoRate.pow(i - startIndex + 1));
			fenMu = fenMu.add(issueCorpus);
		}
		return corpusRemain.divide(fenMu, 20, BigDecimal.ROUND_HALF_UP);
	}


	/**
	 * 2个字符串数组的数字按index相加
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static List<BigDecimal> listAddS(List<BigDecimal> arr1, List<BigDecimal> arr2) {
		List<BigDecimal> result = new ArrayList<BigDecimal>();
		if (arr1 != null) {
			result.addAll(arr1);
			if (arr2 != null) {
				for (int i = 0; i < arr2.size(); i++) {
					if (i < result.size()) {
						result.set(i, result.get(i).add(arr2.get(i)));
					} else {
						result.add(arr2.get(i));
					}
				}
			}
		} else if (arr2 != null) {
			result.addAll(arr2);
		}
		return result;
	}

	/**
	 * 2个数组的数字按index相加
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static List<BigDecimal> listAdd(List<BigDecimal> arr1, List<BigDecimal> arr2) {
		List<BigDecimal> result = new ArrayList<BigDecimal>();
		if (arr1 != null) {
			result.addAll(arr1);
			if (arr2 != null) {
				for (int i = 0; i < arr2.size(); i++) {
					if (i < result.size()) {
						result.set(i, result.get(i).add(arr2.get(i)));
					} else {
						result.add(arr2.get(i));
					}
				}
			}
		} else if (arr2 != null) {
			result.addAll(arr2);
		}
		return result;
	}

	/**
	 * 2个字符串数组的数字按index相减
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static List<BigDecimal> listSubS(List<BigDecimal> array, List<BigDecimal> arrSub) {
		List<BigDecimal> result = new ArrayList<BigDecimal>();
		if (array != null) {
			result.addAll(array);
			if (arrSub != null) {
				for (int i = 0; i < arrSub.size(); i++) {
					if (i < result.size()) {
						result.set(i, result.get(i).subtract(arrSub.get(i)));
					} else {
						result.add(BigDecimal.ZERO.subtract(arrSub.get(i)));
					}
				}
			}
		}
		return result;
	}

	/**
	 * 2个数组的数字按index相减
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static List<BigDecimal> listSub(List<BigDecimal> array, List<BigDecimal> arrSub) {
		List<BigDecimal> result = new ArrayList<BigDecimal>();
		if (array != null) {
			result.addAll(array);
			if (arrSub != null) {
				for (int i = 0; i < arrSub.size(); i++) {
					if (i < result.size()) {
						result.set(i, result.get(i).subtract(arrSub.get(i)));
					} else {
						result.add(BigDecimal.ZERO.subtract(arrSub.get(i)));
					}
				}
			}
		}
		return result;
	}



	/**
	 * 调息开始的第一期新增的利息值
	 * 
	 * @param cb
	 * @param icb
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public static BigDecimal calFirstNewAddInterest(Condition cb, List<? extends RentPlan> rentPlanList, TransRateInfo icb) throws Exception {
		logger.info("第一期合同租金计划特别构建...");
		CalculateCondition condition = new CalculateCondition(cb);
		int startIndex = icb.getStartList() - 1;

		// 本期计息本金
		BigDecimal corpusOverage = condition.getLeaseMoney();// 默认第一期
		if (startIndex >= 1) {// 第二期以后的
			corpusOverage = rentPlanList.get(startIndex - 1).getBusinessCorpusOverage();
		}

		// 新利率适应的天数
		String currentIssueStartDate = icb.getStartDate();
		String currentIssueEndDate = rentPlanList.get(startIndex).getPlanDate();
		long days = DateTools.getDateDiff(currentIssueStartDate, currentIssueEndDate);

		// 计算利率变化后变化的利息
		BigDecimal lastYearRate = rentPlanList.get(startIndex).getYearRate();
		lastYearRate = (lastYearRate == null ? cb.getYearRate() : lastYearRate);
		BigDecimal oldDayRate = condition.getDayRate(lastYearRate).multiply(new BigDecimal(0.01));
		BigDecimal newDayRate = condition.getDayRate(icb.getNewYearRate()).divide(new BigDecimal(0.01));
		BigDecimal newAddInterest = corpusOverage.multiply(newDayRate.subtract(oldDayRate)).multiply(new BigDecimal(days));

		return newAddInterest;
	}
}
