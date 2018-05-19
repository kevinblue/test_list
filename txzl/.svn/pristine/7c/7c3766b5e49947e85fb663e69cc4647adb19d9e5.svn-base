package com.tenwa.leasing.service.fund.fundcollection;

import java.util.Map;

import org.json.JSONObject;

import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.contract.ContractInfo;

public interface FundCollectionService {

	
	/**
	 * @param JObj  本次收款json 值
	 * @param contractInfo  合同信息
	 * @param fundEbankData 网银信息
	 * 说明：把json 值 save 到contract_fund_fund_charge 和网银表中
	 */
	public void getCollectJsonObjStr(JSONObject JObj,ContractInfo contractInfo,FundEbankData fundEbankData);
	
	
	/**
	 * @param mapProperties
	 * 说明 : 处理收款流程第一步 提交网银 校验可用金额
	 */
	public String addEbankInfoImpl(Map<String,String> mapProperties);
	
	
}
