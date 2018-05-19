package com.tenwa.leasing.action.rent.penaltyadjust;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.service.contractcomm.ContractInfoExtends;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;
import com.tenwa.leasing.utils.WorkflowUtil;

/**
 * 罚息减免流程
 * @Title: PenaltyAdjustEndAction.java
 * @package: com.tenwa.leasing.action.rent.penaltyadjust
 * @author: tpf
 * @date 2014年12月9日 下午3:05:24 
 * @version V5.1
 */
@WorkflowAction(name = "penaltyAdjustStartAction", description = "流程开始动作", workflowName = "罚息减免流程")
@Component(value = "penaltyAdjustStartAction")
public class PenaltyAdjustStartAction implements JbpmBaseAction {

	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "contractInfoExtends")
	private ContractInfoExtends contractInfoExtends;
	
	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
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
		String contract_id = variablesMap.get("contract_id");
		
		ContractInfo contractInfo =(ContractInfo) this.tableService.findEntityByID(ContractInfo.class, contract_id);
		CustInfo custInfo = contractInfo.getCustId();
		
		//将合同基本信息放入variablesMap
		contractInfoExtends.getContractBaseInfo(variablesMap, contractInfo, custInfo);
		Map<String, String> queryMainObjectMap = new HashMap<String, String>();
		queryMainObjectMap.put("contract_id", contract_id);
		
		//租金计划
		queryMainObjectMap.put("contract_id", contract_id);
		queryMainObjectMap.put("plan_date",DateUtil.getSystemDate());
		this.fundCommMethod.initFundRentPlan("json_rent_income_plan_str", variablesMap, queryMainObjectMap);
		
		//租金回笼历史
		queryMainObjectMap.put("orderbyclause", "order by income.planlist asc");
		this.fundCommMethod.initFundRentIncome("json_rent_income_his_str", variablesMap, queryMainObjectMap);
		
	}
}
