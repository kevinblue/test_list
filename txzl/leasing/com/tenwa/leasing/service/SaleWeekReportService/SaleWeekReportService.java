package com.tenwa.leasing.service.SaleWeekReportService;   

import java.util.Map;

import com.tenwa.leasing.entity.assetnetworkmonitor.AssetNetMonitorApply;


/**
 * @author 作者 E-mail:
 * @version 创建时间：2014-2-17 下午1:38:07
 * 类说明
 */
public interface SaleWeekReportService {
	
	public void addSaleWeekReportApplication(Map<String, String> variablesMap) throws Exception;
	public void deleteSaleWeekReport(Map<String, String> variablesMap) throws Exception;
	/*public void updateWorkWeekReport(Map<String, String> variablesMap) throws Exception;
	public void updateWorkWeekReportApplication(Map<String, String> variablesMap) throws Exception;
	
	public void deleteWorkWeekReportDetailApplication(Map<String, String> variablesMap) throws Exception;*/
	public void updateSaleWeekReport(Map<String, String> variablesMap) throws Exception;
	
	
}
  
