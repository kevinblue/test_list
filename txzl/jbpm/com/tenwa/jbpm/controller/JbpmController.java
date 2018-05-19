

 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.controller
 * 文件名：         JbpmController.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-10-23-下午04:47:46
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.jbpm.controller;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.jbpm.api.Deployment;
import org.jbpm.api.RepositoryService;
import org.jbpm.pvm.internal.history.model.HistoryProcessInstanceImpl;
import org.jbpm.pvm.internal.repository.DeploymentImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tenwa.business.controller.BaseController;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.exception.BusinessException;
import com.tenwa.exception.TenwaJbpmException;
import com.tenwa.jbpm.entity.ActivityDetail;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfoUser;
import com.tenwa.jbpm.entity.JbpmWorkflowDesigner;
import com.tenwa.jbpm.exception.JbpmException;
import com.tenwa.jbpm.model.ActivityTaskUsers;
import com.tenwa.jbpm.model.InitiatorEnum;
import com.tenwa.jbpm.model.JdbcModel;
import com.tenwa.jbpm.model.WorkflowConfigData;
import com.tenwa.jbpm.service.JbpmService;
import com.tenwa.jbpm.utils.JBPMUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.RemoteUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.WebUtil;


 /**
 * 类名称：     JbpmController
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-10-23 下午04:47:46
 * 修改备注：
 * @version 1.0.0
 **/
@Controller(value="jbpmController")
@RequestMapping(value="/**/jbpm")
public class JbpmController extends BaseController {
   @Resource(name="jbpmService")
   private JbpmService jbpmService ;
   
