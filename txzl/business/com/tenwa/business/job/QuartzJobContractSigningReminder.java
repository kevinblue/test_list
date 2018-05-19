package com.tenwa.business.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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

@QuartzJob(description="合同签约提醒")
public class QuartzJobContractSigningReminder implements Job{
	@Resource(name = "reportDao")
	private ReportDao reportDao;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//使用spring的事物
		BaseService baseService = (BaseService)WebUtil.getApplicationContext().getBean("baseService");
        //使用springAOP事物管理的service做一些事情
		System.out.println(">>>当前时间:"+DateUtil.getSystemDateTime());
		try {
				
				String SQL_MESSAGE = 
						"insert into BASE_MESSAGE "+
						 "(ID, "+ 
						 "MSG_TITLE, SUBJECT_,  SEND_DATE, CREATE_DATE, "+  
						  "MSG_TYPE,  FROMUSER,  CREATOR_  ) "+ 
						" select   sys_guid(), '合同签约提醒', "+            
						" '你好：合同编号为'||cns.contract_number||'的<<'||cns.contract_name||'>>合同已签约完成,请办理融资租赁登记', "+             
						 "  to_char(SYSTIMESTAMP+1, 'yyyy-mm-dd'), "+           
						"to_char(SYSTIMESTAMP, 'yyyy-mm-dd hh:mm:ss'), "  +            
						" 'msgtype.1',  'Administrator',  'Administrator' "+             
						 "  from contract_number_setting cns  "+            
						" where cns.signing_time is not null "+            
						 "and cns.contracting_place is not null "+            
						"and cns.contract_person is not null "+             
						"and  (to_date(cns.signing_time ,'yyyy-mm-dd hh24:mi:ss') > to_date('2017-06-14 00:00:00','yyyy-mm-dd hh24:mi:ss')) ";           
				baseService.updateBySql(SQL_MESSAGE);
				String SQL_BASE_MESSAGE_TO_USER = 
						
						"insert into BASE_MESSAGE_TO_USER bm (ID, READ_STATUS, CREATE_DATE, MSG_ID, READ_USER, CREATOR_) "+ 
				        "select sys_guid(), '1', bm.create_date, bm.id,dd.user_id_,bm.creator_ "+  
				        "from  (select bb.* from BASE_MESSAGE bb  "+  
				        "where instr(to_char(bb.subject_),'融资租赁登记')>0 "+    
				        "and bb.msg_title = '合同签约提醒' "+  
				        "and bb.id not in (select bmtu.msg_id from BASE_MESSAGE_TO_USER bmtu)) bm left join "+        
				         "(select tu.id_ user_id_ from t_users tu  where ( tu.realname_ = '温艳超' or tu.realname_ = '李欣') ) dd   on   1=1 "; 
						
				baseService.updateBySql(SQL_BASE_MESSAGE_TO_USER);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("合同签约提醒办理融资租赁登记失败！");
			e1.printStackTrace();
		}
	}
}
