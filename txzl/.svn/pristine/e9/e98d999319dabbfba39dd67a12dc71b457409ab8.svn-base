package com.reckon.util.tools;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-2-17
 * @desc ( 数字类型处理类)
 */
public class NumTools {
	static Logger logger = Logger.getLogger(NumTools.class);

	/**
	 * 判断是否为浮点数，包括double和float
	 * 
	 * @param str
	 *            传入的字符串
	 * @return 是浮点数返回true,否则返回false
	 */
	public static boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否为整数
	 * 
	 * @param str
	 *            传入的字符串
	 * @return 是整数返回true,否则返回false
	 */
	public static boolean isInteger(String str) {
		System.out.println(":::");
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static String getZeroStr(String value) {
		try {
			String temp_n = value;
			if (temp_n == null || temp_n.equals("") || temp_n.equals("null")) {
				temp_n = "0";
			}
			return temp_n;
		} catch (Exception e) {

		}
		return "0";
	}

	/**
	 * double四舍五入处理 scale--精度
	 * 
	 * @param dbl
	 * @param scale
	 * @return
	 */
	public double rnddouble(double dbl, int scale) {
		try {
			BigDecimal temp_bd = new BigDecimal(dbl);
			double newdbl = temp_bd.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
			return newdbl;
		} catch (Exception e) {

		}
		return 0;
	}

	/**
	 * 
	 * @param str
	 *            要处理的数值
	 * @param num
	 *            精确到小数点后位数
	 * @return
	 */
	public static String formatNumberDoubleScale(String str, int num) {
		try {
			String temp_num = str;
			if ((temp_num == null) || (temp_num.equals(""))) {
				temp_num = "";
			} else {
				temp_num = new BigDecimal(temp_num).setScale(num, BigDecimal.ROUND_HALF_UP).toString();

			}
			return temp_num;
		} catch (Exception e) {
		}
		return "0";
	}

	/**
	 * 
	 * (从第startList开始求和corpus的值)
	 * 
	 * @param corpus
	 * @param startList
	 *            开始求和起点,从1开始
	 * @return
	 */
	public static String getSumCorpusOverage(List<String> corpus, int startList) {
		BigDecimal bd = new BigDecimal("0");
		for (int i = startList - 1; i < corpus.size(); i++) {
			bd = bd.add(new BigDecimal(corpus.get(i).toString()));
		}
		return bd.toString();
	}

	 // 数字格式化 xx,xxx,xxx.xx
	public static String formatNumberDouble(double numstr) {
		try {
			String temp_num = String.valueOf(numstr);
			if ((temp_num == null) || (temp_num.equals(""))) {
				temp_num = "";
			} else {
				java.text.DecimalFormat ft = new java.text.DecimalFormat("#,##0.00");
				BigDecimal bd = new BigDecimal(temp_num);
				temp_num = ft.format(bd);
			}
			return temp_num;
		} catch (Exception e) {
		}
		return "";
	}

	public static String calculationStr(String num1, String num2, char type, int accuracy) {
		String rs = "";
		num1 = num1.equals("") ? "0" : num1;
		num2 = num2.equals("") ? "0" : num2;
		BigDecimal bigNum1 = new BigDecimal(num1);
		BigDecimal bigNum2 = new BigDecimal(num2);
		switch (type) {
		case '+':
			rs = bigNum1.add(bigNum2).setScale(accuracy, BigDecimal.ROUND_HALF_UP).toString();
			break;
		case '-':
			rs = bigNum1.subtract(bigNum2).setScale(accuracy, BigDecimal.ROUND_HALF_UP).toString();
			break;
		case '*':
			rs = bigNum1.multiply(bigNum2).setScale(accuracy, BigDecimal.ROUND_HALF_UP).toString();
			break;
		case '/':
			if (bigNum1.equals("0")) {
				rs = "0";
				break;
			}
			rs = bigNum1.divide(bigNum2, accuracy, BigDecimal.ROUND_HALF_UP).toString();
			break;
		default:
			break;
		}
		return rs;
	}

	public static char ADD = '+';
	public static char SUBTRACT = '-';
	public static char MULTIPLY = '*';
	public static char DIVIDE = '/';

	public static String getRemainCorpus(String cleanLeaseMoney,List<String> corpus, int startList) {
		BigDecimal bd = new BigDecimal(cleanLeaseMoney);
		for (int i = 0; i < startList - 1; i++) {
			bd = bd.subtract(new BigDecimal(corpus.get(i).toString()));
		}
		return bd.toString();
	}
}
