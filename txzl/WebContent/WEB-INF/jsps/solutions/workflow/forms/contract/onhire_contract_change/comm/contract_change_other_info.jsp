<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input name="contract_change_other_info.id" id="contract_change_other_info.id" type="hidden" value="${requestScope['contract_change_other_info.id']}" />
<div id="contract_otherchange_info_form" title="其它实质性变更 ">
	<table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
		<tr class="tr-even">
			<td class="td-content-title" width="12%">变更前内容：</td>
			<td ><input style="width:72%;height: 80px;" id="contract_change_other_info.newchangecontent" name="contract_change_other_info.newchangecontent" class="mini-textarea" allowInput="true" required="true" label="变更前内容" value="${requestScope['contract_change_other_info.newchangecontent']}"/></td>
		</tr>
		<tr class="tr-odd">
			<td class="td-content-title" width="12%">变更后内容：</td>
			<td ><input style="width:72%;height: 80px;" id="contract_change_other_info.afterchangecontent" name="contract_change_other_info.afterchangecontent" class="mini-textarea" allowInput="true" required="true" label="变更后内容" value="${requestScope['contract_change_other_info.afterchangecontent']}"/></td>
		</tr>
	</table>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("contract_otherchange_info_form");};
});
</script>
