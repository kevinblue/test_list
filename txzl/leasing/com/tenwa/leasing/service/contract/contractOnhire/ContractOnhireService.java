package com.tenwa.leasing.service.contract.contractOnhire;   

import java.util.Map;


/**
 * @author zhangc
 * @version 创建时间：2014-11-27 上午11:51
 * 类说明
 */
public interface ContractOnhireService {
	public void updateContractOnhire(Map<String, String> variablesMap) throws Exception;
	public String checkEquipSerial(String id,String equipid) throws Exception;
}
  
