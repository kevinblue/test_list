<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix='spring' uri='/WEB-INF/tlds/spring.tld' %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>融资租赁管理系统-V5.0</title>
		 <!--css sheet-->
	   <style type="text/css">
				          /* CSS Document*/
			body,html{ margin:0px; padding:0px; font-size:12px;font-family:微软雅黑,serif;color:#8e8d8d;}
			*{ margin:0px; padding:0px;}
			.clear { clear:both;}
			body{ z-index:-1;background:url(images/bg.png) repeat;}
			#login_form_{background:url(images/jinmao.gif) repeat-x 20px 20px;}
			.login_container{width: 255px; display: block;margin: 150px 0 0 50%;background: #f7f7f7;border: 1px solid #d5d5d5; border-bottom:1px solid #d5d5d5 !important;border-bottom:3px solid #ddd;-webkit-border-radius: 5px;-moz-border-radius: 5px;border-radius: 5px;box-shadow:  0px 0px 2px #dadada, inset 0px -3px 0px #e6e6e6;padding: 16px 28px 23px !important;padding: 16px 28px 0px;}
			.login_container h1 {width: 255px;font-size:22px;margin-bottom: .4em;color: #263849;text-shadow: 1px 1px 0px #fff;}
			.login_container h1 span{font-size:16px;}
			.login_container p {width: 255px;margin: 1em auto;	text-align:left;line-height: 19px; 	text-shadow: 1px 1px 0px #fff;}
			
			.login-fields {}
			input {color: #8e8d8d;line-height:36px;line-height:36px\9;_line-height:36px;height:36px;border: 1px solid #cccccc;-webkit-border-radius: 3px;-moz-border-radius: 3px;border-radius: 3px;padding: 0 0 0 40px !important;padding: 9px 15px 8px 43px;_padding: 0 0 0 43px;background-color: #fdfdfd;width: 215px !important;width: 255px;display: block;margin: 0 0 1.25em 0;	box-shadow: inset 1px 1px 2px #f1f1f1;}
            .username_field { background: url(images/user.png) 0 50% no-repeat; }
            .password_field { background: url(images/password.png) 0 50% no-repeat; }
			.login_btn {width: 255px;float:left;}
			.login_btn a {background:url(images/login_btn.gif) no-repeat;float:right; cursor:pointer; padding:0 19px;font:bold 16px/36px 微软雅黑,serif; color:#FFF;text-shadow: 0px -1px 0px rgba(0,0,0,.25);}
			.login-extra {display: block; width:100%; margin: 1.5em auto;color: #263849;	text-align: center;	line-height: 19px; 	text-shadow: 1px 1px 0px #fff; }
	   </style>
		<script type="text/javascript">
		<!--
		if (window != window.top) 
		{
		   window.top.location.href = "${pageContext.request.contextPath}/acl/index.acl"; 
		}
		function MM_swapImgRestore() { //v3.0
		  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
		}
		function MM_preloadImages() { //v3.0
		    var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
		    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
		    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
            var userInput    = document.getElementById("j_username");
            var userPassword = document.getElementById("j_password");
		    userInput.value = getCookie("SECURITY_USER_NAME_SAVE_KEY");
			if('true' == '${param.login_password_error}')
			{
				userPassword.focus();
		    }else{
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
		function setCookie(name,value)//两个参数，一个是cookie的名子，一个是值
		{
		 var Days = 30; //此 cookie 将被保存 30 天
		 var exp   = new Date(); //new Date("December 31, 9998");
		 exp.setTime(exp.getTime() + Days*24*60*60*1000);
		 document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
		}

		function getCookie(name)//取cookies函数       
		{
		    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
		    if(arr != null) return unescape(arr[2]); return "";
		}
		function login()
		{
			var inputUsername = document.getElementById("j_username");
			username = inputUsername.value.replace(/(^\s{1,})|(\s{1,}$)/gim,"");
			inputUsername.value = username;
			setCookie("SECURITY_USER_NAME_SAVE_KEY",username);
			if(!username){
			   alert("用户名不能为空！");
               return false;
			}
			document.getElementById("login_form_").submit();
		}
		document.onkeypress= loginAction;
		function loginAction(evt)
		{
			var e=window.event||evt;
			if(e.keyCode==13)
			{
			 	login();
		    }
		}
		//-->
     </script>
</head>
<body onload="MM_preloadImages();">
        <!--
        <a target="_self" href="${pageContext.request.contextPath}/acl/changeLocale.acl?locale=zh_CN">中文</a>
        <a target="_self" href="${pageContext.request.contextPath}/acl/changeLocale.acl?locale=en_US">英文</a>
		-->
		<form name="login_form_" id="login_form_" action="<c:url value='j_spring_security_check'/>" method="post" >
			<div class="login_container">
                    <h1>融资租赁管理系统 <span>V5.0</span></h1>
                    <p>Sign in using your registered account:</p>
					<div class="login_fields">
						<div><input type="text" name="j_username" id='j_username' class="username_field"/></div>
						<div><input type='password' name='j_password' id='j_password' class="password_field"/></div>
					</div>
			        <div class="login_btn">
			            <a onclick="javascript:login()">登 录</a>
			        </div>
			<div class="clear"></div>
            </div>
            
            <div class="login-extra">
	                版权所有  © CHINA  GROUP CO., LTD. All Rights Reserved
            </div>
		</form>
	 <script type="text/javascript" >
		<!--
		 var loadInfo = function(){
			if('empty' == '${param.checkUniqueComputerPass}')
			{
				alert("未检测到该机器的硬盘号，请检查是否已安装所需浏览器插件，或者没有加入受信站点！");
		    }
			if('false' == '${param.checkUniqueComputerPass}')
			{
				alert("请使用已注册的电脑登录（${param.checkUserName}）");
		    }
			if('true' == '${param.login_username_error}')
			{
				alert("您输入的用户不存在，请重新输入！");
		    }
			if('true' == '${param.login_password_error}')
			{
				alert("您输入的密码有误，请重新输入！");
		    }
		 };
		 setTimeout("loadInfo()",200);
		//-->
	 </script>
	</body>
</html>