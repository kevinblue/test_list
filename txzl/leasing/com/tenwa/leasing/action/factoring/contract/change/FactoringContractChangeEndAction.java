package com.tenwa.leasing.action.factoring.contract.change;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.service.contract.contractChange.ContractChangeService;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "factoringContractChangeEndAction", description = "流程结束动作", workflowName = "保理合同变更")
@Component(value = "factoringContractChangeEndAction")
public class FactoringContractChangeEndAction implements JbpmBaseAction {

	@Resource(name = "baseService")
	private BaseService baseService;
	
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
		this.contractChangeService.saveFactoringContractChangeInfo(variablesMap);
	}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

}
