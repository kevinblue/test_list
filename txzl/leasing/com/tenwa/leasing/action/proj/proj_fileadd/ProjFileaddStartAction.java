package com.tenwa.leasing.action.proj.proj_fileadd;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.Proj.proComm.ProCommService;
import com.tenwa.leasing.utils.WorkflowUtil;


/**   
*    
* 项目名称：tls5.1   
* 类名称：ProjFileaddStartAction   
* 类描述：   
* 创建人：rovine   
* 创建时间：2014年12月8日 下午3:04:26   
* @version        
*/
@WorkflowAction(name = "projFileaddStartAction", description = "流程开始动作", workflowName = "项目资料补充")
@Component(value = "projFileaddStartAction")
public class ProjFileaddStartAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "proCommService")
	private ProCommService proCommService;
	
	
	
	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {	
		proCommService.loadProjInfo(variablesMap);
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
	public void back(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}
}
