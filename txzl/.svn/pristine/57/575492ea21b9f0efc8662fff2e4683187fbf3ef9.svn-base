<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input name="contract_change_info.id" id="contract_change_info.id" type="hidden" value="${requestScope['contract_change_info.id']}" />
<div id="proj_gx_form" title="项目概述">
<div class="mini-panel" title="项目概述" showCollapseButton="true" style="width:100%;">
	<table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
		<tr class="tr-odd">
				<td class="td-content-title">剩余租金：</td>
				<td class="td-content"><input name="contract_info.currentoverage" class="mini-textbox"  readOnly type="text" value="${requestScope['contract_info.currentoverage'] }" /></td>
				<td class="td-content-title">剩余本金：</td>
				<td class="td-content"><input name="contract_info.curcorpusoverage" class="mini-textbox"   readOnly type="text" value="${requestScope['contract_info.curcorpusoverage'] }" /></td>
			</tr>
		<tr class="tr-even">
				<td class="td-content-title">保证金：</td>
				<td class="td-content"><input name="contract_info.cautionmoney" class="mini-textbox"  readOnly type="text" value="${requestScope['contract_info.cautionmoney'] }" /></td>
				<td class="td-content-title">保证人：</td>
				<td class="td-content"><input name="contract_info.guarantee" class="mini-textbox"  readOnly  type="text" value="${requestScope['contract_info.guarantee'] }" /></td>
		</tr>
		<tr class="tr-odd">
				<td class="td-content-title">其他担保：</td>
				<td class="td-content"><input name="contract_info.otherguarantee" class="mini-textbox"   type="text" value="${requestScope['contract_info.otherguarantee'] }" /></td>
				<td class="td-content-title">起租日：</td>
				<td class="td-content"><input name="contract_info.startdate" class="mini-textbox"   readOnly type="text" value="${requestScope['contract_info.startdate'] }" /></td>
		</tr>
		<tr class="tr-even">
				<td class="td-content-title">租金总额：</td>
				<td class="td-content"><input name="contract_info.allrent" class="mini-textbox"  readOnly  type="text" value="${requestScope['contract_info.allrent'] }" /></td>
				<td class="td-content-title">催款函/律师函发送时间：</td>
				<td class="td-content"><input name="contract_info.handate" class="mini-datepicker"  readOnly  type="text" value="${requestScope['contract_info.handate'] }" /></td>
		</tr>
		<tr class="tr-odd">
				<td class="td-content-title">首次逾期日：</td>
				<td class="td-content"><input name="contract_info.overduedate" class="mini-datepicker"  readOnly  type="text" value="${requestScope['contract_info.overduedate'] }" /></td>
				<td class="td-content-title">逾期金额：</td>
				<td class="td-content"><input name="contract_info.overduemoney" class="mini-textbox"   readOnly  type="text" value="${requestScope['contract_info.overduemoney'] }" /></td>
		</tr>
	</table>
</div>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("proj_gx_form");};
});
</script>
