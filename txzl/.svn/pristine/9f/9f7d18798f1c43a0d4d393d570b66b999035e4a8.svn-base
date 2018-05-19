package com.tenwa.leasing.action.contract.terminate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.reckon.commons.helper.RentCalculateHelper;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;
import com.tenwa.leasing.utils.WorkflowUtil;

/**
 * 合同终止流程
 * @Title: ContractTerminateStartAction.java
 * @package: com.tenwa.leasing.action.contract.terminate
 * @author: tpf
 * @date 2014年12月11日 上午11:25:05 
 * @version V5.1
 */
@WorkflowAction(name = "contractTerminateStartAction", description = "流程开始动作", workflowName = "合同中途终止流程")
@Component(value = "contractTerminateStartAction")
public class ContractTerminateStartAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String contract_id = variablesMap.get("contract_id");
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, contract_id)); 
		ContractInfo contractInfo=this.contractCommService.loadContractInfo( variablesMap);
		CustInfo custInfo=contractInfo.getCustId();
		
		Map<String, String> queryMainObjectMap = new HashMap<String, String>();
		queryMainObjectMap.put("contractid", contract_id);
		//资金计划
		this.fundCommMethod.initFundFundPlan("json_fund_payment_plan_info_str", variablesMap, queryMainObjectMap);
	    //资金实收
	    this.fundCommMethod.initFundFundCharge("json_fund_charge_his_info_str", variablesMap, queryMainObjectMap); 
		queryMainObjectMap.clear();
		//租金回笼计划
		queryMainObjectMap.put("contract_id", contract_id);
		queryMainObjectMap.put("plan_date",DateUtil.getSystemDate());
		this.fundCommMethod.initFundRentPlan("json_rent_income_plan_str", variablesMap, queryMainObjectMap);
		//租金回笼历史
		queryMainObjectMap.put("orderbyclause", "order by income.planlist asc");
		this.fundCommMethod.initFundRentIncome("json_rent_income_his_str", variablesMap, queryMainObjectMap);
		
		variablesMap.put("framework_condition.docid", jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid() + "");
		//求出提前终止中的其他应收，其他应退
		Map<String, String> fundInfo = this.getChargeInAndOut(contract_id);
		variablesMap.putAll(fundInfo);
		
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
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}
	private Map<String, String> getChargeInAndOut(String contractId) throws Exception{
		String fundSql = "select t2.sumplanin-nvl(t1.sumchargein,0) fundin,t2.sumplanout- nvl(t1.sumchargeout,0) fundout from  ( "
				+ " select sum(case ffc.pay_type when 'pay_type_in' then ffc.fact_money else 0 end )sumchargein, "
				+ " sum(case ffc.pay_type when 'pay_type_out' then ffc.fact_money else 0 end )sumchargeout, "
				+ " min(ffc.contract_id) contract_id "
				+ " from  contract_fund_fund_charge ffc  where ffc.contract_id = ? "
				+ "	)t1 right join  "
				+ "	(select sum(case ffp.pay_type when 'pay_type_in' then ffp.plan_money else 0 end )sumplanin, "
				+ "	sum(case ffp.pay_type when 'pay_type_out' then ffp.plan_money else 0 end)sumplanout, "
				+ "	min(ffp.contract_id) contract_id "
				+ "	from  contract_fund_fund_plan ffp  "
				+ " where ffp.contract_id = ? "
				+ " )t2 on t1.contract_id = t2.contract_id ";
		if(ResourceUtil.getDBType().indexOf("SQLSERVER")>-1){
			fundSql = fundSql.replaceAll("nvl", "ISNULL");
		}else if(ResourceUtil.getDBType().indexOf("MYSQL")>-1){
			fundSql = fundSql.replaceAll("nvl", "IFNULL");
		}
		System.out.println(fundSql);
		List<Map<String, Object>> list = this.tableService.queryListBySql(fundSql, contractId,contractId);
		Map<String, String> fundResult = new HashMap<String, String>();
		if(null != list && list.size() > 0 ){
			Map<String, Object> fundMap =  list.get(0);
			fundResult.put("fund_rent_adjust.otherin", fundMap.get("fundin")+"");
			fundResult.put("fund_rent_adjust.otherout", fundMap.get("fundout")+"");
		}
		Map<String, String> queryMainObjectMap = new HashMap<String, String>();
		queryMainObjectMap.put("contract_id", contractId);
		JSONArray dateList = this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_terminate/contract_fund_rent_plan_min_max_date.xml",queryMainObjectMap);
		if(null != dateList && dateList.length() >= 0 ){
			JSONObject dateObject =  dateList.getJSONObject(0);
			fundResult.put("minplandate", dateObject.getString("minplandate")) ;
			fundResult.put("maxplandate", dateObject.getString("maxplandate")) ;
		}
		return fundResult;
	}
}
