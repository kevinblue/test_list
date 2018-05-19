package com.tenwa.leasing.action.pledge;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.service.pledge.PledgeCommMethod;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "pledgeEndAction", description = "流程结束动作", workflowName = "抵质押注销")
@Component(value = "pledgeEndAction")
public class PledgeEndAction implements JbpmBaseAction {
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "pledgeCommMethod")
	private PledgeCommMethod pledgeCommMehtodService;
	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 
	}
	
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
			    //删除流程互斥
				WorkflowUtil.deleteWorkFlowConflict(this.baseService, variablesMap);  
				String doc_id = jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+"";
				variablesMap.put("docId", doc_id);
				String i=variablesMap.get("pledge.pledgetime");
				String e=variablesMap.get("pledge.pledgereason");
				
				System.out.println("------------------------------------------------------------------------------");
				System.out.println(i);
				System.out.println(e);
				System.out.println("------------------------------------------------------------------------------");
				
				//保pledge表中的数据更新为已注销的
				pledgeCommMehtodService.updatePledgestatus(variablesMap);
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
