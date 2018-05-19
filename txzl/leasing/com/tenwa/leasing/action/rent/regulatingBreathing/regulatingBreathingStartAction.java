package com.tenwa.leasing.action.rent.regulatingBreathing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.reckon.entity.contract.reckon.cash.ContractCashDetailTemp;
import com.reckon.entity.contract.reckon.cash.ContractFinaCashDetailTemp;
import com.reckon.entity.contract.reckon.condition.ContractConditionTemp;
import com.reckon.entity.contract.reckon.fina.ContractFinaRentPlanTemp;
import com.reckon.entity.contract.reckon.fund.ContractFundRentPlanTemp;
import com.reckon.entity.contract.reckon.fund.FundAdjustInterestContractTemp;
import com.reckon.entity.interest.FundStandardInterest;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "regulatingBreathingStartAction", description = "流程开始动作", workflowName = "调息流程")
@Component(value = "regulatingBreathingStartAction")
public class regulatingBreathingStartAction implements JbpmBaseAction{
	
	private static final Logger log = LoggerFactory.getLogger(regulatingBreathingStartAction.class);
	
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "baseService")
	private BaseService baseService;
	
	//@Resource(name="ajustInterestService")
	//private AjustInterestService ajustInterestService;
	
	@Override
	public void back(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}
	@Override
	public String delete(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		WorkflowUtil.deleteWorkFlowConflict(tableService, variablesMap);
		
		String docid    = variablesMap.get("docid");
		log.debug("docid:"+docid);
		//删除临时表数据
		this.removeConditionDataFromTemp(docid, tableService);
		
		/*这段主要是OCC将调息前后HIS数据删除掉，5.1流程中不入租金计划his表
		//删除流程时需要将HIS表数据清楚掉
		//.........................................................................
		if( Tools.isNullOrEmpty(docid) ){
		//	throw new Exception("调息流程为空，无法删除该流程草稿!");
		}else{
			//执行调息撤销时，将调息前后HIS数据删除掉
			this.ajustInterestService.deleteHisAllSqlOfTx(docid);
		}
		//.........................................................................
		*/
		return null;
	}
	@Override
	public void end(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
	}
	@Override
	public String save(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}
	@Override
	public void start(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String flowunid=String.valueOf(jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid());
		variablesMap.put("Central_Bank_id", variablesMap.get("id"));
		variablesMap.put("flowunid", flowunid);
		//查询最新的央行调息记录
		List<Map<String,Object>> list = this.baseService.queryListBySql("select max(START_DATE_) startdate from FUND_STANDARD_INTEREST ");
		Map<String, Object> propertiesMap=new HashMap<String, Object>();
		propertiesMap.put("startDate", list.get(0).get("startdate"));
		FundStandardInterest fajc=this.baseService.findEntityByProperties(FundStandardInterest.class, propertiesMap).get(0);
		
		//央行信息表
		variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(fajc, null, "fund_standard_interest"));
		variablesMap.put("adjustid", variablesMap.get("id"));
		variablesMap.put("docid", flowunid);
		variablesMap.put("adjusttype", "onhire");//调息类型为起租后调息
		variablesMap.put("keyword_workflowid", flowunid);
	}
	/**
	 * 删除临时表里的数据
	 * @param contract_id
	 * @param doc_id
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void removeConditionDataFromTemp(String doc_id,BaseService tService)throws Exception {
		Map<String, Object>	propertiesMap=new HashMap<String, Object>();
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
		{//6.删除合同进行央行调息记录(临时)
			List<FundAdjustInterestContractTemp> faict = tService.findEntityByProperties(FundAdjustInterestContractTemp.class, propertiesMap);
			if (!faict.isEmpty()&&faict.size()>0) {
				this.tableService.removeAllEntites(faict);
			}
		}
	}
}
