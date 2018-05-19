package com.tenwa.leasing.action.factoring.factoring_reconsider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoCompany;
import com.tenwa.leasing.entity.cust.CustInfoPerson;
import com.tenwa.leasing.entity.proj.ProjDevelopInfo;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.Proj.proComm.ProCommService;
import com.tenwa.leasing.service.Proj.projCredit.ProjCreditService;
import com.tenwa.leasing.utils.WorkflowUtil;


@WorkflowAction(name = "factoringReconsiderStartAction", description = "流程开始动作", workflowName = "保理复议流程")
@Component(value = "factoringReconsiderStartAction")
public class  FactoringReconsiderStartAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "projCreditService")
	private ProjCreditService projCreditService;
	
	@Resource(name = "proCommService")
	private ProCommService proCommService;
	
	@Override
	public void start(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String proj_id = variablesMap.get("proj_id");
		//流程冲突
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, proj_id)); 
		variablesMap.put("docId", jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid() + "");
		projCreditService.getFactoringProjReconsiderInfo(variablesMap,jbpmWorkflowHistoryInfo);
	    //加载projinfo项目信息
		proCommService.loadProjInfo(variablesMap);
		//如果一个项目重复复议，再次复议时，这三个字段将被初始化为空。
		variablesMap.put("proj_info.projstatustype","");
		variablesMap.put("proj_info.approvedate","");
		variablesMap.put("proj_info.approveconclusion","");
	}

	@Override
	public void back(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 
		
	}

	@Override
	public void end(HttpServletRequest request,	Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 
		
	}

	@Override
	public String save(HttpServletRequest request,	Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	
		return null;
	}

	@Override
	public String delete(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//删除流程冲突第二步
		WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap); 
		return null;
	}

	
	
	
}
