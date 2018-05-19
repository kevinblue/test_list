package com.reckon.commons.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.reckon.commons.base.CashDetail;
import com.reckon.commons.base.FundPlan;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.util.DateTools;
import com.reckon.commons.util.UUIDTools;

public class CashDetailCalculator {


	/**
	 * 根据租金计划和资金收付计划创建现金流
	 * 
	 * @param rentPlanList
	 * @param fundPlanList
	 * @return
	 * @throws Exception
	 */
	public static <R extends RentPlan, F extends FundPlan, C extends CashDetail> List<C> createCashFlowList(List<F> fundPlanList, List<R> rentPlanList, Class<C> clazs) throws Exception {
		// 重组现金流开始第一步，把租金计划当成默认的现金流，后边把资金收付计划合并进来
		Map<String, C> cashFlowMap = new HashMap<String, C>();
		for (RentPlan rentPlan : rentPlanList) {
			Date planDate = DateTools.ymd.parse(rentPlan.getPlanDate());
			String cashFlowMonth = DateTools.ym.format(planDate);
			C cashFlow = cashFlowMap.get(cashFlowMonth);
			if(cashFlow == null){
				cashFlow = clazs.newInstance();
				cashFlow.setId(UUIDTools.getUUID());
				cashFlow.setDocId(rentPlan.getDocId());
				cashFlow.setCustId(rentPlan.getCustId());
				cashFlow.setProjId(rentPlan.getProjId());
				cashFlow.setContractId(rentPlan.getContractId());
				cashFlow.setEquipId(rentPlan.getEquipId());
				cashFlow.setConditionId(rentPlan.getConditionId());
				cashFlow.setPlanDate(cashFlowMonth);
				cashFlow.setFundIn(BigDecimal.ZERO);
				cashFlow.setFundInDetails("");
				cashFlow.setFundOut(BigDecimal.ZERO);
				cashFlow.setFundOutDetails("");
				cashFlowMap.put(cashFlowMonth, cashFlow);
			}
			cashFlow.setFundIn(cashFlow.getFundIn().add(rentPlan.getRent()));
			cashFlow.setFundInDetails(cashFlow.getFundInDetails() + "租金:" + rentPlan.getRent() + ";");
			cashFlow.setNetFlow(cashFlow.getFundIn().subtract(cashFlow.getFundOut()));
		}

		// 第二部：合并资金收付计划到现金流中。两层循环，确定资金收付计划在现金流中的位置
		for (FundPlan fundPlan : fundPlanList) {
			Date planDate = DateTools.ymd.parse(fundPlan.getPlanDate());
			String cashFlowMonth = DateTools.ym.format(planDate);
			C cashFlow = cashFlowMap.get(cashFlowMonth);
			if (cashFlow == null) {
				cashFlow = clazs.newInstance();
				cashFlow.setId(UUIDTools.getUUID());
				cashFlow.setDocId(fundPlan.getDocId());
				cashFlow.setCustId(fundPlan.getCustId());
				cashFlow.setProjId(fundPlan.getProjId());
				cashFlow.setContractId(fundPlan.getContractId());
				cashFlow.setEquipId(fundPlan.getEquipId());
				cashFlow.setConditionId(fundPlan.getConditionId());
				cashFlow.setPlanDate(cashFlowMonth);
				cashFlow.setFundIn(BigDecimal.ZERO);
				cashFlow.setFundInDetails("");
				cashFlow.setFundOut(BigDecimal.ZERO);
				cashFlow.setFundOutDetails("");
				cashFlowMap.put(cashFlowMonth, cashFlow);
			}
			if ("pay_type_in".equalsIgnoreCase(fundPlan.getPayType().toString())) {
				cashFlow.setFundIn(cashFlow.getFundIn().add(fundPlan.getPlanMoney()));
				cashFlow.setFundInDetails(cashFlow.getFundInDetails() + fundPlan.getFeeType().getName() + ":" + fundPlan.getPlanMoney() + ";");
			}
			if ("pay_type_out".equalsIgnoreCase(fundPlan.getPayType().toString())) {
				cashFlow.setFundOut(cashFlow.getFundOut().add(fundPlan.getPlanMoney()));
				cashFlow.setFundOutDetails(cashFlow.getFundOutDetails() + fundPlan.getFeeType().getName() + ":" + fundPlan.getPlanMoney() + ";");
			}
			cashFlow.setNetFlow(cashFlow.getFundIn().subtract(cashFlow.getFundOut()));
		}
		List<C> cashFlowNew = new ArrayList<C>(cashFlowMap.values());
		Collections.sort(cashFlowNew);
		return cashFlowNew;
	}
}
