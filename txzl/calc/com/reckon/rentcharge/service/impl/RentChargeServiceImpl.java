package com.reckon.rentcharge.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.reckon.bean.AdjustBean;
import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.TabCalBean;
import com.reckon.commons.helper.ObjectConvertUtils;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.commons.util.DateTools;
import com.reckon.dao.impl.AdjustDAOImpl;
import com.reckon.dao.impl.ConditionDAOImpl;
import com.reckon.dao.impl.RentChargeDAOImpl;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.entity.contract.reckon.condition.FundPutPlan;
import com.reckon.rentcalc.service.impl.evencorpus.EvenCorpusPlanContrCalServiceImpl;
import com.reckon.rentcalc.service.impl.eveninterest.EvenInterestPlanContrCalServiceImpl;
import com.reckon.rentcalc.service.impl.pub.RentPlanContrCalServiceImpl;
import com.reckon.rentcharge.service.RentChargeService;
import com.reckon.util.DictTools;
import com.reckon.util.TbBeanTools;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.utils.LeasingException;

/**
 * 
 * @author SCLICX
 * @version 1.0
 * @copyright (C) Tenwa 2011
 * @date Mar 28, 2011
 * @desc  ( 正常租金变更现实主体类,通过对conditionbean和adjustbean的处理再调用租金测算类完成变更)
 */
public class RentChargeServiceImpl implements RentChargeService {

	Logger logger = Logger.getLogger(RentChargeServiceImpl.class);

