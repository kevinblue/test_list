<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="subscription_information">
<table cellspacing="0" cellpadding="0"  style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
	<tr class="tr-odd">
		<td class="td-content-title" width="12%">签约日期：</td>
		<td style="padding: 5px 0px;" width="88%">
			<input name="contract_info.signDate" id="contract_info.signDate" class="mini-datepicker" label="签约日期" value="${requestScope['contract_info.signDate']}"  />
		</td>
	</tr>
	<tr class="tr-odd">
		<td class="td-content-title" width="12%">项目签约人：</td>
		<td style="padding: 5px 0px;" width="88%">
			<input name="contract_info.signPeople" id="contract_info.signPeople" class="mini-textarea" label="项目签约人" style="width: 72%;height: 200px;"  value="${requestScope['contract_info.signPeople']}"  />
		</td>
	</tr>
</table>
</div>
<script>
jQuery(function(){
	if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("contract_other_business_condition_form");};
});
</script>