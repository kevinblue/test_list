package com.tenwa.leasing.action.fund.fund_collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;



@WorkflowAction(name = "fundCollectionEditAction", description = "第一步保存网银信息", workflowName = "收款流程")
@Component(value = "fundCollectionEditAction")
public class FundCollectionEditAction implements JbpmBaseAction {
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
		
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@SuppressWarnings("unused")
	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
        //保存第次占用网银的金额 
		List<String> fields=new ArrayList<String>();
		fields.add("factmoney");
		fundCommMethod.updateEbankProcessFlow("json_collection_current_str", fields, variablesMap);
	}
	   
	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
