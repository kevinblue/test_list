<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<div id="contract_cancel_reason_form" title="本次投放金额">
	<table class="fillTable" cellspacing="0" cellpadding="0" style="border: 1px solid #99CCFF;">
		<tr class="tr-even">
			<td class="td-content-title" width='12%'>本次投放编号：</td>
			<td class="td-content" width='38%'>
				<input id="put_info_putnum" name="put_info.putnum" class="mini-textbox" required="true" allowInput="false" value="<mini:param  name="put_info.putnum"/>"/>
			</td>
			<td class="td-content-title" width='12%'>合同总投放额：</td>
			<td class="td-content" width='38%'>
				<input id="putallequipamt" name="put_info.putallequipamt" class="mini-textbox" required="true" allowInput="false" value="<mini:param  name="put_info.putallequipamt"/>"/>
			</td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title" width='12%'>本次投放金额：</td>
			<td class="td-content" width='38%'>
				<input id="putcurequipamt" name="put_info.putcurequipamt" class="mini-textbox"   required="false" allowInput="false" value="<mini:param  name="put_info.putcurequipamt"/>"/>
			</td>
			<td class="td-content-title" width='12%'>本次投放占比：</td>
			<td class="td-content" width='38%'>
				<input id="putcurrate" name="put_info.putcurrate" class="mini-textbox" required="true" allowInput="false" value="<mini:param  name="put_info.putcurrate"/>"/><font class="required-tag">%</font>
			</td>
		</tr>
	</table>
</div>
<script language="javascript">
jQuery(function(){
	if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask==true){
		miniui_ext.disableFormFields("contract_cancel_reason_form");
	};
	//adjustTotalPrice();
});

function adjustTotalPrice(){
	var putallequipamt =mini.get("putallequipamt").getInputText().replace(/,/g,"");//合同总投放额
	//setformatvalue($mini("putcurequipamt"));
	var putcurequipamt = mini.get("putcurequipamt").getInputText().replace(/,/g,"");//本次投放金额
	var putcurrate = mini.get("putcurrate");//本次投放占比
	var value =!putallequipamt?  0 : decimal(putcurequipamt/putallequipamt*100, 6);
	putcurrate.setValue(value);
}
</script>