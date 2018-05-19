<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c'   uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib uri="/minidict" prefix="mini"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>流程图查看</title>
<%@ include file="designerCoreJS.jsp" %>
<link rel=stylesheet href="${pageContext.request.contextPath}/css/jbpmDesigner/jbpm4-4-designer.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jbpmDesigner/myflow.jpdl4.4.js"></script>
<script type="text/javascript">
var globalClientWidth = document.documentElement.clientWidth;
var globalClientHeight = document.documentElement.clientHeight;
var dpdlVersion = "4.4";
var workflow_designerWorkflowJsonString = "<mini:param  name='workflow_designerWorkflowJsonString'/>";
var activeRects = <mini:param  name='activeRects' defaultValue='{}'/>;
var historyRects =<mini:param  name='historyRects' defaultValue='{}'/>;
$(function(){
	$('#myflow')
	.myflow($.extend(true,{
		basePath : "",
		restore : eval("("+workflow_designerWorkflowJsonString+")"),
		editable : false
	}
	,{
		"activeRects" :activeRects,
		"historyRects":historyRects
		}
	));
});
window.onresize = function(){
   window.location.href = window.location.href;
};
</script>
</head>
<body >
<div class="panel-title"  style="overflow: auto;"> <mini:param  name='workflowdisplaytype'/>-> <mini:param  name='workflowdisplayname'/></div>
<div  id="myflowContainer">
	<div id="myflow"  >
	</div>
</div>

</body>
</html>