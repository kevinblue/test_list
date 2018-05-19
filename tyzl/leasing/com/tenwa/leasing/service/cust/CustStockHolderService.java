

package com.tenwa.leasing.service.cust;

import java.util.Map;

import com.tenwa.business.service.BaseService;



/**
 * 
 * @Title: CustStockholderService.java
 * @package: com.tenwa.leasing.service.cust
 * @author: tpf
 * @date 2014年11月17日 下午6:03:21 
 * @version V5.1
 */
public interface CustStockHolderService extends BaseService{
	public void addCustStockHolder(Map<String,String> model) throws Exception;
	public void updateCustStockHolder(Map<String,String> model) throws Exception;
	public void removeCustStockHolder(Map<String,String> model) throws Exception;
}
