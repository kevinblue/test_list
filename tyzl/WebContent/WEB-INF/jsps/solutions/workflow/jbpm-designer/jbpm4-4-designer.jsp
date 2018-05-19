﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<html>
<head>
<title>流程设计</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ include file="/base/mini.jsp" %>
<%@ include file="designerCoreJS.jsp" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jbpmDesigner/myflow.jpdl4.4.js"></script>
<script type="text/javascript">
	var dpdlVersion = "4.4";
	var designerId  = "<mini:param  name='id'/>";
	var workflowTypeData	= eval('<mini:param  name='workflowTypeData'/>');
	var workflowPostionData = eval('<mini:param  name='workflowPostionData'/>');
	function save(designerWorkflowJsonString) {
		var url = "${pageContext.request.contextPath}/jbpm/transferedJpdlXmlByWorkflowDesigner.action";
		$.ajax({
			type: "POST",
			data:{
			id:designerId,
			designerWorkflowJsonString:designerWorkflowJsonString
			},
			url: url,
			dataType:'text',
			success: function(data){
				var msg  = data.split(",")[0];
				designerId = data.split(",")[1];
				$('#myflow_save').removeClass('mover');
				alert(msg);
			}
		});
	}
	$(function() {
		mini.parse();
		$('#myflow').myflow({
			basePath : "",
			restore : eval("(<mini:param name='workflow_designerWorkflowJsonString'/>)"),
			width:1000,
			tools : {
				save : {
					onclick : function(data) {
						if(!window['isCtrlSave'] && window.confirm("确认保存更改么？")) {
							save(data);
						} else {
							jQuery('#myflow_save').removeClass('mover');
						}
					}
				}
			}
		});
	});
</script>
<style type="text/css">
body ,html{
	margin: 0;
	pading: 0;
	text-align: left;
	font-family: Arial, sans-serif, Helvetica, Tahoma;
	font-size: 12px;
	line-height: 1.5;
	color: black;
	overflow:hidden;
}
#myflow{
	background-image: url(${pageContext.request.contextPath}/css/jbpmDesigner/images/bg.png);
}
.node {
	float:left;
	padding:0px 5px;
	text-align: center;
	vertical-align: middle;
	border-right: 1px dashed #666;
}
.node .node-icon {
	width: 15px;
	height: 15px;
	padding: 5px 0px 5px 8px;
	float: left;
}
.node .node-text {
	height: 15px;
	padding: 5px 8px 5px 8px;
	line-height: 15px;
	font-size:12px;
	float: left;
}

.node_hr{
	width: 1px;
	border-right: 1px dashed #666;
}

.mover {
	background-color: #A0B4DC;
	cursor: pointer;
}

.selected {
	background-color: #A0B4DC;
}

.state {
	
}

#myflow_props table {
	
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
	background-repeat: no-repeat;
	background-position: center;
}

#path {
	background-repeat: no-repeat;
	background-position: center;
}

#task {
	background-repeat: no-repeat;
	background-position: center;
}

#state {
	background-repeat: no-repeat;
	background-position: center;
}

#myflow_tools {
	background:#FFFFF;
	height:25px;
	line-height:25px;
	width: 100%;
}

#myflow_tools div {
	float:left;
}

#myflow_tools  hr {
	position:relative;
	top:8px;
	vertical-align:bottom;
	height:14px;
	width:1px;
	border:0px;
	margin:0px;
	padding:0px;
	height:25px;
	line-height:25px;
	background:url(${pageContext.request.contextPath}/images/sbtn_split.gif) no-repeat;
}

</style>
</head>
<body style="width: 100%;height: 100%;">

