package com.tenwa.business.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.annotation.QuartzJob;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.UUIDUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.report.dao.ReportDao;
import com.tenwa.report.service.ReportService;

@QuartzJob(description = "抵质押物及租赁物保险情况提醒")
public class QuartzJobInsuranceOfGoodsAndIease implements Job {
	@Resource(name = "reportDao")
	private ReportDao reportDao;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		ReportService reportService = (ReportService) WebUtil.getApplicationContext().getBean("reportService");
		// 使用springIoc事物管理的service做一些事情
		Log logger = LogFactory.getLog(this.getClass());
		logger.info(">>>当前时间:" + DateUtil.getSystemDateTime());
		try {
			reportService.runBaoXiDaoQi();
		} catch (Exception e) {
			System.out.println("执行代理失败！");
			e.printStackTrace();
		}
	
	}
}
