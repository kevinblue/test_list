<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>流程实例数据清除</title>
	<%@include file="/base/mini.jsp"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
	<script type="text/javascript">
		function removeProcessInstance(workflowName, workflowVersion) {
			mini.confirm("确认清除 < " + workflowName + " > 下的所有流程数据么?", "确定？", function(action){
				if(action == 'ok'){
					mini.mask({
			            el: document.body,
			            cls: 'mini-mask-loading',
			            html: '正在执行操作  请稍等...'
			        });
					tenwa.ajaxRequest({
						method:'post',
						url:'${pageContext.request.contextPath}/jbpm/removeWorkflowAllProcessInstance.action',
						success:function(res){
							alert("删除成功");
							mini.unmask(document.body);
						},
						failure:function(res){
							alert("删除失败");
							mini.unmask(document.body);
						},
						params:{
							paramWorkflowName		: workflowName,
							paramWorkflowVersion	: workflowVersion
						}
					});
				}
			});
		}
		
		jQuery(function(){
			tenwa.createTable({
				id : 'id_tasksContainer_table',
				renderTo : "id_tasksContainer",
				width : globalClientWidth,
				height : globalClientHeight, 
				title : '流程实例删除', 
				showPager : true, 
				pageSize : 15,
				lazyLoad : false, 
				xmlFileName : 'jbpm/queryAllDeployedWorkflows.xml', 
				tools : [ 'globalQuery' ],
				columns : [ 
				   {type : 'indexcolumn'}, 
				   {type : 'checkcolumn'}, 
				   {field : 'id', header : '记录编号', visible : false, 
					          formEditorConfig : {
						              readOnly : true, 
						          fieldVisible : false }}, 
				   {header:'流程名称',field:'workflow_name_'}, 
				   {header:'流程版本',field:'version_'}, 
				   {header:'操作',field:'oper',
					         renderer:function(e){
						var workflow_name_ = e.record.workflow_name_;
						var version_ = e.record.version_;
						return "<a href='javascript:void(0);' onclick='removeProcessInstance(\"" + workflow_name_ + "\",\"" + version_ + "\")'>清除所有流程数据</a>";
					}
				} ]
			});
		});
	</script>
</head>
<body style="overflow:hidden;"> 
	<div id="id_tasksContainer"></div>
</body>
</html>