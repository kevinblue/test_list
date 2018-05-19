package com.reckon.rentcalc.web;

import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.bean.FundRentPlanIrr;
import com.reckon.bean.KnowingRentBean;
import com.reckon.bean.SpecialRulesBean;
import com.reckon.rentcalc.service.RentCalcService;
import com.reckon.rentcalc.service.impl.evencorpus.DeviEvenCorpusCaleServiceImpl;
import com.reckon.rentcalc.service.impl.evencorpus.EvenCorpusCaleServiceImpl;
import com.reckon.rentcalc.service.impl.eveninterest.DeviEvenInterestCalServiceImpl;
import com.reckon.rentcalc.service.impl.eveninterest.EvenInterestCalcServiceImpl;
import com.reckon.rentcalc.service.impl.evenrent.DeviRentCalServiceImpl;
import com.reckon.rentcalc.service.impl.evenrent.EvenRentCalcServiceImpl;
import com.reckon.rentcalc.service.impl.irregularrent.ExcelRentCalServiceImpl;
import com.reckon.rentcalc.service.impl.specialCal.SpecialRegularCalServiceImpl;
import com.reckon.util.DictTools;


/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-2-28
 * @desc  ( 用于接收租金测算参数，并调用相应的业务规则进行测算)
 */
public class RentCalcDo {
	Logger logger = Logger.getLogger(RentCalcDo.class);

	/**
	 * 
	 *  (  租金测算调用入口点)
	 * 
	 * @param cb
	 * @param calType 流程类型
	 * @param userName测算人
	 * @param obj其它参数的传递
	 * @param kniwnRentBeans 已知租金法测算传入的分段租金计划值，为空则直接传入null
	 * <pre>中文注解：
	 * 第1期到第6期租金值是：1W
	 * 第7期到第10期租金值是：2W
	 * 第11期到第12期租金值是：3W
	 * 底层方法生成完整的租金列集合是：{1W,1W,1W,1W,1W,2W,2W,2W,2W,2W,3W,3W}
	 * kniwnRentBeans伪码参数格式例子如下：
	 * List<KnowingRentBean> bean_l = new ArrayList<KnowingRentBean>();
	 *	KnowingRentBean obj0 = new KnowingRentBean();
	 *	obj0.setStartlist(1);
	 *	obj0.setEndlist(6);
	 *	obj0.setRent("10000.00");
	 *	bean_l.add(obj0);
	 *	KnowingRentBean obj1 = new KnowingRentBean();
	 *	obj1.setStartlist(7);
	 *	obj1.setEndlist(10);
	 *	obj1.setRent("20000.00");
	 *	bean_l.add(obj1);
	 *  ...
	 *  </pre>
	 * @return
	 */
	public Hashtable<String, Object> rentCal(ConditionBean cb, String calType, String userName, String[] rentAdjustList,List<KnowingRentBean> kniwnRentBeans,List<FundPlanBean> fundPlanList,List<SpecialRulesBean> srb,List<FundRentPlanIrr> irrPlans,List<String>...rentListStr) {
		//将交易结构表的一些数据库字典信息翻译成可以程序运行的
		DictTools.getReversDict(cb);
		RentCalcService rcs = null;
		Hashtable<String, Object> resultTable = new Hashtable<String, Object>();
		try {
			// 等额租金法
			if ("even_rent".equals(cb.getSettleMethod()) && null == rentAdjustList) {
				rcs = new EvenRentCalcServiceImpl();
			} else if ("even_rent".equals(cb.getSettleMethod()) && null != rentAdjustList) {
				rcs = new DeviRentCalServiceImpl();
			}
			//均息法
			else if ("even_interest".equals(cb.getSettleMethod()) && null == rentAdjustList) {
				rcs = new EvenInterestCalcServiceImpl();
			} else if ("even_interest".equals(cb.getSettleMethod()) && null != rentAdjustList) {
				rcs = new DeviEvenInterestCalServiceImpl();
			}
			//等额本金
			else if ("even_corpus".equals(cb.getSettleMethod()) && null == rentAdjustList) {
				rcs = new EvenCorpusCaleServiceImpl();
			} else if ("even_corpus".equals(cb.getSettleMethod()) && null != rentAdjustList) {
				rcs = new DeviEvenCorpusCaleServiceImpl();
			} 
			//不规则 excel导入
			else if ("irregular_rent".equals(cb.getSettleMethod())) {
				rcs = new ExcelRentCalServiceImpl();
			}
			//分段
			else if ("special_regular".equals(cb.getSettleMethod())) {
				rcs = new SpecialRegularCalServiceImpl();
			}
			resultTable = rcs.rentCal(cb, calType, userName, rentAdjustList, 1, fundPlanList,srb,irrPlans);
			
			//获取测算结果
			Object resultMessage = resultTable.get("isSucess");
			if (resultMessage == null || resultMessage.toString().isEmpty()) {
				resultTable.put("message", "租金测算成功!");
			}
		} catch (Exception e) {
			resultTable.put("isSucess", "false");
			resultTable.put("message", "租金测算失败!");
			logger.warn("租金测算出现异常...");
			logger.error(e.toString());
			e.printStackTrace();
		}
		// 将报价id返回
		logger.debug("租金测算方法结束...");
		return resultTable;
	}
}
