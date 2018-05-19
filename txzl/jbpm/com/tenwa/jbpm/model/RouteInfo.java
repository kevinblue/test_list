
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.model
 * 文件名：         JbpmRouteInfo.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-11-1-下午04:13:22
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.jbpm.model;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.api.model.Activity;
import org.jbpm.api.model.Transition;


 /**
 * 类名称：     JbpmRouteInfo
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-11-1 下午04:13:22
 * 修改备注：
 * @version 1.0.0
 **/

public class RouteInfo 
{
   private Transition  currentTransition ;
   private String parentActivityName;
   private List<Activity> targetTaskActivitys = new ArrayList<Activity>();
	
	 /**
	 * @param targetTaskActivitys the targetTaskActivitys to set
	 **/
	
	public void setTargetTaskActivitys(List<Activity> targetTaskActivitys) {
		this.targetTaskActivitys = targetTaskActivitys;
	}

	
	 /**
	 * targetTaskActivitys
	 * @return the targetTaskActivitys
	 * @since 1.0.0
	 **/
	
	public List<Activity> getTargetTaskActivitys() {
		return targetTaskActivitys;
	}

	
	 /**
	 * @param currentTransition the currentTransition to set
	 **/
	
	public void setCurrentTransition(Transition currentTransition) {
		this.currentTransition = currentTransition;
	}


	
	 /**
	 * currentTransition
	 * @return the currentTransition
	 * @since 1.0.0
	 **/
	
	public Transition getCurrentTransition() {
		return currentTransition;
	}


	public String getParentActivityName() {
		return parentActivityName;
	}


	public void setParentActivityName(String parentActivityName) {
		this.parentActivityName = parentActivityName;
	}
	
}
