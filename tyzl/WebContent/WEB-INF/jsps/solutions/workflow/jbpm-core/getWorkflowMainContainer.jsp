<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>
<div id="id_content_tabs" style="width: 100%;border: 0px;">
	<!--页签开始-->
	<div class="mini-tabs" id="__jbpmProcessFormTabs" activeIndex="0" maskOnLoad="false" bodyStyle="padding:0;">
		<div title="<spring:message code="WorkFlowForm" text="流程表单"/>" iconCls="icon-user">
			<jsp:include page="getWorkflowInnerForm.jsp"></jsp:include>
		</div>
		<div title="<spring:message code="ApprovalAdvise" text="审批意见"/>" iconCls="icon-help" >
			<jsp:include page="getWorkflowInnerAdvise.jsp"></jsp:include>
		</div>
		<div title="<spring:message code="ShowAttachment" text="附件一览"/>" iconCls="icon-folderopen" >
			<jsp:include page="getWorkflowInnerAttachment.jsp"></jsp:include>
		</div>
		<div title="<spring:message code="ShowPictures" text="图片预览"/>" iconCls="icon-zoomin" >
			<jsp:include page="getWorkflowInnerAttachment_image.jsp"></jsp:include>
		</div>
	</div>
</div>

<script type="text/javascript">
	mini.parse(document.getElementById('id_content_tabs'));

	var __tabs = mini.get("__jbpmProcessFormTabs");
	__tabs.on("activechanged", function(e){
		if (e.tab.title == "流程表单" || e.tab.title == "WorkFlowForm"　) {
			//toggleFormDisplay();
		} else if (e.tab.title == "审批意见" || e.tab.title == "ApprovalAdvise") {
			toggleAdviseDisplay();
		} else if (e.tab.title == "附件一览" || e.tab.title == "ShowAttachment") {
			loadWorkflowAttachment();
		} else if (e.tab.title == "图片预览" || e.tab.title == "ShowPictures") {
			__loadWorkflowAttachmentImage();
		}
	});
</script>
