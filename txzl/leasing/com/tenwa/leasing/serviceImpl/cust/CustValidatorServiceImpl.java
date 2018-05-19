package com.tenwa.leasing.serviceImpl.cust;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.entity.User;
import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.service.TableService;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.base.HolidayClassify;
import com.tenwa.leasing.entity.base.HolidayInfo;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfoAccount;
import com.tenwa.leasing.entity.cust.CustInfoPerson;
import com.tenwa.leasing.entity.cust.CustShareCompany;
import com.tenwa.leasing.entity.cust.CustStockHolder;
import com.tenwa.leasing.entity.factoring.FactoringControversy;
import com.tenwa.leasing.entity.file.BaseFile;
import com.tenwa.leasing.service.cust.CustValidatorService;
import com.tenwa.leasing.utils.InitHolidayInfo;

/**
 * 客户信息
 * 
 * @Title: CustServiceImpl.java
 * @package: com.tenwa.leasing.serviceImpl.cust
 * @author: tpf
 * @date 2014年11月17日 下午6:04:01
 * @version V5.1
 */
@Service(value = "custValidatorService")
public class CustValidatorServiceImpl extends AbstractBaseServiceImpl implements
		CustValidatorService {
	@Resource(name = "tableService")
	private TableService tableService;

	@Resource
	private BaseDao baseDao;

	@Override
	public BaseDao getBussinessDao() throws Exception {
		return baseDao;
	}

	/**
	 * 添加法人客户信息
	 * 
	 * @param 数据Map
	 * @throws Exception
	 */
	@Override
	public String checkIdCard(String idcardno, String id) throws Exception {
		String str = "";
		if (null != id) {
			str = " and person.id <> '" + id + "'";
		}
		List<CustInfoPerson> list = baseDao
				.findResultsByHSQL(
						"select person from CustInfoPerson "
								+ "person where person.custId.invalid = '否' and person.idCardNo = ? "
								+ str, idcardno);
		if (null != list && list.size() > 0) {
			return "repeat";
		}
		return "";
	}

	@Override
	public String checkOrgcode(String orgcode, String id) throws Exception {
		String str = "";
		if (null != id) {
			str = " and company.id <> '" + id + "'";
		}
		List<CustInfoPerson> list = baseDao
				.findResultsByHSQL(
						"select company from CustInfoCompany "
								+ "company where company.custId.invalid = '否' and company.orgCode = ? "
								+ str, orgcode);
		if (null != list && list.size() > 0) {
			return "repeat";
		}
		return "";
	}

	@Override
	public String checkIsMain(String id, String custId) throws Exception {
		String str = "";
		if (null != id) {
			str = " and account.id <> '" + id + "'";
		}
		List<CustInfoPerson> list = baseDao
				.findResultsByHSQL(
						"select account from CustInfoAccount "
								+ "account where account.custId.invalid = '否' and account.isMain = '是' and account.custId.id = ?"
								+ str, custId);
		if (null != list && list.size() > 0) {
			return "repeat";
		}
		return "";
	}

	@Override
	public String checkCustAccountRepeat(String id, String custId,
			String accNumber) throws Exception {
		String str = "";
		if (null != id) {
			str = " and account.id <> '" + id + "'";
		}
		List<CustInfoAccount> list = baseDao
				.findResultsByHSQL(
						"select account from CustInfoAccount "
								+ "account where account.custId.invalid = '否' and account.accNumber = '"
								+ accNumber + "' and account.custId.id = ?"
								+ str, custId);
		if (null != list && list.size() > 0) {
			return "和" + list.get(0).getCustId().getCustName() + "下面的银行账号重复";
		}
		return "";
	}

	@Override
	public String checkCardNoShareCompanyRepeat(String id, String custId,
			String cardNo) throws Exception {
		String str = "";
		if (null != id) {
			str = " and Company.id <> '" + id + "'";
		}
		List<CustShareCompany> list = baseDao
				.findResultsByHSQL(
						"select Company from CustShareCompany "
								+ "Company where Company.custId.invalid = '否' and Company.orgCode = '"
								+ cardNo + "' and Company.custId.id = ?" + str,
						custId);
		if (null != list && list.size() > 0) {
			return "和" + list.get(0).getCustId().getCustName() + "下面的银行账号重复";
		}
		return "";
	}

	@Override
	public String checkCardNoCustStockHolderRepeat(String id, String custId,
			String cardNo) throws Exception {
		String str = "";
		if (null != id) {
			str = " and Company.id <> '" + id + "'";
		}
		List<CustStockHolder> list = baseDao
				.findResultsByHSQL(
						"select Company from CustStockHolder "
								+ "Company where Company.custId.invalid = '否' and Company.orgCode = '"
								+ cardNo + "' and Company.custId.id = ?" + str,
						custId);
		if (null != list && list.size() > 0) {
			return "和" + list.get(0).getCustId().getCustName() + "下面的"
					+ list.get(0).getStockholderName();
		}
		return "";
	}

	@Override
	public String checkCustByProjInfo(String custId) throws Exception {
		List<CustInfoPerson> list = baseDao.findResultsByHSQL(
				"select projinfo from ProjInfo "
						+ "projinfo where projinfo.custInfo.id = ? ", custId);
		if (null != list && list.size() > 0) {
			return "repeat";
		}
		return "";
	}

	@Override
	public String checkIsPersonMain(String id, String custId) throws Exception {
		String str = "";
		if (null != id) {
			str = " and person.id <> '" + id + "'";
		}
		List<CustInfoPerson> list = baseDao
				.findResultsByHSQL(
						"select person from CustRelatedPerson "
								+ "person where person.custId.invalid = '否' and person.mainpersonflag = '是' and person.custId.id = ?"
								+ str, custId);
		if (null != list && list.size() > 0) {
			return "repeat";
		}
		return "";
	}

	@Override
	public String checkCardNoPersonMain(String id, String custId, String cardNo)
			throws Exception {
		String str = "";
		if (null != id) {
			str = " and person.id <> '" + id + "'";
		}
		List<CustInfoPerson> list = baseDao
				.findResultsByHSQL(
						"select person from CustRelatedPerson "
								+ "person where person.custId.invalid = '否' and person.idcardno=?  and person.custId.id = ?"
								+ str, cardNo, custId);
		if (null != list && list.size() > 0) {
			return "repeat";
		}
		return "";
	}

	@Override
	public String checkSigndate(String contractid) throws Exception {
		ContractInfo info = this.baseDao.findEntityByID(ContractInfo.class,
				contractid);
		String signdate = info.getSignDate() == null ? "0" : "1";// 等1的时候说明签约时间有值
		// String
		// sql="select case when c.SIGN_DATE is null then '1' else'0' end signdate from contract_info c  "+contractid;

		return signdate;
	}

	@Override
	public String checkBelongingPeople(String code)
			throws Exception {
		String sql ="";
		if (null != code && !"".equals(code)) {
			 sql = "select c.cust_id id from cust_info_company c  where c.org_code='"+code.trim()+"'";
		 List<Map<String, Object>>  list=this.baseDao.queryListBySql(sql, null);
				 if(null!=list&&list.size()>0){
					String signDate= list.get(0).get("id").toString();
					
					
					return signDate;
				 } 
             
		  }

		return "";
	}

	@Override
	public String updateBelongingPeople(String custid) throws Exception {
		User user  =  SecurityUtil.getPrincipal();//当前人
		String selectsql="";
		String updatesql ="";
		if (null != custid && !"".equals(custid)) {
	    selectsql="select *  from cust_info_company cc where instr(cc.belonging_people, '"+user.getId()+"')>0   and cc.cust_id = '"+custid.trim()+"'";	
		 List<Map<String, Object>>  list=this.baseDao.queryListBySql(selectsql,null);
				 if(null!=list&&list.size()>0){
					 return "solutions/leasing/cust_info/cust_company/cust_company_detail.jsp?id="+custid;
				 } else{
					 updatesql="update cust_info_company c set c.belonging_people = c.belonging_people || ',' || '"+user.getId()+"'  where c.cust_id = '"+custid.trim()+"'";  
					 this.baseDao.updateBySql(updatesql,null);
					 return "solutions/leasing/cust_info/cust_company/cust_company_detail.jsp?id="+custid; 
					 
				 }
             
		  }

		return "";
	}

	@Override
	public String checkPersonBelongingPeople(String idcardno) throws Exception {
		String sql ="";
		if (null != idcardno && !"".equals(idcardno)) {
			 sql = "select c.cust_id id from cust_info_person c  where c.id_card_no='"+idcardno.trim()+"'";
		 List<Map<String, Object>>  list=this.baseDao.queryListBySql(sql, null);
				 if(null!=list&&list.size()>0){
					String signDate= list.get(0).get("id").toString();
					
					
					return signDate;
				 } 
             
		  }

		return "";
	}

	@Override
	public String updatePersonBelongingPeople(String custid) throws Exception {
		User user  =  SecurityUtil.getPrincipal();//当前人
		String selectsql="";
		String updatesql ="";
		if (null != custid && !"".equals(custid)) {
	    selectsql="select *  from cust_info_person cc where instr(cc.belonging_people, '"+user.getId()+"')>0   and cc.cust_id = '"+custid.trim()+"'";	
		 List<Map<String, Object>>  list=this.baseDao.queryListBySql(selectsql,null);
				 if(null!=list&&list.size()>0){
					 return "solutions/leasing/cust_info/cust_person/cust_person_detail.jsp?id="+custid;
				 } else{
					 updatesql="update cust_info_person c set c.belonging_people = c.belonging_people || ',' || '"+user.getId()+"'  where c.cust_id = '"+custid.trim()+"'";  
					 this.baseDao.updateBySql(updatesql,null);
					 return "solutions/leasing/cust_info/cust_person/cust_person_detail.jsp?id="+custid; 
					 
				 }
             
		  }

		return "";
	}

	@Override
	public String updateFactoringControversy(String id,String relievedate,String status,String relieveexplaination)
			throws Exception {
		
		String updatesql ="update  FactoringControversy ss set ss.relieveDate='"+relievedate+"',ss.status='"+status+
				"',ss.relieveExplaination='"+relieveexplaination+"' where ss.id='"+id+"'";
		System.out.println(updatesql);
		int  cou=this.baseDao.updateByHSQL(updatesql, null);
		if(cou>0){
			return "保存成功！";
		}
		
		return "";
	}
	@Override
	public String updateFactoringControversyRelieve(String id,int status) throws Exception {
		String updatesql ="update  FactoringControversyRelieve fcr set fcr.status="+status+" where fcr.id='"+id+"'";
		System.out.println(updatesql);
		int  cou=this.baseDao.updateByHSQL(updatesql, null);
		if(cou>0){
			return "删除成功！";
		}
		return "";
	}

	@Override
	public String getPremiseCondition(Map<String, String> model)
			throws Exception {
		String paymentidTmp= "第"+model.get("paymentid")+"笔";
		//正则表达式只保存数字
		String paymentid=Pattern.compile("[^0-9]").matcher(paymentidTmp).replaceAll("").trim(); 
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.clear();
		queryMainObjectMap.put("contract_id", model.get("contractid"));
		queryMainObjectMap.put("payment_id", paymentidTmp);
		queryMainObjectMap.put("fee_type", model.get("feetype"));
		queryMainObjectMap.put("devicename", model.get("devicename"));
		try{
			String json=this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_comm/conpayment_premise_condition.xml", queryMainObjectMap).toString();
			return  json;
		}catch (Exception e) {
			throw new BusinessException("系统出错" + e);
		}
		
	}
	@Override
	public  String getBaseFileByFlowunidAndId(String id,String flowuid)
			throws Exception {
		try{
			Map<String,String> propertiesMap =new HashMap<String,String>();
			propertiesMap.put("filekey",id);
			propertiesMap.put("flowUnid",flowuid);
			String basefilelist=this.tableService.getJsonArrayData("termsofpayment/termsofpayment.xml",propertiesMap).toString();
			return basefilelist;
		}catch (Exception e) {
			throw new BusinessException("系统出错" + e);
		}
	}

	@Override
	public String getUsersPassword(Map<String, String> model) throws Exception {
		String username=model.get("username");
		String password=model.get("password");
		int flagint=0;
		String getuserSQL="select count(1) as cou from t_users t where t.username_='"+username+"'";
		List<Map<String, Object>>  list=this.baseDao.queryListBySql(getuserSQL,null);
		String getpassword="select count(1) as cuu from t_users t where t.username_='"+username+"' and t.sourcepassword_='"+password+"'";
		if(null!=list&&list.size()>0){
			 flagint=Integer.parseInt((String) list.get(0).get("cou"));
		} 
		if(flagint==0){//表示帐号错误
			return "0";
		}else{
			List<Map<String, Object>>  listcuu=this.baseDao.queryListBySql(getpassword,null);
			int passwordcuu=0;
			if(null!=listcuu&&listcuu.size()>0){
				passwordcuu=Integer.parseInt((String) listcuu.get(0).get("cuu"));
			} 
			if(passwordcuu==0){//密码错误
				return "1";
			}else{
				return "2";
			}
			
		}
	}
	//初始化周六周日
	@Override
	public String updatequartzJobHoliday(String year,String id) throws Exception {
		String sql="delete from holiday_info where holiday_ like '%"+year+"%'";
		this.baseDao.getJdbcTemplate().execute(sql);
		 Map<String,String>holiday=new HashMap<String,String>();
		 holiday=InitHolidayInfo.getHoliday(year);
		 HolidayInfo hi=null;
		 HolidayClassify hc=this.baseDao.findEntityByID(HolidayClassify.class, id);
		 for(String key:holiday.keySet()){
			 hi=new HolidayInfo();
			 hi.setHoliday(key);
			 hi.setWorkday(holiday.get(key));
			 hi.setHolidayclassifyid(hc);
			 this.baseDao.saveEntity(hi);
		 }
		return "操作成功！";
	}

	@Override
	public String createHolidayNumber() throws Exception {
		String sql="select count(1) cou from holiday_classify";
		List<Map<String,Object>> list=this.baseDao.queryListBySql(sql);
		return list.get(0).get("cou").toString();
	}
}
















