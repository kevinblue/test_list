package com.tenwa.leasing.action.contract.approval;

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
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.proj.ProjEquip;
import com.tenwa.leasing.entity.proj.ProjEquipTmp;
import com.tenwa.leasing.entity.proj.ProjGuaranteeEquip;
import com.tenwa.leasing.entity.proj.ProjGuaranteeEquipTmp;
import com.tenwa.leasing.entity.proj.ProjGuaranteeMethod;
import com.tenwa.leasing.entity.proj.ProjGuaranteeMethodTmp;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "contractApprovalStartAction", description = "流程开始动作", workflowName = "合同出具流程")
@Component(value = "contractApprovalStartAction")
public class ContractApprovalStartAction implements JbpmBaseAction {

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
		String proj_id = variablesMap.get("proj_id");
		String flowunid=jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+ "";
		variablesMap.put("flowunid", flowunid);
		ProjInfo projInfo =(ProjInfo) this.tableService.findEntityByID(ProjInfo.class, proj_id);
		//将项目信息转到合同层
		contractCommService.loadContractInfoFromProj(projInfo, variablesMap);
		//创建合同号
		contractCommService.saveAndCreateContractNo(projInfo, variablesMap);
		
		String contract_id=variablesMap.get("contract_info.contractid");
		//流程冲突
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, contract_id)); 
		//商务经办
		variablesMap.put("contract_info.projcommisionera",SecurityUtil.getPrincipal().getId()); 
		variablesMap.put("rawValue_contract_info.projcommisionera",SecurityUtil.getPrincipal().getRealname());
		CustInfo customerInfo = projInfo.getCustInfo();
		//租金开票类型 改为取自客户信息 不取自项目的信息
		contractCommService.loadContractInvoiceFromProj(projInfo, variablesMap);
		//contractCommService.loadContractInvoiceFromCust(customerInfo, variablesMap);
		
		//加載租赁物，从projequip拷贝到projequiptmp
		contractCommService.loadContractEquipTmp(projInfo,variablesMap);
		//加載抵押物，从projeGuaranteeEquip拷贝到projGuaranteeEquipTmp
		contractCommService.loadContractGuaranteeEquipTmp(projInfo,variablesMap);
		//加載担保单位，从projeGuaranteeMethod拷贝到projGuaranteeMethodTmp
		contractCommService.loadContractGuaranteeMethodTmp(projInfo,variablesMap);
		//加载合同各方信息
		contractCommService.loadContractSignatoryInfoToCreate(customerInfo, variablesMap);
		//供应商信息
		contractCommService.loadContractSupplierInfoByProj(projInfo, variablesMap);
/*		//加载付款前提
		contractCommService.loadContractPremiseInfoByProj(projInfo, variablesMap);*/
		//加载付款前提
		contractCommService.loadProjPaymentPremiseCondition(projInfo, variablesMap);
		
		//设置租金测算参数为合同审批--租金测算专用
		contractCommService.loadContractRentCalculationParam(contract_id, customerInfo.getCustName(), customerInfo.getId(), flowunid,"cont_process", variablesMap);
		//因为这里是按照XML配置查询 所以在合同审批发起的时候数据是从项目表来的 所以这里的xml配置的是项目的XML,交易结构，租金计划，应收应付计划
		contractCommService.loadContractRentCalculationFromProj(contract_id, projInfo, variablesMap);
		
		//获得当前登录人部门路径
		String user_proj_route = contractCommService.getDeptLujingChange(variablesMap.get("contract_info.deptroute"));
		JSONArray jsonarray2=new JSONArray(user_proj_route);
		for(int i=0;i<jsonarray2.length();i++){
			JSONObject jsonobject2=jsonarray2.getJSONObject(i);
			variablesMap.put("contract_info.deptroute", jsonobject2.getString("id"));
			variablesMap.put("rawValue_contract_info.deptroute", jsonobject2.getString("name"));
		}
	}


}
