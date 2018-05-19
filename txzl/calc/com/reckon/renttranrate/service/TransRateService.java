package com.reckon.renttranrate.service;

import java.util.List;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.InterContBean;
import com.reckon.bean.SpecialRuleBean;
import com.reckon.entity.contract.reckon.cash.CashHelp;
import com.reckon.entity.contract.reckon.cash.ContractCashDetail;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;


/**
 * @author MHY QQ:648020894
 */
public interface TransRateService {

	/**
	 * 调息处理方法
	 * 
	 * @param cb 租金计划的商务条件
	 * @param ol dRentPlanContext 需要调息的租金计划
	 * @param icb 调息上下文信息
	 * @param newDate 
	 * @param srb 
	 * @param fundPlanBeans 
	 * @return
	 * @throws Exception
	 */
	public void processPmtTranRate(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb, List<SpecialRuleBean> srb) throws Exception;


}
