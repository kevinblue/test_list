
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.controller
 * 文件名：         QuartzPublishController.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-12-4-下午03:03:47
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.quartz.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenwa.business.constant.JsonReturnResultTypeEnum;
import com.tenwa.business.controller.BaseController;
import com.tenwa.business.entity.message.MsgConfig;
import com.tenwa.business.job.QuartzJobMsg;
import com.tenwa.business.model.JsonReturnResult;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.WebUtil;
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
	
	@Resource(name = "baseService")
	private BaseService baseService;
	
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
	
	@RequestMapping("/saveOrUpdateJobAndTrigger.action")
	public String saveOrUpdateJobAndTrigger(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   this.quartzPublishService.saveOrUpdateJobAndTrigger(modelData);
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
	   String jobRecovery = modelData.get("jobRecovery");
	   this.quartzPublishService.saveOrUpdateTrigger(triggerName, jobName,jobRecovery,description, cronExpression, startDate, durableDays, null);
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
	
	@RequestMapping("/resumeMsgTrigger.action")
	public String resumeMsgTrigger(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
		String triggerName = modelData.get("triggerName");
		baseService.updateBySql("update msg_config set msg_status=? where id_=?", "ACQUIRED",modelData.get("id"));
		this.quartzPublishService.resumeMsgTrigger(triggerName);
		return null;	
	}
	@RequestMapping("/pauseMsgTrigger.action")
	public String pauseMsgTrigger(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String triggerName = modelData.get("triggerName");
	   baseService.updateBySql("update msg_config set msg_status=? where id_=?", "PAUSED", modelData.get("id"));
	   this.quartzPublishService.pauseMsgTrigger(triggerName);
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
	
	@RequestMapping("/runMsgTrigger.action")
	public String runMsgTrigger(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
		String triggerName = modelData.get("triggerName");
		this.quartzPublishService.runMsgTrigger(triggerName);
		return null;	
	}
	@RequestMapping("/removeMsgTrigger.action")
	public String removeMsgTrigger(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
		String triggerName = modelData.get("triggerName");
		Scheduler sch = WebUtil.getSchedulerFactory().getScheduler();
		Trigger trigger = sch.getTrigger(triggerName, QuartzJobMsg.MSG_GROUP_NAME);
		String jobName = trigger.getJobName();
		sch.pauseJob(jobName, QuartzJobMsg.MSG_GROUP_NAME);
		sch.deleteJob(jobName, QuartzJobMsg.MSG_GROUP_NAME);
		baseService.updateBySql("delete from msg_config where id_=?", modelData.get("id"));
		this.quartzPublishService.removeMsgTrigger(triggerName);
		return null;	
	}
	@RequestMapping("/removeMsgRelative.action")
	public String removeMsgRelative(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
		baseService.updateBySql("delete from msg_send_relative where sms_id=?", modelData.get("id"));
		return null;	
	}
	
	@RequestMapping(value = "/updateCronExpression.acl")
	@ResponseBody
	public JsonReturnResult createMsgMethod(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		MsgConfig mc = baseService.findEntityByID(MsgConfig.class, model.get("id"));
		baseService.updateBySql("update msg_config set cron_expression=? where id_=?", model.get("cronexpression"),model.get("id"));
		this.quartzPublishService.updateTrigger(model.get("cronexpression"),mc.getTriggername());
		return returnResult;
	}
	
	@RequestMapping(value = "/checkMsgType.acl")
	@ResponseBody
	public JsonReturnResult checkMsgType(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,String> model =QueryUtil.getRequestParameterMapByAjax(request);
		JsonReturnResult returnResult = new JsonReturnResult(JsonReturnResultTypeEnum.SUCCESS,"");
		List<Map<String,Object>> mc = null;
		if("add".equals(model.get("type"))){
			mc = baseService.queryListBySql("select * from msg_config where msg_type=?",model.get("msgtype"));
		}else if("edit".equals(model.get("type"))){
			mc = baseService.queryListBySql("select msg_type from msg_config where msg_type!=(select msg_type from msg_config where id_=?) and msg_type=?",model.get("id"), model.get("msgtype"));
		}
		if(mc.size()>0){
			returnResult.setReturnType(JsonReturnResultTypeEnum.FAILURE);
		}
		return returnResult;
	}
	
	@RequestMapping("/showMission.action")
	public String showMission(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		Scheduler sch = WebUtil.getSchedulerFactory().getScheduler();
		System.out.println(Arrays.toString(sch.getJobNames(QuartzJobMsg.MSG_GROUP_NAME)));
		String[] abc = sch.getJobNames(QuartzJobMsg.MSG_GROUP_NAME);
		for(String s:abc){
			sch.pauseJob(s, "MSG_DEFAULT_GROUP");
			sch.deleteJob(s, "MSG_DEFAULT_GROUP");
		}
		System.out.println(Arrays.toString(sch.getJobNames(QuartzJobMsg.MSG_GROUP_NAME)));
		return null;	
	}
}
