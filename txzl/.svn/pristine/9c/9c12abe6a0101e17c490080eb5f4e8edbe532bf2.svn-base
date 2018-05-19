<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>已办事宜</title>
<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css?version=3" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
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
				title:'已办事宜',
				multiSelect: false,
				isExcel:true,
				remoteOper : true,
				loadMode : 'ajax',
				xmlFileName:"/jbpm/queryCompletedTasks.xml",
				columns: [
					{field: 'serialno', header: '流水号',
						renderer:function(e){
						var rowData = e.record;
						return '<a href="javascript:void(0);" onclick="toProcessForm('+rowData.taskid+')">'+rowData.serialno+'</a>';
					}},
					{field: 'projectname', header: '项目名称'},	
					{field: 'workflowdisplayname', header: '流程名称'},
					{field:'processinstancetype',header:'已办类型',visible:false, /* ,
						renderer:function(e){
						var rowData = e.record;
						return getTaskTypeChineseName(rowData.processinstancetype);
					} */ },
					{field:'processinstancetypename',header:'已办类型' ,
						renderer:function(e){
						var rowData = e.record;
						return getTaskTypeChineseName(rowData.processinstancetype);
					} },
					{field:'taskname',header:'已办任务',
						renderer:function(e){
						var rowData = e.record; 
						return rowData["taskname"].substring(rowData["taskid"].length,rowData["taskname"].length);
				    }},
					{field: 'processstart', header: '开始时间',allowSort : true,dateFormat : 'yyyy-MM-dd HH:mm:ss'},
					{field: 'processend', header: '结束时间',allowSort : true,dateFormat : 'yyyy-MM-dd HH:mm:ss',
						renderer:function(e){
						var rowData = e.record;
						if(rowData["processend"]){
							return mini.formatDate(rowData["processend"],'yyyy-MM-dd HH:mm:ss');//+" < 已结束  > ";
					    }
			            return  "进行中...";
				    }},
					{header:'操作',field:'oper',align:"left",
						renderer:function(e){
							var rowData = e.record;
							var deploy = "";
							if(rowData.deployid) {
								deploy += '<a href="javascript:void(0);" onclick="toProcessActivePicture(\''+rowData.deployid+'\',\''+rowData.processinstanceid+'\',\''+rowData.actorid+'\')">显示流程图</a>'
								+'&nbsp;&nbsp;&nbsp;&nbsp;'+
								'<a href="javascript:void(0);" onclick="toProcessForm('+rowData.taskid+')">查看</a>';
							}
							return deploy;
						}
					}
				],
				tools:['globalQuery','_','exportExcel'],
		        params:{
			  		  USERID:"${sessionScope['login_userid']}"
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
function toProcessForm(currentTaskId){
	var URL = "${pageContext.request.contextPath}/jbpm/viewHistoryProcessForm.action?currentTaskId="+currentTaskId;
	openFullScreenWindow(URL);
}
</script>
</body>
</html>