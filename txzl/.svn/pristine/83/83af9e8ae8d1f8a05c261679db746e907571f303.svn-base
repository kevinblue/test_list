package com.tenwa.business.job;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.kernal.annotation.QuartzJob;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.leasing.controller.SM.SMserviceImpl;
import com.tenwa.leasing.controller.SM.newSM;
import com.tenwa.leasing.entity.SM.MachineMainData;
import com.tenwa.leasing.entity.SM.PickUpMainData;
import com.tenwa.leasing.entity.SM.ProjMainData;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.utils.BSDataBaseManager;
import com.tenwa.leasing.utils.DataBaseManager;
import com.tenwa.report.dao.ReportDao;
import com.tenwa.report.service.ReportService;

@QuartzJob(description = "SM接口后台自动同步")
public class QuartzSMInterface implements Job {

	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//使用spring的事物
		ReportService reportService = (ReportService)WebUtil.getApplicationContext().getBean("reportService");
		//使用springIoc事物管理的service做一些事情
		Log logger = LogFactory.getLog(this.getClass());
		logger.info(">>>当前时间:"+DateUtil.getSystemDateTime());
		try {
			reportService.updateProjMainData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			reportService.updateMachineMainData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			reportService.updatePickupMainData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
