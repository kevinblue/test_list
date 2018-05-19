package com.reckon.rentcharge.service.impl;

import java.util.Arrays;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.reckon.bean.AdjustBean;
import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.TabCalBean;
import com.reckon.dao.impl.AdjustDAOImpl;
import com.reckon.dao.impl.ConditionDAOImpl;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.rentcalc.service.impl.evencorpus.EvenCorpusPlanContrCalServiceImpl;
import com.reckon.rentcalc.service.impl.eveninterest.EvenInterestPlanContrCalServiceImpl;
import com.reckon.rentcalc.service.impl.pub.RentPlanContrCalServiceImpl;
import com.reckon.rentcharge.service.RentChargeService;
import com.reckon.util.DictTools;
import com.reckon.util.TbBeanTools;


/**
 * 
 * @author SCLICX
 * @version 1.0
 * @copyright (C) Tenwa 2011
 * @date Mar 28, 2011
 * @desc  ( 不规则租金变更现实主体类,通过对conditionbean和不规则租金list的处理再调用租金测算类完成变更)
 */
public class DeviRentChargeServiceImpl implements RentChargeService {
	Logger logger = Logger.getLogger(DeviRentChargeServiceImpl.class);

	public Hashtable<String, Object> rentChargeCal(AdjustBean adb, String calType, Hashtable<String, Object> ht, String[] rentAdjustList) throws Exception {
		logger.debug("进入变更主体方法:rentChargeCal");
		String error = (String)ht.get("error");
		error = error == null ? "" : error;
		ConditionBean cdb = new ConditionBean();// 交易结构表
		TabCalBean tcb = TbBeanTools.getTabInfo(calType, adb);
		String wheresql = tcb.getCondition_tb() + " where " + tcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "' and doc_id='" + tcb.getDocId() + "'";
		ConditionDAOImpl cddao = new ConditionDAOImpl();
		cdb = cddao.getConditionBeanByContract(wheresql, tcb);
		// 租金计划变更 或者提前结清不平移租金计划日期 2012-5-7
		//cdb.setChangeDate(false);
		
		// 转换为测算可用bean
		cdb = DictTools.getReversDict(cdb);
		// 获得租金计划表信息 因为用租金计划来算剩余总本金 所以要查询全部的租金计划
		FundRentPlanBean frpb = new RentPlanContrCalDAOImpl().getRentAndDateByTcb(tcb, 1);
		// 根据变更表信息获取condition表信息
		cdb = AdjustDAOImpl.getConditionBeanByAdjustBean(cdb, adb, frpb);
		frpb.setRentAdjustList(Arrays.asList(rentAdjustList));// 租金调整列值

		try {

			// 调用不规则租金测算的构建租金计划方法
			RentPlanContrCalServiceImpl rpcs = null;
			if ("even_rent".equals(cdb.getSettleMethod())) {
				// 为了让等额租金中覆盖这两个值所以要滞空
				frpb.setColumn_1(null);
				frpb.setColumn_2(null);
				rpcs = new RentPlanContrCalServiceImpl();
			} else if ("even_interest".equals(cdb.getSettleMethod())) {// 均息法
				rpcs = new EvenInterestPlanContrCalServiceImpl();
			} else if ("even_corpus".equals(cdb.getSettleMethod())) {// 均息法
				rpcs = new EvenCorpusPlanContrCalServiceImpl();
			} else if ("irregular_rent".equals(cdb.getSettleMethod())) {// 不规则
				ht.put("isSucess", "false");
				error += "\\n租金测算方法为不规则的租金计划不允许进行租金计划变更!\\n请在租金计划修改流程中实现相关操作!\\n已删除此次变更操作记录.";
				return ht;
			}
			frpb = rpcs.rentPlanCalByDevi(cdb, tcb, adb.getStartList(), frpb, rentAdjustList);
			// 租金计划变更的时候单独修改第一期租金支付日
			// new RentPlanContrCalDAOImpl().updateEndPlanDate(tcb);
		} catch (Exception e) {
			e.printStackTrace();
			ht.put("isSucess", "false");
			error += "调用不规则租金测算的构建租金计划方法失败:" + e.toString();
			return ht;
		}

		try {
			ht = new FinaPlanChargeServiceImpl().chargeFinaPlan(tcb, adb, cdb, frpb, ht);
		} catch (Exception e) {
			e.printStackTrace();
			ht.put("isSucess", "false");
			error += "处理会计租金计划或者现金流失败:" + e.toString();
			return ht;
		}
		ht.put("error", error);
		ht.put("isSucess", "true");
		return ht;
	}

	

}
