package com.tenwa.leasing.serviceImpl.voucher;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
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
import com.tenwa.kernal.utils.UUIDUtil;
import com.tenwa.leasing.entity.base.OwnAccount;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.voucher.IntereasVoucherEntries;
import com.tenwa.leasing.entity.voucher.IntereasVoucherHead;
import com.tenwa.leasing.entity.voucher.IntereasVoucherLog;
import com.tenwa.leasing.entity.voucher.IntereasVoucherasStacts;
import com.tenwa.leasing.entity.voucher.VoucherassStactsInfo;
import com.tenwa.leasing.service.voucher.IntereasVoucherasstactsService;

/**
 * 
 * <p>辅助账服务接口实现。</p>
 * <p>2013-10-17</p>
 * @author sea
 * @version 4.5
 */
@Service(value="intereasVoucherasstactsService")
public class IntereasVoucherasstactsServiceImpl extends AbstractBaseServiceImpl  implements IntereasVoucherasstactsService {
	
	/**
	 * 记录log4j
	 */
	private Logger log = Logger.getLogger(IntereasVoucherasstactsServiceImpl.class);
	/**
	 * 持久化服务
	 */
	@Resource
	private BaseDao baseDao;
	
	@Override
	public BaseDao getBussinessDao() throws Exception {
		return baseDao;
	}
	/**
	  * 
	  * <p>辅助账信息持久化操作。</p>
	  * @author sea
	 * @param intereas_voucherasstacts 辅助账部分必填信息entity集合
	 * @param voucherassStactsInfo 完整的辅助账类型对象
	 * @param custInfo   客户信息entity   
	 * @param intereasVoucherentries   凭证体entity
	 * @param intereasVoucherhead      凭证头entity
	 * @param user      当前登录人信息entity
	  * @return
	  * @throws Exception
	 */
	public IntereasVoucherasStacts saveIntereasVoucherasstacts(IntereasVoucherasStacts intereas_voucherasstacts,VoucherassStactsInfo voucherassStactsInfo,CustInfo custInfo,IntereasVoucherEntries intereasVoucherentries,IntereasVoucherHead intereasVoucherhead,User user) throws Exception {
		// 判断参数是否为空或者''   
		String message = "";
		IntereasVoucherasStacts allIntereasVoucherasStacts;
        if (intereas_voucherasstacts == null || "".equals(intereas_voucherasstacts)) {  
        	message = "传入辅助账对象参数为空无法做生成凭证体操作";
        	log.error(message);
        	return null;
        }else{
        	//构建完整的辅助账对象
        	allIntereasVoucherasStacts =  this.getAllIntereasVoucherasstacts(intereas_voucherasstacts, voucherassStactsInfo, custInfo, intereasVoucherentries, intereasVoucherhead);
        	//方式1：
        	this.getBussinessDao().saveEntity(allIntereasVoucherasStacts);
        	
        	Map<String,String> map = this.getSql(intereas_voucherasstacts, voucherassStactsInfo, custInfo, intereasVoucherentries, intereasVoucherhead);
        	message = map.get("message");
        	log.debug("辅助账校验信息："+message);
        	String sql = map.get("sql");
        	log.debug("辅助账SQL："+sql);
        	//方式2：通过SQL方式更新
			//this.getBussinessDao().getJdbcTemplate().update(sql);
        	String filed = map.get("filed");
        	
        	//凭证头日志字符串
			IntereasVoucherLog obj = new IntereasVoucherLog();
			obj.setMemo(filed);
			obj.setMessage(message);
        	obj.setCreateDate(DateUtil.getSystemDateTime());
        	obj.setCreator(user);
        	this.getBussinessDao().saveEntity(obj);
        }
        log.debug("辅助账持久化信息:"+message);
        return allIntereasVoucherasStacts;
	}
	
	
	/**
	 * 
	 * <p>生产完整的辅助账对象集合。</p>
	 * @author sea
	 * @param intereas_voucherasstacts 辅助账部分必填信息entity集合
	 * @param allVoucherassStactsInfo  完整的辅助账类型entity
	 * @param custInfo   客户信息entity   
	 * @param intereasVoucherentries   凭证体entity
	 * @param intereasVoucherhead      凭证头entity
	 * @return
	 * @throws Exception 
	 * @throws DataAccessException 
	 */
	public IntereasVoucherasStacts getAllIntereasVoucherasstacts(IntereasVoucherasStacts intereas_voucherasstacts,VoucherassStactsInfo allVoucherassStactsInfo,CustInfo custInfo,IntereasVoucherEntries intereasVoucherentries,IntereasVoucherHead intereasVoucherhead) throws DataAccessException, Exception{
		//取参数
		String uuId = UUIDUtil.getUUID();//UUID 主键
    	//辅助账行号 根据传入凭证体生成对应行号，一个凭证体下的行号是递增模式.例如：凭证体行号为1，则辅助账行号为：1/2/3..,凭证体行号为2，则辅助账行号为：1/2/3...
		int asstSeq = 1;
		@SuppressWarnings("unused")
		String asstActType = allVoucherassStactsInfo.getAsstActType();//辅助帐类型编码
		String asstActTypeName = allVoucherassStactsInfo.getAsstActTypeName();//辅助帐类型名称
		//String cust_id_b = custInfo.getId();//业务客户编号对应的UUID
		String asstActNumber = intereas_voucherasstacts.getAsstActNumber();//辅助账内容
		String entrySeq = intereasVoucherentries.getId();//凭证体行号 取凭证体的行数据对应的UUID
		asstSeq = this.getNumber(entrySeq);
		
    	IntereasVoucherasStacts obj = new IntereasVoucherasStacts();
    	obj.setId(uuId);//UUID
    	obj.setAsstSeq(String.valueOf( asstSeq ));//辅助账行号
    	obj.setAsstActType(allVoucherassStactsInfo);//辅助帐类型编码 
    	obj.setAsstActTypeName(asstActTypeName);//辅助帐类型名称 
    	obj.setAsstActNumber(asstActNumber);//辅助账内容(核算对象编码) 
    	obj.setEntrySeq(intereasVoucherentries);//凭证体行号
    	obj.setCustIdB(custInfo);
    	
		return obj;
	}
	
