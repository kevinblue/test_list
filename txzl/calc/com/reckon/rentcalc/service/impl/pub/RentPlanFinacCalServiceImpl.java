package com.reckon.rentcalc.service.impl.pub;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.TabCalBean;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.renttranrate.service.TransRateHelper;


/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-4
 * @desc  ( 财务租金测算)
 */
public class RentPlanFinacCalServiceImpl {

	static Logger logger = Logger.getLogger(RentPlanFinacCalServiceImpl.class);

	/**
	 * 
	 *  (  财务租金计划 测算方法)
	 * 
	 * @param cb
	 * @param tcb
	 * @param startList
	 * @param rentScale
	 * @return
	 * @throws Exception
	 */
	public FundRentPlanBean rentPlanCalByConditionAndTab(ConditionBean cb, TabCalBean tcb, int startList, FundRentPlanBean frpb) throws Exception {
		// 利息类返回租金列表
		InterestCalServiceImpl icsi = new InterestCalServiceImpl();
		// 财务利息
		// 2011-11-11 因为变更调息等都是从指定期数开始变的 所以对应 的租金列表也要从对应期项开始
		// 注意此处的 指针传递问题 实际上因为rent_List
		// 的改变frpb中的也变了.但是暂时没发现改变之后对后续的影响所以没有用克隆数组的方式
		List<String> rent_List = frpb.getRentList();
		int size = frpb.getRentList().size();
		for (int i = 0; i < startList - 1 && size >= startList; i++) {
			rent_List.remove(0);
			frpb.getPlanDateList().remove(0);// 把对应的日期数组也移去保证插入日期的和对应的本息的关系正确
		}
		// [Key:scl]手续费分摊修改
		// frpb.setInterest_finac_list(icsi.getFinacInterestLsit(cb, rent_List,
		// cb.getCleanLeaseMoney()));//之前的代码
		// 修改后的代码
		FundRentPlanBean frpb_old = new FundRentPlanBean();
		frpb_old = new RentPlanContrCalDAOImpl().getRentAndDateByTcb(tcb, 1);
		frpb.setInterestFinacList(icsi.getFinacInterestListNew(cb, frpb, frpb_old, startList));
		// 修改结束
		// 本金类返回租金列表
		CorpusServiceImpl csi = new CorpusServiceImpl();
		logger.info("会计租金计划最后一期利息值:frpb.getInterest_finac_list()" + frpb.getInterestFinacList().get(frpb.getInterestFinacList().size() - 1));
		frpb.setCorpusFinacList(csi.getCorpusList(rent_List, frpb.getInterestFinacList()));

		// 本金余额类返回租金列表
		frpb.setCorpusOverageFinacList(TransRateHelper.getCorpusOvergeList(cb.getCleanLeaseMoney(), frpb.getCorpusFinacList()));

		// 调用更新dao操作
		// RentPlanFinacCalDAOImpl rpcd = new RentPlanFinacCalDAOImpl();
		// rpcd.updateRentPlan(frpb, tcb, startList, rentScale);

		// 财务租金计划修改2011-04-07
		// 根据表信息进行对表数据的清除操作
		RentPlanContrCalDAOImpl rpcd = new RentPlanContrCalDAOImpl();
		rpcd.deleteRentPlan(tcb.getRentFinaPlan_tb(), tcb, startList);

		// 重新构建租金计划列表 本金，利息，本金余额
		frpb.setInterestBusinessList(frpb.getInterestFinacList());
		frpb.setCorpusBusinessList(frpb.getCorpusFinacList());
		frpb.setCorpusOverageBusinessList(frpb.getCorpusOverageFinacList());
		frpb.setRentAdjustList(new ArrayList<String>());// 租金调整值

		// 2011-06-20修改 本金，利息格式化，并调整最后一期的本金值，财务租金计划跟资产余值无关,资产余值为0
		RentPlanServiceImpl rpsi = new RentPlanServiceImpl();
		// 2011-07-13修改
		logger.info("会计租金计划最后一期调整:cb.getPmtEndValue()" + cb.getEquipEndValue());
		rpsi.adjustLastRentPlan(frpb, cb.getCleanLeaseMoney(), cb.getEquipEndValue());

		// 调用保存的操作
		rpcd.addRentPlan(tcb.getRentFinaPlan_tb(), frpb, tcb, startList,cb);
		logger.info("会计租金计划生成成功....方法rentPlanCalByConditionAndTab");

		// 返回租金计划实体 bean
		return frpb;
	}

}
