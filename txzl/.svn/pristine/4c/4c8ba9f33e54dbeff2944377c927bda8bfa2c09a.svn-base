package com.tenwa.leasing.action.contract.taxchange;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.contract.ContractInvoiceType;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.utils.WorkflowUtil;

/**
 * @author admin
 * @date 2016-8-1
 * @info
 */
@WorkflowAction(name = "contractTaxChangeStartAction", description = "流程开始动作", workflowName = "开票信息变更")
@Component(value = "contractTaxChangeStartAction")
public class ContractTaxChangeStartAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;
		
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	

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
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String contract_id = variablesMap.get("contract_id");
		String cid = variablesMap.get("cid");
		ContractInfo conf=this.tableService.findEntityByID(ContractInfo.class, contract_id);
		ContractInvoiceType invoice=this.tableService.findEntityByID(ContractInvoiceType.class, cid);
		//流程冲突
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, contract_id)); 
		//加载合同信息
		contractCommService.loadTaxContractInfo(conf, variablesMap);
		//加载开票信息
		contractCommService.loadTaxContractInvoiceType(invoice, variablesMap);

	}
	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 

	}
}
