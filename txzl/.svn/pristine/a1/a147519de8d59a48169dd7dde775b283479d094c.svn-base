package com.tenwa.leasing.action.other.follow;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.reckon.entity.contract.reckon.condition.ContractCondition;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.other.ThingDisposition;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.contractcomm.ContractCommService;

@WorkflowAction(name = "thingFollowStartAction", description = "流程开始动作", workflowName = "重大事项跟踪流程")
@Component(value = "thingFollowStartAction")
public class ThingFollowStartAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
		ContractInfo contractInfo = this.contractCommService.loadContractInfo( variablesMap);

		HashMap<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("dnum", variablesMap.get("dnum"));

		List<ThingDisposition> thingList = this.tableService
				.findEntityByProperties(ThingDisposition.class, propertiesMap);

		if (thingList.size() > 0) {

			ThingDisposition thingDisposition = thingList.get(0);

			variablesMap.put("contract_info.thingstatus",
					thingDisposition.getThingstatus() == null ? ""
							: thingDisposition.getThingstatus());
			
			variablesMap.put("thing_disposition.thingmemo",
					thingDisposition.getThingmemo() == null ? ""
							: thingDisposition.getThingmemo());
			
			variablesMap.put("thing_disposition.occurdate",
					thingDisposition.getOccurdate() == null ? ""
							: thingDisposition.getOccurdate());
			
			variablesMap.put("thing_disposition.followstep",
					thingDisposition.getFollowstep() == null ? ""
							: thingDisposition.getFollowstep());
			
			variablesMap.put("thing_disposition.overplan",
					thingDisposition.getOverplan() == null ? ""
							: thingDisposition.getOverplan());
			
			variablesMap.put("thing_disposition.thingstatus",
					thingDisposition.getThingstatus() == null ? ""
							: thingDisposition.getThingstatus());
			
			variablesMap.put("thing_disposition.assettype",
					thingDisposition.getAssettype() == null ? ""
							: thingDisposition.getAssettype());
			
			DictionaryData assettype = this.tableService.findEntityByID(DictionaryData.class,thingDisposition.getAssettype());
			variablesMap.put("rawValue_thing_disposition.assettype",
					thingDisposition.getAssettype() == null ? ""
							: assettype.getName());

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
		
		//担保单位信息
		this.contractCommService.loadContractGuaranteeMethod(contractInfo, variablesMap);
		//抵押物信息
		this.contractCommService.loadContractGuaranteeEquip(contractInfo, variablesMap);
		
	}
	
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}
	
	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}
}
