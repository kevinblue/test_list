/**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.job
 * 文件名：         QuartzJobTest.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-2-21-上午11:00:16
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.tenwa.business.entity.RemindTask;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.annotation.QuartzJob;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.leasing.service.remindTask.RemindTaskService;

/**
 * 类名称： QuartzJobTest 类描述： 创建人： Administrator 修改人： Administrator 修改时间：2013-2-21
 * 上午11:00:16 修改备注：
 * 
 * @version 1.0.0
 **/
@QuartzJob(description = "起租待办提醒")
public class QuartzJobContractOnhire implements Job {

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 **/

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// 使用spring的事物
		RemindTaskService remindTaskService = (RemindTaskService) WebUtil.getApplicationContext().getBean("remindTaskServcie");
		// 使用springAOP事物管理的service做一些事情
		System.out.println(">>>当前时间:" + DateUtil.getSystemDateTime());
		remindTaskService.saveOnhireRemindTask();
	}
}
