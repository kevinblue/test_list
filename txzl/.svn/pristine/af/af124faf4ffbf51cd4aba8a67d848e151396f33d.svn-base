/**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.kernal.utils
 * 文件名：         PermissionUtil.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-3-5-上午10:45:20
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.kernal.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.tenwa.business.entity.Action;
import com.tenwa.business.entity.ActionDepartment;
import com.tenwa.business.entity.ActionDepartmentRole;
import com.tenwa.business.entity.ActionGroup;
import com.tenwa.business.entity.Department;
import com.tenwa.business.entity.DepartmentRole;
import com.tenwa.business.entity.Group;
import com.tenwa.business.entity.Menu;
import com.tenwa.business.entity.MenuDepartment;
import com.tenwa.business.entity.MenuDepartmentRole;
import com.tenwa.business.entity.MenuGroup;
import com.tenwa.business.entity.QuickUserMenu;
import com.tenwa.business.entity.Resource;
import com.tenwa.business.entity.ResourceDepartment;
import com.tenwa.business.entity.ResourceDepartmentRole;
import com.tenwa.business.entity.ResourceGroup;
import com.tenwa.business.entity.User;
import com.tenwa.business.entity.UserAction;
import com.tenwa.business.entity.UserDepartment;
import com.tenwa.business.entity.UserDepartmentRole;
import com.tenwa.business.entity.UserGroup;
import com.tenwa.business.entity.UserMenu;
import com.tenwa.business.entity.UserResource;
import com.tenwa.business.service.AclService;
import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.entity.JbpmWorkflowDesigner;
import com.tenwa.jbpm.entity.UserWorkflowStart;
import com.tenwa.jbpm.entity.UserWorkflowView;
import com.tenwa.jbpm.entity.WorkflowStartDepartment;
import com.tenwa.jbpm.entity.WorkflowStartDepartmentRole;
import com.tenwa.jbpm.entity.WorkflowStartGroup;
import com.tenwa.jbpm.entity.WorkflowViewDepartment;
import com.tenwa.jbpm.entity.WorkflowViewDepartmentRole;
import com.tenwa.jbpm.entity.WorkflowViewGroup;
import com.tenwa.kernal.i18n.LocaleEnum;

/**
 * 类名称： PermissionUtil
 * 类描述：
 * 创建人： Administrator
 * 修改人： Administrator
 * 修改时间：2013-3-5 上午10:45:20
 * 修改备注：
 * 
 * @version 1.0.0
 **/

public class new_PermissionUtil
{
	private static void menuCallBack(User user, Menu menu, String from) {
	}

	private static void resourceCallBack(User user, Resource resource, String from) {
	}

	private static void actionCallBack(User user, Action action, String from) {
	}

	private static void workflowStartCallBack(User user, JbpmWorkflowDesigner workflowStart, String from) {
	}

	private static void workflowViewCallBack(User user, JbpmWorkflowDesigner workflowView, String from) {
	}

	private static Log			log			= LogFactory.getLog(new_PermissionUtil.class);
	private static AclService	baseService	= (AclService) WebUtil.getApplicationContext().getBean("aclService");
  
	// 通过用户获取menu
	private static void getUserMenu(User user, Map<String, Set<Menu>> userMenus) {
		final String username = user.getUsername();
		Set<UserMenu> currentUserMenus = user.getUserMenus();
		for (UserMenu userMenu : currentUserMenus) {
			Menu menu = userMenu.getMenu();
			menuCallBack(user, menu, "UserMenu");
			Set<Menu> currentMenus = userMenus.get(username);
			if (null == currentMenus) {
				currentMenus = new HashSet<Menu>();
				userMenus.put(username, currentMenus);
			}
			currentMenus.add(menu);
		}
	}

	// 通过部门获取menu
	private static void getUserMenuByDept(User user, Map<String, Set<Menu>> userMenus) {
		final String username = user.getUsername();
		Set<UserDepartment> userDepts = user.getUserDepts();
		for (UserDepartment userDept : userDepts) {
			Department dept = userDept.getDept();
			Set<MenuDepartment> menuDepts = dept.getMenuDepts();
			for (MenuDepartment menuDept : menuDepts) {
				Menu menu = menuDept.getMenu();
				menuCallBack(user, menu, "MenuDepartment");
				Set<Menu> currentMenus = userMenus.get(username);
				if (null == currentMenus) {
					currentMenus = new HashSet<Menu>();
					userMenus.put(username, currentMenus);
				}
				currentMenus.add(menu);
			}
		}
	}

