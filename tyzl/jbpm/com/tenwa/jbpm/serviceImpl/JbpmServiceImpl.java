
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.serviceImpl
 * 文件名：         JbpmServiceImpl.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-10-23-下午04:45:07
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.jbpm.serviceImpl;
  
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.impl.SessionImpl;
import org.jbpm.api.Deployment;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.model.Activity;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.pvm.internal.env.EnvironmentFactory;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.jbpm.pvm.internal.history.model.HistoryProcessInstanceImpl;
import org.jbpm.pvm.internal.history.model.HistoryTaskInstanceImpl;
import org.jbpm.pvm.internal.lob.Lob;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.jbpm.pvm.internal.repository.DeploymentImpl;
import org.jbpm.pvm.internal.repository.RepositoryCache;
import org.jbpm.pvm.internal.stream.StreamInput;
import org.jbpm.pvm.internal.stream.StringStreamInput;
import org.jbpm.pvm.internal.task.TaskImpl;
import org.jbpm.pvm.internal.util.IoUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.tenwa.business.dao.BaseDao;
import com.tenwa.business.entity.AttachmentFileUploadInfo;
import com.tenwa.business.entity.AttachmentFileUploadInfoDetail;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.entity.notice.EmailNotice;
import com.tenwa.business.entity.notice.SmsNotice;
import com.tenwa.business.serviceImpl.AbstractBaseServiceImpl;
import com.tenwa.email.SendEmailUtil;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.dao.JbpmDao;
import com.tenwa.jbpm.entity.ActivityDetail;
import com.tenwa.jbpm.entity.GrantDelegateUser;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfoUser;
import com.tenwa.jbpm.entity.JBPMWorkflowReadUser;
import com.tenwa.jbpm.entity.JBPMWorkflowSignatureUser;
import com.tenwa.jbpm.entity.JbpmWorkFlowAppPush;
import com.tenwa.jbpm.entity.JbpmWorkflowDesigner;
import com.tenwa.jbpm.entity.UserWorkflowStart;
import com.tenwa.jbpm.entity.UserWorkflowView;
import com.tenwa.jbpm.entity.WorkflowDesignerReject;
import com.tenwa.jbpm.entity.WorkflowStartDepartment;
import com.tenwa.jbpm.entity.WorkflowStartDepartmentRole;
import com.tenwa.jbpm.entity.WorkflowStartGroup;
import com.tenwa.jbpm.entity.WorkflowViewDepartment;
import com.tenwa.jbpm.entity.WorkflowViewDepartmentRole;
import com.tenwa.jbpm.entity.WorkflowViewGroup;
import com.tenwa.jbpm.exception.JbpmException;
import com.tenwa.jbpm.model.ActivityTaskUsers;
import com.tenwa.jbpm.model.ActivityTaskUsersComparator;
import com.tenwa.jbpm.model.ConditionRouteTypeEnum;
import com.tenwa.jbpm.model.DesignerActivity;
import com.tenwa.jbpm.model.JdbcModel;
import com.tenwa.jbpm.model.MessageTypeEnum;
import com.tenwa.jbpm.model.RouteInfo;
import com.tenwa.jbpm.model.SubmitTypeEnum;
import com.tenwa.jbpm.model.TaskUsers;
import com.tenwa.jbpm.model.WorkflowConfigData;
import com.tenwa.jbpm.model.WorkflowSubmitTextEnum;
import com.tenwa.jbpm.service.JbpmService;
import com.tenwa.jbpm.utils.DesignerTransferUtil;
import com.tenwa.jbpm.utils.DraftUUIDUtil;
import com.tenwa.jbpm.utils.JBPMUtil;
import com.tenwa.jbpm.workflowRejectCondtion.RejectCondition;
import com.tenwa.jbpm.workflowRejectCondtion.RejectInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.annotation.WorkflowRejectCondition;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.ELUtil;
import com.tenwa.kernal.utils.FileUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.UUIDUtil;
import com.tenwa.kernal.utils.WebUtil;

import edu.emory.mathcs.backport.java.util.Arrays;


 /**
 * 类名称：     JbpmServiceImpl
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-10-23 下午04:45:07
 * 修改备注：
 * @version 1.0.0
 **/
