package com.reckon.rentcharge.web;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.rentcharge.service.impl.RentPlanModServiceImpl;


/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-5-6
 * @desc  ( 租金计划修改)
 */
public class RentPlanModDo {

	Logger logger = Logger.getLogger(RentPlanModDo.class);

	/**
	 * 
	 *  (  租金计划修改)
	 * 
	 * @param contract_id
	 * @param doc_id
	 * @param creator
	 * @param calType
	 *            流程类型
	 * @return
	 */
	public Hashtable<String, Object> updatePlan(String contract_id, String doc_id, FundRentPlanBean rentPlan, String calType,String custId,ConditionBean condition) {

		Hashtable<String, Object> re_ht = new Hashtable<String, Object>();
		try {
			RentPlanModServiceImpl rpmsi = new RentPlanModServiceImpl();
			re_ht = rpmsi.updatePlan(contract_id, doc_id, rentPlan, calType,custId,condition);
			re_ht.put("message", "租金测算成功!");
		} catch (Exception e) {

			e.printStackTrace();
			re_ht.put("isSucess", "false");
			re_ht.put("message", "租金测算出现异常!");
			logger.warn("租金测算出现异常...");
		}
		return re_ht;
	}

}
