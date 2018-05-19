package com.reckon.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.KnowingRentBean;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.entity.contract.reckon.cash.ContractCashDetail;
import com.reckon.entity.contract.reckon.cash.ContractCashDetailHis;
import com.reckon.entity.contract.reckon.cash.ContractFinaCashDetail;
import com.reckon.entity.contract.reckon.cash.ContractFinaCashDetailHis;
import com.reckon.entity.contract.reckon.condition.ContractCondition;
import com.reckon.entity.contract.reckon.condition.ContractConditionHis;
import com.reckon.entity.contract.reckon.fina.ContractFinaRentPlan;
import com.reckon.entity.contract.reckon.fina.ContractFinaRentPlanBefore;
import com.reckon.entity.contract.reckon.fina.ContractFinaRentPlanHis;
import com.reckon.entity.contract.reckon.fund.ContractFundRentPlanBefore;
import com.reckon.entity.proj.KnowingRent;
import com.reckon.entity.proj.ProjCashDetail;
import com.reckon.entity.proj.ProjFinaCashDetail;
import com.reckon.entity.proj.ProjFinaRentPlan;
import com.reckon.entity.proj.ProjFundFundPlan;
import com.reckon.entity.proj.ProjFundRentPlan;
import com.reckon.service.RentCalculateService;
import com.reckon.service.RentConditionDataOperator;
import com.reckon.util.copyObjectToHisTools;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;
import com.tenwa.leasing.entity.fund.ContractFundFundPlanHis;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;
import com.tenwa.leasing.entity.fund.ContractFundRentPlanHis;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.entity.proj.SpecialRegularCalConfig;

/**
 * 
 * @author 徐云龙
 * @date 2014-1-25上午9:59:49
 * @info 交易结构，租金计划,现金流，财务租金计划，财务现金流的保存，加载
 * @Copyright Tenwa
 */
