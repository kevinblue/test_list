<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tenwa.kernal.utils.FileUtil,com.tenwa.kernal.utils.WebUtil" %>
<%@taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/apcomponent/apattachmentfileupload/newAttachmentFileUpload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<%@include file="/base/tracywindyLocale.jsp"%>
<script type="text/javascript">
var pageWidth  = document.documentElement.clientWidth-2;
var pageHeight = document.documentElement.clientHeight-2;
var contractid="${requestScope['contract_info.contractid']}";
var param={};
	jQuery(function(){
		newAttachmentFileUpload({
			renderTo:'id_tableContainer',
			module:'WorkflowAttchmentFiles',
			id:'id_customworkflowattachment',
			batchDownloadAttachmentFileTitle:window.assignBatchDownloadAttachmentFileTitle||"${requestScope['contract_info.custname']}",
			//attachmentFileDictListImplBeanName:'commonAttachmentFileDictList',
			attachmentType:"${param.attachmentType}",
			identifierOne:window.assignAttachmentKeyOne||contractid,
			identifierTwo:window.assignAttachmentKeyTwo||jQuery("#id_currentHistoryTaskInfo_keyOne").val(),
			identifierThree:window.assignAttachmentKeyThree||jQuery("#id_currentHistoryTaskInfo_keyTwo").val(),
			identifierFour:window.assignAttachmentKeyFour||jQuery("#id_currentHistoryTaskInfo_keyThree").val(),
			identifierFive:window.assignAttachmentKeyFive||jQuery("#id_currentHistoryTaskInfo_keyFour").val(),
			identifierSix:window.assignAttachmentKeySix||jQuery("#id_currentHistoryTaskInfo_keyFive").val(),
			identifierSeven:window.assignAttachmentKeySeven||jQuery("#id_currentHistoryTaskInfo_keySix").val(),
			identifierEight:window.assignAttachmentKeyEight||jQuery("#id_currentHistoryTaskInfo_keySeven").val(),
			identifierNine:window.assignAttachmentKeyNine||jQuery("#id_currentHistoryTaskInfo_keyEight").val(),
			identifierTen:window.assignAttachmentKeyTen||jQuery("#id_currentHistoryTaskInfo_keyNine").val(),
			width:'100%',
			height:'310',
			isReadOnlyMini:false,
			border:true,
			displayToggleContainer:"id_toggle_tabs_attachment",
			checkedKey:'isAttachmentChecked'
		});
		
   });

</script>
<div id='id_tableContainer'></div>
</html>