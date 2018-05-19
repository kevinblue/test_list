package com.reckon.irr.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.bean.CashDetailsBean;
import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.TabCalBean;
import com.reckon.dao.impl.ConditionDAOImpl;
import com.reckon.dao.impl.IrrDetailsDAOImpl;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.irr.service.IrrDetailsService;
import com.reckon.irr.service.IrrService;
import com.reckon.util.DictTools;
import com.reckon.util.IrrTools;
import com.reckon.util.TbBeanTools;


/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-4
 * @desc  ( 现金流处理总入口点实现类,调用现金流配置实现接口，构建现金流明细处理点集合点)
 */
public class IrrServiceImpl implements IrrService {
	Logger log = Logger.getLogger(IrrServiceImpl.class);

	/**
	 * 
	 *  (  合同，会计现金流处理)
	 * 
	 * @param cbm
	 * @param tb
	 * @param frpb
	 * @param calType
	 *            1 是按期类型 2按月
	 * @return
	 * @throws Exception
	 */
	public ConditionBean calCashIrrFinacAndCont(ConditionBean cb, TabCalBean tcb, FundRentPlanBean frpb, String calType) throws Exception {
		// 2011-11-11 现金流的处理隔月不补零
		//calType = "2";
		
		//现金流配置情况下，欧力士的合同与会计的流入流出一致，除了保证金只属于业务情况
		//update fund_cash_config set occu_type = '合同,会计' where  occu_type like '%合同%' and id <> '4';
		
		// 处理合同现金流明细
		calContrCashDetails(cb, tcb, frpb, calType);
		// 会计现金流明细
		calFinaCashDetails(cb, tcb, frpb, calType);
		// 2012-4-13 修改多次起租的时候会计现金流重算
		if ("onHire_more_process".equals(tcb.getCalType())) {
			log.debug("==============多次起租重算现金流=====================");
			TabCalBean tcb_cont = new TabCalBean();
			tcb_cont = TbBeanTools.getTabInfo("cont_process");
			tcb_cont.setOnHire_id(tcb.getOnHire_id());
			tcb_cont.setContOrProjCValue(tcb.getContOrProjCValue());
			tcb_cont.setDocId(tcb.getDocId());
			tcb_cont.setIs_merger(tcb.getIs_merger());// 传递是否合并计算
			tcb_cont.setUserId(tcb.getUserId());

			ConditionBean cdb_t = new ConditionBean();
			FundRentPlanBean frpb_t = new FundRentPlanBean();
			String wheresql = tcb_cont.getCondition_tb() + " where " + tcb_cont.getContOrProjCName() + "='" + tcb_cont.getContOrProjCValue() + "' and doc_id='" + tcb_cont.getDocId() + "'";
			cdb_t = new ConditionDAOImpl().getConditionBeanByContract(wheresql, tcb_cont);
			cdb_t = DictTools.getReversDict(cdb_t);
			frpb_t = new RentPlanContrCalDAOImpl().getRentAndDateByTcb(tcb_cont, 1);
			// 处理合同现金流明细
			calContrCashDetails(cdb_t, tcb_cont, frpb_t, calType);
			// 会计现金流明细
			calFinaCashDetails(cdb_t, tcb_cont, frpb_t, calType);
		}
		return cb;
	}

