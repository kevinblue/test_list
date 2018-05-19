package com.tenwa.leasing.action.factoring.contract.change;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "factoringContractChangeStartAction", description = "流程开始动作", workflowName = "保理合同变更")
@Component(value = "factoringContractChangeStartAction")
public class FactoringContractChangeStartAction implements JbpmBaseAction {
	
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//删除流程互斥
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
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String contract_id = variablesMap.get("contract_id");
		String flowunid=jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+ "";
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, contract_id)); 
		ContractInfo contractInfo=this.contractCommService.loadContractInfo( variablesMap);
		if(contractInfo.getProjId()!=null){
			variablesMap.put("projidforend", contractInfo.getProjId().getId().toString());
		}
		CustInfo customerInfo=contractInfo.getCustId();
		this.contractCommService.loadContractEquipTmp(contractInfo, variablesMap,flowunid);//租赁物件明细
		this.contractCommService.loadFactoringContractEquip(contractInfo, variablesMap);//发票信息
		
		this.contractCommService.loadContractGuaranteeMethodTmp(contractInfo, variablesMap,flowunid); //担保单位信息
		this.contractCommService.loadContractGuaranteeEquipTmp(contractInfo, variablesMap,flowunid);//抵押物信息
		this.contractCommService.loadContractNumSet(contractInfo, variablesMap,flowunid);//合同编号设置
		this.contractCommService.loadContractInvoice(contractInfo, variablesMap);//租金开票类型
		contractCommService.loadContractRentCalculationParam(contract_id, customerInfo.getCustName(), customerInfo.getId(), flowunid,"cont_process", variablesMap);
		//商务条件，租金计划，资金计划，现金流
		this.contractCommService.loadContractRentCalculationFromBefore(contractInfo, variablesMap);
		//合同贸易基础交易情况
		this.contractCommService.loadTradeTransaction(contractInfo, variablesMap);
		//合同各方
		this.contractCommService.loadContractSignatoryInfo(contractInfo, variablesMap);
		//供应商信息
		this.contractCommService.loadContractSupplierInfo(contractInfo, variablesMap);
		//加载合同文件
		this.contractCommService.loadFile("json_contract_word_str", "合同管理", contract_id, variablesMap);
		//加载付款前提
		this.contractCommService.loadContractPremise(contractInfo, variablesMap);
		//加载租金计划中付款前提条件
	   this.contractCommService.loadContractPaymentPremiseCondition(contractInfo, variablesMap);

		
	}
}
