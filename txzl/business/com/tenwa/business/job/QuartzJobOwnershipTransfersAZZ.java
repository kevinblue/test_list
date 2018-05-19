package com.tenwa.business.job;

import com.tenwa.kernal.annotation.QuartzJob;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.report.service.ReportService;
import java.io.PrintStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

@QuartzJob(description="直租所有权转移证书提醒")
public class QuartzJobOwnershipTransfersAZZ   implements Job{
  public void execute(JobExecutionContext arg0)
    throws JobExecutionException
  {
    ReportService reportService = (ReportService)WebUtil.getApplicationContext().getBean("reportService");

    Log logger = LogFactory.getLog(getClass());
    logger.info(">>>当前时间:" + DateUtil.getSystemDateTime());
    try
    {
      reportService.runOwnershipTransfersZZ();
    } catch (Exception e) {
      System.out.println("执行代理失败！");
      e.printStackTrace();
    }
  }
}