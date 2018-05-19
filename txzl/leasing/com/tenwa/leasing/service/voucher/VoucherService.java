package com.tenwa.leasing.service.voucher;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.leasing.entity.base.OwnAccount;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.voucher.InterSubjectInfo;
import com.tenwa.leasing.entity.voucher.IntereasVoucherEntries;
import com.tenwa.leasing.entity.voucher.IntereasVoucherHead;
import com.tenwa.leasing.entity.voucher.IntereasVoucherasStacts;
import com.tenwa.leasing.entity.voucher.VoucherassStactsConfig;
import com.tenwa.leasing.entity.voucher.VoucherassStactsInfo;


public interface VoucherService extends BaseService{

	public void updateVoucherhead(String data) throws Exception;
	
	
	/**
	 * 凭证持久化主执行入口
	 * 凭证三张表：
	 * 凭证头：intereas_voucherhead
	 * 凭证体：intereas_voucherentries
	 * 辅助账：intereas_voucherasstacts
	 * 构建三张凭证前需要构建以下4个对象集合：
	 * 对象1：User 构建当前登录人信息对象(或者指定用户对象) 封装入凭证头中的‘制单人’选项中
	 * 对象2：VoucherassStactsInfo 辅助帐类型编码对象(select* from VOUCHERASSSTACTS_INFO)。 封装入：凭证头中‘凭证字’选项中
	 * 对象3：DictionaryData 币别数据字典对象( select * from  t_dicts_datas  where pid_ = 'currency_type';) 最终取的值是CODE_  封装入凭证体中的‘币别’选项中
	 * 对象4：CustInfo 客户对象 ,辅助账为客户情况下构建该对象。法人(或供应商)情况下需同时取出法人信息对象CustInfoCompany    封装入辅助账中‘CUST_ID_B’选项中
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
	 * @author sea
	 * @param List<IntereasVoucherHead>  凭证头对象（封装有以上注释中所有参数信息） 多个凭证，用list封装
	 * @throws Exception 
	 * @throws DataAccessException 
	 */
	public void saveVoucherMain(List<IntereasVoucherHead> list) throws DataAccessException, Exception;
	
	
	
	/**
	  * <p>生成凭头证主执行方法(暂时不用)。</p>
	  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	  * <p>凭证头ENTITY对象中不为空元素如下：</p>
	  * <p>属性1：凭证号/voucherNumber  (用于取组织机构代码等) 【必填】</p>
	  * <p>属性1：所属公司/belong_unit  (用于取组织机构代码等) 【必填】</p>
	  * <p>属性2：业务发生日期/bizDate 【必填】</p>
	  * <p>属性3：财务记账日期/bookedDate (会计记账日期通过函数getAccountDay传入业务日期获取,已提供公用查询接口getBookDate，也可以自行指定日期)【必填】</p>
	  * <p>属性4：凭证类型/voucherType (例如：自动银行/网银转账...)【必填】</p>
	  * <p>属性5：凭证发生日期/generate_date (凭证发生日期，如无则指定‘系统当前日期带时分秒’)【必填】</p>
	  * <p>属性6：业务模块/modleName (例如：已开票增值税发票回导/网银/租金实收...)【必填】</p>
	  * <p>凭证头ENTITY对象中存在则传入的元素，不存在则忽视</p>
	  * <p>属性1：合同号/contract_id (合同号存在则传,传入该合同号对应合同的ENTITY，ContractInfo。合同号主要是为了标示该凭证属于哪个合同发生的凭证处理)</p>
	  * <p>属性2：备注/memo1 </p>
	  * <p>其它传入对象说明：</p>
	  * <p>对象1：User  当前登录人对应的ENTITY </p>
	  * <p>对象2：ContractInfo,该参数存在于凭证头ENTITY中的contract_id中，合同号存在则setContract_id(ContractInfo contractId)</p>
	  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	  * @author sea
	  * @param intereas_voucherhead 凭证头对象
	  * @param user 当前登录人信息对象
	  * @return 持久化过后完整的凭证头对象
	 * @throws Exception 
	 * @throws DataAccessException 
	 */
	public IntereasVoucherHead productionVoucherHead(IntereasVoucherHead intereas_voucherhead,User user) throws DataAccessException, Exception;
	
	/**
	 * <p>凭证体持久化主入口(暂时不用)：</p>
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
	public IntereasVoucherEntries productionIntereasVoucherEntries(IntereasVoucherEntries intereas_voucherentries,User user,IntereasVoucherHead headObj) throws Exception;
	
	/**
	 * <p>辅助账生成及持久化主入口(暂时不用)：</p>
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
	public List<Object> productionIntereasVoucherasStacts(List<IntereasVoucherasStacts> intereas_voucherasstacts,IntereasVoucherEntries oneEntriesObj,IntereasVoucherHead headObj,User user) throws Exception;
	
	/**
	  * <p>生成凭证的例子，具体看代码注释。</p>
	  * <p>该凭证为一借（借2辅助）一贷(贷1辅助)。</p>
	  * @author sea
	  * @throws Exception
	 */
	public void saveVoucher() throws Exception;
	
	public InterSubjectInfo getInterSubjectInfoByType(String TypeNumber) throws Exception;


	/**
	 * 通用生成辅助账
	 * @param contract_id 合同号
	 * @param ebank_id 网银编号
	 * @param config 辅助账配置表
	 * @return 辅助账集合
	 * @throws Exception
	 */
	List<IntereasVoucherasStacts> generateVoucherassStacts(CustInfo custInfo, OwnAccount account, VoucherassStactsConfig config) throws Exception;
}
