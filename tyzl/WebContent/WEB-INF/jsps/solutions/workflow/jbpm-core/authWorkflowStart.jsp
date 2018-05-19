<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib uri="/minidict" prefix="mini"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>可发起的流程</title>
		<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css?version=3" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
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
   <div id="id_startflow"></div>
   <script type="text/javascript" defer>
   
   var deployedIds = ("<mini:param  name="deployedIds" />");
   jQuery(function(){
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id: "id_tasks_table",
				renderTo: "id_startflow",
				width: globalClientWidth,
				height: globalClientHeight,
				lazyLoad : false,
				showPager : true,//分页按钮是否显示
				title:'可启动的流程',
				multiSelect: false,
				remoteOper : true,
				xmlFileName:"/jbpm/queryAllDesignedWorkflows.xml",
				columns: [
					{field: 'id', header: '设计编号',visible:false},
					{field: 'deployid', header: '部署编号',visible:false},	
					{field: 'processdefinitionkey', header: '流程名称'},
					{field: 'processdefinitiondescription', header: '流程描述'},
					{header:'流程图',  field:'diagram',
                        renderer:function(e){
                        	var rowData = e.record; 
                  	 		return '<a href="javascript:void(0);" onclick="toProcessActivePicture(\''+rowData.deployid+'\',\''+(-1)+'\')">显示流程图</a>';
                        }
                  	 },
                  	{header:'流程发起',field:'oper',renderer:function(e){
               		 var rowData = e.record; 
                   	 var deploy = "";
                   	 var workflowname = rowData.processdefinitionkey;
                   	 var attachmentParams = "";
                        if(rowData.deployid)
                        {
                       	 deploy+='&nbsp;&nbsp;&nbsp;&nbsp;'+'<a href="javascript:void(0);" onclick="startProcess_1(\''+rowData["processdefinitionid"]+'\',\''+attachmentParams+'\')">发起流程</a>';
                        }
                   	return deploy;
                    }}
				],
				tools:['globalQuery','_','exportExcel'],
		        params:{
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
	var url = "${pageContext.request.contextPath}/jbpm/requestProcessTaskForm.action?currentTaskId="+currentTaskId;
	openFullScreenWindow(url);
}
function startProcess_1(processDefinitionId,attachmentParams)
{
  	 startProcess(processDefinitionId,attachmentParams);
}
</script>
</body>
</html>