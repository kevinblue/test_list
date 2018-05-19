package com.reckon.util;

import com.reckon.bean.ConditionBean;


/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-10
 * @desc  ( 数据库字典，程序中翻译过程)
 */
public class DictTools {

	/**
	 * 
	 *  (将交易结构表的一些数据库字典信息翻译成可以程序运行的)
	 * 
	 * @param cb
	 * @return
	 */
	public static ConditionBean getReversDict(ConditionBean cb) {

		// 付租类型
		if (null != cb.getIncomeNumberYear() && "income_1".equals(cb.getIncomeNumberYear())) {
			cb.setIncomeNumberYear("12");
		} else if (null != cb.getIncomeNumberYear() && "income_3".equals(cb.getIncomeNumberYear())) {
			cb.setIncomeNumberYear("4");
		} else if (null != cb.getIncomeNumberYear() && "income_6".equals(cb.getIncomeNumberYear())) {
			cb.setIncomeNumberYear("2");
		} else if (null != cb.getIncomeNumberYear() && "income_12".equals(cb.getIncomeNumberYear())) {
			cb.setIncomeNumberYear("1");
		} else if (null != cb.getIncomeNumberYear() && "income_2".equals(cb.getIncomeNumberYear())) {
			cb.setIncomeNumberYear("6");
		}

		// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		//update t_dicts_datas set name_ = '期末' where id_ = 'period_type_0'
		//update t_dicts_datas set name_ = '期初' where id_ = 'period_type_1'
		if (null != cb.getPeriodType() && "period_type_1".equals(cb.getPeriodType())) {// 期初
			cb.setPeriodType("1");
		} else if (null != cb.getPeriodType() && "period_type_0".equals(cb.getPeriodType())) {// 期末时
			cb.setPeriodType("0");
		}
		return cb;
	}

	/**
	 * 
	 *  (  持久化交易结构调用反转)
	 * 
	 * @param cb
	 * @return
	 */
	public static ConditionBean getPersiDict(ConditionBean cb) {

		// 付租类型     年还租次数 cb.getIncomeNumberYear()
		/**
		 * ConvertMapToBeanServiceImpl中的convertContionBean方法
		 * income_1,income_2,income_3,income_6,income_12(直接截取)
		 * conditionBean.setIncome_number_year(income_number_year.substring(7,income_number_year.length()));
		 * income_1	月  付    年还租次数 12
		 * income_3	季  付      年还租次数 4
		 * income_6	半年付   年还租次数 2
		 * income_12 年  付    年还租次数 1
		 * income_2	双月付    年还租次数 6
		 */
		if (null != cb.getIncomeNumberYear() && "12".equals(cb.getIncomeNumberYear())) {
			cb.setIncomeNumberYear("income_1");
		} else if (null != cb.getIncomeNumberYear() && "4".equals(cb.getIncomeNumberYear())) {
			cb.setIncomeNumberYear("income_3");
		} else if (null != cb.getIncomeNumberYear() && "2".equals(cb.getIncomeNumberYear())) {
			cb.setIncomeNumberYear("income_6");
		} else if (null != cb.getIncomeNumberYear() && "1".equals(cb.getIncomeNumberYear())) {
			cb.setIncomeNumberYear("income_12");

		} else if (null != cb.getIncomeNumberYear() && "6".equals(cb.getIncomeNumberYear())) {
			cb.setIncomeNumberYear("income_2");
		}

		// 起租类型 注意: "期初|期末","1|0"
		if (null != cb.getPeriodType() && "1".equals(cb.getPeriodType())) {// 期初
			cb.setPeriodType("period_type_1");
		} 
		if (null != cb.getPeriodType() && "0".equals(cb.getPeriodType())) {// 期末时
			cb.setPeriodType("period_type_0");
		}

		return cb;
	}
}
