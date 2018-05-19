package com.tenwa.leasing.action.contract.conferDecision;

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
import com.tenwa.leasing.entity.conference.ConferenceDecision;
import com.tenwa.leasing.entity.officialsealregistration.OfficialSealRegistration;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "conferDecisionEndAction", description = "流程结束动作", workflowName = "总经理办公会议案")
@Component(value = "conferDecisionEndAction")
public class ConferDecisionEndAction implements JbpmBaseAction {
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
		String confernumber=variablesMap.get("conference_decision.confernumber");
		Map<String, Object> propertiesMap=new HashMap<String, Object>();
		propertiesMap.put("confernumber", confernumber);
		List<ConferenceDecision> listcd=this.baseService.findEntityByProperties(ConferenceDecision.class, propertiesMap);
		ConferenceDecision cd=null;
		if(listcd.size()>0){
			cd=listcd.get(0);
		}else{
			cd=new ConferenceDecision();
		}
		this.tableService.copyAndOverrideExistedValueFromStringMap(variablesMap, cd, null, "conference_decision");
		//ConferenceDecision实体类的项目编号
		cd.setProjId(variablesMap.get("proj_id"));
		this.tableService.saveOrUpdateEntity(cd);
				
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
