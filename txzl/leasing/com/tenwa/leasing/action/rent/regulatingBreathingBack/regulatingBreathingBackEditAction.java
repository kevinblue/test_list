package com.tenwa.leasing.action.rent.regulatingBreathingBack;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.google.inject.internal.Lists;
import com.google.inject.internal.Maps;
import com.reckon.entity.contract.reckon.fund.FundAdjustInterestContractTemp;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "regulatingBreathingBackEditAction", description = "流程编辑动作", workflowName = "调息撤销流程")
@Component(value = "regulatingBreathingBackEditAction")
public class regulatingBreathingBackEditAction implements JbpmBaseAction {

	@Resource(name = "tableService")
	private TableService tableService;

	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap);
	}

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//流程互斥 
		Map<String,Object> properMap = Maps.newHashMap();
		properMap.put("docId", variablesMap.get("docid"));
		List<FundAdjustInterestContractTemp> adjustTempList = this.tableService.findEntityByProperties(FundAdjustInterestContractTemp.class, properMap);
		String workFlowName = jbpmWorkflowHistoryInfo.getWorkflowName(); 
		String flowunid=jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+""; 
		List<String> workFlowFlagsList = Lists.newArrayList();
		 
		//for (int i = 0; i < adjustTempList.size(); i++) {
		//	FundAdjustInterestContractTemp adjustTemp = adjustTempList.get(i);
		//	String contract_id = adjustTemp.getContractId().getId();
		//	 String tempworkflag=WorkflowUtil.checkWorkFlowConflict(this.tableService, workFlowName, flowunid, contract_id);
		//	  workFlowFlagsList.add(tempworkflag);	
		//}
		String workFlowFlags = workFlowFlagsList.toString();
		workFlowFlags = workFlowFlags.substring(1, workFlowFlags.length() - 1);
		variablesMap.put("workFlowFlag", workFlowFlags.replaceAll(" ", ""));
		//结束
		
	}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// TODO Auto-generated method stub

	}

}
