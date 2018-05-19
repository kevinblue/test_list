package com.tenwa.leasing.serviceImpl.contract.onhireContractChange;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import com.tenwa.business.service.BaseService;
import com.tenwa.leasing.entity.contract.ContractEquip;
import com.tenwa.leasing.entity.contract.ContractEquipHis;
import com.tenwa.leasing.entity.contract.ContractGuaranteeEquip;
import com.tenwa.leasing.entity.contract.ContractGuaranteeEquipHis;
import com.tenwa.leasing.entity.contract.ContractGuaranteeMethod;
import com.tenwa.leasing.entity.contract.ContractGuaranteeMethodHis;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.contract.ContractSignatory;
import com.tenwa.leasing.entity.contract.ContractSupplierInfo;
import com.tenwa.leasing.entity.contract.OnhireContractChangeInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.service.contract.onhireContractChange.OnhireContractChangeService;
import com.tenwa.leasing.service.contractcomm.ContractCommService;


@Service(value = "onhireContractChangeService")
public class OnhireContractChangeServiceImpl implements OnhireContractChangeService {

	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@Override
	public void saveOnhireContractChangeInfo(Map<String, String> variablesMap) throws Exception {
		String contract_id = variablesMap.get("contract_info.id");
		String oldcustid = variablesMap.get("contract_info.oldcustid");
		String newcustid = variablesMap.get("contract_info.newcustid");
		ContractInfo contractInfo = (ContractInfo)this.baseService.findEntityByID(ContractInfo.class, contract_id);		
		// 1.承租人变列
		if (newcustid != null && !"".equals(newcustid)) {
		//	if (!oldcustid.equals(newcustid)) {
				CustInfo custInfo = (CustInfo) this.baseService.findEntityByID(CustInfo.class, newcustid);
				contractInfo.setCustId(custInfo);
				this.baseService.saveOrUpdateEntity(contractInfo);
				this.contractCommService.saveContractCustChangeInfo(contractInfo, variablesMap);
		//	}
		}
		// 2.保存变更信息
		this.contractCommService.saveContractChangeInfo(contractInfo, variablesMap);
		// 3.保存变更后租赁物件
		this.contractCommService.saveContractEquip(contractInfo,variablesMap);
		// 4.保存变更后担保单位
		this.contractCommService.saveContractGuaranteeMethod(contractInfo, variablesMap);
		// 5.保存变更后抵押物信息
		this.contractCommService.saveContractGuaranteeEquip(contractInfo, variablesMap);
		// 6.合同各方出租房和承租方信息
		this.contractCommService.saveContractSignatoryInfo(contractInfo, variablesMap);
		// 7供应商(合同各方)
		this.contractCommService.saveContractSupplierInfo(contractInfo, variablesMap);
		// 8发票信息变更
		this.contractCommService.saveContractInvoiceType(contractInfo, variablesMap);
		// 9其它变更信息
		this.contractCommService.saveContractChangeOtherInfo(contractInfo, variablesMap);
	}

	@Override
	public void saveAssetsContractChangeinfo(Map<String, String> variablesMap)
			throws Exception {
		String contract_id = variablesMap.get("contract_info.id");
		String oldcustid = variablesMap.get("contract_info.oldcustid");
		String newcustid = variablesMap.get("contract_info.custid");
		ContractInfo contractInfo = (ContractInfo)this.baseService.findEntityByID(ContractInfo.class, contract_id);	
		// 2.保存变更信息
		this.contractCommService.saveContractChangeInfo(contractInfo, variablesMap);
		
		String sub=variablesMap.get("proj_info.subchangecontent");
		if(sub!=null){
			String[] su=sub.split(",");
			for(int i=0;i<su.length;i++){
				if("租赁物变更".equals(su[i])){
					// 3.保存变更后租赁物件
					this.contractCommService.saveContractEquip(contractInfo,variablesMap);
				}else if("承租人变更".equals(su[i])){
					// 1.承租人变列
					if (newcustid != null && !"".equals(newcustid)) {
						CustInfo custInfo = (CustInfo) this.baseService.findEntityByID(CustInfo.class, newcustid);
						contractInfo.setCustId(custInfo);
						this.baseService.saveOrUpdateEntity(contractInfo);
						this.contractCommService.saveContractCustChangeInfo(contractInfo, variablesMap);
					}
				}else if("抵押物变更".equals(su[i])){
					// 5.保存变更后抵押物信息
					this.contractCommService.saveContractGuaranteeEquip(contractInfo, variablesMap);
				}else if("担保变更".equals(su[i])){
					// 4.保存变更后担保单位
					this.contractCommService.saveContractGuaranteeMethod(contractInfo, variablesMap);
				}
			}			
		}
		String subnot=variablesMap.get("proj_info.subnotchangecontent");
		
		if("null".equals(subnot)){
			
		}else if("租金日变更".equals(subnot)){
			this.contractCommService.updateContractConditionDataAndSaveDatatoHis(contractInfo,variablesMap,variablesMap.get("docId"),"onhire_change");
		}else if("商务条款变更".equals(subnot)){
			this.contractCommService.updateContractCondition(variablesMap);
		}else{
			//7供应商(合同各方)
			this.contractCommService.saveContractSupplierInfo(contractInfo, variablesMap);
		}		
		/*	// 6.合同各方出租房和承租方信息
		this.contractCommService.saveContractSignatoryInfo(contractInfo, variablesMap);
		// 8发票信息变更
		this.contractCommService.saveContractInvoiceType(contractInfo, variablesMap);
		// 9其它变更信息
		this.contractCommService.saveContractChangeOtherInfo(contractInfo, variablesMap);*/
		
	}
}
