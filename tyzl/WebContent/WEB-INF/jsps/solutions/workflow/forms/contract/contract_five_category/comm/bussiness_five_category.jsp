<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<div id="bussiness_five_category_form" title="五级分类">
	<table class="fillTable" cellspacing="0" cellpadding="0" style="border: 1px solid #99CCFF;">
		<tr class="tr-odd">
			<td class="td-content-title" width='12%'>合同五级分类：</td>
			<td class="td-content" width='38%'>
				<input id="five_category.contractfive_business" 
					name="five_category.contractfive_business" 
					class="mini-combobox" 
					required="true"
					textField="name"
					valueField="value"  
				    dataField="datas"
				    allowInput="false" 
				    data-options="{_params:{pid:'five_class'}}" 
				    onbeforeshowpopup="onbeforeshowpopup"
				    value="<mini:param  name="five_category.contractfive_business"/>" 
					text="<mini:param  name="rawValue_five_category.contractfive_business"/>"
					onvaluechanged="comboboxChanged"
				/>
				<input id="rawValue_five_category.contractfive_business" name="rawValue_five_category.contractfive_business" value="<mini:param  name="rawValue_five_category.contractfive_business"/>" class="mini-textbox" style="display:none"/>
				
			</td>
			<td class="td-content-title" width='12%'>五级分类日期：</td>
			<td class="td-content" width='38%'>
				<input id="five_category.contractfivedate_business" name="five_category.contractfivedate_business" class="mini-datepicker" required="true" allowInput="false" value="<mini:param  name="five_category.contractfivedate_business"/>"/>
			</td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">五级分类说明：</td>
			<td colspan="3" style="padding-top: 5px;padding-bottom: 5px;">
				<input id="five_category.explain_business" style="width: 73.5%;height: 100px;" name="five_category.explain_business" required="true" class="mini-textarea" value="<mini:param  name="five_category.explain_business"/>"/>
			</td>
		</tr>
	</table>
</div>
<script language="javascript">
jQuery(function(){
	if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("bussiness_five_category_form");};
});
</script>