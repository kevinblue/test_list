<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>催收明细</title>
<%
	//前缀
	String prefix = "${pageContext.request.contextPath}/table/getTableData.action?tracywindyRandom=1&decorate=none&xmlFileName=";
%>
<%@include file="/base/mini.jsp"%>
<!-- miniui扩展样式 -->
<link rel=stylesheet href="${pageContext.request.contextPath}/css/comm/miniui_ext.css"/>
<!-- miniui扩展JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/miniui_ext.js"></script>
</head>
<body>
<div style="width:100%; height:100%;overflow:scroll;">
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan=4>
					<jsp:include page="rent_collection_info_overduedetails.jsp"  />
				</td>
			</tr>
			<tr>
				<td colspan=4>
					<jsp:include page="rent_collection_info_visirecord.jsp"  />
				</td>
			</tr>
			<tr>
				<td colspan=4>
					<jsp:include page="rent_collection_info_rent.jsp" />
				</td>
			</tr>
			
			<tr>
				<td colspan=4>
					<jsp:include page="rent_collection_info_riskdisposition.jsp" />
				</td>
			</tr>
			<tr>
				<td colspan=4>
					<jsp:include page="rent_collection_info_riskrankadjust.jsp" />
				</td>
			</tr>
			<tr>
				<td colspan=4>
					<jsp:include page="rent_collection_info_contractstateadjust.jsp" />
				</td>
			</tr>
		</table>
	</div>

<script type="text/javascript">
</script>
</body>
</html>