<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="description" content="This is my page">

<style type="text/css">
custom-label {
	
}

.custom-option {
	color: red;
	border: 1px;
	border-color: black;
}
</style>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/ligerui1.2.0/skins/Dark/css/ligerui-all.css" />
<!-- link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/ligerui1.2.0/skins/Gray/css/all.css" /> -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/ligerui1.2.0/skins/lab.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/poshyTip/tip-yellow/tip-yellow.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/poshyTip/tip-violet/tip-violet.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/poshyTip/tip-darkgray/tip-darkgray.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/poshyTip/tip-skyblue/tip-skyblue.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/poshyTip/tip-yellowsimple/tip-yellowsimple.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/poshyTip/tip-twitter/tip-twitter.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/poshyTip/tip-green/tip-green.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/my97DatePicker/skin/WdatePicker.css" />
<!-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/validator/Validform_v5.3.2/css/style.css" /> -->
<!-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/validator/Validform_v5.3.2/css/demo.css" />-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/validator/Validform_v5.3.2/css/custom.css" />

<script type="text/javascript" src="resource/jquery/jquery-1.8.2.js"></script>
<script type="text/javascript" src="resource/jquery/jqueryExtend.js"></script>
<script type="text/javascript" src="resource/jquery/jquery.json-2.4.min.js"></script>
<script src="resource/ligerui1.2.0/js/core/base.js" type="text/javascript"></script>
<script src="resource/ligerui1.2.0/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="resource/ligerui1.2.0/js/plugins/ligerToolBar.js" type="text/javascript"></script>
<script src="resource/ligerui1.2.0/js/plugins/ligerComboBox.js" type="text/javascript"></script>
<script src="resource/ligerui1.2.0/js/plugins/ligerFilter.js" type="text/javascript"></script>
<script src="resource/ligerui1.2.0/js/plugins/ligerForm.js" type="text/javascript"></script>
<script src="resource/ligerui1.2.0/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="resource/ligerui1.2.0/js/plugins/ligerTextBox.js" type="text/javascript"></script>
<script src="resource/ligerui1.2.0/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
<script src="resource/ligerui1.2.0/js/plugins/ligerTab.js" type="text/javascript"></script>
<!-- <script type="text/javascript" src="resource/ligerui1.2.0/js/ligerui.all.js"></script> -->
<script type="text/javascript" src="resource/ligerui1.2.0/js/lab.js"></script>
<script type="text/javascript" src="resource/ligerui1.2.0/js/ligerGrid.showFilter.js"></script>
<script type="text/javascript" src="resource/ligerui1.2.0/js/table.js"></script>
<script type="text/javascript" src="resource/somejs/tableGrid.js"></script>
<script type="text/javascript" src="resource/somejs/commtable.js"></script>
<script type="text/javascript" src="resource/somejs/PageSystem.js"></script>
<script type="text/javascript" src="resource/somejs/ComboBox.js"></script>
<!-- 验证框架 -->

<script type="text/javascript" src="resource/validator/Validform_v5.3.2/js/Validform_v5.3.2.js"></script>
<!-- <script type="text/javascript" src="resource/poshyTip/jquery.poshytip.js"></script> -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindySerializeFormToJsonObject.js"></script>

<script type="text/javascript">
	$(function() {
		//$('#table').ligerTab(); 
		var table = $('#grid').jasonTable({jsonType : 'SMSMessage'});
	});
	
</script>

</head>
<body>
	<div id="grid"></div>
</body>
</html>