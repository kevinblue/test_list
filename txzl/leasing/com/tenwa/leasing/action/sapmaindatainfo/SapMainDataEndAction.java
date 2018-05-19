package com.tenwa.leasing.action.sapmaindatainfo;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.service.sapmaindata.SapMainDataService;

@WorkflowAction(name = "sapMainDataEndAction", description = "流程结束动作", workflowName = "SAP主数据流程")
@Component(value = "sapMainDataEndAction")
public class SapMainDataEndAction implements JbpmBaseAction{
	
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "sapMainDataService")
	private SapMainDataService sapMainDataService;
	
	@Override
	public void start(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void end(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		this.sapMainDataService.saveSapMainDataInfo(variablesMap);
	}

	@Override
	public String save(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
