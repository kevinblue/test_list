package com.tenwa.leasing.action.proj.proj_reconsider;

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

@WorkflowAction(name = "projReconsiderEndAction", description = "流程结束动作", workflowName = "项目复议流程")
@Component(value = "projReconsiderEndAction")
public class ProjReconsiderEndAction implements JbpmBaseAction {
	
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
		 
		
	}

	@Override
	public void end(HttpServletRequest request,Map<String, String> variablesMap,	JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 
		WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap); 
		String doc_id = jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+"";
		variablesMap.put("docId", doc_id);
		this.projCreditService.updateProjCreditInfo(variablesMap);
		String projid=variablesMap.get("proj_id");
		ProjInfo projinfo=this.tableService.findEntityByID(ProjInfo.class,projid);
		proCommService.savProjInvoice(projinfo, variablesMap);
		/*String projchangetype=variablesMap.get("proj_info.projchangetype");
		if( "projChangeType.01".equals(projchangetype)){
			this.projCreditService.updateProjCreditInfo01(variablesMap);						
		}else if(projchangetype.equals("projChangeType.02")){
			this.projCreditService.updateProjCreditInfo02(variablesMap);
		}else{
			this.projCreditService.updateProjCreditInfo03(variablesMap);
		}*/
		
	}

	@Override
	public String save(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 
		return null;
	}

	@Override
	public String delete(HttpServletRequest request,	Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
		return null;
	}

}
