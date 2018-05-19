package com.tenwa.business.job;
import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.dao.DataAccessException;

import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.annotation.QuartzJob;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.report.dao.ReportDao;

@QuartzJob(description = "代办任务OA接口")
public class AgencyTaskOA implements Job {
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//使用spring的事物
		BaseService baseService = (BaseService)WebUtil.getApplicationContext().getBean("baseService");
        //使用springAOP事物管理的service做一些事情
		System.out.println(">>>当前时间:"+DateUtil.getSystemDateTime());
	    String DELETE_QUERY_AGENT_TXZLOA = "DELETE FROM v3xuser.TODOTABLE_FOR_ELS@dbU4ELS";
	    String SAVE_QUERY_AGENT_TXZL = "insert into v3xuser.TODOTABLE_FOR_ELS@dbU4ELS    "
	    		+ "value (select VOP.USER_ID,'融资租赁系统' as TITLE,sum(vop.TODO_COUNT) AS TODO_COUNT,'' as TODO_URL  "
	    		+ "from VI_OA_PENDINGTASKS VOP  GROUP BY VOP.USER_ID)  ";
		try {
			baseService.updateBySql(DELETE_QUERY_AGENT_TXZLOA, null);
			baseService.updateBySql(SAVE_QUERY_AGENT_TXZL, null);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
