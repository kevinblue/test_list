package com.tenwa.leasing.action.lawmng.drawal_cost;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.filingmng.LawFilingNotice;
import com.tenwa.leasing.entity.lawmng.LawApproval;
import com.tenwa.leasing.entity.lawmng.LawCost;
import com.tenwa.leasing.service.filing.LawFilingNoticeService;
import com.tenwa.leasing.utils.WorkflowUtil;
 
@WorkflowAction(name = "drawalCostEndAction", description = "流程结束动作", workflowName =  "撤销诉讼")
@Component(value = "drawalCostEndAction")
public class DrawalCostEndAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name="lawFilingNoticeService")
	private LawFilingNoticeService lawFilingNoticeService;
	
	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 /* 保存立案情况信息 */ 
		lawFilingNoticeService.updateFilingInfo(variablesMap);
		
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
