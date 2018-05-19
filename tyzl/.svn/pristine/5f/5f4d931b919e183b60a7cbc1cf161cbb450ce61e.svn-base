<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<div id="contract_cancel_reason_form" title="撤销原因">
	<table class="fillTable" cellspacing="0" cellpadding="0" style="border: 1px solid #99CCFF;">
		<tr class="tr-odd">
			<td class="td-content-title" width='12%'>撤销时间：</td>
			<td class="td-content" width='38%'>
				<input id="contract_info_repealdate" name="contract_info.repealdate" class="mini-datepicker" required="true" allowInput="false" value="<mini:param  name="contract_info.repealdate"/>"/>
			</td>
			<td class="td-content-title" width='12%'>撤销类型：</td>
			<td class="td-content" width='38%'>
				<input id="contract_info_repealreasontype" 
					name="contract_info.repealreasontype" 
					class="mini-combobox" 
					required="true"
					textField="name"
					valueField="value"  
				    dataField="datas"
				    allowInput="false" 
				    data-options="{_params:{pid:'repeal_type'}}" 
				    onbeforeshowpopup="onbeforeshowpopup"
				    value="<mini:param  name="contract_info.repealreasontype"/>"
					text="<mini:param  name="rawValue_contract_info.repealreasontype"/>"
					onvaluechanged="comboboxChanged"
				/>
				<input id="rawValue_contract_info.repealreasontype" name="rawValue_contract_info.repealreasontype" value="<mini:param  name="rawValue_contract_info.repealreasontype"/>" class="mini-textbox" style="display:none"/>
				
			</td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">撤销原因：</td>
			<td colspan="3" style="padding-top: 5px;padding-bottom: 5px;">
				<input id="contract_info_repealreason" style="width: 72%;height: 100px;" name="contract_info.repealreason" required="true" class="mini-textarea" value="<mini:param  name="contract_info.repealreason"/>" />
			</td>
		</tr>
	</table>
</div>
<script language="javascript">
jQuery(function(){
	if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("contract_cancel_reason_form");};
});
</script>