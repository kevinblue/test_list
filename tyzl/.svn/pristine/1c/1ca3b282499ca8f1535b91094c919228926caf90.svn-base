package com.reckon.util;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import com.reckon.calculation.utils.Scale;


/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-2-17
 * @desc  ( 利率计算类)
 */
public class RateTools {

	static Logger logger = Logger.getLogger(RateTools.class);

	/**
	 * 计算期利率
	 * 
	 * @param calcRate所要计算的年利率或irr
	 * @param lease_interval 每年几期
	 * @param rateReCal 是否复算，越秀的需求  复算利率=(年利率*365/360/12 + 1)lease_interval次方 - 1
	 * @return String
	 */
	public static String getPreRate(String calcRate, String lease_interval) {
		BigDecimal rateDecimal = new BigDecimal(calcRate);
		//2014-07-10 年利率先做四舍五入保留六位小数处理，再算期利率
		rateDecimal = rateDecimal.setScale(6,BigDecimal.ROUND_HALF_UP);
		logger.debug("保留六位的年利率:"+rateDecimal);
		
		try {
			rateDecimal = rateDecimal.divide(new BigDecimal("100"), 8, BigDecimal.ROUND_HALF_EVEN);//利率传过来是分子，所以要先除以100
//			欧力士木有复算，不用放开!还有该方法与CalculationConditionImpl下的getIssueRate方法重复了！！！	
//			if(Integer.parseInt(rateReCal) == 0){//是否复算,0:复算 1:不复算
//				rateDecimal = rateDecimal.multiply(new BigDecimal(365));
//				rateDecimal = rateDecimal.divide(new BigDecimal(360), 20, BigDecimal.ROUND_HALF_UP);
//				rateDecimal = rateDecimal.divide(new BigDecimal(12), 20, BigDecimal.ROUND_HALF_UP);
//				rateDecimal = rateDecimal.add(new BigDecimal(1));
//				rateDecimal = rateDecimal.pow(12 / Integer.parseInt(lease_interval));
//				rateDecimal = rateDecimal.subtract(new BigDecimal(1));
//			} 
//			else {
			rateDecimal = rateDecimal.multiply(new BigDecimal(String.valueOf(12 / Integer.parseInt(lease_interval))));
				rateDecimal = rateDecimal.divide(new BigDecimal("12"), 8, BigDecimal.ROUND_HALF_UP);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rateDecimal.toString();
	}
	
	
	/**
	 * 根据年利率计算期利率，年利率需要在之前就做*365/360计算
	 * 
	 * @param yearRate 经过*365/360处理后的利率
	 * @param lease_interval 每年几期
	 * @param rateReCal 是否复算，越秀的需求  复算利率=(年利率*365/360/12 + 1)lease_interval次方 - 1
	 * @return String
	 */
	public static String getPreRateAfterYearRateReset(String yearRate, String lease_interval, String rateReCal) {
		BigDecimal rateDecimal = new BigDecimal(yearRate);
		try {
			rateDecimal = rateDecimal.divide(new BigDecimal("100"), 20, BigDecimal.ROUND_HALF_UP);//利率传过来是分子，所以要先除以100
			if(Integer.parseInt(rateReCal) == 0){//是否复算,0:复算 1:不复算
				rateDecimal = rateDecimal.divide(new BigDecimal(12), 20, BigDecimal.ROUND_HALF_UP);
				rateDecimal = rateDecimal.add(new BigDecimal(1));
				rateDecimal = rateDecimal.pow(12 / Integer.parseInt(lease_interval));
				rateDecimal = rateDecimal.subtract(new BigDecimal(1));
			} else {
				rateDecimal = rateDecimal.divide(new BigDecimal("12"), 20, BigDecimal.ROUND_HALF_UP);
				rateDecimal = rateDecimal.multiply(new BigDecimal(String.valueOf(12 / Integer.parseInt(lease_interval))));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rateDecimal.toString();
	}

	/**
	 * 计算每月利率值
	 * 
	 * @Title: getPreRate
	 * @Description:
	 * @param
	 * @param calcRate所要计算的年利率或irr
	 * @param
	 * @param lease_interval
	 *            租金间隔
	 * @param
	 * @return
	 * @return String
	 * @throws
	 */
	public static String getPreMonthRate(String calcRate) {
		BigDecimal rateDecimal = new BigDecimal(calcRate);

		try {
			rateDecimal = rateDecimal.divide(new BigDecimal("100"), 20, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal("12"), 20, BigDecimal.ROUND_HALF_UP);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rateDecimal.toString();
	}
	
	
	/**
	 * 计算每天的利率
	 * yearRate : 年利率乘以100后的数值，%号前边的值。
	 */
	public static BigDecimal getPreDayRate(String yearRateStr) {
		BigDecimal yearRate = new BigDecimal(yearRateStr);
		BigDecimal dayRate = yearRate.divide(new BigDecimal(36000), Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN);
		logger.info("租金测算[RentCalculateUtil]日利率：" + dayRate.toString());
		return dayRate;
	}
}
