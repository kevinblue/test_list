

package com.tenwa.leasing.service.cust;

import java.util.Map;

import com.tenwa.business.service.BaseService;



/**
 * 客户校验
 * @Title: CustValidatorService.java
 * @package: com.tenwa.leasing.service.cust
 * @author: tpf
 * @date 2014年11月20日 下午6:30:50 
 * @version V5.1
 */
public interface CustValidatorService extends BaseService{
	public String checkIdCard(String idcardno,String id) throws Exception;
	public String checkOrgcode(String orgcode,String id) throws Exception;
	public String checkIsMain(String id,String custId) throws Exception;
	public String checkCustAccountRepeat(String id,String custId,String accNumber) throws Exception;
	public String checkCardNoShareCompanyRepeat(String id,String custId,String accNumber) throws Exception;
	public String checkCardNoCustStockHolderRepeat(String id,String custId,String accNumber) throws Exception;
	public String checkCustByProjInfo(String custId) throws Exception;
	public String checkIsPersonMain(String id,String custId) throws Exception;
	public String checkCardNoPersonMain(String id,String custId,String cardNo) throws Exception;
	public String changemanage(String custid,String userid) throws Exception;
	public String checkHolholiday(String holholiday) throws Exception;
	public String updatequartzJobHoliday(String year) throws Exception;
}
