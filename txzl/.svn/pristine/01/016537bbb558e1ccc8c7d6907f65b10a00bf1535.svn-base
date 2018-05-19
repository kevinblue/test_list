package com.tenwa.leasing.action.fund.fund_plancharg;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.reckon.service.RentConditionDataOperator;
import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.utils.WorkflowUtil;



@WorkflowAction(name = "fundPlanChargEndAction", description = "流程结束动作", workflowName = "资金计划变更")
@Component(value = "fundPlanChargEndAction")
public class FundPlanChargEndAction implements JbpmBaseAction {
	Logger logger = Logger.getLogger(FundPlanChargEndAction.class);
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "RentConditionDataService")
	private RentConditionDataOperator rentConditionData;
    
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

		
		//删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.baseService, variablesMap);  
		//获取合同Id
		String contractId = variablesMap.get("contract_info.id");
		//获取流水号
		String docId=jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+"";
		ContractInfo contractInfo = (ContractInfo)this.baseService.findEntityByID(ContractInfo.class, contractId); 
		//保存租金计划中的付款前提 
		this.contractCommService.saveProjPaymentPremiseCondition(contractInfo, variablesMap);
		
		//保存变列信息
		 this.rentConditionData.updateContractFundDataAndSaveDatatoHis(contractInfo,variablesMap, docId, "his_fund_plan_change", "json_plancharg_collect_str", "json_plancharg_pay_str");
	}
	 
	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
