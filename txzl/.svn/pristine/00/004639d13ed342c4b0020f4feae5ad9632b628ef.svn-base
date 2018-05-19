package com.tenwa.leasing.action.contract.contract_insurance;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.service.contract.contractChange.ContractChangeService;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "contractInsuranceEndAction", description = "流程结束动作", workflowName = "保险用印申请")
@Component(value = "contractInsuranceEndAction")
public class ContractInsuranceEndAction implements JbpmBaseAction {

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
		String contract_id = variablesMap.get("contract_info.id");
		ContractInfo contract = this.baseService.findEntityByID(ContractInfo.class, contract_id);
		contract.setIsInsurance("1");
	}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

}
