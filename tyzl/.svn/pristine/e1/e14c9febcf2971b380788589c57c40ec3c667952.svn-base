package com.tenwa.leasing.service.voucher;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.leasing.entity.base.OwnAccount;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.voucher.IntereasVoucherEntries;
import com.tenwa.leasing.entity.voucher.IntereasVoucherHead;
import com.tenwa.leasing.entity.voucher.IntereasVoucherasStacts;
import com.tenwa.leasing.entity.voucher.VoucherassStactsConfig;
import com.tenwa.leasing.entity.voucher.VoucherassStactsInfo;

/**
 * 
 * <p>凭证体相关服务。</p>
 * @author sea
 * <p>2013-10-17</p>
 */
public interface IntereasVoucherentriesService {
	
	/**
	 * <p>整凭证体对象生成。</p>
	 * <p>根据必要参数生成一个借方or贷方凭证体操作。</p>
	  * <p>必要参数:完整的币别对象\凭证体行号\凭证体摘要字符串\该凭证体对应的凭证头完整的实体对象信息\科目编号\借方金额\借方贷方。</p>
	  * @author sea
	  * @param dictionaryData  币别数据字段对象
	  * @param num_entrySeq 凭证体行号
	  * @param voucherAbstract 凭证体摘要
	  * @param headObj 该凭证体对应的凭证头完整的实体对象信息
	  * @param accountNumber 科目编号对象
	  * @param originalAmount BigDecimal 借方金额
	  * @param Integer entryDC 借贷方向 （1 借方-1 贷方）
	  * @return 返回一个完整的【借方】凭证体对象
	 */
	public IntereasVoucherEntries importantVoucherasEntries(DictionaryData dictionaryData,int num_entrySeq,String voucherAbstract,IntereasVoucherHead headObj,VoucherassStactsConfig accountNumber,BigDecimal originalAmount,Integer entryDC);
	
	/**
	  * <p>根据凭证体部分必填对象数据完善完整的凭证体对象并且返回。</p>
	  * @author sea
	  * @param intereasVoucherentries  凭证体实体
	  * @param dictionaryData 数据字典实体
	  * @param intereasVoucherHead  凭证头实体
	  * @return
	 */
	public IntereasVoucherEntries getALLIntereasVoucherEntries(IntereasVoucherEntries intereasVoucherentries,DictionaryData dictionaryData,IntereasVoucherHead intereasVoucherHead);
	
	/**
	 * 
	  * <p>凭证体批量持久化方法(批量请使用该方法,JDBC模式,凭证体持久化操作建议使用该方法)。</p>
	  * <p>根据接口方法getALLIntereasVoucherEntries可以生成一个凭证体的对象。</p>
	  * <p>所有凭证体对象封装成LIST集合批量持久化使用该方法进行持久化操作。</p>
	  * @author sea
	  * @param List<IntereasVoucherEntries> entries 多个凭证体对象合计
	  * @throws DataAccessException
	  * @throws Exception
	 */
	public void saveEntries(List<IntereasVoucherEntries> entries) throws DataAccessException, Exception;
	
	/**
	 * <p>凭证体OBJECT持久化方式(不建议使用该方法)。</p>
	 * <p>根据凭证体信息将数据录入到凭证体表中。</p>
	 * <p>凭证体ENTITY对象中不为空元素如下：</p>
	 * <p>属性1： 分录行号/entrySeq (用于标示凭证体的具体数目，例如：1,2,3...)【必填】</p>
	 * <p>属性2：摘要 /voucherAbstract (用于标示该笔钱的用途，例如：**客户，支付分支行，号码为：**，费用类型：网银还款，合同号：***)【必填】</p>
	 * <p>属性3： 科目编码/accountNumber (用于记录钱的科目，例如：2501.02、2202.01.01等等)【必填】</p>
	 * <p>属性4： 方向 （1 借方-1 贷方）/entryDC (标示该凭证体的借贷方)【必填】</p>
	 * <p>属性5： 借贷金额/originalAmount (标示该笔凭证发生的金额)【必填】</p>
	 * 
	 * <p>其它传入对象说明：</p>
	 * <p>对象1：凭证头ENTITY,IntereasVoucherHead (用于取凭证头表中的UUID作为凭证体表中voucherNumber的值)【必填】</p>
	 * <p>对象2：数据字典币种信息ENTITY,DictionaryData (用于凭证体中币种类别的传入值)【必填】</p>
	 * @author sea
	 * @param intereas_voucherentries 凭证体对象(上方必填参数集合)(com.business.entity.voucher.IntereasVoucherEntries)
	 * @param dictionaryData  数据字典币种信息ENTITY  (下方接口getCurrencyNumber_One可以获取该对象)(com.business.entity.DictionaryData)
	 * @param intereasVoucherHead  凭证头对象 (凭证体实在凭证头持久化操作完成后可以取到)(com.business.service.voucher.IntereasVoucherHead) 
	 * @param user 登录人信息(com.business.entity.User)
	 * @return 返回完整的凭证体ENTITY,IntereasVoucherEntries
	 */
	public IntereasVoucherEntries saveIntereasVoucherentries(IntereasVoucherEntries intereasVoucherentries,DictionaryData dictionaryData,IntereasVoucherHead intereasVoucherHead,User user) throws Exception ;
	
