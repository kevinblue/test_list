
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.kernal.enums
 * 文件名：         PermissionEnum.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-8-31-下午01:15:32
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.kernal.enums;



 /**
 * 类名称：     PermissionEnum
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-8-31 下午01:15:32
 * 修改备注：
 * @version 1.0.0
 **/

public enum PermissionEnum {
	AllXMLTable("AllXMLTable","缓存tablexml文件","缓存tablexml文件"),
	AllXMLChart("AllXMLChart","缓存chartxml文件","缓存chartxml文件"),
	UserOwnedMenusJson("UserOwnedMenusJson","用户具有的权限菜单","用户具有的权限菜单"),
	UserOwnedResources("UserOwnedResources","用户具有的权限资源控制","用户具有的权限资源控制"),
	ResourceOwnedUsers("ResourceOwnedUsers","资源的权限用户","资源的权限用户"),
	UserOwnedActions("UserOwnedActions","用户具有的权限操作控制","用户具有的权限操作控制"),
	UserOwnedWorkflowStarts("UserOwnedWorkflowStarts","用户具有的权限发起流程","用户具有的权限发起流程"),
	UserOwnedWorkflowStartSqlIdsStr("UserOwnedWorkflowStartSqlIdsStr","用户具有的权限发起流程的deployids","用户具有的权限发起流程deployids"),
	UserOwnedWorkflowViews("UserOwnedWorkflowViews","用户具有的权限查看流程","用户具有的权限查看流程"),
	UserOwnedWorkflowViewSqlIdsStr("UserOwnedWorkflowViewSqlIdsStr","用户具有的权限查看流程deployids","用户具有的权限查看流程deployids")
	;
	
    private final String code;
    private final String name;
    private final String desc;
    
    private PermissionEnum(String code, String name, String desc) {
		this.code = code;
		this.name = name;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}
    
}
