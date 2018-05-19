
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.job
 * 文件名：         QuartzJobTest.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-2-21-上午11:00:16
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.job;

import java.util.HashMap;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.tenwa.business.service.QuartzJobService;
import com.tenwa.kernal.annotation.QuartzJob;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.WebUtil;


 /**
 * 类名称：     QuartzJobCreateCollectionEmail
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2015-11-16 上午11:00:16
 * 修改备注：
 * @version 1.0.0
 **/
@QuartzJob(description="定时生成催收邮件")
public class QuartzJobCreateCollectionEmail implements Job {

	/**
	 * (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 **/

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//使用spring的事物
		QuartzJobService quartzJobService = (QuartzJobService)WebUtil.getApplicationContext().getBean("quartzJobService");
		//使用springAOP事物管理的service做一些事情
		System.out.println(">>>当前时间:"+DateUtil.getSystemDateTime());
		
		String custOverdueSql = "    SELECT CUST.CUST_NAME,"
				+ "                         CI.CONTRACT_NUMBER,"
				+ "                         CI.SIGN_DATE,"
				+ "                         CFRP.RENT_LIST,"
				+ "                         CFRP.RENT - NVL(INCOME.IN_RENT, 0) AS RENT,"
				+ "                         CFRP.PLAN_DATE,"
				+ "                         CI.PROJ_MANAGE AS SENDOBJ,"
				+ "                         TU.EMAIL_ AS EMAIL,"
				+ "                         CI.ID AS CONTRACT_ID,"
				+ "                         CI.CUST_ID AS CUST_ID,"
				+ "                         ? AS OVERDUEDAY"
				+ "                    FROM CONTRACT_FUND_RENT_PLAN CFRP"
				+ "                    LEFT JOIN ("
				+ "                      SELECT SUM(INCOME.RENT) AS IN_RENT,"
				+ "                             INCOME.CONTRACT_ID,"
				+ "                             INCOME.PLAN_LIST"
				+ "                        FROM CONTRACT_FUND_RENT_INCOME INCOME"
				+ "                       GROUP BY INCOME.CONTRACT_ID, INCOME.PLAN_LIST"
				+ "                    ) INCOME ON CFRP.CONTRACT_ID = INCOME.CONTRACT_ID AND CFRP.RENT_LIST = INCOME.PLAN_LIST"
				+ "                    LEFT JOIN CONTRACT_INFO CI ON CFRP.CONTRACT_ID = CI.ID"
				+ "                    LEFT JOIN CUST_INFO CUST ON CI.CUST_ID = CUST.ID"
				+ "                    LEFT JOIN ("
				+ "                      SELECT * FROM CUST_RELATED_PERSON WHERE MAIN_PERSON_FLAG = '是'"
				+ "                    ) CRP ON CI.CUST_ID = CRP.CUST_ID"
				+ "                    LEFT JOIN T_USERS TU ON CI.PROJ_MANAGE = TU.ID_"
				+ "                   WHERE TRUNC(SYSDATE) - TO_DATE(CFRP.PLAN_DATE, 'YYYY-MM-DD') = ?"
				+ "                     AND CFRP.RENT - NVL(INCOME.IN_RENT, 0) > 0"
				+ "                     AND TU.EMAIL_ IS NOT NULL";
		
		try {
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("custOverdueSql", custOverdueSql);
			
			quartzJobService.createCollectionEmail(sqlMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
