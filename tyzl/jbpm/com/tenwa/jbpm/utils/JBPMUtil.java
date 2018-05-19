package com.tenwa.jbpm.utils;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.IdentityService;
import org.jbpm.api.ManagementService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.history.HistoryTask;
import org.jbpm.api.model.Activity;
import org.jbpm.api.model.Transition;
import org.jbpm.pvm.internal.env.EnvironmentFactory;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.jbpm.pvm.internal.history.model.HistoryProcessInstanceImpl;
import org.jbpm.pvm.internal.history.model.HistoryTaskImpl;
import org.jbpm.pvm.internal.history.model.HistoryTaskInstanceImpl;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;
import org.jbpm.pvm.internal.model.TransitionImpl;
import org.jbpm.pvm.internal.repository.DeploymentImpl;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.tenwa.business.entity.AttachmentFileUploadInfoDetail;
import com.tenwa.business.entity.DepartmentRole;
import com.tenwa.business.entity.Role;
import com.tenwa.business.entity.User;
import com.tenwa.business.entity.UserDepartment;
import com.tenwa.business.relationship.parse.RelationParserFactory;
import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.ActivityDetail;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfoUser;
import com.tenwa.jbpm.entity.JBPMWorkflowReadUser;
import com.tenwa.jbpm.entity.JBPMWorkflowSignatureUser;
import com.tenwa.jbpm.exception.JbpmException;
import com.tenwa.jbpm.model.InitiatorEnum;
import com.tenwa.jbpm.model.RouteInfo;
import com.tenwa.jbpm.model.WorkflowSubmitTextEnum;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.WebUtil;
import com.tenwa.leasing.utils.StrTools;
public class JBPMUtil 
{
   private static ProcessEngine processEngine=null;
   private static RepositoryService repositoryService=null;
   private static ExecutionService executionService=null;
   private static TaskService taskService=null;
   private static ManagementService managementService=null;
   private static HistoryService historyService=null;
   private static IdentityService identityService=null;
   private static final String SPLITSTR = "@@_@@";
   static
   {
	   processEngine = (ProcessEngine)WebUtil.getApplicationContext().getBean("processEngine");
	   repositoryService = processEngine.getRepositoryService();
	   executionService = processEngine.getExecutionService();
	   taskService = processEngine.getTaskService();
	   managementService = processEngine.getManagementService();
	   historyService  = processEngine.getHistoryService();
	   identityService = processEngine.getIdentityService();
   }
   
	public static ProcessEngine getProcessEngine() {
		return processEngine;
	}
	public static void setProcessEngine(ProcessEngine processEngine) {
		JBPMUtil.processEngine = processEngine;
	}
	public static RepositoryService getRepositoryService() {
		return repositoryService;
	}
	public static void setRepositoryService(RepositoryService repositoryService) {
		JBPMUtil.repositoryService = repositoryService;
	}
	public static ExecutionService getExecutionService() {
		return executionService;
	}
	public static void setExecutionService(ExecutionService executionService) {
		JBPMUtil.executionService = executionService;
	}
	public static TaskService getTaskService() {
		return taskService;
	}
	public static void setTaskService(TaskService taskService) {
		JBPMUtil.taskService = taskService;
	}
	public static ManagementService getManagementService() {
		return managementService;
	}
	public static void setManagementService(ManagementService managementService) {
		JBPMUtil.managementService = managementService;
	}
	public static HistoryService getHistoryService() {
		return historyService;
	}
	public static void setHistoryService(HistoryService historyService) {
		JBPMUtil.historyService = historyService;
	}
	public static IdentityService getIdentityService() {
		return identityService;
	}
	public static void setIdentityService(IdentityService identityService) {
		JBPMUtil.identityService = identityService;
	}
	public static List<User> getActorsListByActivitySetting(HttpServletRequest request,ActivityDetail curentActivity,String processInstanceId ,String actorType,String actorValue,Map<String,String> modelData,BaseService baseService,String operFlag) throws Exception
	{	
		//modify by tracywindy 2013-11-20 start
		if(null != modelData){
			if(null == modelData.get("isHasPutRequestParameters")){
				modelData.putAll(QueryUtil.getRequestParameterMapByAjax(request));
				modelData.put("isHasPutRequestParameters", "true");
			}
		}
		//modify by tracywindy 2013-11-20 end
		List<User> actorsList = new ArrayList<User>();
		try {
			String requestInitiator = modelData.get("requestInitiator");
			User   requestInitiatorUser = (User)baseService.findResultsByHSQL("from User u where u.username = ?",requestInitiator).get(0);
			
			InitiatorEnum initiatorEnumType = InitiatorEnum.valueOf(actorType.toUpperCase());
			actorValue = QueryUtil.getQueryString(modelData, StringUtil.nullToString(actorValue));
			String[] actorValueIds = actorValue.split(",");
			Object[] actorValueArrayIds = new Object[]{actorValueIds};
			switch(initiatorEnumType)
			{
			    case REQUESTINITIATOR:{
			    	actorsList.add(requestInitiatorUser);
			    	break;
			    }
				case USER:{
					actorsList = (List<User>)baseService.findEntityByIDArray(User.class,actorValueIds);
					break;
				}
				case REQUESTINITIATORRELATION:
				case SUBMITRELATION:{
					actorsList = JBPMUtil.getUsersByRelationRole(actorType, actorValueIds, modelData.get("jbpmWorkflowHistoryInfoUserId"), requestInitiatorUser.getId(), baseService);
					break;
				}
				case REQUESTINITIATORDEFINEDRELATION:				
				case SUBMITDEFINEDRELATION:{
					actorsList = JBPMUtil.getUsersByDefinedRelationRole(actorType, actorValueIds, modelData.get("jbpmWorkflowHistoryInfoUserId"),
							requestInitiatorUser.getId(), baseService);
					break;
				}
				case DEPT:{
					String hsql = "select u from User u left join u.userDepts ud left join ud.dept d where d.id in(:ids)";
					actorsList = baseService.findResultsByNamedParamHSQL(hsql,new String[]{"ids"},actorValueArrayIds);
					break;
				}
				case DEPTROLE:{
					String hsql = "select u from User u left join u.userDeptRoles udr left join udr.deptRole dr where dr.id in(:ids)";
					actorsList = baseService.findResultsByNamedParamHSQL(hsql,new String[]{"ids"},actorValueArrayIds);
					break;
				}
				case GROUP:{
					String hsql = "select u from User u left join u.userGroups ug left join ug.group g where g.id in(:ids)";
					actorsList = baseService.findResultsByNamedParamHSQL(hsql,new String[]{"ids"},actorValueArrayIds);
					break;
				}
				case STEP:{
					if(null == processInstanceId){
						actorsList.add(requestInitiatorUser);
					}
					else{
						//long hpDBID = taskInfo.getHistoryProcessInstanceImpl().getDbid();
						String hsql = "select jwhi from JBPMWorkflowHistoryInfo jwhi" +
						" where jwhi.activityDetail.id in(:ids) and jwhi.historyProcessInstanceImpl.processInstanceId = :processInstanceId ";
						List<JBPMWorkflowHistoryInfo> workflowHistoryInfos = baseService.findResultsByNamedParamHSQL(hsql,new String[]{"ids","processInstanceId"},new Object[]{actorValueIds,processInstanceId});
					    for(JBPMWorkflowHistoryInfo workflowHistoryInfo : workflowHistoryInfos){
					    	for(JBPMWorkflowHistoryInfoUser workflowHistoryInfoUser : workflowHistoryInfo.getJbpmWorkflowHistoryInfoUsers()){
					    		User dealedPlanUser = workflowHistoryInfoUser.getPlanActor();
					    		actorsList.add(dealedPlanUser);
					    	}
					    }
					}
					break;
				}
				case FORMFIELD:{
					String hsql = "select u from User u where u.username in(:ids) or u.id in(:ids)";
					actorsList = baseService.findResultsByNamedParamHSQL(hsql,new String[]{"ids"},actorValueArrayIds);
					break;
				}
				case SQL:{								
					Map sqlmap=new HashMap();
					sqlmap = StrTools.setSqlCondtion(actorValue, modelData);
					List<Map<String,Object>> listMaps = baseService.getBussinessDao().getJdbcTemplate().queryForList(sqlmap.get("sql").toString(),(Object [])sqlmap.get("value"),(int [])sqlmap.get("valueType"));
					List<String> usernames = new ArrayList<String>();
					String usernameKey =  "username";
					if(listMaps.size()>0){
						Set<String> s =listMaps.get(0).keySet();
						if(1 == s.size()){
							for(String key : s){
								usernameKey = key;
							}
						}
					}
					for(Map<String,Object> m :listMaps)
					{
						usernames.add(StringUtil.nullToString(m.get(usernameKey)));
					}
					String[] usernamesArr = new String[usernames.size()];
					usernamesArr = usernames.toArray(usernamesArr);
					actorsList = baseService.findResultsByNamedParamHSQL("from User u where u.username in(:usernames) or u.id in(:usernames)",new String[]{ "usernames"}, new Object[]{usernamesArr});
					break;
				}
			}
		} catch (Exception e) {
			String  promitInfo = "";
			if("read".equals(operFlag)){
				promitInfo="传阅";
			}
			else if("signature".equals(operFlag)){
				promitInfo="会签";
			}
			throw new JbpmException("< "+curentActivity.getActivityName()+" >,"+promitInfo+"节点没有处理人");
		}
		String currentActivityType = curentActivity.getActivityType();
		String isAutoActivity      = curentActivity.getIsAutoActivity();
		boolean isChecked = ((!"1".equals(isAutoActivity)) || (!"start".equalsIgnoreCase(currentActivityType)) || (!"end".equalsIgnoreCase(currentActivityType)));
		String  promitInfo = "";
		if("read".equals(operFlag)){
			promitInfo="传阅";
		}
		else if("signature".equals(operFlag)){
			promitInfo="会签";
		}
		Set<User> realActorsSet = new HashSet<User>();
		for(User actor : actorsList){
			if(actor.isEnabled()){
				realActorsSet.add(actor);
			}
		}
		if((realActorsSet.size()<1)&&(isChecked)&&("actor".equals(operFlag)))
		{
			throw new JbpmException("< "+curentActivity.getActivityName()+" >,"+promitInfo+"节点没有处理人，或者相应人员已离职！");
		}
		List<User> realActorsList = new ArrayList<User>();
		realActorsList.addAll(realActorsSet);
		return realActorsList;
	}
	
