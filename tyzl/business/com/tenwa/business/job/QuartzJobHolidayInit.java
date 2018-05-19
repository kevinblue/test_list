package com.tenwa.business.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.tenwa.business.service.TableService;
import com.tenwa.kernal.annotation.QuartzJob;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.leasing.entity.base.HolidayInfo;
import com.tenwa.leasing.utils.InitHolidayInfo;

@QuartzJob(description="初始化节假日的日期")
public class QuartzJobHolidayInit  implements Job{
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		TableService tableService = (TableService)WebUtil.getApplicationContext().getBean("tableService");
		 try {		    	
			 Map<String,String>holiday=new HashMap<String,String>();
			 holiday=InitHolidayInfo.getHoliday("2016");
			 for(String key:holiday.keySet()){
				 HolidayInfo hi=new HolidayInfo();
				 hi.setHoliday(key);
				 hi.setWorkday(holiday.get(key));
				 tableService.saveOrUpdateEntity(hi);
			 }
		    } catch (Exception e) {
		    	     e.printStackTrace();
					throw new JobExecutionException("初始化测算规则");
			}
	}

}
