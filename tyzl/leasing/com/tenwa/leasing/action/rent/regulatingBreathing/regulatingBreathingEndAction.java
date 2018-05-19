package com.tenwa.leasing.action.rent.regulatingBreathing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.reckon.entity.contract.reckon.fund.FundAdjustInterestContractHis;
import com.reckon.entity.contract.reckon.fund.FundAdjustInterestContractTemp;
import com.reckon.renttranrate.service.AjustInterestService;
import com.reckon.service.ConditionDataToHisService;
import com.tenwa.business.entity.WorkFlowFlag;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.service.contract.contractChange.ContractChangeService;
import com.tenwa.leasing.utils.StrTools;

@WorkflowAction(name = "regulatingBreathingEndAction", description = "流程结束动作", workflowName = "调息流程")
@Component(value = "regulatingBreathingEndAction")
public class regulatingBreathingEndAction implements JbpmBaseAction{
	
	@Resource(name = "tableService")
	private TableService tableService;
	
	//@Resource(name="conditionDataToHisService")
	//private ConditionDataToHisService  conditionDataToHisService;
	
	@Resource(name="ajustInterestService")
	private AjustInterestService ajustInterestService;
	
	//@Resource(name = "contractChangeService")
	//private ContractChangeService contractChangeService;
	
	/**
	 * log4j日志 
	 */
	private static final Logger log = LoggerFactory.getLogger(regulatingBreathingEndAction.class);
	
	/**
	 * 将临时表数据插入到正式表
	 * @param variablesMap
	 * @param tService
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void addFundAdjustInterestContractTempToFundAdjustInterestContract(Map<String,String> variablesMap,BaseService tService) throws Exception{
		log.debug("variablesMap:"+StrTools.logCheck(variablesMap.toString()));
		String docid    = variablesMap.get("docid");
		log.debug("docid:"+StrTools.logCheck(docid));
		
		//1将央行调息记录临时表copy到央行调息记录表
		this.ajustInterestService.updateCopyFundAdjustInterestContractTempToRecord(variablesMap);
		
		//2将临时表的数据插入历史表
		//sea注释，在下方做操作 this.addFundFundAdjustInterestContractToAdjustInterestContractHis(variablesMap,this.tableService);
		
		/* 3.将租金计划临时表copy到租金计划历史表
		 * 4.将现金流临时表copy到现金流历史表
		 * 5.将交易结构临时表copy到交易就够历史表*/
		
		//使用完Hibernate持久化数据操作后再同一个事物管理中需要做下FLUSH操作
		this.tableService.getBussinessDao().updateFlush();
		
		//设置状态为 ‘调息’
		//variablesMap.put("table_for_his_type", "his_rate_change");
		//this.ajustInterestService.updateCopyTxAllTempToHis(variablesMap,conditionDataToHisService);
		
		//sea 新增 .............................................................................
		Map<String, String> model = new HashMap<String, String>();
		model.put("docid", docid);
		model.put("modReason", "his_rate_change");
		//6.调息流程结束时，将租金计划正式表、临时表数据移往HIS表作为调息前、后记录
		this.ajustInterestService.updateCopyFundRentPlanFromTempToHisBeforeAndAfter(model);
		
		//7.将租金计划临时表update到租金计划正式表中
		this.ajustInterestService.updateCopyFundRentPlanFromTempToOfficial(variablesMap);
		//......................................................................................
		
		variablesMap = null;
	}

	@Override
	public void back(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}
	@Override
	public String delete(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}
	@Override
	public void end(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String workFlowFlags = variablesMap.get("workFlowFlag");
		if (StringUtils.isNotEmpty(workFlowFlags)) {
			/** 流程冲突第二步-结束 */
			List<WorkFlowFlag> workFlags = this.tableService.findEntityByIDArray(WorkFlowFlag.class, workFlowFlags.split(","));
			this.tableService.removeAllEntites(workFlags);
			/** 流程冲突第二步-结束 */
		}
		
		//调息开始
		addFundAdjustInterestContractTempToFundAdjustInterestContract(variablesMap,this.tableService);
		/*
		//生成利息凭证
		Map<String, String> queryMainObjectMap=new HashMap<String, String>();
		queryMainObjectMap.put("docid", variablesMap.get("docid"));
		JSONArray jsonArray =this.tableService.getJsonArrayData("eleasing/workflow/money_rate/interestVoucher.xml", queryMainObjectMap);
		for(int i=0;i<jsonArray.length();i++){
			 
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			//收款金额
			BigDecimal sum = new BigDecimal(jsonObj.optString("interestnum"));
			//查询合同信息
			Map<String, Object> propertiesMap=new HashMap<String, Object>();
			propertiesMap.put("id", jsonObj.optString("contractid"));
			List<ContractInfo> listcinfo=this.tableService.findEntityByProperties(ContractInfo.class, propertiesMap);
			if(listcinfo.size()>0){
				ContractInfo contractInfo=listcinfo.get(0);
				//获取租金发票类型
				Map<String, Object> proMap=new HashMap<String, Object>();
				proMap.put("contractId",contractInfo);
				List<ContractInvoiceType> listciy=this.tableService.findEntityByProperties(ContractInvoiceType.class, proMap);
				String rentInvoiceType="";
				if(listciy.size()>0){
					rentInvoiceType=listciy.get(0).getRentInvoiceType().getCode();
				}else{
					throw new BusinessException("该合同没有找到租金发票类型！");
				}
				//营业税发票
				if (sum.compareTo(BigDecimal.ZERO) != 0) {
					if ("invoice_type05".equals(rentInvoiceType)) {
						//contractChangeService.createVoucherBalanceRent2(contractInfo,sum);
					} else {
					//	contractChangeService.createVoucherBalanceRent1(contractInfo,sum);
					}
				}
			}else{
				throw new BusinessException("没找到对应的合同信息，不能生成凭证！");
			}
		}
		*/
	}
	/**
	 * 将正式表数据插入到历史表
	 * 将状态改为 ‘后’
	 * 类型改为‘调息’
	 * @param variablesMap
	 * @param tableService2
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	private void addFundFundAdjustInterestContractToAdjustInterestContractHis(
			Map<String, String> variablesMap, TableService tableService2) throws Exception {
		String faictid = "";
		String doc_id = "";
		String checkedData = variablesMap.get("json_current_regulating_of_breathing_str");
		JSONArray jsonArray = new JSONArray(checkedData);
		JSONObject jsonObj = null;
		FundAdjustInterestContractTemp faict = null;
		FundAdjustInterestContractHis faich = null;
		for(int i=0;i<jsonArray.length();i++){
			jsonObj = jsonArray.getJSONObject(i);
			faictid = jsonObj.getString("faictid");
			doc_id = variablesMap.get("flowunid");
			faict = tableService2.findEntityByID(FundAdjustInterestContractTemp.class, faictid);
			faich = new FundAdjustInterestContractHis();
			BeanUtils.copyProperties(faict, faich);
			faich.setDocId(doc_id);
			faich.setStatus("his_status_after");
			faich.setModReason("his_rate_change");
			tableService2.saveEntity(faich);
		}
		emptyTheVariable(faictid,doc_id,checkedData,jsonArray,jsonObj,faict,variablesMap,tableService2);
	}
	/**
	 * 清空对象
	 * @param obj
	 */
	private void emptyTheVariable(Object ... obj) {if(obj!=null){for(Object o : obj){if(o != null){o = null;}}}}
	@Override
	public String save(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}
	@Override
	public void start(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}
}