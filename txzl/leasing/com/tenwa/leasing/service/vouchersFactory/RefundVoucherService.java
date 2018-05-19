package com.tenwa.leasing.service.vouchersFactory;   

import java.util.Map;

/**
 * @author 作者 :sunz
 * @version 创建时间：2016-6-12
 * 类说明
 */
public interface RefundVoucherService {
	/**
	 * 
	 * @param variablesMap
	 * @throws Exception
	 */
	public void createVoucherReceiveRefund(Map<String, String> variablesMap) throws Exception;
	
}
  
