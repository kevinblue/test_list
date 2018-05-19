package com.tenwa.leasing.action.contract.onhirechange;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.service.contract.onhireContractChange.OnhireContractChangeService;
import com.tenwa.leasing.utils.WorkflowUtil;



/**
 * @author Jason
 * @date 2013-4-24
 * @info 
 */
@WorkflowAction(name = "contractOnhireChangeEndAction", description = "流程结束动作", workflowName = "起租后合同变更")
@Component(value = "contractOnhireChangeEndAction")
public class ContractOnhireChangeEndAction implements JbpmBaseAction {
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "onhireContractChangeService")
	private OnhireContractChangeService onhireContractChangeService;
	
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.baseService, variablesMap);  
		//获取参数
		String doc_id = jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+"";
		variablesMap.put("docId", doc_id);
		this.onhireContractChangeService.saveOnhireContractChangeInfo(variablesMap);
	}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}	
	@Override
	public void back(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
		
	}

}
