
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	#businessconditionFormCompare .td-content-title{
		width:160px;
	}
	#businessconditionFormCompare .td-content{
		width:160px;
	}
</style>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <div id="businessconditionFormCompare">
	    <jsp:include page="../rent_common/condition_content_5_1_framework.jsp"></jsp:include>
	</div>
<script type="text/javascript">
	var form = new mini.Form("businessconditionFormCompare");
jQuery(function(){
	//inputs[d].className=inputs[d].className+" element-readonly";
	var fields = form.getFields();
	for(var index =0;index<fields.length;index++){
		fields[index].disable();
	}
	updateInputThousand();
});


//刷新页面当中的所有的输入框的钱数为千分位
function updateInputThousand(){
	var fields = form.getFields();
	for(var index =0;index<fields.length;index++){
		 //判断是否是下拉框控件，是则同时把text属性设置combobox
		if(fields[index].vtype == 'double'){
			fields[index].setValue(formatNumberThousand(fields[index].getValue()));
		}
	}
}
</script>
