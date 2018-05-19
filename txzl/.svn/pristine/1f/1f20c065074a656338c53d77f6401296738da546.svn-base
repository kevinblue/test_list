package com.tenwa.leasing.action.lawmng.filing_cost;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.jbpm.service.JbpmService;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.service.overdue.FundOverCommService;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "filingCostStartAction", description = "流程开始动作", workflowName = "立案事项通知流程")
@Component(value = "filingCostStartAction")
public class FilingCostStartAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "jbpmService")
	private JbpmService jbpmService;
	
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
		//将法务申请的附件带入到法务费用申请流程当中去
		//this.jbpmService.transferOneProcessAttachmentToAnother(jbpmWorkflowHistoryInfo, "4028d0814bde04db014bde6bcd920047",variablesMap);
		Map<String,String> mainMap = new HashMap<String,String>();
		mainMap.put("lawnum", variablesMap.get("lawnum"));
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, variablesMap.get("lawnum")));
		String json_filing_cost_his_detail_str = this.tableService.getJsonArrayData("eleasing/jsp/filing_mng/filing_cost_detail_list.xml", mainMap).toString();
		variablesMap.put("json_filing_cost_his_detail_str", json_filing_cost_his_detail_str);
		
		
	}
	
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap);  
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
