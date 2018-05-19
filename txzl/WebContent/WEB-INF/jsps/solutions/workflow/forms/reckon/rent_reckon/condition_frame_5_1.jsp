<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../rent_common/cal_js_5_1.jsp"></jsp:include>
<style>
	#businessconditionForm .td-content-title{
		width:160px;
	}
	#businessconditionForm .td-content{
		width:160px;
	}
	
</style>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <jsp:include page="../rent_common/condition_content_5_1.jsp"></jsp:include>
<script type="text/javascript">
var businessForm = new mini.Form("businessconditionForm");
jQuery(function(){
	startReloadConditionContent();
});
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){
		miniui_ext.disableFormFields("businessconditionForm");
		$('#calculateButton').hide();
	}
});
</script>