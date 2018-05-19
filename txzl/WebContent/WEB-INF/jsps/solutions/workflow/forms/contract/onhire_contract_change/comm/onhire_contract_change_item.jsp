<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="contract_change_item" title="变更选项">
	<div class="mini-panel" title="变更选项" showCollapseButton="true"
		style="width: 100%;">
		<table class="fillTable" cellspacing="0" cellpadding="0"
			style="width: 100%" id="contract_base_info">
			<tr class="tr-odd">
				<td class="td-content-title" width="11%">实质性变更内容：</td>
				<td class="td-content" width="39%">
					<input id="proj_info.subchangecontent" width="70%"
					name="proj_info.subchangecontent" class="mini-textbox" allowInput="false"
					label="实质性变更内容"
					value="${requestScope['proj_info.subchangecontent']}"/>
				</td>
				<td class="td-content-title" width="12%">非实质性变更内容：</td>
				<td class="td-content" width="38%">
					<input id="proj_info.subnotchangecontent"
					name="proj_info.subnotchangecontent" class="mini-textbox" allowInput="false"
					label="非实质性变更内容" 
					value="${requestScope['proj_info.subnotchangecontent']}"/>
				</td>
			</tr>
		</table>
	</div>
</div>
<script>
	jQuery(function() {
		
	});
	jQuery(function() {
		if ('${param.isView}' == 'true' || isViewHistoryTask == true) {
			miniui_ext.disableFormFields("contract_change_item");
		}
		;
	});
</script>