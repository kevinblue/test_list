package com.reckon.rentcharge.service.impl;

import java.util.Date;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.reckon.bean.AdjustBean;
import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.InterContBean;
import com.reckon.bean.TabCalBean;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.irr.service.impl.IrrDetailsServiceByTranImpl;
import com.reckon.renttranrate.service.impl.FinaRentPlanServiceImpl;
import com.reckon.util.tools.DateTools;


/**
 * 
 * @author SCLICX
 * @version 1.0
 * @copyright (C) Tenwa 2011
 * @date Dec 1, 2011
 * @desc 处理变更的时候的财务租金计划和现金流
 */
public class FinaPlanChargeServiceImpl {

	Logger logger = Logger.getLogger(FinaPlanChargeServiceImpl.class);

	/**
	 * 
	 *  处理 财务租金计划和现金流
	 * 
	 * @param tcb
	 * @param adb
	 * @param cdb
	 * @param ht
	 * @return
	 * @throws Exception
	 */
	public Hashtable<String,Object> chargeFinaPlan(TabCalBean tcb, AdjustBean adb, ConditionBean cdb, FundRentPlanBean frpb, Hashtable<String,Object> ht) throws Exception {
		InterContBean icb = new InterContBean();
		icb.setStartList(adb.getStartList());
		//根据表信息，从数据库中读取租金计划信息，合同的
		FundRentPlanBean frpb1 = new RentPlanContrCalDAOImpl().getRentAndDateByTcb(tcb, 1);
		frpb1.setColumn_1(null);
		frpb1.setColumn_2(null);
		// 设置frpb信息
		frpb1.setModificator(adb.getCreator());
		frpb1.setModifyDate(DateTools.formatToDateTime(new Date()));
		// frpb1.setYearRate(adb.getYearRate().toString());
		// 将year_rate_list更新 只要移除保存时会自动更新
		frpb1.setYearRateList(null);
		
		// 调整时 要把租金调整列清空
		frpb1.setRentAdjustList(null);
		
		// 处理财务租金计划 这个时候不区分租金测算方法
		FinaRentPlanServiceImpl frpsi = new FinaRentPlanServiceImpl();
		frpsi.processFinacRentPlan(cdb, icb, tcb, frpb1);
		
		// 现金流明细构建
		IrrDetailsServiceByTranImpl idsbti = new IrrDetailsServiceByTranImpl();
		logger.debug("流程号1:"+cdb.getDocId());
		icb = idsbti.processTranCashDetails(cdb, icb, tcb);
		logger.debug("流程号1:"+cdb.getDocId());
		return ht;
	}
}
