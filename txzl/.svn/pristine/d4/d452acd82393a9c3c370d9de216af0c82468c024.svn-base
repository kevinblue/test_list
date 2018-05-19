package com.tenwa.leasing.service.voucher;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.tenwa.business.entity.User;
import com.tenwa.leasing.entity.voucher.InterV8Vouchers;
/**   
*    
* 项目名称：tls5.1   
* 类名称：VoucherToV8Service   
* 类描述：   
* 创建人：rovine   
* 创建时间：2014年12月8日 下午5:46:08   
* @version        
*/
public interface VoucherToV8Service {

	/**
	 * <p>V8凭证数据入库。</p>
	  * <p>V8凭证数据入库:该方法调用一次产生一张凭证，分录参数请按顺序封装。</p>
	  * @author sea
	  * @param headMap
	  * headMap参数集合传入的数据目前存在：
	  * 参数1：合同号/contract_id  (合同表UUID，用于取凭证所属，合同信息等数据) 
	  * 参数2：业务模块/modleName (例如：已开票增值税发票回导流程/网银核销流程/租金实收流程...等等)
	  * Map<String,String> map1 = new HashMap<String, String>();
	  * map1.put("contract_id", "合同号");
	  * map1.put("moduleName", "业务模块");
	  * map1.put("accNumber", "本方银行账号编码");//网银凭证产生情况下，该字段必填 ,同时请务必把前一个参数‘moduleName’置为‘网银导入’。
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
	 * @throws DataAccessException 
	 */
	public  String saveV8Message(Map<String,String> headMap,List<Map<String,String>> list,User user) throws DataAccessException, Exception;
	
	//
	public  String saveEvidenceInfo(Map<String,String> headMap,List<Map<String,String>> list,User user) throws DataAccessException, Exception;
	
	
	
	/**
	  * <p>查询待导出信息。</p>
	  * @author sea
	  * @param model 凭证查询页面传入的所有查询条件，键值对封装
	  * @return
	 * @throws Exception 
	 * @throws DataAccessException 
	 */
	public String updateMessage(Map<String,String> model,User user) throws DataAccessException, Exception;
	
	/**
	  * <p>记录导出日志操作。</p>
	  * @author sea
	  * @param url 下载的路径
	  * @param user 导出人信息 
	  * @param message 导出的凭证信息记录 
	 * @throws Exception 
	 */
	public void saveFileList(String url, User user,String message) throws Exception;	
	
	/**
	  * <p>反射取值。</p>
	  * @author sea
	  * @param interV8Vouchers
	 */
	public void getString(InterV8Vouchers interV8Vouchers);
}
