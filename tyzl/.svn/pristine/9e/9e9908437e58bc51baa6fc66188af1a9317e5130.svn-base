package com.reckon.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.util.tools.DateTools;





/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-2-17
 * @desc ( 租金计划时间测算)
 */

public class PlanDateTools {

	private static Logger logger = Logger.getLogger(PlanDateTools.class);

	/**
	 * 得到租金计划日期
	 * 
	 * @Title: getPlanDtList
	 * @Description:
	 * @param
	 * @param incomeNumber租金计划期数
	 * @param
	 * @param type期初或期末期数
	 * @param
	 * @param delay延迟期数
	 * @param
	 * @param lease_interval租金间隔
	 *            （月数）
	 * @param
	 * @param plan_date起租日
	 * @param
	 * @return
	 * @return List租金计划日期List
	 * @throws
	 */
	public static List<String> getPlanDtList(String incomeNumber, String type, String delay, String lease_interval, String plan_date) {

		// 如果是期末则第一期租金日期=放款日期+间隔月数
		String start_date = getFirstDate(type, plan_date, lease_interval);

		String day = "";
		if (start_date.indexOf("-") > -1) {// 得到日
			day = plan_date.substring(plan_date.lastIndexOf("-") + 1, plan_date.length());
		}

		List<String> l_date = new ArrayList<String>();
		// 先进行延期的处理
		String delayDate = "";
		for (int i = 0; i < Integer.parseInt(delay); i++) {
			String addDate = DateTools.dateAdd("month", i * Integer.parseInt(lease_interval), start_date);
			delayDate = getNewDate(addDate, day);
		}

		if ("".equals(delayDate)) {
			delayDate = start_date;
		}

		int icount = 1;
		int isize = Integer.parseInt(incomeNumber);
		if (Integer.parseInt(delay) == 0) {
			icount = 0;
			isize = isize - 1;
		}
		// 添加正常的租金计划时间
		for (int i = icount; i <= isize; i++) {
			String addDate = DateTools.dateAdd("month", i * Integer.parseInt(lease_interval), delayDate);
			addDate = getNewDate(addDate, day);
			l_date.add(addDate);
		}

		return l_date;

	}

	/**
	 * 传一个日期，day判断他是不是在这个日期的有效范围之类，如果没有则返回这个月的最大天数的有效日期
	 * 
	 * @Title: getNewDate
	 * @Description:
	 * @param
	 * @param start_date
	 *            用来判断的日期
	 * @param
	 * @param day
	 * @param
	 * @return
	 * @return String
	 * @throws
	 */
	public static String getNewDate(String start_date, String day) {

		// 根据年月得到他的最后一天
		String year = start_date.substring(0, start_date.indexOf("-"));
		String month = start_date.substring(start_date.indexOf("-") + 1, start_date.lastIndexOf("-"));
		String lastDay = DateTools.getLastDayOfMonth(year, month);
		String u_day = "";

		u_day = day;
		if (Integer.parseInt(lastDay) <= Integer.parseInt(day)) {
			u_day = lastDay;
		}

		return year + "-" + month + "-" + u_day;
	}

	/**
	 * 根据期初期末类型，起租日，租金间隔（月数）来得到租金计划开始日期
	 * 
	 * @Title: getFirstDate
	 * @Description:
	 * @param
	 * @param type期初期末类型
	 * @param
	 * @param plan_date起租日
	 * @param
	 * @param lease_interval租金间隔
	 *            （月数）
	 * @param
	 * @return
	 * @return String 租金计划开始日期
	 * @throws
	 */
	public static String getFirstDate(String type, String plan_date, String lease_interval) {

		// 如果是期末则第一期租金日期=放款日期+间隔月数
		String start_date = plan_date;
		String day = "";
		if (start_date.indexOf("-") > -1) {// 得到日
			day = start_date.substring(start_date.lastIndexOf("-") + 1, start_date.length());
		}
		//期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		if (type.equals("0")) {
			start_date = getNewDate(start_date, day);
			//sea 2014-03-12   期末情况下(如果是期末则第一期租金日期=放款日期+间隔月数)这个需求暂时注释掉，有问题以后再把注释放开。 
			//start_date = DateTools.dateAdd("month", Integer.parseInt(lease_interval), start_date);
		}

		logger.debug("第一期时间:" + start_date);
		return start_date;
	}

}
