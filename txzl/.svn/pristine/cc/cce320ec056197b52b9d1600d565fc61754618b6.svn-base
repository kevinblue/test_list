<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="risk_five_category_form" title="风险五级分类">
	<table class="fillTable" cellspacing="0" cellpadding="0" style="border: 1px solid #99CCFF;">
		<tr class="tr-odd">
			<td class="td-content-title" width='12%'>风险五级分类：</td>
			<td class="td-content" width='38%'>
				<input id="five_category.contractfive_risk" 
					name="five_category.contractfive_risk" 
					class="mini-combobox" 
					required="true"
					textField="name"
					valueField="value"  
				    dataField="datas"
				    allowInput="false" 
				    data-options="{_params:{pid:'five_class'}}" 
				    onbeforeshowpopup="onbeforeshowpopup"
					value="${requestScope['five_category.contractfive_risk'] }"
					text="${requestScope['rawValue_five_category.contractfive_risk']}"
					onvaluechanged="comboboxChanged"
				/>
				<input id="rawValue_five_category.contractfive_risk" name="rawValue_five_category.contractfive_risk" value="${requestScope['rawValue_five_category.contractfive_risk']}" class="mini-textbox" style="display:none"/>
				
			</td>
			<td class="td-content-title" width='12%'>五级分类日期：</td>
			<td class="td-content" width='38%'>
				<input id="five_category.contractfivedate_risk" name="five_category.contractfivedate_risk" class="mini-datepicker" required="true" allowInput="false" value="${requestScope['five_category.contractfivedate_risk'] }"/>
			</td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">五级分类说明：</td>
			<td colspan="3" style="padding-top: 5px;padding-bottom: 5px;">
				<input id="five_category.explain_risk" style="width: 73.5%;height: 100px;" name="five_category.explain_risk" required="true" class="mini-textarea" value="${requestScope['five_category.explain_risk'] }"/>
			</td>
		</tr>
	</table>
</div>
<script language="javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("risk_five_category_form");};
});
</script>