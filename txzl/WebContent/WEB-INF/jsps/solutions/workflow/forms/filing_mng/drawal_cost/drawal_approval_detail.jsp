<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input name="contract_change_info.id" id="contract_change_info.id" type="hidden" value="${requestScope['contract_change_info.id']}" />
<div id="contract_patrol_plan_form" title="法务处理信息">
	<table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
		<tr class="tr-odd">
				<td class="td-content-title"  width="160px">法务编号：</td>
	             <td class="td-content">
			             <input name="law_approval.lawnum" class="mini-textbox" readOnly value="${requestScope['law_approval.lawnum'] }"  label="法务编号"   />
						 <font class="required-tag">*</font>
			      </td>
				<td class="td-content-title"  width="160px">法务处理类型：</td>
	             <td class="td-content">
			             <input name="law_approval.lawtype" class="mini-combobox" label="法务处理类型" textField="name"  
				                  	   valueField="value"  
									   dataField="datas"
									   allowInput="false" 
									   data-options="{_params:{pid:'fwclx'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
						               value="${requestScope['law_approval.lawtype'] }" 
									   text="${requestScope['rawValue_law_approval.lawtype'] }" 
									   onvaluechanged="comboboxChanged" />
						 <font class="required-tag">*</font>
						 <input id="rawValue_law_approval.lawtype" name="rawValue_law_approval.lawtype" value="${requestScope['rawValue_law_approval.lawtype']}" class="mini-textbox" style="display:none"/>
			      </td>
			</tr>
		<tr class="tr-odd">
				<td class="td-content-title"  width="160px">是否有预支费用：</td>
	             <td class="td-content" >
				<input name="law_approval.isknownmoney" id ="law_approval.isknownmoney"  class="mini-combobox" 
				   data="[{text:'是'},{text:'否'}]" 
					type="text" value="${requestScope['law_approval.isknownmoney']}"
					textField="text" valueField="text" >
				</td>
			      <td class="td-content-title"  width="160px">预支费用金额：</td>
	             <td class="td-content">
			             <input name="law_approval.knownmoney" class="mini-textbox"  value="${requestScope['law_approval.knownmoney'] }"  />
			      </td>
			</tr>
		<tr class="tr-even">
			<td class="td-content-title">法务处理说明：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input maxLength="1000" emptyText="请输入法务处理说明" label="法务处理说明" style="width:74%;height: 80px;" id="contract_change_info.beforecontent" name="law_approval.lawmemo" class="mini-textarea" allowInput="true" required="true" label="法务处理说明" value="${requestScope['law_approval.lawmemo']}"/></td>
		</tr>
	</table>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("contract_patrol_plan_form");};
});
</script>
