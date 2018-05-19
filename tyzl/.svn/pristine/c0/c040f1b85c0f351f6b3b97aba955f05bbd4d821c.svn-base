package com.tenwa.leasing.action.rent.rentincome;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.base.FundEbankProcess;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.service.contractcomm.ContractInfoExtends;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;
import com.tenwa.leasing.utils.WorkflowUtil;

/**
 * 
 * @author lichaojie
 *
 */
@WorkflowAction(name = "rentIncomeStartAction", description = "流程开始动作", workflowName = "租金回笼流程")
@Component(value = "rentIncomeStartAction")
public class RentIncomeStartAction implements JbpmBaseAction {

	
	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name = "contractInfoExtends")
	private ContractInfoExtends contractInfoExtends;
	
	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap); 
		 //删除网银过程表
	    fundCommMethod.deleteEbankProcessFlow(variablesMap);
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
		String ebank_id = variablesMap.get("ebank_id");
		ContractInfo contractInfo =(ContractInfo) this.tableService.findEntityByID(ContractInfo.class, contract_id);
		CustInfo custInfo = contractInfo.getCustId();
		//将合同基本信息放入variablesMap
		contractInfoExtends.getContractBaseInfo(variablesMap, contractInfo, custInfo);
		
		//查询出原始合同的基本信息
		ContractInfo oldcinfo=this.tableService.findEntityByID(ContractInfo.class,contractInfo.getSupContractId());
		variablesMap.put("contract_info.contractid",oldcinfo.getContractId());
		variablesMap.put("contract_info.contractnumber",oldcinfo.getContractNumber());
	    
		//联合承租人
		contractCommService.loadContractUnionCust(contractInfo, variablesMap);
		

		//投放明细
		this.contractCommService.loadContractFundPut(contractInfo, variablesMap);
		
		//流程冲突
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, contract_id)); 
		//获取网银信息
		FundEbankData fundEbank=this.fundCommMethod.initFundEbankInfo(variablesMap);
		//查入网银过程 表
		this.fundCommMethod.saveEbankProcessFlow(contract_id, fundEbank, variablesMap, jbpmWorkflowHistoryInfo);
		Map<String, String> queryMainObjectMap = new HashMap<String, String>();
		//租金计划
		queryMainObjectMap.put("contract_id", contract_id);
		queryMainObjectMap.put("plan_date",variablesMap.get("fund_ebank_data.factdate"));
		this.fundCommMethod.initFundRentPlan("json_rent_income_plan_str", variablesMap, queryMainObjectMap);
		
		//租金回笼历史
		queryMainObjectMap.put("orderbyclause", "order by income.planlist asc");
		this.fundCommMethod.initFundRentIncome("json_rent_income_his_str", variablesMap, queryMainObjectMap);
		
	}
}
