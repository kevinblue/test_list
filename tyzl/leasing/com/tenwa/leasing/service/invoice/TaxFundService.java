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
public interface TaxFundService{
	/**
	 * @param model
	 * @throws Exception
	 */
	public void submitFundPlanInvoice(Map<String,String> model) throws Exception;

	/**
	 * @param model
	 * @throws Exception
	 */
	public void backFundPlanInvoice(Map<String, String> model) throws Exception;

	/**
	 * @param model
	 * @throws Exception
	 */
	public void submitFundChargeInvoice(Map<String, String> model) throws Exception;
	
	/**
	 * @param model
	 * @throws Exception
	 */
	public void updateFundstatus(Map<String, String> model) throws Exception;

	/**
	 * @param model
	 * @throws Exception
	 */
	public void removeFundChargeInvoice(Map<String, String> model) throws Exception;

	/**
	 * @param model
	 * @throws Exception
	 */
	public void backFundChargeInvoice(Map<String, String> model) throws Exception;
	
	/**
	 * @param model
	 * @throws Exception
	 */
	public void addTaxFundHcInfoInvoice(Map<String, String> model) throws Exception;

	/**
	 * @param model
	 * @throws Exception
	 */
	public void submitFundChargeReceipt(Map<String, String> model) throws Exception;

	/**
	 * @param model
	 * @throws Exception
	 */
	public void backFundChargeReceipt(Map<String, String> model) throws Exception;

	/**
	 * @param model
	 * @throws Exception
	 */
	public void deleteFundInvoiceOrReceipt(Map<String, String> model) throws Exception;

	/**
	 * @param model
	 * @throws Exception
	 */
	public void submitFundChargeNoInvoice(Map<String, String> model) throws Exception;

	

	
}
