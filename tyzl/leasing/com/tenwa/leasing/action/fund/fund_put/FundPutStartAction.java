package com.tenwa.leasing.action.fund.fund_put;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.BusException;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.reckon.entity.contract.reckon.condition.ContractCondition;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
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
	
	@Resource(name = "contractInfoExtends")
	private ContractInfoExtends contractInfoExtends;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
		
		String  contractid=variablesMap.get("contract_id");
		//流程冲突
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, contractid)); 
		
		//获取合同基本信息
		ContractInfo contractinfo=this.tableService.findEntityByID(ContractInfo.class, contractid);
		
		CustInfo custInfo = contractinfo.getCustId();
		//将合同基本信息放入variablesMap
		contractInfoExtends.getContractBaseInfo(variablesMap, contractinfo, custInfo);
		//联合承租人
		contractCommService.loadContractUnionCust(contractinfo, variablesMap);
		//投放基本信息
		//查询投放合同的个数
		Map<String, Object> propertiesMap=new HashMap<String, Object>();
		propertiesMap.put("supContractId", contractid);
		List<ContractInfo> listcinfo=this.tableService.findEntityByProperties(ContractInfo.class, propertiesMap);
		ContractCondition cc=contractinfo.getContractCondition();
		variablesMap.put("put_info.putnum", contractinfo.getContractNumber()+"-"+(listcinfo.size()+1));
		variablesMap.put("put_info.putallequipamt", cc.getEquipAmt().toString());
		
		Map<String,String> queryMap = new HashMap<String,String>();
		queryMap.put("contractid",contractid);
		JSONArray exportdata=this.tableService.getJsonArrayData("eleasing/workflow/fund/fund_put/fund_put_open_list.xml",queryMap);
		if(exportdata.length()>0){
			JSONObject opj=exportdata.getJSONObject(0);
			variablesMap.put("put_info.putalreadyequipamt",opj.getString("factmoney"));
			variablesMap.put("put_info.putoverplusequipamt",opj.getString("overmoney"));
		}else{
			variablesMap.put("put_info.putalreadyequipamt","0");
			variablesMap.put("put_info.putoverplusequipamt", cc.getEquipAmt().toString());
		}
		String flowunid=jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+ "";;
		contractCommService.loadContractRentCalculationParam(contractid, custInfo.getCustName(), custInfo.getId(), flowunid,"cont_process", variablesMap);
		//商务条件，租金计划，资金计划，现金流
		this.contractCommService.loadContractRentCalculationFromBefore(contractinfo, variablesMap);
		
		Map<String,String>  queryMainObjectMap =new HashMap<String,String>();
		queryMainObjectMap.clear();
		queryMainObjectMap.put("sup_contract_id", contractid);
		queryMainObjectMap.put("feetype", "feetype10");
		queryMainObjectMap.put("paytype", "pay_type_out");
	    //已付设备款明细
		this.fundCommMethod.initFundFundCharge("json_put_his_str", variablesMap, queryMainObjectMap);
	   //资金收款情况
	    queryMainObjectMap.clear();
	    queryMainObjectMap.put("contractid", contractid);
	    queryMainObjectMap.put("paytype", "pay_type_in");
	    this.fundCommMethod.initFundFundCharge("json_collection_his_str", variablesMap, queryMainObjectMap);
	 		
	    
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
		    	