package com.tenwa.leasing.service.vouchersFactory;   

import com.tenwa.leasing.entity.invoice.VatInvoiceInfo;

/**
 * @author 作者 :sunz
 * @version 创建时间：2016-6-12 
 * 类说明
 */
public interface InvoiceVoucherService {
	/**
	 * 
	 * @param variablesMap
	 * @throws Exception
	 */
	public void createVoucherReceiveInvoice(VatInvoiceInfo vatInvoiceInfo) throws Exception;
	
}
  
