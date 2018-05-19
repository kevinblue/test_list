package com.tenwa.leasing.serviceImpl.contract.contractCancel;



import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.AppStaticUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.service.contract.contractCancel.ContractCancelService;


@Service(value = "contractCancelService")
public class ContractCancelServiceImpl implements ContractCancelService {

	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Override
	public void saveContractCancelInfo(Map<String, String> variablesMap) throws Exception {
		String contract_id = variablesMap.get("contract_info.id");
		String repeal_date = variablesMap.get("contract_info.repealdate");
		String repeal_reason_type = variablesMap.get("contract_info.repealreasontype");
		String repeal_reason = variablesMap.get("contract_info.repealreason");
		DictionaryData dictdata = new DictionaryData();
		dictdata.setId(repeal_reason_type);
		ContractInfo contract = this.baseService.findEntityByID(ContractInfo.class, contract_id);
		//1.保存合同信息
		contract.setRepealDate(repeal_date);
		contract.setRepealReasonType(dictdata);
		contract.setRepealReason(repeal_reason);
		contract.setContractStatus(AppStaticUtil.CONTRACT_CANCEL);
		this.baseService.saveOrUpdateEntity(contract);
	}
}
