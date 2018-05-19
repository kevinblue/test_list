<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib uri="/minidict" prefix="mini"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>未办事宜</title>
<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css?version=3" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
<script type="text/javascript">
var all_width = (document.documentElement||document.body).clientWidth-2;
var all_height = (document.documentElement||document.body).clientHeight-2;
</script>
<style type="text/css">
      html,body{overflow:hidden;}
</style>
</head>
<%@include file="/base/mini.jsp"%>
<body style="overflow:hidden;"> 
<div id="id_tasksContainer"></div>
<script type="text/javascript" defer>
   jQuery(function(){
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id: "id_tasks_table",
				renderTo: "id_tasksContainer",
				width: globalClientWidth,
				height: globalClientHeight,
				showPager : true,//分页按钮是否显示
				title:'待办事宜',
				isExcel:true,
				remoteOper : true,
				multiSelect: true,
				xmlFileName:"/jbpm/queryPendingTasks.xml",
				columns: [
					{type:'indexcolumn'},
					{type:'checkcolumn'},		
					{field: 'serialno', header: '流水号',
						renderer:function(e){
							var rowData = e.record;
							var deploy = "";
							if(rowData.deployid) {
								deploy +='<a href="javascript:void(0);" onclick="toProcessForm('+rowData.taskid+',\''+rowData.actorid+'\',\''+rowData.tasktype+'\')">'+rowData.serialno+'</a>';
							}
							return deploy;
						}
					},
					{field: 'projectname', header: '项目名称'},	
					{field: 'workflowname', header: '流程名称'},
					{field: 'taskname', header: '任务名称'},
					{field: 'tasktype', header: '任务类型',
						renderer:function(e){
							var rowData = e.record;
							var TaskButton=rowData.taskbutton;
							if(TaskButton=="Back"){
							    return "退回"+getTaskTypeChineseName(rowData.tasktype);
							}else{
                                return  getTaskTypeChineseName(rowData.tasktype);
							}
					}},
					{field: 'taskstart', header: '任务开始时间',allowSort : true,dateFormat : 'yyyy-MM-dd HH:mm:ss' },
					{field: 'actorid', header: '执行人编号',visible:false},
					{field: 'processstart', header: '流程开始时间',allowSort : true,dateFormat : 'yyyy-MM-dd HH:mm:ss'},
					{header:'操作',field:'oper',align:"left",
						renderer:function(e){
							var rowData = e.record;
							var deploy = "";
							if(rowData.deployid) {
								deploy += '<a href="javascript:void(0);" onclick="toProcessActivePicture(\''+rowData.deployid+'\',\''+rowData.processinstanceid+'\',\''+rowData.actorid+'\')">显示流程图</a>'
								+'&nbsp;&nbsp;&nbsp;&nbsp;'+
								'<a href="javascript:void(0);" onclick="toProcessForm('+rowData.taskid+',\''+rowData.actorid+'\',\''+rowData.tasktype+'\')">查看</a>';
							}
							return deploy;
						}
					}
				],
				tools:['globalQuery','_','exportExcel'],
		        params:{
			  		  USERID:"<mini:param name='login_userid'/>",
			  		  NOTPROCESSINSTANCESTATE:'Draft',
			  		  PENDING:true
			  	}
				
			});
		});
	});
	 $("id_queryWorkflowsTableInput").onkeypress = function(evt){
		 var e  = getEvent(evt);
	     var code = e.keyCode||e.charCode;
	     if(13 == code){
	         var workflowsTable = getTracywindyTable("id_tasks_table");
	         workflowsTable.setParams({
	                queryText:this.value.toUpperCase()
	         });
	         workflowsTable.reload();
	     }
	 };
	//显示流程图
	function toProcessActivePicture(deployId,processInstanceId,planActorId)
	{
		var url = "${pageContext.request.contextPath}/workflow/jbpm/getActivedRects.action?deployId="+deployId+"&processInstanceId="+escape(encodeURIComponent(processInstanceId))+"&jbpmWorkflowHistoryInfoUserId="+planActorId+"&randomNumber="+Math.random();
		openFullScreenWindow(url);
	}
	function doQueryByText_pending()
	{
	  var contentText = document.all['id_contextText_pending'].value;
	  var tableContact = getTracywindyTable("pendingRequestTable");
	  tableContact.params['proj_id'] = contentText.toUpperCase();
	  tableContact.reload();
	}
</script>
<script type="text/javascript">
	function toProcessForm(currentTaskId,planActorId,tasktype)
	{
		var url = "${pageContext.request.contextPath}/jbpm/requestProcessTaskForm.action?currentTaskId="+currentTaskId+"&jbpmWorkflowHistoryInfoUserId="+planActorId+"&currentRequestTaskType="+tasktype;
		openFullScreenWindow(url);
	}
</script>
</body>
</html>