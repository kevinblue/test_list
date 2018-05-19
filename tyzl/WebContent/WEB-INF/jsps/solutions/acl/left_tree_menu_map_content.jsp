	<%@ taglib prefix='auth' uri='/WEB-INF/tlds/security.tld' %>
	<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
	<%@ taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>  
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	   	<!--css sheet-->
		<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/css/tracywindy/main.css" rel="stylesheet" type="text/css">
		<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
		<style type="text/css">
		       #id_menuMapContainer{
					color:#87A5C8;
					overflow:auto;
					border:1px solid #DDD;
					border-top:0px;
					padding:5px;
					padding-top:0px;
					font-size:12px;
		       }
		       #id_menuMapContainer div{
					white-space:nowrap;
					word-wrap:normal;
					word-break:break-all;
		       }
			.btn-bg { 
				background:url(${pageContext.request.contextPath}/images/ibtnbg.png) repeat-x;
				border:solid 1px #bfcfe1; 
				height:23px;
				line-height:23px; 
				display:inline-block;
				 /*float:left; */
				 padding:0 15px; 
				 cursor:pointer;
				 color:#87A5C8;
			}
			.btn-bg span{
			   display:inline;
			   height:23px;
			   line-height:23px; 
			}
			a.btn-bg:hover{
			  color:#056DA1;
			  text-decoration:none;
			}
		</style>
		<script type="text/javascript">
		     var all_width = (document.documentElement||document.body).clientWidth;
		     var all_height = (document.documentElement||document.body).clientHeight;
		</script>
	</head>
    <body>
      <div class="x-panel-div-title">菜单地图</div>
      <div id="id_menuMapContainer">
			<div id="id_loadMaskContainer" style="display:block;">
				<div id="loading">
				    <div class="loading-indicator">页面加载中   请稍等...</div>
				</div>
			</div>
			<script type="text/javascript">
				jQuery("#id_loadMaskContainer").css("height",all_height+"px");
				jQuery("#id_menuMapContainer").css("height",(all_height-6)+"px");
			</script>
	  </div>
    </body>
        <script type='text/javascript'>
        var globleMenuMapHtml = "";
        jQuery(function() {
            var url = '${pageContext.request.contextPath}/acl/queryMenus.acl?mathRandom=' + Math.random();
            var rootMenuId = "402881f43bb24656013bb25c5cef0001";
            var data = {rootParentMenuId: rootMenuId};
            var level = 0;
            var callBackFunc = function(jsonData) {
                var $menuMapContainer = $("#id_menuMapContainer");
                recursionMenuMap($menuMapContainer, 0, {children: []},{children: []}, -1, level, true);//首页
                var children = jsonData.children;
                for (var i = 0; i < children.length; i++) {
                    var childJsonObj = children[i];
                    recursionMenuMap($menuMapContainer, i + 1, jsonData, childJsonObj, i, level + 1, false);//二层菜单
                }
                $menuMapContainer.append("<div style='height:5px;width:100%;float:left;'>&nbsp;</div>");
                jQuery("#id_loadMaskContainer").hide();
            };
            var contentType = "json";
            jQuery.post(url, data, callBackFunc, contentType);
        });
        var menuMapTopMargin = 5;
        var menuMapLeftMargin = 5;
        var menuMapPadding = 5;
        
        function recursionMenuMap($menuMapContainer, topMenuIndex, parentJsonObj, jsonObj, childIndex, level, isMainPage) {
            var children = jsonObj.children;
            var icon = jsonObj.icon;
            var text = jsonObj.name;
            var menuId = jsonObj.id;
            var jsonurl = jsonObj.url;
            if (isMainPage) {
                icon = "home.png";
                text = '<spring:message code="HomePage" text="HomePage"/>';
                menuId = "id_mainPage";
            }
            var $divContainer = $("<div/>").attr("topMenuIndex", topMenuIndex);
            $divContainer.css("marginLeft", menuMapLeftMargin + "px");
            $divContainer.css("marginTop", menuMapTopMargin + "px");
           // $divContainer.css("border", "1px solid #CAD6E7");
            $divContainer.css("padding", menuMapPadding + "px");
            //$divContainer.css("background", "#DDD");
            $divContainer.css("float", "left");
            var isHasChildren = (0 < children.length);
            if (isHasChildren) {
                createBrContainer($menuMapContainer, 0);
            }
            $menuMapContainer.append($divContainer);
            var $img = $("<img src='${pageContext.request.contextPath}/menuIcons/" + icon + "'/>").css({"verticalAlign": "middle"});
            //$divContainer.append($img);
            //$divContainer.append("<font>&nbsp;</font>");
            var $text = $("<a class='btn-bg'></a>").attr('name',menuId).html($img[0].outerHTML+"<font>&nbsp;</font>"+text);
            $divContainer.append($text);
           	if (0 == children.length) {//子节点的点击事件
                //$divContainer.css("background","#D4E1F2");
           		$divContainer.css("cursor", "pointer");
				$divContainer.click(function(event){
					window.top.doCheckTree(topMenuIndex,menuId,jsonurl);
				});
            } else {
            	$divContainer.css("cursor", "default");
            	$divContainer.find("a").css("cursor", "default");
            	$divContainer.attr("disabled", true);
            	$divContainer.find("img").attr("disabled", true);
            	$divContainer.find("a").attr("disabled", true);
            	//点击父标签可以隐藏或者显示子节点
            	//$divContainer.click(function(event){
            	//	if($(this).next('div:hidden').length > 0){
	            //		$(this).next('div:hidden').show();
            	//	} else {
	            //		$(this).next('div:visible').hide();
            	//	}
				//});
            }
            if (isMainPage) {
                createBrContainer($menuMapContainer, 5);
                return;
            }
		    if(isHasChildren &&((1 == level)||(sameLevelHasChidren(parentJsonObj)))){
                if ((0 == children.length)) {
                    var margin = 5;
                    if (parentJsonObj.children[parentJsonObj.children.length - 1].id == jsonObj.id) {
                        margin = 0;
                    }
                    createBrContainer($menuMapContainer, margin);
                    return;
                } else {
                    var leftIndent = (level * 35);
                    var $divChildrenContainer = $("<div/>").css({float:'left',width:'100%'});
                    $menuMapContainer.append($divChildrenContainer);
                    
                    var $table = $("<table/>");
                    $divChildrenContainer.append($table);
                    
                    var $tr = $("<tr/>");
                    $table.append($tr);
                    
                    var $leftIndentTd = $("<td/>").css({width:leftIndent});//缩进填充
                    $tr.append($leftIndentTd);
                    
                    var $rightContentTd = $("<td/>");
                    $tr.append($rightContentTd);
                    
                    var $rightContentContainer = $("<div/>");//子菜单table,第三层以后
                    $rightContentTd.append($rightContentContainer);

                    for (var i = 0; i < children.length; i++) {
                        var childJsonObj = children[i];
                        recursionMenuMap($rightContentContainer, topMenuIndex, jsonObj, childJsonObj, i, level, false);
                    }
                }
            }
        }
        function createBrContainer($menuMapContainer, margin) {
            margin = margin || 0;
            var $brContainer = $("<div style='width:100%;float:left;margin-top:" + margin + "px;'></div>");
            $menuMapContainer.append($brContainer);
        }
        function sameLevelHasChidren(parentJsonObj) {
            if (null == parentJsonObj) return true;
            var hashChildren = false;
            var children = parentJsonObj.children;
            for (var i = 0; i < children.length; i++) {
                if (children[i].children.length > 0) {
                    hashChildren = true;
                    break;
                }
            }
            return hashChildren;
        }
        </script>
    </html>