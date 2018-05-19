<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--工作指派-->
<div id="id_work_flow_submit_params_container">
	<input type="hidden" id="id_assignmentUserId_hidden" name="assignmentUserId" readOnly value="${empty currentJBPMWorkflowHistoryInfoUser.assignActor ? '' : currentJBPMWorkflowHistoryInfoUser.assignActor.id}"/>
	<input type="hidden" id="id_assignmentUserRealName_display" name="assignmentUserRealName" readOnly  value="${empty currentJBPMWorkflowHistoryInfoUser.assignActor ? '' : currentJBPMWorkflowHistoryInfoUser.assignActor.realname}"/>
	<input type="hidden" id="id_assignmentedUserRealName_display" name="assignmentedUserRealName" readOnly  value="${empty currentJBPMWorkflowHistoryInfoUser.assignedActor ? '' : currentJBPMWorkflowHistoryInfoUser.assignedActor.realname}"/>
	<input type="hidden" id="id_assignmentIsSilentModel" name="assignmentIsSilentModel" readOnly  value="false"/>
	<textarea style="display:none"  id="id_currentHistoryTaskInfo_keyOne">${currentHistoryTaskInfo.keyOne}</textarea>
	<textarea style="display:none"  id="id_currentHistoryTaskInfo_keyTwo">${currentHistoryTaskInfo.keyTwo}</textarea>
	<textarea style="display:none"  id="id_currentHistoryTaskInfo_keyThree">${currentHistoryTaskInfo.keyThree}</textarea>
	<textarea style="display:none"  id="id_currentHistoryTaskInfo_keyFour">${currentHistoryTaskInfo.keyFour}</textarea>
	<textarea style="display:none"  id="id_currentHistoryTaskInfo_keyFive">${currentHistoryTaskInfo.keyFive}</textarea>
	<textarea style="display:none"  id="id_currentHistoryTaskInfo_keySix">${currentHistoryTaskInfo.keySix}</textarea>
	<textarea style="display:none"  id="id_currentHistoryTaskInfo_keySeven">${currentHistoryTaskInfo.keySeven}</textarea>
	<textarea style="display:none"  id="id_currentHistoryTaskInfo_keyEight">${currentHistoryTaskInfo.keyEight}</textarea>
	<textarea style="display:none"  id="id_currentHistoryTaskInfo_keyNine">${currentHistoryTaskInfo.keyNine}</textarea>
	<textarea style="display:none"  id="id_currentHistoryTaskInfo_keyTen">${currentHistoryTaskInfo.keyTen}</textarea>
	<!--传阅-->
	<input type="hidden" id="id_readUserIds_hidden" name="readUserIds" readOnly  value="${readUserIds}"/>    
	<input type="hidden" id="id_readUserRealNames_display" readOnly  value="${readUserIds}"/>    
	<!--会签-->
	<input type="hidden" id="id_signatureUserIds_hidden" name="signatureUserIds" readOnly value="${signatureUserIds}"/>
	<input type="hidden" id="id_signatureUserRealNames_display"  readOnly value="${signatureUserIds}"/>
	
	<!--工作指派-->
	<input type="hidden" id="id_assignmentedUserId_hidden" name="assignmentedUserId" readOnly value="${assignmentedUserId}"/>
	<input type="hidden" id="id_assignmentUserRealName_display"  readOnly value="${assignmentedUserId}"/>
	
	<!--任务类型 -->
	<input type="hidden" value="${currentRequestTaskType}" name="currentRequestTaskType"/>
	<!-- 必要隐藏参数 -->
	<input type="hidden" id="id_currentTaskId" name="currentTaskId" value="${currentTaskId}" />
	<input type="hidden" name="jbpmWorkflowHistoryInfoUserId" value="${jbpmWorkflowHistoryInfoUserId}" />
	<input type="hidden" name="conditionRouteType" id="conditionRouteType" value="${conditionRouteType}" />
	<!--任意路由参数--> 
	<input type="hidden" id="id_choseRequestNextRouteNodeName" name="choseRequestNextRouteNodeName" value="${currentTaskId}" />
	<!-- 流程实例动态变量 -->
	<input type="hidden" id="id_requestInitiatorRoute" name="requestInitiatorRoute" value=""/>
	<input type="hidden" id="id_workflowbackmodel" name="workflowbackmodel" value=""/>
	<input type="hidden" id="id_readChangeRequestInitiator" name="readChangeRequestInitiator" value=""/>
	<input type="hidden" id="id_signatureChangeRequestInitiator" name="signatureChangeRequestInitiator" value=""/>
	<input type="hidden" id="id_changeRequestInitiator" name="changeRequestInitiator" value=""/>
	<input type="hidden" id="id_hidden_text_choseAdvise" name="processedAdvise"  value=""/>
	<input type="hidden" id="id_hidden_choseResult" name="processedResult"  value=""/>
	<input type="hidden" id="id_show_choseResult" name="processedResultShow"  value=""/>
	<input type="hidden" id="id_currentTaskSubmitButtonText" name="currentTaskSubmitButtonText"  value=""/>
	<input type="hidden" id="id_workflow_ishis" name="is_workflow_ishis" value="${workflow_ishis}" />
</div>