   /**
    * 判断下一步的处理人是不是和当前处理人为同一个
    * @param request
    * @param response
    * @return
    * @throws Exception
    */
   @RequestMapping(value="/getNextWorkflowInfo.action")
   public String getNextWorkflowInfo(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   String jhiCompletedTaskImplId = request.getParameter("jhiCompletedTaskImplId");
	   Boolean isSame = false;
	   try {
		   //下一步list
		   Set<ActivityTaskUsers> next_activityTaskUsersList = new HashSet<ActivityTaskUsers>();
		   Set<ActivityTaskUsers> next_activityTaskUsersListTemp = this.jbpmService.getNextActivityTaskUsersList(request,jhiCompletedTaskImplId,null,false);
		   //List<String> exceptedActivityNames = new ArrayList<String>();
		   if(0 < next_activityTaskUsersListTemp.size()){
			   String exceptedActivityName  = "";
			   for(ActivityTaskUsers atu : next_activityTaskUsersListTemp){
				   String activityName   = atu.getCurrentTaskUsers().getActivityDetail().getActivityKey();
				   if(!exceptedActivityName.equals(activityName)){
					  //next_activityTaskUsersList.remove(atu);
					   next_activityTaskUsersList.add(atu);
				   }
			   }
		   }
		   if(next_activityTaskUsersList.size() >= 1){
			  for(ActivityTaskUsers user : next_activityTaskUsersList){
				  List<User> dealerUsers =  user.getCurrentTaskUsers().getDealUsers();
				  for(User u : dealerUsers){
					  if(u.getId().equals(SecurityUtil.getPrincipal().getId())){
						  isSame = true;
						  break;
					  }
				  }
			  }
		   }
	}
	 catch (Exception e) {
		e.printStackTrace();
	 }
	 this.ajaxReturn(response, isSame.toString());
     return null ;
   }
   
   
   @RequestMapping(value="/getWorkflowInfo.action")
   public String getWorkflowInfo(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	    String currentTaskId = modelData.get("currentTaskId");
	   String jhiCompletedTaskImplId = request.getParameter("jhiCompletedTaskImplId");
	   String historyProcessInstanceImplDbid = request.getParameter("historyProcessInstanceImplDbid");
	   String infoFlag = request.getParameter("infoFlag");
	   String toPage = "";
	   try {
		if(("advise".equals(infoFlag))){
			//currentJbpmWorkflowHistoryInfoId
			   //意见信息列表
			   request.setAttribute("processedHistoryLogInfoList",this.jbpmService.getProcessedHistoryLogInfoList(historyProcessInstanceImplDbid,jhiCompletedTaskImplId) );
			   toPage = "getWorkflowAdviseDetail.jsp";
		   }
		   else if(("history".equals(infoFlag))){
			   //历史信息列表
			   request.setAttribute("processedHistoryLogInfoList",this.jbpmService.getProcessedHistoryLogInfoList(historyProcessInstanceImplDbid,jhiCompletedTaskImplId) );
			   toPage = "getWorkflowHistoryWindow.jsp";
		   }
		   else if(("next".equals(infoFlag))){
			   //下一步list
			   Set<ActivityTaskUsers> next_activityTaskUsersList = new HashSet<ActivityTaskUsers>();
			   Set<ActivityTaskUsers> next_activityTaskUsersListTemp = this.jbpmService.getNextActivityTaskUsersList(request,jhiCompletedTaskImplId,null,false);
			   Set<ActivityTaskUsers> lastBack_next_activityTaskUsersList = new HashSet<ActivityTaskUsers>();
			   String exceptedActivityName  = "";
			   //List<String> exceptedActivityNames = new ArrayList<String>();
			   boolean continueFlag = true;
			   if(0 < next_activityTaskUsersListTemp.size()){
				   lastBack_next_activityTaskUsersList = this.jbpmService.getLastBackNextActivityTaskUsersList(request,jhiCompletedTaskImplId);
				   JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo = this.jbpmService.findEntityByID(JBPMWorkflowHistoryInfo.class, jhiCompletedTaskImplId);
				   if(lastBack_next_activityTaskUsersList != null && lastBack_next_activityTaskUsersList.size() > 0  ){
					   if(jbpmWorkflowHistoryInfo.getBackModel() != null && jbpmWorkflowHistoryInfo.getBackModel().equals("DIRECTPUSH")){
						   continueFlag = false;
					   }
				   }
				   if(continueFlag){
					 /*  for(ActivityTaskUsers atu : lastBack_next_activityTaskUsersList){
						   exceptedActivityName   = atu.getCurrentTaskUsers().getActivityDetail().getActivityKey();
					   }*/
					   for(ActivityTaskUsers atu : next_activityTaskUsersListTemp){
//						   String activityName   = atu.getCurrentTaskUsers().getActivityDetail().getActivityKey();
//						   if(!exceptedActivityName.equals(activityName)){
							   //next_activityTaskUsersList.remove(atu);
							   next_activityTaskUsersList.add(atu);
//						   }
					   }
				   }else{
					  JBPMWorkflowHistoryInfo currentWorkFlow =  this.jbpmService.findEntityByID(JBPMWorkflowHistoryInfo.class,jhiCompletedTaskImplId);
					  Set<JBPMWorkflowHistoryInfoUser> users = currentWorkFlow.getBackedHistoryInfo().getJbpmWorkflowHistoryInfoUsers(); 
					  String backUserIds = "";
					  for( JBPMWorkflowHistoryInfoUser infoUser : users){
						  backUserIds += infoUser.getPlanActor().getId()+",";
					  }
					  //直接退回，直接将流程提交到提交退回的路由节点,先构建参数
					   for(ActivityTaskUsers atu  : lastBack_next_activityTaskUsersList){
						   modelData.put("currentTaskSubmitButtonText", "Submit");   
						   modelData.put("requestInitiatorRoute", "dynamicLastBackRoutePath");   
						   modelData.put("choseRequestNextRouteNodeName", atu.getRouteInfo().getParentActivityName());
						   JSONArray initiatorArray = new JSONArray();
						   for(User user : atu.getCurrentTaskUsers().getDealUsers()){
							   if(backUserIds.indexOf(user.getId()) >=0){
								   JSONObject userJson = new JSONObject();
								   userJson.put("username", user.getUsername());
								   initiatorArray.put(userJson);
							   }
						   }
						   //modelData.put("requestInitiator", initiatorArray.toString());
						   JSONObject changeRequestInitiator = new JSONObject();
						   changeRequestInitiator.put(atu.getCurrentTaskUsers().getActivityDetail().getActivityName(), initiatorArray);
						   modelData.put("changeRequestInitiator", changeRequestInitiator.toString());   
						   this.jbpmService.submitProcessedTaskForm(request,currentTaskId, modelData);
					   }
					   String ajaxCallBackScript = "javascript:window.top.ajaxCallBack('"+WebUtil.getMessageByLocale("BackRedirectRemindInfo", null, "已成功提交到，退回发起节点！！！！", WebUtil.getSessionLocale())+"',false);if(window.top.loadMask)window.top.loadMask.hide();window.top.jQuery('#id_loadMaskContainer').hide();";
					   this.ajaxReturn(response, ajaxCallBackScript);
					   return null;
				   }
			   }
			   request.setAttribute("lastBack_next_activityTaskUsersList",lastBack_next_activityTaskUsersList );
			   request.setAttribute("next_activityTaskUsersList", next_activityTaskUsersList);
			   toPage = "getWorkflowNextActivityTaskUsersWindow.jsp?"+Math.random()+"&continueFlag="+continueFlag;
		   }
		   else if(("back".equals(infoFlag))){
			   //回退list 
			   request.setAttribute("back_activityTaskUsersList", this.jbpmService.getBackActivityTaskUsersList(request,jhiCompletedTaskImplId));
			   toPage = "getWorkflowBackActivityTaskUsersWindow.jsp";
		   }
		   else if(("all".equals(infoFlag))){
			   //任意路由
			   request.setAttribute("all_activityTaskUsersList", this.jbpmService.getAllActivityTaskUsersList(request,jhiCompletedTaskImplId));
			   toPage = "getWorkflowAllActivityTaskUsersWindow.jsp";
		   }
	}catch(TenwaJbpmException je){
		String ajaxCallBackScript = "javascript:window.top.alert('"+je.getMessage()+"');if(window.top.loadMask)window.top.loadMask.hide();window.top.mini.unmask(document.body);window.top.jQuery('#id_loadMaskContainer').hide();";
		this.ajaxReturn(response, ajaxCallBackScript);
		return null;
	}catch (JbpmException je ) {
		String ajaxCallBackScript = "javascript:window.top.alert('"+je.getMessage()+"');if(window.top.loadMask)window.top.loadMask.hide();window.top.mini.unmask(document.body);window.top.jQuery('#id_loadMaskContainer').hide();";
		this.ajaxReturn(response, ajaxCallBackScript);
		return null;
	} 
	 catch (Exception e) {
		 e.printStackTrace();
		String ajaxCallBackScript = "javascript:window.top.alert('服务器繁忙，请与管理员联系！！！');if(window.top.loadMask)window.top.loadMask.hide();window.top.mini.unmask(document.body);window.top.jQuery('#id_loadMaskContainer').hide();";
		this.ajaxReturn(response, ajaxCallBackScript);
		return null;
	 }
     return "solutions/workflow/jbpm-core/"+toPage;
   }
   @RequestMapping(value="/getDesignerSavedDiagramInfo.action")
   public String getDesignerSavedDiagramInfo(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String designerId     = modelData.get("id");
	   String workflow_name  = modelData.get("workflow_name");
	   String workflow_designerWorkflowJsonString = "{}";
	   JbpmWorkflowDesigner jbpmWorkflowDesigner = null;
	   String attachParam = "";
	   if(null != designerId){
		   jbpmWorkflowDesigner = this.jbpmService.findEntityByID(JbpmWorkflowDesigner.class, designerId);
	   }else{
		   String hsql = "from JbpmWorkflowDesigner jwfd where jwfd.workflowName = ? ";
		   List<JbpmWorkflowDesigner> queryList = this.jbpmService.findResultsByHSQL(hsql, workflow_name);
		   if(0 < queryList.size()){
			   jbpmWorkflowDesigner = queryList.get(0);
		   }
	   }
	   if(null != jbpmWorkflowDesigner){
		   workflow_designerWorkflowJsonString = jbpmWorkflowDesigner.getDesignerWorkflowJson();
		   attachParam+="?id="+jbpmWorkflowDesigner.getId();
		   request.setAttribute("maxDotX",jbpmWorkflowDesigner.getMaxDotX());
		   request.setAttribute("maxDotY",jbpmWorkflowDesigner.getMaxDotY());
	   }
	   request.setAttribute("workflow_designerWorkflowJsonString", workflow_designerWorkflowJsonString);
	   return "solutions/workflow/jbpm-designer/jbpm4-4-designer.jsp"+attachParam;
   }
   @RequestMapping(value="/getProjSummaryHistoryInfos.action")
   public String getProjSummaryHistoryInfos(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String keyOne     = modelData.get("keyOne");
	   String proj_id    = modelData.get("proj_id");
	   List<JBPMWorkflowHistoryInfo> projSummaryHistoryInfos = this.jbpmService.getProjSummary(keyOne,proj_id);
	   request.setAttribute("projSummaryHistoryInfos", projSummaryHistoryInfos);
	   return "solutions/workflow/jbpm-core/projSummary.jsp";
   }
   private static Logger log = Logger.getLogger(JbpmController.class);
@RequestMapping(value="/getNodeConfigInfo.action")
   public String getNodeConfigInfo(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String workflowDefinition = modelData.get("workflowDefinition");
	   String nodeType           = modelData.get("nodeType");
	   String nodeName           = modelData.get("nodeName");
	   String returnInfo = nodeType+" ，"+nodeName;
	   if(!"task".equalsIgnoreCase(nodeType)){
	   }else{
		       returnInfo = "节点名称：<font color='red'>"+nodeName+"</font>";
			   String processInstanceId  = URLDecoder.decode(modelData.get("processInstanceId"),"UTF-8");
			   Map<String,Object> propertiesMap = new HashMap<String,Object>();
			   JbpmWorkflowDesigner  jbpmWorkflowDesigner =  null;
			   DeploymentImpl  deploymentImpl =  null;
			   HistoryProcessInstanceImpl historyProcessInstanceImpl = null;
		
			   if(!"-1".equals(processInstanceId)){
				   propertiesMap.put("processInstanceId", processInstanceId);
				   historyProcessInstanceImpl = this.jbpmService.findEntityByProperties(HistoryProcessInstanceImpl.class, propertiesMap).get(0);
				   workflowDefinition         = historyProcessInstanceImpl.getProcessDefinitionId();
				   propertiesMap.clear();
				   propertiesMap.put("workflowDefinition", workflowDefinition);
				   jbpmWorkflowDesigner = this.jbpmService.findEntityByProperties(JbpmWorkflowDesigner.class, propertiesMap).get(0);
				   deploymentImpl = jbpmWorkflowDesigner.getDeploymentImpl();
			   }else{
				    String deployId =  modelData.get("deployId");
					if(deployId.matches("^\\d+$"))
					{
						RepositoryService repositoryService = JBPMUtil.getRepositoryService();
						List<Deployment> l = repositoryService.createDeploymentQuery().deploymentId(deployId).list();
						if(l.size()>0){
							deploymentImpl  = (DeploymentImpl)l.get(0);
							jbpmWorkflowDesigner = deploymentImpl.getJbpmWorkflowDesigner();
						}
					}
					else
					{
						jbpmWorkflowDesigner = (JbpmWorkflowDesigner)this.jbpmService.findEntityByID(JbpmWorkflowDesigner.class, deployId);
						deploymentImpl = jbpmWorkflowDesigner.getDeploymentImpl();
					}
			   }
			  
			   if(null != deploymentImpl){
				   String hsql = "from ActivityDetail ad where ad.deploymentImpl.id = ? and ad.activityType = ? and activityName = ?";
				   List<ActivityDetail> curentActivityList = this.jbpmService.findResultsByHSQL(hsql,deploymentImpl.getDbid(),nodeType,nodeName);
				   if(0 ==  curentActivityList.size()){
					   returnInfo = "<font color='red'>节点配置信息不存在！</font>";
					   this.output(response, returnInfo);
					   return null;
				   }
				   ActivityDetail curentActivity =  (ActivityDetail)curentActivityList.get(0);
				   String actorType = curentActivity.getActorType();
				   String actorValue = curentActivity.getActorValue();
				   String actorTypeChinese = InitiatorEnum.valueOf(actorType.toUpperCase()).getChineseName();
				   String displayActorValue = curentActivity.getDisplayActorValue();
				   returnInfo = (returnInfo+"<br/>参与人类型：<font color='red'>"+actorTypeChinese+"</font><br/>参与人设定值：<font color='red'>"+StringUtil.empty2Other(StringUtil.empty2Other(displayActorValue, actorValue),"无指定"))+"</font>";
				   String jbpmWorkflowHistoryInfoUserId = StringUtil.nullToString(request.getParameter("jbpmWorkflowHistoryInfoUserId"));
				   if((!"-1".equals(processInstanceId))&&!((jbpmWorkflowHistoryInfoUserId.isEmpty())&&("submitRelation".equals(actorType)))){
					  String processInstanceDBID = ""+historyProcessInstanceImpl.getDbid();
					  modelData.putAll(JBPMUtil.getProcessInstanceHistoryData(this.jbpmService.getBussinessDao().getHibernateTemplate(), processInstanceDBID));
					  log.info(modelData.get("jbpmWorkflowHistoryInfoUserId"));
					  List<User> actors = new ArrayList<User>();
					  try{
						  actors = JBPMUtil.getActorsListByActivitySetting(request,curentActivity, processInstanceId, actorType, actorValue, modelData, this.jbpmService, "actor");
					  }catch(Exception e){
						  User user = new User();
						  user.setRealname("暂无法确定处理人");
						  user.setUsername(nodeName+"节点需由关联节点路由提交完后才能确定代办处理人");
						  actors.add(user);
					  }
				      StringBuffer sbString = new StringBuffer(returnInfo+"<br/>解析的人员列表：<font color='red'>");
				      int index = 0;
					  for(User actor : actors){
						  if(index++>0){
							  sbString.append(" ");
						  }
						  sbString.append(actor.getRealname()+"（"+actor.getUsername()+"）");
				      }
					  sbString.append("</font>");
					  returnInfo = sbString.toString();
				   }
			   }
	   }
	   this.output(response, returnInfo);
	   return null;
   }
   @SuppressWarnings("unchecked")
   @RequestMapping(value="/updateSerialNoSql.action")
   public String testHibernateEntityFlush(HttpServletRequest request,HttpServletResponse response) throws Exception{
	   HibernateTemplate hibernateTemplate = (HibernateTemplate)WebUtil.getApplicationContext().getBean("hibernateTemplate");
	   DetachedCriteria criteria = DetachedCriteria.forClass(JBPMWorkflowHistoryInfo.class);
	   List<JBPMWorkflowHistoryInfo> infos = (List<JBPMWorkflowHistoryInfo>)hibernateTemplate.findByCriteria(criteria);
	   final FileOutputStream fos = new FileOutputStream(WebUtil.getWebContextRealPath()+"updateSerialNoSql.sql");
	   for(JBPMWorkflowHistoryInfo info : infos){
		   String processInstanceDBID = info.getHistoryProcessInstanceImpl().getDbid()+"";
		       Map<String,String> m = new HashMap<String,String>();
		       JBPMUtil.getVariablesInfo(hibernateTemplate, processInstanceDBID, null,m);
		       String updateSql = ("update t_jbpm_workflow_info set key_one_='"+m.get("SerialNo")+"' where id_ = '"+info.getId()+"';");
		       fos.write(updateSql.getBytes());
		       fos.write(System.getProperty("line.separator").getBytes());
	   }
	   fos.close();
	   this.output(response,"生成成功");
	   return null;
	}
   
