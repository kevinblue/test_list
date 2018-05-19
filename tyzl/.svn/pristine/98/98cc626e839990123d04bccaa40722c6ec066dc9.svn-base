package com.reckon.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.reckon.entity.contract.reckon.cash.ContractCashDetail;
import com.reckon.entity.contract.reckon.cash.ContractCashDetailHis;
import com.reckon.entity.contract.reckon.cash.ContractFinaCashDetail;
import com.reckon.entity.contract.reckon.cash.ContractFinaCashDetailHis;
import com.reckon.entity.contract.reckon.condition.ContractCondition;
import com.reckon.entity.contract.reckon.condition.ContractConditionHis;
import com.reckon.entity.contract.reckon.fina.ContractFinaRentPlan;
import com.reckon.entity.contract.reckon.fina.ContractFinaRentPlanHis;
import com.reckon.entity.proj.ProjCashDetail;
import com.reckon.entity.proj.ProjCashDetailHis;
import com.reckon.entity.proj.ProjCondition;
import com.reckon.entity.proj.ProjConditionHis;
import com.reckon.entity.proj.ProjFinaCashDetail;
import com.reckon.entity.proj.ProjFinaCashDetailHis;
import com.reckon.entity.proj.ProjFinaRentPlan;
import com.reckon.entity.proj.ProjFinaRentPlanHis;
import com.reckon.entity.proj.ProjFundRentPlan;
import com.reckon.entity.proj.ProjFundRentPlanHis;
import com.reckon.service.ConditionDataToHisService;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.BaseService;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.fund.ContractFundRentInCome;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;
import com.tenwa.leasing.entity.fund.ContractFundRentPlanHis;
import com.tenwa.leasing.entity.proj.ProjInfo;

