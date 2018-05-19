package com.tenwa.leasing.serviceImpl.contractcomm;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.service.contractcomm.ContractInfoExtends;


@Service(value = "contractInfoExtends")
public class ContractInfoExtendsImpl implements ContractInfoExtends{

	@Resource(name = "tableService")
	private TableService tableService;
	
	public void getContractBaseInfo(Map<String, String> variablesMap, ContractInfo contract, CustInfo cust) throws Exception {
		variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(contract, null, "contract_info"));
		String cust_class = cust.getCustClass().getCode();
		variablesMap.put("contract_info.custname", cust.getCustName());
		variablesMap.put("contract_info.custclass", cust_class);
		variablesMap.put("contract_info.oldcustname", cust.getCustName());//旧承租人name(起租后合同变更使用)
		variablesMap.put("contract_info.oldcustid", cust.getId());//旧承租人id(起租后合同变更使用)
		variablesMap.put("contract_info.custid", cust.getId());//新承租人id
		variablesMap.put("rawValue_contract_info.custid", cust.getCustName());//新承租人名字
		variablesMap.put("contract_info.custnumber", cust.getCustNumber());
		variablesMap.put("contract_info.proj_id", contract.getProjId() == null ? "" : contract.getProjId().getProjId());
		variablesMap.put("contract_info.projdate", contract.getProjId() == null ? "" : contract.getProjId().getProjDate());
		variablesMap.put("contract_info.othercondition", contract.getOtherCondition());
	}

}