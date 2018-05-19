package com.tenwa.leasing.action.contract.assetssignreport;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.contract.ContractAssetsSignReport;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.service.contract.contractChange.ContractChangeService;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "contractAssetsSignReportEndAction", description = "流程结束动作", workflowName = "资产签约报告")
@Component(value = "contractAssetsSignReportEndAction")
public class ContractAssetsSignReportEndAction implements JbpmBaseAction {

	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "contractChangeService")
	private ContractChangeService contractChangeService;
	
	@Resource(name = "contractCommService")
	ContractCommService contractCommService;
	
	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	
		String doc_id = jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+"";
		variablesMap.put("docId", doc_id);
		WorkflowUtil.deleteWorkFlowConflict(this.baseService, variablesMap);
		//this.contractChangeService.saveContractChangeInfo(variablesMap);
		//更新联系人
		/*contractCommService.saveorupdateRelationPerson(variablesMap);*/
		ContractInfo contractinfo=this.baseService.findEntityByID(ContractInfo.class,variablesMap.get("contract_info.id"));
		//保存报告
		ContractAssetsSignReport report=new ContractAssetsSignReport();
		this.baseService.copyAndOverrideExistedValueFromStringMap(variablesMap, report, null, "");
		report.setContractId(contractinfo);
		report.setFlowUnid(doc_id);
		report.setCreator(SecurityUtil.getPrincipal());
		report.setCreateDate(DateUtil.getSystemDateTime());
		report.setJsonassetssignwordstr(variablesMap.get("json_assets_sign_word_str"));
		report.setAnother(variablesMap.get("another")==null ? "":variablesMap.get("another"));
		report.setOthers(variablesMap.get("json_other_str"));
		this.baseService.saveEntity(report);
	}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

}
