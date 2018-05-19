<%@ taglib prefix='auth' uri='/WEB-INF/tlds/security.tld' %>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix="fn"  uri="/WEB-INF/tlds/fn.tld" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%> 
<%@ taglib uri="/minidict" prefix="mini"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
      <title><spring:message code="FinancingLeaseBusinessSystem"  text="FinancingLease"/></title>
	   <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	   	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyOperationTable.js"></script>
	   	<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
	   <%@ include file="main_include_js.jsp" %>
	   <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
	   <c:if test="${'true' eq isHasExistUser}">
	       <script type="text/javascript" >
			function closeWindow()
			{
				try{window.opener=null;window.open("","_self");window.close();}catch(e){window.close();}
			}
	        if(confirm("用户${isHasExistUserRealName}（${isHasExistUserName}）已在线，是否继续登录，原用户将被强行注销？")){
		          ajaxRequest({
			          url:'${pageContext.request.contextPath}/acl/removeUserSession.acl',
			          async:false,
			          success:function(res){
				          
				      },
				      failure:function(res){
					  },
			          params:{
				          userId:"<mini:param  name='isHasExistUserId'/>"
			          }
		          });
		    }else{
			      try{
			    	  //closeWindow();  
			    	  window.location.href = "${pageContext.request.contextPath}/closeWindow.jsp";
				  }catch(e){}
			}
	       </script>
	   </c:if>
	   <style type="text/css">
	      html,body{
	         padding:0px;
	         margin:0px;
	         overflow:hidden;
	      }
	   </style>
	   <script type="text/javascript">
	   
		    function showlogoutWindow(){
					var logout=window.confirm("该操作将注销用户身份，是否继续？");
					if(logout==true){
						window.top.location.href="${pageContext.request.contextPath}/j_spring_security_logout";
							alert("注销成功！");
					}

			 }
	   </script>
	</head>
			<body >
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
	              <!-- fuck -->
			      <div class="main_topbg" id='id_mainPage_header'>
					    <div class="topleft">
					       <a href="javascript:void(0);" onclick="jQuery('#id_menuButton_0').click();"><img src="${pageContext.request.contextPath}/images/logo.png" title="系统首页" /></a>
					    </div>
						<!--菜单栏-->	
						<div id="id_scroll_left" class="scroll-container">&nbsp;</div>
						<div id="id_menu_container" class="menu-container">
						    <ul class="nav" id="id_toolbar_menu"> </ul>
						</div>
						<script type="text/javascript">jQuery("#id_menu_container").css("width",(all_width-topLeftAndRightWidth)+"px");</script>
						<div id="id_scroll_right"  class="scroll-container">&nbsp;</div>
						<div class="topright">    
							    <ul>
								    <li><a href="javascript:void(0);" onClick="javascript:showPersonalSettingWindow();" ><img src="${pageContext.request.contextPath}/menuIcons_w/wrench.png" style="top:3px;margin-right:5px;position:relative;" border="0" /><spring:message code="PersonalSetting"  text="快捷"/></a></li>
								    <li><a href="javascript:void(0);" onClick="javascript:showModifyPasswordWindow();" ><img src="${pageContext.request.contextPath}/menuIcons_w/cogs.png" style="top:3px;margin-right:5px;position:relative;" border="0" /><spring:message code="Modify"  text="Modify"/></a></li>
								    <li><a id="id_delegateTooLink" href="javascript:void(0);" onClick="javascript:showDelegateWindow();" ><img src="${pageContext.request.contextPath}/menuIcons_w/key.png" style="top:3px;margin-right:5px;position:relative;" border="0" /><spring:message code="Delegate"  text="Delegate"/></a></li>
								    <li><a href="javascript:void(0);" onClick="javascript:showlogoutWindow();"  style='text-decoration: none;' ><img style="top:3px;margin-right:5px;position:relative;" src="${pageContext.request.contextPath}/menuIcons_w/on-off.png" border="0" /><spring:message code="Logout"  text="Logout"/></a></li>
								    <permission:action  code="dealer_action"><script type="text/javascript">jQuery("#id_delegateTooLink").hide();</script></permission:action>
							    </ul>
							    <div class="user">
							    <c:set var="upperLoginUserName" value="${fn:toUpperCase(sessionScope['login_username'])}"/>
								<c:if test="${('ADMINISTRATOR' eq upperLoginUserName) or ('ADMIN' eq upperLoginUserName)}">
								    &nbsp;<spring:message code="Online"  text="Online"/><a style="text-decoration:none;" href="javascript:void(0);" onClick="javascript:showOnlineUsersWindow();">&nbsp;<font color="#FF9238"><mini:param name='online_user_count'/></font>&nbsp;</a><spring:message code="User"  text="User"/>
								</c:if> 
							    <span><font color="#FFF">${('ZH' eq currentLoginSessionLanguage) ? sessionScope.login_realname : sessionScope.login_username }</font></span>
							    <i>消息</i>
							    <a href="javascript:void(0);" onClick="javascript:shwoMessageWindwos(true);" ><b id="emailNum">0</b></a>
							    </div>    
							    
						</div>
						
						<div class="main_time" style="display:none">
						  <div >
						    <spring:message code="Welcome"  text="Welcome"/>&nbsp;&nbsp;<font color="#FF9238">${('ZH' eq currentLoginSessionLanguage) ? sessionScope.login_realname : sessionScope.login_username }</font>&nbsp;|
					        <br/>
					        <c:choose>
							   <c:when test="${'ZH' eq currentLoginSessionLanguage}">
							       <a style="color:#FF9238" target="_self" href="${pageContext.request.contextPath}/acl/changeLocale.acl?locale=en_US&reloadPageUrl=acl/index.acl"><spring:message code="ChangeTo"  text="ChangeTo"/><spring:message code="English"  text="English"/></a>
							   </c:when>
							   <c:otherwise>
							      <a style="color:#FF9238" target="_self" href="${pageContext.request.contextPath}/acl/changeLocale.acl?locale=zh_CN&reloadPageUrl=acl/index.acl"><spring:message code="ChangeTo"  text="ChangeTo"/><spring:message code="Chinese"  text="Chinese"/></a>
							   </c:otherwise>
							</c:choose>
						  &nbsp;&nbsp;<spring:message code="Today"  text="Today"/>&nbsp;&nbsp;<span id="dateZone" ></span>
						  </div>
						  <c:set var="upperLoginUserName" value="${fn:toUpperCase(sessionScope['login_username'])}"/>
						  <c:if test="${('ADMINISTRATOR' eq upperLoginUserName) or ('ADMIN' eq upperLoginUserName)}">|
						    <spring:message code="Online"  text="Online"/><a style="text-decoration:none;" href="javascript:void(0);" onClick="javascript:showOnlineUsersWindow();">&nbsp;<font color="#FF9238"><mini:param name='online_user_count'/></font>&nbsp;</a><spring:message code="User"  text="User"/>
						  </c:if> 						    
					  </div>
					  <!--  
					  -->
				  </div>
		    </div>
			<div onmouseover='jQuery("body>div.menu-top").menu("hide");'>
				<div id='main_content' style="border-right:0px;">
					<div region="center"  style="overflow:hidden;border-width:0px;" id='id_main_content_div'>
					        <iframe style="overflow:auto;border-width:0px;width:100%;height:500px;display:block;" name="name_iframe_content" id='id_iframe_content' name="name_iframe_content" frameBorder=0 src="${pageContext.request.contextPath}/loadingIndex.html"></iframe>
					        <script type="text/javascript">
					         var iframeContent = jQuery("#id_iframe_content");
					         iframeContent.css("width",(all_width)+"px");
					         iframeContent.css("height",(iframe_clientHeight)+"px");
					        </script>
					</div>
				</div>
			</div>
			<!--在线用户数量开始-->
			  <div id="id_online_users" closed="true" modal="true" title="当前在线人数（<font color='red'><mini:param name="online_user_count"/></font>）" style="display:none;width:500px;height:300px;padding-top:10px;text-align:center;">
		        <center>
			        <div style="width:90%;overflow:auto;" class="window-inner-content">
			             <table width="100%" >
			                <tr><th>序号</th><th>登录时间</th><th>登录名</th><th>真实身份</th></tr>
			                <c:forEach varStatus='varStatus' items='${onlineUsers}' var='loginInfo'>
			                   <c:set var="user" value="${loginInfo.loginUser}"/>
			                   <tr><td>${varStatus.index+1}</td><td>${loginInfo.loginTime}</td><td>${user.username}</td><td>${user.realname}</td></tr>
			                </c:forEach>
			             </table>
			        </div>
		        </center>
		      </div>  
		    <!--在线用户数量结束-->
			<!-- 用户信息修改开始 -->
			<div id="id_modifyPasswordDetailInfoForm" closed="true" modal="true" title="用户信息修改" style="display:none;width:400px;padding-top:10px;">
	        <center>
		        <div style="padding-left:30px;">
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
								<a  id="id_cancel_update_password" style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='jQuery("#id_modifyPasswordDetailInfoForm").window("close");'><span>取消</span></a>
				            </td>
			            </tr>
			        </table>
		        </div>
	        </center>
	        </div>
	        <!-- 用户信息修改结束 -->
			<!-- 个人设置开始 -->
			<div id="id_modifyPersonalSettingDetailInfoFormWindow" closed="true" modal="true" title="个人设置" style="display:none;width:500px;height:550px;">
		        <center>
			        <div>
				        <table  style="margin:auto;padding:auto;">
				            <tr><td>
				                 <div id="id_personalSetting_tabs" style="border:1px solid #DDD;height:450px;width:450px;">
							        <div title="快捷操作">
							            <table>
							                <tr>
							                   <td style="width:20px;">&nbsp;</td><td><div id="id_personalSetting_treeContainer" ></div></td>
							                </tr>
							            </table>
							        </div>
							     </div>
				             </td></tr>
				            <tr class="content-separator">
				                <td >
							        <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary"onclick='distributeQuick();'><span>确定</span></a>
									<a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='jQuery("#id_modifyPersonalSettingDetailInfoFormWindow").window("close");'><span>取消</span></a>
					            </td>
				            </tr>
				        </table>
			        </div>
		        </center>
	        </div>
	        <script>
	            var  currentQuickCheckedNode = null;
	            jQuery(function(){
		            ajaxRequest({
		            	url:'${pageContext.request.contextPath}/acl/getUserQuickMenuTreeData.acl',
                        success:function(res){
                              jQuery('#id_personalSetting_treeContainer').tree({
			        			checkbox:true,
			        			cascadeCheck:false,
			        			data:JsonUtil.decode(res.responseText),
			        			onlyLeafCheck:true/*,
			        			onLoadSuccess:function(a,b){
			        				jQuery("#id_personalSetting_treeContainer span.tree-checkbox").click(function(e){
			        					distributeQuickC(currentQuickCheckedNode);
			        				});
			        		    },
			        			onCheck:function(node,checked){
			        		    	currentQuickCheckedNode = node;
			        		    }*/
			        		});
                        }
			        });
		        });
	        	function distributeQuick(){
	        		loadMask.show();
	        		var checkedNodes = jQuery('#id_personalSetting_treeContainer').tree('getChecked');
	        		var checkedNodesLen = checkedNodes.length;
	        		if(0 == checkedNodesLen){
		        		 //alert("至少需要勾选一个快捷操作!");
		            }
		            var menuIdArr = [];
	        		for(var i=0;i<checkedNodesLen;i++){
	        			menuIdArr.push(checkedNodes[i].id);
		        	}
	                var entityClassName = "QuickUserMenu";
	                var leftFieldName = "user";
	                var leftId = "<mini:param name='login_userid'/>";
	                var rightFieldName = "menu";
	                var rightId = menuIdArr.join(",");
	        	    var params = {
	        	             entityClassName:entityClassName,
	        	       		 leftFieldName  :leftFieldName,
	        	       		 leftId         :leftId,
	        	       		 rightFieldName :rightFieldName,
	        	       		 rightId        :rightId
	        	     };
	        		 var url = "${pageContext.request.contextPath}/acl/addOrRemoveQuickMenuDistribute.acl";
	                 ajaxRequest({
	                      url:url,
	                      params:params,
	                      timeout:30*1000,
	                      success:function(res){
	                          alert("操作成功!");
	                          loadMask.hide();
	                          var $iframeContent = jQuery("#id_iframe_content")
	                          var iframeContentSrc = $iframeContent.attr("src");
	                          if(iframeContentSrc.indexOf("/acl/index.bi")>-1){
	                        	  $iframeContent.attr("src",iframeContentSrc);
		                      }
		                      jQuery("#id_modifyPersonalSettingDetailInfoFormWindow").window("close");
	                      }
	                 });
	            }
	            function doCheckTreeByMenuId(firstLevelMenuId,menuId){
	        		window.top['currentReloadMenuId'] = menuId;
	        		jQuery("li[id^='id_menuButton_'][firstLevelMenuId='"+firstLevelMenuId+"']").click();
		        }
	        </script>
	        <!--个人设置结束 -->
	        <!--站内信开始 -->
			<div id="id_station_message" closed="false" modal="true" title="消息管理"  style="display:none;width:800px;height: 420px;overflow:hidden;">
	        <center>
		        <div id="id_message_table" style="padding:0px;">
			       <!--  <div title="未读消息">
				    	<div id="id_message_list"></div>
			        </div> -->
			        <div title="站内公告" style="overflow: hidden;">
			        	<div id="id_stationnotice_list"></div>
			        </div>
			        <div title="我的消息" style="overflow: hidden;">
			        	<div id="id_mynotice_list"></div>
			        </div>
			        <!-- <div title="发送消息" style="">
			        	<table  style="width:100%;text-indent:10px;border:1px solid #DDD;height: 300px;">
				            <tr style='height:10px;'></tr>
				            <tr><td class="input_label_desc" style='text-align:right'>收件人:</td><td class="input_value"><input type="text" id="id_send_names" onclick="getTracywindyObject('id_sendMessageChoosor').show();"/><input type="hidden" id="id_send_names_id"/></td></tr>
				            <tr><td class="input_label_desc" style='text-align:right'>发送内容:</td><td class="input_value"><textarea id="id_send_contents" style="width: 400px; height: 120px;"></textarea></td></tr>
				            <tr class="content-separator">
				                <td colspan=3 style="text-align:right;">
							        <a  style="margin-right:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='sendMessage();'><span>发送</span></a><span style="width:20px;display:inline-block;">&nbsp;</span>
					            </td>
				            </tr>
				        </table>
			        
			        </div> -->
			   
		        </div>
	        </center>
	        </div>
	        <!-- 站内信结束 -->
	        <div id="myNoticeInfo" buttons="#markRead" closed="true" modal="true" title="我的消息" style="width: 1000px; height: 800px;display: none;overflow-x:hidden;">
				<input type="hidden" name="messageBid" id="messageBid">
				<form id="id_myNoticeInfoForm">
				<table style="width: 100%;border:1px #DDD solid;" class="fillTable">
					<tr class="tr-even">
						<td class="td-content-title" style="width:15%;text-align: left;">主题:</td>  
						<td class="td-content" style="width:35%;text-align: left;"><span name="msgtitle"></span></td>
					</tr> 
					<tr class="tr-odd">
						<td class="td-content-title" style="width:15%;text-align: left;">上报时间:</td>  
						<td class="td-content" style="width:35%;text-align: left;"><span name="senddate"></span></td>
					</tr>
					<tr class="tr-even">
						<td class="td-content-title" style="width:15%;text-align: left;">上报人:  </td>  
						<td class="td-content" style="width:35%;text-align: left;"><span name="fromuser"></span></td>			  
					</tr> 
					<tr class="tr-odd">
						<td class="td-content-title" style="width:15%;text-align: left;">正文:  </td>  
						<td class="td-content" style="width:35%;text-align: left;"><span name="msgtext"></span></td>			  
					</tr>
				</table>
				</form>
			</div>
			<div id="markRead" style="display:none;width:950px;height:40px;line-height:40px;border:0px solid #DDD;text-align:right;background:transparent;">
					<a style="" href="javascript:void(0);" class="btn btn-primary"onclick='markAsRead("0",true)'><span>标为已读</span></a>
					<a style="" href="javascript:void(0);" class="btn btn-primary"onclick='markAsRead("1",true)'><span>标为未读</span></a>
			</div>
	        <!-- 设置代理开始 -->
	        <div id="id_setDelegateWindowContainer"   closed="true" modal="true" title="流程授权   &lt;<mini:param name="login_realname"/>（<mini:param name="login_username"/>）&gt;" style="display:none;width:804px;text-align:center;overflow:hidden;">
	           <div id="id_tableContainer"></div>
	        </div>
			<div id="id_setDelegateForm"   closed="true" modal="true" title="<span style='margin-left:20px;'></span>委托授权" style="display:none;width:420px;padding-top:10px;text-align:center;">
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
	      	<form method="post" target="fileDownloadIframe" action="${pageContext.request.contextPath}/acl/downloadNoticeUploadFile.acl" id="id_nameForm">
			  <input name="type" type="hidden" value="file" />
			  <input name="uuid"  type="hidden" value=""/>
			</form>
	      <!-- 设置代理结束 -->
	      <c:set var="dayOfWeek" value='<%=new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"}[java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_WEEK)-1]%>'></c:set>
		</body>
		<script type="text/javascript" defer>
			var tableLoadFirst = false;
			var tableLoadSecond = false;
		    jQuery(function(){
		    	 loadAjaxTree(true);
				 oldClientWidth = window.document.body.clientWidth;
				 resizeOper(window.top);
		         var iframeContent = jQuery("#id_iframe_content");
		         //加载主页信息
		         setTimeout('iframeContent[0].src="${pageContext.request.contextPath}/<mini:param name='pageUrlPath'/>?mathRandomNumber=<%=new java.util.Random().nextLong() %>"',1000);
				 var myDate=new   Date(); 
				 var myHour=myDate.getHours(); 
				 //var arrayDay=["星期日","星期一","星期二","星期三","星期四","星期五","星期六"]; 
				 //var arrayDay =["Tuesday"]; 
				 //Monday 周一Tuesday 周二Wednesday 周三Thursday 周四Friday  周五Saturday 周六Sunday  周日
				 document.getElementById("dateZone").innerHTML = myDate.getFullYear()+"/"+(myDate.getMonth()+1)+"/"+myDate.getDate()+"&nbsp;&nbsp;"+'<spring:message code="${dayOfWeek}"  text="${dayOfWeek}"/>';
				// jQuery(".main_time").show();
				 jQuery("#id_loadMaskContainer").hide();
				 /*
				//站内公告
				 var mynotice = new tracywindyTable({
				   		 tableComment:'[站内公告]',
				   	     border:true,
				         renderTo:'id_stationnotice_list',
				         width:800,
				         height:350,
				         id:'id_table_stationnotice_list',
				         xmlFileName:'/eleasing/jsp/base/mynoticeInfo.xml',
				         loadMode:'ajax',
				         isPage:true,
				         isFit:true,
				         columns:[
					            {header:'id',name:'id',hidden:true},
					            {header:'bid',name:'bid',hidden:true},
					            {header:'正文',name:'msgtext',hidden:true},
					            {header:'主题',name:'msgtitle',renderer:showMyNotice},
					            {header:'上报时间',name:'senddate'},
					            {header:'上报人',name:'realname'},
					            {header:'状态',name:'readstatus'}
				        ],
				        params:{
			 			     MSG_TYPE:'msgtype.2',
			 			     USER_ID:'${sessionScope.loginUser.id}'
						}
				   	 });
		         //我的消息
				 var mynotice = new tracywindyTable({
				   		 tableComment:'[我的消息]',
				   	     border:true,
				         renderTo:'id_mynotice_list',
				         width:790,
				         height:350,
				         id:'id_table_mynotice_list',
				         xmlFileName:'/eleasing/jsp/base/mynoticeInfo.xml',
				         loadMode:'ajax',
				         isPage:true,
				         isFit:true,
				         columns:[
					            {header:'id',name:'id',hidden:true},
					            {header:'bid',name:'bid',hidden:true},
					            {header:'正文',name:'msgtext',hidden:true},
					            {header:'主题',name:'msgtitle',renderer:showMyNotice},
					            {header:'上报时间',name:'senddate'},
					            {header:'上报人',name:'realname'},
					            {header:'状态',name:'readstatus'}
				        ],
				        params:{
			 			     MSG_TYPE:'msgtype.1',
			 			     USER_ID:'${sessionScope.loginUser.id}'
						}
				   	 });*/
			});
		   	 function showMyNotice(value,tableObj,columnName,columnIndex,rowData){
		   		var base = "<a href='javascript:void(0);' onclick='showMyNoticeInfo(\""+rowData.id+"\",\""+rowData.bid+"\",\""+rowData.readstatus+"\")'>{1}</a>";
		        var updateFlag=value;
		        var updateClickFunc="other";
		        var field=base.replace("{1}",updateFlag);
		        return field;
			 }
			 //readstatus(0只读，1未读) isprompt(标记成功后是否需要提示信息)
			 function markAsRead(readstatus,isprompt){
				var messageBid = $("#messageBid").val();
				var url = "${pageContext.request.contextPath}/leasing/markAsRead.action";
		    	var params = {message_bid:messageBid,user_id:userId,read_status:readstatus};
				 ajaxRequest({
			    	 method:'POST',
			         url:url,
			         params:params,
			         failure:function(res){
			        	alert("标记失败!");
			         },
			         success:function(res){
			 	   		res=res.responseText;
			 	   		res=res.replace(/(^\s+)|(\s+$)/g, ""); 
			     	    if(res!=''){
			     	    	var rs=JsonUtil.decode(res);
			     	    	if("0" == rs['readstatus']){	
				     	    	if(isprompt){
			     	    			alert("标记成功");
					     	    }
		     	    			var currentTable = getTracywindyObject('id_table_mynotice_list');
			     				currentTable.reload();
				     	    	currentTable = getTracywindyObject('id_table_stationnotice_list');
			     				currentTable.reload();
				     	    }
			     	    	if("1" == rs['readstatus']){
			     	    		if(isprompt){
			     	    			alert("标记成功");
					     	    }
		     	    			var currentTable = getTracywindyObject('id_table_mynotice_list');
			     				currentTable.reload();
				     	    	currentTable = getTracywindyObject('id_table_stationnotice_list');
			     				currentTable.reload();
				     	    }
				     	    if("0" == rs['readnum']){
				     	    	$(".panel-tool").show();
				     	    	$("#emailNum").html("0");
					     	}
			     	    }else{
			     	    	alert("无结果返回,请重新尝试!");
			     	    }
			         }
			    });
			 }
			   function addNoticeAttachmentDownloadFunction($v)
			   {
				   $v.find("a.tracywindyFileDownLoadSpan").each(function(i){
					   this.style.cursor = "pointer";
					   this.title = "点击下载文件";
					   this.style.color = "blue";
					   this.href="javascript:void(0)";
					   this.style.fontWeight = "BOLD";
					   var oldClassStr = (this.className||"").replace(/\s{1,}/gm," ");
					   var oldClassArr = oldClassStr.split(" ");
					   for(var i=0;i<oldClassArr.length;i++){
						   var className = oldClassArr[i]||"";
						   if(0 == className.indexOf("uuid-")){
							   var uuid = className.substring("uuid-".length,className.length);
							   var clickFunc = (function(uuid){
								   return function(e){
									   jQuery("#id_nameForm input[name='uuid']").val(uuid);
									   jQuery("#id_nameForm")[0].submit();
									   return false;
								   };
							   })(uuid);
							   this.onclick = clickFunc;
						   }
					   }
				   });
			  }
		    function showMyNoticeInfo(messageId,messageBid,readstatus){
			     $("#messageBid").val(messageBid);
				 var url = "${pageContext.request.contextPath}/leasing/myNoticeInfo.action";
		    	 var params = {message_id:messageId};
				 ajaxRequest({
			    	 method:'POST',
			         url:url,
			         params:params,
			         failure:function(res){
			        	alert("访问服务器失败!");
			         },
			         success:function(res){
			 	   		res=res.responseText;
			 	   		res=res.replace(/(^\s+)|(\s+$)/g, ""); 
			     	    if(res!=''){
			     	    	var rs=JsonUtil.decode(res);
			     	    	$("#myNoticeInfo span").each(function(i, v){
				   		    	var $v = $(v);
				   		    	var name = $v.attr("name");
					   		    if(name == "msgtitle"){
					   		    	$v.html(rs['msgtitle']);
					   		    }
					   		    if(name == "msgtext"){
					   		    	$v.html(rs['msgtext']);
					   		    	addNoticeAttachmentDownloadFunction($v);
						   		}
						   		if(name == "senddate"){
					   		    	$v.html(rs['senddate']);
							    }
						   		if(name == "fromuser"){
					   		    	$v.html(rs['fromuser']);
						   		}
				   		     });
			     	    }else{
			     	    	alert("无结果返回,请重新尝试!");
			     	    }
			         }
			    });
				 $("#myNoticeInfo").show();
				 jQuery("#myNoticeInfo").dialog({
			        	zIndex:9100,
			        	draggable:true,
			        	top:150,
			        	resizable:true,
			        	shadow:true,
			        	modal:true,
			        	inline:false,
			        	collapsible:false,
			        	minimizable:false,
			        	maximizable:false,
			        	closable:true,
			        	closed:false
			     });
				 markAsRead("0",false);
			}
		</script>
</html>