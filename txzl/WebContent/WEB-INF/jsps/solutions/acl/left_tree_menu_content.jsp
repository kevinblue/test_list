	<%@ taglib prefix='auth' uri='/WEB-INF/tlds/security.tld' %>
	<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
	<%@ taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>  
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
	<head>
	   <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	    <%@ include file="main_include_js.jsp" %>
	   	<link href="${pageContext.request.contextPath}/css/tracywindy/left-menu.css" rel="stylesheet" type="text/css">
	   	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
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
           <div class="frame-left-title lefttop" style="width:100%;"><span></span><font id='id_tilteOperName'><spring:message code="OperationMenu"  text="OperationMenu"/></font></div>
           <div class="frame-left-content" id="id_frame-left-content" style="padding:0px;">
               <dl class="leftmenu" id="id_left_content_menu">
                  
               </dl>
           </div>
        </div>
        <script type="text/javascript">
           var widthAdd = all_width*0.18-1;
           jQuery("#id_frameLeft").css("width",(widthAdd)+"px");
        </script>
        <div class="frame-middle" id="id_frameMiddle" ><a href="javascript:void(0);" class="expanded" >&nbsp;</a></div>
        <div class="frame-right" id="id_frameRightContainer">
           <div>
	           <div class="frame-right-title place" style="display:none">
	                <span><spring:message code="MenuPosition"  text="MenuPosition"/>：</span>
	                 <ul class="placeul">
	                    <li id="id_goPage" ><spring:message code="HomePage"  text="HomePage"/></li>
	                 </ul>
			   </div>
           </div>
           <div><iframe frameborder="0" name="name_leftFrame" id="id_frameRight" src="${pageContext.request.contextPath}/loadingIndex.html"></iframe></div>
        </div>
    </div>
    </body>
        <script type='text/javascript'>
             var isTree = false;
             var heightAdd = 0;
             if(!isTree){
            	 heightAdd = 20;
             }
             if(isTree){
            	 jQuery("#id_frame-left-content").css("padding","10px");
             }
             widthAdd+=2;
             var pageWidth,pageHeight;
             function initContainerSize(){
	 		     pageWidth  = all_width-2;
			     pageHeight = all_height-4;
	        	 jQuery("#id_frame-left-content").css("height",(pageHeight-50+heightAdd)+"px");
	        	 jQuery("#id_frame-left-content").css("overflow","auto");
	        	 jQuery("#id_frameLeft").css("height",(pageHeight+2)+"px");
	        	 jQuery("#id_frameMiddle").css("height",(pageHeight+2)+"px");
	        	 jQuery("#id_frameMiddle").css("line-height",(pageHeight+2)+"px");
	        	 jQuery("#id_frameRight").css("width",(pageWidth-widthAdd+3)+"px");
	        	 jQuery(".frame-right-title").css("width",(pageWidth-widthAdd+2)+"px");
	        	 jQuery("#id_frameRight").css("height",(pageHeight-36+41)+"px");
             }
             initContainerSize();
	         jQuery(function(){
	        	// initContainerSize();
	        	 var menuRootId = "${param.rootParentMenuId}";
				 var currentReloadMenuId = window.top['currentReloadMenuId'];//菜单地图链接的参数
	 	    	 if(window.top.iframeContentLoadMask){
		    		window.top.iframeContentLoadMask.show();
			     }
	 	    	 if(!isTree){
	 	    		 loadLeftTreeMenuNonTree("id_left_content_menu",menuRootId,currentReloadMenuId);
	 	    	 }else{
	 	    		loadLeftTreeMenu("id_frame-left-content",menuRootId,currentReloadMenuId);
	 	    	 }
				 jQuery("#id_loadMaskContainer").hide();
				 jQuery("#id_frameMiddle a").click(function(){
					 try{ this.blur();}catch(e){}
					 if(this.className.indexOf("expanded")>-1)
					 {
						 jQuery(this).removeClass("expanded");
						 jQuery(this).addClass("collapsed");
						 jQuery("#id_frameLeft").hide();
						 jQuery("#id_frameRight").css("width",(pageWidth-8)+"px");
						 jQuery(".frame-right-title").css("width",(pageWidth-10)+"px");
				     }
					 else
					 {
						 jQuery(this).removeClass("collapsed");
						 jQuery(this).addClass("expanded");
						 jQuery("#id_frameLeft").show();
						 jQuery("#id_frameRight").css("width",(pageWidth-widthAdd-8)+"px");
						 jQuery(".frame-right-title").css("width",(pageWidth-widthAdd-10)+"px");
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