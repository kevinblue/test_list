package com.tenwa.leasing.service.assetsnetmonitor;   

import java.util.Map;

import com.tenwa.leasing.entity.assetnetworkmonitor.AssetNetMonitorApply;


/**
 * @author 作者 E-mail:
 * @version 创建时间：2014-2-17 下午1:38:07
 * 类说明
 */
public interface AssetsNetMonitorService {
	
	public void addAssetsNetMonitor(AssetNetMonitorApply anma) throws Exception;
	
	public void updateAssetsNetMonitorApplication(Map<String, String> variablesMap) throws Exception;
	public void addAssetsNetMonitorApplication(Map<String, String> variablesMap) throws Exception;
	public void deleteAssetsNetMonitorApplication(Map<String, String> variablesMap) throws Exception;
}
  