@Service(value = "RentConditionDataService")
public class RentConditionDataOperatorImpl implements RentConditionDataOperator {

	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name = "rentCalculateService")
	private RentCalculateService rentCalculateService;
	
	private static Logger log = Logger.getLogger(RentConditionDataOperatorImpl.class);
	
	@Override
	public void saveProjectContionAndRentAndFundToDatabase(ProjInfo projInfo,
			Map<String, String> variablesMap, String conditionString,
			String rentstr, String rentFlowStr, String financeStr,
			String financeFlowStr, String fundStr) throws Exception {
		Map<String, String> dictDataClassMapping = new HashMap<String, String>();
		// 2.1商务条件 #此处的商务条件统一用framework_condition 为了通用性
		// 租金测算页面的域名都用framework_condition开头而不是表名#
		if(!variablesMap.containsKey("yearrate")||new BigDecimal(variablesMap.get("yearrate")).compareTo(BigDecimal.ZERO)==0 ){
			variablesMap.put("yearrate", variablesMap.get("yearrate_helper"));
		}
		this.tableService.updateOneToOneEntity(projInfo, "projCondition","projId", variablesMap, null);
		// 2.2合同租金计划
		String json_fund_rent_plan_str = variablesMap.get(rentstr);
		this.tableService.updateOneToManyCollections(projInfo,"projFundRentPlans", ProjFundRentPlan.class, "projId",json_fund_rent_plan_str, null);
		// 2.3合同现金流
		String json_fund_cash_flow_str = variablesMap.get(rentFlowStr);
		this.tableService.updateOneToManyCollections(projInfo,"projCashDetails", ProjCashDetail.class, "projId",json_fund_cash_flow_str, null);
		// 2.4会计租金计划
		if (null != financeStr && (!"".equals(financeStr)) && null!=variablesMap.get(financeStr)) {
			String json_finance_rent_plan_str = variablesMap.get(financeStr);
			this.tableService.updateOneToManyCollections(projInfo,"projFinaRentPlans", ProjFinaRentPlan.class, "projId",json_finance_rent_plan_str, null);
		}
		// 2.5会计现金流
		if (null != financeFlowStr && (!"".equals(financeFlowStr)) && null!=variablesMap.get(financeFlowStr)) {
			String json_finance_cash_flow_str = variablesMap.get(financeFlowStr);
			this.tableService.updateOneToManyCollections(projInfo,"projFinaCashDetails", ProjFinaCashDetail.class, "projId",json_finance_cash_flow_str, null);
		}
		// 2.6资金收付计划
		String json_fund_fund_charge_str = variablesMap.get(fundStr);
		dictDataClassMapping = new HashMap<String, String>();
		dictDataClassMapping.put("DictionaryData", "code");
		dictDataClassMapping.put("CustInfo", "id");
		this.tableService.updateOneToManyCollections(projInfo,"projFundFundPlans", ProjFundFundPlan.class, "projId",json_fund_fund_charge_str, dictDataClassMapping);
		
		//sea new add****************************************************************************************
		//2.7  需要将已知租金法情况下的输入的租金多行域信息归档入单独的一张表中
		this.addKnowingRentData(variablesMap, null, projInfo);
		//***************************************************************************************************
		this.addSpecialRegularConfigToDB(variablesMap, null, projInfo);
	}
	@Override
	public void LoadProjectContionAndRentAndFundToMap(ProjInfo projInfo,
			Map<String, String> variablesMap, String conditionString,
			String rentstr, String rentFlowStr, String financeStr,
			String financeFlowStr, String fundStr) throws Exception {
		Map<String, String> queryMainObjectMap = new HashMap<String, String>();
		queryMainObjectMap.put("proj_id", projInfo.getId());// 注明：项目其它信息(这里projInfo_id在后面的JsonArrayData中很多用到这种格式)
		// 因为这里是按照XML配置查询 所以在合同审批发起的时候数据是从项目表来的 所以这里的xml配置的是项目的XML
		// 为了租金测算的通用性此处的名字不用contract_codition 而用framework_condition
		// 加载交易
		if (projInfo.getProjCondition() != null) {
			variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(projInfo.getProjCondition(), null,""));
		}
		if (!projInfo.getProjFundRentPlans().isEmpty()) {// 租金计划
			variablesMap.put(rentstr, this.tableService.getJsonArrayData("eleasing/workflow/proj/proj_approval/proj_fund_rent_plan.xml",queryMainObjectMap).toString());
		}
		if (!projInfo.getProjCashDetails().isEmpty()) {// 合同现金流
			variablesMap.put(rentFlowStr, this.tableService.getJsonArrayData("eleasing/workflow/proj/proj_approval/proj_fund_cash_flow.xml",queryMainObjectMap).toString());
		}
		if (!projInfo.getProjFundFundPlans().isEmpty()) {// 会计租金计划
			variablesMap.put(financeStr,this.tableService.getJsonArrayData("eleasing/workflow/proj/proj_approval/proj_fina_rent_plan.xml",queryMainObjectMap).toString());
		}
		if (!projInfo.getProjFundFundPlans().isEmpty()) {// 会计现金流
			variablesMap.put(financeFlowStr,this.tableService.getJsonArrayData("eleasing/workflow/proj/proj_approval/proj_fina_cash_flow.xml",queryMainObjectMap).toString());
			}
		if (null != fundStr || (!"".equals(fundStr)))
			if (!projInfo.getProjFundFundPlans().isEmpty()) {// 项目资金计划
				variablesMap.put(fundStr,this.tableService.getJsonArrayData("eleasing/workflow/proj/proj_approval/proj_fund_fund_plan.xml",queryMainObjectMap).toString());
			}
		//分段测算配置
		JSONArray array1= this.tableService.getJsonArrayData("eleasing/workflow/proj/proj_approval/special_regular.xml",queryMainObjectMap);
		if(null!=array1&&array1.length()>0){
			variablesMap.put("json_special_regular_str",array1.getJSONObject(0).optString("special_regular_jsons").replaceAll("\'", "\""));
		}
		
		//已知租金法的 json字符串返回 json_knowing_rent_plan_str
		//this.secrhKnowingRentJsons(variablesMap, null, projInfo);
		
	}

	@Override
	public void LoadProjectContionAndRentAndFundToContractMap(
			String contractid, ProjInfo projInfo,
			Map<String, String> variablesMap, String conditionString,
			String rentstr, String rentFlowStr, String financeStr,
			String financeFlowStr, String fundStr) throws Exception {
		this.LoadProjectContionAndRentAndFundToMap(projInfo, variablesMap,conditionString, rentstr, rentFlowStr, financeStr,financeFlowStr, null);
		// 将项目层的资金计划移到合同层。并把编号中的项目号改为合同号
		Map<String, String> queryMainObjectMap = new HashMap<String, String>();
		queryMainObjectMap.put("proj_id", projInfo.getId());
		if (!projInfo.getProjFundFundPlans().isEmpty()) {// 项目资金计划
			// 替换资金计划编号
			JSONArray json_fund_fund_charge = this.tableService.getJsonArrayData("eleasing/workflow/proj/proj_approval/proj_fund_fund_plan.xml",queryMainObjectMap);
			for (int i = 0; i < json_fund_fund_charge.length(); i++) {
				JSONObject fund_fund_charge = (JSONObject) json_fund_fund_charge.get(i);
				String paymentidStr = fund_fund_charge.optString("paymentid");
				if (paymentidStr != null && !paymentidStr.equals("")) {
					fund_fund_charge.putOpt("paymentid", paymentidStr.replace(projInfo.getProjId().toLowerCase(), contractid.toLowerCase()));
				}
				fund_fund_charge.putOpt("contractid",contractid.toLowerCase());
			}
			variablesMap.put(fundStr, json_fund_fund_charge.toString());
		}
		
	}

	@Override
	public void saveContractContionAndRentAndFundToDatabase(
			ContractInfo contractInfo, Map<String, String> variablesMap,
			String conditionString, String rentstr, String rentFlowStr,
			String financeStr, String financeFlowStr, String fundStr)
			throws Exception {
		Map<String, String> dictDataClassMapping = new HashMap<String, String>();
		dictDataClassMapping.put("DictionaryData", "code");// 所有数据字典都遵循用code传值
															
		dictDataClassMapping.put("CustInfo", "id");
		// 2.1商务条件 此处的商务条件统一用framework_condition 为了通用性
		// 租金测算页面的域名都用framework_condition开头而不是表名
		//2014-12-2 18:37 更改交易结构、改用存储过程
//		List<String> list = new ArrayList<String>();
//		list.add("CONTRACT_ID");
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("CONTRACT_ID", contractInfo.getId());
		String docid = variablesMap.get("docid");
//		String flag = "CONTRACT_ID = ''"+contractInfo.getContractId()+"''" + " and DOC_ID = ''"+docid+"''";
//		this.tableService.hisEntityByProcedures("CONTRACT_CONDITION_TEMP", "CONTRACT_CONDITION", flag, map,list);
		/*//对象copy
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contractId", contractInfo.getContractId());
		map.put("docId", docid);
		Map<String, Object> otherMap = new HashMap<String, Object>();
		otherMap.put("contractid", contractInfo);
		ContractConditionTemp contractConditionTemp = tableService.findEntityByProperties(ContractConditionTemp.class, map).get(0);
		//ContractConditionTemp contractConditionTemp = 
*/		//ContractCondition contractCondition = (ContractCondition)copyObjectToHisTools.CopyObjectAndAddOtherProperty(tableService, contractConditionTemp, ContractCondition.class, otherMap);
		ContractCondition contractCondition   =  contractInfo.getContractCondition();
		String id="";
		if(null==contractCondition){contractCondition=new ContractCondition();}else{id=contractCondition.getId();}
		contractCondition = this.tableService.copyAndOverrideExistedValueFromStringMap(variablesMap, contractCondition, dictDataClassMapping,true);
		contractCondition.setContractId(contractInfo);
		contractCondition.setDocId(docid);
		contractCondition.setId(id);
		this.tableService.saveEntity(contractCondition);
		//System.out.println(0/0);
		//this.tableService.updateOneToOneEntity(contractInfo,"contractCondition", "contractId", variablesMap, null,conditionString);
		// 2.2合同租金计划
		if (null != rentstr && (!"".equals(rentstr))) {
			String json_fund_rent_plan_str = variablesMap.get(rentstr);
			this.tableService.updateOneToManyCollections(contractInfo,"contractFundRentPlans", ContractFundRentPlan.class,"contractId", json_fund_rent_plan_str, null);
		}
		// 2.3合同现金流
		String json_fund_cash_flow_str = variablesMap.get(rentFlowStr);
		this.tableService.updateOneToManyCollections(contractInfo,"contractCashDetails", ContractCashDetail.class, "contractId",json_fund_cash_flow_str, null);
		// 2.4资金计划

		if (null != fundStr && (!"".equals(fundStr))) {
			String json_contract_fund_charge_str = variablesMap.get(fundStr);
			dictDataClassMapping = new HashMap<String, String>();
			dictDataClassMapping.put("DictionaryData", "code");
			dictDataClassMapping.put("CustInfo", "id");
			this.tableService.updateOneToManyCollections(contractInfo,"fundFundChargePlans", ContractFundFundPlan.class,"contractId", json_contract_fund_charge_str,dictDataClassMapping);
		}
		// 2.5会计租金计划
		if (null != financeStr && (!"".equals(financeStr))) {
			String json_finance_rent_plan_str = variablesMap.get(financeStr);
			this.tableService.updateOneToManyCollections(contractInfo,"contractFinaRentPlans", ContractFinaRentPlan.class,"contractId", json_finance_rent_plan_str, null);
		}
		// 2.6会计现金流
		if (null != financeFlowStr && (!"".equals(financeFlowStr))) {
		String json_finance_cash_flow_str = variablesMap.get(financeFlowStr);
		    if(null!=json_finance_cash_flow_str&&(!"[]".equals(json_finance_cash_flow_str))){
		    this.tableService.updateOneToManyCollections(contractInfo,"contractFinaCashDetails", ContractFinaCashDetail.class,"contractId", json_finance_cash_flow_str, null);
		}}
	}

	@Override
	public void LoadContractContionAndRentAndFundToMap(ContractInfo contractInfo, Map<String, String> variablesMap,String conditionString, String rentstr, String rentFlowStr,String financeStr, String financeFlowStr, String fundStr)throws Exception {
		Map<String, String> queryMainObjectMap = new HashMap<String, String>();
		queryMainObjectMap.put("contract_id", contractInfo.getId());
		// 加载交易结构
		if (null != contractInfo.getContractCondition()) {
			variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(contractInfo.getContractCondition(), null,conditionString));
		}
		// 因为这里是按照XML配置查询 所以在合同审批发起的时候数据是从项目表来的 所以这里的xml配置的是项目的XML
		if (!contractInfo.getContractFundRentPlans().isEmpty()&& (rentstr != null && !"".equals(rentstr))) {// 租金计划
			variablesMap.put(rentstr, this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_fund_rent_plan.xml",queryMainObjectMap).toString());
		}
		if (!contractInfo.getContractCashDetails().isEmpty()) {// 合同现金流
			variablesMap.put(rentFlowStr, this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_fund_cash_flow.xml",queryMainObjectMap).toString());
		}
		if (!contractInfo.getContractFinaRentPlans().isEmpty()&& (financeStr != null && !"".equals(financeStr))) {// 会计租金计划
			variablesMap.put(financeStr, this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_fina_rent_plan.xml",queryMainObjectMap).toString());
		}
		if (!contractInfo.getContractFinaCashDetails().isEmpty()) {// 会计现金流
			variablesMap.put(financeFlowStr,this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_fina_cash_flow.xml",queryMainObjectMap).toString());
		}
		if (!contractInfo.getFundFundChargePlans().isEmpty()) {// 资金计划
			variablesMap.put(fundStr, this.tableService.getJsonArrayData("eleasing/workflow/contract/fund_fund_charge_plan.xml",queryMainObjectMap).toString());
		}
		//分段测算配置
		JSONArray array1= this.tableService.getJsonArrayData("eleasing/workflow/proj/proj_approval/special_regular.xml",queryMainObjectMap);
		if(null!=array1&&array1.length()>0){
			variablesMap.put("json_special_regular_str",array1.getJSONObject(0).optString("special_regular_jsons").replaceAll("\'", "\""));
		}
		if (!contractInfo.getContractFundRentPlans().isEmpty()&& (rentstr != null && !"".equals(rentstr))) {
			//分段测算配置拆分
			RentPlanContrCalDAOImpl rpcd = new RentPlanContrCalDAOImpl();
			ConditionBean cb=new ConditionBean();
			cb.setContractId(contractInfo.getId());
			int	startList=rpcd.findNoIncomeRentPlanMinListChange(cb);
			variablesMap.put("rentadjustlist", String.valueOf(startList));
			if(null!=array1&&array1.length()>0){
				String str=array1.getJSONObject(0).optString("special_regular_jsons").replaceAll("\'", "\"");
				JSONArray array=new JSONArray(str);
				JSONArray newarray=new JSONArray();
				for(int i=0;i<array.length();i++){
					JSONObject obj=array.getJSONObject(i);
					int stlist=Integer.parseInt( obj.optString("startlist"));
					int endlist=Integer.parseInt( obj.optString("endlist"));
					if(startList>stlist&&stlist<=endlist){
						JSONObject newobj=new JSONObject();
						newobj.put("id", "");
						newobj.put("docid", "");
						newobj.put("startlist", stlist);
						newobj.put("endlist", startList-1);
						newobj.put("regular_settlemethodname", obj.optString("regular_settlemethodname"));
						newobj.put("regular_settlemethod", obj.optString("regular_settlemethod"));
						newobj.put("regular_planmoney", obj.optString("regular_planmoney"));
						newobj.put("regular_incomenumberyearname", obj.optString("regular_incomenumberyearname"));
						newobj.put("regular_incomenumberyear", obj.optString("regular_incomenumberyear"));
						newobj.put("regular_months", obj.optString("regular_months"));
						newobj.put("_id", obj.optString("_id"));
						newobj.put("_uid", obj.optString("_uid"));
						newobj.put("_state", obj.optString("_state"));
						newobj.put("readOnly", true);
						newarray.put(newobj);
						JSONObject newobj2=new JSONObject();
						newobj2.put("id", "");
						newobj2.put("docid", "");
						newobj2.put("startlist", startList);
						newobj2.put("endlist", endlist);
						newobj2.put("regular_settlemethodname", obj.optString("regular_settlemethodname"));
						newobj2.put("regular_settlemethod", obj.optString("regular_settlemethod"));
						newobj2.put("regular_planmoney", obj.optString("regular_planmoney"));
						newobj2.put("regular_incomenumberyearname", obj.optString("regular_incomenumberyearname"));
						newobj2.put("regular_incomenumberyear", obj.optString("regular_incomenumberyear"));
						newobj2.put("regular_months", obj.optString("regular_months"));
						newobj2.put("_id", obj.optString("_id"));
						newobj2.put("_uid", obj.optString("_uid"));
						newobj2.put("_state", obj.optString("_state"));
						newarray.put(newobj2);
					}else{
						if(endlist<startList){
							obj.put("readOnly", true);
						}
						newarray.put(obj);
					}
				}
				variablesMap.put("json_special_regular_str",newarray.toString());
			}
		}
	}

	@Override
	public void saveOrLoadRentAndFinalBefore(ContractInfo contractInfo,
			Map<String, String> variablesMap, String conditionString,
			String rentstr, String rentFlowStr, String financeStr,
			String financeFlowStr, String fundStr, String type)
			throws Exception {
		if ("save".equals(type)) {
			saveContractContionAndRentAndFundToDatabase(contractInfo,variablesMap, conditionString, null, rentFlowStr, null,financeFlowStr, fundStr);
			// 保存合同租金计划
			String json_fund_rent_plan_str = variablesMap.get(rentstr);
			this.tableService.updateOneToManyCollections(contractInfo,"contractFundRentPlansBefore",ContractFundRentPlanBefore.class, "contractId",json_fund_rent_plan_str, null);
			// 保存会计租金计划
			String json_finance_rent_plan_str = variablesMap.get(financeStr);
		    if(null!=json_finance_rent_plan_str&&(!"[]".equals(json_finance_rent_plan_str))){
		    	System.out.println(json_finance_rent_plan_str);
			  this.tableService.updateOneToManyCollections(contractInfo,"contractFinaRentPlansBefore",ContractFinaRentPlanBefore.class, "contractId",json_finance_rent_plan_str, null);
		    }
			//sea new add****************************************************************************************
			//2.7  需要将已知租金法情况下的输入的租金多行域信息归档入单独的一张表中
			this.addKnowingRentData(variablesMap, contractInfo, null);
			//***************************************************************************************************
			this.addSpecialRegularConfigToDB(variablesMap, contractInfo, null);
		}else if ("saveput".equals(type)) {
			// 保存合同租金计划
			String json_fund_rent_plan_str = variablesMap.get(rentstr);
			this.tableService.updateOneToManyCollections(contractInfo,"contractFundRentPlansBefore",ContractFundRentPlanBefore.class, "contractId",json_fund_rent_plan_str, null);
			// 保存会计租金计划
			String json_finance_rent_plan_str = variablesMap.get(financeStr);
		    if(null!=json_finance_rent_plan_str&&(!"[]".equals(json_finance_rent_plan_str))){
		    	System.out.println(json_finance_rent_plan_str);
			  this.tableService.updateOneToManyCollections(contractInfo,"contractFinaRentPlansBefore",ContractFinaRentPlanBefore.class, "contractId",json_finance_rent_plan_str, null);
		    }
		    String json_fund_cash_flow_str = variablesMap.get(rentFlowStr);
			this.tableService.updateOneToManyCollections(contractInfo,"contractCashDetails", ContractCashDetail.class, "contractId",json_fund_cash_flow_str, null);
			
			//sea new add****************************************************************************************
			//2.7  需要将已知租金法情况下的输入的租金多行域信息归档入单独的一张表中
			this.addKnowingRentData(variablesMap, contractInfo, null);
			//***************************************************************************************************
			this.addSpecialRegularConfigToDB(variablesMap, contractInfo, null);
			
		} else if ("load".equals(type)) {
			LoadContractContionAndRentAndFundToMap(contractInfo, variablesMap,conditionString, null, rentFlowStr, null, financeFlowStr,fundStr);
			Map<String, String> queryMainObjectMap = new HashMap<String, String>();
			queryMainObjectMap.put("contract_id", contractInfo.getId());
			// 加载合同租金计划
			if (!contractInfo.getContractFundRentPlansBefore().isEmpty()) {// 租金计划
				variablesMap.put(rentstr,this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_fund_rent_plan_before.xml",queryMainObjectMap).toString());
			}
			// 加载会计租金计划
			if (!contractInfo.getContractFinaRentPlansBefore().isEmpty()) {// 会计租金计划
				variablesMap.put(financeStr,this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_fina_rent_plan_before.xml",queryMainObjectMap).toString());
			}
			//分段测算配置
			JSONArray array1= this.tableService.getJsonArrayData("eleasing/workflow/proj/proj_approval/special_regular.xml",queryMainObjectMap);
			if(null!=array1&&array1.length()>0){
				variablesMap.put("json_special_regular_str",array1.getJSONObject(0).optString("special_regular_jsons").replaceAll("\'", "\""));
			}
			//已知租金法的 json字符串返回 json_knowing_rent_plan_str
			//this.secrhKnowingRentJsons(variablesMap, contractInfo, null);
		}
	}

	@Override
	public void updateContractConditionDataAndSaveDatatoHis(ContractInfo contractInfo,Map<String,String> variablesMap,
			String docId, String hisType, String conditionString,
			String rentstr, String rentFlowStr, String financeStr,
			String financeFlowStr,String fundFundStr) throws Exception {
		DictionaryData hisBefore=new DictionaryData();
		DictionaryData hisAfter=new DictionaryData();
		DictionaryData hisTyped=new DictionaryData();
		hisBefore=this.tableService.findEntityByID(DictionaryData.class, "his_status_before");
		hisAfter=this.tableService.findEntityByID(DictionaryData.class, "his_status_after");
		hisTyped=this.tableService.findEntityByID(DictionaryData.class, hisType);
		Map<String,Object>otherPropMap=new HashMap<String,Object>();
		otherPropMap.put("docid", docId);
		otherPropMap.put("modreason", hisTyped);
		
		log.info("保存交易结构并his开始时间:"+DateUtil.getSystemDateTime());
		//保存交易结构(先his再保存再his)
		if(null!=conditionString&&(!"".equals(conditionString))){
			ContractCondition condition=contractInfo.getContractCondition();
			otherPropMap.put("modstatus", hisBefore);
			if(null!=condition){
			   ContractConditionHis conditionHis=(ContractConditionHis)copyObjectToHisTools.CopyObjectAndAddOtherProperty(tableService,condition, ContractConditionHis.class, otherPropMap);
			   this.tableService.saveEntity(conditionHis);
			}
			ContractCondition newcondition=this.tableService.updateOneToOneEntity(contractInfo,"contractCondition", "contractId", variablesMap, null);
			otherPropMap.put("modstatus", hisAfter);
			ContractConditionHis newconditionHis=(ContractConditionHis)copyObjectToHisTools.CopyObjectAndAddOtherProperty(tableService,newcondition, ContractConditionHis.class, otherPropMap);
			this.tableService.saveEntity(newconditionHis);
		}
		log.info("保存交易结构并his结束时间:"+DateUtil.getSystemDateTime());
		
		log.info("保存业务租金计划开始时间:"+DateUtil.getSystemDateTime());
		//保存业务租金(先his再保存再his)
		if(null!=rentstr&&(!"".equals(rentstr))&&(!"null".equals(rentstr))){
			Set<ContractFundRentPlan> RentPlans=contractInfo.getContractFundRentPlans();
			otherPropMap.put("modstatus", hisBefore);
			if(null!=RentPlans && RentPlans.size()>0){
			   List<ContractFundRentPlanHis> RentPlanshis=(List<ContractFundRentPlanHis>)copyObjectToHisTools.CopySetObjectAndAddOtherProperty(tableService,RentPlans, ContractFundRentPlanHis.class, otherPropMap);
		       this.tableService.saveAllEntities(RentPlanshis);
			}
			String json_rentstr=variablesMap.get(rentstr).toString();
			if((!"".equals(json_rentstr))&&(!"[]".equals(json_rentstr))){
		       Map<String,Set>rentMap=(Map<String,Set>)copyObjectToHisTools.CopyOjbectFormStrAndRemove(tableService, RentPlans, ContractFundRentPlan.class, variablesMap.get(rentstr).toString(),"");
		       Set<ContractFundRentPlan>saveset=rentMap.get("save");
		       Set<ContractFundRentPlan>removeset=rentMap.get("remove");
		       for(ContractFundRentPlan cp : saveset){
		    	   cp.setContractId(contractInfo);
		    	   this.tableService.saveOrUpdateEntity(cp);
		       }   
		       
		       this.tableService.removeAllEntites(removeset);
		       otherPropMap.put("modstatus", hisAfter);
		       List<ContractFundRentPlanHis> newRentPlanshis=(List<ContractFundRentPlanHis>)copyObjectToHisTools.CopySetObjectAndAddOtherProperty(tableService,saveset, ContractFundRentPlanHis.class, otherPropMap);
		       this.tableService.saveAllEntities(newRentPlanshis);
			}
		}
		log.info("保存业务租金计划结束时间:"+DateUtil.getSystemDateTime());
		
		log.info("保存业务现金流开始时间:"+DateUtil.getSystemDateTime());
		//保存业务现金流(先his再保存再his)
		if(null!=rentFlowStr&&(!"".equals(rentFlowStr))){
			Set<ContractCashDetail> CashPlans=contractInfo.getContractCashDetails();
			otherPropMap.put("modstatus", hisBefore);
			if(null!=CashPlans&&CashPlans.size()>0){
			   List<ContractCashDetailHis> CashPlanshis=(List<ContractCashDetailHis>)copyObjectToHisTools.CopySetObjectAndAddOtherProperty(tableService,CashPlans, ContractCashDetailHis.class, otherPropMap);
		       this.tableService.saveAllEntities(CashPlanshis);
			}
			String json_rentFlowStr=variablesMap.get(rentFlowStr).toString();
			if((!"".equals(json_rentFlowStr))&&(!"[]".equals(json_rentFlowStr))){
		       Map<String,Set>rentMap=(Map<String,Set>)copyObjectToHisTools.CopyOjbectFormStrAndRemove(tableService, CashPlans, ContractCashDetail.class, json_rentFlowStr,"");
		       Set<ContractCashDetail>saveset=rentMap.get("save");
		       Set<ContractCashDetail>removeset=rentMap.get("remove");
		       for(ContractCashDetail cd : saveset){
		    	   cd.setContractId(contractInfo);
		    	   this.tableService.saveOrUpdateEntity(cd);
		       }
		       this.tableService.removeAllEntites(removeset);
		       otherPropMap.put("modstatus", hisAfter);
		       List<ContractFundRentPlanHis> newCashPlanshis=(List<ContractFundRentPlanHis>)copyObjectToHisTools.CopySetObjectAndAddOtherProperty(tableService,saveset, ContractCashDetailHis.class, otherPropMap);
		       this.tableService.saveAllEntities(newCashPlanshis);
			}
		}
		log.info("保存业务现金流结束时间:"+DateUtil.getSystemDateTime());
		
		log.info("保存财务租金计划开始时间:"+DateUtil.getSystemDateTime());
		//保存财务租金(先his再保存再his)
		if(null!=financeStr&&(!"".equals(financeStr))){
			Set<ContractFinaRentPlan> RentPlans=contractInfo.getContractFinaRentPlans();
			otherPropMap.put("modstatus", hisBefore);
			if(null!=RentPlans&&RentPlans.size()>0){
			   List<ContractFinaRentPlanHis> RentPlanshis=(List<ContractFinaRentPlanHis>)copyObjectToHisTools.CopySetObjectAndAddOtherProperty(tableService,RentPlans, ContractFinaRentPlanHis.class, otherPropMap);
		       this.tableService.saveAllEntities(RentPlanshis);
			}
			String json_financeStr=variablesMap.get(financeStr).toString();
			if((!"".equals(json_financeStr))&&(!"[]".equals(json_financeStr))){
		        Map<String,Set>rentMap=(Map<String,Set>)copyObjectToHisTools.CopyOjbectFormStrAndRemove(tableService, RentPlans, ContractFinaRentPlan.class, variablesMap.get(financeStr).toString(),"");
		        Set<ContractFinaRentPlan>saveset=rentMap.get("save");
		        Set<ContractFinaRentPlan>removeset=rentMap.get("remove");
		        for(ContractFinaRentPlan cr : saveset){
		        	cr.setContractId(contractInfo);
		        	this.tableService.saveOrUpdateEntity(cr);
		        }
		        this.tableService.removeAllEntites(removeset);
		        otherPropMap.put("modstatus", hisAfter);
		        List<ContractFinaRentPlanHis> newRentPlanshis=(List<ContractFinaRentPlanHis>)copyObjectToHisTools.CopySetObjectAndAddOtherProperty(tableService,saveset, ContractFinaRentPlanHis.class, otherPropMap);
		        this.tableService.saveAllEntities(newRentPlanshis);
			}
		}
		log.info("保存财务租金计划结束时间:"+DateUtil.getSystemDateTime());
		
		log.info("保存财务现金流开始时间:"+DateUtil.getSystemDateTime());
		//保存财务现金流(先his再保存再his)
		if(null!=financeFlowStr&&(!"".equals(financeFlowStr))){
			Set<ContractFinaCashDetail> CashPlans=contractInfo.getContractFinaCashDetails();
			otherPropMap.put("modstatus", hisBefore);
			if(null!=CashPlans&&CashPlans.size()>0){
			List<ContractFinaCashDetailHis> CashPlanshis=(List<ContractFinaCashDetailHis>)copyObjectToHisTools.CopySetObjectAndAddOtherProperty(tableService,CashPlans, ContractFinaCashDetailHis.class, otherPropMap);
		    this.tableService.saveAllEntities(CashPlanshis);
			}
			String json_financeFlowStr=variablesMap.get(financeFlowStr).toString();
			if((!"".equals(json_financeFlowStr))&&(!"[]".equals(json_financeFlowStr))){
		    Map<String,Set>rentMap=(Map<String,Set>)copyObjectToHisTools.CopyOjbectFormStrAndRemove(tableService, CashPlans, ContractFinaCashDetail.class, variablesMap.get(financeFlowStr).toString(),"");
		    Set<ContractFinaCashDetail>saveset=rentMap.get("save");
		    Set<ContractFinaCashDetail>removeset=rentMap.get("remove");
		    for(ContractFinaCashDetail cd : saveset){
		    	this.tableService.saveOrUpdateEntity(cd);
		    }
		    this.tableService.removeAllEntites(removeset);
		    otherPropMap.put("modstatus", hisAfter);
		    List<ContractFinaCashDetailHis> newCashPlanshis=(List<ContractFinaCashDetailHis>)copyObjectToHisTools.CopySetObjectAndAddOtherProperty(tableService,saveset, ContractFinaCashDetailHis.class, otherPropMap);
		    this.tableService.saveAllEntities(newCashPlanshis);
			}
		}
		log.info("保存财务现金流结束时间:"+DateUtil.getSystemDateTime());
		
		log.info("保存资金计划开始时间："+DateUtil.getSystemDateTime());
		if(null != fundFundStr && "" != fundFundStr){
			Map<String, Object> propertiesMap = new HashMap<String, Object>();
			propertiesMap.put("contractId", contractInfo);
			List<ContractFundFundPlan> fundPlans = this.tableService.findEntityByProperties(ContractFundFundPlan.class, propertiesMap);
			String fundPlanJsonStr = variablesMap.get(fundFundStr);
			fundPlans = copyObjectToHisTools.CopyFundPlanAndOverwrite(tableService, fundPlans, ContractFundFundPlan.class, fundPlanJsonStr);
			for(ContractFundFundPlan fundPlan : fundPlans){
				this.tableService.saveOrUpdateEntity(fundPlan);
			}
		}
		log.info("保存资金计划结束时间："+DateUtil.getSystemDateTime());
	}

	@Override
	public void updateContractFundDataAndSaveDatatoHis(ContractInfo contractInfo,Map<String,String> variablesMap,
			String docId, String hisType,String fundStrOne,String fundStrTwo) throws Exception {
		DictionaryData hisBefore=new DictionaryData();
		DictionaryData hisAfter=new DictionaryData();
		DictionaryData hisTyped=new DictionaryData();
		hisBefore=this.tableService.findEntityByID(DictionaryData.class, "his_status_before");
		hisAfter=this.tableService.findEntityByID(DictionaryData.class, "his_status_after");
		hisTyped=this.tableService.findEntityByID(DictionaryData.class, hisType);
		Map<String,Object>otherPropMap=new HashMap<String,Object>();
		otherPropMap.put("docid", docId);
		otherPropMap.put("modreason", hisTyped);
		if(null!=fundStrOne||(!"".equals(fundStrOne))){
			Set<ContractFundFundPlan> fundPlans=contractInfo.getFundFundChargePlans();
			otherPropMap.put("modstatus", hisBefore);
			if(null!=fundPlans&&fundPlans.size()>0){
			   List<ContractFundFundPlanHis> fundPlanshis=(List<ContractFundFundPlanHis>)copyObjectToHisTools.CopySetObjectAndAddOtherProperty(tableService,fundPlans, ContractFundFundPlanHis.class, otherPropMap);
		       this.tableService.saveAllEntities(fundPlanshis);
			}			
			String json_fundStrOneTwo="";
			if(null!=fundStrTwo&&(!"".equals(fundStrTwo))){
				json_fundStrOneTwo=variablesMap.get(fundStrTwo).toString();
			}
		    Map<String,Set>fundMap=(Map<String,Set>)copyObjectToHisTools.CopyOjbectFormStrAndRemove(tableService, fundPlans, ContractFundFundPlan.class, variablesMap.get(fundStrOne).toString(),json_fundStrOneTwo);
		    Set<ContractFundFundPlan>saveset=fundMap.get("save");
		    Set<ContractFundFundPlan>removeset=fundMap.get("remove");
		    for(ContractFundFundPlan cf :saveset){
		    	cf.setContractId(contractInfo);
		    	
		    	this.tableService.saveOrUpdateEntity(cf);
		    }
 	        this.tableService.removeAllEntites(removeset);
		    otherPropMap.put("modstatus", hisAfter);
		    List<ContractFinaCashDetailHis> newFundPlanshis=(List<ContractFinaCashDetailHis>)copyObjectToHisTools.CopySetObjectAndAddOtherProperty(tableService,saveset, ContractFundFundPlanHis.class, otherPropMap);
		    if(null!=newFundPlanshis&&newFundPlanshis.size()>0){
		         this.tableService.saveAllEntities(newFundPlanshis);
		    }
		}
	}
	
	/**
	 * <p>根据项目or合同信息在流程结束时在已知租金法情况下，将已知租金法对应的多行域值存入到特定的一张表中。</p>
	 * @author sea
	 * @param variablesMap 封装后‘已知租金法多行域值’及整个租金测算jsp页面中的所有值的集合
	 * @param contractInfo 合同对象，主要使用UUID
	 * @param projInfo 项目对象，主要使用UUID
	 * @throws Exception
	 */
	public void addKnowingRentData(Map<String,String> variablesMap,ContractInfo contractInfo,ProjInfo projInfo) throws Exception{
		// json_knowing_rent_plan_str=[{"id": "","startrentlist": "1","endrentlist": "36","rent": "83420.00","rowIndex": 0}]
		//2.7  需要将已知租金法情况下的输入的租金多行域信息归档入单独的一张表中
		String json_knowing_rent_plan_str = variablesMap.get("json_knowing_rent_plan_str"); // 已知租金法多行值
		if(null!=json_knowing_rent_plan_str&&(!"[]".equals(json_knowing_rent_plan_str))){
		//log.info("json_knowing_rent_plan_str1:"+json_knowing_rent_plan_str);
		if(json_knowing_rent_plan_str.length() > 0){
			String doc_id = variablesMap.get("framework_condition.docid");//文档号
			//获取固定格式的对象
			List<KnowingRentBean> knowingRentBeans = this.rentCalculateService.getList(json_knowing_rent_plan_str);
			List<KnowingRent> list =  new ArrayList<KnowingRent>();
			for(KnowingRentBean obj : knowingRentBeans){
				KnowingRent oneObj = new KnowingRent();
				oneObj.setDocId(doc_id);
				if(null == contractInfo){//合同对象为空就是项目层
					oneObj.setProjId(projInfo);
				}else{
					oneObj.setContractId(contractInfo);
				}
				oneObj.setStartlist(obj.getStartlist());
				oneObj.setEndlist(obj.getEndlist());
				oneObj.setRent(obj.getRent());
				oneObj.setKnowingRentJsons(json_knowing_rent_plan_str);
				list.add(oneObj);
			}
			
			//一个项目号对应的已知租金法在表中只存在一批数据，因此每次插入操作前，要根据项目号删除该项目号可能存在的多行域数据
			String knowingRent_key = ""; 
			String key = "";
			if(null == contractInfo){//合同对象为空就是项目层
				knowingRent_key = projInfo.getId();
				key = "proj_id";
			}else{
				knowingRent_key = contractInfo.getId();
				key = "contract_id";
			}
			String delSql = " delete fund_knowing_rent where "+key+" = ?";
			this.tableService.getBussinessDao().getJdbcTemplate().update(delSql,knowingRent_key);
			
			//持久化新的已知租金法多行域信息
			this.tableService.saveAllEntities(list);
		}}
	}
	
	/**
	 * <p>根据项目or合同信息在流程开始时在已知租金法情况下，将已知租金法对应的多行域值JSON字符串取出并返回至前台jsp使用。</p>
	 * @author sea
	 * @param variablesMap 封装后‘已知租金法多行域值’及整个租金测算jsp页面中的所有值的集合
	 * @param contractInfo 合同对象，主要使用UUID
	 * @param projInfo 项目对象，主要使用UUID
	 * @throws DataAccessException
	 * @throws Exception
	 */
	public void secrhKnowingRentJsons(Map<String, String> variablesMap,ContractInfo contractInfo,ProjInfo projInfo) {
		//已知租金法的 json字符串返回 json_knowing_rent_plan_str
		// json_knowing_rent_plan_str=[{"id": "","startrentlist": "1","endrentlist": "36","rent": "83420.00","rowIndex": 0}]
		String knowingRent_key = ""; 
		String key = "";
		if(null == contractInfo){//合同对象为空就是项目层
			knowingRent_key = projInfo.getId();
			key = "proj_id";
		}else{
			knowingRent_key = contractInfo.getId();
			key = "contract_id";
		}
		
		//
		try {
			//直取top 1数据
			String sql = " select nvl(knowing_rent_jsons,'') as knowing_rent_jsons from FUND_KNOWING_RENT  where ROWNUM <= 1 and  "+key+" = ? ";
			log.info("流程开始时加载已知租金法多行域查询sql:"+sql);
			Map<String, Object> map;
			map = this.tableService.getBussinessDao().getJdbcTemplate().queryForMap(sql,knowingRent_key);
			log.info("流程开始时加载已知租金法多行域："+String.valueOf( map.get("knowing_rent_jsons") ));
			variablesMap.put("json_knowing_rent_plan_str", String.valueOf( map.get("knowing_rent_jsons") == null ? "" : map.get("knowing_rent_jsons") ));
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 特殊规则测算配置信息入库
	 * 
	 */
	public void addSpecialRegularConfigToDB(Map<String,String> variablesMap,ContractInfo contractInfo,ProjInfo projInfo) throws Exception{
		String json_special_regular_str = variablesMap.get("json_special_regular_str"); // 特殊规则json
		if(null!=json_special_regular_str&&(!"[]".equals(json_special_regular_str))){
			if(json_special_regular_str.length() > 0 && "special_regular".equals(variablesMap.get("settlemethod"))){//租金计算方式为特殊规则的时候  才会入库
				String doc_id = variablesMap.get("framework_condition.docid");//文档号
				SpecialRegularCalConfig oneObj = new SpecialRegularCalConfig();
				oneObj.setDocId(doc_id);
				String delSql ="";
				if(null == contractInfo){//合同对象为空就是项目层
					oneObj.setProjId(projInfo);
					delSql = " delete SPECIAL_REGULAR_CALCONFIG where PROJ_ID='"+projInfo.getId()+"'";
				}else{
					oneObj.setContractId(contractInfo);
					delSql = " delete SPECIAL_REGULAR_CALCONFIG where CONTRACT_ID='"+contractInfo.getId()+"'";
				}
				oneObj.setSpecialRegularJsons(json_special_regular_str);;
				this.tableService.getBussinessDao().getJdbcTemplate().execute(delSql);
				this.tableService.saveEntity(oneObj);
			}}
	}
}
