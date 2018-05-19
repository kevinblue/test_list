

package com.tenwa.leasing.service.cust;

import java.util.Map;

import com.tenwa.business.service.BaseService;



/**
 * 
 * @Title: CustAccountService.java
 * @package: com.tenwa.leasing.service.cust
 * @author: tpf
 * @date 2014年11月17日 下午6:03:21 
 * @version V5.1
 */
public interface CustAccountService extends BaseService{
	public void addCustAccount(Map<String,String> model) throws Exception;
	public void updateCustAccount(Map<String,String> model) throws Exception;
	public void removeCustAccount(Map<String,String> model) throws Exception;
}
