<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="cust_grade_form" title="客户评价">
	<table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
		<tr class="tr-odd">
			<td class="td-content-title" width="12%">客户信用优势：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input style="width:72%;height: 80px;" id="cust_grade.advantage" name="cust_grade.advantage" class="mini-textarea" allowInput="true" required="true" label="客户信用优势" value="${requestScope['cust_grade.advantage']}"/></td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">客户信用劣势：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input style="width:72%;height: 80px;" id="cust_grade.inferiority" name="cust_grade.inferiority" class="mini-textarea" allowInput="true" required="true" label="客户信用劣势" value="${requestScope['cust_grade.inferiority']}"/></td>
		</tr>
		<tr class="tr-odd">
			<td class="td-content-title">客户盈利资产项目的实施情况 ：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input style="width:72%;height: 80px;" id="cust_grade.implementation" name="cust_grade.implementation" class="mini-textarea" allowInput="true" required="true" label="客户盈利资产项目的实施情况" value="${requestScope['cust_grade.implementation']}"/></td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">客户未来信用等级预测：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input style="width:72%;height: 80px;" id="cust_grade.forecast" name="cust_grade.forecast" class="mini-textarea" allowInput="true" required="true" label="客户未来信用等级预测" value="${requestScope['cust_grade.forecast']}"/></td>
		</tr>
	   <tr class="tr-odd">
			<td class="td-content-title">客户评级结论：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input style="width:72%;height: 80px;" id="cust_grade.conclusion" name="cust_grade.conclusion" class="mini-textarea" allowInput="true" required="true" label="客户评级结论" value="${requestScope['cust_grade.conclusion']}"/></td>
		</tr>
	</table>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("cust_grade_form");};
});
</script>
