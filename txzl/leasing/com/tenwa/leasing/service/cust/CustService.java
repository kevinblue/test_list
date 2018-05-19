package com.tenwa.leasing.service.cust;

import java.util.Map;

import com.tenwa.business.service.BaseService;



/**
 * 
 * @Title: CustService.java
 * @package: com.tenwa.leasing.service.cust
 * @author: tpf
 * @date 2014年11月17日 下午6:04:10 
 * @version V5.1
 */
public interface CustService extends BaseService{
	public void addCustLaw(Map<String,String> model) throws Exception;
	public void updateCustLaw(Map<String,String> model) throws Exception;
	public void removeCustLaw(Map<String,String> model) throws Exception;
	public void addCustEwlp(Map<String,String> model) throws Exception;
	public void updateCustEwlp(Map<String,String> model) throws Exception;
	public void removeCustEwlp(Map<String,String> model) throws Exception;
	public void saveBelongingPeople(Map<String,String> model) throws Exception;
}
