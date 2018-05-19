package com.reckon.util.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-2-17
 * @desc ( Date时间处理工具类)
 */
public class DateTools {

	public static SimpleDateFormat timeFormart = new SimpleDateFormat("HH:mm:ss");

	public static SimpleDateFormat dateFormart = new SimpleDateFormat("yyyy-MM-dd");

	public static SimpleDateFormat dateTimeFormart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String getSystemDate(){
		return formatToDate(new Date());
	}
	
	public static String getSystemTime(){
		return formatToTime(new Date());
	}
	
	public static String getSystemDateTime(){
		return formatToDateTime(new Date());
	}
	
	public static String formatToDate(Date date) {
		if(date != null){
			return dateFormart.format(date);
		}
		return null;
	}
	
	public static String formatToDateTime(Date date) {
		if(date != null){
			return dateTimeFormart.format(date);
		}
		return null;
	}
	
	public static String formatToTime(Date date) {
		if(date != null){
			return timeFormart.format(date);
		}
		return null;
	}
	
	public static Date parseToDate(String date) {
		if (date != null) {
			try {
				if (date.length() == 10) {
					return dateFormart.parse(date);
				} else if (date.length() == 19) {
					return dateTimeFormart.parse(date);
				}
			} catch (ParseException e) {
				
			}
		}
		return null;
	}

	/**
	 * DB时间字符串取出处理
	 * 
	 * @param datestr
	 * @return
	 */
	public static String getDBDateStr(String datestr) {
		try {
			String temp_date = datestr;
			if ((temp_date == null) || (temp_date.equals("")) || (temp_date.indexOf("1900") >= 0)) {
				temp_date = "";
			} else {
				temp_date = temp_date.substring(0, 10);
			}
			return temp_date;
		} catch (Exception e) {

		}
		return "";
	}

	/**
	 * 返回系统时间 0--返回时间字符串 1--返回sql时间字符串
	 * 
	 * @param rtype
	 * @return
	 */
	public static String getSystemDate(int rtype) {
		try {
			Calendar cal = Calendar.getInstance();
			String module = "yyyy-MM-dd";
			if (rtype == 2) {
				module = "yyyyMMdd";
			}
			SimpleDateFormat formatter1 = new SimpleDateFormat(module);
			String fld_date = formatter1.format(cal.getTime());
			if (rtype == 0)
				return fld_date;
			else if (rtype == 1)
				return "'" + fld_date + "'"; // sql server
			// return "to_date("+fld_date+",'yyyy-mm-dd')"; //oracle
			else
				return fld_date;
		} catch (Exception e) {

		}
		return "null";
	}

	/**
	 * 在原有的日期上添加值
	 * 
	 * @param date
	 *            所要添加的日期
	 * @param leng
	 *            所要添加数
	 * @param type
	 *            添加的类型 年，月，日
	 * @return
	 */
	public static Date getDateAdd(Date date, int leng, String type) {
		Date addDate = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (type.equals("yy")) {
			cal.add(Calendar.YEAR, leng);
		} else if (type.equals("mm")) {
			cal.add(Calendar.MONTH, leng);
		} else if (type.equals("we")) {
			cal.add(Calendar.WEEK_OF_YEAR, leng);
		} else if (type.equals("dd")) {
			cal.add(Calendar.DAY_OF_YEAR, leng);
		} else if (type.equals("hh")) {
			cal.add(Calendar.HOUR_OF_DAY, leng);
		} else if (type.equals("mi")) {
			cal.add(Calendar.MINUTE, leng);
		} else if (type.equals("ss")) {

		}
		addDate = cal.getTime();
		return addDate;
	}

	/**
	 * 在原有的日期上添加值
	 * 
	 * @param date
	 *            所要添加的日期(字符串类型的日期)
	 * @param leng
	 *            所要添加数
	 * @param type
	 *            添加的类型 年，月，日
	 * @return
	 */
	public static String getDateAdd(String strDate, int leng, String type) {
		Date addDate = null;
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(strDate);
		} catch (Exception e) {
			System.out.println(e);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (type.equals("yy")) {
			cal.add(Calendar.YEAR, leng);
		} else if (type.equals("mm")) {
			cal.add(Calendar.MONTH, leng);
		} else if (type.equals("we")) {
			cal.add(Calendar.WEEK_OF_YEAR, leng);
		} else if (type.equals("dd")) {
			cal.add(Calendar.DAY_OF_YEAR, leng);
		} else if (type.equals("hh")) {
			cal.add(Calendar.HOUR_OF_DAY, leng);
		} else if (type.equals("mi")) {
			cal.add(Calendar.MINUTE, leng);
		} else if (type.equals("ss")) {

		}
		addDate = cal.getTime();
		return sdf.format(addDate);
	}

	/**
	 * 得到两时间的间隔月数
	 * 
	 * @param bdate
	 * @param edate
	 * @return
	 */

	public static int getDateDiffMonth(String bdate, String edate) {
		try {
			String[] barray = bdate.split("-");
			String[] earray = edate.split("-");
			return (Integer.parseInt(earray[0]) - Integer.parseInt(barray[0])) * 12 + (Integer.parseInt(earray[1]) - Integer.parseInt(barray[1]));
		} catch (Exception e) {

		}
		return 0;
	}

	/**
	 * 返回两时间间隔天数 bdate--开始时间字符串 edate--结束时间字符串
	 * 
	 * @param bdate
	 * @param edate
	 * @return
	 */
	public static long getDateDiff(Date bdate, Date edate) {
		try {
			long datediff = (bdate.getTime() - edate.getTime()) / (1000 * 60 * 60 * 24);
			return datediff;
		} catch (Exception e) {

		}
		return 0;
	}

