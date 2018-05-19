package com.tenwa.business.job;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;

import com.tenwa.business.service.BaseService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.annotation.QuartzJob;
import com.tenwa.kernal.utils.WebUtil;

@QuartzJob(description="(初始化合同租金情况) ")
public class ContractInitRentInfoJbo  implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		BaseService baseService = (BaseService)WebUtil.getApplicationContext().getBean("baseService");
		 try {		    	
			
		       baseService.getBussinessDao().getJdbcTemplate().execute(  
		    		     new CallableStatementCreator() {  
		    		        public CallableStatement createCallableStatement(Connection con) throws SQLException {  
		    		           String storedProc = "{call PROC_INIT_CONTRACT}";// 调用的sql  		    		           
		    		           CallableStatement cs = con.prepareCall(storedProc);  
		    		           return cs;  
		    		        }  
		    		     }, new CallableStatementCallback() {  
		    		         public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {  
		    		           cs.execute();  
		    		           return cs;// 获取输出参数的值  
		    		     }  
		    	});  
		    } catch (Exception e) {
		    	     e.printStackTrace();
					throw new JobExecutionException("初始化合同租金情况失败");
			}
	}
	

}
