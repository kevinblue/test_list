

package com.tenwa.leasing.service.cust;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.service.BaseService;
import com.tenwa.leasing.entity.file.BaseFile;



/**
 * 客户校验
 * @Title: CustValidatorService.java
 * @package: com.tenwa.leasing.service.cust
 * @author: tpf
 * @date 2014年11月20日 下午6:30:50 
 * @version V5.1
 */
public interface CustValidatorService extends BaseService{  
	public String updateFactoringControversy(String id,String relievedate,String status,String relieveexplaination) throws Exception;
	public String updateFactoringControversyRelieve(String id,int status) throws Exception;
	public String checkIdCard(String idcardno,String id) throws Exception;
	public String checkOrgcode(String orgcode,String id) throws Exception;
	public String checkIsMain(String id,String custId) throws Exception;
	public String checkCustAccountRepeat(String id,String custId,String accNumber) throws Exception;
	public String checkCardNoShareCompanyRepeat(String id,String custId,String accNumber) throws Exception;
	public String checkCardNoCustStockHolderRepeat(String id,String custId,String accNumber) throws Exception;
	public String checkCustByProjInfo(String custId) throws Exception;
	public String checkIsPersonMain(String id,String custId) throws Exception;
	public String checkCardNoPersonMain(String id,String custId,String cardNo) throws Exception;
	
	public String checkSigndate(String contractid) throws Exception;
	
	public String checkBelongingPeople(String code) throws Exception;
	public String updateBelongingPeople(String custid) throws Exception;
	public String checkPersonBelongingPeople(String idcardno) throws Exception;
	public String updatePersonBelongingPeople(String custid) throws Exception;
	public String getPremiseCondition(Map<String,String> model) throws Exception;
	public String getBaseFileByFlowunidAndId(String id,String flowuid)throws Exception;
	public String getUsersPassword(Map<String,String> model) throws Exception;
	//初始化周六周日
	public String updatequartzJobHoliday(String year,String id) throws Exception;
	//自动创建节假日编号
	public String createHolidayNumber()throws Exception;
}
