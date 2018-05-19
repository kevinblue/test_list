<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="contract_cancel_reason_form" title="撤销明细">
	<table class="fillTable" cellspacing="0" cellpadding="0" style="border: 1px solid #99CCFF;">
		<tr class="tr-odd">
			<td class="td-content-title" width='12%'>撤销时间：</td>
			<td class="td-content" width='38%'>
				<input id="contract_info_repealdate" label="撤销时间" name="contract_info.repealdate" class="mini-datepicker" required="true" allowInput="false" value="${requestScope['contract_info.repealdate'] }"/>
			</td>
			<td class="td-content-title" width='12%'>撤销类型：</td>
			<td class="td-content" width='38%'>
				<input id="contract_info_repealreasontype"  label="撤销类型"
					name="contract_info.repealreasontype" 
					class="mini-combobox" 
					required="true"
					textField="name"
					valueField="value"  
				    dataField="datas"
				    allowInput="false" 
				    data-options="{_params:{pid:'repeal_type'}}" 
				    onbeforeshowpopup="onbeforeshowpopup"
					value="${requestScope['contract_info.repealreasontype'] }"
					text="${requestScope['rawValue_contract_info.repealreasontype']}"
					onvaluechanged="comboboxChanged"
				/>
				<input id="rawValue_contract_info.repealreasontype" name="rawValue_contract_info.repealreasontype" value="${requestScope['rawValue_contract_info.repealreasontype']}" class="mini-textbox" style="display:none"/>
				
			</td>
		</tr>
		<%-- <tr class="tr-even">
			<td class="td-content-title" style="width:15%">结束后是否发起项目变更流程：</td>
			<td class="td-content" >
				<input id="contract_info_changeflow" label="结束后是否发起项目变更流程"
					name="contract_info.changeflow" 
					class="mini-combobox" 
					required="true"
					textField="name"
					valueField="value"  
				   	data="[{name : '是',value:'是'},{name : '否',value:'否'}]"
				    allowInput="false" 
				    value="${requestScope['contract_info.changeflow'] }"
					text="${requestScope['rawValue_contract_info.changeflow']}"
					onvaluechanged="comboboxChanged"
				/>
				<input id="rawValue_contract_info.changeflow" name="rawValue_contract_info.changeflow" value="${requestScope['rawValue_contract_info.changeflow']}" class="mini-textbox" style="display:none"/>
				
			</td>
		</tr> --%>
		<tr class="tr-even">
			<td class="td-content-title">撤销原因：</td>
			<td colspan="3" style="padding-top: 5px;padding-bottom: 5px;">
				<input id="contract_info_repealreason" label="撤销原因" style="width: 72%;height: 100px;" name="contract_info.repealreason" required="true" class="mini-textarea" value="${requestScope['contract_info.repealreason'] }"/>
			</td>
		</tr>
	</table>
</div>
<script language="javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("contract_cancel_reason_form");};
});
</script>