<div class="mini-toolbar" style="width: 100%;">
<div id="myflow_tools" class="ui-widget-content">
	<!-- 
	<div id="myflow_tools_handle" class="ui-widget-header">
		<div style="node-text">
			工具集
		</div>
		<div style="clear: both;"></div>
	</div>
	-->
	
	<div class="node" id="myflow_save">
		<div class="node-icon">
			<img src="${pageContext.request.contextPath}/css/jbpmDesigner/images/save.gif" />&nbsp;&nbsp;
		</div>
		<div class="node-text">
			保存
		</div>
		<div style="clear: both;"></div>
	</div>
	
	<div class="node" id="myflow_cancel" onclick="backFunc()">
		<div class="node-icon">
			<img src="${pageContext.request.contextPath}/css/jbpmDesigner/images/arrow_left.png" />&nbsp;&nbsp;
		</div>
		<div class="node-text">
			返回
		</div>
		<div style="clear: both;"></div>
	</div>
	
	<div class="node_hr">&nbsp;</div>
	
	<div class="node selectable" id="pointer">
		<div class="node-icon">
			<img src="${pageContext.request.contextPath}/css/jbpmDesigner/images/select16.gif" />&nbsp;&nbsp;
		</div>
		<div class="node-text">
			选择
		</div>
		<div style="clear: both;"></div>
	</div>
	
	<div class="node selectable" id="path">
		<div class="node-icon">
			<img src="${pageContext.request.contextPath}/css/jbpmDesigner/images/16/flow_sequence.png" />&nbsp;&nbsp;
		</div>
		<div class="node-text">
			转移
		</div>
		<div style="clear: both;"></div>
	</div>
	
	<div class="node_hr">&nbsp;</div>
	
	<div class="node state" id="start" type="start">
		<div class="node-icon">
			<img src="${pageContext.request.contextPath}/css/jbpmDesigner/images/16/start_event_empty.png" />&nbsp;&nbsp;
		</div>
		<div class="node-text">
			开始
		</div>
		<div style="clear: both;"></div>
	</div>
	
	<div class="node state" id="end" type="end" >
		<div class="node-icon">
			<img src="${pageContext.request.contextPath}/css/jbpmDesigner/images/16/end_event_terminate.png" />&nbsp;&nbsp;
		</div>
		<div class="node-text">
			结束
		</div>
		<div style="clear: both;"></div>
	</div>
	
	<div class="node_hr">&nbsp;</div>
	
	<!--
	<div class="node state" id="state" type="state">
		<div class="node-icon">
			<img src="${pageContext.request.contextPath}/css/jbpmDesigner/images/16/task_empty.png" />&nbsp;&nbsp;
		</div>
		<div class="node-text">
			状态
		</div>
		<div style="clear: both;"></div>
	</div>
	-->
	
	<div class="node state" id="task" type="task">
		<div class="node-icon">
			<img src="${pageContext.request.contextPath}/css/jbpmDesigner/images/16/task_empty.png" />&nbsp;&nbsp;
		</div>
		<div class="node-text">
			任务
		</div>
		<div style="clear: both;"></div>
	</div>
	
	<div class="node state" id="exclusive" type="exclusive">
		<div class="node-icon">
			<img src="${pageContext.request.contextPath}/css/jbpmDesigner/images/16/gateway_exclusive.png" />&nbsp;&nbsp;
		</div>
		<div class="node-text">
			条件
		</div>
		<div style="clear: both;"></div>
	</div>
	
	<div class="node state" id="fork" type="fork">
		<div class="node-icon">
			<img src="${pageContext.request.contextPath}/css/jbpmDesigner/images/16/gateway_exclusive.png" />&nbsp;&nbsp;
		</div>
		<div class="node-text">
			分支
		</div>
		<div style="clear: both;"></div>
	</div>
	
	<div class="node state" id="join" type="join">
		<div class="node-icon">
			<img src="${pageContext.request.contextPath}/css/jbpmDesigner/images/16/gateway_parallel.png" />&nbsp;&nbsp;
		</div>
		<div class="node-text">
			合并
		</div>
		<div style="clear: both;"></div>
	</div>
	
	<!--
	<div class="node state" id="end-cancel" type="end-cancel">
		<div class="node-icon">
			<img src="${pageContext.request.contextPath}/css/jbpmDesigner/images/16/end_event_cancel.png" />&nbsp;&nbsp;
		</div>
		<div class="node-text">
			取消
		</div>
		<div style="clear: both;"></div>
	</div>
	
	<div class="node state" id="end-error" type="end-error">
		<div class="node-icon">
			<img src="${pageContext.request.contextPath}/css/jbpmDesigner/images/16/end_event_error.png" />&nbsp;&nbsp;
		</div>
		<div class="node-text">
			错误
		</div>
		<div style="clear: both;"></div>
	</div>
	-->
	
	<div class="node_hr">&nbsp;</div>
	
	<div class="node" id="nodeRemoveBtn" style="display:none;">
		<a href="javascript:void(0);" id="nodeRemoveBtnClick">
			<div class="node-icon">
				<img src="${pageContext.request.contextPath}/css/jbpmDesigner/images/16/delete.gif" />&nbsp;&nbsp;
			</div>
			<div class="node-text">
				删除
			</div>
			<div style="clear: both;"></div>
		</a>
	</div>
</div>
</div>

<div id="myflow_props" style="position: absolute; width: 300px; padding: 3px;" class="ui-widget-content">
	<div id="myflow_props_handle" class="ui-widget-header">&nbsp;&nbsp;&nbsp;&nbsp;属性</div>
	<table border="1" width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
	<div>&nbsp;</div>
</div>


<div id="myflowContainer">
	<div id="myflow"></div>
</div>


</body>
</html>