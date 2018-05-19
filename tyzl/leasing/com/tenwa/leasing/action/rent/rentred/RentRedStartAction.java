package com.tenwa.leasing.action.rent.rentred;

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

/**
 * 
 * @author lichaojie
 *
 */
@WorkflowAction(name = "rentRedStartAction", description = "流程开始动作", workflowName = "租金红冲流程")
@Component(value = "rentRedStartAction")
public class RentRedStartAction implements JbpmBaseAction {

	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name = "contractInfoExtends")
	private ContractInfoExtends contractInfoExtends;
	

	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
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
		//查询出原始合同的基本信息
			ContractInfo oldcinfo=this.tableService.findEntityByID(ContractInfo.class,contractInfo.getSupContractId());
			variablesMap.put("contract_info.contractid",oldcinfo.getContractId());
		    variablesMap.put("contract_info.contractnumber",oldcinfo.getContractNumber());
		

			//投放明细
			this.contractCommService.loadContractFundPut(contractInfo, variablesMap);
		
		Map<String, String> queryMainObjectMap = new HashMap<String, String>();
		queryMainObjectMap.put("contract_id", contract_id);	
		//租金回笼历史
		queryMainObjectMap.put("orderbyclause", "order by income.planlist asc");
		this.fundCommMethod.initFundRentIncome("json_rent_income_his_str", variablesMap, queryMainObjectMap);
	}
}
