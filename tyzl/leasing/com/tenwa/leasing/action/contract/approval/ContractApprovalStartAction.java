package com.tenwa.leasing.action.contract.approval;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "contractApprovalStartAction", description = "流程开始动作", workflowName = "合同审批流程")
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
		ProjInfo projInfo =(ProjInfo) this.tableService.findEntityByID(ProjInfo.class, proj_id);
		variablesMap.put("projtype", projInfo.getProjType().getId());
		//将项目信息转到合同层
		contractCommService.loadContractInfoFromProj(projInfo, variablesMap);
		//创建合同号
		contractCommService.saveAndCreateContractNo(projInfo, variablesMap);
		String contract_id=variablesMap.get("contract_info.contractid");
		//流程冲突
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, contract_id)); 
		CustInfo customerInfo = projInfo.getCustInfo();
		//租金开票类型
		contractCommService.loadContractInvoiceFromProj(projInfo, variablesMap);
		
		//租赁物件明细
		contractCommService.loadContractEquipFromProj(projInfo, variablesMap);
		
		//联合承租人
		contractCommService.loadContractUnionCustFromProj(projInfo, variablesMap);
		
		//担保单位信息
		contractCommService.loadContractGuaranteeMethodFromProj(projInfo, variablesMap);
		
		//抵押物信息
		contractCommService.loadContractGuaranteeEquipFromProj(projInfo, variablesMap);
		//加载合同各方信息
		contractCommService.loadContractSignatoryInfoToCreate(customerInfo, variablesMap);
		//加载承租方基本信息
		contractCommService.loadContractClientInfo(customerInfo, variablesMap);
		//供应商信息
		contractCommService.loadContractSupplierInfoByProj(projInfo, variablesMap);
		
		//设置租金测算参数为合同审批--租金测算专用
		String flowunid=jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+ "";;
		contractCommService.loadContractRentCalculationParam(contract_id, customerInfo.getCustName(), customerInfo.getId(), flowunid,"cont_process", variablesMap);
		//因为这里是按照XML配置查询 所以在合同审批发起的时候数据是从项目表来的 所以这里的xml配置的是项目的XML,交易结构，租金计划，应收应付计划
		contractCommService.loadContractRentCalculationFromProj(contract_id, projInfo, variablesMap);
		//添加联合承租人
		
	}
}
