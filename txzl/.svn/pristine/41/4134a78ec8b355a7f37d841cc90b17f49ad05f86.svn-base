
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.model
 * 文件名：         TaskUsers.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-11-1-下午04:18:04
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.jbpm.model;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.api.model.Activity;

import com.tenwa.business.entity.User;
import com.tenwa.jbpm.entity.ActivityDetail;


 /**
 * 类名称：     TaskUsers
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-11-1 下午04:18:04
 * 修改备注：
 * @version 1.0.0
 **/

public class TaskUsers 
{
	  private Activity taskActivity ;
	  private List<User> dealUsers = new ArrayList<User>();
	  private List<User> readDealUsers = new ArrayList<User>();
	  private List<User> signatureUsers = new ArrayList<User>();
	  private ActivityDetail activityDetail ;
	
	public void setTaskActivity(Activity taskActivity) {
		this.taskActivity = taskActivity;
	}
	
	public Activity getTaskActivity() {
		return taskActivity;
	}
	
	public void setDealUsers(List<User> dealUsers) {
		this.dealUsers = dealUsers;
	}
	
	public List<User> getDealUsers() {
		return dealUsers;
	}
	
	public List<User> getReadDealUsers() {
		return readDealUsers;
	}

	public List<User> getSignatureUsers() {
		return signatureUsers;
	}

	public void setReadDealUsers(List<User> readDealUsers) {
		this.readDealUsers = readDealUsers;
	}

	public void setSignatureUsers(List<User> signatureUsers) {
		this.signatureUsers = signatureUsers;
	}
	public void setActivityDetail(ActivityDetail activityDetail) {
		this.activityDetail = activityDetail;
	}
	
	public ActivityDetail getActivityDetail() {
		return activityDetail;
	}
}
