package com.tenwa.leasing.service.contractcomm;

import java.util.Map;

import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;

public interface ContractInfoExtends{

	public void getContractBaseInfo(Map<String, String> variablesMap, ContractInfo contract, CustInfo cust) throws Exception;
	
}
