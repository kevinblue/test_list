package com.tenwa.leasing.action.rent.regulatingBreathingBack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.reckon.entity.contract.reckon.fund.FundAdjustInterestContract;
import com.reckon.entity.contract.reckon.fund.FundAdjustInterestContractTemp;
import com.reckon.entity.interest.FundStandardInterest;
import com.reckon.renttranrate.service.AjustInterestService;
import com.tenwa.business.entity.User;
import com.tenwa.business.entity.WorkFlowFlag;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;

@WorkflowAction(name = "regulatingBreathingBackEndAction", description = "流程结束动作", workflowName = "调息撤销流程")
@Component(value = "regulatingBreathingBackEndAction")
public class regulatingBreathingBackEndAction implements JbpmBaseAction{
	@Resource(name = "tableService")
	private TableService tableService;
	
	//@Resource(name = "conditionDataToHisService")
	//private ConditionDataToHisService conditionDataToHisService;
	
	@Resource(name="ajustInterestService")
	private AjustInterestService ajustInterestService;
	 
	@Override
	public void back(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}
	@Override
	public String delete(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}
	/**
	 * #.回滚流程结束操作
	 */
	@Override
	public void end(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String workFlowFlags = variablesMap.get("workFlowFlag");
		if (StringUtils.isNotEmpty(workFlowFlags)) {
			/** 流程冲突第三步-结束 */
			List<WorkFlowFlag> workFlags = this.tableService.findEntityByIDArray(WorkFlowFlag.class, workFlowFlags.split(","));
			this.tableService.removeAllEntites(workFlags);
			/** 流程冲突第三步-结束 */
		}
		System.out.println(variablesMap);
		    String docid      = variablesMap.get("docid");
		    
			if(docid!=null && !"".equals(docid)){
				//1.把调息记录临时表FUND_ADJUST_INTEREST_C_TEMP 的数据写入 FUND_ADJUST_INTEREST_CONTRACT
				this.ajustInterestService.updateCopyFundAdjustInterestContractTempToRecord(variablesMap);
				
				//将原来的调息记录表的状态改为rate_change_back
				this.updateFundAdjustInterestContractStatus(variablesMap, this.tableService);
				
				//variablesMap.put("table_for_his_type", "his_rate_change_back");
				//2.把商务条件表数据写入历史表 CONTRACT_CONDITION -->CONTRACT_CONDITION_HIS 
				//历史表中字段MOD_STATUS值为 his_status_before  字段 MOD_REASON的值为his_rate_change
				//3.租金计划表 CONTRACT_FUND_RENT_PLAN 和 现金流表 CONTRACT_CASH_DETAIL  同步骤2商务条件表的操作
				//4.把商务条件临时表  CONTRACT_CONDITION_TEMP 的数据 更新到正式表CONTRACT_CONDITION
				//5.把现金流临时表 CONTRACT_CASH_DETAIL_TEMP 的数据 更新到正式表CONTRACT_CASH_DETAIL  
				/*6.把租金计划临时表 CONTRACT_FUND_RENT_PLAN_TEMP 的数据  差异性写入 正式表CONTRACT_FUND_RENT_PLAN
			     [差异性为  如果正式表中已有的期项做修改操作,正式表中没有的期项做新增操作,临时表没有正式表有的期项 做删除操作 如果删除操作因为外键关系删除失败则友好提示,并回滚数据]*/
				/*7.把把商务条件临时表  CONTRACT_CONDITION_TEMP 的数据 更新到正式表>CONTRACT_CONDITION_HIS 
				      历史表中字段MOD_STATUS值为 his_status_after 字段 MOD_REASON的值为his_rate_change*/
				//8.租金计划表临时表 CONTRACT_FUND_RENT_PLAN_TEMP 和 现金流表 CONTRACT_CASH_DETAIL_TEMP  同步骤7商务条件表的操作
				//System.out.println("111114444");
				//this.ajustInterestService.updateCopyTxAllTempToHis(variablesMap,this.conditionDataToHisService);
				//System.out.println("2222224444");
				//this.addFundFundAdjustInterestContractToAdjustInterestContractHis(variablesMap,this.tableService);
				
				Map<String, String> model = new HashMap<String, String>();
				//String docid    = variablesMap.get("docid");
				model.put("docid", docid);
				model.put("modReason", "his_rate_change_back");
				//6.调息流程结束时，将租金计划正式表、临时表数据移往HIS表作为调息前、后记录
				this.ajustInterestService.updateCopyFundRentPlanFromTempToHisBeforeAndAfter(model,"contract_fund_rent_plan_before");
				
				//7.将租金计划临时表update到租金计划正式表中
				this.ajustInterestService.updateCopyFundRentPlanFromTempToOfficial(variablesMap);
				//6.调息流程结束时，将资金计划正式表、临时表数据移往HIS表作为调息前、后记录
				this.ajustInterestService.updateCopyFundFundPlanFromTempToHisBeforeAndAfter(model);
				//7.将资金计划临时表update到资金计划正式表中**************
				this.ajustInterestService.updateCopyFundFundPlanFromTempToOfficial(variablesMap);
				
				variablesMap = null;
			}else{
				System.out.println("选中的回滚数据字符串没有docid");
			}
	}
	/**
	 * 清空对象
	 * @param obj
	 */
	private void emptyTheVariable(Object ... obj) {if(obj!=null){for(Object o : obj){if(o != null){o = null;}}}}
	/**
	 * 将临时表数据插入到正式表
	 * @param variablesMap
	 * @param tService
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void addFundAdjustInterestContractTempToFundAdjustInterestContract(Map<String,String> variablesMap,BaseService tService) throws Exception{
		String faictid = "";
		String doc_id = "";
		String checkedData = variablesMap.get("json_pranayama_to_roll_back_the_record_str");
		JSONArray jsonArray = new JSONArray(checkedData);
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			faictid = jsonObj.getString("faictid");
			doc_id = variablesMap.get("flowunid");
			FundAdjustInterestContractTemp fundadjustinterestcontracttemp = tService.findEntityByID(FundAdjustInterestContractTemp.class, faictid);
			FundAdjustInterestContract faic = new FundAdjustInterestContract();
			BeanUtils.copyProperties(fundadjustinterestcontracttemp, faic);
			faic.setDocId(doc_id);
			tService.saveEntity(faic);
			
		}
	}
	
	public void updateFundAdjustInterestContractStatus(Map<String,String> variablesMap,BaseService tService) throws Exception{
		User user = (User) SecurityUtil.getPrincipal();
		String date=DateUtil.getSystemDateTime();
		String roll_back_json_val = variablesMap.get("json_curent_roll_back_the_record_str");
		String contractId = "";
		String adjustId = variablesMap.get("adjustid");;
		FundStandardInterest fsi = tService.findEntityByID(FundStandardInterest.class, adjustId);
		JSONArray jsonArray = new JSONArray(roll_back_json_val);
		for(int i=0; i<jsonArray.length(); i++){
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			contractId = jsonObj.getString("id");
			Map<String, Object> propertiesMap=new HashMap<String, Object>();
					
			ContractInfo ci = tService.findEntityByID(ContractInfo.class, contractId);
			propertiesMap.put("adjustId", fsi);
			propertiesMap.put("contractId", ci);
			propertiesMap.put("status", "rate_change_in");
			propertiesMap.put("modReason", "his_rate_change");
			//根据adjustId、contractId、status将本次回滚对应的原来调息记录表中的状态改为：rate_change_back
			List<FundAdjustInterestContract> faictList = tService.findEntityByProperties(FundAdjustInterestContract.class, propertiesMap);
			if(faictList!=null && faictList.size()>0){
				FundAdjustInterestContract faic = faictList.get(0);
				faic.setStatus("rate_change_back");
				faic.setModificator(user);
				faic.setModifyDate(date);
				tService.saveEntity(faic);
			}
		}
	}
	@Override
	public String save(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}
	@Override
	public void start(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}
}
