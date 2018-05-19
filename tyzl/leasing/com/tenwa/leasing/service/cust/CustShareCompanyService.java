

package com.tenwa.leasing.service.cust;

import java.util.Map;

import com.tenwa.business.service.BaseService;



/**
 * 
 * @Title: CustShareCompanyService.java
 * @package: com.tenwa.leasing.service.cust
 * @author: tpf
 * @date 2014年11月17日 下午6:03:21 
 * @version V5.1
 */
public interface CustShareCompanyService extends BaseService{
	public void addCustShareCompany(Map<String,String> model) throws Exception;
	public void updateCustShareCompany(Map<String,String> model) throws Exception;
	public void removeCustShareCompany(Map<String,String> model) throws Exception;
}
