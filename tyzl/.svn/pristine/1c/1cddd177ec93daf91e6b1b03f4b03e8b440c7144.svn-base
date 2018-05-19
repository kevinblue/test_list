package com.tenwa.leasing.action.contract.finish;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.AppStaticUtil;
import com.tenwa.leasing.entity.contract.ContractEndInfo;
import com.tenwa.leasing.entity.contract.ContractInfo;



@WorkflowAction(name = "contractFinishEndAction", description = "流程结束动作", workflowName = "合同结束流程")
@Component(value = "contractFinishEndAction")
public class ContractFinishEndAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService  tableService;
	
	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// 1.更新合同结束状态
		String  contractid=variablesMap.get("contract_info.id");
		String endDate = variablesMap.get("contract_end_info.actualenddate");
		ContractInfo contract = this.tableService.findEntityByID(ContractInfo.class, contractid);
		contract.setActualEndDate(endDate);
		contract.setContractStatus(AppStaticUtil.CONTRACT_END);
		this.tableService.updateEntity(contract);
		// 2.保存合同结束信息
		ContractEndInfo endInfo = new ContractEndInfo();
		this.tableService.copyAndOverrideExistedValueFromStringMap(variablesMap,endInfo,null,"contract_end_info");
		endInfo.setContractId(contract);
		this.tableService.saveEntity(endInfo);
	}
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}
	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}
	
	@Override
	public void back(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 
		
	}

}
 