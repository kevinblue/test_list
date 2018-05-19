package com.tenwa.leasing.action.proj.proj_cancel_stop;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.service.Proj.projCancel.ProjCancelService;
import com.tenwa.leasing.utils.WorkflowUtil;

/**
 * 
 * @author admin
 * @date 2016-7-1
 * @info项目执行中止流程的Action
 * @Copyright 
 * Tenwa
 */
@WorkflowAction(name = "projCancelStopEndAction", description = "流程结束动作", workflowName = "项目执行中止流程")
@Component(value = "projCancelStopEndAction")
public class ProjCancelStopEndAction implements JbpmBaseAction {
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "projCancelService")
	private ProjCancelService projCancelService;
	
	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
	}
	
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//String proj_id = variablesMap.get("proj_id");// 先获得proj_id
		//删除流程冲突 
		 
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 //删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.baseService, variablesMap);
		//projCancelService.saveProjectInfo(variablesMap);
		
		projCancelService.saveProjectInfoStop(variablesMap);
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
