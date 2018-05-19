<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="asset_base_info_form" title="基本信息">
	<div class="mini-panel" title="基本信息" showCollapseButton="true" style="width:100%;">
		<table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%" id="contract_base_info">
			<tr class="tr-even">
				<td class="td-content-title" width="12%">资产分类流水号：</td>
				<td class="td-content" width="38%"><input name="assetnumber" id="assetnumber" class="mini-textbox" label="资产分类编号" readOnly type="text" value="${requestScope['assetnumber']}"></td>
				<td class="td-content-title" width="12%">分类日期：</td>
				<td class="td-content" width="38%"><input name="curdate" id="curdate" class="mini-textbox" label="分类时间" readOnly type="text" value="${requestScope['curdate']}"></td>
			</tr>
			
		</table>
	</div>
</div>
<input name="fiveids" id="fiveids" type="hidden"  value="${requestScope['fiveids'] }"/>
<script >
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){
		miniui_ext.disableFormFields("asset_base_info_form");
	};
});
</script>