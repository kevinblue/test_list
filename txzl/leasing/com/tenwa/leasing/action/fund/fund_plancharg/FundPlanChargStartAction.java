package com.tenwa.leasing.action.fund.fund_plancharg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.entity.contract.reckon.condition.ContractCondition;
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
		ContractCondition ccinfo =contractinfo.getContractCondition();
		variablesMap.put("equipamt", ccinfo.getEquipAmt()+"");
		CustInfo custInfo = contractinfo.getCustId();
		//将合同基本信息放入variablesMap
		contractInfoExtends.getContractBaseInfo(variablesMap, contractinfo, custInfo);
	
		//流程冲突
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, contractid)); 
		
		Map<String,String>  queryMainObjectMap =new HashMap<String,String>();
		//去除租前息的收款计划 
		queryMainObjectMap.clear();
		queryMainObjectMap.put("contractid", contractid);
		queryMainObjectMap.put("paytype", "pay_type_in");
		queryMainObjectMap.put("notin", "\'feetype9\'");
		this.fundCommMethod.initFundFundPlan("json_plancharg_collect_str", variablesMap, queryMainObjectMap);
		//设备款
		queryMainObjectMap.clear();
		queryMainObjectMap.put("contractid", contractid);
		this.fundCommMethod.initFundEquip("json_equip_amt_str", variablesMap, queryMainObjectMap);
			//去除租前息的收款计划 	
		queryMainObjectMap.clear();
		queryMainObjectMap.put("contractid", contractid);
		//投放计划
		queryMainObjectMap.clear();
		queryMainObjectMap.put("contractid", contractid);
		queryMainObjectMap.put("feetype", "feetype10"); 
		this.fundCommMethod.initFundPutPlan("json_fund_put_config_str", variablesMap, queryMainObjectMap);
		//租前息计划
		queryMainObjectMap.clear();
		queryMainObjectMap.put("contractid", contractid);
		queryMainObjectMap.put("feetype", "feetype9"); 
		this.fundCommMethod.initFundPutPlan("json_grace_plan_str", variablesMap, queryMainObjectMap);
		
		//付款计划（去除设备款）
		queryMainObjectMap.clear();
		queryMainObjectMap.put("contractid", contractid);
		queryMainObjectMap.put("isnoequip", "feetype10"); 
		queryMainObjectMap.put("paytype", "pay_type_out");
		this.fundCommMethod.initFundFundPlan("json_plancharg_pay_str", variablesMap, queryMainObjectMap);
		
		//资金计划历史
		queryMainObjectMap.clear();
		queryMainObjectMap.put("contractid", contractid);
		this.fundCommMethod.initFundFundPlan("json_fund_plan_old_str", variablesMap, queryMainObjectMap);
		
		//加载租金计划中付款前提条件
		this.contractCommService.loadContractPaymentPremiseCondition(contractinfo, variablesMap);
		
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
