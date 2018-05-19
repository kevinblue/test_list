<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="contract_asset_class" title="初始资产分类">
	<table cellspacing="0" cellpadding="0" style="width:100%;" class="fillTable">
		<tr class="tr-even">
			<td class="td-content-title" width="12%">初始资产分类：</td>
			<td class="td-content" width="38%">
				<input id="contract_info.assetclass" name="contract_info.assetclass" class="mini-combobox" textField="name"  label="纳税人类别"
		                  	   valueField="value"  
							   dataField="datas"
							   allowInput="false" 
							   showNullItem="true"
							   required="true" 
							   data-options="{_params:{pid:'five_class'}}" 
							   onbeforeshowpopup="onbeforeshowpopup"
							   value="${requestScope['contract_info.assetclass'] }" 
							   text="${requestScope['rawValue_contract_info.assetclass'] }" 
							   onvaluechanged="comboboxChanged"
				 />	 
				 <input id="rawValue_contract_info.assetclass" name="rawValue_contract_info.assetclass" value="${requestScope['rawValue_contract_info.assetclass']}" class="mini-textbox" visible="false"/>
             </td>
			<td class="td-content-title"></td>
			<td class="td-content"></td>
		</tr>
	</table>
</div>
<script type="text/javascript">
var showTools = true;
if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
if(showTools==false){
     miniui_ext.disableFormFields("contract_asset_class");
}
</script>