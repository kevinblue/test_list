<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="debt_info_list" title="巡视基本信息">
	<fieldset class="fieldset-title">
		<table cellspacing="0" cellpadding="0" style="width:100%" class="fillTable">
			<tr class="tr-even">
				<td class="td-content-title" width="12%">小结分析：</td>
				<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3">
					<input style="width:75%;height:100px" id="debt_info_list.resfx" 
					name="debt_info_list.resfx" class="mini-textarea" 
					value="${requestScope['debt_info_list.resfx']}"/>
				</td>
			</tr>
		</table>
			<br/>
	</fieldset>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("debt_info_list");};
});
</script>
