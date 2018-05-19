
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.service
 * 文件名：         JbpmService.java
 * 版本信息：    1.0.0
 * 创建日期：     2012-10-23-下午04:43:26
 * Copyright：2012XX公司-版权所有
 **/

package com.tenwa.jbpm.service;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.pvm.internal.history.model.HistoryProcessInstanceImpl;
import org.jbpm.pvm.internal.repository.DeploymentImpl;
import org.jbpm.pvm.internal.task.TaskImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.entity.ActivityDetail;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.jbpm.model.ActivityTaskUsers;
import com.tenwa.jbpm.model.SubmitTypeEnum;
import com.tenwa.jbpm.model.WorkflowConfigData;
import com.tenwa.jbpm.model.WorkflowSubmitTextEnum;


 /**
 * 类名称：     JbpmService
 * 类描述：     
 * 创建人：     tracywindy
 * 修改人：     tracywindy
 * 修改时间：2012-10-23 下午04:43:26
 * 修改备注：
 * @version 1.0.0
 **/
public interface JbpmService extends BaseService
{
   //消息提醒
  public void   sendMessageNoticeToUser(JBPMWorkflowHistoryInfo jwi,ActivityDetail pendingActivityDetail,User u,Map<String,String> modelData,SubmitTypeEnum ste) throws Exception;
   //项目总表
   public List<JBPMWorkflowHistoryInfo> getProjSummary(String keyOne,String proj_id) throws Exception;
   public void removeWorkflowAllProcessInstance(HttpServletRequest request,String workflowName, int version) throws Exception;
   public void updateAbandonWorkflowProcessInstance(HttpServletRequest request,Long processInstanceDBID_) throws Exception;
   public String getWorkflowActions(String paramWorkflowName) throws Exception;
   public  JSONArray  getWorkflowRejectBeanName(boolean isFilter,String sourceWorkflowName,String rejectWorkflowName) throws Exception;
   public void saveKeyToWorkflow(String designerId, String keyOne,
			String keyTwo, String keyThree, String keyFour,String keyFive,
			String keySix, String keySeven, String keyEight, 
			String keyNine, String keyTen) throws Exception;
   public void removeSignaturePersonToTask(String jbpmWorkflowHistoryInfoId,String signaturedAdvise) throws Exception;
   public void removeReadPersonToTask(String jbpmWorkflowHistoryInfoId,String readedAdvise) throws Exception;
   public void addWorkAssignmentPersonToTask(JBPMWorkflowHistoryInfo jhi_completedTaskImpl,String jbpmWorkflowHistoryInfoUserId,String assignmentUserId,String processedAdvise,String processedResult) throws Exception;
   public void addReadPersonToTask(JBPMWorkflowHistoryInfo jhi_completedTaskImpl,ActivityDetail assignToTaskAd,Map<String,String> modelData,String jbpmWorkflowHistoryInfoUserId,User readActor,String userIds,String splitStr,String readAdvise,List<User> userList) throws Exception;
   public void addSignaturePersonToTask(JBPMWorkflowHistoryInfo jhi_completedTaskImpl,Map<String,String> modelData,String jbpmWorkflowHistoryInfoUserId,User signatureActor,String userIds,String splitStr,String signatureAdvise,List<User> userList) throws Exception;
   public void delegateSingleWorkflow(String id,Object deploymentIdOrDeploymentImpl,String delegateUserId,String startDate,String endDate,String grantMethod) throws Exception;
   public void delegateCheckedWorkflows(String checkedIds,String checkedDeploymentIds,String delegateUserId,String startDate,String endDate,String grantMethod) throws Exception ;
   public void delegateAllWorkflows(String delegateUserId,String startDate,String endDate,String grantMethod) throws Exception;
   public void removeSingleDelegatedWorkflow(String id)  throws Exception;
   public void removeCheckedDelegatedWorkflows(String checkedIds)  throws Exception;
   public void removeAllDelegatedWorkflows() throws Exception;
   public void removeReadPersonToTaskByFlowUnid(String jbpmWorkflowHistoryInfoId,String readedAdvise) throws Exception;
   
