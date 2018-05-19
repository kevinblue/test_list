package com.reckon.renttranrate.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.reckon.entity.contract.fund.ContractFundFundPlanTemp;
import com.reckon.entity.contract.reckon.cash.ContractCashDetailTemp;
import com.reckon.entity.contract.reckon.condition.ContractConditionHis;
import com.reckon.entity.contract.reckon.condition.ContractConditionTemp;
import com.reckon.entity.contract.reckon.fund.ContractFundRentPlanTemp;
import com.reckon.entity.contract.reckon.fund.FundAdjustInterestContract;
import com.reckon.entity.contract.reckon.fund.FundAdjustInterestContractTemp;
import com.reckon.entity.contract.reckon.fund.FundFundPlanTemp;
import com.reckon.entity.interest.FundStandardInterest;
import com.reckon.renttranrate.service.AjustInterestService;
import com.reckon.service.ConditionDataToHisService;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.UUIDUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;
import com.tenwa.leasing.entity.fund.ContractFundFundPlanHis;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;
import com.tenwa.leasing.entity.fund.ContractFundRentPlanHis;
import com.tenwa.leasing.utils.Tools;

/**
 * @author Chennes
 * 2013-10-22下午11:48:23
 */
@Service(value = "ajustInterestService")
public class AjustInterestServiceImpl implements AjustInterestService {
	
	@Resource(name="tableService")
	private TableService tableService;
	
	/**
	 * log4j日志 
	 */
	private static final Logger log = LoggerFactory.getLogger(AjustInterestServiceImpl.class);
	
	/**
	 * 交易结构his表（调息前）-->交易结构temp表
	 */
	@Override
	public void updateCopyConditionFromHisToTemp(Map<String, String> model) throws Exception{
		//商务条件历史表
		String docid            				= model.get("docid");
		String already_json_val 				= model.get("already_json_val");
		JSONArray jsonArray     				= new JSONArray(already_json_val);
		String his1ContractId  					= "";
		String his1DocId                        = "";
		String his1ModStatus                    = "";
		ContractConditionTemp contractcondition = null;
		List<ContractConditionHis> his1 		= null;
		String contractid = "";
		JSONObject jsonObj = null;
		for(int i=0;i<jsonArray.length();i++){
			jsonObj 		  = jsonArray.getJSONObject(i);
			contractid 		  = jsonObj.get("id").toString();
			his1ContractId 	  = contractid;
			his1DocId 		  = docid;
			his1ModStatus 	  = "his_status_before";
			contractcondition = new ContractConditionTemp();
			his1 = this.tableService.findResultsByHSQL(" from ContractConditionHis cch where cch.contractId.id=? and cch.docId=? and cch.modStatus.code=?", his1ContractId,his1DocId,his1ModStatus);
			
			contractid 		  = getContractID(contractid);
			this.tableService.updateByHSQL("delete from ContractConditionTemp where contractId = ? and docId = ?", contractid,docid);
			if(!his1.isEmpty()){
				this.tableService.copyAndOverrideExistedValueFromObject(his1.get(0), contractcondition);
				contractcondition.setDocId(docid);	//设置最新的docid到temp表
				this.tableService.saveEntity(contractcondition);
			}
		}
		emptyTheVariable(docid,already_json_val,jsonArray,his1ContractId,his1DocId,his1ModStatus,contractcondition,his1,contractid,jsonObj);
	}

	/**
	 * 央行调息记录表（调息前）-->到央行调息临时表FUND_ADJUST_INTEREST_CONTRACT to FUND_ADJUST_INTEREST_C_TEMP
	 */
	@Override
	public void updateCopyFundAdjustInterestContractFromHisToTemp(
			Map<String, String> model) throws Exception {
		String requestAdjustId                = model.get("adjustId");
		String docid                          = model.get("docid");
		String already_json_val               = model.get("already_json_val");
		JSONArray jsonArray                   = new JSONArray(already_json_val);
		List<FundAdjustInterestContract> faic = null;
		FundAdjustInterestContractTemp faict  = null;
		FundAdjustInterestContract fundAdjust = null;
		BigDecimal bigd                       = null;
		JSONObject jsonObj                    = null;
		String contractid                     = null;
		String adjustid                       = null;
		String docIdHis                       = null;
		//1.----Start插入数据到 调息临时表
		String hsql = " FROM FundAdjustInterestContract faic WHERE faic.contractId.id =? AND faic.adjustId.id=? and faic.docId=?";
		for(int i=0;i<jsonArray.length();i++){
			bigd        = new BigDecimal(0);
			jsonObj     = jsonArray.getJSONObject(i);
			contractid  = jsonObj.get("id").toString();
			docIdHis	= jsonObj.getString("docid");
			adjustid    = requestAdjustId;
			faic        = this.tableService.findResultsByHSQL(hsql,contractid,adjustid,docIdHis);
			faict       = new FundAdjustInterestContractTemp();
			faict = (FundAdjustInterestContractTemp)this.tableService.copyAndOverrideExistedValueFromObjectNoSet(faic.get(0),faict);
			/*
			 * 将正式表的数据复制到 临时表 然后将  调息后 数据变成调息钱前的数据  调息前的数据 变成调息后的数据
			 * {header:'调息前利率',name:'rateoriginal'},
			 * {header:'调息后利率',name:'rateadjust'},
			 * {header:'调息前内部收益率',name:'oldirr'},
			 * {header:'调息后内部收益率',name:'newirr'},
			 */
			fundAdjust  = faic.get(0);
			faict.setDocId(docid);
			faict.setRateOriginal(fundAdjust.getRateAdjust()  ==null?bigd:fundAdjust.getRateAdjust());
			faict.setRateAdjust  (fundAdjust.getRateOriginal()==null?bigd:fundAdjust.getRateOriginal());
			faict.setOldIrr      (fundAdjust.getNewIrr()      ==null?bigd:fundAdjust.getNewIrr());
			faict.setNewIrr      (fundAdjust.getOldIrr()      ==null?bigd:fundAdjust.getOldIrr());
			faict.setNewPlanIrr  (fundAdjust.getOldPlanIrr()  ==null?bigd:fundAdjust.getOldPlanIrr());
			faict.setOldPlanIrr  (fundAdjust.getNewPlanIrr()  ==null?bigd:fundAdjust.getNewPlanIrr());
			faict.setModReason("his_rate_change_back");	//调息回滚
			this.tableService.updateByHSQL("delete from FundAdjustInterestContractTemp where contractId.id = ? and docId = ?", contractid,docid);
			log.info(fundAdjust.getContractId()==null ? "null" : fundAdjust.getContractId().getId());
			log.info(faict.getContractId()==null ? "null" : faict.getContractId().getId());
			log.info("delete "+contractid+"==>"+docid);
			this.tableService.saveEntity(faict);
		}
		emptyTheVariable(requestAdjustId,docid,already_json_val,jsonArray,faic,faict,fundAdjust,bigd,jsonObj,contractid,adjustid,hsql);

	}

	@Override
	public void updateCopyConditionFromTempToHis(Map<String, String> model)
			throws Exception {
		
	}

