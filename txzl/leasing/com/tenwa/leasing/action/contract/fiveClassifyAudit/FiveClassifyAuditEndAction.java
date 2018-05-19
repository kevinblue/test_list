package com.tenwa.leasing.action.contract.fiveClassifyAudit;

import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractFiveCategory;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.fivecategoryapply.FiveCategoryApply;
import com.tenwa.leasing.service.contract.contractCancel.ContractCancelService;
import com.tenwa.leasing.utils.WorkflowUtil;

/**   
*    
* 项目名称：tls5.1   
* 类名称：FiveCategoryEndAction   
* 类描述：   
* 创建人：rovine   
* 创建时间：2015年1月12日 上午10:32:54   
* @version        
*/
@WorkflowAction(name = "fiveClassifyAuditEndAction", description = "流程结束动作", workflowName = "资产分类审核上报")
@Component(value = "fiveClassifyAuditEndAction")
public class FiveClassifyAuditEndAction implements JbpmBaseAction {

	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "contractCancelService")
	private ContractCancelService contractCancelService;
	
	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		WorkflowUtil.deleteWorkFlowConflict(this.baseService, variablesMap);
		
		FiveCategoryApply fiveCategoryApply=this.baseService.updateMainEntity(FiveCategoryApply.class, null, variablesMap, null, "fivecategoryapply");
		String  applyuser=variablesMap.get("login_userid");
		User user=this.baseService.findEntityByID(User.class, applyuser);
		fiveCategoryApply.setApplyuser(user);			
		System.out.println(fiveCategoryApply);
		this.baseService.saveOrUpdateEntity(fiveCategoryApply);
	   	 	
	}
    
	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

}
