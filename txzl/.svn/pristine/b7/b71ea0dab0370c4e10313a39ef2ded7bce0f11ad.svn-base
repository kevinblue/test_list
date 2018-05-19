<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="guaratee_info_list" title="担保人情况">
	<fieldset class="fieldset-title">
		<table cellspacing="0" cellpadding="0" style="width:100%" class="fillTable">
			<tr class="tr-even">
				<td class="td-content-title" width="12%">经营投资情况：</td>
				<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3">
					<input style="width:75%;height:100px" id="contract_patrol_info.investSituation" 
					name="contract_patrol_info.investSituation" class="mini-textarea" 
					value="${requestScope['contract_patrol_info.investSituation']}"/>
				</td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title" width="12%">融资/负债情况：</td>
				<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3">
					<input style="width:75%;height:100px" id="contract_patrol_info.debtinfo" 
					name="contract_patrol_info.debtinfo" class="mini-textarea" 
					value="${requestScope['contract_patrol_info.debtinfo']}"/>
				</td>
			</tr>
			<tr class="tr-even">
				<td class="td-content-title" width="12%">担保人分析：</td>
				<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3">
					<input style="width:75%;height:100px" id="contract_patrol_info.guarantorAnalysis" 
					name="contract_patrol_info.guarantorAnalysis" class="mini-textarea" 
					value="${requestScope['contract_patrol_info.guarantorAnalysis']}"/>
				</td>
			</tr>
		</table>
			<br/>
	</fieldset>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("guaratee_info_list");};
});
</script>