	public Hashtable<String ,Object> rentChargeCal(AdjustBean adb, String calType, Hashtable<String ,Object> ht, String[] rentAdjustList) throws Exception {
		String error = (String)ht.get("error");
		error = error == null ? "" : error;
		
		
		logger.debug("进入变更主体方法:rentChargeCal");
		// 保存AdjustBean
		RentChargeDAOImpl rcd = new RentChargeDAOImpl();
		try {
			rcd.deleteAjust(adb);
		} catch (Exception e) {
			ht.put("isSucess", "false");
			error += "删除fund_rent_adjust出错:" + e.toString();
			return ht;
		}
		try {
			int ini = 0;
			ini = rcd.addAjust(adb);
			if (ini == 0) {
				ht.put("isSucess", "false");
				error += "没有成功添加fund_rent_adjust记录!";
			}
		} catch (Exception e) {
			ht.put("isSucess", "false");
			// 添加捕获到的错误信息
			error += "添加fund_rent_adjust出错:" + e.toString();
			return ht;
		}
		// 获得condition表信息
		ConditionBean cdb = new ConditionBean();// 交易结构表
		TabCalBean tcb = TbBeanTools.getTabInfo(calType, adb);
		String wheresql = tcb.getCondition_tb() + " where " + tcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "' and doc_id='" + tcb.getDocId() + "'";
		if (adb.getOnHireId() != null && !adb.getOnHireId().equals("")) {
			wheresql += " and onHire_id='" + adb.getOnHireId() + "'";
		}
		//( 根据查取交易结构信息)
		ConditionDAOImpl cddao = new ConditionDAOImpl();
		//logger.info("( 根据查取交易结构信息):"+wheresql);
		cdb = cddao.getConditionBeanByContract(wheresql, tcb);
//		Map<String, Object> paramsMap = new HashMap<String, Object>();
//		paramsMap.putAll(modelData);
//		ConditionBean cb = ObjectConvertUtils.convertMapToBean(ConditionBean.class, paramsMap);
		//投放计划
		TableService tableService =(TableService) WebUtil.getApplicationContext().getBean("tableService");
		Map<String, String> queryMainObjectMap = new HashMap<String, String>();
		queryMainObjectMap.put("contract_id", adb.getContractInfoId());
		queryMainObjectMap.put("feetypein", "feetype10");
		String putstr = tableService.getJsonArrayData("eleasing/workflow/contract/fund_fund_charge_plan.xml",queryMainObjectMap).toString();
		List<FundPutPlan> fpps=ObjectConvertUtils.convertObjectByJson(FundPutPlan.class, putstr);
		//List<SpecialRulesBean> srbs=ObjectConvertUtils.convertObjectByJson(SpecialRulesBean.class, modelData.get("json_special_regular_str"));
		cdb.setFpps(fpps);
		
		// 宽限期 要临时存来下 在后续的变更中暂时无法确定是否会修改 且该值用于后续的计算还款次数
		int grace = cdb.getGrace();
		// 租金计划变更 或者提前结清不平移租金计划日期 2012-5-7
		//cdb.setChangeDate(false);
		
		// 转换为测算可用bean
		cdb = DictTools.getReversDict(cdb);
		String startDate = cdb.getStartDate();
		// 获得租金计划表信息
		FundRentPlanBean frpb = new RentPlanContrCalDAOImpl().getRentAndDateByTcb(tcb, 1);

		// 设置frpb信息
		frpb.setModificator(adb.getCreator());
		frpb.setModifyDate(DateTools.formatToDateTime(new Date()));
		frpb.setYearRate(adb.getYearRate().toString());
		// 将year_rate_list更新 只要移除保存时会自动更新
		frpb.setYearRateList(null);
		// 调整时 要把租金调整列清空
		frpb.setRentAdjustList(null);

		try {
			// 根据变更表信息获取condition表信息
			// 根据变更条件，创建一个新的用于测算的ConditionBean，
			// 其实就是创建一个从调整期开始重新测算所需要的bean
			//备份start_date，为资金计划的重新生成做备份
			cdb = AdjustDAOImpl.getConditionBeanByAdjustBean(cdb, adb, frpb);
		} catch (Exception e) {
			e.printStackTrace();
			error += "根据变更表信息获取condition表信息失败:" + e.toString();
			ht.put("isSucess", "false");
			ht.put("error", error);
			return ht;
		}
		String newStartDate = cdb.getStartDate();
		cdb.setFirstPlanDate(adb.getPaydayAdjust());// 修改第一期租金支付日为起租日
		logger.debug("流程号1:"+cdb.getDocId());
		String oldRentOrRate = cdb.getRentOrRate();
		cdb.setRentOrRate("rate");
		try {
			// 调用租金测算的构建租金计划方法
			RentPlanContrCalServiceImpl rpcs = null;
			// 等额租金法
			if (	"even_rent".equals(cdb.getSettleMethod()) ||
					"irregular_rent".equals(cdb.getSettleMethod())||
					"special_regular".equals(cdb.getSettleMethod())||
					"excel_import".equals(cdb.getSettleMethod())) {
				// 为了让等额租金中覆盖这两个值所以要滞空
				frpb.setColumn_1(null);
				frpb.setColumn_2(null);
				rpcs = new RentPlanContrCalServiceImpl();
			} else if ("even_interest".equals(cdb.getSettleMethod())) {// 均息法
				rpcs = new EvenInterestPlanContrCalServiceImpl();
			} else if ("even_corpus".equals(cdb.getSettleMethod())) {// 等额本金
				rpcs = new EvenCorpusPlanContrCalServiceImpl();
			}
			frpb = rpcs.rentPlanCalByConditionAndTab(cdb, tcb, adb.getStartList(), frpb);
		} catch (Exception e) {
			e.printStackTrace();
			ht.put("isSucess", "false");
			error += "处理租金计划失败:" + e.toString();
			ht.put("error", error);
			return ht;
		}
		//现金流处理
		try {
			//根据新生成的租金计划生成新的资金
			cdb.setContractId(adb.getContractInfoId());
			cdb.setStartDate(startDate);
			ht = RentCalculateHelper.createFundPlanCashIrr(cdb, tcb, null, frpb,true);
			ht.put("irr",cdb.getIrr());
		} catch (LeasingException e) {
			ht.put("isSucess", "false");
			error += e.getMessage();
			ht.put("error", error);
			return ht;
		} catch (Exception e) {
			e.printStackTrace();
			ht.put("isSucess", "false");
			error += "处理会计租金计划或者现金流失败:" + e.toString();
			ht.put("error", error);
			return ht;
		}
		// 转换为保存bean并保存
		try {
			// 还原数据字典
			cdb.setStartDate(newStartDate);
			cdb.setRentOrRate(oldRentOrRate);
			cdb.setIncomeNumber(adb.getAdjustList() - grace);// 还款次数要减去宽限期
			cdb.setLeaseTerm(adb.getAdjustList() * (12 / Integer.parseInt(cdb.getIncomeNumberYear())));
			cdb.setYearRate(adb.getYearRate().toString());
			cdb = DictTools.getPersiDict(cdb);
			cddao.updateConditionIncomeNum(tcb, cdb);// 更新
			// [KEY:scl-A]2012-12-6 租金计划变更的时候单独修改最后一期租金支付日
			if (!adb.getAdjustType().equals("his_contract_end")) {// [KEY:scl]2013-1-8合同中途终止不更新最后一期租金支付日
				new RentPlanContrCalDAOImpl().updateEndPlanDate(tcb);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ht.put("isSucess", "false");
			error += "转换为保存bean并先删后存失败:" + e.toString();
			ht.put("error", error);
			return ht;
		}
		ht.put("isSucess", "true");
		return ht;
	}
}
