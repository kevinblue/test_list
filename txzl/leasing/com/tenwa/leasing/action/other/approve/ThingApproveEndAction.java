package com.tenwa.leasing.action.other.approve;

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
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "thingApproveEndAction", description = "流程结束动作", 
 workflowName = "重大事项申请流程")
@Component(value = "thingApproveEndAction")
public class ThingApproveEndAction implements JbpmBaseAction {
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
		
		ThingDisposition thingDisposition = new ThingDisposition();

		this.baseService.copyAndOverrideExistedValueFromStringMap(variablesMap,
				thingDisposition,null,"thing_disposition");

		String dnum = WorkflowUtil.getCustConditionSerialNumber(baseService
				.getBussinessDao().getHibernateTemplate(), baseService
				.getBussinessDao().getJdbcTemplate());

		thingDisposition.setDnum(dnum);
		thingDisposition.setContractId(variablesMap.get("contract_id"));
		
		this.baseService.saveEntity(thingDisposition);
		
		
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
