<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程参与情况</title>
		<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css?version=3" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
	    <style type="text/css">
	       html,body{
	         overflow:hidden;
	       }
	    </style>
</head>
<%@include file="/base/mini.jsp"%>
<body style="overflow:hidden;"> 
   <div id="id_tasksContainer1" style="width: 100%;height: 100%;"></div>
   <script type="text/javascript" defer>
   jQuery(function(){
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id: "id_tasks_table",
				renderTo: "id_tasksContainer1",
				width: '100%',
				height: '100%',
				showPager : false,//分页按钮是否显示
				title:'流程参与情况',
				remoteOper : false,
				//loadMode:'local',
				multiSelect: false,
				data: datas_workflow,
				columns: [
					{type : 'indexcolumn'},
					{field: 'workflowname', header: '流程名称'},
					{field: 'processinstancestate', header: '流程状态',
			    	    renderer:function(e){
			    	    	var rowData = e.record;
			    	    	var valueText=rowData['processinstancestate'];
			    			switch(valueText){
			    			case 'Draft' : valueText+= "[草稿]"; break;
			    			case 'Pending' : valueText+= "[进行中]"; break;
			    			case 'Finish' : valueText+= "[结束]"; break;
			    			case 'Delete' : valueText+= "[删除]"; break;
			    			case 'Abondon' : valueText+= "[废弃流程]"; break;
			    			}
			    			return valueText;
			    		}},	
					{field: 'keyone', header: '关键字一'
					},
					{field: 'keytwo', header: '关键字二'
					},
					{field: 'keythree', header: '关键字三'
					},
					{field: 'starttime', header: '任务开始时间',dateFormat : 'yyyy-MM-dd HH:mm:ss' 
					},
					{field: 'endtime', header: '任务结束时间'
					},
					{header:'操作',field:'taskid',align:"center",
						renderer:function(e){
							var rowData = e.record;
							return "<a href='javascript:void(0);' onclick='toProcessForm(\""+rowData['taskid']+"\")'>查看详情</a>";
						}
					}
				]
				
			});
		});
	});
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
function toProcessForm(currentTaskId,planActorId)
{
	/* var url = "${pageContext.request.contextPath}/jbpm/requestProcessTaskForm.action?currentTaskId="+currentTaskId+"&jbpmWorkflowHistoryInfoUserId="+planActorId;
	openFullScreenWindow(url); */
	window.open("${pageContext.request.contextPath}/jbpm/viewHistoryProcessForm.action?currentTaskId="+currentTaskId,"_blank");
}
</script>
</body>
</html>