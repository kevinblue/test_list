package com.tenwa.leasing.action.proj.proj_fileadd;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.service.Proj.projAddFile.ProjAddFileService;
import com.tenwa.leasing.utils.WorkflowUtil;


/**   
*    
* 项目名称：tls5.1   
* 类名称：ProjFileaddEndAction   
* 类描述：   
* 创建人：rovine   
* 创建时间：2014年12月8日 下午3:03:11   
* @version        
*/
@WorkflowAction(name = "projFileaddEndAction", description = "流程结束动作", workflowName = "项目资料补充")
@Component(value = "projFileaddEndAction")
public class ProjFileaddEndAction implements JbpmBaseAction {
	
	@Resource(name = "baseService")
	private BaseService baseService;
	 @Resource (name = "projAddFileService")
	 private ProjAddFileService proAddFileService;
    
    
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
				proAddFileService.saveProjectInfo(variablesMap);
				
	}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}
	
	@Override
	public void back(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}
}
