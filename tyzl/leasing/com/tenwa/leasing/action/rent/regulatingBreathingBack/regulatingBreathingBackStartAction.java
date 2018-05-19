package com.tenwa.leasing.action.rent.regulatingBreathingBack;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.reckon.entity.interest.FundStandardInterest;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "regulatingBreathingBackStartAction", description = "流程开始动作", workflowName = "调息撤销流程")
@Component(value = "regulatingBreathingBackStartAction")
public class regulatingBreathingBackStartAction implements JbpmBaseAction{
	@Resource(name = "tableService")
	private TableService tableService;

	@Override
	public void back(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}
	@Override
	public String delete(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		WorkflowUtil.deleteWorkFlowConflict(tableService, variablesMap);
		return null;
	}
	@Override
	public void end(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//如果有相同记录删除调息记录表，再插入一条新的
	}
	@Override
	public String save(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}
	@Override
	public void start(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String flowunid=String.valueOf(jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid());
		variablesMap.put("flowunid", flowunid);
		//1.央行信息表
		FundStandardInterest fajc = (FundStandardInterest)this.tableService.findEntityByID(FundStandardInterest.class, variablesMap.get("id"));
		variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(fajc, null, "fund_standard_interest"));
		variablesMap.put("adjustid", variablesMap.get("id"));
		variablesMap.put("docid", flowunid);
		variablesMap.put("keyword_workflowid", flowunid);
	}
}
