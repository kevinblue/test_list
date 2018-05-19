package com.tenwa.business.job;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.tenwa.business.service.TableService;
import com.tenwa.kernal.annotation.QuartzJob;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.leasing.entity.proj.SpecialRegular;
import com.tenwa.leasing.entity.proj.SpecialRegularCalConfig;

@QuartzJob(description="(初始化测算规则) ")
public class QuartzJobInitspecialRegular  implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		TableService tableService = (TableService)WebUtil.getApplicationContext().getBean("tableService");
		 try {		    	
			    List<SpecialRegularCalConfig> sc=tableService.findEntities(SpecialRegularCalConfig.class);
			    for(int i=0;i<sc.size();i++){
			          String json=sc.get(i).getSpecialRegularJsons();
			          System.out.println(json);
			         tableService.updateOneToManyCollections(sc.get(i), "specialRegulars", SpecialRegular.class, "specialRegularCal", json,null);
			    }
		    } catch (Exception e) {
		    	     e.printStackTrace();
					throw new JobExecutionException("初始化测算规则");
			}
	}
	

}
