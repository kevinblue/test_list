package com.tenwa.leasing.serviceImpl.voucher;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.UUIDUtil;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.voucher.IntereasVoucherEntries;
import com.tenwa.leasing.entity.voucher.IntereasVoucherHead;
import com.tenwa.leasing.entity.voucher.IntereasVoucherLog;
import com.tenwa.leasing.entity.voucher.VoucherassStactsConfig;
import com.tenwa.leasing.service.voucher.IntereasVoucherentriesService;
import com.tenwa.leasing.utils.MoneyUtils;
import com.tenwa.leasing.utils.Tools;
/**
 * 
 * <p>凭证体服务对应继承实现。</p>
 * <p>2013-10-17</p>
 * @author sea
 * @version 4.5
 */
@Service(value="intereasVoucherentriesService")
public class IntereasVoucherentriesServiceImpl extends AbstractBaseServiceImpl  implements IntereasVoucherentriesService {
	/**
	 * 记录log4j
	 */
	private Logger log = Logger.getLogger(IntereasVoucherentriesServiceImpl.class);
	/**
	 * 持久化服务
	 */
	@Resource
	private BaseDao baseDao;
	
	@Resource(name="tableService")
	private TableService tableService;
	
//	@Resource(name="voucherToEasService")
//	private VoucherToEasService voucherToEasService;
	
	@Override
	public BaseDao getBussinessDao() throws Exception {
		return baseDao;
	}
	
