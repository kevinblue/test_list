package com.tenwa.leasing.action.other.accident;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.other.AccidentInfo;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "accidentInsuranceEndAction", description = "流程结束动作", 
 workflowName = "出险流程")
@Component(value = "accidentInsuranceEndAction")
public class AccidentInsuranceEndAction implements JbpmBaseAction {
	@Resource(name = "baseService")
	private BaseService baseService;

	@Override
	public void start(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String delete(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
		AccidentInfo accidentInfo = new AccidentInfo();

		this.baseService.copyAndOverrideExistedValueFromStringMap(variablesMap,
				accidentInfo,null,"accident_info");

		String dnum = WorkflowUtil.getCustConditionSerialNumber(baseService
				.getBussinessDao().getHibernateTemplate(), baseService
				.getBussinessDao().getJdbcTemplate());

		accidentInfo.setDnum(dnum);
		accidentInfo.setContractId(variablesMap.get("contract_id"));
		this.baseService.saveEntity(accidentInfo);
		
		
	}

	@Override
	public String save(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}
}
