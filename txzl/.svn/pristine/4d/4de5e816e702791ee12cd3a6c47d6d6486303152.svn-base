package com.reckon.calculation.utils;

import java.math.BigDecimal;

import com.reckon.calculation.vo.CalculationCondition;


public class PMTCalculateUtil {
	
	/**
	 * 用于计算每一期的租金
	 * 
	 * pv:本金，fv:期末残值，n:还款期数nper，i:期利率rate
	 * 
	 * 设 x = 1 / i ;
	 * 
	 * 期初：
	 * Rx^0 + Rx^1 + Rx^2 + ............ + Rx^n-1        = B - Fx^n
	 *        Rx^1 + Rx^2 + ............ + Rx^n-1 + Rx^n = Bx - Fx^n+1
	 * 期末：
	 * Rx^1 + Rx^2 + Rx^3 + ............ + Rx^n-1 + Rx^n = B - Fx^n
	 * 
	 * 期初：
	 * 
	 *                  1
	 *    pv - fv × ( ----- ) ^ n
	 *                1 + i                   1
	 * ---------------------------- × i × ( ----- )
	 *            1                         1 + i
	 *    1 - ( ----- ) ^ n
	 *          1 + i
	 * 
	 * 
	 * 期末：
	 * 
	 *                  1
	 *    pv - fv × ( ----- ) ^ n
	 *                1 + i
	 * ----------------------------- × i
	 *            1
	 *    1 - ( ----- ) ^ n
	 *          1 + i
	 * 
	 * 
	 * @param rate
	 *            利率(注意同期数周期一致，且要求已经包括百分号的数值！如0.05)
	 * @param nper
	 *            总共的期数
	 * @param pv
	 *            租赁本金
	 * @param fv
	 *            期末余值
	 * @param type
	 *            数字 0 或 非0，分别用以指定各期的付款时间是在期初还是期末
	 * @return
	 */
	public static BigDecimal getPMT(BigDecimal rate, int nper, BigDecimal pv, BigDecimal fv, int type) {
		BigDecimal result = BigDecimal.ZERO;
		fv = (fv == null ? BigDecimal.ZERO : fv);
		pv = (pv == null ? BigDecimal.ZERO : pv);
		rate = (rate == null ? BigDecimal.ZERO : rate);
		nper = (nper < 0 ? 0 : nper);
		if(rate.compareTo(BigDecimal.ZERO) != 0){
			BigDecimal one = BigDecimal.ONE;
			BigDecimal reciprocal = one.divide(rate.add(one), 20, BigDecimal.ROUND_HALF_UP);
			result = pv.subtract(fv.multiply(reciprocal.pow(nper))).multiply(rate);
			result = result.divide(BigDecimal.ONE.subtract(reciprocal.pow(nper)), 20, BigDecimal.ROUND_HALF_UP);
			result = result.multiply(type == 0 ? reciprocal : one);
		} else {
			if(nper != 0){
				result = pv.subtract(fv).divide(new BigDecimal(nper), 20, BigDecimal.ROUND_HALF_UP);
			} else {
				result = pv.subtract(fv);
			}
		}
		return result.setScale(Scale.RENT_SCALE, BigDecimal.ROUND_HALF_UP);
	}
	
	public static void main(String [] args) {
		BigDecimal rate = new BigDecimal(0.01);
		int nper = 12;
		BigDecimal pv = new BigDecimal(1000000);
		BigDecimal fv = new BigDecimal(300000);
		int type = 1;
		BigDecimal rent = getPMT(rate, nper, pv, fv, type);
		System.out.println(rent.toString());
	}


	public static BigDecimal getPMT(CalculationCondition condition) {

		BigDecimal issueRate = condition.getIssueRate();
		int nper = condition.getIncomeTimes();
		BigDecimal pv = condition.getLeaseAmt();
		BigDecimal fv = condition.getEquipEndValue();
		int type = condition.getPeriodType();
		
		return getPMT(issueRate, nper, pv, fv, type);
	}
}
