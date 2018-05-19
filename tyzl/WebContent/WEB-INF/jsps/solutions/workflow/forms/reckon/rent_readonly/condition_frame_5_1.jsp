
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
	#businessconditionFormReadonly .td-content-title{
		width:160px;
	}
	#businessconditionFormReadonly .td-content{
		width:160px;
	}
</style>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="businessconditionFormReadonly">
<jsp:include page="../rent_common/condition_content_5_1.jsp"></jsp:include>
</div>
<script type="text/javascript">
var businessFormReadOnly = new mini.Form("businessconditionFormReadonly");
jQuery(function(){
	//inputs[d].className=inputs[d].className+" element-readonly";
	var form = new mini.Form("businessconditionFormReadonly");
	var fields = form.getFields();
	for(var index =0;index<fields.length;index++){
		 //判断是否是下拉框控件，是则同时把text属性传入后台
			fields[index].disable();
	}
	//测算租金or测算年利率
	var rentorrate = mini.get('rentorrate').getValue();
	if(rentorrate == 'rate'){
		$('#testrent').hide();
		$('#testrate').show();
	}else{
		$('#testrent').show();
		$('#testrate').hide();
	}
	$('#calculateButton').hide();
	updateInputThousandReadOnly();
});

//刷新页面当中的所有的输入框的钱数为千分位
function updateInputThousandReadOnly(){
	var fields = businessFormReadOnly.getFields();
	for(var index =0;index<fields.length;index++){
		 //判断是否是下拉框控件，是则同时把text属性设置combobox
		if(fields[index].vtype == 'double'){
			fields[index].setValue(formatNumberThousand(fields[index].getValue()));
		}
	}
}

</script>

