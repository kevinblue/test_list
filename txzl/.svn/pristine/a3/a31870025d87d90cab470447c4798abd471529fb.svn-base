package com.tenwa.leasing.action.contract.letterapproval;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.letter.LetterApproval;
import com.tenwa.leasing.entity.other.ExpressdeliverInfo;
import com.tenwa.leasing.service.pledge.PledgeCommMethod;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "letterApprovalEndAction", description = "流程结束动作", workflowName = "公函审批流程")
@Component(value = "letterApprovalEndAction")
public class LetterApprovalEndAction implements JbpmBaseAction {
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 
	}
	
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.baseService, variablesMap);  
		String letternumber=variablesMap.get("letter_approval.letternumber");
		Map<String, Object> propertiesMap=new HashMap<String, Object>();
		propertiesMap.put("letternumber", letternumber);
		List<LetterApproval> listla=this.baseService.findEntityByProperties(LetterApproval.class, propertiesMap);
		LetterApproval la=null;
		if(listla.size()>0){
			la=listla.get(0);
		}else{
			la=new LetterApproval();
		}
		this.tableService.copyAndOverrideExistedValueFromStringMap(variablesMap, la, null, "letter_approval");
		//LetterApproval实体类的项目名称
		la.setProjectName(variablesMap.get("rawValue_letter_approval.projname"));
		this.tableService.saveOrUpdateEntity(la);
				
	}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}
	
	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}
	
}
