<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>

<div class="mini-panel" maskOnLoad="false" title="<spring:message code="UploadAttachment" text="附件上传"/>" style="width: 100%;position: relative;">
	<div id="id_workflowAttachmentContainer" style="width: 100%;"></div>
</div>
 <script type="text/javascript">
	function loadWorkflowAttachment() {
		
		var pageHeightV = jQuery(window).height() - 150;
		var pageWidthV = jQuery(window).width() - 40;
		jQuery('#id_workflowAttachmentContainer').css({height:pageHeightV});
		
		var infoFlag = "attachment";
		if(lazyLoadedObj[infoFlag]){
			return true;
		}
		if(!window.initFileListParams){
		
			apAttachmentFileUpload({
				renderTo:'id_workflowAttachmentContainer',
				module:'WorkflowAttchmentFiles',
				id:'id_customworkflowattachment',
				batchDownloadAttachmentFileTitle:window.assignBatchDownloadAttachmentFileTitle||"${requestScope['proj_info.custname']}",
				//attachmentFileDictListImplBeanName:'commonAttachmentFileDictList',
				attachmentType:window.assignAttachmentTypeIds||'${currentTaskActivityDetailInfo.attachmentTypeIds}',
				identifierOne:window.assignAttachmentKeyOne||"${currentProcessInstanceDBID}",
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
				height:'100%',
				//isReadOnly: window.isCompletedTask||window.assignAttachmentIsReadOnly||false,
				isReadOnlyMini: window.isCompletedTask||window.assignAttachmentIsReadOnly||false,
				border:true,
				displayToggleContainer:"id_toggle_tabs_attachment",
				checkedKey:'isAttachmentChecked'
			});
		
		} else {
			window.initFileListParams();
		}
	}
	var checkAttachmentInfoFunc = function(){return true;};
 </script>
 
 
 <c:if test="${true eq currentTaskIsAttachmentChecked}">
	<script type='text/javascript'>
		checkAttachmentInfoFunc = function(){
			
			if(!lazyLoadedObj["attachment"]){
				//mini.alert("由于此流程步骤需要检查附件完整性，请点击附件一览确认附件完整性，确认无误后再进行提交！");
			///	return false;
			//}else{
				loadWorkflowAttachment();
			}
			var attachmentTable=mini.get("id_customworkflowattachment");
			//var attachmentTable	= getTracywindyObject("id_customworkflowattachment");
			var attachmentRowsJsonArrayData = attachmentTable.getData();
			
			//检查附件
			var checkedKey = attachmentTable.checkedKey;
			for(var i = 0;i < attachmentRowsJsonArrayData.length; i++){
				var attachmentRowData = attachmentRowsJsonArrayData[i];
				var attachmentFileDictFileName = attachmentRowData["attachmentFileDictFileName"] ;
				var fileListUUIDStr = attachmentRowData["attachmentFileUploadInfoDetailIds"] ;
				var currentIsAttachmentChecked = attachmentRowData[checkedKey] ;
				if("true" == (currentIsAttachmentChecked+"")){
					if(attachmentTable.checkAttachmentInfoFuncCallback){
						isPassed = attachmentTable.checkAttachmentInfoFuncCallback(attachmentFileDictFileName,fileListUUIDStr,attachmentRowData,attachmentTable); 
						if(false == isPassed){
							return isPassed;
						}
					} else {
						if(!fileListUUIDStr) {
							mini.alert(attachmentFileDictFileName+"必需上传附件");
							return false;
						}
					}
				}
			}
			return true;
		};
	</script>
 </c:if>