   @RequestMapping(value="/getWorkflowActions.action")
   public String getWorkflowActions(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String paramWorkflowName     = modelData.get("paramWorkflowName");
	   this.output(response, "{datas:"+this.jbpmService.getWorkflowActions(paramWorkflowName)+"}");
	   return null;
   }
   @RequestMapping(value="/getWorkflowRejectBeanNames.action")
   public String getWorkflowRejectBeanNames(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   /*Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String sourceWorkflowName     = modelData.get("sourceWorkflowName");
	   String rejectWorkflowName     = modelData.get("rejectWorkflowName");*/
	   this.output(response, this.jbpmService.getWorkflowRejectBeanName(false,null,null).toString());
	   return null;
   }
   @RequestMapping(value="/removeWorkflowAllProcessInstance.action")
   public String removeWorkflowAllProcessInstance(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String workflowName     = modelData.get("paramWorkflowName");
	   int    version          = Integer.parseInt(modelData.get("paramWorkflowVersion"));
	   this.jbpmService.removeWorkflowAllProcessInstance(request,workflowName, version);
	   return null;
   }
   
   @RequestMapping(value="/saveKeyToWorkflow.action")
   public String saveKeyToWorkflow(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String designerId         = modelData.get("designerId");
	   String keyOne = modelData.get("keyOne");
	   String keyTwo  = modelData.get("keyTwo");
	   String keyThree  = modelData.get("keyThree");
	   String keyFour  = modelData.get("keyFour");
	   String keyFive  = modelData.get("keyFive");
	   String keySix  = modelData.get("keySix");
	   String keySeven  = modelData.get("keySeven");
	   String keyEight  = modelData.get("keyEight");
	   String keyNine  = modelData.get("keyNine");
	   String keyTen  = modelData.get("keyTen");
	   
	   this.jbpmService.saveKeyToWorkflow(designerId, keyOne, keyTwo, keyThree, keyFour, keyFive, keySix, keySeven, keyEight, keyNine, keyTen);
	   return null;
   }
   
