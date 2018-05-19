
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.listener
 * 文件名：         GlobalQuartzJobListener.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-12-13-上午11:32:45
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.quartz.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.Trigger;

import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.StringUtil;


 /**
 * 类名称：     GlobalQuartzJobListener
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-12-13 上午11:32:45
 * 修改备注：
 * @version 1.0.0
 **/

public class JobListenerImpl implements JobListener {
    private Log log = LogFactory.getLog(this.getClass());
	/**
	 * (non-Javadoc)
	 * @see org.quartz.JobListener#getName()
	 **/

	@Override
	public String getName() {
		return "QuartzJobListener";
	}

	/**
	 * (non-Javadoc)
	 * @see org.quartz.JobListener#jobExecutionVetoed(org.quartz.JobExecutionContext)
	 **/

	@Override
	public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {

	}

	/**
	 * (non-Javadoc)
	 * @see org.quartz.JobListener#jobToBeExecuted(org.quartz.JobExecutionContext)
	 **/

	@Override
	public void jobToBeExecuted(JobExecutionContext jobExecutionContext) 
	{
	}

	/**
	 * (non-Javadoc)
	 * @see org.quartz.JobListener#jobWasExecuted(org.quartz.JobExecutionContext, org.quartz.JobExecutionException)
	 **/

	@Override
	public void jobWasExecuted(JobExecutionContext jobExecutionContext,JobExecutionException jobExecutionException) 
	{
		if(log.isInfoEnabled())
		{
			JobDetail jobDetail = jobExecutionContext.getJobDetail();
			Trigger trigger = jobExecutionContext.getTrigger();
			
			log.info("任务 <"+StringUtil.empty2Other(jobDetail.getDescription(), jobDetail.getName())+","+jobDetail.getJobClass().getName()+" > ");
			log.info("触发器 <"+StringUtil.empty2Other(trigger.getDescription(), trigger.getName())+" > ");
			log.info("本次触发时间 <"+DateUtil.getDateTime(trigger.getPreviousFireTime())+"> ,下次触发时间<"+DateUtil.getDateTime(trigger.getNextFireTime())+" > ");
			log.info("本次任务执行共用时 < "+jobExecutionContext.getJobRunTime()/1000 + " 秒 > ");
		}
	}
}