	/**
	  * <p>产生一个完整辅助账类型为:‘001-客户’,‘002-经销商’的辅助账对象。</p>
	  * @author sea
	  * @param voucherassStactsInfo 完整的辅助账类型编码对象
	  * @param custInfo 完整的客户对象
	  * @param intereasVoucherentries 该辅助账对应的凭证体的完整信息
	  * @param headObj 该凭证体对应的凭证头完整的实体对象信息
	  * @return IntereasVoucherasStacts obj
	 * @throws Exception 
	 * @throws DataAccessException 
	 */
	public IntereasVoucherasStacts importantVoucherasStacts(VoucherassStactsInfo voucherassStactsInfo,CustInfo custInfo,IntereasVoucherEntries intereasVoucherentries,IntereasVoucherHead headObj) throws DataAccessException, Exception{
		
		//辅助账 1  银行账户-辅助账 
		IntereasVoucherasStacts obj = new IntereasVoucherasStacts();//辅助账1
		//辅助账1： 辅助帐类型编码
		obj.setAsstActType(voucherassStactsInfo);
		//辅助账1：辅助账内容 客户情况下应存入客户表对应的UUID
		obj.setAsstActNumber(custInfo.getId());
		//辅助账1：凭证体行号
		obj.setEntrySeq( intereasVoucherentries );
		//辅助账1：辅助账类型为001，这这里存入客户对应数据的UUID
		obj.setCustIdB(custInfo);
		//构建完整的客户辅助账对象
		obj = this.getAllIntereasVoucherasstacts(obj, voucherassStactsInfo, custInfo, intereasVoucherentries, headObj);
		return obj;
	}
	