	/**
	 * 返回两时间间隔天数 bdate--开始时间字符串 edate--结束时间字符串
	 * 
	 * @param strbdate
	 * @param stredate
	 * @return
	 */
	public static long getDateDiff(String strbdate, String stredate) {
		Date bdate = null;
		Date edate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			bdate = sdf.parse(strbdate);
			edate = sdf.parse(stredate);
			long datediff = (bdate.getTime() - edate.getTime()) / (1000 * 60 * 60 * 24);
			return datediff;
		} catch (Exception e) {

		}
		return 0;
	}

	/**
	 * 获得一月中的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(String year, String month) {
		Calendar cal = Calendar.getInstance();
		// 年
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		// 月，因为Calendar里的月是从0开始，所以要-1
		cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
		// 日，设为一号
		cal.set(Calendar.DATE, 1);
		// 月份加一，得到下个月的一号
		cal.add(Calendar.MONTH, 1);
		// 下一个月减一为本月最后一天
		cal.add(Calendar.DATE, -1);
		return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));// 获得月末是几号

	}

	/**
	 * 两日期间隔数
	 * 
	 * @param type
	 *            年，月，日
	 * @param b_date
	 * @param e_date
	 * @return
	 */
	public static long dateDiff(String type, String b_date, String e_date) {
		long r_turn = -1;
		String b_year = b_date.substring(0, 4);
		String e_year = e_date.substring(0, 4);
		String b_month = b_date.substring(5, 7);
		String e_month = e_date.substring(5, 7);
		if (type.equals("year")) {
			r_turn = Long.parseLong(e_year) - Long.parseLong(b_year);
		} else if (type.equals("month")) {
			r_turn = Long.parseLong(e_year) * 12 + Long.parseLong(e_month) - Long.parseLong(b_year) * 12 - Long.parseLong(b_month);
		} else if (type.equals("day")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date begin_date = sdf.parse(b_date);
				Date end_date = sdf.parse(e_date);
				r_turn = (end_date.getTime() - begin_date.getTime()) / 24 / 60 / 60 / 1000;
			} catch (ParseException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
		}

		return r_turn;
	}

	/**
	 * 在某日期上添加某类型的日期数
	 * 
	 * @param type
	 * @param leng
	 * @param strDate
	 * @return
	 */
	public static String dateAdd(String type, int leng, String strDate) {
		Date addDate = null;
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = sdf.parse(strDate);
		} catch (Exception e) {
			System.out.println(e);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (type.equals("year")) {
			cal.add(Calendar.YEAR, leng);
		} else if (type.equals("month")) {
			cal.add(Calendar.MONTH, leng);
		} else if (type.equals("we")) {
			cal.add(Calendar.WEEK_OF_YEAR, leng);
		} else if (type.equals("day")) {
			cal.add(Calendar.DAY_OF_YEAR, leng);
		} else if (type.equals("hh")) {
			cal.add(Calendar.HOUR_OF_DAY, leng);
		} else if (type.equals("mi")) {
			cal.add(Calendar.MINUTE, leng);
		} else if (type.equals("ss")) {

		}
		addDate = cal.getTime();
		return sdf.format(addDate);
	}

	/**
	 * 返回一个日期的下一年的第一天
	 * 
	 * @Title: getYearFirstDate
	 * @Description:
	 * @param
	 * @param txrq
	 * @param
	 * @return
	 * @return String
	 * @throws
	 */
	public static String getYearFirstDay(String chargeDate) {

		String rdate = getDateAdd(chargeDate, 1, "yy");
		rdate = rdate.substring(0, 4) + "-01-01";
		return rdate;

	}

	/**
	 * 得到下个月的第一天
	 * 
	 * @return
	 */
	public static String getMonthFirstDay(String date) {
		String rdate = getDateAdd(date, 1, "mm");
		rdate = rdate.substring(0, rdate.length() - 2) + "01";
		return rdate;
	}
	/**
	 * 返回下一天
	 * @param date
	 * @return
	 */
	public static String getNextDay(String date) {
		String rdate = getDateAdd(date, 1, "dd");
		return rdate;
	}

	/**
	 * 
	 * ( 得到一个日期的年份)
	 * 
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static String getDateYear(String strDate) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.setTime(sdf.parse(strDate));
		return String.valueOf(date.get(Calendar.YEAR));

	}

	/**
	 * 
	 * ( 得到一个日期的月份)
	 * 
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static String getDateMonth(String strDate) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.setTime(sdf.parse(strDate));
		return String.valueOf(date.get(Calendar.MONTH) + 1);

	}

	/**
	 * 
	 * ( 得到一个日期的天数)
	 * 
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static String getDateDay(String strDate) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.setTime(sdf.parse(strDate));
		return String.valueOf(date.get(Calendar.DAY_OF_MONTH));

	}

	// public static void main(String[] args) throws ParseException {
	//
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	//
	// Calendar date = Calendar.getInstance();
	// date.setTime(sdf.parse("2004-06-07 "));
	//
	// int iYear = date.get(Calendar.YEAR);
	// int iMonth = date.get(Calendar.MONTH) + 1;
	// int iDay = date.get(Calendar.DAY_OF_MONTH);
	// System.out.println(String.valueOf(iYear) + "=="
	// + String.valueOf(iMonth) + "==" + String.valueOf(iDay));
	//
	// System.out.println(getDateYear("2004-06-07")+getDateMonth("2004-06-07")+getDateDay("2004-06-07"));
	//
	// }

	/**
	 * 根据日期，如果是空则返回当前日期值
	 */
	public static String getNullDate(String date) {

		String tempDate = date;
		if (date == null || "".equals(date)) {
			tempDate = getSystemDate(0);
		}

		return tempDate;

	}

}
