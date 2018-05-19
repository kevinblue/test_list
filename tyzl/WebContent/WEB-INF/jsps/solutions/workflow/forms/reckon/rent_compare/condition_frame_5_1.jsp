<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	#businessconditionForm .td-content-title{
		width:160px;
	}
	#businessconditionForm .td-content{
		width:160px;
	}
	
</style>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="condition_content_5_2.jsp"></jsp:include>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){
		miniui_ext.disableFormFields("comparebusinessconditionForm");
	}
});
</script>