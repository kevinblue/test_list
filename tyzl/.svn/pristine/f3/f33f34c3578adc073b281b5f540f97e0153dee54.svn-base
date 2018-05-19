package com.tenwa.leasing.serviceImpl.cust;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.entity.Department;
import com.tenwa.business.entity.User;
import com.tenwa.business.entity.UserDepartment;
import com.tenwa.business.job.QuartzJobHolidayInit;
import com.tenwa.business.service.TableService;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.leasing.entity.base.HolidayInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoAccount;
import com.tenwa.leasing.entity.cust.CustInfoClique;
import com.tenwa.leasing.entity.cust.CustInfoCompany;
import com.tenwa.leasing.entity.cust.CustInfoPerson;
import com.tenwa.leasing.entity.cust.CustShareCompany;
import com.tenwa.leasing.entity.cust.CustStockHolder;
import com.tenwa.leasing.service.cust.CustValidatorService;
import com.tenwa.leasing.utils.InitHolidayInfo;




/**
 * 客户信息
 * @Title: CustServiceImpl.java
 * @package: com.tenwa.leasing.serviceImpl.cust
 * @author: tpf
 * @date 2014年11月17日 下午6:04:01 
 * @version V5.1
 */
@Service(value="custValidatorService")
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
	  * @param 数据Map
	  * @throws Exception
	  */
	@Override
	public String checkIdCard(String idcardno,String id) throws Exception {
		String str = "";
		if(null!=id){
			str = " and person.id <> '"+id+"'";
		}
		List<CustInfoPerson> list = 
				baseDao.findResultsByHSQL("select person from CustInfoPerson "
						+ "person where person.custId.invalid = '否' and person.idCardNo = ? "+str, idcardno);
		if(null != list && list.size()>0){
			return "repeat";
		}
		return "";
	}
	@Override
	public String checkOrgcode(String orgcode,String id) throws Exception {
		String str = "";
		if(null!=id){
			str = " and company.id <> '"+id+"'";
		}
		List<CustInfoPerson> list = 
				baseDao.findResultsByHSQL("select company from CustInfoCompany "
						+ "company where company.custId.invalid = '否' and company.orgCode = ? "+str, orgcode);
		if(null != list && list.size()>0){
			return "repeat";
		}
		return "";
	}
	@Override
	public String checkIsMain(String id,String custId) throws Exception {
		String str = "";
		if(null!=id){
			str = " and account.id <> '"+id+"'";
		}
		List<CustInfoPerson> list = 
				baseDao.findResultsByHSQL("select account from CustInfoAccount "
						+ "account where account.custId.invalid = '否' and account.isMain = '是' and account.custId.id = ?"+str,custId);
		if(null != list && list.size()>0){
			return "repeat";
		}
		return "";
	}
	
	@Override
	public String checkCustAccountRepeat(String id, String custId,String accNumber)
			throws Exception {
		String str = "";
		if(null!=id){
		    str = " and account.id <> '"+id+"'";
		}
		List<CustInfoAccount> list = 
				baseDao.findResultsByHSQL("select account from CustInfoAccount "
						+ "account where account.custId.invalid = '否' and account.accNumber = '"+accNumber+"' and account.custId.id = ?"+str,custId);
		if(null != list && list.size()>0){
			return "和"+list.get(0).getCustId().getCustName()+"下面的银行账号重复";
		}
		return "";
	}
	
	
	@Override
	public String checkCardNoShareCompanyRepeat(String id, String custId,
			String cardNo) throws Exception {
		String str = "";
		if(null!=id){
			str = " and Company.id <> '"+id+"'";
		}
		List<CustShareCompany> list = 
				baseDao.findResultsByHSQL("select Company from CustShareCompany "
						+ "Company where Company.custId.invalid = '否' and Company.orgCode = '"+cardNo+"' and Company.custId.id = ?"+str,custId);
		if(null != list && list.size()>0){
			return "和"+list.get(0).getCustId().getCustName()+"下面的银行账号重复";
		}
		return "";
	}
	@Override
	public String checkCardNoCustStockHolderRepeat(String id, String custId,
			String cardNo) throws Exception {
		String str = "";
		if(null!=id){
			str = " and Company.id <> '"+id+"'";
		}
		List<CustStockHolder> list = 
				baseDao.findResultsByHSQL("select Company from CustStockHolder "
						+ "Company where Company.custId.invalid = '否' and Company.orgCode = '"+cardNo+"' and Company.custId.id = ?"+str,custId);
		if(null != list && list.size()>0){
			return "和"+list.get(0).getCustId().getCustName()+"下面的"+list.get(0).getStockholderName();
		}
		return "";
	}
	
	@Override
	public String checkCustByProjInfo(String custId) throws Exception {
		List<CustInfoPerson> list = 
				baseDao.findResultsByHSQL("select projinfo from ProjInfo "
						+ "projinfo where projinfo.custInfo.id = ? ",custId);
		if(null != list && list.size()>0){
			return "repeat";
		}
		return "";
	}
	@Override
	public String checkIsPersonMain(String id, String custId) throws Exception {
		String str = "";
		if(null!=id){
			str = " and person.id <> '"+id+"'";
		}
		List<CustInfoPerson> list = 
				baseDao.findResultsByHSQL("select person from CustRelatedPerson "
						+ "person where person.custId.invalid = '否' and person.mainpersonflag = '是' and person.custId.id = ?"+str,custId);
		if(null != list && list.size()>0){
			return "repeat";
		}
		return "";
	}
	@Override
	public String checkCardNoPersonMain(String id, String custId,String cardNo)
			throws Exception {
		String str = "";
		if(null!=id){
			str = " and person.id <> '"+id+"'";
		}
		List<CustInfoPerson> list = 
				baseDao.findResultsByHSQL("select person from CustRelatedPerson "
						+ "person where person.custId.invalid = '否' and person.idcardno=?  and person.custId.id = ?"+str,cardNo,custId);
		if(null != list && list.size()>0){
			return "repeat";
		}
		return "";
	}
	@Override
	public String changemanage(String custid,String userid) throws Exception
	{
		User user=this.tableService.findEntityByID(User.class, userid);
		Set<UserDepartment> userdepts= user.getUserDepts();
		Department dep=null;
		for(UserDepartment dept:userdepts)
		{
			dep=dept.getDept();
		}
		CustInfo custinfo=this.tableService.findEntityByID(CustInfo.class, custid);
		int i=0;
		if("CUST_INFO_COMPANY".equals(custinfo.getCustClass().getId()))
		{
			CustInfoCompany company=custinfo.getCustInfoCompany();
			 i=baseDao.updateByHSQL("update CustInfoCompany set creator=?,department=? where id=?", user,dep,company.getId());
		}
		else if("CUST_INFO_CLIQUE".equals(custinfo.getCustClass().getId()))
		{
			CustInfoClique clique=custinfo.getCustInfoClique();
			 i=baseDao.updateByHSQL("update CustInfoClique set creator=?,department=? where id=?", user,dep,clique.getId());
		}
		if(i>0)
		{
			return "repeat";
		}
		return "";
	}
	@Override
	public String checkHolholiday(String holholiday) throws Exception {
		String message="";
		String querysql="select count(1) cou from holiday_info h where h.holiday='"+holholiday+"'";
		List<Map<String,Object>> list=this.baseDao.queryListBySql(querysql, null);
		if(null!=list&&list.size()>0){
			message=list.get(0).get("cou").toString();
		}
		return message;
	}
	@Override
	public String updatequartzJobHoliday(String year) throws Exception {
		String sql="delete from holiday_info where holiday like '%"+year+"%'";
		this.baseDao.getJdbcTemplate().execute(sql);
		 Map<String,String>holiday=new HashMap<String,String>();
		 holiday=InitHolidayInfo.getHoliday(year);
		 HolidayInfo hi=null;
		 for(String key:holiday.keySet()){
			 hi=new HolidayInfo();
			 hi.setHoliday(key);
			 hi.setWorkday(holiday.get(key));
			this.tableService.saveOrUpdateEntity(hi);
		 }
		return "操作成功！";
	}
	
}
