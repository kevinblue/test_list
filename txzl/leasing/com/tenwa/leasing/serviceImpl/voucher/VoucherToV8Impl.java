package com.tenwa.leasing.serviceImpl.voucher;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Service;

import com.reckon.util.DateUtils;
import com.reckon.util.MathExtend;
import com.reckon.util.NumberUtils;
import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.entity.Department;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.kernal.utils.UUIDUtil;
import com.tenwa.leasing.entity.base.OwnAccount;
import com.tenwa.leasing.entity.base.OwnInfo;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.voucher.CustOtherNumber;
import com.tenwa.leasing.entity.voucher.InterEvidenceInfo;
import com.tenwa.leasing.entity.voucher.InterV8Vouchers;
import com.tenwa.leasing.entity.voucher.IntereasVoucherLog;
import com.tenwa.leasing.entity.voucher.VoucherConnection;
import com.tenwa.leasing.entity.voucher.VoucherassStactsConfig;
import com.tenwa.leasing.entity.voucher.VoucherassStactsInfo;
import com.tenwa.leasing.service.voucher.IntereasVoucherheadService;
import com.tenwa.leasing.service.voucher.VoucherToV8Service;
import com.tenwa.leasing.utils.MoneyUtils;
import com.tenwa.leasing.utils.Tools;

/**
 * 
 * <p>。</p>
 * <p>2014-3-20</p>
 * @author sea
 * @version 4.5
 */
@Service(value = "voucherToV8Service")
public class VoucherToV8Impl extends AbstractBaseServiceImpl  implements VoucherToV8Service {
	
	/**
	 * log4j日志 
	 */
	private static final Logger log = LoggerFactory.getLogger(VoucherToV8Impl.class);
	
	@Resource(name="tableService")
	private TableService tableService ;
	
	/**
	 * 持久层DAO服务
	 */
	@Resource(name="baseDao")
	private BaseDao baseDao;
	@Override
	public BaseDao getBussinessDao() throws Exception {
		
		return baseDao;
	}
	
	/**
	 * 注入凭证头服务接口
	 */
	@Resource(name="intereasVoucherheadService")
	private IntereasVoucherheadService intereasVoucherheadService;
	/**
	 * 网银凭证情况下的使用的条件参考变量
	 */
	private static final String moduleName_value = "网银导入";
	
	/**
	 * 欧力士特殊生成凭证模块
	 * 因根据合同号都会把凭证归属在合同对应所属公司下，这样不满足欧力士现业务需求
	 */
	private static final String SPECIAL_MODULENAME = "代收深圳款";
	