@Service(value = "conditionDataToHisService")
public class ConditionDataToHisServiceImpl implements ConditionDataToHisService {
	@Resource(name = "baseService")
	private BaseService baseService;
	@Override
	public void saveProjConditionDataToHis(String projId, String docId,
			String hisType, Map<String, String> datas, String conditioinKey,
			String rentPlanKey, String finaPlanKey, String rentCashKey,
			String finaCashKey) throws Exception {
		ProjInfo projInfo = (ProjInfo)this.baseService.findEntityByID(ProjInfo.class, projId);
		//先设定入历史表的类型
		Map<String, Object> whereMap=new HashMap<String, Object>();
		DictionaryData hisBefore=new DictionaryData();
		DictionaryData hisAfter=new DictionaryData();
		DictionaryData hisTypeDict=new DictionaryData();
		whereMap.put("code", "his_status_before");
		hisBefore=(DictionaryData)this.baseService.findEntityByProperties(DictionaryData.class, whereMap).get(0);
		whereMap.put("code", "his_status_after");
		hisAfter=(DictionaryData)this.baseService.findEntityByProperties(DictionaryData.class, whereMap).get(0);
		whereMap.put("code", hisType);
		hisTypeDict=(DictionaryData)this.baseService.findEntityByProperties(DictionaryData.class, whereMap).get(0);
		//商务条件
		ProjCondition pCondition=projInfo.getProjCondition();
	    ProjConditionHis pConditionHis=new ProjConditionHis();
		BeanUtils.copyProperties(pCondition, pConditionHis);
		//pConditionHis=(ProjConditionHis)this.baseService.copyAndOverrideExistedValueFromObject(pCondition, pConditionHis);
		pConditionHis.setModStatus(hisBefore);
		pConditionHis.setModReason(hisTypeDict);
		pConditionHis.setId(null);
		pConditionHis.setDocId(docId);
		this.baseService.saveEntity(pConditionHis);
		//合同租金计划
		Set<ProjFundRentPlan> fundRentPlans=projInfo.getProjFundRentPlans();
		List<ProjFundRentPlanHis> fundRentPlanHiss=new ArrayList<ProjFundRentPlanHis>();
		for (ProjFundRentPlan projFundRentPlan : fundRentPlans) {
			ProjFundRentPlanHis fundRentPlanHis=new ProjFundRentPlanHis();
			BeanUtils.copyProperties(projFundRentPlan, fundRentPlanHis);
			fundRentPlanHis.setModStatus(hisBefore);
			fundRentPlanHis.setModReason(hisTypeDict);
			fundRentPlanHis.setId(null);
			fundRentPlanHis.setDocId(docId);
			fundRentPlanHiss.add(fundRentPlanHis);
		}
		this.baseService.saveAllEntities(fundRentPlanHiss);
		//会计租金计划
		Set<ProjFinaRentPlan> finaRentPlans=projInfo.getProjFinaRentPlans();
		List<ProjFinaRentPlanHis> finaRentPlanHiss=new ArrayList<ProjFinaRentPlanHis>();
		for (ProjFinaRentPlan projFinaRentPlan : finaRentPlans) {
			ProjFinaRentPlanHis finaRentPlanHis=new ProjFinaRentPlanHis();
			BeanUtils.copyProperties(projFinaRentPlan, finaRentPlanHis);
			finaRentPlanHis.setModStatus(hisBefore);
			finaRentPlanHis.setModReason(hisTypeDict);
			finaRentPlanHis.setId(null);
			finaRentPlanHis.setDocId(docId);
			finaRentPlanHiss.add(finaRentPlanHis);
		}
		this.baseService.saveAllEntities(finaRentPlanHiss);
		//合同现金流
		Set<ProjCashDetail> fundCashDetails=projInfo.getProjCashDetails();
		List<ProjCashDetailHis> fundCashDetailHiss=new ArrayList<ProjCashDetailHis>();
		for (ProjCashDetail projCashDetail : fundCashDetails) {
			ProjCashDetailHis fundCashDetailHis=new ProjCashDetailHis();
			BeanUtils.copyProperties(projCashDetail, fundCashDetailHis);
			fundCashDetailHis.setModStatus(hisBefore);
			fundCashDetailHis.setModReason(hisTypeDict);
			fundCashDetailHis.setId(null);
			fundCashDetailHis.setDocId(docId);
			fundCashDetailHiss.add(fundCashDetailHis);
		}
		this.baseService.saveAllEntities(fundCashDetailHiss);
		//会计现金流
		Set<ProjFinaCashDetail> finaCashDetails=projInfo.getProjFinaCashDetails();
		List<ProjFinaCashDetailHis> finaCashDetailHiss=new ArrayList<ProjFinaCashDetailHis>();
		for (ProjFinaCashDetail projFundCashDetail : finaCashDetails) {
			ProjFinaCashDetailHis finaCashDetailHis=new ProjFinaCashDetailHis();
			BeanUtils.copyProperties(projFundCashDetail, finaCashDetailHis);
			finaCashDetailHis.setModStatus(hisBefore);
			finaCashDetailHis.setModReason(hisTypeDict);
			finaCashDetailHis.setId(null);
			finaCashDetailHis.setDocId(docId);
			finaCashDetailHiss.add(finaCashDetailHis);
		}
		this.baseService.saveAllEntities(finaCashDetailHiss);
		//删除正式表
		this.baseService.removeAllEntites(projInfo.getProjFundRentPlans());
		this.baseService.removeAllEntites(projInfo.getProjFinaRentPlans());
		this.baseService.removeAllEntites(projInfo.getProjCashDetails());
		this.baseService.removeAllEntites(projInfo.getProjFinaCashDetails());
		projInfo=(ProjInfo)this.baseService.findEntityByID(ProjInfo.class, projId);
		//再入正式表
		// 2.1商务条件  此处的商务条件统一用framework_condition 为了通用性 租金测算页面的域名都用framework_condition开头而不是表名
		Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		dictDataClassMapping.put("DictionaryData", "code");
		ProjCondition projCondition=(ProjCondition)this.baseService.findEntityByID(ProjCondition.class, projInfo.getProjCondition().getId());
		this.baseService.copyAndOverrideExistedValueFromStringMap(datas, projCondition, dictDataClassMapping);
		this.baseService.removeEntity(projInfo.getProjCondition());
		this.baseService.saveOrUpdateEntity(projCondition);
		
		// 2.2合同租金计划
		String json_fund_rent_plan_str = datas.get(rentPlanKey);
		JSONArray jsonArray = new JSONArray(json_fund_rent_plan_str);
		JSONArray newjsonArray = new JSONArray();
		List<String> jsonFundList=new ArrayList<String>();
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			jsonObj.remove("id");
			jsonFundList.add(jsonObj.toString());
		}
		this.baseService.updateOneToManyCollections(projInfo, "projFundRentPlans", ProjFundRentPlan.class, "projId", jsonFundList.toString(),null);
		// 2.3合同现金流
		String json_fund_cash_flow_str = datas.get(rentCashKey);
		jsonArray = new JSONArray(json_fund_cash_flow_str);
	    newjsonArray = new JSONArray();
	    jsonFundList=new ArrayList<String>();
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			jsonObj.remove("id");
			jsonFundList.add(jsonObj.toString());
		}
		
		this.baseService.updateOneToManyCollections(projInfo, "projCashDetails", ProjCashDetail.class, "projId", jsonFundList.toString(),null);
		// 2.4会计租金计划
		String json_finance_rent_plan_str = datas.get(finaPlanKey);
		jsonArray = new JSONArray(json_finance_rent_plan_str);
	    newjsonArray = new JSONArray();
	    jsonFundList=new ArrayList<String>();
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			jsonObj.remove("id");
			jsonFundList.add(jsonObj.toString());
		}
		this.baseService.updateOneToManyCollections(projInfo, "projFinaRentPlans", ProjFinaRentPlan.class, "projId", jsonFundList.toString(),null);
		// 2.5会计现金流
		String json_finance_cash_flow_str = datas.get(finaCashKey);
	 
		jsonArray = new JSONArray(json_finance_cash_flow_str);
	    newjsonArray = new JSONArray();
	    jsonFundList=new ArrayList<String>();
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			jsonObj.remove("id");
			jsonFundList.add(jsonObj.toString());
		}
		this.baseService.updateOneToManyCollections(projInfo, "projFinaCashDetails", ProjFinaCashDetail.class, "projId", jsonFundList.toString(),null);
		//再入历史表
		projInfo = (ProjInfo)this.baseService.findEntityByID(ProjInfo.class, projId);
		//商务条件
		pCondition=projInfo.getProjCondition();
		pConditionHis=new ProjConditionHis();
		BeanUtils.copyProperties(pCondition, pConditionHis);
		pConditionHis.setModStatus(hisAfter);
		pConditionHis.setModReason(hisTypeDict);
		pConditionHis.setId(null);
		pConditionHis.setDocId(docId);
		this.baseService.saveEntity(pConditionHis);
		//合同租金计划
		fundRentPlans=projInfo.getProjFundRentPlans();
		fundRentPlanHiss=new ArrayList<ProjFundRentPlanHis>();
		for (ProjFundRentPlan projFundRentPlan : fundRentPlans) {
			ProjFundRentPlanHis fundRentPlanHis=new ProjFundRentPlanHis();
			BeanUtils.copyProperties(projFundRentPlan, fundRentPlanHis);
			fundRentPlanHis.setModStatus(hisAfter);
			fundRentPlanHis.setModReason(hisTypeDict);
			fundRentPlanHis.setId(null);
			fundRentPlanHis.setDocId(docId);
			fundRentPlanHiss.add(fundRentPlanHis);
		}
		this.baseService.saveAllEntities(fundRentPlanHiss);
		//会计租金计划
		finaRentPlans=projInfo.getProjFinaRentPlans();
		finaRentPlanHiss=new ArrayList<ProjFinaRentPlanHis>();
		for (ProjFinaRentPlan projFinaRentPlan : finaRentPlans) {
			ProjFinaRentPlanHis finaRentPlanHis=new ProjFinaRentPlanHis();
			BeanUtils.copyProperties(projFinaRentPlan, finaRentPlanHis);
			finaRentPlanHis.setModStatus(hisAfter);
			finaRentPlanHis.setModReason(hisTypeDict);
			finaRentPlanHis.setId(null);
			finaRentPlanHis.setDocId(docId);
			finaRentPlanHiss.add(finaRentPlanHis);
		}
		this.baseService.saveAllEntities(finaRentPlanHiss);
		//合同现金流
		fundCashDetails=projInfo.getProjCashDetails();
		fundCashDetailHiss=new ArrayList<ProjCashDetailHis>();
		for (ProjCashDetail projCashDetail : fundCashDetails) {
			ProjCashDetailHis fundCashDetailHis=new ProjCashDetailHis();
			BeanUtils.copyProperties(projCashDetail, fundCashDetailHis);
			fundCashDetailHis.setModStatus(hisAfter);
			fundCashDetailHis.setModReason(hisTypeDict);
			fundCashDetailHis.setId(null);
			fundCashDetailHis.setDocId(docId);
			fundCashDetailHiss.add(fundCashDetailHis);
		}
		this.baseService.saveAllEntities(fundCashDetailHiss);
		//会计现金流
		finaCashDetails=projInfo.getProjFinaCashDetails();
		finaCashDetailHiss=new ArrayList<ProjFinaCashDetailHis>();
		for (ProjFinaCashDetail projFundCashDetail : finaCashDetails) {
			ProjFinaCashDetailHis finaCashDetailHis=new ProjFinaCashDetailHis();
			BeanUtils.copyProperties(projFundCashDetail, finaCashDetailHis);
			finaCashDetailHis.setModStatus(hisAfter);
			finaCashDetailHis.setModReason(hisTypeDict);
			finaCashDetailHis.setId(null);
			finaCashDetailHis.setDocId(docId);
			finaCashDetailHiss.add(finaCashDetailHis);
		}
		   
		this.baseService.saveAllEntities(finaCashDetailHiss);
		//this.baseService.saveEntity(projInfo);
	}
        
	@Override
	public void saveContractConditionDataToHis(String contractId, String docId,
			String hisType, Map<String, String> datas, String conditioinKey,
			String rentPlanKey, String finaPlanKey, String rentCashKey,
			String finaCashKey) throws Exception {
		System.out.println("contractId="+contractId);
		ContractInfo contractInfo = (ContractInfo)this.baseService.findEntityByID(ContractInfo.class, contractId);
		//------------start[增加判断参数  contract_info  bussiness_type_equip  设备的 需要ContractFinaCashDetail 和ContractFinaRentPlan 表的his 表操作]
		//String bussinessTypeEquip = "";
		//if(contractInfo!=null){
			//bussinessTypeEquip = contractInfo.getBusinessType().getCode();
		//}
		//------------end[增加判断参数  contract_info  bussiness_type_equip  设备的 需要ContractFinaCashDetail 和ContractFinaRentPlan 表的his 表操作]
		//先设定入历史表的类型
		Map<String, Object> whereMap=new HashMap<String, Object>();
		DictionaryData hisBefore=new DictionaryData();
		DictionaryData hisAfter=new DictionaryData();
		DictionaryData hisTypeDict=new DictionaryData();
		whereMap.put("code", "his_status_before");
		hisBefore=(DictionaryData)this.baseService.findEntityByProperties(DictionaryData.class, whereMap).get(0);
		whereMap.put("code", "his_status_after");
		hisAfter=(DictionaryData)this.baseService.findEntityByProperties(DictionaryData.class, whereMap).get(0);
		whereMap.put("code", hisType);
		hisTypeDict=(DictionaryData)this.baseService.findEntityByProperties(DictionaryData.class, whereMap).get(0);
		//商务条件
		ContractCondition cCondition=contractInfo.getContractCondition();
		ContractConditionHis cConditionHis=new ContractConditionHis();
		BeanUtils.copyProperties(cCondition, cConditionHis);
		cConditionHis.setModStatus(hisBefore);
		cConditionHis.setModReason(hisTypeDict);
		cConditionHis.setId(null);
		cConditionHis.setDocId(docId);
		this.baseService.saveEntity(cConditionHis);
		//合同租金计划
		Set<ContractFundRentPlan> fundRentPlans=  contractInfo.getContractFundRentPlans();
		List<ContractFundRentPlanHis> fundRentPlanHiss=new ArrayList<ContractFundRentPlanHis>();
		for (ContractFundRentPlan contractFundRentPlan : fundRentPlans) {
			ContractFundRentPlanHis fundRentPlanHis=new ContractFundRentPlanHis();
			BeanUtils.copyProperties(contractFundRentPlan, fundRentPlanHis);
			fundRentPlanHis.setModStatus(hisBefore);
			fundRentPlanHis.setModReason(hisTypeDict);
			fundRentPlanHis.setId(null);
			fundRentPlanHis.setDocId(docId);
			fundRentPlanHiss.add(fundRentPlanHis);
		}
		this.baseService.saveAllEntities(fundRentPlanHiss);
		System.out.println("合同租金计划；；");
		//会计租金计划
		Set<ContractFinaRentPlan> finaRentPlans=contractInfo.getContractFinaRentPlans();
		List<ContractFinaRentPlanHis> finaRentPlanHiss=new ArrayList<ContractFinaRentPlanHis>();
		for (ContractFinaRentPlan contractFinaRentPlan : finaRentPlans) {
			ContractFinaRentPlanHis finaRentPlanHis=new ContractFinaRentPlanHis();
			BeanUtils.copyProperties(contractFinaRentPlan, finaRentPlanHis);
			finaRentPlanHis.setModStatus(hisBefore);
			finaRentPlanHis.setModReason(hisTypeDict);
			finaRentPlanHis.setId(null);
			finaRentPlanHis.setDocId(docId);
			finaRentPlanHiss.add(finaRentPlanHis);
		}
		this.baseService.saveAllEntities(finaRentPlanHiss);
		//合同现金流
		Set<ContractCashDetail> fundCashDetails=contractInfo.getContractCashDetails();
		List<ContractCashDetailHis> fundCashDetailHiss=new ArrayList<ContractCashDetailHis>();
		for (ContractCashDetail contractCashDetail : fundCashDetails) {
			ContractCashDetailHis fundCashDetailHis=new ContractCashDetailHis();
			BeanUtils.copyProperties(contractCashDetail, fundCashDetailHis);
			fundCashDetailHis.setModStatus(hisBefore);
			fundCashDetailHis.setModReason(hisTypeDict);
			fundCashDetailHis.setId(null);
			fundCashDetailHis.setDocId(docId);
			fundCashDetailHiss.add(fundCashDetailHis);
		}
		this.baseService.saveAllEntities(fundCashDetailHiss);
		//会计现金流
		Set<ContractFinaCashDetail> finaCashDetails=contractInfo.getContractFinaCashDetails();
		List<ContractFinaCashDetailHis> finaCashDetailHiss=new ArrayList<ContractFinaCashDetailHis>();
		for (ContractFinaCashDetail contractFundCashDetail : finaCashDetails) {
			ContractFinaCashDetailHis finaCashDetailHis=new ContractFinaCashDetailHis();
			BeanUtils.copyProperties(contractFundCashDetail, finaCashDetailHis);
			finaCashDetailHis.setModStatus(hisBefore);
			finaCashDetailHis.setModReason(hisTypeDict);
			finaCashDetailHis.setId(null);
			finaCashDetailHis.setDocId(docId);
			finaCashDetailHiss.add(finaCashDetailHis);
		}
		this.baseService.saveAllEntities(finaCashDetailHiss);
		/*删除正式表 有主外键不能移除
		this.baseService.removeAllEntites(contractInfo.getContractFundRentPlans());
		this.baseService.removeAllEntites(contractInfo.getContractFinaRentPlans());
		this.baseService.removeAllEntites(contractInfo.getContractFinaCashDetails());*/
		this.baseService.removeAllEntites(contractInfo.getContractCashDetails());
		//再入正式表
		// 2.1商务条件  此处的商务条件统一用framework_condition 为了通用性 租金测算页面的域名都用framework_condition开头而不是表名
		Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		dictDataClassMapping.put("DictionaryData", "code");
		ContractCondition contractCondition=(ContractCondition)this.baseService.findEntityByID(ContractCondition.class, contractInfo.getContractCondition().getId());
		this.baseService.copyAndOverrideExistedValueFromStringMap(datas, contractCondition, dictDataClassMapping,conditioinKey);
		contractCondition.setContractId(contractInfo);
		this.baseService.saveOrUpdateEntity(contractCondition);
		// 2.2合同租金计划
		String json_fund_rent_plan_str = datas.get(rentPlanKey);
		//load 原租金计划替换现在的ID
		/**
		 * 1.把json中的数据转成 jsonmap1  key 是 id  jsonmap2 key 是 rentlist
		 * 2.把数据库中的数据转成 dbmap1 key 是 id dbmap2 key是 rentlist
		 * 按照 jsonmap2和 dbmap2的关系构建新的newmap 用dbmap中key存jsonmap1中的value[原id和新数据的map]
		 * 3.更新income中的引用按照rentlist
		 */
		Map<String, ContractFundRentPlan> jsonmap1=new LinkedHashMap<String, ContractFundRentPlan>();
		Map<String, ContractFundRentPlan> jsonmap2=new LinkedHashMap<String, ContractFundRentPlan>();
		Map<String, ContractFundRentPlan> dbmap1=new LinkedHashMap<String, ContractFundRentPlan>();
		Map<String, ContractFundRentPlan> dbmap2=new LinkedHashMap<String, ContractFundRentPlan>();
		Map<String, ContractFundRentPlan> newmap=new LinkedHashMap<String, ContractFundRentPlan>();
		List<ContractFundRentPlan> newList=new ArrayList<ContractFundRentPlan>();
		List<ContractFundRentPlan> removeList=new ArrayList<ContractFundRentPlan>();
		List<ContractFundRentPlan> listRentPlanData=new ArrayList<ContractFundRentPlan>(contractInfo.getContractFundRentPlans());
		for (ContractFundRentPlan contractFundRentPlan : listRentPlanData) {
			dbmap1.put(contractFundRentPlan.getId(), contractFundRentPlan);
			dbmap2.put(contractFundRentPlan.getRentList().toString(), contractFundRentPlan);
		}
		ObjectMapper jsonMapper = new ObjectMapper();
		Map[] newSetMaps = jsonMapper.readValue(json_fund_rent_plan_str, Map[].class);
		for (int j = 0; j < newSetMaps.length; j++) {//构建出json中的map对象
			String key=newSetMaps[j].get("id").toString();
			String rentlist=newSetMaps[j].get("rentlist").toString();
			ContractFundRentPlan contractFundRentPlan=new ContractFundRentPlan();
			if(dbmap2.containsKey(rentlist)){
				contractFundRentPlan=dbmap2.get(rentlist);
				newSetMaps[j].remove("id");
				newSetMaps[j].remove("contractid");
				newSetMaps[j].remove("quotid");
				newSetMaps[j].remove("quotid1");
			}
			this.baseService.copyAndOverrideExistedValueFromStringMap(newSetMaps[j], contractFundRentPlan, null);
			jsonmap1.put(key, contractFundRentPlan);
			jsonmap2.put(newSetMaps[j].get("rentlist").toString(), contractFundRentPlan);
			if(dbmap2.containsKey(rentlist)){
				newmap.put(contractFundRentPlan.getId(), contractFundRentPlan);
			}else{
				newmap.put(key, contractFundRentPlan);
			}
			newList.add(contractFundRentPlan);
		}
		//保存 newmap
		this.baseService.saveOrUpdateAllEntities(newList);
		//更新income
		Set<String> dbmap1set=dbmap1.keySet();
		for (String id : dbmap1set) {
			if(!newmap.containsKey(id)){
				Set<ContractFundRentInCome> incomSet=dbmap1.get(id).getContractFundRentInComes();
				List<ContractFundRentInCome> incomeList=new ArrayList<ContractFundRentInCome>();
				for (ContractFundRentInCome income : incomSet) {
					Integer rentList=income.getPlanList();
					while(!jsonmap2.containsKey(rentList.toString())){
						rentList=rentList-1;
					}
					income.setPlanId(jsonmap2.get(rentList.toString()));
					income.setPlanList(rentList);
					incomeList.add(income);
				}
				if(incomeList.size()>0){
					this.baseService.updateAllEntities(incomeList);
				}
				removeList.add(dbmap1.get(id));
			}
		}
		this.baseService.removeAllEntites(removeList);
		/*Map<String, ContractFundRentPlan> mapRentPlanJsonData=new LinkedHashMap<String, ContractFundRentPlan>();
		List<ContractFundRentPlan> listRentPlanData=new ArrayList<ContractFundRentPlan>(contractInfo.getContractFundRentPlans());
		ObjectMapper jsonMapper = new ObjectMapper();
		Map[] newSetMaps = jsonMapper.readValue(json_fund_rent_plan_str, Map[].class);
		for (int j = 0; j < newSetMaps.length; j++) {//构建出json中的map对象
			String key=newSetMaps[j].get("id").toString();
			ContractFundRentPlan contractFundRentPlan=new ContractFundRentPlan();
			if(StringUtil.nullToString(key).isEmpty()){
				key=UUIDUtil.getUUID();
				contractFundRentPlan.setId(key);
			}
			this.baseService.copyAndOverrideExistedValueFromStringMap(newSetMaps[j], contractFundRentPlan, null);
			mapRentPlanJsonData.put(key, contractFundRentPlan);
		}
		this.baseService.updateOneToManyCollections(contractInfo, "contractFundRentPlans", ContractFundRentPlan.class, "contractId", json_fund_rent_plan_str,null);*/
		// 2.3合同现金流
		String json_fund_cash_flow_str = datas.get(rentCashKey);
		JSONArray jsonArray = new JSONArray(json_fund_cash_flow_str);
		List<String> jsonFundList=new ArrayList<String>();
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			jsonObj.remove("id");
			jsonFundList.add(jsonObj.toString());
		}
		this.baseService.updateOneToManyCollections(contractInfo, "contractCashDetails", ContractCashDetail.class, "contractId", jsonFundList.toString(),null);
		// 2.4会计租金计划
		if(finaPlanKey != null && !"".equals(finaPlanKey)){
			String json_finance_rent_plan_str = datas.get(finaPlanKey);
			jsonArray = new JSONArray(json_finance_rent_plan_str);
			jsonFundList=new ArrayList<String>();
			for(int i=0;i<jsonArray.length();i++){
				JSONObject jsonObj = jsonArray.getJSONObject(i);
				jsonObj.remove("id");
				jsonFundList.add(jsonObj.toString());
			}
			this.baseService.updateOneToManyCollections(contractInfo, "contractFinaRentPlans", ContractFinaRentPlan.class, "contractId", jsonFundList.toString(),null);
		}
		// 2.5会计现金流
		if(finaCashKey != null && !"".equals(finaCashKey)){
			String json_finance_cash_flow_str = datas.get(finaCashKey);
			jsonArray = new JSONArray(json_finance_cash_flow_str);
			jsonFundList=new ArrayList<String>();
			for(int i=0;i<jsonArray.length();i++){
				JSONObject jsonObj = jsonArray.getJSONObject(i);
				jsonObj.remove("id");
				jsonFundList.add(jsonObj.toString());
			}
			this.baseService.updateOneToManyCollections(contractInfo, "contractFinaCashDetails", ContractFinaCashDetail.class, "contractId", jsonFundList.toString(),null);
		}
		//再入历史表
		contractInfo = (ContractInfo)this.baseService.findEntityByID(ContractInfo.class, contractId);//再查一次更新数据
		//商务条件
		cCondition=contractInfo.getContractCondition();
		cConditionHis=new ContractConditionHis();
		BeanUtils.copyProperties(cCondition, cConditionHis);
		cConditionHis.setModStatus(hisAfter);
		cConditionHis.setModReason(hisTypeDict);
		cConditionHis.setId(null);
		cConditionHis.setDocId(docId);
		this.baseService.saveEntity(cConditionHis);
		//合同租金计划
		fundRentPlans=contractInfo.getContractFundRentPlans();
		fundRentPlanHiss=new ArrayList<ContractFundRentPlanHis>();
		for (ContractFundRentPlan contractFundRentPlan : fundRentPlans) {
			ContractFundRentPlanHis fundRentPlanHis=new ContractFundRentPlanHis();
			BeanUtils.copyProperties(contractFundRentPlan, fundRentPlanHis);
			fundRentPlanHis.setModStatus(hisAfter);
			fundRentPlanHis.setModReason(hisTypeDict);
			fundRentPlanHis.setId(null);
			fundRentPlanHis.setDocId(docId);
			fundRentPlanHiss.add(fundRentPlanHis);
		}
		this.baseService.saveAllEntities(fundRentPlanHiss);
		//会计租金计划
		finaRentPlans=contractInfo.getContractFinaRentPlans();
		finaRentPlanHiss=new ArrayList<ContractFinaRentPlanHis>();
		for (ContractFinaRentPlan contractFinaRentPlan : finaRentPlans) {
			ContractFinaRentPlanHis finaRentPlanHis=new ContractFinaRentPlanHis();
			BeanUtils.copyProperties(contractFinaRentPlan, finaRentPlanHis);
			finaRentPlanHis.setModStatus(hisAfter);
			finaRentPlanHis.setModReason(hisTypeDict);
			finaRentPlanHis.setId(null);
			finaRentPlanHis.setDocId(docId);
			finaRentPlanHiss.add(finaRentPlanHis);
		}
		this.baseService.saveAllEntities(finaRentPlanHiss);
		//合同现金流
		fundCashDetails=contractInfo.getContractCashDetails();
		fundCashDetailHiss=new ArrayList<ContractCashDetailHis>();
		for (ContractCashDetail contractCashDetail : fundCashDetails) {
			ContractCashDetailHis fundCashDetailHis=new ContractCashDetailHis();
			BeanUtils.copyProperties(contractCashDetail, fundCashDetailHis);
			fundCashDetailHis.setModStatus(hisAfter);
			fundCashDetailHis.setModReason(hisTypeDict);
			fundCashDetailHis.setId(null);
			fundCashDetailHis.setDocId(docId);
			fundCashDetailHiss.add(fundCashDetailHis);
		}
		this.baseService.saveAllEntities(fundCashDetailHiss);
		//会计现金流
		finaCashDetails=contractInfo.getContractFinaCashDetails();
		finaCashDetailHiss=new ArrayList<ContractFinaCashDetailHis>();
		for (ContractFinaCashDetail contractFundCashDetail : finaCashDetails) {
			ContractFinaCashDetailHis finaCashDetailHis=new ContractFinaCashDetailHis();
			BeanUtils.copyProperties(contractFundCashDetail, finaCashDetailHis);
			finaCashDetailHis.setModStatus(hisAfter);
			finaCashDetailHis.setModReason(hisTypeDict);
			finaCashDetailHis.setId(null);
			finaCashDetailHis.setDocId(docId);
			finaCashDetailHiss.add(finaCashDetailHis);
		}
		this.baseService.saveEntity(contractInfo);
	}

}