   public void updateWorkflowConfigSynchronized(String type,String host, String port, String dbname, String user,String password,String updateWorkflowIds,Locale locale) throws Exception;
   public void updateWorkflowSynchronized(String deployId,Locale locale) throws Exception;
   public void removeDesignedWorkflow(String designedId) throws Exception;
   public String transferedJpdlXmlByWorkflowDesigner(Map<String,String> modelData) throws Exception;
   public List<Map<String,String>> queryDeployedWorkflows()  throws Exception;
   public DeploymentImpl deployWorkflow(String designedId) throws Exception ;
   public void removeDeployedWorkflow(String designedId,long deployId) throws Exception ;
   public byte[] getDeployedWorkflowDiagramDatas(String deployId) throws Exception ;
   public Map<String,String> getActivedRects(String deployId,String processInstanceId) throws Exception;
   public List<ActivityCoordinates> getActivityCoordinates(String processInstanceId) throws Exception;
   public void saveChangeToActivity(String id,Map<String,String> model) throws Exception ;
   public String startDeployedProcess(HttpServletRequest request,String processDefinitionId,Map<String,String> modelData) throws Exception;
   public String workflowDataManage(HttpServletRequest request,String jbpmWorkflowHistoryInfoUserId,JBPMWorkflowHistoryInfo history_jhi_processInstance,JBPMWorkflowHistoryInfo jhi_completedTaskImpl_inner,DeploymentImpl deploymentImpl,ProcessDefinition processDefinition,ProcessInstance processInstance,HistoryProcessInstanceImpl historyProcessInstanceImpl,TaskImpl completedTaskImpl,List<ActivityDetail> activitiesDetailList,Map<String,String> modelData,boolean isStart,String flag,boolean isBack) throws Exception;
   public String updateRequestProcessTaskForm(HttpServletRequest request,String currentTaskId,Map<String,String> modelData) throws Exception ;
   public String submitProcessedTaskForm(HttpServletRequest request,String currentTaskId, Map<String,String> modelData) throws Exception ;
   public String removeProcessInstance(HttpServletRequest request,String processInstanceDBID) throws Exception;
   public void getRequestFormDealedInfo(HttpServletRequest request,JBPMWorkflowHistoryInfo jhi_completedTaskImpl,JBPMWorkflowHistoryInfo jhi_processInstance,Map<String,String> modelData,Set<String> activityNames) throws Exception;
   public List<Object>   getAuthDesignedWorkflowList(Map<String,String> modelData,boolean isViewWorkflows) throws Exception ;
   //自动提交流程步骤
  	public void submitAutoProcessedTaskForm(
			HttpServletRequest     request,
			String     		       currentTaskId,
			String                 jbpmWorkflowHistoryInfoUserId,
			String                 requestInitiatorRoute,
			JSONObject             changeRequestInitiator,
			String                 processedAdvise,
			String                 processedResult,
			String                 choseRequestNextRouteNodeName,
			WorkflowSubmitTextEnum submitType ,
			Map<String,String>     modelData
	 ) throws Exception;
	public Set<ActivityTaskUsers> getLastBackNextActivityTaskUsersList(HttpServletRequest request,
			String jhiCompletedTaskImplId)
			throws Exception;
	public Set<ActivityTaskUsers> getNextActivityTaskUsersList(HttpServletRequest request,
			String jhiCompletedTaskImplId,Collection<String> exceptedActivityNames,boolean isLastBack)
					throws Exception;
	public Set<ActivityTaskUsers> getBackActivityTaskUsersList(HttpServletRequest request,
			String jhiCompletedTaskImplId)
			throws Exception;
	public Set<ActivityTaskUsers> getAllActivityTaskUsersList(HttpServletRequest request,
			String jhiCompletedTaskImplId)
			throws Exception;
	
	public List<JBPMWorkflowHistoryInfo> getProcessedHistoryLogInfoList(String historyProcessInstanceImplDbid,String currentJbpmWorkflowHistoryInfoId) throws Exception;
	public String updatePendingUserToOther(int changePlanUserFlag,String taskType,String jbpmWorkflowHistoryInfoUserId,String  planUserId,String  changeToUserId) throws Exception;
	public void getDynamicRequestInitiatorRoute(HttpServletRequest request,String conditionRouteType, String conditionRouteValue,Map<String, String> modelData) throws Exception;
	public void addWorkFlowAppPush(String taskId) throws Exception;
	public void transferOneProcessAttachmentToAnother(JBPMWorkflowHistoryInfo jBPMWorkflowHistoryInfo,String designerId,Map<String, String> paramsMap) throws Exception;
	
	public List<WorkflowConfigData> exportWorkflowConfigData() throws Exception;
	public WorkflowConfigData exportWorkflowConfigDataUnique(String id) throws Exception;
	public void updateWorkflowConfig(String workflowName, int version, Map<String, String> transferMap,
			List<Map<String, String>> activitiesDetailDatas, Locale locale) throws Exception;
}
