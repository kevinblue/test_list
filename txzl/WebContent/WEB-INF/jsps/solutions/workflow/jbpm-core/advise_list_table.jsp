<%@page import="com.tenwa.kernal.utils.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c'   uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix="fn"  uri="/WEB-INF/tlds/fn.tld" %> 
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<%@ taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>	
<html>
<head>
<title>审批意见</title>

<style type="text/css">
/* #table_info {
	border-collapse: collapse;
	border: none;
	width: 200px;
}

#table_info  td {
	border: solid #000 1px;
	text-align: center;
} */
.workFlowTableClass{
	top:10px;
	width: 90%; 
	border-color: black; 
	border-width: 1px; 
	margin-left: auto; 
	margin-right: auto;
	border-collapse: collapse;
	border: none;
	margin-bottom: 10px;
}
.workFlowTableClass td{
	border: solid #000 1px;
	text-align: center;
	font-size:10pt;
}
.title{
	font-weight: bold;
}
</style>

</head>
<body>
	<div id="id_fund_put_application" style="width: 100%; height: 100%">
		<div style="text-align:right;padding-right:100px;padding-bottom:10px;font-size:14px;font-weight:bold;">流水号：${param.DocNo}</div>
		<div style="clear: both;"></div>
	   <table class="workFlowTableClass">
	   		<tr>
				<td colspan="5" style="text-align: center; font-weight: bold;margin-top: auto;font-size:14pt;">审&nbsp;批&nbsp;意&nbsp;见</td>
			</tr>
			
				  	 <tr>
				  	 	<td class="title">流程名称</td>
		           	    <td class="title">流程状态</td>
		           	    <td class="title">项目编号</td>
		           	    <td  class="title">业务合同编号</td>
		           	    <td  class="title">项目名称</td>
				  	 </tr>
				  	 <tr>
				  	 	<td class="title">${workFlowName}</td>
		           	    <td class="title">${state_ == 'active' ? '进行中': '结束'}</td>
		           	    <td class="title">${key1}</td>
		           	    <td >${key3}</td>
		           	    <td >${key2}</td>
				  	 </tr>
				
			<c:forEach items="${processedHistoryLogInfoList}" var="jbpmWorkflowHistoryInfo" varStatus="status">
		           <c:set var="historyTask" value="${jbpmWorkflowHistoryInfo.historyTaskInstanceImpl}"></c:set>
		           <c:set var="activityDetail" value="${jbpmWorkflowHistoryInfo.activityDetail}"></c:set>
		           <c:set var="fromButtonTextToCome" value="${jbpmWorkflowHistoryInfo.fromButtonTextToCome}"></c:set>
		           <c:set var="taskName" value="${activityDetail.activityName}"></c:set>
		           <c:set var="taskStartTime" value="${historyTask.startTime}"></c:set>
		           <c:set var="taskEndTime" value="${historyTask.endTime}"></c:set>
		           <c:set var="durationMinutes" value="${historyTask.duration/1000/60}"></c:set>
                  <tr>
					   <td width="15%">【<font color="red"><spring:message code="${fromButtonTextToCome}"  text="${fromButtonTextToCome}"/></font>】${taskName}</td>
                       <td width="20%">【开始时间】<fmt:formatDate value="${taskStartTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                       <td width="20%">【结束时间】<fmt:formatDate value="${taskEndTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                       <td width="45%" colspan="2">【耗时】<fmt:formatNumber value="${durationMinutes}" pattern="#.##" type="number"></fmt:formatNumber>&nbsp;分钟</td>
                   </tr>
                   <tr>
		           	  <td class="title">处理状态</td>
		           	  <td class="title">处理人</td>
		           	  <td class="title">处理时间</td>
		           	  <td width="10%" class="title">审批结论</td>
		           	  <td>处理意见</td>
		           </tr>
			       <c:forEach items="${jbpmWorkflowHistoryInfo.jbpmWorkflowHistoryInfoUsers}" var="jbpmWorkflowHistoryInfoUser" varStatus="userStatus">
			        <c:set var="taskPlanActor" value="${jbpmWorkflowHistoryInfoUser.planActor}"></c:set>
		            <c:set var="taskActualActor" value="${jbpmWorkflowHistoryInfoUser.actualActor}"></c:set>
		            <c:set var="taskPlanActorRealName" value="${taskPlanActor.realname}"></c:set>
		            <c:set var="taskActualActorRealName" value="${taskActualActor.realname}"></c:set>
		            <c:set var="processedAdvise" value="${jbpmWorkflowHistoryInfoUser.processedAdvise}"></c:set>
		             <c:set var="processedResult" value="${jbpmWorkflowHistoryInfoUser.processedResult.name}"></c:set>
		           <tr>
		           	  <td>${empty jbpmWorkflowHistoryInfoUser.endTime ? (jbpmWorkflowHistoryInfoUser.isReaded ?  '已查看' : '未处理') : '已处理' } </td>
		           	  <td>${taskPlanActor.realname}</td>
		           	  <td>${jbpmWorkflowHistoryInfoUser.endTime}</td>
		           	  <td>${processedResult}</td>
		           	  <td>${processedAdvise}</td>
		           </tr>
			       </c:forEach>
			       <c:forEach items="${jbpmWorkflowHistoryInfo.jbpmWorkflowReadUsers}" var="jbpmWorkflowReadUser" varStatus="userStatus">
			       <c:if test="${0==userStatus.index}">
				        <tr>
				           	  <td colspan="2">传阅状态</td>
				           	  <td colspan="2">传阅人</td>
				           	  <td colspan="2">传阅意见</td>
			          	</tr>
			        </c:if>
		           	<tr>
		           	  <td colspan="2">${empty jbpmWorkflowReadUser.endTime ? '未查看' : '已查看' }</td>
		           	  <td colspan="2">${jbpmWorkflowReadUser.readedActor.realname}</td>
		           	  <td colspan="2">${jbpmWorkflowReadUser.readedAdvise}</td>
		           </tr>
			       </c:forEach>
		      </c:forEach>
		  </table>
		  <!-- 
		  <table class="workFlowTableClass">
		  	 <tr>
		  	 	<td class="title">流程名称</td>
           	    <td class="title">流程状态</td>
           	    <td class="title">项目编号</td>
           	    <td  class="title">项目名称</td>
           	    <td  class="title">合同编号</td>
		  	 </tr>
		  	 <tr>
		  	 	<td class="title">${workFlowName}</td>
           	    <td class="title">${state_ == 'active' ? '进行中': '结束'}</td>
           	    <td class="title">${key1}</td>
           	    <td >${key2}</td>
           	    <td >${key3}</td>
		  	 </tr>
		  </table> 
		  -->    
	</div>
</body>
</html>


