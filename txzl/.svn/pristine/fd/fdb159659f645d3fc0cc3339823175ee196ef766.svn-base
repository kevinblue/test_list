<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix="fn"  uri="/WEB-INF/tlds/fn.tld" %> 
<%@ taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>
<%@ taglib prefix="fmt"  uri="/WEB-INF/tlds/fmt.tld" %>

<div id="__processAdviseDetail">
	<table class="workflowTable_bl" style="width:100%">
		<tr><td style="width: 10%;">【<spring:message code="HandleStatus" text="处理状态"/>】</td>
		<!--<td >【<spring:message code="PlanPerson" text="计划处理人"/>】</td>--> 
		<td style="width: 15%;">【<spring:message code="ActualPerson" text="处理人"/>】</td> 
		<td style="width: 15%;">【<spring:message code="HandleTime" text="处理时间"/>】</td>
		<td style="width: 15%;">【<spring:message code="approveResult" text="审批结论"/>】</td>
		<td style="width: 45%;">【<spring:message code="HandleAdvise" text="处理意见"/>】</td></tr>
	<c:forEach items="${processedHistoryLogInfoList}" var="jbpmWorkflowHistoryInfo" varStatus="status">
		<c:set var="historyTask" value="${jbpmWorkflowHistoryInfo.historyTaskInstanceImpl}"></c:set>
		<c:set var="activityDetail" value="${jbpmWorkflowHistoryInfo.activityDetail}"></c:set>
		<c:set var="fromButtonTextToCome" value="${jbpmWorkflowHistoryInfo.fromButtonTextToCome}"></c:set>
		<c:set var="taskName" value="${activityDetail.activityName}"></c:set>
		<c:set var="taskStartTime" value="${historyTask.startTime}"></c:set>
		<c:set var="taskEndTime" value="${historyTask.endTime}"></c:set>
		<c:set var="durationMinutes" value="${historyTask.duration/1000/60}"></c:set>
		<div class="taskname">
			<table><tr>
				<td><strong>【<font color="red"><spring:message code="${fromButtonTextToCome}"  text="${fromButtonTextToCome}"/></font>】&nbsp;&nbsp;<a href="javascript:void(0);" onclick="toProcessForm('${historyTask.historyTask.id}')">${taskName}</a></strong></td>
				<td>【<spring:message code="StartDate" text="开始时间"/>】<fmt:formatDate value="${taskStartTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
				<td>【<spring:message code="EndDate" text="结束时间"/>】<fmt:formatDate value="${taskEndTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
				<td>【<spring:message code="WasteTime" text="耗时"/>】<fmt:formatNumber value="${durationMinutes}" pattern="#.##" type="number"></fmt:formatNumber>&nbsp;<spring:message code="Minute" text="分钟"/></td>
			</tr></table>
		</div>
		
		
		<div class="taskactor">
		<c:set var="planActorsStr" value=""></c:set>
		<c:set var="assignActorsStr" value=""></c:set>
		<c:set var="assignedActorsStr" value=""></c:set>
		<c:set var="assignProcessAdvisesStr" value=""></c:set>
		<c:set var="assignedProcessAdvisesStr" value=""></c:set>
		<c:set var="assignedEndTimesStr" value=""></c:set>
		<c:set var="finalProcessAdvisesStr" value=""></c:set>
		<c:set var="exclusionMessageFlag" value="${jbpmWorkflowHistoryInfo.exclusionMessageFlag}"></c:set>
		<!-- <div class="mini-panel" title="<spring:message code="HandleInfo" text="处理信息"/>：" showCollapseButton="true" style="width: 100%;"> -->
		<table class="workflowTable" style="width:100%">
		<tr><td style="width: 10%;"></td>
		<!--<td >【<spring:message code="PlanPerson" text="计划处理人"/>】</td>--> 
		<td style="width: 15%;"></td> 
		<td style="width: 15%;"></td>
		<td style="width: 15%;"></td>
		<td style="width: 45%;"></td></tr>
		<c:forEach items="${jbpmWorkflowHistoryInfo.jbpmWorkflowHistoryInfoUsers}" var="jbpmWorkflowHistoryInfoUser" varStatus="userStatus">
			<c:set var="taskPlanActor" value="${jbpmWorkflowHistoryInfoUser.planActor}"></c:set>
			<c:set var="taskActualActor" value="${jbpmWorkflowHistoryInfoUser.actualActor}"></c:set>
			<c:set var="taskAssignActor" value="${jbpmWorkflowHistoryInfoUser.assignActor}"></c:set>
			<c:set var="taskAssignedActor" value="${jbpmWorkflowHistoryInfoUser.assignedActor}"></c:set>
			<c:if test="${sessionScope['login_userlanguage'].language  eq 'en'}">
				<c:set var="taskPlanActorRealName" value="${taskPlanActor.username}"></c:set>
				<c:set var="taskActualActorRealName" value="${taskActualActor.username}"></c:set>
			</c:if>
			<c:if test="${sessionScope['login_userlanguage'].language  eq 'zh'}">
				<c:set var="taskPlanActorRealName" value="${taskPlanActor.realname}"></c:set>
				<c:set var="taskActualActorRealName" value="${taskActualActor.realname}"></c:set>
			</c:if>
			
			<c:set var="processedAdvise" value="${jbpmWorkflowHistoryInfoUser.processedAdvise}"></c:set>
			<c:set var="processedResult" value="${jbpmWorkflowHistoryInfoUser.processedResult.name}"></c:set>
			
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
			<c:set var="currentColor" value="${empty jbpmWorkflowHistoryInfoUser.endTime ? (jbpmWorkflowHistoryInfoUser.isReaded ?  'green' : 'red') : '' }"></c:set>
			<c:if test="${sessionScope['login_userlanguage'].language  eq 'en'}">
				<th><font color="${currentColor}">${empty jbpmWorkflowHistoryInfoUser.endTime ? 'Unsettled' : 'Settled' }</font>
				<c:if test="${jbpmWorkflowHistoryInfoUser.submitOnApp}">
				  <input type="image" alt="手机" title="手机APP审批" src="${pageContext.request.contextPath}/images/phone.png" width="20">
				</c:if>
				</th>
			</c:if>
			<c:if test="${sessionScope['login_userlanguage'].language  eq 'zh'}">
				<th><font color="${currentColor}">${empty jbpmWorkflowHistoryInfoUser.endTime ? (jbpmWorkflowHistoryInfoUser.isReaded ?  '已查看' : '未处理') : '已处理' }</font>
				<c:if test="${jbpmWorkflowHistoryInfoUser.submitOnApp}">
				  <input type="image" alt="手机" title="手机APP审批" src="${pageContext.request.contextPath}/images/phone.png" width="20">
				</c:if>
				</th>
			</c:if>
			<!-- <th><font color="${currentColor}">${taskPlanActorRealName}</font></th> -->
			<th>
				<c:choose>
					<c:when test="${taskPlanActorRealName eq taskActualActorRealName||taskActualActorRealName==null}">
						<font color="${currentColor}">${taskPlanActorRealName}</font>
					</c:when>
					<c:otherwise>
						<font color="${currentColor}">${taskPlanActorRealName}</font>【<font color="${currentColor}">${taskActualActorRealName}</font>代办】
					</c:otherwise>
				</c:choose>
			</th>
			<th><font color="${currentColor}">${jbpmWorkflowHistoryInfoUser.endTime}</font></th>
			<th><font color="${currentColor}">${processedResult}</font></th> 	
			<th><font color="${currentColor}">${processedAdvise}</font></th> 
			</tr>
			<!-- 
			<c:if test="${'false' eq exclusionMessageFlag}">
				<tr>
					<th>【<spring:message code="HandleAdvise" text="处理意见"/>】</th>
					<th colspan="3">
						<font color="${currentColor}">${processedAdvise}</font>
					</th>
				</tr>
			</c:if> -->
		</c:forEach>
		</table>
		<!--</div> -->
		
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
				<!--<div class="mini-panel" title="<spring:message code="Assignment" text="工作指派"/>：" showCollapseButton="true" style="width: 100%;">-->
				<c:set var="tempVar" value="true"></c:set>	
				<table class="workflowTable" style="width:100%"><tr><th>【<spring:message code="HandleStatus" text="处理状态"/>】</th><th>【<spring:message code="Designer" text="指派人"/>】</th><th>【<spring:message code="Designee" text="被指派人"/>】</th><th>【<spring:message code="DesignerAdvise" text="指派人意见"/>】</th><th>【<spring:message code="DesigneeAdvise" text="被指派人意见"/>】</th><th>【<spring:message code="HandleTime" text="处理时间"/>】</th><th>【<spring:message code="HandleAdvise" text="处理意见"/>】</th></tr>
			</c:if>
			<c:if test="${0 <= userStatus.index}">
				<tr>
				<c:set var="currentColor"  value="${empty assignedActorsArray[userStatus.index] ? 'red' : '' }"></c:set>
				<c:if test="${sessionScope['login_userlanguage'].language  eq 'en'}">
					<th><font color="${currentColor}">${empty assignedActorsArray[userStatus.index] ? 'Unsettled' : 'Settled' }</font></th>
				</c:if>
				<c:if test="${sessionScope['login_userlanguage'].language  eq 'zh'}">
					<th><font color="${currentColor}">${empty assignedActorsArray[userStatus.index] ? '未处理' : '已处理' }</font></th>
				</c:if>
				<td><font color="${currentColor}">${assignActorStr}</font></td>
				<td><font color="${currentColor}">${empty assignedActorsArray[userStatus.index] ? planActorsArray[userStatus.index] : assignedActorsArray[userStatus.index]}</font></td>
				<td><font color="${currentColor}">${assignProcessAdvisesArray[userStatus.index]}</font></td>
				<td><font color="${currentColor}">${assignedProcessAdvisesArray[userStatus.index]}</font></td>
				<td><font color="${currentColor}">${assignedEndTimesArray[userStatus.index]}</font></td>
				<td><font color="${currentColor}">${finalProcessAdvisesArray[userStatus.index]}</font></td>
				</tr>
			</c:if>
		</c:forEach>
		<c:if test="${!empty tempVar}"></table><!--</div>--></c:if>
		</c:if>
		
		
		<c:set var="tempVar" value=""></c:set>
		<c:forEach items="${jbpmWorkflowHistoryInfo.attachmentFileUploadInfoDetails}" var="attachmentFileUploadInfoDetail" varStatus="userStatus">
			<c:if test="${0==userStatus.index}">
			<!--<div class="mini-panel" title="<spring:message code="Attachment" text="附件信息"/>：" showCollapseButton="true" style="width: 100%;">-->
			<c:set var="tempVar" value="true"></c:set>
			<table class="workflowTable" style="width:100%"><tr><th>【<spring:message code="AttachmentName" text="附件名称"/>】</th><th>【<spring:message code="UploadPerson" text="上传人"/>】</th><th>【<spring:message code="UploadTime" text="上传时间"/>】</th></tr>
			</c:if>
			<tr>
			<td><font color='red'>${attachmentFileUploadInfoDetail.chineseFileName}</font></td>
			<c:if test="${sessionScope['login_userlanguage'].language  eq 'en'}">
				<td><font color='red'>${attachmentFileUploadInfoDetail.uploadUser.username}</font></td>
			</c:if>
			<c:if test="${sessionScope['login_userlanguage'].language  eq 'zh'}">
				<td><font color='red'>${attachmentFileUploadInfoDetail.uploadUser.realname}</font></td>
			</c:if>
			<td><font color='red'>${attachmentFileUploadInfoDetail.uploadTime}</font></td>
			</tr>
		</c:forEach>
		<c:if test="${!empty tempVar}"></table><!--</div>--></c:if>
		
		
		<c:set var="tempVar" value=""></c:set>
		<c:forEach items="${jbpmWorkflowHistoryInfo.jbpmWorkflowReadUsers}" var="jbpmWorkflowReadUser" varStatus="userStatus">
			<c:if test="${0==userStatus.index}">
			<!--<div class="mini-panel" title="<spring:message code="Read" text="传阅信息"/>：" showCollapseButton="true" style="width: 100%;">-->
			<c:set var="tempVar" value="true"></c:set>
			<table class="workflowTable" style="width:100%"><tr><td>【<spring:message code="ReadStatus" text="传阅状态"/>】</td><td>【<spring:message code="ReadUser" text="传阅人"/>】</td><td>【<spring:message code="ReadAdvise" text="传阅意见"/>】</td></tr>
			</c:if>
			<tr>
			<c:set var="currentColor" value="${empty jbpmWorkflowReadUser.endTime ? 'red' : '' }"></c:set>
			<c:if test="${sessionScope['login_userlanguage'].language  eq 'en'}">
				<th><font color="${currentColor}">${empty jbpmWorkflowReadUser.endTime ? 'Unview' : 'View' }</font></th>
			</c:if>
			<c:if test="${sessionScope['login_userlanguage'].language  eq 'zh'}">
				<th><font color="${currentColor}">${empty jbpmWorkflowReadUser.endTime ? '未查看' : '已查看' }</font></th>
			</c:if>
			<th><font color="${currentColor}">${jbpmWorkflowReadUser.readedActor.realname}</font></th>
			<th><font color="${currentColor}">${jbpmWorkflowReadUser.readedAdvise}</font></th>
			</tr>
		</c:forEach>
		<c:if test="${!empty tempVar}"></table><!--</div>--></c:if>
		
		
		<c:set var="tempVar" value=""></c:set>
		<c:forEach items="${jbpmWorkflowHistoryInfo.jbpmWorkflowSignatureUsers}" var="jbpmWorkflowSignatureUser" varStatus="userStatus">
			<c:if test="${0==userStatus.index}">
			<!--<div class="mini-panel" title="<spring:message code="Signature" text="会签信息"/>：" showCollapseButton="true" style="width: 100%;">-->
			<c:set var="tempVar" value="true"></c:set>
			<table class="workflowTable" style="width:100%"><tr><td>【<spring:message code="SignatureStatus" text="会签状态"/>】</td><th>【<spring:message code="SignatureUser" text="会签人"/>】</td><td>【<spring:message code="SignatureAdvise" text="会签意见"/>】</td></tr>
			</c:if>
			<tr>
			<c:set var="currentColor" value="${empty jbpmWorkflowSignatureUser.endTime ? 'red' : '' }"></c:set>
			<c:if test="${sessionScope['login_userlanguage'].language  eq 'en'}">
				<th><font color="${currentColor}">${empty jbpmWorkflowSignatureUser.endTime ? 'unfinished' : 'finished' }</font></th>
			</c:if>
			<c:if test="${sessionScope['login_userlanguage'].language  eq 'zh'}">
				<th><font color="${currentColor}">${empty jbpmWorkflowSignatureUser.endTime ? '未完成' : '已完成' }</font></th>	
			</c:if>
			<th><font color="${currentColor}">${jbpmWorkflowSignatureUser.signaturedActor.realname}</font></th>
			<th><font color="${currentColor}">${jbpmWorkflowSignatureUser.signaturedAdvise}</font></th>
			</tr>
		</c:forEach>
		<c:if test="${!empty tempVar}"></table><!--</div>--></c:if>
		</div>
	</c:forEach>
	</table>
</div>
<script type="text/javascript">
	mini.parse(document.getElementById('__processAdviseDetail'));
</script>