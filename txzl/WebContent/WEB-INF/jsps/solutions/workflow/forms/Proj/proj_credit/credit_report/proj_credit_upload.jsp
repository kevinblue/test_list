<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<input name="json_due_report_file_str" id="json_due_report_file_str" type="hidden" value='${empty json_due_report_file_str ? "[]" : json_due_report_file_str}'></input>
<input name="due_report_base_file_id" id="due_report_base_file_id" type="hidden" value="${requestScope['due_report_base_file_id'] }"/>
<input name="due_report_base_file_name" id="due_report_base_file_name" type="hidden" value="${requestScope['due_report_base_file_name'] }"/>
	<div id="id_div_due_report_container" style="padding: 5px 5px;"></div>
<script type="text/javascript">
$(function(){
	var industrytype = "${requestScope['proj_info.industrytype']}";
	var importType = "";
	if("Public" == industrytype){
		importType = "尽调报告-公用事业";
	}else if("Education" == industrytype){
		importType = "尽调报告-教育";
	}else if("medical" == industrytype){
		importType = "尽调报告-医疗";
	}else if("Pharma"==industrytype){
		importType = "尽调报告-制药";
	}else if("IntraGroup"== industrytype){
		importType = "尽调报告-集团内部";
	}else if("tourism"==industrytype){
		importType = "尽调报告-旅游";	
	}
	new importExcelUtil({
		isAttachment : true,
		attachmentParams: {
			module:'WorkflowAttchmentFiles',
			jbpmWorkflowHistoryInfoId : window.currentJbpmWorkflowHistoryInfoId ? window.currentJbpmWorkflowHistoryInfoId : '',
			attachmentFileDictId : 'root.FileType.Type4.01', //评估报告ID
			identifierOne:window.assignAttachmentKeyOne||"${currentProcessInstanceDBID}",
			identifierTwo:window.assignAttachmentKeyTwo||jQuery("#id_currentHistoryTaskInfo_keyOne").val(),
			identifierThree:window.assignAttachmentKeyThree||jQuery("#id_currentHistoryTaskInfo_keyTwo").val(),
			identifierFour:window.assignAttachmentKeyFour||jQuery("#id_currentHistoryTaskInfo_keyThree").val(),
			identifierFive:window.assignAttachmentKeyFive||jQuery("#id_currentHistoryTaskInfo_keyFour").val(),
			identifierSix:window.assignAttachmentKeySix||jQuery("#id_currentHistoryTaskInfo_keyFive").val(),
			identifierSeven:window.assignAttachmentKeySeven||jQuery("#id_currentHistoryTaskInfo_keySix").val(),
			identifierEight:window.assignAttachmentKeyEight||jQuery("#id_currentHistoryTaskInfo_keySeven").val(),
			identifierNine:window.assignAttachmentKeyNine||jQuery("#id_currentHistoryTaskInfo_keyEight").val(),
			identifierTen:window.assignAttachmentKeyTen||jQuery("#id_currentHistoryTaskInfo_keyNine").val()
		},
		id : "due_report",
		renderTo : "id_div_due_report_container",
		uploadButtonText : "导入评估报告",		
		importType : importType,
		showBtn : !('${param.isView}' == 'true'),
		fileKey : "${requestScope['proj_info.id']}",
		data : mini.decode('${empty json_due_report_file_str ? "[]" : json_due_report_file_str}'),
		baseFileId : "${requestScope['due_report_base_file_id'] }",
		baseFileName : "${requestScope['due_report_base_file_name'] }",
		importCompleteCallback : "uploadDueReportExcelFileCallBack"
	});
	
});

function uploadDueReportExcelFileCallBack(result){
	result = result.replace(/(\n)|(\r)/g,"<br/>")
	var res = mini.decode(result);
	if(res.result == "success"){		
		mini.alert(res.message);		
		$("#json_due_report_file_str").val(mini.encode(res.exceldata));
		$("#due_report_base_file_id").val(res.basefileid);
		$("#due_report_base_file_name").val(res.basefilename);
		var due_reprot = getMiniuiExtObject("due_report");
		due_reprot.showDownloadHref(res.basefileid, res.basefilename);
		due_reprot.loadExcelData(res.exceldata);
		due_reprot.hideUploadWindow();
		mini.unmask(document.body);
	
	}else{
		mini.alert(res.message);
		mini.unmask(document.body);
	}
}

</script>