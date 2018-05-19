<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<html>
<head>
<title>流程设计</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="designerCoreJS.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jbpmDesigner/myflow.jpdl4.3.js"></script>
<link rel=stylesheet href="${pageContext.request.contextPath}/css/jbpmDesigner/jbpm4-3-designer.css"/>
<script type="text/javascript">
   var dpdlVersion = "4.3";
   var designerId  = "<mini:param  name='id'/> ";
   function save(designerWorkflowJsonString)
   {
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
				    alert(msg);
				   // window.location.href="${pageContext.request.contextPath}/jbpm/queryAllDesignedWorkflows.action?mathRandom="+Math.random();
			   }
			});
   }
	$(function() {
		$('#myflow').myflow({
							basePath : "",
							restore : eval("(<mini:param name='workflow_designerWorkflowJsonString'/>)"),
							tools : {
								save : {
									onclick : function(data) {
			                            if(!window['isCtrlSave']&&window.confirm("确认保存更改么？"))
			                            {
			                            	save(data);
				                        }
									}
								}
							}
						});
	});
</script>
</head>
<body>
<div id="myflow_tools"
	style="position: absolute; top: 10; left: 10; background-color: #fff; width: 70px; cursor: default; padding: 3px;"
	class="ui-widget-content">
<div id="myflow_tools_handle" style="text-align: center;"
	class="ui-widget-header">工具集</div>
	<div class="node" id="myflow_save">
	    <img style="position:relative;top:4px;" src="${pageContext.request.contextPath}/css/jbpmDesigner/images/save.gif" />&nbsp;&nbsp;保存</div>
	<div><hr /></div>
	<div class="node" >
	    <a href="javascript:void(0);" style="text-decoration:none;" onclick = "javascript:backFunc();">
	       <img style="position:relative;top:2px;border:0px;"
	       src="${pageContext.request.contextPath}/css/jbpmDesigner/images/arrow_left.png" />
	       &nbsp;&nbsp;返回</a>
	</div>
<div><hr /></div>
<div class="node selectable" id="pointer"><img
	src="${pageContext.request.contextPath}/css/jbpmDesigner/images/select16.gif" />&nbsp;&nbsp;选择</div>
<div class="node selectable" id="path"><img
	src="${pageContext.request.contextPath}/css/jbpmDesigner/images/16/flow_sequence.png" />&nbsp;&nbsp;转换</div>
<div>
<hr />
</div>
<div class="node state" id="start" type="start"><img
	src="${pageContext.request.contextPath}/css/jbpmDesigner/images/16/start_event_empty.png" />&nbsp;&nbsp;开始</div>
<!--div class="node state" id="state" type="state"><img
	src="${pageContext.request.contextPath}/css/jbpmDesigner/images/16/task_empty.png" />&nbsp;&nbsp;状态</div-->
<div class="node state" id="task" type="task"><img
	src="${pageContext.request.contextPath}/css/jbpmDesigner/images/16/task_empty.png" />&nbsp;&nbsp;任务</div>
<div class="node state" id="fork" type="fork"><img
	src="${pageContext.request.contextPath}/css/jbpmDesigner/images/16/gateway_parallel.png" />&nbsp;&nbsp;分支</div>
<div class="node state" id="join" type="join"><img
	src="${pageContext.request.contextPath}/css/jbpmDesigner/images/16/gateway_parallel.png" />&nbsp;&nbsp;合并</div>
<div class="node state" id="end" type="end"><img
	src="${pageContext.request.contextPath}/css/jbpmDesigner/images/16/end_event_terminate.png" />&nbsp;&nbsp;结束</div>
<!--div class="node state" id="end-cancel" type="end-cancel"><img
	src="${pageContext.request.contextPath}/css/jbpmDesigner/images/16/end_event_cancel.png" />&nbsp;&nbsp;取消</div>
<div class="node state" id="end-error" type="end-error"><img
	src="${pageContext.request.contextPath}/css/jbpmDesigner/images/16/end_event_error.png" />&nbsp;&nbsp;错误</div-->
</div>

<div id="myflow_props"
	style="position: absolute; top: 30; right: 50; background-color: #fff; width: 300px; padding: 3px;"
	class="ui-widget-content">
<div id="myflow_props_handle" class="ui-widget-header">属性</div>
<table border="1" width="100%" cellpadding="0" cellspacing="0">
	<tr>
		<td>aaa</td>
	</tr>
	<tr>
		<td>aaa</td>
	</tr>
</table>
<div>&nbsp;</div>
</div>

<div id="myflow" style="height:2000px"></div>
</body>
</html>