	/**
	 * 
	 * <p>根据传入参数生成凭证头的SQL以及针对这些参数的校验信息。</p>
	 * @author sea
	 * @param intereas_voucherasstacts 辅助账部分必填信息entity集合
	 * @param allVoucherassStactsInfo  完整的辅助账类型entity
	 * @param custInfo   客户信息entity   
	 * @param intereasVoucherentries   凭证体entity
	 * @param intereasVoucherhead      凭证头entity
	 * @return
	 * @throws Exception 
	 * @throws DataAccessException 
	 */
	private Map<String,String> getSql(IntereasVoucherasStacts intereas_voucherasstacts,VoucherassStactsInfo allVoucherassStactsInfo,CustInfo custInfo,IntereasVoucherEntries intereasVoucherentries,IntereasVoucherHead intereasVoucherhead) throws DataAccessException, Exception{
		Map<String,String> map = new HashMap<String, String>();
		String message = "";
		StringBuffer sql = new StringBuffer();
		StringBuffer filed = new StringBuffer();
		//接参数
		int asstSeq = 1;//辅助账行号 根据传入凭证体生成对应行号，一个凭证体下的行号是递增模式.例如：凭证体行号为1，则辅助账行号为：1/2/3..,凭证体行号为2，则辅助账行号为：1/2/3...
		
		String asstActType = allVoucherassStactsInfo.getAsstActType();//辅助帐类型编码
		String asstActTypeName = allVoucherassStactsInfo.getAsstActTypeName();//辅助帐类型名称
		
		/**
		辅助账内容：辅助帐类型编码(asstActType) 为001时 ，‘辅助账类容’应存入客户编号,
		，然后根据cust_id_b中存入的是客户编号对应的UUID
		*/
		String asstActNumber = intereas_voucherasstacts.getAsstActNumber();//辅助账内容
		String cust_id_b = custInfo.getId();//业务客户编号对应的UUID
		 
		String entrySeq = intereasVoucherentries.getId();//凭证体行号 取凭证体的行号
		asstSeq = this.getNumber(entrySeq);
		
    	//拼装凭证头信息用于存凭证日志记录,做为补凭证的依据记录
    	filed.append("辅助账intereas_voucherasstacts信息如下/")
    		.append("凭证号： "+intereasVoucherhead.getVoucherNumber()+"/")
    		.append("辅助账行号： "+asstSeq+"/")
    		.append("辅助帐类型编码： "+asstActType+"/")
    		.append("辅助帐类型名称： "+asstActTypeName+"/")
    		.append("凭证体行号： "+entrySeq+"/")
    		.append("辅助账内容： "+asstActNumber+"/")
    		.append("业务客户编号： "+cust_id_b+"")
	    	.append(" ");
    	log.debug("辅助账filed:"+filed);
    	
    	//拼接SQL
    	sql.append(" insert into intereas_voucherasstacts ( ")
	        .append("   asstSeq,asstActType,asstActTypeName,cust_id_b,asstActNumber,entrySeq ") 
	        .append("  )values( ") 
	        .append("  '"+asstSeq+"', ")//辅助账行号
	        .append(" '"+asstActType+"', ") //辅助帐类型编码 
	        .append(" '"+asstActTypeName+"', ") //辅助帐类型名称 
	        .append(" '"+cust_id_b+"', ")//业务客户编号
	        .append(" '"+asstActNumber+"', ") //辅助账内容(核算对象编码)  
	        .append(" '"+entrySeq+"' ")//凭证体行号
	        .append("  );");
    	
    	//封装
    	map.put("message", message);
    	map.put("sql", sql.toString());
    	map.put("filed",filed.toString());
		return map;
	}
	
