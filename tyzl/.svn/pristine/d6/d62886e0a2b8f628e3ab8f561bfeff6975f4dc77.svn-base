package com.reckon.renttranrate.service.impl;

import org.apache.log4j.Logger;

import com.reckon.util.tools.DateTools;



/**
 * @author MHY QQ:648020894
 */
public class ContrRentPlanServiceImpl {

	static Logger logger = Logger.getLogger(ContrRentPlanServiceImpl.class);

	/**
	 * 
	 *  (根据交易结构的宽限期与调息开始期项得到新的测算宽限期)
	 * 
	 * @param grace
	 * @param startList
	 * @return
	 */
	public static String getGraceByAdjust(String grace, String startList) {

		String fgrace = "0";
		// 为了要引用正常 的租金测算所以先要重新计算他的宽限期
		if (Integer.parseInt(grace) >= Integer.parseInt(startList)) {// 当调息是从宽限期开始时,得到实际的宽限期天数
			fgrace = String.valueOf(Integer.parseInt(grace) - Integer.parseInt(startList) + 1);
		} else {// 如果不是从宽限期开始调时
			fgrace = "0";
		}
		logger.info("新宽限期为:.." + fgrace);

		return fgrace;
	}

	/**
	 * 
	 *  (求出新的pmt测算期数)
	 * 
	 * @param incomeNumber
	 * @param startList
	 * @param grace
	 * @return
	 */
	public static int getIncomeNumByAdjust(int incomeNumber, int startList, int grace) {
		int totalNumber = 0;
		if (grace >= startList) {// 当从宽限期开始时
			totalNumber = incomeNumber;// 正常测算时的租金期数
		} else if (grace < startList) {// 从正常的测算期项开始时
			totalNumber = incomeNumber - startList + 1;
		}
		logger.info("新总期数为:.." + totalNumber);
		return totalNumber;
	}

	/**
	 * 
	 *  (调息时得到新的pmt测算的期初期末类型)
	 * 
	 * @param startList
	 * @param grace
	 * @param oldType
	 * @return
	 */
	public static String getPeroidType(int startList, int grace, String oldType) {
		// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		String peroidType = oldType;
		if (grace >= startList) {// 当从宽限期开始时
			peroidType = oldType;
		} else if (grace + 1 == startList) {// 当是第一期时
			peroidType = oldType;
		} else {// 当从正常的租金计划开始且不是第一期时
			peroidType = "0";// 变为期末付,利息测算时需注意的
		}
		logger.info("新起租类型为:.." + peroidType);
		return peroidType;
	}

	/**
	 * 
	 *  (计算重新测算的起租时间)
	 * 
	 * @param startList
	 *            开始调息期项
	 * @param peroidType
	 *            期初期末
	 * @param startDate
	 *            起租时间
	 * @param currDate
	 *            当前期时间
	 * @param lease_interval
	 *            租金间隔
	 * @return
	 */
	public static String getStartDate(int startList, String peroidType, String startDate, String currDate, String leaseInterval) {

		String newStartDate = startDate;
		if (startList == 1) {
			if (!currDate.equals("")) {
				newStartDate = currDate;
			} else {
				newStartDate = startDate;
			}
		} else {
			if ("1".equals(peroidType)) {
				newStartDate = currDate;
			} else {
				newStartDate = DateTools.dateAdd("month", -Integer.parseInt(leaseInterval), currDate);
			}
		}
		logger.debug("新起租日期为:.." + newStartDate);
		return newStartDate;
	}
}
