package com.tenwa.leasing.action.contract.assetssignreport;

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
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "contractAssetsSignReportStartAction", description = "流程开始动作", workflowName = "资产签约报告")
@Component(value = "contractAssetsSignReportStartAction")
public class ContractAssetsSignReportStartAction implements JbpmBaseAction {
	
	@Resource(name = "tableService")
	private TableService tableService;
	
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
		String contract_id = variablesMap.get("contract_id");
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, contract_id)); 
		ContractInfo contractInfo=this.contractCommService.loadContractInfo( variablesMap);
		CustInfo customerInfo=contractInfo.getCustId();
	
		this.contractCommService.loadContractEquip(contractInfo, variablesMap);//租赁物件明细
		this.contractCommService.loadContractGuaranteeMethod(contractInfo, variablesMap); //担保单位信息
		this.contractCommService.loadContractGuaranteeEquip(contractInfo, variablesMap);//抵押物信息
		this.contractCommService.loadContractInvoice(contractInfo, variablesMap);//租金开票类型
		String flowunid=jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+ "";;
		contractCommService.loadContractRentCalculationParam(contract_id, customerInfo.getCustName(), customerInfo.getId(), flowunid,"cont_process", variablesMap);
		
		//初始化联系人
		Map<String,String> properties=new HashMap<String,String>();
		properties.put("custid", customerInfo.getId());
	    String json_relateperson_str=tableService.getJsonArrayData("/eleasing/jsp/cust_info/cust_relatedperson/cust_relatedperson_list.xml", properties).toString();
	    variablesMap.put("json_relateperson_str", json_relateperson_str);
	    
	    //赋值城市，省份，资金总额
	  	variablesMap.put("cust_info.provice", contractInfo.getCustId().getCustInfoCompany().getProvince().getName());
	  	variablesMap.put("cust_info.city", contractInfo.getCustId().getCustInfoCompany().getCity().getName());
	  		
	    
		//商务条件，租金计划，资金计划，现金流
		this.contractCommService.loadContractRentCalculationFromBefore(contractInfo, variablesMap);
		//合同各方
		this.contractCommService.loadContractSignatoryInfo(contractInfo, variablesMap);
		//供应商信息
		this.contractCommService.loadContractSupplierInfo(contractInfo, variablesMap);
		//加载合同文件
		this.contractCommService.loadFile("json_contract_word_str", "合同管理", contract_id, variablesMap);
		
	}
}
