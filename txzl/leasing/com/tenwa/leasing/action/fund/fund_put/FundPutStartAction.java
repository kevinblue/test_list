package com.tenwa.leasing.action.fund.fund_put;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.fund.ContractFundFundCharge;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.service.contractcomm.ContractInfoExtends;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;
import com.tenwa.leasing.utils.WorkflowUtil;
 

@WorkflowAction(name = "fundPutStartAction", description = "流程开始动作", workflowName = "实际投放")
@Component(value = "fundPutStartAction")
public class FundPutStartAction implements JbpmBaseAction {
	Logger logger =Logger.getLogger(FundPutStartAction.class);
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@Resource(name = "contractInfoExtends")
	private ContractInfoExtends contractInfoExtends;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String docId=jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+"";
		variablesMap.put("docId", jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid() + "");
		String  contractid=variablesMap.get("contract_id");
		// 当前用户信息
		User user = (User) SecurityUtil.getPrincipal();
		//流程发起人
		variablesMap.put("payment_userid", user.getId());
		variablesMap.put("payment_user", user.getRealname());
		//给付款业务类型赋值：business_type.lease表示租赁形式付款，business_type.factoring表示保理形式付款
		variablesMap.put("paymenttypeid", "business_type.lease");
		//流程冲突
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, contractid)); 
		
		//获取合同基本信息
		ContractInfo contractinfo=this.tableService.findEntityByID(ContractInfo.class, contractid);
		CustInfo custInfo = contractinfo.getCustId();
		this.contractCommService.loadContractEquip(contractinfo, variablesMap);//租赁物件明细
		
		//将合同基本信息放入variablesMap
		contractInfoExtends.getContractBaseInfo(variablesMap, contractinfo, custInfo);
		
		
		this.contractCommService.loadContractNumberSetting(contractinfo, variablesMap);
		
		Map<String,String>  queryMainObjectMap =new HashMap<String,String>();
		queryMainObjectMap.clear();
		queryMainObjectMap.put("contractid", contractid);
	//	queryMainObjectMap.put("feetype", "feetype10");
		queryMainObjectMap.put("paytype", "pay_type_out");
		queryMainObjectMap.put("feetypetwo", "feetype16");
	    //设备款计划  页签2.付款计划明细
		this.fundCommMethod.initFundFundPlan("json_put_plan_str", variablesMap, queryMainObjectMap);
		//已付设备款明细 页签3 已付款明细"
		queryMainObjectMap.clear();
		queryMainObjectMap.put("contractid", contractid);
		queryMainObjectMap.put("paytype", "pay_type_out");
		this.fundCommMethod.initFundFundCharge("json_put_his_str", variablesMap, queryMainObjectMap);
		this.contractCommService.loadContractInvoice(contractinfo, variablesMap);//租金开票类型
	   //资金收款情况
	    queryMainObjectMap.clear();
	    queryMainObjectMap.put("contractid", contractid);
	    queryMainObjectMap.put("paytype", "pay_type_in");
	    //页签4 已付款明细"
	    this.fundCommMethod.initFundFundCharge("json_collection_his_str", variablesMap, queryMainObjectMap);
		
	    //加载租金回笼历史--json_fund_put_rent_income_his_str
	    queryMainObjectMap.clear();
	    queryMainObjectMap.put("contract_id", contractid);
	    queryMainObjectMap.put("orderbyclause", "order by income.planlist asc");
	    this.fundCommMethod.initFundRentIncome("json_fund_put_rent_income_his_str", variablesMap, queryMainObjectMap);
	    //加载付款前提--以前的方法  
		//this.contractCommService.loadContractPremise(contractinfo, variablesMap);
		//加载付款前提---防止方法冲突单独写了一个方法
		//this.contractCommService.loadPremiseCondition(contractinfo, variablesMap);
	}
	@Override
	public void back(HttpServletRequest request,Map<String, String> variablesMap,
    		JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	
		
	}

	@Override
	public void end(HttpServletRequest request,Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
		
	}

	@Override
	public String save(HttpServletRequest request,Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public String delete(HttpServletRequest request,Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap);  
		return null;
	}
	
}
		    	