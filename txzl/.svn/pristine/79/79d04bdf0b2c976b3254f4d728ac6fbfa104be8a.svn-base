package com.tenwa.leasing.action.contract.onhirechange;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.contract.ContractInvoiceType;
import com.tenwa.leasing.entity.contract.ContractSignatory;
import com.tenwa.leasing.entity.contract.ContractSupplierInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.service.contractcomm.ContractInfoExtends;
import com.tenwa.leasing.utils.WorkflowUtil;

/**
 * @author Jason
 * @date 2013-4-24
 * @info
 */
@WorkflowAction(name = "contractOnhireChangeStartAction", description = "流程开始动作", workflowName = "起租后合同变更")
@Component(value = "contractOnhireChangeStartAction")
public class ContractOnhireChangeStartAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;
		
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
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
		//合同各方
		this.contractCommService.loadContractSignatoryInfo(contractInfo, variablesMap);
		//供应商
		this.contractCommService.loadContractSupplierInfo(contractInfo, variablesMap);
		//发票信息
	    this.contractCommService.loadContractInvoice(contractInfo, variablesMap) ;
	    //租赁物件明细
	    this.contractCommService.loadContractEquip(contractInfo, variablesMap);
	    variablesMap.put("json_old_contract_equip_str",variablesMap.get("json_contract_equip_str"));
		//担保单位信息
		this.contractCommService.loadContractGuaranteeMethod(contractInfo, variablesMap);
		variablesMap.put("json_old_contract_guarantee_method_str", variablesMap.get("json_contract_guarantee_method_str"));
		//抵押物信息
		this.contractCommService.loadContractGuaranteeEquip(contractInfo, variablesMap);
		variablesMap.put("json_old_contract_guarantee_equip_str", variablesMap.get("json_contract_guarantee_equip_str"));
	}
	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 

	}
}
