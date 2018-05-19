package com.tenwa.leasing.action.fund.fund_payment;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;
import com.tenwa.leasing.utils.WorkflowUtil;
@WorkflowAction(name = "NetSilverRefundStartAction", description = "流程开始动作", workflowName = "网银退还流程")
@Component(value = "NetSilverRefundStartAction")
public class NetSilverRefundStartAction implements JbpmBaseAction{
	Logger logger =Logger.getLogger(FundPaymentStartAction.class);
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
	@Override
	public void start(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		         String contractid = variablesMap.get("contract_id");
		        //流程冲突
				variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, contractid));
		        //获取网银信息
				FundEbankData fundEbank=this.fundCommMethod.initFundEbankInfo(variablesMap);
				//查入网银过程表
				this.fundCommMethod.saveEbankProcessFlow(contractid, fundEbank, variablesMap, jbpmWorkflowHistoryInfo);		
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
		         //删除流程互斥
				WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap); 
				 //删除网银过程表
			    fundCommMethod.deleteEbankProcessFlow(variablesMap);
		         return null;
	}

}
