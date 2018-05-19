package com.tenwa.leasing.service.invoice;

import java.util.Map;



/**   
*    
* 项目名称：tls5.1   
* 类名称：VatInvoiceService   
* 类描述：   
* 创建人：luoshuai   
* 创建时间：2014年11月24日 下午5:13:41   
* @version        
*/
public interface VatInvoiceService{
	/**
	 * @param model
	 * @throws Exception
	 */
	public void submitVatInvoice(Map<String,String> model) throws Exception;

	/**
	 * @param model
	 * @throws Exception
	 */
	public void saveVatInvoice(Map<String, String> model) throws Exception;

	/**
	 * @param model
	 * @throws Exception
	 */
	public void backVatInvoice(Map<String, String> model) throws Exception;
	
	/**
	 * @param model
	 * @throws Exception
	 */
	public void saveVatInvoiceContract(Map<String, String> model) throws Exception;
	
	/**
	 * @param model
	 * @throws Exception
	 */
	public void updateVatInvoiceContract(Map<String, String> model) throws Exception;
}
