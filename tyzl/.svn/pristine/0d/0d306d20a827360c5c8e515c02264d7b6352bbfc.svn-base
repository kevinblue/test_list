package com.tenwa.leasing.serviceImpl.voucher;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.UUIDUtil;
import com.tenwa.leasing.entity.base.OwnAccount;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoCompany;
import com.tenwa.leasing.entity.voucher.InterSubjectInfo;
import com.tenwa.leasing.entity.voucher.IntereasVoucherEntries;
import com.tenwa.leasing.entity.voucher.IntereasVoucherHead;
import com.tenwa.leasing.entity.voucher.IntereasVoucherasStacts;
import com.tenwa.leasing.entity.voucher.VoucherassStactsConfig;
import com.tenwa.leasing.entity.voucher.VoucherassStactsInfo;
import com.tenwa.leasing.service.voucher.IntereasVoucherasstactsService;
import com.tenwa.leasing.service.voucher.IntereasVoucherentriesService;
import com.tenwa.leasing.service.voucher.IntereasVoucherheadService;
import com.tenwa.leasing.service.voucher.VoucherService;
import com.tenwa.leasing.utils.Tools;


/**   
*    
* 项目名称：tls5.1   
* 类名称：VoucherServiceImpl   
* 类描述：   
* 创建人：rovine   
* 创建时间：2014年12月9日 上午10:34:58   
* @version        
*/
@Service(value = "voucherService")
public class VoucherServiceImpl extends AbstractBaseServiceImpl implements VoucherService {
	/**
	 * log4j日志 
	 */
	private static final Logger log = LoggerFactory.getLogger(VoucherServiceImpl.class);

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
	 * 注入凭证体服务接口
	 */
	@Resource(name="intereasVoucherentriesService")
	private IntereasVoucherentriesService intereasVoucherentriesService;
	/**
	 * 注入辅助账服务接口
	 */
	@Resource(name="intereasVoucherasstactsService")
	private IntereasVoucherasstactsService intereasVoucherasstactsService;
	
	
	@Override
	public void updateVoucherhead(String data) throws Exception {
		//凭证头修改
		System.out.println(data);
		JSONObject voucherJson = new JSONObject(data);
		JSONObject voucherHeaderJson = voucherJson.getJSONObject("header");
		IntereasVoucherHead intereasVoucherHead = null;
		try {
			intereasVoucherHead = this.findEntityByID(IntereasVoucherHead.class, voucherHeaderJson.getString("id"));
		} catch (Exception e) {
			log.error("", e);
		}
		if (intereasVoucherHead == null) {
			throw new BusinessException("凭证头不存在！");
		}
		this.copyAndOverrideExistedValueFromJSONObject(voucherHeaderJson, intereasVoucherHead, null);
		intereasVoucherHead.setStatus("已完整");
		this.updateEntity(intereasVoucherHead);

		JSONArray jsonArray = voucherJson.optJSONArray("contents");
		
		IntereasVoucherEntries intereasVoucherEntries = null;
		 for(int i = 0; i<jsonArray.length();i++){
			 JSONObject contentObject = jsonArray.optJSONObject(i);
			 //凭证体
			 JSONObject voucherContent = contentObject.getJSONObject("content");
			 
			 try {
				 intereasVoucherEntries = this.findEntityByID(IntereasVoucherEntries.class, voucherContent.getString("id"));
				} catch (Exception e) {
					log.error("", e);
				}
				if (intereasVoucherEntries == null) {
					throw new BusinessException("凭证体不存在！");
				}
				
			 this.copyAndOverrideExistedValueFromJSONObject(voucherContent, intereasVoucherEntries, null);
			 intereasVoucherEntries.setVoucherNumber(intereasVoucherHead);
			 this.saveOrUpdateEntity(intereasVoucherEntries);
		 
		     JSONArray voucherasStacts = contentObject.getJSONArray("intereasVoucherEntrie");
			 this.updateOneToManyCollectionsNoRemoved(intereasVoucherEntries, "intereasVoucherasStacts",IntereasVoucherasStacts.class, "entrySeq",voucherasStacts.toString(), null);
		 }
	}

	/**
	  * <p>生成凭头证主执行方法。</p>
	  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	  * <p>凭证头ENTITY对象中不为空元素如下：</p>
	  * <p>属性1：所属公司/belong_unit  (用于取组织机构代码等) 【必填】</p>
	  * <p>属性2：业务发生日期/bizDate 【必填】</p>
	  * <p>属性3：财务记账日期/bookedDate (会计记账日期通过函数getAccountDay传入业务日期获取,已提供公用查询接口getBookDate，也可以自行指定日期)【必填】</p>
	  * <p>属性4：凭证类型/voucherType (例如：自动银行/网银转账...)【必填】</p>
	  * <p>属性5：凭证发生日期/generate_date (凭证发生日期，如无则指定‘系统当前日期带时分秒’)【必填】</p>
	  * <p>属性6：业务模块/modleName (例如：已开票增值税发票回导/网银/租金实收...)【必填】</p>
	  * <p>凭证头ENTITY对象中存在则传入的元素，不存在则忽视</p>
	  * <p>属性1：合同号/contract_number (合同号存在则传,传入该合同号对应合同的ENTITY，ContractInfo。合同号主要是为了标示该凭证属于哪个合同发生的凭证处理)</p>
	  * <p>属性2：备注/memo1 </p>
	  * <p>其它传入对象说明：</p>
	  * <p>对象1：User  当前登录人对应的ENTITY </p>
	  * <p>对象2：ContractInfo,该参数存在于凭证头ENTITY中的contract_id中，合同号存在则setContract_id(ContractInfo contractId)</p>
	  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	  * @author sea
	  * @param intereas_voucherhead 凭证头对象
	  * @param user 当前登录人信息对象
	  * @return 持久化过后完整的凭证头对象headObj
	 * @throws Exception 
	 * @throws DataAccessException 
	 */
	public IntereasVoucherHead productionVoucherHead(IntereasVoucherHead intereas_voucherhead,User user) throws DataAccessException, Exception{
		/**
		 * 构建凭证头所需的前置条件
		 */
    	//1 根据凭证头中封装的合同号去获取全部的合同对象,前提是合同号存在值
    	ContractInfo contract_obj = intereas_voucherhead.getContract_id();
//    	//取得合同号，然后再获取完整的合同对象
//    	String contract_id = contract_obj.getContractId();
//    	//合同对象重新赋值
//    	if(!Tools.isNullOrEmpty(contract_id)){
//    		contract_obj = this.seachAllContract(contract_obj);
//    	}
		//2 获取凭证号
		String voucherNumber =  intereasVoucherheadService.getVoucherNumber();
		//讲凭证号注入进凭证头ENTITY中
		intereas_voucherhead.setVoucherNumber(voucherNumber);
		//3 生成凭证头对象并做持久化操作
		IntereasVoucherHead headObj =  intereasVoucherheadService.saveIntereasVoucherhead(intereas_voucherhead, contract_obj, user);
		//返回
		return headObj;
	}
	
