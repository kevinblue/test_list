package com.tenwa.leasing.action.contract.discount;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.reckon.entity.contract.reckon.condition.ContractCondition;
import com.reckon.entity.proj.ProjCashDetail;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.fund.FinanceCashDetail;
import com.tenwa.leasing.entity.fund.FinanceFundFundPlan;
import com.tenwa.leasing.entity.fund.FinanceFundFundPlanTemp;
import com.tenwa.leasing.entity.fund.FinanceFundRentPlan;
import com.tenwa.leasing.entity.fund.FinanceIncomeDiscount;
import com.tenwa.leasing.service.contract.contractChange.ContractChangeService;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "incomeDiscountEndAction", description = "流程结束动作", workflowName = "收入折现流程")
@Component(value = "incomeDiscountEndAction")
public class IncomeDiscountEndAction implements JbpmBaseAction {

	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name = "contractChangeService")
	private ContractChangeService contractChangeService;
	
	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	
		String doc_id = jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+"";
		variablesMap.put("docId", doc_id);
		WorkflowUtil.deleteWorkFlowConflict(this.baseService, variablesMap);
		//保存contract_condition；现金流、租金计划、资金收付计划、收入折现
		//this.contractChangeService.saveContractChangeInfo(variablesMap);
		
		String contractId = variablesMap.get("contract_id");
		ContractInfo contractinfo=this.tableService.findEntityByID(ContractInfo.class,contractId );
		ContractCondition contractCondition = contractinfo.getContractCondition();
		
		//保存contract_condition
		//json_fund_rent_plan_str", "json_fund_cash_flow_str", "json_finance_rent_plan_str", "json_finance_cash_flow_str", "json_fund_fund_charge_str","json_fund_put_config_str","json_special_regular_str","json_grace_plan_str");
		contractCondition.setFinaLeaseAmtDate(variablesMap.get("finaleaseamtdate"));
		String finairr=variablesMap.get("finairr");
		String taxratio=variablesMap.get("taxratio");
		contractCondition.setTaxRatio(new BigDecimal(taxratio==null||taxratio==""?"0":taxratio));
		contractCondition.setFinaIrr(new BigDecimal(finairr==null||finairr==""?"0":finairr));
		this.tableService.saveEntity(contractCondition);
		//保存租金计划、资金收付计划、现金流、收入折现入正式库
		String json_fund_fund_charge_str  = variablesMap.get("json_fund_fund_charge_str");
		String json_fund_rent_plan_str = variablesMap.get("json_fund_rent_plan_str");
		String json_fund_cash_flow_str  = variablesMap.get("json_fund_cash_flow_str");
		String json_income_discount_str = variablesMap.get("json_income_discount_str");
		this.tableService.updateOneToManyCollections(contractinfo,"financeFundFundPlans", FinanceFundFundPlan.class, "contractId",json_fund_fund_charge_str, null);
		this.tableService.updateOneToManyCollections(contractinfo,"financeFundrentPlans", FinanceFundRentPlan.class, "contractId",json_fund_rent_plan_str, null);
		this.tableService.updateOneToManyCollections(contractinfo,"financeCashDetail", FinanceCashDetail.class, "contractId",json_fund_cash_flow_str, null);
		this.tableService.updateOneToManyCollections(contractinfo,"financeIncomeDiscount", FinanceIncomeDiscount.class, "contractId",json_income_discount_str, null);
	}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

}