	/**
      * FIXME SEA 代收租金 判断与网银规则一致，科目主表加废弃字段，字表也加废弃字段，下方随之加对应的判断 
	  * <p>V8凭证数据入库。</p>
	  * @author sea
	  * @param headMap
	  * headMap参数集合传入的数据目前存在：
	  * 参数1：合同号/contract_id  (合同表UUID，用于取凭证所属，合同信息等数据) 
	  * 参数2：业务模块/modleName (例如：已开票增值税发票回导流程/网银核销流程/租金实收流程...等等)
	  * Map<String,String> map1 = new HashMap<String, String>();
	  * map1.put("contract_id", "合同号");
	  * map1.put("moduleName", "业务模块");
	  * map1.put("accNumber", "本方银行账号编码");//网银凭证产生情况下，该字段必填 ,同时请务必把前一个参数‘moduleName’置为‘网银导入’。
	  * map1.put("oldContractid", "老合同转新合同情况下老合同号");
	  * @param vouchersMap
	  * 参数二集合传入的数据是一个list集合，里面是一个个的map集合，一个map封装一个凭证分录
	  * 参数1：摘要/F5 
	  * 参数2：科目序号/F6  VOUCHERASS_STACTS_CONFIG表的ID
	  * 参数3：借方金额/F7   原币金额  贷方金额为0
	  * 参数4：贷方金额 /F8  原币金额  借方金额为0
	  * 参数5：业务日期(到账日期)/F15 网银到账日期 
	  * 参数6：供应商编码/F19  供应商编码 
	  * 参数7：会计记账日期/F1 (产生凭证的地方存在会计日期则必须传入该日期，不存在可不传)。
	  * (会计记账日期传入为空清下：取值通过函数getAccountDay传入F15业务日期获取,已提供公用查询接口getBookDate) 
	  * List<Map<String,String>> list = new ArrayList<Map<String,String>>();
	  * Map<String,String> map2 = new HashMap<String, String>();
	  * map2.put("F5", "摘要");
	  * map2.put("F6", "科目序号");
	  * map2.put("F7", "借方金额");
	  * map2.put("F8", "贷方金额");
	  * map2.put("F15", "到账日期");
	  * map2.put("F19", "供应商编码");
	  * map2.put("F1", "会计记账日期");
	  * list.add(map2);
	  * @param user 参数三 当前用户登录信息
	  * @return
	 * @throws Exception 
	 * @throws Exception 
	 * @throws Exception 
	 * @throws DataAccessException 
	 */
	public  String saveV8Message(Map<String,String> headMap,List<Map<String,String>> list,User user) throws Exception{
		String v8Str = "";
		try {
			///List list_all=new ArrayList();
			//转换接口字段
            for (int i = 0; i < list.size(); i++) {
				Map<String,String> map1 = list.get(i);
				if(map1.containsKey("voucherSummary")){
				   map1.put("F5", map1.get("voucherSummary"));//摘要
				  
				}
				if(map1.containsKey("subjectsId")){
				   map1.put("F6", map1.get("subjectsId"));//科目编码ID
				}
				if(map1.containsKey("debitMoney")){
				   map1.put("F7", map1.get("debitMoney"));//借方金额
				}
				if(map1.containsKey("lenderMoney")){
				   map1.put("F8", map1.get("lenderMoney"));//贷方金额
				}
				if(map1.containsKey("ebankFactDate")){
				   map1.put("F15", map1.get("ebankFactDate"));//网银到账日期
				}
				if(map1.containsKey("accountDate")){
				   map1.put("F1", map1.get("accountDate"));//会计处理日期
				}
			
            }
			String message = "";
			//当前登录人对象获取
	    	String creator = user.getId();//制单人,当前登录人对象 
	    	String status = "已完整";//凭证状态
	    	String eas_flag  = "0";//导入财务系统状态 默认0
	    	String moduleName = headMap.get("moduleName") ;
	    	
			String contract_id = Tools.getStr( headMap.get("contract_id") );//获取合同id
			//网银导入、代收租金 的凭证不需要合同号
			if(!moduleName_value.equals(moduleName) && !SPECIAL_MODULENAME.equals(moduleName) && Tools.isNullOrEmpty(contract_id)){
				log.error("合同号为空,无法构建对应的凭证信息!");
				return null;
			}
			
			String accNumber = headMap.get("accNumber");//网银编号
			if( (moduleName_value.equals(moduleName) || SPECIAL_MODULENAME.equals(moduleName) ) && Tools.isNullOrEmpty(accNumber)){
				log.error("本方银行账号为空,无法构建对应的凭证信息!");
				return null;
			}
			//所属公司，目前项目只有一个
			DictionaryData dict =this.baseDao.findEntityByID(DictionaryData.class, "ContractBelong1");
			Department projDept = null;
			ContractInfo contractInfo = null;//合同基本信息表
			ContractInfo oldcontractinfo = null;//老合同转新合同时，老合同对应信息
			
			if(!Tools.isNullOrEmpty(headMap.get("oldContractid"))){
				oldcontractinfo = this.findEntityByID(ContractInfo.class, headMap.get("oldContractid"));
			}
			
			//网银、代收租金情况下不存在合同相关信息
			if(!moduleName_value.equals(moduleName) && !SPECIAL_MODULENAME.equals(moduleName)){
				contractInfo = this.findEntityByID(ContractInfo.class, contract_id);
				
				if(Tools.isNullOrEmpty(contractInfo.getId())){
					message = message + "合同信息为空!";
					status = "未完整";//凭证状态
				}
				//项目出单部门
				projDept = contractInfo.getDepartment();
				//合同其他信息表 包含签约信息
				//ContractOtherInfo contractOtherInfo = contractInfo.getContractOtherInfo();
				//字段三四  凭证所属公司编码及其名称
//				dict = contractOtherInfo.getContractBelong();
//				if(dict == null && ( !moduleName_value.equals(moduleName) || !SPECIAL_MODULENAME.equals(moduleName) ) ){
//					message = message + "所属公司编码为空!";
//					status = "未完整";//凭证状态
//				}
				//一个合同对应多个租赁物件信息
				//Set<ContractEquip> contractEquips = contractInfo.getContractEquips();
				//合同对应客户信息
				//CustInfo custInfo = contractInfo.getCustId();
			}else{
				//根据网银编号去查询(OWN_ACCOUNT)归属ACCOWNER及科目序号accSubject
				Map<String, Object> propertiesMap = new HashMap<String, Object>();
				propertiesMap.put("accNumber", accNumber);
				OwnAccount  ownAccount = this.baseDao.findEntityByProperties(OwnAccount.class, propertiesMap).get(0);
				//构建网银本方账号对应的归属的数据字典对象
				//dict = ownAccount.getAccowner();
			}
			
			String F2 = "记";
			//获取凭证号
			String  F3 = intereasVoucherheadService.getVoucherNumber();
			
			//F4 附单据数为空
			List<InterV8Vouchers> v8Vouchers = new ArrayList<InterV8Vouchers>();
			Double f7 = 0.00;//借方金额 原币金额 贷就为0
			//Double f8 = 0.00;//贷方金额 原币金额 借就为0
			Integer entryDC = 0;//1 借方-1 贷方
			//
			BigDecimal F7Count = BigDecimal.ZERO;//借方金额总计
			BigDecimal F8Count = BigDecimal.ZERO;//贷方金额总计
			for (int i = 0; i < list.size(); i++) {
				
				Map<String,String> map2 = list.get(i);
				//会计记账日期
				//String F1 = Tools.getDBDateStr( map2.get("F1") );	//2014-11-28: Tools.getDBDateStr("2014-9-19")返回""。因为函数内调用date.substring(0, 10)时抛出下标越界异常
				String F1 = DateUtils.date2Str(map2.get("F1"));
				
				//摘要
				String F5 =  map2.get("F5") ;
				//科目编号
				String F6 = Tools.getStr( map2.get("F6") );//这时取出的是科目序号
				if(Tools.isNullOrEmpty(F6)){
					message = message + "科目序号为空!";
					status = "未完整";//凭证状态
				}
				VoucherassStactsConfig  config = this.seachConfig(F6, dict);
				if(Tools.isNullOrEmpty(config.getSubjectsCode())){
					message = message + "科目相关信息获取失败!";
					status = "未完整";//凭证状态
				}
				F6 = config.getSubjectsCode();//科目编号
				 
				
				BigDecimal F7  = BigDecimal.valueOf( MathExtend.parseDouble(  MoneyUtils.getZeroStr( map2.get("F7") ) ) );//借方金额/F7   原币金额  贷方金额为0
				F7Count = F7Count.add(F7);
				BigDecimal F8 = BigDecimal.valueOf( MathExtend.parseDouble(  MoneyUtils.getZeroStr( map2.get("F8") ) ) );//贷方金额/F8   原币金额  借方金额为0
				F8Count = F8Count.add(F8);
				
				f7 = NumberUtils.parseDouble( MoneyUtils.getZeroStr( String.valueOf( F7 ) ) );//借方金额
				//f8 = NumberUtils.parseDouble( MoneyUtils.getZeroStr( String.valueOf( F8 ) ) );//贷方金额
				//如果两个数一样则返回0，如果第一个数比第二个数大则返回1，反之返回-1 
				if(NumberUtils.compareTo(f7, 0.00) != 0){//借方金额 不等于0.00  该条分录是 借
					entryDC = 1;//1 借方   -1 贷方
				}else{//贷 
					entryDC = -1;//1 借方 -1 贷方
				}
				
				Integer F9 = 0;//数量
				String F10 = "0";//外币
				//F11 为空
				String F12 = user.getRealname();//制单人
				//F13 F14 2个 为空
				//业务发生日期
				String date = map2.get("F15");
				if(Tools.isNullOrEmpty(date)){
					log.error("业务日期为空，无法产生凭证信息!");
					throw new Exception("业务日期为空，无法产生凭证信息!!!!!!!!");
				}
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				//String F15 = sdf.format( sdf.parse(date) );
				String F15 = DateUtils.date2Str(date);
				if(Tools.isNullOrEmpty(F15)){
					log.error("业务日期获取失败!");
					message = message + "业务日期获取失败!";
					status = "未完整";//凭证状态
				}
				
				//会计期间-年  以下2个变量都是
				String periodYear = "";
				String F51 = "";
				//会计期间-月
				String F52 = "";
				
				//自定义会计记账日为空前提下
				/**
				 * 2014-09-10 会计处理日原则变更处理如下
				 * 前端传入的会计处理日为空，则根据业务日期及关帐日函数去处理
				 * 前端传入的会计处理日不为空，则根据会计处理日及关帐日函数取处理
				 */
				if(Tools.isNullOrEmpty(F1)){
					//根据业务操作日期查询会计记账日
					F1 = intereasVoucherheadService.getBookDate(F15);
				}else{
					F1 = intereasVoucherheadService.getBookDate(F1);
				}
				
				if(Tools.isNullOrEmpty(F1)){
					log.error("财务记账日期获取失败!");
					message = message + "财务记账日期获取失败!";
					status = "未完整";//凭证状态
				}else{
					periodYear = F1.substring(0,4);//会计期间-年 
					F51 = F1.substring(0,4);//会计期间-年 
					F52 = F1.substring(5,7);//会计期间-月
				}
				
				//按部门辅助核算时带入部门编码,根据科目去科目子表查询是否存在部门类型的辅助核算，存在数据则去取合同的部门编号
				Department F16 = null;
				Department F63 = null;
				//按供应商辅助核算时带入供应商名称 与上方一致
				CustInfo F19 = null;
				CustInfo F66 = null;
				//按合同辅助核算 F22自定义项1 /F44 自由项1  
				ContractInfo F22 = null;
				ContractInfo F24 = null;
				//按项目辅助核算  
				ContractInfo F21 = null;//项目编码
				ContractInfo F67 = null;//项目名称
				//按客户辅助核算
				ContractInfo F18 = null;//F18客户编码
				ContractInfo F65 = null;//F65客户名称
				
				String configid = config.getId();//科目主配置表的主键
				log.info("configid:"+configid);
				
				//获取配置子表的完整信息,主要是统计该科目存在多少个辅助核算信息
				Map<String,Object> propertiesMap = new HashMap<String, Object>();
				propertiesMap.put("configid", config);
				propertiesMap.put("status", 0);//0是有效； 1为作废
				//根据具体的字段条件查询一个entity，条件使用map封装
				List<VoucherConnection>  voucherConnection_l = this.getBussinessDao().findEntityByProperties(VoucherConnection.class, propertiesMap);
				
				//F17个人编码 空 F18在下方赋值
				
				/**
				 * 网银导入及代收租金情况下无辅助核算信息产生
				 * U8凭证中辅助核算配置生成规则伪码说明(具体实现见OCC工程VoucherToV8Impl.java中代码实现及数据库中对应表数据)：
				 * 配置1：请在科目主配置表(VOUCHERASS_STACTS_CONFIG)根据具体的科目编码配置科目名称，科目所属公司，科目序号等信息。
				 * 		 相同科目录属不同公司或不同科目录属同一公司或不同科目录属不同公司如下配置:
				 * 		  例如：相同科目录属不同公司  相同科目不同公司科目序号必须一致
				 * 			科目序号：109、科目名称：应收租赁款 、科目编码:16102、所属公司：欧力士上海公司
				 * 			科目序号：109、科目名称：应收租赁款 、科目编码:16102、所属公司：欧力士深圳分公司
				 * 		  例如：不同科目录属同一公司
				 * 			科目序号：112(不允许重复)、科目名称：预收账款、科目编码:20606、所属公司：欧力士上海公司
				 * 			科目序号：113(不允许重复)、科目名称：银行存款、科目编码:102****、所属公司：欧力士上海公司
				 * 		  例如：不同科目录属不同公司
				 * 			科目序号：114(不允许重复)、科目名称：银行存款、科目编码:119、所属公司：欧力士上海公司
				 * 			科目序号：115(不允许重复)、科目名称：其他应付款-期末退还保证金、科目编码:21801、所属公司：欧力士深圳公司
				 * 配置2：一行科目信息在主配置表配置完成后，需在科目子配置表(VOUCHER_CONNECTION)配置对应的科目辅助核算信息。
				 * 辅助核算项目前存在8项，分别如下(辅助核算表：VOUCHERASSSTACTS_INFO，该表数据不允许随意删除及修改，只可增加)：
				 *  辅助核算唯一字符串标示符          唯一数字标示符      辅助核算中文名称    是否已启用
				 * voucherassstacts_info_id_1	  	01			客户  			已使用 
				 * voucherassstacts_info_id_2		02			经销商			已使用
				 * voucherassstacts_info_id_3		03			银行账户			未使用
				 * voucherassstacts_info_id_4		04			合同	           	已使用
				 * voucherassstacts_info_id_5		05			部门 			已使用
				 * voucherassstacts_info_id_6		06			供应商 			已使用
				 * voucherassstacts_info_id_7		07			金融机构 			未使用
				 * voucherassstacts_info_id_8		08			项目  			已使用
				 * ....其它数据增加请在此处加上对应注释，方便开发人员的开发和维护
			     * 科目子配置表需主配置表增加一条科目信息后增加对应的子配置信息(辅助核算信息),例如：
				 *      主表存在上海公司科目为16102的一条科目信息，该科目存在项目辅助核算，则字表具体格式如下：
				 *      科目16102对应主表中的UUID，辅助核算表为项目的唯一字符串标示符‘voucherassstacts_info_id_8’
				 *      
				 * 查询规则：
				 * 1.前台传入科目序号，根据需要查询该序号对应科目主表的信息
				 * 2.根据主表中UUID去子表查询所有属于该科目的辅助核算信息，遍历辅助核算信息，产生具体的辅助核算项
				 * 注意事项：
				 *    欧力士项目中存在：部门、供应商、合同、项目、客户五种核算项，
				 *    其中‘合同、项目、客户’这三种核算在科目配置中配置的是对应的辅助核算类型，
				 *    但是他们辅助核算的内容目前都为‘合同号’
				 */			
				if(!moduleName_value.equals(moduleName) && !SPECIAL_MODULENAME.equals(moduleName) && voucherConnection_l.size() > 0){//网银情况下不存在辅助核算项
					for (int j = 0; j < voucherConnection_l.size(); j++) {
						VoucherConnection voucherConnection = voucherConnection_l.get(j);
						//辅助账类型编号
						VoucherassStactsInfo obj = voucherConnection.getAsstActType();
						String voucherassStactsInfoId = Tools.getStr( obj.getId() );//辅助账类型表主键
						/**
						 *辅助核算1：按部门核算
						 *规则：根据科目信息去科目子表查询是否存在部门类型的辅助核算，存在数据则去取合同的部门编号
						 *F16部门编码	按部门辅助核算时带入部门编码	
						 *F63部门名称	按部门辅助核算时带入部门名称 
						 */				
						if("voucherassstacts_info_id_5".equals(voucherassStactsInfoId)){
							if(projDept == null){
								log.error("部门为空，辅助核算项无法产生!");
								message = message + "部门辅助核算项无法产生!";
								status = "未完整";//凭证状态
							}else{
								F16 = projDept;
								F63 = F16;
							}
						}
						
						/**
						 *辅助核算2：按供应商核算
						 *规则：根据科目信息去科目子表查询是否存在供应商类型的辅助核算，存在数据则根据供应商编码去取供应商相关信息
						 *F19 供应商编码	
						 *F66供应商简称
						 */
						if("voucherassstacts_info_id_6".equals(voucherassStactsInfoId)){
							String f19 = map2.get("F19");
							F19 = this.getBussinessDao().findEntityByID(CustInfo.class, f19);
							//按供应商辅助核算时带入供应商名称 与上方一致
							F66 = F19;
							if(F19 == null || F66 == null){
								log.error("供应商为空，辅助核算项无法产生!");
								message = message + "供应商辅助核算项无法产生!";
								status = "未完整";
							}
						}
						
						/**
						 *辅助核算3：按合同核算
						 *规则：根据科目信息去科目子表查询是否存在合同类型的辅助核算，存在数据则根据供应商编码去取合同相关信息
						 *F22自定义项1	按合同辅助核算	自定项1 辅助核算为合同时才插入合同号
						 *F24自由项1	         按合同辅助核算	自由项1 辅助核算为合同时才插入合同号
						 */
						if("voucherassstacts_info_id_4".equals(voucherassStactsInfoId)){
							//F22自定义项1 
							if("起租流程-旧案件转新案件".equals(moduleName) && i == 1){
								F22 = oldcontractinfo;
							} else {
								F22 = contractInfo;
							}
							F24 = F22;
							if(null == contractInfo || null == F24){
								log.error("合同信息为空，辅助核算项无法产生!");
								message = message + "合同辅助核算项无法产生!";
								status = "未完整";
							}
						}
						
						/**
						 *辅助核算4：按项目核算
						 *规则：根据科目信息去科目子表查询是否存在项目的辅助核算，存在数据则取合同编号
						 *F21项目编码	按合同辅助核算	
						 *F67项目名称	按项目辅助核算带合同号
						 */
						if("voucherassstacts_info_id_8".equals(voucherassStactsInfoId)){
							//F21项目编码 
							if("起租流程-旧案件转新案件".equals(moduleName) && i == 1){//分录2
								F21 = oldcontractinfo;
							} else {
								F21 = contractInfo;
							}
							F67 = F21;
							if(F21 == null){
								log.error("合同信息为空，项目辅助核算项无法产生!");
								message = message + "项目辅助核算项无法产生!";
								status = "未完整";
							}
						}
						
						/**
						 *辅助核算5：按客户核算
						 *规则：根据科目信息去科目子表查询是否存在客户的辅助核算，存在数据则取合同编号
						 *F18客户编码	合同号
						 *F65客户名称	合同号
						 */
						if("voucherassstacts_info_id_1".equals(voucherassStactsInfoId)){
							//F18客户编码  客户就是合同，直接存合同号
							if("起租流程-旧案件转新案件".equals(moduleName) && i == 1){//分录2
								F18 = oldcontractinfo;
							} else {
								F18 = contractInfo;
							}
							F65 = F18;
							if(F18 == null){
								log.error("合同信息为空，项目辅助核算项无法产生!");
								message = message + "项目辅助核算项无法产生!";
								status = "未完整";
							}
						}
						//其它辅助核算信息生成判断..........
						//...........do something
					}
				}
				
				//F20业务员 空
				//F21项目编码  上方赋值
				//F22自定义项1 在上方赋值
				//F23-F33 F34 F35业务类型-F38 都为空
				String F39 = "0";
				String F40 = "0";
				String F41 = "0";
				String F42 = "0";
				String F43 = "0";
				//F44 为空
				String F45 = "0";
				String F46 = "0";
				String F47 = "0";
				String F48 = "0";
				//暂时定为帐套
				DictionaryData F49 = dict;
				DictionaryData F50 = dict;
				
				String F53 = "1";
				//F54F55F56三个为空
				String F57 = "0";
				//F58 为空
				String F59 = String.valueOf( i+1 );
				//F60F61两个为空
				//String subjectsName = config.getSubjectsName();//科目名称
				VoucherassStactsConfig F62 = config;
				//F63在上方赋值
				//F64 为空
				//F65在上方赋值
				//F66 F67在上方赋值
				String F68	= "00";//大类编码 暂定
				String F69	= "组别";//大类名称
				
				//F70	对方科目，多个以“，”隔开	借就全取贷方的所有，贷就取所有借的 表中暂时存入空
				//F71-F90为空
				
				//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@华丽的分割线@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				//构建待持久化的V8凭证完整对象信息，一行分录是一个对象
				InterV8Vouchers v8Obj = new InterV8Vouchers();
				v8Obj.setEvidenceMessage("凭证输出");
				v8Obj.setEvidenceType("U800");
				v8Obj.setDeptId(dict);
				v8Obj.setDeptName(dict);
				v8Obj.setPeriodYear(periodYear);
				v8Obj.setF1(F1);
				v8Obj.setF2(F2);
				v8Obj.setF3(F3);
				//v8Obj.setF4(f4);
				v8Obj.setF5(F5);
				v8Obj.setF6(config);
				v8Obj.setF7(F7);
				v8Obj.setF8(F8);
				v8Obj.setF9(F9);
				v8Obj.setF10(F10);
				//v8Obj.setF11(f11)
				v8Obj.setF12(F12);
				//v8Obj.setF13(f13);
				//v8Obj.setF14(f14);
				v8Obj.setF15(F15);
				v8Obj.setF16(F16);
				//v8Obj.setF17(f17)
				v8Obj.setF18(F18);
				v8Obj.setF19(F19);
				//v8Obj.setF20(f20)
				v8Obj.setF21(F21);
				v8Obj.setF22(F22);
				//v8Obj.setF23(f23)直到38都为空
				v8Obj.setF24(F24);//按合同辅助核算
				v8Obj.setF39(F39);
				v8Obj.setF40(F40);
				v8Obj.setF41(F41);
				v8Obj.setF42(F42);
				v8Obj.setF43(F43);
				//v8Obj.setF44(f44)
				v8Obj.setF45(F45);
				v8Obj.setF46(F46);
				v8Obj.setF47(F47);
				v8Obj.setF48(F48);
				
				v8Obj.setF49(F49);
				v8Obj.setF50(F50);
				v8Obj.setF51(F51);
				v8Obj.setF52(F52);
				v8Obj.setF53(F53);
				//54-56 空
				v8Obj.setF57(F57);
				//58空
				v8Obj.setF59(F59);
				//60 61 空
				v8Obj.setF62(F62);
				v8Obj.setF63(F63);
				//64空
				v8Obj.setF65(F65);
				v8Obj.setF66(F66);
				v8Obj.setF67(F67);
				v8Obj.setF68(F68);
				v8Obj.setF69(F69);
				//v8Obj.setF70(f70)
				//71-90 空
				v8Obj.setCreator(user);
				v8Obj.setStatus(status);
				v8Obj.setV8Flag(eas_flag);//导入财务系统状态 默认0
				v8Obj.setModuleName(moduleName); 
				v8Obj.setGenerateDate(DateUtils.getSystemDate(3)); 
				v8Obj.setV8Memo("未导出");
				v8Obj.setEntryDC(entryDC);
				v8Obj.setRollBack("0");
				v8Vouchers.add(v8Obj);
				//this.getString(v8Obj);
			}	
			log.info("凭证分录数量:"+v8Vouchers.size());
			//批量持久化操作
			//FIXME:sea 这里存在2种凭证产生方式，应该取效率最快的为准
			if( F7Count != BigDecimal.ZERO && F8Count != BigDecimal.ZERO ){//凭证借贷双方各自总价值大于0才可以产生凭证
				//实体方式保存凭证
				this.saveAllEntities(v8Vouchers);
			}
			//批量JDBC方式产生凭证
			//this.saveVouchersToPLJDBC(v8Vouchers);
		
		} catch (Exception e) {
			//徐云龙及吕永辉两位强烈建议这里把整个一起做try catch处理，后人如果改这里千万不要骂原作者，因为之前的异常处理方式是通过方法把异常抛到上层方法中的
			e.printStackTrace();
			throw new Exception("凭证生成出错!");
		}
		return v8Str;
	}
	