	/**
	 * <p>凭证体持久化主入口：</p>
	  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	  * <p>凭证体ENTITY对象中不为空元素如下：</p>
	  * <p>属性1： 分录行号/entrySeq (用于标示凭证体的具体数目，例如：1,2,3...)【必填】</p>
	  * <p>属性2：摘要 /voucherAbstract (用于标示该笔钱的用途，例如：**客户，支付分支行，号码为：**，费用类型：网银还款，合同号：***)【必填】</p>
	  * <p>属性3： 科目编码/accountNumber (用于记录钱的科目，例如：2501.02、2202.01.01等等)【必填】</p>
	  * <p>属性4： 方向 （1 借方-1 贷方）/entryDC (标示该凭证体的借贷方)【必填】</p>
	  * <p>属性5： 借贷金额/originalAmount (标示该笔凭证发生的金额)【必填】</p>
	  * <p>其它传入对象说明：</p>
	  * <p>对象1：凭证头ENTITY,IntereasVoucherHead (用于取凭证头表中的UUID作为凭证体表中voucherNumber的值,intereasVoucherHead.setVoucherNumber(IntereasVoucherHead中voucherNumber对应的UUID))【必填】</p>
	  * <p>对象2：数据字典币种信息ENTITY,DictionaryData (用于凭证体中币种类别的传入值，IntereasVoucherEntries.setCurrencyNumber(DictionaryData))【必填】</p>
	  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	  * @author sea
	  * @param intereas_voucherentries 该辅助对应的凭证体完整对象
	  * @param user 当前登录人信息对象
	  * @param headObj 完整的凭证头对象
	  * @param intereas_voucherasstacts 所有对应该凭证体的辅助账对象集合
	  * @return 下标为0的对象是凭证体对象，后续全部是该凭证体对应的辅助账对象集合
	 * @throws Exception 
	 */
	public IntereasVoucherEntries productionIntereasVoucherEntries(IntereasVoucherEntries intereas_voucherentries,User user,IntereasVoucherHead headObj) throws Exception{
		/**
		 * 构建凭证体对象，并且做持久化操作
		 */
		// 数据字典
		DictionaryData dictObj = intereas_voucherentries.getCurrencyNumber();
		// 完整的凭证证体对象(用于下方的辅助账生成操作)
		IntereasVoucherEntries oneEntriesObj = intereasVoucherentriesService.saveIntereasVoucherentries(intereas_voucherentries, dictObj, headObj, user);
		return oneEntriesObj;
	}
	
	/**
	 * <p>辅助账生成及持久化主入口：</p>
	  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	  * <p>辅助账对象中必须传入元素如下：</p>
	  * <p>属性1：辅助账内容 /asstActNumber (表示该辅助账的具体含义)【必填】</p>
	  * <p>其它传入对象说明：</p>
	  * <p>对象1：VoucherassStactsInfo，辅助帐类型编码/asstActType (表示该条辅助账的含义，例如：001客户，002物料,该条件封装入辅助账类型ENTITY(VoucherassStactsInfo)中) 【必填】</p>
	  * <p>对象2：凭证头ENTITY,IntereasVoucherHead (用于取凭证体表中的UUID作为凭证体表中voucherNumber的值)【必填】</p>
	  * <p>对象3： 凭证体ENTITY,IntereasVoucherEntries，凭证体行号/entrySeq (凭证体entity中获取,辅助账是在凭证体存入数据库之后做的操作) 【必填】</p>
	  * <p>对象4：User  当前登录人对应的ENTITY 【必填】</p>
	  * <p>对象4：CustInfo  客户信息对应的ENTITY 【必填】</p>
	  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	  * @author sea
	  * @param intereas_voucherasstacts 所有对应该凭证体的辅助账对象集合
	  * @param oneEntriesObj 该辅助对应的凭证体完整对象
	  * @param headObj 完整的凭证头对象
	  * @param user 当前登录人信息对象
	  * @return 该凭证体对应的辅助账对象集合按辅助账行号顺序
	 * @throws Exception 
	 */
	public List<Object> productionIntereasVoucherasStacts(List<IntereasVoucherasStacts> intereas_voucherasstacts,IntereasVoucherEntries oneEntriesObj,IntereasVoucherHead headObj,User user) throws Exception{
		List<Object> all_l = new ArrayList<Object>(); 
		//
		for (int i = 0; i < intereas_voucherasstacts.size(); i++) {
			//获取一个单独的辅助账对象
			IntereasVoucherasStacts oneVoucherasStacts = intereas_voucherasstacts.get(i); 
			//辅助帐类型对象
			VoucherassStactsInfo voucherassStactsInfo = oneVoucherasStacts.getAsstActType();
			//客户信息对象
			CustInfo custInfo = oneVoucherasStacts.getCustIdB();
			/**辅助账底层方法传入的参数说明
			 * @param  辅助账部分必填信息entity集合(com.business.entity.voucher.IntereasVoucherasStacts)
			 * @param  辅助账类型entity   (com.business.entity.voucher.VoucherassStactsInfo)
			 * @param 客户信息entity   (com.business.entity.cust.CustInfo)
			 * @param 凭证体entity(com.business.entity.voucher.IntereasVoucherEntries)
			 * @param 凭证头entity(com.business.service.voucher.IntereasVoucherHead) 
			 * @param 当前登录人信息entity(com.business.entity.User)
			 */
			IntereasVoucherasStacts allVoucherasStacts = intereasVoucherasstactsService.saveIntereasVoucherasstacts(oneVoucherasStacts, voucherassStactsInfo, custInfo, oneEntriesObj, headObj, user);
			all_l.add(allVoucherasStacts);
		}
		
		return all_l;
	}
	
	
	/**
	  * <p>根据合同号查询合同ENTITY。</p>
	  * @author sea
	  * @param obj 封装有合同号contract_number的合同ENTITY
	  * @return
	  * @throws Exception
	 */
	private ContractInfo seachAllContract(ContractInfo obj) throws Exception{
		ContractInfo contractInfo = new ContractInfo();
		Map<String,Object> map = new HashMap<String, Object>();
		log.error("根据合同号查询合同ENTITY，查询条件:"+obj.getContractNumber());
		map.put("contractNumber", obj.getContractNumber());
		//根据具体的字段条件查询一个entity，条件使用map封装
		contractInfo = this.getBussinessDao().findEntityByProperties(ContractInfo.class, map).get(0);
		
		return contractInfo;
	}
	
	
	