	/**
	 * 调息开始
	 * 将央行调息记录临时表 copy到 央行调息记录表
	 */
	@Override
	public void updateCopyFundAdjustInterestContractTempToRecord(
			Map<String, String> model) throws Exception {
		User user = (User) SecurityUtil.getPrincipal();
		String date=DateUtil.getSystemDateTime();
		String docid    = model.get("docid");
		String adjustid = model.get("fund_standard_interest.id");
		Map<String, Object> propertiesMap=new HashMap<String, Object>();
		log.info("调息开始将央行调息记录临时表 copy到 央行调息记录表adjustid="+adjustid);		
		FundStandardInterest fsi=this.tableService.findEntityByID(FundStandardInterest.class, adjustid);
		propertiesMap.put("adjustId", fsi);
		propertiesMap.put("docId", docid);
		List<FundAdjustInterestContractTemp> faictList = this.tableService.findEntityByProperties(FundAdjustInterestContractTemp.class, propertiesMap);
		FundAdjustInterestContract faic = null;
		for (FundAdjustInterestContractTemp temp : faictList) {
			faic = new FundAdjustInterestContract();
			BeanUtils.copyProperties(temp,faic);
			faic.setContractId(temp.getContractId());
			faic.setAdjustId(fsi);
			faic.setCreator(user);
			faic.setCreateDate(date);
			this.tableService.saveEntity(faic);
		}
		emptyTheVariable(docid,adjustid,propertiesMap,fsi,faictList,faic,model);
	}

	/**
	 *# 回滚
	 * 删除央行调息记录表数据
	 */
	@Override
	public void updateFundAdjustInterestContract(Map<String, String> model)
			throws Exception {
		User user = (User) SecurityUtil.getPrincipal();
		String date=DateUtil.getSystemDateTime();
		String docid    = model.get("docid");
		String adjustid = model.get("adjustid");
		Map<String, Object> propertiesMap=new HashMap<String, Object>();
		log.info("调息开始将央行调息记录临时表 copy到 央行调息记录表adjustid="+adjustid);		
		FundStandardInterest fsi=this.tableService.findEntityByID(FundStandardInterest.class, adjustid);
		propertiesMap.put("adjustId", fsi);
		propertiesMap.put("docId", docid);
		List<FundAdjustInterestContractTemp> faictList = this.tableService.findEntityByProperties(FundAdjustInterestContractTemp.class, propertiesMap);
		FundAdjustInterestContract faic = null;
		for (FundAdjustInterestContractTemp temp : faictList) {
			faic = new FundAdjustInterestContract();
			BeanUtils.copyProperties(temp,faic);
			faic.setContractId(temp.getContractId());
			faic.setAdjustId(fsi);
			faic.setCreator(user);
			faic.setCreateDate(date);
			this.tableService.saveEntity(faic);
		}
		emptyTheVariable(docid,adjustid,propertiesMap,fsi,faictList,faic,model);
		/*
		String docid              = model.get("docid");
		//需要调息的条目
		String adjust_contractids   = model.get("adjust_contractids");
		List<FundAdjustInterestContractTemp> fundadjustinterestcontracttemp = null;
		FundAdjustInterestContract fundAdjustInterestContract = null;
System.out.println("回滚流程结束把调息记录临时表FUND_ADJUST_INTEREST_C_TEMP 的数据写入 FUND_ADJUST_INTEREST_CONTRACT表 docid="+docid);		
		
        if(adjust_contractids!=""){
        	System.out.println(adjust_contractids);
			if(adjust_contractids.indexOf(",")!=-1){
				String[] adjust_contractidss = adjust_contractids.split(",");
				for(String contractid : adjust_contractidss){
					System.out.println(contractid);
					fundadjustinterestcontracttemp = this.tableService.findResultsByHSQL(" FROM FundAdjustInterestContractTemp faict WHERE faict.contractId.id =? AND faict.docId=?", contractid,docid);
					fundAdjustInterestContract = new FundAdjustInterestContract();
					this.tableService.copyAndOverrideExistedValueFromObject(fundadjustinterestcontracttemp.get(0), fundAdjustInterestContract);
					this.tableService.saveEntity(fundAdjustInterestContract);
				}
			}else{
				fundadjustinterestcontracttemp = this.tableService.findResultsByHSQL(" FROM FundAdjustInterestContractTemp faict WHERE faict.contractId.id =? AND faict.docId=?", adjust_contractids,docid);
				fundAdjustInterestContract = new FundAdjustInterestContract();
				this.tableService.copyAndOverrideExistedValueFromObject(fundadjustinterestcontracttemp.get(0), fundAdjustInterestContract);
				this.tableService.saveEntity(fundAdjustInterestContract);
			
			}
		}
		emptyTheVariable(docid,fundadjustinterestcontracttemp,fundAdjustInterestContract,model);
		*/
	}

