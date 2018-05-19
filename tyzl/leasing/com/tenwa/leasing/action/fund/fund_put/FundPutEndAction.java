package com.tenwa.leasing.action.fund.fund_put;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.util.BeanUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.reckon.entity.contract.reckon.cash.ContractCashDetail;
import com.reckon.entity.contract.reckon.condition.ContractCondition;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.AppStaticUtil;
import com.tenwa.kernal.utils.UUIDUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.fund.ContractFundFundCharge;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;
import com.tenwa.leasing.utils.WorkflowUtil;


@WorkflowAction(name = "fundPutEndAction", description = "流程结束动作", workflowName = "实际投放")
@Component(value = "fundPutEndAction")
public class FundPutEndAction implements JbpmBaseAction {
	Logger logger = Logger.getLogger(FundPutEndAction.class);
	
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap);
		
		//获取原始合同信息
		ContractInfo oldcinfo=this.tableService.findEntityByID(ContractInfo.class, variablesMap.get("contract_info.id"));
		//保存合同信息
		String proj_id = variablesMap.get("contract_info.contractid");
		Map<String,Object> queryMainObjectMap = new HashMap<String,Object>();
		queryMainObjectMap.put("contractId", proj_id);
		variablesMap.put("contract_info.contractstatus", String.valueOf(AppStaticUtil.CONTRACT_APPROVAL));
		variablesMap.put("contract_info.id","");
		ContractInfo contractInfo =new ContractInfo();
		this.tableService.copyAndOverrideExistedValueFromStringMap(variablesMap, contractInfo, null,"contract_info");
		
		contractInfo.setContractId(null);
		contractInfo.setContractNumber(null);
		contractInfo.setSupContractId(oldcinfo.getId());
		contractInfo.setContractPutNumber(variablesMap.get("put_info.putnum"));
		//contractInfo.setId(UUIDUtil.getUUID());
		this.tableService.saveOrUpdateEntity(contractInfo);
		
		int a=Integer.parseInt("sss");
		//保存交易结构和租金计划，现金流，资金计划
		ContractCondition contractCondition   =  new ContractCondition();
		String docid = variablesMap.get("docid");
		
		Map<String, String> dictDataClassMapping = new HashMap<String, String>();
		dictDataClassMapping.put("DictionaryData", "code");// 所有数据字典都遵循用code传值
		dictDataClassMapping.put("CustInfo", "id");
		contractCondition = this.tableService.copyAndOverrideExistedValueFromStringMap(variablesMap, contractCondition, dictDataClassMapping,true);
		contractCondition.setContractId(contractInfo);
		contractCondition.setDocId(docid);
		contractCondition.setId(UUIDUtil.getUUID());
		this.tableService.saveEntity(contractCondition);
		
//		// 2.2合同租金计划
//		String json_fund_rent_plan_str = variablesMap.get("json_fund_rent_plan_str");
//		this.tableService.updateOneToManyCollections(contractInfo,"contractFundRentPlans", ContractFundRentPlan.class,"contractId", json_fund_rent_plan_str, null);
//	
		// 2.3合同现金流
//		String json_fund_cash_flow_str = variablesMap.get("json_fund_cash_flow_str");
//		this.tableService.updateOneToManyCollections(contractInfo,"contractCashDetails", ContractCashDetail.class, "contractId",json_fund_cash_flow_str, null);
//		//保存交易结构和租金计划,测算规则
		this.contractCommService.saveContractRentCalculationBeforePut(contractInfo, variablesMap);
		
		// 2.4资金计划

		String json_contract_fund_charge_str = variablesMap.get("json_fund_fund_charge_str");
		JSONArray arrs=new JSONArray(json_contract_fund_charge_str);
		List<ContractFundFundPlan> cplist=new ArrayList<ContractFundFundPlan>();
		List<ContractFundFundCharge> cclist=new ArrayList<ContractFundFundCharge>();
		
		for (int i=0;i<arrs.length();i++){
			JSONObject jsonObj=(JSONObject) arrs.get(i);
			ContractFundFundPlan cp=new ContractFundFundPlan();
			this.tableService.copyAndOverrideExistedValueFromJSONObject(jsonObj, cp, null,"");
			cp.setContractId(contractInfo);
			cplist.add(cp);
			
			//this.tableService.saveOrUpdateEntity(cp);
			//如果是设备款
			if("feetype10".equals(cp.getFeeType().getId())){
				String json_put_current_str = variablesMap.get("json_put_current_str");
				JSONArray puts=new JSONArray(json_put_current_str);
				for(int j=0;j<puts.length();j++){
					JSONObject jsonput=(JSONObject) puts.get(j);
					ContractFundFundCharge cc=new ContractFundFundCharge();
					this.tableService.copyAndOverrideExistedValueFromJSONObject(jsonput, cc, null,"");
					cc.setContractId(contractInfo);
					cc.setFundFundChargePlan(cp);
					cclist.add(cc);
					//this.tableService.saveOrUpdateEntity(cc);
				}
			}else{
				//货扣资金
				String json_put_current_str = variablesMap.get("json_caution_money_refund_detail_str");
				JSONArray refunds=new JSONArray(json_put_current_str);
				for(int k=0;k<refunds.length();k++){
					JSONObject jsonfund=(JSONObject) refunds.get(k);
					String feetype=jsonfund.getString("feetype");
					if("pay_type_in".equals(cp.getPayType().getId())&&feetype.equals(cp.getFeeType().getId())){
						ContractFundFundCharge cc=new ContractFundFundCharge();
						this.tableService.copyAndOverrideExistedValueFromJSONObject(jsonfund, cc, null,"");
						cc.setContractId(contractInfo);
						cc.setFundFundChargePlan(cp);
						cclist.add(cc);
						//this.tableService.saveOrUpdateEntity(cc);
						break;
					}
				}
			}
			
		}
		this.tableService.saveAllEntities(cplist);
		
		
		this.tableService.saveAllEntities(cclist);
	    //保存货扣租金
	    //fundCommMethod.saveFundRentIncome("json_rent_income_detail_str", variablesMap);
	    //throw new BusinessException("");
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 
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
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}
}
