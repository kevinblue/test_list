<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="bq_content_form" title="保全信息">
	<table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
		<tr class="tr-odd">
				<td class="td-content-title"  width="158px">保全情况：</td>
	             <td class="td-content" >
					<input name="law_approval.ispreserve" id ="law_approval.ispreserve"  class="mini-combobox" 
					   data="[{text:'是'},{text:'否'}]" 
						type="text" value="${requestScope['law_approval.ispreserve']}"
						textField="text" valueField="text" >
				</td>
				<td class="td-content-title"  width="130px">保全日期：</td>
	             <td class="td-content">
			             <input name="law_approval.preservedate" class="mini-datepicker" label="保全日期"   allowInput="false"  
						               value="${requestScope['law_approval.preservedate'] }"  />
			      </td>
		</tr>
		<tr class="tr-odd">
				<td class="td-content-title" >保全到期日：</td>
	             <td class="td-content">
			             <input name="law_approval.maturitydate" class="mini-datepicker" allowInput="false"  value="${requestScope['law_approval.maturitydate'] }"  label="法务编号"   />
			      </td>
				<td class="td-content-title" >解除保全日：</td>
	             <td class="td-content">
			             <input name="law_approval.reliefpreservation" class="mini-datepicker"  label="解除保全日"   allowInput="false" 
						               value="${requestScope['law_approval.reliefpreservation'] }"  />
			      </td>
		</tr>
	</table>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("bq_content_form");};
});
</script>
