<%@ taglib prefix='auth' uri='/WEB-INF/tlds/security.tld' %>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	   <title>管理平台-V3.0</title>
	   <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	   <%@ include file="main_include_js.jsp" %>
	   	<script type="text/javascript" src="${pageContext.request.contextPath}/js/accordion/prototype.lite.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/accordion/moo.fx.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/accordion/moo.fx.pack.js"></script>
	</head>
			<body>
       		  <div id="id_loadMaskContainer" style="display:block;">
					<div id="loading">
					    <div class="loading-indicator">页面加载中   请稍等...</div>
					</div>
			  </div>
			  <script type="text/javascript">
			     jQuery("#id_loadMaskContainer").css("height",all_height);
			  </script>
			  <!--页面的头部包括头部logo背景图片和菜单栏-->
	          <div class="header" style="overflow:hidden;" >
	              <!--页面的头部包括头部logo背景图片-->
			      <div class="main_topbg" id='id_mainPage_header'>
						<div class="main_logo"><a>JMCS</a></div>
						<div class="main_logo_img"></div>
						<div class="main_time" style="display:none;">
								欢迎您：<font color="#FF9238">${sessionScope.login_realname}</font>&nbsp;|
						    <a href="javascript:void(0);" onClick="javascript:showModifyPasswordWindow();" title="用户信息修改"><img src="${pageContext.request.contextPath}/menuIcons_w/cogs.png" style="top:3px;margin-right:5px;position:relative;" border="0" />修改</a>
						    <a href="javascript:void(0);" onClick="javascript:showDelegateWindow();" title="委托"><img src="${pageContext.request.contextPath}/menuIcons_w/key.png" style="top:3px;margin-right:5px;position:relative;" border="0" />委托</a>
					        <a href='${pageContext.request.contextPath}/j_spring_security_logout' style='text-decoration: none;' title="注销"><img style="top:3px;margin-right:5px;position:relative;" src="${pageContext.request.contextPath}/menuIcons_w/on-off.png" border="0" />注销</a><br>
						  今天是 <span id="dateZone" ></span>
						  <auth:authorize ifAnyGranted="ROLE_ADMIN">|
						    当前在线<a style="text-decoration:none;" href="javascript:void(0);" onClick="javascript:showOnlineUsersWindow();">&nbsp;<font color="#FF9238">${requestScope.online_user_count}</font>&nbsp;</a>个用户
						  </auth:authorize> 						    
					  </div>
				  </div>
				  <!--菜单栏-->	
				  <div id="id_menu_container" class="menu-container">
				     <div id="id_scroll_left"><a class="scroll-left" href="javascript:void(0);">&lt;</a></div>
				     <div class="toolbar-menu" id="id_toolbar_menu"></div>
				     <script type="text/javascript">jQuery("#id_toolbar_menu").css("width",(all_width-2)+"px")</script>
				     <div id="id_scroll_right"><a class="scroll-right" href="javascript:void(0);">&gt;</a></div>
				  </div>
		    </div>
			<div style="padding-top:5px;padding-left:5px;padding-right:5px;" onmouseover='jQuery("body>div.menu-top").menu("hide");'> <!--1112 helen change padding-top-->	
				<div id='main_content' style="border-right:0px;">
					<div region="center"  style="overflow:hidden;border-width:0px;" id='id_main_content_div'>
					        <iframe style="overflow:auto;border-width:0px;width:100%;height:500px;display:block;" name="name_iframe_content" id='id_iframe_content' name="name_iframe_content" frameBorder=0 src="${pageContext.request.contextPath}/loadingIndex.html"></iframe>
					        <script type="text/javascript">
					         var iframeContent = jQuery("#id_iframe_content");
					         iframeContent.css("width",(iframe_clientWidth-8)+"px");
					         iframeContent.css("height",(iframe_clientHeight)+"px");
					        </script>
					</div>
				</div>
			</div>
			<!-- 正文  结束>
			<div class="scroll-menu"><div id="id_scroll_content" class="scroll-content"></div></div -->
			<!-- 结尾开始 -->
			<div class="foot-above"></div>
			<!--Foot-->
				<div class="foot">
					<div class="logoMark"></div>
					<div class="copyRight">
				    	<p>COPYRIGHT ©2012  CORPORATION ALL RIGHTS RESERVED.&nbsp;&nbsp;&nbsp;&nbsp;京公网安备 号</p>
				    </div>
				</div>
			<!-- 结尾结束 -->
			<!--在线用户数量开始-->
			  <div id="id_online_users" closed="true" modal="true" title="当前在线人数（<font color='red'>${online_user_count}</font>）" style="display:none;width:500px;height:300px;padding-top:10px;text-align:center;">
		        <center>
			        <div style="width:90%;overflow:auto;" class="window-inner-content">
			             <table width="100%" >
			                <tr><th>序号</th><th>登录名</th><th>真实身份</th></tr>
			                <c:forEach varStatus='varStatus' items='${onlineUsers}' var='user'>
			                   <tr><td>${varStatus.index+1}</td><td>${user.username}</td><td>${user.realname}</td></tr>
			                </c:forEach>
			             </table>
			        </div>
		        </center>
		      </div>  
		    <!--在线用户数量结束-->
			<!-- 用户信息修改开始 -->
			<div id="id_modifyPasswordDetailInfoForm" closed="true" modal="true" title="用户信息修改" style="display:none;width:500px;padding-top:10px;">
	        <center>
		        <div style="width:95%;">
			        <table  style="width:90%">
			            <tr style='height:10px;'></tr>
			            <tr><td class="input_label_desc">登录用户:</td><td class="input_value"><input type="text" id="id_userName" disabled/></td></tr>
			            <tr><td class="input_label_desc">真实身份:</td><td class="input_value"><input type="text" id="id_realName" disabled/></td></tr>
			            <tr><td class="input_label_desc">联系电话:</td><td class="input_value"><input type="text" id="id_telephone" /></td><td><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">邮箱地址:</td><td class="input_value"><input type="text" id="id_email" /></td><td><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">原始密码:</td><td class="input_value"><input type="password" id="id_oldPassword"/></td><td><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">新密码:</td><td class="input_value"><input type="password" id="id_newPassword"/></td><td><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">确认新密码:</td><td class="input_value"><input type="password" id="id_confirmNewPassword"/></td><td><span class="content-required">*</span></td></tr>
			            <tr class="content-separator">
			                <td colspan=3>
						        <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary"onclick='doModifyPasswordOperation();'><span>确定</span></a>
								<a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='jQuery("#id_modifyPasswordDetailInfoForm").window("close");'><span>取消</span></a>
				            </td>
			            </tr>
			        </table>
		        </div>
	        </center>
	        </div>
	        <!-- 用户信息修改结束 -->
	        <div id="id_setDelegateWindowContainer"  class="easyui-window"  closed="true" modal="true" title="<span style='margin-left:20px;'></span>委托授权" style="display:none;width:800px;padding-top:10px;text-align:center;">
	           <div id="id_tableContainer"></div>
	        </div>
	        <!-- 设置代理开始 -->
			<div id="id_setDelegateForm"  class="easyui-window"  closed="true" modal="true" title="<span style='margin-left:20px;'></span>委托授权" style="display:none;width:420px;padding-top:10px;text-align:center;">
		        <center>
			        <div style="width:96%">
			            <input type="hidden" id="id_delegateUser"/>
				        <table align="center" width="98%">
				            <tr style='height:10px;'></tr>
				            <tr><td style='width:80px;' class="input_label_desc">当前用户:</td><td class="input_value">${sessionScope.login_realname}（${sessionScope.login_username}）</td></tr>
				            <tr><td style='width:80px;' class="input_label_desc">授权流程:</td><td class="input_value"><label id="id_listAllWorflows" ></label></td></tr>
				            <tr><td style='width:80px;' class="input_label_desc">授权对象:</td><td class="input_value"><input type="text" readonly id="id_delegateRealName" style="border:1px solid silver;width:160px;"/>&nbsp;<a type="button" style="width:50px;" class="btn btn-primary"  href="javascript:void(0);" onClick="javascript:choseDelegateUser();">选择用户</a><font color='red' style="margin-left:2px;">（必填）</font></td></tr>			            
				            <tr><td style='width:80px;' class="input_label_desc">开始日期:</td><td class="input_value"><input type="text" readonly id="id_startDate" class="Wdate" style="border:1px solid silver;width:160px;" onClick="WdatePicker(this,{readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/><span class="content-required">*</span></td></tr>
				            <tr><td style='width:80px;' class="input_label_desc">结束日期:</td><td class="input_value"><input type="text" readonly id="id_endDate"   class="Wdate" style="border:1px solid silver;width:160px;" onClick="WdatePicker(this,{readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/><span class="content-required">*</span></td></tr>
				            <tr class='content-separator'>
					            <td colspan=2>
						            <input class="btn btn-primary" type="button" value="确定" onclick='javascript:setDelegate();'/>
						            <input style="margin-left:20px;" class="btn btn-primary" type="button" value="关闭" onclick='javascript:jQuery("#id_setDelegateForm").window("close");'/>
					            </td>
				            </tr>
				        </table>
			        </div>
		        </center>
	        </div>
	      <!-- 设置代理结束 -->
		</body>
		<script type="text/javascript" defer>
		    jQuery(function(){
		    	 loadAjaxTree(false);
				 oldClientWidth = window.document.body.clientWidth;
				 resizeOper(window.top);
		         var iframeContent = jQuery("#id_iframe_content");
		         //加载主页信息
		         setTimeout('iframeContent[0].src="${pageContext.request.contextPath}/${param.pageUrlPath}?mathRandomNumber=<%=new java.util.Random().nextLong() %>"',1000);
				 var myDate=new   Date(); 
				 var myHour=myDate.getHours(); 
				 var arrayDay=["日","一","二","三","四","五","六"];  
				 document.getElementById("dateZone").innerHTML = myDate.getFullYear()+"年"+(myDate.getMonth()+1)+"月"+myDate.getDate()+"日 "+"星期"+arrayDay[myDate.getDay()];
				 jQuery(".main_time").show();
				 jQuery("#id_loadMaskContainer").hide();
			});
		</script>
</html>