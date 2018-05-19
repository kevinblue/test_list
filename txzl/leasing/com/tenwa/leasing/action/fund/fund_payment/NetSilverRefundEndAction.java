package com.tenwa.leasing.action.fund.fund_payment;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.service.ebank.NetSilverService;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "NetSilverRefundEndAction", description = "流程结束动作", workflowName = "网银退还流程")
@Component(value = "NetSilverRefundEndAction")
public class NetSilverRefundEndAction implements JbpmBaseAction{
	
	@Resource(name = "NetSilverService")
	private NetSilverService NetSilverService;
	
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
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

		
	}

	@Override
	public void end(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		       //删除流程互斥
				WorkflowUtil.deleteWorkFlowConflict(this.baseService, variablesMap);  
		        NetSilverService.saveNetSilverRefund(request,variablesMap);
	}

	@Override
	public String save(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

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
