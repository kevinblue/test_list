package com.tenwa.leasing.action.lawmng.law_cost;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.lawmng.LawCost;
import com.tenwa.leasing.utils.WorkflowUtil;
 
@WorkflowAction(name = "lawCostEndAction", description = "流程结束动作", workflowName =  "法务费用申请流程")
@Component(value = "lawCostEndAction")
public class LawCostEndAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;
	
	
	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 /* 保存法务信息 */ 
		ContractInfo contractInfo = this.tableService.findEntityByID(ContractInfo.class,variablesMap.get("contract_id"));
		String jsonLawcostString = variablesMap.get("json_law_cost_detail_str");
		this.tableService.updateOneToManyCollectionsNoRemoved(contractInfo, "lawCosts", LawCost.class, "contractId", jsonLawcostString,null);
	}
	
	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
	}
	
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap);  
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
