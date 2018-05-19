package com.reckon.irr.service.impl;

import org.apache.log4j.Logger;

/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-23
 * @desc  ( 调息时现金流构建)
 */
public class IrrServiceByTranImpl {
	static Logger logger = Logger.getLogger(IrrServiceByTranImpl.class);

	// public ConditionBean calCashIrrFinacAndCont(ConditionBean cb,
	// TabCalBean tcb, FundRentPlanBean frpb, String calType)
	// throws Exception {
	// // 处理合同现金流明细
	// calContrCashDetails(cb, tcb, frpb, calType);
	// // 会计现金流明细
	// calFinaCashDetails(cb, tcb, frpb, calType);
	// return cb;
	// }
	//
	//
	// /**
	// *
	// *  (  处理合同现金流明细)
	// *
	// * @param cb
	// * @param tcb
	// * @param frpb
	// * @throws Exception
	// */
	// private void calContrCashDetails(ConditionBean cb, TabCalBean tcb,
	// FundRentPlanBean frpb, String calType) throws Exception {
	// // 处理合同配置的现金流
	// IrrDetailsService ids = new IrrDetailsServiceImpl();
	// List<CashDetailsBean> cdbList = ids.getCashDetailByCfg(tcb, "合同");
	// // 租金计划现金流明细的构建
	// cdbList = ids.getCashDetailByRentPlan(frpb, cdbList);
	//
	// // 以上操作完后，根据日期进行，重组现金流明细
	// IrrCalServiceImpl icsi = new IrrCalServiceImpl();
	// cdbList = icsi.getNewCashDetailsByCalType(calType, cdbList);//
	// 按期得到新的现金流明细,如按其他现金流测算的，可以在此修改，并重写方法
	//
	// // 合同的保证金抵扣现金流明细构建
	// cdbList = IrrTools.getRentDetailsByDeduct(cdbList, cb
	// .getCaution_money());
	// // 最后一期留购价，期末列值更新
	// cdbList = IrrTools.getCashDetailsByEndValue(cdbList, cb
	// .getEquip_end_value(), cb.getNominal_price());
	// // 先删现金流明细,再添加现金流明细
	// IrrDetailsDAO idd = new IrrDetailsDAOImpl();
	// idd.deleteCashDetails(tcb, tcb.getContractCashTb());// 合同现金流明细表
	// idd.addCashDetails(tcb, cdbList, tcb.getContractCashTb());
	// // 测算新的合同 irr
	//
	// String irr = IrrTools.getIrr(cdbList, cb);
	//
	// // 更新交易结构表irr
	// ConditionDAO cd = new ConditionDAOImpl();
	// cd.updateConditionContIrr(tcb, irr);
	//
	// // ConditionBean cb 设置 irr属性
	// cb.setIrr(irr);
	// }
	//
	//
	// /**
	// *
	// *  (  处理会计现金流明细)
	// *
	// * @param cb
	// * @param tcb
	// * @param frpb
	// * @throws Exception
	// */
	// private void calFinaCashDetails(ConditionBean cb, TabCalBean tcb,
	// FundRentPlanBean frpb, String calType) throws Exception {
	//
	// // 处理会计配置的现金流
	// IrrDetailsService ids = new IrrDetailsServiceImpl();
	// List<CashDetailsBean> cdbList = ids.getCashDetailByCfg(tcb, "会计");
	// // 租金计划现金流明细的构建
	// cdbList = ids.getCashDetailByRentPlan(frpb, cdbList);
	//
	// // 以上操作完后，根据日期进行，重组现金流明细
	// IrrCalServiceImpl icsi = new IrrCalServiceImpl();
	// cdbList = icsi.getNewCashDetailsByCalType(calType, cdbList);//
	// 按期得到新的现金流明细,如按其他现金流测算的，可以在此修改，并重写方法
	//
	// // 最后一期留购价，期末列值更新
	// cdbList = IrrTools.getCashDetailsByEndValue(cdbList, cb
	// .getEquip_end_value(), cb.getNominal_price());
	// // 先删现金流明细,再添加现金流明细
	// IrrDetailsDAO idd = new IrrDetailsDAOImpl();
	// idd.deleteCashDetails(tcb, tcb.getFinacCashTb());// 会计现金流明细表
	// idd.addCashDetails(tcb, cdbList, tcb.getFinacCashTb());
	// // 测算新的会计 plan_irr
	// String finacIrr = IrrTools.getIrr(cdbList, cb);
	//
	// // 更新交易结构表irr
	// ConditionDAO cd = new ConditionDAOImpl();
	// cd.updateConditionFinaIrr(tcb, finacIrr);
	//
	// // ConditionBean cb 设置 plan_irr属性
	// cb.setPlan_irr(new BigDecimal(finacIrr).multiply(new BigDecimal("100"))
	// .toString());
	//
	// }

//	/**
//	 * 
//	 *  (  调息时会计租金计划irr计算)
//	 * 
//	 * @param cb
//	 * @param tcb
//	 * @param frpb
//	 * @param calType
//	 * @param startList
//	 * @return
//	 * @throws Exception
//	 */
//	public ConditionBean getAdjustFinacIrr(ConditionBean cb, TabCalBean tcb, FundRentPlanBean frpb, String calType, String startList) throws Exception {
//
//		// 处理会计配置的现金流
//		IrrDetailsService ids = new IrrDetailsServiceImpl();
//		List<CashDetailsBean> cdbList = new ArrayList<CashDetailsBean>();// 是从正常期开始时
//
//		if (Integer.parseInt(startList) == 1) {// 查看调息是是从第一期开始时则加添加交易结构的现金流明细
//			cdbList = ids.getCashDetailByCfg(tcb, "会计");
//		} else {
//			CashDetailsBean cdb = new CashDetailsBean();
//			cdb.setNet_flow("-" + cb.getCalTotalByFinac());// 由于只是净流量影响irr,时间为上一期的时间值
//			cdb.setFund_in("0");
//			cdb.setFund_out(cb.getCalTotalByFinac());
//			
////			// 2011-11-13 因为调息合同终止不同. 调息 后面还有租金计划 终止实际上只有一期租金计划了 所以这个日期可能取不到.
////			String plan_temp = "";
////			if (frpb.getPlanDate_list().size() >= Integer.parseInt(startList)) {
////				plan_temp = frpb.getPlanDate_list().get(Integer.parseInt(startList) - 1).toString();
////			} else {
////				plan_temp = frpb.getPlanDate_list().get(frpb.getPlanDate_list().size() - 1).toString();
////			}
////			String plan_date = DateTools.dateAdd("month", -12 / Integer.parseInt(cb.getIncomeNumberYear()), plan_temp);
////			cdb.setPlan_date(plan_date);
//			
//			
//			cdb.setPlan_date(cb.getStartDate());
//			cdbList.add(cdb);
//		}
//		// 租金计划现金流明细的构建
//		cdbList = ids.getCashDetailByRentPlan(frpb, cdbList, Integer.parseInt(startList));
//
//		// 以上操作完后，根据日期进行，重组现金流明细
//		IrrCalServiceImpl icsi = new IrrCalServiceImpl();
//		cdbList = icsi.getNewCashDetailsByCalType(calType, cdbList);// 按期得到新的现金流明细,如按其他现金流测算的，可以在此修改，并重写方法
//
//		// 最后一期留购价，期末列值更新
//		cdbList = IrrTools.getCashDetailsByEndValue(cdbList, cb.getEquipEndValue(), cb.getNominalPrice());
//
//		// 测算新的会计 plan_irr
//		String finacIrr = IrrTools.getIrrByPreMonth(cdbList, cb);
//
//		// ConditionBean cb 设置 plan_irr属性,暂时用于会计租金计划的测算
//		cb.setPlanIrr(new BigDecimal(finacIrr).multiply(new BigDecimal("100")).toString());
//
//		logger.info("调息时会计租金计划测算irr:" + finacIrr);
//		return cb;
//
//	}
}
