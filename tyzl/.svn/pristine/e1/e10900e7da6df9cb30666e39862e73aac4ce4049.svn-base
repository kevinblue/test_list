
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.controller
 * 文件名：         QuartzPublishController.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-12-4-下午03:03:47
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.quartz.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenwa.business.controller.BaseController;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.quartz.service.QuartzPublishService;


 /**
 * 类名称：     QuartzPublishController
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-12-4 下午03:03:47
 * 修改备注：
 * @version 1.0.0
 **/
@Controller(value="quartzPublishController")
@RequestMapping("/**/quartz")
public class QuartzPublishController extends BaseController 
{
	@Resource(name="quartzPublishService")
	private QuartzPublishService quartzPublishService;
	
	   @RequestMapping(value="/getQuartzJobs.action")
		public String getWorkflowActions(HttpServletRequest request,HttpServletResponse response) throws Exception
		{
			this.output(response, "{datas:"+this.quartzPublishService.getQuartzJobs()+"}");
			return null;
		}
	@RequestMapping("/saveOrUpdateJob.action")
	public String saveOrUpdateJob(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String jobName = modelData.get("jobName");
	   String description = modelData.get("description");
	   String jobClassName = modelData.get("jobClassName");
	   this.quartzPublishService.saveOrUpdateJob(jobName, description, jobClassName);
	   return null;	
	}
	@RequestMapping("/removeJob.action")
	public String removeJob(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String jobName = modelData.get("job_name");
	   this.quartzPublishService.removeJob(jobName);
	   return null;	
	}
	@RequestMapping("/saveOrUpdateTrigger.action")
	public String saveOrUpdateTrigger(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String triggerName = modelData.get("triggerName");
	   String jobName = modelData.get("jobName");
	   String description = modelData.get("description");
	   String cronExpression = modelData.get("cronExpression");
	   String startDate = modelData.get("startDate");
	   String durableDays = modelData.get("durableDays");
	   this.quartzPublishService.saveOrUpdateTrigger(triggerName, jobName,description, cronExpression, startDate, durableDays, null);
	   return null;	
	}
	@RequestMapping("/resumeTrigger.action")
	public String resumeTrigger(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
		String triggerName = modelData.get("triggerName");
		this.quartzPublishService.resumeTrigger(triggerName);
		return null;	
	}
	@RequestMapping("/pauseTrigger.action")
	public String pauseTrigger(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String triggerName = modelData.get("triggerName");
	   this.quartzPublishService.pauseTrigger(triggerName);
	   return null;	
	}
	@RequestMapping("/runTrigger.action")
	public String runTrigger(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
		String triggerName = modelData.get("triggerName");
		this.quartzPublishService.runTrigger(triggerName);
		return null;	
	}
	@RequestMapping("/removeTrigger.action")
	public String removeTrigger(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
		String triggerName = modelData.get("triggerName");
		this.quartzPublishService.removeTrigger(triggerName);
		return null;	
	}
}
