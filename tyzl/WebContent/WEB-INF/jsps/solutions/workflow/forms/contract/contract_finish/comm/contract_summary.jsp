<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<div id="contract_summary" title="合同总结">
	<table class="fillTable" cellspacing="0" cellpadding="0" style="border: 1px solid #99CCFF;">
		<tr class="tr-even">
			<td class="td-content-title" width="11%">合同总结：</td>
			<td class="td-content" width="85%" colspan="3">
				<input style="width:74%;height: 100px;" id="contract_end_info.incomememo" label="合同总结" name="contract_end_info.incomememo"   value="<mini:param  name="contract_end_info.endmemo"/>"  required="true" label="收益性总结" class="mini-textarea" emptyText="请输入。。。" /> 
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
jQuery(function(){
	if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("contract_summary");};
});
</script>