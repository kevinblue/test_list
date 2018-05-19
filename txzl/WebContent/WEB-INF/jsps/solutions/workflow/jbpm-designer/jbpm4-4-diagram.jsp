<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c'   uri='/WEB-INF/tlds/c.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>流程图查看</title>
<%@ include file="designerCoreJS.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jbpmDesigner/myflow.jpdl4.4.js"></script>
<script type="text/javascript">
var globalClientWidth = document.documentElement.clientWidth;
var globalClientHeight = document.documentElement.clientHeight;
var dpdlVersion = "4.4";
var workflow_designerWorkflowJsonString = "${(empty param.workflow_designerWorkflowJsonString) ? workflow_designerWorkflowJsonString : param.workflow_designerWorkflowJsonString}";
var activeRects = ${empty activeRects ? '{}' :activeRects};
var historyRects =${empty historyRects ? '{}' :historyRects};
$(function(){
	$('#myflow')
	.myflow($.extend(true,{
		basePath : "",
		restore : eval("("+workflow_designerWorkflowJsonString+")"),
		editable : false
	}
	,{
		"activeRects" :activeRects
			//{"rects":[{"paths":[],"name":"任务3"},{"paths":[],"name":"任务4"},{"paths":[],"name":"任务2"}]}
	    ,"historyRects":historyRects
		   //{"rects":[{"paths":["TO 任务1"],"name":"开始"},{"paths":["TO 分支"],"name":"任务1"},{"paths":["TO 任务3","TO 任务4","TO 任务2"],"name":"分支"}]}
	}
	));
});
window.onresize = function(){
   window.location.href = window.location.href;
};
</script>
<style type="text/css">
body {
  margin : 0;
  pading: 0;
  text-align: left;
  font-family: Arial, sans-serif, Helvetica, Tahoma;
  font-size: 12px;
  line-height: 1.5;
  color: black;
  /*background-image: url(img/bg.png);*/
}

.node {
	width : 70px;
	text-align : center;
	vertical-align:middle;
	border: 1px solid #fff;
}

.mover{
	border: 1px solid #ddd;
	background-color: #ddd;
}
.selected{
	background-color: #ddd;
}
.state{}

#myflow_props table{
	
}
#myflow_props th {
	letter-spacing: 2px;
	text-align: left;
	padding: 6px;
	background: #ddd;
} 
#myflow_props td {
	background: #fff;
	padding: 6px;
} 

#pointer {
	background-repeat:no-repeat;
	background-position:center;
	
}
#path {
	background-repeat:no-repeat;
	background-position:center;
	
}
#task{
	background-repeat:no-repeat;
	background-position:center;
	
}
#state{
	background-repeat:no-repeat;
	background-position:center;
	
}
</style>
</head>
<body >
<div class="panel-title"  style="overflow: auto;"> ${workflowdisplaytype}-> ${workflowdisplayname}</div>
<div  id="myflowContainer">
	<div id="myflow"  >
	</div>
</div>

</body>
</html>