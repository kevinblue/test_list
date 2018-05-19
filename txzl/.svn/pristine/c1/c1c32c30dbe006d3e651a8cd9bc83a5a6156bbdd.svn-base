<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input name="contract_change_info.id" id="contract_change_info.id" type="hidden" value="${requestScope['contract_change_info.id']}" />
<div id="proj_occus_form" title="预警出险原因及风险分析">
<div class="mini-panel"   showCollapseButton="true" style="width:100%;">
	<table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
		<tr class="tr-odd">
	             <td class="td-content-title" width="160px">预警/出险原因：</td>
	             <td colspan="3" style="padding-top: 5px;padding-bottom: 5px;"><input style="width:74%;height: 80px;" label="预警/出险原因" name="accident_info.accidentres" value="${requestScope['accident_info.accidentres'] }" class="mini-textarea"  type="text" >  </td>
	       </tr>
		<tr class="tr-even">
	             <td class="td-content-title" width="160px">风险因素分析：</td>
	             <td colspan="3" ><input style="width:74%;height: 80px;" label="风险因素分析" name="accident_info.factor" value="${requestScope['accident_info.factor'] }" class="mini-textarea"  type="text" >  </td>
	       </tr>
	</table>
</div>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("proj_occus_form");};
});
</script>
