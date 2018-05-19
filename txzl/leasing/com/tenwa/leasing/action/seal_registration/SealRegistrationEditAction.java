package com.tenwa.leasing.action.seal_registration;
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
import com.tenwa.leasing.entity.officialsealregistration.OfficialSealRegistration;
import com.tenwa.leasing.service.Proj.proComm.ProCommService;

/**
 * 
 * @author rovine
 * @date 2014-8-18
 * @info项目立项的Action
 * @Copyright 
 * Tenwa
 */
@WorkflowAction(name = "sealRegistrationEditAction", description = "流程编辑动作", workflowName = "公章使用登记")
@Component(value = "sealRegistrationEditAction")
public class SealRegistrationEditAction implements JbpmBaseAction {
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "proCommService")
	private ProCommService proCommService;
	
	
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
			//throw new BusinessException("没找到对应的公章使用信息！！！");
			osr=new OfficialSealRegistration();
		}
		
		this.tableService.copyAndOverrideExistedValueFromStringMap(variablesMap, osr, null, "seal_registration");
		//用章种类保存显示值
		//pe.setPetitiondept(variablesMap.get("rawValue_petition_approval.petitiondept"));
		this.tableService.saveOrUpdateEntity(osr);
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