	public static List<User> getUsersByDefinedRelationRole(String relation, String[] roleIds, String jbpmWorkflowHistoryInfoUserId,
			String requestInitiatorId, BaseService baseService) throws Exception {
		User currentUser = null;
		if ("requestInitiatorDefinedRelation".equalsIgnoreCase(relation)) {
			currentUser = baseService.findEntityByID(User.class, requestInitiatorId);
		} else if ("submitDefinedRelation".equalsIgnoreCase(relation)) {
			currentUser = baseService.findEntityByID(JBPMWorkflowHistoryInfoUser.class, jbpmWorkflowHistoryInfoUserId).getPlanActor();
		}
		List<User> actorsList = RelationParserFactory.parser(currentUser, roleIds, baseService);

		return actorsList;

	}
	
	public static List<User> getUsersByRelationRole(
			String relation,
			String[] roleIds,
			String jbpmWorkflowHistoryInfoUserId,
			String requestInitiatorId,BaseService baseService
		) throws Exception{
		User currentUser = null;
		if("requestInitiatorRelation".equals(relation)){
			currentUser = baseService.findEntityByID(User.class, requestInitiatorId);
		}else if("submitRelation".equals(relation)){
			currentUser = baseService.findEntityByID(JBPMWorkflowHistoryInfoUser.class, jbpmWorkflowHistoryInfoUserId).getPlanActor();
		}
		//List<Role> roles = baseService.findResultsByHSQL("select r from Role r where id in(?)", roleIds);
		List<Role> roles = (List<Role>)baseService.findEntityByIDArray(Role.class,roleIds);
		Set<UserDepartment> userDepts = currentUser.getUserDepts();
		Set<String> deptRoleIdList = new HashSet<String>();
		for(Role role : roles){
			for(DepartmentRole deptRole : role.getDeptRoles()){
				String outerDeptId = deptRole.getDept().getId();
				for(UserDepartment userDept : userDepts){
					String innerDeptId = userDept.getDept().getId();
					if(outerDeptId.equals(innerDeptId)){
						deptRoleIdList.add(deptRole.getId());
						break;
					}
				}
			}
		}
		String[] deptRoleArrayIds = new String[deptRoleIdList.size()];
		deptRoleArrayIds = deptRoleIdList.toArray(deptRoleArrayIds);
		String hsql = "select u from User u left join u.userDeptRoles udr left join udr.deptRole dr where dr.id in(:ids)";
		List<User> actorsList = baseService.findResultsByNamedParamHSQL(hsql,new String[]{"ids"},new Object[]{deptRoleArrayIds});
		return actorsList;
	} 
	public static Map<String,String> getProcessInstanceHistoryData(HibernateTemplate hibernateTemplate,String processInstanceDBID)
	{
		String queryProcessInstanceHistoryHSql = "from JBPMWorkflowHistoryInfo jwhi where jwhi.historyProcessInstanceImpl.dbid=? and jwhi.historyTaskInstanceImpl is null";
		JBPMWorkflowHistoryInfo jwhi = (JBPMWorkflowHistoryInfo)hibernateTemplate.find(queryProcessInstanceHistoryHSql, Long.parseLong(processInstanceDBID)).get(0);
		String sourceKeyStr = new String(jwhi.getProcessedFormVariables());
		String sourceValueStr = new String(jwhi.getProcessedFormValues());
		Map<String,String>  map = getVaribalesMapByAllString(sourceKeyStr,sourceValueStr);
		return map;
	}
	public static Map<String,String> getTaskHistoryData(HibernateTemplate hibernateTemplate,String taskId)
	{
		String queryTaskHistoryHSql = "from JBPMWorkflowHistoryInfo jwhi where jwhi.historyTaskInstanceImpl.historyTask.dbid=?";
		JBPMWorkflowHistoryInfo jwhi = (JBPMWorkflowHistoryInfo)hibernateTemplate.find(queryTaskHistoryHSql, Long.parseLong(taskId)).get(0);
		String sourceKeyStr = new String(jwhi.getProcessedFormVariables());
		String sourceValueStr = new String(jwhi.getProcessedFormValues());
		Map<String,String>  map = getVaribalesMapByAllString(sourceKeyStr,sourceValueStr);
		return map;
	}
	public static Map<String,String> getTaskHistoryDataById(HibernateTemplate hibernateTemplate,String taskId){
		String queryTaskHistoryHSql = "from JBPMWorkflowHistoryInfo jwhi where jwhi.id=?";
		JBPMWorkflowHistoryInfo jwhi = (JBPMWorkflowHistoryInfo)hibernateTemplate.find(queryTaskHistoryHSql, taskId).get(0);
		String sourceKeyStr = new String(jwhi.getProcessedFormVariables());
		String sourceValueStr = new String(jwhi.getProcessedFormValues());
		Map<String,String>  map = getVaribalesMapByAllString(sourceKeyStr,sourceValueStr);
		return map;
	}
	public static String getMapDiff(Map<String,String>fromMap,Map<String,String> toMap) throws Exception{
		 JSONObject jsonObject = new JSONObject();
		for(String key:fromMap.keySet()){
			if(toMap.containsKey(key)){
				if(!fromMap.get(key).equals(toMap.get(key))){
					jsonObject.put(key, toMap.get(key));
				}
			}else{
				String tempkey="";
				if(key.startsWith("proj_")){//项目层和合同层比较
					tempkey="contract_"+key.substring("proj_".length());
				}
                if(key.startsWith("contract_")){//合同层和项目层比较
                	tempkey="proj_"+key.substring("contract_".length());
				}
                if(key.startsWith("rawValue_")){
                	if(key.startsWith("rawValue_proj_")){//项目层和合同层比较
    					tempkey="rawValue_contract_"+key.substring("rawValue_proj_".length());
    				}
                    if(key.startsWith("rawValue_contract_")){//合同层和项目层比较
                    	tempkey="rawValue_proj_"+key.substring("rawValue_contract_".length());
    				}
                }
                if(key.startsWith("json_")){
                	if(key.startsWith("json_proj_")){//项目层和合同层比较
    					tempkey="json_contract_"+key.substring("json_proj_".length());
    				}
                    if(key.startsWith("json_contract_")){//合同层和项目层比较
                    	tempkey="json_proj_"+key.substring("json_contract_".length());
    				}
                }
                if(toMap.containsKey(tempkey)){
	                if(!fromMap.get(key).equals(toMap.get(tempkey))){
						jsonObject.put(key, toMap.get(tempkey));
					}
                }
			}
		}
		return jsonObject.toString();
	}
	public static Map<String,String> getVaribalesMapByAllString(String sourceKeyStr,String sourceValueStr)
	{
		   Map<String,String>  map = new LinkedHashMap<String,String>();
		   String[] keyStrs   = sourceKeyStr.split(getSplitStr());
		   String[] valueStrs = sourceValueStr.split(getSplitStr());
		   
		   int len = Math.min(keyStrs.length,valueStrs.length);
		   for(int i=0;i<len;i++)
		   {
			   String key = keyStrs[i];
			   String value = valueStrs[i];
			   map.put(key, value);
		   }
		   return map;
	}
	public static void getVaribalesAllStringByMap(Map<String,String> map,StringBuffer sb_sourceKeyStr,StringBuffer sb_sourceValueStr)
	{
		int index = 0;
		Iterator<String> keySetIterator = map.keySet().iterator();
		while(keySetIterator.hasNext())
		{
		   String key = keySetIterator.next();
		   String value = map.get(key);
		   if(null == value)continue;
		   if( (index++) != 0 )
		   {
			   sb_sourceKeyStr.append(getSplitStr());
			   sb_sourceValueStr.append(getSplitStr());
		   }
		   sb_sourceKeyStr.append(key);
		   sb_sourceValueStr.append(value);
		}
	}
	public static void getVariablesInfo(HibernateTemplate hibernateTemplate,String processInstanceDBID,String taskId,Map<String,String> modelData)
	{
		Map<String,String> historyActivtyDataMap_processInstance = getProcessInstanceHistoryData(hibernateTemplate,processInstanceDBID);
		Map<String,String> historyActivtyDataMap = historyActivtyDataMap_processInstance;
		if(null != taskId)
		{
			Map<String,String> historyActivtyDataMap_task = getTaskHistoryData(hibernateTemplate,taskId);
			historyActivtyDataMap_processInstance.putAll(historyActivtyDataMap_task);
		}
		historyActivtyDataMap.putAll(modelData);
		modelData.putAll(historyActivtyDataMap);
	}
	public static void recursionActivityInfo(Transition transition,List<String> transitionToTasksList )
	{
		Activity activity = transition.getDestination();
		if("task".equalsIgnoreCase(activity.getType()))
		{
			transitionToTasksList.add(activity.getName());
		}
		else
		{
			 for(Transition transition_inner:activity.getOutgoingTransitions())
			 {
				 recursionActivityInfo(transition_inner,transitionToTasksList);
			 }
		}
	}
	public static Map<String,List<Map<String,List<String>>>>   getAllWorkFlowNodesTransitions(String deployId) throws Exception
	{
		ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().deploymentId(deployId).uniqueResult();
		ProcessDefinitionImpl definitionimpl = (ProcessDefinitionImpl)definition;
		//    01 ->r1 》》02
		//       ->r2 》》03|04  类型加名称
		Map<String,List<Map<String,List<String>>>>  allWorkFlowNodesTransitions = new HashMap<String,List<Map<String,List<String>>>>();
		List<? extends Activity> ActivitiesList = definitionimpl.getActivities();
		for (Activity activity : ActivitiesList) 
		{
			 if("task".equalsIgnoreCase(activity.getType()))
			 {
				 List<Map<String,List<String>>> routesList = new ArrayList<Map<String,List<String>>>();
	             Map<String,List<String>>   transitionInfoMap   = new HashMap<String,List<String>>();
	             for(Transition transition:activity.getOutgoingTransitions())
	             {
	            	 List<String>  transitionToTasksList = new ArrayList<String>();
	            	 recursionActivityInfo(transition,transitionToTasksList);
	            	 transitionInfoMap.put("ROUTE_"+JBPMUtil.getSplitStr()+transition.getName(), transitionToTasksList);
	            	 routesList.add(transitionInfoMap);
	             }
				 allWorkFlowNodesTransitions.put("ACTIVITYNAME_"+activity.getName(), routesList); 
			 }
		}
		return null;
	}
	public static void recursionActivityRouteInfo(Transition transition,List<Activity> targetTaskActivitys )
	{
		Activity activity = transition.getDestination();
		if("end".equalsIgnoreCase(activity.getType())||"task".equalsIgnoreCase(activity.getType()))
		{
		   targetTaskActivitys.add(activity);
		}
		else
		{
			for(Transition transition_inner:activity.getOutgoingTransitions())
			{
				recursionActivityRouteInfo(transition_inner,targetTaskActivitys);
			}
		}
	}
	public static boolean isSubmitPassed(ActivityDetail currentActivityDetail,JBPMWorkflowHistoryInfo jhi_completedTaskImpl_inner) throws Exception
	{
			String dealMethod = currentActivityDetail.getDealMethod();
			boolean isSubmit = false;
			if(null == dealMethod){
				isSubmit = true;
			}
			//处理方式   1.单人处理,2.多人全部,3.多人任一,4.N人处理
			if("Single".equals(dealMethod))//"单人处理"
			{
				isSubmit = true;
			}
			else if("OnePassed".equals(dealMethod))//"多人任一"
			{
				isSubmit = true;
			}
			else if(("AllPassed".equals(dealMethod))||(("NPassed".equals(dealMethod))))//"多人全部"或者"处理"
			{
				Set<JBPMWorkflowHistoryInfoUser>  jbpmWorkflowHistoryInfoUsers = jhi_completedTaskImpl_inner.getJbpmWorkflowHistoryInfoUsers();
				int passedCount = 0;
				if("AllPassed".equals(dealMethod))
				{
					passedCount = jbpmWorkflowHistoryInfoUsers.size();
				}
				else
				{
					passedCount = Integer.parseInt(currentActivityDetail.getPassedCount());
				}
				if(1 == passedCount)
				{
					isSubmit = true;
				}
				else
				{
					int currentCount = 0;
					for(JBPMWorkflowHistoryInfoUser jbpmWorkflowHistoryInfoUser : jbpmWorkflowHistoryInfoUsers)
					{
						if(null != jbpmWorkflowHistoryInfoUser.getActualActor())
						{
							if((passedCount-1) == ++currentCount)
							{
								isSubmit = true;
								break;
							}
						}
					}
				}
		     }
			return isSubmit;
	}
//	protected  static  void removeWorkflowAllProcessInstance(String workflowName,int version,JdbcTemplate jdbcTemplate,HibernateTemplate hibernateTemplate) throws Exception{
//		String querySql = "SELECT DBID_ as PROCESSINSTANCEDBID_ FROM JBPM4_HIST_PROCINST WHERE PROCDEFID_ = ?";
//		for(Map<String,Object> rowMap :jdbcTemplate.queryForList(querySql, workflowName+"-"+version)){
//			Long processInstanceDBID_ = new Long(rowMap.get("PROCESSINSTANCEDBID_").toString());
//			removeWorkflowProcessInstance(processInstanceDBID_, jdbcTemplate,hibernateTemplate);
//		}
//		
//	}
	@SuppressWarnings("unchecked")
	public static void removeWorkflowProcessInstance(Long processInstanceDBID_,JdbcTemplate jdbcTemplate,HibernateTemplate hibernateTemplate) throws Exception
	{
		SqlParameterValue processInstanceDBID = new SqlParameterValue(Types.NUMERIC,processInstanceDBID_);
		//删除run
		String delExececution    = "DELETE  FROM JBPM4_EXECUTION WHERE HISACTINST_ IN(SELECT DBID_ FROM JBPM4_HIST_ACTINST WHERE JBPM4_HIST_ACTINST.HPROCI_ = ? )";
		jdbcTemplate.update(delExececution, processInstanceDBID);
		String delTask           = "DELETE  FROM JBPM4_TASK WHERE PROCINST_= ?";
		jdbcTemplate.update(delTask, processInstanceDBID);
		//删除history
		String updateHistoryInfo = "UPDATE T_JBPM_WORKFLOW_INFO SET JBPM4_HIST_ACTINST_DBID_ = NULL,JBPM4_HIST_ACTINST_LAST_DBID_=NULL,PREV_JBPM4_HIST_ACTINST_DBID_ = NULL WHERE JBPM4_HIST_PROCINST_DBID_ = ?";
		jdbcTemplate.update(updateHistoryInfo, processInstanceDBID);
		
		StringBuffer historyTaskIds = new StringBuffer();
		String queryAllHistTaskID = "SELECT HTASK_ FROM JBPM4_HIST_ACTINST WHERE JBPM4_HIST_ACTINST.HPROCI_ = ? ";
		int index = 0;
		for(Map<String,Object> map :jdbcTemplate.queryForList(queryAllHistTaskID, processInstanceDBID)){
			if(++index > 1)
			{
				historyTaskIds.append(",");
			}
			historyTaskIds.append(map.get("HTASK_"));
		}
		
		String delHistActinst    = "DELETE  FROM JBPM4_HIST_ACTINST WHERE HPROCI_= ?";
		jdbcTemplate.update(delHistActinst, processInstanceDBID);
		
		if(historyTaskIds.length()>0)
		{
			String tempids=historyTaskIds.toString().replaceAll("'", "");
			String delHistTask       = "DELETE  FROM JBPM4_HIST_TASK WHERE DBID_ IN("+StringUtil.toSqlJoinStringToIn(tempids, ",")+")";
			jdbcTemplate.update(delHistTask,tempids.split(","),StringUtil.toSqlJoinStringToType(tempids,","));
		}
		//删除传阅用户信息
		String queryString_JBPMWorkflowReadUser = " from JBPMWorkflowReadUser jwhiu where jwhiu.jbpmWorkflowHistoryInfo.historyProcessInstanceImpl.dbid  = ? ";
		List<JBPMWorkflowReadUser> removed_JBPMWorkflowReadUsers =(List<JBPMWorkflowReadUser>)hibernateTemplate.find(queryString_JBPMWorkflowReadUser, processInstanceDBID_);
		String ids = getIdStrsFromList(removed_JBPMWorkflowReadUsers);
		if(ids.length() > 0){
			String tempids=ids.replaceAll("'", "");
			String deletePushRead = "delete from T_JBPM_APP_PUSH WHERE TASK_ID_ IN ("+StringUtil.toSqlJoinStringToIn(tempids, ",")+")";
			jdbcTemplate.update(deletePushRead,tempids.split(","),StringUtil.toSqlJoinStringToType(ids,","));
		}
		hibernateTemplate.deleteAll(removed_JBPMWorkflowReadUsers);
		hibernateTemplate.flush();
		//删除会签用户信息   
		String queryString_JBPMWorkflowSignatureUser = " from JBPMWorkflowSignatureUser jwhiu where jwhiu.jbpmWorkflowHistoryInfo.historyProcessInstanceImpl.dbid  = ? ";
		List<JBPMWorkflowSignatureUser> removed_JBPMWorkflowSignatureUsers =(List<JBPMWorkflowSignatureUser>)hibernateTemplate.find(queryString_JBPMWorkflowSignatureUser, processInstanceDBID_);
		ids = getIdStrsFromList(removed_JBPMWorkflowSignatureUsers);
		if(ids.length() > 0){
			String tempids=ids.replaceAll("'", "");	
			String deletePushSignature = "delete from T_JBPM_APP_PUSH WHERE TASK_ID_ IN ("+StringUtil.toSqlJoinStringToIn(tempids, ",")+")";
			jdbcTemplate.update(deletePushSignature,tempids.split(","),StringUtil.toSqlJoinStringToType(ids,","));
		}
		hibernateTemplate.deleteAll(removed_JBPMWorkflowSignatureUsers);
		hibernateTemplate.flush();
		
		//删除处理用户信息   
		String queryString_JBPMWorkflowHistoryInfoUser = " from JBPMWorkflowHistoryInfoUser jwhiu where jwhiu.jbpmWorkflowHistoryInfo.historyProcessInstanceImpl.dbid  = ? ";
		List<JBPMWorkflowHistoryInfoUser> removed_JBPMWorkflowHistoryInfoUsers =(List<JBPMWorkflowHistoryInfoUser>)hibernateTemplate.find(queryString_JBPMWorkflowHistoryInfoUser,processInstanceDBID_);
		ids = getIdStrsFromList(removed_JBPMWorkflowHistoryInfoUsers);
		if(ids.length() > 0){
			String tempids=ids.replaceAll("'", "");	
			String deletePushUser = "delete from T_JBPM_APP_PUSH WHERE TASK_ID_ IN ("+StringUtil.toSqlJoinStringToIn(tempids, ",")+")";
			jdbcTemplate.update(deletePushUser,tempids.split(","),StringUtil.toSqlJoinStringToType(ids,","));
		}
		hibernateTemplate.deleteAll(removed_JBPMWorkflowHistoryInfoUsers);
		hibernateTemplate.flush();
		//删除附件信息
		Set<String> delAttachmentFileUploadInfoIds = new HashSet<String>();
		List<JBPMWorkflowHistoryInfo>  jbpmWorkflowHistoryInfoTasks = (List<JBPMWorkflowHistoryInfo>)hibernateTemplate.find("from  JBPMWorkflowHistoryInfo jwhi where jwhi.historyProcessInstanceImpl.dbid =?",processInstanceDBID_);
		for(JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfoTask : jbpmWorkflowHistoryInfoTasks)
		{
			List<AttachmentFileUploadInfoDetail>  attachmentFileUploadInfoDetails = (List<AttachmentFileUploadInfoDetail>)hibernateTemplate.find("from  AttachmentFileUploadInfoDetail afuid where afuid.jbpmWorkflowHistoryInfo.id =?",jbpmWorkflowHistoryInfoTask.getId());
		    for(AttachmentFileUploadInfoDetail attachmentFileUploadInfoDetail : attachmentFileUploadInfoDetails)
		    {
		    	String delAttachmentFileUploadInfoId = attachmentFileUploadInfoDetail.getAttachmentFileUploadInfo().getId();
		    	delAttachmentFileUploadInfoIds.add(delAttachmentFileUploadInfoId);
		    	//删除上传明细下载表
		    	hibernateTemplate.bulkUpdate("delete from  AttachmentFileUploadInfoDetailDownload afuidd where afuidd.attachmentFileUploadInfoDetail.id = ?", attachmentFileUploadInfoDetail.getId());
		    }
		    //删除上传明细表
			hibernateTemplate.deleteAll(attachmentFileUploadInfoDetails);
			hibernateTemplate.flush();
		}
		for(String delAttachmentFileUploadInfoId : delAttachmentFileUploadInfoIds)
		{
			String delAttachmentFileUploadInfoSql = "delete from t_attachment_info where not exists(select t_attachment_info_detail.id_ from t_attachment_info_detail where t_attachment_info_detail.attachment_info_id_ = t_attachment_info.id_) and t_attachment_info.id_ = ? ";
			jdbcTemplate.update(delAttachmentFileUploadInfoSql,delAttachmentFileUploadInfoId);
		}
		String  delHistoryInfo   = "DELETE  FROM T_JBPM_WORKFLOW_INFO WHERE JBPM4_HIST_PROCINST_DBID_ = ? ";
		jdbcTemplate.update(delHistoryInfo, processInstanceDBID);
		
		String  delHistProcinst   = "delete from jbpm4_hist_procinst where dbid_=?";
		jdbcTemplate.update(delHistProcinst, processInstanceDBID);
	}
	@SuppressWarnings("unchecked")
	public static void updateAbandonWorkflowProcessInstance(HttpServletRequest request,Long processInstanceDBID_,JdbcTemplate jdbcTemplate,HibernateTemplate hibernateTemplate) throws Exception
	{
		SqlParameterValue processInstanceDBID = new SqlParameterValue(Types.NUMERIC,processInstanceDBID_);
		//删除run
		String delExececution    = "DELETE  FROM JBPM4_EXECUTION WHERE HISACTINST_ IN(SELECT DBID_ FROM JBPM4_HIST_ACTINST WHERE JBPM4_HIST_ACTINST.HPROCI_ = ? )";
		jdbcTemplate.update(delExececution, processInstanceDBID);
		String delTask           = "DELETE  FROM JBPM4_TASK WHERE PROCINST_= ?";
		jdbcTemplate.update(delTask, processInstanceDBID);
		
		//全局变量
	    String abondonButtonDisplayText  = "废弃流程";
		//String abondonStateDisplayText   = "已废弃";
	    String abondonState   = WorkflowSubmitTextEnum.abondonButtonDisplayText.getText();//"已废弃";
		Date   nowTime   = new Date();
		String endTime   = DateUtil.getSystemTimeByFormat(nowTime,"yyyy-MM-dd HH:mm:ss");
		User user    =  (User)SecurityUtil.getPrincipal();
		
		//废弃历史
		JBPMWorkflowHistoryInfo  processInstanceHistoryInfo = (JBPMWorkflowHistoryInfo)hibernateTemplate.find("from  JBPMWorkflowHistoryInfo jwhi where jwhi.historyProcessInstanceImpl.dbid =? and ( jwhi.historyTaskInstanceImpl is null)",processInstanceDBID_).get(0);
		//T_JBPM_WORKFLOW_INFO --process_state_
		processInstanceHistoryInfo.setProcessInstanceState(abondonState);
		hibernateTemplate.update(processInstanceHistoryInfo);
		
		//JBPM4_HIST_PROCINST --end_,duration_
		String lastActivityName = null;
		HistoryProcessInstanceImpl historyProcessInstanceImpl = processInstanceHistoryInfo.getHistoryProcessInstanceImpl();
		Set<HistoryTaskInstanceImpl>  activedHistoryTaskInstanceImpls = processInstanceHistoryInfo.getActiveHistoryTaskInstanceImpls();
		
		JBPMWorkflowHistoryInfo lastJbpmWorkflowHistoryInfoTask = null;
		for(HistoryTaskInstanceImpl historyTaskInstanceImpl : activedHistoryTaskInstanceImpls)
		{
			//更新JBPM4_HIST_ACTINST --end_ duration_ transition_
			historyTaskInstanceImpl.setProcessInstanceHistoryInfo(null);
			historyTaskInstanceImpl.setLastToProcessInstanceHistoryInfo(processInstanceHistoryInfo);
			historyTaskInstanceImpl.setEndTime(nowTime);
			historyTaskInstanceImpl.setTransitionName(abondonButtonDisplayText);
			hibernateTemplate.update(historyTaskInstanceImpl);
			
			//更新JBPM4_HIST_TASK  --outcome_ ,end_,duration_
			HistoryTaskImpl  historyTaskImpl = historyTaskInstanceImpl.getHistoryTask();
			historyTaskImpl.setOutcome(abondonButtonDisplayText);
			historyTaskImpl.setEndTime(nowTime);
			historyTaskImpl.setState(HistoryTask.STATE_COMPLETED);
			hibernateTemplate.update(historyTaskImpl);
			
			//更新T_JBPM_WORKFLOW_INFO --process_state_
			lastJbpmWorkflowHistoryInfoTask = historyTaskInstanceImpl.getTaskHistoryInfo();
			lastJbpmWorkflowHistoryInfoTask.setProcessInstanceState(abondonState);
			hibernateTemplate.update(lastJbpmWorkflowHistoryInfoTask);
			
			//赋值当前节点为最终节点
			lastActivityName = historyTaskInstanceImpl.getActivityName();
		}
		//更新JBPM4_HIST_PROCINST --end_,duration_
		historyProcessInstanceImpl.setEndActivityName(lastActivityName);
		historyProcessInstanceImpl.setEndTime(nowTime);
		historyProcessInstanceImpl.setState(HistoryProcessInstance.STATE_ENDED);
		hibernateTemplate.update(historyProcessInstanceImpl);
		//废弃传阅用户信息
		String queryString_JBPMWorkflowReadUser = " from JBPMWorkflowReadUser jwhiu where jwhiu.jbpmWorkflowHistoryInfo.historyProcessInstanceImpl.dbid  = ?  and jwhiu.endTime is null ";
		List<JBPMWorkflowReadUser> abondoned_JBPMWorkflowReadUsers =(List<JBPMWorkflowReadUser>)hibernateTemplate.find(queryString_JBPMWorkflowReadUser, processInstanceDBID_);
		for(JBPMWorkflowReadUser ru : abondoned_JBPMWorkflowReadUsers){
			ru.setEndTime(endTime);
			ru.setReadedAdvise(abondonButtonDisplayText);
			hibernateTemplate.update(ru);
		}
		//废弃会签用户信息   
		String queryString_JBPMWorkflowSignatureUser = " from JBPMWorkflowSignatureUser jwhiu where jwhiu.jbpmWorkflowHistoryInfo.historyProcessInstanceImpl.dbid  = ?  and jwhiu.endTime is null ";
		List<JBPMWorkflowSignatureUser> abondoned_JBPMWorkflowSignatureUsers =(List<JBPMWorkflowSignatureUser>)hibernateTemplate.find(queryString_JBPMWorkflowSignatureUser, processInstanceDBID_);
		for(JBPMWorkflowSignatureUser su : abondoned_JBPMWorkflowSignatureUsers){
			su.setEndTime(endTime);
			su.setSignaturedAdvise(abondonButtonDisplayText);
			hibernateTemplate.update(su);
		}
		//废弃处理用户信息   
		String queryString_JBPMWorkflowHistoryInfoUser = " from JBPMWorkflowHistoryInfoUser jwhiu where jwhiu.jbpmWorkflowHistoryInfo.historyProcessInstanceImpl.dbid  = ? and jwhiu.endTime is null ";
		List<JBPMWorkflowHistoryInfoUser> abondoned_JBPMWorkflowHistoryInfoUsers =(List<JBPMWorkflowHistoryInfoUser>)hibernateTemplate.find(queryString_JBPMWorkflowHistoryInfoUser,processInstanceDBID_);
		for(JBPMWorkflowHistoryInfoUser u : abondoned_JBPMWorkflowHistoryInfoUsers){
			u.setEndTime(endTime);
			u.setActualActor(user);
			u.setProcessedAdvise(abondonButtonDisplayText);
			hibernateTemplate.update(u);
		}
        //执行start的delete方法
		DeploymentImpl deploymentImpl = processInstanceHistoryInfo.getDeploymentImpl();
		Set<ActivityDetail> activitiesDetailList = deploymentImpl.getActivityDetails();
		final String currentJbpmWorkflowDesignerId = "currentJbpmWorkflowDesignerId";
		final String currentJbpmDepolymentId       = "currentJbpmDepolymentId";
		final String currentActivityDetailId       = "currentActivityDetailId";
		
		for(ActivityDetail activityDetail : activitiesDetailList){
			 String activityType =  StringUtil.nullToString(activityDetail.getActivityType());
			 if("start".equalsIgnoreCase(activityType))
			 {
				 String startAction =  StringUtil.nullToString(activityDetail.getActivityAction()).trim();
				 if(!"".equals(startAction))
				 {
						Map<String,String> modelData = JBPMUtil.getProcessInstanceHistoryData(hibernateTemplate, String.valueOf(processInstanceDBID_));
						String deleteAction = StringUtil.nullToString(activityDetail.getActivityAction()).trim();
						if(!deleteAction.isEmpty())
						{
							 //modify by tracywindy 2013-07-02
							 modelData.put(currentJbpmWorkflowDesignerId, deploymentImpl.getJbpmWorkflowDesigner().getId());
							 modelData.put(currentJbpmDepolymentId, deploymentImpl.getDbid()+"");
							 modelData.put(currentActivityDetailId, activityDetail.getId());
							 ((JbpmBaseAction)WebUtil.getApplicationContext().getBean(deleteAction)).delete(request, modelData,lastJbpmWorkflowHistoryInfoTask);
						}
				 }
				 break;
			 }
		}
		
	}
	public static List<RouteInfo>   getWorkFlowRouteInfos(JBPMWorkflowHistoryInfo jhi_completedTaskImpl,Set<String> activityNames,Collection<String> exceptedActivityNames,Map<String,String> modelData,HibernateTemplate hibernateTemplate,JdbcTemplate jdbcTemplate) throws Exception
	{
		String deployId = String.valueOf(jhi_completedTaskImpl.getDeploymentImpl().getDbid());
		ActivityDetail currentActivityDetail = jhi_completedTaskImpl.getActivityDetail();
		//String currentActivityName = currentActivityDetail.getActivityName();
		String currentActivityKey    = currentActivityDetail.getActivityKey();
		//HistoryTaskInstanceImpl historyTaskInstanceImpl = jhi_completedTaskImpl.getHistoryTaskInstanceImpl();
		ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().deploymentId(deployId).uniqueResult();
		ProcessDefinitionImpl definitionimpl = (ProcessDefinitionImpl)definition;
		//    01 ->r1 》》02
		//       ->r2 》》03|04  类型加名称
		List<? extends Activity> ActivitiesList = definitionimpl.getActivities();
		
		List<RouteInfo> routeInfos = new ArrayList<RouteInfo>();
		String assignmentUserId = modelData.get("assignmentUserId");
		String assignmentedUserId = modelData.get("assignmentedUserId");
		if((null != assignmentUserId)&&(null == assignmentedUserId))
		{
			return routeInfos;
		}
		for (Activity activity : ActivitiesList) 
		{
			 if("task".equalsIgnoreCase(activity.getType()))
			 {
				 String activityName = activity.getName();
				 if(!currentActivityKey.equalsIgnoreCase(activityName))
				 {
					 continue;
				 }
				 List<? extends Transition> outgoingTransitions = activity.getOutgoingTransitions();
	             for(Transition transition:outgoingTransitions)
	             {
	            	 String currentDestActivityName = transition.getDestination().getName();
	            	 if((null != exceptedActivityNames) && exceptedActivityNames.contains(currentDestActivityName)){
						 continue;
					 }
	            	 RouteInfo routeInfo = new RouteInfo(); 
	            	 routeInfo.setParentActivityName(currentDestActivityName);
	            	 routeInfo.setCurrentTransition(transition);
	            	 List<Activity> targetTaskActivitys = new ArrayList<Activity>();
	            	 boolean isNeedChosePerson = true;
	            	 int activityNamesLen = activityNames.size();
	            	 //String  currentTaskDateTimeString = DateUtil.getDateTime(historyTaskInstanceImpl.getStartTime());
	            	isNeedChosePerson =  JBPMUtil.isSubmitPassed(currentActivityDetail, jhi_completedTaskImpl);
	            	//System.out.println("isNeedChosePerson:"+isNeedChosePerson+";TransitionName:"+transition.getName());
	     			if(isNeedChosePerson)
	     			{
		            	 if(activityNamesLen > 1)
		            	 {
		            		Activity dest= transition.getDestination();
		                	if("join".equalsIgnoreCase(dest.getType()))
			            	 {
		                		if(isNeedChosePerson)
		                		{
				            		 Activity joinActivity = dest;
				            		 int joinLen = joinActivity.getIncomingTransitions().size();
				            		 String hsql = "select count(ei.dbid) from ExecutionImpl ei left join ei.processInstance pi where ei.activityName = ? and ei.state=? and pi.dbid = ?";
				        		     int joinedSize = Integer.parseInt(String.valueOf(hibernateTemplate.find(hsql,dest.getName(),"inactive-join",jhi_completedTaskImpl.getHistoryProcessInstanceImpl().getDbid()).get(0)));
				        		     isNeedChosePerson = ( (joinLen-1) == joinedSize );
				            		 /* for(Transition ts:joinActivity.getIncomingTransitions())
				            		 {
				            			 String currentIncomeAcivityName = ts.getSource().getName();
				            			 if(!currentIncomeAcivityName.equals(currentActivityName))
				            			 {
				            				 if(activityNames.contains(currentIncomeAcivityName))
				            				 {
				            					 isNeedChosePerson = false;
				            					 break;
				            				 }
				            			 }
				            		 }*/
		                		}
		                		/*if(isNeedChosePerson)
		                		{
				            		 Activity joinActivity = definitionimpl.getActivity(transition.getDestination().getName());
				            		 for(Transition ts:joinActivity.getIncomingTransitions())
				            		 {
				            			 String currentIncomeAcivityName = ts.getSource().getName();
				            			 if(!currentIncomeAcivityName.equals(currentActivityName))
				            			 {
				            				 if(activityNames.contains(currentIncomeAcivityName))
				            				 {
				            					 isNeedChosePerson = false;
				            					 break;
				            				 }
				            			 }
				            		 }
		                		}
		                		if(isNeedChosePerson)
		                		{
			                		//String taskExecutionId = taskService.createTaskQuery().processInstanceId(jhi_completedTaskImpl.getHistoryProcessInstanceImpl().getProcessInstanceId()).activityName(currentActivityName).uniqueResult().getExecutionId();
			                		//int taskParentIndex    = ((ExecutionImpl)getExecutionService().findExecutionById(taskExecutionId)).getParent().get;
			                		for(String taskName :activityNames)
			                		{
			                			if(!currentActivityName.equals(taskName))
			                			{
			                				Task task = taskService.createTaskQuery().processInstanceId(jhi_completedTaskImpl.getHistoryProcessInstanceImpl().getProcessInstanceId()).activityName(taskName).uniqueResult();
			                				String taskDateTimeString = DateUtil.getDateTime(task.getCreateTime());
			                				if(currentTaskDateTimeString.equals(taskDateTimeString) || (currentTaskDateTimeString.compareTo(taskDateTimeString)<0))
			                				{
				            					 isNeedChosePerson = true;
				            					 break;
			                				}
			                			}
			                		}
		                		}*/
			            	 }
		            	 }
	     			 }
	            	 if(isNeedChosePerson)
	            	 {
		            	 recursionActivityRouteInfo(transition,targetTaskActivitys);
			             routeInfo.setTargetTaskActivitys(targetTaskActivitys);
			             routeInfos.add(routeInfo);
	            	 }
	             }
			 }
		}
		return routeInfos;
	}
	public static List<RouteInfo>   getWorkFlowAllRouteInfos(String deployId,String currentActivityKey) throws Exception
	{
		ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().deploymentId(deployId).uniqueResult();
		ProcessDefinitionImpl definitionimpl = (ProcessDefinitionImpl)definition;
		//    01 ->r1 》》02
		//       ->r2 》》03|04  类型加名称
		List<? extends Activity> ActivitiesList = definitionimpl.getActivities();
		
		List<RouteInfo> routeInfos = new ArrayList<RouteInfo>();
		for (Activity activity : ActivitiesList) 
		{
			// if("task".equalsIgnoreCase(activity.getType()))
			 {
				 if(currentActivityKey.equalsIgnoreCase(activity.getName())||"start".equalsIgnoreCase(activity.getType()))
				 {
					 continue;
				 }
				 RouteInfo routeInfo = new RouteInfo(); 
            	 List<Activity> targetTaskActivitys = new ArrayList<Activity>();
            	 routeInfo.setParentActivityName(activity.getName());
	             routeInfo.setTargetTaskActivitys(targetTaskActivitys);
	             routeInfos.add(routeInfo);
	             
	             if("fork".equalsIgnoreCase(activity.getType())){
					 List<? extends Transition> outgoingTransitions = activity.getOutgoingTransitions();
		             for(Transition transition:outgoingTransitions)
		             {
		            	 routeInfo.setCurrentTransition(transition);
		            	 recursionActivityRouteInfo(transition,targetTaskActivitys);
		             }
	             }else{
	            	 targetTaskActivitys.add(activity);
	             }
			 }
		}
		return routeInfos;
	}
	 /**
	 * splitstr
	 * @return the splitstr
	 * @since 1.0.0
	 **/
	
