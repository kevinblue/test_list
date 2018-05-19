package com.tenwa.leasing.action.contract.rentChange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.reckon.commons.helper.ObjectConvertUtils;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.entity.contract.reckon.cash.ContractCashDetail;
import com.reckon.entity.contract.reckon.cash.ContractCashDetailTemp;
import com.reckon.entity.contract.reckon.cash.ContractFinaCashDetail;
import com.reckon.entity.contract.reckon.cash.ContractFinaCashDetailTemp;
import com.reckon.entity.contract.reckon.condition.ContractConditionTemp;
import com.reckon.entity.contract.reckon.fina.ContractFinaRentPlan;
import com.reckon.entity.contract.reckon.fina.ContractFinaRentPlanTemp;
import com.reckon.entity.contract.reckon.fund.ContractFundRentPlanTemp;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.contract.ContractInvoiceType;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.service.contractcomm.ContractInfoExtends;
import com.tenwa.leasing.utils.WorkflowUtil;

/**
 * 
 * @author zhangc
 * @ClassName: ContractRentChangeStartAction 
 * @date 2014年12月2日 下午1:43:49 
 * @Description: 租金计划变更流程开始
 */
@WorkflowAction(name = "contractRentChangeStartAction", description = "流程开始动作", workflowName = "租金计划变更流程")
@Component(value = "contractRentChangeStartAction")
public class ContractRentChangeStartAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap);  
		//删除流程重新显示推送提醒任务
		WorkflowUtil.updatRemindTaskStatus(this.tableService,variablesMap,jbpmWorkflowHistoryInfo,"租金计划变更流程-1","0");
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
		String contract_id = variablesMap.get("contract_id");
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo,contract_id)); 
		//起租推送提醒
		WorkflowUtil.updatRemindTaskStatus(this.tableService,variablesMap,jbpmWorkflowHistoryInfo,"租金计划变更流程-1","1");
		ContractInfo contractInfo=this.contractCommService.loadContractInfo( variablesMap);
		CustInfo custInfo=contractInfo.getCustId();
        this.contractCommService.loadContractEquip(contractInfo, variablesMap);
		Map<String, String> queryMainObjectMap = new HashMap<String, String>();
		queryMainObjectMap.put("contract_id", contract_id);
		this.contractCommService.loadContractRentCalculation(contractInfo,variablesMap);
		variablesMap.put("framework_condition_old.custname", custInfo.getCustName());
		// 获得一个流水号
		String doc_id = jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid() + "";
		//存入商务条件的现数据
		variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(contractInfo.getContractCondition(), null,"framework_condition"));
		variablesMap.put("json_fund_rent_plan_new_str",variablesMap.get("json_fund_rent_plan_str"));
		variablesMap.put("json_fund_cash_flow_new_str",variablesMap.get("json_fund_cash_flow_str"));
		variablesMap.put("json_fund_fund_charge_new_str",variablesMap.get("json_fund_fund_charge_str"));
	    this.contractCommService.loadContractRentCalculationParam(contract_id, custInfo.getCustName(), custInfo.getId(), doc_id, "cont_process", variablesMap);
		RentCalculateHelper.saveConditionDataToTemp(contractInfo, doc_id);
	}

	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 

	}
}
