package com.tenwa.business.serviceImpl;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;
import com.tenwa.ad.AdUtil;
import com.tenwa.business.dao.AclDao;
import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.entity.Action;
import com.tenwa.business.entity.Department;
import com.tenwa.business.entity.Dictionary;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.Menu;
import com.tenwa.business.entity.PermissionCache;
import com.tenwa.business.entity.User;
import com.tenwa.business.entity.base.BaseDocumentConfig;
import com.tenwa.business.entity.base.BaseDocumentConfigData;
import com.tenwa.business.entity.base.BaseRole;
import com.tenwa.business.entity.base.BaseRoleBlock;
import com.tenwa.business.service.AclService;
import com.tenwa.kernal.enums.PermissionEnum;
import com.tenwa.kernal.i18n.LocaleEnum;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.LicenseUtil;
import com.tenwa.kernal.utils.MD5Util;
import com.tenwa.kernal.utils.PermissionUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.UUIDUtil;

@Service(value = "aclService")
public class AclServiceImpl extends AbstractBaseServiceImpl implements AclService {

	public AclServiceImpl() {
	}

	@Resource(name = "aclDao")
	private AclDao aclDao;

	@Override
	public void saveOrUpdatePermissionCachedToDB(Map<String, Map<String, String>> allXMLTable, Map<String, Map<String, String>> allXMLChart, Map<String, Map<String, String>> userOwnedMenusJson, Map<String, List<Map<String, String>>> userOwnedMenus, Map<String, List<Map<String, String>>> userOwnedResources, Map<String, List<Map<String, String>>> userOwnedActions,
			Map<String, List<Map<String, String>>> userOwnedWorkflowStarts, Map<String, List<Map<String, String>>> userOwnedWorkflowViews, Map<String, List<String>> resourceOwnedUsers, Map<String, String> userOwnedWorkflowStartSqlIdsStr, Map<String, String> userOwnedWorkflowViewSqlIdsStr) throws Exception {
		String[] permessionKeys = { "AllXMLTable", "AllXMLChart", "UserOwnedMenusJson", "UserOwnedResources", "UserOwnedActions", "UserOwnedWorkflowStarts", "UserOwnedWorkflowViews", "ResourceOwnedUsers", "UserOwnedWorkflowStartSqlIdsStr", "UserOwnedWorkflowViewSqlIdsStr" };
		Object[] permessionObjs = { allXMLTable, allXMLChart, userOwnedMenusJson, userOwnedResources, userOwnedActions, userOwnedWorkflowStarts, userOwnedWorkflowViews, resourceOwnedUsers, userOwnedWorkflowStartSqlIdsStr, userOwnedWorkflowViewSqlIdsStr };
		ObjectMapper mapper = new ObjectMapper();
		Class<PermissionCache> clazz = PermissionCache.class;
		// //更新
		for (int i = 0; i < permessionKeys.length; i++) {
			String key = PermissionEnum.valueOf(permessionKeys[i]).getCode();
			String permissionCachedJson = mapper.writeValueAsString(permessionObjs[i]);
			String type = key;

			HashMap<String, Object> propertiesMap = new HashMap<String, Object>();
			propertiesMap.put("key", key);
			PermissionCache cachedEntity = (PermissionCache) this.aclDao.getNewOrUpdateObject(clazz, propertiesMap);
			HashMap<String, String> saveOrUpdateMap = new HashMap<String, String>();
			saveOrUpdateMap.put("key", key);
			saveOrUpdateMap.put("permissionCachedJson", permissionCachedJson);
			saveOrUpdateMap.put("type", type);
			this.aclDao.copyAndOverrideExistedValueFromStringMap(saveOrUpdateMap, cachedEntity, null);
			this.aclDao.saveOrUpdateEntity(cachedEntity);
		}
		// WebUtil.getServletContext().setAttribute("userOwnedWorkflowStartSqlIds",
		// userOwnedWorkflowStartSqlIdsStr);
		// WebUtil.getServletContext().setAttribute("userOwnedWorkflowViewSqlIds",
		// userOwnedWorkflowViewSqlIdsStr);
	}

	@Override
	public User findUserByUserName(String username) throws Exception {
		return this.aclDao.findUserByUserName(username);
	}

