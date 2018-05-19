<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.tenwa.kernal.utils.ResourceUtil"%>
<%@ page session="true"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix='spring' uri='/WEB-INF/tlds/spring.tld' %>
<% 
String isVerifyCheck = ResourceUtil.getConfigValue("login_verification_code_check");
%>
<c:set scope="request" var="isVerifyCheck" value="<%=isVerifyCheck %>"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
     <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>天信融资租赁业务管理系统-Eleasing</title>
	<script type="text/javascript"	src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/cloud.js"></script> --%>
	<!--css sheet-->
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		//alert('${isVerifyCheck}');
		if (window != window.top) {
			window.top.location.href = "${pageContext.request.contextPath}/acl/index.acl"; 
		}
		if("${sessionScope.login_username}" != ""){
			window.location.href = "${pageContext.request.contextPath}/acl/index.acl";
		}
		function MM_swapImgRestore() { //v3.0
			var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
		}
		function MM_preloadImages() { //v3.0
			var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
			var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
			if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
			var userInput	= document.getElementById("j_username");
			var userPassword = document.getElementById("j_password");
			userInput.value = getCookie("SECURITY_USER_NAME_SAVE_KEY");
			if('true' == '${param.login_password_error}') {
				userPassword.focus();
			} else {
				userInput.focus();
			}
		}
		
		function MM_findObj(n, d) { //v4.01
			var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
			d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
			if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
			for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
			if(!x && d.getElementById) x=d.getElementById(n); return x;
		}
		
		function MM_swapImage() { //v3.0
			var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
			if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
		}
		function setCookie(name,value) { //两个参数，一个是cookie的名子，一个是值
			var Days = 30; //此 cookie 将被保存 30 天
			var exp   = new Date(); //new Date("December 31, 9998");
			exp.setTime(exp.getTime() + Days*24*60*60*1000);
			document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
		}

		function getCookie(name) {//取cookies函数
			var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
			if(arr != null) return unescape(arr[2]); return "";
		}
		function login() {
			var inputUsername = document.getElementById("j_username");
			username = inputUsername.value.replace(/(^\s{1,})|(\s{1,}$)/gim,"");
			inputUsername.value = username;
			setCookie("SECURITY_USER_NAME_SAVE_KEY",username);
			if(!username){
				alert("用户名不能为空！");
				return false;
			}
			if('${isVerifyCheck}' == 'true'){
			 	var validatenum = $('#validatenum').val();
			    if(!validatenum){
			    	 alert("验证码不能为空！");
			    	 return false;
			    }/* else{
			    	var numReg = /[\d]{1,2}/;
			    	if(!numReg.test(validatenum)){
			    		alert('请你输入正确格式的验证码！');
			    		return false;
			    	}
			    } */
			}
			document.getElementById("login_form_").submit();
		}
		document.onkeyup = loginAction;
		function loginAction(evt) {
			var e=window.event||evt;
			if(e.keyCode==13) {
				login();
			}
		}
		
		jQuery(function (){
			var message="";
			if('${param["login_username_error"]}' == 'true'){
				message="用户名";
			}
			if('${param["login_password_error"]}' == 'true'){
				if(message!=""){message=message+"和";}
				message="密码";
			}
			if(message!=""){
			    alert(message+"错误！");
			}
			if('${requestScope["login_verification_error"]}' == 'true'){
				alert("请你输入正确的验证码！");
			}
			if('${requestScope["login_usermac_error"]}' == 'true'){
				alert("请用自己的计算机登陆此账号！");
			}
			
		});
		//刷新验证码
		function reloadImg(url){
		    document.getElementById('img1').src ="${pageContext.request.contextPath}/validatecode/getValidatecode.acl?"+Math.random();
		}
	</script>
</head>
<body onload="MM_preloadImages();"  style="background-color: #1c77ac;">
	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
		<div class="logintop" style="height:47px;">
			<span>欢迎登录融资租赁业务管理系统</span>
		</div>
		<div class="loginbody">
			<div class="loginbox" id='id_loginBox'>
				<form name="login_form_" id="login_form_" action="<c:url value='j_spring_security_check'/>" method="post" >
					<div style="padding-left: 280px; padding-top: 70px;">
						<div style="margin-top: 20px;">
							<input type="text" class="loginuser" id="j_username" name="j_username">
						</div>
						<div style="margin-top: 20px;">
							<input type="password" class="loginpwd" id="j_password" name="j_password">
						</div>
						<div style="margin-top: 20px;  padding-right: 70px;">
							<!-- 如需验证码，请在此处添加内容 -->
							<c:if test="${true eq isVerifyCheck }">
								<div style="margin-top: 20px;">
									<li class="logincode_li">
										<img  src="${pageContext.request.contextPath}/images/yan.png" >
										<input name="validatenum" id="validatenum" type="text"  maxlength="4">
										<img id="img1" src="${pageContext.request.contextPath}/validatecode/getValidatecode.acl" onclick="reloadImg()" width="90" height="25"/>&nbsp;
									</li>
								</div> 
							</c:if>
							<input type="button" onclick="javascript:login();" value="登录" class="loginbtn" name="">
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="loginbm">版权所有 © 2016 天信国际租赁有限公司</div>
	</div>
</body>
</html>