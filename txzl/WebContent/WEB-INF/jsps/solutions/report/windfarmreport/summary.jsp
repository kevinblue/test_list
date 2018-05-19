<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div id="proj_change_explain" title="运行报告结论">
	    <table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
		         <tr class="tr-odd">
					<td class="td-content-title" style="width:110px">结论/关注点：</td>
					<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input style="width:72%;height: 80px;" id="4.1" name="conclusion" class="mini-textarea" allowInput="true"  label="结论/关注点" value="${requestScope['conclusion']}"/></td>
				</tr>
				<tr class="tr-even" style="width:110px">
					<td class="td-content-title">改进措施与建议：</td>
					<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3"><input style="width:72%;height: 80px;" id="4.2" name="suggestions" class="mini-textarea" allowInput="true"  label="改进措施与建议	" value="${requestScope['suggestions']}"/></td>
				</tr>
   	    </table>
</div>
<script language="javascript">
	 
	if('${param.isView}' == 'true'||isViewHistoryTask==true){ 
         miniui_ext.disableFormFields("proj_change_explain");
    }

</script>