	/**
	  * <p>根据币种编号或者币种对应的数据字典ENTITY。</p>
	  * <p>select * from t_dicts_datas  where code_ = 'currency_type1';</p>
	  * @author sea
	  * @param dictionarydata  数据字典ENTITY,存入值：code_，例如：dictionarydata.setCode("currency_type1")
	  * @return 完整的币种对应数据字典ENTITY ,例如传入：currency_type1则返回人民币对应的集合，currency_type2则返回美元的集合...
	  * @throws Exception
	 */
	public DictionaryData getCurrencyNumberOne(DictionaryData dictionarydata) throws Exception;
	
	/**
	 *  <p>单个凭证体JDBC持久化方式(JDBC持久化方式，不建议使用该方法)。</p>
	 * <p>根据凭证体信息将数据录入到凭证体表中。</p>
	 * <p>凭证体ENTITY对象中不为空元素如下：</p>
	 * <p>属性1： 分录行号/entrySeq (用于标示凭证体的具体数目，例如：1,2,3...)【必填】</p>
	 * <p>属性2：摘要 /voucherAbstract (用于标示该笔钱的用途，例如：**客户，支付分支行，号码为：**，费用类型：网银还款，合同号：***)【必填】</p>
	 * <p>属性3： 科目编码/accountNumber (用于记录钱的科目，例如：2501.02、2202.01.01等等)【必填】</p>
	 * <p>属性4： 方向 （1 借方-1 贷方）/entryDC (标示该凭证体的借贷方)【必填】</p>
	 * <p>属性5： 借贷金额/originalAmount (标示该笔凭证发生的金额)【必填】</p>
	 * 
	 * <p>其它传入对象说明：</p>
	 * <p>对象1：凭证头ENTITY,IntereasVoucherHead (用于取凭证头表中的UUID作为凭证体表中voucherNumber的值)【必填】</p>
	 * <p>对象2：数据字典币种信息ENTITY,DictionaryData (用于凭证体中币种类别的传入值)【必填】</p>
	 * @author sea
	 * @param intereas_voucherentries 凭证体对象(上方必填参数集合)(com.business.entity.voucher.IntereasVoucherEntries)
	 * @param dictionaryData  数据字典币种信息ENTITY  (下方接口getCurrencyNumber_One可以获取该对象)(com.business.entity.DictionaryData)
	 * @param intereasVoucherHead  凭证头对象 (凭证体实在凭证头持久化操作完成后可以取到)(com.business.service.voucher.IntereasVoucherHead) 
	 * @param user 登录人信息(com.business.entity.User)
	 * @return 返回完整的凭证体ENTITY,IntereasVoucherEntries
	 */
	public IntereasVoucherEntries saveIntereasVoucherentriesJdbc(IntereasVoucherEntries intereasVoucherentries,DictionaryData dictionaryData,IntereasVoucherHead intereasVoucherHead,User user) throws Exception ;
	
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
	public int getNumber(String voucherNumber) throws DataAccessException, Exception;
	
	/**
	  * <p>根据客户编号查询客户信息封装为客户ENTITY。</p>
	  * @author sea
	  * @param cust_number
	  * @return CustInfo的ENTITY
	  * @throws Exception
	 */
	public CustInfo serchCustInfo(String cust_number) throws Exception;
	
	/**
	 * <p>根据前台查询信息查询出导出凭证</p>
	 * @return
	 * @throws Exception
	 */
	public String saveToEas(Map<String, String> model) throws Exception;

}
