<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<table cellspacing="0" cellpadding="0" >
	<tr class="tr-person_review-report">
		<td class="td-even-line-separator">
			<div id="div_id_credit_grade">
				<input id="json_credit_grade_str" style="display:none;"	class="mini-textarea" name="json_credit_grade_str" value='${empty json_credit_grade_str ? "[]" : json_credit_grade_str }'>
			</div>
		</td>
	</tr>
</table>
<script language="javascript">
jQuery(function(){
	var showTools = false;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){ showTools = true;}
	jQuery(function(){
		new tracywindyTree2Table({
		   width:formWidth-1,
		   isAutoHeight:true,
		   refreshStatisticCallBack:window.setCreditLevel,
	 	   renderTo:'div_id_credit_grade',
	 	   id:'credit_grade_data',
	 	   rootDictId:'CREDIT_GRADE',
	 	   readOnly:showTools,
	 	   savedDataKey:'credit_grade_data'+flowUnid,
	 	   savedDataKey1:"${requestScope['cust_info.id'] }",
		   savedDataKey3:"${currentProcessInstanceDBID}",  //当前流程的ID
		   savedDataKey4:"${requestScope['currentWorkFlowName']}",//流程名称
	 	   height:document.documentElement.clientHeight-50,
	 	   totalScoreGrantValueTo:"id_credit_grade_result",
	 	   treeTableJson:'${empty json_credit_grade_str ? "[]" : json_credit_grade_str }'
	    }); 
	});

});	
</script>