   @RequestMapping(value="/saveRalatedAttachmentToWorkflow.action")
   public String saveRalatedAttachmentToWorkflow(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String designerId         = modelData.get("designerId");
	   String instanceids = modelData.get("instanceids");
	   JbpmWorkflowDesigner jbpmWorkflowDesigner = (JbpmWorkflowDesigner)this.jbpmService.findEntityByID(JbpmWorkflowDesigner.class, designerId);
	   jbpmWorkflowDesigner.setInstanceIds(instanceids);
	   this.jbpmService.saveOrUpdateEntity(jbpmWorkflowDesigner);
	   return null;
   }
   
   @RequestMapping(value="/delegateSingleWorkflow.action")
   public String delegateSingleWorkflow(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String id          = modelData.get("id");
	   String deploymentId = modelData.get("deploymentId");
	   String delegateUserId  = modelData.get("delegateUserId");
	   String startDate  = modelData.get("startDate");
	   String endDate  = modelData.get("endDate");
	   String grantMethod  = modelData.get("grantMethod");
	   this.jbpmService.delegateSingleWorkflow(id, deploymentId, delegateUserId, startDate, endDate, grantMethod);
	   return null;
   }
   @RequestMapping(value="/delegateCheckedWorkflows.action")
   public String delegateCheckedWorkflows(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String delegateUserId  = modelData.get("delegateUserId");
	   String startDate  = modelData.get("startDate");
	   String endDate  = modelData.get("endDate");
	   String grantMethod  = modelData.get("grantMethod");
	   String checkedIds  = modelData.get("checkedIds");
	   String checkedDeploymentIds  = modelData.get("checkedDeploymentIds");
	   this.jbpmService.delegateCheckedWorkflows(checkedIds, checkedDeploymentIds, delegateUserId, startDate, endDate, grantMethod);
	   return null;
   }
   @RequestMapping(value="/delegateAllWorkflows.action")
   public String delegateAllWorkflows(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String delegateUserId  = modelData.get("delegateUserId");
	   String startDate  = modelData.get("startDate");
	   String endDate  = modelData.get("endDate");
	   String grantMethod  = modelData.get("grantMethod");
	   this.jbpmService.delegateAllWorkflows(delegateUserId, startDate, endDate, grantMethod);
	   return null;
   }
   @RequestMapping(value="/removeSingleDelegatedWorkflow.action")
   public String removeSingleDelegatedWorkflow(HttpServletRequest request,HttpServletResponse response) throws Exception{
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String id = modelData.get("id");
	   this.jbpmService.removeSingleDelegatedWorkflow(id);
	   return null;
   }
   @RequestMapping(value="/removeCheckedDelegatedWorkflows.action")
   public String removeCheckedDelegatedWorkflows(HttpServletRequest request,HttpServletResponse response) throws Exception{
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String checkedIds = modelData.get("checkedIds");
	   this.jbpmService.removeCheckedDelegatedWorkflows(checkedIds);
	   return null;
   }
   @RequestMapping(value="/removeAllDelegatedWorkflows.action")
   public String removeAllDelegatedWorkflows(HttpServletRequest request,HttpServletResponse response) throws Exception{
	   this.jbpmService.removeAllDelegatedWorkflows();
	   return null;
   }
   @RequestMapping(value="/removeDesignedWorkflow.action")
   public String removeDesignedWorkflow(HttpServletRequest request,HttpServletResponse response) throws Exception{
	   String designedId = QueryUtil.getRequestParameterMapByAjax(request).get("designedId");
	   this.jbpmService.removeDesignedWorkflow(designedId);
	   return null;
   }
   @RequestMapping(value="/workflowSynchronized.action")
   public String workflowSynchronized(HttpServletRequest request,HttpServletResponse response) throws Exception{
	  String deployId = QueryUtil.getRequestParameterMapByAjax(request).get("deployId");
	  String returnInfo = "流程同步成功";
	  Locale locale = WebUtil.getSessionLocale();
	  try{
		  this.jbpmService.updateWorkflowSynchronized(deployId,locale);
	  }catch(Exception e){
		  e.printStackTrace();
		  returnInfo = e.getMessage();
	  }
	  this.output(response, returnInfo) ;
	  RemoteUtil.remoteWorkflowSynchronized(deployId);
	  return null;
   }
   @RequestMapping(value="/transferedJpdlXmlByWorkflowDesigner.action")
   public String transferedJpdlXmlByWorkflowDesigner(HttpServletRequest request,HttpServletResponse response) throws Exception{
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String returnInfo = "保存成功";
	   try 
	   {
		  String id = this.jbpmService.transferedJpdlXmlByWorkflowDesigner(modelData);
		  returnInfo = "保存成功,"+id;
	   } 
	   catch (Exception e) 
	   {
			e.printStackTrace();
			returnInfo = e.getMessage();
	   }
	   this.output(response,returnInfo);
	   return null;
   }
   
