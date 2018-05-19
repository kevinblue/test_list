package com.tenwa.leasing.service.voucher;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.tenwa.business.entity.User;
import com.tenwa.leasing.entity.base.OwnAccount;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.voucher.IntereasVoucherEntries;
import com.tenwa.leasing.entity.voucher.IntereasVoucherHead;
import com.tenwa.leasing.entity.voucher.IntereasVoucherasStacts;
import com.tenwa.leasing.entity.voucher.VoucherassStactsInfo;


/**
 * 
 * <p>辅助账服务接口。</p>
 * <p>2013-10-17</p>
 * @author sea
 * @version 4.5
 */
public interface IntereasVoucherasstactsService {
	
	/**
	 * <p>‘001-客户’‘002-供应商’辅助账类型通用生成方法。</p>
	  * <p>产生一个完整辅助账类型为:‘001-客户’‘002-供应商’的辅助账对象。</p>
	  * @author sea
	  * @param voucherassStactsInfo 完整的辅助账类型编码对象
	  * @param custInfo 完整的客户对象
	  * @param intereasVoucherentries 该辅助账对应的凭证体的完整信息
	  * @param headObj 该凭证体对应的凭证头完整的实体对象信息
	  * @return IntereasVoucherasStacts obj
	 * @throws Exception 
	 * @throws DataAccessException 
	 */
	public IntereasVoucherasStacts importantVoucherasStacts(VoucherassStactsInfo voucherassStactsInfo,CustInfo custInfo,IntereasVoucherEntries intereasVoucherentries,IntereasVoucherHead headObj) throws DataAccessException, Exception;
	
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
	public IntereasVoucherasStacts importantVoucherasStacts003(VoucherassStactsInfo voucherassStactsInfo,OwnAccount ownAccount,IntereasVoucherEntries intereasVoucherentries,IntereasVoucherHead headObj) throws DataAccessException, Exception;
	
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
	public IntereasVoucherasStacts importantVoucherasStacts004(VoucherassStactsInfo voucherassStactsInfo,ContractInfo contractInfo,IntereasVoucherEntries intereasVoucherentries,IntereasVoucherHead headObj) throws DataAccessException, Exception;
	
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
	public IntereasVoucherasStacts getAllIntereasVoucherasstacts(IntereasVoucherasStacts intereas_voucherasstacts,VoucherassStactsInfo allVoucherassStactsInfo,CustInfo custInfo,IntereasVoucherEntries intereasVoucherentries,IntereasVoucherHead intereasVoucherhead) throws DataAccessException, Exception;
	
	/**
	 * 
	  * <p>辅助账批量持久化方法(批量请使用该方法,JDBC模式,凭证体持久化操作建议使用该方法)。</p>
	  * <p>根据接口方法getAllIntereasVoucherasstacts可以生成一个辅助账的对象。</p>
	  * <p>所有辅助账对象封装成LIST集合批量持久化使用该方法进行持久化操作。</p>
	  * @author sea
	  * @param List<IntereasVoucherasStacts> stacts 多个辅助账对象合集
	  * @throws DataAccessException
	  * @throws Exception
	 */
	public void saveStacts(List<IntereasVoucherasStacts> stacts) throws DataAccessException, Exception;
	
	/**
	 * <p>辅助账OBJECT持久化方式(不建议使用该方法)。</p>
	 * <p>辅助账生产方法。</p>
	 * <p>辅助账对象中必须传入元素如下：</p>
	 * <p>属性1：辅助账内容 /asstActNumber (表示该辅助账的具体含义)【必填】</p>
	 * 
	 * <p>其它传入对象说明：</p>
	 * <p>对象1：VoucherassStactsInfo，辅助帐类型编码/asstActType (表示该条辅助账的含义，例如：001客户，002物料,该条件封装入辅助账类型ENTITY(VoucherassStactsInfo)中) 【必填】</p>
	 * <p>对象2：凭证头ENTITY,IntereasVoucherHead (用于取凭证体表中的UUID作为凭证体表中voucherNumber的值)【必填】</p>
	 * <p>对象3： 凭证体ENTITY,IntereasVoucherEntries，凭证体行号/entrySeq (凭证体entity中获取,辅助账是在凭证体存入数据库之后做的操作) 【必填】</p>
	 * <p>对象4：User  当前登录人对应的ENTITY 【必填】</p>
	 * <p>对象4：CustInfo  客户信息对应的ENTITY 【必填】</p>
	 * @author sea
	 * @param intereas_voucherasstacts 辅助账部分必填信息entity集合,目前只有一个属性值需要在调用该方法时封装入该ENTITY中(com.business.entity.voucher.IntereasVoucherasStacts)
	 * @param voucherassStactsInfo   辅助账类型entity   (com.business.entity.voucher.VoucherassStactsInfo)
	 * @param custInfo   客户信息entity   (com.business.entity.cust.CustInfo)
	 * @param intereasVoucherentries   凭证体entity(com.business.entity.voucher.IntereasVoucherEntries)
	 * @param intereasVoucherhead      凭证头entity(com.business.service.voucher.IntereasVoucherHead) 
	 * @param user      当前登录人信息entity(com.business.entity.User)
	  * @return 返回完整的辅助账ENTITY，IntereasVoucherasStacts
	  * @throws Exception
	 */
	public IntereasVoucherasStacts saveIntereasVoucherasstacts(IntereasVoucherasStacts intereas_voucherasstacts,VoucherassStactsInfo voucherassStactsInfo,CustInfo custInfo,IntereasVoucherEntries intereasVoucherentries,IntereasVoucherHead intereasVoucherhead,User user) throws Exception;
	
	/**
	 * 
	 * <p>根据辅助账类型编号查询辅助账类型整个对象。</p>
	 * @author sea
	 * @param asstActType 辅助账类型编号,例如：001 客户 ，002经销商等等
	 * @return VoucherassStactsInfo
	 * @throws Exception 
	 */
	public VoucherassStactsInfo getVoucherassStactsInfo(String asstActType) throws Exception;
}