	@Override
	public String getMenusTreeMenu(Locale locale) throws Exception {
		String username = ((User) SecurityUtil.getPrincipal()).getUsername();
		return PermissionUtil.getUserMenusJsonString(locale, username);
	}

	@Override
	// @TriggersRemove(cacheName = "menusTreeMenuCache", when =
	// When.AFTER_METHOD_INVOCATION, removeAll = true)
	public void saveOrUpdateMenuWithPosition(Menu menu, String parentMenuId, String currentPosition) throws Exception {
		this.aclDao.saveOrUpdateEntity(menu);
		this.aclDao.updateFlush();
		String tablename = "t_menus";
		String currentId = menu.getId();
		this.aclDao.updateOrderPosition(tablename, currentId, parentMenuId, currentPosition);
		ResourceUtil.setNeedUpdatePermissionCache(true);
	}

	@Override
	// @TriggersRemove(cacheName = "menusTreeMenuCache", when =
	// When.AFTER_METHOD_INVOCATION, removeAll = true)
	public void removeMenu(String menuId) throws Exception {
		// this.aclDao.removeEntityById(Menu.class, menuId);
		this.aclDao.updateBySql("delete from t_users_menus where menu_id_ = ?", menuId);
		this.aclDao.updateBySql("delete from t_menus_depts where menu_id_ = ?", menuId);
		this.aclDao.updateBySql("delete from t_menus_deptroles where menu_id_ = ?", menuId);
		this.aclDao.updateBySql("delete from t_menus_groups where menu_id_ = ?", menuId);
		this.aclDao.updateBySql("delete from t_menus        where id_ = ?", menuId);
		ResourceUtil.setNeedUpdatePermissionCache(true);
	}

	// 资源
	@Override
	public void saveOrUpdateResourceWithPosition(com.tenwa.business.entity.Resource resource, String parentResourceId, String currentPosition) throws Exception {
		this.aclDao.saveOrUpdateEntity(resource);
		this.aclDao.updateFlush();
		String tablename = "t_resources";
		String currentId = resource.getId();
		this.aclDao.updateOrderPosition(tablename, currentId, parentResourceId, currentPosition);
		ResourceUtil.setNeedUpdatePermissionCache(true);
	}

	@Override
	public void removeResource(String resourceId) throws Exception {
		this.aclDao.removeEntityById(com.tenwa.business.entity.Resource.class, resourceId);
		ResourceUtil.setNeedUpdatePermissionCache(true);
	}

	// 操作action
	@Override
	public void saveOrUpdateActionWithPosition(Action action, String parentActionId, String currentPosition) throws Exception {
		this.aclDao.saveOrUpdateEntity(action);
		this.aclDao.updateFlush();
		String tablename = "t_actions";
		String currentId = action.getId();
		this.aclDao.updateOrderPosition(tablename, currentId, parentActionId, currentPosition);
		ResourceUtil.setNeedUpdatePermissionCache(true);
	}

	@Override
	public void removeAction(String actionId) throws Exception {
		this.aclDao.removeEntityById(Action.class, actionId);
		ResourceUtil.setNeedUpdatePermissionCache(true);
	}

	@Cacheable(cacheName = "dictionariesTreeMenuCache")
	public String getDictionariesTreeMenu(String rootParentDictId) throws Exception {
		return this.findEntityByID(Dictionary.class, rootParentDictId).toString();
	}

