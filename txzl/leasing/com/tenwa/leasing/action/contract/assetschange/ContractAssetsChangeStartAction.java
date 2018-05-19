package com.tenwa.leasing.action.contract.assetschange;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
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
@WorkflowAction(name = "contractAssetsChangeStartAction", description = "流程开始动作", workflowName = "资产变更")
@Component(value = "contractAssetsChangeStartAction")
public class ContractAssetsChangeStartAction implements JbpmBaseAction {
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
			
		
		/*	
		 //设置租金测算参数为合同审批
		String flowunid=jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+ "";;
		contractCommService.loadContractRentCalculationParam(contractInfo.getContractId(), customerInfo.getCustName(), customerInfo.getId(), flowunid,"onHire_process", variablesMap);
		//加载合同层的交易结构，租金计划，现在金流，财务租金计划，财务现金流	
		contractCommService.loadContractRentCalculation(contractInfo, variablesMap);
		variablesMap.put("json_fund_rent_plan_before_str", variablesMap.get("json_fund_rent_plan_str"));
		
		//赋值城市，省份，资金总额
	  	variablesMap.put("cust_info.provice", contractInfo.getCustId().getCustInfoCompany().getProvince().getName());
	  	variablesMap.put("cust_info.city", contractInfo.getCustId().getCustInfoCompany().getCity().getName());
	  	
		
		//供应商
	//	this.contractCommService.loadContractSupplierInfo(contractInfo, variablesMap);
		//发票信息
	 //   this.contractCommService.loadContractInvoice(contractInfo, variablesMap) ;
	    //租赁物件明细
	    this.contractCommService.loadContractEquip(contractInfo, variablesMap);
	    variablesMap.put("json_old_contract_equip_str",variablesMap.get("json_contract_equip_str"));
		//担保单位信息
		this.contractCommService.loadContractGuaranteeMethod(contractInfo, variablesMap);
		variablesMap.put("json_old_contract_guarantee_method_str", variablesMap.get("json_contract_guarantee_method_str"));
		//抵押物信息
		this.contractCommService.loadContractGuaranteeEquip(contractInfo, variablesMap);
		variablesMap.put("json_old_contract_guarantee_equip_str", variablesMap.get("json_contract_guarantee_equip_str"));
		//获得某个合同的商务信息 保证金、保证金抵扣、保证金退还、调息节点、罚息率、IRR
		String jsoncontractcondition=contractCommService.getContractCondition(contract_id);
		JSONArray jsonarray1=new JSONArray(jsoncontractcondition);
		for(int i=0;i<jsonarray1.length();i++){
			JSONObject conditioninfo=jsonarray1.getJSONObject(i);
			variablesMap.put("contract_condition.id", conditioninfo.getString("ccid"));
			variablesMap.put("beforeadjuststyle", conditioninfo.getString("adjuststyle"));
			variablesMap.put("beforeadjuststylename", conditioninfo.getString("adjuststylename"));
			variablesMap.put("beforepenarate", conditioninfo.getString("penarate"));
			variablesMap.put("beforecautionmoney", conditioninfo.getString("cautionmoney"));
			variablesMap.put("beforecautiondeductionmoney", conditioninfo.getString("cautiondeductionmoney"));
			variablesMap.put("beforecautionmoneyremain", conditioninfo.getString("cautionmoneyremain"));
			variablesMap.put("beforeirr", conditioninfo.getString("irr"));
			variablesMap.put("beforeincomenumber", conditioninfo.getString("incomenumber"));
			variablesMap.put("beforeleaseterm", conditioninfo.getString("leaseterm"));
			variablesMap.put("beforegrossprofit", conditioninfo.getString("grossprofit"));
			variablesMap.put("beforecleancreditmoney", conditioninfo.getString("cleancreditmoney"));
		}
		*/
		
		
		
	}
	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 

	}
}
