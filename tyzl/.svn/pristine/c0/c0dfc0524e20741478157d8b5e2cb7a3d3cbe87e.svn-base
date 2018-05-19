
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.job
 * 文件名：         QuartzJobTest.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-2-21-上午11:00:16
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.tenwa.business.service.QuartzJobService;
import com.tenwa.kernal.annotation.QuartzJob;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.WebUtil;


 /**
 * 类名称：     QuartzJobSendCollectionEmail
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2015-11-16 上午11:00:16
 * 修改备注：
 * @version 1.0.0
 **/
@QuartzJob(description="定时发送邮件")
public class QuartzJobSendCollectionEmail implements Job {

	/**
	 * (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 **/

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//使用spring的事物
		QuartzJobService quartzJobService = (QuartzJobService)WebUtil.getApplicationContext().getBean("quartzJobService");
		//使用springAOP事物管理的service做一些事情
		System.out.println(">>>当前时间:"+DateUtil.getSystemDateTime());
		
		String currData = DateUtil.getSystemDate();
		
		String hql = "from EmailNotice where (sendFlag = 0 or sendFlag is null) and createDate like '%" +currData+ "%'"
				+ " and contentType in ('OVERDUEDAY_B7', 'OVERDUEDAY_A30')";
		
		try {
			quartzJobService.sendCollectionEmail(hql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
