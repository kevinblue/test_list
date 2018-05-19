package com.tenwa.leasing.serviceImpl.contract.contractApproval;



import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
  
import com.reckon.service.RentConditionDataOperator;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.AppStaticUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.service.contract.contractApproval.ContractApprovalService;
import com.tenwa.leasing.service.contractcomm.ContractCommService;


@Service(value = "contractApprovalService")
public class ContractApprovalServiceImpl implements ContractApprovalService {

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
		// 2.租赁物明细
		this.contractCommService.saveContractEquip(contract, variablesMap);
		
		// 3.担保单位
		this.contractCommService.saveContractGuaranteeMethod(contract, variablesMap);
		
		// 4.抵押物信息
		this.contractCommService.saveContractGuaranteeEquip(contract, variablesMap);
		
		// 5.合同各方出租房和承租方信息
		this.contractCommService.saveContractSignatoryInfo(contract, variablesMap);
		
		//合同各方承租方信息
		this.contractCommService.saveContractSignatorySecondInfo(contract, variablesMap);
		
		// 5供应商(合同各方)
		this.contractCommService.saveContractSupplierInfo(contract, variablesMap);
		
		//租金开票类型
		this.contractCommService.saveContractInvoiceType(contract, variablesMap);
		
		//保存合同文本
		this.contractCommService.updateWordKey("json_contract_word_str", contract.getId(), variablesMap);
		
		this.contractCommService.saveContractUnionCust(contract, variablesMap);
	}
}
