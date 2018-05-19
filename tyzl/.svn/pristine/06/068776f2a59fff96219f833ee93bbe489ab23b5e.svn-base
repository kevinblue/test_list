
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.job
 * 文件名：         QuartzJobTest.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-2-21-上午11:00:16
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.annotation.QuartzJob;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.UUIDUtil;
import com.tenwa.kernal.utils.WebUtil;


 /**
 * 类名称：     QuartzJobTest
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-2-21 上午11:00:16
 * 修改备注：
 * @version 1.0.0
 **/
@QuartzJob(description="定时计算罚息")
public class QuartzJobCalculatePenalty implements Job {

	/**
	 * (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 **/

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//使用spring的事物
		BaseService baseService = (BaseService)WebUtil.getApplicationContext().getBean("baseService");
        //使用springAOP事物管理的service做一些事情
		System.out.println(">>>当前时间:"+DateUtil.getSystemDateTime());
		
		String delete_sql = "DELETE FROM CONTRACT_FUND_RENT_TERM_PENA";
		String update_sql = "INSERT INTO CONTRACT_FUND_RENT_TERM_PENA (ID,CONTRACT_ID,EQUIP_ID,RENT_LIST,PENALTY,PENALTY_OVERAGE) "
				+ "SELECT '"+UUIDUtil.getUUID()+"' AS ID,CONTRACT_ID,EQUIP_ID,RENT_LIST,YSFX,DSFX FROM V_PROD_FAXI WHERE YSFX>0";
		try {
			baseService.updateBySql(delete_sql, null);
			baseService.updateBySql(update_sql, null);
		} catch (Exception e) {
			System.out.println("罚息计算失败！");
			e.printStackTrace();
		}
		
	}

}