	/**
	  * <p>根据项科目序号和合同/网银所属取出对应的科目对象。</p>
	  * @author sea
	  * @param subjectsNum 科目序号
	  * @param dict 合同所属or网银所属
	  * @return
	  * @throws Exception
	 */
	private VoucherassStactsConfig seachConfig(String subjectsNum,DictionaryData dict) throws Exception{
		VoucherassStactsConfig config = new VoucherassStactsConfig();
		Map<String,Object> map = new HashMap<String, Object>();
		log.info("根据科目序号及凭证的公司所属查询科目ENTITY:");
		log.info("subjectsNum:"+subjectsNum);
		//log.info("SUBJECTS_OWNER:"+dict.getId());
		map.put("subjectsId", subjectsNum);//科目编码ID(用于多分公司同一科目不同科目编码)
		//新项目不分合同所属
		//map.put("subjectsOwner", dict);//所属区域(科目对应的所属区域，存入的是数据字典的UUID)
		 map.put("status", 0);//科目状态为有效 , 0是有效； 1为作废
		//根据具体的字段条件查询一个entity，条件使用map封装
		config = this.getBussinessDao().findEntityByProperties(VoucherassStactsConfig.class, map).get(0);
		
		return config;
	}
	
	/**
	  * <p>JDBC方式批量持久化凭证信息。</p>
	  * @author sea
	  * @param v8Vouchers 一个凭证的多条凭证分录信息
	  * @throws DataAccessException
	  * @throws Exception
	 */
	public void saveVouchersToPLJDBC(List<InterV8Vouchers> v8Vouchers) throws DataAccessException, Exception
	{
		  //使用完Hibernate持久化数据操作后再同一个事物管理中需要做下FLUSH操作
		  //this.baseDao.updateFlush();
		  StringBuffer sql = new StringBuffer();	
		  final List<InterV8Vouchers> tempEntries = v8Vouchers;
		  //构建执行SQL
		  sql.append(" insert  into  VOUCHER_V8 ")
		     .append(" ( ") 
		     .append(" ID,EVIDENCE_MESSAGE,EVIDENCE_TYPE, DEPT_ID,DEPT_NAME, ")//1
		     .append(" periodYear,F1,F2,F3,F4, ")//2
		     .append(" F5,F6,F7,F8,F9, ")//3
		     .append(" F10,F11,F12,F13,F14, ")//4
		     .append(" F15,F16,F17,F18,F19, ")//5
		     .append(" F20,F21,F22,F23,F24, ")//6
		     .append(" F25,F26,F27,F28,F29, ")//7
		     .append(" F30,F31,F32,F33,F34, ")//8
		     .append(" F35,F36,F37,F38,F39, ")//9
		     .append(" F40,F41,F42,F43,F44, ")//10
		     .append(" F45,F46,F47,F48,F49, ")//11
    		 .append(" F50,F51,F52,F53,F54, ")//12
			 .append(" F55,F56,F57,F58,F59, ")//13
			 .append(" F60,F61,F62,F63,F64, ")//14
			 .append(" F65,F66,F67,F68,F69, ")//15
			 .append(" F70,STATUS_,GENERATE_DATE,V8_FLAG,MODULE_NAME, ")//16
			 .append(" V8_MEMO,CREATOR_,CREATE_DATE,MODIFICATOR_,MODIFY_DATE, ")//17
		     .append(" entryDC)") 
			 .append(" values ( ")
			 .append(" ") 
		     .append(" ?, ?, ?, ?, ?, ")//1
		     .append(" ?, ?, ?, ?, ?, ")//2
		     .append(" ?, ?, ?, ?, ?, ")//3
		     .append(" ?, ?, ?, ?, ?, ")//4
		     .append(" ?, ?, ?, ?, ?, ")//5
		     .append(" ?, ?, ?, ?, ?, ")//6
		     .append(" ?, ?, ?, ?, ?, ")//7
		     .append(" ?, ?, ?, ?, ?, ")//8
		     .append(" ?, ?, ?, ?, ?, ")//9
		     .append(" ?, ?, ?, ?, ?, ")//10
		     .append(" ?, ?, ?, ?, ?, ")//11
		     .append(" ?, ?, ?, ?, ?, ")//12
		     .append(" ?, ?, ?, ?, ?, ")//13
		     .append(" ?, ?, ?, ?, ?, ")//14
		     .append(" ?, ?, ?, ?, ?, ")//15
		     .append(" ?, ?, ?, ?, ?, ")//16
		     .append(" ?, ?, ?, ?, ?,  ")//17
		     .append(" ?)");
		  
		  
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
					   if(Tools.isNullOrEmpty(uuId)){
						   uuId = UUIDUtil.getUUID();
				       }
					   String evidenceMessage = tempEntries.get(i).getEvidenceMessage();
					   String evidenceType = tempEntries.get(i).getEvidenceType();
					   //DictionaryData
					   String deptId  = tempEntries.get(i).getDeptId().getId();
					   String deptName  = tempEntries.get(i).getDeptName().getId();
					   String periodYear  = tempEntries.get(i).getPeriodYear(); 
					   String F1  = tempEntries.get(i).getF1();
					   String F2  = tempEntries.get(i).getF2();
					   String F3 = tempEntries.get(i).getF3();
					   String F4 = tempEntries.get(i).getF4();
					   String F5 = tempEntries.get(i).getF5();
					   //VoucherassStactsConfig
					   String F6 = tempEntries.get(i).getF6().getId();
					   
					   BigDecimal F7  = tempEntries.get(i).getF7();//借方金额 分录行号为1就=原币金额 分录行号为2就为0
					   BigDecimal F8  = tempEntries.get(i).getF8();//贷方金额  分录行号为2就=原币金额 分录行号为1就为0
					   Integer F9  = tempEntries.get(i).getF9();
					   String F10 = tempEntries.get(i).getF10();
					   BigDecimal F11  = tempEntries.get(i).getF11();
					   String F12 = tempEntries.get(i).getF12();
					   String F13 = tempEntries.get(i).getF13();
					   String F14 = tempEntries.get(i).getF14();
					   
					   String F15  = tempEntries.get(i).getF15();
					   //Department 按部门辅助核算时带入部门编码
					   String F16  = "";
					   if( !Tools.isNullOrEmpty( tempEntries.get(i).getF16() ) ){
						   F16  = tempEntries.get(i).getF16().getId();
					   }
					   //User
					   String F17  = "";
					   if( !Tools.isNullOrEmpty( tempEntries.get(i).getF17() ) ){
						   F17  = tempEntries.get(i).getF17().getId();
					   }
					   //ContractInfo
					   String F18  = "";//欧力士客户就是合同，这里存入合同号,其余项目请具体处理
					   if( !Tools.isNullOrEmpty( tempEntries.get(i).getF18() ) ){
						   F18  = tempEntries.get(i).getF18().getId();
					   }
					   //CustInfo
					   String F19  = "";//按供应商辅助核算时带入供应商编码
					   if( !Tools.isNullOrEmpty( tempEntries.get(i).getF19() ) ){
						   F19  = tempEntries.get(i).getF19().getId();
					   }
					   
					   String F20  = tempEntries.get(i).getF20();
					   //ContractInfo
					   String F21  = "";
					   if( !Tools.isNullOrEmpty( tempEntries.get(i).getF21() ) ){
						   F21  = tempEntries.get(i).getF21().getId();
					   }
					   
					   String F22  = "";
					   if( !Tools.isNullOrEmpty( tempEntries.get(i).getF22() ) ){
						   F22  = tempEntries.get(i).getF22().getId();
					   }
					   
					   String F23  = tempEntries.get(i).getF23();
					   String F24  = F22;
					   
					   String F25  = tempEntries.get(i).getF25();
					   String F26  = tempEntries.get(i).getF26();
					   String F27  = tempEntries.get(i).getF27();
					   String F28  = tempEntries.get(i).getF28();
					   String F29  = tempEntries.get(i).getF29();
					   
					   String F30  = tempEntries.get(i).getF30();
					   String F31  = tempEntries.get(i).getF31();
					   String F32  = tempEntries.get(i).getF32();
					   String F33  = tempEntries.get(i).getF33();
					   String F34  = tempEntries.get(i).getF34();
					   
					   String F35  = tempEntries.get(i).getF35();
					   String F36  = tempEntries.get(i).getF36();
					   String F37  = tempEntries.get(i).getF37();
					   String F38  = tempEntries.get(i).getF38();
					   String F39  = tempEntries.get(i).getF39();
					   
					   String F40  = tempEntries.get(i).getF40();
					   String F41  = tempEntries.get(i).getF41();
					   String F42  = tempEntries.get(i).getF42();
					   String F43  = tempEntries.get(i).getF43();
					   String F44  = tempEntries.get(i).getF44();
					   
					   String F45  = tempEntries.get(i).getF45();
					   String F46  = tempEntries.get(i).getF46();
					   String F47  = tempEntries.get(i).getF47();
					   String F48  = tempEntries.get(i).getF48();
					   //DictionaryData
					   String F49  = "";
					   if( !Tools.isNullOrEmpty( tempEntries.get(i).getF49() ) ){
						   F49  = tempEntries.get(i).getF49().getId();
					   }
					   
					   String F50  = tempEntries.get(i).getF50().getId();
					   String F51  = tempEntries.get(i).getF51(); 
					   String F52  = tempEntries.get(i).getF52(); 
					   String F53  = tempEntries.get(i).getF53();
					   String F54  = tempEntries.get(i).getF54();
					   
					   String F55  = tempEntries.get(i).getF55();
					   String F56  = tempEntries.get(i).getF56();
					   String F57  = tempEntries.get(i).getF57();
					   String F58  = tempEntries.get(i).getF58();
					   String F59  = tempEntries.get(i).getF59();
					   
					   String F60  = tempEntries.get(i).getF60();
					   String F61  = tempEntries.get(i).getF61();
					   //VoucherassStactsConfig
					   String F62 = "";
					   if( !Tools.isNullOrEmpty( tempEntries.get(i).getF62() ) ){
						   F62  = tempEntries.get(i).getF62().getId();
					   }
					   
					   //Department
					   String F63  = "";//按部门辅助核算时带入部门名称
					   if( !Tools.isNullOrEmpty( tempEntries.get(i).getF63() ) ){
						   F63  = tempEntries.get(i).getF63().getId();
					   }
					   String F64  = tempEntries.get(i).getF64();
					   
					   //CustInfo
					   String F65  = "";
					   String F66  = "";//按供应商辅助核算时带入供应商名称
					   if( !Tools.isNullOrEmpty( tempEntries.get(i).getF65() ) ){
						   F65  = tempEntries.get(i).getF65().getId();
					   }
					   if( !Tools.isNullOrEmpty( tempEntries.get(i).getF66() ) ){
						   F66  = tempEntries.get(i).getF66().getId();//按供应商辅助核算时带入供应商名称
					   }
					   
					   //ContractInfo
					   String F67 = "";
					   if( !Tools.isNullOrEmpty( tempEntries.get(i).getF67() ) ){
						   F67  = tempEntries.get(i).getF67().getId();
					   }
					   String F68  = tempEntries.get(i).getF68();//固定为：00
					   String F69  = tempEntries.get(i).getF69();//固定为：组别
					   
					   String F70  = tempEntries.get(i).getF70();//对方科目，多个以“，”隔开 ,借就全取贷方的所有，贷就取所有借的
					   /*String F71  = tempEntries.get(i);
					   String F72  = tempEntries.get(i);
					   String F73  = tempEntries.get(i);
					   String F74  = tempEntries.get(i);
					   String F75  = tempEntries.get(i);
					   String F76  = tempEntries.get(i);
					   String F77  = tempEntries.get(i);
					   String F78  = tempEntries.get(i);
					   String F79  = tempEntries.get(i);
					   String F80  = tempEntries.get(i);
					   String F81  = tempEntries.get(i);
					   String F82  = tempEntries.get(i);
					   String F83  = tempEntries.get(i);
					   String F84  = tempEntries.get(i);
					   String F85  = tempEntries.get(i);
					   String F86  = tempEntries.get(i);
					   String F87  = tempEntries.get(i);
					   String F88  = tempEntries.get(i);
					   String F89  = tempEntries.get(i);
					   String F90  = tempEntries.get(i);*/
					   String status  = tempEntries.get(i).getStatus();
					   String generateDate   = tempEntries.get(i).getGenerateDate();
					   String v8Flag   = tempEntries.get(i).getV8Flag();
					   String moduleName  = tempEntries.get(i).getModuleName();
					   
					   String v8Memo  = tempEntries.get(i).getV8Memo();
					   String creator = "";
					   String createDate = tempEntries.get(i).getCreateDate();
					   String modificator = "";
					   String modifyDate = tempEntries.get(i).getModifyDate();
					   
					   if( !Tools.isNullOrEmpty( tempEntries.get(i).getCreator() ) ){
						   creator = tempEntries.get(i).getCreator().getId();
						   modificator = creator;
					   }
					   Integer entryDC = tempEntries.get(i).getEntryDC();
					   
					   //装值
					   ps.setString(1, uuId);
					   ps.setString(2, evidenceMessage);
					   ps.setString(3, evidenceType);
					   ps.setString(4, deptId);
					   ps.setString(5, deptName);
					   //
					   ps.setString(6, periodYear);
					   ps.setString(7, F1);
					   ps.setString(8, F2);
					   ps.setString(9, F3);
					   ps.setString(10, F4);//null
					   //
					   ps.setString(11, F5);
					   ps.setString(12, F6);
					   ps.setBigDecimal(13, F7);
					   ps.setBigDecimal(14, F8);
					   ps.setInt(15, F9);
					   
					   //
					   ps.setString(16, F10);
					   ps.setBigDecimal(17, F11);//null
					   ps.setString(18, F12);//""
					   ps.setString(19, F13);//null
					   ps.setString(20, F14);//null
					   //
					   ps.setString(21, F15);
					   ps.setString(22, F16);//""
					   ps.setString(23, F17);//""
					   ps.setString(24, F18);
					   ps.setString(25, F19);//""
					   //
					   ps.setString(26, F20);//null
					   ps.setString(27, F21);
					   ps.setString(28, F22);
					   ps.setString(29, F23);//null
					   ps.setString(30, F24);//null
					   //
					   ps.setString(31, F25);//null
					   ps.setString(32, F26);//null
					   ps.setString(33, F27);//null
					   ps.setString(34, F28);//null
					   ps.setString(35, F29);//null
					   //
					   ps.setString(36, F30);//null
					   ps.setString(37, F31);//null
					   ps.setString(38, F32);//null
					   ps.setString(39, F33);//null
					   ps.setString(40, F34);//null
					   //
					   ps.setString(41, F35);//null
					   ps.setString(42, F36);//null
					   ps.setString(43, F37);//null
					   ps.setString(44, F38);//null
					   ps.setString(45, F39);
					   //
					   ps.setString(46, F40);
					   ps.setString(47, F41);
					   ps.setString(48, F42);
					   ps.setString(49, F43);
					   ps.setString(50, F44);//null
					   //
					   ps.setString(51, F45);
					   ps.setString(52, F46);
					   ps.setString(53, F47);
					   ps.setString(54, F48);
					   ps.setString(55, F49);
					   //
					   ps.setString(56, F50);
					   ps.setString(57, F51);
					   ps.setString(58, F52);
					   ps.setString(59, F53);
					   ps.setString(60, F54);//null
					   //
					   ps.setString(61, F55);//null
					   ps.setString(62, F56);//null
					   ps.setString(63, F57);
					   ps.setString(64, F58);//null
					   ps.setString(65, F59);
					   //
					   ps.setString(66, F60);//null
					   ps.setString(67, F61);//null
					   ps.setString(68, F62);
					   ps.setString(69, F63);//""
					   ps.setString(70, F64);//null
					   //
					   ps.setString(71, F65);
					   ps.setString(72, F66);//""
					   ps.setString(73, F67);
					   ps.setString(74, F68);
					   ps.setString(75, F69);
					   //
					   ps.setString(76, F70);//null
					   ps.setString(77, status);
					   ps.setString(78, generateDate);
					   ps.setString(79, v8Flag);
					   ps.setString(80, moduleName);
					   //
					   ps.setString(81, v8Memo);
					   ps.setString(82, creator);
					   ps.setString(83, createDate);//null
					   ps.setString(84, modificator);
					   ps.setString(85, modifyDate);//null
					   ps.setInt(86, entryDC);//null

				   }
				   
