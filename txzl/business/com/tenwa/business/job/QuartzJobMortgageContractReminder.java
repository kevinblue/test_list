package com.tenwa.business.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.tenwa.kernal.annotation.QuartzJob;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.report.service.ReportService;

@QuartzJob(description="抵押合同制作提醒")
public class QuartzJobMortgageContractReminder implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//使用spring的事物
		ReportService reportService = (ReportService)WebUtil.getApplicationContext().getBean("reportService");
		//使用springIoc事物管理的service做一些事情
		Log logger = LogFactory.getLog(this.getClass());
		logger.info(">>>当前时间:"+DateUtil.getSystemDateTime());
		try {			
			reportService.runMortgageContractRemind();				
		}catch (Exception e) {
			System.out.println("执行代理失败！");
			e.printStackTrace();
		}
	}
}
