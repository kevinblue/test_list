<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列表组件测试</title>
    <!--css sheet-->
	<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/my97DatePicker/skin/WdatePicker.css"/>
	<style type="text/css">
	  html,body{
	    overflow:hidden;
	  }
	</style>
	<!--javascript libray-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree/dtree.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyQueryInput.js"></script>
    <script type="text/javascript">
    jQuery(function(){
    	new tracywindyQueryInput({
    		renderTo:'id_tableContainer',
    		id:'test',
    		xmlFileName:'/eleasing/jsp/service_coordination/ewlp_contractid.xml',
    		label:'测试查询信息1111',
    		otherAttributes:"",
    		//assignInputHiddenInputId:'test_hidden',
    		//assignInputDisplayInputId:'test_display',
    		name:'test',
    		onSelect:function(combo,rowData){
    			alert(getTracywindyObject('test').getRawValue()+":"+JsonUtil.encode(getTracywindyObject('test').getSelectedOptionData()));
    		},
    		params:{
    			pid:0
    		}
    	});
    });
    </script>
</head>
<body>
<div id="info"></div>
<table>
   <tr>
   <td><div id="id_tableContainer"></div></td>
   <td style="display:none"><input id="test_hidden" type="hidden"><input id="test_display"></div></td>
   <td><input type="button" value="获取显示值" onclick="alert(getTracywindyObject('test').getRawValue());"/></td>
   <td><input type="button" value="获取隐藏值" onclick="alert(getTracywindyObject('test').getValue());"/></td>
   <td><input type="button" value="获取选中行JSON数据" onclick="alert(JsonUtil.encode(getTracywindyObject('test').getSelectedOptionData()));"/></td>
   <td><input type="button" value="获取选所有JSON数据" onclick="alert(JsonUtil.encode(getTracywindyObject('test').getOptionDatas()));"/></td>
   </tr>
   <table>
</body>
</html>