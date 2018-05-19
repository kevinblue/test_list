
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
 * 类名称：     QuartzJobSendCollectionSMS
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2015-11-16 上午11:00:16
 * 修改备注：
 * @version 1.0.0
 **/
@QuartzJob(description="定时发送短信")
public class QuartzJobSendCollectionSMS implements Job {

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
		
		String hql = "from SmsNotice where (sendFlag = 0 or sendFlag is null) and createDate like '%" +currData+ "%'"
				+ " and messageType in ('REFUND_MSG','OVERDUE_MSG_1','OVERDUE_MSG_3','OVERDUE_MSG_5','OVERDUE_MSG_PROJ',"
				+ " 'OVERDUE_MSG_TEAM','OVERDUE_MSG_AREA','OVERDUE_MSG_FINA','ASSUROR_MSG','PROJ_MSG')";
		
		try {
			quartzJobService.sendCollectionMsg(hql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