	/*
	 * @author sea
	 */
	public void saveStacts(List<IntereasVoucherasStacts> stacts) throws DataAccessException, Exception
	{
		  //使用完Hibernate持久化数据操作后再同一个事物管理中需要做下FLUSH操作
		  this.baseDao.updateFlush();
		  StringBuffer sql = new StringBuffer();	
		  final List<IntereasVoucherasStacts> tempStacts = stacts;
		  //构建执行SQL
		  sql.append(" insert into intereas_voucherasstacts ( ")
		    .append("  ID,")
	        .append("   asstSeq,asstActType,asstActTypeName, ") 
	        .append("   cust_id_b,asstActNumber,entrySeq ") 
	        .append("  )values( ") 
	        .append(" ?, ")
	        .append(" ?,?,?, ")
	        .append(" ?,?,? ")
	        .append(" ) ");
	        
		  this.getBussinessDao().getJdbcTemplate().batchUpdate(
				  sql.toString(), new BatchPreparedStatementSetter(){	
			  	   //该方法会自动通过i遍历List 取出相应的增删改数据
				   public void setValues(PreparedStatement ps,int i)throws SQLException
				   {
					   //取值
					   String uuId = tempStacts.get(i).getId();//UUID 主键
					   System.out.println("--------------------------------------------------------");
					   System.out.println("辅助账的uuId"+uuId);
					   System.out.println("--------------------------------------------------------");
					   String asstSeq =  tempStacts.get(i).getAsstSeq();//辅助账行号
					   String asstActType =  tempStacts.get(i).getAsstActType().getId();//辅助帐类型编码
					   String asstActTypeNum =  tempStacts.get(i).getAsstActType().getAsstActType();
					   String asstActTypeName =  tempStacts.get(i).getAsstActTypeName();//辅助帐类型名称
					   
					   String asstActNumber =  tempStacts.get(i).getAsstActNumber();//辅助账内容:客户表UUID或者银行账号表UUID
						/**
						辅助账内容：辅助帐类型编码(asstActType) 为01时 ，‘辅助账类容’应存入客户编号,
						，然后根据cust_id_b中存入的也是客户编号对应的UUID
						*/
					   String cust_id_b = "";//
					   if("01".equals(asstActTypeNum) || "02".equals(asstActTypeNum)){//客户  经销商
						   cust_id_b = tempStacts.get(i).getCustIdB().getId();//业务客户编号对应的UUID
					   } 
					   
					   String entrySeq =  tempStacts.get(i).getEntrySeq().getId();//凭证体行号 取凭证体的行号
					   
					   log.debug(1+"-个辅助账-对应凭证体行号UUID:"+entrySeq);
					   //装值
					   ps.setString(1, uuId);
					   
					   ps.setString(2, asstSeq);
					   ps.setString(3, asstActType);
					   ps.setString(4, asstActTypeName);
					   
					   ps.setString(5, cust_id_b);
					   ps.setString(6, asstActNumber);
					   ps.setString(7, entrySeq);
				   }
				   
				   //返回批量条数 也就是执行多少次增删改操作
				   public int getBatchSize()
				   {
					   return tempStacts.size();
				   }
			 }
		  );
		  log.debug("批量持久化辅助账操作完成!");
	}
	
	
	/*
	 * @author sea
	 */
	public VoucherassStactsInfo getVoucherassStactsInfo(String asstActType) throws Exception{
		VoucherassStactsInfo obj = new VoucherassStactsInfo();
		//String sql = " select * from  voucherasstacts_info where asstActType = '"+asstActType+"' ";
		Map<String,Object> map = new HashMap<String, Object>();
		log.error("根据辅助账类型编号查询辅助账类型整个对象，查询条件:"+asstActType);
		map.put("asstActType", asstActType);
		//根据具体的字段条件查询一个entity，条件使用map封装
		obj = this.getBussinessDao().findEntityByProperties(VoucherassStactsInfo.class, map).get(0);
		
		return obj;
	}
	
