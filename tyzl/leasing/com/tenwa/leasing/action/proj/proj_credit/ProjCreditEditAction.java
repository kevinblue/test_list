package com.tenwa.leasing.action.proj.proj_credit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.Proj.proComm.ProCommService;
import com.tenwa.leasing.service.Proj.projCredit.ProjCreditService;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "projCreditEditAction", description = "第一步项目信息入库", workflowName = "项目信审流程")
@Component(value = "projCreditEditAction")
public class ProjCreditEditAction implements JbpmBaseAction {
	
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "projCreditService")
	private ProjCreditService projCreditService;

	@Resource(name = "proCommService")
	private ProCommService proCommService;
	
	@Override
	public void start(HttpServletRequest request,	Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 
		
	}

	@Override
	public void back(HttpServletRequest request,	Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String proj_id = variablesMap.get("proj_info.projid");
		Map<String,Object> queryMainObjectMap = new HashMap<String,Object>();
		queryMainObjectMap.put("projId", proj_id);
		List<ProjInfo> projinfos=this.tableService.findEntityByProperties(ProjInfo.class, queryMainObjectMap);
		if(null!=projinfos&&projinfos.size()>0){
			ProjInfo projinfo=projinfos.get(0);
			this.tableService.removeEntity(projinfo);
		}
	}

	@Override
	public void end(HttpServletRequest request,Map<String, String> variablesMap,	JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 
		String proj_id = variablesMap.get("proj_info.projid");
		Map<String,Object> queryMainObjectMap = new HashMap<String,Object>();
		queryMainObjectMap.put("projId", proj_id);
		ProjInfo projinfo=this.tableService.updateMainEntity(ProjInfo.class, queryMainObjectMap, variablesMap,null, "proj_info");
		proCommService.saveProjEquipment(projinfo, variablesMap);
		proCommService.saveProjRentCalculation(projinfo, variablesMap);
		proCommService.saveProjGuaranteeMethod(projinfo, variablesMap);
		proCommService.saveProjGuaranteeEquipment(projinfo, variablesMap);
		proCommService.savProjInvoice(projinfo, variablesMap);
	}

	@Override
	public String save(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 
		return null;
	}

	@Override
	public String delete(HttpServletRequest request,	Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String proj_id = variablesMap.get("proj_info.projid");
		Map<String,Object> queryMainObjectMap = new HashMap<String,Object>();
		queryMainObjectMap.put("projId", proj_id);
		List<ProjInfo> projinfos=this.tableService.findEntityByProperties(ProjInfo.class, queryMainObjectMap);
		if(null!=projinfos&&projinfos.size()>0){
			ProjInfo projinfo=projinfos.get(0);
			this.tableService.removeEntity(projinfo);
		}
		return null;
	}

}
