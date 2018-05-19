package com.reckon.rentcalc.service.impl.irregularrent;

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
import com.reckon.irr.service.IrrService;
import com.reckon.irr.service.impl.IrrServiceImpl;
import com.reckon.rentcalc.service.RentCalcService;
import com.reckon.rentcalc.service.impl.evenrent.EvenRentCalcServiceImpl;
import com.reckon.rentcalc.service.impl.pub.ConditionServiceImpl;
import com.reckon.rentcalc.service.impl.pub.RentPlanContrCalServiceImpl;
import com.reckon.rentcalc.service.impl.pub.RentPlanFinacCalServiceImpl;
import com.reckon.util.TbBeanTools;


/**
 * 
 * @author SCLICX
 * @version 1.0
 * @copyright (C) Tenwa 2011
 * @date Nov 26, 2011
 * @desc  不规则的租金测算 在测算的时候只是保存 condition 而已 在起租的时候直接提示不允许
 */
public class IrregularRentCalServiceImpl implements RentCalcService {
	static Logger logger = Logger.getLogger(EvenRentCalcServiceImpl.class);

	/**
	 * 不规则时测算的方法 不规则的租金测算 只保存交易结果就可以了 租金计划 等一系列的动作 不负责.
	 * 
	 * @param cb
	 * @param calType
	 * @param userName
	 * @param rentAdjustList
	 */
	public Hashtable<String, Object> rentCal(ConditionBean cb, String calType, String userName, String[] rentAdjustList, int startList,Boolean isCharge) throws Exception {
		logger.info("进入方法:rentCal 进行不规则（等额租金）的测算..");
		// 得到操作表信息
		TabCalBean tcb = TbBeanTools.getTabInfo(calType, cb);
		ConditionServiceImpl csi = new ConditionServiceImpl();
		// 对交易结构信息处理
		csi.addConditionByCal(tcb, cb);
		// 合同租金测算
		RentPlanContrCalServiceImpl rpcs = new RentPlanContrCalServiceImpl();

		FundRentPlanBean frpb = new FundRentPlanBean();
		// 设置他的合同号
		frpb.setProjOrCont(cb.getProjId());
		frpb.setCreator(cb.getCreator());
		frpb.setYearRate(cb.getYearRate());
		frpb.setRentAdjustList(new ArrayList<String>());// 租金调整值

		// 1,表从第一期开始;2,表租金格式化为几位小数,合同租金计划测算
		frpb = rpcs.rentPlanCalByConditionAndTab(cb, tcb, startList, frpb);

		// // 调用保存的操作
		// // 根据表信息进行对表数据的清除操作
		// RentPlanContrCalDAOImpl rpcd = new RentPlanContrCalDAOImpl();
		// rpcd.addRentPlan(frpb, tcb, "1", "2");

		// 现金流处理,返回一个财务现金流,并将财务irr赋值到交易结构plan_iir上
		IrrService is = new IrrServiceImpl();
		// 1,默认按期来计算现金流irr,其它的如按月，按天，在此改参数
		// 2011-06-14修改为安月计算
		// 2011-11-11 改为按期
		cb = is.calCashIrrFinacAndCont(cb, tcb, frpb, "1");

		//
		// 财务租金计划处理
		RentPlanFinacCalServiceImpl rpfcs = new RentPlanFinacCalServiceImpl();
		// 1,表从第一期开始;2,表租金格式化为几位小数
		rpfcs.rentPlanCalByConditionAndTab(cb, tcb, startList, frpb);

		Hashtable<String, Object> re_ht = new Hashtable<String, Object>();
		re_ht.put("isSucess", "true");

		logger.info("结束不规则测算方法:rentCal ..");
		return re_ht;
	}

	/**
	 * 不规则时起租的方法
	 * 
	 * @param cb
	 * @param calType
	 * @param userName
	 * @param rentAdjustList
	 */
	public Hashtable<String, Object> rentCalOnHire(ConditionBean cb, String calType, String userName, String[] rentAdjustList) throws Exception {
		//  Auto-generated method stub
		Hashtable<String, Object> re_ht = new Hashtable<String, Object>();
		re_ht.put("isSucess", "false");
		re_ht.put("message", "不规则的租金测算起租日不允许变更,如果要修改租金计划中的还款日请在租金计划修改中完成!");
		logger.debug("进行不规则时测算的方法:rentCalOnHire ..");
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
