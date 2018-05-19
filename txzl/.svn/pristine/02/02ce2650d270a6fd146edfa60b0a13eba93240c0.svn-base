package com.tenwa.leasing.serviceImpl.contract.accountChange;



import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.contract.ContractSignatory;
import com.tenwa.leasing.service.contract.accountChange.AccountChangeService;
import com.tenwa.leasing.service.contractcomm.ContractCommService;


@Service(value = "accountChangeService")
public class AccountChangeServiceImpl implements AccountChangeService {

	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@Override
	public void saveAccountChangeInfo(Map<String, String> variablesMap) throws Exception {
		String contract_id = variablesMap.get("contract_info.id");
		ContractInfo contractInfo = (ContractInfo)this.baseService.findEntityByID(ContractInfo.class, contract_id);		
		ContractSignatory contractSignatory = new ContractSignatory();
		if(null!=contractInfo.getContractSignatory()){
			contractSignatory = contractInfo.getContractSignatory();
		}else{
			contractSignatory.setContractId(contractInfo);
		}
		contractSignatory.setLeaseAccNumber(variablesMap.get("contract_signatory.leaseaccnumber"));
		contractSignatory.setLeaseAccBank(variablesMap.get("contract_signatory.leaseaccbank"));
		contractSignatory.setLeaseAccName(variablesMap.get("contract_signatory.leaseaccname"));
		User user  = (User) SecurityUtil.getPrincipal();//当前人
		String systemDate = DateUtil.getSystemDateTime();//当前时间
		contractSignatory.setModifyDate(systemDate);
		contractSignatory.setModificator(user);
		this.baseService.saveOrUpdateEntity(contractSignatory);
		
	}
}