	@Override
	@TriggersRemove(cacheName = "dictionariesTreeMenuCache", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
	public void removeDictionary(String dictId) throws Exception {
		this.aclDao.removeEntityById(Dictionary.class, dictId);
	}

	@Override
	@TriggersRemove(cacheName = "dictionariesTreeMenuCache", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
	public void saveOrUpdateDictionaryWithPosition(Dictionary dictionary, String parentDictId, String currentPosition) throws Exception {
		this.aclDao.saveOrUpdateEntity(dictionary);
		this.aclDao.updateFlush();
		String tablename = "t_dicts";
		String currentId = dictionary.getId();
		this.aclDao.updateOrderPosition(tablename, currentId, parentDictId, currentPosition);
		this.aclDao.updateFlush();
		if (!"0".equals(currentId)) {
			String sql = " update t_dicts set id_ = code_ where id_= ? ";
			this.getBussinessDao().getJdbcTemplate().update(sql, currentId);
		}
	}

	@Override
	@Cacheable(cacheName = "baseDocumentConfigsTreeMenuCache")
	public String getBaseDocumentConfigsTreeMenu(String rootParentBaseDocumentConfig) throws Exception {
		return this.findEntityByID(BaseDocumentConfig.class, rootParentBaseDocumentConfig).toString();
	}

	@Override
	@TriggersRemove(cacheName = "baseDocumentConfigsTreeMenuCache", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
	public void removeBaseDocumentConfig(String baseDocumentConfigId) throws Exception {
		this.aclDao.removeEntityById(BaseDocumentConfig.class, baseDocumentConfigId);
	}

	@Override
	@TriggersRemove(cacheName = "baseDocumentConfigsTreeMenuCache", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
	public void saveOrUpdateBaseDocumentConfigWithPosition(BaseDocumentConfig baseDocumentConfig, String parentDocumentId, String currentPosition) throws Exception {
		this.aclDao.saveOrUpdateEntity(baseDocumentConfig);
		this.aclDao.updateFlush();
		String tablename = "BASE_DOCUMENT_CONFIG";
		String currentId = baseDocumentConfig.getId();
		this.aclDao.updateOrderPosition(tablename, currentId, parentDocumentId, currentPosition);
		this.aclDao.updateFlush();
		if (!"0".equals(currentId)) {
			String sql = " update BASE_DOCUMENT_CONFIG set id_ = code_ where id_= ? ";
			this.getBussinessDao().getJdbcTemplate().update(sql, currentId);
		}
	}
	
	@Override
	public void saveOrUpdateBaseDocumentConfigDataWithPosition(BaseDocumentConfigData dictionaryData, String parentDictDataId, String currentPosition) throws Exception {
		this.aclDao.saveOrUpdateEntity(dictionaryData);
		this.aclDao.updateFlush();
		String tablename = "BASE_DOCUMENT_COLUMN_CONFIG";
		String currentId = dictionaryData.getId();
		this.aclDao.updateOrderPosition(tablename, currentId, parentDictDataId, currentPosition);
		this.aclDao.updateFlush();
		String sql = " update BASE_DOCUMENT_COLUMN_CONFIG set id_ = code_ where id_= ? ";
		this.getBussinessDao().getJdbcTemplate().update(sql, currentId);
	}

	@Override
	public void saveOrUpdateDictionaryDataWithPosition(DictionaryData dictionaryData, String parentDictDataId, String currentPosition) throws Exception {
		this.aclDao.saveOrUpdateEntity(dictionaryData);
		this.aclDao.updateFlush();
		String tablename = "t_dicts_datas";
		String currentId = dictionaryData.getId();
		this.aclDao.updateOrderPosition(tablename, currentId, parentDictDataId, currentPosition);
		this.aclDao.updateFlush();
		String sql = " update t_dicts_datas set id_ = code_ where id_= ? ";
		this.getBussinessDao().getJdbcTemplate().update(sql, currentId);
	}
	
	@Override
	@TriggersRemove(cacheName = "baseRolesTreeMenuCache", when = When.AFTER_METHOD_INVOCATION, removeAll = true)
	public void saveOrUpdateBaseRoleWithPosition(BaseRole baseRole, String pid, String currentPosition) throws Exception {
		this.aclDao.saveOrUpdateEntity(baseRole);
		this.aclDao.updateFlush();
		String tablename = "BASE_ROLE";
		String currentId = baseRole.getId();
		this.aclDao.updateOrderPosition(tablename, currentId, pid, currentPosition);
		this.aclDao.updateFlush();
		if (!"0".equals(currentId)) {
			String sql = " update BASE_ROLE set id_ = code_ where id_= ? ";
			this.getBussinessDao().getJdbcTemplate().update(sql, currentId);
		}
	}
	@Override
	public void saveOrUpdateBaseRoleBlockWithPosition(
			BaseRoleBlock baseRoleBlock, String pid, String currentPosition)
			throws Exception {
		this.aclDao.saveOrUpdateEntity(baseRoleBlock);
		this.aclDao.updateFlush();
		String tablename = "BASE_ROLE_BLOCK";
		String currentId = baseRoleBlock.getId();
		this.aclDao.updateOrderPosition(tablename, currentId, pid, currentPosition);
		this.aclDao.updateFlush();
	}

	@Override
	// @TriggersRemove(cacheName = "deptsTreeMenuCache", when =
	// When.AFTER_METHOD_INVOCATION, removeAll = true)
	public void removeDept(String deptId) throws Exception {
		this.aclDao.removeEntityById(Department.class, deptId);
	}

	@Override
	// @TriggersRemove(cacheName = "deptsTreeMenuCache", when =
	// When.AFTER_METHOD_INVOCATION, removeAll = true)
	public void saveOrUpdateDeptWithPosition(Department dept, String parentDeptId, String currentPosition) throws Exception {
		this.aclDao.saveOrUpdateEntity(dept);
		this.aclDao.updateFlush();
		String tablename = "t_depts";
		String currentId = dept.getId();
		this.aclDao.updateOrderPosition(tablename, currentId, parentDeptId, currentPosition);
	}

	@Override
	public String getCurrentUserIndexPageUrl() throws Exception {
		return this.aclDao.getCurrentUserIndexPageUrl();
	}

	@Override
	public String modifyUserPassword(String currentUserId, String oldPassword, String newPassword, String telephone, String email) throws Exception {
		User user = (User) this.findEntityByID(User.class, currentUserId);
		String currentOldPassword = user.getPassword();
		if (!currentOldPassword.equals(MD5Util.getMD5EncodedPasswordWithSalt(oldPassword, user.getId()))) {
			return "{status:'failure',message:'原始密码输入不正确，请重新输入'}";
		}
		String lastUpdatePasswordDate = DateUtil.getSystemDateTime();
		user.setLastUpdatePasswordDate(lastUpdatePasswordDate);
		// 更新当前session中的用户信息
		user.setPassword((MD5Util.getMD5EncodedPasswordWithSalt(newPassword, user.getId())));
		user.setTelephone(telephone);
		user.setEmail(email);
		user.setSourcepassword(newPassword);
		this.updateEntity(user);
		return "{status:'success',message:'用户信息修改成功'}";
	}

	@Override
	public BaseDao getBussinessDao() throws Exception {
		return this.aclDao;
	}

	@Override
	public void addOrRemoveDistribute(String entityClassName, String leftFieldName, String leftId, String rightFieldName, String rightIds, Map<String, String> model) throws Exception {
		Assert.notNull(leftId);
		Assert.notNull(rightIds);
		boolean isNeedRecursionChildrenDepts = "MenuDepartment".equals(entityClassName) || "ResourceDepartment".equals(entityClassName) || "ActionDepartment".equals(entityClassName);
		String flag = "add";
		for (String rightId : rightIds.split(",")) {
			String hsql = "from {0} where {1}.id = ? and {2}.id = ? ";
			hsql = MessageFormat.format(hsql, entityClassName, leftFieldName, rightFieldName);
			List<?> l = this.aclDao.findResultsByHSQL(hsql, leftId, rightId);
			if (l.size() > 0) {
				Object oldInstance = l.get(0);
				this.aclDao.removeEntity(oldInstance);
				if ("menu".equalsIgnoreCase(leftFieldName)) {
					Menu menu = (Menu) this.aclDao.findEntityByID(Menu.class, leftId);
					this.removeRecursionDistributeChildrenMenus(menu, entityClassName, rightFieldName, rightIds);
				}
				flag = "remove";
			} else {
				String className = "classpath:com/tenwa/*/entity/" + entityClassName + ".class";
				ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
				org.springframework.core.io.Resource[] rcs = resolver.getResources(className);
				className = rcs[0].getURL().getPath().replace("/", ".");
				int i = className.indexOf("com.tenwa.");
				className = className.substring(i, className.length() - 6);
				
				Object instance = Class.forName(className).newInstance();
				Map<String, String> sourceMapModel = new HashMap<String, String>();
				sourceMapModel.put(leftFieldName, leftId);
				sourceMapModel.put(rightFieldName, rightId);
				model.putAll(sourceMapModel);
				this.aclDao.copyAndOverrideExistedValueFromStringMap(model, instance, null);
				this.aclDao.saveEntity(instance);
				if ("menu".equalsIgnoreCase(leftFieldName)) {
					Menu menu = (Menu) this.aclDao.findEntityByID(Menu.class, leftId);
					this.addRecursionDistributeParentMenu(menu, entityClassName, rightFieldName, rightIds);
				}
			}
			if (isNeedRecursionChildrenDepts) {
				Department dept = (Department) this.aclDao.findEntityByID(Department.class, rightId);
				this.addOrRemoveRecursionDistributeChildrenDepts(flag, dept, rightFieldName, entityClassName, leftFieldName, leftId);
			}
		}
		ResourceUtil.setNeedUpdatePermissionCache(true);
	}

	// 迭代部门
	public void addOrRemoveRecursionDistributeChildrenDepts(String flag, Department dept, String deptFieldName, String entityClassName, String distributeFieldName, String distributeFieldId) throws Exception {
		for (Department childDept : dept.getChildrenDepts()) {
			String deptFieldId = childDept.getId();
			String hsql = "from {0} where {1}.id = ? and {2}.id = ? ";
			hsql = MessageFormat.format(hsql, entityClassName, deptFieldName, distributeFieldName);
			List<?> l = this.aclDao.findResultsByHSQL(hsql, deptFieldId, distributeFieldId);
			if (l.size() > 0) {
				Object oldInstance = l.get(0);
				if ("remove".equalsIgnoreCase(flag)) {
					this.aclDao.removeEntity(oldInstance);
					if ("menu".equalsIgnoreCase(distributeFieldName)) {
						Menu menu = (Menu) this.aclDao.findEntityByID(Menu.class, distributeFieldId);
						this.removeRecursionDistributeChildrenMenus(menu, entityClassName, "dept", deptFieldId);
					}
				}
			} else {
				if ("add".equalsIgnoreCase(flag)) {
					String className = "com.tenwa.business.entity." + entityClassName;
					Object instance = Class.forName(className).newInstance();
					Map<String, String> sourceMapModel = new HashMap<String, String>();
					sourceMapModel.put(deptFieldName, deptFieldId);
					sourceMapModel.put(distributeFieldName, distributeFieldId);
					this.aclDao.copyAndOverrideExistedValueFromStringMap(sourceMapModel, instance, null);
					this.aclDao.saveEntity(instance);
					if ("menu".equalsIgnoreCase(distributeFieldName)) {
						Menu menu = (Menu) this.aclDao.findEntityByID(Menu.class, distributeFieldId);
						this.addRecursionDistributeParentMenu(menu, entityClassName, "dept", deptFieldId);
					}
				}
			}
			this.addOrRemoveRecursionDistributeChildrenDepts(flag, childDept, deptFieldName, entityClassName, distributeFieldName, distributeFieldId);
		}
	}

	// 快捷菜单删除
	@Override
	public void addOrRemoveQuickMenuDistribute(String entityClassName, String leftFieldName, String leftId, String rightFieldName, String rightIds, Map<String, String> model) throws Exception {
		Assert.notNull(leftId);
		this.aclDao.updateByHSQL("delete from " + entityClassName + " a where a." + leftFieldName + ".id = ?", leftId);
		if (!StringUtil.nullToString(rightIds).trim().isEmpty()) {
			for (String rightId : StringUtil.nullToString(rightIds).split(",")) {
				String className = "com.tenwa.business.entity." + entityClassName;
				Object instance = Class.forName(className).newInstance();
				Map<String, String> sourceMapModel = new HashMap<String, String>();
				sourceMapModel.put(leftFieldName, leftId);
				sourceMapModel.put(rightFieldName, rightId);
				model.putAll(sourceMapModel);
				this.aclDao.copyAndOverrideExistedValueFromStringMap(model, instance, null);
				this.aclDao.saveEntity(instance);
			}
		}
		PermissionUtil.cachedAllUserTreeMenus(true);
	}

	public void addRecursionDistributeParentMenu(Menu menu, String entityClassName, String rightFieldName, String rightIds) throws Exception {
		Menu parentMenu = menu.getParentMenu();
		if (null == parentMenu)
			return;
		String leftFieldName = "menu";
		Assert.notNull(rightIds);
		for (String rightId : rightIds.split(",")) {
			String leftId = parentMenu.getId();
			String hsql = "from {0} where {1}.id = ? and {2}.id = ? ";
			hsql = MessageFormat.format(hsql, entityClassName, leftFieldName, rightFieldName);
			List<?> l = this.aclDao.findResultsByHSQL(hsql, leftId, rightId);
			if (l.size() == 0) {
				String className = "com.tenwa.business.entity." + entityClassName;
				Object instance = Class.forName(className).newInstance();
				Map<String, String> sourceMapModel = new HashMap<String, String>();
				sourceMapModel.put(leftFieldName, leftId);
				sourceMapModel.put(rightFieldName, rightId);
				this.aclDao.copyAndOverrideExistedValueFromStringMap(sourceMapModel, instance, null);
				this.aclDao.saveEntity(instance);
			}
		}
		addRecursionDistributeParentMenu(parentMenu, entityClassName, rightFieldName, rightIds);
	}

	public void removeRecursionDistributeChildrenMenus(Menu menu, String entityClassName, String rightFieldName, String rightIds) throws Exception {
		Assert.notNull(rightIds);
		String leftFieldName = "menu";
		for (Menu childMenu : menu.getChildrenMenus()) {
			String leftId = childMenu.getId();
			for (String rightId : rightIds.split(",")) {
				String hsql = "from {0} where {1}.id = ? and {2}.id = ? ";
				hsql = MessageFormat.format(hsql, entityClassName, leftFieldName, rightFieldName);
				List<?> l = this.aclDao.findResultsByHSQL(hsql, leftId, rightId);
				if (l.size() > 0) {
					Object oldInstance = l.get(0);
					this.aclDao.removeEntity(oldInstance);
				}
			}
			removeRecursionDistributeChildrenMenus(childMenu, entityClassName, rightFieldName, rightIds);
		}
	}

	@Override
	public String addUser(Map<String, String> model) throws Exception {
		String userId = UUIDUtil.getUUID();
		String username = model.get("username");
		String userCode = model.get("code");
		boolean[] checkUserExists = this.checkUserExists(userId, username, userCode);
		if (checkUserExists[0]) {
			return "登录名重复，请重新输入";
		}
		if (checkUserExists[1]) {
			return "用户编号，请重新输入";
		}
		User user = new User();
		this.copyAndOverrideExistedValueFromStringMap(model, user, null);
		user.setPassword("TEMP_PASSWORD");
		user.setSourcepassword("111111");
		//add by zhanc 2015-03-26 updated language
		if(null != model.get("userlocal") && 0 < model.get("userlocal").length()){
			user.setUserLocal(LocaleEnum.valueOf(model.get("userlocal")));
		}
		this.saveEntity(user);
		String MD5Password = MD5Util.getMD5EncodedPasswordWithSalt("111111", user.getId());
		user.setPassword(MD5Password);
		this.saveOrUpdateEntity(user);
		ResourceUtil.setNeedUpdatePermissionCache(true);
		return "";
	}

	@Override
	public String updateUser(String userId, Map<String, String> model) throws Exception {
		String username = model.get("username");
		String userCode = model.get("code");
		boolean[] checkUserExists = this.checkUserExists(userId, username, userCode);
		if (checkUserExists[0]) {
			return "登录名重复，请重新输入";
		}
		if (checkUserExists[1]) {
			return "用户编号，请重新输入";
		}
		User user = this.findEntityByID(User.class, userId);
		this.copyAndOverrideExistedValueFromStringMap(model, user, null);
		
		if(null != model.get("userlocal") && 0 < model.get("userlocal").length()){
			user.setUserLocal(LocaleEnum.valueOf(model.get("userlocal")));
		}
		this.updateEntity(user);
		ResourceUtil.setNeedUpdatePermissionCache(true);
		return "";
	}

	public boolean[] checkUserExists(String userId, String username, String userCode) throws Exception {
		boolean[] rs = new boolean[] { false, false };
		username = StringUtil.nullToString(username).toUpperCase();
		userCode = StringUtil.nullToString(userCode).toUpperCase();
		String queryUserNameHsql = "select u.id from User u where UPPER(u.username) = ? and u.id != ?";
		List<String> userIdListByUserName = this.aclDao.findResultsByHSQL(queryUserNameHsql, username, userId);
		if (userIdListByUserName.size() > 0) {
			rs[0] = true;
		}
		String queryUserCodeHsql = "select u.id from User u where UPPER(u.code) = ? and u.id != ?";
		List<String> userIdListByUserCode = this.aclDao.findResultsByHSQL(queryUserCodeHsql, userCode, userId);
		if (userIdListByUserCode.size() > 0) {
			rs[1] = true;
		}
		return rs;
	}

	@Override
	public String updateAndCheckLicenseInfo(MultipartFile privateKeyMultipartFile, MultipartFile authorizeInfoMultipartFile, Map<String, String> modelData) throws Exception {
		return LicenseUtil.updateUploadLicenseInfo(privateKeyMultipartFile, authorizeInfoMultipartFile, modelData, this.aclDao.getJdbcTemplate());
	}

	@Override
	public String updateAllPasswordToSixOne() throws Exception {
		for (User user : this.findEntities(User.class)) {
			String lastUpdatePasswordDate = DateUtil.getSystemDateTime();
			user.setLastUpdatePasswordDate(lastUpdatePasswordDate);
			String password = MD5Util.getMD5EncodedPasswordWithSalt("111111", user.getId());
			user.setPassword(password);
			user.setSourcepassword("111111");
			this.updateEntity(user);
		}
		return "将所有用户密码重置成6个1成功！";
	}

	@Override
	public void updateAdSynchronizedUsers() throws Exception {
		List<Map<String, String>> ADInfoList = AdUtil.getADInfoList(null);
		List<User> userList = this.findEntities(User.class);
		boolean tbExistAccount;
		for (Map<String, String> ADInfo : ADInfoList) {
			String adAccountName = ADInfo.get("sAMAccountName");
			tbExistAccount = false;
			User currentFindUser = null;
			for (User tbUser : userList) {
				if (adAccountName.equalsIgnoreCase(tbUser.getUsername())) {
					tbExistAccount = true;
					currentFindUser = tbUser;
					break;
				}
			}
			// "name", "mobile", "department", "sAMAccountName", "mail"
			if (tbExistAccount) {// 账号已存在，ad域和表都有
				currentFindUser.setDeptName(ADInfo.get("department"));
				currentFindUser.setRealname(ADInfo.get("name"));
				currentFindUser.setUsername(ADInfo.get("sAMAccountName"));
				currentFindUser.setCode(ADInfo.get("sAMAccountName"));
				currentFindUser.setEmail(ADInfo.get("mail"));
				currentFindUser.setTelephone(ADInfo.get("mobile"));
				currentFindUser.setEnabled(true);
				this.updateEntity(currentFindUser);
			} else if (!tbExistAccount) {// 账号不存在，ad域有，表没有
				currentFindUser = new User();
				currentFindUser.setDeptName(ADInfo.get("department"));
				currentFindUser.setRealname(ADInfo.get("name"));
				currentFindUser.setUsername(ADInfo.get("sAMAccountName"));
				currentFindUser.setCode(ADInfo.get("sAMAccountName"));
				currentFindUser.setPassword("111111");
				currentFindUser.setEmail(ADInfo.get("mail"));
				currentFindUser.setTelephone(ADInfo.get("mobile"));
				currentFindUser.setEnabled(true);
				this.saveEntity(currentFindUser);
			}
		}
		boolean adExistAccount;
		for (User tbUser : userList) {
			String adAccountName = tbUser.getUsername();
			adExistAccount = false;
			for (Map<String, String> ADInfo : ADInfoList) {
				if (adAccountName.equalsIgnoreCase(ADInfo.get("sAMAccountName"))) {
					adExistAccount = true;
					break;
				}
			}
			// 表里有，但是ad域没有，说明该账户被删掉
			if (!adExistAccount && !tbUser.getIsExcepted()) {
				tbUser.setEnabled(false);
				this.updateEntity(tbUser);
			}
		}
	}
}
