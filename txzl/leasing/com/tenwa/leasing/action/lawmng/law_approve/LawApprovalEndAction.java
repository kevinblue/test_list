package com.tenwa.leasing.action.lawmng.law_approve;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.lawmng.LawApproval;
 
@WorkflowAction(name = "lawApprovalEndAction", description = "流程结束动作", workflowName =  "法务申请流程")
@Component(value = "lawApprovalEndAction")
public class LawApprovalEndAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 /* 保存法务信息 */ 
		 LawApproval lawApproval = new LawApproval();
		 this.tableService.copyAndOverrideExistedValueFromStringMap(variablesMap, lawApproval,null,"law_approval");
		 String contract_id =variablesMap.get("contract_id");	 
		 ContractInfo contractInfo = this.tableService.findEntityByID(ContractInfo.class, contract_id);
		 lawApproval.setContractInfo(contractInfo);
		 this.tableService.saveEntity(lawApproval);
	}
	
	
	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}
	
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}
	
	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}
}