	/**
	 * 凭证三张表：
	 * 凭证头：intereas_voucherhead
	 * 凭证体：intereas_voucherentries
	 * 辅助账：intereas_voucherasstacts
	 * 构建三张凭证前需要构建以下5个对象集合：
	 * 对象1：User 构建当前登录人信息对象(或者指定用户对象) 封装入凭证头中的‘制单人’选项中
	 * 对象2：VoucherassStactsInfo 辅助帐类型编码对象(select* from VOUCHERASSSTACTS_INFO)。 封装入：凭证头中‘凭证字’选项中
	 * 对象3：DictionaryData 币别数据字典对象( select * from  t_dicts_datas  where pid_ = 'currency_type';)   封装入凭证体中的‘币别’选项中
	 * 对象4：CustInfo 客户对象 ,辅助账为客户情况下构建该对象。    封装入辅助账中‘CUST_ID_B’选项中
	 * 对象5: OwnAccount 本方账户信息对象 （如果对象2为‘03’则这里必须传入该对象信息）
	 * 每个凭证头必须传入参数如下：
	 * 参数1：belongUnit 所属公司对应UUID select * from INTER_ORGCODE
	 * 参数2：bizDate 业务发生日期
	 * 参数3：bookedDate 财务记账日期
	 * 参数4：voucherType 凭证类型（客户/经销商/银行账户/物料）
	 * 参数5：generateDate 凭证发生日期(系统当前时间或者根据客户财务规定的日期计算逻辑)
	 * 参数6：modleName 业务模块(凭证产生的模块流程名称)
	 * 参数7：contract_number 合同号(存在则比填，不存在则传空)
	 * 参数8：memo1 备注(存在则填，不存在则传空)
	 * 每个凭证体必须传入参数如下：
	 * 参数1：dictionaryData  币别数据字段对象
	 * 参数2：num_entrySeq 凭证体分录行号 (递增)
	 * 参数3：accountNumber 科目编码
	 * 参数4：originalAmount 原币金额 
	 * 参数5：voucherAbstract 摘要
	 * 参数6：entryDC 借贷方向 （1 借方-1 贷方）
	 * 参数7：headObj 该凭证体对应的凭证头完整的实体对象信息
	 * 每个辅助账必须传入参数如下：
	 * 参数1：voucherassStactsInfo 完整的辅助账类型编码对象
	 * 参数2：custInfo 完整的客户对象
	 * 参数3：intereasVoucherentries 该辅助账对应的凭证体的完整信息
	 * 参数4：headObj 该凭证体对应的凭证头完整的实体对象信息
	 */
	public void saveVoucherMain(List<IntereasVoucherHead> list) throws DataAccessException, Exception{
		
//		list = null;
//		log.debug("凭证数据暂时不入库！");
		
		for (IntereasVoucherHead intereasVoucherhead : list) {
				//对象1：
				User user = intereasVoucherhead.getCreator();
				//凭证字（凭证对象）
				String voucherType = intereasVoucherhead.getVoucherType().getName();
				//合同对象：
				ContractInfo contractObj  = intereasVoucherhead.getContract_id();
				//合同对象重新赋值构建完整的合同信息对象
				if(!Tools.isNullOrEmpty(contractObj)){
					if(!Tools.isNullOrEmpty(contractObj.getContractNumber())){
						contractObj = this.seachAllContract(contractObj);
					}
				}
				
				//获取凭证头对应凭证体的对象信息,一个凭证头存在多个凭证体 ，之所以在凭证头持久化前获取是因为持久化后凭证头对象被重新赋值了
				List<IntereasVoucherEntries> voucherEntries_l = intereasVoucherhead.getIntereasVoucherEntriesInAction();
				
				//凭证头持久化操作
				intereasVoucherhead = this.productionVoucherHead(intereasVoucherhead, user);
				
				//所有待持久化凭证体
				List<IntereasVoucherEntries> allEntries_l  = new ArrayList<IntereasVoucherEntries>();
				//所有待持久化辅助账集合
				List<IntereasVoucherasStacts> allStacts_l  = new ArrayList<IntereasVoucherasStacts>();
				for (int i = 0; i < voucherEntries_l.size(); i++) {
					IntereasVoucherEntries one_entries = voucherEntries_l.get(i);
					
					//设置凭证体行号
					one_entries.setEntrySeq( String.valueOf( i+1 ) );
					
					//取出所有辅助账对象
					List<IntereasVoucherasStacts> stacts_l  = one_entries.getIntereasVoucherasStactsInAction();
					//对象2：
					//DictionaryData dictionaryData = one_entries.getCurrencyNumber();
					/**
					 * <p>整凭证体对象生成。</p>
					 * <p>根据必要参数生成一个借方or贷方凭证体操作。</p>
					 * <p>必要参数:完整的币别对象\凭证体行号\凭证体摘要字符串\该凭证体对应的凭证头完整的实体对象信息\科目编号\借方金额\借方贷方。</p>
					 * @author sea
					 * @param dictionaryData  币别数据字段对象
					 * @param num_entrySeq 凭证体行号
					 * @param voucherAbstract 凭证体摘要
					 * @param headObj 该凭证体对应的凭证头完整的实体对象信息
					 * @param accountNumber 科目编号
					 * @param originalAmount BigDecimal 借方金额
					 * @param Integer entryDC 借贷方向 （1 借方-1 贷方）
					 * @return 返回一个完整的【借方】凭证体对象
					 */
					one_entries  = intereasVoucherentriesService.getALLIntereasVoucherEntries(one_entries, one_entries.getCurrencyNumber(), intereasVoucherhead);
					allEntries_l.add(one_entries);
					
					for (int j = 0; j < stacts_l.size(); j++) {
						IntereasVoucherasStacts one_stacts = stacts_l.get(j);
						//对象4：辅助帐类型编码对象
						VoucherassStactsInfo stactsInfo = intereasVoucherasstactsService.getVoucherassStactsInfo(one_stacts.getAsstActType().getAsstActType());
						//
						if("01".equals(stactsInfo.getAsstActType()) || "02".equals(stactsInfo.getAsstActType())){//客户  经销商
							//取出完整的客户对象
							CustInfo custInfo = one_stacts.getCustIdB();
							/*
							 * @param1 完整的辅助账类型编码对象
							 * @param2  完整的客户对象
							 * @param3  该辅助账对应完整的凭证体信息
							 * @param4  该凭证体对应的凭证头完整的实体对象信息
							 */
							one_stacts = intereasVoucherasstactsService.importantVoucherasStacts(stactsInfo, custInfo, one_entries, intereasVoucherhead);
						}else if("03".equals(stactsInfo.getAsstActType())){//银行账户
							//取出完整的银行账户对象
							OwnAccount ownAccount = one_stacts.getOwnAccount();
							/*
							 * @param1  完整的辅助账类型编码对象
							 * @param2  完整的本方银行账号信息
							 * @param3  该辅助账对应完整的凭证体信息
							 * @param4  该凭证体对应的凭证头完整的实体对象信息
							 */
							one_stacts = intereasVoucherasstactsService.importantVoucherasStacts003(stactsInfo, ownAccount, one_entries, intereasVoucherhead);
						}
						/*		目前木有	该选项	
	  				else if("04".equals(voucherType)){//物料(合同)
						
						 * @param1  完整的辅助账类型编码对象
						 * @param2  完整的合同信息(根据合同号查询)
						 * @param3  该辅助账对应完整的凭证体信息
						 * @param4  该凭证体对应的凭证头完整的实体对象信息
						
						one_stacts = intereasVoucherasstactsService.importantVoucherasStacts004(stactsInfo, contractObj, one_entries, intereasVoucherhead);
					}
						 */
						else if("05".equals(stactsInfo.getAsstActType())){//职员
							//预留接口，目前无该种方式的凭证处理模式
						}
						else if("07".equals(stactsInfo.getAsstActType())){//行政组织
							//预留接口，目前无该种方式的凭证处理模式
						}
						else{
							//do nothing 预留
							
						}
						allStacts_l.add(one_stacts);
					}
				}
				//凭证体批量持久化操作
				intereasVoucherentriesService.saveEntries(allEntries_l);
				//辅助账批量持久化操作
				intereasVoucherasstactsService.saveStacts(allStacts_l);
		}
	}

	
	/**
	 * 
	 * <p>生成凭证头方法，该方法可以使用copyAndOverrideExistedValueFromStringMap方法代替。</p>
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * <p>凭证头ENTITY对象中不为空元素如下：</p>
	 * <p>属性1：所属公司/belong_unit  (用于取组织机构代码等) 【必填】</p>
	 * <p>属性2：业务发生日期/bizDate 【必填】</p>
	 * <p>属性3：财务记账日期/bookedDate (会计记账日期通过函数getAccountDay传入业务日期获取,已提供公用查询接口getBookDate，也可以自行指定日期)【必填】</p>
	 * <p>属性4：凭证类型/voucherType (例如：自动银行/网银转账...)【必填】</p>
	 * <p>属性5：凭证发生日期/generate_date (凭证发生日期，如无则指定‘系统当前日期带时分秒’)【必填】</p>
	 * <p>属性6：业务模块/modleName (例如：已开票增值税发票回导/网银/租金实收...)【必填】</p>
	 * <p>凭证头ENTITY对象中存在则传入的元素，不存在则忽视</p>
	 * <p>属性1：合同号/contract_number (合同号存在则传,传入该合同号对应合同的ENTITY，ContractInfo。合同号主要是为了标示该凭证属于哪个合同发生的凭证处理)</p>
	 * <p>属性2：备注/memo1 </p>
	 * <p>其它传入对象说明：</p>
	 * <p>对象1：User  当前登录人对应的ENTITY </p>
	 * <p>对象2：ContractInfo,该参数存在于凭证头ENTITY中的contract_id中，合同号存在则setContract_id(ContractInfo contractId)</p>
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * @author sea
	 * @param map  
	 * @return
	 * @throws Exception 
	 */
	public IntereasVoucherHead packagingHead(Map<String,String> map) throws Exception {
		//取值
		String uuId = map.get("uuId");//UUID
		String belongUnit = map.get("belongUnit");//所属公司
		String bizDate = map.get("bizDate");//业务发生日期
		String bookedDate = map.get("bookedDate");//财务记账日期
		String voucherType = map.get("voucherType");//凭证类型
		DictionaryData obj = new DictionaryData();
		obj.setName("voucherType");
		String generateDate = map.get("generateDate");//凭证发生日期
		String modleName = map.get("modleName");//业务模块
		String contract_number = map.get("contract_number");//合同号
		String memo1 = map.get("memo1");//备注
		//InsuranceServiceImpl: copyAndOverrideExistedValueFromStringMap
		
		//
		ContractInfo contractObj = new ContractInfo();
		//合同对象重新赋值
    	if(!Tools.isNullOrEmpty(contract_number)){
    		contractObj.setContractNumber(contract_number);
    		contractObj = this.seachAllContract(contractObj);
    	}
    	//构建对象
		IntereasVoucherHead intereasVoucherhead = new IntereasVoucherHead();
		intereasVoucherhead.setId(uuId);
		intereasVoucherhead.setBelong_unit(belongUnit);//公司所属编号
		intereasVoucherhead.setBizDate(bizDate);//业务日期
		intereasVoucherhead.setBookedDate(bookedDate);
		intereasVoucherhead.setVoucherType(obj);//凭证类型
		intereasVoucherhead.setGenerate_date(generateDate);
		intereasVoucherhead.setModleName(modleName);//业务模块
		intereasVoucherhead.setContract_id(contractObj);//合同对象
		intereasVoucherhead.setMemo1(memo1);
		//返回凭证头对象
		return intereasVoucherhead;
	}
	
	
	
	
	/**
	  * <p>生成凭证的例子，具体看代码注释。</p>
	  * <p>该凭证为一借（借2辅助）一贷(贷1辅助)。</p>
	  * @author sea
	  * @throws Exception
	 */
	public void saveVoucher() throws Exception{
		log.debug("凭证生成开始：");
		/**
		 * 一借五贷
		 * 借：预收账款  150984.00 科目 2203   摘要确认客户首付款 ，   辅助核算：客户-河南君腾汽车贸易有限公司
		 * 贷1:首付款 121600.00       科目1531.02 摘要 确认客户首付款，辅助核算：客户-赵东亮
		 * 贷2:保证金 24320.00         科目2241.01 摘要 确认客户首付款，辅助核算：客户-赵东亮
		 * 贷3:留购价 200.00              科目2241.06 摘要 确认客户首付款，辅助核算：客户-赵东亮
		 * 贷4:手续费收入4157.26   科目6001.02 摘要 确认客户首付款，辅助核算：客户-赵东亮
		 * 贷5:销项税额 706.74          科目6001.02 摘要 确认客户首付款，辅助核算：无
		 */
		//构建生成凭证头的必要参数
		Map<String,String> map = new HashMap<String, String>();
		//select ID from inter_orgcode where org_name = '欧力士租赁'
		String uuId = UUIDUtil.getUUID();
		//map.put("uuId",uuId);//UUID 可以穿空，后台会自动生成并封装
		map.put("belongUnit","inter_orgcode_id_1");//所属公司
		map.put("bizDate","2013-11-04");//业务发生日期
		map.put("bookedDate","2013-11-05");//财务记账日期
		map.put("voucherType","客户");//凭证类型
		map.put("generateDate","2013-11-05");//凭证发生日期
		map.put("modleName","确认首付款");//业务模块
		map.put("contract_number","欧力士(2013)租字第00034号");//合同号
		map.put("memo1","确认首付款凭证测试-sea");//备注
		/**
		 * 凭证三张表：
		 * 凭证头：intereas_voucherhead
		 * 凭证体：intereas_voucherentries
		 * 辅助账：intereas_voucherasstacts
		 * 构建三张凭证前需要构建以下4个对象集合：
		 * 对象1：User 构建当前登录人信息对象
		 * 对象2：DictionaryData 币别数据字典对象( select * from  t_dicts_datas  where pid_ = 'currency_type';) 最终取的值是CODE_
		 * 对象3：CustInfo 客户对象 ,辅助账为客户情况下构建该对象。法人(或供应商)情况下需同时取出法人信息对象CustInfoCompany 
		 * 对象4：VoucherassStactsInfo 辅助帐类型编码对象(select* from VOUCHERASSSTACTS_INFO)。
		 * 每个凭证头必须传入参数如下：
		 * 参数1：belongUnit 所属公司对应UUID select * from INTER_ORGCODE
		 * 参数2：bizDate 业务发生日期
		 * 参数3：bookedDate 财务记账日期
		 * 参数4：voucherType 凭证类型（客户/经销商/银行账户/物料）
		 * 参数5：generateDate 凭证发生日期(系统当前时间或者根据客户财务规定的日期计算逻辑)
		 * 参数6：modleName 业务模块(凭证产生的模块流程名称)
		 * 参数7：contract_number 合同号(存在则填，不存在则传空)
		 * 参数8：memo1 备注(存在则填，不存在则传空)
		 * 每个凭证体必须传入参数如下：
		 * 参数1：dictionaryData  币别数据字段对象
		 * 参数2：num_entrySeq 凭证体分录行号 (递增)
		 * 参数3：accountNumber 科目编码
		 * 参数4：originalAmount 原币金额 
		 * 参数5：voucherAbstract 摘要
		 * 参数6：entryDC 借贷方向 （1 借方-1 贷方）
		 * 参数7：headObj 该凭证体对应的凭证头完整的实体对象信息
		 * 每个辅助账必须传入参数如下：
		 * 参数1：voucherassStactsInfo 完整的辅助账类型编码对象
		 * 参数2：custInfo 完整的客户对象
		 * 参数3：intereasVoucherentries 该辅助账对应的凭证体的完整信息
		 * 参数4：headObj 该凭证体对应的凭证头完整的实体对象信息
		 */
		//1 构建当前登录人信息对象
		User user = (User) SecurityUtil.getPrincipal();
		//2 构建币别数据字段对象
		DictionaryData dictionaryData = new DictionaryData();
		//人民币 select DESCRIPTION_ from  T_DICTS_DATAS where CODE_ = 'currency_type1'
		dictionaryData.setCode("currency_type1");
		dictionaryData = intereasVoucherentriesService.getCurrencyNumberOne(dictionaryData);
		//3 根据客户编号构建客户所有对象
		String cust_number = "CI201310039";
		CustInfo custInfo = intereasVoucherentriesService.serchCustInfo(cust_number);
		String cust_name = custInfo.getCustName();
		//客户法人情况下:带出法人对应的法人代表名称
		if("CUST_INFO_COMPANY".equals(custInfo.getCustClass().getCode())){
			//获取法人信息
			CustInfoCompany custInfoCompany = custInfo.getCustInfoCompany();
			//法人代表
			cust_name = custInfoCompany.getPersonRep();
		}
//		else{//自然人信息
//			CustInfoPerson custInfoPerson = custInfo.getCustInfoPerson();
//		}
		//4 构建 辅助帐类型编码对象
		VoucherassStactsInfo voucherassStactsInfo = new VoucherassStactsInfo();
		//select *  from VOUCHERASSSTACTS_INFO
		String asstActType = "001";
		voucherassStactsInfo = intereasVoucherasstactsService.getVoucherassStactsInfo(asstActType);
		
		
		log.debug("凭证头生成开始：");
		// 凭证头构建
		IntereasVoucherHead headObj = this.packagingHead(map);
		//根据凭证头中封装的合同号去获取全部的合同对象,前提是合同号存在值
    	ContractInfo contract_obj = headObj.getContract_id();
    	//取得合同号，然后再获取完整的合同对象
    	String contract_id = contract_obj.getContractId();
    	//合同对象重新赋值
    	if(!Tools.isNullOrEmpty(contract_id)){
    		contract_obj = this.seachAllContract(contract_obj);
    	}
		//凭证头持久化方式1：
		headObj = this.productionVoucherHead(headObj, user);
		log.debug("凭证头生成结束！");
		
		//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
		int num_entrySeq = 1;//凭证体行号变量
		//num_entrySeq = intereasVoucherentriesService.getNumber(headObj.getId());
		
		log.debug("凭证体1‘借’生成开始：");
		List<IntereasVoucherEntries> voucherEntries_l = new ArrayList<IntereasVoucherEntries>();
		//凭证体 借 借：预收账款  150984.00 科目 2203   摘要确认客户首付款 ，   辅助核算：客户-河南君腾汽车贸易有限公司
		//String accountNumber = "22003";//科目编码
		VoucherassStactsConfig accountNumber = new VoucherassStactsConfig();
		accountNumber.setSubjectsCode("22003");
		BigDecimal originalAmount = new BigDecimal( 150984.00 );//借方金额
		//构建摘要信息
		String voucherAbstract = "确认客户‘"+cust_name+"’-预收账款-首付款‘"+Tools.formatNumberDoubleTwo( String.valueOf(originalAmount) )+"’";
		Integer entryDC = 1;//借贷方向 （1 借方-1 贷方）
		/**
		 * <p>整凭证体对象生成。</p>
		 * <p>根据必要参数生成一个借方or贷方凭证体操作。</p>
		  * <p>必要参数:完整的币别对象\凭证体行号\凭证体摘要字符串\该凭证体对应的凭证头完整的实体对象信息\科目编号\借方金额\借方贷方。</p>
		  * @author sea
		  * @param dictionaryData  币别数据字段对象
		  * @param num_entrySeq 凭证体行号
		  * @param voucherAbstract 凭证体摘要
		  * @param headObj 该凭证体对应的凭证头完整的实体对象信息
		  * @param accountNumber 科目编号
		  * @param originalAmount BigDecimal 借方金额
		  * @param Integer entryDC 借贷方向 （1 借方-1 贷方）
		  * @return 返回一个完整的【借方】凭证体对象
		 */
		IntereasVoucherEntries intereasVoucherentries1  = intereasVoucherentriesService.importantVoucherasEntries(dictionaryData, num_entrySeq, voucherAbstract, headObj, accountNumber, originalAmount,entryDC);
		voucherEntries_l.add(intereasVoucherentries1);
		log.debug("凭证体1‘借’生成结束！");
		
		//凭证体-借，对应辅助账开始
		List<IntereasVoucherasStacts> voucherasstacts_l = new ArrayList<IntereasVoucherasStacts>();
		//凭证体1---辅助账1 ，例如：客户-河南君腾汽车贸易有限公司
		/**
		 * <p>‘001-客户’辅助账类型通用生成方法。</p>
		  * <p>产生一个完整辅助账类型为:‘001-客户’的辅助账对象。</p>
		  * @author sea
		  * @param voucherassStactsInfo 完整的辅助账类型编码对象
		  * @param custInfo 完整的客户对象
		  * @param intereasVoucherentries 该辅助账对应的凭证体的完整信息
		  * @param headObj 该凭证体对应的凭证头完整的实体对象信息
		  * @return IntereasVoucherasStacts obj
		 * @throws Exception 
		 * @throws DataAccessException 
		 */
		log.debug("凭证体1‘借’辅助账1开始：");
		IntereasVoucherasStacts intereasVoucherasstacts1  = intereasVoucherasstactsService.importantVoucherasStacts(voucherassStactsInfo, custInfo, intereasVoucherentries1, headObj);
		//封装辅助账1
		voucherasstacts_l.add(intereasVoucherasstacts1);
		log.debug("凭证体1‘借’辅助账1结束！");
		
		
		//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
		log.debug("凭证体2‘贷’生成开始：");
		//凭证体2,贷1:首付款 121600.00       科目1531.02 摘要 确认客户首付款，辅助核算：客户-赵东亮
		//凭证体行号得重新查询累加一次
		num_entrySeq = voucherEntries_l.size()+1;//intereasVoucherentriesService.getNumber(headObj.getId());
		//科目编码
		accountNumber = new VoucherassStactsConfig();
		accountNumber.setSubjectsCode("1531.02");
		originalAmount = new BigDecimal( 121600.00 );//贷方金额
		//构建摘要信息
		voucherAbstract = "确认客户‘"+cust_name+",长期应收款-应收融资租赁款-首付款"+Tools.formatNumberDoubleTwo( String.valueOf(originalAmount) )+"’";
		entryDC = -1;//借贷方向 （1 借方-1 贷方）
		IntereasVoucherEntries intereasVoucherentries2 = intereasVoucherentriesService.importantVoucherasEntries(dictionaryData, num_entrySeq, voucherAbstract, headObj, accountNumber, originalAmount,entryDC);
		voucherEntries_l.add(intereasVoucherentries2);
		log.debug("凭证体2‘贷1’生成结束！");
		/**
		 * 凭证体2-贷1--辅助账1 ，例如：客户-赵东亮
		 */
		//辅助账  
		IntereasVoucherasStacts intereasVoucherasstacts2 = intereasVoucherasstactsService.importantVoucherasStacts(voucherassStactsInfo, custInfo, intereasVoucherentries2, headObj);
		//封装辅助账
		voucherasstacts_l.add(intereasVoucherasstacts2);
		log.debug("凭证体2-贷1-辅助账1结束！");
		
		//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
		//凭证体3-贷2:保证金 24320.00         科目2241.01 摘要 确认客户首付款，辅助核算：客户-赵东亮
		//凭证体行号得重新查询累加一次
		num_entrySeq = voucherEntries_l.size()+1;//intereasVoucherentriesService.getNumber(headObj.getId());
		//科目编码
		accountNumber = new VoucherassStactsConfig();
		accountNumber.setSubjectsCode("2241.01");
		originalAmount = new BigDecimal(  24320.00  );//贷方金额
		//构建摘要信息
		voucherAbstract = "确认客户‘"+cust_name+",长期应收款-应收融资租赁款-保证金"+Tools.formatNumberDoubleTwo( String.valueOf(originalAmount) )+"’";
		entryDC = -1;//借贷方向 （1 借方-1 贷方）
		//必要参数:完整的币别对象\凭证体行号\凭证体摘要字符串\该凭证体对应的凭证头完整的实体对象信息\科目编号\借方金额\借方贷方。
		IntereasVoucherEntries intereasVoucherentries3 = intereasVoucherentriesService.importantVoucherasEntries(dictionaryData, num_entrySeq, voucherAbstract, headObj, accountNumber, originalAmount,entryDC);
		voucherEntries_l.add(intereasVoucherentries3);
		log.debug("凭证体3‘贷2’生成结束！");
		/**
		 * 凭证体3-贷2--辅助账1 ，例如：客户-赵东亮
		 */
		//辅助账    
		IntereasVoucherasStacts intereasVoucherasstacts3 = intereasVoucherasstactsService.importantVoucherasStacts(voucherassStactsInfo, custInfo, intereasVoucherentries3, headObj);
		//封装辅助账
		voucherasstacts_l.add(intereasVoucherasstacts3);
		log.debug("凭证体3-贷2-辅助账结束！");
		
		//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
		//凭证体4-贷3:留购价 200.00              科目2241.06 摘要 确认客户首付款，辅助核算：客户-赵东亮
		//凭证体行号得重新查询累加一次
		num_entrySeq = voucherEntries_l.size()+1;//intereasVoucherentriesService.getNumber(headObj.getId());
		//科目编码
		accountNumber = new VoucherassStactsConfig();
		accountNumber.setSubjectsCode("2241.06");
		originalAmount = new BigDecimal(   200.00  );//贷方金额
		//构建摘要信息
		voucherAbstract = "确认客户‘"+cust_name+",长期应收款-应收融资租赁款-留购价"+Tools.formatNumberDoubleTwo( String.valueOf(originalAmount) )+"’";
		entryDC = -1;//借贷方向 （1 借方-1 贷方）
		//必要参数:完整的币别对象\凭证体行号\凭证体摘要字符串\该凭证体对应的凭证头完整的实体对象信息\科目编号\借方金额\借方贷方。
		IntereasVoucherEntries intereasVoucherentries4 = intereasVoucherentriesService.importantVoucherasEntries(dictionaryData, num_entrySeq, voucherAbstract, headObj, accountNumber, originalAmount,entryDC);
		voucherEntries_l.add(intereasVoucherentries4);
		log.debug("凭证体4‘贷3’生成结束！");
		/**
		 * 凭证体4-贷3--辅助账1 ，例如：客户-赵东亮
		 */
		//辅助账    
		IntereasVoucherasStacts intereasVoucherasstacts4 = intereasVoucherasstactsService.importantVoucherasStacts(voucherassStactsInfo, custInfo, intereasVoucherentries4, headObj);
		//封装辅助账
		voucherasstacts_l.add(intereasVoucherasstacts4);
		log.debug("凭证体4-贷3-辅助账结束！");

		//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
		//凭证体5-贷4:手续费收入4157.26   科目6001.02 摘要 确认客户首付款，辅助核算：客户-赵东亮
		//凭证体行号得重新查询累加一次
		num_entrySeq = voucherEntries_l.size()+1;//intereasVoucherentriesService.getNumber(headObj.getId());
		//科目编码
		accountNumber = new VoucherassStactsConfig();
		accountNumber.setSubjectsCode("6001.02");
		originalAmount = new BigDecimal(   4157.26  );//贷方金额
		//构建摘要信息
		voucherAbstract = "确认客户‘"+cust_name+",长期应收款-应收融资租赁款-手续费收入"+Tools.formatNumberDoubleTwo( String.valueOf(originalAmount) )+"’";
		entryDC = -1;//借贷方向 （1 借方-1 贷方）
		//必要参数:完整的币别对象\凭证体行号\凭证体摘要字符串\该凭证体对应的凭证头完整的实体对象信息\科目编号\借方金额\借方贷方。
		IntereasVoucherEntries intereasVoucherentries5 = intereasVoucherentriesService.importantVoucherasEntries(dictionaryData, num_entrySeq, voucherAbstract, headObj, accountNumber, originalAmount,entryDC);
		voucherEntries_l.add(intereasVoucherentries5);
		log.debug("凭证体5‘贷5’生成结束！");
		/**
		 * 凭证体5-贷4--辅助账1 ，例如：客户-赵东亮
		 */
		//辅助账    
		IntereasVoucherasStacts intereasVoucherasstacts5 = intereasVoucherasstactsService.importantVoucherasStacts(voucherassStactsInfo, custInfo, intereasVoucherentries5, headObj);
		//封装辅助账
		voucherasstacts_l.add(intereasVoucherasstacts5);
		log.debug("凭证体5-贷4-辅助账1结束！");
		
		//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
		//凭证体6-贷5:销项税额 706.74          科目6001.02 摘要 确认客户首付款，辅助核算：无
		//凭证体行号得重新查询累加一次
		num_entrySeq = voucherEntries_l.size()+1;//intereasVoucherentriesService.getNumber(headObj.getId());
		//科目编码
		accountNumber = new VoucherassStactsConfig();
		accountNumber.setSubjectsCode("6001.02");
		originalAmount = new BigDecimal(   706.74  );//贷方金额
		//构建摘要信息
		voucherAbstract = "确认客户‘"+cust_name+",应交税费-应交增值税-销项税额"+Tools.formatNumberDoubleTwo( String.valueOf(originalAmount) )+"’";
		entryDC = -1;//借贷方向 （1 借方-1 贷方）
		//必要参数:完整的币别对象\凭证体行号\凭证体摘要字符串\该凭证体对应的凭证头完整的实体对象信息\科目编号\借方金额\借方贷方。
		IntereasVoucherEntries intereasVoucherentries6 = intereasVoucherentriesService.importantVoucherasEntries(dictionaryData, num_entrySeq, voucherAbstract, headObj, accountNumber, originalAmount,entryDC);
		voucherEntries_l.add(intereasVoucherentries6);
		log.debug("凭证体6‘贷5’生成结束！");
		//* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
		
		//凭证体批量持久化操作
		intereasVoucherentriesService.saveEntries(voucherEntries_l);
		//辅助账批量持久化操作
		intereasVoucherasstactsService.saveStacts(voucherasstacts_l);
		
		log.debug("凭证生成结束!");
	}
	
