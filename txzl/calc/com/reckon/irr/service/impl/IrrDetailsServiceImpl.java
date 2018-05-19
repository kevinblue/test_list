package com.reckon.irr.service.impl;

import java.util.Collections;
import java.util.List;

import com.reckon.bean.CashConfigBean;
import com.reckon.bean.CashDetailsBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.TabCalBean;
import com.reckon.dao.impl.IrrConfigDAOImpl;
import com.reckon.dao.impl.IrrDetailsDAOImpl;
import com.reckon.irr.service.IrrDetailsService;
import com.reckon.util.IrrTools;
import com.reckon.util.tools.NumTools;


/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-4
 * @desc  ( 现金流明细实现类)
 */
public class IrrDetailsServiceImpl implements IrrDetailsService {

	/**
	 * 
	 *  (  根据配置信息整理配置现金流信息)
	 * 
	 * @param tcb
	 * @param occu_type
	 *            合同或者会计
	 * @return
	 * @throws Exception
	 */
	public List<CashDetailsBean> getCashDetailByCfg(TabCalBean tcb, String occu_type) throws Exception {

		// 配置现金流信息
		IrrConfigDAOImpl icd = new IrrConfigDAOImpl();
		List<CashConfigBean> configList = icd.getConfigInfo(occu_type);
		// 根据配置现金流信息，读取相应的表数据构建配置现金流明细
		// 得到配置现金流所要执行的sql
		// IrrCalServiceImpl icsi = new IrrCalServiceImpl();
		// String sql = icsi.getCfgCashSqlByCfgAndTb(configList, tcb);
		String sql = IrrTools.getCfgCashSqlByCfgAndTb(configList, tcb);

		IrrDetailsDAOImpl idd = new IrrDetailsDAOImpl();
		return idd.getcDetailsBySql(sql, tcb.getQuot_id());
	}

	/**
	 * 
	 *  (  租金计划现金流明细构建)
	 * 
	 * @param frpb
	 * @param cdbList
	 * @param startList
	 *            开始期项
	 * @return
	 * @throws Exception
	 */
	public List<CashDetailsBean> getCashDetailByRentPlan(FundRentPlanBean frpb, List<CashDetailsBean> cdbList, int startList) throws Exception {

		List<String> rent_list = frpb.getRentList();
		List<String> date_list = frpb.getPlanDateList();
		
		Collections.sort(date_list);
		
		System.out.println("---------------------------------------------------");
		
		System.out.println("现金流日期输出开始：");
		for (int i = startList - 1; i < rent_list.size(); i++) {
			System.out.println(date_list.get(i));
		}
		System.out.println("现金流日期输出结束!");
		
		for (int i = startList - 1; i < rent_list.size(); i++) {
			
			CashDetailsBean cdb = new CashDetailsBean();
			cdb.setContractId(frpb.getProjOrCont());
			cdb.setPlanDate(date_list.get(i).toString());
			cdb.setQuotId(frpb.getQuotId());

			cdb.setFundIn(rent_list.get(i).toString());
			cdb.setFundInDetails("租金：" + NumTools.formatNumberDouble(Double.parseDouble(NumTools.formatNumberDoubleScale(rent_list.get(i).toString(), 2))));

			cdb.setFundOut("0");
			cdb.setFundOutDetails("");

			cdb.setNetFlow(rent_list.get(i).toString());
			cdbList.add(cdb);

		}

		return cdbList;
	}
}
