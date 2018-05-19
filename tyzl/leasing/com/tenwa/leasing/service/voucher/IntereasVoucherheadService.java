package com.tenwa.leasing.service.voucher;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.tenwa.business.entity.User;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.voucher.InterOrgCode;
import com.tenwa.leasing.entity.voucher.IntereasVoucherHead;


/**
 * <p>凭证头相关服务方法。</p>
 * @author sea
 * <p>2013-10-17</p>
 */
public interface IntereasVoucherheadService {
	
	/**
	 * 
	 * <p>根据函数生产凭证号。</p>
	 * <p>select evidenceNocreateEAS() as voucherNumber from dual</p>
	 * @author sea
	 * @return 函数生产的合同号，类型为：String 
	 * @throws Exception 
	 * @throws DataAccessException 
	 */
	public String getVoucherNumber() throws DataAccessException, Exception;
	
	/**
	 * 
	 * <p>获取租赁公司所属组织机构代码对象。</p>
	 * @author sea
	 * @param uuId  公司所属表inter_orgcode对应主键
	 * @return InterOrgCode实体
	 * @throws Exception 
	 * @throws DataAccessException 
	 */
	public InterOrgCode getCompanyNumber(String uuId) throws DataAccessException, Exception;
	
	/**
	 * 
	 * <p>构建完整的凭证头对象。</p>
	 * @author sea
	 * @param intereasVoucherhead 带有必要参数的凭证头对象
	 * @param contractInfo  带有合同号的对象
	 * @param user  当前登录人对象
	 * @return 完整的凭证头对象
	 * @throws DataAccessException
	 * @throws Exception
	 */
	public IntereasVoucherHead saveAllIntereasVoucherHead(IntereasVoucherHead intereasVoucherhead,ContractInfo contractInfo,User user) throws DataAccessException, Exception;
	
	/**
	 * 
	  * <p>凭证头批量持久化方法(批量请使用该方法,JDBC模式,不建议使用)。</p>
	  * <p>根据接口方法getAllIntereasVoucherHead可以生成一个凭证头的对象。</p>
	  * <p>所有凭证头对象封装成LIST集合批量持久化使用该方法进行持久化操作。</p>
	  * @author sea
	  * @param List<IntereasVoucherHead> head 多个凭证头对象合计
	  * @throws DataAccessException
	  * @throws Exception
	 */
	public void saveHeads(List<IntereasVoucherHead> head) throws DataAccessException, Exception;
	
