package com.tenwa.leasing.action.pledge;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.service.contractcomm.ContractInfoExtends;
import com.tenwa.leasing.service.pledge.PledgeCommMethod;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "pledgeStartAction", description = "流程开始动作", workflowName = "抵质押注销")
@Component(value = "pledgeStartAction")
public class PledgeStartAction implements JbpmBaseAction {
	
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "pledgeCommMethod")
	private PledgeCommMethod pledgeCommMehtodService;
	
	
	@Resource(name = "contractInfoExtends")
	private ContractInfoExtends contractInfoExtends;
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
	
		String  contractid = variablesMap.get("contract_id");
		//流程冲突
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, contractid)); 
		//variablesMap.put("docId", jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid() + "");
		
	    ContractInfo contractinfo = this.tableService.findEntityByID(ContractInfo.class, contractid);
		//1将合同基本信息放入variablesMap
		contractInfoExtends.getContractBaseInfo(variablesMap, contractinfo, contractinfo.getCustId());
		
	    //2加载pledge未注销和已注销数据
		pledgeCommMehtodService.loadPledge(contractid, variablesMap);
	}
}


