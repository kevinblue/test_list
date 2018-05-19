package com.tenwa.leasing.action.contract.onhirechange;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.entity.RemindTask;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.service.contract.contractModify.ContractModifyService;
import com.tenwa.leasing.service.contract.contractRentChange.ContractRentChangeService;
import com.tenwa.leasing.service.contract.onhireContractChange.OnhireContractChangeService;
import com.tenwa.leasing.utils.WorkflowUtil;

/**
 * @author Jason
 * @date 2013-4-24
 * @info
 */
@WorkflowAction(name = "contractOnhireChangeEndAction", description = "流程结束动作", workflowName = "起租后合同变更")
@Component(value = "contractOnhireChangeEndAction")
public class ContractOnhireChangeEndAction implements JbpmBaseAction {
	@Resource(name = "baseService")
	private BaseService baseService;

	@Resource(name = "onhireContractChangeService")
	private OnhireContractChangeService onhireContractChangeService;
	
	@Resource(name = "contractRentChangeService")
	private ContractRentChangeService contractRentChangeService;
	
	@Resource(name = "contractModifyService")
	private ContractModifyService contractModifyService;

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// 删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.baseService, variablesMap);
		// 获取参数
		String doc_id = jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid() + "";
		variablesMap.put("docId", doc_id);
		this.onhireContractChangeService.saveOnhireContractChangeInfo(variablesMap);
		String changeType = variablesMap.get("changeType");
		if("租金计划变更".equals(changeType)){
			this.contractRentChangeService.startContractChangeInfo(variablesMap);
		}else{
			this.contractModifyService.startContractModify(variablesMap);
		}
//		if ("是".equals(variablesMap.get("ischangerent"))) {
//			RemindTask remindtask = new RemindTask();
//			remindtask.setDocID(doc_id);
//			remindtask.setStatus("0");
//			remindtask.setWorkflowName("租金计划变更流程");
//			remindtask.setDeploypropPdid("租金计划变更流程-1");
//			remindtask.setWorkflowParams("contract_id=" + variablesMap.get("contract_info.id"));
//			remindtask.setcId(variablesMap.get("contract_info.id"));
//			remindtask.setProjectName(variablesMap.get("contract_info.projectname"));
//			remindtask.setContractId(variablesMap.get("contract_info.contractid"));
//			ContractInfo contractInfo = (ContractInfo)this.baseService.findEntityByID(ContractInfo.class, variablesMap.get("contract_info.id"));
//			String remindSql = " select tu1.id_||tu2.id_ reminduser  "
//							+" from contract_info ci "
//							+" left join cust_info_company cic on ci.cust_id = cic.cust_id "
//							+" left join cust_info_person cip on cip.cust_id = ci.cust_id "
//							+" left join dunning_district dd1 on dd1.district = cic.province_ "
//							+" left join dunning_district dd2 on dd2.district = cip.province_ "
//							+" left join t_users tu1 on tu1.id_ = dd1.dunning "
//							+" left join t_users tu2 on tu2.id_ = dd2.dunning "
//							+ "where ci.id = ? ";
//			String remindUser = "";
//			List<Map<String, Object>> resultlist = this.baseService.queryListBySql(remindSql, variablesMap.get("contract_info.id"));
//			if(resultlist.size()>0){
//				for(Map.Entry<String,Object> entry: resultlist.get(0).entrySet()){
//					if("reminduser".equals(entry.getKey())){
//						remindUser = String.valueOf(entry.getValue());
//					}
//				}
//			}
//			remindtask.setRemindUser(remindUser);
//			remindtask.setCreateDate(DateUtil.getSystemDateTime());;
//			baseService.saveEntity(remindtask);
//		}
	}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

}