	/**
	 * 
	 *  (  处理合同现金流明细)
	 * 
	 * @param cb
	 * @param tcb
	 * @param frpb
	 * @throws Exception
	 */
	private void calContrCashDetails(ConditionBean cb, TabCalBean tcb, FundRentPlanBean frpb, String calType) throws Exception {
		// 处理合同配置的现金流
		IrrDetailsService ids = new IrrDetailsServiceImpl();

		List<CashDetailsBean> cdbList = ids.getCashDetailByCfg(tcb, "合同");
		
		//如果是固定补0情况下，现金流需特别处理，不能按日期合并，也不能直接把租金计划合并上去
		int size = cdbList.size();
		//log.debug("第0期存在多少行记录:"+size); 
		
		// 租金计划现金流明细的构建
		cdbList = ids.getCashDetailByRentPlan(frpb, cdbList, 1);
		
		// 以上操作完后，根据日期进行，重组现金流明细
		IrrCalServiceImpl icsi = new IrrCalServiceImpl();
		cdbList = icsi.getNewCashDetailsByCalType(calType, cdbList,cb,size);// 按期得到新的现金流明细,如按其他现金流测算的，可以在此修改，并重写方法
		
		// 合同的保证金抵扣现金流明细构建
		cdbList = IrrTools.getRentDetailsByDeduct(cdbList, cb.getCautionDeductionMoney());
		
		// 最后一期留购价，期末列值更新
		//对最后一项现金流明细进行整理,针对项目类型为‘VP业务’情况下，期末购买权不计算入最后一期现金流中
		// 项目类型 proj_type  	 VP业务/proj_type1  一般业务/proj_type2  建机/proj_type3
		//String projProjType = cb.getProjProjType();
//		if("proj_type1".equals(projProjType)){//VP
//			cdbList = IrrTools.getCashDetailsByEndValueCompanyProjType(cdbList, cb.getEquipEndValue(), cb.getNominalPrice(),cb);
//		}else{//其它
			cdbList = IrrTools.getCashDetailsByEndValue(cdbList, cb.getEquipEndValue(), cb.getNominalPrice(),cb);
//		}
		
		// 先删现金流明细,再添加现金流明细
		IrrDetailsDAOImpl idd = new IrrDetailsDAOImpl();
		idd.deleteCashDetails(tcb, tcb.getContractCashTb());// 合同现金流明细表
		idd.addCashDetails(tcb, cdbList, tcb.getContractCashTb());

		// 测算新的合同 irr
		// 2011-11-11 因为现金流是按期构造所以IRR不能用月计算
		//按期计算：String irr = IrrTools.getIrr(cdbList, cb);
		
		//欧力士IRR按固定补0计算IRR
		String irr = IrrTools.getIrr(cdbList);
		System.out.println("更新交易结构表irr:" + irr);

		// 更新交易结构表irr
		ConditionDAOImpl cd = new ConditionDAOImpl();
		cd.updateConditionContIrr(tcb, irr);

		// ConditionBean cb 设置 irr属性
		cb.setIrr(new BigDecimal(irr).multiply(new BigDecimal("100")).toString());
	}

	/**
	 * 
	 *  (  处理会计现金流明细)
	 * 
	 * @param cb
	 * @param tcb
	 * @param frpb
	 * @throws Exception
	 */
	private void calFinaCashDetails(ConditionBean cb, TabCalBean tcb, FundRentPlanBean frpb, String calType) throws Exception {

		// 处理会计配置的现金流
		IrrDetailsService ids = new IrrDetailsServiceImpl();
		List<CashDetailsBean> cdbList = ids.getCashDetailByCfg(tcb, "会计");
		int size = cdbList.size();
		// 租金计划现金流明细的构建
		cdbList = ids.getCashDetailByRentPlan(frpb, cdbList, 1);

		// 以上操作完后，根据日期进行，重组现金流明细
		IrrCalServiceImpl icsi = new IrrCalServiceImpl();
		cdbList = icsi.getNewCashDetailsByCalType(calType, cdbList,cb,size);// 按期得到新的现金流明细,如按其他现金流测算的，可以在此修改，并重写方法

		// 最后一期留购价，期末列值更新 2012-5-7 改两项不参与计算
		// cdbList = IrrTools.getCashDetailsByEndValue(cdbList, cb
		// .getEquip_end_value(), cb.getNominal_price());
		// 先删现金流明细,再添加现金流明细
		IrrDetailsDAOImpl idd = new IrrDetailsDAOImpl();
		idd.deleteCashDetails(tcb, tcb.getFinacCashTb());// 会计现金流明细表
		idd.addCashDetails(tcb, cdbList, tcb.getFinacCashTb());
		
		// 测算新的会计 plan_irr
		// 2011-11-11 因为现金流是按期构造所以IRR不能用月计算
		//String finacIrr = IrrTools.getIrr(cdbList, cb);
		
		//欧力士IRR按固定补0计算IRR
		String finacIrr = IrrTools.getIrr(cdbList);
		
		// 更新交易结构表irr
		ConditionDAOImpl cd = new ConditionDAOImpl();
		cd.updateConditionFinaIrr(tcb, finacIrr);

		// ConditionBean cb 设置 plan_irr属性
		cb.setPlanIrr(new BigDecimal(finacIrr).multiply(new BigDecimal("100")).toString());

	}

}
