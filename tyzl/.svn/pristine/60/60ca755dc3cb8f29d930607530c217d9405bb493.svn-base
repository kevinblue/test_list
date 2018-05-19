
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.serviceImpl
 * 文件名：         PermissionRemoteCachedImpl.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-8-31-下午06:23:07
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.serviceImpl;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.jbpm.pvm.internal.env.EnvironmentFactory;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.jbpm.pvm.internal.repository.RepositoryCache;
import org.springframework.stereotype.Service;

import com.tenwa.business.service.RemoteSynchronize;
import com.tenwa.jbpm.utils.JBPMUtil;
import com.tenwa.kernal.utils.PermissionUtil;
import com.tenwa.kernal.utils.WebUtil;


 /**
 * 类名称：     PermissionRemoteCachedImpl
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-8-31 下午06:23:07
 * 修改备注：
 * @version 1.0.0
 **/
@Service(value="remoteSynchronize")
public class RemoteSynchronizeImpl /*extends UnicastRemoteObject*/  implements RemoteSynchronize  {
	private static final long serialVersionUID = 1L;
	
	public RemoteSynchronizeImpl() throws RemoteException {
    }
	//缓存系统信息到中间库，兼容负载均衡和集群模式
	@Override
	public synchronized boolean remotePermissionSynchronized(		        
			Map<Locale,Map<String, String>>      userOwnedMenusJson 	 ,
	        Map<String,List<Map<String,String>>> userOwnedMenus      	 ,
	        Map<String,List<Map<String,String>>> userOwnedLeafMenus      ,
	        Map<String,List<Map<String,String>>> userOwnedResources  	 ,
	        Map<String,List<Map<String,String>>> userOwnedActions        ,
	        Map<String,List<Map<String,String>>> userOwnedWorkflowStarts ,
	        Map<String,List<Map<String,String>>> userOwnedWorkflowViews  ,
	        Map<String,List<String>>             resourceOwnedUsers      ,
	        Map<String, String> 				 userOwnedWorkflowStartSqlIdsStr, 
	        Map<String, String> 				 userOwnedWorkflowViewSqlIdsStr
    ) throws RemoteException {
		boolean success = true;
            try {
				PermissionUtil.cachedAllUserTreeMenus(false);
			} catch (Exception e) {
				success = false;
				e.printStackTrace();
			}
			return success;
	}
	
	@Override
	public synchronized boolean  remoteWorkflowSynchronized(String deployId) throws RemoteException {
		boolean success = true;
		//更新jbpm缓存
		EnvironmentImpl env = ((EnvironmentFactory)JBPMUtil.getProcessEngine()).openEnvironment();
		try{
	        RepositoryCache repositoryCache = (RepositoryCache)env.get(RepositoryCache.class);
	        repositoryCache.remove(deployId);
		} catch(Exception e){
			success = false;
			e.printStackTrace();
		}
		finally{
		   env.close();
		}	 
		return success;
	}
	
	@Override
	public boolean remoteXmlDataSynchronized(Map<String,Map<String,String>> allXMLTable,Map<String,Map<String,String>> allXMLChart )
			throws RemoteException {
		boolean success = true;
		//更新xmlData缓存数据
		try{
		    WebUtil.setAllXMLTable(allXMLTable);
			WebUtil.setAllXMLChart(allXMLChart);
		} catch(Exception e){
			success = false;
			e.printStackTrace();
		}
		return success;
	}

}
