<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input name="contract_change_info.id" id="contract_change_info.id" type="hidden" value="${requestScope['contract_change_info.id']}" />
<div id="contract_change_info_form" title="变更说明">
	<table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
		<tr class="tr-odd">
			<td class="td-content-title" width="12%">终止时间：</td>
			<td class="td-content" width="88%"><input id="contract_change_info.signdate" name="contract_change_info.changedate" class="mini-datepicker" allowInput="false" required="true" label="变更时间" value="${requestScope['contract_change_info.changedate']}"/></td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">终止原因：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input style="width:72%;height: 80px;" id="contract_change_info.beforecontent" name="contract_change_info.signpeople" class="mini-textarea" allowInput="true" required="true" label="变更前内容" value="${requestScope['contract_change_info.signpeople']}"/></td>
		</tr>
		<tr class="tr-odd" style="display:none">
			<td class="td-content-title">变更后内容：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input style="width:72%;height: 80px;" id="contract_change_info.aftercontent" name="contract_change_info.aftercontent" class="mini-textarea" allowInput="true" required="true" label="变更后内容" value="${requestScope['contract_change_info.aftercontent']}"/></td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">备注：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input style="width:72%;height: 80px;" id="contract_change_info.changeinstruction" name="contract_change_info.changeinstruction" class="mini-textarea" allowInput="true" required="true" label="变更说明" value="${requestScope['contract_change_info.changeinstruction']}"/></td>
		</tr>
	</table>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("contract_change_info_form");};
});
</script>
