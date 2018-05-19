package com.tenwa.leasing.service.contract.contractChange;   

import java.util.Map;


/**
 * @author 作者 E-mail:
 * @version 创建时间：2014-2-17 下午1:38:07
 * 类说明
 */
public interface ContractChangeService {
	public void saveContractChangeInfo(Map<String, String> variablesMap) throws Exception;
	public void saveFactoringContractChangeInfo(Map<String, String> variablesMap) throws Exception;
}
  
