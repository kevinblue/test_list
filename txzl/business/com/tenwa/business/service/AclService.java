package com.tenwa.business.service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.tenwa.business.entity.Action;
import com.tenwa.business.entity.Department;
import com.tenwa.business.entity.Dictionary;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.Menu;
import com.tenwa.business.entity.Resource;
import com.tenwa.business.entity.User;
import com.tenwa.business.entity.base.BaseDocumentConfig;
import com.tenwa.business.entity.base.BaseDocumentConfigData;
import com.tenwa.business.entity.base.BaseRole;
import com.tenwa.business.entity.base.BaseRoleBlock;

public interface AclService  extends BaseService
{      
	   //缓存系统信息到中间库，兼容负载均衡和集群模式
	   public void saveOrUpdatePermissionCachedToDB(
	        Map<String,Map<String,String>>       allXMLTable             ,
	        Map<String,Map<String,String>>       allXMLChart			 ,
	        Map<String,Map<String, String>>      userOwnedMenusJson 	 ,
	        Map<String,List<Map<String,String>>> userOwnedMenus      	 ,
	        Map<String,List<Map<String,String>>> userOwnedResources  	 ,
	        Map<String,List<Map<String,String>>> userOwnedActions        ,
	        Map<String,List<Map<String,String>>> userOwnedWorkflowStarts ,
	        Map<String,List<Map<String,String>>> userOwnedWorkflowViews  ,
	        Map<String,List<String>>             resourceOwnedUsers      ,
	        Map<String, String> 				 userOwnedWorkflowStartSqlIdsStr, 
	        Map<String, String> 				 userOwnedWorkflowViewSqlIdsStr
	   ) throws Exception;
	 //缓存系统信息到中间库，兼容负载均衡和集群模式结束
	   public void addOrRemoveDistribute(String entityClassName,String leftFieldName,String leftId,String rightFieldName,String rightId,Map<String,String> model) throws Exception;    
	   public void addOrRemoveQuickMenuDistribute(String entityClassName,
				String leftFieldName, String leftId, String rightFieldName,
				String rightIds,Map<String,String> model) throws Exception;
	   //用户
	   public User   findUserByUserName(String username) throws Exception;
	   public String addUser(Map<String,String> model) throws Exception;
	   public String updateUser(String userId,Map<String,String> model) throws Exception;
	   //部门管理
	   public void   saveOrUpdateDeptWithPosition(Department dept,String parentDeptId, String currentPosition) throws Exception;
	   public void   removeDept(String deptId) throws Exception;
	   //菜单
	   public void   saveOrUpdateMenuWithPosition(Menu menu,String parentMenuId, String currentPosition) throws Exception;
	   public void   removeMenu(String menuId) throws Exception;
	   public String getMenusTreeMenu(Locale locale) throws Exception ;
	   //资源
	   public void   saveOrUpdateResourceWithPosition(Resource resource,String parentResourceId, String currentPosition) throws Exception;
	   public void   removeResource(String resourceId) throws Exception;
	   //操作action
	   public void   saveOrUpdateActionWithPosition(Action action,String parentActionId, String currentPosition) throws Exception;
	   public void   removeAction(String actionId) throws Exception;
	   //数据字典
	   public void saveOrUpdateDictionaryWithPosition(Dictionary dictionary, String parentDictId, String currentPosition) throws Exception;
	   public String getDictionariesTreeMenu(String rootParentDictId) throws Exception;
	   public void removeDictionary(String dictId) throws Exception;
	   //数据字典数据
	   public void saveOrUpdateDictionaryDataWithPosition(DictionaryData dictionaryData, String parentDictDataId,String currentPosition) throws Exception; 
	   //树形转表
	   public void   saveOrUpdateBaseDocumentConfigWithPosition(BaseDocumentConfig baseDocumentConfig, String parentDocumentId, String currentPosition) throws Exception;
	   public String getBaseDocumentConfigsTreeMenu(String rootParentBaseDocumentConfig) throws Exception;
	   public void   removeBaseDocumentConfig(String baseDocumentConfigId) throws Exception;
	   //树转表评分规则
	   public void   saveOrUpdateBaseRoleWithPosition(BaseRole baseRole, String pid, String currentPosition) throws Exception;
	   public void   saveOrUpdateBaseRoleBlockWithPosition(BaseRoleBlock baseRoleBlock, String pid, String currentPosition) throws Exception;
	   //树形转表数据
	   public void saveOrUpdateBaseDocumentConfigDataWithPosition(BaseDocumentConfigData dictionaryData, String parentDictDataId,String currentPosition) throws Exception;
	   //定位到用户主页
	   public String getCurrentUserIndexPageUrl() throws Exception;
	   //修改密码
	   public String modifyUserPassword(String currentUserId, String oldPassword,String newPassword,String telephone,String email) throws Exception;
	
	   public String updateAndCheckLicenseInfo(MultipartFile privateKeyMultipartFile,MultipartFile authorizeInfoMultipartFile,Map<String, String> modelData) throws Exception;
	   public String updateAllPasswordToSixOne() throws Exception;
	   public void updateAdSynchronizedUsers() throws Exception;
}
