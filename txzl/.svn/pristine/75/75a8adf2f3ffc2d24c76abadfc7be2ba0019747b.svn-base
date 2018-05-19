package com.tenwa.leasing.action.seal_registration;
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
import com.tenwa.leasing.service.Proj.proComm.ProCommService;
import com.tenwa.leasing.utils.WorkflowUtil;

/**
 * 
 * @author rovine
 * @date 2014-8-18
 * @info项目立项的Action
 * @Copyright 
 * Tenwa
 */
@WorkflowAction(name = "sealRegistrationStartAction", description = "流程开始动作", workflowName = "公章使用登记")
@Component(value = "sealRegistrationStartAction")
public class SealRegistrationStartAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "proCommService")
	private ProCommService proCommService;
	
	
	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		/*公章编号 生成*/
		String flowNumber = WorkflowUtil.getOfficialSerialNumber(null,tableService.getBussinessDao().getHibernateTemplate()
				, tableService.getBussinessDao().getJdbcTemplate());
		variablesMap.put("seal_registration.flownumber",flowNumber);
		variablesMap.put("seal_registration.registrationdate",DateUtil.getSystemDate());
	
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
