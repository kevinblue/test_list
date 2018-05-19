package com.tenwa.leasing.action.contract.contractPatrol;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Component;

import com.reckon.service.impl.JSONUtil;
import com.tenwa.business.entity.JsonUtil;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.AppStaticUtil;
import com.tenwa.leasing.entity.contract.ContractEndInfo;
import com.tenwa.leasing.entity.contract.ContractGuaranteeMethod;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.contract.ContractPatrolInfo;
import com.tenwa.leasing.entity.contract.ContractSupplierInfo;



@WorkflowAction(name = "contractPatrolEndAction", description = "流程结束动作", workflowName = "租后巡视")
@Component(value = "contractPatrolEndAction")
public class ContractPatrolEndAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService  tableService;
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
		String  contractid=variablesMap.get("contract_info.id");
		ContractInfo contract = this.tableService.findEntityByID(ContractInfo.class, contractid);
		//保存租后巡视信息
		ContractPatrolInfo contractPatrolInfo = new ContractPatrolInfo();
		this.tableService.copyAndOverrideExistedValueFromStringMap(variablesMap,contractPatrolInfo,null,"contract_patrol_info");
		contractPatrolInfo.setContractId(contract);
		//保存
		String json_stock_patrol_report_str = variablesMap.get("json_stock_patrol_report_str");
		//JSONArray jsonArray = JSONArray.fromObject(json_stock_patrol_report_str); 
		
		this.tableService.saveOrUpdateEntity(contractPatrolInfo);
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

	
	 /**
	 * (non-Javadoc)
	 * @see com.business.action.JbpmBaseAction#back(javax.servlet.http.HttpServletRequest, java.util.Map)
	 **/
	
	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// TODO Auto-generated method stub
		
	}

}