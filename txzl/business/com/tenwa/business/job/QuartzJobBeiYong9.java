
 /**
 * 项目名称：    系统名称
 * 包名：              com.business.job
 * 文件名：         QuartzJobTest.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-2-21-上午11:00:16
 * Copyright：2013XX公司-版权所有
 **/

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


 /**
 * 类名称：     QuartzJobOnhireRemind
 * 类描述：     
 * 创建人：     zhangmuqing
 * 修改人：     zhangmuqing
 * 修改时间：2017-3-28 下午11:00
 * 修改备注：
 * @version 1.0.0
 **/
@QuartzJob(description="备用9提醒")
public class QuartzJobBeiYong9 implements Job {

	/**
	 * (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 **/
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//使用spring的事物
		ReportService reportService = (ReportService)WebUtil.getApplicationContext().getBean("reportService");
		//使用springIoc事物管理的service做一些事情
		Log logger = LogFactory.getLog(this.getClass());
		logger.info(">>>当前时间:"+DateUtil.getSystemDateTime());
		try {			
			reportService.runBeiYong9();				
		}catch (Exception e) {
			System.out.println("执行代理失败！");
			e.printStackTrace();
		}
		
		
	}
	
}
