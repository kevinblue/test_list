<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="patrol_result_info" title="十、本次检查评估结论">
	<fieldset class="fieldset-title">
		<table cellspacing="0" cellpadding="0" style="width:100%" class="fillTable">
			<tr class="tr-even">
				<td class="td-content-title" width="12%">本次结论：</td>
				<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3">
					<input style="width:75%;height:100px" id="contract_patrol_info.resadvice" 
					name="contract_patrol_info.resadvice" class="mini-textarea" 
					value="${requestScope['contract_patrol_info.resadvice']}"/>
				</td>
			</tr>
			
			<tr class="tr-odd">
				<td class="td-content-title" >关注点：</td>
				<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3">
					<input style="width:75%;height:100px" id="contract_patrol_info.curassettype" 
					name="contract_patrol_info.curassettype" class="mini-textarea" 
					value="${requestScope['contract_patrol_info.curassettype']}"/>
				</td>
			</tr>
			<tr class="tr-even">
				<td class="td-content-title" width="12%">管理措施建议：</td>
				<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3">
					<input style="width:75%;height:100px" id="contract_patrol_info.qassettype" 
					name="contract_patrol_info.qassettype" class="mini-textarea" 
					value="${requestScope['contract_patrol_info.qassettype']}"/>
				</td>
			</tr>
		</table>
			<br/>
	</fieldset>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("patrol_result_info");};
});
</script>
