package com.tenwa.business.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.entity.DepartmentRole;
import com.tenwa.business.entity.User;
import com.tenwa.business.entity.UserDepartment;
import com.tenwa.business.entity.UserDepartmentRole;
import com.tenwa.business.entity.UserGroup;
import com.tenwa.business.service.BeforeTableParamsLoadService;
import com.tenwa.kernal.utils.WebUtil;

@Service(value="permissionBeforeTableParamsLoadService")
public class PermissionBeforeTableParamsLoadServiceImpl extends AbstractBaseServiceImpl implements BeforeTableParamsLoadService{
    @Resource(name="baseDao")
	private BaseDao baseDao;
	@Override
	public void beforeTableParamsLoad(Map<String, String> beforeParamsMap) throws Exception 
	{
		//用户编号,用户名称,用户菜单权限,用户操作权限,用户部门,用户角色,用户群组 
		StringBuffer customSqlStr = new StringBuffer();
		//Locale locale = WebUtil.getSessionLocale();
		//Map<String,String> userOwnedMenusJson = WebUtil.getUserOwnedMenusJson().get(locale);
		Map<String,List<Map<String,String>>> userOwnedLeafMenus = WebUtil.getUserOwnedLeafMenus();
		Map<String,List<Map<String,String>>> userOwnedActions = WebUtil.getUserOwnedActions();
		List<User> users = this.baseDao.findEntities(User.class);
		int rowIndex = 0;
		for(User user : users){
			if(!user.isEnabled())continue;
			String username     = user.getUsername();
			String realName = user.getRealname();
			if(rowIndex++ > 0 ){
				customSqlStr.append(" union all ");
			}
			customSqlStr .append(" select ")
						 .append("'"+username+"' as username,")
			             .append("'"+realName+"' as realname,");
			int colCount = 4;
			
			int index = 0;
			StringBuffer leafMenuNames = new StringBuffer();
			List<Map<String,String>> userOwnedLeafMenusMapList = userOwnedLeafMenus.get(username);
			if(null != userOwnedLeafMenusMapList){
				for(Map<String,String> m : userOwnedLeafMenusMapList){
					String leafMenuName = m.get("name");
					if(( index > 0 )){
						leafMenuNames.append("，");
						if(( 0 == (index % colCount) )){
						   leafMenuNames.append("\r\n");
						}
					}
					leafMenuNames.append(leafMenuName);
					index++;
				}
			}
			customSqlStr.append("'"+leafMenuNames+"' as menus,");
			StringBuffer actionNames = new StringBuffer();
			index = 0;
			List<Map<String,String>> userOwnedActionMapList = userOwnedActions.get(username);
			if(null != userOwnedActionMapList){
				for(Map<String,String> m : userOwnedActionMapList){
					String actionName = m.get("name");
					if(( index > 0 )){
						actionNames.append("，");
						if(( 0 == (index % colCount) )){
						   actionNames.append("\r\n");
						}
					}
					actionNames.append(actionName);
					index++;
				}
			}
			customSqlStr.append("'"+actionNames+"' as actions,");
			StringBuffer deptNames = new StringBuffer();
			index = 0;
			for(UserDepartment userDept : user.getUserDepts()){
				String  deptName = userDept.getDept().getName();
				if(( index > 0 )){
					deptNames.append("，");
					if(( 0 == (index % colCount) )){
						deptNames.append("\r\n");
					}
				}
				deptNames.append(deptName);
				index++;
			}
			customSqlStr.append("'"+deptNames+"' as depts,");
			
			StringBuffer deptRoleNames = new StringBuffer();
			index = 0;
			for(UserDepartmentRole userDeptRole : user.getUserDeptRoles()){
				DepartmentRole deptRole = userDeptRole.getDeptRole();
				String  deptName = deptRole.getDept().getName();
				String  roleName = deptRole.getRole().getName();
				String deptRoleName = roleName+"（"+deptName+"）";
				if(( index > 0 )){
					deptRoleNames.append("，");
					if(( 0 == (index % colCount) )){
						deptRoleNames.append("\r\n");
					}
				}
				deptRoleNames.append(deptRoleName);
				index++;
			}
			customSqlStr.append("'"+deptRoleNames+"' as deptRoles,");
			StringBuffer groupNames = new StringBuffer();
			index = 0;
			for(UserGroup userGroup : user.getUserGroups()){
				String  groupName = userGroup.getGroup().getName();
				if(( index > 0 )){
					groupNames.append("，");
					if(( 0 == (index % colCount) )){
						groupNames.append("\r\n");
					}
				}
				groupNames.append(groupName);
				index++;
			}
			customSqlStr.append("'"+groupNames+"' as groups")
			            .append(" from dual ");
		}
		beforeParamsMap.put("permissionCustomSqlStr", customSqlStr.toString());
	}

	@Override
	public BaseDao getBussinessDao() throws Exception {
		return baseDao;
	}
   
}
