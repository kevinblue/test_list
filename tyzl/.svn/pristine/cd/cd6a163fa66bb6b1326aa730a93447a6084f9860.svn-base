<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<div id="contract_patrol_plan_form" title="客户巡视计划表单">
		<table cellspacing="0" cellpadding="0" style="width:100%" class="fillTable">
			<tr class="tr-odd">
				<td class="td-content-title" width="11%">巡视重点：</td>
				<td class="td-content" width="39%"><input id="contract_patrol_info.patrolpoint" name="contract_patrol_info.patrolpoint" class="mini-textbox" value="<mini:param  name="contract_patrol_info.patrolpoint"/>"  required="true" label="巡视重点"/></td>
				<td class="td-content-title" width="12%">巡视时间：</td>
				<td class="td-content" width="38%"><input id="contract_patrol_info.patroldate" name="contract_patrol_info.patroldate" class="mini-datepicker" allowInput="false" value="<mini:param  name="contract_patrol_info.patroldate"/>" required="true" label="巡视时间"/></td>
			</tr>
			<tr class="tr-even">
				<td class="td-content-title" width="11%">市场估价：</td>
				<td class="td-content" width="39%"><input id="contract_patrol_info.marketvaluation" name="contract_patrol_info.marketvaluation" class="mini-textbox" value="<mini:param  name="contract_patrol_info.marketvaluation"/>" required="true" label="市场估价"/></td>
			</tr>
		</table>
</div>
<script type="text/javascript">
jQuery(function(){
	if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("contract_patrol_plan_form");};
});
</script>
