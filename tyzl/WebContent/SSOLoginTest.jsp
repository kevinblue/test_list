<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="org.springframework.security.authentication.encoding.SSOEncoder"%>
<%@page import="java.text.SimpleDateFormat"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>单点登录测试</title>
</head>
<body>
    <%
       String userNameRealValue = "Administrator";
       java.util.Map<String,String> encodeInfo = SSOEncoder.getInstance().setEncodeSSOUserName(userNameRealValue);
       String userNameParamNameEncode =  encodeInfo.get("userNameParamNameEncode");
       String userNameEncode          =  encodeInfo.get("userNameEncode");
       String isSSOParamNameEncode    =  encodeInfo.get("isSSOParamNameEncode");
       String isSSOEncode             =  encodeInfo.get("isSSOEncode");
    %>
	<form id="id_loginSSOForm" action="http://www.deewinfl.cn/deewinfl/acl/index.acl" target="_blank" >
	     <input type="hidden" name="<%=userNameParamNameEncode %>" value="<%=userNameEncode %>"/>
	     <input type="hidden" name="<%=isSSOParamNameEncode %>" value="<%=isSSOEncode %>">
	</form> 
	<%=("http://www.deewinfl.cn/deewinfl/acl/index.acl?"+userNameParamNameEncode+"="+userNameEncode+"&"+isSSOParamNameEncode+"="+isSSOEncode) %>
</body>
</html>