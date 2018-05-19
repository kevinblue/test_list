<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="/WEB-INF/tlds/c.tld"%>
<%@taglib prefix="fn"  uri="/WEB-INF/tlds/fn.tld"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTree2Table.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script type="text/javascript">
var datas_workflow = [];
var datas_attachment = [];
<c:forEach var="projSummaryHistoryInfo" items="${projSummaryHistoryInfos}">
   datas_workflow.push({
       "taskid"      :"${projSummaryHistoryInfo.historyTaskInstanceImpl.historyTask.id}",
       "workflowname":"${projSummaryHistoryInfo.workflowDisplayName}",
       "processinstancestate":"${projSummaryHistoryInfo.processInstanceState}",
       "keyone":"${projSummaryHistoryInfo.keyOne}",
       "keytwo":"${projSummaryHistoryInfo.keyTwo}",
       "keythree":"${projSummaryHistoryInfo.keyThree}",
       "keyfour":"${projSummaryHistoryInfo.keyFour}",
       "starttime":"${projSummaryHistoryInfo.historyProcessInstanceImpl.startTime}",
       "endtime":"${projSummaryHistoryInfo.historyProcessInstanceImpl.endTime}"
   });
</c:forEach>
<c:forEach var="pshi" items="${projSummaryHistoryInfos}">
	<c:forEach var="afuid" items="${pshi.historyProcessInstanceImpl.attachmentFileUploadInfoDetails}">
	datas_attachment.push({
	    "attachmentType":"${afuid.attachmentFileUploadInfo.attachmentFileDict.name}",
	    "attachmentName":"${afuid.chineseFileName}",
	    "attachmentSize":"${afuid.fileSize}",
	    "attachmentUploadTime":"${afuid.uploadTime}",
	    "attachmentUploadUser":"${afuid.uploadUser.realname}",
	    "attachmentDownloadCount":"${fn:length(afuid.attachmentFileUploadInfoDetailDownloads)}",
	    "attachmentId":"${afuid.id}"});
	</c:forEach>
</c:forEach>
</script>
	<div id="contract_approval_form">
		<div class="mini-tabs" activeIndex="0"   style="width: 100%;height:100%;" id="tabDetailsBase"  onactivechanged="changTab">
			<div title="基本信息" name="proj_equip" iconCls="icon-cut" >
				<jsp:include page="baseInfo.jsp">
					<jsp:param value="false" name="isView"/>
				</jsp:include>
			</div>
			<div title="附件预览" name="conditionDetail" iconCls="icon-cut" style="width: 100%;height:100%;" >
				<jsp:include page="attachmentPreShow.jsp" >
					<jsp:param value="false" name="isView"/>
				</jsp:include>
			</div>
	   </div>
	</div>
