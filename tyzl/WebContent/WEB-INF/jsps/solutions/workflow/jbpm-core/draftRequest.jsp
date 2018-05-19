<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib uri="/minidict" prefix="mini"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>我的草稿</title>
		<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css?version=3" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
        <script type="text/javascript">
	      var all_width = (document.documentElement||document.body).clientWidth-2;
	      var all_height = (document.documentElement||document.body).clientHeight-2;
	    </script>
	    <style type="text/css">
	       html,body{
	         overflow:hidden;
	       }
	    </style>
</head>
<%@include file="/base/mini.jsp"%>
<c:set var="deployedIds" value="${applicationScope['userOwnedWorkflowStartSqlIds'][sessionScope['login_username']]}"></c:set>
<body style="overflow:hidden;"> 
   <div id="id_tasksContainer"></div>
   <script type="text/javascript" defer>
   var loadMask = null;
   var deployedIds = ("<mini:param  name='deployedIds'/>");
   jQuery(function(){
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id: "id_tasks_table",
				renderTo: "id_tasksContainer",
				width: globalClientWidth,
				height: globalClientHeight,
				showPager : true,//分页按钮是否显示
				title:'我的草稿',
				multiSelect: true,
				remoteOper : true,
				xmlFileName:"/jbpm/queryPendingTasks.xml",
				columns: [
				    {type : 'checkcolumn'},
					{field: 'serialno', header: '流水号',renderer:function(e){
						var rowData = e.record;
						return '<a href="javascript:void(0);" onclick="toProcessForm('+rowData.taskid+',\''+rowData.actorid+'\',\''+rowData.tasktype+'\')">'+rowData.serialno+'</a>';
					 }},
					{field: 'projectname', header: '项目名称'},	
					{field: 'workflowname', header: '流程名称'},
					{field: 'taskname', header: '任务名称'},
					{header:'任务开始时间',field:'taskstart',dateFormat:'yyyy-MM-dd HH:mm:ss'},
					{header:'执行人编号',field:'actorid',visible:false},
					{header:'执行人',field:'actorrealname',visible:false},
					{header:'流程开始时间',field:'processstart',dateFormat:'yyyy-MM-dd HH:mm:ss'},
					{header:'操作',field:'oper',renderer:function(e){
						var rowData = e.record;
						 return '<a href="javascript:void(0);" onclick="toProcessActivePicture(\''+rowData.deployid+'\',\''+rowData.processinstanceid+'\',\''+rowData.actorid+'\')">显示流程图</a>'
							+'&nbsp;&nbsp;&nbsp;&nbsp;'
						 +'<a href="javascript:void(0);" onclick="toProcessForm('+rowData.taskid+',\''+rowData.actorid+'\',\''+rowData.tasktype+'\')">查看</a>';
					 }}
				],
				tools:['globalQuery','_','exportExcel','_',{
					html : '删除草稿',//自定义按钮的名字
					plain : true,//按钮是否有立体感
					iconCls : 'icon-remove',//按钮的图标
					handler : function(miniTable, buttonText) {//按钮响应函数
						var checkedRowDatas = miniTable.getSelecteds();//多选的数据，推荐使用，无选择时返回空数组对象[]
			             var rowDatasLen = checkedRowDatas.length;
			             if(0 == rowDatasLen){
			                 mini.alert("请选择需要删除的草稿");
			                 return;
			             }
			             mini.confirm("确认删除该草稿么?", "确定？", function(action){
			            	 if(action == 'ok'){
					             if(null == loadMask){
					            	 loadMask = new tracywindyLoadMask(document.body,'操作进行中 请稍后...');
					             }
					             loadMask.show();
					             var checkedProcessInstanceDbidArr = [];
					             for(var i=0;i<rowDatasLen;i++){
					                 var rowData = checkedRowDatas[i];
					                 checkedProcessInstanceDbidArr.push(rowData["processinstancedbid"]);
					             }
					             var processInstanceDBID = checkedProcessInstanceDbidArr.join(",");
					             ajaxRequest({
					                  url:'${pageContext.request.contextPath}/jbpm/removeProcessInstance.action',
					                  timeout:30*60*1000,
					                  success:function(res){
					                     loadMask.hide();
					                     miniTable.reload();
					                     mini.alert("操作成功");
					                  },
					                  failure:function(res){
					                	 loadMask.hide();
					                  },
					                  params:{
					                	  processInstanceDBID:processInstanceDBID
					                  }
					             });
			            	 }
			             });
			             
					}
				}],
		        params:{
		        	USERID:"<mini:param name='login_userid'/>",
		  		 	 PROCESSINSTANCESTATE:'Draft'
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
function toProcessForm(currentTaskId)
{
	var url = "${pageContext.request.contextPath}/jbpm/requestProcessTaskForm.action?currentTaskId="+currentTaskId;
	openFullScreenWindow(url);
}
</script>
</body>
</html>