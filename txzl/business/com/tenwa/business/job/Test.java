package com.tenwa.business.job;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.WebUtil;

public class Test  implements Job{
	/**
	 * 后台模拟短信发送
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			Scheduler sch = WebUtil.getSchedulerFactory().getScheduler();
			Trigger tri = context.getTrigger();
			BaseService baseService = (BaseService) WebUtil.getApplicationContext().getBean("baseService");
			List<Map<String,Object>> sms = baseService.queryListBySql("select b.id_,b.sms_content from msg_send_relative a,t_sms_notice_tasks b where a.sms_id=b.id_ and a.msg_send_jobname=?", tri.getJobName());
			baseService.updateBySql("update t_sms_notice_tasks set send_time=?,send_flag=1 where id_=?", DateUtil.getDateTime(new Date()),sms.get(0).get("id_").toString());
			System.out.println("当前任务执行时间为=============="+DateUtil.getDateTime(new Date()));
			System.out.println(sms.get(0).get("sms_content").toString());
			sch.pauseTrigger(tri.getName(), "MSG_DEFAULT_GROUP");
			sch.unscheduleJob(tri.getJobName(), "MSG_DEFAULT_GROUP");
			sch.deleteJob(tri.getJobName(), "MSG_DEFAULT_GROUP");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
