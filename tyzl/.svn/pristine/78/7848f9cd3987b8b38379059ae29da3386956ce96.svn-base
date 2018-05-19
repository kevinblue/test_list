package com.reckon.irr.service.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.InterContBean;
import com.reckon.bean.TabCalBean;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.irr.service.IrrService;
import com.reckon.renttranrate.service.impl.ConditionTranServicesImpl;
import com.reckon.util.DictTools;


/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-25
 * @desc  ( 调息时现金流明细构建)
 */
public class IrrDetailsServiceByTranImpl {

	/**
	 * 
	 *  ( 调息时现金流明细构建)
	 * 
	 * @param cb
	 * @param icb
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public InterContBean processTranCashDetails(ConditionBean cb, InterContBean icb, TabCalBean tcb) throws Exception {

		// 重新构建交易结构bean 和租金计划bean,调息只更改了交易结构表的irr，所以交易结构实体仍然可从真实表取，
		// 则要重新设置设置他的doc_id,创建时间，修改人，修改时间等信息
		ConditionTranServicesImpl ctsi = new ConditionTranServicesImpl();
		// tcb.setTableToView();//2011-12-7 从视图取数 这样是为了同步客户报价时也能调用这一段构建现金流
		// 2011-12-7 在调息时构建现金流应该是从temp中根据合同号和docID来取数的.
		String cbsql = tcb.getCondition_tb() + "  where " + tcb.getContOrProjCName() + "=? and doc_id=? ";
		String fbsql = tcb.getContOrProjCName() + "=?  and doc_id=?";
		tcb.setTableToTable();// 把相关表再改回去.
		List paramValue=new ArrayList();
		List paramType=new ArrayList();
		paramValue.add(cb.getProjId());
		paramType.add(Types.VARCHAR);
		paramValue.add(tcb.getDocId());
		paramType.add(Types.VARCHAR);

        Map whereMap=new HashMap();
		whereMap.put("where",cbsql);
		whereMap.put("value", paramValue);
		whereMap.put("type", paramType);
		ConditionBean cbNew = ctsi.getCondCashBean(cb, whereMap, tcb);

		// 租金计划可从相应的临时表去取,实际上用于明细的构建只用到租金计划的租金列，租金支付日期列,合同号与报价id
		RentPlanContrCalDAOImpl rpccdi = new RentPlanContrCalDAOImpl();
		paramValue.clear();
		paramType.clear();
		paramValue.add(cb.getProjId());
		paramType.add(Types.VARCHAR);
		paramValue.add(tcb.getDocId());
		paramType.add(Types.VARCHAR);

        whereMap.clear();
		whereMap.put("where",fbsql);
		whereMap.put("value", paramValue);
		whereMap.put("type", paramType);
		FundRentPlanBean frpbNew = rpccdi.getRentInfoForCash(tcb.getRentPlan_tb(), whereMap);
		// 设置他的合同号
		frpbNew.setProjOrCont(cbNew.getProjId());

		// 由于conditionBean是从数据库中查询出来的，所以对于一些后面测算用到的数据库字段需转换
		cbNew = DictTools.getReversDict(cbNew);
		// 现金流处理,返回一个财务现金流,并将财务irr赋值到交易结构plan_iir上
		IrrService is = new IrrServiceImpl();
		// 1,默认按期来计算现金流irr,其它的如按月，按天，在此改参数 2011-06-14修改为安月计算
		cbNew = is.calCashIrrFinacAndCont(cbNew, tcb, frpbNew, "2");

		// 设置合同与央行调之关的关系
		icb.setNewIrr(cbNew.getIrr());
		icb.setNewPlanIrr(cbNew.getPlanIrr());
		return icb;
	}

}
