
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.model
 * 文件名：         ActivityTaskUsersComparator.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-11-16-上午10:00:50
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.jbpm.model;

import java.util.Comparator;


 /**
 * 类名称：     ActivityTaskUsersComparator
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-11-16 上午10:00:50
 * 修改备注：
 * @version 1.0.0
 **/

public class ActivityTaskUsersComparator implements
		Comparator<ActivityTaskUsers> {

	/**
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 **/

	@Override
	public int compare(ActivityTaskUsers o1, ActivityTaskUsers o2) 
	{
		String o1_activityName = o1.getCurrentTaskUsers().getActivityDetail().getActivityName();
		String o2_activityName = o2.getCurrentTaskUsers().getActivityDetail().getActivityName();
	    return o1_activityName.compareTo(o2_activityName);
	}

}
