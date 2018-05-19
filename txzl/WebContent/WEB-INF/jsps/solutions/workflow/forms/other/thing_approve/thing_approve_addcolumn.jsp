<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input name="contract_change_info.id" id="contract_change_info.id" type="hidden" value="${requestScope['contract_change_info.id']}" />
<div id="contract_addcolumn_form" title="重大事项信息">
<div class="mini-panel" title="事项信息" showCollapseButton="true" style="width:100%;">
	<table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
		<tr class="tr-odd">
				<td class="td-content-title">剩余租金：</td>
				<td class="td-content"><input name="contract_info.currentoverage" class="mini-textbox"  readOnly type="text" value="${requestScope['contract_info.currentoverage'] }" /></td>
				<td class="td-content-title">剩余本金：</td>
				<td class="td-content"><input name="contract_info.curcorpusoverage" class="mini-textbox"   readOnly type="text" value="${requestScope['contract_info.curcorpusoverage'] }" /></td>
			</tr>
		<tr class="tr-even">
				<td class="td-content-title">保证金：</td>
				<td class="td-content" colspan="3"><input name="contract_info.cautionmoney" class="mini-textbox"  readOnly type="text" value="${requestScope['contract_info.cautionmoney'] }" /></td>
		</tr>
	</table>
</div>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("contract_addcolumn_form");};
});
</script>