	public static String getSplitStr() {
		return SPLITSTR;
	}
	//##########动态路由开始
	/**   
	 * 动态创建连接当前任务节点至名称为destName的节点的Transition   
	 * @param taskId 任务节点ID   
	 * @param sourceName 源节点名称   
	 * @param destName  目标节点名称   
	 */    
	private  static  TransitionImpl addOutTransition(ProcessDefinitionImpl pd ,String sourceName,String midtransition,String destName)
	{     
		 EnvironmentFactory environmentFactory = (EnvironmentFactory)processEngine;     
		 EnvironmentImpl env = null;    
		 TransitionImpl transition = null;  
		 try {     
		     env = environmentFactory.openEnvironment();     
		     //取得当前流程的活动定义      
		     ActivityImpl sourceActivity = pd.findActivity(sourceName);  
		     //取得目标的活动定义      
		     ActivityImpl destActivity=pd.findActivity(destName);     
		     //为两个节点创建连接      
		     transition = sourceActivity.createOutgoingTransition();     
		     transition.setName(midtransition.trim());     
		     transition.setDestination(destActivity);   
		     sourceActivity.addOutgoingTransition(transition);    
		       
		 }catch(Exception ex){     
		     ex.printStackTrace();  
		    
		 }finally{     
		     if(env!=null)env.close();  
		    
		 }  
		  return transition;  
	}     
	 /**   
	 * 动态删除连接sourceName与destName的Transition   
	 * @param taskId   
	 * @param sourceName   
	 * @param destName   
	 */    
	@SuppressWarnings("unchecked")
    private static void removeOutTransition( ProcessDefinitionImpl pd ,String sourceName,String route)
	{     
		 EnvironmentFactory environmentFactory = (EnvironmentFactory) processEngine;     
		 EnvironmentImpl env=null;     
		 try 
		 {     
		     env = environmentFactory.openEnvironment();     
		     //取得当前流程的活动定义      
		     ActivityImpl sourceActivity = pd.findActivity(sourceName);     
		     //若存在这个连接，则需要把该连接删除      
		     List<Transition> trans=(List<Transition>)sourceActivity.getOutgoingTransitions();     
		     for(Transition tran:trans){     
		        if(route.equals(tran.getName())){//删除该连接      
		            trans.remove(tran);  
		            System.out.println("########删除临时路由:"+route);
		            break;     
		        }     
		     }     
		 }catch(Exception ex){     
		     ex.printStackTrace();    
		 }finally{     
		     if(env!=null)env.close();     
		 }     
	} 
	@SuppressWarnings("unchecked")
	public static void dynamicTempOutTransition(String deployId,String currentTaskId,String sourceName,String destName) 
	{
		ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().deploymentId(deployId).uniqueResult();
		ProcessDefinitionImpl pd = (ProcessDefinitionImpl)definition;
		//取得当前流程的活动定义      
	     ActivityImpl sourceActivity = pd.findActivity(sourceName);     
	     //若存在这个连接，则需要把该连接删除      
	     List<Transition> trans=(List<Transition>)sourceActivity.getOutgoingTransitions();
	     String dynamicRouteName = "Dynamic ROUTE FROM "+sourceName +" TO "+destName;
	     boolean isExistRoute = false;
	     for(Transition tran:trans){     
		    if(destName.equals(tran.getDestination().getName()))
		    {//删除该连接      
		    	dynamicRouteName = tran.getName();  
		    	isExistRoute = true;
		    	System.out.println("########使用已存在路由:"+dynamicRouteName);
		    	break;
		    }     
		 }
	     if(!isExistRoute)
	     {
	    	 addOutTransition(pd, sourceName, dynamicRouteName, destName);
	    	 System.out.println("########创建临时路由:"+dynamicRouteName);
	     }
		 taskService.completeTask(currentTaskId,dynamicRouteName);
		 if(!isExistRoute)
		 {
			 removeOutTransition(pd, sourceName,dynamicRouteName);
		 }
		 
	}
	//##########动态路由结束
	
