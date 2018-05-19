
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.service
 * 文件名：         PermissionRemoteCached.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-8-31-下午06:19:38
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.service;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Locale;
import java.util.Map;


 /**
 * 类名称：     PermissionRemoteCached
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-8-31 下午06:19:38
 * 修改备注：
 * @version 1.0.0
 **/

public interface RemoteSynchronize {
	public boolean remoteWorkflowSynchronized(String deployId)   throws RemoteException;
	public boolean remoteXmlDataSynchronized(Map<String,Map<String,String>> allXMLTable,Map<String,Map<String,String>> allXMLChart )   throws RemoteException;
	public boolean remotePermissionSynchronized(
			Map<Locale, Map<String, String>> userOwnedMenusJson,
			Map<String, List<Map<String, String>>> userOwnedMenus,
			Map<String, List<Map<String, String>>> userOwnedLeafMenus,
			Map<String, List<Map<String, String>>> userOwnedResources,
			Map<String, List<Map<String, String>>> userOwnedActions,
			Map<String, List<Map<String, String>>> userOwnedWorkflowStarts,
			Map<String, List<Map<String, String>>> userOwnedWorkflowViews,
			Map<String, List<String>> resourceOwnedUsers,
			Map<String, String> userOwnedWorkflowStartSqlIdsStr,
			Map<String, String> userOwnedWorkflowViewSqlIdsStr)
			throws RemoteException; 
}
