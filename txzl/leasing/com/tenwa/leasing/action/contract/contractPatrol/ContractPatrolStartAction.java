package com.tenwa.leasing.action.contract.contractPatrol;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.contract.ContractPatrolInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.utils.WorkflowUtil;

/**
 * @author Jason
 * @date 2013-4-24
 * @info
 */
@WorkflowAction(name = "contractPatrolStartAction", description = "流程开始动作", workflowName = "租后巡视")
@Component(value = "contractPatrolStartAction")
public class ContractPatrolStartAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;

 
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
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
		String selectyear = variablesMap.get("selectyear");//所选年
		
		String id = variablesMap.get("id");//所选年
		String projid= variablesMap.get("projid");//项目编号
		String custid = variablesMap.get("custid");//承租人
		String industryType = variablesMap.get("industryType");//企业类型
		String contractid=variablesMap.get("contractid");
		String quarter=variablesMap.get("quarter");//季度
		String guarantee=variablesMap.get("guarantee");//主要担保人
		String hql = " from ContractPatrolInfo  where contractId.id  = ? and custInfo.id= ? order by createDate desc";
	    List<ContractPatrolInfo> queryList = this.tableService.findResultsByHSQL(hql,contractid,custid);
	    if(queryList.size()>0){
	    	ContractPatrolInfo contractpatrolinfo = queryList.get(0);
	    	variablesMap.put("contract_patrol_info.mark", contractpatrolinfo.getMark());
	    }
		variablesMap.put("contract_info.id", id);
		variablesMap.put("contract_info.projid", projid);
		variablesMap.put("industryType", industryType);
		variablesMap.put("guarantee", guarantee);
		variablesMap.put("contractidselect", contractid);
		variablesMap.put("custid", custid);
		variablesMap.put("quarter", quarter);
		
		
		
		/*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String nowtime = df.format(new Date());// new Date()为获取当前系统时间
		String[] nowtimeArray =  nowtime.split("-");*/
		variablesMap.put("thisyear", selectyear);
		int thisyear = Integer.parseInt(selectyear);
		
		
		String lastyear = thisyear-1+"";
		String twoyearago = thisyear-2+"";
		String threeyearago = thisyear-3+"";
		variablesMap.put("lastyear", lastyear);
		variablesMap.put("twoyearago", twoyearago);
		variablesMap.put("threeyearago", threeyearago);
		
		//String rowvaluestr=variablesMap.get("rowvalue");//承租人基本信息
		//JSONObject jsonObject = new JSONObject(rowvaluestr);
		//项目名称
		variablesMap.put("project_name", variablesMap.get("project_name"));
		//租赁合同编号
		variablesMap.put("contractnumber", variablesMap.get("contractnumber"));
		//承租人
		variablesMap.put("cust_name",variablesMap.get("cust_name"));
		//租赁种类
		variablesMap.put("leasetype", variablesMap.get("leasetype"));
		//租赁物
		variablesMap.put("custname", variablesMap.get("custname"));
		//融资金额（万元）
		variablesMap.put("leasemoney", variablesMap.get("leasemoney"));
		//融资余额（万元）
		variablesMap.put("finbalance", variablesMap.get("financing_balance"));
		//租赁期限
		variablesMap.put("lease_term", variablesMap.get("leaseterm"));
		//融资余额（万元）
		//variablesMap.put("financing_balance", jsonObject.get("financing_balance").toString());
		//租金总期数
		variablesMap.put("rentnum", variablesMap.get("rentnum"));
		//剩余期数
		variablesMap.put("surplus", variablesMap.get("surplus"));
		//每期平均租金
		variablesMap.put("avgrent",variablesMap.get("avgrent"));
		//支付方式
		variablesMap.put("periodtype", variablesMap.get("periodtype"));
		//租金支付周期
		variablesMap.put("incometime",variablesMap.get("incometime"));
		//首次建立租赁关系时间
		variablesMap.put("department", variablesMap.get("department"));
		//逾期期数
		variablesMap.put("ouotlist", variablesMap.get("ouotlist"));
		//逾期金额
		variablesMap.put("outmoney", variablesMap.get("outmoney"));
		//应收租赁款余额
		variablesMap.put("currentoverage",variablesMap.get("currentoverage"));
		
	}
	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	 
	}
}
