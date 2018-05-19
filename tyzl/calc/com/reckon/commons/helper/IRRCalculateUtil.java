package com.reckon.commons.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.reckon.commons.base.CashDetail;
import com.reckon.commons.base.Condition;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.util.DateTools;

/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-2-17
 * @desc (-list 现金流处理)
 */
public class IRRCalculateUtil {

	private static final BigDecimal zero = BigDecimal.ZERO;
	private static final BigDecimal one = BigDecimal.ONE;
	private static final BigDecimal second = new BigDecimal(2);

	/**
	 * 计算年IRR
	 * 
	 * @param rentPlanList 租金计划
	 * @param condition 商务报价
	 * @return
	 */
	public static BigDecimal getYearIRR(Condition condition, List<RentPlan> rentPlanList) {
		BigDecimal leaseMoney = condition.getLeaseMoney();
		int issueNumber = 12 / condition.getIncomeNumberYear().intValue();
		List<BigDecimal> inflowPour = new ArrayList<BigDecimal>();
		inflowPour.add(leaseMoney.multiply(new BigDecimal(-1)));
		for(RentPlan rp : rentPlanList){
			inflowPour.add(rp.getRent());
		}
		return getYearIRR(inflowPour, issueNumber);
	}
	
	/**
	 * 计算年IRR
	 * 
	 * @param rentPlanList 租金计划
	 * @param condition 商务报价
	 * @return
	 */
	public static BigDecimal getYearIRR2(Condition condition, List<CashDetail> cashFlow) {
		int issueNumber = 12 / condition.getIncomeNumberYear().intValue();
		List<BigDecimal> inflowPour = new ArrayList<BigDecimal>();
		for(CashDetail rp : cashFlow){
			inflowPour.add(rp.getNetFlow());
		}
		return getYearIRR(inflowPour, issueNumber);
	}

	/**
	 * 计算财务每期的收益率，返回的是年利率。 <br>
	 * 中心思想：二分查找irr属于(0,1) 然后不断尝试IRR新值，直到每一期租金加利息的总和等于本金<br>
	 * 
	 * @param inflowPour
	 *            所有现金流入流出
	 * @param issueNumber
	 *            每期几个月1,2,3,4,6,12
	 * @return irr
	 */
	public static BigDecimal getYearIRR(List<BigDecimal> inflowPour, int issueNumber) {
		return getIRR(inflowPour).multiply(new BigDecimal(12 / issueNumber));
	}

	/**
	 * 期IRR计算
	 * 
	 * @param inflowPour
	 * @return
	 */
	public static BigDecimal getIRR2(List<String> inflowPour) {
		List<BigDecimal> inflowPourNew = new ArrayList<BigDecimal>();
		for (String val : inflowPour) {
			inflowPourNew.add(new BigDecimal(val));
		}
		return getIRR(inflowPourNew);
	}

	/**
	 * 期IRR计算
	 * 
	 * @param inflowPour
	 * @return
	 */
	public static BigDecimal getIRR(List<BigDecimal> inflowPour) {
		BigDecimal irr = zero;
		BigDecimal tmp1 = zero;
		BigDecimal tmp2 = one;
		try {
			int j = 0;
			BigDecimal remain = one;
			while (remain.abs().doubleValue() > 0.0000000001 && j++ < 200) {
				remain = inflowPour.get(0);// 这个是负值，表示本金欠值
				for (int i = 1; i < inflowPour.size(); i++) {
					// 本金=租金/(1+irr)i次方，本金一直加
					remain = remain.add(inflowPour.get(i).divide(irr.add(one).pow(i), 20, BigDecimal.ROUND_HALF_UP));
				}
				if (remain.doubleValue() > 0) {// 剩余租金 >0，irr小了
					tmp1 = irr;
					irr = irr.add(tmp2).divide(second, 20, BigDecimal.ROUND_HALF_UP);
				} else if (remain.doubleValue() < 0) {// 剩余租金<0，irr大了
					tmp2 = irr;
					irr = irr.add(tmp1).divide(second, 20, BigDecimal.ROUND_HALF_UP);
				} else {
					break;// 剩余租金=0，irr正好，结束循环
				}
			}
			return irr;
		} catch (Exception e) {
			return zero;
		}
	}

	/**
	 * XIRR计算公式 参数inflowPour是对于的现金流,参数dateList是对于的现金流时间 这个需要一一对于. <br>
	 * 这个算法对于的是EXECL中的XIRR算法.可以用于精确到日的 日IRR计算<br>
	 * 
	 * @param inflowPour
	 *            现金流List
	 * @param dateList
	 *            对应的日期List
	 * @return
	 */
	public static String getXIRR(List<BigDecimal> inflowPour, List<Date> dateList) {
		BigDecimal irr = new BigDecimal("0.5");
		BigDecimal tmp = one;
		BigDecimal tmp1 = zero;
		BigDecimal tmp2 = one;
		int j = 0;
		try {
			while (tmp.abs().doubleValue() > 0.0000000001 && j < 200) {
				tmp = inflowPour.get(0);
				Date date0 = dateList.get(0);
				for (int i = 1; i < inflowPour.size(); i++) {
					Long quot = DateTools.getDateDiff(dateList.get(i), date0);
					BigDecimal rate2 = new BigDecimal(quot / 365.0);
					tmp = tmp.add(inflowPour.get(i).divide(new BigDecimal(Math.pow(irr.add(one).doubleValue(), rate2.doubleValue())), 20, BigDecimal.ROUND_HALF_UP));
				}
				if (tmp.doubleValue() > 0) {
					tmp1 = irr;
					irr = irr.add(tmp2).divide(second, 20, BigDecimal.ROUND_HALF_UP);
				}
				if (tmp.doubleValue() < 0) {
					tmp2 = irr;
					irr = irr.add(tmp1).divide(second, 20, BigDecimal.ROUND_HALF_UP);
				}
				j++;
			}
			return irr.toString();
		} catch (Exception e) {
			return zero.toString();
		}
	}
}
