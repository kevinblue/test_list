package com.tenwa.leasing.serviceImpl.contract.contractModify;



import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.reckon.entity.contract.reckon.fund.FundRentAdjust;
import com.reckon.service.RentConditionDataOperator;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.BaseService;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.service.contract.contractModify.ContractModifyService;
import com.tenwa.leasing.service.contractcomm.ContractCommService;


@Service(value = "contractModifyService")
public class ContractModifyServiceImpl implements  ContractModifyService {

	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "RentConditionDataService")
	private RentConditionDataOperator rentConditionData;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@Override
	public void startContractModify(Map<String, String> variablesMap)
			throws Exception {
		String contractId =  variablesMap.get("contract_info.id");
		ContractInfo ci  = this.baseService.findEntityByID(ContractInfo.class, contractId);
		ci.setActualEndDate(variablesMap.get("framework_condition.enddate"));
		this.baseService.saveOrUpdateEntity(ci);
		this.contractCommService.saveContractChangeInfo(ci, variablesMap);
		FundRentAdjust adjust = new FundRentAdjust();
		this.baseService.copyAndOverrideExistedValueFromStringMap(variablesMap, adjust, null,true,"fund_rent_adjust");
		adjust.setDocId(variablesMap.get("docId"));
		adjust.setContractId(ci);
		DictionaryData adjustType = new DictionaryData();
		adjustType.setId("his_plan_mod");//租金变更类型
		adjust.setAdjustType(adjustType);
		this.baseService.saveEntity(adjust);
		this.rentConditionData.updateContractConditionDataAndSaveDatatoHis(ci, variablesMap, variablesMap.get("docId"), "his_plan_mod",  "framework_condition", "json_fund_rent_plan_modify_str","json_fund_cash_flow_new_str","", "","json_fund_fund_charge_new_str");
	}
}
