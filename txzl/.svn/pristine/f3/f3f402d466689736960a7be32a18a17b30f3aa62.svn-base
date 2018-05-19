<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="proj_planadvice_form" title="处理预案建议">
<div class="mini-panel"   showCollapseButton="true" style="width:100%;">
	<table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
		<tr class="tr-odd">
	             <td class="td-content-title" width="160px">处理预案/建议：</td>
	             <td colspan="3" style="padding-top: 5px;padding-bottom: 5px;"><input style="width:74%;height: 80px;" label="处理预案/建议" name="accident_info.planadvice" value="${requestScope['accident_info.planadvice'] }" class="mini-textarea"  type="text" >  </td>
	       </tr>
		<tr class="tr-odd">
	             <td class="td-content-title" >历史重大事项内容：</td>
	             <td colspan="3" style="padding-top: 5px;padding-bottom: 5px;"><input style="width:74%;height: 80px;" label="历史重大事项内容" name="accident_info.thinghiscontent" value="${requestScope['accident_info.thinghiscontent'] }" class="mini-textarea"  type="text" >  </td>
	       </tr>
		<tr class="tr-even">
	             <td class="td-content-title" >历史重大事项时间：</td>
	             <td colspan="3" style="padding-top: 5px;padding-bottom: 5px;"><input   label="历史重大事项时间" name="accident_info.thinghisdate" value="${requestScope['accident_info.thinghisdate'] }" class="mini-datepicker"  type="text" >  </td>
	       </tr>
		<%-- <tr class="tr-odd">
	             <td class="td-content-title" width="12%">重大事项内容：</td>
	             <td colspan="3" style="padding-top: 5px;padding-bottom: 5px;"><input style="width:88%;height:50px" label="重大事项内容" name="accident_info.thingcontent" value="${requestScope['accident_info.thingcontent'] }" class="mini-textarea"  type="text" >  </td>
	       </tr> --%>
		<tr class="tr-even">
	             <td class="td-content-title" >其他说明事项：</td>
	             <td colspan="3" style="padding-top: 5px;padding-bottom: 5px;"><input style="width:74%;height: 80px;" label="其他说明事项" name="accident_info.thingother" value="${requestScope['accident_info.thingother'] }" class="mini-textarea"  type="text" >  </td>
	       </tr>
	   <tr class="tr-odd">
				<td class="td-content-title" width="12%">资产经理：</td>
				<td class="td-content"><input name="accident_info.assetproj" id="accident_info.assetproj" class="mini-textbox" label="资产经理"   type="text" value="${requestScope['accident_info.assetproj']}"></td>
				<td class="td-content-title" width="12%">填报时间：</td>
				<td class="td-content" ><input name="accident_info.reportdate" id="proj_date" class="mini-datepicker" label="填报时间"    type="text" value="${requestScope['accident_info.reportdate']}"></td>
	   </tr>
	</table>
</div>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("proj_planadvice_form");};
});
</script>
