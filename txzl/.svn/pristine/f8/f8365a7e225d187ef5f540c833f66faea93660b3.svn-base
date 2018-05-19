package com.tenwa.leasing.action.contract.borrow;

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

@WorkflowAction(name = "contractBorrowStartAction", description = "流程开始动作", workflowName = "合同借阅")
@Component(value = "contractBorrowStartAction")
public class ContractBorrowStartAction implements JbpmBaseAction {
	
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
		//this.contractCommService.loadContractEquip(contractInfo, variablesMap);//租赁物件明细
		
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("contract_id", contract_id);
		//合同文本借阅
		/*String json_contract_borrow_str = this.tableService.getJsonArrayData("docadd/contract_doc_borrow_list.xml", queryMainObjectMap).toString();
		variablesMap.put("json_contract_borrow_str",json_contract_borrow_str);*/
		String json_contract_borrow_str=this.tableService.getJsonArrayData("docadd/contract_borrow_present.xml", queryMainObjectMap).toString();
		variablesMap.put("json_contract_borrow_str",json_contract_borrow_str);
		/*//放款资料清单借阅
		String json_fundput_file_borrow_str = this.tableService.getJsonArrayData("docadd/fundput_file_borrow_list.xml", queryMainObjectMap).toString();
		variablesMap.put("json_fundput_file_borrow_str",json_fundput_file_borrow_str);*/
	}
}
