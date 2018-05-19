package com.tenwa.leasing.action.contract.contractOnhire;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.contract.ContractSignatory;
import com.tenwa.leasing.service.contract.contractOnhire.ContractOnhireService;
import com.tenwa.leasing.utils.WorkflowUtil;



/**
 * @author Jason
 * @date 2013-4-24
 * @info 
 */
@WorkflowAction(name = "contractOnhireEndActio", description = "流程结束动作", workflowName = "合同起租流程")
@Component(value = "contractOnhireEndActio")
public class ContractOnhireEndAction implements JbpmBaseAction {
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "contractOnhireService")
	private ContractOnhireService contractOnhireService;
	
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
		//删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.baseService, variablesMap);  
		//获取参数
		String doc_id = jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+"";
		variablesMap.put("docId", doc_id);
		this.contractOnhireService.updateContractOnhire(variablesMap);
		
		String contract_id = variablesMap.get("contract_id");
		Map<String, Object> propertiesMap=new HashMap<String, Object>();
		ContractInfo contractid=this.baseService.findEntityByID(ContractInfo.class, contract_id);
		propertiesMap.put("contractId", contractid);
		List<ContractSignatory> contractSignatorys=this.baseService.findEntityByProperties(ContractSignatory.class, propertiesMap);
		ContractSignatory contractSignatory=contractSignatorys.get(0);
		if(variablesMap.get("contract_signatory.leaseaccnumber")!=variablesMap.get("contractsignatory.leaseaccnumber")){
			contractSignatory.setLeaseAccName(variablesMap.get("contractsignatory.leaseaccname"));
			contractSignatory.setLeaseAccNumber(variablesMap.get("contractsignatory.leaseaccnumber"));
			contractSignatory.setLeaseAccBank(variablesMap.get("contractsignatory.leaseaccbank"));
			this.baseService.saveOrUpdateEntity(contractSignatory);
		}
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