	/**
	 * <p>凭证头OBJECT持久化方式(单个凭证头持久化请使用该方法)。</p>
	 * <p>根据凭证头对象生成对应的凭证头数据插入凭证头表中。</p>
	 * <p>凭证头ENTITY对象中不为空元素如下：</p>
	 * <p>属性1：所属公司/belong_unit  (用于取组织机构代码等) 【必填】</p>
	 * <p>属性2：业务发生日期/bizDate 【必填】</p>
	 * <p>属性3：财务记账日期/bookedDate (会计记账日期通过函数getAccountDay传入业务日期获取,已提供公用查询接口getBookDate，也可以自行指定日期)【必填】</p>
	 * <p>属性4：凭证类型/voucherType (例如：自动银行/网银转账...)【必填】</p>
	 * <p>属性5：凭证发生日期/generate_date (凭证发生日期，如无则指定‘系统当前日期带时分秒’)【必填】</p>
	 * <p>属性6：业务模块/modleName (例如：已开票增值税发票回导/网银/租金实收...)【必填】</p>
	 * 
	 * <p>凭证头ENTITY对象中存在则传入的元素，不存在则忽视</p>
	 * <p>属性1：合同号/contract_id (合同号存在则传,传入该合同号对应合同的ENTITY，ContractInfo。合同号主要是为了标示该凭证属于哪个合同发生的凭证处理)</p>
	 * <p>属性2：备注/memo1 </p>
	 * 
	 * <p>其它传入对象说明：</p>
	 * <p>对象1：User  当前登录人对应的ENTITY </p>
	 * <p>对象2：ContractInfo,合同号对应合同的ENTITY</p>
	 * @author sea
	 * @param intereasVoucherhead 装有必填参数的凭证头ENTITY(com.business.service.voucher.IntereasVoucherHead) 
	 * @param contractInfo  合同号对应合同的ENTITY(com.business.entity.contract.ContractInfo)
	 * @param user  登录用户ENTITY (com.business.entity.User)
	 * @return 返回凭证头完整的ENTITYIntereasVoucherHead 
	 */
	public IntereasVoucherHead saveIntereasVoucherhead(IntereasVoucherHead intereas_voucherhead,ContractInfo contractInfo,User user) throws Exception;
	
	
	/**
	  * <p>凭证头JDBC持久化方式(JDBC请使用该方法)。</p>
	  * @author sea
	  * @param map 封装有凭证头的相关数据信息
	  * <p>MAP中封装数据说明:</p>
	  * <p>属性1:凭证号,map.get("voucherNumber")</p>
	  * <p>属性2:公司编码（select org_code from INTER_ORGCODE where org_name = '所属公司名称默认欧力士';）,map.get("org_code")</p>
	  * <p>属性3:业务日期,map.get("bizDate")</p>
	  * <p>属性4:凭证类型,map.get("voucherType")</p>
	  * <p>属性5:业务模块,map.get("modleName")</p>
	  * <p>属性6:租赁合同号(空则封装空串),map.get("contractId")</p>
	  * <p>属性7:凭证备注(空则封装空串),map.get("memo1")</p>
	  * <p>属性8:UUID主键(获取UUID：UUIDUtil.getUUID()封装入map中),map.get("uuId")</p>
	  * @return 无返回值
	 * @throws Exception 
	 */
	public  void  saveVoucherheadJdbc(Map<String,String> map,User user) throws Exception;
	
	/**
	 * 
	 * <p>根据业务操作日期查询财务对账日期。</p>
	 * <p>根据函数 getAccountDay(‘业务操作日/bizDate’)去查询,查询不到置为空字符串</p>
	 * <p> select  to_char(getAccountDay('业务操作日'),'YYYY-MM-DD') as bookDate from  dual; </p>
	 * @author sea
	 * @param bizDate 业务操作日期
	 * @return String 财务会计日
	 */
	public String getBookDate(String bizDate);
	
	/**
	 * 
	 * <p>根据凭证号修改凭证的状态,更新为'不完整'状态。</p>
	 * <p>update  intereas_voucherhead set status = '未完整' where voucherNumber = '凭证号'</p>
	 * @author sea
	 * @param voucherNumber 凭证号
	 */
	public void updateStatus(String voucherNumber) throws DataAccessException, Exception;
	
	/**
	 * 
	 * <p>根据凭证号修改凭证的状态,更新为'已完整'状态。</p>
	 * <p>update  intereas_voucherhead set status = '已完整' where voucherNumber = '凭证号'</p>
	 * @author sea
	 * @param voucherNumber 凭证号
	 */
	public void updateStatusEnd(String voucherNumber) throws DataAccessException, Exception;
	
	/**
	 * 
	 * <p>根据凭证导入信息,更新凭证头中导入EAS状态备注等。</p>
	 * <p>update intereas_voucherhead set eas_flag = '1',eas_memo = '"+eas_memo+"' where voucherNumber = '凭证号'</p>
	 * @author sea
	 * @param voucherNumber 凭证号
	 * @param eas_flag 凭证导入状态：0 未导入(包含导入失败) 1导入成功
	 * @param eas_memo 导入EAS后信息记录
	 */
	public void updateEasStatus(String voucherNumber,String eas_flag,String eas_memo) throws DataAccessException, Exception;
	
}
