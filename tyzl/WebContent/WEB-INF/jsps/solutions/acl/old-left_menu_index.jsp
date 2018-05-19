	<%@ taglib prefix='auth' uri='/WEB-INF/tlds/security.tld' %>
	<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib uri="/minidict" prefix="mini"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
	<head>
	<title>管理平台-V3.0</title>
	   <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	   <%@ include file="main_include_js.jsp" %>
	   	<script type="text/javascript" src="${pageContext.request.contextPath}/js/accordion/prototype.lite.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/accordion/moo.fx.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/accordion/moo.fx.pack.js"></script>
	   	<link href="${pageContext.request.contextPath}/css/tracywindy/left-menu.css" rel="stylesheet" type="text/css">
	</head>
    <body>
    <div>
             <div id="id_loadMaskContainer" style="display:block;">
					<div id="loading">
					    <div class="loading-indicator">页面加载中   请稍等...
					    </div>
					</div>
			  </div>
			  <script type="text/javascript">
			     jQuery("#id_loadMaskContainer").css("height",all_height);
			  </script>
           <div class="frame-left" id="id_frameLeft">
           <div class="frame-left-title"><img src="${pageContext.request.contextPath}/images/application_side_boxes.png" >系统菜单</div>
        </div>
        <div class="frame-middle" id="id_frameMiddle" ><a href="javascript:void(0);" class="expanded" title="单击鼠标 (显示 / 隐藏  )左侧菜单栏">&nbsp;</a></div>
        <div class="frame-right" >
           <div>
	           <div class="frame-right-title">
	                <img src="${pageContext.request.contextPath}/images/application_view_gallery.png" >
	                <span id="id_goPage" >用户主页</span>
	           		<span class="main_time" style="line-height:100%;margin-top:0px;font-size:12px;">
							  <span id="dateZone" ></span>
							  <auth:authorize ifAnyGranted="ROLE_ADMIN">
							    &nbsp;&nbsp;在线用户数：<a style="text-decoration:none;" href="javascript:void(0);" onclick="javascript:showOnlineUsersWindow();"><font><mini:param name="online_user_count"/>&nbsp;</font></a>，
							  </auth:authorize>  
							    欢迎您：<font color="#961433"><mini:param name="login_realname"/></font>
							    &nbsp;&nbsp;<a href="javascript:void(0);" style="text-indent:0px;margin:0px;padding:0px;text-align:center;" onclick="javascript:showModifyPasswordWindow();" title="用户信息修改"><img src="${pageContext.request.contextPath}/images/icon_settings.gif" style="top:3px;position:relative;" border="0" /></a>
						        <a href='${pageContext.request.contextPath}/j_spring_security_logout' style='text-decoration: none;text-indent:0px;margin-left:-15px;padding:0px;' title="注销"><img style="top:3px;position:relative;" src="${pageContext.request.contextPath}/images/action_stop.gif" border="0" /></a>
					</span>
			   </div>
           </div>
           <div><iframe frameborder="0" name="name_rightFrame" id="id_frameRight" src="${pageContext.request.contextPath}/loadingIndex.html"></iframe></div>
        </div>
    </div>
    
    			<!--在线用户数量开始-->
			  <div id="id_online_users" closed="true" modal="true" title="当前在线人数（<font color='red'><mini:param name="online_user_count"/></font>）" style="display:none;width:500px;height:300px;padding-top:10px;text-align:center;">
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
			            <tr><td class="input_label_desc">联系电话:</td><td class="input_value"><input type="text" id="id_telephone" /><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">邮箱地址:</td><td class="input_value"><input type="text" id="id_email" /><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">原始密码:</td><td class="input_value"><input type="password" id="id_oldPassword"/><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">新密码:</td><td class="input_value"><input type="password" id="id_newPassword"/><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">确认新密码:</td><td class="input_value"><input type="password" id="id_confirmNewPassword"/><span class="content-required">*</span></td></tr>
			            <tr class="content-separator">
			                <td colspan=3>
						        <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary"onclick='doModifyPasswordOperation();'><span>确定</span></a>
								<a  style="margin-left:20px;" href="javascript:void(0);" id="id_cancel_update_password" class="btn btn-primary" onclick='jQuery("#id_modifyPasswordDetailInfoForm").window("close");'><span>取消</span></a>
				            </td>
			            </tr>
			        </table>
		        </div>
	        </center>
	        </div>
	        <!-- 用户信息修改结束 -->
	         <!-- 设置代理开始 -->
	        <div id="id_setDelegateWindowContainer"  closed="true" modal="true" title="<span style='margin-left:20px;'></span>委托授权" style="display:none;width:800px;padding-top:10px;text-align:center;">
	           <div id="id_tableContainer"></div>
	        </div>
			<div id="id_setDelegateForm"  closed="true" modal="true" title="<span style='margin-left:20px;'></span>委托授权" style="display:none;width:420px;padding-top:10px;text-align:center;">
		        <center>
			        <div style="width:96%">
			            <input type="hidden" id="id_delegateUser"/>
				        <table align="center" width="98%">
				            <tr style='height:10px;'></tr>
				            <tr><td style='width:80px;' class="input_label_desc">当前用户:</td><td class="input_value"><mini:param name="login_realname"/>（<mini:param name="login_username"/>）</td></tr>
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
        <script type='text/javascript'>
             var pageWidth,pageHeight;
             function initContainerSize(){
	 		     pageWidth  = all_width-4;
			     pageHeight = all_height-4;
	        	 jQuery("#id_frameLeft").css("height",pageHeight+"px");
	        	 jQuery("#id_frameMiddle").css("height",(pageHeight+2)+"px");
	        	 jQuery("#id_frameMiddle").css("line-height",(pageHeight+2)+"px");
	        	 jQuery("#id_frameRight").css("width",(pageWidth-183-10)+"px");
	        	 jQuery(".frame-right-title").css("width",(pageWidth-183-10)+"px");
	        	 jQuery("#id_frameRight").css("height",(pageHeight-28)+"px");
             }
	         jQuery(function(){
	        	 initContainerSize();
	        	 loadLeftMenu('id_frameLeft','0');
	        	 
				 var myDate=new   Date(); 
				 var myHour=myDate.getHours(); 
				 var arrayDay=["日","一","二","三","四","五","六"];  
				 document.getElementById("dateZone").innerHTML = "<img alt='当前日期' style='margin-top:3px;margin-right:5px;' src='${pageContext.request.contextPath}/images/calendar_1.png'/>"+myDate.getFullYear()+" 年 "+(myDate.getMonth()+1)+" 月 "+myDate.getDate()+" 日   "+"星期"+arrayDay[myDate.getDay()];
				 jQuery(".main_time").show();
				 jQuery("#id_loadMaskContainer").hide();

				 jQuery("#id_frameMiddle a").click(function(){
					 if(this.className.indexOf("expanded")>-1)
					 {
						 jQuery(this).removeClass("expanded");
						 jQuery(this).addClass("collapsed");
						 jQuery("#id_frameLeft").hide();
						 jQuery("#id_frameRight").css("width",(pageWidth-10)+"px");
						 jQuery(".frame-right-title").css("width",(pageWidth-10)+"px");
				     }
					 else
					 {
						 jQuery(this).removeClass("collapsed");
						 jQuery(this).addClass("expanded");
						 jQuery("#id_frameLeft").show();
						 jQuery("#id_frameRight").css("width",(pageWidth-183-10)+"px");
						 jQuery(".frame-right-title").css("width",(pageWidth-183-10)+"px");
					 }
					 document.getElementById('id_frameRight').src = document.getElementById('id_frameRight').src;
			     });
			     function resizeLeftMenu()
			     {
					 all_width = window.document.body.clientWidth;
					 all_height = window.document.documentElement.clientHeight;
					 
					 initContainerSize();
					 if(Math.abs(all_width-oldClientWidth)>4)
					 {
						 document.getElementById('id_frameRight').src = document.getElementById('id_frameRight').src;
					 }
			    	 oldClientWidth = all_width;
				 }
			     window.onresize = function(){
				     resizeLeftMenu();
				 };
	         });
        </script>
    </html>