	/*
	 * @author sea
	 */
	public IntereasVoucherEntries saveIntereasVoucherentries(IntereasVoucherEntries intereasVoucherentries,DictionaryData dictionaryData,IntereasVoucherHead intereasVoucherHead,User user) throws Exception {
		// 判断参数是否为空或者''   
		String message = "";
		IntereasVoucherEntries allEntries;
        if (intereasVoucherentries == null || "".equals(intereasVoucherentries)) {  
        	message = "传入凭证体对象参数为空无法做生成凭证体操作";
        	log.error(message);
        	return null;
        }else{
        	//方式1：获取完整的凭证体对象，通过对象更新
        	allEntries = this.getALLIntereasVoucherEntries(intereasVoucherentries, dictionaryData, intereasVoucherHead);
        	//更新实体
        	this.getBussinessDao().saveEntity(allEntries);
        	
        	Map<String,String> map = this.getSql(intereasVoucherentries, dictionaryData, intereasVoucherHead);
        	message = map.get("message");
        	log.debug("凭证体校验信息："+message);
        	String sql = map.get("sql");
        	log.debug("凭证体SQL："+sql);
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
        log.debug("凭证体持久化信息:"+message);
        return allEntries;
	}
	
	/*
	 * @author sea
	 */
	public IntereasVoucherEntries saveIntereasVoucherentriesJdbc(IntereasVoucherEntries intereasVoucherentries,DictionaryData dictionaryData,IntereasVoucherHead intereasVoucherHead,User user) throws Exception {
		// 判断参数是否为空或者''   
		String message = "";
		IntereasVoucherEntries allEntries;
		if (intereasVoucherentries == null || "".equals(intereasVoucherentries)) {  
			message = "传入凭证体对象参数为空无法做生成凭证体操作";
			log.error(message);
			return null;
		}else{
			//获取完整的凭证体对象，通过对象更新
			allEntries = this.getALLIntereasVoucherEntries(intereasVoucherentries, dictionaryData, intereasVoucherHead);
			
			Map<String,String> map = this.getSql(intereasVoucherentries, dictionaryData, intereasVoucherHead);
			message = map.get("message");
			log.debug("凭证体校验信息："+message);
			String sql = map.get("sql");
			log.debug("凭证体SQL："+sql);
			//方式2：通过SQL方式更新
			this.getBussinessDao().getJdbcTemplate().update(sql);
			
			String filed = map.get("filed");
			//凭证头日志字符串
			IntereasVoucherLog obj = new IntereasVoucherLog();
			obj.setMemo(filed);
			obj.setMessage(message);
			obj.setCreateDate(DateUtil.getSystemDateTime());
			obj.setCreator(user);
			this.getBussinessDao().saveEntity(obj);
		}
		log.debug("凭证体持久化信息:"+message);
		return allEntries;
	}
	
	/**
	 * 
	 * <p>根据传入参数生成凭证头的SQL以及针对这些参数的校验信息。</p>
	 * @author sea
	 * @param intereasVoucherhead
	 * @return
	 */
	private Map<String,String> getSql(IntereasVoucherEntries intereasVoucherentries,DictionaryData dictionaryData,IntereasVoucherHead intereasVoucherHead){
		HashMap<String,String> map = new HashMap<String, String>();
		String message = "";
		StringBuffer sql = new StringBuffer();
		StringBuffer filed = new StringBuffer();
		//接参数
		String uuId = intereasVoucherentries.getId();//UUID 主键
    	if(Tools.isNullOrEmpty(uuId)){
    		uuId = UUIDUtil.getUUID();
    	}
		String entrySeq = intereasVoucherentries.getEntrySeq();//分录行号
		String voucherAbstract = intereasVoucherentries.getVoucherAbstract();//摘要
		String accountNumber = intereasVoucherentries.getAccountNumber().getSubjectsCode();//科目编码
		Integer entryDC = intereasVoucherentries.getEntryDC();//方向 （1 借方-1 贷方）
		//数据字典实体 t_dicts_datas
		String currencyNumber = dictionaryData.getDescription();//币别编号
		BigDecimal originalAmount =  intereasVoucherentries.getOriginalAmount();//借贷金额(原币金额)
		BigDecimal debitAmount = new BigDecimal("0.00");//借方金额    借方为1就=原币金额,贷方为-1就为0
		if(entryDC.equals("1")){
			debitAmount = originalAmount;
		}
		BigDecimal creditAmount = new BigDecimal("0.00");//贷方金额  借方为1就=0,贷方为-1就为原币金额
		if(entryDC.equals("-1")){
			creditAmount = originalAmount;
		}
		String voucherNumber = intereasVoucherHead.getId();//凭证号
		//判断值
    	if(Tools.isNullOrEmpty(voucherNumber)){
    		message = message + "凭证号获取失败!";
    	}
    	if(Tools.isNullOrEmpty(entrySeq)){
    		message = message + "分录行号获取失败!";
    	}
    	if(Tools.isNullOrEmpty(voucherAbstract)){
    		message = message + "摘要获取失败!";
    	}
    	if(Tools.isNullOrEmpty(accountNumber)){
    		message = message + "科目编码获取失败!";
    	}
    	if(Tools.isNullOrEmpty(entryDC)){
    		message = message + "方向 （1 借方-1 贷方）编码获取失败!";
    	}
    	if(Tools.isNullOrEmpty(currencyNumber)){
    		message = message + "币别获取失败!";
    		currencyNumber = "currency_type1";
    	}
    	
    	if(Tools.isNullOrEmpty(originalAmount)){
    		message = message + "借贷金额获取失败!";
    	}
    	//币种类别
    	currencyNumber = dictionaryData.getDescription();
		
    	//拼装凭证头信息用于存凭证日志记录,做为补凭证的依据记录
    	filed.append("凭证体intereas_voucherentries信息如下/")
    		.append("凭证号： "+voucherNumber+"/")
    		.append("分录行号： "+entrySeq+"/")
    		.append("摘要： "+voucherAbstract+"/")
    		.append("科目编码： "+accountNumber+"/")
    		.append("方向 （1 借方-1 贷方）： "+entryDC+"/")
    		.append("币别： "+entryDC+"/")
    		.append("借贷金额： "+MoneyUtils.formatNumberDoubleTwo( MoneyUtils.getZeroStr( String.valueOf( originalAmount ) ) )+"/")
    		.append("借方金额 分录行号为1就=原币金额 分录行号为2就为0： "+MoneyUtils.formatNumberDoubleTwo( MoneyUtils.getZeroStr( String.valueOf( debitAmount ) ) )+"/")
    		.append("贷方金额  分录行号为2就=原币金额 分录行号为1就为0： "+MoneyUtils.formatNumberDoubleTwo( MoneyUtils.getZeroStr( String.valueOf( creditAmount ) ) )+"")
	    	.append(" ");
    	log.debug("凭证体filed:"+filed);
    	
    	//拼接SQL
    	sql.append(" insert into  intereas_voucherentries ( ")
	        .append(" ID,voucherNumber,entrySeq,voucherAbstract,accountNumber,entryDC,")
	        .append(" currencyNumber,originalAmount,debitAmount,creditAmount  ")
	        .append(" )values( ")
	        .append(" '"+uuId+"', ")
	        .append(" '"+voucherNumber+"','"+entrySeq+"', ")//分录行号
	        .append(" '"+voucherAbstract+"',")//摘要
	        .append(" '"+accountNumber+"',")//科目编码
	        .append(" '"+entryDC+"', ")//方向 （1 借方-1 贷方）
	        .append(" '"+currencyNumber+"', ")//币别
	        .append(" '"+MoneyUtils.formatNumberDoubleTwo( MoneyUtils.getZeroStr( String.valueOf( originalAmount ) ) )+"', ")//借贷金额
	        .append(" '"+MoneyUtils.formatNumberDoubleTwo( MoneyUtils.getZeroStr( String.valueOf( debitAmount ) ) )+"', ")//借方金额 分录行号为1就=原币金额 分录行号为2就为0
	        .append(" '"+MoneyUtils.formatNumberDoubleTwo( MoneyUtils.getZeroStr( String.valueOf( creditAmount ) ) )+"' ")//贷方金额  分录行号为2就=原币金额 分录行号为1就为0
	        .append(" ); ");
    	//封装
    	map.put("message", message);
    	map.put("sql", sql.toString());
    	map.put("filed",filed.toString());
		return map;
	}
	
	/*
	 * @author sea
	 */
	public void saveEntries(List<IntereasVoucherEntries> entries) throws DataAccessException, Exception
	{
		  //使用完Hibernate持久化数据操作后再同一个事物管理中需要做下FLUSH操作
		  this.baseDao.updateFlush();
		  StringBuffer sql = new StringBuffer();	
		  final List<IntereasVoucherEntries> tempEntries = entries;
		  //构建执行SQL
		  sql.append(" insert into  intereas_voucherentries ( ")
	        .append(" ID,voucherNumber,entrySeq,voucherAbstract,accountNumber,")
	        .append(" entryDC,currencyNumber,originalAmount,debitAmount,creditAmount,  ")
	        //现金流量标记 ITEM_FLAG 
	        //本方分录号 LOCAL_ACCOUNT_SEQ 
	        //对方分录号 OPP_ACCOUNT_SEQ
	        //主表信息 PRIMARY_ITEM  
	        //附表信息 SUPPLY_ITEM
	        .append(" ITEM_FLAG,LOCAL_ACCOUNT_SEQ,OPP_ACCOUNT_SEQ,PRIMARY_ITEM,SUPPLY_ITEM, ")
	        //原币 CASHFLOW_AMOUNT_ORIGINAL
	        //本位币 CASHFLOW_AMOUNT_LOCAL
	        //报告币 CASHFLOW_AMOUNT_RPT
	        //主表金额系数 PRIMARY_COEF
	        //附表金额系数 SUPPLY_COEF
	        .append(" CASHFLOW_AMOUNT_ORIGINAL,CASHFLOW_AMOUNT_LOCAL,CASHFLOW_AMOUNT_RPT,PRIMARY_COEF,SUPPLY_COEF, ")
	        //性质 CASHFLOW_PROPERTIES
	        .append(" CASHFLOW_PROPERTIES ")
	        .append(" )values( ")
	        .append(" ?,?,?,?,?, ")
	        .append(" ?,?,?,?,?, ")
	        .append(" ?,?,?,?,?, ")
	        .append(" ?,?,?,?,?, ")
	        .append(" ? ")
	        .append(" ) ");
		  
//		    查询对应的关联关系
//		  select * from user_constraints 
//		  left join user_cons_columns on user_cons_columns.constraint_name = user_constraints.constraint_name
//		  where user_constraints.constraint_name='FK45607DE660FE33E4'
			  
		  //取的数据源
		  //Connection conn = this.getBussinessDao().getJdbcTemplate().getDataSource().getConnection();
		  
		  this.getBussinessDao().getJdbcTemplate().batchUpdate(
				  sql.toString(), new BatchPreparedStatementSetter(){	
			  	   //该方法会自动通过i遍历List 取出相应的增删改数据
				   public void setValues(PreparedStatement ps,int i)throws SQLException
				   {
					   //取值
					   String uuId = tempEntries.get(i).getId();//UUID 主键
					   String voucherNumber = tempEntries.get(i).getVoucherNumber().getId();//凭证号，存入的是凭证头的UUID
					   String entrySeq = tempEntries.get(i).getEntrySeq();//分录行号
					   String voucherAbstract = tempEntries.get(i).getVoucherAbstract();//摘要
					   String accountNumber = tempEntries.get(i).getAccountNumber().getId();//科目编码
					   
					   Integer entryDC = tempEntries.get(i).getEntryDC();//方向 （1 借方-1 贷方）
					   String currencyNumber = tempEntries.get(i).getCurrencyNumber().getId();//币别编号
					   BigDecimal originalAmount =  tempEntries.get(i).getOriginalAmount();//借贷金额(原币金额)
					   BigDecimal debitAmount = tempEntries.get(i).getDebitAmount();//借方金额    借方为1就=原币金额,贷方为-1就为0
					   BigDecimal creditAmount = tempEntries.get(i).getCreditAmount();//贷方金额  借方为1就=0,贷方为-1就为原币金额
					   /*****************现金流******************/
					   int itemflag = tempEntries.get(i).getItemflag();//1情况下方字段请填写，0情况下方这10个值都为空
					   int localAccountSeq = 0;//借是1，贷是2 
					   int oppAccountSeq = 0;//借是1，贷是2 
					   String primaryItem = "";//对方分录号是1时：01.01.01；对方分录号是2时：01.02.01
					   String supplyItem = "";
					   BigDecimal cashflowAmountOriginal = new BigDecimal("0");//取银行数
					   BigDecimal cashflowAmountLocal = new BigDecimal("0");//取银行数
					   BigDecimal cashflowAmountRpt = new BigDecimal("0");//取银行数
					   int primaryCoef = 0;//默认值1
					   int supplyCoef = 0;//默认值0
					   String cashflowProperties = "";//取汉字 “外部”
					   if(itemflag == 1){
						    localAccountSeq = tempEntries.get(i).getLocalAccountSeq();//借是1，贷是2 
						    oppAccountSeq = tempEntries.get(i).getOppAccountSeq();//借是1，贷是2 
						    primaryItem = tempEntries.get(i).getPrimaryItem();//对方分录号是1时：01.01.01；对方分录号是2时：01.02.01
						    supplyItem = tempEntries.get(i).getSupplyItem();
						    cashflowAmountOriginal = tempEntries.get(i).getCashflowAmountOriginal();//取银行数
						    cashflowAmountLocal = tempEntries.get(i).getCashflowAmountLocal();//取银行数
						    cashflowAmountRpt = tempEntries.get(i).getCashflowAmountRpt();//取银行数
						    primaryCoef = tempEntries.get(i).getPrimaryCoef();//默认值1
						    supplyCoef = tempEntries.get(i).getSupplyCoef();//默认值0
						    cashflowProperties = tempEntries.get(i).getCashflowProperties();//取汉字 “外部”
					   }else{//为空情况
						    localAccountSeq = 0;//借是1，贷是2 
						    oppAccountSeq = 0;//借是1，贷是2 
						    primaryItem = "";//对方分录号是1时：01.01.01；对方分录号是2时：01.02.01
						    supplyItem = "";
						    cashflowAmountOriginal = new BigDecimal("0");//取银行数
						    cashflowAmountLocal = new BigDecimal("0");//取银行数
						    cashflowAmountRpt = new BigDecimal("0");//取银行数
						    primaryCoef = 0;//默认值1
						    supplyCoef = 0;//默认值0
						    cashflowProperties = "现金流为空情况";//取汉字 “外部”
					   }
					   /*****************现金流******************/
					   log.debug(1+"-个凭证体-凭证号:"+voucherNumber);
					   //装值
					   ps.setString(1, uuId);
					   ps.setString(2, voucherNumber);
					   ps.setString(3, entrySeq);
					   ps.setString(4, voucherAbstract);
					   ps.setString(5, accountNumber);
					   
					   ps.setInt(6, entryDC);
					   ps.setString(7, currencyNumber);
					   ps.setBigDecimal(8, originalAmount);
					   ps.setBigDecimal(9, debitAmount);
					   ps.setBigDecimal(10,creditAmount);
					   
					   /*****************现金流******************/
					   ps.setInt(11,itemflag);
					   ps.setInt(12,localAccountSeq);
					   ps.setInt(13,oppAccountSeq);
					   ps.setString(14,primaryItem);
					   ps.setString(15,supplyItem);
					   ps.setBigDecimal(16,cashflowAmountOriginal);
					   ps.setBigDecimal(17,cashflowAmountLocal);
					   ps.setBigDecimal(18,cashflowAmountRpt);
					   ps.setInt(19,primaryCoef);
					   ps.setInt(20,supplyCoef);
					   ps.setString(21,cashflowProperties);
					   /*****************现金流******************/
				   }
				   
				   //返回批量条数 也就是执行多少次增删改操作
				   public int getBatchSize()
				   {
					   return tempEntries.size();
				   }
			 }
		  );
		  log.debug("批量持久化凭证体操作完成!");
	}
	
	/*
	 * @author sea
	 */
	public IntereasVoucherEntries getALLIntereasVoucherEntries(IntereasVoucherEntries intereasVoucherentries,DictionaryData dictionaryData,IntereasVoucherHead intereasVoucherHead){
		IntereasVoucherEntries obj = new IntereasVoucherEntries();
		//取参数
		String uuId = intereasVoucherentries.getId();//UUID 主键
    	if(Tools.isNullOrEmpty(uuId)){
    		uuId = UUIDUtil.getUUID();
    	}
		String entrySeq = intereasVoucherentries.getEntrySeq();//分录行号
		String voucherAbstract = intereasVoucherentries.getVoucherAbstract();//摘要
		String accountNumber = intereasVoucherentries.getAccountNumber().getSubjectsCode();//科目编码
		//科目编码 做为对象配置进入
		VoucherassStactsConfig  stactsConfig = intereasVoucherentries.getAccountNumber();
		
		Integer entryDC = intereasVoucherentries.getEntryDC();//方向 （1 借方-1 贷方）
		BigDecimal originalAmount =  intereasVoucherentries.getOriginalAmount();//借贷金额(原币金额)
		BigDecimal debitAmount = new BigDecimal("0.00");//借方金额    借方为1就=原币金额,贷方为-1就为0
		if(entryDC == 1){
			debitAmount = originalAmount;
		}
		BigDecimal creditAmount = new BigDecimal("0.00");//贷方金额  借方为1就=0,贷方为-1就为原币金额
		if(entryDC == -1){
			creditAmount = originalAmount;
		}
		 //封装
		obj.setId(uuId);
		obj.setVoucherNumber(intereasVoucherHead);
		obj.setEntrySeq(entrySeq);
		obj.setVoucherAbstract(voucherAbstract);
		obj.setAccountNumber(stactsConfig);
		
		obj.setEntryDC(entryDC);
		obj.setCurrencyNumber(dictionaryData);//币别编号
		obj.setOriginalAmount(originalAmount);
		obj.setDebitAmount(debitAmount);
		obj.setCreditAmount(creditAmount);
		
		/*****************现金流******************/
		obj.setItemflag(intereasVoucherentries.getItemflag());//现金流量标记 ITEM_FLAG 1情况下方字段请填写，0情况下方这10个值都为空
		if(intereasVoucherentries.getItemflag() == 1){
			/*	int localAccountSeq = intereasVoucherentries.getLocalAccountSeq();//本方分录号 借是1，贷是2 
				int oppAccountSeq = intereasVoucherentries.getOppAccountSeq();//对方分录号 借是1，贷是2
				String primaryItem = intereasVoucherentries.getPrimaryItem();//对方分录号是1时：01.01.01；对方分录号是2时：01.02.01
				if(entryDC == 1){
					localAccountSeq = 1;
					oppAccountSeq = 1;
					primaryItem = "01.01.01";
				}
				if(entryDC == -1){
					localAccountSeq = 2;
					oppAccountSeq = 2;
					primaryItem = "01.02.01";
				}*/
				obj.setLocalAccountSeq(intereasVoucherentries.getLocalAccountSeq());//本方分录号 借是1，贷是2 
				obj.setOppAccountSeq(intereasVoucherentries.getOppAccountSeq());//对方分录号 借是1，贷是2
				obj.setPrimaryItem(intereasVoucherentries.getPrimaryItem());//主表信息 对方分录号是1时：01.01.01；对方分录号是2时：01.02.01
				
				obj.setSupplyItem(intereasVoucherentries.getSupplyItem());//附表信息
				obj.setCashflowAmountOriginal(intereasVoucherentries.getCashflowAmountOriginal());//原币
				obj.setCashflowAmountLocal(intereasVoucherentries.getCashflowAmountLocal());//本位币
				obj.setCashflowAmountRpt(intereasVoucherentries.getCashflowAmountRpt());//报告币
				int primaryCoef = intereasVoucherentries.getPrimaryCoef();
				if(Tools.isNullOrEmpty(primaryCoef) || primaryCoef != 1){
					primaryCoef = 1;
				}
				obj.setPrimaryCoef(primaryCoef);//主表金额系数默认值1
				//附表金额系数默认值0
				int supplyCoef = intereasVoucherentries.getPrimaryCoef();
				if(Tools.isNullOrEmpty(supplyCoef) || supplyCoef != 0){
					supplyCoef = 0;
				}
				obj.setSupplyCoef(supplyCoef);
				String cashflowProperties = intereasVoucherentries.getCashflowProperties();
				if(Tools.isNullOrEmpty(cashflowProperties)){
					cashflowProperties = "性质";
				}
				obj.setCashflowProperties(cashflowProperties);//取汉字 “外部”
		}
		/*****************现金流******************/
		return obj;
	}
	
	/*
	 * 借方凭证
	 * @author sea
	 */
	public IntereasVoucherEntries importantVoucherasEntries(DictionaryData dictionaryData,int num_entrySeq,String voucherAbstract,IntereasVoucherHead headObj,VoucherassStactsConfig accountNumber,BigDecimal originalAmount,Integer entryDC){
		if(entryDC <= 0){
			log.debug("贷方凭证体产生!");
		}else{
			log.debug("借方凭证体产生!");
		}
		//凭证体
		IntereasVoucherEntries obj = new IntereasVoucherEntries();//体
		obj.setVoucherAbstract(voucherAbstract);
		//凭证体：分录行号
		obj.setEntrySeq(String.valueOf(num_entrySeq));
		//凭证体： 科目编码 
		obj.setAccountNumber(accountNumber);
		//凭证体： 方向 （1 借方-1 贷方）
		obj.setEntryDC(entryDC);
		//凭证体：币别
		obj.setCurrencyNumber(dictionaryData);
		//凭证体：借贷金额
		obj.setOriginalAmount( originalAmount );
		//构建一个完整的借方凭证体对象
		obj = this.getALLIntereasVoucherEntries(obj, dictionaryData, headObj);
		
		return obj;
	}
	
	
	/*
	 * @author sea
	 */
	public DictionaryData getCurrencyNumberOne(DictionaryData dictionarydata) throws Exception{
		//String sql = " select * from t_dicts_datas  where code_ = '"+currency_type+"';";
		//log.debug("查询凭证币别编号："+sql);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("code", dictionarydata.getCode());
		log.debug("code:"+dictionarydata.getCode());
		//map.put("name_", dictionarydata.getName());
		//根据具体的字段条件查询一个entity，条件使用map封装
		DictionaryData obj = this.getBussinessDao().findEntityByProperties(DictionaryData.class, map).get(0);
		
		return obj;
	}
	
	/**
	 * 
	 * <p>查询凭证体行号。</p>
	 * <p>根据传入凭证号，一个凭证头下的凭证体行号是递增模式.例如：凭证头为1，则凭证体行号为：1/2/3..,凭证体头为2，则凭证体行号为：1/2/3...。</p>
	 * @author sea
	 * @param voucherNumber  凭证体号
	 * @return entrySeq 凭证体行号
	 * @throws Exception 
	 * @throws DataAccessException 
	 */
	public int getNumber(String voucherNumber) throws DataAccessException, Exception{
		String sql = "   select nvl(max(entrySeq),0)+1 as entrySeq from  intereas_voucherentries  where   voucherNumber = '"+voucherNumber+"' ";
		log.error("查询凭证体行号SQL:"+sql);
		int entrySeq = 1;
		entrySeq = this.getBussinessDao().getJdbcTemplate().queryForInt(sql);
		return entrySeq;
	}
	
	/*
	 * @author sea
	 */
	public CustInfo serchCustInfo(String cust_number) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("custNumber", cust_number);
		//根据具体的字段条件查询一个entity，条件使用map封装
		CustInfo obj = this.getBussinessDao().findEntityByProperties(CustInfo.class, map).get(0);
		return obj;
	}

	@Override
	public String saveToEas(Map<String, String> model) throws Exception {
		
		String jsonStr = tableService.getTableJsonData(model.get("url"), model);
		JSONObject jsonObject = new JSONObject(jsonStr);
		
		JSONArray jsonArray = (JSONArray) jsonObject.opt("datas");
		List<IntereasVoucherHead> intereasVoucherHeads = new ArrayList<IntereasVoucherHead>();
		
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject IntereasVoucherJson  = (JSONObject) jsonArray.opt(i);
			String id = IntereasVoucherJson.optString("id");
			IntereasVoucherHead  intereasVoucherHead= this.tableService.findEntityByID(IntereasVoucherHead.class, id);
			intereasVoucherHeads.add(intereasVoucherHead);
		}
		
		//voucherToEasService.saveVoucherByList(intereasVoucherHeads);
		return null;
	}
	
	
	
}