	public static <T> String getIdStrsFromList(List<T> objs){
		String ids = "";
		for(T obj : objs){
			try{
				String id = "'"+(String)obj.getClass().getMethod("getId").invoke(obj)+"'";
				ids += ","+id;
			}catch(Exception e){
				continue;
			}
		}
		if(ids.length() > 0 ){
			ids = ids.substring(1);
		}
		return ids;
	}
	
	/*
	 * 更改某个流程数据中某个表单的值
	 */
	public static void updateWorkFlowInfoFormFieldValue(BaseService baseService,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo,Map<String, String>replaceFormValues)throws Exception{
		String processedFormVariables = jbpmWorkflowHistoryInfo.getProcessedFormVariables();
		String processedFormValues  = jbpmWorkflowHistoryInfo.getProcessedFormValues();
		Map<String, String>processFormMap =  getVaribalesMapByAllString(processedFormVariables,processedFormValues);
		for(String key : replaceFormValues.keySet()){
			if(processFormMap.containsKey(key)){
				System.out.println(processFormMap.get(key)); 
				processFormMap.put(key, replaceFormValues.get(key));
			}
		}
		StringBuffer sb_sourceKeyStr = new StringBuffer();
		StringBuffer sb_sourceValueStr = new StringBuffer();
		getVaribalesAllStringByMap(processFormMap, sb_sourceKeyStr, sb_sourceValueStr);
		jbpmWorkflowHistoryInfo.setProcessedFormVariables(sb_sourceKeyStr.toString());
		jbpmWorkflowHistoryInfo.setProcessedFormValues(sb_sourceValueStr.toString());
		baseService.updateEntity(jbpmWorkflowHistoryInfo);
	}
	
}
 