<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="cal_js_5_1.jsp"></jsp:include> 
<style>
	#businessconditionForm .td-content-title{
		width:160px;
	}
	#businessconditionForm .td-content{
		width:160px;
	}
	
</style>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <jsp:include page="condition_content_5_1O.jsp"></jsp:include>
<script type="text/javascript">
var businessForm = new mini.Form("businessconditionForm0");
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){
		miniui_ext.disableFormFields("businessconditionForm0");
		$('#calculateButton').hide();
	}
});

</script>