	// 通过部门角色获取menu
	private static void getUserMenuByDeptRole(User user, Map<String, Set<Menu>> userMenus) {
		final String username = user.getUsername();
		Set<UserDepartmentRole> userDeptRoles = user.getUserDeptRoles();
		for (UserDepartmentRole userDeptRole : userDeptRoles) {
			DepartmentRole deptRole = userDeptRole.getDeptRole();
			Set<MenuDepartmentRole> menuDeptRoles = deptRole.getMenuDeptRoles();
			for (MenuDepartmentRole menuDeptRole : menuDeptRoles) {
				Menu menu = menuDeptRole.getMenu();
				menuCallBack(user, menu, "MenuDepartmentRole");
				Set<Menu> currentMenus = userMenus.get(username);
				if (null == currentMenus) {
					currentMenus = new HashSet<Menu>();
					userMenus.put(username, currentMenus);
				}
				currentMenus.add(menu);
			}
		}
	}

	// 通过群组获取menu
	private static void getUserMenuByGroup(User user, Map<String, Set<Menu>> userMenus) {
		final String username = user.getUsername();
		Set<UserGroup> userGroups = user.getUserGroups();
		for (UserGroup userGroup : userGroups) {
			Group dept = userGroup.getGroup();
			Set<MenuGroup> menuGroups = dept.getMenuGroups();
			for (MenuGroup menuGroup : menuGroups) {
				Menu menu = menuGroup.getMenu();
				menuCallBack(user, menu, "MenuGroup");
				Set<Menu> currentMenus = userMenus.get(username);
				if (null == currentMenus) {
					currentMenus = new HashSet<Menu>();
					userMenus.put(username, currentMenus);
				}
				currentMenus.add(menu);
			}
		}
	}

	// 获取指定用户拥有的所有菜单
	private static void getUserMenus(User user, Map<String, Set<Menu>> userMenus) {

		getUserMenu(user, userMenus);
		getUserMenuByDept(user, userMenus);
		getUserMenuByDeptRole(user, userMenus);
		getUserMenuByGroup(user, userMenus);
	}
    private static boolean isQuickUserMenuChecked(String menuId,Set<QuickUserMenu> quickUserMenus){
    	boolean isChecked = false;
    	for(QuickUserMenu quickUserMenu : quickUserMenus){
    		if(menuId.equals(quickUserMenu.getMenu().getId())){
    			isChecked = true;
    			break;
    		}
    	}
    	return isChecked;
    }
	// 缓存单个用户的树形菜单
	private static void getUserMenusJsonObject(String username, Set<Menu> userMenus, Menu node, JSONObject jsonRootObject,
			JSONArray jsonArr,String firstLevelMenuId,Map<String,Set<QuickUserMenu>> quickUserMenusMap,Locale locale) throws JSONException, Exception
	{
		if (null == node)
			return;
		Menu parentMenu = node.getParentMenu();
		boolean isRoot = (null == parentMenu);
		String pid = "";
		if (isRoot) {
			pid = "0";
		}
		else {
			boolean isNext = false;
			for (Menu menu : userMenus) {
				if (node.getId().equals(menu.getId())) {
					isNext = true;
					break;
				}
			}
			if (!isNext) {
				return;
			}
			pid = parentMenu.getId();
		}
		JSONObject jsonObj = null;
		if (null != jsonRootObject) {
			jsonObj = jsonRootObject;
		}
		else
		{
			jsonObj = new JSONObject();
			jsonArr.put(jsonObj);
		}
        String text = WebUtil.getMessageByLocale(node.getCode(), null, node.getName(), locale);
		String icon = StringUtil.empty2Other(node.getIcon(),"node.png");
		String iconCls  = "icon-"+icon.substring(0,icon.lastIndexOf("."));
		JSONObject      attributes = getMenuArributesJsonObject(node);
		attributes.put("firstLevelMenuId", firstLevelMenuId);
		boolean isHashChildren = node.isHasChildren();
 		String state  = isHashChildren ? "closed":"open";
		jsonObj.put("id", StringUtil.nullToString(node.getId()))
				.put("pid", StringUtil.nullToString(pid))
				// modify by tracywindy 国际化菜单
				.put("name",StringUtil.nullToString(text))
				.put("text",StringUtil.nullToString(text))
				//.put("url", StringUtil.nullToString(node.getUrl()))
				.put("url", StringUtil.nullToString(node.getParameterizedUrl()))
				.put("checked", isQuickUserMenuChecked(node.getId(),quickUserMenusMap.get(username)))
				.put("icon",icon)
				.put("state",state)
				.put("iconCls",iconCls)
				.put("iconClose", StringUtil.nullToString(node.getIconClose()))
				.put("iconOpen", StringUtil.nullToString(node.getIconOpen()))
				.put("description", StringUtil.nullToString(node.getDescription()))
				.put("position", StringUtil.nullToString(node.getPosition()))
				.put("authorities", StringUtil.nullToString("ROLE_USER"))
				.put("attributes", attributes)
		        .put("firstLevelMenuId", firstLevelMenuId);
        
		JSONArray jsonArray = new JSONArray();
		jsonObj.put("children", jsonArray);
		if(isHashChildren)
		{
			Set<Menu> childrenNodes = node.getChildrenMenus();
			for (Menu mn : childrenNodes)
			{
				if(isRoot){
					firstLevelMenuId = mn.getId();
				}
				getUserMenusJsonObject(username, userMenus, mn, null, jsonArray,firstLevelMenuId,quickUserMenusMap,locale);
			}
		}
	}

