<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix='spring' uri='/WEB-INF/tlds/spring.tld' %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>天信融资租赁业务系统</title>
		<%
			String oamFlag = request.getHeader("OAM_REMOTE_USER");
		%>
		
	</head>	
	<body>
		<form name="login_form_" id="login_form_"  action="<c:url value='j_spring_security_check'/>" method="post" >
				<input type="hidden"  id="j_username" name="j_username"/>
				<input type="hidden"  id="j_password" name="j_password"/>
		</form>
	</body>
	
	<script type="text/javascript">
			var oamFlag = '<%=oamFlag%>';
			if(oamFlag != 'null' && oamFlag ){
				document.getElementById("login_form_").submit();
			}else{
				window.location.href="${pageContext.request.contextPath}/login_old.jsp";
			}
    	 </script>
</html>