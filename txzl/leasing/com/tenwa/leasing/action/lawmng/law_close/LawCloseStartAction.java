package com.tenwa.leasing.action.lawmng.law_close;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.service.overdue.FundOverCommService;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "lawCloseStartAction", description = "流程开始动作", workflowName = "法务结案流程")
@Component(value = "lawCloseStartAction")
public class LawCloseStartAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@Resource(name = "fundOverCommService")
	private FundOverCommService fundOverCommService;
	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		this.contractCommService.loadContractInfo(variablesMap);
		String lawRegSerialNumber = WorkflowUtil.getLawRegSerialNumber(null,tableService.getBussinessDao().getHibernateTemplate()
				, tableService.getBussinessDao().getJdbcTemplate());
		variablesMap.put("law_approval.lawnum",lawRegSerialNumber);
		 /* 合同基本信息 */
		this.contractCommService.loadContractInfo(variablesMap);
		/* 根据法务编号拿到每次申请的法务信息 */
		this.fundOverCommService.loadLawInfoDetail(variablesMap);
		
	}
	
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}
	
	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}
}
