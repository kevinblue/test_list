package com.tenwa.leasing.service.vouchersFactory;   

import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.contract.ContractInfo;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2014-2-17 下午1:38:07
 * 类说明
 */
public interface FundCollectionVoucherService {
	/**
	 * @param cinfo
	 * @param jsonFundString
	 * @param fbd
	 * @throws Exception
	 */
	public void createVoucherReceiveFund(ContractInfo cinfo,String jsonFundString,FundEbankData fbd) throws Exception;
	
}
  
