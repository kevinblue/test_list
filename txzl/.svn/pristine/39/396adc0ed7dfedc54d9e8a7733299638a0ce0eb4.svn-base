
 /**
 * 项目名称：    系统名称
 * 包名：              com.business.job
 * 文件名：         QuartzJobTest.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-2-21-上午11:00:16
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
 * 类名称：     QuartzJobCommonWork
 * 类描述：     
 * 创建人：     zhouyahui
 * 修改人：     zhouyahui
 * 修改时间：2017-2-17 下午11:00
 * 修改备注：
 * @version 1.0.0
 **/
@QuartzJob(description="全球接口定時任务")
public class QuartzJobGlobal implements Job {

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
			//定时每月的1日执行定时代理，查询上一月份的数据
			 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
			 Calendar c = Calendar.getInstance();
			  c.setTime(new Date());
		      c.add(Calendar.MONTH, -1);
		      Date m = c.getTime();
		      String reportdate = format.format(m);
			//String  reportdate=DateUtil.getSystemTimeByFormat("yyyy-MM");
			//String  reportdate="2016-11";
			//维护历史数据年
			/*Calendar cal = Calendar.getInstance();
			for (int a = 0; a < 3; a++) {
				cal.set(Calendar.YEAR, 2017);
				cal.set(Calendar.MONTH, a);
				String reportdate = new SimpleDateFormat("yyyy-MM").format(cal.getTime());}//大括号放在最后面*/
			
			reportService.saveGlobalContract();//保存风场和风机采购合同对应表
			reportService.saveGlobal(reportdate);//保存单个项目月（年）度运行报表
			reportService.saveGlobalFan(reportdate);//保存基本信息表Fan
			
		}catch (Exception e) {
			System.out.println("执行代理失败！");
			e.printStackTrace();
		}
		
		
	}
	
}
