<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="riskmanager_five_category_form" title="风控五级分类">
	<table class="fillTable" cellspacing="0" cellpadding="0" style="border: 1px solid #99CCFF;">
		<tr class="tr-odd">
			<td class="td-content-title" width='12%'>风控五级分类：</td>
			<td class="td-content" width='38%'>
				<input id="five_category.contractfive_riskmanager" 
					name="five_category.contractfive_riskmanager" 
					class="mini-combobox" 
					required="true"
					textField="name"
					valueField="value"  
				    dataField="datas"
				    allowInput="false" 
				    data-options="{_params:{pid:'five_class'}}" 
				    onbeforeshowpopup="onbeforeshowpopup"
					value="${requestScope['five_category.contractfive_riskmanager'] }"
					text="${requestScope['rawValue_five_category.contractfive_riskmanager']}"
					onvaluechanged="comboboxChanged"
				/>
				<input id="rawValue_five_category.contractfive_riskmanager" name="rawValue_five_category.contractfive_riskmanager" value="${requestScope['rawValue_five_category.contractfive_riskmanager']}" class="mini-textbox" style="display:none"/>
				
			</td>
			<td class="td-content-title" width='12%'>五级分类日期：</td>
			<td class="td-content" width='38%'>
				<input id="five_category.contractfivedate_riskmanager" name="five_category.contractfivedate_riskmanager" class="mini-datepicker" required="true" allowInput="false" value="${requestScope['five_category.contractfivedate_riskmanager'] }"/>
			</td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">五级分类说明：</td>
			<td colspan="3" style="padding-top: 5px;padding-bottom: 5px;">
				<input id="five_category.explain_riskmanager" style="width: 73.5%;height: 100px;" name="five_category.explain_riskmanager" required="true" class="mini-textarea" value="${requestScope['five_category.explain_riskmanager'] }"/>
			</td>
		</tr>
	</table>
</div>
<script language="javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("riskmanager_five_category_form");};
});
</script>