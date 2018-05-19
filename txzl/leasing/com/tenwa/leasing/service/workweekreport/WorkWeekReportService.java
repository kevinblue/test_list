package com.tenwa.leasing.service.workweekreport;   

import java.util.Map;

import com.tenwa.leasing.entity.assetnetworkmonitor.AssetNetMonitorApply;


/**
 * @author 作者 E-mail:
 * @version 创建时间：2014-2-17 下午1:38:07
 * 类说明
 */
public interface WorkWeekReportService {
	
	public void addWorkWeekReportApplication(Map<String, String> variablesMap) throws Exception;
	public void updateWorkWeekReport(Map<String, String> variablesMap) throws Exception;
	public void updateWorkWeekReportApplication(Map<String, String> variablesMap) throws Exception;
	public void deleteWorkWeekReport(Map<String, String> variablesMap) throws Exception;
	
	public void deleteWorkWeekReportDetailApplication(Map<String, String> variablesMap) throws Exception;
	public void deleteNextWorkWeekReportDetailApplication(Map<String, String> variablesMap) throws Exception;
}
  
