package com.tenwa.leasing.action.contract.rentinvoice;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.springframework.stereotype.Component;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.utils.WorkflowUtil;
@WorkflowAction(name = "contractRentInvoiceStartAction", description = "流程开始动作", workflowName = "租金开票流程")
@Component(value = "contractRentInvoiceStartAction")
public class ContractRentInvoiceStartAction implements JbpmBaseAction {

	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;

	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap); 
		this.contractCommService.deleteFundInvoiceInfo(variablesMap);
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
		User user  = (User) SecurityUtil.getPrincipal();//当前人
		String systemDate = DateUtil.getSystemDate();//当前时间
		String tsdxml=variablesMap.get("tsdxml");
		if("rentshishou".equals(tsdxml)){//租金实收
			this.contractCommService.saveOrUpdateShishouRentInvoiceInfo(variablesMap);
		}else{//租金提前
			this.contractCommService.saveOrUpdateTiqianRentInvoiceInfo(variablesMap);
		}
		String cid=variablesMap.get("cid");
        ContractInfo  info =this.tableService.findEntityByID(ContractInfo.class, cid);
        //流程冲突
      	variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, cid)); 
		String flowunid=jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+ "";
		variablesMap.put("flowunid", flowunid);
		String invoiceid = WorkflowUtil.getFundInvoiceRent(variablesMap,this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
		variablesMap.put("contract_info.projectname", info.getProjectName());
		variablesMap.put("contract_info.invoiceid", invoiceid);
		variablesMap.put("contract_info.realname", user.getRealname());
		variablesMap.put("contract_info.thisdate", systemDate);
		
	}
}
