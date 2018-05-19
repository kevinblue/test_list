<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c'   uri='/WEB-INF/tlds/c.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商务条件查看</title>
<jsp:include page="/WEB-INF/jsps/solutions/workflow/jbpm-core/getWorkflowCommonCssAndJs.jsp"></jsp:include>

<script language="javascript">
var loadMask;
jQuery(function() {
	isSubTable=true;
	var all_width  =document.documentElement.clientWidth;
	var all_height =  document.documentElement.clientHeight;
	var oldCurrentPageClientWidth = all_width;
	var oldCurrentPageClientHeight = all_height;
	formHeight = 0;
	formWidth = 0;//formWidth
	var containerWidthAdd = 12;
	var containerHeightAdd = 115;
	formHeight = all_height-containerHeightAdd-10;
	formWidth = all_width-containerWidthAdd-20;
});
	var isViewHistoryTask =true;//只读多行控件
</script>
</head>
<body style="overflow: auto;">
<c:set var="isWhere" value="${requestScope['isWhere']}"/>
<c:if test="${'false' eq isWhere}">
	传入参数不正确
</c:if>
<c:if test="${'true' eq isWhere}">
	<c:set var="isRs" value="${requestScope['isRs']}"/>
	<c:if test="${'false' eq isRs}">
		通过传入参数没有查询到对应的合同!
	</c:if>
	<c:if test="${'true' eq isRs}">
		<jsp:include page="main_5_1.jsp"/>
	</c:if>
</c:if>
</body>
<script language="javascript">
//只读商务条件
formJSPInputHideOrShow("id_submitFrameworkConditionForm","",0);
</script>
</html>