	// 获取用户其他属性
	private static JSONObject getMenuArributesJsonObject(Menu node) throws Exception
	{
		JSONObject jsonObj = new JSONObject();
		Map<String, String> nodeAttributes = node.getAttributes();
		if (null != nodeAttributes)
		{
			for (String key : nodeAttributes.keySet())
			{
				String value = StringUtil.nullToString(nodeAttributes.get(key));
				jsonObj.put(key, StringUtil.getJsonString(value));
			}
		}
		return jsonObj;
	}

	// 缓存所有用户的树形菜单
	private static Map<String, Map<String, String>> getAllUserMenusJsonString(Map<String, Set<Menu>> allUserMenus, Menu rootMenu,Map<String,Set<QuickUserMenu>> quickUserMenusMap )
			throws JSONException, Exception
	{
		Map<String, Map<String, String>> localeUserMenusJsonStringMap = new HashMap<String, Map<String, String>>();
		for (LocaleEnum localeEmnu : LocaleEnum.values()) {
			Locale locale = localeEmnu.getLocale();
			Map<String, String> userMenusJsonStringMap = new HashMap<String, String>();
			localeUserMenusJsonStringMap.put(locale.getLanguage()+"_"+locale.getCountry(), userMenusJsonStringMap);
			for (String username : allUserMenus.keySet()) {
				Set<Menu> userMenus = allUserMenus.get(username);
				JSONObject rootJsonObj = new JSONObject();
				getUserMenusJsonObject(username, userMenus, rootMenu, rootJsonObj, null,"",quickUserMenusMap, locale);
				userMenusJsonStringMap.put(username, rootJsonObj.toString());
			}
		}

		return localeUserMenusJsonStringMap;
	}

