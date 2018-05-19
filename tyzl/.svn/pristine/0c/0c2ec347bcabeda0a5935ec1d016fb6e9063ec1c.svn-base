	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>  
	<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld' %>
	<%@ taglib uri="/minidict" prefix="mini"%>
	<!--css sheet-->
	<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/main.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">
	<style type="text/css">
	   html,body{
	      overflow:auto;
	   }
	</style>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyMultiTable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree/dtree.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyCommonUserSelection.js"></script>
	<script type="text/javascript">
		var isAdminUser = false;
		<permission:action  code="admin_action">
		   var isAdminUser = true;
		</permission:action>
	</script>
	<%@ include file="workflowDelegate.jsp" %>
    <script type='text/javascript'>
          //jQuery.noConflict();
          var isIE = (SysBrowser.getBrowser().toLowerCase().indexOf("ie")>-1);
          var init_iframe_clientWidth = (document.documentElement||document.body).clientWidth-2;
          var all_width = (document.documentElement||document.body).clientWidth;
          var all_height = (document.documentElement||document.body).clientHeight;
          var topLeftAndRightWidth = 640;
          if("${sessionScope['login_userlanguage'].language}" == 'en'){
        	  topLeftAndRightWidth = 720;
          }
          //var iframe_add_height = 147;
          var iframe_add_height = 75;
	      var iframe_clientWidth = all_width-2;
	      var iframe_clientHeight = (document.documentElement||document.body).clientHeight-iframe_add_height;
	      var init_iframe_clientHeight = iframe_clientHeight+2;
          var globalMenuJson = {};
          //菜单个数
	      var menuCount = 0;
          var menuAllWidth = 0;
          var isNeedScroll = true;
          var oldClientWidth  = all_width;
          var oldClientHeight = all_height;
          var tempWidthAdd  = 24;
          var incrementSize = all_width - tempWidthAdd*2 - topLeftAndRightWidth;
         
         function resizeOper(window)
		 {
				  //重新改变大小
				  window.onresize = function(){
					  all_width = window.document.body.clientWidth;
					  all_height = window.document.documentElement.clientHeight;
					  incrementSize = all_width - topLeftAndRightWidth;
					  if((Math.abs(all_width-oldClientWidth)> 50)&&(Math.abs(all_height-oldClientHeight)> 50))
					  {
						  if(!confirm("您改变了页面的大小，请确认已录入的内容是否已保存，页面重新布局后未保存的信息将丢失。请确认是否需要重新布局？")){
							  oldClientWidth = all_width;
							  oldClientHeight = all_height;
							  return;
						  }
						  var iframeContent = window.document.getElementById('id_iframe_content');
			              if(menuAllWidth <= (all_width-topLeftAndRightWidth-15))
			              {
			              	  isNeedScroll = false;
			              }
			              else
			              {
			            	  isNeedScroll = true;
				          }
						  with(iframeContent)
						  {
							  iframeContent.style.width = (all_width-1)+"px";
							  iframeContent.style.height = (all_height-iframe_add_height)+"px";
							  iframeContent.src = iframeContent.src;
						  }
						  var toolMenuDiv = jQuery("#id_menu_container")[0];
					      if(isNeedScroll)
					      {
					    	   jQuery("#id_menu_container")[0].scrollLeft = 0;
							   hideScrollLeft();
	    					   showScrollRight();
					           jQuery(toolMenuDiv).css("width",(all_width-topLeftAndRightWidth-tempWidthAdd-2)+"px");
						  }
					      else
					      {
							   hideScrollLeft();
	    					   hideScrollRight();
	    					   jQuery(toolMenuDiv).css("width",(all_width-topLeftAndRightWidth-2)+"px");
						  }
				        	 //jQuery("#id_scroll_left").click(scrollLeftClick);
				        	 //jQuery("#id_scroll_right").click(scrollRightClick);
				      }
					  oldClientWidth = all_width;
					  oldClientHeight = all_height;
				  };
			  }
		     function scrollLeftClick(){
	        	   var toolMenuDiv = jQuery("#id_menu_container")[0];
	        	   if(toolMenuDiv.scrollLeft<=incrementSize)
	        	   {
	        		   toolMenuDiv.scrollLeft = 0;
	        		   hideScrollLeft();
					   showScrollRight();
					   //jQuery("#id_toolbar_menu").css("width",(all_width-tempWidthAdd-2)+"px");
		           }
	        	   else
	        	   {
	        		   toolMenuDiv.scrollLeft = (toolMenuDiv.scrollLeft-incrementSize);
					   showScrollLeft();
					   showScrollRight();
			           //jQuery("#id_toolbar_menu").css("width",(all_width-tempWidthAdd*2-2)+"px");
		           }
	        	   return false;
	         }
		     function scrollRightClick(e){
		        	   var toolMenuDiv = jQuery("#id_menu_container")[0];
		        	   if((toolMenuDiv.scrollLeft+incrementSize)>=(toolMenuDiv.scrollWidth-toolMenuDiv.clientWidth-2))
		        	   {
		        		   toolMenuDiv.scrollLeft = (toolMenuDiv.scrollWidth-toolMenuDiv.clientWidth);
				           //jQuery("#id_toolbar_menu").css("width",(all_width-tempWidthAdd-2)+"px");
    					   showScrollLeft();
    					   hideScrollRight();
			           }
		        	   else
		        	   {
		        		   toolMenuDiv.scrollLeft = (toolMenuDiv.scrollLeft+incrementSize);
		        		  // tempWidthAdd = 30;
				           //jQuery("#id_toolbar_menu").css("width",(all_width-tempWidthAdd*2-2)+"px");
    					   showScrollLeft();
    					   showScrollRight();
			           }
	         }
	         var iframeContentLoadMask = null;
			 function loadAjaxTree(isTreeMenu)
			 {
				//通用
			    ajaxRequest({
				     url:'${pageContext.request.contextPath}/acl/queryMenus.acl',
				     method:'POST',
				     success:function(res){
					        var menuJson = eval("("+res.responseText+")");
					        globalMenuJson = menuJson;
					        var toolbar_menu_div = document.getElementById("id_toolbar_menu");
					        var toolbar_menus = menuJson ? menuJson.children : [];
					       //添加首页
					       var menuItemDiv = document.createElement("li");
					       //jQuery(menuItemDiv).attr("iconCls","icon-home-w");
					       jQuery(menuItemDiv).attr("isRelativedPath","true");
					       jQuery(menuItemDiv).attr("plain","true");
					       var pageUrl = '<mini:param name="pageUrlPath"/>';
                           var clickFunc = (function(pageUrl,currentMenuItemLinkTag){return function(){
                        	   changeMenuItemCssStyle(currentMenuItemLinkTag);
                            	var url = pageUrl;
                            	
                				if(null == iframeContentLoadMask){
                					iframeContentLoadMask = new tracywindyLoadMask("main_content","数据加载中  请稍等...");
                    			}
                    			
                    			url = getTracywindyRandomUrl(url);
                    			url += "&isLogOperator=true&operatorType=menu&LogOperatorModel=MENUCLASSONE&LogOperatorName="+escape(encodeURIComponent('<spring:message code="HomePage"  text="HomePage"/>'));
                				iframeContentLoadMask.show();
                				var oFrm = document.getElementById('id_iframe_content');
                				oFrm.src = (("true" == currentMenuItemLinkTag.getAttribute("isRelativedPath")) ? "${pageContext.request.contextPath}/" : "" )+url;
                   				oFrm.onload = oFrm.onreadystatechange = function() {
                   			     if (this.readyState && this.readyState != 'complete') return;
                   			     else {
                   			    	iframeContentLoadMask.hide();
                   			     }};
                            };})(pageUrl,menuItemDiv);
					        with(menuItemDiv)
					        {
					        	href="javascript:void(0)";
					        	id = "id_menuButton_0";
					        	style.display="none";
					        	//className="easyui-linkbutton m-btn-plain-active";
					        	innerHTML = '<a href="javascript:void(0)"><img src="${pageContext.request.contextPath}/images/ico01.png" title="首页" /><h2><spring:message code="HomePage"  text="HomePage"/></h2></a>';
                                onclick = clickFunc;
						    }
					        menuCount++;
					        toolbar_menu_div.appendChild(menuItemDiv);
					        jQuery(menuItemDiv).linkbutton();
					        //添加分隔符
					        var menuItemSeparator = document.createElement("a");
					        jQuery(menuItemSeparator).attr("class","x-div-menu-separator-image");
					        menuItemSeparator.innerHTML="&nbsp;";
					        menuItemSeparator.style.display="none";
					        toolbar_menu_div.appendChild(menuItemSeparator);
					        //开始迭代用户菜单
					        for(var tbIndex = 0;tbIndex<toolbar_menus.length;tbIndex++)
					        {
						        var tbMenu = toolbar_menus[tbIndex];
						        if(!compareAuthorities("<mini:param name='currentAllAuthoritiesString'/>",tbMenu.authorities))continue;
						        //增加菜单数量
						        menuCount++;
                                var icon_class = "icon-"+ tbMenu.icon.substring(0,tbMenu.icon.lastIndexOf(".")<0?0:tbMenu.icon.lastIndexOf("."))+"-w";
						        var menuItemDiv = null;
                                var isHasChildren = true;
                                var isRelativedPath = tbMenu.attributes['isRelativedPath'];
                                if(tbMenu.children.length<1)
                                {
                                	menuItemDiv = document.createElement("li");
                                	//jQuery(menuItemDiv).attr("iconCls",icon_class);
                                	jQuery(menuItemDiv).attr("isRelativedPath",isRelativedPath);
                                	jQuery(menuItemDiv).attr("plain","true");
                                	isHasChildren=false;
                                	var pageUrl = tbMenu.url;
                                	var menuName = tbMenu.name;
                                    var clickFunc = (function(pageUrl,currentMenuItemLinkTag,menuName){return function(){
                                    	
                                    	changeMenuItemCssStyle(currentMenuItemLinkTag);
                                    	var currentUrl = pageUrl;
                                    	currentUrl = currentUrl ? currentUrl : "errorPages/error404.bi";
                                     	var url = currentUrl;
                                     
                         				url = getTracywindyRandomUrl(url);
                         				url += "&isLogOperator=true&LogOperatorModel=MENUCLASSONE&operatorType=menu&LogOperatorName="+escape(encodeURIComponent(menuName));
                        				if(null == iframeContentLoadMask){
                        					iframeContentLoadMask = new tracywindyLoadMask("main_content","数据加载中  请稍等...");
                            			}
                        				iframeContentLoadMask.show();
                        				var oFrm = document.getElementById('id_iframe_content');
                        				//alert(currentMenuItemLinkTag.getAttribute("isRelativedPath"));
                        				oFrm.src = (("true" == currentMenuItemLinkTag.getAttribute("isRelativedPath")) ? "${pageContext.request.contextPath}/" : "" )+url;
                           				oFrm.onload = oFrm.onreadystatechange = function() {
                           			     if (this.readyState && this.readyState != 'complete') return;
                           			     else {
                           			    	iframeContentLoadMask.hide();
                           			     }};
                                     };})(pageUrl,menuItemDiv,menuName);
                                    menuItemDiv.onclick=clickFunc;
                                }
                                else
                                {
                                	menuItemDiv = document.createElement("li");
        					        //jQuery(menuItemDiv).attr("iconCls",icon_class);
        					        jQuery(menuItemDiv).attr("isRelativedPath",isRelativedPath);
        					        jQuery(menuItemDiv).attr("plain","true");
        					        jQuery(menuItemDiv).attr("menu","#id_menu_"+(tbIndex+1));
                                    if(isTreeMenu)
                                    {
                                    	var pageUrl = tbMenu.url;
                                    	var tbMenuId = tbMenu.id;
                                    	var menuName = tbMenu.name;
                                        var clickFunc = (function(pageUrl,currentMenuItemLinkTag,tbMenuId,menuName){return function(){
                                        	
                                        	changeMenuItemCssStyle(currentMenuItemLinkTag);
                             				var url = "acl/left_tree_menu_content.bi";
                             				url = getTracywindyRandomUrl(url);
                             				url+="&rootParentMenuId="+tbMenuId;
                             				url+="&pageUrl="+pageUrl;
                             				url += "&isLogOperator=true&LogOperatorModel=MENUCLASSONE&operatorType=menu&LogOperatorName="+escape(encodeURIComponent(menuName));
                            				if(null == iframeContentLoadMask){
                            					iframeContentLoadMask = new tracywindyLoadMask("main_content","数据加载中  请稍等...");
                                			}
                            				iframeContentLoadMask.show();
                            				var oFrm = document.getElementById('id_iframe_content');
                            				oFrm.src =  (("true" == currentMenuItemLinkTag.getAttribute("isRelativedPath")) ? "${pageContext.request.contextPath}/" : "" )+ url;
                               				oFrm.onload = oFrm.onreadystatechange = function() {
                               			     if (this.readyState && this.readyState != 'complete') return;
                               			     else {
                               			    	iframeContentLoadMask.hide();
                               			     }};
                                         };})(pageUrl,menuItemDiv,tbMenuId,menuName);
                                        menuItemDiv.onclick=clickFunc;
                                    }
                                }
						        with(menuItemDiv)
						        {	
						        	id = "id_menuButton_"+(tbIndex+1);
						        	href="javascript:void(0)";
						        	//className="easyui-menubutton ";
						        	var otherClass  = "";
						        	if( 2 == menuCount){
						        		//otherClass = "selected";
						        	}
						        	innerHTML = '<a href="javascript:void(0)" class="'+otherClass+'"><img src="${pageContext.request.contextPath}/images/i0'+(menuCount-1)+'.png" title="'+tbMenu.name+'" /><h2>'+tbMenu.name+'</h2></a>';
						        	
							    }
						        menuItemDiv.setAttribute("firstLevelMenuId", tbMenu.attributes.firstLevelMenuId);
							    if(!isHasChildren||(isTreeMenu))
							    {
							    	menuItemDiv.className="easyui-linkbutton";
								}
						        menuItemDiv.setAttribute("isHasChildren",isHasChildren);
						        var menuButtonItemDiv = document.createElement("div");
						        with(menuButtonItemDiv)
						        {
							        id="id_menu_"+(tbIndex+1);
							        style.width="150px";
							    }
							    if(!isTreeMenu)
							    {
							    	recursionTreeMenu(menuItemDiv,tbMenu,menuButtonItemDiv,true,'');
								}
						        toolbar_menu_div.appendChild(menuItemDiv);
						        document.body.appendChild(menuButtonItemDiv);
                                //添加分隔符
                                var menuItemSeparator = document.createElement("a");
					            jQuery(menuItemSeparator).attr("class","x-div-menu-separator-image");
						        menuItemSeparator.innerHTML="&nbsp;";
						        menuItemSeparator.style.display="none";
						        toolbar_menu_div.appendChild(menuItemSeparator);
					        }
					        /*for(var tbIndex = 0;tbIndex<toolbar_menus.length;tbIndex++)
					        {
						        var tempJqueryObj = jQuery('#id_menuButton_'+(tbIndex+1));
						        
						        if((toolbar_menus[tbIndex].children.length > 0)&&!isTreeMenu)
						        {
						        	tempJqueryObj.menubutton({plain:true});
							    }
						        else
						        {
						        	tempJqueryObj.linkbutton({plain:true});
							    }
					        }
					        
					        jQuery(".easyui-linkbutton").each(function(){
                                   this.onmouseover=function(){jQuery("body>div.menu-top").menu("hide");};
						    });
					        jQuery(".x-div-menu-separator-image").each(function(){
                                this.onmouseover=function(){jQuery("body>div.menu-top").menu("hide");};
						    });
					        jQuery("#id_mainPage_header")[0].onmouseover = function(){jQuery("body>div.menu-top").menu("hide");};*/
                            //菜单总宽度
                            //menuAllWidth = Math.max(menuCount*102,jQuery("#id_toolbar_menu")[0].scrollWidth);
                            //menuAllWidth = Math.max(jQuery("#id_toolbar_menu")[0].offsetWidth,jQuery("#id_toolbar_menu")[0].scrollWidth);
                           //menuAllWidth = (menuCount-1)*100+60;
                           menuAllWidth = 0;
                           jQuery("#id_toolbar_menu>li").each(function(i){
                        	   menuAllWidth+=this.offsetWidth;
                           });
                           jQuery("#id_toolbar_menu").css("width",menuAllWidth);
                            if(menuAllWidth <= (all_width-topLeftAndRightWidth+12))
                            {
                            	iframe_clientHeight = iframe_clientHeight+18;
                            	isNeedScroll = false;
                            }
                            else
                            {
        					   hideScrollLeft();
        					   showScrollRight();
                            }
                            jQuery(".menu-item").css("height","24px");
                        	//顶部导航切换
                        	/*jQuery(".nav li a").click(function(){
                        		$(".nav li a.selected").removeClass("selected");
                        		$(this).addClass("selected");
                        	});	*/
                            //jQuery(".menu-item").css("line-height","32px");
				     },
				     failure:function(res){},
				     async:false,
				     timeout:30*1000
				});
		    };
		    function hideScrollLeft(){
		    	jQuery("#id_scroll_left").html("&nbsp;");
		    	jQuery("#id_scroll_left")[0].onclick = null;
		    	//scrollRightClick
		    }
		    function showScrollLeft(){
		    	jQuery("#id_scroll_left").html('<a class="scroll-left" href="javascript:void(0);">&lt;</a>');
		    	jQuery("#id_scroll_left")[0].onclick = (scrollLeftClick);
		    }
		    function hideScrollRight(){
		    	jQuery("#id_scroll_right").html("&nbsp;");
		    	jQuery("#id_scroll_right")[0].onclick = null;
		    }
		    function showScrollRight(){
		    	jQuery("#id_scroll_right").html('<a class="scroll-right" href="javascript:void(0);">&gt;</a>');
		    	jQuery("#id_scroll_right")[0].onclick = (scrollRightClick);
		    }
		    function changeMenuItemCssStyle(currentMenuItemLinkTag)
		    {
		    	/*jQuery("#id_toolbar_menu a.easyui-linkbutton").removeClass("m-btn-plain-active");
		    	jQuery("#id_toolbar_menu a.easyui-menubutton").removeClass("m-btn-plain-active");
		    	jQuery("#id_toolbar_menu a.easyui-linkbutton").removeClass("menu-item-selected");
		    	jQuery("#id_toolbar_menu a.easyui-menubutton").removeClass("menu-item-selected");
		    	jQuery(currentMenuItemLinkTag).addClass("m-btn-plain-active");
		    	jQuery(currentMenuItemLinkTag).addClass("menu-item-selected");*/
		    	jQuery(currentMenuItemLinkTag).find("a").each(function(){
            		$(".nav li a.selected").removeClass("selected");
            		$(this).addClass("selected");
            	});	
			} 
	       function recursionTreeMenu(topMenuItemDiv,menuJson,menuButtonItemDiv,isInit,pageUrl)
				{
					var menus = menuJson.children;
					for(var mIndex = 0;mIndex<menus.length;mIndex++)
			        {
				        var tbMenu = menus[mIndex];
				        if(!compareAuthorities("<mini:param name='currentAllAuthoritiesString'/>",tbMenu.authorities))continue;
	                    var icon_class = "icon-"+ tbMenu.icon.substring(0,tbMenu.icon.lastIndexOf(".")<0?0:tbMenu.icon.lastIndexOf("."))+"-w";
				        var menuItemDiv = document.createElement("div");
				        jQuery(menuItemDiv).attr("iconCls",icon_class);
	                    if(tbMenu.children.length > 0)
	                    {
	                        var insertSpanMenuItem = document.createElement("span");
	                        with(insertSpanMenuItem)
	                        {
	                        	innerHTML=tbMenu.name;
	                        }
	                        menuItemDiv.appendChild(insertSpanMenuItem);
	                        var childrenMenusDiv = document.createElement("div");
	                        jQuery(childrenMenusDiv).attr("iconCls",icon_class);
	                        with(childrenMenusDiv)
	                        {
	                            style.width="180px";
	                        }
	                        recursionTreeMenu(topMenuItemDiv,tbMenu,childrenMenusDiv,true,pageUrl);
	                        menuItemDiv.appendChild(childrenMenusDiv);
	                    }
	                    else
	                    {
	                        var pageUrl = tbMenu.url;
	                        var clickFunc = (function(pageUrl,currentMenuItemLinkTag){return function(){
	                        	changeMenuItemCssStyle(currentMenuItemLinkTag);
	                        	var url = pageUrl;	                        	
	            				url = getTracywindyRandomUrl(url);
	            				document.getElementById('id_iframe_content').src = "${pageContext.request.contextPath}/"+url;
	                        };})(pageUrl,topMenuItemDiv);
	                    	with(menuItemDiv)
	                        {
	                    		innerHTML=tbMenu.name;
	                    		onclick=clickFunc;
	                        }
	                    }
	                    menuButtonItemDiv.appendChild(menuItemDiv);
				    }
				}
				function compareAuthorities(userAuthoritiesStr,menuAuthoritiesStr)
				{
					return true;
					if(!userAuthoritiesStr)return false;
					if(!menuAuthoritiesStr)return false;
					var userAuthorities = userAuthoritiesStr.split(",");
					var menuAuthorities = menuAuthoritiesStr.split(",");
					var pass = false;
					for(var userIndex = 0;userIndex<userAuthorities.length;userIndex++)
					{
						for(var menuIndex = 0;menuIndex<menuAuthorities.length;menuIndex++)
						{
							if(userAuthorities[userIndex] == menuAuthorities[menuIndex])
							{
								pass=true;
								break;
							}
						}
					}
					return pass;
				}
			
			//消息函数开始
			function shwoMessageWindwos(){
				var isClosable = true;
			/* 	if(isForceUpdatePassword)
				{
					isClosable = false;
					jQuery("#id_station_message").hide();
				} */
				//id_message_table
				
				jQuery('#id_message_table').tabs({  
				    border:false,   
				    onSelect:function(title){   
				       // alert(title+' is selected');   
				    }   
				});  

				jQuery("#id_station_message").show();
				jQuery("#id_station_message").window({
		        	zIndex:9000,
		        	draggable:true,
		        	top:150,
		        	resizable:false,
		        	shadow:true,
		        	modal:true,
		        	inline:false,
		        	collapsible:false,
		        	minimizable:false,
		        	maximizable:false,
		        	closable:isClosable,
		        	closed:false
		        });
		        var messageWidthAdd = 0;
		        if(isIE){
		        	messageWidthAdd = 10;
			    }
		        
				 
			}
			function sendMessage(){
				
				
				 var names = jQuery('#id_send_names_id').val();
				 var content = jQuery('#id_send_contents').val();
				 var data = {receives : names, content : content};
				 
				 if(names == null || names == ''){
					 alert("请输入收件人");
					 return;
				 }
				 jQuery.post('msg/sendMsg.action', data,  function(i,v){
					 if(i == 'ok'){
						jQuery('#id_send_names_id').val('');
						jQuery('#id_send_names').val('');
						jQuery('#id_send_contents').val('');
						alert("发送成功"); 
					 }
				});
				
			}
 			jQuery(function(){
				new tracywindyCommonUserSelection({
					 hiddenInput:'id_send_names_id',
					 displayInput:'id_send_names',
					 id:'id_sendMessageChoosor',
					 isMultiSelect:true
				});
			}); 
			//消息函数结束
			//修改密码
			function showModifyPasswordWindow(isForceUpdatePassword)
			{
				var isClosable = true;
				if(isForceUpdatePassword)
				{
					isClosable = false;
					jQuery("#id_cancel_update_password").hide();
				}
				jQuery("#id_modifyPasswordDetailInfoForm").show();
				jQuery("#id_modifyPasswordDetailInfoForm").window({
		        	zIndex:9000,
		        	draggable:true,
		        	top:150,
		        	resizable:true,
		        	shadow:true,
		        	modal:true,
		        	inline:false,
		        	collapsible:false,
		        	minimizable:false,
		        	maximizable:false,
		        	closable:isClosable,
		        	closed:false
		        });
		        jQuery("#id_userName").val("${sessionScope.login_username}");
		        jQuery("#id_realName").val("${sessionScope.login_realname}");
		        jQuery("#id_telephone").val("${sessionScope.login_usertelephone}");
		        jQuery("#id_email").val("${sessionScope.login_useremail}");
		        jQuery("#id_oldPassword").val("");
				jQuery("#id_newPassword").val("");
				jQuery("#id_confirmNewPassword").val("");
			    jQuery("#id_modifyPasswordDetailInfoForm").window('open');
			}
			//个人设置
			function showPersonalSettingWindow(){
				jQuery("#id_modifyPersonalSettingDetailInfoFormWindow").show();
				jQuery("#id_modifyPersonalSettingDetailInfoFormWindow").window({
		        	zIndex:9000,
		        	draggable:true,
		        	top:50,
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
				jQuery("#id_modifyPersonalSettingDetailInfoFormWindow").window('open');
			}
			jQuery(function(){
				jQuery('#id_personalSetting_tabs').tabs({   
				    border:false,   
				    onSelect:function(title){   
				    }   
				}); 
		    });
		    //显示在线用户信息
		    function showOnlineUsersWindow()
		    {
				jQuery("#id_online_users").show();
				jQuery("#id_online_users").window({
		        	zIndex:9000,
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
			}
			function doModifyPasswordOperation()
			{
				var telephone = jQuery("#id_telephone").val();
				var email  = jQuery("#id_email").val();
				var currentUserId = "<mini:param name='login_userid'/>";
				var realOldPassword = "<mini:param name='login_userpassword'/>";
				//if(""==telephone){alert("联系人不能为空");jQuery("#id_telephone").focus();return;}
				//if(""==email){alert("邮箱地址不能为空");jQuery("#id_email").focus();return;}
				var oldPassword = jQuery("#id_oldPassword").val();
				var newPassword = jQuery("#id_newPassword").val();
				var confirmNewPassword = jQuery("#id_confirmNewPassword").val();
				if(newPassword.length < <%=com.tenwa.kernal.utils.ResourceUtil.getMinPasswordLength()%>)
				{
					alert("新密码不能少于<%=com.tenwa.kernal.utils.ResourceUtil.getMinPasswordLength()%>位!");
					jQuery("#id_newPassword").focus();
					return;
			    }
			    /*if(!(new RegExp("<%=com.tenwa.kernal.utils.ResourceUtil.getMinPasswordLength()%>").test(newPassword))){
                    alert("密码必须包括2种以上字符（可选，大写字母、小写字母、数字、特殊字符）");
                    jQuery("#id_newPassword").focus();
				    return;
			    }*/
			    /*if(!/\d+/gi.test(newPassword)){
			    	alert("密码必须至少包含一个数字");
				    jQuery("#id_newPassword").focus();
				    return;
				}
				else if(!/[a-zA-Z]+/gi.test(newPassword)){
				    alert("密码必须包含一个字母");
				    jQuery("#id_newPassword").focus();
				    return;
				}*/
				if(""==oldPassword){alert("原始密码不能为空");jQuery("#id_oldPassword").focus();return;}
				//if(realOldPassword!=oldPassword){alert("旧密码不正确");jQuery("#id_oldPassword").focus();return;}
				if(""==newPassword){alert("新密码不能为空");jQuery("#id_newPassword").focus();return;}
				if(confirmNewPassword!=newPassword){alert("确认密码应与新密码保持一致");jQuery("#id_confirmNewPassword").focus();return;}
                var params ={};
				params['method']='modifyUserPassword';
				params['currentUserId']=currentUserId;
				params['oldPassword']=oldPassword;
				params['newPassword']=newPassword;
				params['telephone']=telephone;
				params['email']=email;
				ajaxRequest({
		   		     url:'${pageContext.request.contextPath}/acl/modifyUserPassword.acl',
		   		     method:'POST',
		   		     success:function(res){
		   		    	  var returnInfo = JsonUtil.decode(res.responseText);
			   		      if('success'==returnInfo.status)
			   		      {
			   		    	jQuery("#id_modifyPasswordDetailInfoForm").window("close");
			   		    	alert("信息修改成功,系统将退出，请你重新登录！！");
			   		    	window.top.location.href = "${pageContext.request.contextPath}/j_spring_security_logout";
				   		  }
			   		      else
			   		      {
			   		    	alert(returnInfo.message);
			   		    	jQuery("#id_oldPassword").focus();
				   		  }
		   		     },
		   		     failure:function(res){
			   		 },
		   		     params:params
	   		 });
		    }
		function showDelegateWindow()
		{
	        jQuery("#id_setDelegateWindowContainer").show();
	        jQuery("#id_setDelegateWindowContainer").window({top:50});
	        jQuery("#id_setDelegateWindowContainer").window('open');
		}
	    function logoutUserByUserName()
	    {
             window.open("${pageContext.request.contextPath}/logoutUserByUserName.acl?method=logoutUserByUserName","_blank");
		}
		jQuery(function(){
				if('true'=='<mini:param name="isForceUpdatePassword"/>')
				{
					jQuery("#id_loadMaskContainer").hide();
					showModifyPasswordWindow(true);
					return;
				}
	   });
	   /**加载左侧菜单**/
	     function loadLeftMenu(menuContainerIdOrObject,menuRootId,isTopMenu)
         {
        	var url = '${pageContext.request.contextPath}/acl/queryMenus.acl?mathRandom='+Math.random();
        	var data = {rootParentMenuId:menuRootId};
        	var callBackFunc = function(jsonData){
            	var menuContainer = null;
        		if(!(typeof(menuContainerIdOrObject)=='object'))
        		{
        			menuContainer = document.getElementById(menuContainerIdOrObject);
        		}
        		else
        		{
        			menuContainer = menuContainerIdOrObject;
                }
                
			    var menuButtonItemUL = document.createElement("div");
			    menuButtonItemUL.id = "id_menu_container";
			    menuContainer.appendChild(menuButtonItemUL);
        		recursionLeftMenu(jsonData,menuButtonItemUL,true);
        		addOperToLeftMenu();
            };
            var contentType = "json";
        	jQuery.post(url,data,callBackFunc,contentType);
         }
	     function recursionLeftMenu(menuJson,menuButtonItemUL,isTopMenu)
		 {
			    var imgSrcPath = "${pageContext.request.contextPath}/images/menu_topline.gif";
				var menus = menuJson.children;
				for(var mIndex = 0;mIndex<menus.length;mIndex++)
		        {
			        var tbMenu = menus[mIndex];
			        if(!compareAuthorities("<mini:param name='currentAllAuthoritiesString'/>",tbMenu.authorities))continue;
                    var icon_class = "icon-"+ tbMenu.icon.substring(0,tbMenu.icon.lastIndexOf(".")<0?0:tbMenu.icon.lastIndexOf("."))+"-w";
			        var menuItemLI = null;
			        var insertHrefMenuItem = null;
			        var childrenCount = tbMenu.children.length ;
			        
                   if(childrenCount > 0)
                   {
                       var tempHtml = "";
                           tempHtml+='<h1><a href="javascript:void(0)" class="type">'+tbMenu.name+'</a></h1>';
        				   with(menuButtonItemUL)
        				   {
            				   innerHTML+=tempHtml;
            			   }    
                       var contentDiv = document.createElement("div");
                           tempHtml = "";
	                       tempHtml+='<table width="100%" border="0" cellspacing="0" cellpadding="0">';
	                       tempHtml+='<tr>';
	                       tempHtml+='<td><img src="'+imgSrcPath+'" width="182" height="5" /></td>';
	                       tempHtml+='</tr>';
	                       tempHtml+='</table>';
                           with(contentDiv)
                           {
                        	   className="content";
                        	   innerHTML=tempHtml;
                           }
                       menuButtonItemUL.appendChild(contentDiv);
                       
                       var childrenMenusUL = document.createElement("ul");
    			       contentDiv.appendChild(childrenMenusUL);
    			       with(childrenMenusUL)
    			       {
    			    	   className = "MM";
        			   }
    			       childrenMenusUL.setAttribute("superTitle",tbMenu.name);
                       with(childrenMenusUL)
                       {
                    	   //className = "box-list hidden";
                       }
                       recursionLeftMenu(tbMenu,childrenMenusUL,false);
                       contentDiv.appendChild(childrenMenusUL);
                   }
                   else
                   {
                	   var tempHtml = "";
	   			        if(isTopMenu)
				        {
	   			        	tempHtml+='<h1 class="type"><a href="javascript:void(0)">'+tbMenu.name+'</a></h1>';
					        with(menuButtonItemUL)
					        {
					        	innerHTML+= tempHtml;
						    }
				        }
	   			        else
	   			        {
	   			        	var pageUrl = tbMenu.url;
		                    var url = "${pageContext.request.contextPath}/"+pageUrl;		                  
		           			url = getTracywindyRandomUrl(url);
	                 	    menuItemLI = document.createElement("li");
	                 	    var aLink = document.createElement("a");
	                 	    var pageUrl = tbMenu.url;
	                 	   with(aLink)
	                 	   {
	                 		  href      = "javascript:void(0);";
		                 	  innerHTML = tbMenu.name;
		                 	  setAttribute("pageUrl",pageUrl);
		                   }
	                       menuItemLI.appendChild(aLink);
	                	   menuButtonItemUL.appendChild(menuItemLI);
	   	   			    }
                   }     
			    }
			}
			function addOperToLeftMenu()
			{
				jQuery(function(){
				     		var contents = document.getElementsByClassName('content');
				    		var toggles = document.getElementsByClassName('type');
				    	
				    		var myAccordion = new fx.Accordion(
				    			toggles, contents
				    		);
				    		 myAccordion.showThisHideOpen(contents[0]);
				        	 var currentUserIndex = '<mini:param name="pageUrlPath"/>';
				        	 if(currentUserIndex)
				        	 {
				        		 jQuery("#id_frameRight")[0].src = "${pageContext.request.contextPath}/"+currentUserIndex;
				             }	
				             jQuery("#id_menu_container div.content ul.MM li>a").click(function(e){
					                var pageUrl = jQuery(this).attr("pageUrl");
	                        	    document.getElementById('id_frameRight').src = "${pageContext.request.contextPath}/loadingIndex.html";
		                            var parentNodeTitle = jQuery(this).parent().parent().attr("superTitle");
		                            var titleHtml = parentNodeTitle+"&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;"+jQuery(this).text();
		                            jQuery("#id_goPage").html(titleHtml);
		                            jQuery("#id_menu_container div.content ul.MM li>a").removeClass("linkclicked");
		                            jQuery(this).addClass("linkclicked");
			                       	var url = "${pageContext.request.contextPath}/"+pageUrl;
			                      
			           				url = getTracywindyRandomUrl(url);
			           				document.getElementById('id_frameRight').src = url;
			           				
					        });
				});
		    }
	         function loadLeftMenu_levelN(menuContainerIdOrObject,menuRootId,isTopMenu)
	         {
	        	var url = '${pageContext.request.contextPath}/acl/queryMenus.acl?mathRandom='+Math.random();
	        	var data = {rootParentMenuId:menuRootId};
	        	var callBackFunc = function(jsonData){
	            	var menuContainer = null;
	        		if(!(typeof(menuContainerIdOrObject)=='object'))
	        		{
	        			menuContainer = document.getElementById(menuContainerIdOrObject);
	        		}
	        		else
	        		{
	        			menuContainer = menuContainerIdOrObject;
	                }
	                
				    var menuButtonItemUL = document.createElement("div");
				    menuButtonItemUL.className = "pnav-cnt";
				    menuContainer.appendChild(menuButtonItemUL);
	        		recursionLeftMenu_levelN(jsonData,menuButtonItemUL,true);
	        		addOperToLeftMenu_levelN();
	            };
	            var contentType = "json";
	        	jQuery.post(url,data,callBackFunc,contentType);
	         }
		     function recursionLeftMenu_levelN(menuJson,menuButtonItemUL,isTopMenu)
			 {
					var menus = menuJson.children;
					for(var mIndex = 0;mIndex<menus.length;mIndex++)
			        {
				        var tbMenu = menus[mIndex];
				        if(!compareAuthorities("<mini:param name='currentAllAuthoritiesString'/>",tbMenu.authorities))continue;
	                    var icon_class = "icon-"+ tbMenu.icon.substring(0,tbMenu.icon.lastIndexOf(".")<0?0:tbMenu.icon.lastIndexOf("."))+"_w";
				        var menuItemLI = null;
				        var insertHrefMenuItem = null;
				        var childrenCount = tbMenu.children.length ;
				        
				        if(isTopMenu)
				        {
				        	menuItemLI = document.createElement("div");
				        	menuItemLI.className = "pnav-box";
				        	insertHrefMenuItem = document.createElement("div");

		                    var tempHtml =  "";      
		                    if(childrenCount>0)
		                    {
		                        tempHtml+='<a class="btn-fold " href=""></a>';
			                    tempHtml+='<a class="btn-unfold hidden" href=""></a>';
			                } 
		                    else
		                    {
		                    	tempHtml+="&nbsp;&nbsp;"; 
			                }     
		                    if(childrenCount>0)
		                    {
		                    	tempHtml+='<span class="menu-content">'+tbMenu.name+'</span>';
			                } 
		                    else
		                    {
					        	var pageUrl = tbMenu.url;
		                       	var url = "${pageContext.request.contextPath}/"+pageUrl;
		                       
		           				url = getTracywindyRandomUrl(url);
					        	tempHtml+='<b>';
					        	tempHtml+='<a  href="'+url+'" target="name_rightFrame">'+tbMenu.name+'</a>';
					        	tempHtml+='</b>';
			                }  	   
		                    with(insertHrefMenuItem)
		                    {
		                       className = "box-title";
		                       innerHTML= tempHtml;
		                    }
		                    menuItemLI.appendChild(insertHrefMenuItem);
				        }
				        else
				        {
				        	menuItemLI = document.createElement("li");
				        	
				        	var tempHtml =  "";  
				        	if(childrenCount > 0)
				        	{
					        	tempHtml+='<a class="btn-fold" href=""></a>';
					        	tempHtml+='<a class="btn-unfold hidden" href=""></a>';
					        }
		                    else
		                    {
		                    	tempHtml+="&nbsp;&nbsp;"; 
			                }  
				        	if(childrenCount > 0)
				        	{
					        	tempHtml+='<b>';
					        	tempHtml+='<a  href="">'+tbMenu.name+'</a>';
					        	tempHtml+='</b>';
				        	}
				        	else
				        	{
					        	var pageUrl = tbMenu.url;
		                       	var url = "${pageContext.request.contextPath}/"+pageUrl;
		                       
		           				url = getTracywindyRandomUrl(url);
					        	tempHtml+='<b>';
					        	tempHtml+='<a  href="'+url+'" target="name_rightFrame">'+tbMenu.name+'</a>';
					        	tempHtml+='</b>';
						    }

				        	with(menuItemLI)
				        	{
				        		innerHTML = tempHtml;
					        }
					    }
	                   
	                   if(childrenCount > 0)
	                   {
	                       var childrenMenusUL = document.createElement("ul");
	                       with(childrenMenusUL)
	                       {
	                    	   className = "box-list hidden";
	                       }
	                       recursionLeftMenu_levelN(tbMenu,childrenMenusUL,false);
	                       menuItemLI.appendChild(childrenMenusUL);
	                   }
	                   else
	                   {
	                	   /*  var pageUrl = tbMenu.url;
	                        var clickFunc = (function(pageUrl){return function(){
		                       	var url = "${pageContext.request.contextPath}/"+pageUrl;
		           				if(url.indexOf('?')>-1)
		           				{
		           					url+=("&tracywindyRandom=" +Math.random());
		           			    }
		           				else
		           				{
		           					url+=("?tracywindyRandom=" +Math.random());
		           				}
		           				window.open(url);
		           				//document.getElementById('id_iframe_content').src = "${pageContext.request.contextPath}/"+url;
		                       };
	                       })(pageUrl);
	                   	   with(insertHrefMenuItem)
	                       {
	                   		 onclick=clickFunc;
	                       }*/
	                   }     
	                   menuButtonItemUL.appendChild(menuItemLI);
				    }
				}
				function addOperToLeftMenu_levelN()
				{
					(function($){
						$("div.pnav-box div.box-title a.btn-fold").bind("click", function(e){
								e.preventDefault();
								var eleTitle = $(this.parentNode);
								$(this).addClass("hidden");
								eleTitle.children("a.btn-unfold").removeClass("hidden");
								$(eleTitle.parent()).children("ul.box-list").removeClass("hidden");
							});
							$("div.pnav-box div.box-title a.btn-unfold").bind("click", function(e){
								e.preventDefault();
								var eleTitle = $(this.parentNode);
								$(this).addClass("hidden");
								eleTitle.children("a.btn-fold").removeClass("hidden");
								$(eleTitle.parent()).children("ul.box-list").addClass("hidden");
							});
							$("div.pnav-box ul.box-list a.btn-fold").bind("click", function(e){
								e.preventDefault();
								var eleTitle = $(this.parentNode);
								$(this).addClass("hidden");
								eleTitle.children("a.btn-unfold").removeClass("hidden");
								eleTitle.children("ul.box-list").removeClass("hidden");
							});
							$("div.pnav-box ul.box-list a.btn-unfold").bind("click", function(e){
								e.preventDefault();
								var eleTitle = $(this.parentNode);
								$(this).addClass("hidden");
								eleTitle.children("a.btn-fold").removeClass("hidden");
								eleTitle.children("ul.box-list").addClass("hidden");
							});
					})(jQuery);
			    }
		    /***加载左侧菜单完成******/
		    function recusionParentTitle(cn,parentNodeTitle)
		    {
			    if(-1 == cn.id)
			    {
				    return parentNodeTitle;
				}
			    else
			    {
			    	var pnt = cn.name;
				    if(parentNodeTitle)
				    {
				    	pnt = cn.name+"&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;";
					}
				    parentNodeTitle=(pnt+parentNodeTitle);
			    	return recusionParentTitle(cn._p,parentNodeTitle);
				}
			}
		 /******加载左侧树形菜单*****/
		 //加载左侧非树形菜单
		   //  loadLeftTreeMenu("id_frame-left-content",menuRootId,true);
		 function loadLeftTreeMenuNonTree(menuContainerIdOrObject,menuRootId,currentReloadMenuId) {
			/*	<dd>
				<div class="title"><span><img src="${pageContext.request.contextPath}/images/leftico01.png" /></span>管理信息</div>
				<ul class="menuson">
					<li><cite></cite><a href="index.html" target="rightFrame">首页模版</a><i></i></li>
					<li class="active"><cite></cite><a href="right.html" target="rightFrame">数据列表</a><i></i></li>
					<li><cite></cite><a href="imgtable.html" target="rightFrame">图片数据表</a><i></i></li>
					<li><cite></cite><a href="form.html" target="rightFrame">添加编辑</a><i></i></li>
					<li><cite></cite><a href="imglist.html" target="rightFrame">图片列表</a><i></i></li>
					<li><cite></cite><a href="imglist1.html" target="rightFrame">自定义</a><i></i></li>
					<li><cite></cite><a href="tools.html" target="rightFrame">常用工具</a><i></i></li>
					<li><cite></cite><a href="filelist.html" target="rightFrame">信息管理</a><i></i></li>
					<li><cite></cite><a href="tab.html" target="rightFrame">Tab页</a><i></i></li>
					<li><cite></cite><a href="error.html" target="rightFrame">404页面</a><i></i></li>
				</ul>
			</dd>	  */
			    var findLeftMenuActivedIndex = 1;
			    var currentUrl = "";
	        	var url = '${pageContext.request.contextPath}/acl/queryMenus.acl?mathRandom='+Math.random();
	        	var data = {rootParentMenuId:menuRootId};
	        	var callBackFunc = function(jsonData){
	            	var menuContainer = null;
	        		if(!(typeof(menuContainerIdOrObject)=='object'))
	        		{
	        			menuContainer = document.getElementById(menuContainerIdOrObject);
	        		}
	        		else
	        		{
	        			menuContainer = menuContainerIdOrObject;
	                }
	        		var $menuContainer = jQuery(menuContainer);
	    	 		var menus = jsonData.children;
	    	 		for(var mIndex = 0;mIndex<menus.length;mIndex++)
	    	 		{
	    	 		   var tbMenu = menus[mIndex];
	    	 		   if( menuRootId == tbMenu.id){
	    	 			  var menuActiveIndex = 0; 
	    	 			  findLeftMenuTreeIndex = 1;
	    	 			  //recursionLeftTreeMenuData(tbMenu,tree,true);
	    	 			  var ddChildrenMenus = tbMenu["children"];
	    	 			  for(var ddIndex = 0;ddIndex < ddChildrenMenus.length;ddIndex++){
	    	 				  var ddMenu  = ddChildrenMenus[ddIndex];
	    	 				  var $ddMenuContainer = jQuery("<dd></dd>");
	    	 				  $menuContainer.append($ddMenuContainer);
	    	 				  $ddMenuContainer.attr({
	    	 					  index : ((ddIndex+1)+"")
	    	 				  }); 
	    	 				  var $menuDDTitle = jQuery('<div class="title"><span><img width="17" height="17" src="${pageContext.request.contextPath}/images/leftico' + ddIndex + '.png" /></span>'+ddMenu.name+'</div>');
	    	 				  $ddMenuContainer.append($menuDDTitle);
	    	 				  var $menuULContentContainer = jQuery('<ul class="menuson"></ul>'); 
	    	 				  $ddMenuContainer.append($menuULContentContainer);
	    	 				  var liChildrenMenus = ddMenu["children"];
	    	 				  var liChildrenMenusLen = liChildrenMenus.length;
	    	 				  for(var liIndex = 0;liIndex<liChildrenMenusLen;liIndex++){
	    	 					  var liMenu = liChildrenMenus[liIndex];
	    	 					  var liMenuChildren = liMenu["children"];
	    	 					  var liMenuChildrenLen = liMenuChildren.length;
	    	 					  if(0 < liMenuChildrenLen){
	    	 						 for(var ii =0 ;ii< liMenuChildrenLen;ii++){
	    	 							  liMenu = liMenuChildren[ii];
			    	 					  var $menuLi   = jQuery('<li><cite></cite><a href="javascript:void(0);" onclick="">'+liMenu.name+'</a><i></i></li>');
			    	 					  url = liMenu.url;
			    	 					  if(url.indexOf('?')>-1){
			    	 						 url += "&isLogOperator=true&LogOperatorModel=MENUCLASSTWO&operatorType=menu&LogOperatorName="+escape(encodeURIComponent(liMenu.name));		
			    	 					  }else {
			    	 						 url += "?isLogOperator=true&LogOperatorModel=MENUCLASSTWO&operatorType=menu&LogOperatorName="+escape(encodeURIComponent(liMenu.name));
			    	 					  }
			    	 					  $menuLi.attr({
			    	 						  isRelativedPath : liMenu.isRelativedPath,
				    	 					  index : ((++menuActiveIndex)+""),
				    	 					  url   : url
				    	 				  });
			    	 					  $menuULContentContainer.append($menuLi);
			    	 					  if(findLeftMenuActivedIndex == menuActiveIndex){
			    	 						 $menuLi.addClass("active");
			    	 						 currentUrl = url;
			    	 						 jQuery("#id_tilteOperName").html(liMenu.name);
			    	 					  }
	    	 						 }
	    	 					  }else{
		    	 					  var $menuLi   = jQuery('<li><cite></cite><a href="javascript:void(0);" onclick="">'+liMenu.name+'</a><i></i></li>');
		    	 					  url = liMenu.url;
		    	 					  if(url.indexOf('?')>-1){
		    	 						 url += "&isLogOperator=true&LogOperatorModel=MENUCLASSTWO&operatorType=menu&LogOperatorName="+escape(encodeURIComponent(liMenu.name));		
		    	 					  }else {
		    	 						 url += "?isLogOperator=true&LogOperatorModel=MENUCLASSTWO&operatorType=menu&LogOperatorName="+escape(encodeURIComponent(liMenu.name));
		    	 					  }
		    	 					  $menuLi.attr({
		    	 						  isRelativedPath : liMenu.isRelativedPath,
			    	 					  index : ((++menuActiveIndex)+""),
			    	 					  url   : url
			    	 				  });
		    	 					  $menuULContentContainer.append($menuLi);
		    	 					  /* if(findLeftMenuActivedIndex == menuActiveIndex){
		    	 						 $menuLi.addClass("active");
		    	 						 currentUrl = liMenu.url;
		    	 						 jQuery("#id_tilteOperName").html(liMenu.name);
		    	 					  } */
		    	 					  if(liMenu.id == currentReloadMenuId){
		    	 						 $menuLi.addClass("active");
	    	 						 	 currentUrl = liMenu.url;
	    	 						 	 jQuery("#id_tilteOperName").html(liMenu.name);
		    	 					  }
	    	 					  }
	    	 				  }
	    	 				  if(0 == liChildrenMenusLen){
	    	 					 var liMenu = ddMenu;
	    	 					  var $menuLi   = jQuery('<li><cite></cite><a href="javascript:void(0);" onclick="">'+liMenu.name+'</a><i></i></li>');
	    	 					  if(url.indexOf('?')>-1){
	    	 						 url += "&isLogOperator=true&LogOperatorModel=MENUCLASSTWO&operatorType=menu&LogOperatorName="+escape(encodeURIComponent(liMenu.name));		
	    	 					  }else {
	    	 						 url += "?isLogOperator=true&LogOperatorModel=MENUCLASSTWO&operatorType=menu&LogOperatorName="+escape(encodeURIComponent(liMenu.name));
	    	 					  }
	    	 					  $menuLi.attr({
	    	 						  isRelativedPath : liMenu.isRelativedPath,
		    	 					  index : ((++menuActiveIndex)+""),
		    	 					  url   : liMenu.url
		    	 				  });
	    	 					  $menuULContentContainer.append($menuLi);
	    	 					  if(findLeftMenuActivedIndex == menuActiveIndex){
	    	 						 //$menuLi.addClass("active");
	    	 						 //currentUrl = liMenu.url;
	    	 						 //jQuery("#id_tilteOperName").html(liMenu.name);
	    	 					  }
	    	 				  }
	    	 			  }
	    	 				//导航切换
		    	 			$menuContainer.find(".menuson li").click(function(){
	    	 					$menuContainer.find(".menuson li.active").removeClass("active")
	    	 					jQuery(this).addClass("active");
	    	 					jQuery("#id_tilteOperName").html(jQuery(this).text());
	    	 					var currentUrl = jQuery(this).attr("url");
	    	 					document.getElementById('id_frameRight').src = "${pageContext.request.contextPath}/"+currentUrl;
	    	 				});
		    	 				
		    	 			$menuContainer.find('.title').click(function(){
	    	 					var $ul = $(this).next('ul');
	    	 					$menuContainer.find('dd').find('ul').slideUp();
	    	 					if($ul.is(':visible')){
	    	 						$(this).next('ul').slideUp();
	    	 					}else{
	    	 						$(this).next('ul').slideDown();
	    	 					}
	    	 				});
	    	 				break;
	    		 		}
	    	 		}
	    	 		if($("li.active")[0]){
	    	 			if($("li.active").parent().parent().attr("index") != 1){
	    	 				$("li.active").parent().parent().find('.title').click();
	    	 			}
	    	 		}else{
	    	 			$("li[index=1]").addClass("active");
	    	 			currentUrl = currentUrl ? currentUrl : $("li[index=1]").attr("url");
	    	 			jQuery("#id_tilteOperName").html($("li[index=1] a").html());
	    	 		}
	    	 		
	            	currentUrl = currentUrl ? currentUrl : "errorPages/error404.bi";
	            	//currentUrl = "acl/right_default_content.bi";
	            	//currentUrl = "acl/left_tree_menu_map_content_block.bi";
	            	//alert(menuClickUrl)
	            	//currentUrl = (currentUrl == menuClickUrl ? currentUrl : menuClickUrl);
	        		document.getElementById('id_frameRight').src = "${pageContext.request.contextPath}/"+currentUrl;
	        		
					var titleHtml = "";
					jQuery("#id_goPage").html(titleHtml);
					window.top['currentReloadMenuId'] = null;
		        	if(window.top.iframeContentLoadMask){
				    	window.top.iframeContentLoadMask.hide();
					}
	            };
	            var contentType = "json";
	        	jQuery.post(url,data,callBackFunc,contentType);
		 }
		 ///加载左侧非树形菜单结束
		 var frameRightLoadMask = null;
	     function loadLeftTreeMenu(menuContainerIdOrObject,menuRootId,currentReloadMenuId)
         {
	    	leftOperMenuTree = new dTree('leftOperMenuTree');
	    	leftOperMenuTree.config.useCookies = false;
	    	// Toggle Open or close
	    	leftOperMenuTree.onclick = function(nodeIndex) {
	    	    if(null == frameRightLoadMask){
	    	    	frameRightLoadMask = new tracywindyLoadMask("id_frameRightContainer","数据加载中 请稍等...");
                }
	    	    frameRightLoadMask.show();
	    		var cn = this.aNodes[nodeIndex];
	    		if(cn._hc){
	    			frameRightLoadMask.hide();
	    			return false; 
		        }
	    		/*this.nodeStatus(!cn._io, id, cn._ls);
	    		cn._io = !cn._io;*/
	    		if (this.config.closeSameLevel) this.closeLevel(cn);
	    		if (this.config.useCookies) this.updateCookie();
	    		this.s(nodeIndex);
	    		var cn = this.aNodes[nodeIndex];
	    		
	    	    document.getElementById('id_frameRight').src = "${pageContext.request.contextPath}/loadingIndex.html";
                
                //var parentNodeTitle = //cn._p.name;
                var titleHtml = recusionParentTitle(cn,"");//parentNodeTitle+"&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;"+cn.name;
                jQuery("#id_goPage").html(titleHtml);
            	var currentUrl = cn.url;
            	currentUrl = currentUrl ? currentUrl : "errorPages/error404.bi";
               	var url = (("true" == cn.attributes['isRelativedPath']) ? "${pageContext.request.contextPath}/" : "" ) + currentUrl;
               	
               	url = getTracywindyRandomUrl(url);
               	
   				var oFrm = document.getElementById('id_frameRight');
   				oFrm.src = url;
   				oFrm.onload = oFrm.onreadystatechange = function() {
   			     if (this.readyState && this.readyState != 'complete') return;
   			     else {
   			    	frameRightLoadMask.hide();
   			     }};
	    	};
	    	
        	var url = '${pageContext.request.contextPath}/acl/queryMenus.acl?mathRandom='+Math.random();
        	var data = {rootParentMenuId:menuRootId};
        	var callBackFunc = function(jsonData){
        		recursionLeftTreeMenu(menuRootId,jsonData,leftOperMenuTree,true);
        		var currentSelectedMenuNode = leftOperMenuTree.aNodes[findLeftMenuTreeIndex];
        		if(!currentSelectedMenuNode)
        		{
            		alert("子菜单不可操作,请检查菜单权限");
            		return;
            	}
            	var currentUrl = currentSelectedMenuNode.url;
            	currentUrl = currentUrl ? currentUrl : "errorPages/error404.bi";
        		document.getElementById('id_frameRight').src = "${pageContext.request.contextPath}/"+currentUrl;
                 
            	var menuContainer = null;
        		if(!(typeof(menuContainerIdOrObject)=='object'))
        		{
        			menuContainer = document.getElementById(menuContainerIdOrObject);
        		}
        		else
        		{
        			menuContainer = menuContainerIdOrObject;
                }
        		jQuery(menuContainer).html(leftOperMenuTree.toString());
        		leftOperMenuTree.s(findLeftMenuTreeIndex);
                var parentNodeTitle = currentSelectedMenuNode._p.name;
                var titleHtml = recusionParentTitle(currentSelectedMenuNode,"");
                jQuery("#id_goPage").html(titleHtml);
                window.top['currentReloadMenuId'] = null;
	        	if(window.top.iframeContentLoadMask){
			    	window.top.iframeContentLoadMask.hide();
				}
            };
            var contentType = "json";
        	jQuery.post(url,data,callBackFunc,contentType);
         }
         var findLeftMenuTreeIndex  = -1;
	     //迭代左侧树
	 	function recursionLeftTreeMenu(menuRootId,menuJson,tree,isInit)
	 	{
	 		var menus = menuJson.children;
	 		for(var mIndex = 0;mIndex<menus.length;mIndex++)
	 		{
	 		   var tbMenu = menus[mIndex];
	 		   if( menuRootId == tbMenu.id){
	 			  findLeftMenuTreeIndex = 1;
	 			  recursionLeftTreeMenuData(tbMenu,tree,true);
	 			  break;
		 	   }
	 		   
	 		}
	 	}
	 	function recursionLeftTreeMenuData(menuJson,tree,isInit)
	 	{
		 	var isRelativedPath = menuJson.attributes['isRelativedPath'];
		 	if(isInit)
		 	{
		 		var topMenu = menuJson;
		 		tree.add(topMenu.id,-1,topMenu.name,topMenu.url,topMenu.icon,topMenu.iconClose,topMenu.iconOpen,topMenu.description,topMenu.position,true,{isRelativedPath:isRelativedPath});
		 		tree.add('id_leftTreeSystemMenuMap',topMenu.id,'<spring:message code="MenuMap" text="MenuMap"/>',"acl/left_tree_menu_map_content.bi"/*+topMenu.id*/,'diagram.png','diagram.png','diagram.png','菜单地图',0,true,{isRelativedPath:'true'});
			}
	 		var menus = menuJson.children;
	 		for(var mIndex = 0;mIndex<menus.length;mIndex++)
	 		{
	 		   var tbMenu = menus[mIndex];
	 		   if(!compareAuthorities("${sessionScope.currentAllAuthoritiesString}",tbMenu.authorities))continue;
	 		   tree.add(tbMenu.id,tbMenu.pid,tbMenu.name,tbMenu.url,tbMenu.icon,tbMenu.icon,tbMenu.icon,tbMenu.description,tbMenu.position,true,{isRelativedPath:isRelativedPath});
          
               if(window.top['currentReloadMenuId']){
            	   if(window.top['currentReloadMenuId']==tbMenu.id){
            		   findLeftMenuTreeIndex = (tree.aNodes.length-1);
                   }
               }else{
            	     /*if((tbMenu.children.length == 0)&&(-1 == findLeftMenuTreeIndex))
                     {
                  	   findLeftMenuTreeIndex = (tree.aNodes.length-1);
                     }*/
               }
               recursionLeftTreeMenuData(tbMenu,tree,false);
	 		}
		}
		function choseDelegateUser(){
			new tracywindyCommonUserSelection({
				 hiddenInput:'id_delegateUser',
				 displayInput:'id_delegateRealName',
				 id:'commonUserDelgateChoseUser'
			}).show();
			
		}
		function doCheckTree(topMenuIndex, menuId){
			window.top['currentReloadMenuId'] = menuId;
			document.getElementById("id_menuButton_"+topMenuIndex).click();
	   }
		
		
		function showLanguageWindow(){
			jQuery("#id_modifyLanguageForm").show();
			jQuery("#id_modifyLanguageForm").window({
	        	zIndex:9000,
	        	draggable:true,
	        	top:200,
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
			jQuery("#id_modifyLanguageForm").window('open');
		}
		function doModifyLanguage()
		{
			//以及修改session中缓存的系统语言
            var params ={};
			params['language']=getTracywindyObject('id_combo_id_language').getValue();
			
			ajaxRequest({
	   		     url:'${pageContext.request.contextPath}/acl/modifyLanguage.acl',
	   		     method:'POST',
	   		     success:function(res){
	   		    	  var returnInfo = JsonUtil.decode(res.responseText);
		   		      if('success'==returnInfo.status)
		   		      {
			   		    	jQuery("#id_modifyLanguageForm").window("close");
			   		    	alert(returnInfo.message);
			   		    	window.top.location.reload();
				   		}
		   		      else
		   		      {
		   		    	alert(returnInfo.message);
		   		    	//jQuery("#id_oldPassword").focus();
			   		  }
	   		     },
	   		     failure:function(res){
		   		 },
	   		     params:params
   		 });
	    }
 </script>
