
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.service
 * 文件名：         QuartzPublishService.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-12-4-下午01:44:33
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.quartz.service;

import java.util.Map;




 /**
 * 类名称：     QuartzPublishService
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-12-4 下午01:44:33
 * 修改备注：
 * @version 1.0.0
 **/

public interface QuartzPublishService {

	public  String  getQuartzJobs() throws Exception;
	public void saveOrUpdateJob(String jobName,String description,String jobClassName) throws Exception;
	public void removeJob(String jobName) throws Exception;
	public void saveOrUpdateTrigger(String triggerName, String jobName,String description, String cronExpression,String startDate,String durableDays,Map<String,Object> dataMap) throws Exception; 
	public void pauseTrigger(String triggerName) throws Exception;
	public void resumeTrigger(String triggerName) throws Exception;
	public void runTrigger(String triggerName) throws Exception;
	public boolean removeTrigger(String triggerName) throws Exception;
}
