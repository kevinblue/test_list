<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c'   uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix="fn"  uri="/WEB-INF/tlds/fn.tld" %> 
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<%@ taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>
<%@ taglib uri="/minidict" prefix="mini"%>

<div id="id_workflowHistoryDetailInfoWindow" class="mini-window" title="流程历史处理信息" style="display:none;width:1004px;height:550px;padding:0px;">
	<c:forEach items="${processedHistoryLogInfoList}" var="jbpmWorkflowHistoryInfo" varStatus="status">
		<c:set var="historyTask" value="${jbpmWorkflowHistoryInfo.historyTaskInstanceImpl}"></c:set>
		<c:set var="historyTaskid" value="${jbpmWorkflowHistoryInfo.historyTaskInstanceImpl.historyTask.id}"></c:set>
		<c:set var="activityDetail" value="${jbpmWorkflowHistoryInfo.activityDetail}"></c:set>
		<c:set var="fromButtonTextToCome" value="${jbpmWorkflowHistoryInfo.fromButtonTextToCome}"></c:set>
		<c:set var="taskName" value="${activityDetail.activityName}"></c:set>
		<c:set var="taskStartTime" value="${historyTask.startTime}"></c:set>
		<c:set var="taskEndTime" value="${historyTask.endTime}"></c:set>
		<c:set var="durationMinutes" value="${historyTask.duration/1000/60}"></c:set>
		<div class="taskname">
			<table>
				<tr>
					<td><strong>【<font color="red"><spring:message code="${fromButtonTextToCome}"  text="${fromButtonTextToCome}"/></font>】&nbsp;&nbsp;<a href="javascript:void(0);" onclick="toProcessForm('<mini:param  name="historyTaskid"/>')"><mini:param  name="taskName"/></a></strong></td>
					<td>【开始时间】<fmt:formatDate value="${taskStartTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
					<td>【结束时间】<fmt:formatDate value="${taskEndTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
					<td>【耗时】<fmt:formatNumber value="${durationMinutes}" pattern="#.##" type="number"></fmt:formatNumber>&nbsp;分钟</td>
				</tr>
			</table>
		</div>
		<div class="taskactor">
			<c:set var="planActorsStr" value=""></c:set>
			<c:set var="assignActorsStr" value=""></c:set>
			<c:set var="assignedActorsStr" value=""></c:set>
			<c:set var="assignProcessAdvisesStr" value=""></c:set>
			<c:set var="assignedProcessAdvisesStr" value=""></c:set>
			<c:set var="assignedEndTimesStr" value=""></c:set>
			<c:set var="finalProcessAdvisesStr" value=""></c:set>
			
			<div class="mini-panel" title="处理信息" showCollapseButton="true" style="width: 100%;">
				<table class="workflowTable"><tr><th style="width:20px;">&nbsp;</th><th>【处理状态】</th><th>【计划处理人】</th><th>【实际处理人】</th><th>【处理时间】</th><th>【处理意见】</th></tr>
				<c:forEach items="${jbpmWorkflowHistoryInfo.jbpmWorkflowHistoryInfoUsers}" var="jbpmWorkflowHistoryInfoUser" varStatus="userStatus">
					<c:set var="taskPlanActor" value="${jbpmWorkflowHistoryInfoUser.planActor}"></c:set>
					<c:set var="taskActualActor" value="${jbpmWorkflowHistoryInfoUser.actualActor}"></c:set>
					<c:set var="taskAssignActor" value="${jbpmWorkflowHistoryInfoUser.assignActor}"></c:set>
					<c:set var="taskAssignedActor" value="${jbpmWorkflowHistoryInfoUser.assignedActor}"></c:set>
					<c:set var="taskPlanActorRealName" value="${taskPlanActor.realname}"></c:set>
					<c:set var="taskActualActorRealName" value="${taskActualActor.realname}"></c:set>
					<c:set var="processedAdvise" value="${jbpmWorkflowHistoryInfoUser.processedAdvise}"></c:set>
					
					<c:if test="${!empty taskAssignActor}">
						<c:set var="planActorsStr" value="${planActorsStr},${taskPlanActor.realname}"></c:set>
						<c:set var="assignActorsStr" value="${assignActorsStr},${taskAssignActor.realname}"></c:set>
						<c:set var="assignedActorsStr" value="${assignedActorsStr},${taskAssignedActor.realname}"></c:set>
						<c:set var="assignProcessAdvisesStr" value="${assignProcessAdvisesStr},${jbpmWorkflowHistoryInfoUser.assignAdvise}"></c:set>
						<c:set var="assignedProcessAdvisesStr" value="${assignedProcessAdvisesStr},${jbpmWorkflowHistoryInfoUser.assignedAdvise}"></c:set>
						<c:set var="assignedEndTimesStr" value="${assignedEndTimesStr},${jbpmWorkflowHistoryInfoUser.endTime }"></c:set>
						<c:set var="finalProcessAdvisesStr" value="${finalProcessAdvisesStr},${processedAdvise}"></c:set>
					</c:if>
					<tr>
						<c:set var="currentColor" value="${empty jbpmWorkflowHistoryInfoUser.endTime ? 'red' : '' }"></c:set>
						<c:set var="vuserstatusindex" value="${userStatus.index+1}"></c:set>
						<c:set var="t_endtime" value="${jbpmWorkflowHistoryInfoUser.endTime}"></c:set>
						<td><mini:param  name="vuserstatusindex" /></td>
						<c:set var="t_vprocess" value="${empty jbpmWorkflowHistoryInfoUser.endTime ? '未处理' : '已处理' }"></c:set>		
						<td><font color="${currentColor}"><mini:param  name="t_vprocess"/></font></td>
						<td><font color="${currentColor}"><mini:param  name="taskPlanActorRealName"/></font></td>
						<td><font color="${currentColor}"><mini:param  name="taskActualActorRealName"/></font></td>
						<td><font color="${currentColor}"><mini:param  name="t_endtime"/></font></td>
						<td><font color="${currentColor}"><mini:param  name="processedAdvise" /></font></td>
					</tr>
				</c:forEach>
				</table>
			</div>
			
			<c:set var="tempVar" value=""></c:set>
			<c:set var="planActorsArray" value="${fn:split(planActorsStr,',')}"></c:set>
			<c:set var="assignActorsArray" value="${fn:split(assignActorsStr,',')}"></c:set>
			<c:set var="assignedActorsArray" value="${fn:split(assignedActorsStr,',')}"></c:set>
			<c:set var="assignProcessAdvisesArray" value="${fn:split(assignProcessAdvisesStr,',')}"></c:set>
			<c:set var="assignedProcessAdvisesArray" value="${fn:split(assignedProcessAdvisesStr,',')}"></c:set>
			<c:set var="assignedEndTimesArray" value="${fn:split(assignedEndTimesStr,',')}"></c:set>
			<c:set var="finalProcessAdvisesArray" value="${fn:split(finalProcessAdvisesStr,',')}"></c:set>
			
			
			<c:if test="${fn:length(assignActorsStr)>1}">
				<c:forEach items="${assignActorsArray}" var="assignActorStr" varStatus="userStatus">
					<c:if test="${0 == userStatus.index}">
					<div class="mini-panel historyFieldSet" title="工作指派" showCollapseButton="true" style="width: 100%;">
					<c:set var="tempVar" value="true"></c:set>
						<table class="workflowTable"><tr><th>【处理状态】</th><th>【指派人】</th><th>【被指派人】</th><th>【指派人意见】</th><th>【被指派人意见】</th><th>【处理时间】</th><th>【处理意见】</th></tr>
					</c:if>
					<c:if test="${0 <= userStatus.index}">
						<tr>
							<c:set var="currentColor"  value="${empty assignedActorsArray[userStatus.index] ? 'red' : '' }"></c:set>
							
						    <c:set var="T_Designee" value="${empty assignedActorsArray[userStatus.index] ? planActorsArray[userStatus.index] : assignedActorsArray[userStatus.index]}"></c:set>
			                <c:set var="T_DesignerAdvise" value="${assignProcessAdvisesArray[userStatus.index]}"></c:set>
			                <c:set var="T_DesigneeAdvise" value="${assignedProcessAdvisesArray[userStatus.index]}"></c:set>
			                <c:set var="T_HandleTime" value="${assignedEndTimesArray[userStatus.index]}"></c:set>
			                <c:set var="T_HandleAdvise" value="${finalProcessAdvisesArray[userStatus.index]}"></c:set>
			                
			                
			                <c:if test="${!empty assignedActorsArray[userStatus.index]}">
						      <c:set var="processstatus" value="已处理"></c:set>
						    </c:if>
						    <c:if test="${empty assignedActorsArray[userStatus.index]}">
						       <c:set var="processstatus" value="未处理"></c:set>
						    </c:if>
							<td><font color="${currentColor}"><mini:param  name="processstatus" /></font></td>
							<td><font color="${currentColor}">><mini:param  name="assignActorStr" /></font></td>
							<td><font color="${currentColor}"><mini:param  name="T_Designee" /></font></td>
							<td><font color="${currentColor}"><mini:param  name="T_DesignerAdvise" /></font></td>
							<td><font color="${currentColor}"><mini:param  name="T_DesigneeAdvise" /></font></td>
							<td><font color="${currentColor}"><mini:param  name="T_HandleTime" /></font></td>
							<td><font color="${currentColor}"><mini:param  name="T_HandleAdvise" /></font></td>
						</tr>
					</c:if>
				</c:forEach>
				<c:if test="${!empty tempVar}">
						</table>
					</div>
				</c:if>
			</c:if>
			
			
			<c:set var="tempVar" value=""></c:set>
			<c:forEach items="${jbpmWorkflowHistoryInfo.attachmentFileUploadInfoDetails}" var="attachmentFileUploadInfoDetail" varStatus="userStatus">
				<c:if test="${0==userStatus.index}">
				<div class="mini-panel historyFieldSet" title="附件信息" showCollapseButton="true" style="width: 100%;">
				<c:set var="tempVar" value="true"></c:set>
				<table class="workflowTable"><tr><th>【附件名称】</th><th>【上传人】</th><th>【上传时间】</th></tr>
				</c:if>
				<tr>
				 <c:set var="T_filename" value="${attachmentFileUploadInfoDetail.chineseFileName}"></c:set>
			     <c:set var="T_realname" value="${attachmentFileUploadInfoDetail.uploadUser.realname}"></c:set>
			     <c:set var="T_uploadtime" value="${attachmentFileUploadInfoDetail.uploadTime}"></c:set>		                
				<td><font color='red'><mini:param  name="T_filename" /></font></td>
				<td><font color='red'><mini:param  name="T_realname" /></font></td>
				<td><font color='red'><mini:param  name="T_uploadtime" /></font></td>
				</tr>
			</c:forEach>
			<c:if test="${!empty tempVar}">
				</table>
				</div>
			</c:if>
			
			
			<c:set var="tempVar" value=""></c:set>
			<c:forEach items="${jbpmWorkflowHistoryInfo.jbpmWorkflowReadUsers}" var="jbpmWorkflowReadUser" varStatus="userStatus">
				<c:if test="${0==userStatus.index}">
				<div class="mini-panel historyFieldSet" title="传阅信息" showCollapseButton="true" style="width: 100%;">
				<c:set var="tempVar" value="true"></c:set>
				<table class="workflowTable"><tr><th>【传阅状态】</th><th>【传阅人】</th><th>【传阅意见】</th></tr>
				</c:if>
				<tr>
				<c:set var="T_vrealname" value="${jbpmWorkflowReadUser.readedActor.realname}"></c:set>
			    <c:set var="T_vreadedAdvise" value="${jbpmWorkflowReadUser.readedAdvise}"></c:set>			    	                
				<c:set var="currentColor" value="${empty jbpmWorkflowReadUser.endTime ? 'red' : '' }"></c:set>
				<c:set var="t_processstatus" value="${empty jbpmWorkflowReadUser.endTime ? '未查看' : '已查看' }"></c:set>
				<td><font color="${currentColor}"><mini:param  name="t_processstatus" /></font></td>
				<td><font color="${currentColor}"><mini:param  name="T_vrealname" /></font></td>
				<td><font color="${currentColor}"><mini:param  name="T_vreadedAdvise" /></font></td>
				</tr>
			</c:forEach>
			<c:if test="${!empty tempVar}">
				</table>
				</div>
			</c:if>
			
			
			<c:set var="tempVar" value=""></c:set>
			<c:forEach items="${jbpmWorkflowHistoryInfo.jbpmWorkflowSignatureUsers}" var="jbpmWorkflowSignatureUser" varStatus="userStatus">
				<c:if test="${0==userStatus.index}">
				<div class="mini-panel historyFieldSet" title="会签信息" showCollapseButton="true" style="width: 100%;">
				<c:set var="tempVar" value="true"></c:set>
				<table class="workflowTable"><tr><th>【会签状态】</th><th>【会签人】</th><th>【会签意见】</th></tr>
				</c:if>
				<tr>
				<c:set var="currentColor" value="${empty jbpmWorkflowSignatureUser.endTime ? 'red' : '' }"></c:set>
				<c:set var="T_processstatus" value="${empty jbpmWorkflowSignatureUser.endTime ? '未完成' : '已完成' }"></c:set>
					<c:set var="T_svrealname" value="${jbpmWorkflowSignatureUser.signaturedActor.realname}"></c:set>
			    <c:set var="T_svreadedAdvise" value="${jbpmWorkflowSignatureUser.signaturedAdvise}"></c:set>			   
				<td><font color="${currentColor}"><mini:param  name="T_processstatus" /></font></td>	
				<td><font color="${currentColor}"><mini:param  name="T_svrealname" /></font></td>
				<td><font color="${currentColor}"><mini:param  name="T_svreadedAdvise" /></font></td>
				</tr>
			</c:forEach>
			<c:if test="${!empty tempVar}">
				</table>
				</div>
			</c:if>
		</div>
	</c:forEach>
</div>
<script type="text/javascript">
	mini.parse(document.getElementById('id_workflowHistoryDetailInfoWindow'));
</script>