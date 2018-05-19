package com.tenwa.leasing.action.contract.finish;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.service.contractcomm.ContractInfoExtends;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;
import com.tenwa.leasing.utils.WorkflowUtil;

/**
 * 
 * @author 李超杰
 * @date 2013-8-7
 * @info合同结束的Action
 * @Copyright Tenwa
 */
@WorkflowAction(name = "contractFinishStartAction", description = "流程开始动作", workflowName = "合同结束流程")
@Component(value = "contractFinishStartAction")
public class ContractFinishStartAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String contract_id = variablesMap.get("contract_id");
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, contract_id)); 
		ContractInfo contractInfo=this.contractCommService.loadContractInfo( variablesMap);
		CustInfo custInfo=contractInfo.getCustId();
		
		Map<String, String> queryMainObjectMap = new HashMap<String, String>();
		queryMainObjectMap.put("contractid", contract_id);		
		
		//租赁物明细
		this.fundCommMethod.initFundEquipDetail("json_contract_equip_detail_str", variablesMap, queryMainObjectMap);
		
		//资金计划
		this.fundCommMethod.initFundFundPlan("json_fund_payment_plan_info_str", variablesMap, queryMainObjectMap);
	    //资金首付款历史记录---在合同中途结束流程中
		this.fundCommMethod.initFundFundCharge("json_fund_charge_his_info_str", variablesMap, queryMainObjectMap); 
	    //资金收款历史    json_contract_fund_payin
		queryMainObjectMap.put("paytypename", "收款");
		this.fundCommMethod.initFundFundCharge("json_contract_fund_payin_str", variablesMap, queryMainObjectMap); 
	    //资金付款历史      json_contract_fund_payout_str  paytypename
	    queryMainObjectMap.put("paytypename", "付款");
	    this.fundCommMethod.initFundFundCharge("json_contract_fund_payout_str", variablesMap, queryMainObjectMap);  
		queryMainObjectMap.clear();		
		//租金回笼计划
		queryMainObjectMap.put("contract_id", contract_id);
		queryMainObjectMap.put("plan_date",DateUtil.getSystemDate());
		this.fundCommMethod.initFundRentPlan("json_rent_income_plan_str", variablesMap, queryMainObjectMap);
		//租金回笼历史
		queryMainObjectMap.put("orderbyclause", "order by income.planlist asc");
		this.fundCommMethod.initFundRentIncome("json_rent_income_his_str", variablesMap, queryMainObjectMap);
	}

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap); 
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

		return null;
	}

	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}
}
