<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<table cellspacing="0" cellpadding="0" >
	<tr class="tr-person_review-report">
		<td class="td-even-line-separator">
			<div id="div_id_document_list">
				<input id="json_document_list_str" style="display:none;" class="mini-textarea" name="json_document_list_str" value='${empty json_document_list_str ? "[]" : json_document_list_str }'>
			</div>
		</td>
	</tr>
</table>
<script language="javascript">
jQuery(function(){
	var showTools = false;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = true; }
	new tracywindyTree2Table({
	   width:formWidth-1,
	   isAutoHeight:true,
	   refreshStatisticCallBack:window.setCreditLevel,
 	   renderTo:'div_id_document_list',
 	   id:'document_list_data',
 	   readOnly:showTools,
 	   rootDictId:'DOCUMENT_LIST',
 	   savedDataKey:'document_list_data'+flowUnid,
 	   savedDataKey1:"${requestScope['cust_info.id'] }",
	   savedDataKey3:"${currentProcessInstanceDBID}",  //当前流程的ID
	   savedDataKey4:"${requestScope['currentWorkFlowName']}",//流程名称
 	   height:document.documentElement.clientHeight-50,
 	   totalScoreGrantValueTo:"id_document_list_result",
 	   treeTableJson:'${empty json_document_list_str ? "[]" : json_document_list_str }'
    }); 
	//readOnlyRowColumn(1,4,'div_id_document_list');
});
</script>