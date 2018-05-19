<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列表组件测试</title>
    <!--css sheet-->
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/my97DatePicker/skin/WdatePicker.css"/>
	<style type="text/css">
	  html,bode{
	    overflow:hidden;
	  }
	</style>
	<!--javascript libray-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/my97DatePicker/WdatePicker.js"></script>
    <!-- tracywindy libray-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTree2Table.js"></script>
    <script type="text/javascript">
       jQuery(function(){
    	   new tracywindyTree2Table({
        	   renderTo:'id_tableContainer',
        	   id:'test',
        	   rootDictId:'doc_code_300',
        	   savedDataKey:'test0000',
        	   savedDataKey1:'test1',
        	   savedDataKey2:'test2',
        	   savedDataKey3:'test3',
        	   savedDataKey4:'test4',
        	   savedDataKey5:'test5',
        	   width:500,
        	   height:document.documentElement.clientHeight-50
           }); 
       });
    </script>
</head>
<body>
 <input type="button" value="保存" onclick="/*getTracywindyObject('test').savedData();*/">
<div id="info"></div>
   <div id="id_tableContainer"></div>
</body>
</html>