package com.tenwa.leasing.action.contract.rentModify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.reckon.commons.helper.ObjectConvertUtils;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.entity.contract.reckon.cash.ContractCashDetail;
import com.reckon.entity.contract.reckon.cash.ContractCashDetailTemp;
import com.reckon.entity.contract.reckon.cash.ContractFinaCashDetail;
import com.reckon.entity.contract.reckon.cash.ContractFinaCashDetailTemp;
import com.reckon.entity.contract.reckon.condition.ContractConditionTemp;
import com.reckon.entity.contract.reckon.fina.ContractFinaRentPlan;
import com.reckon.entity.contract.reckon.fina.ContractFinaRentPlanTemp;
import com.reckon.entity.contract.reckon.fund.ContractFundRentPlanTemp;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.contract.ContractInvoiceType;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.service.contractcomm.ContractInfoExtends;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;
import com.tenwa.leasing.utils.WorkflowUtil;
@WorkflowAction(name = "contractRentModifyStartAction", description = "租金修改开始动作", workflowName = "租金计划修改")
@Component(value = "contractRentModifyStartAction")
public class ContractRentModifyStartAction implements JbpmBaseAction{
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	
	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
	
	@Override
	public void start(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String contract_id = variablesMap.get("contract_id");
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo,contract_id)); 
		ContractInfo contractInfo=this.contractCommService.loadContractInfo( variablesMap);
		CustInfo custInfo=contractInfo.getCustId();
        this.contractCommService.loadContractEquip(contractInfo, variablesMap);
        
		//查询出原始合同的基本信息
		ContractInfo oldcinfo=this.tableService.findEntityByID(ContractInfo.class,contractInfo.getSupContractId());
		variablesMap.put("contract_info.contractid",oldcinfo.getContractId());
		variablesMap.put("contract_info.contractnumber",oldcinfo.getContractNumber());
        
		Map<String, String> queryMainObjectMap = new HashMap<String, String>();
		queryMainObjectMap.put("contract_id", contract_id);
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
	    //租金回笼历史
		queryMainObjectMap.put("orderbyclause", "order by planlist asc");
		this.fundCommMethod.initFundRentIncome("json_rent_income_his_str", variablesMap, queryMainObjectMap);
		
	    RentCalculateHelper.saveConditionDataToTemp(contractInfo, doc_id);
	    this.saveConditionDataToTemp(contractInfo, doc_id, tableService);
		
	}

	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void end(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String save(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap);  
		return null;
	}
	
	public void saveConditionDataToTemp(ContractInfo contractInfo,String doc_id,BaseService tService)throws Exception{
		String contract_id=contractInfo.getContractId();
		Map<String, Object>	propertiesMap=new HashMap<String, Object>();
		propertiesMap.put("contractId", contract_id);
		propertiesMap.put("docId", doc_id);
		removeConditionDataFromTemp( contract_id, doc_id,this.tableService);
		{//1.商务条件
			ContractConditionTemp contractCondition=new ContractConditionTemp();
			//复制数据
			Map<String,Object> objectParams = ObjectConvertUtils.convertBeanToMap(contractInfo.getContractCondition());
			//ObjectConvertUtils.convertMapToBean(type, map)
			contractCondition= ObjectConvertUtils.convertMapToBean(ContractConditionTemp.class, objectParams); 
			contractCondition.setContractId(contract_id);
			contractCondition.setDocId(doc_id);
			//写入
			this.tableService.saveEntity(contractCondition);
		}
		{//2.租金计划
			List<ContractFundRentPlanTemp> contractFundRentPlanTemps=new ArrayList<ContractFundRentPlanTemp>();
			//复制数据
			for(ContractFundRentPlan temp:contractInfo.getContractFundRentPlans()){
				ContractFundRentPlanTemp contractFundRentPlanTemp=new ContractFundRentPlanTemp();
				contractFundRentPlanTemp=(ContractFundRentPlanTemp)tService.copyAndOverrideExistedValueFromObject(temp, contractFundRentPlanTemp);
				contractFundRentPlanTemp.setContractId(contract_id);
				contractFundRentPlanTemp.setDocId(doc_id);
				contractFundRentPlanTemps.add(contractFundRentPlanTemp);
			}
			//写入
			tService.saveAllEntities(contractFundRentPlanTemps);
		}
		{//3.会计租金计划
			List<ContractFinaRentPlanTemp> contractFinaRentPlanTemps=new ArrayList<ContractFinaRentPlanTemp>();
			//复制数据
			for(ContractFinaRentPlan temp:contractInfo.getContractFinaRentPlans()){
				ContractFinaRentPlanTemp contractFinaRentPlanTemp=new ContractFinaRentPlanTemp();
				contractFinaRentPlanTemp=(ContractFinaRentPlanTemp)tService.copyAndOverrideExistedValueFromObject(temp, contractFinaRentPlanTemp);
				contractFinaRentPlanTemp.setContractId(contract_id);
				contractFinaRentPlanTemp.setDocId(doc_id);
				contractFinaRentPlanTemps.add(contractFinaRentPlanTemp);
			}
			//写入
			tService.saveAllEntities(contractFinaRentPlanTemps);
		}
		{//4.现金流
			//先删除
			List<ContractCashDetailTemp> contractCashDetailTemps=new ArrayList<ContractCashDetailTemp>();
			//复制数据
			for(ContractCashDetail temp:contractInfo.getContractCashDetails()){
				ContractCashDetailTemp contractCashDetailTemp=new ContractCashDetailTemp();
				contractCashDetailTemp=(ContractCashDetailTemp)tService.copyAndOverrideExistedValueFromObject(temp, contractCashDetailTemp);
				contractCashDetailTemp.setContractId(contract_id);
				contractCashDetailTemp.setDocId(doc_id);
				contractCashDetailTemps.add(contractCashDetailTemp);
			}
			//写入
			tService.saveAllEntities(contractCashDetailTemps);
		}
		{//5.会计现金流
			//先删除
			List<ContractFinaCashDetailTemp> contractFinaCashDetailTemps=new ArrayList<ContractFinaCashDetailTemp>();
			//复制数据
			for(ContractFinaCashDetail temp:contractInfo.getContractFinaCashDetails()){
				ContractFinaCashDetailTemp contractFinaCashDetailTemp=new ContractFinaCashDetailTemp();
				contractFinaCashDetailTemp=(ContractFinaCashDetailTemp)tService.copyAndOverrideExistedValueFromObject(temp, contractFinaCashDetailTemp);
				contractFinaCashDetailTemp.setContractId(contract_id);
				contractFinaCashDetailTemp.setDocId(doc_id);
				contractFinaCashDetailTemps.add(contractFinaCashDetailTemp);
			}
			//写入
			tService.saveAllEntities(contractFinaCashDetailTemps);
		}
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void removeConditionDataFromTemp(String contract_id, String doc_id,BaseService tService)throws Exception {
		Map<String, Object>	propertiesMap=new HashMap<String, Object>();
		propertiesMap.put("contractId", contract_id);
		propertiesMap.put("docId", doc_id);
		{//1.商务条件
			//先删除
			List<ContractConditionTemp> contractConditionTemps=new ArrayList<ContractConditionTemp>();
			contractConditionTemps=tService.findEntityByProperties(ContractConditionTemp.class, propertiesMap);
			if(contractConditionTemps!=null&&contractConditionTemps.size()>0){
				tService.removeAllEntites(contractConditionTemps);
			}
		}
		{//2.租金计划
			//先删除
			List<ContractFundRentPlanTemp> contractFundRentPlanTemps=new ArrayList<ContractFundRentPlanTemp>();
			contractFundRentPlanTemps=tService.findEntityByProperties(ContractFundRentPlanTemp.class, propertiesMap);
			if(contractFundRentPlanTemps!=null&&contractFundRentPlanTemps.size()>0){
				tService.removeAllEntites(contractFundRentPlanTemps);
			}
		}
		{//3.会计租金计划
			//先删除
			List<ContractFinaRentPlanTemp> contractFinaRentPlanTemps=new ArrayList<ContractFinaRentPlanTemp>();
			contractFinaRentPlanTemps=tService.findEntityByProperties(ContractFinaRentPlanTemp.class, propertiesMap);
			if(contractFinaRentPlanTemps!=null&&contractFinaRentPlanTemps.size()>0){
				tService.removeAllEntites(contractFinaRentPlanTemps);
			}
		}
		{//4.现金流
			//先删除
			List<ContractCashDetailTemp> contractCashDetailTemps=new ArrayList<ContractCashDetailTemp>();
			contractCashDetailTemps=tService.findEntityByProperties(ContractCashDetailTemp.class, propertiesMap);
			if(contractCashDetailTemps!=null&&contractCashDetailTemps.size()>0){
				tService.removeAllEntites(contractCashDetailTemps);
			}
		}
		{//5.会计现金流
			//先删除
			List<ContractFinaCashDetailTemp> contractFinaCashDetailTemps=new ArrayList<ContractFinaCashDetailTemp>();
			contractFinaCashDetailTemps=tService.findEntityByProperties(ContractFinaCashDetailTemp.class, propertiesMap);
			if(contractFinaCashDetailTemps!=null&&contractFinaCashDetailTemps.size()>0){
				tService.removeAllEntites(contractFinaCashDetailTemps);
			}
		}
	}

}
