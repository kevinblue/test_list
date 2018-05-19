package com.tenwa.leasing.action.lawmng.law_change;
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
import com.tenwa.leasing.entity.lawmng.LawApproval;
 
@WorkflowAction(name = "lawChangeEndAction", description = "流程结束动作", workflowName =  "法务变更流程")
@Component(value = "lawChangeEndAction")
public class LawChangeEndAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 /* 保存法务信息 */ 
		 Map<String, Object> propertiesMap = new HashMap<String,Object>();
		 propertiesMap.put("lawnum", variablesMap.get("law_approval.lawnum"));
		 LawApproval lawApproval = this.tableService.findEntityByProperties(LawApproval.class, propertiesMap).get(0);
		 this.tableService.copyAndOverrideExistedValueFromStringMap(variablesMap, lawApproval,null,"law_approval");
		 String contract_id =variablesMap.get("contract_id");
		 
		 ContractInfo contractInfo = this.tableService.findEntityByID(ContractInfo.class, contract_id);
		 lawApproval.setContractInfo(contractInfo);
		 this.tableService.updateEntity(lawApproval);
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
