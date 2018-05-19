package com.tenwa.leasing.service.fund.redout;

import java.util.List;

import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.fund.ContractFundFundCharge;

public interface FundRedOutService {

	/**
	 * 说明： 红冲手工导入问题处理  特殊情况下 如果网银编号为空的情况,虚拟一笔网银到网银表
	 * @param fundFundChargeList 实收表list
	 * @param contractinfo  合同信息
	 * @throws Exception
	 */
//	public FundEbankData getStartRedOutInfo(List<ContractFundFundCharge> fundFundChargeList,ContractInfo contractinfo) throws Exception;
	public void getStartRedOutInfo(List<ContractFundFundCharge> fundFundChargeList,ContractInfo contractinfo) throws Exception;
	
	
	
}