	/**
	 * 
	 * <p>查询辅助账行号。</p>
	 * <p>根据传入凭证体生成对应行号，一个凭证体下的行号是递增模式.例如：凭证体行号为1，则辅助账行号为：1/2/3..,凭证体行号为2，则辅助账行号为：1/2/3...。</p>
	 * @author sea
	 * @param entrySeq  辅助账中存入的凭证体行号(实际存入的是凭证体表中的UUID)
	 * @return
	 * @throws Exception 
	 * @throws DataAccessException 
	 */
	public int getNumber(String entrySeq) throws DataAccessException, Exception{
		String sql = "   select nvl(max(asstSeq),0)+1 as asstSeq from  intereas_voucherasstacts  where   entrySeq = ? ";
		log.error("查询辅助账行号SQL:"+sql);
		int asstSeq = 1;
		asstSeq = this.getBussinessDao().getJdbcTemplate().queryForInt(sql,entrySeq);
		return asstSeq;
	}
	
	
	/**
	  * <p>产生一个完整辅助账类型为:‘003-银行账户’的辅助账对象。</p>
	  * @author sea
	  * @param voucherassStactsInfo 完整的辅助账类型编码对象
	  * @param ownAccount 完整的本方银行账号信息
	  * @param intereasVoucherentries 该辅助账对应的凭证体的完整信息
	  * @param headObj 该凭证体对应的凭证头完整的实体对象信息
	  * @return IntereasVoucherasStacts obj
	 * @throws Exception 
	 * @throws DataAccessException 
	 */
	public IntereasVoucherasStacts importantVoucherasStacts003(VoucherassStactsInfo voucherassStactsInfo,OwnAccount ownAccount,IntereasVoucherEntries intereasVoucherentries,IntereasVoucherHead headObj) throws DataAccessException, Exception{
		
		//辅助账 1  银行账户-辅助账 
		IntereasVoucherasStacts obj = new IntereasVoucherasStacts();//辅助账1
		//辅助账1： 辅助帐类型编码
		obj.setAsstActType(voucherassStactsInfo);
		//辅助账1：辅助账内容  银行的这里存银行表对应的UUID
		obj.setAsstActNumber(ownAccount.getId());
		//辅助账1：凭证体行号
		obj.setEntrySeq( intereasVoucherentries );
		//构建完整的客户辅助账对象  ，辅助账类型为：‘003-银行账户’情况下，客户不需要来产生
		obj = this.getAllIntereasVoucherasstacts(obj, voucherassStactsInfo, null, intereasVoucherentries, headObj);
		return obj;
	}
	
	/**
	 * <p>产生一个完整辅助账类型为:‘004-物料(合同号)’的辅助账对象。</p>
	 * @author sea
	 * @param voucherassStactsInfo 完整的辅助账类型编码对象
	 * @param contractInfo 完整的合同信息(根据合同号查询)
	 * @param intereasVoucherentries 该辅助账对应的凭证体的完整信息
	 * @param headObj 该凭证体对应的凭证头完整的实体对象信息
	 * @return IntereasVoucherasStacts obj
	 * @throws Exception 
	 * @throws DataAccessException 
	 */
	public IntereasVoucherasStacts importantVoucherasStacts004(VoucherassStactsInfo voucherassStactsInfo,ContractInfo contractInfo,IntereasVoucherEntries intereasVoucherentries,IntereasVoucherHead headObj) throws DataAccessException, Exception{
		
		//辅助账 1  银行账户-辅助账 
		IntereasVoucherasStacts obj = new IntereasVoucherasStacts();//辅助账1
		//辅助账1： 辅助帐类型编码
		obj.setAsstActType(voucherassStactsInfo);
		//辅助账1：辅助账内容
		obj.setAsstActNumber(contractInfo.getId());
		//辅助账1：凭证体行号
		obj.setEntrySeq( intereasVoucherentries );
		//构建完整的客户辅助账对象  ，辅助账类型为：‘003-银行账户’情况下，客户不需要来产生
		obj = this.getAllIntereasVoucherasstacts(obj, voucherassStactsInfo, null, intereasVoucherentries, headObj);
		return obj;
	}
	
	
	
}
