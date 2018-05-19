package com.tenwa.leasing.service.invoice;

import java.util.Map;



/**   
*    
* 项目名称：tls5.1   
* 类名称：TaxFundService   
* 类描述：   
* 创建人：rovine   
* 创建时间：2014年11月24日 下午5:13:41   
* @version        
*/
public interface TaxInvoiceService{
	/**
	 * @param model
	 * @throws Exception
	 */

	public void submitConfirmInvoice(Map<String, String> model) throws Exception;

	public void deleteInvoiceInfo(Map<String, String> model) throws Exception;

	public void submitRentConfirmInvoice(Map<String, String> model) throws Exception;
	
}
