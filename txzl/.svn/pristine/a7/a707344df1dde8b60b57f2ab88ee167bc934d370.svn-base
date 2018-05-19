
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.kernal.utils
 * 文件名：         RemoteUtil.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-9-6-下午01:50:07
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.kernal.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import com.tenwa.business.service.RemoteSynchronize;
import com.tenwa.hessian.service.HessianService;


 /**
 * 类名称：     RemoteUtil
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-9-6 下午01:50:07
 * 修改备注：
 * @version 1.0.0
 **/

public class RemoteUtil {
	private static final List<RemoteSynchronize> remoteSynchronizes = new ArrayList<RemoteSynchronize>();
	private static final Class<RemoteSynchronize> serviceInterface = RemoteSynchronize.class;
	private static final boolean lookupStubOnStartup = false;
	private static final boolean refreshStubOnConnectFailure = true;
	private static final boolean cacheStub = false;
	
    private static void getRemoteSynchronizes(){
    	if(0 < remoteSynchronizes.size())return;
    	String clusterRMIs = ResourceUtil.getRMICluster();
    	String localRmi    = "rmi://"+ResourceUtil.getRMILocalIp()+":"+ResourceUtil.getRMILocalPort()+"/"+ResourceUtil.getRMILocalServiceName();
    	for(String clusterRMI:clusterRMIs.replaceAll("\\s","").split("\\|")){
    		if(localRmi.equals(clusterRMI))continue;
    		RmiProxyFactoryBean rmiNode = new RmiProxyFactoryBean();
    		rmiNode.setServiceUrl(clusterRMI);
    		rmiNode.setServiceInterface(serviceInterface);
    		rmiNode.setLookupStubOnStartup(lookupStubOnStartup);
    		rmiNode.setRefreshStubOnConnectFailure(refreshStubOnConnectFailure);
    		rmiNode.setCacheStub(cacheStub);
    		rmiNode.afterPropertiesSet(); //更改ServiceInterface或ServiceUrl之后必须调用该方法，来获取远程调用桩  
    		RemoteSynchronize remoteSynchronize = (RemoteSynchronize)rmiNode.getObject();
    		remoteSynchronizes.add(remoteSynchronize);
    		System.out.println(clusterRMI+":"+remoteSynchronize);
    	}
    }
    
    
    public static boolean remotePermissionSynchronized(
			final Map<Locale, Map<String, String>> userOwnedMenusJson,
			final Map<String, List<Map<String, String>>> userOwnedMenus,
			final Map<String, List<Map<String, String>>> userOwnedLeafMenus,
			final Map<String, List<Map<String, String>>> userOwnedResources,
			final Map<String, List<Map<String, String>>> userOwnedActions,
			final Map<String, List<Map<String, String>>> userOwnedWorkflowStarts,
			final Map<String, List<Map<String, String>>> userOwnedWorkflowViews,
			final Map<String, List<String>> resourceOwnedUsers,
			final Map<String, String> userOwnedWorkflowStartSqlIdsStr,
			final Map<String, String> userOwnedWorkflowViewSqlIdsStr )
    {
    	boolean success = true;
     	try {
	    	getRemoteSynchronizes();
			for(final RemoteSynchronize remoteSynchronize :remoteSynchronizes){
				remoteSynchronize.remotePermissionSynchronized(userOwnedMenusJson, userOwnedMenus,userOwnedLeafMenus, userOwnedResources, userOwnedActions, userOwnedWorkflowStarts, userOwnedWorkflowViews, resourceOwnedUsers, userOwnedWorkflowStartSqlIdsStr, userOwnedWorkflowViewSqlIdsStr);
			}
		}catch(Exception e){
			e.printStackTrace();
			success = false;
		}
    	return success;
    }
    public static boolean remoteWorkflowSynchronized(final String deployId)
    {
    	boolean success = true;
    	
    	try {
    		getRemoteSynchronizes();
    		for(final RemoteSynchronize remoteSynchronize :remoteSynchronizes){
    			remoteSynchronize.remoteWorkflowSynchronized(deployId);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		success = false;
    	}
    	return success;
    }
    public static boolean remoteXmlDataSynchronized(Map<String,Map<String,String>> allXMLTable,Map<String,Map<String,String>> allXMLChart )
    {
    	boolean success = true;
    	
    	try {
    		getRemoteSynchronizes();
    		for(final RemoteSynchronize remoteSynchronize :remoteSynchronizes){
    			remoteSynchronize.remoteXmlDataSynchronized(allXMLTable,allXMLChart);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		success = false;
    	}
    	return success;
    }
    public static void main(String []args) throws Exception{
    	//RemoteUtil.remoteWorkflowSynchronized("");
    	String clusterRmi = "rmi://10.1.3.106:1098/synchronizeRemoteFlow";
    	RmiProxyFactoryBean rmiNode = new RmiProxyFactoryBean();
		rmiNode.setServiceUrl(clusterRmi);
		rmiNode.setServiceInterface(HessianService.class);
		rmiNode.setLookupStubOnStartup(lookupStubOnStartup);
		rmiNode.setRefreshStubOnConnectFailure(refreshStubOnConnectFailure);
		rmiNode.setCacheStub(cacheStub);
		rmiNode.afterPropertiesSet(); //更改ServiceInterface或ServiceUrl之后必须调用该方法，来获取远程调用桩  
		HessianService object = (HessianService)rmiNode.getObject();
		object.getEndAction("contractBackStartAction", "ff8080815c670094015c766036c301ee", "111");
    }
}