	@Override
	public void updateCopyTxAllTempToHis(Map<String,String> variablesMap,ConditionDataToHisService conditionDataToHisService) throws Exception {
		//获取被选中的调息条目所有合同号集合
		String adjust_contractids      = variablesMap.get("adjust_contractids");
		String docid                   = variablesMap.get("docid");
		String framework_condition     = "framework_condition";
		String json_fund_rent_plan_str = "json_fund_rent_plan_str";
		String json_fund_cash_flow_str = "json_fund_cash_flow_str";
		Map<String, String> queryMainObjectMap = new HashMap<String, String>();
		String whereContractid         = "";
		String whereDocid              = "";
		ContractInfo contractInfo      = null;
		String table_for_his_type = variablesMap.get("table_for_his_type");
		if(adjust_contractids != null && adjust_contractids.length() > 0){
			//if(adjust_contractids.indexOf(",")!=-1){
				String[] adjust_contractidss = adjust_contractids.split(",");
				for(String contractid:adjust_contractidss){
					contractInfo = (ContractInfo) this.tableService.findEntityByID(ContractInfo.class, contractid);
					whereContractid = contractInfo.getContractId();
					whereDocid      = docid;
					queryMainObjectMap.put("contract_id", whereContractid);
					queryMainObjectMap.put("doc_id", whereDocid);
					//商务条件临时表
					List<ContractConditionTemp> contractconditiontemp = this.tableService.findResultsByHSQL(" from ContractConditionTemp cct where cct.contractId=? and cct.docId=?", whereContractid,whereDocid);
					System.out.println("contractconditiontemp size="+contractconditiontemp.size());
					if(!contractconditiontemp.isEmpty()){
						variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(contractconditiontemp.get(0), null, framework_condition));
					}	
						CustInfo customerInfo = contractInfo.getCustId();
						// 设置租金测算参数为合同审批
						variablesMap.put("framework_condition.contractid", contractInfo.getContractId());
						variablesMap.put("framework_condition.process", "cont_process");
						variablesMap.put("framework_condition.custname", customerInfo.getCustName());
						variablesMap.put("framework_condition.custid", customerInfo.getCustNumber());
						variablesMap.put("framework_condition.docid", docid);
						variablesMap.put("framework_condition.onhireid", "");
						variablesMap.put("framework_condition.projid", "");
					
						if (!contractInfo.getContractFundRentPlans().isEmpty()) {// 租金计划
							variablesMap.put(json_fund_rent_plan_str, this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_fund_rent_plan_temp_of_tx.xml", queryMainObjectMap).toString());
						}
						if (!contractInfo.getContractCashDetails().isEmpty()) {// 合同现金流
							variablesMap.put(json_fund_cash_flow_str, this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_fund_cash_flow_temp.xml", queryMainObjectMap).toString());
						}
						variablesMap.remove("framework_condition.id");
					if(!contractconditiontemp.isEmpty()&&!contractInfo.getContractFundRentPlans().isEmpty()&&!contractInfo.getContractCashDetails().isEmpty()){
					conditionDataToHisService.saveContractConditionDataToHis(contractid, docid, table_for_his_type, variablesMap, framework_condition,
						json_fund_rent_plan_str, "", json_fund_cash_flow_str, "");
					}
				//}
			}
		}else{
			//选择一个调息条目 时，商务条件、现金流、租金计划变更
			oneContractIdQueryForOther(variablesMap, conditionDataToHisService,adjust_contractids, docid, queryMainObjectMap, table_for_his_type,framework_condition,json_fund_rent_plan_str,json_fund_cash_flow_str);
		}
		emptyTheVariable(adjust_contractids,docid,framework_condition,json_fund_rent_plan_str,json_fund_cash_flow_str,queryMainObjectMap,whereContractid,whereDocid,contractInfo);
		
	}

	private void oneContractIdQueryForOther(Map<String, String> variablesMap,
			ConditionDataToHisService conditionDataToHisService,
			String adjust_contractids, String docid,
			Map<String, String> queryMainObjectMap,String table_for_his_type,
			String framework_condition,String json_fund_rent_plan_str,String json_fund_cash_flow_str
	) throws Exception {
		String whereContractid;
		String whereDocid;
		queryMainObjectMap.put("contractId", adjust_contractids);
		queryMainObjectMap.put("docId", docid);
		ContractInfo contractInfo = (ContractInfo) this.tableService.findEntityByID(ContractInfo.class, adjust_contractids);
		whereContractid 		  = contractInfo.getContractId();
		whereDocid      		  = docid;
		CustInfo customerInfo	  = null;
		//商务条件临时表
		List<ContractConditionTemp> contractconditiontemp = this.tableService.findResultsByHSQL(" from ContractConditionTemp cct where cct.contractId=? and cct.docId=?", whereContractid,whereDocid);
		if(contractInfo.getContractCondition() != null){
			variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(contractInfo.getContractCondition(), null, framework_condition));
		}
		customerInfo = contractInfo.getCustId();
		// 设置租金测算参数为合同审批
		variablesMap.put("framework_condition.contractid", contractInfo.getContractId());
		variablesMap.put("framework_condition.process", "cont_process");
		variablesMap.put("framework_condition.custname", customerInfo.getCustName());
		variablesMap.put("framework_condition.custid", customerInfo.getCustNumber());
		variablesMap.put("framework_condition.docid", docid);
		variablesMap.put("framework_condition.onhireid", "");
		variablesMap.put("framework_condition.projid", "");
		// 租金计划
		variablesMap.put(json_fund_rent_plan_str, this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_fund_rent_plan_temp.xml", queryMainObjectMap).toString());
		// 合同现金流
		variablesMap.put(json_fund_cash_flow_str, this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_fund_cash_flow_temp.xml", queryMainObjectMap).toString());
		//现金流、租金计划、商务条件等入历史表
		conditionDataToHisService.saveContractConditionDataToHis(adjust_contractids, docid, table_for_his_type, variablesMap, framework_condition,
				json_fund_rent_plan_str, "", json_fund_cash_flow_str, "");
System.out.println("礼毕！！！");
		emptyTheVariable(whereContractid,whereDocid,queryMainObjectMap,contractInfo,customerInfo);
	}

	@Override
	public void updateCopyFundRentCashFromTempToHis(Map<String, String> model)
			throws Exception {
		
	}
	

	/**
	 * 现金流his表（调息前）-->现金流temp表
	 */
	@Override
	public void updateCopyFundRentCashFromHisToTemp(Map<String, String> model) throws Exception {
		String docid            = model.get("docid");
		String already_json_val = model.get("already_json_val");
		JSONArray jsonArray 	= new JSONArray(already_json_val);
		String his3ContractId 	= ""; 
		String his3docId 		= ""; 
		String his3modStatus 	= ""; 
		JSONObject jsonObj		= null;
		String contractid		= "";
		List<ContractFundRentPlanHis> his3 = null;
		ContractCashDetailTemp contractcashdetailtemp = null;
		for(int i=0;i<jsonArray.length();i++){
			jsonObj = jsonArray.getJSONObject(i);
			contractid = jsonObj.get("id").toString();
			his3ContractId 	   = contractid;
			his3docId          = jsonObj.get("docid").toString();
			his3modStatus      = "his_status_before";
			//现金流
			his3 = this.tableService.findResultsByHSQL(" from ContractCashDetailHis ccdh where ccdh.contractId.id=? and ccdh.docId=? and ccdh.modStatus.code=?", his3ContractId,his3docId,his3modStatus);
			contractcashdetailtemp = new ContractCashDetailTemp();
			contractid = getContractID(contractid);
			this.tableService.updateByHSQL("delete from ContractCashDetailTemp where contractId = ? and docId = ?", contractid,docid);
			if(!his3.isEmpty()){
				this.tableService.copyAndOverrideExistedValueFromObject(his3.get(0),contractcashdetailtemp);
				contractcashdetailtemp.setDocId(docid);	//设置最新的docid到temp表
				this.tableService.saveEntity(contractcashdetailtemp);
			}
		}
		emptyTheVariable(docid,already_json_val,jsonArray,his3ContractId,his3docId,his3modStatus,his3modStatus,jsonObj,contractid,his3,contractcashdetailtemp);

	}

	/**
	 * 租金计划his表（调息前）-->租金计划temp表 
	 */
	@Override
	public void updateCopyFundRentPlanFromHisToTemp(Map<String, String> model) throws Exception {
		String valueField = "SQLSERVER".equals(ResourceUtil.getDBType())?"replace(newid(), '-', '')":"sys_guid()";//选择的列
		String docid            = model.get("docid");
		String already_json_val = model.get("already_json_val");
		JSONArray jsonArray 	= new JSONArray(already_json_val);
		String his2ContractId 	= ""; 
		String his2docId 		= ""; 
		String his2modStatus 	= ""; 
		JSONObject jsonObj		= null;
		String contractid		= null;
		List<ContractFundRentPlanHis> his2 = null;
		ContractFundRentPlanTemp contractfundrentplan = null;
		StringBuffer sql = null;
		for(int i=0;i<jsonArray.length();i++){
			jsonObj = jsonArray.getJSONObject(i);
			contractid = jsonObj.get("id").toString();
			his2ContractId = contractid;
			his2docId= jsonObj.get("docid").toString();;
			his2modStatus = "his_status_before";
			his2 = this.tableService.findResultsByHSQL(" from ContractFundRentPlanHis cfrp where cfrp.contractId.id=? and cfrp.docId=? and cfrp.modStatus.code=?", his2ContractId,his2docId,his2modStatus);
			contractfundrentplan = new ContractFundRentPlanTemp();
			contractid = getContractID(contractid);
			log.info("租金计划his表（调息前）-->租金计划temp表 contractid="+contractid+"docid="+docid);
			this.tableService.updateByHSQL("DELETE from ContractFundRentPlanTemp where contractId = ? and docId = ?", contractid,docid);
			sql = new StringBuffer();
			sql.append("insert into contract_fund_rent_plan_temp(id,doc_id,contract_id,rent_list,plan_date,rent,rent_adjust,corpus")
				.append(",corpus_business,year_rate,interest,interest_business,rent_overage,corpus_overage,interest_overage,penalty_overage")
				.append(",all_remain_rent,all_remain_corpus,all_remain_interest,creator_,create_date,modificator_,modify_date")
				.append(") ")
				.append("select lower("+valueField+"),'").append(docid).append("','").append(contractid)
				.append("',rent_list,plan_date,rent,rent_adjust,corpus,corpus_business,year_rate,interest,interest_business")
				.append(",rent_overage,corpus_overage,interest_overage,penalty_overage,all_remain_rent,all_remain_corpus")
				.append(",all_remain_interest,creator_,create_date,modificator_,modify_date from contract_fund_rent_plan_his")
				.append(" where mod_status='his_status_before' and mod_reason='his_rate_change'")
				.append(" and doc_id='").append(his2docId)
				.append("' and contract_id='").append(his2ContractId).append("'");
			log.info("SQL==>" + sql.toString());
			this.tableService.getBussinessDao().getJdbcTemplate().execute(sql.toString());
			/*
			if(!his2.isEmpty()){
				//this.tableService.copyAndOverrideExistedValueFromObject(his2.get(0),contractfundrentplan);
				this.tableService.copyAndOverrideExistedValueFromObjectNoSet(his2.get(0),contractfundrentplan);
				contractfundrentplan.setDocId(docid);	//设置最新的docid到temp表
				this.tableService.saveEntity(contractfundrentplan);
			}
			*/
		}
		emptyTheVariable(docid,already_json_val,jsonArray,jsonObj,contractid,his2ContractId,his2docId,his2modStatus,his2,contractfundrentplan);
	}
	@Override
	public void updateCopyFundFundPlanFromHisToTemp(Map<String, String> model) throws Exception {
		String valueField = "SQLSERVER".equals(ResourceUtil.getDBType())?"replace(newid(), '-', '')":"sys_guid()";//选择的列
		String docid            = model.get("docid");
		String already_json_val = model.get("already_json_val");
		JSONArray jsonArray 	= new JSONArray(already_json_val);
		String his2ContractId 	= ""; 
		String his2docId 		= ""; 
		String his2modStatus 	= ""; 
		JSONObject jsonObj		= null;
		String contractid		= null;
		List<ContractFundFundPlanHis> his2 = null;
		FundFundPlanTemp contractfundfundplan = null;
		StringBuffer sql = null;
		for(int i=0;i<jsonArray.length();i++){
			jsonObj = jsonArray.getJSONObject(i);
			contractid = jsonObj.get("id").toString();
			his2ContractId = contractid;
			his2docId= jsonObj.get("docid").toString();;
			his2modStatus = "his_status_before";
			his2 = this.tableService.findResultsByHSQL(" from ContractFundFundPlanHis cfrp where cfrp.contractId.id=? and cfrp.docId=? and cfrp.modStatus.code=?", his2ContractId,his2docId,his2modStatus);
			contractfundfundplan = new FundFundPlanTemp();
			contractid = getContractID(contractid);
			//关键是临时表中的planmoney
			log.info("资金计划his表（调息前）-->资金计划temp表 contractid="+contractid+"docid="+docid);
			this.tableService.updateByHSQL("DELETE from FundFundPlanTemp where contractId = ? and docId = ?", contractid,docid);
			sql = new StringBuffer();
			sql.append("insert into fund_fund_plan_temp( id,doc_id,contract_id, ")
				.append(" payment_id,plan_date,plan_money,pay_obj,fpnote,fee_type,pay_cust,pay_type,settle_method ")
				.append(") ")
				.append("select lower("+valueField+"),'").append(docid).append("','").append(contractid).append("',")
				.append(" payment_id,plan_date,plan_money,pay_obj,fpnote,fee_type,pay_cust,pay_type,settle_method from contract_fund_fund_plan_his")
				.append(" where mod_status='his_status_before' and mod_reason='his_rate_change'")
				.append(" and doc_id='").append(his2docId)
				.append("' and contract_id='").append(his2ContractId).append("'");
			log.info("SQL==>" + sql.toString());
			this.tableService.getBussinessDao().getJdbcTemplate().execute(sql.toString());
			/*
			if(!his2.isEmpty()){
				//this.tableService.copyAndOverrideExistedValueFromObject(his2.get(0),contractfundrentplan);
				this.tableService.copyAndOverrideExistedValueFromObjectNoSet(his2.get(0),contractfundrentplan);
				contractfundrentplan.setDocId(docid);	//设置最新的docid到temp表
				this.tableService.saveEntity(contractfundrentplan);
			}
			*/
		}
		emptyTheVariable(docid,already_json_val,jsonArray,jsonObj,contractid,his2ContractId,his2docId,his2modStatus,his2,contractfundfundplan);
	}

	@Override
	public void removeConditionFromHisToTemp(Map<String, String> model) throws Exception{
		String docid            = model.get("docid");
		String already_json_val = model.get("already_json_val");
		JSONArray jsonArray = new JSONArray(already_json_val);
		JSONObject jsonObj = null;
		String contractid = null;
		for(int i=0;i<jsonArray.length();i++){
			jsonObj = jsonArray.getJSONObject(i);
			contractid  = jsonObj.get("id").toString();
System.out.println("交易结构临时表删除：contractid="+contractid+"docid="+docid);
			contractid = getContractID(contractid);
			this.tableService.updateByHSQL("DELETE from ContractConditionTemp where contractId = ? and docId = ?", contractid,docid);
		}
		emptyTheVariable(docid,already_json_val,jsonArray,jsonObj,contractid);
	}

	@Override
	public void removeFundAdjustInterestCTemp(Map<String, String> model)throws Exception {
		
		String docid            = model.get("docid");
		String already_json_val = model.get("already_json_val");
		JSONArray jsonArray = new JSONArray(already_json_val);
		JSONObject jsonObj = null;
		String contractid = null;
		for(int i=0;i<jsonArray.length();i++){
			jsonObj = jsonArray.getJSONObject(i);
			contractid = jsonObj.get("id").toString();
System.out.println("央行记录临时表删除转化前：contractid="+contractid+"docid="+docid);
			this.tableService.updateByHSQL("DELETE from FundAdjustInterestContractTemp where contractId.id = ? and docId = ?", contractid,docid);
		}
		emptyTheVariable(docid,already_json_val,jsonArray,jsonObj,contractid,model);
	}

	/**
	 * 根据合同id得到合同编号
	 * @param contractid
	 * @return
	 * @throws Exception
	 */
	private String getContractID(String contractid) throws Exception {
		ContractInfo contractinfo = this.tableService.findEntityByID(ContractInfo.class, contractid);
		contractid = contractinfo.getContractId();
		return contractid;
	}

	@Override
	public void removeFundRentCashFromHisToTemp(Map<String, String> model) throws Exception{
		String docid            = model.get("docid");
		String already_json_val = model.get("already_json_val");
		JSONArray jsonArray = new JSONArray(already_json_val);
		JSONObject jsonObj = null;
		String contractid = null;
		for(int i=0;i<jsonArray.length();i++){
			jsonObj = jsonArray.getJSONObject(i);
			contractid = jsonObj.get("id").toString();
			contractid = getContractID(contractid);
			this.tableService.updateByHSQL("DELETE from ContractCashDetailTemp where contractId = ? and docId = ?", contractid,docid);
		}
		emptyTheVariable(docid,already_json_val,jsonArray,jsonObj,contractid);
	}

	/**
	 * 清空对象
	 * @param obj
	 */
	private void emptyTheVariable(Object ... obj) {if(obj!=null){for(Object o : obj){if(o != null){o = null;}}}}

	@Override
	public void removeFundRentPlanFromHisToTemp(Map<String, String> model) throws Exception {
		String docid            = model.get("docid");
		String already_json_val = model.get("already_json_val");
		JSONArray jsonArray = new JSONArray(already_json_val);
		JSONObject jsonObj = null;
		String contractid = null;
		for(int i=0;i<jsonArray.length();i++){
			jsonObj = jsonArray.getJSONObject(i);
			contractid = jsonObj.get("id").toString();
			log.info("央行记录临时表删除转化前：contractid="+contractid+"docid="+docid);
			contractid = getContractID(contractid);
			this.tableService.updateByHSQL("DELETE from ContractFundRentPlanTemp where contractId = ? and docId = ?", contractid,docid);
		}
		emptyTheVariable(docid,already_json_val,jsonArray,jsonObj,contractid);

	}
	@Override
	public void removeFundFundPlanFromHisToTemp(Map<String, String> model) throws Exception {
		String docid            = model.get("docid");
		String already_json_val = model.get("already_json_val");
		JSONArray jsonArray = new JSONArray(already_json_val);
		JSONObject jsonObj = null;
		String contractid = null;
		for(int i=0;i<jsonArray.length();i++){
			jsonObj = jsonArray.getJSONObject(i);
			contractid = jsonObj.get("id").toString();
			log.info("央行记录临时表删除转化前：contractid="+contractid+"docid="+docid);
			contractid = getContractID(contractid);
			this.tableService.updateByHSQL("DELETE from FundFundPlanTemp where contractId = ? and docId = ?", contractid,docid);
		}
		emptyTheVariable(docid,already_json_val,jsonArray,jsonObj,contractid);

	}

	@Override
	public void updateCondition(Map<String, String> model) {

	}

	@Override
	public void updateFundRentCash(Map<String, String> model) {

	}

	@Override
	public void updateFundRentPlan(Map<String, String> model) {

	}

	@Override
	public void updateStatusTo(Map<String, String> model) {

	}
	
	
	
	/**
	 * 调息流程结束时:
	 * <p>租金计划temp表-->租金计划正式表 。</p>
	 * <p>jdbc批量模式更新，一个流程号下存在N个合同调息，则批量将这些合同对应的租金计划根据临时表中的数据更新到正式表中。</p>
	 * @author sea
	 * @param model 只用docid 例如:model.get("docid")
	 * @throws Exception
	 */
	public void updateCopyFundRentPlanFromTempToOfficial(Map<String, String> model) throws Exception {
		String docid = model.get("docid");
		if(Tools.isNullOrEmpty(docid)){
			throw new Exception("调息文档号为空，无法将资金计划从临时更新到正式!");
		}
		//根据docid更新正式的租金计划
		String sql = this.getSql(docid,"contract_fund_rent_plan");
		if("SQLSERVER".equals(ResourceUtil.getDBType())){
			sql = sql.replace("nvl", "isnull");
		}
		log.info("调息流程结束时，批量更新资金正式表的资金计划:"+sql);
		this.tableService.getBussinessDao().getJdbcTemplate().execute(sql);
		//***租金计划表更换标记***
//		sql = this.getSql(docid,"contract_fund_rent_plan_before");
//		if("SQLSERVER".equals(ResourceUtil.getDBType())){
//			sql = sql.replace("nvl", "isnull");
//		}
//		log.info("调息流程结束时，批量更新资金正式表的资金计划:"+sql);
//		this.tableService.getBussinessDao().getJdbcTemplate().execute(sql);
		
		emptyTheVariable(docid);
	}
	public void updateCopyFundFundPlanFromTempToOfficial(Map<String, String> model) throws Exception {
		String docid = model.get("docid");
		if(Tools.isNullOrEmpty(docid)){
			throw new Exception("调息文档号为空，无法将租金计划从临时更新到正式!");
		}
		//根据docid更新正式的租金计划
//		List<ContractFundFundPlan> nowList = this.tableService.findEntityByProperties(ContractFundFundPlan.class, null);
//		List<FundFundPlanTemp> tempList = this.tableService.findEntityByProperties(FundFundPlanTemp.class, queryMap);
//		BeanUtils.copyProperties(before, onhire);
//		this.baseService.saveEntity(onhire);
		String sql = this.getFundFundSql(docid);
		if("SQLSERVER".equals(ResourceUtil.getDBType())){
			sql = sql.replace("nvl", "isnull");
		}
		log.info("调息流程结束时，批量更新租金正式表的租金计划:"+sql);
		this.tableService.getBussinessDao().getJdbcTemplate().execute(sql);
		
		emptyTheVariable(docid);
	}
	
	/**
	 * <p>根据DOCID构建批量更新租金计划正式表数据的SQL。</p>
	 * <p>构建原则是：数据来源于租金计划临时表，临时表的合同号与正式表合同号一致，临时表期项与正式表期项一致。</p>
	 * @author sea
	 * @param docid
	 * @return
	 */
	private String getSql(String docid,String tabName){
		//公用部分SQL抽出
		String publicSonSql = "  where doc_id = '"+docid+"'  ";
		publicSonSql = publicSonSql + "and rent_list = "+tabName+".Rent_List   ";
		publicSonSql = publicSonSql + "and contract_id in ( ";
		publicSonSql = publicSonSql + "    select distinct t1.contract_id  from contract_fund_rent_plan_temp t1 ";
		publicSonSql = publicSonSql + "    left join contract_info t2 on t2.contract_id = t1.contract_id ";
		publicSonSql = publicSonSql + "    where t1.doc_id = '"+docid+"' ";
		publicSonSql = publicSonSql + "    and t2.id = "+tabName+".CONTRACT_ID ";
		publicSonSql = publicSonSql + ") ";
		String beforeOrOnhire ;
		if("contract_fund_rent_plan".equals(tabName)){
			beforeOrOnhire = "onhire";
		}else{
			beforeOrOnhire = "before";
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" update "+tabName+" set ")
		.append(" rent = ( ")//租金
		.append("      select nvl(rent,0)  from  contract_fund_rent_plan_temp ")
		.append(publicSonSql)//相同公用SQL
		.append(" ), ")
		.append(" corpus = ( ")//财务本金
		.append("      select nvl(corpus,0)  from  contract_fund_rent_plan_temp ")
		.append(publicSonSql)//相同公用SQL
		.append(" ), ")
		.append(" corpus_business = ( ")//业务本金
		.append("      select nvl(corpus_business,0)  from  contract_fund_rent_plan_temp ")
		.append(publicSonSql)//相同公用SQL
		.append(" ), ")
		.append(" year_rate = ( ")//年利率
		.append("      select nvl(year_rate,0)  from  contract_fund_rent_plan_temp ")
		.append(publicSonSql)//相同公用SQL
		.append(" ), ")
		.append(" interest = ( ")//财务利息
		.append("      select nvl(interest,0)  from  contract_fund_rent_plan_temp ")
		.append(publicSonSql)//相同公用SQL
		.append(" ),  ")
		.append(" interest_business = ( ")//业务利息
		.append("      select nvl(interest_business,0)  from  contract_fund_rent_plan_temp ")
		.append(publicSonSql)//相同公用SQL
		.append(" ), ")
		.append(" corpus_overage = ( ")//剩余本金
		.append("      select nvl(corpus_overage,0)  from  contract_fund_rent_plan_temp ")
		.append(publicSonSql)//相同公用SQL
		.append(" ), ")
		.append(" interest_overage = ( ")//剩余利息
		.append("      select nvl(interest_overage,0)  from  contract_fund_rent_plan_temp ")
		.append(publicSonSql)//相同公用SQL
		.append(" ), ")
		.append(" penalty_overage = ( ")
		.append("      select nvl(penalty_overage,0)  from  contract_fund_rent_plan_temp ")
		.append(publicSonSql)//相同公用SQL
		.append(" ), ")
		.append(" penalty = ( ")
		.append("      select nvl(penalty,0)  from  contract_fund_rent_plan_temp ")
		.append(publicSonSql)//相同公用SQL
		.append(" ) ")
		.append(" where contract_id in ( ")
		//正式表使用的是合同号对应的uuid
		.append("   select distinct t2.id as contract_id  from contract_fund_rent_plan_temp t1 ")
		.append("   left join contract_info t2 on t2.contract_id = t1.contract_id ")
		.append("   left join fund_adjust_interest_c_temp temp on temp.contract_id=t2.id and temp.doc_id = '"+docid+"' ")
		//***租金计划表更换标记***(不需要插入两次,不需要这个判断)
		.append("   where t1.doc_id = '"+docid+"'")//+"' and temp.adjust_style='"+beforeOrOnhire+"'")
		.append(" ) ")
		.append("");
		return sql.toString();
	}
	private String getFundFundSql(String docid){
		//公用部分SQL抽出
		String publicSonSql = "  where doc_id = '"+docid+"'  ";
		publicSonSql = publicSonSql + "and plan_date=CONTRACT_FUND_FUND_PLAN.plan_date   and fee_type='feetype9' ";
		publicSonSql = publicSonSql + "and contract_id in ( ";
		publicSonSql = publicSonSql + "    select distinct t1.contract_id  from fund_fund_plan_temp t1 ";
		publicSonSql = publicSonSql + "    left join contract_info t2 on t2.contract_id = t1.contract_id ";
		publicSonSql = publicSonSql + "    where t1.doc_id = '"+docid+"' ";
		publicSonSql = publicSonSql + "    and t2.id = CONTRACT_FUND_FUND_PLAN.CONTRACT_ID ";
		publicSonSql = publicSonSql + ") ";
		
		StringBuffer sql = new StringBuffer();
		//资金收付计划只需要更改planmoney
		sql.append(" update CONTRACT_FUND_FUND_PLAN set ")
		.append(" plan_money = ( ")//租金
		.append("      select nvl(plan_money,0)  from  fund_fund_plan_temp ")
		.append(publicSonSql)//相同公用SQL
		.append(" ) ")
		.append(" where contract_id in ( ")
		//正式表使用的是合同号对应的uuid
		.append("   select distinct t2.id as contract_id  from fund_fund_plan_temp t1 ")
		.append("   left join contract_info t2 on t2.contract_id = t1.contract_id ")
		.append("   where t1.doc_id = '"+docid+"' ")
		.append(" )and fee_type='feetype9' ")
		.append("");
		return sql.toString();
	}
	
	/*
	 * @author sea
	 */
	@Override
	public void updateCopyFundRentPlanFromTempToHisAfter(Map<String, String> model) throws Exception {}
	
	/*
	 * @author sea
	 */
	@Override
	public void updateCopyFundRentPlanFromTempToHisBefore(Map<String, String> model) throws Exception {}
	
	/*
	 * @author sea
	 */
	@Override
	public void updateCopyFundRentPlanFromTempToHisBeforeAndAfter(Map<String, String> model,String rentTab) throws Exception {
		String docid = model.get("docid");
		String modReason = model.get("modReason");
		//String contractId = model.get("contractId");
		if(Tools.isNullOrEmpty(docid)){
			throw new Exception("调息文档号为空，无法将租金计划从临时更新到正式!");
		}
		if(Tools.isNullOrEmpty(modReason)){
			modReason = "his_rate_change";
		}
		
		//移动数据前先删除
		String delSql = this.getDelHisSql(docid,"his_status_before",modReason);
		log.info("调息流程开始时，租金正式表的租金计划移往HIS表前删除:"+delSql);
		this.tableService.getBussinessDao().getJdbcTemplate().execute(delSql);
		
		//调息前数据，因为正式表不确定是起租后还是起租前的表，因此执行两次（根据央行调息记录临时表的adjust_style进行分别update）。
		//把contract_fund_rent_plan数据入历史表
		String sql = this.getHisSql(docid, "his_status_before",modReason,"contract_fund_rent_plan");
		log.info("调息流程结束时，租金正式表的租金计划移往HIS表作为调息前数据:"+sql);
		this.tableService.getBussinessDao().getJdbcTemplate().execute(sql.toString());
		//把contract_fund_rent_plan_before数据入历史表
		//***租金计划表更换标记***
//		sql = this.getHisSql(docid, "his_status_before",modReason,"contract_fund_rent_plan_before");
//		log.info("调息流程结束时，租金正式表的租金计划移往HIS表作为调息前数据:"+sql);
//		this.tableService.getBussinessDao().getJdbcTemplate().execute(sql.toString());
		
		//--------------------------------------------------------------------------------------------------------------------
		//移动数据前先删除
		delSql = this.getDelHisSql(docid,"his_status_after",modReason);
		log.info("调息流程结束时，租金正式表的租金计划移往HIS表前删除:"+delSql);
		this.tableService.getBussinessDao().getJdbcTemplate().execute(delSql);
		
		//调息后数据
		sql = this.getHisSql(docid, "his_status_after",modReason,"contract_fund_rent_plan_temp");
		log.info("调息流程结束时，租金正式表的租金计划移往HIS表作为调息后数据:"+sql);
		this.tableService.getBussinessDao().getJdbcTemplate().execute(sql.toString());
		}
	/**
	 * 起租前调息，资金收付计划正式、临时入历史表
	 */
	@Override
	public void updateCopyFundFundPlanFromTempToHisBeforeAndAfter(Map<String, String> model) throws Exception {
		String docid = model.get("docid");
		String modReason = model.get("modReason");
		//String contractId = model.get("contractId");
		if(Tools.isNullOrEmpty(docid)){
			throw new Exception("调息文档号为空，无法将资金收付计划从临时更新到正式!");
		}
		if(Tools.isNullOrEmpty(modReason)){
			modReason = "his_rate_change";
		}
		
		//移动数据前先删除
		String delSql =this.getFundFundDelHisSql(docid,"his_status_before",modReason);
		log.info("调息流程开始时，资金正式表的资金计划移往HIS表前删除:"+delSql);
		this.tableService.getBussinessDao().getJdbcTemplate().execute(delSql);
		
		//调息前数据
		String sql = this.getFundFundHisSql(docid, "his_status_before",modReason,"contract_fund_fund_plan");
		log.info("调息流程结束时，资金正式表的资金计划移往HIS表作为调息前数据:"+sql);
		this.tableService.getBussinessDao().getJdbcTemplate().execute(sql.toString());
		
		//--------------------------------------------------------------------------------------------------------------------
		//移动数据前先删除
		delSql = this.getFundFundDelHisSql(docid,"his_status_after",modReason);
		log.info("调息流程结束时，资金正式表的资金计划移往HIS表前删除:"+delSql);
		this.tableService.getBussinessDao().getJdbcTemplate().execute(delSql);
		
		//调息后数据
		sql = this.getFundFundHisSql(docid, "his_status_after",modReason,"fund_fund_plan_temp");
		log.info("调息流程结束时，资金正式表的资金计划移往HIS表作为调息后数据:"+sql);
		this.tableService.getBussinessDao().getJdbcTemplate().execute(sql.toString());
		
		
//		//前：his_status_before ，后：his_status_after 
//		//将调息前的数据中的合同号update成与调息后的合同号一致,用前改后的，因为后的数据来源与正式表
//		sql = " update contract_fund_rent_plan_his set contract_id = ( ";
//		sql = sql + " select distinct son1.contract_id from contract_fund_rent_plan_his son1 ";
//		sql = sql + " where son1.doc_id = '"+docid+"'   ";
//		sql = sql + " and son1.mod_status = 'his_status_before' ";
//		sql = sql + " and son1.mod_reason = 'his_rate_change' ";
//		sql = sql + " and son1.doc_id = contract_fund_rent_plan_his.doc_id ";
//		sql = sql + " and son1.rent_list = contract_fund_rent_plan_his.rent_list ";
//		sql = sql + " ) ";
//		sql = sql + " where doc_id = '"+docid+"' ";
//		this.tableService.getBussinessDao().getJdbcTemplate().execute(sql.toString());
//		
	}
	
	/**
	 * <p>根据表名和前后状态将数据移往HIS的操作,作为前后对比操作。</p>
	 * @author sea
	 * @param docid 文档号
	 * @param mod_status 前后状态，前：his_status_before ，后：his_status_after
	 * @param table_name 表名称
	 * @return
	 */
	private String getHisSql(String docid,String mod_status,String table_name){
	
		StringBuffer sql = new StringBuffer();
		sql.append("  ")
		.append(" insert into contract_fund_rent_plan_his ( ")
		.append(" his_id,id,doc_id,rent_list,plan_date,rent,rent_adjust, ")
		.append(" corpus,corpus_business,year_rate,interest,interest_business,rent_overage, ")
		.append(" corpus_overage,interest_overage,penalty_overage,penalty, ")
		.append(" create_date,mod_status,mod_reason,creator_, ")
		.append(" modify_date,modificator_, ")
		.append(" contract_id ")
		.append(" ) ")
		.append(" select sys_guid() as his_id,tab.id, '"+docid+"',rent_list,plan_date,rent,rent_adjust, ")
		.append(" corpus,corpus_business,year_rate,interest,interest_business,rent_overage, ")
		.append(" corpus_overage,interest_overage,penalty_overage,penalty, ")
		.append(" create_date,'"+mod_status+"' as mod_status,'his_rate_change' as mod_reason,creator_, ")
		.append(" modify_date,modificator_, ")
		.append("  ");
		
		if("contract_fund_rent_plan_temp".equals(table_name)){
			sql.append(" sql2.id as contract_id ")
			.append(" from contract_fund_rent_plan_temp tab ")
			.append(" left join ( ")
			.append("   select distinct t2.id , t1.contract_id  from contract_fund_rent_plan_temp t1 ")
			.append("   left join contract_info t2 on t2.contract_id = t1.contract_id ")
			.append("   where t1.doc_id = '"+docid+"' ")
			.append(" ) sql2  on sql2.contract_id = tab.contract_id ")
			.append(" where doc_id = '"+docid+"'  ");
		}
		
		if("contract_fund_rent_plan".equals(table_name)){
			sql.append(" contract_id ")
			.append(" from "+table_name + " tab ")
			.append(" where contract_id in ( ")
			//正式表使用的是合同号对应的uuid
			.append("   select distinct t2.id as contract_id  from contract_fund_rent_plan_temp t1 ")
			.append("   left join contract_info t2 on t2.contract_id = t1.contract_id ")
			.append("   where t1.doc_id = '"+docid+"' ")
			.append(" ) ")
			.append("  ");
		}
		String returnSql = sql.toString();
		if(ResourceUtil.getDBType().indexOf("SQLSERVER")>-1){
			returnSql = returnSql.replaceAll("sys_guid", "replace(newid(), '-', '')");
		}else if(ResourceUtil.getDBType().indexOf("MYSQL")>-1){
			returnSql = returnSql.replaceAll("sys_guid", "uuid");
		}
		
		return sql.toString();
	}
	
	/**
	 * <p>根据表名和前后状态将数据移往HIS的操作,作为前后对比操作。</p>
	 * @author sea
	 * @param docid 文档号
	 * @param mod_status 前后状态，前：his_status_before ，后：his_status_after
	 * @param mod_reason@param mod_reason 流程名称: his_rate_change 表示调息流程; his_rate_change_back 表示调息回滚流程
	 * @param table_name 表名称
	 * @return
	 */
	private String getHisSql(String docid,String mod_status,String mod_reason,String table_name){
		String UUID = "SQLSERVER".equals(ResourceUtil.getDBType())?"replace(newid(), '-', '')":"sys_guid()";
		StringBuffer sql = new StringBuffer();
		sql.append("  ")
		.append(" insert into contract_fund_rent_plan_his ( ")
		.append(" his_id,id,doc_id,rent_list,plan_date,rent,rent_adjust, ")
		.append(" corpus,corpus_business,year_rate,interest,interest_business,rent_overage, ")
		.append(" corpus_overage,interest_overage,penalty_overage,penalty, ")
		.append(" create_date,mod_status,mod_reason,creator_, ")
		.append(" modify_date,modificator_, ")
		.append(" contract_id ")
		.append(" ) ")
		
		.append(" select lower(" + UUID + ") as his_id,tab.id, '"+docid+"',rent_list,plan_date,rent,rent_adjust, ")
		.append(" corpus,corpus_business,year_rate,interest,interest_business,rent_overage, ")
		.append(" corpus_overage,interest_overage,penalty_overage,penalty, ")
		.append(" create_date,'"+mod_status+"' as mod_status,'"+mod_reason+"' as mod_reason,creator_, ")
		.append(" modify_date,modificator_, ")
		.append("  ");
		
		if("contract_fund_rent_plan_temp".equals(table_name)){
			sql.append(" sql2.id as contract_id ")
			.append(" from contract_fund_rent_plan_temp tab ")
			.append(" left join ( ")
			.append("   select distinct t2.id , t1.contract_id  from contract_fund_rent_plan_temp t1 ")
			.append("   left join contract_info t2 on t2.contract_id = t1.contract_id ")
			.append("   where t1.doc_id = '"+docid+"' ")
			.append(" ) sql2  on sql2.contract_id = tab.contract_id ")
			.append(" where doc_id = '"+docid+"'  ");
		}
		
		if("contract_fund_rent_plan".equals(table_name)){
			sql.append(" contract_id ")
			.append(" from "+table_name + " tab ")
			.append(" where contract_id in ( ")
			//正式表使用的是合同号对应的uuid
			.append("   select distinct t2.id as contract_id  from contract_fund_rent_plan_temp t1 ")
			.append("   left join contract_info t2 on t2.contract_id = t1.contract_id ")
			.append("   left join fund_adjust_interest_c_temp temp on temp.contract_id=t2.id and temp.doc_id = '"+docid+"' ")
			.append("   where t1.doc_id = '"+docid+"'") //and temp.adjust_style='onhire'")
			.append(" ) ")
			.append("  ");
		}
		//***租金计划表更换标记***
//		if("contract_fund_rent_plan_before".equals(table_name)){
//			sql.append(" contract_id ")
//			.append(" from "+table_name + " tab ")
//			.append(" where contract_id in ( ")
//			//正式表使用的是合同号对应的uuid
//			.append("   select distinct t2.id as contract_id  from contract_fund_rent_plan_temp t1 ")
//			.append("   left join contract_info t2 on t2.contract_id = t1.contract_id ")
//			.append("   left join fund_adjust_interest_c_temp temp on temp.contract_id=t2.id and temp.doc_id = '"+docid+"' ")
//			.append("   where t1.doc_id = '"+docid+"' and temp.adjust_style='before'")
//			.append(" ) ")
//			.append("  ");
//		}
		
		return sql.toString();
	}
	/**
	 * 入资金收付计划临时表
	 * @param docid
	 * @param mod_status
	 * @param mod_reason
	 * @param table_name
	 * @return
	 */
	private String getFundFundHisSql(String docid,String mod_status,String mod_reason,String table_name){
		String UUID = "SQLSERVER".equals(ResourceUtil.getDBType())?"replace(newid(), '-', '')":"sys_guid()";
		StringBuffer sql = new StringBuffer();
		sql.append("  ")
		.append(" insert into contract_fund_fund_plan_his ( ")
		.append(" his_id,id,doc_id,payment_id,plan_date,plan_money, ")
		.append(" pay_obj,whether_deduct,is_prepay,fpnote,fee_type, ")
		.append(" fund_type,pay_cust,pay_type,settle_method, ")
		.append(" create_date,mod_status,mod_reason,creator_, ")
		.append(" modify_date,modificator_, ")
		.append(" contract_id ")
		.append(" ) ")
		
		.append(" select lower(" + UUID + ") as his_id,tab.id, '"+docid+"',payment_id,plan_date,plan_money, ")
		.append(" pay_obj,'','',fpnote,fee_type, ")
		.append(" '',pay_cust,pay_type,settle_method, ")
		.append(" '','"+mod_status+"' as mod_status,'"+mod_reason+"' as mod_reason,'', ")
		.append(" '','', ")
		.append("  ");
		
		if("fund_fund_plan_temp".equals(table_name)){
			sql.append(" sql2.id as contract_id ")
			.append(" from fund_fund_plan_temp tab ")
			.append(" left join ( ")
			.append("   select distinct t2.id , t1.contract_id  from fund_fund_plan_temp t1 ")
			.append("   left join contract_info t2 on t2.contract_id = t1.contract_id ")
			.append("   where t1.doc_id = '"+docid+"' ")
			.append(" ) sql2  on sql2.contract_id = tab.contract_id ")
			.append(" where doc_id = '"+docid+"'  ");
		}
		
		if("contract_fund_fund_plan".equals(table_name)){
			sql.append(" contract_id ")
			.append(" from "+table_name + " tab ")
			.append(" where contract_id in ( ")
			//正式表使用的是合同号对应的uuid
			.append("   select distinct t2.id as contract_id  from fund_fund_plan_temp t1 ")
			.append("   left join contract_info t2 on t2.contract_id = t1.contract_id ")
			.append("   where t1.doc_id = '"+docid+"' ")
			.append(" ) ")
			.append("  ");
		}
		
		return sql.toString();
	}
	
	/*
	 * @author sea
	 */
	@Override
	public void deleteHisAllSqlOfTx(String docid) throws DataAccessException, Exception{
		String delSql = " delete contract_fund_rent_plan_his ";
		delSql = delSql + " where doc_id = '"+docid+"' ";
		delSql = delSql + " and mod_reason = 'his_rate_change' "; 
		delSql = delSql + " and( mod_status = 'his_status_before' or mod_status = 'his_status_after' )";//前：his_status_before ，后：his_status_after
		log.info("调息撤销or流程草稿废弃时删除HIS表数据："+delSql);
		this.tableService.getBussinessDao().getJdbcTemplate().execute(delSql);
	}
	
	/*
	 * @author sea
	 */
	@Override
	public void deleteHisAllSqlOfTx(String docId,String contractId) throws DataAccessException, Exception{
		String delSql = " delete contract_fund_rent_plan_his ";
		delSql = delSql + " where doc_id = '"+docId+"' ";
		delSql = delSql + " and contract_id = '"+contractId+"' ";
		delSql = delSql + " and  mod_reason = 'his_rate_change' "; 
		delSql = delSql + " and( mod_status = 'his_status_before' or mod_status = 'his_status_after' )";//前：his_status_before ，后：his_status_after
		log.info("调息撤销or流程草稿废弃时删除HIS表数据："+delSql);
		this.tableService.getBussinessDao().getJdbcTemplate().execute(delSql);
	}
	
	/**
	 * <p>调息开始及结束时将租金计划移往HIS做对比记录前生成的删除HIS表记录SQL。</p>
	 * @author sea
	 * @param docid 文档号
	 * @param mod_status 前后状态: 前 : his_status_before| 后：his_status_after
	 * @param mod_reason 流程名称: his_rate_change 表示调息流程
	 * @return
	 */
	private String getDelHisSql(String docid,String mod_status,String mod_reason){
		String delSql = " delete contract_fund_rent_plan_his ";
		delSql = delSql + " where doc_id = '"+docid+"' ";
		delSql = delSql + " and mod_status = '"+mod_status+"' "; 
		delSql = delSql + " and mod_reason = '"+mod_reason+"' ";
		delSql = delSql + " and contract_id in ( ";
		delSql = delSql + "   select distinct t2.id  from contract_fund_rent_plan_temp t1 ";
		delSql = delSql + "   left join contract_info t2 on t2.contract_id = t1.contract_id ";
		delSql = delSql + "   where t1.doc_id = '"+docid+"' ";
		delSql = delSql + " ) ";
		
		return delSql;
	}
	/**
	 * <p>调息开始及结束时将资金计划移往HIS做对比记录前生成的删除HIS表记录SQL。</p>
	 * @author sea
	 * @param docid 文档号
	 * @param contractId合同号uuid
	 * @param mod_status 前后状态: 前 : his_status_before| 后：his_status_after
	 * @param mod_reason 流程名称: his_rate_change 表示调息流程
	 * @return
	 */
	private String getFundFundDelHisSql(String docid, String mod_status,String mod_reason){
		String delSql = " delete contract_fund_fund_plan_his ";
		delSql = delSql + " where doc_id = '"+docid+"' ";
		delSql = delSql + " and mod_status = '"+mod_status+"' "; 
		delSql = delSql + " and mod_reason = '"+mod_reason+"' ";
		delSql = delSql + " and contract_id in ( ";
		delSql = delSql + "   select distinct t2.id  from fund_fund_plan_temp t1 ";
		delSql = delSql + "   left join contract_info t2 on t2.contract_id = t1.contract_id ";
		delSql = delSql + "   where t1.doc_id = '"+docid+"' ";
		delSql = delSql + " ) ";
		
		return delSql;
		//		String delSql = " delete contract_fund_rent_plan_his ";
//		delSql = delSql + " where doc_id = '"+docid+"' ";
//		delSql = delSql + " and mod_status = '"+mod_status+"' "; 
//		delSql = delSql + " and mod_reason = '"+mod_reason+"' ";
//		delSql = delSql + " and contract_id = '"+contractId+"' ";
//		delSql = delSql + " and contract_id in ( ";
//		delSql = delSql + "   select distinct t2.id  from contract_fund_rent_plan_temp t1 ";
//		delSql = delSql + "   left join contract_info t2 on t2.contract_id = t1.contract_id ";
//		delSql = delSql + "   where t1.doc_id = '"+docid+"' ";
//		delSql = delSql + " ) ";
//		
//		return delSql;
	}
}
