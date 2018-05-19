package com.tenwa.leasing.action.seal_registration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.BaseService;
import com.tenwa.exception.BusinessException;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.officialsealregistration.OfficialSealRegistration;
import com.tenwa.leasing.service.Proj.projApproval.ProjApprovalService;

/**
 * 
 * @author rovine
 * @date 2014-8-18
 * @info项目立项的Action
 * @Copyright 
 * Tenwa
 */
@WorkflowAction(name = "sealRegistrationEndAction", description = "流程结束动作", workflowName = "公章使用登记")
@Component(value = "sealRegistrationEndAction")
public class SealRegistrationEndAction implements JbpmBaseAction {
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "projApprovalService")
	private ProjApprovalService projApprovalService;
	
	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 
	}
	
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String flownumber=variablesMap.get("seal_registration.flownumber");
		Map<String, Object> propertiesMap=new HashMap<String, Object>();
		propertiesMap.put("flownumber", flownumber);
		List<OfficialSealRegistration> listosr=this.baseService.findEntityByProperties(OfficialSealRegistration.class, propertiesMap);
		OfficialSealRegistration osr=null;
		if(listosr.size()>0){
			osr=listosr.get(0);
		}else{
			throw new BusinessException("没找到对应的公章使用信息！！！");
		}
		//签呈结论
//		DictionaryData petitionconclusion=this.baseService.findEntityByID(DictionaryData.class,variablesMap.get("petition_approval.petitionconclusion"));
//		pe.setPetitionconclusion(petitionconclusion);
//		pe.setConclusionmemo(variablesMap.get("conclusionmemo"));
		
		this.baseService.saveOrUpdateEntity(osr);
		//throw new BusinessException("11");
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
