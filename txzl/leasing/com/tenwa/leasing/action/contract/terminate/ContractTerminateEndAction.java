package com.tenwa.leasing.action.contract.terminate;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.reckon.entity.contract.reckon.fund.FundRentAdjust;
import com.reckon.service.RentConditionDataOperator;
import com.reckon.service.impl.RentCalculateServiceImpl;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.AppStaticUtil;
import com.tenwa.leasing.entity.contract.ContractEndInfo;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.utils.WorkflowUtil;


/**
 * 合同终止流程
 * @Title: ContractTerminateStartAction.java
 * @package: com.tenwa.leasing.action.contract.terminate
 * @author: tpf
 * @date 2014年12月11日 上午11:25:05 
 * @version V5.1
 */
@WorkflowAction(name = "contractTerminateEndAction", description = "流程结束动作", workflowName = "合同中途终止流程")
@Component(value = "contractTerminateEndAction")
public class ContractTerminateEndAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService  tableService;
	
	private static Logger logger = Logger.getLogger(RentCalculateServiceImpl.class);
	
	@Resource(name = "RentConditionDataService")
	private RentConditionDataOperator rentConditionData;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// 1.更新合同结束状态
		String  contractid=variablesMap.get("contract_info.id");
		String endDate = variablesMap.get("contract_end_info.actualenddate");
		ContractInfo contract = this.tableService.findEntityByID(ContractInfo.class, contractid);
		contract.setContractStatus(AppStaticUtil.CONTRACT_TERMINATE);
		this.tableService.updateEntity(contract);
		WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap);
		this.contractCommService.saveContractChangeInfo(contract, variablesMap);
		FundRentAdjust adjust = new FundRentAdjust();
		this.tableService.copyAndOverrideExistedValueFromStringMap(variablesMap, adjust, null,true,"fund_rent_adjust");
		adjust.setDocId(variablesMap.get("docId"));
		adjust.setContractId(contract);
		DictionaryData adjustType = new DictionaryData();
		adjustType.setId("his_contract_end");//租金变更类型
		adjust.setAdjustType(adjustType);
		this.tableService.saveOrUpdateEntity(adjust);
		this.rentConditionData.updateContractConditionDataAndSaveDatatoHis(contract, variablesMap, variablesMap.get("docId"), "his_plan_change",  "framework_condition", "json_fund_rent_plan_new_str","json_fund_cash_flow_new_str","", "","json_fund_fund_charge_new_str");
		
	}
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}
	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}
	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// TODO Auto-generated method stub
		
	}

}