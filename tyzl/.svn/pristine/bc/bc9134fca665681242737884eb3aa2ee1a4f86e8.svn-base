<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<input name="contract_invoice_type.id" id="contract_invoice_type.id" type="hidden"  value="<mini:param  name="rawValue_proj_info.projtype"/>" value="<mini:param  name="contract_invoice_type.id"/>"/>
<div id="contract_sign_info_form" title="签约信息">
	<table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
		<tr class="tr-odd">
			<td class="td-content-title" width="12%">签约日期：</td>
			<td class="td-content" width="38%">
				<input id="contract_info.signdate" name="contract_info.signdate" class="mini-datepicker" required="true" label="签约日期" allowInput="false"  value="<mini:param  name="contract_info.signdate"/>"/>
			</td>
			<td class="td-content-title" width="12%">签约地点：</td>
			<td class="td-content" width="38%">
				<input id="contract_info.signplace" name="contract_info.signplace" class="mini-textbox" required="true" label="签约地点" allowInput="true"  value="<mini:param  name="contract_info.signplace"/>" />
			</td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">签约参与人：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3">
				<input style="width:72%;height: 200px;" id="contract_info.signpeople" name="contract_info.signpeople" class="mini-textarea" required="true" label="签约参与人" allowInput="true"  value="<mini:param  name="contract_info.signpeople"/>"/>
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
jQuery(function(){
	if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("contract_sign_info_form");};
});
</script>