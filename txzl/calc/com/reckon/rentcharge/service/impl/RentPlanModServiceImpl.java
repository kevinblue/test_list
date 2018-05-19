package com.reckon.rentcharge.service.impl;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.TabCalBean;
import com.reckon.commons.helper.ObjectConvertUtils;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.dao.impl.ConditionDAOImpl;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.entity.contract.reckon.condition.FundPutPlan;
import com.reckon.renttranrate.service.TransRateHelper;
import com.reckon.util.DictTools;
import com.reckon.util.TbBeanTools;
import com.reckon.util.tools.DateTools;
import com.reckon.util.tools.NumTools;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;


/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-5-6
 * @desc  ( 租金计划修改业务处理类)
 */
public class RentPlanModServiceImpl {
	Logger logger = Logger.getLogger(RentPlanModServiceImpl.class);

	/**
	 * 
	 *  (  租金计划业务处理方法)
	 * 
	 * @param contract_id
	 * @param doc_id
	 * @param creator
	 * @param calType
	 *            流程类型
	 * @return
	 * @throws Exception
	 */
	public Hashtable<String, Object> updatePlan(String contract_id, String doc_id, FundRentPlanBean frpb, String calType,String custId,ConditionBean cb) throws Exception {
		TabCalBean tcb = TbBeanTools.getTabInfo(calType);// 先获得操作表信息 用以查询出交易结构
		// [key:scl-A]2012-12-07在租金计划修改的时候如果是客户报价则要记录下报价ID
		tcb.setDocId(doc_id);
		tcb.setContOrProjCValue(contract_id);
		// tcb.setTableToView();//为了配合统一的SQL语句查询
		// 客户报价没有doc_id所以在这里查询交易结构的时候通过视图查询
		logger.info("进入方法:updatePlan 进行租金计划修改..");
		// 构建从交易结构表查询的form语句,交易结构表信息获得
		if(cb == null){
			ConditionDAOImpl cdi = new ConditionDAOImpl();
			String wheresql = tcb.getCondition_tb() + " where " + tcb.getContOrProjCName() + "='" + contract_id + "' and doc_id='" + doc_id + "'";
			cb = cdi.getConditionBeanByContract(wheresql, tcb);
			//投放计划,为后面的资金计划现金流以及宽限期收款计划
			TableService tableService =(TableService) WebUtil.getApplicationContext().getBean("tableService");
			Map<String,Object> querymap = new HashMap<String, Object>();
			querymap.put("contractId", contract_id);
			List<ContractInfo> ci = tableService.findEntityByProperties(ContractInfo.class, querymap);
			String cntractId = "";
			if(ci.size()>0){
				cntractId = ci.get(0).getId();
			}
			Map<String, String> queryMainObjectMap = new HashMap<String, String>();
			queryMainObjectMap.put("contract_id", cntractId);
			queryMainObjectMap.put("feetypein", "feetype10");
			String putstr = tableService.getJsonArrayData("eleasing/workflow/contract/fund_fund_charge_plan.xml",queryMainObjectMap).toString();
			List<FundPutPlan> fpps=ObjectConvertUtils.convertObjectByJson(FundPutPlan.class, putstr);
			cb.setFpps(fpps);
		}

		// 设置他的此时操作的,String doc_id
		cb.setDocId(doc_id);
		cb.setContractId(contract_id);

		cb.setModificator(SecurityUtil.getPrincipal());
		cb.setModifyDate(DateTools.getSystemDate(0));

		// 得到操作表信息,类似于合同的租金测算
		tcb = TbBeanTools.getTabInfo(calType, cb);// 然后用交易结构再次获取到完整的操作表信息
		// 为了后面的计算再转向
		cb = DictTools.getReversDict(cb);

		RentPlanContrCalDAOImpl rpccdi = new RentPlanContrCalDAOImpl();
		rpccdi.deleteRentPlan(tcb.getRentPlan_tb(), tcb, 1);// 删除
		
		frpb.setCorpusOverageBusinessList(TransRateHelper.getCorpusOvergeList(NumTools.getSumCorpusOverage(frpb.getCorpusBusinessList(), 1), frpb.getCorpusBusinessList()));
		// 重构本金余额集合
		frpb.setQuotId(contract_id);
		
		rpccdi.addRentPlan(tcb.getRentPlan_tb(), frpb, tcb, 1,cb);
		cb.setCustId(custId);
		Hashtable<String, Object> re_ht = new Hashtable<String, Object>();
		Boolean isAdust = calType.equals("cont_process") ? true : false;
		try {
			re_ht = RentCalculateHelper.createFundPlanCashIrr(cb, tcb, null, frpb,isAdust);
			re_ht.put("irr", cb.getIrr());
		} catch (Exception e) {
			logger.error("构建现金流或者计算IRR出错!");
			e.toString();
			e.printStackTrace();
			re_ht.put("isSucess", "false");
			re_ht.put("message", "构建现金流或者计算IRR出错!");
			return re_ht;
		}
		
		re_ht.put("isSucess", "true");
		logger.info("结束租金计划修改测算 ..");
		return re_ht;
	}
}
