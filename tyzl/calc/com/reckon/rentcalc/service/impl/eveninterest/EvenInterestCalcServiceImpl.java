package com.reckon.rentcalc.service.impl.eveninterest;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.FundRentPlanIrr;
import com.reckon.bean.SpecialRulesBean;
import com.reckon.bean.TabCalBean;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.irr.service.IrrService;
import com.reckon.irr.service.impl.IrrServiceImpl;
import com.reckon.rentcalc.service.RentCalcService;
import com.reckon.rentcalc.service.impl.evenrent.PlanDateServiceImpl;
import com.reckon.rentcalc.service.impl.pub.ConditionServiceImpl;
import com.reckon.rentcalc.service.impl.pub.RentPlanFinacCalServiceImpl;
import com.reckon.util.InsureTypeTools;
import com.reckon.util.TbBeanTools;


/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-2
 * @desc  ( 均息法测算)
 */
public class EvenInterestCalcServiceImpl implements RentCalcService {

	static Logger logger = Logger.getLogger(EvenInterestCalcServiceImpl.class);

	public Hashtable<String, Object> rentCal(ConditionBean cb, String calType, String userName, String[] rentAdjustList, int startList,List<FundPlanBean> fundPlanList,List<String> ...planList) throws Exception {
		logger.info("进入方法:rentCal 进行均息法的测算..");
		// 得到操作表信息
		TabCalBean tcb = TbBeanTools.getTabInfo(calType, cb);
		
		// 保存商务条件
		ConditionServiceImpl csi = new ConditionServiceImpl();
		csi.addConditionByCal(tcb, cb);
				
		// 合同租金测算
		FundRentPlanBean frpb = new FundRentPlanBean();
		frpb.setProjOrCont(cb.getProjId());
		frpb.setCreator(cb.getCreator());
		frpb.setYearRate(cb.getYearRate());
		frpb.setRentAdjustList(new ArrayList<String>());// 租金调整值

		// 合同租金计划测算
		EvenInterestPlanContrCalServiceImpl rpcs = new EvenInterestPlanContrCalServiceImpl();
		rpcs.rentPlanCalByConditionAndTab(cb, tcb, startList, frpb);

		Hashtable<String, Object> re_ht = new Hashtable<String, Object>();
		re_ht = RentCalculateHelper.createFundPlanCashIrr(cb, tcb, fundPlanList, frpb);
		re_ht.put("isSucess", "true");

		logger.info("结束均息法的测算方法:rentCal ..");
		return re_ht;
	}

	/**
	 * 
	 *  (  起租时实现方法)
	 * 
	 * @param cb
	 * @param calType
	 * @param userName
	 * @param rentAdjustList
	 * @return
	 * @throws Exception
	 */
	public Hashtable<String, Object> rentCalOnHire(ConditionBean cb, String calType, String userName, String[] rentAdjustList,List<FundPlanBean> fundPlanList) throws Exception {

		logger.info("进入方法:rentCalOnHire 进行起租时的均息法测算..");
		// 起租时只重新保存交易结构信息，更改租金计划支付日期，更改现金流明细

		// 得到操作表信息
		TabCalBean tcb = TbBeanTools.getTabInfo(calType, cb);
		ConditionServiceImpl csi = new ConditionServiceImpl();
		// 对交易结构信息处理
		csi.addConditionByCal(tcb, cb);
		// 合同租金测算
		// RentPlanContrCalServiceImpl rpcs = new RentPlanContrCalServiceImpl();

		// 查询他的租金计划
		RentPlanContrCalDAOImpl rpccdi = new RentPlanContrCalDAOImpl();
		FundRentPlanBean frpb = rpccdi.getRentAndDateByTcb(tcb, 1);
		// FundRentPlanBean frpb = new FundRentPlanBean();
		// 设置他的合同号
		frpb.setProjOrCont(cb.getProjId());
		frpb.setCreator(cb.getCreator());
		frpb.setYearRate(cb.getYearRate());
		frpb.setRentAdjustList(new ArrayList<String>());// 租金调整值

		// 根据交易结构重新算出的租金计划日期
		// 日期类返回租金列表
		PlanDateServiceImpl pdsi = new PlanDateServiceImpl();
		frpb.setPlanDateList(pdsi.getPlanDateList(cb, String.valueOf(frpb.getRentList().size())));
		// 更新租金计划 的时间
		rpccdi.updateRentPlanDate(tcb.getRentPlan_tb(), tcb, 1, frpb.getPlanDateList());
		// 更新会计的租金计划时间
		rpccdi.updateRentPlanDate(tcb.getRentFinaPlan_tb(), tcb, 1, frpb.getPlanDateList());

		Hashtable<String, Object> re_ht = new Hashtable<String, Object>();
		re_ht = RentCalculateHelper.createFundPlanCashIrr(cb, tcb, fundPlanList, frpb);
		// 保存商务条件
		csi.addConditionByCal(tcb, cb);
		re_ht.put("isSucess", "true");

		logger.info("结束均息法起租时测算方法:rentCalOnHire ..");
		return re_ht;
	}

	@Override
	public Hashtable<String, Object> rentCal(ConditionBean cb, String calType,
			String userName, String[] rentAdjustList, int startList,
			List<FundPlanBean> fundPlanList, List<SpecialRulesBean> srb,
			List<FundRentPlanIrr> irrPlans, List<String>... planList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hashtable<String, Object> rentCalOnHire(ConditionBean cb,
			String calType, String userName, String[] rentAdjustList,
			List<FundPlanBean> fundPlanList, List<SpecialRulesBean> srb,
			List<FundRentPlanIrr> irrPlans) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