@Service(value="jbpmService")
public class JbpmServiceImpl extends AbstractBaseServiceImpl  implements JbpmService 
{
	/*
	read 传阅
	signature 会签
	assignmentPending 工作指派未执行
	assignmentCompleted 工作指派已完成
	delegate 委托任务
	pending 待办
	*/
	@Resource(name="jbpmDao")
	private JbpmDao jbpmDao;
	//private static Log log = LogFactory.getLog(JbpmServiceImpl.class);
	private final String currentJbpmWorkflowDesignerId = "currentJbpmWorkflowDesignerId";
	private final String currentJbpmDepolymentId       = "currentJbpmDepolymentId";
	private final String currentActivityDetailId       = "currentActivityDetailId";
	private final String conditionRouteType       = "conditionRouteType";
	@Override
	public  JSONArray  getWorkflowRejectBeanName(boolean isFilter,String sourceWorkflowName,String rejectWorkflowName) throws Exception
	{
			JSONArray jsonArray   = new JSONArray();
			Set<Class<?>> classes = FileUtil.getClasses("com.tenwa.business.workflowRejectCondtion");
			for(Class<?>  clazz :classes)
			{
				WorkflowRejectCondition workflowAction = clazz.getAnnotation(WorkflowRejectCondition.class);
				if(null != workflowAction){
					String sourceWorkflowName_ = StringUtil.nullToString(workflowAction.sourceWorkflowName()).trim();
					String rejectWorkflowName_ = StringUtil.nullToString(workflowAction.rejectWorkflowName()).trim();
					String workflowActionName = workflowAction.name();
					JSONObject  json = new JSONObject();
					Assert.assertNotNull(workflowActionName);
					json.put("sourceWorkflowName", sourceWorkflowName_);
					json.put("rejectWorkflowName", rejectWorkflowName_);
					json.put("value", workflowActionName);
					String workflowActionDescription = workflowAction.description();
					Assert.assertNotNull(workflowActionDescription);
					json.put("name", workflowActionName+"("+workflowActionDescription+")");
					boolean isPut = true;
					if(isFilter){
						isPut = false;
						if(sourceWorkflowName.equals(sourceWorkflowName_) && rejectWorkflowName.equals(rejectWorkflowName_))
						{
							isPut = true;
						}
					}
					if(isPut){
						jsonArray.put(json);
					}
				}
			}
		return jsonArray;
	}
	@Override
	public  String  getWorkflowActions(String paramWorkflowName) throws Exception
	{
		    String workflowActions = "[]";
			JSONArray jsonArray   = new JSONArray();
			Set<Class<?>> classes = FileUtil.getClasses("com.tenwa.leasing.action");
			for(Class<?>  clazz :classes)
			{
				WorkflowAction workflowAction = clazz.getAnnotation(WorkflowAction.class);
				if(null != workflowAction){
					String workflowName = workflowAction.workflowName();
					if(StringUtils.isBlank(workflowName) || paramWorkflowName.equalsIgnoreCase(workflowName))
					{
						JSONObject  json = new JSONObject();
						String workflowActionName = workflowAction.name();
						Assert.assertNotNull(workflowActionName);
						json.put("value", workflowActionName);
						String workflowActionDescription = workflowAction.description();
						Assert.assertNotNull(workflowActionDescription);
						json.put("name", workflowActionName+"("+workflowActionDescription+")");
						jsonArray.put(json);
					}
				}
			}
			workflowActions = jsonArray.toString();
		return workflowActions;
	}
	@Override 
	public void delegateSingleWorkflow(String id,Object deploymentIdOrDeploymentImpl,String delegateUserId,String startDate,String endDate,String grantMethod) throws Exception
	{
		User grantUser    =  (User)SecurityUtil.getPrincipal();
		User delegateUser = (User)this.findEntityByID(User.class, delegateUserId);
		GrantDelegateUser grantDelegateUser = null;
		if(null == id)
		{
			DeploymentImpl deploymentImpl = null;
			if(deploymentIdOrDeploymentImpl instanceof String)
			{
				deploymentImpl = this.findEntityByID(DeploymentImpl.class, deploymentIdOrDeploymentImpl.toString());
			}
			else if(deploymentIdOrDeploymentImpl instanceof DeploymentImpl)
			{
				deploymentImpl = (DeploymentImpl)deploymentIdOrDeploymentImpl;
			}
			grantDelegateUser = new GrantDelegateUser();
			grantDelegateUser.setGrantUser(grantUser);
			grantDelegateUser.setDeploymentImpl((DeploymentImpl)deploymentImpl);
		}
		else
		{
			grantDelegateUser = (GrantDelegateUser)this.findEntityByID(GrantDelegateUser.class, id);
		}
		grantDelegateUser.setDelegateUser(delegateUser);
		grantDelegateUser.setStartDate(startDate);
		grantDelegateUser.setEndDate(endDate);
		grantDelegateUser.setGrantMethod(grantMethod);
		this.saveOrUpdateEntity(grantDelegateUser);
	}
	@Override
	@SuppressWarnings("unchecked")
	public void delegateCheckedWorkflows(String checkedIds,String checkedDeploymentIds,String delegateUserId,String startDate,String endDate,String grantMethod) throws Exception
	{
		List<String> checkedIdsList = (List<String>)StringUtil.getCollectionByString("list", checkedIds, ",");
		List<Long> checkedDeploymentIdsList = (List<Long>)StringUtil.getLongCollectionByString("list", checkedDeploymentIds, ",");
		List<DeploymentImpl> checkedDeploymentImpls = (List<DeploymentImpl>)this.jbpmDao.getHibernateTemplate().findByNamedParam("from DeploymentImpl di where dbid in(:checkedDeploymentIds)", "checkedDeploymentIds", checkedDeploymentIdsList);
	    for(int i=0;i<checkedDeploymentImpls.size();i++)
	    {
	    	DeploymentImpl checkedDeploymentImpl = checkedDeploymentImpls.get(i);
	    	String recordId  = checkedIdsList.get(i);
	    	if(recordId.equalsIgnoreCase("null"))
	    	{
	    		recordId = null;
	    	}
	    	this.delegateSingleWorkflow(recordId, checkedDeploymentImpl, delegateUserId, startDate, endDate, grantMethod);
	    }
	}
	@Override
	public void delegateAllWorkflows(String delegateUserId,String startDate,String endDate,String grantMethod) throws Exception
	{
		this.removeAllDelegatedWorkflows();
		String queryAllWorkflowHsql = "from DeploymentImpl";
		List<Object> deploymentImpls = this.jbpmDao.getSinglObjectListByHSQL(queryAllWorkflowHsql);
		for(Object deploymentImpl : deploymentImpls)
		{
			this.delegateSingleWorkflow(null, deploymentImpl, delegateUserId, startDate, endDate, grantMethod);
		}
	}
	@Override
	public void removeSingleDelegatedWorkflow(String id)  throws Exception
	{
		this.updateByHSQL("delete from GrantDelegateUser gdu where id = ? ", id);
	}
	@Override
	@SuppressWarnings("unchecked")
	public void removeCheckedDelegatedWorkflows(String checkedIds)  throws Exception
	{
		List<String> checkedIdsList = (List<String>)StringUtil.getCollectionByString("list", checkedIds, ",");
		String querySql = "from GrantDelegateUser gdu where id in(:checkedIds)";
		List<GrantDelegateUser> checkedGrantDelegateUsers = (List<GrantDelegateUser>)this.jbpmDao.getHibernateTemplate().findByNamedParam(querySql,"checkedIds",checkedIdsList);
	    this.removeAllEntites(checkedGrantDelegateUsers);
	}
	@Override
	public void removeAllDelegatedWorkflows()  throws Exception
	{
		User	grantUser    =  (User)SecurityUtil.getPrincipal();
		this.updateByHSQL("delete from GrantDelegateUser gdu where gdu.grantUser.id = ? ", grantUser.getId());
	}
	@Override
	public void removeDesignedWorkflow(String designedId) throws Exception
	{
		JbpmWorkflowDesigner jbpmWorkflowDesigner = this.findEntityByID(JbpmWorkflowDesigner.class, designedId);
		Set<WorkflowDesignerReject> sourceWorkflowDesignerRejects = jbpmWorkflowDesigner.getSourceWorkflowDesignerRejects();
		this.removeAllEntites(sourceWorkflowDesignerRejects);
		Set<WorkflowDesignerReject> rejectWorkflowDesignerRejects = jbpmWorkflowDesigner.getRejectWorkflowDesignerRejects();
		this.removeAllEntites(rejectWorkflowDesignerRejects);
		Set<WorkflowStartDepartment> workflowStartDepts = jbpmWorkflowDesigner.getWorkflowStartDepts();
		this.removeAllEntites(workflowStartDepts);
		Set<WorkflowStartDepartmentRole> workflowStartDeptRoles = jbpmWorkflowDesigner.getWorkflowStartDeptRoles();
		this.removeAllEntites(workflowStartDeptRoles);
		Set<WorkflowStartGroup> workflowStartGroups = jbpmWorkflowDesigner.getWorkflowStartGroups();
		this.removeAllEntites(workflowStartGroups);
		Set<UserWorkflowStart> userWorkflowStarts = jbpmWorkflowDesigner.getUserWorkflowStarts();
		this.removeAllEntites(userWorkflowStarts);
		Set<WorkflowViewDepartment> workflowViewDepts = jbpmWorkflowDesigner.getWorkflowViewDepts();
		this.removeAllEntites(workflowViewDepts);
		Set<WorkflowViewDepartmentRole> workflowViewDeptRoles = jbpmWorkflowDesigner.getWorkflowViewDeptRoles();
		this.removeAllEntites(workflowViewDeptRoles);
		Set<WorkflowViewGroup> workflowViewGroups = jbpmWorkflowDesigner.getWorkflowViewGroups();
		this.removeAllEntites(workflowViewGroups);
		Set<UserWorkflowView> userWorkflowViews = jbpmWorkflowDesigner.getUserWorkflowViews();
		this.removeAllEntites(userWorkflowViews);
		
		this.removeEntity(jbpmWorkflowDesigner);
	}
	@Override
	public void updateWorkflowConfigSynchronized(String type,String host, String port, String dbname, String user,String password,String updateWorkflowIds,Locale locale) throws Exception
	{
		JdbcModel jdbcModel = new JdbcModel(type,host,port,dbname,user,password);
		JdbcTemplate fromJdbcTemplate = jdbcModel.getJdbcTemplate();
	   
		String designerSql = "select t_jbpm_designer_transfer.*,jbpm4_deployment.dbid_ from t_jbpm_designer_transfer left join jbpm4_deployment on jbpm4_deployment.jbpm_workflow_designer_id_ = t_jbpm_designer_transfer.id_ ";
	    List aids=new ArrayList();
		if(null != updateWorkflowIds){
			String ids=StringUtil.toSqlJoinString(updateWorkflowIds,",");
			ids=ids.replaceAll("'", "");	
			aids=StringUtil.toSqlJoinList(updateWorkflowIds,",");
			designerSql +=" where t_jbpm_designer_transfer.id_ in("+StringUtil.toSqlJoinStringToIn(ids, ",")+") ";
		}
		List<Map<String,String>> workflowList = fromJdbcTemplate.query(designerSql,aids.toArray(), new RowMapper<Map<String,String>>(){
			@Override
			public Map<String, String> mapRow(ResultSet rs, int index)throws SQLException 
		    {
				
				Map<String,String> rowDataMap = new HashMap<String,String>();
				rowDataMap.put("displayName", rs.getString("DISPLAY_NAME_"));
				rowDataMap.put("type", rs.getString("TYPE_"));
				rowDataMap.put("position", rs.getString("POSITION_"));
				rowDataMap.put("code", rs.getString("CODE_"));
				rowDataMap.put("workflowName", rs.getString("WORKFLOW_NAME_"));
				rowDataMap.put("key",         rs.getString("KEY_"));
				rowDataMap.put("keyOne", rs.getString("KEY_ONE_"));
				rowDataMap.put("keyTwo", rs.getString("KEY_TWO_"));
				rowDataMap.put("keyThree", rs.getString("KEY_THREE_"));
				rowDataMap.put("keyFour", rs.getString("KEY_FOUR_"));
				rowDataMap.put("keyFive", rs.getString("KEY_FIVE_"));
				rowDataMap.put("keySix", rs.getString("KEY_SIX_"));
				rowDataMap.put("keySeven", rs.getString("KEY_SEVEN_"));
				rowDataMap.put("keyEight", rs.getString("KEY_EIGHT_"));
				rowDataMap.put("keyNine", rs.getString("KEY_NINE_"));
				rowDataMap.put("keyTen", rs.getString("KEY_TEN_"));
				rowDataMap.put("version", rs.getString("VERSION_"));
				rowDataMap.put("jpdlVersion", rs.getString("JPDL_VERSION_"));
				rowDataMap.put("description", rs.getString("DESCRIPTION_"));
				rowDataMap.put("workflowDefinition", rs.getString("WORKFLOW_DEFINITION_"));
				rowDataMap.put("createTime", rs.getString("CREATE_TIME_"));
				rowDataMap.put("designerWorkflowJson", rs.getString("DESIGNER_WORKFLOW_JSON_"));
				rowDataMap.put("transferedJpdlXml", rs.getString("TRANSFERED_JPDL_XML_"));
				rowDataMap.put("fromDBDeployId", rs.getString("DBID_"));
				rowDataMap.put("maxDotX", rs.getString("MAX_DOT_X_"));
				rowDataMap.put("maxDotY", rs.getString("MAX_DOT_Y_"));
				return rowDataMap;
			}
		});
		for(Map<String,String> transferMap : workflowList ){
			String workflowName = transferMap.get("workflowName");
			int    version      = Integer.parseInt(transferMap.get("version"));
			String fromDBDeployId = transferMap.get("fromDBDeployId");
			List<Map<String,String>> activitiesDetailDatas = new ArrayList<Map<String,String>>();
			if(null != fromDBDeployId){
				String detailSql    = "select * from t_jbpm_activity_detail where jbpm4_deployment_dbid_ =?";
				activitiesDetailDatas = fromJdbcTemplate.query(detailSql,new String[]{fromDBDeployId}, new RowMapper<Map<String,String>>(){
					@Override
					public Map<String, String> mapRow(ResultSet rs, int index)throws SQLException 
				    {
						Map<String,String> rowDataMap = new HashMap<String,String>();
						rowDataMap.put("activityName", rs.getString("ACTIVITY_NAME_"));
						rowDataMap.put("activityKey", rs.getString("ACTIVITY_KEY_"));
						rowDataMap.put("activityPosition", rs.getString("ACTIVITY_POSITION_"));
						rowDataMap.put("activityType",         rs.getString("ACTIVITY_TYPE_"));
						rowDataMap.put("activityAction", rs.getString("ACTIVITY_ACTION_"));
						rowDataMap.put("attachmentType", rs.getString("ATTACHMENT_TYPE_"));
						rowDataMap.put("attachmentTypeIds", rs.getString("ATTACHMENT_TYPE_IDS_"));
						rowDataMap.put("actorType", rs.getString("ACTOR_TYPE_"));
						rowDataMap.put("actorValue", rs.getString("ACTOR_VALUE_"));
						rowDataMap.put("formPath", rs.getString("FORM_PATH_"));
						rowDataMap.put("formTitle", rs.getString("FORM_TITLE_"));
						rowDataMap.put("operationButtons", rs.getString("OPERATION_BUTTONS_"));
						rowDataMap.put("isNeedAdvise", rs.getString("IS_NEED_ADVISE_"));
						rowDataMap.put("isAutoActivity", rs.getString("IS_AUTO_ACTIVITY_"));
						rowDataMap.put("backActivities", rs.getString("BACK_ACTIVITIES_"));
						rowDataMap.put("dealMethod", rs.getString("DEAL_METHOD_"));
						rowDataMap.put("passedCount", rs.getString("PASSED_COUNT_"));
						rowDataMap.put("autoReadActorType", rs.getString("AUTO_READ_ACTOR_TYPE_"));
						rowDataMap.put("autoReadActorValue", rs.getString("AUTO_READ_ACTOR_VALUE_"));
						rowDataMap.put("autoSignatureActorType", rs.getString("AUTO_SIGNATURE_ACTOR_TYPE_"));
						rowDataMap.put("autoSignatureActorValue", rs.getString("AUTO_SIGNATURE_ACTOR_VALUE_"));
						rowDataMap.put("displayActorValue", rs.getString("ACTOR_VALUE_DISPLAY_"));
						rowDataMap.put("autoReadDisplayActorValue", rs.getString("AUTO_READ_DISPLAY_"));
						rowDataMap.put("autoSignatureDisplayActorValue", rs.getString("AUTO_SIGNATURE_DISPLAY_"));
						rowDataMap.put("isAttachmentChecked", rs.getString("IS_ATTACHMENT_CHECKED_"));
						return rowDataMap;
					}
				});
			}
			this.updateWorkflowConfig(workflowName, version, transferMap, activitiesDetailDatas,locale);
		}
	}
	public void updateWorkflowConfig(String workflowName,int version,Map<String,String> transferMap,List<Map<String,String>> activitiesDetailDatas,Locale locale) throws Exception
	{
		Assert.assertTrue(transferMap.containsKey("fromDBDeployId"));
		String fromDBDeployId = transferMap.get("fromDBDeployId");
		Map<String,Object> propertiesMap = new HashMap<String,Object>();
		propertiesMap.put("workflowDefinition", workflowName+"-"+version);
		List<JbpmWorkflowDesigner> jwd_list = (List<JbpmWorkflowDesigner>)this.jbpmDao.findEntityByProperties(JbpmWorkflowDesigner.class, propertiesMap);
		JbpmWorkflowDesigner jbpmWorkflowDesigner = null;
		DeploymentImpl deploymentImpl = null;
		if(0 == jwd_list.size())//目的数据库中没有设计流程
	    {
			//资金计划变更流程
			jbpmWorkflowDesigner = new JbpmWorkflowDesigner();
			//将最新设计的表动更新到目的数据库
			this.copyAndOverrideExistedValueFromStringMap(transferMap, jbpmWorkflowDesigner, null);
			//在目的数据库生成配置记录
			this.saveEntity(jbpmWorkflowDesigner);
			//在目的数据库上发布配置
			if(null != fromDBDeployId){
				deploymentImpl = this.deployWorkflow(jbpmWorkflowDesigner.getId());
			}
		}
		else //已经设计过流程图
		{
			jbpmWorkflowDesigner = jwd_list.get(0);
			//将最新设计的表动更新到目的数据库
			this.copyAndOverrideExistedValueFromStringMap(transferMap, jbpmWorkflowDesigner, null);
			this.updateEntity(jbpmWorkflowDesigner);
			deploymentImpl = jbpmWorkflowDesigner.getDeploymentImpl();
			if( null == deploymentImpl)//目的数据库没有发布过
			{
				//在目的数据库上部署
				if(null != fromDBDeployId)
				{
					deploymentImpl = this.deployWorkflow(jbpmWorkflowDesigner.getId());
				}
			}
			else //目的数据库发布过，同步目的数据库xml的blob数值
			{
				this.updateDeployedLobBlobValue(deploymentImpl);
			}
		}
		if(null != deploymentImpl){
			//同步最新节点配置到目的数据库上
			this.updateActivitiesDetail(deploymentImpl,jbpmWorkflowDesigner,locale);
			List<ActivityDetail> activitiesDetailList = this.jbpmDao.getActivitiesDetailListByDeployId(deploymentImpl.getId());
			for(ActivityDetail ad : activitiesDetailList){
				
				String destActivityName = ad.getActivityName();
				String destActivityType = ad.getActivityType();
				
				for(Map<String,String> rowDataMap : activitiesDetailDatas)
				{
					String activityName = rowDataMap.get("activityName");
					String activityType = rowDataMap.get("activityType");
					if(destActivityName.equals(activityName) && destActivityType.equals(activityType)){
						this.copyAndOverrideExistedValueFromStringMap(rowDataMap, ad, null);
						this.updateEntity(ad);
						//modify by tracywindy 2013-08-17 20:30
						break;
					}
				}
			}

		}
	}
	//同步最新节点配置
	public void updateActivitiesDetail(DeploymentImpl deploymentImpl,JbpmWorkflowDesigner jbpmWorkflowDesigner,Locale locale) throws Exception
	{
		if(null == jbpmWorkflowDesigner){
			jbpmWorkflowDesigner = deploymentImpl.getJbpmWorkflowDesigner();
		}
		User user = (User)SecurityUtil.getPrincipal();
		String currentTime = DateUtil.getSystemDateTime();
		
		Set<String> currentActivedActivities = new HashSet<String>();
		ExecutionService  executionService = JBPMUtil.getExecutionService();
		List<ProcessInstance> pi_list = executionService.createProcessInstanceQuery().processDefinitionId(jbpmWorkflowDesigner.getWorkflowDefinition()).list();
		for(ProcessInstance pi : pi_list){
			currentActivedActivities.addAll(pi.findActiveActivityNames());
		}
		//同步节点
		List<ActivityDetail> activitiesDetailList = this.jbpmDao.getActivitiesDetailListByDeployId(deploymentImpl.getId());
		List<DesignerActivity> designerTasksList= DesignerTransferUtil.getDesignerActivities(jbpmWorkflowDesigner.getDesignerWorkflowJson());//.getDesignerNameTypeTaskMap(jbpmWorkflowDesigner.getDesignerWorkflowJson());
		List<String> detailListActivityKeys     = new ArrayList<String>();
		for(ActivityDetail ad : activitiesDetailList){
			detailListActivityKeys.add(ad.getActivityKey());
		}
		List<String> designerListActivityKeys     = new ArrayList<String>();
		for(DesignerActivity da : designerTasksList){
			designerListActivityKeys.add(da.getKey());
		}
		for(ActivityDetail ad:activitiesDetailList)
		{
			String taskKey = ad.getActivityKey();//.getActivityName();
			String taskName = ad.getActivityName();//.getActivityName();
			if(!designerListActivityKeys.contains(taskKey))
			{
				if(currentActivedActivities.contains(taskKey)){
					throw new Exception(WebUtil.getMessageByLocale(WorkflowSubmitTextEnum.errorActivityNotRemoved.getText(), null, WorkflowSubmitTextEnum.errorActivityNotRemoved.getText(), locale)+" < "+taskName+" > ");
				}
				ad.setEnabled(new Integer(0));
				ad.setModificator(user);
				ad.setModifyDate(currentTime);
				this.updateEntity(ad);
			}
		}
		
		for(DesignerActivity da : designerTasksList)
		{
			ActivityDetail activityDetail = null;
			String designerActivityKey  = da.getKey();
			String designerActivityName = da.getName();
			Integer designerActivityPostion = da.getPosition();
			boolean isExist = false;//detailListActivityKeys.contains(designerActivityKey);
			boolean isEmptyActivityKey = false;
			
			for(ActivityDetail ad : activitiesDetailList)
			{
				boolean firstBoolean = designerActivityKey.equals(ad.getActivityKey());
				boolean allBoolean   = firstBoolean ? firstBoolean : designerActivityName.equals(ad.getActivityName());
				//if(designerActivityKey.equals(ad.getActivityKey()))
				//if(designerActivityName.equals(ad.getActivityName()))
				if(allBoolean)
				{
					isExist = true;
					activityDetail = ad;
					if((null == ad.getActivityKey())&&(1 == ad.getEnabled())){
						isEmptyActivityKey = true;
					}
					break;
				}
			}
			if(!isExist)
			{
				activityDetail = new ActivityDetail();
				activityDetail.setDeploymentImpl(deploymentImpl);
				activityDetail.setActivityType("task");
				activityDetail.setEnabled(new Integer(1));
				activityDetail.setIsAttachmentChecked(false);
				activityDetail.setCreator(user);
				activityDetail.setCreateDate(currentTime);
			}
			activityDetail.setActivityName(designerActivityName);
			activityDetail.setActivityKey(designerActivityKey);
			activityDetail.setActivityPosition(designerActivityPostion);
			activityDetail.setIsNeedAdviseExclude(false);
			this.jbpmDao.saveOrUpdateEntity(activityDetail);
			if(isEmptyActivityKey){
				List<HistoryProcessInstance> hpi_list = JBPMUtil.getHistoryService().createHistoryProcessInstanceQuery().processDefinitionId(jbpmWorkflowDesigner.getWorkflowDefinition()).list();
				this.updateProcessedAcivitiesNameToKey(pi_list, hpi_list, da);
			}
		}
	}
	public synchronized void updateProcessedAcivitiesNameToKey(List<ProcessInstance> pi_list,List<HistoryProcessInstance> hpi_list,DesignerActivity da) throws Exception{
		String adName = da.getName();
		String adKey = da.getKey();
		if(StringUtil.nullToString(adKey).isEmpty())throw new Exception("未点击节点生成节点KEY");
		for(ProcessInstance pi : pi_list){
			ExecutionImpl ei = (ExecutionImpl)pi;
			String activityName = ei.getActivityName();
			if(adName.equals(activityName)){
				this.updateFlush();
				String sql = "update jbpm4_execution set activityname_ = ? where dbid_ = ?";
				this.jbpmDao.getJdbcTemplate().update(sql, adKey,ei.getDbid());
			}
		}
		for(HistoryProcessInstance hpi : hpi_list){
			HistoryProcessInstanceImpl hpii = (HistoryProcessInstanceImpl)hpi;
			for(JBPMWorkflowHistoryInfo jwhi : hpii.getJbpmWorkflowHistoryInfos()){
				if(null == jwhi.getActivityDetail())continue;
				String activityName = jwhi.getActivityDetail().getActivityName();
				if(adName.equals(activityName)){
					jwhi.setHistoryActivityName(activityName);
					this.jbpmDao.updateEntity(jwhi);
					HistoryTaskInstanceImpl historyTaskInstanceImpl = jwhi.getHistoryTaskInstanceImpl();
					this.updateFlush();
					String sql = "update jbpm4_hist_actinst set activity_name_ = ? where dbid_ = ?";
					this.jbpmDao.getJdbcTemplate().update(sql, adKey,historyTaskInstanceImpl.getDbid());
				}
			}
		}
	}
	//同步目的数据库xml的blob数值
	public synchronized void updateDeployedLobBlobValue(DeploymentImpl deploymentImpl) throws Exception
	{
		JbpmWorkflowDesigner jbpmWorkflowDesigner = deploymentImpl.getJbpmWorkflowDesigner();
		final String deployId = deploymentImpl.getId();
		//同步jpdl.xml
		Map<String, Lob> resources = deploymentImpl.getResources();
		String lobKey  = null;
		StreamInput streamInput = new StringStreamInput(jbpmWorkflowDesigner.getTransferedJpdlXml());
		final InputStream inputStream = streamInput.openStream();
		if(resources.keySet().size()>0){
			lobKey = resources.keySet().iterator().next();
			String prepareSqlTemp = null;
			if("ORACLE".equalsIgnoreCase(ResourceUtil.getDBType())){
				prepareSqlTemp = "update jbpm4_lob set blob_value_ = ? where deployment_= ? and to_char(name_)= ? ";
			}else if("SQLSERVER".equalsIgnoreCase(ResourceUtil.getDBType())){
				prepareSqlTemp = "update jbpm4_lob set blob_value_ = ? where deployment_= ? and convert(nvarchar(255),name_)= ? ";
			}
			this.jbpmDao.updateFlush();
			final String prepareSql = prepareSqlTemp;
			final String  key = lobKey;
			this.jbpmDao.getJdbcTemplate().execute(new PreparedStatementCreator(){
				@Override
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(prepareSql);
					ps.setLong(2, Long.parseLong(deployId));
					ps.setString(3,key);
					return ps;
				}
			}, new  PreparedStatementCallback<Integer>(){
				@Override
				public Integer doInPreparedStatement(PreparedStatement ps)
						throws SQLException, DataAccessException {
					try {
						ps.setBinaryStream(1, inputStream, inputStream.available());
					} catch (IOException e) {
						e.printStackTrace();
						throw new SQLException(StringUtil.traceExceptionMessage(this, e));
					}
					return ps.executeUpdate();
				}
			});
		}
		else
		{
			lobKey = UUIDUtil.getUUID()+".jpdl.xml";
			byte[] bytes = IoUtil.readBytes(inputStream);
			Lob lob = new Lob(bytes, false);
			resources.put(lobKey, lob);
			this.jbpmDao.getHibernateTemplate().update(deploymentImpl);
		}
		//更新缓存
		EnvironmentImpl env = ((EnvironmentFactory)JBPMUtil.getProcessEngine()).openEnvironment();
		try{
	        RepositoryCache repositoryCache = (RepositoryCache)env.get(RepositoryCache.class);
	        repositoryCache.remove(deployId);
		} finally{
		   env.close();
		   IoUtil.close(inputStream);
		}
	}
	@Override
	public void updateWorkflowSynchronized(String deployId,Locale locale) throws Exception {
		JbpmWorkflowDesigner jbpmWorkflowDesigner = (JbpmWorkflowDesigner)this.jbpmDao.getSinglObjectListByHSQL("select jwd from JbpmWorkflowDesigner jwd where jwd.deploymentImpl.dbid=?",Long.parseLong(deployId)).get(0);
		DeploymentImpl deploymentImpl = jbpmWorkflowDesigner.getDeploymentImpl();
		jbpmWorkflowDesigner.setDeployedTime(DateUtil.getSystemDateTime());
		this.jbpmDao.getHibernateTemplate().update(jbpmWorkflowDesigner);
		//更新blob
		this.updateDeployedLobBlobValue(deploymentImpl);
		//同步节点
		this.updateActivitiesDetail(deploymentImpl,null,locale);
	}
	@Override
	public String transferedJpdlXmlByWorkflowDesigner(Map<String, String> modelData) throws Exception 
	{
		String id    = StringUtil.nullToString(modelData.get("id"));
		String designerWorkflowJsonString = modelData.get("designerWorkflowJsonString");
		Map<String,String> transferedMap    = DesignerTransferUtil.getTransferedJpdlXml(designerWorkflowJsonString);
		
		JbpmWorkflowDesigner jbpmWorkflowDesigner = null;
		String currentDateTime = DateUtil.getSystemDateTime();
		if(id.isEmpty())
		{
			jbpmWorkflowDesigner = new JbpmWorkflowDesigner();
			
			jbpmWorkflowDesigner.setCreateTime(currentDateTime);
		}
		else
		{
			jbpmWorkflowDesigner = this.jbpmDao.getHibernateTemplate().get(JbpmWorkflowDesigner.class, id);
			jbpmWorkflowDesigner.setUpdateTime(currentDateTime);
		}
		String key = transferedMap.get("key");
		int    version = Integer.parseInt(transferedMap.get("version"));
		int    maxDotX =  Integer.parseInt(transferedMap.get("maxDotX"));
		int    maxDotY =  Integer.parseInt(transferedMap.get("maxDotY"));
		String workflowDefinition = key + "-"+ version;
		jbpmWorkflowDesigner.setDisplayName(transferedMap.get("displayName"));
		jbpmWorkflowDesigner.setWorkflowName(transferedMap.get("name"));
		jbpmWorkflowDesigner.setType((DictionaryData)this.findEntityByID(DictionaryData.class, transferedMap.get("type")));
		jbpmWorkflowDesigner.setCode(transferedMap.get("code"));
		jbpmWorkflowDesigner.setKey(key);
		jbpmWorkflowDesigner.setVersion(version);
		jbpmWorkflowDesigner.setWorkflowDefinition(workflowDefinition);
		jbpmWorkflowDesigner.setJpdlVersion(transferedMap.get("jpdlVersion"));
		jbpmWorkflowDesigner.setDescription(transferedMap.get("description"));
		jbpmWorkflowDesigner.setDesignerWorkflowJson(designerWorkflowJsonString);
		jbpmWorkflowDesigner.setTransferedJpdlXml(transferedMap.get("transferedJpdlXml"));
		jbpmWorkflowDesigner.setPosition(0);
		jbpmWorkflowDesigner.setMaxDotX(maxDotX);
		jbpmWorkflowDesigner.setMaxDotY(maxDotY);
		String currentPosition = transferedMap.get("position");
		/*********最新代码更新************/
		this.saveOrUpdateEntity(jbpmWorkflowDesigner);
		this.updateFlush();
		String tablename = "t_jbpm_designer_transfer";
		String currentId = jbpmWorkflowDesigner.getId();
		this.jbpmDao.updateOrderPosition(tablename, currentId, null, currentPosition);
		return jbpmWorkflowDesigner.getId()+","+jbpmWorkflowDesigner.getPosition();
	}
	/**
	 * (non-Javadoc)
	 * @see com.tenwa.jbpm.service.JbpmService#queryDeployedWorkflows()
	 **/
	@Override
	public List<Map<String,String>> queryDeployedWorkflows() throws Exception{
		return this.jbpmDao.queryDeployedWorkflows();
	}
	@Override
	public DeploymentImpl deployWorkflow(String designedId) throws Exception {
		return this.jbpmDao.deployWorkflow(designedId);
	}
	@Override
	public void removeDeployedWorkflow(String designedId,long deployId) throws Exception {
		this.jbpmDao.removeDeployedWorkflow(designedId,deployId);
	}
	@Override
	public byte[] getDeployedWorkflowDiagramDatas(String deployId)
			throws Exception {
		return this.jbpmDao.getDeployedWorkflowDiagramDatas(deployId);
	}
	@Override
	public List<ActivityCoordinates> getActivityCoordinates(String processInstanceId) throws Exception
	{
		List<ActivityCoordinates> activityCoordinatesList = new ArrayList<ActivityCoordinates>();
		RepositoryService repositoryService = JBPMUtil.getRepositoryService();
		ExecutionService executionService = JBPMUtil.getExecutionService();
		ProcessInstance processInstance = executionService.findProcessInstanceById(processInstanceId);
		if(processInstance!=null)
		{
			Set<String> activityNames = processInstance.findActiveActivityNames();
			for(String activityName:activityNames)
			{
				ActivityCoordinates ac = repositoryService.getActivityCoordinates(processInstance.getProcessDefinitionId(),activityName);
				activityCoordinatesList.add(ac);
			}
		}
		return activityCoordinatesList;
	}
	@Override
	public Map<String,String> getActivedRects(String deployId,String processInstanceId) throws Exception
	{
		Map<String,String> map = new HashMap<String,String>();
		RepositoryService repositoryService = JBPMUtil.getRepositoryService();
		ExecutionService executionService = JBPMUtil.getExecutionService();
		JbpmWorkflowDesigner jbpmWorkflowDesigner =null;
		if(deployId.matches("^\\d+$"))
		{
			List<Deployment> l = repositoryService.createDeploymentQuery().deploymentId(deployId).list();
			if(l.size()>0){
				DeploymentImpl deploymentImpl  = (DeploymentImpl)l.get(0);
				jbpmWorkflowDesigner = deploymentImpl.getJbpmWorkflowDesigner();
			}
		}
		else
		{
			jbpmWorkflowDesigner = (JbpmWorkflowDesigner)this.jbpmDao.findEntityByID(JbpmWorkflowDesigner.class, deployId);
		}
		String designerWorkflowJsonToString = jbpmWorkflowDesigner.getDesignerWorkflowJsonToString();
		ProcessInstance processInstance = executionService.findProcessInstanceById(processInstanceId);
		map.put("workflow_designerWorkflowJsonString", designerWorkflowJsonToString);
		map.put("jpdlVersion", jbpmWorkflowDesigner.getJpdlVersion());
		map.put("activeRects", "{}");
		map.put("historyRects", "{}");
		map.put("maxDotX",StringUtil.empty2Other(jbpmWorkflowDesigner.getMaxDotX(), "0"));
		map.put("maxDotY",StringUtil.empty2Other(jbpmWorkflowDesigner.getMaxDotY(), "0"));
		map.put("workflowdisplaytype",jbpmWorkflowDesigner.getType().getName());
		map.put("workflowdisplayname",jbpmWorkflowDesigner.getDisplayName());
		if(null == processInstance)
		{
			return map;
		}
		JSONObject activeInfo = new JSONObject();
		JSONArray activeRects = new JSONArray();
		activeInfo.put("rects", activeRects);
		Set<String> names = processInstance.findActiveActivityNames();
		Set<String> activityNames = new HashSet<String>();
		for(ActivityDetail ad : jbpmWorkflowDesigner.getDeploymentImpl().getActivityDetails()){
			 String adActivityName = ad.getActivityName();
			 String adActivityKey = ad.getActivityKey();
			 if(null == adActivityKey){
				 continue;
			 }
			 for(String activityKey : names){
				 if(adActivityKey.equals(activityKey)){
					 activityNames.add(adActivityName);
					 break;
				 }
			 }
		 }
		for(String activityName : activityNames)
		{
			JSONObject activeRect = new JSONObject();
			activeRect.put("paths", new JSONArray());
			activeRect.put("name", activityName);
			activeRects.put(activeRect);
		}
		map.put("activeRects", activeInfo.toString());
		return map;
	}
	@Override
	public void saveChangeToActivity(String id,Map<String, String> model) throws Exception {
		ActivityDetail activityDetail = (ActivityDetail)this.findEntityByID(ActivityDetail.class, id);
		this.jbpmDao.copyAndOverrideExistedValueFromStringMap(model, activityDetail, null);
		this.jbpmDao.saveChangeToActivity(activityDetail);
	}
	@Override
	public  synchronized String startDeployedProcess(HttpServletRequest request,String processDefinitionId,Map<String,String> modelData) throws Exception 
	{
		     processDefinitionId = URLDecoder.decode(processDefinitionId, "UTF-8");
		     //jbpm内置api
		     RepositoryService repositoryService = JBPMUtil.getRepositoryService(); 
			 ExecutionService  executionService = JBPMUtil.getExecutionService(); 
			 HistoryService  historyService = JBPMUtil.getHistoryService(); 
			 //流程定义信息
			 ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).uniqueResult();
			 String deployId =  processDefinition.getDeploymentId();
			 DeploymentImpl deploymentImpl = (DeploymentImpl)this.jbpmDao.getSinglObjectListByHSQL("from DeploymentImpl where dbid = ?", Long.parseLong(deployId)).get(0);
			 //从前台作为参数传递的请求transition路径(优先级高于所有)
			 String requestInitiatorRoute = StringUtil.empty2Other(modelData.get("requestInitiatorRoute"), "");
			 //流程草稿处理
			 //String draftUUID = DraftUUIDUtil.getDraftUUID("",QueryUtil.getRequestParameterMapNoAjax(request).toString());
			 String queryString = request.getQueryString();
			 String draftUUID = DraftUUIDUtil.getDraftUUID("",queryString);
			 //modify by tracywindy used revise the repeated draft workflowInfo records 2013-08-16 21:27:00
			 modelData.put("draftUUID",draftUUID);
			 String hsql_query_processInstance = "from JBPMWorkflowHistoryInfo jh where jh.processDefinitionId=? and jh.processInstanceState=? and jh.draftUUID=? and jh.requestInitiatorUser.id=? and jh.historyTaskInstanceImpl is null";
			 //System.out.println("####DraftSql:select * from t_jbpm_workflow_info where processinstance_state_='Draft' and draft_uuid_='"+draftUUID+"' and request_initiator_='"+request.getSession(false).getAttribute("login_username").toString()+"'");
			 List<Object> infos = this.jbpmDao.getSinglObjectListByHSQL(hsql_query_processInstance,processDefinitionId,WorkflowSubmitTextEnum.draftButtonDisplayText.getText(),draftUUID,request.getSession(false).getAttribute("login_userid").toString());
			 //System.out.println(">>Size:"+infos.size());
			 //System.out.println("####MAP:"+QueryUtil.getRequestParameterMapNoAjax(request).toString());
			 if(infos.size()>=1)
			 {
				 JBPMWorkflowHistoryInfo jhi_processInstance = (JBPMWorkflowHistoryInfo)infos.get(0);
				 JBPMUtil.getVariablesInfo(this.jbpmDao.getHibernateTemplate(),String.valueOf(jhi_processInstance.getHistoryProcessInstanceImpl().getDbid()), null, modelData);
				 long currentActivityDBID = jhi_processInstance.getActiveHistoryTaskInstanceImpls().iterator().next().getDbid();
				 JBPMWorkflowHistoryInfo jhi_completedTaskImpl = (JBPMWorkflowHistoryInfo)this.jbpmDao.getSinglObjectListByHSQL("from JBPMWorkflowHistoryInfo jwhi where jwhi.historyTaskInstanceImpl.dbid = ?", currentActivityDBID).get(0);
				 ProcessInstance processInstance = executionService.createProcessInstanceQuery().processInstanceId(jhi_completedTaskImpl.getHistoryProcessInstanceImpl().getProcessInstanceId()).uniqueResult();
				 //System.out.println("$########:"+jhi_completedTaskImpl.getId());
				 modelData.put("jbpmWorkflowHistoryInfoUserId",jhi_completedTaskImpl.getJbpmWorkflowHistoryInfoUsers().iterator().next().getId());
				 this.getRequestFormDealedInfo(request,jhi_completedTaskImpl,jhi_processInstance, modelData,processInstance.findActiveActivityNames());
				 return jhi_completedTaskImpl.getActivityDetail().getFormPath();//jhi_processInstance.getProcessFormPath();
			 }
			   //判断互斥流程
			   JbpmWorkflowDesigner jbpmWorkflowDesigner = deploymentImpl.getJbpmWorkflowDesigner();
			   Set<WorkflowDesignerReject> workflowDesignerRejects = jbpmWorkflowDesigner.getRejectWorkflowDesignerRejects();
			   for(WorkflowDesignerReject workflowDesignerReject : workflowDesignerRejects)
			   {
				   String sourceWorkflowName = jbpmWorkflowDesigner.getKey();
				   String rejectWorkflowName = workflowDesignerReject.getRejectJbpmWorkflowDesigner().getKey();
				   JSONArray rejectConditionBeanJsonArray = this.getWorkflowRejectBeanName(true, sourceWorkflowName, rejectWorkflowName);
				   String rejectConditionBeanName = "commonWorkflowRejectCondition";
				   for(int rci=0;rci<rejectConditionBeanJsonArray.length();rci++){
					   JSONObject rejectConditionBeanJson = rejectConditionBeanJsonArray.getJSONObject(rci);
					   String sourceWorkflowName_ = rejectConditionBeanJson.getString("sourceWorkflowName");
					   String rejectWorkflowName_ = rejectConditionBeanJson.getString("rejectWorkflowName");
					   if((sourceWorkflowName.equals(sourceWorkflowName_))&&(rejectWorkflowName.equals(rejectWorkflowName_))){
						   rejectConditionBeanName = rejectConditionBeanJson.getString("value");
						   break;
					   }
				   }
				   RejectCondition rejectCondition = (RejectCondition)WebUtil.getApplicationContext().getBean(rejectConditionBeanName);
				   RejectInfo  rejectInfo = rejectCondition.reject(workflowDesignerReject, modelData);
				   if(rejectInfo.isRejected()){
			    	   StringBuffer rs = new StringBuffer("@@_@@WORKFLOWREJECTED:");
			    	   rs.append(rejectInfo.returnedRejectInfo());
			    	   return rs.toString();
				   }
				   /* String rejectWorkflowDefinitionId    = workflowDesignerReject.getRejectJbpmWorkflowDesigner().getWorkflowDefinition();
				   String hsql="select distinct jhi.workflowName from JBPMWorkflowHistoryInfo jhi left join jhi.jbpmWorkflowHistoryInfoUsers jhiuser" +
			   		" where  jhi.historyTaskInstanceImpl is not null " +
			   		" and jhiuser.endTime is null " +
			   		" and jhi.processDefinitionId = ? ";
				   String rejectQueryConditions = workflowDesignerReject.getQueryConditions();
				   if(null != rejectQueryConditions){
					   String queryText = QueryUtil.getQueryString(modelData, rejectQueryConditions);
					   hsql+=queryText;
				   }
			       List<String> rs_list = this.findResultsByHSQL(hsql, rejectWorkflowDefinitionId);
			       if(rs_list.size()>0){
			    	   StringBuffer rs = new StringBuffer("互斥信息:");
			    	   rs.append(StringUtil.join(rs_list, ","));
			    	   return rs.toString();
			       }*/
			   }
		       
			 //发起流程实例
			 ProcessInstance processInstance = null;
			 if("".equals(requestInitiatorRoute.trim()))
			 {
				 processInstance = executionService.startProcessInstanceById(processDefinitionId);
			 }
			 else
			 {
				 processInstance = executionService.startProcessInstanceById(processDefinitionId,requestInitiatorRoute);
			 }
			 //当流程开始时执行start节点的startaction 
			 List<ActivityDetail> activitiesDetailList = this.jbpmDao.getActivitiesDetailListByDeployId(deployId);
			 HistoryProcessInstanceImpl historyProcessInstanceImpl = (HistoryProcessInstanceImpl) historyService.createHistoryProcessInstanceQuery().processInstanceId(processInstance.getId()).uniqueResult();
			 return this.workflowDataManage(request,null,null,null, deploymentImpl,processDefinition, processInstance,historyProcessInstanceImpl , null, activitiesDetailList, modelData, true,"start",false);
		}
	 /**
	 * (non-Javadoc)
	 * @see com.tenwa.jbpm.service.JbpmService#requestProcessTaskForm(javax.servlet.http.HttpServletRequest, java.lang.String, java.util.Map)
	 **/
	@Override
	public String updateRequestProcessTaskForm(HttpServletRequest request,String currentTaskId,Map<String,String> modelData) throws Exception 
	{
	 	String query_task="from JBPMWorkflowHistoryInfo jwhi where jwhi.historyTaskInstanceImpl.historyTask.dbid = ?";
	 	JBPMWorkflowHistoryInfo jhi_completedTaskImpl = (JBPMWorkflowHistoryInfo)this.jbpmDao.getSinglObjectListByHSQL(query_task,Long.parseLong(currentTaskId)).get(0);
		 
	 	String formPath = null;
		if("true".equals(modelData.get("isViewHistoryTask")) && !"true".equals(modelData.get("isPreivewTask"))){
			formPath  = jhi_completedTaskImpl.getProcessFormPath();
		}else{
			formPath  = jhi_completedTaskImpl.getActivityDetail().getFormPath();
			modelData.put("WorkFlowDealMethod", jhi_completedTaskImpl.getActivityDetail().getDealMethod());
		}
		
		//流程实例中的变量
		HistoryProcessInstanceImpl historyProcessInstanceImpl = jhi_completedTaskImpl.getHistoryProcessInstanceImpl();//(HistoryProcessInstanceImpl) historyService.createHistoryProcessInstanceQuery().processInstanceId(processInstanceId).uniqueResult();
		//JBPMWorkflowHistoryInfo  processInstanceHistoryInfo = jhi_completedTaskImpl.getHistoryTaskInstanceImpl().getProcessInstanceHistoryInfo();
		String queryProcessInstanceHistoryHSql = "from JBPMWorkflowHistoryInfo jwhi where jwhi.historyProcessInstanceImpl.dbid=? and jwhi.historyTaskInstanceImpl is null";
		JBPMWorkflowHistoryInfo processInstanceHistoryInfo = (JBPMWorkflowHistoryInfo)this.jbpmDao.getSinglObjectListByHSQL(queryProcessInstanceHistoryHSql,historyProcessInstanceImpl.getDbid()).get(0);
		Set<String> activityNames = new HashSet<String>();
		if(null != processInstanceHistoryInfo)
		{
			for(HistoryTaskInstanceImpl historyActivityTask : processInstanceHistoryInfo.getActiveHistoryTaskInstanceImpls())
			{
				if(null != historyActivityTask.getEndTime())
				{
					continue;
				}
				String activityName = historyActivityTask.getActivityName();
				activityNames.add(activityName);
			}
		}
		JBPMUtil.getVariablesInfo(this.jbpmDao.getHibernateTemplate(),String.valueOf(historyProcessInstanceImpl.getDbid()),currentTaskId, modelData);
		this.getRequestFormDealedInfo(request,jhi_completedTaskImpl,processInstanceHistoryInfo, modelData,activityNames);
		JBPMWorkflowHistoryInfoUser currentJbpmWorkflowHistoryInfoUser  = null;
		if(null == modelData.get("jbpmWorkflowHistoryInfoUserId")){
			boolean isFound = false;
			for(JBPMWorkflowHistoryInfoUser jbpmWorkflowHistoryInfoUser : jhi_completedTaskImpl.getJbpmWorkflowHistoryInfoUsers())
			{
				currentJbpmWorkflowHistoryInfoUser = jbpmWorkflowHistoryInfoUser;
				if(null == jbpmWorkflowHistoryInfoUser.getEndTime()){
					isFound = true;
					modelData.put("jbpmWorkflowHistoryInfoUserId",jbpmWorkflowHistoryInfoUser.getId());
				}
			}
			if((null != currentJbpmWorkflowHistoryInfoUser)&&(!isFound)){
				modelData.put("jbpmWorkflowHistoryInfoUserId",currentJbpmWorkflowHistoryInfoUser.getId());
			}
		}
		return formPath;
	}
	//获取路由信息
	@Override
	public void getDynamicRequestInitiatorRoute(HttpServletRequest request,String conditionRouteType,String conditionRouteValue,Map<String,String> modelData) throws Exception{
		String dynamicRequestInitiatorRoute = "";
		if(null != conditionRouteType){
			ConditionRouteTypeEnum conditionRouteTypeEnum = ConditionRouteTypeEnum.valueOf(conditionRouteType.toUpperCase());
			/*PAGECALLBACK("pageCallBack","页面回调函数"),//默认 workflowNextRouteCallBack
			FIELD("field","表单域"),
			EXPRESSION("expression","表达式"),
			SQL("sql","自定义")*/
			switch(conditionRouteTypeEnum){
			   case PAGECALLBACK:{
				   dynamicRequestInitiatorRoute = "";
				   break;
			   }
			   case FIELD:{
				   dynamicRequestInitiatorRoute = QueryUtil.getQueryString(modelData, conditionRouteValue);
				   break;
			   }
			   case EXPRESSION:{
				   if(!conditionRouteValue.startsWith("$")){
					   conditionRouteValue = "$"+conditionRouteValue;
				   }
				   dynamicRequestInitiatorRoute = StringUtil.nullToString(ELUtil.evaluate(conditionRouteValue, modelData));
				   break;
			   }
			   case SQL:{
				   String dynamicSql = QueryUtil.getQueryString(modelData, conditionRouteValue);
				   List<Map<String,Object>> listMaps = this.jbpmDao.getJdbcTemplate().queryForList(dynamicSql);
					String routeNameKey =  "routeName";
					if(listMaps.size()>0){
						Set<String> s =listMaps.get(0).keySet();
						if(1 == s.size()){
							for(String key : s){
								routeNameKey = key;
							}
						}
					}
					for(Map<String,Object> m :listMaps)
					{
						dynamicRequestInitiatorRoute = StringUtil.nullToString(m.get(routeNameKey));
					}
				   break;
			   }
			   default:{
				   dynamicRequestInitiatorRoute = "";
			   }
			}
		}
		
		request.setAttribute("dynamicRequestInitiatorRoute", dynamicRequestInitiatorRoute);
	}
	@Override
	public Set<ActivityTaskUsers> getLastBackNextActivityTaskUsersList(HttpServletRequest request,String jhi_completedTaskImplId) throws Exception
	{
		JBPMWorkflowHistoryInfo jhi_completedTaskImpl = (JBPMWorkflowHistoryInfo)this.jbpmDao.findEntityByID(JBPMWorkflowHistoryInfo.class, jhi_completedTaskImplId);
		Set<ActivityTaskUsers> lastBack_next_activityTaskUsersList = new TreeSet<ActivityTaskUsers>();

		JBPMWorkflowHistoryInfo backedHistoryInfo = jhi_completedTaskImpl.getBackedHistoryInfo();
		if(null != backedHistoryInfo){
			lastBack_next_activityTaskUsersList = getNextActivityTaskUsersList(request,backedHistoryInfo.getId(),null,true);
		}
		return lastBack_next_activityTaskUsersList;
	}
	//获取提交列表信息
	@Override
	public Set<ActivityTaskUsers> getNextActivityTaskUsersList(HttpServletRequest request,String jhi_completedTaskImplId,Collection<String> exceptedActivityNames,boolean isLastBack) throws Exception
	{
		JBPMWorkflowHistoryInfo jhi_completedTaskImpl = (JBPMWorkflowHistoryInfo)this.jbpmDao.findEntityByID(JBPMWorkflowHistoryInfo.class, jhi_completedTaskImplId);
		ActivityDetail activityDetail = jhi_completedTaskImpl.getActivityDetail();
		ActivityTaskUsersComparator activityTaskUsersComparator = new ActivityTaskUsersComparator();
		Set<ActivityTaskUsers> next_activityTaskUsersList = new TreeSet<ActivityTaskUsers>(activityTaskUsersComparator);
		Set<ActivityDetail> activitiesDetailList = jhi_completedTaskImpl.getDeploymentImpl().getActivityDetails();
		//流程实例
		HistoryProcessInstanceImpl historyProcessInstanceImpl = jhi_completedTaskImpl.getHistoryProcessInstanceImpl();//(HistoryProcessInstanceImpl) historyService.createHistoryProcessInstanceQuery().processInstanceId(processInstanceId).uniqueResult();
		String queryProcessInstanceHistoryHSql = "from JBPMWorkflowHistoryInfo jwhi where jwhi.historyProcessInstanceImpl.dbid=? and jwhi.historyTaskInstanceImpl is null";
		JBPMWorkflowHistoryInfo processInstanceHistoryInfo = (JBPMWorkflowHistoryInfo)this.jbpmDao.getSinglObjectListByHSQL(queryProcessInstanceHistoryHSql,historyProcessInstanceImpl.getDbid()).get(0);
		Set<String> activityNames = new HashSet<String>();
		if(null != processInstanceHistoryInfo)
		{
			for(HistoryTaskInstanceImpl historyActivityTask : processInstanceHistoryInfo.getActiveHistoryTaskInstanceImpls())
			{
				if(null != historyActivityTask.getEndTime())
				{
					continue;
				}
				String activityName = historyActivityTask.getActivityName();
				activityNames.add(activityName);
			}
		}
		Map<String,String> modelData = JBPMUtil.getProcessInstanceHistoryData(this.jbpmDao.getHibernateTemplate(), historyProcessInstanceImpl.getDbid()+"");
		//modify by tracywindy 2014-02-11
		modelData.remove("isHasPutRequestParameters");
		List<RouteInfo> routeInfos = null;
		if(isLastBack){
			routeInfos = new ArrayList<RouteInfo>();
			String deployId = String.valueOf(jhi_completedTaskImpl.getDeploymentImpl().getDbid());
			String currentActivityKey = activityDetail.getActivityKey(); 
			 RouteInfo routeInfo = new RouteInfo(); 
        	 routeInfo.setParentActivityName(currentActivityKey);
        	 routeInfo.setCurrentTransition(null);
        	 List<Activity> targetTaskActivitys = new ArrayList<Activity>();
        	 ProcessDefinition definition = JBPMUtil.getRepositoryService().createProcessDefinitionQuery().deploymentId(deployId).uniqueResult();
     		 ProcessDefinitionImpl definitionimpl = (ProcessDefinitionImpl)definition;
     		 targetTaskActivitys.add(definitionimpl.findActivity(currentActivityKey));
             routeInfo.setTargetTaskActivitys(targetTaskActivitys);
             routeInfos.add(routeInfo);
		}else{
			routeInfos = JBPMUtil.getWorkFlowRouteInfos(jhi_completedTaskImpl,activityNames,exceptedActivityNames,modelData,this.jbpmDao.getHibernateTemplate(),this.jbpmDao.getJdbcTemplate());
		}
		for(RouteInfo routeInfo:routeInfos)
		{
			for(Activity av : routeInfo.getTargetTaskActivitys())
			{
				ActivityTaskUsers activityTaskUsers = new ActivityTaskUsers();
				activityTaskUsers.setRouteInfo(routeInfo);
				
				TaskUsers taskUsers = new TaskUsers();
				taskUsers.setTaskActivity(av);
				
				for(ActivityDetail ad:activitiesDetailList)
				{
					//System.out.println(">>>>:"+av.getName()+":"+ad.getActivityKey());
					if(0 == ad.getEnabled())continue;
					if((av.getType().equalsIgnoreCase(ad.getActivityType()))&&(av.getName().equals(ad.getActivityKey())))
					{
						taskUsers.setActivityDetail(ad);
						List<User> dealUsers = new ArrayList<User>();
						List<User> readDealUsers = new ArrayList<User>();
						List<User> signatureUsers = new ArrayList<User>();
						if(!"start".equalsIgnoreCase(ad.getActivityType()))
						{
							String currentProcessInstanceId = jhi_completedTaskImpl.getHistoryProcessInstanceImpl().getProcessInstanceId();
							if(!"end".equalsIgnoreCase(ad.getActivityType())){
							    dealUsers = JBPMUtil.getActorsListByActivitySetting(request,ad,currentProcessInstanceId,ad.getActorType(), ad.getActorValue(), modelData,this,"actor");
								String autoSignatureActorType = ad.getAutoSignatureActorType();//ad.getAutoSignatureActorType();
								if(null != autoSignatureActorType){
									signatureUsers = JBPMUtil.getActorsListByActivitySetting(request,ad,currentProcessInstanceId,autoSignatureActorType, ad.getAutoSignatureActorValue(), modelData,this,"signature");
								}
							}
							String autoReadType = ad.getAutoReadActorType();//ad.getAutoReadActorType();
							if(null != autoReadType){
								readDealUsers = JBPMUtil.getActorsListByActivitySetting(request,ad,currentProcessInstanceId,autoReadType, ad.getAutoReadActorValue(), modelData,this,"read");
							}
						}
						taskUsers.setDealUsers(dealUsers);
						taskUsers.setReadDealUsers(readDealUsers);
						taskUsers.setSignatureUsers(signatureUsers);
						activityTaskUsers.setCurrentTaskUsers(taskUsers);
						next_activityTaskUsersList.add(activityTaskUsers);
						break;
					}
				}
			}
		}
		//modify by tracywindy 2014-02-11
		String conditionRouteType = activityDetail.getConditionRouteType();
		String conditionRouteValue = activityDetail.getConditionRouteValue();
		try {
			this.getDynamicRequestInitiatorRoute(request, conditionRouteType, conditionRouteValue,modelData);
		} catch (Exception e) {
			e.printStackTrace();
			throw new JbpmException("条件路由配置异常！");
		}
		modelData.remove("isHasPutRequestParameters");
		return next_activityTaskUsersList;
	}
	private static Logger log = Logger.getLogger(JbpmServiceImpl.class);
	//获取回退列表信息
	@Override
	public Set<ActivityTaskUsers> getBackActivityTaskUsersList(HttpServletRequest request,String jhi_completedTaskImplId) throws Exception
	{
		JBPMWorkflowHistoryInfo jhi_completedTaskImpl = (JBPMWorkflowHistoryInfo)this.jbpmDao.findEntityByID(JBPMWorkflowHistoryInfo.class, jhi_completedTaskImplId);
		Long processId =   jhi_completedTaskImpl.getHistoryProcessInstanceImpl().getDbid();
		ActivityDetail activityDetail = jhi_completedTaskImpl.getActivityDetail();
		ActivityTaskUsersComparator activityTaskUsersComparator = new ActivityTaskUsersComparator();
		Set<ActivityTaskUsers> back_activityTaskUsersList = new TreeSet<ActivityTaskUsers>(activityTaskUsersComparator);
		DeploymentImpl deploymentImpl = jhi_completedTaskImpl.getDeploymentImpl();
		String deployId = deploymentImpl.getId();
		Set<ActivityDetail> activitiesDetailList = deploymentImpl.getActivityDetails();
		Set<ActivityTaskUsers> all_activityTaskUsersList = new TreeSet<ActivityTaskUsers>(activityTaskUsersComparator);
		List<RouteInfo>  allRouteInfos=JBPMUtil.getWorkFlowAllRouteInfos(deployId, activityDetail.getActivityName());
		String backRoutesNodes = StringUtil.nullToString(activityDetail.getBackActivities());
		HistoryProcessInstanceImpl historyProcessInstanceImpl = jhi_completedTaskImpl.getHistoryProcessInstanceImpl();
		String[] backRoutesArr = backRoutesNodes.split(",");
		Set<String> back_allWorkFlowNodesSet = new HashSet<String>();
		for(int i_=0;i_<backRoutesArr.length;i_++)
		{
			String nodeNameKey = backRoutesArr[i_];
			back_allWorkFlowNodesSet.add(nodeNameKey);
		}
		Map<String,String> modelData = JBPMUtil.getProcessInstanceHistoryData(this.jbpmDao.getHibernateTemplate(), historyProcessInstanceImpl.getDbid()+"");
		//modify by tracywindy 2014-02-11
		modelData.remove("isHasPutRequestParameters");
		for(RouteInfo routeInfo:allRouteInfos)
		{
			for(Activity av : routeInfo.getTargetTaskActivitys())
			{
				ActivityTaskUsers activityTaskUsers = new ActivityTaskUsers();
				activityTaskUsers.setRouteInfo(routeInfo);
				
				TaskUsers taskUsers = new TaskUsers();
				taskUsers.setTaskActivity(av);
				
				for(ActivityDetail ad:activitiesDetailList)
				{
					if(0 == ad.getEnabled())continue;
					if((av.getType().equalsIgnoreCase(ad.getActivityType()))&&(av.getName().equals(ad.getActivityKey())))
					{
						taskUsers.setActivityDetail(ad);
						List<User> dealUsers = new ArrayList<User>();
						List<User> readDealUsers = new ArrayList<User>();
						List<User> signatureUsers = new ArrayList<User>();
						
						if(!"start".equalsIgnoreCase(ad.getActivityType())){
							String currentProcessInstanceId = jhi_completedTaskImpl.getHistoryProcessInstanceImpl().getProcessInstanceId();
							
							if(!"end".equalsIgnoreCase(ad.getActivityType())){
								try{
									//add by zhanc
									if(ad.getActorType().equalsIgnoreCase("submitDefinedRelation")){
										String sql = " select WU.PLAN_ACTOR_USER_ID_ ID from  T_JBPM_WORKFLOW_INFO WORKINFO "
												+ "	LEFT JOIN   T_JBPM_WORKFLOW_INFOS_USERS wu on wu.jbpmworkflowhistoryinfo_id_ = WORKINFO.id_  "
												+ "	WHERE WORKINFO.JBPM4_HIST_PROCINST_DBID_ = ? "
												+ "	AND WORKINFO.ACTIVITY_DETAIL_ID_ = ? ";
										List<Map<String,Object>> prevUserIdLists =  this.jbpmDao.queryListBySql(sql, processId,ad.getId());
										for(Map<String, Object> userMap : prevUserIdLists){
											User user = (User)this.jbpmDao.findEntityByID(User.class, (String)userMap.get("id")) ;
											dealUsers.add(user);
										}
									}else{
										dealUsers = JBPMUtil.getActorsListByActivitySetting(request,ad,currentProcessInstanceId,ad.getActorType(), ad.getActorValue(), modelData,this,"actor");
									}
									
								}catch(JbpmException e){
									e.printStackTrace();
									continue;
								}
								String autoSignatureActorType = ad.getAutoSignatureActorType();//ad.getAutoSignatureActorType();
								if(null != autoSignatureActorType){
									signatureUsers = JBPMUtil.getActorsListByActivitySetting(request,ad,currentProcessInstanceId,autoSignatureActorType, ad.getAutoSignatureActorValue(), modelData,this,"signature");
								}
							}
							String autoReadType = ad.getAutoReadActorType();//ad.getAutoReadActorType();
							if(null != autoReadType){
								readDealUsers = JBPMUtil.getActorsListByActivitySetting(request,ad,currentProcessInstanceId,autoReadType, ad.getAutoReadActorValue(), modelData,this,"read");
							}
						}
						taskUsers.setDealUsers(dealUsers);
						taskUsers.setReadDealUsers(readDealUsers);
						taskUsers.setSignatureUsers(signatureUsers);
						
						activityTaskUsers.setCurrentTaskUsers(taskUsers);
						all_activityTaskUsersList.add(activityTaskUsers);
						if(back_allWorkFlowNodesSet.contains(ad.getActivityName()))
						{
							back_activityTaskUsersList.add(activityTaskUsers);
						}	
						break;
					}
				}
			}
		}
		//modify by tracywindy 2014-02-11		
		/*String conditionRouteType = activityDetail.getConditionRouteType();
		String conditionRouteValue = activityDetail.getConditionRouteValue();
		this.getDynamicRequestInitiatorRoute(request, conditionRouteType, conditionRouteValue,modelData);*/
		modelData.remove("isHasPutRequestParameters");
		return back_activityTaskUsersList;
	}
	//获取任意路由列表信息
	@Override
	public Set<ActivityTaskUsers> getAllActivityTaskUsersList(HttpServletRequest request,String jhi_completedTaskImplId) throws Exception
	{
		JBPMWorkflowHistoryInfo jhi_completedTaskImpl = (JBPMWorkflowHistoryInfo)this.jbpmDao.findEntityByID(JBPMWorkflowHistoryInfo.class, jhi_completedTaskImplId);
		ActivityDetail activityDetail = jhi_completedTaskImpl.getActivityDetail();
		ActivityTaskUsersComparator activityTaskUsersComparator = new ActivityTaskUsersComparator();
		DeploymentImpl deploymentImpl = jhi_completedTaskImpl.getDeploymentImpl();
		String deployId = deploymentImpl.getId();
		Set<ActivityDetail> activitiesDetailList = deploymentImpl.getActivityDetails();
		Set<ActivityTaskUsers> all_activityTaskUsersList = new TreeSet<ActivityTaskUsers>(activityTaskUsersComparator);
		List<RouteInfo>  allRouteInfos=JBPMUtil.getWorkFlowAllRouteInfos(deployId, activityDetail.getActivityKey());
		HistoryProcessInstanceImpl historyProcessInstanceImpl = jhi_completedTaskImpl.getHistoryProcessInstanceImpl();
		Map<String,String> modelData = JBPMUtil.getProcessInstanceHistoryData(this.jbpmDao.getHibernateTemplate(), historyProcessInstanceImpl.getDbid()+"");
		//modify by tracywindy 2014-02-11
		modelData.remove("isHasPutRequestParameters");
		for(RouteInfo routeInfo:allRouteInfos)
		{
			for(Activity av : routeInfo.getTargetTaskActivitys())
			{
				ActivityTaskUsers activityTaskUsers = new ActivityTaskUsers();
				activityTaskUsers.setRouteInfo(routeInfo);
				
				TaskUsers taskUsers = new TaskUsers();
				taskUsers.setTaskActivity(av);
				
				for(ActivityDetail ad:activitiesDetailList)
				{
					if(0 == ad.getEnabled())continue;
					if((av.getType().equalsIgnoreCase(ad.getActivityType()))&&(av.getName().equals(ad.getActivityKey())))
					{
						taskUsers.setActivityDetail(ad);
						List<User> dealUsers = new ArrayList<User>();
						List<User> readDealUsers = new ArrayList<User>();
						List<User> signatureUsers = new ArrayList<User>();
						if(!"start".equalsIgnoreCase(ad.getActivityType())){
							String currentProcessInstanceId = jhi_completedTaskImpl.getHistoryProcessInstanceImpl().getProcessInstanceId();
							if(!"end".equalsIgnoreCase(ad.getActivityType())){
								dealUsers = JBPMUtil.getActorsListByActivitySetting(request,ad,currentProcessInstanceId,ad.getActorType(), ad.getActorValue(), modelData,this,"actor");
								String autoSignatureActorType = ad.getAutoSignatureActorType();
								if(null != autoSignatureActorType){
									signatureUsers = JBPMUtil.getActorsListByActivitySetting(request,ad,currentProcessInstanceId,autoSignatureActorType, ad.getAutoSignatureActorValue(), modelData,this,"signature");
								}
							}
							String autoReadType = ad.getAutoReadActorType();
							if(null != autoReadType){
								readDealUsers = JBPMUtil.getActorsListByActivitySetting(request,ad,currentProcessInstanceId,autoReadType, ad.getAutoReadActorValue(), modelData,this,"read");
							}
						}
						taskUsers.setDealUsers(dealUsers);
						taskUsers.setReadDealUsers(readDealUsers);
						taskUsers.setSignatureUsers(signatureUsers);
						activityTaskUsers.setCurrentTaskUsers(taskUsers);
						all_activityTaskUsersList.add(activityTaskUsers);
						break;
					}
				}
			}
		}
		//modify by tracywindy 2014-02-11
		/*String conditionRouteType = activityDetail.getConditionRouteType();
		String conditionRouteValue = activityDetail.getConditionRouteValue();
		this.getDynamicRequestInitiatorRoute(request, conditionRouteType, conditionRouteValue,modelData);*/
		modelData.remove("isHasPutRequestParameters");
		return all_activityTaskUsersList;
	}
	//获取历史信息列表
	@Override
	public List<JBPMWorkflowHistoryInfo> getProcessedHistoryLogInfoList(String historyProcessInstanceImplDbid,String currentJbpmWorkflowHistoryInfoId) throws Exception{
		List<Object> sourceProcessedHistoryLogInfoList = this.jbpmDao.getSinglObjectListByHSQL(" from  JBPMWorkflowHistoryInfo jwhi where jwhi.historyProcessInstanceImpl.dbid = ? and jwhi.historyTaskInstanceImpl is not null order by jwhi.historyTaskInstanceImpl.endTime desc,jwhi.historyTaskInstanceImpl.startTime desc",Long.parseLong(historyProcessInstanceImplDbid));
		List<JBPMWorkflowHistoryInfo> prefixProcessedHistoryLogInfoList = new ArrayList<JBPMWorkflowHistoryInfo>();
		List<JBPMWorkflowHistoryInfo> suffixProcessedHistoryLogInfoList = new ArrayList<JBPMWorkflowHistoryInfo>();
		/**
		 * 针对于流程审批意见互斥
		 * 判断 WorkFlowInfo 所在的 activity 是否与当前结点的流程的activity 想冲突
		 */
		JBPMWorkflowHistoryInfo currentWorkFlowInfo = this.jbpmDao.findEntityByID(JBPMWorkflowHistoryInfo.class, currentJbpmWorkflowHistoryInfoId);
		ActivityDetail currentDetail = currentWorkFlowInfo.getActivityDetail();
		//查询当前结点是否由于互斥权限
		Boolean isExclude = false;
		Map<String, ActivityDetail> exludeAcitiviesMap = new HashMap<String, ActivityDetail>();
		if(currentDetail.getIsNeedAdviseExclude()){
			String messageExcludeActivies =  currentDetail.getExcludeMessageActivities();
			if(null != messageExcludeActivies && messageExcludeActivies.length() > 0){
				isExclude = true;
				String [] ids = messageExcludeActivies.split(",");
				List<ActivityDetail> excludeActivities =  this.jbpmDao.findResultsByNamedParamHSQL("from ActivityDetail where id in (:ids) ", new String[]{"ids"}, new Object[]{ids});
				for(ActivityDetail activity : excludeActivities){
					exludeAcitiviesMap.put(activity.getId(), activity);
				}
			}
		}
		for(int si=0;si<sourceProcessedHistoryLogInfoList.size();si++)
		{
			JBPMWorkflowHistoryInfo obj = (JBPMWorkflowHistoryInfo)sourceProcessedHistoryLogInfoList.get(si);
			//判断该代办是不是当前流程代办的退回代办
			Date taskEndTime = obj.getHistoryTaskInstanceImpl().getEndTime();
			if(currentWorkFlowInfo.getBackedHistoryInfo() !=null && currentWorkFlowInfo.getBackedHistoryInfo().getId().equals(obj.getId())){
				obj.setExclusionMessageFlag(false);
			}else if(isExclude){
				if(exludeAcitiviesMap.containsKey(obj.getActivityDetail().getId())){
					obj.setExclusionMessageFlag(true);
				}else{
					obj.setExclusionMessageFlag(false);
				}
			}else{
				obj.setExclusionMessageFlag(false);
			}
			if(null == taskEndTime)
			{
				prefixProcessedHistoryLogInfoList.add(obj);
			}
			else
			{
				suffixProcessedHistoryLogInfoList.add(obj);
			}
		}
		prefixProcessedHistoryLogInfoList.addAll(suffixProcessedHistoryLogInfoList);
		return prefixProcessedHistoryLogInfoList;
	}
	@Override
	public void getRequestFormDealedInfo(HttpServletRequest request,JBPMWorkflowHistoryInfo jhi_completedTaskImpl,JBPMWorkflowHistoryInfo jhi_processInstance,Map<String,String> modelData,Set<String> activityNames) throws Exception
	{
		//追加task附加信息，但是变量不进行数据库存储
		ActivityDetail activityDetail = jhi_completedTaskImpl.getActivityDetail();
		HistoryTaskInstanceImpl historyTaskInstanceImpl = jhi_completedTaskImpl.getHistoryTaskInstanceImpl();
		DeploymentImpl deploymentImpl = jhi_completedTaskImpl.getDeploymentImpl();
		String deployId = String.valueOf(deploymentImpl.getDbid());
		HistoryProcessInstanceImpl historyProcessInstanceImpl = jhi_completedTaskImpl.getHistoryProcessInstanceImpl();
		//流程表单内置变量
		modelData.put("currentTaskId", String.valueOf(historyTaskInstanceImpl.getHistoryTask().getId()));
		modelData.put("currentDeployId",deployId );
		modelData.put("currentTaskName", activityDetail.getActivityName());
		Set<HistoryTaskInstanceImpl>  firstHistoryTaskInstanceImpls = jhi_processInstance.getFirstHistoryTaskInstanceImpls();
		boolean isFirst = false;
		for(HistoryTaskInstanceImpl firstHistoryTaskInstanceImpl : firstHistoryTaskInstanceImpls){
			if(activityDetail.getActivityKey().equals(firstHistoryTaskInstanceImpl.getActivityName()))
			{
				isFirst = true;
			}
			break;
		}
		modelData.put("isFirstTask",String.valueOf(isFirst).toLowerCase());
		HistoryTaskInstanceImpl previousHistoryTaskInstanceImpl = jhi_completedTaskImpl.getPreviousHistoryTaskInstanceImpl();
		if(null != previousHistoryTaskInstanceImpl)
		{
			for(ActivityDetail ad : deploymentImpl.getActivityDetails()){
				if(previousHistoryTaskInstanceImpl.getActivityName().equals(ad.getActivityKey())){
					modelData.put("previousTaskName",ad.getActivityName());
					break;
				}
			}
			//modelData.put("previousTaskName",previousHistoryTaskInstanceImpl.getActivityName());
		}
		modelData.put("currentJbpmWorkflowHistoryInfoId",jhi_completedTaskImpl.getId() );
		modelData.put("currentWorkFlowName",jhi_completedTaskImpl.getWorkflowName() );
		modelData.put("currentWorkFlowDisplayName",jhi_completedTaskImpl.getWorkflowDisplayName() );
		modelData.put("currentTaskActivityName",activityDetail.getActivityName());
		modelData.put("currentTaskActivityDetailId",activityDetail.getId());
		modelData.put(this.conditionRouteType,activityDetail.getConditionRouteType());
		modelData.put("currentProcessInstanceDBID",String.valueOf(historyProcessInstanceImpl.getDbid()));
		modelData.put("currentProcessInstanceId",historyProcessInstanceImpl.getProcessInstanceId());
		modelData.put("currentTaskFormPath", StringUtil.nullToString(jhi_completedTaskImpl.getProcessFormPath())); 
		modelData.put("currentTaskFormTitle",StringUtil.empty2Other(jhi_completedTaskImpl.getProcessFormTitle(), activityDetail.getActivityName())); 
		modelData.put("currentTaskOperationButtons", StringUtil.nullToString(activityDetail.getOperationButtons())); 
		modelData.put("currentTaskIsNeedAdvise", StringUtil.nullToString(activityDetail.getIsNeedAdvise())); 
		modelData.put("currentTaskIsAttachmentChecked", (null == activityDetail.getIsAttachmentChecked()) ? "false" :  activityDetail.getIsAttachmentChecked().toString().toLowerCase()); 
		//条件路由
		modelData.put("currentConditionRouteType",activityDetail.getConditionRouteType()); 
		modelData.put("currentConditionRouteValue", activityDetail.getConditionRouteValue()); 
		
		modelData.put("processInstanceState", jhi_completedTaskImpl.getProcessInstanceState()); 
		//节点配置明细
		request.setAttribute("currentHistoryTaskInfo", jhi_completedTaskImpl);
		request.setAttribute("currentTaskActivityDetailInfo",activityDetail);
		//传阅、工作指派、会签
		JBPMWorkflowHistoryInfoUser jbpmWorkflowHistoryInfoUser = (JBPMWorkflowHistoryInfoUser)this.jbpmDao.findEntityByID(JBPMWorkflowHistoryInfoUser.class, StringUtil.empty2Other(modelData.get("jbpmWorkflowHistoryInfoUserId"), UUIDUtil.getUUID()));
		request.setAttribute("currentJBPMWorkflowHistoryInfoUser", jbpmWorkflowHistoryInfoUser);
		request.setAttribute("currentJBPMWorkflowReadUsersList", jhi_completedTaskImpl.getJbpmWorkflowReadUsers());
		request.setAttribute("currentJBPMWorkflowSignatureUsersList", jhi_completedTaskImpl.getJbpmWorkflowSignatureUsers());
		if((null != jbpmWorkflowHistoryInfoUser)&&(null != jbpmWorkflowHistoryInfoUser.getAssignActor()))
		{
			modelData.put("assignmentUserId",jbpmWorkflowHistoryInfoUser.getAssignActor().getId());
		}
		if(null != jbpmWorkflowHistoryInfoUser&&(null != jbpmWorkflowHistoryInfoUser.getAssignedActor()))
		{
			modelData.put("assignmentedUserId",jbpmWorkflowHistoryInfoUser.getAssignedActor().getId());
		}
		StringBuffer sb_readUserIds = new StringBuffer();
		StringBuffer sb_readUserRealNames = new StringBuffer();
		StringBuffer sb_signatureUserIds = new StringBuffer();
		StringBuffer sb_signatureUserRealNames = new StringBuffer();
		int currentIndex = 0;
		for(JBPMWorkflowReadUser jbpmWorkflowReadUser : jhi_completedTaskImpl.getJbpmWorkflowReadUsers() )
		{
			if(0 < currentIndex++)
			{
				sb_readUserIds.append(",");
				sb_readUserRealNames.append(",");
			}
			User readedActor = jbpmWorkflowReadUser.getReadedActor();
			sb_readUserIds.append(readedActor.getId());
			sb_readUserRealNames.append(readedActor.getRealname()+"（"+readedActor.getUsername()+"）");
		}
		currentIndex = 0;
		for(JBPMWorkflowSignatureUser jbpmWorkflowSignatureUser : jhi_completedTaskImpl.getJbpmWorkflowSignatureUsers())
		{
			if(0 < currentIndex++)
			{
				sb_signatureUserIds.append(",");
				sb_signatureUserRealNames.append(",");
			}
			User signaturedActor = jbpmWorkflowSignatureUser.getSignaturedActor();
			sb_signatureUserIds.append(signaturedActor.getId());
			sb_signatureUserRealNames.append(signaturedActor.getRealname()+"（"+signaturedActor.getUsername()+"）");
		}
		modelData.put("readUserIds", sb_readUserIds.toString());
		modelData.put("readUserRealNames", sb_readUserRealNames.toString());
		modelData.put("signatureUserIds", sb_signatureUserIds.toString());
		modelData.put("signatureUserRealNames", sb_signatureUserRealNames.toString());
		/*
		//回退list 
		request.setAttribute("back_activityTaskUsersList", this.getBackActivityTaskUsersList(request, jhi_completedTaskImpl.getId(), modelData));
		//下一步list
		request.setAttribute("next_activityTaskUsersList", this.getNextActivityTaskUsersList(request, jhi_completedTaskImpl.getId(), modelData,historyProcessInstanceImpl.getDbid()));
		//任意路由
		request.setAttribute("all_activityTaskUsersList", this.getAllActivityTaskUsersList(request, jhi_completedTaskImpl.getId(), modelData));
		//历史信息列表
		request.setAttribute("processedHistoryLogInfoList",this.getProcessedHistoryLogInfoList(historyProcessInstanceImpl.getDbid()) );
		*/
		jhi_completedTaskImpl.setIsReaded(true);
		this.updateEntity(jhi_completedTaskImpl);
	}
	@Override
	public void addReadPersonToTask(JBPMWorkflowHistoryInfo jhi_completedTaskImpl,ActivityDetail assignToTaskAd,Map<String,String> modelData,String jbpmWorkflowHistoryInfoUserId,User readActor,String userIds,String splitStr,String readAdvise,List<User> userList) throws Exception
	{
	   JBPMWorkflowHistoryInfoUser jbpmWorkflowHistoryInfoUser = (JBPMWorkflowHistoryInfoUser)this.jbpmDao.findEntityByID(JBPMWorkflowHistoryInfoUser.class, jbpmWorkflowHistoryInfoUserId);
	   User planActor = null;
	   if(null != jbpmWorkflowHistoryInfoUser)
	   {
		   planActor = jbpmWorkflowHistoryInfoUser.getPlanActor();   
	   }
	   else
	   {
		   JBPMWorkflowReadUser jwru = (JBPMWorkflowReadUser)this.jbpmDao.findEntityByID(JBPMWorkflowReadUser.class, jbpmWorkflowHistoryInfoUserId);      
		   planActor = jwru.getPlanActor();
	   }
	   Set<JBPMWorkflowReadUser>  jbpmWorkflowReadUsers = jhi_completedTaskImpl.getJbpmWorkflowReadUsers();
	   if(null != userList)
	   {
		   for(User u : userList)
		   {
			   String userId = u.getId();
			   boolean isExist = false;
			   for(JBPMWorkflowReadUser jbpmWorkflowReadUser : jbpmWorkflowReadUsers)
			   {
				   String id = jbpmWorkflowReadUser.getReadedActor().getId();
				   if(id.equals(userId))
				   {
				       isExist = true;
				       break;
				   }
			   }
			   if(!isExist)
			   {
				   JBPMWorkflowReadUser readUser = new JBPMWorkflowReadUser();
				   readUser.setPlanActor(planActor);
				   readUser.setReadActor(readActor);
				   readUser.setJbpmWorkflowHistoryInfo(jhi_completedTaskImpl);
				   readUser.setStartTime(DateUtil.getSystemDateTime());
				   readUser.setReadAdvise(readAdvise);
				   User readedActor = u;
				   readUser.setReadedActor(readedActor);
				   jbpmWorkflowReadUsers.add(readUser);
				   this.jbpmDao.getHibernateTemplate().save(readUser);
				   addWorkFlowAppPush(readUser.getId());
				   //自动传阅发送消息提醒
				   this.sendMessageNoticeToUser(jhi_completedTaskImpl.getWorkflowDisplayName(), assignToTaskAd, readedActor, modelData, SubmitTypeEnum.READ);
			   }
		   }
	   }

	   Collection<String> userIdsList = StringUtil.getCollectionByString("list", userIds, splitStr)	;
	   for(String userId : userIdsList)
	   {
		   boolean isExist = false;
		   for(JBPMWorkflowReadUser jbpmWorkflowReadUser : jbpmWorkflowReadUsers)
		   {
			   String id = jbpmWorkflowReadUser.getReadedActor().getId();
			   if(id.equals(userId))
			   {
			       isExist = true;
			       break;
			   }
		   }
		   if(!isExist)
		   {
			   JBPMWorkflowReadUser readUser = new JBPMWorkflowReadUser();
			   readUser.setPlanActor(planActor);
			   readUser.setReadActor(readActor);
			   readUser.setJbpmWorkflowHistoryInfo(jhi_completedTaskImpl);
			   readUser.setStartTime(DateUtil.getSystemDateTime());
			   readUser.setReadAdvise(readAdvise);
			   User readedActor = (User)this.jbpmDao.findEntityByID(User.class, userId);
			   readUser.setReadedActor(readedActor);
			   jbpmWorkflowReadUsers.add(readUser);
			   this.jbpmDao.getHibernateTemplate().save(readUser);
			   addWorkFlowAppPush(readUser.getId());
			   //继续传阅发送消息提醒
			   this.sendMessageNoticeToUser(jhi_completedTaskImpl.getWorkflowDisplayName(), jhi_completedTaskImpl.getActivityDetail(), readedActor, modelData, SubmitTypeEnum.READ);
		   }
	   }
	}
	@Override
	public void addSignaturePersonToTask(JBPMWorkflowHistoryInfo jhi_completedTaskImpl,Map<String,String> modelData,String jbpmWorkflowHistoryInfoUserId,User signatureActor,String userIds,String splitStr,String signatureAdvise,List<User> userList) throws Exception
	{
		   JBPMWorkflowHistoryInfoUser jbpmWorkflowHistoryInfoUser = (JBPMWorkflowHistoryInfoUser)this.jbpmDao.findEntityByID(JBPMWorkflowHistoryInfoUser.class, jbpmWorkflowHistoryInfoUserId);
		   
		   User planActor = null;
		   if(null != jbpmWorkflowHistoryInfoUser)
		   {
			   planActor = jbpmWorkflowHistoryInfoUser.getPlanActor();   
		   }
		   else
		   {
			   JBPMWorkflowSignatureUser jwsu = (JBPMWorkflowSignatureUser)this.jbpmDao.findEntityByID(JBPMWorkflowSignatureUser.class, jbpmWorkflowHistoryInfoUserId);      
			   planActor = jwsu.getPlanActor();
		   }
		   Set<JBPMWorkflowSignatureUser>  jbpmWorkflowSignatureUsers = jhi_completedTaskImpl.getJbpmWorkflowSignatureUsers();
		   if(null != userList)
		   {
			   for(User u:userList)
			   {
				   String userId = u.getId();
				   boolean isExist = false;
				   for(JBPMWorkflowSignatureUser jbpmWorkflowSignatureUser : jbpmWorkflowSignatureUsers)
				   {
					   String id = jbpmWorkflowSignatureUser.getSignaturedActor().getId();
					   if(id.equals(userId))
					   {
					    	   isExist = true;
					    	   break;
					   }
				   }
				   if(!isExist)
				   {
					   JBPMWorkflowSignatureUser signatureUser = new JBPMWorkflowSignatureUser();
					   signatureUser.setPlanActor(planActor);
					   signatureUser.setSignatureActor(signatureActor);
					   signatureUser.setJbpmWorkflowHistoryInfo(jhi_completedTaskImpl);
					   signatureUser.setStartTime(DateUtil.getSystemDateTime());
					   signatureUser.setSignatureAdvise(signatureAdvise);
					   User signaturedActor = u;
					   signatureUser.setSignaturedActor(signaturedActor);
					   jbpmWorkflowSignatureUsers.add(signatureUser);
					   this.jbpmDao.getHibernateTemplate().save(signatureUser);
					   addWorkFlowAppPush(signatureUser.getId());
					   //会签发送消息提醒
					   this.sendMessageNoticeToUser(jhi_completedTaskImpl.getWorkflowDisplayName(), jhi_completedTaskImpl.getActivityDetail(), signaturedActor, modelData, SubmitTypeEnum.SIGNATURE);
				   }
			   }
			   return;
		   }
		   Collection<String> userIdsList = StringUtil.getCollectionByString("list", userIds, splitStr)	;
		   for(String userId : userIdsList)
		   {
			   boolean isExist = false;
			   for(JBPMWorkflowSignatureUser jbpmWorkflowSignatureUser : jbpmWorkflowSignatureUsers)
			   {
				   String id = jbpmWorkflowSignatureUser.getSignaturedActor().getId();
				   if(id.equals(userId))
				   {
				    	   isExist = true;
				    	   break;
				   }
			   }
			   if(!isExist)
			   {
				   JBPMWorkflowSignatureUser signatureUser = new JBPMWorkflowSignatureUser();
				   signatureUser.setPlanActor(planActor);
				   signatureUser.setSignatureActor(signatureActor);
				   signatureUser.setJbpmWorkflowHistoryInfo(jhi_completedTaskImpl);
				   signatureUser.setStartTime(DateUtil.getSystemDateTime());
				   signatureUser.setSignatureAdvise(signatureAdvise);
				   User signaturedActor = (User)this.jbpmDao.findEntityByID(User.class, userId);
				   signatureUser.setSignaturedActor(signaturedActor);
				   jbpmWorkflowSignatureUsers.add(signatureUser);
				   this.jbpmDao.getHibernateTemplate().save(signatureUser);
				   addWorkFlowAppPush(signatureUser.getId());
				   //继续会签发送消息提醒
				   this.sendMessageNoticeToUser(jhi_completedTaskImpl.getWorkflowDisplayName(), jhi_completedTaskImpl.getActivityDetail(), signaturedActor, modelData, SubmitTypeEnum.SIGNATURE);
			   }
		   }
	}
	public void removeReadPersonToTaskByFlowUnid(String jbpmWorkflowHistoryInfoId,String readedAdvise) throws Exception
	{
		//当前用户信息
		User user = (User)SecurityUtil.getPrincipal();
		String querySql = "from JBPMWorkflowReadUser jwru where jwru.jbpmWorkflowHistoryInfo.historyProcessInstanceImpl.dbid = ? and jwru.readedActor.id = ?";
		JBPMWorkflowReadUser readedUser = (JBPMWorkflowReadUser)this.jbpmDao.getSinglObjectListByHSQL(querySql, Double.valueOf(jbpmWorkflowHistoryInfoId).longValue(),user.getId()).get(0);
		readedUser.setEndTime(DateUtil.getSystemDateTime());
		readedUser.setReadedAdvise(readedAdvise);
		this.jbpmDao.getHibernateTemplate().update(readedUser);
	}
	@Override
	public void removeSignaturePersonToTask(String jbpmWorkflowHistoryInfoId,String signaturedAdvise) throws Exception
	{
		//当前用户信息
		User user = (User)SecurityUtil.getPrincipal();
		String querySql = "from JBPMWorkflowSignatureUser jwsu where jwsu.jbpmWorkflowHistoryInfo.id = ? and jwsu.signaturedActor.id = ?";
		JBPMWorkflowSignatureUser signaturedUser = (JBPMWorkflowSignatureUser)this.jbpmDao.getSinglObjectListByHSQL(querySql, jbpmWorkflowHistoryInfoId,user.getId()).get(0);
		signaturedUser.setEndTime(DateUtil.getSystemDateTime());
		signaturedUser.setSignaturedAdvise(signaturedAdvise);
		this.jbpmDao.getHibernateTemplate().update(signaturedUser);
		String hql = "update JbpmWorkFlowAppPush set isPush = true where taskId = ?";
		this.jbpmDao.updateByHSQL(hql, signaturedUser.getId());
	}
	@Override
	public void removeReadPersonToTask(String jbpmWorkflowHistoryInfoId,String readedAdvise) throws Exception
	{
		//当前用户信息
		User user = (User)SecurityUtil.getPrincipal();
		String querySql = "from JBPMWorkflowReadUser jwru where jwru.jbpmWorkflowHistoryInfo.id = ? and jwru.readedActor.id = ?";
		JBPMWorkflowReadUser readedUser = (JBPMWorkflowReadUser)this.jbpmDao.getSinglObjectListByHSQL(querySql, jbpmWorkflowHistoryInfoId,user.getId()).get(0);
		readedUser.setEndTime(DateUtil.getSystemDateTime());
		readedUser.setReadedAdvise(readedAdvise);
		this.jbpmDao.getHibernateTemplate().update(readedUser);
		String hql = "update JbpmWorkFlowAppPush set isPush = true where taskId = ?";
		this.jbpmDao.updateByHSQL(hql, readedUser.getId());
	}
	@Override
	public void addWorkAssignmentPersonToTask(JBPMWorkflowHistoryInfo jhi_completedTaskImpl,String jbpmWorkflowHistoryInfoUserId,String assignmentUserId,String processedAdvise) throws Exception
	{
	    JBPMWorkflowHistoryInfoUser jbpmWorkflowHistoryInfoUser = (JBPMWorkflowHistoryInfoUser)this.jbpmDao.findEntityByID(JBPMWorkflowHistoryInfoUser.class, jbpmWorkflowHistoryInfoUserId);
	    User newPlanActor = (User)this.jbpmDao.findEntityByID(User.class, assignmentUserId);
	    User oldPlanActor = jbpmWorkflowHistoryInfoUser.getPlanActor();
	    jbpmWorkflowHistoryInfoUser.setAssignActor(oldPlanActor);
	    jbpmWorkflowHistoryInfoUser.setPlanActor(newPlanActor);
		if((null !=processedAdvise) &&(!processedAdvise.isEmpty()))
		{
		 	jbpmWorkflowHistoryInfoUser.setAssignAdvise(processedAdvise);
	 	}
		this.jbpmDao.getHibernateTemplate().update(jbpmWorkflowHistoryInfoUser);
		addWorkFlowAppPush(jbpmWorkflowHistoryInfoUser.getId());
	}
	@Override
	public String submitProcessedTaskForm(HttpServletRequest request,String currentTaskId, Map<String,String> modelData) throws Exception 
	{
		
		Locale locale = WebUtil.getSessionLocale();
		//流程处理标志
		String flag = "submit";
		//当前用户信息
		User user = (User)SecurityUtil.getPrincipal();
		RepositoryService repositoryService = JBPMUtil.getRepositoryService();
		String saveActionReturnResult = "";
	 	String jbpmWorkflowHistoryInfoUserId = modelData.get("jbpmWorkflowHistoryInfoUserId");
		String currentTaskSubmitButtonText= StringUtil.nullToString(request.getParameter("currentTaskSubmitButtonText"));
		String query_task="from JBPMWorkflowHistoryInfo jwhi where jwhi.historyTaskInstanceImpl.historyTask.dbid = ?";
		JBPMWorkflowHistoryInfo jhi_completedTaskImpl_inner = (JBPMWorkflowHistoryInfo)this.jbpmDao.getSinglObjectListByHSQL(query_task,Long.parseLong(currentTaskId)).get(0);
		HistoryProcessInstanceImpl historyProcessInstanceImpl = jhi_completedTaskImpl_inner.getHistoryProcessInstanceImpl();
		//流程实例中的变量 
		JBPMUtil.getVariablesInfo(this.jbpmDao.getHibernateTemplate(),String.valueOf(historyProcessInstanceImpl.getDbid()),null, modelData);
		//回调action执行
		ActivityDetail currentActivityDetail = jhi_completedTaskImpl_inner.getActivityDetail();
	 	String taskAction = StringUtil.nullToString(currentActivityDetail.getActivityAction());
	 	if(currentActivityDetail.getConditionRouteType() != null ){
	 		modelData.put("conditionRouteType", currentActivityDetail.getConditionRouteType());
	 	}
	 	//是否是退回
	 	boolean isBack = WorkflowSubmitTextEnum.backButtonDisplayText.getText().equals(currentTaskSubmitButtonText);
		
		if(WorkflowSubmitTextEnum.readButtonDisplayText.getText().equals(currentTaskSubmitButtonText))
		{
			String userIds = modelData.get("readUserIds");
			String processedAdvise = modelData.get("processedAdvise");
			this.addReadPersonToTask(jhi_completedTaskImpl_inner,null,modelData,jbpmWorkflowHistoryInfoUserId, user, userIds, ",",processedAdvise,null);
			flag = "read";
			return saveActionReturnResult;
		}
		else if(WorkflowSubmitTextEnum.signatureButtonDisplayText.getText().equals(currentTaskSubmitButtonText))
		{
			String userIds = modelData.get("signatureUserIds");
			String processedAdvise = modelData.get("processedAdvise");
			this.addSignaturePersonToTask(jhi_completedTaskImpl_inner,modelData,jbpmWorkflowHistoryInfoUserId, user, userIds, ",",processedAdvise,null);
			flag = "signature";
			return saveActionReturnResult;
		}
		else if(WorkflowSubmitTextEnum.assignmentButtonDisplayText.getText().equals(currentTaskSubmitButtonText))
		{
			flag = "workAssignment";
			String assignmentedUserId = modelData.get("assignmentedUserId");
			String processedAdvise = modelData.get("processedAdvise");
			this.addWorkAssignmentPersonToTask(jhi_completedTaskImpl_inner, jbpmWorkflowHistoryInfoUserId, assignmentedUserId, processedAdvise);
			return saveActionReturnResult;
		}
		else if(WorkflowSubmitTextEnum.deleteButtonNoSavedDisplayText.getText().equals(currentTaskSubmitButtonText))
		{
			String hsql = "select jwhi from JBPMWorkflowHistoryInfo jwhi where jwhi.historyProcessInstanceImpl.dbid = ? and historyTaskInstanceImpl is null";
			long historyProcessInstanceDBID = historyProcessInstanceImpl.getDbid();
			JBPMWorkflowHistoryInfo processInstanceHistoryInfo = (JBPMWorkflowHistoryInfo)this.findResultsByHSQL(hsql, historyProcessInstanceDBID).get(0);
			Boolean isRemovedDraft = processInstanceHistoryInfo.getIsRemovedDraft();
			if((null != isRemovedDraft)&& isRemovedDraft){
				this.removeProcessInstance(request, String.valueOf(historyProcessInstanceDBID));
			}
			return saveActionReturnResult;
		}
		else if(WorkflowSubmitTextEnum.deleteButtonDisplayText.getText().equals(currentTaskSubmitButtonText))
		{
			this.removeProcessInstance(request, String.valueOf(historyProcessInstanceImpl.getDbid()));
			return saveActionReturnResult;
		}
		else if(WorkflowSubmitTextEnum.abondonButtonDisplayText.getText().equals(currentTaskSubmitButtonText))
		{
			this.updateAbandonWorkflowProcessInstance(request,historyProcessInstanceImpl.getDbid());
			return saveActionReturnResult;
		}
		//任务节点
		TaskService taskService = JBPMUtil.getTaskService();
		TaskImpl completedTaskImpl  = (TaskImpl)taskService.getTask(currentTaskId);
		if(null == completedTaskImpl){
			throw new JbpmException("该任务节点已被成功提交至下一步！");
		}
		ProcessInstance processInstance = completedTaskImpl.getProcessInstance();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processInstance.getProcessDefinitionId()).uniqueResult();
		String deployId = processDefinition.getDeploymentId();
		List<ActivityDetail> activitiesDetailList = this.jbpmDao.getActivitiesDetailListByDeployId(deployId);
		/*if(!WorkflowSubmitTextEnum.saveButtonDisplayText.getText().equals(currentTaskSubmitButtonText))
		{
				Set<String> nodeSet = processInstance.findActiveActivityNames();
				String []nodesNameArr = new String[nodeSet.size()];
				int indx = 0;
				for(String activityKey : nodeSet){
					for(ActivityDetail ad : activitiesDetailList){
						if(activityKey.equals(ad.getActivityKey())){
							nodesNameArr[indx++] = ad.getActivityName();
							break;
						}
					}
				}
				//nodesNameArr = nodeSet.toArray(nodesNameArr);
				Arrays.sort(nodesNameArr);
				saveActionReturnResult = WebUtil.getMessageByLocale(currentTaskSubmitButtonText, null, currentTaskSubmitButtonText, locale)+WebUtil.getMessageByLocale(WorkflowSubmitTextEnum.messageComfirmSuccess.getText(),null,WorkflowSubmitTextEnum.messageComfirmSuccess.getText(),locale)+" < "+StringUtil.join(nodesNameArr, ",")+" > ";
		}*/
		
		DeploymentImpl deploymentImpl = (DeploymentImpl)this.jbpmDao.getSinglObjectListByHSQL("from DeploymentImpl where dbid = ?", Long.parseLong(deployId)).get(0);
		String requestInitiatorRoute = StringUtil.nullToString(modelData.get("requestInitiatorRoute"));
		
		//流程自定义信息
		String query_processInstance="from JBPMWorkflowHistoryInfo jwhi where jwhi.historyProcessInstanceImpl.dbid = ? and jwhi.historyTaskInstanceImpl is null ";
		JBPMWorkflowHistoryInfo history_jhi_processInstance = (JBPMWorkflowHistoryInfo)this.jbpmDao.getSinglObjectListByHSQL(query_processInstance, historyProcessInstanceImpl.getDbid()).get(0);
		if(WorkflowSubmitTextEnum.saveButtonDisplayText.getText().equals(currentTaskSubmitButtonText))
		{
			flag = "save";
			if(!"".equals(taskAction))
			{
				 //modify by tracywindy 2013-07-02
				 modelData.put(this.currentJbpmWorkflowDesignerId, deploymentImpl.getJbpmWorkflowDesigner().getId());
				 modelData.put(this.currentJbpmDepolymentId, deploymentImpl.getDbid()+"");
				 modelData.put(this.currentActivityDetailId, currentActivityDetail.getId());
				saveActionReturnResult = ((JbpmBaseAction)WebUtil.getApplicationContext().getBean(taskAction)).save(request, modelData,jhi_completedTaskImpl_inner);
			}
		}
		else if((WorkflowSubmitTextEnum.resetRouteButtonDisplayText.getText().equals(currentTaskSubmitButtonText))||(WorkflowSubmitTextEnum.backButtonDisplayText.getText().equals(currentTaskSubmitButtonText)))
		{
			if(!"".equals(taskAction))
			{
				JbpmBaseAction endEventAction = (JbpmBaseAction)WebUtil.getApplicationContext().getBean(taskAction);
				if(WorkflowSubmitTextEnum.backButtonDisplayText.getText().equals(currentTaskSubmitButtonText))
				{
					 //modify by tracywindy 2013-07-02
					 modelData.put(this.currentJbpmWorkflowDesignerId, deploymentImpl.getJbpmWorkflowDesigner().getId());
					 modelData.put(this.currentJbpmDepolymentId, deploymentImpl.getDbid()+"");
					 modelData.put(this.currentActivityDetailId, currentActivityDetail.getId());
					endEventAction.back(request,modelData,jhi_completedTaskImpl_inner);
				}
			}
			flag = "submit";
			String destName = modelData.get("choseRequestNextRouteNodeName");
			String sourceName = currentActivityDetail.getActivityKey();
			JBPMUtil.dynamicTempOutTransition(deployId, currentTaskId, sourceName, destName);
		}
		else if((WorkflowSubmitTextEnum.submitButtonDisplayText.getText().equals(currentTaskSubmitButtonText)))
		{
			flag = "submit";
			boolean isSubmit = false;
			String assignmentUserId = modelData.get("assignmentUserId");
			String assignmentedUserId = modelData.get("assignmentedUserId");
			if((null != assignmentUserId)&&(null == assignmentedUserId))
			{
				isSubmit = false;
			}
			else
			{
				if(!"".equals(taskAction))
				{
					JbpmBaseAction endEventAction = (JbpmBaseAction)WebUtil.getApplicationContext().getBean(taskAction);
					 //modify by tracywindy 2013-07-02
					 modelData.put(this.currentJbpmWorkflowDesignerId, deploymentImpl.getJbpmWorkflowDesigner().getId());
					 modelData.put(this.currentJbpmDepolymentId, deploymentImpl.getDbid()+"");
					 modelData.put(this.currentActivityDetailId, currentActivityDetail.getId());
					 endEventAction.end(request,modelData,jhi_completedTaskImpl_inner);
					 /*
					 //modify by tracywindy 2013-07-02
					 //结束时自动传阅和会签功能
					 //String jbpmWorkflowHistoryInfoUserId = modelData.get("jbpmWorkflowHistoryInfoUserId");
					 String autoReadActorType= StringUtil.nullToString(currentActivityDetail.getAutoReadActorType()); 
					 String processedAdvise = modelData.get("processedAdvise");
					 if(!autoReadActorType.isEmpty())
					 {
						 List<User> userList = JBPMUtil.getActorsListByActivitySetting(currentActivityDetail,jhi_completedTaskImpl_inner,autoReadActorType, currentActivityDetail.getAutoReadActorValue(), modelData, this,"read");
						 if(userList.size()>0){
							 this.addReadPersonToTask(jhi_completedTaskImpl_inner, jbpmWorkflowHistoryInfoUserId, user,null, ",", processedAdvise,userList);
						 }
					 }
					 String autoSignatureActorType= StringUtil.nullToString(currentActivityDetail.getAutoSignatureActorType()); 
					 if(!autoSignatureActorType.isEmpty())
					 {
						 List<User> userList = JBPMUtil.getActorsListByActivitySetting(currentActivityDetail,jhi_completedTaskImpl_inner,autoSignatureActorType, currentActivityDetail.getAutoSignatureActorValue(), modelData, this,"signature");
						 if(userList.size()>0){
							 this.addSignaturePersonToTask(jhi_completedTaskImpl_inner, jbpmWorkflowHistoryInfoUserId, user,null, ",", processedAdvise,userList);
						 }
					}
					*/
				}
				isSubmit = JBPMUtil.isSubmitPassed(currentActivityDetail, jhi_completedTaskImpl_inner);
				if(isSubmit)
				{
					if(requestInitiatorRoute.trim().isEmpty())
					{
						taskService.completeTask(currentTaskId);
					}
					else if("dynamicLastBackRoutePath".equals(requestInitiatorRoute.trim())){
						String destName = modelData.get("choseRequestNextRouteNodeName");
						String sourceName = currentActivityDetail.getActivityKey();
						JBPMUtil.dynamicTempOutTransition(deployId, currentTaskId, sourceName, destName);
					}
					else
					{
						taskService.completeTask(currentTaskId,requestInitiatorRoute);
					}
					String dealMethod = currentActivityDetail.getDealMethod();
					if("OnePassed".equals(dealMethod)||("NPassed".equals(dealMethod))){
						Set<JBPMWorkflowHistoryInfoUser>  jbpmWorkflowHistoryInfoUsers = jhi_completedTaskImpl_inner.getJbpmWorkflowHistoryInfoUsers();
						for(JBPMWorkflowHistoryInfoUser jbpmWorkflowHistoryInfoUser : jbpmWorkflowHistoryInfoUsers)
						{
							jbpmWorkflowHistoryInfoUser.setEndTime(DateUtil.getSystemDateTime());
							this.jbpmDao.updateEntity(jbpmWorkflowHistoryInfoUser);
							/*String hql = "update JbpmWorkFlowAppPush set isPush = true where taskId = ?";
							this.jbpmDao.updateByHSQL(hql, jbpmWorkflowHistoryInfoUser.getId());*/
						}
					}
				}
			}
		}
		else{
			throw new Exception( WebUtil.getMessageByLocale(WorkflowSubmitTextEnum.noDefinedDisplayText.getText(),null,WorkflowSubmitTextEnum.noDefinedDisplayText.getText(), locale)+" < "+WebUtil.getMessageByLocale(currentTaskSubmitButtonText, null, currentTaskSubmitButtonText, locale)+" > ");
		}
		if(!WorkflowSubmitTextEnum.saveButtonDisplayText.getText().equals(currentTaskSubmitButtonText))
		{
			if(processInstance.isEnded()){
				saveActionReturnResult =  WebUtil.getMessageByLocale(WorkflowSubmitTextEnum.messageComfirmFinish.getText(),null,WorkflowSubmitTextEnum.messageComfirmFinish.getText(),locale);
			}
			else{
//				Set<String> nodeSet = processInstance.findActiveActivityNames();
//				String []nodesNameArr = new String[nodeSet.size()];
//				nodesNameArr = nodeSet.toArray(nodesNameArr);
//				Arrays.sort(nodesNameArr);
				Set<String> nodeSet = processInstance.findActiveActivityNames();
				String []nodesNameArr = new String[nodeSet.size()];
				int indx = 0;
				for(String activityKey : nodeSet){
					for(ActivityDetail ad : activitiesDetailList){
						if(activityKey.equals(ad.getActivityKey())){
							nodesNameArr[indx++] = ad.getActivityName();
							break;
						}
					}
				}
				Arrays.sort(nodesNameArr);
				saveActionReturnResult = WebUtil.getMessageByLocale(currentTaskSubmitButtonText, null, currentTaskSubmitButtonText, locale)+WebUtil.getMessageByLocale(WorkflowSubmitTextEnum.messageComfirmSuccess.getText(), null, WorkflowSubmitTextEnum.messageComfirmSuccess.getText(), locale)+" < "+StringUtil.join(nodesNameArr, ",")+" > ";
			}
		}
		if(completedTaskImpl.getProcessInstance().isEnded()){
			 //当流程开始时执行start节点的startaction 
			 int activitiesDetailListLen = activitiesDetailList.size();
			 for(int i=0;i<activitiesDetailListLen;i++)
			 {
				 ActivityDetail activityDetail = activitiesDetailList.get(i);
				 String activityType =  StringUtil.nullToString(activityDetail.getActivityType());
				 if("end".equalsIgnoreCase(activityType))
				 {
					 String endAction =  StringUtil.nullToString(activityDetail.getActivityAction()).trim();
					 if(!"".equals(endAction))
					 {
						 //JbpmBaseAction startEventAction = ((JbpmBaseAction)Class.forName(startAction).newInstance());
						 JbpmBaseAction endEventAction = (JbpmBaseAction)WebUtil.getApplicationContext().getBean(endAction);
						 //modify by tracywindy 2013-07-02
						 modelData.put(this.currentJbpmWorkflowDesignerId, deploymentImpl.getJbpmWorkflowDesigner().getId());
						 modelData.put(this.currentJbpmDepolymentId, deploymentImpl.getDbid()+"");
						 modelData.put(this.currentActivityDetailId, activityDetail.getId());
						 endEventAction.end(request,modelData,jhi_completedTaskImpl_inner);
					 }
					 break;
				 }
			 }
		}
		this.workflowDataManage(request,jbpmWorkflowHistoryInfoUserId,history_jhi_processInstance,jhi_completedTaskImpl_inner, deploymentImpl, processDefinition, processInstance,historyProcessInstanceImpl, completedTaskImpl, activitiesDetailList, modelData, false,flag,isBack);
		return saveActionReturnResult;
	}
	 /**
	 * (non-Javadoc)
	 * @see com.tenwa.jbpm.service.JbpmService#workflowDataManange(javax.servlet.http.HttpServletRequest, org.jbpm.pvm.internal.repository.DeploymentImpl, org.jbpm.api.ProcessDefinition, org.jbpm.api.ProcessInstance, org.jbpm.pvm.internal.task.TaskImpl, java.util.List, java.util.Map, boolean, java.lang.String)
	 **/
	@Override
	public String workflowDataManage(HttpServletRequest request,String jbpmWorkflowHistoryInfoUserId,JBPMWorkflowHistoryInfo history_jhi_processInstance,JBPMWorkflowHistoryInfo jhi_completedTaskImpl_inner,DeploymentImpl deploymentImpl,ProcessDefinition processDefinition,ProcessInstance processInstance,HistoryProcessInstanceImpl historyProcessInstanceImpl,TaskImpl completedTaskImpl,List<ActivityDetail> activitiesDetailList,Map<String,String> modelData,boolean isStart,String flag,boolean isBack) throws Exception
	{
		 /***
		  * 内置流程变量结束
		  * ***/
		 JbpmWorkflowDesigner jbpmWorkflowDesigner = deploymentImpl.getJbpmWorkflowDesigner();
		 Set<String> activityNames =processInstance.findActiveActivityNames();
		 String returnFormPath = "";
		 TaskService taskService = JBPMUtil.getTaskService();
		 //当前用户信息
		 User user = (User)SecurityUtil.getPrincipal();
		 //task和processinstance通用变量
		 String draftUUID  = modelData.get("draftUUID");
		 String requestInitiator = null;
		 String requestInitiatorRealName = null;
		 String changeRequestInitiator =   null;
		 //modify by tracywindy 2013-08-26 add read and signature
		 String readChangeRequestInitiator = null;
		 String signatureChangeRequestInitiator = null;
		 String processInstanceState = WorkflowSubmitTextEnum.pendingButtonDisplayText.getText();
		 if(isStart)
		 {
			 //流程草稿处理
			 requestInitiator = user.getUsername();
			 requestInitiatorRealName = user.getRealname();
			 modelData.put("requestInitiator",requestInitiator);
			 modelData.put("requestInitiatorRealName",requestInitiatorRealName);
			 processInstanceState = WorkflowSubmitTextEnum.draftButtonDisplayText.getText();
		 }
		 else
		 {
			 requestInitiator = modelData.get("requestInitiator");
			 requestInitiatorRealName = modelData.get("requestInitiatorRealName");
			 changeRequestInitiator = modelData.get("changeRequestInitiator");
			 readChangeRequestInitiator = modelData.get("readChangeRequestInitiator");
			 signatureChangeRequestInitiator = modelData.get("signatureChangeRequestInitiator");
		 }
		 if(processInstance.isEnded())
		 {
			 processInstanceState = WorkflowSubmitTextEnum.finishButtonDisplayText.getText();
		 }
		 if("submit".equalsIgnoreCase(flag))
		 {
			 modelData.put("processInstanceState", processInstanceState);
		 }
		 String processDefinitionId = processDefinition.getId();
		 String processInstanceId = processInstance.getId();
		 String workflowName = processDefinition.getKey();
		 String workflowDisplayName = jbpmWorkflowDesigner.getDisplayName();
		 //task变量
		 String processFormPath = null;
		 String processFormTitle = null;
	     String processedAdvise = request.getParameter("processedAdvise");
		 JBPMWorkflowHistoryInfo jhi_completedTaskImpl_outer = null;
		 //保存整个流程实例历史数据
		 String keyOne = null;
		 String keyTwo = null;
		 String keyThree = null;
		 String keyFour = null;
		 String keyFive = null;
		 String keySix = null;
		 String keySeven = null;
		 String keyEight = null;
		 String keyNine = null;
		 String keyTen = null;
		 
		 String keyOneKey = jbpmWorkflowDesigner.getKeyOne();
		 String keyTwoKey = jbpmWorkflowDesigner.getKeyTwo();
		 String keyThreeKey = jbpmWorkflowDesigner.getKeyThree();
		 String keyFourKey = jbpmWorkflowDesigner.getKeyFour();
		 String keyFiveKey = jbpmWorkflowDesigner.getKeyFive();
		 String keySixKey = jbpmWorkflowDesigner.getKeySix();
		 String keySevenKey = jbpmWorkflowDesigner.getKeySeven();
		 String keyEightKey = jbpmWorkflowDesigner.getKeyEight();
		 String keyNineKey = jbpmWorkflowDesigner.getKeyNine();
		 String keyTenKey  = jbpmWorkflowDesigner.getKeyTen();
		 
		 if(!StringUtils.isBlank(keyOneKey)){keyOne = modelData.get(keyOneKey);}
		 if(!StringUtils.isBlank(keyTwoKey)){keyTwo = modelData.get(keyTwoKey);}
		 if(!StringUtils.isBlank(keyThreeKey)){keyThree = modelData.get(keyThreeKey);}
		 if(!StringUtils.isBlank(keyFourKey)){keyFour = modelData.get(keyFourKey);}
		 if(!StringUtils.isBlank(keyFiveKey)){keyFive = modelData.get(keyFiveKey);}
		 if(!StringUtils.isBlank(keySixKey)){keySix = modelData.get(keySixKey);}
		 if(!StringUtils.isBlank(keySevenKey)){keySeven = modelData.get(keySevenKey);}
		 if(!StringUtils.isBlank(keyEightKey)){keyEight = modelData.get(keyEightKey);}
		 if(!StringUtils.isBlank(keyNineKey)){keyNine = modelData.get(keyNineKey);}
		 if(!StringUtils.isBlank(keyTenKey)){keyTen = modelData.get(keyTenKey);}
		 
		 //实例化流程对象
		 JBPMWorkflowHistoryInfo jhi_processInstance = null;
		 if(isStart)
		 {
			 jhi_processInstance = new JBPMWorkflowHistoryInfo();
			 jhi_processInstance.setHistoryTaskInstanceImpl(null);
			 jhi_processInstance.setHistoryProcessInstanceImpl(historyProcessInstanceImpl);
			 jhi_processInstance.setDeploymentImpl(deploymentImpl);
			 
			 jhi_processInstance.setDraftUUID(draftUUID);
			 jhi_processInstance.setWorkflowName(workflowName);
			 jhi_processInstance.setWorkflowDisplayName(workflowDisplayName);
			 
			 jhi_processInstance.setProcessDefinitionId(processDefinitionId);
			 jhi_processInstance.setRequestInitiatorUser(user);
			 jhi_processInstance.setRequestInitiator(requestInitiator);
			 jhi_processInstance.setRequestInitiatorRealName(requestInitiatorRealName);
		 }
		 else
		 {
			jhi_processInstance = history_jhi_processInstance;
		 }
		 if(!"save".equalsIgnoreCase(flag))
		 {
			 jhi_processInstance.setProcessInstanceState(processInstanceState); 
		 }
		 //将Map转化成stringBuffer,task变量存储
		 jhi_processInstance.setProcessedFormVariables("temp");
		 jhi_processInstance.setProcessedFormValues("temp");
		 
		 if(processInstance.isEnded())
		 {
			 HistoryTaskInstanceImpl lastHistoryTaskInstanceImpl = (HistoryTaskInstanceImpl) this.jbpmDao.getSinglObjectListByHSQL("from HistoryTaskInstanceImpl htii where htii.historyTask.dbid=?", completedTaskImpl.getDbid()).get(0);
			 jhi_processInstance.setLastHistoryTaskInstanceImpl(lastHistoryTaskInstanceImpl);
		 }
		 if(isStart)
		 {
			 jhi_processInstance.setProcessFormPath(returnFormPath);
			 this.jbpmDao.getHibernateTemplate().save(jhi_processInstance);
		 }
		 
		 
		 if(!isStart)
		 {
			 if(null != completedTaskImpl )
			 {
			    //合并变量
			    JBPMUtil.getVariablesInfo(this.jbpmDao.getHibernateTemplate(),String.valueOf(historyProcessInstanceImpl.getDbid()) , null, modelData);
			 	JBPMWorkflowHistoryInfoUser jbpmWorkflowHistoryInfoUser = (JBPMWorkflowHistoryInfoUser)this.jbpmDao.findEntityByID(JBPMWorkflowHistoryInfoUser.class, modelData.get("jbpmWorkflowHistoryInfoUserId"));//this.jbpmDao.getSinglObjectListByHSQL("from JBPMWorkflowHistoryInfoUser jwhiu where jwhiu.jbpmWorkflowHistoryInfo.id = ? and jwhiu.planActor.id = ?",jhi_completedTaskImpl_inner.getId(),modelData.get("planActorId")).get(0);
			 	
			 	if("submit".equalsIgnoreCase(flag))
			 	{
			 		User assignUser = jbpmWorkflowHistoryInfoUser.getAssignActor();
			 		User assignedUser = jbpmWorkflowHistoryInfoUser.getAssignedActor();
			 		
			 		boolean isFinish = true;
			 		if((null != assignUser))
			 		{
                        if(null == assignedUser)
			 			{
			 				isFinish = false;
			 				jbpmWorkflowHistoryInfoUser.setAssignedActor(jbpmWorkflowHistoryInfoUser.getPlanActor());
			 				jbpmWorkflowHistoryInfoUser.setPlanActor(assignUser);
			 			 	if((null !=processedAdvise) &&(!processedAdvise.isEmpty()))
						 	{
						 		jbpmWorkflowHistoryInfoUser.setAssignedAdvise(processedAdvise);
						 	}
			 			}
			 		}
			 		if(isFinish) 
			 		{
				 		jbpmWorkflowHistoryInfoUser.setActualActor(user);
				 		jbpmWorkflowHistoryInfoUser.setEndTime(DateUtil.getSystemDateTime());
					 	if((null !=processedAdvise) &&(!processedAdvise.isEmpty()))
					 	{
					 		jbpmWorkflowHistoryInfoUser.setProcessedAdvise(processedAdvise);
					 	}
			 		}
			 		String hql = "update JbpmWorkFlowAppPush set isPush = true where taskId = ?";
					this.jbpmDao.updateByHSQL(hql, jbpmWorkflowHistoryInfoUser.getId());
			 		
			 	}
			 	else if("save".equals(flag))
			 	{
			 		jbpmWorkflowHistoryInfoUser.setProcessedAdvise(processedAdvise);
			 	}
			 	this.jbpmDao.getHibernateTemplate().update(jbpmWorkflowHistoryInfoUser);
			 	
			 	jhi_completedTaskImpl_inner.setKeyOne(keyOne);
			 	jhi_completedTaskImpl_inner.setKeyTwo(keyTwo);
			 	jhi_completedTaskImpl_inner.setKeyThree(keyThree);
			 	jhi_completedTaskImpl_inner.setKeyFour(keyFour);
			 	jhi_completedTaskImpl_inner.setKeyFive(keyFive);
			 	jhi_completedTaskImpl_inner.setKeySix(keySix);
			 	jhi_completedTaskImpl_inner.setKeySeven(keySeven);
			 	jhi_completedTaskImpl_inner.setKeyEight(keyEight);
			 	jhi_completedTaskImpl_inner.setKeyNine(keyNine);
			 	jhi_completedTaskImpl_inner.setKeyTen(keyTen);
			 	//将Map转化成stringBuffer,task变量存储
			    StringBuffer sb_sourceKeyStr = new StringBuffer();
			    StringBuffer sb_sourceValueStr = new StringBuffer();
			 	JBPMUtil.getVaribalesAllStringByMap(modelData, sb_sourceKeyStr, sb_sourceValueStr);
			    String processedFormVariables =    sb_sourceKeyStr.toString();	
			    String processedFormValues    =    sb_sourceValueStr.toString();
				jhi_completedTaskImpl_inner.setProcessedFormVariables(processedFormVariables);
				jhi_completedTaskImpl_inner.setProcessedFormValues(processedFormValues);
				if("submit".equalsIgnoreCase(flag))
				{
					jhi_completedTaskImpl_inner.setProcessInstanceState(processInstanceState);
				}
				if(!activityNames.contains(completedTaskImpl.getActivityName()))
				{
					HistoryTaskInstanceImpl historyTaskInstanceImpl = jhi_completedTaskImpl_inner.getHistoryTaskInstanceImpl();
					historyTaskInstanceImpl.setActivedProcessInstanceHistoryInfo(null);
					this.jbpmDao.getHibernateTemplate().update(historyTaskInstanceImpl);
				}
			 	this.jbpmDao.getHibernateTemplate().update(jhi_completedTaskImpl_inner);
			 }
		 }
		 //获取流程节点配置信息
		 if((!processInstance.isEnded())&&(("submit".equalsIgnoreCase(flag))||isStart))
		 {
			 for(String activityName_ : activityNames)
			 {
				 TaskImpl taskImpl = (TaskImpl)taskService.createTaskQuery().processInstanceId(processInstanceId).activityName(activityName_).uniqueResult();
				 HistoryTaskInstanceImpl historyTaskInstanceImpl = (HistoryTaskInstanceImpl) this.jbpmDao.getSinglObjectListByHSQL("from HistoryTaskInstanceImpl htii where htii.historyTask.dbid=?", taskImpl.getDbid()).get(0);
				 
				 for(int i=0;i<activitiesDetailList.size();i++)
				 {
					ActivityDetail activityDetail = activitiesDetailList.get(i);
					
					String activityType =  StringUtil.nullToString(activityDetail.getActivityType());
					if("task".equalsIgnoreCase(activityType))
					{
						String activityName =  StringUtil.nullToString(activityDetail.getActivityName());
						String activityKey = StringUtil.nullToString(activityDetail.getActivityKey());
						if(activityName_.equals(activityKey))
						{
							 //实例化流程对象
							 JBPMWorkflowHistoryInfo jhi_task = null;
							 boolean isNew = true;
							 if(!isStart)
							 {
								 List<Object> infos_list = this.jbpmDao.getSinglObjectListByHSQL("from  JBPMWorkflowHistoryInfo jwhi where jwhi.historyTaskInstanceImpl.dbid = ?", historyTaskInstanceImpl.getDbid());
								 if((null != infos_list) && (infos_list.size()>0))
								 {
									 jhi_task = (JBPMWorkflowHistoryInfo)infos_list.get(0);
									 isNew = false;
								 }
							 }
							 if(isNew)
							 {
								 String fromButtonTextToCome = StringUtil.nullToString(modelData.get("currentTaskSubmitButtonText"));
								 fromButtonTextToCome = StringUtil.empty2Other(fromButtonTextToCome, WorkflowSubmitTextEnum.workflowStart.getText());
								 
								 processFormPath  = activityDetail.getFormPath();
								 processFormTitle = QueryUtil.getQueryString(modelData, activityDetail.getFormTitle());
								 
								 jhi_task = new JBPMWorkflowHistoryInfo();
								 jhi_task.setHistoryTaskInstanceImpl(historyTaskInstanceImpl);
								 jhi_task.setHistoryProcessInstanceImpl(historyProcessInstanceImpl);
								 jhi_task.setDeploymentImpl(deploymentImpl);
								 jhi_task.setActivityDetail(activityDetail);
								 jhi_task.setFromButtonTextToCome(fromButtonTextToCome);
								 jhi_task.setWorkflowName(workflowName);
								 jhi_task.setWorkflowDisplayName(workflowDisplayName);
								 jhi_task.setProcessDefinitionId(processDefinitionId);
								 jhi_task.setProcessFormPath(processFormPath);
								 jhi_task.setProcessFormTitle(processFormTitle);
								 jhi_task.setDraftUUID(draftUUID);
								 jhi_task.setRequestInitiatorUser(user);
								 jhi_task.setRequestInitiator(requestInitiator);
								 jhi_task.setRequestInitiatorRealName(requestInitiatorRealName);
								 
								 jhi_task.setKeyOne(keyOne);
								 jhi_task.setKeyTwo(keyTwo);
								 jhi_task.setKeyThree(keyThree);
								 jhi_task.setKeyFour(keyFour);
								 jhi_task.setKeyFive(keyFive);
								 jhi_task.setKeySix(keySix);
								 jhi_task.setKeySeven(keySeven);
								 jhi_task.setKeyEight(keyEight);
								 jhi_task.setKeyNine(keyNine);
								 jhi_task.setKeyTen(keyTen);
								 jhi_task.setIsReaded(false);
								 if(!isStart)
								 {
									 HistoryTaskInstanceImpl previousHistoryTaskInstanceImpl = jhi_completedTaskImpl_inner.getHistoryTaskInstanceImpl();
									 jhi_task.setPreviousHistoryTaskInstanceImpl(previousHistoryTaskInstanceImpl);
									 //modify by tracywindy 2014-03-18
									 if(isBack){
										 jhi_task.setBackedHistoryInfo(jhi_completedTaskImpl_inner);
										 //退回模式
										 String backModel = modelData.get("workflowbackmodel");
										 jhi_task.setBackModel(backModel);
										 JBPMWorkflowHistoryInfoUser jbpmWorkflowHistoryInfoUser = (JBPMWorkflowHistoryInfoUser)this.jbpmDao.findEntityByID(JBPMWorkflowHistoryInfoUser.class, modelData.get("jbpmWorkflowHistoryInfoUserId"));//this.jbpmDao.getSinglObjectListByHSQL("from JBPMWorkflowHistoryInfoUser jwhiu where jwhiu.jbpmWorkflowHistoryInfo.id = ? and jwhiu.planActor.id = ?",jhi_completedTaskImpl_inner.getId(),modelData.get("planActorId")).get(0);
										 String hql = "update JbpmWorkFlowAppPush set isPush = true where taskId = ?";
										this.jbpmDao.updateByHSQL(hql, jbpmWorkflowHistoryInfoUser.getId());
									 }
								 }
								 else
								 {
									 historyTaskInstanceImpl.setFirstedProcessInstanceHistoryInfo(jhi_processInstance);
									 Set<HistoryTaskInstanceImpl> firstHistoryTaskInstanceImpls = new HashSet<HistoryTaskInstanceImpl>();
									 firstHistoryTaskInstanceImpls.add(historyTaskInstanceImpl);
									 jhi_processInstance.setFirstHistoryTaskInstanceImpls(firstHistoryTaskInstanceImpls);
								 }
							 }
							 //将Map转化成stringBuffer,task变量存储
						     StringBuffer sb_sourceKeyStr = new StringBuffer();
						     StringBuffer sb_sourceValueStr = new StringBuffer();
						     JBPMUtil.getVaribalesAllStringByMap(modelData, sb_sourceKeyStr, sb_sourceValueStr);
						     String processedFormVariables =    sb_sourceKeyStr.toString();	
							 String processedFormValues    =    sb_sourceValueStr.toString();
							 jhi_task.setProcessedFormVariables(processedFormVariables);
							 jhi_task.setProcessedFormValues(processedFormValues);
							 jhi_task.setProcessInstanceState(processInstanceState);
							 //新增历史节点
							 jhi_task.setHistoryActivityName(activityName);
							 this.jbpmDao.getHibernateTemplate().saveOrUpdate(jhi_task);
							 
							 if(isNew)
							 {
								 historyTaskInstanceImpl.setActivedProcessInstanceHistoryInfo(jhi_processInstance);
								 historyTaskInstanceImpl.setProcessInstanceHistoryInfo(jhi_processInstance);
								 historyTaskInstanceImpl.setTaskHistoryInfo(jhi_task);
								 this.jbpmDao.getHibernateTemplate().save(jhi_processInstance);
								 //String actorType  =  activityDetail.getActorType();
								// String actorValue = activityDetail.getActorValue();
								 String isAutoAcitivity = activityDetail.getIsAutoActivity();
								 List<User> actorList = new ArrayList<User>();
								 List<User> readActorList = new ArrayList<User>();
								 List<User> signatureActorList = new ArrayList<User>();
								 
								 if(!WorkflowSubmitTextEnum.yesButtonDisplayText.getText().equals(isAutoAcitivity))
								 {
									 if(isStart)
									 {
										// actorList = JBPMUtil.getActorsListByActivitySetting(activityDetail,null,actorType, actorValue, modelData, this); 
										 User requestInitiatorUser = (User)this.jbpmDao.findEntityByID(User.class, user.getId());
										 actorList.add(requestInitiatorUser);
										 activityDetail.setActorType("requestInitiator");
										 activityDetail.setActorValue(null);
										 this.jbpmDao.updateEntity(activityDetail);
									 }
									 else
									 {
										 //modify by tracywindy 2013-08-26
										 if(null != readChangeRequestInitiator){
											 //传阅
											 JSONObject readRequestInitiators = new JSONObject(readChangeRequestInitiator);
											 JSONArray readUsernameJSONArray = readRequestInitiators.getJSONArray(activityName);
											 for(int index=0;index<readUsernameJSONArray.length();index++)
											 {
												 String username = ((JSONObject)readUsernameJSONArray.get(index)).get("username").toString();
												 readActorList.add((User)this.jbpmDao.getSinglObjectListByHSQL("from User u where u.username = ? ", username).get(0));
											 }
										 }
										 if(null != signatureChangeRequestInitiator){
											 //会签
											 JSONObject signatureRequestInitiators = new JSONObject(signatureChangeRequestInitiator);
											 JSONArray signatureUsernameJSONArray = signatureRequestInitiators.getJSONArray(activityName);
											 for(int index=0;index<signatureUsernameJSONArray.length();index++)
											 {
												 String username = ((JSONObject)signatureUsernameJSONArray.get(index)).get("username").toString();
												 signatureActorList.add((User)this.jbpmDao.getSinglObjectListByHSQL("from User u where u.username = ? ", username).get(0));
											 }
										 }
										 
										 JSONObject requestInitiators = new JSONObject(changeRequestInitiator);
										 JSONArray usernameJSONArray = requestInitiators.getJSONArray(activityName);
										 for(int index=0;index<usernameJSONArray.length();index++)
										 {
											 String username = ((JSONObject)usernameJSONArray.get(index)).get("username").toString();
											 actorList.add((User)this.jbpmDao.getSinglObjectListByHSQL("from User u where u.username = ? ", username).get(0));
										 }
										 
									 }
								 }
								 for(User u : actorList)
								 {
									 JBPMWorkflowHistoryInfoUser jbpmWorkflowHistoryInfoUser = new JBPMWorkflowHistoryInfoUser();
									 jbpmWorkflowHistoryInfoUser.setDeploymentImpl(deploymentImpl);
									 jbpmWorkflowHistoryInfoUser.setPlanActor(u);
									 jbpmWorkflowHistoryInfoUser.setJbpmWorkflowHistoryInfo(jhi_task);
									 jbpmWorkflowHistoryInfoUser.setStartTime(DateUtil.getSystemDateTime());
									 this.jbpmDao.getHibernateTemplate().save(jbpmWorkflowHistoryInfoUser);
									 addWorkFlowAppPush(jbpmWorkflowHistoryInfoUser.getId());
									 
									 if(user.getUsername().equals(u.getUsername()))
									 {
										modelData.put("jbpmWorkflowHistoryInfoUserId",jbpmWorkflowHistoryInfoUser.getId());
										returnFormPath = processFormPath;
										if(isStart)
										{
											jhi_completedTaskImpl_outer = jhi_task;
										}
									 }
									 //待办提醒
									 ActivityDetail  pendingActivityDetail = jhi_task.getActivityDetail();
									 this.sendMessageNoticeToUser(workflowDisplayName, pendingActivityDetail, u, modelData, SubmitTypeEnum.SUBMIT);
//									 String messageTypes   = StringUtil.nullToString(pendingActivityDetail.getMessageTypes());
//									 if(!messageTypes.isEmpty()){
//										 String messageContent = StringUtil.nullToString(pendingActivityDetail.getMessageContent());
//										 messageContent+=workflowDisplayName;
//										 for(String messageType : messageTypes.split(",")){
//											 switch(MessageTypeEnum.valueOf(messageType.toUpperCase())){
//											   case SMS:{
//												   String receiverMobilePhone    = u.getTelephone();
//												   SendMessageUtil.sendMessage(receiverMobilePhone, messageContent);
//												   break;
//											   }
//											   case MAIL:{
//												   String mailToAddress = u.getEmail();
//												   String mailSubject  = workflowDisplayName+"待办任务提醒";
//												   SendEmailUtil.sendSimpleMessage(mailToAddress, mailSubject, messageContent);
//												   break;
//											   }
//											   case STATIONMESSAGE:{
//													//发站内信给经销商，提报GPS信息
//													/*Map<String,Object> queryMainObjectMap = new HashMap<String,Object>();
//													queryMainObjectMap.put("code", "msgtype.1");
//													BaseMessage baseMessage = new BaseMessage();
//													baseMessage.setMsgTitle("待办任务提醒 ");
//													baseMessage.setMsgType((DictionaryData)this.findEntityByProperties(DictionaryData.class, queryMainObjectMap).get(0));
//													baseMessage.setSendDate(DateUtil.getSystemDate());
//													baseMessage.setFromUser(user);
//													baseMessage.setCreateDate(DateUtil.getSystemDateTime());
//													baseMessage.setCreator(user);
//													baseMessage.setMsgText("");
//													this.saveEntity(baseMessage);
//													List<User> users = new ArrayList<User>();
//													//发站内信给待办人
//													BaseMessageToUser baseMessageToUser = new BaseMessageToUser();
//												    baseMessageToUser.setMsgID(baseMessage);
//													baseMessageToUser.setReadStatus("1");
//													baseMessageToUser.setReadUser(users.get(i));
//													baseMessageToUser.setCreateDate(DateUtil.getSystemDateTime());
//													baseMessageToUser.setCreator(user);
//													this.saveEntity(baseMessageToUser);*/
//												   break;
//											   }
//											 }
//										 }
//									 }
								 }
								//modify by tracywindy 2013-08-02
								 //进入时自动传阅和会签功能
								 //String jbpmWorkflowHistoryInfoUserId = modelData.get("jbpmWorkflowHistoryInfoUserId");
								 /*String autoReadActorType= StringUtil.nullToString(activityDetail.getAutoReadActorType()); 
								 if(!autoReadActorType.isEmpty())
								 {
									 List<User> userList = JBPMUtil.getActorsListByActivitySetting(activityDetail,(null == jhi_completedTaskImpl_outer ) ? jhi_completedTaskImpl_inner : jhi_completedTaskImpl_outer,autoReadActorType, activityDetail.getAutoReadActorValue(), modelData, this,"read");
									 this.addReadPersonToTask(jhi_task, jbpmWorkflowHistoryInfoUserId, user,null, ",", processedAdvise,userList);
								 }
								 String autoSignatureActorType= StringUtil.nullToString(activityDetail.getAutoSignatureActorType()); 
								 if(!autoSignatureActorType.isEmpty())
								 {
									 List<User> userList = JBPMUtil.getActorsListByActivitySetting(activityDetail,(null == jhi_completedTaskImpl_outer ) ? jhi_completedTaskImpl_inner : jhi_completedTaskImpl_outer,autoSignatureActorType, activityDetail.getAutoSignatureActorValue(), modelData, this,"signature");
									 this.addSignaturePersonToTask(jhi_task, jbpmWorkflowHistoryInfoUserId, user,null, ",", processedAdvise,userList);
								 }*/
								 if(0 < signatureActorList.size()){
									 this.addSignaturePersonToTask(jhi_task,modelData, jbpmWorkflowHistoryInfoUserId, user,null, ",", processedAdvise,signatureActorList);
								 }
								 if(0 < readActorList.size()){
									 this.addReadPersonToTask(jhi_completedTaskImpl_inner,jhi_task.getActivityDetail(),modelData, jbpmWorkflowHistoryInfoUserId, user,null, ",", processedAdvise,readActorList);
								 }
								 
								 String fromButtonTextToCome = StringUtil.nullToString(modelData.get("currentTaskSubmitButtonText"));
								 fromButtonTextToCome = StringUtil.empty2Other(fromButtonTextToCome, WorkflowSubmitTextEnum.workflowStart.getText());
								 String startAction_task =  StringUtil.nullToString(activityDetail.getActivityAction()).trim();
								 if(!"".equals(startAction_task))
								 {
									 JbpmBaseAction startEventAction = (JbpmBaseAction)WebUtil.getApplicationContext().getBean(startAction_task);
									 if(WorkflowSubmitTextEnum.submitButtonDisplayText.getText().equals(fromButtonTextToCome)||WorkflowSubmitTextEnum.workflowStart.getText().equals(fromButtonTextToCome)){
										 //modify by tracywindy 2013-07-02
										 modelData.put(this.currentJbpmWorkflowDesignerId, jbpmWorkflowDesigner.getId());
										 modelData.put(this.currentJbpmDepolymentId, deploymentImpl.getDbid()+"");
										 modelData.put(this.currentActivityDetailId, activityDetail.getId());
										 modelData.put(this.currentActivityDetailId, activityDetail.getId());
										 modelData.put(this.conditionRouteType, activityDetail.getConditionRouteType());
										 startEventAction.start(request,modelData,jhi_task);
									 }
									 else if(WorkflowSubmitTextEnum.backButtonDisplayText.getText().equals(fromButtonTextToCome)){
										 //modify by tracywindy 2013-07-02
										 modelData.put(this.currentJbpmWorkflowDesignerId, jbpmWorkflowDesigner.getId());
										 modelData.put(this.currentJbpmDepolymentId, deploymentImpl.getDbid()+"");
										 modelData.put(this.currentActivityDetailId, activityDetail.getId());
										 modelData.put(this.conditionRouteType, activityDetail.getConditionRouteType());
										 startEventAction.back(request, modelData,jhi_task);
									 }
								 }
							 }
							 break;
						}
					 }
				}
			 }
		 }
		 else if(processInstance.isEnded())
		 {
			 processInstanceState = WorkflowSubmitTextEnum.finishButtonDisplayText.getText();
			 modelData.put("processInstanceState", processInstanceState);
			 //流程结束时候时自动传阅
			 List<User> readActorList = new ArrayList<User>();
			 if(null != readChangeRequestInitiator){
				 //传阅
				 JSONObject readRequestInitiators = new JSONObject(readChangeRequestInitiator);
				 String activityName = StringUtil.nullToString(readRequestInitiators.keys().next()).toString();//jhi_completedTaskImpl_inner.getActivityDetail().getActivityName();
				 //modify by tracywindy 2014-01-21 15:50
				 if(readRequestInitiators.has(activityName))
				 {
					 JSONArray readUsernameJSONArray = readRequestInitiators.getJSONArray(activityName);
					 for(int index=0;index<readUsernameJSONArray.length();index++)
					 {
						 String username = ((JSONObject)readUsernameJSONArray.get(index)).get("username").toString();
						 readActorList.add((User)this.jbpmDao.getSinglObjectListByHSQL("from User u where u.username = ? ", username).get(0));
					 }
				 }
			 }
			 //String jbpmWorkflowHistoryInfoUserId = modelData.get("jbpmWorkflowHistoryInfoUserId");
			 if(0 < readActorList.size()){
				 for(ActivityDetail ad : activitiesDetailList){
					 if("end".equalsIgnoreCase(ad.getActivityType())){
						 this.addReadPersonToTask(jhi_completedTaskImpl_inner,ad,modelData,jbpmWorkflowHistoryInfoUserId, user,null, ",", processedAdvise,readActorList);
						 break;
					 }
				 }
			 }
		 }
		 //保存整个流程实例历史数据
		 if(!"save".equalsIgnoreCase(flag))
		 {
			 jhi_processInstance.setProcessInstanceState(processInstanceState);
		 }
		 //存入start节点动作map中的变量
		 if(isStart)
		 {
			 int activitiesDetailListLen = activitiesDetailList.size();
			 for(int i=0;i<activitiesDetailListLen;i++)
			 {
				 ActivityDetail activityDetail = activitiesDetailList.get(i);
				 String activityType =  StringUtil.nullToString(activityDetail.getActivityType());
				 if("start".equalsIgnoreCase(activityType))
				 {
					 String startAction =  StringUtil.nullToString(activityDetail.getActivityAction()).trim();
					 if(!"".equals(startAction))
					 {
						 //JbpmBaseAction startEventAction = ((JbpmBaseAction)Class.forName(startAction).newInstance());
						 JbpmBaseAction startEventAction = (JbpmBaseAction)WebUtil.getApplicationContext().getBean(startAction);
						 //modify by tracywindy 2013-07-02
						 modelData.put(this.currentJbpmWorkflowDesignerId, jbpmWorkflowDesigner.getId());
						 modelData.put(this.currentJbpmDepolymentId, deploymentImpl.getDbid()+"");
						 modelData.put(this.currentActivityDetailId, activityDetail.getId());
						 startEventAction.start(request,modelData,jhi_completedTaskImpl_outer);
						 if(!StringUtils.isBlank(keyOneKey)){keyOne = modelData.get(keyOneKey);}
						 if(!StringUtils.isBlank(keyTwoKey)){keyTwo = modelData.get(keyTwoKey);}
						 if(!StringUtils.isBlank(keyThreeKey)){keyThree = modelData.get(keyThreeKey);}
						 if(!StringUtils.isBlank(keyFourKey)){ keyFour = modelData.get(keyFourKey);}
						 if(!StringUtils.isBlank(keyFiveKey)){keyFive = modelData.get(keyFiveKey);}
						 if(!StringUtils.isBlank(keySixKey)){ keySix= modelData.get(keySixKey);}
						 if(!StringUtils.isBlank(keySevenKey)){ keySeven= modelData.get(keySevenKey);}
						 if(!StringUtils.isBlank(keyEightKey)){ keyEight= modelData.get(keyEightKey);}
						 if(!StringUtils.isBlank(keyNineKey)){keyNine = modelData.get(keyNineKey);}
						 if(!StringUtils.isBlank(keyTenKey)){keyTen = modelData.get(keyTenKey);}
						 jhi_completedTaskImpl_outer.setKeyOne(keyOne);
						 jhi_completedTaskImpl_outer.setKeyTwo(keyTwo);
						 jhi_completedTaskImpl_outer.setKeyThree(keyThree);
						 jhi_completedTaskImpl_outer.setKeyFour(keyFour);
						 jhi_completedTaskImpl_outer.setKeyFive(keyFive);
						 jhi_completedTaskImpl_outer.setKeySix(keySix);
						 jhi_completedTaskImpl_outer.setKeySeven(keySeven);
						 jhi_completedTaskImpl_outer.setKeyEight(keyEight);
						 jhi_completedTaskImpl_outer.setKeyNine(keyNine);
						 jhi_completedTaskImpl_outer.setKeyTen(keyTen);
						 this.jbpmDao.updateEntity(jhi_completedTaskImpl_outer);
						 
					 }
					 break;
				 }
			 }
		 }
		 jhi_processInstance.setKeyOne(keyOne);
		 jhi_processInstance.setKeyTwo(keyTwo);
		 jhi_processInstance.setKeyThree(keyThree);
		 jhi_processInstance.setKeyFour(keyFour);
		 jhi_processInstance.setKeyFive(keyFive);
		 jhi_processInstance.setKeySix(keySix);
		 jhi_processInstance.setKeySeven(keySeven);
		 jhi_processInstance.setKeyEight(keyEight);
		 jhi_processInstance.setKeyNine(keyNine);
		 jhi_processInstance.setKeyTen(keyTen);
		 //将Map转化成stringBuffer,task变量存储
	     StringBuffer sb_sourceKeyStr = new StringBuffer();
	     StringBuffer sb_sourceValueStr = new StringBuffer();
	     JBPMUtil.getVaribalesAllStringByMap(modelData, sb_sourceKeyStr, sb_sourceValueStr);
	     String processedFormVariables =    sb_sourceKeyStr.toString();	
	     String processedFormValues    =    sb_sourceValueStr.toString();
		 jhi_processInstance.setProcessedFormVariables(processedFormVariables);
		 jhi_processInstance.setProcessedFormValues(processedFormValues);
		 
		 if(processInstance.isEnded())
		 {
			 HistoryTaskInstanceImpl lastHistoryTaskInstanceImpl = (HistoryTaskInstanceImpl) this.jbpmDao.getSinglObjectListByHSQL("from HistoryTaskInstanceImpl htii where htii.historyTask.dbid=?", completedTaskImpl.getDbid()).get(0);
			 jhi_processInstance.setLastHistoryTaskInstanceImpl(lastHistoryTaskInstanceImpl);
		 }
		 if(isStart)
		 {
			 jhi_processInstance.setProcessFormPath(returnFormPath);
			 if((null == jhi_processInstance.getIsRemovedDraft())){
				 jhi_processInstance.setIsRemovedDraft(true);
			 }
		 }else{
			 jhi_processInstance.setIsRemovedDraft(false);
		 }
		 this.jbpmDao.getHibernateTemplate().update(jhi_processInstance);
		 if(isStart)
		 {
			 this.getRequestFormDealedInfo(request,jhi_completedTaskImpl_outer,jhi_processInstance, modelData,activityNames);
		 }
		 return returnFormPath;
	}
	@Override
	public void   sendMessageNoticeToUser(String workflowDisplayName,ActivityDetail pendingActivityDetail,User u,Map<String,String> modelData,SubmitTypeEnum ste) throws Exception{
		//待办提醒
		 String messageTypes   = StringUtil.nullToString(pendingActivityDetail.getMessageTypes());
		 if(!messageTypes.isEmpty()){
			 String messageContent = "";
			 String mailSubject  = workflowDisplayName+"待办任务提醒";
			 switch(ste){
			    case SUBMIT:{
			    	messageContent = QueryUtil.getQueryString(modelData, StringUtil.nullToString(pendingActivityDetail.getMessageContent()));
			    	messageContent+=workflowDisplayName;
			    	mailSubject  = workflowDisplayName+"待办任务提醒";
			    	break;
			    }
			    case READ:{
			    	messageContent = QueryUtil.getQueryString(modelData, StringUtil.nullToString(pendingActivityDetail.getReadMessageContent()));
			    	messageContent+=workflowDisplayName;
			    	mailSubject  = workflowDisplayName+"待办任务提醒";
			    	break;
			    }
			    case SIGNATURE:{
			    	messageContent = QueryUtil.getQueryString(modelData, StringUtil.nullToString(pendingActivityDetail.getSignatureMessageContent()));
			    	messageContent+=workflowDisplayName;
			    	mailSubject  = workflowDisplayName+"待办任务提醒";
			    	break;
			    }
			 }
			 for(String messageType : messageTypes.split(",")){
				 switch(MessageTypeEnum.valueOf(messageType.toUpperCase())){
				   case SMS:{
					   String receiverMobilePhone    = u.getTelephone();
					   if(receiverMobilePhone!= null && receiverMobilePhone.length() > 0 ){
							SmsNotice sms = new SmsNotice();
							sms.setCreateDate(DateUtil.getSystemDateDetailTime());
							sms.setCreator(SecurityUtil.getPrincipal());
							sms.setPhoneNumber(receiverMobilePhone);
							sms.setSmsContent(messageContent);
							sms.setSmsType("IMMEDIATELY");
							sms.setNoticeTime(DateUtil.getSystemDateTime());
//						String result = SendMessageUtil.sendMessageByHttpClient(receiverMobilePhone, messageContent);
//							
//							if("否".equals(result)){
//								sms.setSendFlag(0);
//							}else{
//								sms.setSendResult(result);
//								sms.setSendTime(DateUtil.getSystemDateTime());
//								sms.setSendFlag(1);
//							}
//						 	this.jbpmDao.saveEntity(sms);
						}
						break;
				   }
				   case MAIL:{
					   String mailToAddress = u.getEmail();
					   if(mailToAddress != null && mailToAddress.length()>0){
						   EmailNotice email = new EmailNotice();
						   email.setCreateDate(DateUtil.getSystemDateDetailTime());
						   email.setCreator(SecurityUtil.getPrincipal());
						   email.setEmailAddress(mailToAddress);
						   email.setEmailContent(messageContent);
						   email.setSendFlag("0");
						   email.setEmailTitle(mailSubject);
						   this.jbpmDao.saveEntity(email);
						   SendEmailUtil.sendSimpleMessage(mailToAddress, mailSubject, messageContent);
					   }
					   break;
				   }
				   case STATIONMESSAGE:{
						//发站内信给经销商，提报GPS信息
						/*Map<String,Object> queryMainObjectMap = new HashMap<String,Object>();
						queryMainObjectMap.put("code", "msgtype.1");
						BaseMessage baseMessage = new BaseMessage();
						baseMessage.setMsgTitle("待办任务提醒 ");
						baseMessage.setMsgType((DictionaryData)this.findEntityByProperties(DictionaryData.class, queryMainObjectMap).get(0));
						baseMessage.setSendDate(DateUtil.getSystemDate());
						baseMessage.setFromUser(user);
						baseMessage.setCreateDate(DateUtil.getSystemDateTime());
						baseMessage.setCreator(user);
						baseMessage.setMsgText("");
						this.saveEntity(baseMessage);
						List<User> users = new ArrayList<User>();
						//发站内信给待办人
						BaseMessageToUser baseMessageToUser = new BaseMessageToUser();
					    baseMessageToUser.setMsgID(baseMessage);
						baseMessageToUser.setReadStatus("1");
						baseMessageToUser.setReadUser(users.get(i));
						baseMessageToUser.setCreateDate(DateUtil.getSystemDateTime());
						baseMessageToUser.setCreator(user);
						this.saveEntity(baseMessageToUser);*/
					   break;
				   }
				 }
			 }
		 }
	}
	@Override
	public String removeProcessInstance(HttpServletRequest request,String processInstanceDBID) throws Exception
	{
		long processInstanceDBID_ = Long.parseLong(processInstanceDBID);
		
		String returnValue = "";
		//删除信息
		/****
		List<JBPMWorkflowHistoryInfo>  jbpmWorkflowHistoryInfoTasks = (List<JBPMWorkflowHistoryInfo>)this.jbpmDao.findResultsByHSQL("from  JBPMWorkflowHistoryInfo jwhi where jwhi.historyProcessInstanceImpl.dbid =? and jwhi.historyTaskInstanceImpl is not null ",processInstanceDBID_);
		for(JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfoTask : jbpmWorkflowHistoryInfoTasks)
		{
			Map<String,String> modelData = JBPMUtil.getProcessInstanceHistoryData(this.jbpmDao.getHibernateTemplate(), processInstanceDBID);
			String deleteAction = StringUtil.nullToString(jbpmWorkflowHistoryInfoTask.getActivityDetail().getActivityAction()).trim();
			if(!deleteAction.isEmpty())
			{
				returnValue = ((JbpmBaseAction)WebUtil.getApplicationContext().getBean(deleteAction)).delete(request, modelData,jbpmWorkflowHistoryInfoTask);
			}
		}
		*****/
		//modify by tracywindy 2013-07-02
		List<DeploymentImpl>  deploymentImpls = this.jbpmDao.findResultsByHSQL("select jwhi.deploymentImpl from  JBPMWorkflowHistoryInfo jwhi where jwhi.historyProcessInstanceImpl.dbid =? and jwhi.historyTaskInstanceImpl is  null ",processInstanceDBID_);
		DeploymentImpl deploymentImpl = deploymentImpls.get(0);
		Set<ActivityDetail> activitiesDetailList = deploymentImpl.getActivityDetails();
		for(ActivityDetail activityDetail : activitiesDetailList){
			 String activityType =  StringUtil.nullToString(activityDetail.getActivityType());
			 if("start".equalsIgnoreCase(activityType))
			 {
				 String startAction =  StringUtil.nullToString(activityDetail.getActivityAction()).trim();
				 if(!"".equals(startAction))
				 {
						Map<String,String> modelData = JBPMUtil.getProcessInstanceHistoryData(this.jbpmDao.getHibernateTemplate(), processInstanceDBID);
						String deleteAction = StringUtil.nullToString(activityDetail.getActivityAction()).trim();
						if(!deleteAction.isEmpty())
						{
							 //modify by tracywindy 2013-07-02
							 modelData.put(this.currentJbpmWorkflowDesignerId, deploymentImpl.getJbpmWorkflowDesigner().getId());
							 modelData.put(this.currentJbpmDepolymentId, deploymentImpl.getDbid()+"");
							 modelData.put(this.currentActivityDetailId, activityDetail.getId());
							 returnValue = ((JbpmBaseAction)WebUtil.getApplicationContext().getBean(deleteAction)).delete(request, modelData,null);
						}
				 }
				 break;
			 }
		}
			
		JBPMUtil.removeWorkflowProcessInstance(processInstanceDBID_, this.jbpmDao.getJdbcTemplate(), this.jbpmDao.getHibernateTemplate());
		return returnValue;
	}
	
	@Override
	public List<Object> getAuthDesignedWorkflowList(
			Map<String, String> modelData,boolean isViewWorkflows) throws Exception {
		//当前用户信息
		//User user = (User)SecurityUtil.getPrincipal();
//		Set<Role> userAuthRoles = new HashSet<Role>();//user.getRoles();
//		String hsql = "from JbpmWorkflowDesigner";
//		List<Object> designedAllWorkflow = this.jbpmDao.getSinglObjectListByHSQL(hsql);
		List<Object> designedAuthWorkflows = new ArrayList<Object>();
//		for(Object designedWorkflow:designedAllWorkflow)
//		{
//			JbpmWorkflowDesigner jbpmWorkflowDesigner = (JbpmWorkflowDesigner)designedWorkflow;
//			Set<Role> roles = null;
//			if(!isViewWorkflows)
//			{
//				roles = jbpmWorkflowDesigner.getRoles();
//			}
//			else
//			{
//				roles = jbpmWorkflowDesigner.getViewRoles();
//			}
//			for(Role role:roles)
//			{
//				if(userAuthRoles.contains(role))
//				{
//					designedAuthWorkflows.add(jbpmWorkflowDesigner);
//					break;
//				}
//			}
//		}
		return designedAuthWorkflows;
	}
	
	
	@Override
	public void saveKeyToWorkflow(String designerId, String keyOne,
			String keyTwo, String keyThree, String keyFour,String keyFive,
			String keySix, String keySeven, String keyEight, 
			String keyNine, String keyTen) throws Exception {
		JbpmWorkflowDesigner jbpmWorkflowDesigner = (JbpmWorkflowDesigner)this.jbpmDao.findEntityByID(JbpmWorkflowDesigner.class, designerId);
		jbpmWorkflowDesigner.setKeyOne(keyOne);
		jbpmWorkflowDesigner.setKeyTwo(keyTwo);
		jbpmWorkflowDesigner.setKeyThree(keyThree);
		jbpmWorkflowDesigner.setKeyFour(keyFour);
		jbpmWorkflowDesigner.setKeyFive(keyFive);
		jbpmWorkflowDesigner.setKeySix(keySix);
		jbpmWorkflowDesigner.setKeySeven(keySeven);
		jbpmWorkflowDesigner.setKeyEight(keyEight);
		jbpmWorkflowDesigner.setKeyNine(keyNine);
		jbpmWorkflowDesigner.setKeyTen(keyTen);
		this.jbpmDao.saveOrUpdateEntity(jbpmWorkflowDesigner);
	}
	
	@Override
	public BaseDao getBussinessDao() throws Exception {
		return this.jbpmDao;
	}
	
	
	@Override
	public void removeWorkflowAllProcessInstance(HttpServletRequest request,String workflowName,
			int version) throws Exception {
		String querySql = "SELECT DBID_ as PROCESSINSTANCEDBID_,ID_ as PROCESSINSTANCEID FROM JBPM4_HIST_PROCINST WHERE PROCDEFID_ = ?";
		for(Map<String,Object> rowMap :this.jbpmDao.getJdbcTemplate().queryForList(querySql, workflowName+"-"+version)){
			String processInstanceDBID = rowMap.get("PROCESSINSTANCEDBID_").toString();
			this.removeProcessInstance(request, processInstanceDBID);
		}
	}
	/*		
	 *     changeRequestInitiator={
	 * 			"02-分支任务1":[{"username":"Admin"}],
	 * 			"03-分支任务2":[{"username":"Admin"}],
	 * 			"04-分支任务3":[{"username":"Admin"}]
	 * 		}
	 */
	@Override
	public void submitAutoProcessedTaskForm(
			HttpServletRequest     request,
			String     		       currentTaskId,
			String                 jbpmWorkflowHistoryInfoUserId,
			String                 requestInitiatorRoute,
			JSONObject             changeRequestInitiator,
			String                 processedAdvise,
			String                 choseRequestNextRouteNodeName,
			WorkflowSubmitTextEnum submitType ,
			Map<String,String>     modelData
	 ) throws Exception 
	 {
		Assert.assertNotNull(currentTaskId);
		Assert.assertNotNull(jbpmWorkflowHistoryInfoUserId);
		Assert.assertNotNull(changeRequestInitiator);
		Assert.assertNotNull(choseRequestNextRouteNodeName);
		Assert.assertNotNull(submitType);
		
		//Task task = JBPMUtil.getTaskService().getTask(currentTaskId);
		//String query_task="from JBPMWorkflowHistoryInfo jwhi where jwhi.historyTaskInstanceImpl.historyTask.dbid = ?";
		//JBPMWorkflowHistoryInfo jhi_completedTaskImpl_inner = (JBPMWorkflowHistoryInfo)this.jbpmDao.getSinglObjectListByHSQL(query_task,Long.parseLong(currentTaskId)).get(0);
		JBPMWorkflowHistoryInfoUser jbpmWorkflowHistoryInfoUser = (JBPMWorkflowHistoryInfoUser)this.jbpmDao.findEntityByID(JBPMWorkflowHistoryInfoUser.class, jbpmWorkflowHistoryInfoUserId);
		//变量
		String assignmentUserId       = ( null == jbpmWorkflowHistoryInfoUser.getAssignActor())   ? null : jbpmWorkflowHistoryInfoUser.getAssignActor().getId() ;
		String assignmentedUserId     = ( null == jbpmWorkflowHistoryInfoUser.getAssignedActor()) ? null : jbpmWorkflowHistoryInfoUser.getAssignedActor().getId() ;
		String currentTaskSubmitButtonText = submitType.getText();
		if(null == modelData ){
			modelData = new HashMap<String,String>();
		}
		modelData.put("currentTaskId", currentTaskId);
		modelData.put("jbpmWorkflowHistoryInfoUserId", jbpmWorkflowHistoryInfoUserId);
		modelData.put("changeRequestInitiator", changeRequestInitiator.toString());
		modelData.put("choseRequestNextRouteNodeName", choseRequestNextRouteNodeName);
		modelData.put("assignmentUserId"  , assignmentUserId);
		modelData.put("assignmentedUserId", assignmentedUserId);
		modelData.put("choseRequestNextRouteNodeName", choseRequestNextRouteNodeName);
		modelData.put("currentTaskSubmitButtonText", currentTaskSubmitButtonText);
		modelData.put("processedAdvise", processedAdvise);
		//提交节点
		this.submitProcessedTaskForm(request, currentTaskId, modelData);
	}
	@Override
	public void updateAbandonWorkflowProcessInstance(HttpServletRequest request,Long processInstanceDBID_) throws Exception{
	   JBPMUtil.updateAbandonWorkflowProcessInstance(request,processInstanceDBID_, this.jbpmDao.getJdbcTemplate(), this.jbpmDao.getHibernateTemplate());
	}
	@Override
	public List<JBPMWorkflowHistoryInfo> getProjSummary(String keyOne,String proj_id)
			throws Exception {
		String queryProjSummaryHsqlHistoryTaskInstanceImplIds = "select max(jwhi.historyTaskInstanceImpl.dbid) from JBPMWorkflowHistoryInfo jwhi where jwhi.historyTaskInstanceImpl is not null and (jwhi.keyOne = ? or jwhi.keyOne = ? ) group by jwhi.deploymentImpl,jwhi.historyProcessInstanceImpl";
	    List<Long> historyId_list  = this.findResultsByHSQL(queryProjSummaryHsqlHistoryTaskInstanceImplIds, keyOne,proj_id) ;   
        Long[] historyTaskInstanceIds = new Long[historyId_list.size()];
        historyTaskInstanceIds = historyId_list.toArray(historyTaskInstanceIds);
        
        String queryProjSummaryHsql = "from JBPMWorkflowHistoryInfo jwhi where  jwhi.historyTaskInstanceImpl is not null and  jwhi.historyTaskInstanceImpl.dbid in(:historyTaskInstanceIds) order by jwhi.historyTaskInstanceImpl.startTime";
        List<JBPMWorkflowHistoryInfo> summayHistoryInfos = this.jbpmDao.findResultsByNamedParamHSQL(queryProjSummaryHsql, new String[]{"historyTaskInstanceIds"}, new Object[]{historyTaskInstanceIds});
        return summayHistoryInfos;
	}
	@Override
	public String updatePendingUserToOther(int changePlanUserFlag,String taskType,
			String jbpmWorkflowHistoryInfoUserId, String planUserId,
			String changeToUserId) throws Exception {
		Assert.assertNotNull(planUserId);
		Assert.assertNotNull(changeToUserId);
		User changeToUser = this.findEntityByID(User.class, changeToUserId);
		switch(changePlanUserFlag){
		  case 0:{
			  if("read".equals(taskType)){//read
				  String hsql = "select jwhiu from JBPMWorkflowReadUser jwhiu where jwhiu.id = ?  and jwhiu.readedActor.id = ?  and jwhiu.endTime is  null";
				  List<JBPMWorkflowReadUser> jbpmWorkflowReadUserList = this.findResultsByHSQL(hsql, jbpmWorkflowHistoryInfoUserId,planUserId);
				  for(JBPMWorkflowReadUser jbpmWorkflowReadUser  : jbpmWorkflowReadUserList){
					  jbpmWorkflowReadUser.setReadedActor(changeToUser);
				  }
				  this.updateAllEntities(jbpmWorkflowReadUserList);
			  }else if("signature".equals(taskType)){//signature
				  String hsql = "select jwhiu from JBPMWorkflowSignatureUser jwhiu where jwhiu.id = ?  and jwhiu.signaturedActor.id = ?  and jwhiu.endTime is  null";
				  List<JBPMWorkflowSignatureUser> jbpmWorkflowSignatureUserList = this.findResultsByHSQL(hsql, jbpmWorkflowHistoryInfoUserId,planUserId);
				  for(JBPMWorkflowSignatureUser jbpmWorkflowSignatureUser  : jbpmWorkflowSignatureUserList){
					  jbpmWorkflowSignatureUser.setSignaturedActor(changeToUser);
				  }
				  this.updateAllEntities(jbpmWorkflowSignatureUserList);
			  }else{ //pending,assignmentPending,assignmentCompleted
				  String hsql = "select jwhiu from JBPMWorkflowHistoryInfoUser jwhiu where jwhiu.id = ?  and jwhiu.planActor.id = ?  and jwhiu.endTime is  null";
				  List<JBPMWorkflowHistoryInfoUser> jbpmWorkflowHistoryInfoUserList = this.findResultsByHSQL(hsql, jbpmWorkflowHistoryInfoUserId,planUserId);
				  for(JBPMWorkflowHistoryInfoUser jbpmWorkflowHistoryInfoUser  : jbpmWorkflowHistoryInfoUserList){
					  jbpmWorkflowHistoryInfoUser.setPlanActor(changeToUser);
				  }
				  this.updateAllEntities(jbpmWorkflowHistoryInfoUserList);
			  }
			  break;
		  }
		  case 1:{
			  if("read".equals(taskType)){//read
				  String hsql = "select jwhiu from JBPMWorkflowReadUser jwhiu where  jwhiu.readedActor.id = ?  and jwhiu.endTime is  null";
				  List<JBPMWorkflowReadUser> jbpmWorkflowReadUserList = this.findResultsByHSQL(hsql, jbpmWorkflowHistoryInfoUserId,planUserId);
				  for(JBPMWorkflowReadUser jbpmWorkflowReadUser  : jbpmWorkflowReadUserList){
					  jbpmWorkflowReadUser.setReadedActor(changeToUser);
				  }
				  this.updateAllEntities(jbpmWorkflowReadUserList);
			  }else if("signature".equals(taskType)){//signature
				  String hsql = "select jwhiu from JBPMWorkflowSignatureUser jwhiu where  jwhiu.signaturedActor.id = ?  and jwhiu.endTime is  null";
				  List<JBPMWorkflowSignatureUser> jbpmWorkflowSignatureUserList = this.findResultsByHSQL(hsql, planUserId);
				  for(JBPMWorkflowSignatureUser jbpmWorkflowSignatureUser  : jbpmWorkflowSignatureUserList){
					  jbpmWorkflowSignatureUser.setSignaturedActor(changeToUser);
				  }
				  this.updateAllEntities(jbpmWorkflowSignatureUserList);
			  }else{ //pending,assignmentPending,assignmentCompleted
				  String hsql = "select jwhiu from JBPMWorkflowHistoryInfoUser jwhiu where jwhiu.planActor.id = ?  and jwhiu.endTime is  null";
				  List<JBPMWorkflowHistoryInfoUser> jbpmWorkflowHistoryInfoUserList = this.findResultsByHSQL(hsql, planUserId);
				  for(JBPMWorkflowHistoryInfoUser jbpmWorkflowHistoryInfoUser  : jbpmWorkflowHistoryInfoUserList){
					  jbpmWorkflowHistoryInfoUser.setPlanActor(changeToUser);
				  }
				  this.updateAllEntities(jbpmWorkflowHistoryInfoUserList);
			  }
			  break;
		  }
		}
		return "success";
	}
	
	public void addWorkFlowAppPush(String taskId) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("taskId", taskId);
		List<JbpmWorkFlowAppPush>pushs =  this.jbpmDao.findEntityByProperties(JbpmWorkFlowAppPush.class, params);
		JbpmWorkFlowAppPush push = null;
		if(null != pushs && pushs.size() > 0 ){
			push = pushs.get(0);
			push.setIsPush(false);
		}else{
			push = new JbpmWorkFlowAppPush();
			push.setCreateDate(DateUtil.getSystemDateTime());
			push.setIsPush(false);
			push.setTaskId(taskId);
		}
		this.jbpmDao.saveOrUpdateEntity(push);
	}
	
	@Override
	public void transferOneProcessAttachmentToAnother(
			JBPMWorkflowHistoryInfo jBPMWorkflowHistoryInfo, String designerId,Map<String, String> paramsMap) throws Exception{
		//找到指定的流程发布实例
		JbpmWorkflowDesigner designer = this.findEntityByID(JbpmWorkflowDesigner.class, designerId);
		HistoryProcessInstanceImpl historyProcessInstanceImpl = jBPMWorkflowHistoryInfo.getHistoryProcessInstanceImpl();
		JbpmWorkflowDesigner jbpmWorkflowDesigner = jBPMWorkflowHistoryInfo.getDeploymentImpl().getJbpmWorkflowDesigner();
		//得到流程关键字1，一般为合同号或者项目号，而流程附件的关键字2也是为此
		String key1 = paramsMap.get(designer.getKeyOne());
		String key2 = paramsMap.get(designer.getKeyTwo());
		String key3 = paramsMap.get(designer.getKeyThree());
		String docId = jBPMWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+"";
		//一般不会超过三个
		String  uploadDetailsHql =  "from AttachmentFileUploadInfoDetail  WHERE jbpmWorkflowDesigner = ?";
		if(key1 != null){
			uploadDetailsHql += " and attachmentFileUploadInfo.identifierTwo = '"+key1+"' ";
		}
		if(key2 != null){
			uploadDetailsHql += " and attachmentFileUploadInfo.identifierThree ='"+key2+"' ";
		}
		if(key3 != null ){
			uploadDetailsHql += " and attachmentFileUploadInfo.identifierFour = '"+key3+"' ";
		}
		List<AttachmentFileUploadInfoDetail>detailsOld =  this.findResultsByHSQL(uploadDetailsHql, designer);
		String uploadIds = "";
		Boolean flag = true;
		Map<String, AttachmentFileUploadInfo> attachmentMap = new HashMap<String, AttachmentFileUploadInfo>();
		for(AttachmentFileUploadInfoDetail detail : detailsOld){
			String uploadId = detail.getAttachmentFileUploadInfo().getId();
			if(uploadIds.indexOf(uploadId) < 0 ){//说明没有生成新的AttachmentFileUploadInfo
				if(flag){
					uploadIds += uploadId;
					flag = false;
				}else{
					uploadIds = uploadIds+","+uploadId;
				}
				AttachmentFileUploadInfo oldUploadInfo =  detail.getAttachmentFileUploadInfo();
				AttachmentFileUploadInfo attachmentFileUploadInfo = new AttachmentFileUploadInfo();
				attachmentFileUploadInfo.setModule(oldUploadInfo.getModule());
				attachmentFileUploadInfo.setIdentifierOne(docId);
				attachmentFileUploadInfo.setIdentifierTwo(oldUploadInfo.getIdentifierTwo());
				attachmentFileUploadInfo.setIdentifierThree(oldUploadInfo.getIdentifierThree());
				attachmentFileUploadInfo.setIdentifierFour(oldUploadInfo.getIdentifierFour());
				attachmentFileUploadInfo.setIdentifierFive(oldUploadInfo.getIdentifierFive());
				attachmentFileUploadInfo.setIdentifierSix(oldUploadInfo.getIdentifierSix());
				attachmentFileUploadInfo.setIdentifierSeven(oldUploadInfo.getIdentifierSeven());
				attachmentFileUploadInfo.setIdentifierEight(oldUploadInfo.getIdentifierEight());
				attachmentFileUploadInfo.setIdentifierNine(oldUploadInfo.getIdentifierNine());
				attachmentFileUploadInfo.setIdentifierTen(oldUploadInfo.getIdentifierTen());
				DictionaryData attachmentFileDict =  new DictionaryData();
				attachmentFileDict.setId(oldUploadInfo.getAttachmentFileDict().getId());
				attachmentFileUploadInfo.setAttachmentFileDict(attachmentFileDict);
				attachmentFileUploadInfo.setUnionKey(oldUploadInfo.getUnionKey());
				this.saveEntity(attachmentFileUploadInfo);
				attachmentMap.put(uploadId, attachmentFileUploadInfo);
			}
			//再去生成对应的detail
			AttachmentFileUploadInfoDetail attachmentFileUploadInfoDetail = new AttachmentFileUploadInfoDetail();
			attachmentFileUploadInfoDetail.setAttachmentFileUploadInfo(attachmentMap.get(uploadId));
			attachmentFileUploadInfoDetail.setChineseFileName(detail.getChineseFileName());
			attachmentFileUploadInfoDetail.setEncodeFileName(detail.getEncodeFileName());
			attachmentFileUploadInfoDetail.setFileSize(detail.getFileSize());
			attachmentFileUploadInfoDetail.setRemark(detail.getRemark());
			attachmentFileUploadInfoDetail.setFileImagesId(detail.getFileImagesId());
			User user = new User();
			user.setId(detail.getUploadUser().getId());
			attachmentFileUploadInfoDetail.setUploadUser(user);
			attachmentFileUploadInfoDetail.setUploadTime(detail.getUploadTime());
			attachmentFileUploadInfoDetail.setJbpmWorkflowHistoryInfo(jBPMWorkflowHistoryInfo);
			attachmentFileUploadInfoDetail.setHistoryProcessInstanceImpl(historyProcessInstanceImpl);
			attachmentFileUploadInfoDetail.setJbpmWorkflowDesigner(jbpmWorkflowDesigner);
			attachmentFileUploadInfoDetail.setActivityDetail(jBPMWorkflowHistoryInfo.getActivityDetail());
			attachmentFileUploadInfoDetail.setId(null);
			attachmentFileUploadInfoDetail.setAttachmentFileUploadInfo(attachmentMap.get(uploadId));
			this.saveEntity(attachmentFileUploadInfoDetail);
		}
		
	}
	
	
	@Override
	public List<WorkflowConfigData> exportWorkflowConfigData() throws Exception {

		List<WorkflowConfigData> configs = new ArrayList<WorkflowConfigData>();
		String designerSql = "select t_jbpm_designer_transfer.*,jbpm4_deployment.dbid_ from t_jbpm_designer_transfer left join jbpm4_deployment on jbpm4_deployment.jbpm_workflow_designer_id_ = t_jbpm_designer_transfer.id_ ";
		List<Map<String, String>> workflowList = this.jbpmDao.getJdbcTemplate().query(designerSql,
				new RowMapper<Map<String, String>>() {
					@Override
					public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
						Map<String, String> rowDataMap = new HashMap<String, String>();
						rowDataMap.put("displayName", rs.getString("DISPLAY_NAME_"));
						rowDataMap.put("type", rs.getString("TYPE_"));
						rowDataMap.put("position", rs.getString("POSITION_"));
						rowDataMap.put("code", rs.getString("CODE_"));
						rowDataMap.put("workflowName", rs.getString("WORKFLOW_NAME_"));
						rowDataMap.put("key", rs.getString("KEY_"));
						rowDataMap.put("keyOne", rs.getString("KEY_ONE_"));
						rowDataMap.put("keyTwo", rs.getString("KEY_TWO_"));
						rowDataMap.put("keyThree", rs.getString("KEY_THREE_"));
						rowDataMap.put("keyFour", rs.getString("KEY_FOUR_"));
						rowDataMap.put("keyFive", rs.getString("KEY_FIVE_"));
						rowDataMap.put("keySix", rs.getString("KEY_SIX_"));
						rowDataMap.put("keySeven", rs.getString("KEY_SEVEN_"));
						rowDataMap.put("keyEight", rs.getString("KEY_EIGHT_"));
						rowDataMap.put("keyNine", rs.getString("KEY_NINE_"));
						rowDataMap.put("keyTen", rs.getString("KEY_TEN_"));
						rowDataMap.put("version", rs.getString("VERSION_"));
						rowDataMap.put("jpdlVersion", rs.getString("JPDL_VERSION_"));
						rowDataMap.put("description", rs.getString("DESCRIPTION_"));
						rowDataMap.put("workflowDefinition", rs.getString("WORKFLOW_DEFINITION_"));
						rowDataMap.put("createTime", rs.getString("CREATE_TIME_"));
						rowDataMap.put("designerWorkflowJson", rs.getString("DESIGNER_WORKFLOW_JSON_"));
						rowDataMap.put("transferedJpdlXml", rs.getString("TRANSFERED_JPDL_XML_"));
						rowDataMap.put("fromDBDeployId", rs.getString("DBID_"));
						rowDataMap.put("maxDotX", rs.getString("MAX_DOT_X_"));
						rowDataMap.put("maxDotY", rs.getString("MAX_DOT_Y_"));
						return rowDataMap;
					}
				});

		for (Map<String, String> workflowDefine : workflowList) {
			WorkflowConfigData config = new WorkflowConfigData();
			String workflowName = workflowDefine.get("workflowName");
			int version = Integer.parseInt(workflowDefine.get("version"));
			String fromDBDeployId = workflowDefine.get("fromDBDeployId");
			config.setWorkflowName(workflowName);
			config.setVersion(String.valueOf(version));
			config.setDeployId(fromDBDeployId);
			config.setWorkflowDefine(workflowDefine);
			if (null != fromDBDeployId) {
				String detailSql = "select * from t_jbpm_activity_detail where jbpm4_deployment_dbid_ =?";			
				List<Map<String, String>> activitiesDetailDatas = this.jbpmDao.getJdbcTemplate().query(detailSql,
						new Object[]{fromDBDeployId},
						new RowMapper<Map<String, String>>() {
							@Override
							public Map<String, String> mapRow(ResultSet rs, int index) throws SQLException {
								Map<String, String> rowDataMap = new HashMap<String, String>();
								rowDataMap.put("activityName", rs.getString("ACTIVITY_NAME_"));
								//rowDataMap.put("activityEnname", rs.getString("ACTIVITY_ENNAME_"));
								rowDataMap.put("activityKey", rs.getString("ACTIVITY_KEY_"));
								rowDataMap.put("activityPosition", rs.getString("ACTIVITY_POSITION_"));
								rowDataMap.put("activityType", rs.getString("ACTIVITY_TYPE_"));
								rowDataMap.put("activityAction", rs.getString("ACTIVITY_ACTION_"));
								rowDataMap.put("attachmentType", rs.getString("ATTACHMENT_TYPE_"));
								rowDataMap.put("attachmentTypeIds", rs.getString("ATTACHMENT_TYPE_IDS_"));
								rowDataMap.put("actorType", rs.getString("ACTOR_TYPE_"));
								rowDataMap.put("actorValue", rs.getString("ACTOR_VALUE_"));
								rowDataMap.put("displayActorValue", rs.getString("ACTOR_VALUE_DISPLAY_"));
								rowDataMap.put("formPath", rs.getString("FORM_PATH_"));
								rowDataMap.put("formTitle", rs.getString("FORM_TITLE_"));
								rowDataMap.put("operationButtons", rs.getString("OPERATION_BUTTONS_"));
								rowDataMap.put("isNeedAdvise", rs.getString("IS_NEED_ADVISE_"));
								rowDataMap.put("isNeedAdviseExclude", rs.getString("IS_NEED_ADVISE_EXCLUDE_"));
//								rowDataMap.put("IsNeedBackAdvise", rs.getString("IS_NEED_BACK_ADVISE_"));

								rowDataMap.put("isAutoActivity", rs.getString("IS_AUTO_ACTIVITY_"));
								rowDataMap.put("backActivities", rs.getString("BACK_ACTIVITIES_"));
//								rowDataMap.put("submitBackStrategy", rs.getString("SUBMIT_BACK_STRATEGY"));
								rowDataMap.put("dealMethod", rs.getString("DEAL_METHOD_"));
								rowDataMap.put("passedCount", rs.getString("PASSED_COUNT_"));
								rowDataMap.put("autoReadActorType", rs.getString("AUTO_READ_ACTOR_TYPE_"));
								rowDataMap.put("autoReadActorValue", rs.getString("AUTO_READ_ACTOR_VALUE_"));
								rowDataMap.put("autoReadDisplayActorValue", rs.getString("AUTO_READ_DISPLAY_"));
								rowDataMap.put("autoSignatureActorType", rs.getString("AUTO_SIGNATURE_ACTOR_TYPE_"));
								rowDataMap.put("autoSignatureActorValue", rs.getString("AUTO_SIGNATURE_ACTOR_VALUE_"));
								rowDataMap.put("autoSignatureDisplayActorValue",
										rs.getString("AUTO_SIGNATURE_DISPLAY_"));
								rowDataMap.put("displayActorValue", rs.getString("ACTOR_VALUE_DISPLAY_"));
								rowDataMap.put("autoReadDisplayActorValue", rs.getString("AUTO_READ_DISPLAY_"));
								rowDataMap.put("autoSignatureDisplayActorValue",
										rs.getString("AUTO_SIGNATURE_DISPLAY_"));
								rowDataMap.put("isAttachmentChecked", rs.getString("IS_ATTACHMENT_CHECKED_"));
								rowDataMap.put("conditionRouteType", rs.getString("CONDITION_ROUTE_TYPE_"));
								rowDataMap.put("conditionRouteValue", rs.getString("CONDITION_ROUTE_VALUE_"));
								rowDataMap.put("messageTypes", rs.getString("MESSAGE_TYPES_"));
								rowDataMap.put("messageContent", rs.getString("MESSAGE_CONTENT_"));
								rowDataMap.put("readMessageTypes", rs.getString("READ_MESSAGE_TYPES_"));
								rowDataMap.put("readMessageContent", rs.getString("READ_MESSAGE_CONTENT_"));
								rowDataMap.put("signatureMessageTypes", rs.getString("SIGNATURE_MESSAGE_TYPES_"));
								rowDataMap.put("signatureMessageContent", rs.getString("SIGNATURE_MESSAGE_CONTENT_"));
								return rowDataMap;
							}
						});
				config.setActivitiDefine(activitiesDetailDatas);
				configs.add(config);
			}
		}
		return configs;
	}
	
	@Override
	public WorkflowConfigData exportWorkflowConfigDataUnique(String id) throws Exception {

		List<WorkflowConfigData> configs = new ArrayList<WorkflowConfigData>();
		String designerSql = "select t_jbpm_designer_transfer.*,jbpm4_deployment.dbid_ from t_jbpm_designer_transfer left join jbpm4_deployment on jbpm4_deployment.jbpm_workflow_designer_id_ = t_jbpm_designer_transfer.id_ where t_jbpm_designer_transfer.id_ = ?";
		
		
		List<Map<String, String>> workflowList = this.jbpmDao.getJdbcTemplate().query(designerSql,new Object[]{id},
				new RowMapper<Map<String, String>>() {
					@Override
					public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
						Map<String, String> rowDataMap = new HashMap<String, String>();
						rowDataMap.put("displayName", rs.getString("DISPLAY_NAME_"));
						rowDataMap.put("type", rs.getString("TYPE_"));
						rowDataMap.put("position", rs.getString("POSITION_"));
						rowDataMap.put("code", rs.getString("CODE_"));
						rowDataMap.put("workflowName", rs.getString("WORKFLOW_NAME_"));
						rowDataMap.put("key", rs.getString("KEY_"));
						rowDataMap.put("keyOne", rs.getString("KEY_ONE_"));
						rowDataMap.put("keyTwo", rs.getString("KEY_TWO_"));
						rowDataMap.put("keyThree", rs.getString("KEY_THREE_"));
						rowDataMap.put("keyFour", rs.getString("KEY_FOUR_"));
						rowDataMap.put("keyFive", rs.getString("KEY_FIVE_"));
						rowDataMap.put("keySix", rs.getString("KEY_SIX_"));
						rowDataMap.put("keySeven", rs.getString("KEY_SEVEN_"));
						rowDataMap.put("keyEight", rs.getString("KEY_EIGHT_"));
						rowDataMap.put("keyNine", rs.getString("KEY_NINE_"));
						rowDataMap.put("keyTen", rs.getString("KEY_TEN_"));
						rowDataMap.put("version", rs.getString("VERSION_"));
						rowDataMap.put("jpdlVersion", rs.getString("JPDL_VERSION_"));
						rowDataMap.put("description", rs.getString("DESCRIPTION_"));
						rowDataMap.put("workflowDefinition", rs.getString("WORKFLOW_DEFINITION_"));
						rowDataMap.put("createTime", rs.getString("CREATE_TIME_"));
						rowDataMap.put("designerWorkflowJson", rs.getString("DESIGNER_WORKFLOW_JSON_"));
						rowDataMap.put("transferedJpdlXml", rs.getString("TRANSFERED_JPDL_XML_"));
						rowDataMap.put("fromDBDeployId", rs.getString("DBID_"));
						rowDataMap.put("maxDotX", rs.getString("MAX_DOT_X_"));
						rowDataMap.put("maxDotY", rs.getString("MAX_DOT_Y_"));
						return rowDataMap;
					}
				});

		for (Map<String, String> workflowDefine : workflowList) {
			WorkflowConfigData config = new WorkflowConfigData();
			String workflowName = workflowDefine.get("workflowName");
			int version = Integer.parseInt(workflowDefine.get("version"));
			String fromDBDeployId = workflowDefine.get("fromDBDeployId");
			config.setWorkflowName(workflowName);
			config.setVersion(String.valueOf(version));
			config.setDeployId(fromDBDeployId);
			config.setWorkflowDefine(workflowDefine);
			if (null != fromDBDeployId) {
				String detailSql = "select * from t_jbpm_activity_detail where jbpm4_deployment_dbid_ =?";
				List<Map<String, String>> activitiesDetailDatas = this.jbpmDao.getJdbcTemplate().query(detailSql,
						new Object[]{fromDBDeployId},						
						new RowMapper<Map<String, String>>() {
							@Override
							public Map<String, String> mapRow(ResultSet rs, int index) throws SQLException {
								Map<String, String> rowDataMap = new HashMap<String, String>();
								rowDataMap.put("activityName", rs.getString("ACTIVITY_NAME_"));
								//rowDataMap.put("activityEnname", rs.getString("ACTIVITY_ENNAME_"));
								rowDataMap.put("activityKey", rs.getString("ACTIVITY_KEY_"));
								rowDataMap.put("activityPosition", rs.getString("ACTIVITY_POSITION_"));
								rowDataMap.put("activityType", rs.getString("ACTIVITY_TYPE_"));
								rowDataMap.put("activityAction", rs.getString("ACTIVITY_ACTION_"));
								rowDataMap.put("attachmentType", rs.getString("ATTACHMENT_TYPE_"));
								rowDataMap.put("attachmentTypeIds", rs.getString("ATTACHMENT_TYPE_IDS_"));
								rowDataMap.put("actorType", rs.getString("ACTOR_TYPE_"));
								rowDataMap.put("actorValue", rs.getString("ACTOR_VALUE_"));
								rowDataMap.put("displayActorValue", rs.getString("ACTOR_VALUE_DISPLAY_"));
								rowDataMap.put("formPath", rs.getString("FORM_PATH_"));
								rowDataMap.put("formTitle", rs.getString("FORM_TITLE_"));
								rowDataMap.put("operationButtons", rs.getString("OPERATION_BUTTONS_"));
								rowDataMap.put("isNeedAdvise", rs.getString("IS_NEED_ADVISE_"));
//								rowDataMap.put("IsNeedBackAdvise", rs.getString("IS_NEED_BACK_ADVISE_"));

								rowDataMap.put("isAutoActivity", rs.getString("IS_AUTO_ACTIVITY_"));
								rowDataMap.put("backActivities", rs.getString("BACK_ACTIVITIES_"));
//								rowDataMap.put("submitBackStrategy", rs.getString("SUBMIT_BACK_STRATEGY"));
								rowDataMap.put("dealMethod", rs.getString("DEAL_METHOD_"));
								rowDataMap.put("passedCount", rs.getString("PASSED_COUNT_"));
								rowDataMap.put("autoReadActorType", rs.getString("AUTO_READ_ACTOR_TYPE_"));
								rowDataMap.put("autoReadActorValue", rs.getString("AUTO_READ_ACTOR_VALUE_"));
								rowDataMap.put("autoReadDisplayActorValue", rs.getString("AUTO_READ_DISPLAY_"));
								rowDataMap.put("autoSignatureActorType", rs.getString("AUTO_SIGNATURE_ACTOR_TYPE_"));
								rowDataMap.put("autoSignatureActorValue", rs.getString("AUTO_SIGNATURE_ACTOR_VALUE_"));
								rowDataMap.put("autoSignatureDisplayActorValue",
										rs.getString("AUTO_SIGNATURE_DISPLAY_"));
								rowDataMap.put("displayActorValue", rs.getString("ACTOR_VALUE_DISPLAY_"));
								rowDataMap.put("autoReadDisplayActorValue", rs.getString("AUTO_READ_DISPLAY_"));
								rowDataMap.put("autoSignatureDisplayActorValue",
										rs.getString("AUTO_SIGNATURE_DISPLAY_"));
								rowDataMap.put("isAttachmentChecked", rs.getString("IS_ATTACHMENT_CHECKED_"));
								rowDataMap.put("conditionRouteType", rs.getString("CONDITION_ROUTE_TYPE_"));
								rowDataMap.put("conditionRouteValue", rs.getString("CONDITION_ROUTE_VALUE_"));
								rowDataMap.put("messageTypes", rs.getString("MESSAGE_TYPES_"));
								rowDataMap.put("messageContent", rs.getString("MESSAGE_CONTENT_"));
								rowDataMap.put("readMessageTypes", rs.getString("READ_MESSAGE_TYPES_"));
								rowDataMap.put("readMessageContent", rs.getString("READ_MESSAGE_CONTENT_"));
								rowDataMap.put("signatureMessageTypes", rs.getString("SIGNATURE_MESSAGE_TYPES_"));
								rowDataMap.put("signatureMessageContent", rs.getString("SIGNATURE_MESSAGE_CONTENT_"));
								return rowDataMap;
							}
						});
				config.setActivitiDefine(activitiesDetailDatas);
				configs.add(config);
			}
		}
		return configs.get(0);
	}
	
}
