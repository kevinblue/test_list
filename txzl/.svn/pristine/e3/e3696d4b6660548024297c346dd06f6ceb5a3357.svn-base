package com.tenwa.leasing.action.factoring.factoring_saler_receipt_red;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.action.fund.red_out.FundRedOutStartAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.service.contractcomm.ContractInfoExtends;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;
import com.tenwa.leasing.utils.WorkflowUtil;


@WorkflowAction(name = "factoringSalerReceiptRedStartAction", description = "流程开始动作", workflowName = "保理卖方收款红冲流程")
@Component(value = "factoringSalerReceiptRedStartAction")
public class FactoringSalerReceiptRedStartAction implements JbpmBaseAction {
	Logger logger =Logger.getLogger(FundRedOutStartAction.class);
	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
	@Resource(name = "contractInfoExtends")
	private ContractInfoExtends contractInfoExtends;
	
	@SuppressWarnings("unchecked")
	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
		//传contract_info表主键id
		String  contractid = variablesMap.get("contract_id");
		//流程冲突
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, contractid)); 
	
		//获取合同基本信息
		ContractInfo contractinfo = this.tableService.findEntityByID(ContractInfo.class, contractid);
		CustInfo custInfo = contractinfo.getCustId();
		//将合同基本信息放入variablesMap
		contractInfoExtends.getContractBaseInfo(variablesMap, contractinfo, custInfo);
	    
		Map<String,String>  queryMainObjectMap =new HashMap<String,String>();
		queryMainObjectMap.clear();
		queryMainObjectMap.put("contractid", contractid);
		queryMainObjectMap.put("paytype", "pay_type_in");
	    //设备款计划
		this.fundCommMethod.initFundFundCharge("json_collection_his_str", variablesMap, queryMainObjectMap);
		queryMainObjectMap.clear();
	    queryMainObjectMap.put("contractid", contractid);
	    queryMainObjectMap.put("paytype", "pay_type_out");
	    this.fundCommMethod.initFundFundCharge("json_pay_his_str", variablesMap, queryMainObjectMap); 	
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
