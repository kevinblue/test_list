package com.tenwa.leasing.serviceImpl.contract.contractApproval;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
  

















import com.reckon.service.RentConditionDataOperator;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.utils.AppStaticUtil;
import com.tenwa.leasing.entity.contract.ContractEquip;
import com.tenwa.leasing.entity.contract.ContractEquipTmp;
import com.tenwa.leasing.entity.contract.ContractGuaranteeEquip;
import com.tenwa.leasing.entity.contract.ContractGuaranteeMethod;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.proj.ContractNumberSetting;
import com.tenwa.leasing.entity.proj.ContractNumberSettingTmp;
import com.tenwa.leasing.entity.proj.ProjEquipTmp;
import com.tenwa.leasing.entity.proj.ProjGuaranteeEquipTmp;
import com.tenwa.leasing.entity.proj.ProjGuaranteeMethod;
import com.tenwa.leasing.entity.proj.ProjGuaranteeMethodTmp;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.service.contract.contractApproval.ContractApprovalService;
import com.tenwa.leasing.service.contractcomm.ContractCommService;


@Service(value = "contractApprovalService")
public class ContractApprovalServiceImpl implements ContractApprovalService {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "RentConditionDataService")
	private RentConditionDataOperator rentConditionData;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	
	@Override
	public void saveContractApprovalInfo(Map<String, String> variablesMap) throws Exception {
		
		
		//保存合同信息
		ContractInfo contract=contractCommService.saveContractInfo(variablesMap, AppStaticUtil.CONTRACT_APPROVAL);
		//保存交易结构和租金计划，现金流，资金计划
		this.contractCommService.saveContractRentCalculationBefore(contract, variablesMap);
		if(null!=contract.getContractCondition()){
			contract.setStartDate(contract.getContractCondition().getStartDate());
			contract.setEndDate(contract.getContractCondition().getEndDate());
		}
		this.baseService.saveOrUpdateEntity(contract);		
		
/*	    this.contractCommService.saveContractGuaranteeMethod(contract, variablesMap);
		
		this.contractCommService.saveContractGuaranteeEquip(contract, variablesMap);
		*/

		// 5.合同各方出租房和承租方信息
		this.contractCommService.saveContractSignatoryInfo(contract, variablesMap);
		
		// 5供应商(合同各方)
		this.contractCommService.saveContractSupplierInfo(contract, variablesMap);
		
		//租金开票类型
		this.contractCommService.saveContractInvoiceType(contract, variablesMap);
		
		//保存合同文本  
		this.contractCommService.updateWordKey("json_contract_word_str", contract.getId(), variablesMap);
		//保存合同文本到contractdoclist
		this.contractCommService.saveContractDocList("json_contract_word_str", contract, variablesMap);
		//特别付款前提 
		//this.contractCommService.saveContractPremiseInfo(contract, variablesMap);
		
		//保存付款前提 
	    this.contractCommService.saveProjPaymentPremiseCondition(contract, variablesMap);
		
		this.saveContractEquipInfo(contract,variablesMap);
		this.saveContractGuaranteeEquipInfo(contract,variablesMap);
		this.saveContractGuaranteeMethodInfo(contract,variablesMap);
		this.saveContractNumberSettingInfo(contract,variablesMap);
		
	}
	
	// 2.租赁物明细
	public void saveContractEquipInfo(ContractInfo contract,Map<String, String> variablesMap) throws Exception {
				 Map<String,Object>propertiesMap=new HashMap<String,Object>();
				 String docid= variablesMap.get("flowunid");
				 String proj_id = variablesMap.get("proj_id");
				 ProjInfo projinfo = new ProjInfo();
				 projinfo.setId(proj_id);
				 propertiesMap.put("docid", docid);
				 propertiesMap.put("projInfo", projinfo);
				List<ProjEquipTmp>  petlist=this.tableService.findEntityByProperties(ProjEquipTmp.class, propertiesMap);
				ContractEquip contractEquip = null;
				for(ProjEquipTmp pet:petlist){
					contractEquip = new ContractEquip();
					this.tableService.copyAndOverrideExistedValueFromObjectNoSet(pet,contractEquip);
					contractEquip.setContractId(contract);
		          this.tableService.saveEntity(contractEquip);
				 }
	}
	// 3.担保单位
	public void saveContractGuaranteeMethodInfo(ContractInfo contract,Map<String, String> variablesMap) throws Exception {
		/*this.contractCommService.saveContractGuaranteeMethod(contract, variablesMap);*/
		Map<String,Object>propertiesMap=new HashMap<String,Object>();
		 String docid= variablesMap.get("flowunid");
		 String proj_id = variablesMap.get("proj_id");
		 ProjInfo projinfo = new ProjInfo();
		 projinfo.setId(proj_id);
		 propertiesMap.put("docid", docid);
		propertiesMap.put("projInfo",projinfo);
		List<ProjGuaranteeMethodTmp>  pgmtlist=this.tableService.findEntityByProperties(ProjGuaranteeMethodTmp.class, propertiesMap);
		ContractGuaranteeMethod contractGuaranteeMethod = null;
		for(ProjGuaranteeMethodTmp pgmt:pgmtlist){
			contractGuaranteeMethod = new ContractGuaranteeMethod();
			this.tableService.copyAndOverrideExistedValueFromObjectNoSet(pgmt, contractGuaranteeMethod);
			contractGuaranteeMethod.setContractId(contract);
         this.tableService.saveEntity(contractGuaranteeMethod);
		 }
      }
	 // 4.抵押物信息
    public void saveContractGuaranteeEquipInfo(ContractInfo contract,Map<String, String> variablesMap) throws Exception {
			/*this.contractCommService.saveContractGuaranteeEquip(contract, variablesMap);*/
		 Map<String,Object>propertiesMap=new HashMap<String,Object>();
		 String proj_id = variablesMap.get("proj_id");
		 String docid= variablesMap.get("flowunid");
		 propertiesMap.put("docid", docid);
		 propertiesMap.put("projInfo", this.tableService.findEntityByID(ProjInfo.class, proj_id));
		List<ProjGuaranteeEquipTmp>  pgetlist=this.tableService.findEntityByProperties(ProjGuaranteeEquipTmp.class, propertiesMap);
		ContractGuaranteeEquip contractGuaranteeEquip = null;
		for(ProjGuaranteeEquipTmp pget:pgetlist){
			contractGuaranteeEquip = new ContractGuaranteeEquip();
			this.tableService.copyAndOverrideExistedValueFromObjectNoSet(pget, contractGuaranteeEquip);
			contractGuaranteeEquip.setTmpid(pget.getId());;
			contractGuaranteeEquip.setContractId(contract);
         this.tableService.saveEntity(contractGuaranteeEquip);
		 }
     }

	@Override
	public void saveFactoringContractApprovalInfo(Map<String, String> variablesMap) throws Exception {
		//保存合同信息
		ContractInfo contract=contractCommService.saveContractInfo(variablesMap, AppStaticUtil.CONTRACT_APPROVAL);
		//保存交易结构和租金计划，现金流，资金计划
		this.contractCommService.saveContractRentCalculationBefore(contract, variablesMap);
		if(null!=contract.getContractCondition()){
			contract.setStartDate(contract.getContractCondition().getStartDate());
			contract.setEndDate(contract.getContractCondition().getEndDate());
		}
		this.baseService.saveOrUpdateEntity(contract);	
		//保存租赁物明细
		this.contractCommService.saveFactoringContractEquip(contract, variablesMap);
		//合同各方出租房和承租方信息
		this.contractCommService.saveContractSignatoryInfo(contract, variablesMap);
		//供应商(合同各方)
		this.contractCommService.saveContractSupplierInfo(contract, variablesMap);
		//合同贸易基础交易情况
		this.contractCommService.saveConTradeTransaction(contract, variablesMap);
		//保存合同文本  
		this.contractCommService.updateWordKey("json_contract_word_str", contract.getId(), variablesMap);
		//保存合同文本到contractdoclist
		this.contractCommService.saveContractDocList("json_contract_word_str", contract, variablesMap);
		
		
	}
	//合同编号设置
	public void saveContractNumberSettingInfo(ContractInfo contract,Map<String, String> variablesMap) throws Exception {
		 Map<String,Object>propertiesMap=new HashMap<String,Object>();
		 String proj_id = variablesMap.get("proj_id");
		 String docid= variablesMap.get("flowunid");
		 ProjInfo projinfo = new ProjInfo();
		 projinfo.setId(proj_id);
		 propertiesMap.put("docid", docid);
		 propertiesMap.put("projInfo", projinfo);
		List<ContractNumberSettingTmp>  cnstlist=this.tableService.findEntityByProperties(ContractNumberSettingTmp.class, propertiesMap);
		ContractNumberSetting contractNumberSetting = null;
		for(ContractNumberSettingTmp cnst:cnstlist){
			contractNumberSetting = new ContractNumberSetting();
			this.tableService.copyAndOverrideExistedValueFromObjectNoSet(cnst, contractNumberSetting);
			contractNumberSetting.setContractId(contract);
         this.tableService.saveEntity(contractNumberSetting);
		}
     }
	
	
}