<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<input name="contract_info.oldcustid" id="contract_info.oldcustid" type="hidden" value="<mini:param  name="contract_info.oldcustid"/>" />
<div id="onhire_contract_cust_change_form" title="承租人变更">
		<table cellspacing="0" cellpadding="0" style="width:100%" class="fillTable">
			<tr class="tr-odd">
				<td class="td-content-title" width="11%">原承租人：</td>
				<td class="td-content" width="39%">
					<input id="contract_info.oldcustname" name="contract_info.oldcustname" class="mini-textbox" allowInput="false"  value="<mini:param  name="contract_info.oldcustname"/>" />
				</td>
				<td class="td-content-title" width="12%">现承租人：</td>
				<td class="td-content" width="38%">
					<input id="contract_info.custid" name="contract_info.custid" class="mini-buttonedit mini-queryinput" allowInput="false"
						text="<mini:param  name="rawValue_contract_info.custid"/>"
						value="<mini:param  name="contract_info.custid"/>"
						onvaluechanged="comboboxChanged(e)"/>
					<input id="rawValue_contract_info.custid" name="rawValue_contract_info.custid"  value="<mini:param  name="rawValue_contract_info.custid"/>" 
						class="mini-textbox" style="display: none" />
				</td>
			</tr>
		</table>
</div>
<script>
jQuery(function(){
	tenwa.createQueryInput({
		id:'contract_info.custid',
		label:'现承租人',
		windowWidth: 600,
		onSelect: function($me, inputObj, rowData){
			mini.get("rawValue_contract_info.custid").setValue(rowData.name);
		},
		params: {
			cust_type: 'cust_type.cust',
			xmlFileName: '/eleasing/workflow/contract/contract_approval/contract_custinfo.xml'
		}
	});
});
jQuery(function(){
	if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("onhire_contract_cust_change_form");};
});
</script>