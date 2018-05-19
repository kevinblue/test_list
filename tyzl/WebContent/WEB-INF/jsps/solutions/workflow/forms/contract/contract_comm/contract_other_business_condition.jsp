<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="contract_other_business_condition_form">
<table cellspacing="0" cellpadding="0"  style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
	<tr class="tr-odd">
		<td class="td-content-title" width="12%">其他商务条件：</td>
		<td style="padding: 5px 0px;" width="88%">
			<input name="contract_info.othercondition" id="contract_info.othercondition" class="mini-textarea" label="其它商务条件" style="width: 72%;height: 200px;" emptyText="请输入其它商务条件" value="${requestScope['contract_info.othercondition'] }"  />
		</td>
	</tr>
</table>
</div>
<script>
jQuery(function(){
	if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("contract_other_business_condition_form");};
});
</script>