   @RequestMapping(value="/queryDeployedWorkflows.action")
   public String queryDeployedWorkflows(HttpServletRequest request,HttpServletResponse response) throws Exception{
	   request.setAttribute("deployedWorkflows", this.jbpmService.queryDeployedWorkflows());
	   return "solutions/workflow/jbpm-core/listProcessDefinitions.jsp";
   }
   @RequestMapping(value="/deployWorkflow.action")
   public String deployWorkflow(HttpServletRequest request,HttpServletResponse response) throws Exception{
	   String designedId = QueryUtil.getRequestParameterMapByAjax(request).get("designedId");
	   this.jbpmService.deployWorkflow(designedId);
	   return null;
   }
   @RequestMapping(value="/removeDeployedWorkflow.action")
   public String removeDeployedWorkflow(HttpServletRequest request,HttpServletResponse response) throws Exception{
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String deployId = modelData.get("deployId");
	   String designedId = modelData.get("designedId");
	   this.jbpmService.removeDeployedWorkflow(designedId,Long.parseLong(deployId));
	   return null;
   }
   @RequestMapping(value="/getDeployedWorkflowDiagram.action")
   public String getDeployedWorkflowDiagram(HttpServletRequest request,HttpServletResponse response) throws Exception{
	    String deployId = QueryUtil.getRequestParameterMapByAjax(request).get("deployId");
	    byte[] workflowPictureByteData = this.jbpmService.getDeployedWorkflowDiagramDatas(deployId);
	    ServletOutputStream sos = response.getOutputStream();
	    sos.write(workflowPictureByteData, 0, workflowPictureByteData.length);
		sos.flush();
		sos.close();
	   return null;
   }
   @RequestMapping(value="/getActivedRects.action")
   public String getActivedRects(HttpServletRequest request,HttpServletResponse response) throws Exception{
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String processInstanceId = modelData.get("processInstanceId");
	   String deployId = modelData.get("deployId");
	   Map<String,String> map = this.jbpmService.getActivedRects(deployId,processInstanceId);
	   this.setAllKeyToRequestAttributeByMap(request, map);
	   String toPage = "";
	   String jpdlVersion = map.get("jpdlVersion");
	   if("4.4".equals(jpdlVersion))
	   {
		   toPage = "solutions/workflow/jbpm-designer/jbpm4-4-diagram.jsp";
	   }
	   else if("4.3".equals(jpdlVersion))
	   {
		   toPage = "solutions/workflow/jbpm-designer/jbpm4-3-diagram.jsp";
	   }
	   return toPage;
   }
   @RequestMapping(value="/getActivityCoordinates.action")
   public String getActivityCoordinates(HttpServletRequest request,HttpServletResponse response) throws Exception{
	   String processInstanceId = QueryUtil.getRequestParameterMapByAjax(request).get("processInstanceId");
	   request.setAttribute("activityCoordinatesList", this.jbpmService.getActivityCoordinates(processInstanceId));
	   return "solutions/workflow/jbpm-core/activePicture.jsp";
   }
   @RequestMapping(value="/saveChangeToActivity.action")
   public String saveChangeToActivity(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
	   String id = model.get("id");
	   this.jbpmService.saveChangeToActivity(id,model);
	   return null;
   }
   @RequestMapping(value="/startDeployedProcess.action")
   public String startDeployedProcess(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
	   String processDefinitionId = model.get("processDefinitionId");
	   if(null == processDefinitionId)return null;
	   String reuturnInfo = "<script type='text/javascript'>" +
		   		/*"window.opener.rejectWindowDiv = window.opener.document.getElementById('id_rejectWindowDiv');" +
		  		"if(!window.opener.rejectLoadMask)try{window.opener.rejectLoadMask = new window.opener.tracywindyLoadMask(window.opener.document.body,'');}catch(e){}" +
		   		"if(!window.opener.rejectWindowDiv ){" +
		   		"window.opener.rejectWindowDiv  = window.opener.document.createElement('div');" +
		   		"window.opener.rejectWindowDiv.id='id_rejectWindowDiv';" +
		   		"window.opener.rejectWindowDiv.style.position='absolute';" +
		   		"window.opener.rejectWindowDiv.style.top='20%';" +
		   		"window.opener.rejectWindowDiv.style.left='40%';" +
		   		"window.opener.rejectWindowDiv.style.lineHeight='40px';" +
		   		"window.opener.rejectWindowDiv.style.border='1px solid #DDD';" +
		   		"window.opener.rejectWindowDiv.style.width='200px';" +
		   		"window.opener.rejectWindowDiv.style.padding='10px';" +
		   		"window.opener.rejectWindowDiv.style.height='100px';" +
		   		"window.opener.rejectWindowDiv.style.background='#FFFFFF';" +
		   		"window.opener.rejectWindowDiv.style.zIndex=10000000;" +
		   		"window.opener.rejectWindowDiv.style.overflow='auto';" +
		   		"window.opener.document.body.appendChild(window.opener.rejectWindowDiv );" +
		   		"}" +*/
		   		"if(window.opener.rejectLoadMask)window.opener.rejectLoadMask.show();" +
		   		/*"if(window.opener.rejectWindowDiv){window.opener.rejectWindowDiv.style.display='block';" +
		   		"window.opener.rejectWindowDiv.innerHTML=('已经发起的冲突流程：<a href=\"javascript:void(0);\" class=\"mini-button\"  onclick=\"this.parentNode.style.display=&quot;none&quot;;if(window.rejectLoadMask)window.rejectLoadMask.hide();\">关闭</a>" +
		   		"<div style=\"color:red;padding-top:4px;text-indent:20px;\">{0}</div>');"*/
		   		"window.opener.mini.alert('<div style=\"color:red;\">{0}</div>')"+
		   		";window.opener=null;window.open('','_self');window.close();</script>";
	   String formPath = "";	   
	   try{
	     formPath = this.jbpmService.startDeployedProcess(request, processDefinitionId, model);
	   }catch(BusinessException be)
	   {
		   formPath = ""+be.getMessage();
		   this.ajaxReturn(response, reuturnInfo.replace("{0}", formPath));
	       return null;
	   }
	   if(StringUtil.nullToString(formPath).startsWith("@@_@@WORKFLOWREJECTED:")){
		   this.ajaxReturn(response, reuturnInfo.replace("{0}", formPath.substring("@@_@@WORKFLOWREJECTED:".length())));
	       return null;
	   }
	   this.setAllKeyToRequestAttributeByMap(request, model);
	   model.put("requestFormPath", formPath);
	   String returnFormPath = "solutions/workflow/jbpm-core/commonWorkflow.jsp";
	   this.setAllKeyToRequestAttributeByMap(request, model);
	   return returnFormPath;
   }
   @RequestMapping(value="/requestProcessTaskForm.action")
   public String requestProcessTaskForm(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);
	   String currentTaskId = modelData.get("currentTaskId");
	   
