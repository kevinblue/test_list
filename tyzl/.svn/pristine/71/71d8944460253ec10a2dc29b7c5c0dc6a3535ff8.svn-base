package com.tenwa.leasing.serviceImpl.voucher;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.entity.User;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.voucher.InterOrgCode;
import com.tenwa.leasing.entity.voucher.IntereasVoucherHead;
import com.tenwa.leasing.entity.voucher.IntereasVoucherLog;
import com.tenwa.leasing.service.voucher.IntereasVoucherheadService;
import com.tenwa.leasing.utils.StrTools;
import com.tenwa.leasing.utils.Tools;
/**
 * 
 * <p>凭证头服务对应继承实现。</p>
 * <p>2013-10-17</p>
 * @author sea
 * @version 4.5
 */
@Service(value="intereasVoucherheadService")
public class IntereasVoucherheadServiceImpl extends AbstractBaseServiceImpl implements IntereasVoucherheadService {
	/**
	 * 记录log4j
	 */
	private Logger log = Logger.getLogger(IntereasVoucherheadServiceImpl.class);
	/**
	 * 持久化服务
	 */
	@Resource
	private BaseDao baseDao;
	
	@Override
	public BaseDao getBussinessDao() throws Exception {
		return baseDao;
	}
//	User user = (User) SecurityUtil.getPrincipal();
	/*
	 * (non-JAVADOC)
	 * @see com.business.service.voucher.Intereas_Voucherhead_Service#init_Intereas_Voucherhead(com.business.entity.voucher.IntereasVoucherHead, com.business.entity.contract.ContractInfo, com.business.entity.User)
	 */
	@Override
	public IntereasVoucherHead saveIntereasVoucherhead(IntereasVoucherHead intereasVoucherhead,ContractInfo contractInfo,User user) throws Exception {
		IntereasVoucherHead allHead;
		String message = "";
        if (intereasVoucherhead == null || "".equals(intereasVoucherhead)) {  
        	message = "传入凭证头对象参数为空无法做生成凭证头操作";
        	log.error(message);
        	return null;
        }else{
			//方式1：获取完整的凭证头对象，通过对象更新
			allHead = this.saveAllIntereasVoucherHead(intereasVoucherhead, contractInfo,user);
			//this.getBussinessDao().saveEntity(allHead);
			this.baseDao.saveEntity(allHead);
			message = "凭证头初始化完成";
        }
        log.debug("凭证头持久化信息："+message);
		return allHead;
	}
	
	
	/*
	 * @author sea
	 */
	public  void  saveVoucherheadJdbc(Map<String,String> map,User user) throws Exception {
		String voucherNumber = "";
		String message = "";
		StringBuffer sql = new StringBuffer();
		StringBuffer filed = new StringBuffer();
		
		voucherNumber = Tools.getDBStr( map.get("voucherNumber") );
    	log.debug("本次凭证凭证号："+voucherNumber);
    	String companyNumber = Tools.getDBStr( map.get("org_code") );//公司编码
    	String bizDate = Tools.getDBDateStr( map.get("bizDate") );//业务日期
    	String voucherType = Tools.getDBStr( map.get("voucherType") );//凭证字（凭证类型）
    	String modleName = Tools.getDBStr( map.get("modleName") );//业务模块
    	String contractId  = Tools.getDBStr( map.get("contractId") );//租赁合同号
    	String memo1  = Tools.getDBStr( map.get("memo1") );//凭证备注 可以为空
    	String uuId = Tools.getDBStr( map.get("uuId") );//UUID 主键 非空
    	//获取UUID：UUIDUtil.getUUID();
    	
    	//当前登录人对象获取
    	String creator = user.getId();//制单人,当前登录人对象 
    	String status = "已完整";//凭证状态
    	String eas_flag  = "0";//导入财务系统状态 默认0
    	String bookedDate =  "";//Tools.getDBDateStr( map.get("bookedDate") ) ;//会计记帐日期
    	String periodYear = "";//会计期间-年 
    	String periodNumber = "";//会计期间-月
    	String generateDate  = "";//凭证发生日期（取系统当前日期）
    	//判断值
    	if(Tools.isNullOrEmpty(voucherNumber)){
    		message = message + "凭证号获取失败!";
    	}
    	if(Tools.isNullOrEmpty(companyNumber)){
    		message = message + "公司编码获取失败!";
    	}
    	if(Tools.isNullOrEmpty(bizDate)){
    		message = message + "业务日期获取失败!";
    	}else{
    		//根据业务操作日期查询财务对账日期
			bookedDate = this.getBookDate(bizDate);
    		if(Tools.isNullOrEmpty(bookedDate)){
        		message = message + "财务记账日期获取失败!";
        	}else{
        		periodYear = bookedDate.substring(0,4);//会计期间-年 
        		periodNumber = bookedDate.substring(5,7);//会计期间-月
        	}
    	}
    	
    	if(Tools.isNullOrEmpty(voucherType)){
    		message = message + "凭证类型获取失败!";
    	}
    	if(Tools.isNullOrEmpty(creator)){
    		creator = "预设用户";
    	}
    	if(Tools.isNullOrEmpty(modleName)){
    		message = message + "业务模块获取失败!";
    	}
		if(Tools.isNullOrEmpty(generateDate)){
			generateDate = DateUtil.getSystemDateTime();
		}
		
    	//以上校验数据中任何一个参数为空，则将凭证状态置为‘未完整’
    	if(!Tools.isNullOrEmpty(message)){
    		status = "未完整";
    	}
    	
    	//拼装凭证头信息用于存凭证日志记录,做为补凭证的依据记录
    	filed.append("凭证头intereas_voucherhead信息如下/")
    		.append("凭证号:"+voucherNumber+"/")
	    	.append("公司编码:"+companyNumber+"/")
	    	.append("会计记帐日期:"+bookedDate+"/")
	    	.append("业务日期:"+bizDate+"/")
	    	.append("会计期间-年 :"+periodYear+"/")
	    	.append("会计期间-月 :"+periodNumber+"/")
	    	.append("凭证类型:"+voucherType+"/")
	    	.append("制单人:"+creator+"/")
	    	.append("凭证状态:"+status+"/")
	    	.append("凭证发生日期:"+generateDate+"/")
	    	.append("导入财务系统状态:"+eas_flag+"/")
	    	.append("业务模块:"+modleName+"/")
	    	.append("合同号:"+contractId+"/")
	    	.append("凭证备注:"+memo1+"");
    	log.debug("凭证头filed:"+filed);
    	//凭证头日志字符串
		IntereasVoucherLog obj = new IntereasVoucherLog();
		obj.setMemo(filed.toString());
		obj.setMessage(message);
    	obj.setCreateDate(DateUtil.getSystemDateTime());
    	obj.setCreator(user);
    	this.getBussinessDao().saveEntity(obj);
    	
    
    	//拼接SQL
    	sql.append(" insert into  intereas_voucherhead ")
	        .append(" ( ")
	        .append(" ID,voucherNumber,companyNumber,bookedDate,bizDate, ")
	        .append(" periodYear,periodNumber,voucherType,creator, ")
	        .append(" status_,generate_date,eas_flag,modleName,contract_id,memo1 ")
	        .append("  ) values ( ")
	        .append(" ?")//uuId
	        .append("  ?, ")//凭证号voucherNumber
	        .append("  ?,")//公司编码companyNumber
	        .append("  ?, ")//记帐日期bookedDate
	        .append("  ?, ")//业务日期bizDate
	        .append("  ?, ")//会计期间-年 periodYear
	        .append("  ?, ")//会计期间-月periodNumber
	        .append("  ?, ")//凭证字（凭证类型）voucherType
	        .append("  ?, ")//制单人creator
	        .append("  ?, ")//凭证状态status
	        .append("  ?, ")//凭证发生日期generateDate
	        .append("  ?, ")//导入财务系统状态eas_flag
	        .append("  ?, ")//业务模块modleName
	        .append("  ?, ")//合同号contractId
	        .append("  ? ")//凭证备注memo1
	        .append(" ) ");
    	//凭证头JDBC持久化操作
    	this.getBussinessDao().getJdbcTemplate().update(sql.toString(),uuId,voucherNumber,companyNumber,bookedDate,bizDate,periodYear,periodNumber,voucherType,creator,status,generateDate,eas_flag,modleName,contractId,memo1);
	}
	
	
	/**
	 * 
	  * <p>凭证头批量持久化方法(批量请使用该方法)。</p>
	  * <p>根据接口方法getAllIntereasVoucherHead可以生成一个凭证头的对象。</p>
	  * <p>所有凭证头对象封装成LIST集合批量持久化使用该方法进行持久化操作。</p>
	  * @author sea
	  * @param List<IntereasVoucherHead> head 多个凭证头对象合计
	  * @throws DataAccessException
	  * @throws Exception
	 */
	public void saveHeads(List<IntereasVoucherHead> head) throws DataAccessException, Exception
	{
		  StringBuffer sql = new StringBuffer();	
		  final List<IntereasVoucherHead> tempHead = head;
		  //构建执行SQL
		  sql.append(" insert into  intereas_voucherhead ")
	        .append(" ( ")
	        .append(" ID,voucherNumber,companyNumber,bookedDate,bizDate, ")
	        .append(" periodYear,periodNumber,voucherType,creator,status_, ")
	        .append(" generate_date,eas_flag,modleName,contract_id,memo1 ")
	        .append(" ) values ( ")
	        .append(" ?,?,?,?,?, ")
	        .append(" ?,?,?,?,?, ")
	        .append(" ?,?,?,?,? ")
	        .append(" ) ");
	        
		  this.getBussinessDao().getJdbcTemplate().batchUpdate(
				  sql.toString(), new BatchPreparedStatementSetter(){	
			  	   //该方法会自动通过i遍历List 取出相应的增删改数据
				   public void setValues(PreparedStatement ps,int i)throws SQLException
				   {
					   //取值
					   String ID = tempHead.get(i).getId();//UUID 主键 非空
					   String voucherNumber = tempHead.get(i).getVoucherNumber();//凭证号
					   String companyNumber = tempHead.get(i).getCompanyNumber().getId();//公司编码
					   String bookedDate =  tempHead.get(i).getBookedDate();//会计记帐日期
					   String bizDate = tempHead.get(i).getBizDate();//业务日期
					   
					   String periodYear = tempHead.get(i).getPeriodYear();//会计期间-年 
					   String periodNumber = tempHead.get(i).getPeriodNumber();//会计期间-月
					   String voucherType = tempHead.get(i).getVoucherType().getName();//凭证字（凭证类型）
					   String creator = tempHead.get(i).getCreator().getId();//创建人对应的UUID
					   String status_ = tempHead.get(i).getStatus();//凭证状态
					   
					   String generate_date = tempHead.get(i).getGenerate_date();//凭证发生日期（系统当前日期）
					   String eas_flag  = tempHead.get(i).getEas_flag();//导入财务系统状态 默认0
					   String modleName = tempHead.get(i).getModleName();//业务模块
					   String contractId  = tempHead.get(i).getContract_id().getId();//租赁合同号
					   String memo1  = tempHead.get(i).getMemo1();//凭证备注 可以为空
					   log.debug(1+"-个凭证头-凭证号:"+voucherNumber);
					   //装值
					   ps.setString(1, ID);
					   ps.setString(2, voucherNumber);
					   ps.setString(3, companyNumber);
					   ps.setString(4, bookedDate);
					   ps.setString(5, bizDate);
					   
					   ps.setString(6, periodYear);
					   ps.setString(7, periodNumber);
					   ps.setString(8, voucherType);
					   ps.setString(9, creator);
					   ps.setString(10,status_);
					   
					   ps.setString(11, generate_date);
					   ps.setString(12, eas_flag);
					   ps.setString(13, modleName);
					   ps.setString(14, contractId);
					   ps.setString(15, memo1);
				   }
				   
				   //返回批量条数 也就是执行多少次增删改操作
				   public int getBatchSize()
				   {
					   return tempHead.size();
				   }
			 }
		  );
		  log.debug("批量持久化凭证头操作完成!");
	}
	
