<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix='c'   uri='/WEB-INF/tlds/c.tld' %>
<%@include file="/base/mini.jsp"%>
<%
	String actionParametersName = request.getAttribute("actionParametersName") + "";
	String actionParameters = request.getAttribute("actionParameters")+ "";
	String url = request.getAttribute("pageUrl")+ "";
	String prefixUrl = request.getContextPath();
	if(!url.matches("((?i)http).*?")){
		url = prefixUrl + url;
	}
	if(null != actionParametersName && 0 < actionParametersName.length()){
		if(url.indexOf("?") < 0){
			url += "?";
		}
		String [] params = actionParametersName.split("\\|");
		String [] paramValues = actionParameters.split("\\|");
		for(int i = 0;i<params.length ; i++){
			url += "&" + params[i]+"=" + paramValues[i];
		}
	}
	System.out.println(url);
%>
<c:if test="${empty needjs || (needjs eq '1')}">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>

</head>
<body>

</c:if>	
	<div id='page_${id}' class="mini-panel" title="${title}"  style="width: 100%;">
		<div id="page_content_${id}" style="width:100%;height:90%;overflow:hidden;">
			<iframe style="overflow:auto;border-width:0px;display:block;width:100%;height:100%" name="page_iframe_${id}" id="id_page_iframe_${id}" frameborder="0" src="<%=url%>"></iframe>
		</div>
	</div>
	
<c:if test="${empty needjs || (needjs eq '1')}">
</body>
</html>
</c:if>