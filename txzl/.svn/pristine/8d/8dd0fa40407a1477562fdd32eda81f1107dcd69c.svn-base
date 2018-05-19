<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div id="drawal_cost_detail" title="撤诉情况">
	<table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
		<tr class="tr-odd">
				<td class="td-content-title"  width="160px">撤诉时间：</td>
	             <td class="td-content">
			             <input name="drawalDate" class="mini-datepicker" label="撤诉时间" allowInput="false" value="${requestScope['drawalDate']}" type="date" required="true"/>
			      </td>
			</tr>
		<tr class="tr-odd">
			<td class="td-content-title">撤诉原因：</td>
			<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3">
			<input maxLength="1000" emptyText="请输入撤诉原因" style="width:74%;height: 80px;" value="${requestScope['drawalreason']}" name="drawalreason" class="mini-textarea" required="true" label="撤诉原因" /></td>
		</tr>
	</table>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){
		miniui_ext.disableFormFields("drawal_cost_detail");
		};
});
</script>
