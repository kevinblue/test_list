package com.tenwa.leasing.action.proj.proj_cancel;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.reckon.service.RentConditionDataOperator;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.Proj.proComm.ProCommService;
import com.tenwa.leasing.utils.WorkflowUtil;

/**
 * 
 * @author rovine
 * @date 2014-8-18
 * @info项目撤销的Action
 * @Copyright 
 * Tenwa
 */
@WorkflowAction(name = "projCancelStartAction", description = "流程开始动作", workflowName = "项目撤销")
@Component(value = "projCancelStartAction")
public class ProjCancelStartAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "proCommService")
	private ProCommService proCommService;
	
	@Resource(name = "RentConditionDataService")
	private RentConditionDataOperator rentConditionData;
	
	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		  
		String proj_id = variablesMap.get("proj_id");
		//流程互斥操作
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, proj_id)); 
		ProjInfo projInfo =proCommService.loadProjInfo(variablesMap);
		proCommService.loadProjEquipment(projInfo, variablesMap);
		proCommService.loadProjRentCalculation(projInfo, variablesMap);
		proCommService.loadProjGuaranteeMethod(projInfo, variablesMap);
		proCommService.loadProjGuaranteeEquipment(projInfo, variablesMap);
		String flowunid=jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+ "";
		proCommService.loadRentCalculationParam(proj_id, projInfo.getCustInfo().getCustName(), projInfo.getCustInfo().getId(), flowunid, variablesMap);
		proCommService.loadProjInvoice(projInfo, variablesMap);
	}
	
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap);
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}
	
	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}
}
