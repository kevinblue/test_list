package com.tenwa.leasing.action.contract.approval;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfoCompany;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.contract.contractApproval.ContractApprovalService;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "contractApprovalAuditingSubmit", description = "流程动作", workflowName = "合同审批流程")
@Component(value = "contractApprovalAuditingSubmit")
public class ContractApprovalAuditingSubmit implements JbpmBaseAction {

	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String contract_info_leasform = variablesMap.get("contract_info.leasform");
		variablesMap.put("leasForm", contract_info_leasform);
		//创建法务审核提交生成的合同号
	   // contractCommService.createContractNo(variablesMap); 
	}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

		
	}

}