				   //返回批量条数 也就是执行多少次增删改操作
				   public int getBatchSize()
				   {
					   return tempEntries.size();
				   }
			 }
		  );
		  log.debug("批量持久化凭证操作完成!");
	}
	
	/*
	 * @author sea
	 */
	public String updateMessage(Map<String,String> model,User user) throws DataAccessException, Exception{
		StringBuffer mes = new StringBuffer();
		//构建导出字符串
		//message = "\"凭证输出\",\"V800\",\"001\",\"中投租赁有限责任公司\",\""+Tools.getSystemDate(0).substring(0, 4)+"\" \r\n ";
		mes.append("\"凭证输出\",\"V800\",");
		//获取查询SQL
		 
		String sql = this.getV8Sql(model);
		log.info("sql:"+sql);
		 
		//查询数据
		//List<InterV8Vouchers> list =  this.getBussinessDao().getJdbcTemplate().queryForList(sql, InterV8Vouchers.class);
		List<InterV8Vouchers> list =  this.getBussinessDao().findResultsByHSQL(sql);
		 
		//拼接所属公司的编号及名称
		if(list.size() > 0){
			mes.append("\""+list.get(0).getDeptId().getPropOne()+"\",")
			   .append(" \""+list.get(0).getDeptId().getName()+"\", ")
			   .append("\""+list.get(0).getPeriodYear()+"\",");//年份 \r\n  
		}			
	    //拼接上表头
		//,"F1日期F2类别","F3凭证号F4附单据数","F5摘要F6科目编码","F7借方F8贷方",
		//"F9数量F10外币","F11汇率","F12制单人","F13结算方式","F14票号","F15发生日期","F16部门编码","F17个人编码","F18客户编码","F19供应商编码","F20业务员","F21项目编码","F22自定义项1","F23自定义项2","F24自由项1","F25自由项2","F26自由项3","F27自由项4","F28自由项5","F29自由项6","F30自由项7","F31自由项8","F32自由项9","F33自由项10","F34外部系统标识","F35业务类型","F36单据类型","F37单据日期","F38单据号","F39凭证是否可改","F40分录是否可增删","F41合计金额是否保值","F42数值是否可改","F43科目是否可改","F44受控科目","F45往来是否可改","F46部门是否可改","F47项目是否可改","F48往来项是否必输","F49账套号","F50核算单位","F51会计年度","F52会计期间","F53类别顺序号","F54凭证号","F55审核人","F56记账人","F57是否记账","F58出纳人","F59行号","F60外币名称","数量单位","F61单价","F62科目名称","F63部门名称","F64个人名称","F65客户简称","F66供应商简称","F67项目名称","F68项目大类编码","F69项目大类名称","F70对方科目","F71银行两清标志","F72往来两清标志","F73银行核销标志","F74外部系统名称","F75外部账套号","F76外部会计年度","F77外部会计期间","F78外部制单日期","F79外部系统版本","F80凭证标识","F81分录自动编号","F82唯一标识","F83主管签字","F84自由项11","F85自由项12","F86自由项13","F87自由项14","F88自由项15","F89自由项16","F90审核日期："
		mes.append("\"F1日期F2类别\",\"F3凭证号F4附单据数\",\"F5摘要F6科目编码\",\"F7借方F8贷方\",\"F9数量F10外币\",\"F11汇率\",\"F12制单人\",\"F13结算方式\",\"F14票号\",\"F15发生日期\",\"F16部门编码\",\"F17个人编码\",\"F18客户编码\",\"F19供应商编码\",\"F20业务员\",\"F21项目编码\",\"F22自定义项1\",\"F23自定义项2\",\"F24自由项1\",\"F25自由项2\",\"F26自由项3\",\"F27自由项4\",\"F28自由项5\",\"F29自由项6\",\"F30自由项7\",\"F31自由项8\",\"F32自由项9\",\"F33自由项10\",\"F34外部系统标识\",\"F35业务类型\",\"F36单据类型\",\"F37单据日期\",\"F38单据号\",\"F39凭证是否可改\",\"F40分录是否可增删\",\"F41合计金额是否保值\",\"F42数值是否可改\",\"F43科目是否可改\",\"F44受控科目\",\"F45往来是否可改\",\"F46部门是否可改\",\"F47项目是否可改\",\"F48往来项是否必输\",\"F49账套号\",\"F50核算单位\",\"F51会计年度\",\"F52会计期间\",\"F53类别顺序号\",\"F54凭证号\",\"F55审核人\",\"F56记账人\",\"F57是否记账\",\"F58出纳人\",\"F59行号\",\"F60外币名称\",\"数量单位\",\"F61单价\",\"F62科目名称\",\"F63部门名称\",\"F64个人名称\",\"F65客户简称\",\"F66供应商简称\",\"F67项目名称\",\"F68项目大类编码\",\"F69项目大类名称\",\"F70对方科目\",\"F71银行两清标志\",\"F72往来两清标志\",\"F73银行核销标志\",\"F74外部系统名称\",\"F75外部账套号\",\"F76外部会计年度\",\"F77外部会计期间\",\"F78外部制单日期\",\"F79外部系统版本\",\"F80凭证标识\",\"F81分录自动编号\",\"F82唯一标识\",\"F83主管签字\",\"F84自由项11\",\"F85自由项12\",\"F86自由项13\",\"F87自由项14\",\"F88自由项15\",\"F89自由项16\",\"F90审核日期：\"\r\n");
		
		for (InterV8Vouchers oneVoucher : list) {
			 mes.append("\""+oneVoucher.getF1()+"\",")//F1
			    .append("\""+oneVoucher.getF2()+"\",")//F2
			    .append("\""+oneVoucher.getF3()+"\",")//F3
			    .append(",")//空
			    .append("\""+oneVoucher.getF5().replace("L2", "2")+"\",")//
			    .append("\""+oneVoucher.getF6().getSubjectsCode()+"\",")//科目编码
		    	.append(oneVoucher.getF7()+",")//借
			    .append(oneVoucher.getF8()+",")//贷
		    	.append(oneVoucher.getF9()+",")//固定为0
			    .append(oneVoucher.getF10()+",")//固定为0
			    .append(",")//空
			    .append("\""+user.getUsername()+"\",")//制单人
			    .append(",")//空
			    .append(",")//空
			    .append("\""+oneVoucher.getF15()+"\",");//到账日期
			  Department dept = oneVoucher.getF16();
			  /**
				 *辅助核算1：按部门核算
				 *规则：根据科目信息去科目子表查询是否存在部门类型的辅助核算，存在数据则去取合同的部门编号
				 *F16部门编码	按部门辅助核算时带入部门编码	 取PropTwo属性
				 *F63部门名称	按部门辅助核算时带入部门名称     取propThree属性
			  */
			  if(dept != null){
				  mes.append("\""+dept.getCode()+"\",");//按部门辅助核算时带入部门编码
				  //System.out.println("凭证导出的部门编码:"+list.get(0).getDeptId().getId());
//				 if("ContractBelong1".equals(list.get(0).getDeptId().getId())){//欧力士融资租赁（中国）有限公司
//					 mes.append("\""+dept.getPropTwo()+"\",");//按部门辅助核算时带入部门编码
//				 }else{//欧力士融资租赁 ( 中国 ) 有限公司深圳分公司
//					 mes.append("\""+dept.getPropSix()+"\",");//按部门辅助核算时带入部门编码
//				 }
			  }else{
			     mes.append(",");
			  }
			  
			  mes.append(",");//F17 个人编码为空
			  if(oneVoucher.getF18() != null){
				    //欧力士导入凭证合同号不能有L存在，顾去除L
				    mes.append("\""+oneVoucher.getF18().getContractNumber().replace("L", "")+"\",");//F18客户编码	即为合同号
				    //mes.append("\""+oneVoucher.getF18().getContractNumber()+"\",");//F18客户编码	即为合同号
			  }else{
				  mes.append(",");//F18 客户编码为空
			  }
				  
			  CustInfo custObj =  oneVoucher.getF19();
			  CustOtherNumber custOtherNumber = null;
			  if(custObj != null){
				  //查询不属于上海总部的供应商的编码及名称
				  if("ContractBelong2".equals(list.get(0).getDeptId().getId())){//深圳
					  Map<String, Object> propertiesMap = new  HashMap<String, Object>();
					  propertiesMap.put("custId", custObj);//徐云龙修改，原来为custObj.getId()
					  DictionaryData numberBelong=this.baseDao.findEntityByID(DictionaryData.class, "ContractBelong2");
					  propertiesMap.put("numberBelong", numberBelong);
					  List<CustOtherNumber> custOtherNumbers=new ArrayList<CustOtherNumber>();
					  custOtherNumbers=this.baseDao.findEntityByProperties(CustOtherNumber.class, propertiesMap);
					  if(custOtherNumbers.size()>0){
						  custOtherNumber=custOtherNumbers.get(0);
					  }
					 
				  }
				  //欧力士导入凭证供应商编码为财务系统的对应编码，即custinfo中的custnumber去除 vndr、cust 前缀
				  if(null != custOtherNumber){
					  //深圳的供应商编码从客户对应供应商编码表CUST_OTHER_NUMBER中去取
					  mes.append("\""+custOtherNumber.getCustNumber().replace("vndr", "").replace("cust", "").replace("assur", "").replace("assurp", "")+"\",");//F19供应商编码	按供应商辅助核算时带入供应商编码
				  }else{
					  //不属于深圳的直接去传入时封装的供应商编码
					  mes.append("\""+custObj.getCustNumber().replace("vndr", "").replace("cust", "").replace("assur", "").replace("assurp", "")+"\",");//F19供应商编码	按供应商辅助核算时带入供应商编码
				  }
				  
			  }else{
			     mes.append(",");
			  }
			  
			  mes.append(",");//空
			  if(oneVoucher.getF21() != null ){
				   //欧力士导入凭证合同号不能有L存在，顾去除L
				   mes.append("\""+oneVoucher.getF21().getContractNumber().replace("L", "")+"\",");//F18客户编码	即为合同号
				   //mes.append("\""+oneVoucher.getF21().getContractNumber()+"\",");
			  }else{
				  mes.append(",");
			  }
			  
			  if(oneVoucher.getF22() != null){
				  //mes.append("\""+oneVoucher.getF22().getContractNumber()+"\",");//
				  //欧力士导入凭证合同号不能有L存在，顾去除L
				  mes.append("\""+oneVoucher.getF22().getContractNumber().replace("L", "")+"\",");//F18客户编码	即为合同号
				   
			  }else{
				  mes.append(",");
			  }
			  
			    //23为空
			  mes.append(",");//F23
			  
			  //F24
			  if(oneVoucher.getF24() != null){
				  //欧力士导入凭证合同号不能有L存在，顾去除L
				  mes.append("\""+oneVoucher.getF24().getContractNumber().replace("L", "")+"\",");//
			  }else{
				  mes.append(",");
			  }
			  
			  //25 - 38 为空  其中28和30固定为0
			  //.append("25,26,27,0,29,0,31,32,33,34,35,36,37,38, ")//
			  mes.append(",,,0,,0,,,,,,,,, ")
			    .append("0,")//F39
			    .append("0,")//F40
			    .append("0,")//F41
			    .append("0,")//F42
			    .append("0,")//F43
			    .append(",")//F44 空
			    .append("0,")//F45
			    .append("0,")//F46
			    .append("0,")//F47
			    .append("0,");//F48
			  
			    if(oneVoucher.getF49() != null ){
			    	mes.append("\""+oneVoucher.getF49().getPropOne()+"\",");//帐套 与第三个参数一致
			    }else{
				  mes.append(",");
			    }
			    
			    if(oneVoucher.getF50() != null){
			    	mes.append("\""+oneVoucher.getF50().getName()+"\",");//帐套 与第四个参数一致
			    }else{
			    	mes.append(",");
			    }
			  
			    mes.append(oneVoucher.getF51()+",")//会计年度
			    .append(oneVoucher.getF52()+",")//会计月份
			    .append("1,")//F53 固定为1
			    //54-56空
			    //.append("54,55,56,")//
			    .append(",,,")//
			    .append("0,")//F57
			    .append(",")//F58
			    .append(oneVoucher.getF59()+",")//
			    //60-61 空
			    //.append("60,61,")//
			    .append(",,")//
			    //.append("\""+oneVoucher.getF62().getSubjectsName()+"\",");//F62 
			    .append("0,");//F62暂时为0
		    /**
			 *辅助核算1：按部门核算
			 *规则：根据科目信息去科目子表查询是否存在部门类型的辅助核算，存在数据则去取合同的部门编号
			 *F16部门编码	按部门辅助核算时带入部门编码	 取PropTwo属性
			 *F63部门名称	按部门辅助核算时带入部门名称     取propThree属性
			  */
		    if(dept != null){
				 mes.append("\""+dept.getPropThree()+"\",");//按部门辅助核算时带入部门名称
			}else{
			     mes.append(",");
			}
			 mes.append(",");//F64 空
			 
			 if(oneVoucher.getF65() != null ){//客户名称 F18是客户编码
				 //欧力士导入凭证合同号不能有L存在，顾去除L
				 mes.append("\""+oneVoucher.getF18().getContractNumber().replace("L", "")+"\",");//
			 }else{
				 mes.append(",");
			 }
			 
			 //供应商辅助核算
			if(oneVoucher.getF66() != null){
				 //欧力士导入凭证供应商编码为财务系统的对应编码，即custinfo中的custnumber去除 vndr、cust 前缀
				  if(null != custOtherNumber){
					  //深圳的供应商编码从客户对应供应商编码表CUST_OTHER_NUMBER中去取
					  mes.append("\""+custOtherNumber.getCustName()+"\",");//F19供应商编码	按供应商辅助核算时带入供应商编码
				  }else{
					  mes.append("\""+oneVoucher.getF66().getCustName()+"\",");//
				  }
			}else{
			    mes.append(",");
			}
			if(oneVoucher.getF67() != null && oneVoucher.getF67() != null){
				//欧力士导入凭证合同号不能有L存在，顾去除L
				mes.append("\""+oneVoucher.getF67().getContractNumber().replace("L", "")+"\",");//
		    }else{
			    mes.append(",");
		    }
			//对方科目
			Integer entryDC = oneVoucher.getEntryDC();//1 借方-1 贷方
			String f70 = ""; 
			String f71 = ""; 
			if(!Tools.isNullOrEmpty(entryDC)){
				f71 = this.getF70Str(entryDC,oneVoucher.getF3()); 	
			}
			log.info("对方科目:"+f70);
			if(entryDC == 1){//借方
				f70 = "";
			}else{
				f70 = "组别";
			}
			mes.append("\""+oneVoucher.getF68()+"\",")//
			   .append("\""+oneVoucher.getF69()+"\",")//
			   // F71 对方科目，多个以“，”隔开 F71对方科目  F70组别
			   .append("\""+f70+"\",")//
			   .append("\""+f71+"\",")//
			   //72-90为空 .append("0,73,0,75,76,0,0,79,80,81,82,83,84,85,86,87,88,0,0,")//
			   .append("0,,0,,,0,0,,,,,,,,,,,0,0,")//82 83 存在值
			   //.append("\""+oneVoucher.getF+"\",")//
			   .append("\r\n");
			
			
			//修改导出状态:
			InterV8Vouchers v8Obj = oneVoucher;
			oneVoucher.setV8Flag("1");//title:'未导出',name:'0'},{title:'已导出',name:'1'
			oneVoucher.setV8Memo("已导出TXT文件");
			oneVoucher.setExp_date(DateUtils.getSystemDate(3));
			this.baseDao.updateEntity(v8Obj);
			
		}
		
		return mes.toString();
	}
	
	/**
	  * <p>对方科目，多个以“，”隔开。</p>
	  * <p>借方情况下，取所有贷方分录集合 。</p>
	  * <p>贷方情况下，取所有借方分录集合。</p>
	  * @author sea 
	  * @param entryDC  1 借方-1 贷方
	  * @param f3  该条凭证分录对应的凭证号
	  * @return
	 * @throws Exception 
	 */
	private String getF70Str(Integer entryDC,String f3) throws Exception{
		String f70 = "";//对方分录集合 
		
		String HSQL = "";
		
		if(entryDC == 1){//借方查询所有贷方分录的科目编码
			HSQL = " FROM  InterV8Vouchers VO WHERE  entryDC = -1 AND f3 = '"+f3+"' ";
		}else{//贷方查询所有借方分录的科目编码
			HSQL = " FROM  InterV8Vouchers VO WHERE  entryDC = 1 AND f3 = '"+f3+"'   ";
		}
		List<InterV8Vouchers> list =  this.getBussinessDao().findResultsByHSQL(HSQL);
		//循环拼接对方科目
		for (InterV8Vouchers oneV8 : list) {
			f70 = f70 + oneV8.getF6().getSubjectsCode()+",";
		}
		
		//
		if(f70.length() > 0){
			f70 = f70.substring(0, f70.length()-1);
		}
		
		return f70;
	}
	
	/**
	  * <p>拼装要导出凭证的查询SQL语句，目前返回的值HSQL，SQL未返回。</p>
	  * @author sea
	  * @param model 查询条件
	  * @return
	 */
	private String getV8Sql(Map<String,String> model){
		//凭证号"vouchernumber":"20140221172690"
		System.out.println("222");
		System.out.println("params"+model.get("params"));
		String vouchernumber = model.get("vouchernumber");//凭证号
		String moduleName = Tools.getDBStr( model.get("modlename") );//隐藏值
		String contract_number = model.get("contract_number");//合同号
		//String url = model.get("url");//xml路径
		//this.tableService.getJsonArrayData("eleasing/workflow/proj_approval/proj_fund_cash_flow.xml", queryMainObjectMap)
		String f1_start =  Tools.getDBDateStr( model.get("f1_start") );//记账日期开始
		String f1_end = Tools.getDBDateStr( model.get("f1_end") );//记账日期截止
		String f15_start = Tools.getDBDateStr( model.get("f15_start") );//业务日期开始
		String f15_end = Tools.getDBDateStr( model.get("f15_end") );//业务日期截止
		String status = model.get("status_");//凭证状态
		String v8Flag = model.get("v8flag");//导出状态
		String dept_name = model.get("dept_name");//所属公司名称
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select  VOUCHER_V8.*  ")
		/*.append("  VOUCHER_V8.id, ")
		.append(" 	VOUCHER_V8.EVIDENCE_MESSAGE             as evidence_message, ")
		.append(" 	VOUCHER_V8.EVIDENCE_TYPE                as evidencetype, ")
		.append("	VOUCHER_V8.Dept_Id                      as dept_uuid, ")
		.append("	dict1.DATA_KEY1                         as dept_id, ")
		.append("	dict1.NAME_                             as dept_name, ")
		.append("	VOUCHER_V8.PERIODYEAR				    as periodyear, ")
		.append("	VOUCHER_V8.F1						    as f1, ")
		.append("	VOUCHER_V8.F2 						    as f2, ")
		.append("	VOUCHER_V8.F3                           as vouchernumber, ")
		.append("	VOUCHER_V8.F5						    as f5, ")
		.append("	VOUCHER_V8.F6 						    as f6, ")
		.append("	VOUCHERASS_STACTS_CONFIG.Subjects_Code  as subjects_code, ")
		.append("	VOUCHERASS_STACTS_CONFIG.Subjects_Name  as subjects_name, ")
		.append("	VOUCHERASS_STACTS_CONFIG.Subjects_Id    as subjects_id, ")
		.append("	VOUCHERASS_STACTS_CONFIG.Subjects_Owner as subjects_owner, ")
		.append("	VOUCHER_V8.F7 						    as f7, ")
		.append("	VOUCHER_V8.F8 						    as f8, ")
		.append("	VOUCHER_V8.F9 						    as f9, ")
		.append("	VOUCHER_V8.F10 						    as f10, ")
		.append("	 VOUCHER_V8.F12 					    as f12, ")
		.append("	VOUCHER_V8.F15 						    as f15, ")
		.append("	VOUCHER_V8.F16                          as t_depts_uuid, ")
		.append("	T_DEPTS.Code_                           as f16, ")
		.append("	T_DEPTS.NAME_                           as f63, ")
		.append("	VOUCHER_V8.F18 						    as f18, ")
		.append("	contract_info.contract_number           as contract_number, ")
		.append("	VOUCHER_V8.F19 						    as f19, ")
		.append("	CUST_INFO.Cust_Number                   as cust_number, ")
		.append("	CUST_INFO.Cust_Name                     as f66, ")
		.append("	VOUCHER_V8.F49                          as f49uuid, ")
		.append("	dict1.DATA_KEY1                         as f49, ")
		.append("	dict1.NAME_                             as f49name, ")
		.append("	VOUCHER_V8.F52 						    as f52, ")
		.append("	VOUCHER_V8.F59 						    as f59, ")
		.append("	VOUCHER_V8.F68 						    as f68, ")
		.append("	VOUCHER_V8.F69 						    as f69, ")
		.append("	VOUCHER_V8.Module_Name                  as modlename, ")
		.append("	VOUCHER_V8.Status_ 					    as status_, ")
		.append("	VOUCHER_V8.V8_Flag                      as v8flag, ")
		.append("	VOUCHER_V8.V8_Memo                      as v8memo, ")
		.append("	VOUCHER_V8.Exp_Date                     as expdate, ")
		.append("	VOUCHER_V8.Generate_Date                as generatedate ")*/
       .append(" from VOUCHER_V8 ")
       .append(" left join T_DICTS_DATAS dict1 on dict1.Id_ = VOUCHER_V8.Dept_Id ")
       .append(" left join contract_info on contract_info.id = VOUCHER_V8.F21 ")
       .append(" left join VOUCHERASS_STACTS_CONFIG on VOUCHERASS_STACTS_CONFIG.Id = VOUCHER_V8.F6 ")
       .append(" left join T_DEPTS on T_DEPTS.Id_ = VOUCHER_V8.F16 ")
       .append(" left join CUST_INFO on CUST_INFO.Id = VOUCHER_V8.F19 ")
       .append(" WHERE 1=1  ")
       .append("  ");
		
		String hql = " FROM InterV8Vouchers AS VO WHERE VO.v8Flag = '0' ";
		if(!Tools.isNullOrEmpty(vouchernumber)){
			hql = hql + " AND VO.f3 = '"+vouchernumber+"' ";
			sql.append(" and VOUCHER_V8.vouchernumber = '"+vouchernumber+"' ");
		}
		if(!Tools.isNullOrEmpty(moduleName)){
			hql = hql + " AND VO.moduleName = '"+moduleName+"' ";
			sql.append(" and  VOUCHER_V8.modlename = '"+moduleName+"'  ");
		}
		if(!Tools.isNullOrEmpty(contract_number)){
			//hql = hql + " AND VO.f67.contractNumber = '"+contract_number+"' ";
			
			String serch_sql = " select distinct a.f3  as vouchernumber from VOUCHER_V8 a  left join CONTRACT_INFO b  ";
			serch_sql = serch_sql + " on  ( ";
			serch_sql = serch_sql + " a.f18 = b.id ";
			serch_sql = serch_sql + " or a.f21 = b.id ";
			serch_sql = serch_sql + " or a.f22 = b.id ";
			serch_sql = serch_sql + " or a.f24 = b.id ";
			serch_sql = serch_sql + " or a.f65 = b.id ";
			serch_sql = serch_sql + " or a.f67 = b.id ";
			serch_sql = serch_sql + " ) ";
			serch_sql = serch_sql + " where b.contract_id like '%"+contract_number+"%'  ";
			serch_sql = serch_sql + "  and a.V8_Flag = '0' ";
			if(!Tools.isNullOrEmpty(moduleName)){
				serch_sql = serch_sql + "  and a.module_name = '"+moduleName+"' ";
			}
			log.info("根据合同号查询凭证号:"+serch_sql);
			try {
				List<Map<String, Object>> list = this.baseDao.getJdbcTemplate().queryForList(serch_sql);
				String strs = "";
				for (int i = 0; i < list.size(); i++) {
					log.info("凭证号："+list.get(i).get("vouchernumber"));
					strs = strs +"'"+list.get(i).get("vouchernumber")+"',";
				}
				
				if(strs.length() > 0){
					strs = strs.substring(0, strs.length()-1);
					hql = hql + " AND VO.f3 in ( "+strs+" )";
				}
				
			} catch (DataAccessException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			sql.append("  and  contract_info.contract_number like '%"+contract_number+"%'  ");
		}
		if(!Tools.isNullOrEmpty(f1_start)){
			hql = hql + " AND VO.f1 >= '"+f1_start+"'  ";
			sql.append(" and VOUCHER_V8.f1 >= '"+f1_start+"'  ");
		}
		if(!Tools.isNullOrEmpty(f1_end)){
			hql = hql + " AND VO.f1 <= '"+f1_end+"'  ";
			sql.append(" and VOUCHER_V8.f1 <= '"+f1_end+"'  ");
		}
		if(!Tools.isNullOrEmpty(f15_start)){
			hql = hql + " AND VO.f15 >= '"+f15_start+"' ";
			sql.append(" and VOUCHER_V8.f15 >= '"+f15_start+"' ");
		}
		if(!Tools.isNullOrEmpty(f15_end)){
			hql = hql + " AND VO.f15 <= '"+f15_end+"' ";
			sql.append(" and VOUCHER_V8.f15 <= '"+f15_end+"'  ");
		}
		if(!Tools.isNullOrEmpty(status)){
			hql = hql + " AND VO.status = '"+status+"' ";
			sql.append(" and VOUCHER_V8.status_ = '"+status+"' ");
		}
		//{title:'未导出',name:'0'},{title:'已导出',name:'1'}
		if(!Tools.isNullOrEmpty(v8Flag)){
			hql = hql + " AND VO.v8Flag = '"+v8Flag+"' ";
			sql.append(" and VOUCHER_V8.V8_Flag = '"+v8Flag+"' ");
		}
		
		//增加一个按所属公司查询的
		if(!Tools.isNullOrEmpty(dept_name)){
			hql = hql + " AND VO.deptName.name like '%"+dept_name+"%' ";
			
			sql.append(" and dict1.NAME_ like '%"+dept_name+"%' ");
		}
		
		/*
		else{//默认导出状态为：未导出
			sql.append(" and VOUCHER_V8.V8_Flag = '0' ");
		}
		*/
		//先根据凭证号再根据凭证分录序号排序
		hql = hql + " ORDER BY f3,f59 ";
		return hql.toString();
	}
	
	
	/*
	 * @author sea
	 */
	public void saveFileList(String url, User user,String message) throws Exception {
		//String sql = "insert into inter_evidence_download(down_date,down_url,create_date,creator,delete_flag,export_flag) values (getdate(),'"+url+"',getdate(),'"+czyid+"','有效','已导出')";
		
		IntereasVoucherLog intereasVoucherLog = new IntereasVoucherLog();
		intereasVoucherLog.setMemo(url);
		intereasVoucherLog.setMessage(message);
		intereasVoucherLog.setCreator(user);
		intereasVoucherLog.setCreateDate(DateUtils.getSystemDate(3));
		this.baseDao.saveEntity(intereasVoucherLog);
		
	}
	
	
	
	//反射取值
	public void getString(InterV8Vouchers interV8Vouchers){
		PropertyDescriptor[] ps = BeanUtils.getPropertyDescriptors(interV8Vouchers.getClass());
	     String str1 = "";
	     String str2 = "";
		for (PropertyDescriptor p : ps)
		{
			Method m = p.getReadMethod();
			try {
				Object v = m.invoke(interV8Vouchers);
				if(v!= null && v.getClass().getName().equals("".getClass().getName())){
					String s = (String)v;
					System.out.println("名称:" + p.getName() +"  || 长度:" + s.length()+"  || 值:" + s);
					str1 = str1 + ""+p.getName()+""+",";
					str2 = str2 + "'"+s+"'"+",";
				}
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 获取读get方法
			catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("str1:" + str1);
		System.out.println("str2:" + str2);
	}

	@Override
	public String saveEvidenceInfo(Map<String, String> headMap, List<Map<String, String>> list, User user)
			throws DataAccessException, Exception {
		String v8Str = "";
		try {
			///List list_all=new ArrayList();
			//转换接口字段
            for (int i = 0; i < list.size(); i++) {
				Map<String,String> map1 = list.get(i);
				if(map1.containsKey("voucherSummary")){
				   map1.put("EVIDENCE_SUMMARY", map1.get("voucherSummary"));//摘要
				  
				}
				if(map1.containsKey("subjectsId")){
				   map1.put("SUBJECT_NUMBER", map1.get("subjectsId"));//科目编码ID
				}
				if(map1.containsKey("debitMoney")){
				   map1.put("DEBIT", map1.get("debitMoney"));//借方金额
				}
				if(map1.containsKey("lenderMoney")){
				   map1.put("CREDIT", map1.get("lenderMoney"));//贷方金额
				}
				if(map1.containsKey("ebankFactDate")){
				   map1.put("EBANK_FACT_DATE", map1.get("ebankFactDate"));//网银到账日期
				}
				if(map1.containsKey("accountDate")){
				   map1.put("HAPPEN_DATE", map1.get("accountDate"));//会计处理日期
				}
			
            }
			String message = "";
			//当前登录人对象获取
	    	String creator = user.getId();//制单人,当前登录人对象 
	    	String status = "已完整";//凭证状态
	    	String eas_flag  = "0";//导入财务系统状态 默认0
	    	String moduleName = headMap.get("moduleName") ;
	    		
			String contract_id = Tools.getStr( headMap.get("contract_id") );//获取合同id	
		 // ContractInfo INTER_CONTRACT_ID =	this.tableService.findEntityByID(ContractInfo.class, contract_id);
			//网银导入、代收租金 的凭证不需要合同号
			if(!moduleName_value.equals(moduleName) && !SPECIAL_MODULENAME.equals(moduleName) && Tools.isNullOrEmpty(contract_id)){
				log.error("合同号为空,无法构建对应的凭证信息!");
				return null;
			}
			
			String accNumber = headMap.get("accNumber");//网银编号
			if( (moduleName_value.equals(moduleName) || SPECIAL_MODULENAME.equals(moduleName) ) && Tools.isNullOrEmpty(accNumber)){
				log.error("本方银行账号为空,无法构建对应的凭证信息!");
				return null;
			}
			//所属公司，目前项目只有一个
			DictionaryData dict =this.baseDao.findEntityByID(DictionaryData.class, "ContractBelong1");
			String companyname=dict.getName();//公司名称
			String companycode=dict.getCode();//公司编码
		    /*List<OwnAccount> own=this.baseDao.findResultsByHSQL("from OwnAccount");		    		    
			OwnAccount OwnAccount = own.get(0);
			OwnInfo owninfo=OwnAccount.getOwnId();
			String ownName=owninfo.getOwnName();
			String ownNumber=owninfo.getOwnNumber();*/
			Department projDept = null;
			ContractInfo contractInfo = null;//合同基本信息表
			ContractInfo oldcontractinfo = null;//老合同转新合同时，老合同对应信息
			
			if(!Tools.isNullOrEmpty(headMap.get("oldContractid"))){
				oldcontractinfo = this.findEntityByID(ContractInfo.class, headMap.get("oldContractid"));
			}
			
			//网银、代收租金情况下不存在合同相关信息
			if(!moduleName_value.equals(moduleName) && !SPECIAL_MODULENAME.equals(moduleName)){
				contractInfo = this.findEntityByID(ContractInfo.class, contract_id);
				
				if(Tools.isNullOrEmpty(contractInfo.getId())){
					message = message + "合同信息为空!";
					status = "未完整";//凭证状态
				}
				//项目出单部门
				projDept = contractInfo.getDepartment();
				//合同其他信息表 包含签约信息
				//ContractOtherInfo contractOtherInfo = contractInfo.getContractOtherInfo();
				//字段三四  凭证所属公司编码及其名称
//				dict = contractOtherInfo.getContractBelong();
//				if(dict == null && ( !moduleName_value.equals(moduleName) || !SPECIAL_MODULENAME.equals(moduleName) ) ){
//					message = message + "所属公司编码为空!";
//					status = "未完整";//凭证状态
//				}
				//一个合同对应多个租赁物件信息
				//Set<ContractEquip> contractEquips = contractInfo.getContractEquips();
				//合同对应客户信息
				//CustInfo custInfo = contractInfo.getCustId();
			}else{
				//根据网银编号去查询(OWN_ACCOUNT)归属ACCOWNER及科目序号accSubject
				Map<String, Object> propertiesMap = new HashMap<String, Object>();
				propertiesMap.put("accNumber", accNumber);
				OwnAccount  ownAccount = this.baseDao.findEntityByProperties(OwnAccount.class, propertiesMap).get(0);
				//构建网银本方账号对应的归属的数据字典对象
				//dict = ownAccount.getAccowner();
			}
			
			String RECORDING_VOUCHER = "记";
			//获取凭证号
			String  EVIDENCE_NUMBER = intereasVoucherheadService.getVoucherNumber();
			
			//F4 附单据数为空
			List<InterEvidenceInfo> interevidenceinfo =new  ArrayList<InterEvidenceInfo>();
			//List<InterV8Vouchers> v8Vouchers = new ArrayList<InterV8Vouchers>();
			Double debit = 0.00;//借方金额 原币金额 贷就为0
			//Double f8 = 0.00;//贷方金额 原币金额 借就为0
			Integer entryDC = 0;//1 借方-1 贷方===================
			//
			BigDecimal DebitCount = BigDecimal.ZERO;//借方金额总计
			BigDecimal CreditCount = BigDecimal.ZERO;//贷方金额总计
			for (int i = 0; i < list.size(); i++) {
				
				Map<String,String> map2 = list.get(i);
				//会计记账日期
				//String F1 = Tools.getDBDateStr( map2.get("F1") );	//2014-11-28: Tools.getDBDateStr("2014-9-19")返回""。因为函数内调用date.substring(0, 10)时抛出下标越界异常
				String HAPPEN_DATE = DateUtils.date2Str(map2.get("HAPPEN_DATE"));
				
				//摘要
				String EVIDENCE_SUMMARY =  map2.get("EVIDENCE_SUMMARY") ;
				//科目编号subjectsId
				String SUBJECT_NUMBER = Tools.getStr( map2.get("subjectsId") );//这时取出的是科目序号				
				if(Tools.isNullOrEmpty(SUBJECT_NUMBER)){
					message = message + "科目序号为空!";
					status = "未完整";//凭证状态
				}
				VoucherassStactsConfig  config = this.seachConfig(SUBJECT_NUMBER, dict);
				if(Tools.isNullOrEmpty(config.getSubjectsCode())){
					message = message + "科目相关信息获取失败!";
					status = "未完整";//凭证状态
				}
				SUBJECT_NUMBER = config.getSubjectsCode();//科目编号
				String SUBJECT_NAME =config.getSubjectsName();
				
				BigDecimal DEBIT  = BigDecimal.valueOf( MathExtend.parseDouble(  MoneyUtils.getZeroStr( map2.get("DEBIT") ) ) );//借方金额/F7   原币金额  贷方金额为0
				DebitCount = DebitCount.add(DEBIT);
				BigDecimal CREDIT = BigDecimal.valueOf( MathExtend.parseDouble(  MoneyUtils.getZeroStr( map2.get("CREDIT") ) ) );//贷方金额/F8   原币金额  借方金额为0
				CreditCount = CreditCount.add(CREDIT);
				
				debit= NumberUtils.parseDouble( MoneyUtils.getZeroStr( String.valueOf( DEBIT ) ) );//借方金额
				//f8 = NumberUtils.parseDouble( MoneyUtils.getZeroStr( String.valueOf( F8 ) ) );//贷方金额
				//如果两个数一样则返回0，如果第一个数比第二个数大则返回1，反之返回-1 
				if(NumberUtils.compareTo(debit, 0.00) != 0){//借方金额 不等于0.00  该条分录是 借
					entryDC = 1;//1 借方   -1 贷方
				}else{//贷 
					entryDC = -1;//1 借方 -1 贷方
				}
				
				/*Integer F9 = 0;//数量
				String F10 = "0";//外币				
                 */				
				//F11 为空
				//String F12 = user.getRealname();//制单人
				//F13 F14 2个 为空
				//业务发生日期
				String date = map2.get("EBANK_FACT_DATE");//F15
				if(Tools.isNullOrEmpty(date)){
					log.error("业务日期为空，无法产生凭证信息!");
					throw new Exception("业务日期为空，无法产生凭证信息!!!!!!!!");
				}
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				//String F15 = sdf.format( sdf.parse(date) );
				String EBANK_FACT_DATE = DateUtils.date2Str(date);
				if(Tools.isNullOrEmpty(EBANK_FACT_DATE)){
					log.error("业务日期获取失败!");
					message = message + "业务日期获取失败!";
					status = "未完整";//凭证状态
				}
				
				//会计期间-年  以下2个变量都是
				String periodYear = "";
				String ACC_YEAR = "";
				//会计期间-月
				String ACC_MONTH = "";
				
				//自定义会计记账日为空前提下
				/**
				 * 2014-09-10 会计处理日原则变更处理如下
				 * 前端传入的会计处理日为空，则根据业务日期及关帐日函数去处理
				 * 前端传入的会计处理日不为空，则根据会计处理日及关帐日函数取处理
				 */
				if(Tools.isNullOrEmpty(HAPPEN_DATE)){
					//根据业务操作日期查询会计记账日
					HAPPEN_DATE = intereasVoucherheadService.getBookDate(EBANK_FACT_DATE);
				}else{
					HAPPEN_DATE = intereasVoucherheadService.getBookDate(HAPPEN_DATE);
				}
				
				if(Tools.isNullOrEmpty(HAPPEN_DATE)){
					log.error("财务记账日期获取失败!");
					message = message + "财务记账日期获取失败!";
					status = "未完整";//凭证状态
				}else{
					periodYear = HAPPEN_DATE.substring(0,4);//会计期间-年 
					ACC_YEAR = HAPPEN_DATE.substring(0,4);//会计期间-年 
					ACC_MONTH = HAPPEN_DATE.substring(5,7);//会计期间-月
				}
				
				//按部门辅助核算时带入部门编码,根据科目去科目子表查询是否存在部门类型的辅助核算，存在数据则去取合同的部门编号
				/*Department F16 = null;
				Department F63 = null;*/
				//按供应商辅助核算时带入供应商名称 与上方一致
				CustInfo VNDR_ID = null;
				//CustInfo F66 = null;
				//按合同辅助核算 F22自定义项1 /F44 自由项1  
				//ContractInfo F22 = null;
				//ContractInfo F24 = null;
				//按项目辅助核算  
				ContractInfo PROJ_ID = null;//项目编码F21
				ContractInfo INTER_CONTRACT_ID = null;//项目名称				
				//按客户辅助核算
				ContractInfo CLIENT_ID = null;//F18客户编码
				
				//ContractInfo F65 = null;//F65客户名称
				
				String configid = config.getId();//科目主配置表的主键
				log.info("configid:"+configid);
				
				//获取配置子表的完整信息,主要是统计该科目存在多少个辅助核算信息
				Map<String,Object> propertiesMap = new HashMap<String, Object>();
				propertiesMap.put("configid", config);
				propertiesMap.put("status", 0);//0是有效； 1为作废
				//根据具体的字段条件查询一个entity，条件使用map封装
				List<VoucherConnection>  voucherConnection_l = this.getBussinessDao().findEntityByProperties(VoucherConnection.class, propertiesMap);
				
				//F17个人编码 空 F18在下方赋值
				
				/**
				 * 网银导入及代收租金情况下无辅助核算信息产生
				 * U8凭证中辅助核算配置生成规则伪码说明(具体实现见OCC工程VoucherToV8Impl.java中代码实现及数据库中对应表数据)：
				 * 配置1：请在科目主配置表(VOUCHERASS_STACTS_CONFIG)根据具体的科目编码配置科目名称，科目所属公司，科目序号等信息。
				 * 		 相同科目录属不同公司或不同科目录属同一公司或不同科目录属不同公司如下配置:
				 * 		  例如：相同科目录属不同公司  相同科目不同公司科目序号必须一致
				 * 			科目序号：109、科目名称：应收租赁款 、科目编码:16102、所属公司：欧力士上海公司
				 * 			科目序号：109、科目名称：应收租赁款 、科目编码:16102、所属公司：欧力士深圳分公司
				 * 		  例如：不同科目录属同一公司
				 * 			科目序号：112(不允许重复)、科目名称：预收账款、科目编码:20606、所属公司：欧力士上海公司
				 * 			科目序号：113(不允许重复)、科目名称：银行存款、科目编码:102****、所属公司：欧力士上海公司
				 * 		  例如：不同科目录属不同公司
				 * 			科目序号：114(不允许重复)、科目名称：银行存款、科目编码:119、所属公司：欧力士上海公司
				 * 			科目序号：115(不允许重复)、科目名称：其他应付款-期末退还保证金、科目编码:21801、所属公司：欧力士深圳公司
				 * 配置2：一行科目信息在主配置表配置完成后，需在科目子配置表(VOUCHER_CONNECTION)配置对应的科目辅助核算信息。
				 * 辅助核算项目前存在8项，分别如下(辅助核算表：VOUCHERASSSTACTS_INFO，该表数据不允许随意删除及修改，只可增加)：
				 *  辅助核算唯一字符串标示符          唯一数字标示符      辅助核算中文名称    是否已启用
				 * voucherassstacts_info_id_1	  	01			客户  			已使用 
				 * voucherassstacts_info_id_2		02			经销商			已使用
				 * voucherassstacts_info_id_3		03			银行账户			未使用
				 * voucherassstacts_info_id_4		04			合同	           	已使用
				 * voucherassstacts_info_id_5		05			部门 			已使用
				 * voucherassstacts_info_id_6		06			供应商 			已使用
				 * voucherassstacts_info_id_7		07			金融机构 			未使用
				 * voucherassstacts_info_id_8		08			项目  			已使用
				 * ....其它数据增加请在此处加上对应注释，方便开发人员的开发和维护
			     * 科目子配置表需主配置表增加一条科目信息后增加对应的子配置信息(辅助核算信息),例如：
				 *      主表存在上海公司科目为16102的一条科目信息，该科目存在项目辅助核算，则字表具体格式如下：
				 *      科目16102对应主表中的UUID，辅助核算表为项目的唯一字符串标示符‘voucherassstacts_info_id_8’
				 *      
				 * 查询规则：
				 * 1.前台传入科目序号，根据需要查询该序号对应科目主表的信息
				 * 2.根据主表中UUID去子表查询所有属于该科目的辅助核算信息，遍历辅助核算信息，产生具体的辅助核算项
				 * 注意事项：
				 *    欧力士项目中存在：部门、供应商、合同、项目、客户五种核算项，
				 *    其中‘合同、项目、客户’这三种核算在科目配置中配置的是对应的辅助核算类型，
				 *    但是他们辅助核算的内容目前都为‘合同号’
				 */			
				if(!moduleName_value.equals(moduleName) && !SPECIAL_MODULENAME.equals(moduleName) && voucherConnection_l.size() > 0){//网银情况下不存在辅助核算项
					for (int j = 0; j < voucherConnection_l.size(); j++) {
						VoucherConnection voucherConnection = voucherConnection_l.get(j);
						//辅助账类型编号
						VoucherassStactsInfo obj = voucherConnection.getAsstActType();
						String voucherassStactsInfoId = Tools.getStr( obj.getId() );//辅助账类型表主键
						/**
						 *辅助核算1：按部门核算
						 *规则：根据科目信息去科目子表查询是否存在部门类型的辅助核算，存在数据则去取合同的部门编号
						 *F16部门编码	按部门辅助核算时带入部门编码	
						 *F63部门名称	按部门辅助核算时带入部门名称 
						 */				
						/*if("voucherassstacts_info_id_5".equals(voucherassStactsInfoId)){
							if(projDept == null){
								log.error("部门为空，辅助核算项无法产生!");
								message = message + "部门辅助核算项无法产生!";
								status = "未完整";//凭证状态
							}else{
								F16 = projDept;
								F63 = F16;
							}
						}*/
						
						/**
						 *辅助核算2：按供应商核算
						 *规则：根据科目信息去科目子表查询是否存在供应商类型的辅助核算，存在数据则根据供应商编码去取供应商相关信息
						 *F19 供应商编码	
						 *F66供应商简称
						 */
						if("voucherassstacts_info_id_6".equals(voucherassStactsInfoId)){
							String vndrId = map2.get("VNDR_ID");
							VNDR_ID = this.getBussinessDao().findEntityByID(CustInfo.class, vndrId);
							//按供应商辅助核算时带入供应商名称 与上方一致
							//F66 = F19;
							if(VNDR_ID == null ){
								log.error("供应商为空，辅助核算项无法产生!");
								message = message + "供应商辅助核算项无法产生!";
								status = "未完整";
							}
						}
						
						/**
						 *辅助核算3：按合同核算
						 *规则：根据科目信息去科目子表查询是否存在合同类型的辅助核算，存在数据则根据供应商编码去取合同相关信息
						 *F22自定义项1	按合同辅助核算	自定项1 辅助核算为合同时才插入合同号
						 *F24自由项1	         按合同辅助核算	自由项1 辅助核算为合同时才插入合同号
						 *//*
						if("voucherassstacts_info_id_4".equals(voucherassStactsInfoId)){
							//F22自定义项1 
							if("起租流程-旧案件转新案件".equals(moduleName) && i == 1){
								F22 = oldcontractinfo;
							} else {
								F22 = contractInfo;
							}
							F24 = F22;
							if(null == contractInfo || null == F24){
								log.error("合同信息为空，辅助核算项无法产生!");
								message = message + "合同辅助核算项无法产生!";
								status = "未完整";
							}
						}*/
						
						/**
						 *辅助核算4：按项目核算
						 *规则：根据科目信息去科目子表查询是否存在项目的辅助核算，存在数据则取合同编号
						 *F21项目编码	按合同辅助核算	
						 *F67项目名称	按项目辅助核算带合同号
						 */
						if("voucherassstacts_info_id_8".equals(voucherassStactsInfoId)){
							//F21项目编码 
							if("起租流程-旧案件转新案件".equals(moduleName) && i == 1){//分录2
								PROJ_ID = oldcontractinfo;
							} else {
								PROJ_ID = contractInfo;
							}
							INTER_CONTRACT_ID = PROJ_ID;
							if(PROJ_ID == null){
								log.error("合同信息为空，项目辅助核算项无法产生!");
								message = message + "项目辅助核算项无法产生!";
								status = "未完整";
							}
						}
						
						/**
						 *辅助核算5：按客户核算
						 *规则：根据科目信息去科目子表查询是否存在客户的辅助核算，存在数据则取合同编号
						 *F18客户编码	合同号
						 *F65客户名称	合同号
						 */
						if("voucherassstacts_info_id_1".equals(voucherassStactsInfoId)){
							//F18客户编码  客户就是合同，直接存合同号
							if("起租流程-旧案件转新案件".equals(moduleName) && i == 1){//分录2
								CLIENT_ID = oldcontractinfo;
							} else {
								CLIENT_ID = contractInfo;
							}
						//	F65 = F18;
							if(CLIENT_ID == null){
								log.error("合同信息为空，项目辅助核算项无法产生!");
								message = message + "项目辅助核算项无法产生!";
								status = "未完整";
							}
						}
						//其它辅助核算信息生成判断..........
						//...........do something
					}
				}
				
				//F20业务员 空
				//F21项目编码  上方赋值
				//F22自定义项1 在上方赋值
				//F23-F33 F34 F35业务类型-F38 都为空
				/*String F39 = "0";
				String F40 = "0";
				String F41 = "0";
				String F42 = "0";
				String F43 = "0";
				//F44 为空
				String F45 = "0";
				String F46 = "0";
				String F47 = "0";
				String F48 = "0";*/
				//暂时定为帐套
				/*DictionaryData ACC_SET_NUMBER = dict;//公司编码
				DictionaryData ACCOUTING_UNIT = dict;//公司名称
*/				
				//String F53 = "1";
				//F54F55F56三个为空
				//String F57 = "0";
				//F58 为空
				String LINE_NUMBER = String.valueOf( i+1 );//行号
				//F60F61两个为空
				//String subjectsName = config.getSubjectsName();//科目名称
				//VoucherassStactsConfig SUBJECT_NAME = config;
				//F63在上方赋值
				//F64 为空
				//F65在上方赋值
				//F66 F67在上方赋值
				//String F68	= "00";//大类编码 暂定
				//String F69	= "组别";//大类名称
				
				//F70	对方科目，多个以“，”隔开	借就全取贷方的所有，贷就取所有借的 表中暂时存入空
				//F71-F90为空
				
				//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@华丽的分割线@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				//构建待持久化的V8凭证完整对象信息，一行分录是一个对象
				INTER_CONTRACT_ID =	this.tableService.findEntityByID(ContractInfo.class, contract_id);//自己加的				
				CustInfo custInfo=INTER_CONTRACT_ID.getCustId();
				
				InterEvidenceInfo evidenceObj=new InterEvidenceInfo();
			//	InterV8Vouchers v8Obj = new InterV8Vouchers();
				evidenceObj.setHappenDate(HAPPEN_DATE);//会计处理日
				evidenceObj.setRecordingVoucher(RECORDING_VOUCHER);//记账凭证
				evidenceObj.setEvidenceNumber(EVIDENCE_NUMBER);//凭证号
				evidenceObj.setEvidenceSummary(EVIDENCE_SUMMARY);//摘要
				evidenceObj.setSubjectNumber(config);//科目编号6				evidenceObj.setDebit(DEBIT);//借方
				evidenceObj.setCredit(CREDIT);//贷方
				evidenceObj.setDebit(DEBIT);//贷方
				evidenceObj.setEbankFactDate(EBANK_FACT_DATE);//网银到账日期
				evidenceObj.setClientId(custInfo);//客户编号
				evidenceObj.setVndrId(VNDR_ID);//供应商编码
				evidenceObj.setAccSetNumber(companycode);//公司编码
				evidenceObj.setAccoutingUnit(companyname);//公司名称
				evidenceObj.setAccYear(ACC_YEAR);//年
				evidenceObj.setAccMonth(ACC_MONTH);//月
				evidenceObj.setLineNumber(LINE_NUMBER);//行号
				evidenceObj.setSubjectName(SUBJECT_NAME);//科目名称
				evidenceObj.setInterContractId(INTER_CONTRACT_ID);//合同编号contract_id
				evidenceObj.setCreator(user);//创建人
				evidenceObj.setVoucherStatus(status);//凭证状态
				evidenceObj.setExpFlag(eas_flag);//导出状态
				evidenceObj.setModuleName(moduleName);
				evidenceObj.setGenerateDate(DateUtils.getSystemDate(3));
				evidenceObj.setEntryDC(entryDC);
				evidenceObj.setRollBack("0");				
				/*v8Obj.setEvidenceMessage("凭证输出");
				v8Obj.setEvidenceType("U800");
				v8Obj.setDeptId(dict);
				v8Obj.setDeptName(dict);
				v8Obj.setPeriodYear(periodYear);*/				
				interevidenceinfo.add(evidenceObj);				
				//this.getString(v8Obj);
			}	
			log.info("凭证分录数量:"+interevidenceinfo.size());
			//批量持久化操作
			//FIXME:sea 这里存在2种凭证产生方式，应该取效率最快的为准
			if( DebitCount != BigDecimal.ZERO && CreditCount != BigDecimal.ZERO ){//凭证借贷双方各自总价值大于0才可以产生凭证
				//实体方式保存凭证
				this.saveAllEntities(interevidenceinfo);	
			}
			//批量JDBC方式产生凭证
			//this.saveVouchersToPLJDBC(v8Vouchers);
		
		} catch (Exception e) {
			//徐云龙及吕永辉两位强烈建议这里把整个一起做try catch处理，后人如果改这里千万不要骂原作者，因为之前的异常处理方式是通过方法把异常抛到上层方法中的
			e.printStackTrace();
			throw new Exception("凭证生成出错!");
		}
		return v8Str;
	
	}
}
