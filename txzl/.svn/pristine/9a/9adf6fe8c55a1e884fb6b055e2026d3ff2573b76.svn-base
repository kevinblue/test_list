package com.tenwa.leasing.action.contract.conferDecision;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.Proj.proComm.ProCommService;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.service.contractcomm.ContractInfoExtends;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "conferDecisionStartAction", description = "流程开始动作", workflowName = "总经理办公会议案")
@Component(value = "conferDecisionStartAction")
public class ConferDecisionStartAction implements JbpmBaseAction {
	
	@Resource(name = "tableService")
	private TableService tableService;	
	
	
	@Resource(name = "contractInfoExtends")
	private ContractInfoExtends contractInfoExtends;
	
	@Resource(name = "proCommService")
	private ProCommService proCommService;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap);  
		return null;
	} 
	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}
    
	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//String proj_id = variablesMap.get("projid");
		//流程冲突
		//variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, proj_id)); 
		String docId = jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid() + "";
		variablesMap.put("docId", docId);
		ProjInfo projInfo =proCommService.loadProjInfo(variablesMap);
		variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(projInfo,null, "proj_info"));
		//String  contractid = variablesMap.get("contract_id");
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		String loginuserid=model.get("login_userid");
		System.out.println("=============================="+loginuserid);
		User u=this.tableService.findEntityByID(User.class, loginuserid);
		System.out.println("==============================="+u.getRealname());
		variablesMap.put("conference_decision.originator",u.getRealname());
		
		String time = DateUtil.getSystemDate();
		variablesMap.put("conference_decision.originatime",time);
		
		String confer_id = WorkflowUtil.getConferDecisionSerialNumber(variablesMap,this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
		variablesMap.put("conference_decision.confernumber",confer_id);
	}
}


