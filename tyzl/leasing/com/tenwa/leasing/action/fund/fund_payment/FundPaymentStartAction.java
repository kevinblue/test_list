package com.tenwa.leasing.action.fund.fund_payment;

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

@WorkflowAction(name = "fundPaymentStartAction", description = "流程开始动作", workflowName = "付款流程")
@Component(value = "fundPaymentStartAction")
public class FundPaymentStartAction implements JbpmBaseAction {
	Logger logger =Logger.getLogger(FundPaymentStartAction.class);
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
		
		String  contractid = variablesMap.get("contract_id");
		
		ContractInfo contractinfo = this.tableService.findEntityByID(ContractInfo.class, contractid);
		CustInfo custInfo = contractinfo.getCustId();
		//将合同基本信息放入variablesMap
		contractInfoExtends.getContractBaseInfo(variablesMap, contractinfo, custInfo);
		
		//查询出原始合同的基本信息
		ContractInfo oldcinfo=this.tableService.findEntityByID(ContractInfo.class,contractinfo.getSupContractId());
		variablesMap.put("contract_info.contractid",oldcinfo.getContractId());
		variablesMap.put("contract_info.contractnumber",oldcinfo.getContractNumber());
		
		//联合承租人
		contractCommService.loadContractUnionCust(contractinfo, variablesMap);

		//投放明细
		this.contractCommService.loadContractFundPut(contractinfo, variablesMap);
		
		Map<String,String>  queryMainObjectMap =new HashMap<String,String>();
		queryMainObjectMap.clear();
		queryMainObjectMap.put("contractid", contractid);
		queryMainObjectMap.put("isnoequip", "true");
		queryMainObjectMap.put("paytype", "pay_type_out");
	    //设备款计划
		this.fundCommMethod.initFundFundPlan("json_payment_plan_str", variablesMap, queryMainObjectMap);

		 //资金付款明细
		queryMainObjectMap.clear();
		queryMainObjectMap.put("contractid", contractid);
		queryMainObjectMap.put("paytype", "pay_type_out");   
		this.fundCommMethod.initFundFundCharge("json_pay_his_str", variablesMap, queryMainObjectMap);
		
	}
	
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 
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
