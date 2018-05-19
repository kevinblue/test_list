<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib uri="/minidict" prefix="mini"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>未办事宜人员转移</title>
		<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css?version=3" rel="stylesheet" type="text/css"/>
		<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTable.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyCommonUserSelection.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
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
<body style="overflow:hidden;"> 
   <div id="id_tasksContainer"></div>
   <script type="text/javascript" defer>
	var processDefinitionsColumns = [
		{header:'流水号',name:'serialno',renderer:function(value,tableObj,columnName,columnIndex,rowData){
		 return '<a href="javascript:void(0);" onclick="toProcessForm('+rowData.taskid+',\''+rowData.actorid+'\',\''+rowData.tasktype+'\')">'+rowData.serialno+'</a>';
		}},
		{header:'项目名称',name:'projectname'},
		{header:'流程名称',name:'workflowname'},
		{header:'任务名称',name:'taskname'},
		{header:'执行人',name:'planuser',width:150,renderer:function(value,tableObj,columnName,columnIndex,rowData){
			return rowData.planusername+"（"+rowData.planrealname+"）<br/><a href='javascript:void(0);' onclick='changePlanUser("+rowData.rowIndex+",0);'>项目移交</a>&nbsp;&nbsp;<a href='javascript:void(0);' onclick='changePlanUser("+rowData.rowIndex+",1);'>同用户全部移交</a>";
		}},
		{header:'任务类型',name:'tasktype',renderer:function(value){return getTaskTypeChineseName(value);},width:80},
		{header:'任务开始时间',name:'taskstart',width:150},
		{header:'执行人编号',name:'actorid',hidden:true},
		{header:'操作',name:'oper',renderer:function(value,tableObj,columnName,columnIndex,rowData){
		return '<a href="javascript:void(0);" onclick="toProcessActivePicture(\''+rowData.deployid+'\',\''+rowData.processinstanceid+'\',\''+rowData.actorid+'\')">显示流程图</a>'
		+'&nbsp;&nbsp;&nbsp;&nbsp;'
		+'<a href="javascript:void(0);" onclick="toProcessForm('+rowData.taskid+',\''+rowData.actorid+'\',\''+rowData.tasktype+'\')">查看</a>';
		}}
	];
	var tableActivityDetail= new tracywindyTable({
		renderTo:'id_tasksContainer',
		isAutoBreakContent:true,
		width:all_width,
		height:all_height,
		isCheck:false,
		isRank:false,
		toolsLeftMargin:20,
		tools:[{
	    	  isHtml:true,
	    	  html:'全局搜索：<input type="text" style="margin-right:4px;border:1px solid #DDD;" id="id_queryWorkflowsTableInput" value="<mini:param  name=\'queryText\'/>" />'
	        }],
		id:'id_tasks_table',
		xmlFileName:'/jbpm/queryPendingTasksChangePlanUser.xml',
		isFit:true,
		isPage:true,
		border:true,
		title:'项目移交',
		loadMode:'ajax',
		isExcel:true,
		columns:processDefinitionsColumns,
		params:{
	
		  PENDING:true
		}
	});
	 document.getElementById("id_queryWorkflowsTableInput").onkeypress = function(evt){
		 var e  = getEvent(evt);
	     var code = e.keyCode||e.charCode;
	     if(13 == code){
	         var workflowsTable = getTracywindyTable("id_tasks_table");
	         workflowsTable.sets({
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
var globalFlag = 0;
function changePlanUser(rowIndex,flag){
	globalFlag = flag;
	var rowData = getTracywindyObject("id_tasks_table").getRowDataAt(rowIndex);
	var $window = jQuery("#id_detailInfoWindowContainer");
	var $windowForm = jQuery("#id_detailInfoWindowContainer form");
	
	if(0 == flag){
		$windowForm.find("tr[displayFlag='single']").show();
	}
	else if(1 == flag){
		$windowForm.find("tr[displayFlag='single']").hide();
	}
	$windowForm.form('clear');
	$windowForm.form('load',rowData);
    $window.show();
    $window.dialog({
        top: 20,
        autoScroll: true
    });
    $window.dialog('open');
}
var loadMask = null;
function submitChangePlanUser(){
	if(!Validator.Validate(jQuery("#id_detailInfoWindowContainer form")[0],1)){
		return ;
	}
	jQuery("#id_detailInfoWindowContainer").dialog('close');
	if(!loadMask){
		loadMask = new tracywindyLoadMask(document.body,'数据处理中 请稍后 ...');
	}
	loadMask.show();
	var $windowForm = jQuery("#id_detailInfoWindowContainer form");
	$windowForm.find("input[name='changeplanuserflag']").val(globalFlag);
	$windowForm.submit();
	return false;
}
function submitCallBack(returnInfo){
	getTracywindyObject("id_tasks_table").reload();
	loadMask.hide();
}
jQuery(function(){
	var $windowForm = jQuery("#id_detailInfoWindowContainer form");
	new tracywindyCommonUserSelection({
		displayInput:$windowForm.find("input[name='changetouserrealname']")[0],
		hiddenInput:$windowForm.find("input[name='changetouserid']")[0],
		id:'id_chose_user'
	});
});
</script>
   	<div id="id_detailInfoWindowContainer"  closed="true" modal="true" title="移交流程信息" style="display:none;width:500px;padding-top:20px;height:300px;">
	        <center>
		        <form target="submitIframeName" action="${pageContext.request.contextPath}/jbpm/updatePendingUserToOther.action">
			        <input type="hidden" name="changeplanuserflag" require="false" label="标志位"/>
			        <table style="width:90%">
			            <tr style="display:none;"><td class="input_label_desc">记录编号:  </td>  <td class="input_value"><input name="actorid" require="true" label="记录编号" readOnly  type="text" /><span class="content-required">&nbsp;&nbsp;</span>
			            <tr displayFlag="single"><td class="input_label_desc">任务类型:  </td>  <td class="input_value"><input name="tasktype" require="false" label="任务类型" readOnly  type="text" /><span class="content-required">&nbsp;&nbsp;</span>
			            <tr displayFlag="single"><td class="input_label_desc">项目名称:  </td>  <td class="input_value"><input name="projectname" require="false" label="项目名称" readOnly  type="text" /><span class="content-required">&nbsp;&nbsp;</span>
			            <tr displayFlag="single"><td class="input_label_desc">流程名称:</td>  <td class="input_value"><input name="workflowname" require="false" label="流程名称"  readOnly type="text" /><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr displayFlag="single"><td class="input_label_desc">任务名称:</td>  <td class="input_value"><input name="taskname" type="text" require="false" readOnly label="任务名称"  /><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr style="display:none;"><td class="input_label_desc">当前执行人:  </td>  <td class="input_value"><input name="planuserid" type="text"  require="true" readOnly/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr><td class="input_label_desc">当前执行人:  </td>  <td class="input_value"><input name="planrealname" type="text" readOnly/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr><td class="input_label_desc">转移至:  </td>  <td class="input_value"><input name="changetouserrealname" label="转移至"  require="false" type="text" readOnly onclick="getTracywindyObject('id_chose_user').show();"/><span class="content-required">*</span></td></tr>
			            <tr style="display:none;"><td class="input_label_desc">转移至:  </td>  <td class="input_value"><input name="changetouserid" label="转移至"  require="true" type="text" readOnly/><span class="content-required">*</span></td></tr>
			            <tr ><td><input name="enabled" type="hidden" value="true"/></td></tr>
			            <tr class="content-separator">
				            <td colspan='2'>
				                 <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary"onclick='submitChangePlanUser();'><span>确定</span></a>
						         <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='jQuery("#id_detailInfoWindowContainer").window("close");'><span>取消</span></a>
				            </td>
			            </tr>
			        </table>
		        </form>
	        </center>
	</div>
	<iframe style="display:none;" name="submitIframeName"></iframe>
</body>
</html>