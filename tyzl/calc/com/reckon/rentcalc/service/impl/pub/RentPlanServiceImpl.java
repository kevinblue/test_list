package com.reckon.rentcalc.service.impl.pub;

import java.math.BigDecimal;
import java.util.List;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.TabCalBean;
import com.reckon.util.RentTools;
import com.reckon.util.tools.NumTools;


/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-4
 * @desc  ( 租金计划处理类)
 */
public class RentPlanServiceImpl {

	// 持久表租金计划信息
	// 租金计划集合调用利息，本金测算方法
	// RentCalcServiceImpl 租金测算服务接口调用方法 通过表信息,交易结构信息
	// calType：会计测算还是合同租金测算

	public FundRentPlanBean rentPlanCalByCondAndTb(ConditionBean cb, TabCalBean tcb, String calType) {
		return null;
	}

	/**
	 * 
	 * 
	 *  (  调整最后一期租金计划)
	 * 
	 * @param fpb
	 * @param rentScale
	 * @param totalCorpus
	 * @return
	 */
	public FundRentPlanBean adjustLastRentPlan(FundRentPlanBean fpb, String totalCorpus, String pmtEndValue) {

		if (null != fpb) {
			List<String> rent_list = fpb.getRentList();// 租金列表
			List<String> corpus_list = fpb.getCorpusBusinessList();// 本金列表
			List<String> interest_list = fpb.getInterestBusinessList();// 利息列表

			corpus_list = formateByScale(corpus_list, RentTools.getCorpusAccuracy());
			interest_list = formateByScale(interest_list, RentTools.getInterestAccuracy());
			rent_list = formateByScale(rent_list, RentTools.getRentAccuracy());

			// 调整利息列
			interest_list = adjustInter(rent_list, corpus_list, interest_list);
			interest_list = formateByScale(interest_list, RentTools.getInterestAccuracy());

			// 本金，利息格式化为两位小数，最后在调整最后一期的租金计划信息

			// 得到最后期的本金值
			String beforCorpus = getBefLastCorpus(corpus_list);
			String lastCorpus = NumTools.formatNumberDoubleScale(new BigDecimal(totalCorpus).subtract(new BigDecimal(pmtEndValue)).subtract(new BigDecimal(beforCorpus)).toString(), RentTools.getCorpusAccuracy());

			String lastInter = NumTools.formatNumberDoubleScale(new BigDecimal(rent_list.get(rent_list.size() - 1).toString()).subtract(new BigDecimal(lastCorpus)).toString(), RentTools.getInterestAccuracy());

			// 重新设置list值
			corpus_list.set(corpus_list.size() - 1, lastCorpus);
			interest_list.set(interest_list.size() - 1, lastInter);

			fpb.setCorpusBusinessList(corpus_list);
			fpb.setInterestBusinessList(interest_list);
			fpb.setRentList(rent_list);
			
			List<String> rentRemainList = new RentalServiceImpl().getRentRemainList(rent_list);
			//Collections.sort( rentRemainList );
			fpb.setAllRemainRentList(rentRemainList);
		}

		return fpb;
	}

	/**
	 * 
	 *  (  小数位数引起的误差，调整利息)
	 * 
	 * @param rent_list
	 * @param corpus_list
	 * @return
	 */
	public List<String> adjustInter(List<String> rent_list, List<String> corpus_list, List<String> interest_list) {
		BigDecimal bd = new BigDecimal("0");
		for (int i = 0; i < rent_list.size(); i++) {
			bd = new BigDecimal(rent_list.get(i).toString());
			bd = bd.subtract(new BigDecimal(corpus_list.get(i).toString()));
			interest_list.set(i, NumTools.formatNumberDoubleScale(bd.toString(), RentTools.getInterestAccuracy()));
		}
		return interest_list;
	}

	/**
	 * 
	 *  (  计算除最后一期外的所有的本金和)
	 * 
	 * @param corpus_list
	 * @return
	 */
	public String getBefLastCorpus(List<String> corpus_list) {
		BigDecimal bd = new BigDecimal("0");
		for (int i = 0; i < corpus_list.size() - 1; i++) {
			bd = bd.add(new BigDecimal(NumTools.formatNumberDoubleScale(corpus_list.get(i).toString(), 2)));
		}
		return bd.toString();
	}

	/**
	 * 
	 *  (  将集合中的元素格式化成两位小数返回)
	 * 
	 * @param number_list
	 * @return
	 */
	public List<String> formateByScale(List<String> number_list, int scale) {
		for (int i = 0; i < number_list.size(); i++) {
			number_list.set(i, NumTools.formatNumberDoubleScale(number_list.get(i).toString(), scale));
		}
		return number_list;
	}
}
