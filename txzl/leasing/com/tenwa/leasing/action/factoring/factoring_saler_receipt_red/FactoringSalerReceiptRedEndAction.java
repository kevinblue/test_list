package com.tenwa.leasing.action.factoring.factoring_saler_receipt_red;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.action.fund.red_out.FundRedOutEndAction;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;
import com.tenwa.leasing.utils.WorkflowUtil;


@WorkflowAction(name = "factoringSalerReceiptRedEndAction", description = "流程结束动作", workflowName = "保理卖方收款红冲流程")
@Component(value = "factoringSalerReceiptRedEndAction")
public class FactoringSalerReceiptRedEndAction implements JbpmBaseAction {
	Logger logger = Logger.getLogger(FundRedOutEndAction.class);
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.baseService, variablesMap);  
		//保存实收红冲
		fundCommMethod.saveFundFundCharge("json_redout_current_str", variablesMap);
		//更新被红冲的资金收付
		fundCommMethod.saveFundRebackFlag("json_redout_current_str", variablesMap);
	}
	  
	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public void back(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// TODO Auto-generated method stub
		
	}
}