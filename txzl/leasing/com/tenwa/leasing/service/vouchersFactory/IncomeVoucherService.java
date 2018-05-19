package com.tenwa.leasing.service.vouchersFactory;   

import java.util.Map;

/**
 * @author 作者 :sunz
 * @version 创建时间：2016-6-12 
 * 类说明
 */
public interface IncomeVoucherService {
	/**
	 * 
	 * @param variablesMap
	 * @throws Exception
	 */
	public void createVoucherReceiveIncome(Map<String, String> variablesMap) throws Exception;
	
}
  
