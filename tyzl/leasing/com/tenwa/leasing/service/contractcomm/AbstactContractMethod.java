package com.tenwa.leasing.service.contractcomm;

import java.util.Map;

public abstract interface AbstactContractMethod {

	/**
	 * @author caiyc
	 * @param o   传projInfo|contractInfo
	 * @param variablesMap
	 * @return 从项目层把数据带到合同层,显示项目(合同)基本信息
	 */
	public void getProjToMapProjBaseInfo(Object o,
			Map<String, String> variablesMap);
	
	/**
	 * @author caiyc
	 * @param projInfo
	 * @param contractInfo
	 * @param mainObjectMap
	 * @param variablesMap
	 * @return 传一个合同(项目)对象获取合同信息  <租赁物明细、担保单位、抵押物清单 等 (项目合同层通用的表单信息)>
	 */
	public void getProjOtherBussieseeMethodsInfo(Object o,
			Map<String,String> mainObjectMap, Map<String, String> variablesMap);
	
	
}
