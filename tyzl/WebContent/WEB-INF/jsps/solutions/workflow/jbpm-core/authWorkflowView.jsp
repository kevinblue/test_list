<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>流程实例</title>
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
				title:'流程信息',
				multiSelect: false,
				isExcel:true,
				remoteOper : true,
				xmlFileName:"/jbpm/queryAllProcessInstances.xml",
				columns: [
					{type : 'checkcolumn'},	
					{field: 'serialno', header: '流水号'},
					{field: 'projectname', header: '项目名称'},	
					{field: 'workflowname', header: '流程名称'},
					{field:'taskid',header:'当前任务',align:'center',renderer:function(e){
						var rowData = e.record; 
						var taskids = rowData['taskid'].split(",");
						var tasknames = rowData['taskname'].split(",");
						var rs = "";
                        for(var i=0;i<taskids.length;i++){
                            var taskid = taskids[i];
                            var taskname = "";
                            for(var j=0;j<tasknames.length;j++){
                                var tempTaskName = tasknames[j];
                                var tempTaskId   = tempTaskName.split("@@_@@")[0];
                                if(tempTaskId == taskid){
                              	  taskname = tempTaskName.substring(taskid.length+"@@_@@".length,tempTaskName.length);
                              	  break;
                                }
                            }
                            if(i>0){
                          	  rs+='<br>&nbsp;';
                            }
                            rs+='<a href="javascript:void(0);" onclick="toProcessForm('+taskid+')">'+taskname+'</a>';
                        }
                        return rs;
				    }},
				    {field:'viewdiagram',header:'流程图',align:'center',renderer:function(e){
			    	var rowData = e.record; 
                   	 return '<a href="javascript:void(0);" onclick="toProcessActivePicture(\''+rowData.deployid+'\',\''+((rowData.processinstanceid))+'\')">查看</a>';
                    }},
					{field: 'processstart', header: '流程开始时间',allowSort : true,dateFormat : 'yyyy-MM-dd HH:mm:ss'},
					{field: 'processend', header: '流程结束时间',allowSort : true,dateFormat : 'yyyy-MM-dd HH:mm:ss',renderer:function(e){
						var rowData = e.record;
						if(rowData["processend"]){
							return mini.formatDate(rowData["processend"],'yyyy-MM-dd HH:mm:ss');//+" < 已结束  > ";
					    }
			            return  "进行中...";
				    }}
				],
				tools:['globalQuery','_','exportExcel'],
		        params:{
			  		  USERID:"${sessionScope['login_userid']}",
			  		  deployedIds: (!deployedIds ? "null" : deployedIds)
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
	var url = "${pageContext.request.contextPath}/jbpm/viewHistoryProcessForm.action?currentTaskId="+currentTaskId;
	openFullScreenWindow(url);
}
</script>
</body>
</html>