	public InterSubjectInfo getInterSubjectInfoByType(String TypeNumber) throws Exception{
		InterSubjectInfo info = new InterSubjectInfo();
		String hsql = "from InterSubjectInfo where TypeNumber=?";
		List<InterSubjectInfo>  infoList = this.baseDao.findResultsByHSQL(hsql, TypeNumber);
		if(infoList.size()>0){
			info = infoList.get(0);
		}
		return info;
		
		
	}
	
	
	/**
	 * 通用生成辅助账
	 * @param variablesMap
	 * @return
	 * @throws Exception 
	 */
	@Override
	public List<IntereasVoucherasStacts> generateVoucherassStacts(CustInfo custInfo, OwnAccount account, VoucherassStactsConfig config) throws Exception{
		List<IntereasVoucherasStacts> intereasVoucherasStactsList = new ArrayList<IntereasVoucherasStacts>();

		//公用参数ContractInfo
		
		
		// 辅助账类型表
		List<VoucherassStactsInfo> voucherassStactsInfoEbanks = this.findEntities(VoucherassStactsInfo.class);

		VoucherassStactsInfo stactsInfo_01 = null; // 客户
		VoucherassStactsInfo stactsInfo_02 = null; // 供应商
		VoucherassStactsInfo stactsInfo_03 = null; // 银行账户
		VoucherassStactsInfo stactsInfo_04 = null; //预留
		VoucherassStactsInfo stactsInfo_05 = null; //职员
		VoucherassStactsInfo stactsInfo_06 = null; //预留
		VoucherassStactsInfo stactsInfo_07 = null; //行政组织

		// 预设辅助账类型
		for (int i = 0; i < voucherassStactsInfoEbanks.size(); i++) {
			VoucherassStactsInfo voucherassStactsInfoEbank = voucherassStactsInfoEbanks.get(i);
			String asstActType = voucherassStactsInfoEbank.getAsstActType();
			if (asstActType.equals("01")) {
				stactsInfo_01 = voucherassStactsInfoEbank;
			} else if (asstActType.equals("02")) {
				stactsInfo_02 = voucherassStactsInfoEbank;
			} else if (asstActType.equals("03")) {
				stactsInfo_03 = voucherassStactsInfoEbank;
			}else if (asstActType.equals("04")) {
				stactsInfo_04 = voucherassStactsInfoEbank;
			}else if (asstActType.equals("05")) {
				stactsInfo_05 = voucherassStactsInfoEbank;
			}else if (asstActType.equals("06")) {
				stactsInfo_06 = voucherassStactsInfoEbank;
			}else if (asstActType.equals("07")) {
				stactsInfo_07 = voucherassStactsInfoEbank;
			}
		}
		
		int cust = config.getAsstActTypeCust();
		int vendor = config.getAsstActTypeVendor();
		int bank = config.getAsstActTypeBank();
		int four = config.getAsstActTypeFour();
		int employee = config.getAsstActTypeEmployee();
		int six = config.getAsstActTypeSix();
		int organization = config.getAsstActTypeOrganization();

		if (cust > 0) {//01客户
			IntereasVoucherasStacts stacts_01 = new IntereasVoucherasStacts();
			stacts_01.setAsstActType(stactsInfo_01);
			stacts_01.setCustIdB(custInfo);
			intereasVoucherasStactsList.add(stacts_01);
		}

		if (vendor > 0) {//02供应商(经销商)
			IntereasVoucherasStacts stacts_02 = new IntereasVoucherasStacts();
			stacts_02.setAsstActType(stactsInfo_02);
			stacts_02.setCustIdB(custInfo);
			intereasVoucherasStactsList.add(stacts_02);

		}

		if (bank > 0) {//03银行
/*			String accNumber  = ebankData.getOwnAccNumber();
			
			Map<String, Object> propertiesMap = new HashMap<String, Object>();
			propertiesMap.put("accNumber", accNumber);
			OwnAccount account = this.findEntityByProperties(OwnAccount.class, propertiesMap).get(0);*/
			IntereasVoucherasStacts stacts_03 = new IntereasVoucherasStacts();
			stacts_03.setAsstActType(stactsInfo_03);
			stacts_03.setOwnAccount(account);
			intereasVoucherasStactsList.add(stacts_03);
		}

		if (four > 0) {//04预留
			//预留字段
		}

		if (employee > 0) {//05职员
			//预留字段
		}

		if (six > 0) {//06预留
			//预留字段
		}
		if (organization > 0) {//07行政组织
			//预留字段
		}
		return intereasVoucherasStactsList;
	}
	
	
}
