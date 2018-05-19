<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<input name="contract_change_info.id" id="contract_change_info.id" type="hidden"  value="<mini:param  name="contract_change_info.id"/>"  />
<div id="contract_change_info_form" title="变更说明">
	<table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
		<tr class="tr-odd">
			<td class="td-content-title" width="12%">变更时间：</td>
			<td class="td-content" width="88%"><input id="contract_change_info.signdate" name="contract_change_info.changedate" class="mini-datepicker" allowInput="false" required="true" label="变更时间"  value="<mini:param  name="contract_change_info.changedate"/>" /></td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">变更前内容：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input style="width:72%;height: 80px;" id="contract_change_info.beforecontent" name="contract_change_info.signpeople" class="mini-textarea" allowInput="true" required="true" label="变更前内容" value="<mini:param  name="contract_change_info.signpeople"/>" /></td>
		</tr>
		<tr class="tr-odd">
			<td class="td-content-title">变更后内容：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input style="width:72%;height: 80px;" id="contract_change_info.aftercontent" name="contract_change_info.aftercontent" class="mini-textarea" allowInput="true" required="true" label="变更后内容"  value="<mini:param  name="contract_change_info.aftercontent"/>" /></td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">变更说明：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input style="width:72%;height: 80px;" id="contract_change_info.changeinstruction" name="contract_change_info.changeinstruction" class="mini-textarea" allowInput="true" required="true" label="变更说明"  value="<mini:param  name="contract_change_info.changeinstruction"/>" /></td>
		</tr>
	</table>
</div>
<script type="text/javascript">
jQuery(function(){
	if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("contract_change_info_form");};
});
</script>