	/*
	 * @author sea
	 */
	public IntereasVoucherHead saveAllIntereasVoucherHead(IntereasVoucherHead intereasVoucherhead,ContractInfo contractInfo,User user) throws DataAccessException, Exception{
		log.debug("构建凭证头对象start:");
		//接参数
    	String voucherNumber = intereasVoucherhead.getVoucherNumber();//凭证号
    	log.debug("本次凭证凭证号："+voucherNumber);
    	//公司编码
    	InterOrgCode interOrgCode = intereasVoucherhead.getCompanyNumber();//this.getCompanyNumber(intereasVoucherhead.getBelong_unit());
    	//业务日期
    	String bizDate = Tools.getDBDateStr( intereasVoucherhead.getBizDate() );
    	//(根据业务发生日期通过函数getAccountDay获取)
    	String bookedDate =  Tools.getDBDateStr(intereasVoucherhead.getBookedDate() ) ;//会计记帐日期
    	String periodYear = "";//会计期间-年 
		String periodNumber = "";//会计期间-月
    	String voucherType = intereasVoucherhead.getVoucherType().getName();//凭证字（凭证类型）
    	String status = "已完整";//凭证状态
    	String generate_date  = intereasVoucherhead.getGenerate_date();//凭证发生日期（系统当前日期）
    	String eas_flag  = "0";//导入财务系统状态
    	String modleName = intereasVoucherhead.getModleName();//业务模块
    	String memo1  = intereasVoucherhead.getMemo1();//凭证备注 可以为空
    	String uuId = intereasVoucherhead.getId();//UUID 主键
//    	if(Tools.isNullOrEmpty(uuId)){
//    		uuId = UUIDUtil.getUUID();
//    	}
		if(Tools.isNullOrEmpty(bookedDate)){
			//根据业务操作日期查询财务对账日期
			bookedDate = this.getBookDate(bizDate);
    	}
		periodYear = bookedDate.substring(0,4);//会计期间-年 
		periodNumber = bookedDate.substring(5,7);//会计期间-月
    	
		
    	//封装对象
    	IntereasVoucherHead obj = new IntereasVoucherHead();
    	obj.setId(uuId);
    	obj.setVoucherNumber(voucherNumber);////凭证号
    	obj.setCompanyNumber(interOrgCode);//
    	obj.setBookedDate(bookedDate);//
    	obj.setBizDate(bizDate);//
    	
    	obj.setPeriodYear(periodYear);//
    	obj.setPeriodNumber(periodNumber);//
    	obj.setVoucherType(intereasVoucherhead.getVoucherType());//
    	obj.setCreator(user);//
    	obj.setStatus(status);//
    	
    	obj.setGenerate_date(generate_date);//
    	obj.setEas_flag(eas_flag);//
    	obj.setModleName(modleName);//
    	obj.setContract_id(contractInfo);//
    	obj.setMemo1(memo1);//
    	
    	obj.setBelong_unit(interOrgCode.getOrgName());
    	
    	log.debug("构建凭证头对象end");
    	return obj;
	}
	
