package com.tenwa.leasing.action.other.follow;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.other.ThingDisposition;
import com.tenwa.leasing.service.Proj.projApproval.ProjApprovalService;

@WorkflowAction(name = "thingFollowEndAction", description = "流程结束动作", 
 workflowName = "重大事项跟踪流程")
@Component(value = "thingFollowEndAction")
public class ThingFollowEndAction implements JbpmBaseAction {
	@Resource(name = "baseService")
	private BaseService baseService;

	@Resource(name = "projApprovalService")
	private ProjApprovalService projApprovalService;

	@Override
	public void start(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String delete(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
		Map<String, Object> propertiesMap = new HashMap<String,Object>();
		propertiesMap.put("dnum", variablesMap.get("dnum"));
		
		ThingDisposition thingDisposition = this.baseService.findEntityByProperties(ThingDisposition.class, propertiesMap).get(0);

		this.baseService.copyAndOverrideExistedValueFromStringMap(variablesMap,
				thingDisposition,null,"thing_disposition");

		this.baseService.updateEntity(thingDisposition);
		
		
	}

	@Override
	public String save(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}
}