	// 缓存所有用户拥有的所有树形菜单
	public static void cachedAllUserTreeMenus() throws Exception
	{
		baseService.getBussinessDao().getHibernateTemplate().execute(new HibernateCallback<Map<String, String>>() {
			@SuppressWarnings("unchecked")
			@Override
			public Map<String, String> doInHibernate(Session session)
					throws HibernateException, SQLException {
				log.info("重置用户菜单缓存");
				long startMilis = System.currentTimeMillis();
				// 用户菜单权限
				Map<String, Set<Menu>> userMenus = new HashMap<String, Set<Menu>>();
				//用户快捷菜单
				Map<String,Set<QuickUserMenu>> quickUserMenusMap = new HashMap<String,Set<QuickUserMenu>>();
				// 用户资源权限
				Map<String, Set<Resource>> userResources = new HashMap<String, Set<Resource>>();
				// 用户操作权限
				Map<String, Set<Action>> userActions = new HashMap<String, Set<Action>>();
				// 流程发起权限
				Map<String, Set<JbpmWorkflowDesigner>> userWorkflowStarts = new HashMap<String, Set<JbpmWorkflowDesigner>>();
				// 流程查看权限
				Map<String, Set<JbpmWorkflowDesigner>> userWorkflowViews = new HashMap<String, Set<JbpmWorkflowDesigner>>();
                
				List<User> users = session.createCriteria(User.class).list();
				session.flush();
				for (User user : users) {
					new_PermissionUtil.getUserMenus(user, userMenus);
					new_PermissionUtil.getUserResources(user, userResources);
					new_PermissionUtil.getUserActions(user, userActions);
					new_PermissionUtil.getUserWorkflowStarts(user, userWorkflowStarts);
					new_PermissionUtil.getUserWorkflowViews(user, userWorkflowViews);
					quickUserMenusMap.put(user.getUsername(), user.getQuickUserMenus());
				}
				// 设置menu
				Map<String, List<Map<String, String>>> userOwnedMenus = new HashMap<String, List<Map<String, String>>>();
				for (String username : userMenus.keySet())
				{
					List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
					Set<Menu> datas = userMenus.get(username);
					for (Menu node : datas) {
						Map<String, String> dataMap = EntityUtil.getEntityPropertiesToStringMap(node, null);
						dataList.add(dataMap);
					}
					userOwnedMenus.put(username, dataList);
				}
				//WebUtil.setUserOwnedMenus(userOwnedMenus);
				// 设置resource
				// 通过url拦截
				Map<String, List<String>> resourceOwnedUsers = new HashMap<String, List<String>>();
				Map<String, List<Map<String, String>>> userOwnedResources = new HashMap<String, List<Map<String, String>>>();
				for (String username : userResources.keySet())
				{
					List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
					Set<Resource> datas = userResources.get(username);
					for (Resource node : datas) {
						Map<String, String> dataMap = EntityUtil.getEntityPropertiesToStringMap(node, null);
						dataList.add(dataMap);
						String resourceUrl = node.getUrl();
						if (null != resourceUrl) {
							List<String> userNamesList = resourceOwnedUsers.get(resourceUrl);
							if (null == userNamesList) {
								userNamesList = new ArrayList<String>();
								resourceOwnedUsers.put(resourceUrl, userNamesList);
							}
							if (!userNamesList.contains(username)) {
								userNamesList.add(username);
							}
						}
					}
					userOwnedResources.put(username, dataList);
				}
				//WebUtil.setResourceOwnedUsers(resourceOwnedUsers);
				//WebUtil.setUserOwnedResources(userOwnedResources);
				// 设置action
				Map<String, List<Map<String, String>>> userOwnedActions = new HashMap<String, List<Map<String, String>>>();
				for (String username : userActions.keySet())
				{
					List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
					Set<Action> datas = userActions.get(username);
					for (Action node : datas) {
						Map<String, String> dataMap = EntityUtil.getEntityPropertiesToStringMap(node, null);
						dataList.add(dataMap);
					}
					userOwnedActions.put(username, dataList);
				}
				//WebUtil.setUserOwnedActions(userOwnedActions);
				// 设置流程发起权限
				Map<String, List<Map<String, String>>> userOwnedWorkflowStarts = new HashMap<String, List<Map<String, String>>>();
				Map<String, List<String>> userOwnedWorkflowStartSqlIds = new HashMap<String, List<String>>();
				for (String username : userWorkflowStarts.keySet())
				{
					List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

					Set<JbpmWorkflowDesigner> datas = userWorkflowStarts.get(username);
					for (JbpmWorkflowDesigner node : datas) {
						List<String> ownedWorkflowStartSqlIds = userOwnedWorkflowStartSqlIds.get(username);
						if (null == ownedWorkflowStartSqlIds) {
							ownedWorkflowStartSqlIds = new ArrayList<String>();
							userOwnedWorkflowStartSqlIds.put(username, ownedWorkflowStartSqlIds);
						}
						String workflowId = (null == node.getDeploymentImpl()) ? null : node.getDeploymentImpl().getId();

						if ((null != workflowId) && !ownedWorkflowStartSqlIds.contains(workflowId)) {
							ownedWorkflowStartSqlIds.add("'" + workflowId + "'");
						}

						Map<String, String> dataMap = EntityUtil.getEntityPropertiesToStringMap(node, null);
						dataList.add(dataMap);
					}
					userOwnedWorkflowStarts.put(username, dataList);
				}
				//WebUtil.setUserOwnedWorkflowStarts(userOwnedWorkflowStarts);
				Map<String, String> userOwnedWorkflowStartSqlIdsStr = new HashMap<String, String>();
				for (String username : userOwnedWorkflowStartSqlIds.keySet()) {
					String sqlIdsStr = StringUtil.join(userOwnedWorkflowStartSqlIds.get(username), ",");
					userOwnedWorkflowStartSqlIdsStr.put(username, sqlIdsStr);
				}
				userOwnedWorkflowStartSqlIds = null;
				//WebUtil.getServletContext().setAttribute("userOwnedWorkflowStartSqlIds", userOwnedWorkflowStartSqlIdsStr);
				// 设置流程查看权限
				Map<String, List<Map<String, String>>> userOwnedWorkflowViews = new HashMap<String, List<Map<String, String>>>();
				Map<String, List<String>> userOwnedWorkflowViewSqlIds = new HashMap<String, List<String>>();
				for (String username : userWorkflowViews.keySet())
				{
					List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

					Set<JbpmWorkflowDesigner> datas = userWorkflowViews.get(username);
					for (JbpmWorkflowDesigner node : datas) {
						List<String> ownedWorkflowViewSqlIds = userOwnedWorkflowViewSqlIds.get(username);
						if (null == ownedWorkflowViewSqlIds) {
							ownedWorkflowViewSqlIds = new ArrayList<String>();
							userOwnedWorkflowViewSqlIds.put(username, ownedWorkflowViewSqlIds);
						}
						String workflowId = (null == node.getDeploymentImpl()) ? null : (node.getDeploymentImpl().getDbid() + "");

						if ((null != workflowId) && !ownedWorkflowViewSqlIds.contains(workflowId)) {
							ownedWorkflowViewSqlIds.add("'" + workflowId + "'");
						}

						Map<String, String> dataMap = EntityUtil.getEntityPropertiesToStringMap(node, null);
						dataList.add(dataMap);
					}
					userOwnedWorkflowViews.put(username, dataList);
				}
				//WebUtil.setUserOwnedWorkflowViews(userOwnedWorkflowViews);
				Map<String, String> userOwnedWorkflowViewSqlIdsStr = new HashMap<String, String>();
				for (String username : userOwnedWorkflowViewSqlIds.keySet()) {
					String sqlIdsStr = StringUtil.join(userOwnedWorkflowViewSqlIds.get(username), ",");
					userOwnedWorkflowViewSqlIdsStr.put(username, sqlIdsStr);
				}
				userOwnedWorkflowViewSqlIds = null;
				//WebUtil.getServletContext().setAttribute("userOwnedWorkflowViewSqlIds", userOwnedWorkflowViewSqlIdsStr);

				log.info("用户菜单缓存完毕,共用时:" + (System.currentTimeMillis() - startMilis) / 1000 + " 秒 ");
				Menu rootMenu = (Menu) session.get(Menu.class, "0");
				Map<String, Map<String, String>> userOwnedMenusJson = null;
				
				try {
					userOwnedMenusJson = getAllUserMenusJsonString(userMenus, rootMenu,quickUserMenusMap );
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
	  			Map<String,Map<String,String>> allXMLTable   = new HashMap<String,Map<String,String>>();
    			Map<String, Map<String, String>> allXMLChart = new HashMap<String,Map<String,String>>();
				try {
					allXMLTable = XMLDataUtil.getAllXMLTable();
					allXMLChart = XMLDataUtil.getAllXMLChart();
					baseService.saveOrUpdatePermissionCachedToDB(allXMLTable, allXMLChart, userOwnedMenusJson, userOwnedMenus, userOwnedResources, userOwnedActions, userOwnedWorkflowStarts, userOwnedWorkflowViews, resourceOwnedUsers, userOwnedWorkflowStartSqlIdsStr, userOwnedWorkflowViewSqlIdsStr);
				} catch (Exception e) {
					e.printStackTrace();
				}
				ResourceUtil.setNeedUpdatePermissionCache(false);
				return null;
			}
		});
	}

	// 获取用户的菜单
	public static String getUserMenusJsonString(Locale locale, String username) throws Exception
	{
		Map<String, String> allUserTreeMenus = WebUtil.getUserOwnedMenusJson().get(locale.getLanguage()+"_"+locale.getCountry());
		return allUserTreeMenus.get(username);
	}

	// 通过用户获取resource
	private static void getUserResource(User user, Map<String, Set<Resource>> userResources) {
		final String username = user.getUsername();
		Set<UserResource> currentUserResources = user.getUserResources();
		for (UserResource userResource : currentUserResources) {
			Resource resource = userResource.getResource();
			resourceCallBack(user, resource, "UserResource");
			Set<Resource> currentResources = userResources.get(username);
			if (null == currentResources) {
				currentResources = new HashSet<Resource>();
				userResources.put(username, currentResources);
			}
			currentResources.add(resource);
		}
	}

	// 通过部门获取resource
	private static void getUserResourceByDept(User user, Map<String, Set<Resource>> userResources) {
		final String username = user.getUsername();
		Set<UserDepartment> userDepts = user.getUserDepts();
		for (UserDepartment userDept : userDepts) {
			Department dept = userDept.getDept();
			Set<ResourceDepartment> resourceDepts = dept.getResourceDepts();
			for (ResourceDepartment resourceDept : resourceDepts) {
				Resource resource = resourceDept.getResource();
				resourceCallBack(user, resource, "ResourceDepartment");
				Set<Resource> currentResources = userResources.get(username);
				if (null == currentResources) {
					currentResources = new HashSet<Resource>();
					userResources.put(username, currentResources);
				}
				currentResources.add(resource);
			}
		}
	}

	// 通过部门角色获取resource
	private static void getUserResourceByDeptRole(User user, Map<String, Set<Resource>> userResources) {
		final String username = user.getUsername();
		Set<UserDepartmentRole> userDeptRoles = user.getUserDeptRoles();
		for (UserDepartmentRole userDeptRole : userDeptRoles) {
			DepartmentRole deptRole = userDeptRole.getDeptRole();
			Set<ResourceDepartmentRole> resourceDeptRoles = deptRole.getResourceDeptRoles();
			for (ResourceDepartmentRole resourceDeptRole : resourceDeptRoles) {
				Resource resource = resourceDeptRole.getResource();
				resourceCallBack(user, resource, "ResourceDepartmentRole");
				Set<Resource> currentResources = userResources.get(username);
				if (null == currentResources) {
					currentResources = new HashSet<Resource>();
					userResources.put(username, currentResources);
				}
				currentResources.add(resource);
			}
		}
	}

	// 通过群组获取resource
	private static void getUserResourceByGroup(User user, Map<String, Set<Resource>> userResources) {
		final String username = user.getUsername();
		Set<UserGroup> userGroups = user.getUserGroups();
		for (UserGroup userGroup : userGroups) {
			Group dept = userGroup.getGroup();
			Set<ResourceGroup> resourceGroups = dept.getResourceGroups();
			for (ResourceGroup resourceGroup : resourceGroups) {
				Resource resource = resourceGroup.getResource();
				resourceCallBack(user, resource, "ResourceGroup");
				Set<Resource> currentResources = userResources.get(username);
				if (null == currentResources) {
					currentResources = new HashSet<Resource>();
					userResources.put(username, currentResources);
				}
				currentResources.add(resource);
			}
		}
	}

	// 获取指定用户拥有的所有resource资源
	private static void getUserResources(User user, Map<String, Set<Resource>> userResources) {

		getUserResource(user, userResources);
		getUserResourceByDept(user, userResources);
		getUserResourceByDeptRole(user, userResources);
		getUserResourceByGroup(user, userResources);
	}

	// 通过用户获取action
	private static void getUserAction(User user, Map<String, Set<Action>> userActions) {
		final String username = user.getUsername();
		Set<UserAction> currentUserActions = user.getUserActions();
		for (UserAction userAction : currentUserActions) {
			Action action = userAction.getAction();
			actionCallBack(user, action, "UserAction");
			Set<Action> currentActions = userActions.get(username);
			if (null == currentActions) {
				currentActions = new HashSet<Action>();
				userActions.put(username, currentActions);
			}
			currentActions.add(action);
		}
	}

	// 通过部门获取action
	private static void getUserActionByDept(User user, Map<String, Set<Action>> userActions) {
		final String username = user.getUsername();
		Set<UserDepartment> userDepts = user.getUserDepts();
		for (UserDepartment userDept : userDepts) {
			Department dept = userDept.getDept();
			Set<ActionDepartment> actionDepts = dept.getActionDepts();
			for (ActionDepartment actionDept : actionDepts) {
				Action action = actionDept.getAction();
				actionCallBack(user, action, "ActionDepartment");
				Set<Action> currentActions = userActions.get(username);
				if (null == currentActions) {
					currentActions = new HashSet<Action>();
					userActions.put(username, currentActions);
				}
				currentActions.add(action);
			}
		}
	}

	// 通过部门角色获取action
	private static void getUserActionByDeptRole(User user, Map<String, Set<Action>> userActions) {
		final String username = user.getUsername();
		Set<UserDepartmentRole> userDeptRoles = user.getUserDeptRoles();
		for (UserDepartmentRole userDeptRole : userDeptRoles) {
			DepartmentRole deptRole = userDeptRole.getDeptRole();
			Set<ActionDepartmentRole> actionDeptRoles = deptRole.getActionDeptRoles();
			for (ActionDepartmentRole actionDeptRole : actionDeptRoles) {
				Action action = actionDeptRole.getAction();
				actionCallBack(user, action, "ActionDepartmentRole");
				Set<Action> currentActions = userActions.get(username);
				if (null == currentActions) {
					currentActions = new HashSet<Action>();
					userActions.put(username, currentActions);
				}
				currentActions.add(action);
			}
		}
	}

	// 通过群组获取action
	private static void getUserActionByGroup(User user, Map<String, Set<Action>> userActions) {
		final String username = user.getUsername();
		Set<UserGroup> userGroups = user.getUserGroups();
		for (UserGroup userGroup : userGroups) {
			Group dept = userGroup.getGroup();
			Set<ActionGroup> actionGroups = dept.getActionGroups();
			for (ActionGroup actionGroup : actionGroups) {
				Action action = actionGroup.getAction();
				actionCallBack(user, action, "ActionGroup");
				Set<Action> currentActions = userActions.get(username);
				if (null == currentActions) {
					currentActions = new HashSet<Action>();
					userActions.put(username, currentActions);
				}
				currentActions.add(action);
			}
		}
	}

	// 获取指定用户拥有的所有操作(action)
	private static void getUserActions(User user, Map<String, Set<Action>> userActions) {

		getUserAction(user, userActions);
		getUserActionByDept(user, userActions);
		getUserActionByDeptRole(user, userActions);
		getUserActionByGroup(user, userActions);
	}


	// 通过用户获取workflowStart
	private static void getUserWorkflowStart(User user, Map<String, Set<JbpmWorkflowDesigner>> userWorkflowStarts) {
		final String username = user.getUsername();
		Set<UserWorkflowStart> currentUserWorkflowStarts = user.getUserWorkflowStarts();
		for (UserWorkflowStart userWorkflowStart : currentUserWorkflowStarts) {
			JbpmWorkflowDesigner workflowStart = userWorkflowStart.getWorkflowStart();
			workflowStartCallBack(user, workflowStart, "UserWorkflowStart");
			Set<JbpmWorkflowDesigner> currentWorkflowStarts = userWorkflowStarts.get(username);
			if (null == currentWorkflowStarts) {
				currentWorkflowStarts = new HashSet<JbpmWorkflowDesigner>();
				userWorkflowStarts.put(username, currentWorkflowStarts);
			}
			currentWorkflowStarts.add(workflowStart);
		}
	}

	// 通过部门获取workflowStart
	private static void getUserWorkflowStartByDept(User user, Map<String, Set<JbpmWorkflowDesigner>> userWorkflowStarts) {
		final String username = user.getUsername();
		Set<UserDepartment> userDepts = user.getUserDepts();
		for (UserDepartment userDept : userDepts) {
			Department dept = userDept.getDept();
			Set<WorkflowStartDepartment> workflowStartDepts = dept.getWorkflowStartDepts();
			for (WorkflowStartDepartment workflowStartDept : workflowStartDepts) {
				JbpmWorkflowDesigner workflowStart = workflowStartDept.getWorkflowStart();
				workflowStartCallBack(user, workflowStart, "WorkflowStartDepartment");
				Set<JbpmWorkflowDesigner> currentWorkflowStarts = userWorkflowStarts.get(username);
				if (null == currentWorkflowStarts) {
					currentWorkflowStarts = new HashSet<JbpmWorkflowDesigner>();
					userWorkflowStarts.put(username, currentWorkflowStarts);
				}
				currentWorkflowStarts.add(workflowStart);
			}
		}
	}

	// 通过部门角色获取workflowStart
	private static void getUserWorkflowStartByDeptRole(User user, Map<String, Set<JbpmWorkflowDesigner>> userWorkflowStarts) {
		final String username = user.getUsername();
		Set<UserDepartmentRole> userDeptRoles = user.getUserDeptRoles();
		for (UserDepartmentRole userDeptRole : userDeptRoles) {
			DepartmentRole deptRole = userDeptRole.getDeptRole();
			Set<WorkflowStartDepartmentRole> workflowStartDeptRoles = deptRole.getWorkflowStartDeptRoles();
			for (WorkflowStartDepartmentRole workflowStartDeptRole : workflowStartDeptRoles) {
				JbpmWorkflowDesigner workflowStart = workflowStartDeptRole.getWorkflowStart();
				workflowStartCallBack(user, workflowStart, "WorkflowStartDepartmentRole");
				Set<JbpmWorkflowDesigner> currentWorkflowStarts = userWorkflowStarts.get(username);
				if (null == currentWorkflowStarts) {
					currentWorkflowStarts = new HashSet<JbpmWorkflowDesigner>();
					userWorkflowStarts.put(username, currentWorkflowStarts);
				}
				currentWorkflowStarts.add(workflowStart);
			}
		}
	}

	// 通过群组获取workflowStart
	private static void getUserWorkflowStartByGroup(User user, Map<String, Set<JbpmWorkflowDesigner>> userWorkflowStarts) {
		final String username = user.getUsername();
		Set<UserGroup> userGroups = user.getUserGroups();
		for (UserGroup userGroup : userGroups) {
			Group dept = userGroup.getGroup();
			Set<WorkflowStartGroup> workflowStartGroups = dept.getWorkflowStartGroups();
			for (WorkflowStartGroup workflowStartGroup : workflowStartGroups) {
				JbpmWorkflowDesigner workflowStart = workflowStartGroup.getWorkflowStart();
				workflowStartCallBack(user, workflowStart, "WorkflowStartGroup");
				Set<JbpmWorkflowDesigner> currentWorkflowStarts = userWorkflowStarts.get(username);
				if (null == currentWorkflowStarts) {
					currentWorkflowStarts = new HashSet<JbpmWorkflowDesigner>();
					userWorkflowStarts.put(username, currentWorkflowStarts);
				}
				currentWorkflowStarts.add(workflowStart);
			}
		}
	}

	// 获取指定用户拥有的所有操作(workflowStart)
	private static void getUserWorkflowStarts(User user, Map<String, Set<JbpmWorkflowDesigner>> userWorkflowStarts) {

		getUserWorkflowStart(user, userWorkflowStarts);
		getUserWorkflowStartByDept(user, userWorkflowStarts);
		getUserWorkflowStartByDeptRole(user, userWorkflowStarts);
		getUserWorkflowStartByGroup(user, userWorkflowStarts);
	}

	// 通过用户获取workflowView
	private static void getUserWorkflowView(User user, Map<String, Set<JbpmWorkflowDesigner>> userWorkflowViews) {
		final String username = user.getUsername();
		Set<UserWorkflowView> currentUserWorkflowViews = user.getUserWorkflowViews();
		for (UserWorkflowView userWorkflowView : currentUserWorkflowViews) {
			JbpmWorkflowDesigner workflowView = userWorkflowView.getWorkflowView();
			workflowViewCallBack(user, workflowView, "UserWorkflowView");
			Set<JbpmWorkflowDesigner> currentWorkflowViews = userWorkflowViews.get(username);
			if (null == currentWorkflowViews) {
				currentWorkflowViews = new HashSet<JbpmWorkflowDesigner>();
				userWorkflowViews.put(username, currentWorkflowViews);
			}
			currentWorkflowViews.add(workflowView);
		}
	}

	// 通过部门获取workflowView
	private static void getUserWorkflowViewByDept(User user, Map<String, Set<JbpmWorkflowDesigner>> userWorkflowViews) {
		final String username = user.getUsername();
		Set<UserDepartment> userDepts = user.getUserDepts();
		for (UserDepartment userDept : userDepts) {
			Department dept = userDept.getDept();
			Set<WorkflowViewDepartment> workflowViewDepts = dept.getWorkflowViewDepts();
			for (WorkflowViewDepartment workflowViewDept : workflowViewDepts) {
				JbpmWorkflowDesigner workflowView = workflowViewDept.getWorkflowView();
				workflowViewCallBack(user, workflowView, "WorkflowViewDepartment");
				Set<JbpmWorkflowDesigner> currentWorkflowViews = userWorkflowViews.get(username);
				if (null == currentWorkflowViews) {
					currentWorkflowViews = new HashSet<JbpmWorkflowDesigner>();
					userWorkflowViews.put(username, currentWorkflowViews);
				}
				currentWorkflowViews.add(workflowView);
			}
		}
	}

	// 通过部门角色获取workflowView
	private static void getUserWorkflowViewByDeptRole(User user, Map<String, Set<JbpmWorkflowDesigner>> userWorkflowViews) {
		final String username = user.getUsername();
		Set<UserDepartmentRole> userDeptRoles = user.getUserDeptRoles();
		for (UserDepartmentRole userDeptRole : userDeptRoles) {
			DepartmentRole deptRole = userDeptRole.getDeptRole();
			Set<WorkflowViewDepartmentRole> workflowViewDeptRoles = deptRole.getWorkflowViewDeptRoles();
			for (WorkflowViewDepartmentRole workflowViewDeptRole : workflowViewDeptRoles) {
				JbpmWorkflowDesigner workflowView = workflowViewDeptRole.getWorkflowView();
				workflowViewCallBack(user, workflowView, "WorkflowViewDepartmentRole");
				Set<JbpmWorkflowDesigner> currentWorkflowViews = userWorkflowViews.get(username);
				if (null == currentWorkflowViews) {
					currentWorkflowViews = new HashSet<JbpmWorkflowDesigner>();
					userWorkflowViews.put(username, currentWorkflowViews);
				}
				currentWorkflowViews.add(workflowView);
			}
		}
	}

	// 通过群组获取workflowView
	private static void getUserWorkflowViewByGroup(User user, Map<String, Set<JbpmWorkflowDesigner>> userWorkflowViews) {
		final String username = user.getUsername();
		Set<UserGroup> userGroups = user.getUserGroups();
		for (UserGroup userGroup : userGroups) {
			Group dept = userGroup.getGroup();
			Set<WorkflowViewGroup> workflowViewGroups = dept.getWorkflowViewGroups();
			for (WorkflowViewGroup workflowViewGroup : workflowViewGroups) {
				JbpmWorkflowDesigner workflowView = workflowViewGroup.getWorkflowView();
				workflowViewCallBack(user, workflowView, "WorkflowViewGroup");
				Set<JbpmWorkflowDesigner> currentWorkflowViews = userWorkflowViews.get(username);
				if (null == currentWorkflowViews) {
					currentWorkflowViews = new HashSet<JbpmWorkflowDesigner>();
					userWorkflowViews.put(username, currentWorkflowViews);
				}
				currentWorkflowViews.add(workflowView);
			}
		}
	}

	// 获取指定用户拥有的所有操作(workflowView)
	private static void getUserWorkflowViews(User user, Map<String, Set<JbpmWorkflowDesigner>> userWorkflowViews) {

		getUserWorkflowView(user, userWorkflowViews);
		getUserWorkflowViewByDept(user, userWorkflowViews);
		getUserWorkflowViewByDeptRole(user, userWorkflowViews);
		getUserWorkflowViewByGroup(user, userWorkflowViews);
	}
}
