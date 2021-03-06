
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.serviceImpl
 * 文件名：         QuartzPublishServiceImpl.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-12-4-下午01:43:39
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.quartz.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.annotation.QuartzJob;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.FileUtil;
import com.tenwa.kernal.utils.UUIDUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.quartz.service.QuartzPublishService;



 /**
 * 类名称：     QuartzPublishServiceImpl
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-12-4 下午01:43:39
 * 修改备注：
 * @version 1.0.0
 **/
@Service(value="quartzPublishService")
public class QuartzPublishServiceImpl implements QuartzPublishService
{
	@Resource(name="baseService")
	private BaseService baseService;
	protected final Log log = LogFactory.getLog(getClass());
	private final String DEAULT_GROUP_NAME = Scheduler.DEFAULT_GROUP;
	@Override
	public  String  getQuartzJobs() throws Exception
	{
		    String quartzJobs = "[]";
			JSONArray jsonArray   = new JSONArray();
			Set<Class<?>> classes = FileUtil.getClasses("com.tenwa.business.job");
			for(Class<?>  clazz :classes)
			{
				QuartzJob quartzJob = clazz.getAnnotation(QuartzJob.class);
				if(null != quartzJob){
					    String jobClassName = clazz.getName();
						JSONObject  json = new JSONObject();
						Assert.assertNotNull(jobClassName);
						json.put("value", jobClassName);
						String quartzJobDescription = quartzJob.description();
						Assert.assertNotNull(quartzJobDescription);
						json.put("name", clazz.getSimpleName()+"("+quartzJobDescription+")");
						jsonArray.put(json);
				}
			}
			quartzJobs = jsonArray.toString();
		return quartzJobs;
	}
	public Scheduler getScheduler() throws Exception
	{
		return WebUtil.getSchedulerFactory().getScheduler();
	}
	public void saveOrUpdateJob(String jobName,String description,String jobClassName) throws Exception
	{
		JobDetail jobDetail = null;
		if(!StringUtils.isBlank(jobName))
		{
			jobDetail = this.getScheduler().getJobDetail(jobName, DEAULT_GROUP_NAME);
			getScheduler().deleteJob(jobName,DEAULT_GROUP_NAME);
		}
		jobDetail = new JobDetail();
		jobName = UUIDUtil.getUUID();
		jobDetail.setJobClass(Class.forName(jobClassName));
		jobDetail.setName(jobName);
		jobDetail.setGroup(DEAULT_GROUP_NAME);
		jobDetail.setDescription(description);
		//jobDetail.setJobDataMap(new JobDataMap(dataMap));
		 //当该任务完成后，是否还在JobStore中继续保留该任务
		jobDetail.setDurability(true);
		//重启应用之后是否删除任务的相关信息，默认false
		jobDetail.setVolatility(false);
		//当系统重新启动后，是否再次执行过期任务
		jobDetail.setRequestsRecovery(true);
        //添加quartz任务监听日志
		jobDetail.addJobListener("QuartzJobListener");
		getScheduler().addJob(jobDetail, true);
	}
	public void removeJob(String jobName) throws Exception
	{
		//JobDetail jobDetail = getScheduler().getJobDetail(jobName, getScheduler().DEFAULT_GROUP);
		getScheduler().deleteJob(jobName,DEAULT_GROUP_NAME);
	}
	/**
	* 根据job的名称获取job,进而添加到trigger
	* @param name
	* @param jobName
	* @param cronExpression
	* @throws SchedulerException 
	*/
	public void saveOrUpdateTrigger(String triggerName, String jobName,String description, String cronExpression,String startDate,String durableDays,Map<String,Object> dataMap) throws Exception 
	{
		//JobDetail jobDetail = scheduler.getJobDetail(jobName, DEAULT_GROUP_NAME);
		//getScheduler().addJob(jobDetail, true);
		CronTrigger cronTrigger = null;
		/*if(!StringUtils.isBlank(triggerName))
		{
			cronTrigger = (CronTrigger)this.getScheduler().getTrigger(triggerName, DEAULT_GROUP_NAME);
		}
		else
		{
			cronTrigger = new CronTrigger();
			triggerName = UUIDUtil.getUUID();
		}*/
		getScheduler().unscheduleJob(triggerName, DEAULT_GROUP_NAME);
		cronTrigger = new CronTrigger();
		triggerName = UUIDUtil.getUUID();
		cronTrigger.setName(triggerName);
		cronTrigger.setGroup(DEAULT_GROUP_NAME);
		cronTrigger.setDescription(description);
		CronExpression ce = new CronExpression(cronExpression);
		cronTrigger.setCronExpression(ce);
		Date startTime = DateUtil.getTimeByFormat(startDate, "yyyy-MM-dd HH:mm:ss");
		int days = Integer.parseInt(durableDays);
		Date endTime   = DateUtil.getEndDateByDays(startTime, days);
		cronTrigger.setJobName(jobName);
		cronTrigger.setJobGroup(DEAULT_GROUP_NAME);
		Date currentDate  = new Date();
		if(startTime.compareTo(currentDate)<=0)
		{
			startTime = new Date(currentDate.getTime()+1000);
		}
		cronTrigger.setStartTime(startTime);
		cronTrigger.setEndTime(endTime);
		cronTrigger.setVolatility(false);
		//cronTrigger.setPreviousFireTime(null);
		cronTrigger.setNextFireTime(ce.getNextValidTimeAfter(startTime));
        //添加quartz任务监听日志
		cronTrigger.addTriggerListener("QuartzTriggerListener");
		
		if(null!=dataMap)
		{
			cronTrigger.setJobDataMap(new JobDataMap(dataMap));
		}
		try
		{
			getScheduler().scheduleJob(cronTrigger);
		}catch(Exception e)
		{
			getScheduler().rescheduleJob(cronTrigger.getName(), cronTrigger.getGroup(), cronTrigger);
		}
		getScheduler().pauseTrigger(cronTrigger.getName(), cronTrigger.getGroup());
	}
	public void pauseTrigger(String triggerName) throws Exception
	{ 
		try 
		{
		   getScheduler().pauseTrigger(triggerName, DEAULT_GROUP_NAME);//停止触发器
		} 
		catch (SchedulerException e) 
		{
			log.error(e.getMessage());
			throw new SchedulerException();
		}
	}
	public void resumeTrigger(String triggerName) throws Exception
	{ 
		try 
		{
		   getScheduler().resumeTrigger(triggerName, DEAULT_GROUP_NAME);//重启触发器
		} 
		catch (SchedulerException e) 
		{
		   log.error(e.getMessage());
		   throw new SchedulerException();
		}
	}
	public boolean removeTrigger(String triggerName) throws Exception
	{ 
		try 
		{
		  getScheduler().pauseTrigger(triggerName, DEAULT_GROUP_NAME);//停止触发器
		  return getScheduler().unscheduleJob(triggerName, DEAULT_GROUP_NAME);//移除触发器
		} catch (SchedulerException e) {
		log.error(e.getMessage());
		throw new SchedulerException();
		}
	}
	public static final Map<String,String> status = new HashMap<String,String>();
	static
	{
		status.put("ACQUIRED", "运行");
		status.put("PAUSED", "暂停");
		status.put("WAITING", "等待");
	}
	
	 /**
	 * (non-Javadoc)
	 * @see com.tenwa.quartz.service.QuartzPublishService#runTrigger(java.lang.String)
	 **/
	
	@Override
	public void runTrigger(String triggerName) throws Exception {
		CronTrigger trigger = (CronTrigger)this.getScheduler().getTrigger(triggerName, DEAULT_GROUP_NAME);
		//trigger.setPreviousFireTime(new Date());
		String jobName  = trigger.getJobName();
		String jobGroup = trigger.getJobGroup();
		this.getScheduler().triggerJob(jobName, jobGroup);
		String sql = "UPDATE QRTZ_TRIGGERS SET PREV_FIRE_TIME = ? WHERE TRIGGER_NAME = ? AND TRIGGER_GROUP = ? AND JOB_NAME = ? AND JOB_GROUP = ?";
		this.baseService.getBussinessDao().getJdbcTemplate().update(sql,new Date().getTime(),triggerName,DEAULT_GROUP_NAME,jobName,jobGroup );
	}
}