	   String formPath = "";
		try
		{
			formPath = this.jbpmService.updateRequestProcessTaskForm(request, currentTaskId, modelData);
		}catch(TenwaJbpmException jbpmE){
			String ajaxCallBackScript = "<script type='text/javascript'>alert('"+jbpmE.getMessage()+"');window.close();</script>";
			this.ajaxReturn(response, ajaxCallBackScript);
			return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			String ajaxCallBackScript = "<script type='text/javascript'>alert('该任务已被处理！');window.close();</script>";
			this.ajaxReturn(response, ajaxCallBackScript);
			return null;
		}
	   modelData.put("requestFormPath", formPath);
	   String returnFormPath = "solutions/workflow/jbpm-core/commonWorkflow.jsp";
	   this.setAllKeyToRequestAttributeByMap(request, modelData);
	   return returnFormPath;
   }
   @RequestMapping(value="/preivewProcessTaskForm.action")
   public String preivewProcessTaskForm(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);
	   modelData.put("isViewHistoryTask","true");
	   modelData.put("isCompletedTask","true");
	   modelData.put("isPreivewTask","true");
	   String currentTaskId = modelData.get("currentTaskId");
	   
	   String formPath = "";
	   try
	   {
		   formPath = this.jbpmService.updateRequestProcessTaskForm(request, currentTaskId, modelData);
	   }catch(Exception e)
	   {
		   e.printStackTrace();
		   String ajaxCallBackScript = "<script type='text/javascript'>alert('该任务已被处理！');window.close();</script>";
		   this.ajaxReturn(response, ajaxCallBackScript);
		   return null;
	   }
	   modelData.put("requestFormPath", formPath);
	   String returnFormPath = "solutions/workflow/jbpm-core/commonWorkflowPrint.jsp";
	   this.setAllKeyToRequestAttributeByMap(request, modelData);
	   return returnFormPath;
   }
   @RequestMapping(value="/viewHistoryProcessForm.action")
   public String viewHistoryProcessForm(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);
	   modelData.put("isViewHistoryTask","true");
	   modelData.put("isCompletedTask","true");
	   String currentTaskId = modelData.get("currentTaskId");
	   
	   String formPath = "";
	   try
	   {
		   formPath = this.jbpmService.updateRequestProcessTaskForm(request, currentTaskId, modelData);
	   }catch(TenwaJbpmException jbpmE){
			String ajaxCallBackScript = "<script type='text/javascript'>alert('"+jbpmE.getMessage()+"');window.close();</script>";
			this.ajaxReturn(response, ajaxCallBackScript);
			return null;
		}
	   catch(Exception e)
	   {
		   e.printStackTrace();
		   String ajaxCallBackScript = "<script type='text/javascript'>alert('该任务已被处理！');window.close();</script>";
		   this.ajaxReturn(response, ajaxCallBackScript);
		   return null;
	   }
	   modelData.put("requestFormPath", formPath);
	   String returnFormPath = "solutions/workflow/jbpm-core/commonWorkflow.jsp";
	   this.setAllKeyToRequestAttributeByMap(request, modelData);
	   return returnFormPath;
   }
   @RequestMapping(value="/submitProcessedForm.action")
   public String submitProcessedForm(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	    Map<String,String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);
	    String currentTaskId = modelData.get("currentTaskId");
		String returnResult = "";
		String ajaxCallBackScript = "";
		String processedAdvise = modelData.get("processedAdvise");
		if(processedAdvise != null && processedAdvise.length() > 1000){
			ajaxCallBackScript = "<script type='text/javascript' defer='defer'>window.top.alert('审批意见超过规定长度，请检查后操作！！');if(window.top.loadMask)window.top.loadMask.hide();window.top.mini.unmask(document.body);window.top.jQuery('#id_loadMaskContainer').hide();</script>";
			this.ajaxReturn(response, ajaxCallBackScript);
			return null;
		}
		try
		{
			returnResult = this.jbpmService.submitProcessedTaskForm(request,currentTaskId, modelData);
			ajaxCallBackScript = "<script type='text/javascript' defer='defer'>window.top.ajaxCallBack('"+returnResult+"',false);if(window.top.loadMask)window.top.loadMask.hide();window.top.jQuery('#id_loadMaskContainer').hide();</script>";
			if("true".equalsIgnoreCase(modelData.get("assignmentIsSilentModel"))){
				ajaxCallBackScript = "<script type='text/javascript' defer='defer'>window.top.ajaxCallBack('"+returnResult+"',true);if(window.top.loadMask)window.top.loadMask.hide();window.top.jQuery('#id_loadMaskContainer').hide();</script>";
			}
			this.ajaxReturn(response, ajaxCallBackScript);
			return null;
		}	
		catch(BusinessException be)
		{
			ajaxCallBackScript = be.getMessage();
		}
		catch(JbpmException je)
		{
			ajaxCallBackScript = je.getMessage();
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			ajaxCallBackScript = e.getMessage();
			ajaxCallBackScript = "服务器正忙，请与管理员联系！！！";
		}
		ajaxCallBackScript = "<script type='text/javascript' defer='defer'>window.top.alert('"+ajaxCallBackScript+"');if(window.top.loadMask)window.top.loadMask.hide();window.top.mini.unmask(document.body);window.top.jQuery('#id_loadMaskContainer').hide();</script>";
		this.ajaxReturn(response, ajaxCallBackScript);
		return null;
   }
   @RequestMapping(value="/removeReadPersonToTask.action")
   public String removeReadPersonToTask(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String readedAdvise =  modelData.get("processedAdvise");
	   String jbpmWorkflowHistoryInfoId =  modelData.get("jbpmWorkflowHistoryInfoId");
	   this.jbpmService.removeReadPersonToTask(jbpmWorkflowHistoryInfoId, readedAdvise);
	   return null;
   }
   @RequestMapping(value="/removeSignaturePersonToTask.action")
   public String removeSignaturePersonToTask(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String signaturedAdvise =  modelData.get("processedAdvise");
	   String jbpmWorkflowHistoryInfoId =  modelData.get("jbpmWorkflowHistoryInfoId");
	   this.jbpmService.removeSignaturePersonToTask(jbpmWorkflowHistoryInfoId, signaturedAdvise);
	   return null;
   }
   @RequestMapping(value="/removeProcessInstance.action")
   public String removeProcessInstance(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   
	   String processInstanceDBIDs = modelData.get("processInstanceDBID");
	   for(String processInstanceDBID : processInstanceDBIDs.split(",")){
		   this.jbpmService.removeProcessInstance(request, processInstanceDBID);
	   }
	   return null;
   }
   @RequestMapping(value="/removeBatchReadPersonToTask.action")
   public String removeBatchReadPersonToTask(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String readedAdvise =  modelData.get("processedAdvise");
	   String jbpmWorkflowHistoryInfoId =  modelData.get("jbpmWorkflowHistoryInfoId");
	   System.out.println(jbpmWorkflowHistoryInfoId);
	   String[]jbpmWorkflowHistoryInfoIds=jbpmWorkflowHistoryInfoId.split(",");
	   for(int i=0;i<jbpmWorkflowHistoryInfoIds.length;i++){
	      this.jbpmService.removeReadPersonToTaskByFlowUnid(jbpmWorkflowHistoryInfoIds[i], readedAdvise);
	   }
	   return null;
   }
   
   @RequestMapping(value="/getAuthDesignedWorkflowList.action")
   public String getAuthDesignedWorkflowList(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	  // Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   //request.setAttribute("authDesignedWorkflowList", this.jbpmService.getAuthDesignedWorkflowList(modelData,false));
	   request.setAttribute("authDesignedWorkflowList", this.jbpmService.findEntities(JbpmWorkflowDesigner.class));
	   return "solutions/workflow/jbpm-core/authDesignedWorkflowRequest.jsp";
   }
   @RequestMapping(value="/getAuthViewDesignedWorkflowList.action")
   public String getViewAuthDesignedWorkflowList(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   request.setAttribute("authViewDesignedWorkflowList", this.jbpmService.getAuthDesignedWorkflowList(modelData,true));
	   return "solutions/workflow/jbpm-core/authViewDesignedWorkflowRequest.jsp";
   }
   
   @RequestMapping(value="/getAuthWorkflowJson.action")
   public String getAuthDesignedWorkflowJson(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   request.setAttribute("authDesignedWorkflowList", this.jbpmService.getAuthDesignedWorkflowList(modelData,false));
	   return "solutions/workflow/jbpm-core/authWorkflowJson.jsp";
   }
   @RequestMapping(value="/updateWorkflowConfigSynchronized.action")
   public String updateWorkflowConfigSynchronized(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Locale locale = WebUtil.getSessionLocale();
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String type = modelData.get("type");
	   String host = modelData.get("host");
	   String port = modelData.get("port");
	   String dbname = modelData.get("dbname");
	   String user = modelData.get("user");
	   String password = modelData.get("password");
	   String updateWorkflowIds = modelData.get("updateWorkflowIds");
	   this.jbpmService.updateWorkflowConfigSynchronized(type, host, port, dbname, user, password, updateWorkflowIds,locale);
	   return null;
   }
   @RequestMapping(value="/updatePendingUserToOther.action")
   public String updatePendingUserToOther(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);
	   int changePlanUserFlag               = Integer.parseInt(modelData.get("changeplanuserflag"));
	   String jbpmWorkflowHistoryInfoUserId = modelData.get("actorid");
	   String taskType 						= modelData.get("tasktype");
	   String planUserId                    = modelData.get("planuserid");
	   String changeToUserId                = modelData.get("changetouserid");
	   String returnInfo = this.jbpmService.updatePendingUserToOther(changePlanUserFlag,taskType, jbpmWorkflowHistoryInfoUserId, planUserId, changeToUserId);
	   String ajaxCallBackScript = "<script type='text/javascript'>window.parent.submitCallBack('"+returnInfo+"')</script>";
	   this.ajaxReturn(response, ajaxCallBackScript);
	   return null;
   }
   @RequestMapping(value="/getDeployedWorkflowsTreeData.action")
   public String getDeployedWorkflowsTreeData(HttpServletRequest request,HttpServletResponse response) throws Exception
   {
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String init  = modelData.get("init");
	   JSONArray jsonArr = new JSONArray();
	   if(null != init){
		   String hsql = " select distinct jt from JbpmWorkflowDesigner jd left join jd.type jt order by jt.position";
		   List<DictionaryData>  workflowTypes = this.jbpmService.findResultsByHSQL(hsql);
		   for(DictionaryData dictionaryData : workflowTypes){
			   JSONObject jsonObj = dictionaryData.getJsonObjectDictionaryData(dictionaryData);
			   jsonArr.put(jsonObj);
		   }
	   }else{
		   String typeId  = modelData.get("typeId");
		   DictionaryData worflowType = (DictionaryData)this.jbpmService.findEntityByID(DictionaryData.class, typeId);
		   jsonArr = worflowType.getJbpmWorkflowDesignersJsonArray();
	   }
	   this.output(response, jsonArr.toString());
	   return null;
   }
   ///************同步流程**************/////
	@RequestMapping(value="/getSynchronizedWorkflowDesignersTreeData.action")
	public String getSynchronizedWorkflowDesignersTreeData(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
	   Map<String,String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
	   String type = modelData.get("type");
	   String host = modelData.get("host");
	   String port = modelData.get("port");
	   String dbname = modelData.get("dbname");
	   String user = modelData.get("user");
	   String password = modelData.get("password");
	   JdbcModel jdbcModel = new JdbcModel(type,host,port,dbname,user,password);
	   JdbcTemplate fromJdbcTemplate = jdbcModel.getJdbcTemplate();
	   final JSONArray workflowDesignerJsonArray = new JSONArray();
	   String sql = "select t_jbpm_designer_transfer.id_,display_name_,version_ from t_jbpm_designer_transfer left join t_dicts_datas on t_dicts_datas.id_ = t_jbpm_designer_transfer.type_"
	   		+ " where 1 = 1 ";
	   String selectedUserIds = modelData.get("selectedUserIds");
	   String queryText       = modelData.get("queryText");
	   //List<String> params = new ArrayList<String>();
	   //List<Object> values = new ArrayList<Object>();
	   if(null != selectedUserIds){
			//params.add("ids");
			String ids = StringUtil.toSqlJoinString(selectedUserIds,",");
			sql+=" AND t_jbpm_designer_transfer.id_ not in("+ids+") ";
		}
	   if(null != queryText){
			String whereCondition = " AND ( UPPER(workflow_name_) like '%"+queryText.toUpperCase()+"%'  "
					+ " OR  UPPER(display_name_) like '%"+queryText.toUpperCase()+"%' ) ";
			sql+=whereCondition;
	   }
       for(Map m : fromJdbcTemplate.queryForList(sql)){
    	   JSONObject obj = new JSONObject();
    	   obj.put("id",m.get("id_").toString());
    	   obj.put("text", m.get("display_name_").toString()+"-"+m.get("version_").toString());
    	   JSONObject attributesJsonObj = new JSONObject();
   		   attributesJsonObj.put("type","synchronizedWorkflowDesigners");
   		   obj.put("attributes", attributesJsonObj);
    	   workflowDesignerJsonArray.put(obj);
       }
	   this.output(response, workflowDesignerJsonArray.toString());
	   return null;
	}
	@RequestMapping(value="/getSynchronizedWorkflowDesignersSelectedTreeData.action")
	public String getSynchronizedWorkflowDesignersSelectedTreeData(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Map<String, String> modelData = QueryUtil.getRequestParameterMapByAjax(request);
		String selectedUserIds = modelData.get("selectedUserIds");
		if(null == selectedUserIds){
			this.output(response, "[]");
			return null;
		}
		String type = modelData.get("type");
		String host = modelData.get("host");
		String port = modelData.get("port");
		String dbname = modelData.get("dbname");
		String user = modelData.get("user");
		String password = modelData.get("password");
		JdbcModel jdbcModel = new JdbcModel(type,host,port,dbname,user,password);
		JdbcTemplate fromJdbcTemplate = jdbcModel.getJdbcTemplate();
		final JSONArray workflowDesignerJsonArray = new JSONArray();
		String sql = "select t_jbpm_designer_transfer.id_,display_name_,version_ from t_jbpm_designer_transfer left join t_dicts_datas on t_dicts_datas.id_ = t_jbpm_designer_transfer.type_"
				+ " where 1 = 1 ";
		if(null != selectedUserIds){
				//params.add("ids");
				String ids = StringUtil.toSqlJoinString(selectedUserIds,",");
				sql+=" AND t_jbpm_designer_transfer.id_ in("+ids+") ";
		}
	    for(Map m : fromJdbcTemplate.queryForList(sql)){
	    	   JSONObject obj = new JSONObject();
	    	   obj.put("id",m.get("id_").toString());
	    	   obj.put("text", m.get("display_name_").toString()+"-"+m.get("version_").toString());
	    	   JSONObject attributesJsonObj = new JSONObject();
	   		   attributesJsonObj.put("type","synchronizedWorkflowDesigners");
	   		   obj.put("attributes", attributesJsonObj);
	    	   workflowDesignerJsonArray.put(obj);
	    }
		this.output(response, workflowDesignerJsonArray.toString());
		return null;
	}
	
	@RequestMapping(value="/getTwoFlowDifDate.action")
	   public String getTwoFlowDifDate(HttpServletRequest request,HttpServletResponse response) throws Exception
	   {
		   Map<String,String> modelData = QueryUtil.getRequestParameterMapNoAjax(request);
		   String fromflowid;
		   String toflowid;
		   fromflowid=modelData.get("fromflowid");
		   toflowid=modelData.get("toflowid");
		   Map<String,String>fromMap=JBPMUtil.getTaskHistoryDataById(this.jbpmService.getBussinessDao().getHibernateTemplate(), fromflowid);
		   Map<String,String>toMap=JBPMUtil.getTaskHistoryDataById(this.jbpmService.getBussinessDao().getHibernateTemplate(), toflowid);
		   String ajaxCallBackScript = "<script type='text/javascript'>window.parent.setFlowDifInputRed("+JBPMUtil.getMapDiff(fromMap, toMap)+");</script>";
		   this.ajaxReturn(response, ajaxCallBackScript);
		   return null;
	   }
	
	 @RequestMapping(value = "/importWorkflowConfig.action")
		@ResponseBody
		public String importWorkflowConfig(MultipartHttpServletRequest request, HttpServletResponse response) {
			Locale locale = WebUtil.getSessionLocale();
			for (String fileName : request.getMultiFileMap().keySet()) {
				MultipartFile attachment = request.getFile(fileName);
				if (attachment != null) {
					try {
						InputStream ins = attachment.getInputStream();
						ObjectInputStream ois = new ObjectInputStream(ins);
						WorkflowConfigData config = (WorkflowConfigData) ois.readObject();
						if (config != null) {
							this.jbpmService.updateWorkflowConfig(config.getWorkflowName(),
									Integer.parseInt(config.getVersion()), config.getWorkflowDefine(),
									config.getActivitiDefine(), locale);
						}
					} catch (IOException e) {
						log.error(e.getMessage(), e);
					} catch (ClassNotFoundException e) {
						log.error(e.getMessage(), e);
					} catch (NumberFormatException e) {
						log.error(e.getMessage(), e);
					} catch (Exception e) {
						log.error(e.getMessage(), e);
					}

				}
			}
			return "SUCCESS";
		}
	 
	 @RequestMapping(value = "/exportWorkflowConfig.action")
		public void exportWorkflowConfig(HttpServletRequest request, HttpServletResponse response) throws Exception {
			List<WorkflowConfigData> configs = this.jbpmService.exportWorkflowConfigData();
			String path = this.getClass().getResource("/").getFile();
			path = FileUtils.getTempDirectoryPath() + File.separator + UUID.randomUUID().toString().replace("-", "_");
			File pathFile = new File(path);
			if (pathFile.exists()) {
				FileUtils.forceDelete(pathFile);
			}
			if (!pathFile.exists()) {
				FileUtils.forceMkdir(pathFile);
			}

			for (WorkflowConfigData config : configs) {
				File f = new File(pathFile.getAbsolutePath() + File.separator + config.getWorkflowName() + ".dat");
				ObjectOutputStream oss = null;
				try {
					oss = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
					oss.writeObject(config);
				} catch (Exception e) {
					log.debug("", e);
				} finally {
					if (oss != null) {
						oss.flush();
						oss.close();
					}
					oss = null;
				}
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
			String zipFile = FileUtils.getTempDirectoryPath() + File.separator + "workflow_"
					+ sdf.format(Calendar.getInstance().getTime()) + ".zip";
			log.debug(zipFile);
			ZipOutputStream zos = null;
			try {
				zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
				String[] workflows = pathFile.list();
				for (String workflow : workflows) {
					File w = new File(pathFile.getAbsolutePath() + File.separator + workflow);
					if (w.isFile()) {
						InputStream is = null;
						try {
							is = new BufferedInputStream(new FileInputStream(w));
							zos.putNextEntry(new ZipEntry(w.getName()));
							byte[] zdata = new byte[1024];
							int zc = 0;
							while ((zc = is.read(zdata, 0, 1024)) != -1) {
								zos.write(zdata, 0, zc);
							}
							zos.closeEntry();
						} catch (Exception e1) {
							log.error("", e1);
						} finally {
							if (is != null) {
								is.close();
							}
							is = null;
						}
					}
				}
			} catch (Exception e) {

			} finally {
				if (zos != null) {
					zos.flush();
					zos.close();
				}
				zos = null;
			}

			File workflowZip = new File(zipFile);
			if (workflowZip.exists()) {
				InputStream is = null;
				try {
					is = new BufferedInputStream(new FileInputStream(workflowZip));
					response.reset();
					response.setContentType("application/octet-stream");
					response.addHeader(
							"Content-Disposition",
							"attachment;filename="
									+ getFilenameAssociateBrowser(
											StringUtils.defaultString(request.getParameter("browser")),
											workflowZip.getName()));
					byte[] data = new byte[1024];
					int c;
					while ((c = is.read(data, 0, 1024)) != -1) {
						response.getOutputStream().write(data, 0, c);
					}
				} catch (Exception e) {
					log.error("", e);
				} finally {
					if (is != null) {
						is.close();
					}
					is = null;
					FileUtils.forceDelete(workflowZip);
					FileUtils.forceDelete(pathFile);
				}
			}

		}
	 
	 
	 private String getFilenameAssociateBrowser(String brower, String fileName) {
			try {
				if ("firefox".equalsIgnoreCase(brower)) {
					fileName = new String(fileName.getBytes("GB2312"), "ISO-8859-1");
				} else if ("chrome".equalsIgnoreCase(brower)) {
					fileName = URLEncoder.encode(fileName, "UTF-8");
				} else if ("safari".equalsIgnoreCase(brower)) {
					fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
				} else {
					fileName = URLEncoder.encode(fileName, "UTF-8");
				}
			} catch (UnsupportedEncodingException e) {
				log.error("", e);
			}
			return fileName;
		}
	 
	 
	 @RequestMapping(value = "/exportWorkflowConfigUnique.action")
	  	public void exportWorkflowConfigUnique(HttpServletRequest request, HttpServletResponse response) throws Exception {
		   	String id = request.getParameter("id");
	  		WorkflowConfigData config = this.jbpmService.exportWorkflowConfigDataUnique(id);
	  		String path = this.getClass().getResource("/").getFile();
	  		path = FileUtils.getTempDirectoryPath() + File.separator + UUID.randomUUID().toString().replace("-", "_");
	  		File pathFile = new File(path);
	  		if (pathFile.exists()) {
	  			FileUtils.forceDelete(pathFile);
	  		}
	  		if (!pathFile.exists()) {
	  			FileUtils.forceMkdir(pathFile);
	  		}

			File f = new File(pathFile.getAbsolutePath() + File.separator + config.getWorkflowName() + ".dat");
			ObjectOutputStream oss = null;
			try {
				oss = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
				oss.writeObject(config);
			} catch (Exception e) {
				log.debug("", e);
			} finally {
				if (oss != null) {
					oss.flush();
					oss.close();
				}
				oss = null;
			}

	  		
	  		if (f.exists()) {
	  			InputStream is = null;
	  			try {
	  				is = new BufferedInputStream(new FileInputStream(f));
	  				response.reset();
	  				response.setContentType("application/octet-stream");
	  				response.addHeader(
	  						"Content-Disposition",
	  						"attachment;filename="
	  								+ getFilenameAssociateBrowser(
	  										StringUtils.defaultString(request.getParameter("browser")),
	  										f.getName()));
	  				byte[] data = new byte[1024];
	  				int c;
	  				while ((c = is.read(data, 0, 1024)) != -1) {
	  					response.getOutputStream().write(data, 0, c);
	  				}
	  			} catch (Exception e) {
	  				log.error("", e);
	  			} finally {
	  				if (is != null) {
	  					is.close();
	  				}
	  				is = null;
	  				FileUtils.forceDelete(pathFile);
	  			}
	  		}

	  	}
	 
	 @RequestMapping(value = "/getPrint.acl")
		public String getPrint(HttpServletRequest request, HttpServletResponse response) throws Exception {
			 String jhiCompletedTaskImplId = request.getParameter("jhiCompletedTaskImplId");
			 String historyProcessInstanceImplDbid = request.getParameter("DocNo");
			try{
				request.setAttribute("processedHistoryLogInfoList",this.jbpmService.getProcessedHistoryLogInfoList(historyProcessInstanceImplDbid,jhiCompletedTaskImplId) );
			}catch(Exception e){
				e.printStackTrace();
			}
			JBPMWorkflowHistoryInfo currentWorkFlowInfo = this.jbpmService.findEntityByID(JBPMWorkflowHistoryInfo.class, jhiCompletedTaskImplId);
			JbpmWorkflowDesigner designer = currentWorkFlowInfo.getDeploymentImpl().getJbpmWorkflowDesigner();
			
			request.setAttribute("workFlowName", designer.getDisplayName());
			request.setAttribute("state_", currentWorkFlowInfo.getHistoryProcessInstanceImpl().getState());
			request.setAttribute("key1", currentWorkFlowInfo.getKeyOne());		
			request.setAttribute("key2", currentWorkFlowInfo.getKeyTwo());
			request.setAttribute("key3", currentWorkFlowInfo.getKeyThree());
			return "solutions/workflow/jbpm-core/advise_list_table.jsp";
		}
}
