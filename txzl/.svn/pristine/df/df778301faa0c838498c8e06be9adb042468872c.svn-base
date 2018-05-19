package com.tenwa.leasing.serviceImpl.contract.contractOnhire;



import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.reckon.service.RentConditionDataOperator;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.AppStaticUtil;
import com.tenwa.leasing.entity.contract.ContractEquip;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfoPerson;
import com.tenwa.leasing.service.contract.contractOnhire.ContractOnhireService;
import com.tenwa.leasing.service.contractcomm.ContractCommService;


@Service(value = "contractOnhireService")
public class ContractOnhireServiceImpl implements ContractOnhireService {

	private static final Logger log = LoggerFactory.getLogger(ContractOnhireServiceImpl.class);
	
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "RentConditionDataService")
	private RentConditionDataOperator rentConditionData;
	
	
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@Override
	public void updateContractOnhire(Map<String, String> variablesMap)throws Exception {
		
		//保存合同信息
		ContractInfo contractInfo=contractCommService.saveContractInfo(variablesMap, AppStaticUtil.CONTRACT_START);
		
		//保存租赁物件
		this.contractCommService.saveContractEquip(contractInfo, variablesMap);
		
		//保存发票信息
		this.contractCommService.saveContractInvoiceType(contractInfo, variablesMap);
		
		//保存交易结构并his,保存租金计划，现金流并his
		String docId = variablesMap.get("docId");
		this.contractCommService.updateContractConditionDataAndSaveDatatoHis(contractInfo,variablesMap,docId,"his_contract_onhire");
		//保存应收应付计划并his
		this.contractCommService.updateContractFundDataAndSaveDatatoHis(contractInfo,variablesMap,docId,"his_contract_onhire");
		
		//列新合同表中的实际开始日和计划结束日期
		if(null!=contractInfo.getContractCondition()){
			contractInfo.setActualStartDate(contractInfo.getContractCondition().getStartDate());
			contractInfo.setEndDate(contractInfo.getContractCondition().getEndDate());	
		}
		this.baseService.saveOrUpdateEntity(contractInfo);		
	}

	@Override
	public String checkEquipSerial(String id,String equipid)
			throws Exception {
		String str = "";
		if(null!=id){
			str = " and equip.id not in ("+id+")";
		}
		if(null!=equipid){
			str += " and equip.equipId in ("+equipid+")";
		}
		List<ContractEquip> list = 
				baseService.findResultsByHSQL("select equip from ContractEquip "
						+ "equip where 1 = 1 "+str);
		if(null != list && list.size()>0){
			return "repeat";
		}
		return "";
		
	}
}
