package com.tenwa.leasing.action.fund.fund_plancharg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.fund.ContractFundFundCharge;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.service.contractcomm.ContractInfoExtends;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;
import com.tenwa.leasing.service.fund.redout.FundRedOutService;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "fundPlanChargStartAction", description = "流程开始动作", workflowName = "资金计划变更")
@Component(value = "fundPlanChargStartAction")
public class FundPlanChargStartAction implements JbpmBaseAction {
	Logger logger =Logger.getLogger(FundPlanChargStartAction.class);
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
	@Resource(name = "contractInfoExtends")
	private ContractInfoExtends contractInfoExtends;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@SuppressWarnings("unchecked")
	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
		//传contract_info表主键id
		String  contractid = variablesMap.get("contract_id");
		
		//获取合同基本信息
		ContractInfo contractinfo = this.tableService.findEntityByID(ContractInfo.class, contractid);
		CustInfo custInfo = contractinfo.getCustId();
		//将合同基本信息放入variablesMap
		contractInfoExtends.getContractBaseInfo(variablesMap, contractinfo, custInfo);

		//投放明细
		this.contractCommService.loadContractFundPut(contractinfo, variablesMap);
		//联合承租人
		contractCommService.loadContractUnionCust(contractinfo, variablesMap);
		//查询出原始合同的基本信息
		ContractInfo oldcinfo=this.tableService.findEntityByID(ContractInfo.class,contractinfo.getSupContractId());
		variablesMap.put("contract_info.contractid",oldcinfo.getContractId());
	    variablesMap.put("contract_info.contractnumber",oldcinfo.getContractNumber());
		//流程冲突
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, contractid)); 
		
		Map<String,String>  queryMainObjectMap =new HashMap<String,String>();
		queryMainObjectMap.clear();
		queryMainObjectMap.put("contractid", contractid);
		queryMainObjectMap.put("paytype", "pay_type_in");
	    //付款计划
		this.fundCommMethod.initFundFundPlan("json_plancharg_collect_str", variablesMap, queryMainObjectMap);
		
		queryMainObjectMap.clear();
		queryMainObjectMap.put("contractid", contractid);
		queryMainObjectMap.put("paytype", "pay_type_out");
	    //收款计划
		this.fundCommMethod.initFundFundPlan("json_plancharg_pay_str", variablesMap, queryMainObjectMap);
		
		
		queryMainObjectMap.clear();
		queryMainObjectMap.put("contractid", contractid);
	    //资金计划历史
		this.fundCommMethod.initFundFundPlan("json_fund_plan_old_str", variablesMap, queryMainObjectMap);
			    
		
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
