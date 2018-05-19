package com.reckon.irr.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.dao.impl.ActuIrrDAOImpl;
import com.reckon.util.IrrTools;
import com.reckon.util.tools.DateTools;


/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-6-9
 * @desc  ( 实际irr业务测算类)
 */
public class ActuIrrServiceImpl {

	Logger logger = Logger.getLogger(ActuIrrServiceImpl.class);

	/**
	 * 
	 *  (  根据合同号算出他的实际irr)
	 * 
	 * @param contract_id
	 * @return
	 * @throws Exception
	 */
	public String calActuIrrByContractId(String contract_id) throws Exception {
		logger.debug("进入实际irr测算方法..");

		// 根据合同号，查询出资金实收，租金实收
		Hashtable<String, List<String>> actu_ht = new ActuIrrDAOImpl().getActuCash(contract_id);

		String irr = IrrTools.getXIRR(actu_ht.get("fact_money"), actu_ht.get("fact_date"));

		logger.info("实际irr:" + irr);
		return irr;
	}

	/**
	 * 
	 *  (  得到实际测算的现金流list)
	 * 
	 * @param actu_ht
	 * @return
	 */
	public List<String> getCashList(Hashtable<String, List<String>> actu_ht) {
		List<String> actu_date = (List<String>) actu_ht.get("fact_date");
		List<String> cashList = new ArrayList<String>();
		// 求出第一元素与最后元素的间隔月数
		if (actu_ht != null && actu_date.size() > 0) {
			// /fact_date,fact_money
			String beginDate = actu_date.get(0).toString();
			String endDate = actu_date.get(actu_date.size() - 1).toString();
			long diffMonth = DateTools.dateDiff("month", beginDate, endDate);
			logger.debug("实际收付跨付月数:" + diffMonth + "beginDate:" + beginDate + ";endDate:" + endDate);
			List<String> actu_money = (List<String>) actu_ht.get("fact_money");
			// 根据同一日期的金额合并
			Hashtable<String, String> disHt = remoRepDate(actu_date, 7);// 年月
			// 新的时间集合
			String[] obj = (String[])disHt.keySet().toArray();
			Arrays.sort(obj);
			List<String> dateNewList = Arrays.asList(obj);

			// 新的金额
			List<String> moneyNewList = getNetCashByDate(disHt, actu_money);
			// 根据日期list，月间隔重新组建租金List
			for (int i = 0; i <= diffMonth; i++) {
				String newDate = DateTools.dateAdd("month", i, beginDate);

				// 判断此日期是否是在相应的新的时间集合当中
				if (dateNewList.contains(newDate.substring(0, 7))) {// 包含时
					// 去查询相应的下标值
					int seat = getSeat(dateNewList, newDate.substring(0, 7));
					cashList.add(moneyNewList.get(seat));

				} else {// 不在时以0来补充
					cashList.add("0");
				}
			}
		}
		return cashList;
	}

	/**
	 * 
	 *  (  返回一个元素的下标值)
	 * 
	 * @param inList
	 * @param element
	 * @return
	 */
	public static int getSeat(List<String> inList, String element) {
		int i = 0;
		for (i = 0; i < inList.size(); i++) {
			if (element.equals(inList.get(i).toString())) {
				break;
			}
		}
		return i;
	}

	/**
	 * 
	 *  (  得到去除重复的时间集合，返回一个含有一个时间，对应他的现金集合的下标的键值对
	 * ,如按天则substring(0, 7)需处理)
	 * 
	 * @param ccfbList
	 * @return
	 */
	public static Hashtable<String, String> remoRepDate(List<String> actu_date, int dtlen) {
		Hashtable<String, String> htDate = new Hashtable<String, String>();
		String str = "";
		for (int i = 0; i < actu_date.size(); i++) {

			String ccfb = actu_date.get(i).toString();
			if (!htDate.containsKey(ccfb.substring(0, dtlen))) {
				htDate.put(ccfb.substring(0, dtlen), String.valueOf(i));
			} else {
				str = (String) htDate.get(ccfb.substring(0, dtlen));
				str = str + "," + String.valueOf(i);
				htDate.put(ccfb.substring(0, dtlen), str);
			}
		}
		return htDate;

	}

	/**
	 * 
	 *  (  根据时间得到新的现金流集合 )
	 * 
	 * @param ht_date
	 * @return
	 */
	public static List<String> getNetCashByDate(Hashtable<String, String> ht_date, List<String> moneyList) {

		List<String> listNew = new ArrayList<String>();
		Object[] obj = ht_date.keySet().toArray();
		Arrays.sort(obj); // 按键值排序

		for (int i = 0; i < obj.length; i++) {

			String[] strArray = ht_date.get(obj[i].toString()).toString().split(",");
			String net_flow = "0"; // 净流量

			for (int j = 0; j < strArray.length; j++) {

				String cdbOld = moneyList.get(Integer.parseInt(strArray[j].toString())).toString();

				net_flow = new BigDecimal(net_flow).add(new BigDecimal(cdbOld)).toString();

			}
			listNew.add(net_flow);

		}
		// 返回新的现金流明细
		return listNew;

	}
}
