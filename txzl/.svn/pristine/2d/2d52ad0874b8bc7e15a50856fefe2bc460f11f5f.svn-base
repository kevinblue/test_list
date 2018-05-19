<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
alert('${param.CONTRACT_ID}');
var params = {};
params['xmlFileName'] = "/test/sqlserverTest.xml";
ajaxRequest({
	url: "${pageContext.request.contextPath}/table/getTableData.action",
	timeout:30*60*1000,
	params:params,
	success:function(response){
		alert('成功');
	},
	failure:function(response){
		alert('失败');
	}
});

</script>
</body>
</html>