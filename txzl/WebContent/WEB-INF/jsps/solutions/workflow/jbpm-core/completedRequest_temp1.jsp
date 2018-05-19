<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>已办事宜</title>
<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css?version=3" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTable.js"></script>
<script type="text/javascript">
	var all_width = (document.documentElement||document.body).clientWidth-2;
	var all_height = (document.documentElement||document.body).clientHeight-1;
</script>
	    <style type="text/css">
	       html,body{
	         overflow:hidden;
	       }
	    </style>
</head>
<body style="overflow:hidden;"> 
   <div id="id_tasksContainer"></div>
   <script type="text/javascript" defer>
	
	var processDefinitionsColumns = [
		{header:'流水号',name:'serialno',renderer:function(value,tableObj,columnName,columnIndex,rowData){
		 return '<a href="javascript:void(0);" onclick="toProcessForm('+rowData.taskid+')">'+rowData.serialno+'</a>';
		},width:'12%'},
		{header:'项目名称',name:'projectname',width:'25%'},
		{header:'流程名称',name:'workflowname'},
		{header:'已办类型',name:'processinstancetype',renderer:function(value){return getTaskTypeChineseName(value);},width:'7.8%'},
		{header:'当前任务',name:'taskname',renderer:function(value,tableObj,columnName,columnIndex,rowData){
              return value.substring(rowData["taskid"].length,value.length);
	    },width:'11.5%'},
		{header:'执行人编号',name:'actorid',hidden:true},
		{header:'流程开始时间',name:'processstart',width:'11%'},
		{header:'流程结束时间',name:'processend',renderer:function(value,tableObj,columnName,columnIndex,rowData){
			if(rowData["processend"]){
				return rowData["processend"];//+" < 已结束  > ";
		    }
            return  "进行中...";
	    },width:'11%'},
		{header:'操作',name:'oper',renderer:function(value,tableObj,columnName,columnIndex,rowData){
			return '<a href="javascript:void(0);" onclick="toProcessActivePicture(\''+rowData.deployid+'\',\''+rowData.processinstanceid+'\')">显示流程图</a>'
			+'&nbsp;&nbsp;&nbsp;&nbsp;'
		    +'<a href="javascript:void(0);" onclick="toProcessForm('+rowData.taskid+')">查看</a>';
		}}
	];
	var tableActivityDetail= new tracywindyTable({
		renderTo:'id_tasksContainer',
		width:all_width,
		height:all_height,
		isCheck:false,
		isRank:false,
		title:'已办事宜',
		toolsLeftMargin:20,
		tools:[{
	    	  isHtml:true,
	    	  html:'全局搜索：<input type="text" style="margin-right:4px;border:1px solid #DDD;" id="id_queryWorkflowsTableInput" value="${param.queryText}" />'
	    }],
		id:'id_tasks_table',
		showHeader:true,
		isPage:true,
		isExcel:true,
		border:true,
		xmlFileName:'/jbpm/queryCompletedTasks.xml',
		pageSize:20,
		isFit:false,
		loadMode:'ajax',
		columns:processDefinitionsColumns,
		isAutoBreakContent:true,
		params:{
			USERID:"${sessionScope['login_userid']}"
	    }
	});
	 document.getElementById("id_queryWorkflowsTableInput").onkeypress = function(evt){
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
	   function toProcessActivePicture(deployId,processInstanceId)
	   {
	        var winoption =null;//"height="+sheight+"px;width="+swidth+"px;status=no;scroll=yes;resizable=yes";
			window.open("${pageContext.request.contextPath}/workflow/jbpm/getActivedRects.action?deployId="+deployId+"&processInstanceId="+escape(encodeURIComponent(processInstanceId))+"&randomNumber="+Math.random(),"_blank");
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