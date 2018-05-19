package com.reckon.commons.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTools {

	public static SimpleDateFormat ym = new SimpleDateFormat("yyyy-MM");

	public static SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");

	public static SimpleDateFormat hms = new SimpleDateFormat("HH:mm:ss");

	public static SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @return yyyy-MM-dd
	 */
	public static String getSystemDate() {
		return formatToDate(new Date());
	}

	/**
	 * @return HH:mm:ss
	 */
	public static String getSystemTime() {
		return formatToTime(new Date());
	}

	/**
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getSystemDateTime() {
		return formatToDateTime(new Date());
	}

	/**
	 * @param date
	 * @return yyyy-MM-dd
	 */
	public static String formatToDate(Date date) {
		if (date != null) {
			return ymd.format(date);
		}
		return null;
	}

	/**
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String formatToDateTime(Date date) {
		if (date != null) {
			return ymdhms.format(date);
		}
		return null;
	}

	/**
	 * @param date
	 * @return HH:mm:ss
	 */
	public static String formatToTime(Date date) {
		if (date != null) {
			return hms.format(date);
		}
		return null;
	}

	/**
	 * 判断是不是常规的日期类型
	 * @param date
	 * @return
	 */
	public static boolean isStringDate(String date) {
		if (date != null) {
			try {
				date = date.replace("/", "-");
				if(date.length() >= 6 && date.length() <= 10){
					ymd.parse(date);
					return true;
				}
			} catch (Exception e) {}
			
			try {
				date = date.replace("/", "-");
				if(date.length() >= 12 && date.length() <= 19){
					ymdhms.parse(date);
					return true;
				}
			} catch (Exception e) {}
		}
		return false;
	}
	
	
	/**
	 * 根据字符串长度自动转换成日期
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date parseToDate(String date) {
		if (date != null) {
			try {
				date = date.trim();
				if (date.length() == 6) {
					return new SimpleDateFormat("yyyyMM").parse(date);
				} else if (date.length() == 7) {
					return ym.parse(date);
				} else if (date.length() == 8) {
					return new SimpleDateFormat("yyyyMMdd").parse(date);
				} else if (date.length() == 10) {
					return ymd.parse(date);
				} else if (date.length() == 14) {
					return new SimpleDateFormat("yyyyMMddHHmmss").parse(date);
				} else if (date.length() == 15) {
					return new SimpleDateFormat("yyyyMMdd HHmmss").parse(date);
				} else if (date.length() == 19) {
					return ymdhms.parse(date);
				}
			} catch (ParseException e) {

			}
		}
		return null;
	}

	/**
	 * 
	 * 得到一个日期的年份
	 * 
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static String getDateYear(String strDate) throws ParseException {
		Calendar date = Calendar.getInstance();
		date.setTime(ymd.parse(strDate));
		return String.valueOf(date.get(Calendar.YEAR));
	}

	/**
	 * 
	 * 得到一个日期的月份
	 * 
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static String getDateMonth(String strDate) throws ParseException {
		Calendar date = Calendar.getInstance();
		date.setTime(ymd.parse(strDate));
		return String.valueOf(date.get(Calendar.MONTH) + 1);
	}

	/**
	 * 得到一个日期的天数
	 * 
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static String getDateDay(String strDate) throws ParseException {
		Calendar date = Calendar.getInstance();
		date.setTime(ymd.parse(strDate));
		return String.valueOf(date.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 计算两个日期之间的差的月数。 </br> 月付时：1月31号第一期，</br> 则第二期是2月28号或者29号。 </br>
	 * 这个需要判断，不能直接加一个月，否则会变成3月2号的</br>
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	public static int getDiffMonth(Date beginDate, Date endDate) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginDate);
		int beginYear = calendar.get(Calendar.YEAR);
		int beginMonth = calendar.get(Calendar.MONTH);
		calendar.setTime(endDate);
		int endYear = calendar.get(Calendar.YEAR);
		int endMonth = calendar.get(Calendar.MONTH);
		int difMonth = (endYear - beginYear) * 12 + (endMonth - beginMonth);
		return difMonth;
	}

	/**
	 * 返回两时间间隔天数
	 * 
	 * @param startDate
	 *            开始时间字符串 2013-03-05
	 * @param endDate
	 *            结束时间字符串 2013-03-07
	 * @return 2
	 */
	public static long getDateDiff(String startDate, String endDate) {
		try {
			Date sd = parseToDate(startDate);
			Date ed = parseToDate(endDate);
			return getDateDiff(sd, ed);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 返回两时间间隔天数
	 * 
	 * @param startDate
	 *            开始时间 2013-03-05 23:59:59
	 * @param endDate
	 *            结束时间 2013-03-07 00:00:01
	 * @return 2
	 */
	public static long getDateDiff(Date startDate, Date endDate) {
		try {
			long start = parseToDate(formatToDate(startDate)).getTime();
			long end = parseToDate(formatToDate(endDate)).getTime();
			return (end - start) / 86400000;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 获取一个日期所在的年份的第一天
	 * 
	 * @param date
	 *            2013-03-05
	 * @return 2013-01-01
	 */
	public static String getYearFirstDay(String date) {
		try {
			String year = date.substring(0, 4);
			return year + "-01-01";
		} catch (Exception e) {
			return date;
		}
	}

	/**
	 * 获取一个日期所在的月份的第一天
	 * 
	 * @param date
	 *            2013-03-05
	 * @return 2013-03-01
	 */
	public static String getMonthFirstDay(String date) {
		try {
			String year = ym.format(parseToDate(date));
			return year + "-01";
		} catch (Exception e) {
			return date;
		}
	}
}
