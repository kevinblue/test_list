<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input name="contract_change_info.id" id="contract_change_info.id" type="hidden" value="${requestScope['contract_change_info.id']}" />
<div id="contract_change_info_form" title="运行状况分析">
	<table cellspacing="0" cellpadding="0" style="width:99%;border: 1px solid #99CCFF;" class="fillTable">
		<tr class="tr-odd">
			<td class="td-content-title" style="width:99px">运行状况分析：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input style="width:70%;height: 80px;" id = "3.1" name="statusanalysis" class="mini-textarea" allowInput="true"  label="运行状况分析" value="${requestScope['statusanalysis']}"/></td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title" style="width:99px">故障数据分析：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input style="width:70%;height: 80px;" id="3.2" name="dataanalysis" class="mini-textarea" allowInput="true" label="故障数据分析" value="${requestScope['dataanalysis']}"/></td>
		</tr>
		<tr class="tr-odd">
			<td class="td-content-title" style="width:99px">损失电量分析：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input style="width:70%;height: 80px;" id="3.3" name="lossanalysis" class="mini-textarea" allowInput="true"  label="损失电量分析" value="${requestScope['lossanalysis']}"/></td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title" style="width:99px">功率曲线分析：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input style="width:70%;height: 80px;" id="3.4" name="curveanalysis" class="mini-textarea" allowInput="true"  label="功率曲线分析" value="${requestScope['curveanalysis']}"/></td>
		</tr>
	</table>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("contract_change_info_form");};
});
</script>