	/*
	 * (non-JAVADOC)
	 * @see com.business.service.voucher.Intereas_Voucherhead_Service#getBookDate(java.lang.String)
	 */
	public String getBookDate(String bizDate){
		String bookDate = "";
		String sql = " select  to_char(getAccountDay('"+bizDate+"'),'YYYY-MM-DD') as bookDate from  dual ";
		log.debug("根据业务操作日期查询财务对账日期:"+sql);
		try {
			bookDate =   (String) this.getBussinessDao().getJdbcTemplate().queryForMap(sql).get("bookDate");
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return bookDate;
	}
	
	/*
	 * @author sea
	 */
	public InterOrgCode getCompanyNumber(String uuId) throws DataAccessException, Exception{
		//方式1：String companyNumber = "";
		//String sql = " select * from   inter_orgcode where id = '"+uuId+"' ";
		//方式2：公司编码 /公司所属编号
		InterOrgCode interOrgCode = new InterOrgCode();
		interOrgCode.setOrgCode(uuId);
		interOrgCode = this.getBussinessDao().findEntityByID(InterOrgCode.class, uuId);
		log.debug("获取租赁公司编码:"+interOrgCode.getOrgCode());
		return interOrgCode;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.business.service.voucher.Intereas_Voucherhead_Service#update_Status(java.lang.String)
	 */
	public void updateStatus(String voucherNumber) throws DataAccessException, Exception{
		String sql = " update  intereas_voucherhead set status = ? where voucherNumber = ?";
		this.getBussinessDao().getJdbcTemplate().update(sql,"未完整",voucherNumber);
	}
	
	/*
	 * @author sea
	 */
	public void updateStatusEnd(String voucherNumber) throws DataAccessException, Exception{
		String sql = " update  intereas_voucherhead set status = ? where voucherNumber = ? ";
		this.getBussinessDao().getJdbcTemplate().update(sql,"已完整",voucherNumber);
	}
	
	public void updateEasStatus(String voucherNumber,String eas_flag,String eas_memo) throws DataAccessException, Exception{
		//eas_flag,eas_memo,
		String sql = " update  intereas_voucherhead set eas_flag = ? ,eas_memo = ? where voucherNumber = ? ";
		log.info("导入凭证至EAS系统后修改凭证头对应的状态:"+sql);
		this.getBussinessDao().getJdbcTemplate().update(sql,eas_flag,eas_memo,voucherNumber);
	}
	
	/*
	 * @author sea
	 */
	public String getVoucherNumber() throws DataAccessException, Exception{
		String voucherNumber = "";
		String sql = " select evidenceNocreateEAS() as voucherNumber from dual ";
		voucherNumber =   (String) this.getBussinessDao().getJdbcTemplate().queryForMap(sql).get("voucherNumber");
		log.debug("获取凭证号:"+StrTools.logCheck(voucherNumber));
		return voucherNumber;
	}
	
	
}
