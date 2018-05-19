package com.tenwa.leasing.action.contract.letterapproval;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.service.contractcomm.ContractInfoExtends;
import com.tenwa.leasing.service.pledge.PledgeCommMethod;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "letterApprovalStartAction", description = "流程开始动作", workflowName = "公函审批流程")
@Component(value = "letterApprovalStartAction")
public class LetterApprovalStartAction implements JbpmBaseAction {
	
	@Resource(name = "tableService")
	private TableService tableService;	
	
	
	@Resource(name = "contractInfoExtends")
	private ContractInfoExtends contractInfoExtends;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap);  
		return null;
	} 
	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}
    
	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	
		//String  contractid = variablesMap.get("contract_id");
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		String loginuserid=model.get("login_userid");
		System.out.println("=============================="+loginuserid);
		User u=this.tableService.findEntityByID(User.class, loginuserid);
		System.out.println("==============================="+u.getRealname());
		variablesMap.put("letter_approval.originator",u.getRealname());
		
		String time = DateUtil.getSystemDate();
		variablesMap.put("letter_approval.originatime",time);
		
		String letter_id = WorkflowUtil.getLetterApprovalSerialNumber(variablesMap,this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
		variablesMap.put("letter_approval.letternumber",letter_id);
	}
}


