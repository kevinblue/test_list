package com.tenwa.leasing.serviceImpl.contract.contractChange;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;









import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.reckon.service.RentConditionDataOperator;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.AppStaticUtil;
import com.tenwa.leasing.entity.contract.ContractEquip;
import com.tenwa.leasing.entity.contract.ContractEquipTmp;
import com.tenwa.leasing.entity.contract.ContractGuaranteeEquip;
import com.tenwa.leasing.entity.contract.ContractGuaranteeEquipTmp;
import com.tenwa.leasing.entity.contract.ContractGuaranteeMethod;
import com.tenwa.leasing.entity.contract.ContractGuaranteeMethodTmp;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.proj.ContractNumberSetting;
import com.tenwa.leasing.entity.proj.ContractNumberSettingTmp;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.contract.contractChange.ContractChangeService;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
@Service(value = "contractChangeService")
public class ContractChangeServiceImpl implements ContractChangeService {
	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "RentConditionDataService")
	private RentConditionDataOperator rentConditionData;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	@Override
	public void saveContractChangeInfo(Map<String, String> variablesMap) throws Exception {
		
		
		//保存合同信息
		ContractInfo contract=contractCommService.saveContractInfo(variablesMap, AppStaticUtil.CONTRACT_APPROVAL);
		
		//保存交易结构和租金计划，现金流，资金计划
		this.contractCommService.saveContractRentCalculationBefore(contract, variablesMap);
		if(null!=contract.getContractCondition()){
			contract.setStartDate(contract.getContractCondition().getStartDate());
			contract.setEndDate(contract.getContractCondition().getEndDate());
		}
		this.baseService.saveOrUpdateEntity(contract);
		//保存变列信息
		 this.contractCommService.saveContractChangeInfo(contract, variablesMap);
		if(null!=contract.getContractCondition()){
			contract.setStartDate(contract.getContractCondition().getStartDate());
			contract.setEndDate(contract.getContractCondition().getEndDate());
		}
		this.baseService.saveOrUpdateEntity(contract);		
		
		// 5.合同各方出租房和承租方信息
		this.contractCommService.saveContractSignatoryInfo(contract, variablesMap);
		
		// 合同贸易基础交易情况
		this.contractCommService.saveConTradeTransaction(contract, variablesMap);
		
		// 5供应商(合同各方)
		this.contractCommService.saveContractSupplierInfo(contract, variablesMap);
		
		//租金开票类型
		this.contractCommService.saveContractInvoiceType(contract, variablesMap);
		
		
		//特别付款前提 
		//this.contractCommService.saveContractPremiseInfo(contract, variablesMap);
		
		//保存租金计划中的付款前提 
		this.contractCommService.saveProjPaymentPremiseCondition(contract, variablesMap);
		
		//保存合同文本  
        this.contractCommService.updateWordKey("json_contract_word_str", contract.getId(), variablesMap);
		
		//保存合同文本到contractdoclist
		this.contractCommService.saveContractDocList("json_contract_word_str", contract, variablesMap);
		
		// 2.租赁物明细
		this.saveContractEquip(contract,variablesMap);
		// 4.抵押物信息
	 	this.saveContractGuaranteeEquip(contract,variablesMap);
		// 3.担保单位
		this.saveContractGuaranteeMethod(contract,variablesMap);
		// 4.合同编号设置
		this.saveContractNumberSettingInfo(contract,variablesMap);
	}
	
	
	public void saveFactoringContractChangeInfo(Map<String, String> variablesMap) throws Exception {
		
		
		//保存合同信息
		ContractInfo contract=contractCommService.saveFactoringContractInfo(variablesMap, AppStaticUtil.CONTRACT_APPROVAL);
		
		//保存交易结构和租金计划，现金流，资金计划
		this.contractCommService.saveContractRentCalculationBefore(contract, variablesMap);
		if(null!=contract.getContractCondition()){
			contract.setStartDate(contract.getContractCondition().getStartDate());
			contract.setEndDate(contract.getContractCondition().getEndDate());
		}
		this.baseService.saveOrUpdateEntity(contract);
		//保存变列信息
		 this.contractCommService.saveContractChangeInfo(contract, variablesMap);
		if(null!=contract.getContractCondition()){
			contract.setStartDate(contract.getContractCondition().getStartDate());
			contract.setEndDate(contract.getContractCondition().getEndDate());
		}
		this.baseService.saveOrUpdateEntity(contract);		
		
		// 5.合同各方出租房和承租方信息
		this.contractCommService.saveContractSignatoryInfo(contract, variablesMap);
		
		// 合同贸易基础交易情况
		this.contractCommService.saveConTradeTransaction(contract, variablesMap);
		
		// 5供应商(合同各方)
		this.contractCommService.saveContractSupplierInfo(contract, variablesMap);
		
		//租金开票类型
		this.contractCommService.saveContractInvoiceType(contract, variablesMap);
		
		//特别付款前提 
		this.contractCommService.saveContractPremiseInfo(contract, variablesMap);
		
		//保存租金计划中的付款前提 
		this.contractCommService.saveProjPaymentPremiseCondition(contract, variablesMap);
		
		//保存合同文本到contractdoclist
		this.contractCommService.saveContractDocList("json_contract_word_str", contract, variablesMap);
		
		// 2.租赁物明细
		this.saveContractEquip(contract,variablesMap);
		// 4.抵押物信息
		this.saveContractGuaranteeEquip(contract,variablesMap);
		// 3.担保单位
		this.saveContractGuaranteeMethod(contract,variablesMap);
		// 4.合同编号设置
		this.saveContractNumberSettingInfo(contract,variablesMap);
	}
	// 2.租赁物明细
	public void saveContractEquip(ContractInfo contract,Map<String, String> variablesMap) throws Exception {
		 Map<String,Object>propertiesMap=new HashMap<String,Object>();
		 String docid= variablesMap.get("docid");
		 propertiesMap.put("docid", docid);
		 propertiesMap.put("contractId", contract);
		List<ContractEquipTmp>  cetlist=this.tableService.findEntityByProperties(ContractEquipTmp.class, propertiesMap);
		String  json_contract=variablesMap.get("json_contract_equip_str");
		ContractEquip contractEquip = null;
		 JSONArray jsonarrayupdate=new JSONArray();
		for(ContractEquipTmp pet:cetlist){
			ContractEquip  oldid =this.tableService.findEntityByID(ContractEquip.class, pet.getOldId()==null?"0000000":pet.getOldId());
			String savestatus = pet.getSaveStatus();
			contractEquip = new ContractEquip();
			if((oldid!=null)&&(!"删除".equals(savestatus))){//更新
			 JSONArray jsonarray=new JSONArray(json_contract);
			 for(int i=0;jsonarray.length()>i;i++){
				JSONObject jsonobj=jsonarray.getJSONObject(i);
				String tmpid=jsonobj.getString("id");
				if(pet.getId().equals(tmpid)&&pet.getOldId().equals(oldid.getId())){
					jsonobj.put("id", oldid.getId());
					jsonarrayupdate.put(jsonobj);
				  }
			   }
			}else if((oldid!=null)&&("删除".equals(savestatus))){
				this.tableService.removeEntity(oldid);
			}else if((oldid==null || oldid.equals(""))&&(savestatus==null||savestatus.equals(""))){
				contractEquip.setContractId(contract);
				this.tableService.copyAndOverrideExistedValueFromObjectNoSet(pet, contractEquip);
				this.tableService.saveEntity(contractEquip);
			}else{}
		}
		if(jsonarrayupdate.length()>0){
			 this.tableService.updateOneToManyCollections(contract, "contractEquips", ContractEquip.class, "contractId", jsonarrayupdate.toString(),null);
		}
	}
	
	// 3.担保单位
	public void saveContractGuaranteeMethod(ContractInfo contract,Map<String, String> variablesMap) throws Exception {
		Map<String,Object>propertiesMap=new HashMap<String,Object>();
		String docid= variablesMap.get("docid");
		propertiesMap.put("contractId",contract);
		propertiesMap.put("docid", docid);
		JSONArray jsonarrayupdate=new JSONArray();
		String json_method_str=variablesMap.get("json_contract_guarantee_method_str");
		List<ContractGuaranteeMethodTmp>  cgmtlist=this.tableService.findEntityByProperties(ContractGuaranteeMethodTmp.class, propertiesMap);
		ContractGuaranteeMethod contractGuaranteeMethod = null;
		for(ContractGuaranteeMethodTmp cgmt:cgmtlist){
			String savestatus = cgmt.getSaveStatus();//是否删除
			ContractGuaranteeMethod oldid = this.tableService.findEntityByID(ContractGuaranteeMethod.class, cgmt.getOldId()==null?"000000":cgmt.getOldId());
			contractGuaranteeMethod = new ContractGuaranteeMethod();
			//1.oldid存在并且savestatus状态为空情况下更新
			if((oldid!=null)&&(!"删除".equals(savestatus))){
				 JSONArray jsonarray=new JSONArray(json_method_str);
				 for(int i=0;jsonarray.length()>i;i++){
						JSONObject jsonobj=jsonarray.getJSONObject(i);
						String tmpid=jsonobj.getString("id");
						if(cgmt.getId().equals(tmpid)&&cgmt.getOldId().equals(oldid.getId())){
							jsonobj.put("id", oldid.getId());
							jsonarrayupdate.put(jsonobj);
						}
					   }
			}else if((oldid!=null)&&("删除".equals(savestatus))){
				this.tableService.removeEntity(oldid);
			}else if((oldid==null || oldid.equals(""))&&(savestatus==null||savestatus.equals(""))){
				contractGuaranteeMethod.setContractId(contract);
				this.tableService.copyAndOverrideExistedValueFromObjectNoSet(cgmt, contractGuaranteeMethod);
				this.tableService.saveEntity(contractGuaranteeMethod);
			}else{}
		}
		if(jsonarrayupdate.length()>0){
			 this.tableService.updateOneToManyCollections(contract, "contractGuaranteeMethods", ContractGuaranteeMethod.class, "contractId",jsonarrayupdate.toString(),null);
		}
	}
	
	// 4.抵押物信息
	public void saveContractGuaranteeEquip(ContractInfo contract,Map<String, String> variablesMap) throws Exception {
		/*this.contractCommService.saveContractGuaranteeEquip(contract, variablesMap);*/
		 Map<String,Object>propertiesMap=new HashMap<String,Object>();
		  String docid= variablesMap.get("docid");
		  propertiesMap.put("contractId",contract);
		  propertiesMap.put("docid", docid);
		  JSONArray jsonarrayupdate=new JSONArray();
		  String json_contract_guarantee_equip_str=variablesMap.get("json_contract_guarantee_equip_str");
		 List<ContractGuaranteeEquipTmp>  cgetlist=this.tableService.findEntityByProperties(ContractGuaranteeEquipTmp.class, propertiesMap);
		ContractGuaranteeEquip contractGuaranteeEquip = null;
		for(ContractGuaranteeEquipTmp cget:cgetlist){
			String savestatus = cget.getSaveStatus();
			ContractGuaranteeEquip oldid =this.tableService.findEntityByID(ContractGuaranteeEquip.class,cget.getOldId()==null?"0000000":cget.getOldId());
			contractGuaranteeEquip = new ContractGuaranteeEquip();
			if((oldid!=null)&&(!"删除".equals(savestatus))){
				 JSONArray jsonarray=new JSONArray(json_contract_guarantee_equip_str);
				 for(int i=0;jsonarray.length()>i;i++){
						JSONObject jsonobj=jsonarray.getJSONObject(i);
						String tmpid=jsonobj.getString("id");
						if(cget.getId().equals(tmpid)&&cget.getOldId().equals(oldid.getId())){
							jsonobj.put("id", oldid.getId());
							jsonarrayupdate.put(jsonobj);
						}
					   }
			}else if((oldid!=null)&&("删除".equals(savestatus))){
				this.tableService.removeEntity(oldid);
			}else if((oldid==null || oldid.equals(""))&&(savestatus==null||savestatus.equals(""))){
				contractGuaranteeEquip.setContractId(contract);
				contractGuaranteeEquip.setTmpid(cget.getId());
				this.tableService.copyAndOverrideExistedValueFromObjectNoSet(cget, contractGuaranteeEquip);
				this.tableService.saveEntity(contractGuaranteeEquip);
			}else{}
		 }
		if(jsonarrayupdate.length()>0){
			 this.tableService.updateOneToManyCollections(contract, "contractGuaranteeEquips", ContractGuaranteeEquip.class, "contractId",jsonarrayupdate.toString(),null);
		}
	}
	//合同编号设置
		public void saveContractNumberSettingInfo(ContractInfo contract,Map<String, String> variablesMap) throws Exception {
			 Map<String,Object>propertiesMap=new HashMap<String,Object>();
			 String docid= variablesMap.get("docid");
			 propertiesMap.put("contractId",contract);
			 propertiesMap.put("docid", docid);
			 JSONArray jsonarrayupdate=new JSONArray();
			 String json_contract_change_numset_str=variablesMap.get("json_contract_change_numset_str");
			List<ContractNumberSettingTmp>  cnstlist=this.tableService.findEntityByProperties(ContractNumberSettingTmp.class, propertiesMap);
			ContractNumberSetting contractNumberSetting = null;
			for(ContractNumberSettingTmp cnst:cnstlist){
				String savestatus = cnst.getSaveStatus();
				ContractNumberSetting oldid =this.tableService.findEntityByID(ContractNumberSetting.class,cnst.getOldId()==null?"000000":cnst.getOldId());
				contractNumberSetting = new ContractNumberSetting();
				if((oldid!=null)&&(!"删除".equals(savestatus))){
					 JSONArray jsonarray=new JSONArray(json_contract_change_numset_str);
					 for(int i=0;jsonarray.length()>i;i++){
							JSONObject jsonobj=jsonarray.getJSONObject(i);
							String tmpid=jsonobj.getString("id");
							if(cnst.getId().equals(tmpid)&&cnst.getOldId().equals(oldid.getId())){
								jsonobj.put("id", oldid.getId());
								jsonarrayupdate.put(jsonobj);
							}
						   }
				}else if((oldid!=null)&&("删除".equals(savestatus))){
					this.tableService.removeEntity(oldid);
				}else if((oldid==null || oldid.equals(""))&&(savestatus==null||savestatus.equals(""))){
					contractNumberSetting.setContractId(contract);
					this.tableService.copyAndOverrideExistedValueFromObjectNoSet(cnst, contractNumberSetting);
					this.tableService.saveEntity(contractNumberSetting);
				}else{}
			}
			if(jsonarrayupdate.length()>0){
				 this.tableService.updateOneToManyCollections(contract, "contractNumberSettings", ContractNumberSetting.class, "contractId",jsonarrayupdate.toString(),null);
			}
	     }
}
