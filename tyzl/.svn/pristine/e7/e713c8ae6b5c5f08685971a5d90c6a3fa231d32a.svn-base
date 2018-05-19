package com.tenwa.leasing.serviceImpl.vouchersFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.contract.ContractInvoiceType;
import com.tenwa.leasing.service.voucher.VoucherToV8Service;
import com.tenwa.leasing.service.vouchersFactory.FundCollectionVoucherService;
import com.tenwa.leasing.utils.Tools;

/**   
*    
* 项目名称：tls5.1   
* 类名称：FundCollectionVoucherServiceImpl   
* 类描述：   
* 创建人：rovine   
* 创建时间：2014年12月9日 下午3:38:15   
* @version        
*/
@Service(value = "fundCollectionVoucherService")
public class FundCollectionVoucherServiceImpl implements FundCollectionVoucherService {

	@Resource(name = "baseService")
	private BaseService baseService;

	@Resource(name = "voucherToV8Service")
	private VoucherToV8Service voucherToV8Service;
	
	private static String modlename="收款流程";

	/* 
	 *凭证：收款确认
	 */
	@Override
	public void createVoucherReceiveFund(ContractInfo contractInfo,String jsonFundString,FundEbankData fbd) throws Exception {
		
		/************************************** 凭证体1-分割线-开始 ********************************************************/
		//根据发票类型确定增值税或是营业税
		  //获取租金发票类型
		Map<String, Object> proMap=new HashMap<String, Object>();
		proMap.put("contractId",contractInfo);
		List<ContractInvoiceType> listciy=this.baseService.findEntityByProperties(ContractInvoiceType.class, proMap);
		String rentInvoiceType="";
		if(listciy.size()>0){
			rentInvoiceType=listciy.get(0).getRentInvoiceType().getCode();
		}else{
			throw new BusinessException("该合同没有找到租金发票类型！");
		}
		String currentDate = DateUtil.getSystemDate();                                                                                                  // 当前时间
		User currentUser = SecurityUtil.getPrincipal();  
		
		/* 参数1：合同号/contract_id  (合同表UUID，用于取凭证所属，合同信息等数据) 
		  * 参数2：业务模块/modleName (例如：已开票增值税发票回导流程/网银核销流程/租金实收流程...等等)*/
		
		Map<String,String> headMap = new HashMap<String, String>();
    	if(contractInfo.getId()!=null){
			headMap.put("contract_id", contractInfo.getId());
		}else{
			throw new BusinessException("合同号为空，不能生成凭证！");
		}
		headMap.put("moduleName",modlename);
		//获取合同号传入摘要
		String contractnum=contractInfo.getContractNumber();
		
		/*参数二集合传入的数据是一个list集合，里面是一个个的map集合，一个map封装一个凭证分录
		  * 参数1：摘要/F5 
		  * 参数2：科目序号/F6  VOUCHERASS_STACTS_CONFIG表的ID
		  * 参数3：借方金额/F7   原币金额  贷方金额为0
		  * 参数4：贷方金额 /F8  原币金额  借方金额为0
		  * 参数5：到账日期/F15 网银到账日期 (会计记账日期通过函数getAccountDay传入业务日期获取,已提供公用查询接口getBookDate，也可以自行指定日期)
		  //* 参数6：行号/F59
		  * 参数7：供应商编码/F19  供应商编码 可为空
		  * 参数8：会计记账日期/F1 (自行指定日期情况下,可不传) */
		  //不含税融资额
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		  
		JSONArray jsonArray = new JSONArray(jsonFundString);
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			//收款金额
			String sum = jsonObj.optString("factmoney");
			String feetype = jsonObj.optString("feetype");
			//收款获取本次收款明细中的会计处理日期
			String accountDate=jsonObj.optString("accountingdate")==null?"":jsonObj.optString("accountingdate");
			
			//String rollback = jsonObj.optString("rollback");
			
			//if(rollback != null && rollback.equals("1")){
			//}
			//获取资金类型的科目id 增值税对应取PROP_TWO_字段，营业税对应取PROP_THREE_
			DictionaryData FeeType=this.baseService.findEntityByID(DictionaryData.class,feetype);
			// 收款确认为:手续费、租前息、期末购买权、期末退还保证金、期末抵充保证金 生成凭证
			// 营业税发票
			String map2F6=null;
			if ("invoice_type05".equals(rentInvoiceType)) {
				if (FeeType.getPropThree() != null) {
					map2F6=FeeType.getPropThree();// VOUCHERASS_STACTS_CONFIG表中SUBJECTS_CODE对应的SUBJECTS_ID
				}
			} else {// 增值税发票
				if (FeeType.getPropTwo() != null) {
					map2F6=FeeType.getPropThree();// VOUCHERASS_STACTS_CONFIG表中SUBJECTS_CODE对应的SUBJECTS_ID
				}
			}
			if (map2F6!=null){
				// 借1
				Map<String, String> map1 = new HashMap<String, String>();
				String mapF5="收" + FeeType.getName() + "(" + contractnum+ ")";
				map1.put("F5", mapF5);
				map1.put("F6", "108");// VOUCHERASS_STACTS_CONFIG表中SUBJECTS_CODE对应的SUBJECTS_ID
				map1.put("F7", Tools.formatNumberDoubleTwo(sum));// 借方金额
				map1.put("F8", "0"); // 贷方金额
				// 租金红冲没有网银信息
				if (fbd == null) {
					map1.put("F15", currentDate);// 网银到账日期
				} else {
					map1.put("F15", fbd.getFactDate());// 网银到账日期
				}
				// * map1.put("F59", "行号");
				map1.put("F19", ""); // 供应商编码 可为空
				map1.put("F1", accountDate);// 自行指定日期情况下,可不传
				list.add(map1);

				// 贷1
				Map<String, String> map2 = new HashMap<String, String>();
				map2.put("F5", mapF5);
				map2.put("F6", map2F6);
				map2.put("F7", "0");// 借方金额
				map2.put("F8", Tools.formatNumberDoubleTwo(sum)); // 贷方金额
				// 租金红冲没有网银信息
				if (fbd == null) {
					map2.put("F15", currentDate);// 网银到账日期
				} else {
					map2.put("F15", fbd.getFactDate());// 网银到账日期
				}
				// * map2.put("F59", "行号");
				map2.put("F19", ""); // 供应商编码 可为空
				map2.put("F1", accountDate);// 自行指定日期情况下,可不传
				list.add(map2);
			}
	    }
		if(list.size()>0){
	        voucherToV8Service.saveV8Message(headMap, list, currentUser);
		}
	}
	
}
