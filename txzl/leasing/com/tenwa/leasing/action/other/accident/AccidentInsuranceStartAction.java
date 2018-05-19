package com.tenwa.leasing.action.other.accident;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.reckon.entity.contract.reckon.condition.ContractCondition;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;
import com.tenwa.leasing.entity.other.ThingDisposition;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "accidentInsuranceStartAction", description = "流程开始动作", workflowName = "出险流程")
@Component(value = "accidentInsuranceStartAction")
public class AccidentInsuranceStartAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;

	@Override
	public void start(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String custid = variablesMap.get("custid");
	
		CustInfo custInfo = this.tableService.findEntityByID(CustInfo.class, custid);
		variablesMap.put("cust_info.custname", custInfo.getCustName());
		variablesMap.put("cust_info.custnumber", custInfo.getCustNumber());
		
		if (custInfo.getCustClass().getCode().equals("CUST_INFO_COMPANY")) {
			variablesMap.put("cust_info.custkind",
					custInfo.getCustInfoCompany().getCustKind() == null ? "" : 
					custInfo.getCustInfoCompany().getCustKind().getName() );
			variablesMap.put("cust_info.personrep",custInfo.getCustInfoCompany().getPersonRep());
			variablesMap.put("cust_info.code",custInfo.getCustInfoCompany().getOrgCode());
		} else {
			variablesMap.put("cust_info.custkind",
					custInfo.getCustInfoPerson().getCustKind() == null ? "" : 
					custInfo.getCustInfoPerson().getCustKind().getName() );
			variablesMap.put("cust_info.code",custInfo.getCustInfoPerson().getIdCardNo());
		}
		String xsnum = WorkflowUtil.getCustPatrolSerialNumber(variablesMap, 
				this.tableService.getBussinessDao().getHibernateTemplate(), 
				this.tableService.getBussinessDao().getJdbcTemplate());
		 variablesMap.put("contract_patrol_info.xsnum",xsnum);

		/*基本信息*/
		String custinfo=contractCommService.getcustinfo(custid);
		JSONArray jsonarray=new JSONArray(custinfo);
		for(int i=0;i<jsonarray.length();i++){
			JSONObject jsonobject=jsonarray.getJSONObject(i);			
			variablesMap.put("cust_info.belongingpeoplename", jsonobject.getString("username1"));
			variablesMap.put("cust_info.deptname", jsonobject.getString("deptname1"));
			variablesMap.put("cust_info.deptrote", jsonobject.getString("deptroute1"));
			variablesMap.put("cust_info.rentmoney", jsonobject.getString("rent"));
			variablesMap.put("cust_info.corpusmoney", jsonobject.getString("corpus"));
		}
		
		//担保单位信息
		ContractInfo contractInfo = this.contractCommService.loadContractInfo( variablesMap);
		this.contractCommService.loadContractGuaranteeMethod(contractInfo,variablesMap);
		String guaranteeMethodStr = variablesMap.get("json_contract_guarantee_method_str");
		JSONArray jsonArray = new JSONArray(guaranteeMethodStr);
		String assurorkey ="";
		List<String> list = new LinkedList<String>();
		for (int i = 0, len=jsonArray.length(); i < len; i++) 
		{
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			if (!jsonObject.get("assurorname").equals("") || jsonObject.get("assurorname") != "") {
				assurorkey = jsonObject.get("assurorname").toString();
				list.add(assurorkey);
			}
		}
		variablesMap.put("contract_info.guarantee",
				list.toString().replace("[", "").replace("]", ""));
		/*
		
		HashMap<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("contractId", variablesMap.get("contract_id"));

		List<ThingDisposition> thingList = this.tableService
				.findEntityByProperties(ThingDisposition.class, propertiesMap);

		if (thingList.size() > 0) {

			ThingDisposition thingDisposition = thingList.get(0);

			variablesMap.put("contract_info.thingstatus",
					thingDisposition.getThingstatus());

		} else {

			variablesMap.put("contract_info.thingstatus", "正常");

		}

		HashMap<String, String> queryMainObjectMap = new HashMap<String, String>();
		queryMainObjectMap.put("contractid", variablesMap.get("contract_id"));

		// 获取剩余租金和本金

			JSONArray jsonData = this.tableService.getJsonArrayData(
				"eleasing/workflow/other/approve_contract_info.xml",
				queryMainObjectMap);

		JSONObject jsonObj = jsonData.getJSONObject(0);

		variablesMap.put(
				"contract_info.currentoverage",
				jsonObj.getString("currentoverage") == "" ? "0" : jsonObj
						.getString("currentoverage"));

		variablesMap.put(
				"contract_info.curcorpusoverage",
				jsonObj.getString("curcorpusoverage") == "" ? "0" : jsonObj
						.getString("curcorpusoverage"));

		HashMap<String, Object> conditionMap = new HashMap<String,Object>();
		conditionMap.put("contractId.id", variablesMap.get("contract_id"));
		
		List<ContractCondition> contractConditionList = this.tableService
				.findEntityByProperties(ContractCondition.class, conditionMap);
		
		if (contractConditionList.size() > 0) {
			ContractCondition condition = contractConditionList.get(0);
			BigDecimal cautionMoney = condition.getCautionMoney();
			variablesMap.put("contract_info.cautionmoney", cautionMoney.toString());
		} else {
			variablesMap.put("contract_info.cautionmoney", "0");
		}
		
		
		
		//总租金
		List<ContractFundRentPlan> rentList = this.tableService
				.findEntityByProperties(ContractFundRentPlan.class, conditionMap);
		
		BigDecimal allRent = BigDecimal.ZERO;
		
		if (rentList.size() > 0) {
			for (int i = 0; i < rentList.size(); i++) {
				BigDecimal rent = rentList.get(i).getRent();
				allRent = allRent.add(rent);
			}
		}
		allRent.setScale(2);
		variablesMap.put("contract_info.allrent", allRent.toString());
		
		
		
		//逾期信息
		JSONArray accidentarr = this.tableService.getJsonArrayData(
				"eleasing/workflow/other/accident_info.xml",
				queryMainObjectMap);

		if(accidentarr.length()>0){
			JSONObject accidentjson = accidentarr.getJSONObject(0);
			
			variablesMap.put("contract_info.overduedate", accidentjson.get("plan_date")==""?"":accidentjson.get("plan_date").toString());
			variablesMap.put("contract_info.overduemoney", accidentjson.get("penalty")==""?"":accidentjson.get("penalty").toString());
		}*/
		
	}

	@Override
	public String delete(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}

	@Override
	public String save(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}
}
