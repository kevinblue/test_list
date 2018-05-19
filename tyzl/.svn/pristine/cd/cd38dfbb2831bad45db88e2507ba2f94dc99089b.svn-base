<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib uri="/minidict" prefix="mini"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>可发起的流程</title>
<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
<style type="text/css">
   body,html{
      overflow:hidden;
   }
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>

<script type="text/javascript">
	var all_width  = document.documentElement.clientWidth-2;
	var all_height = document.documentElement.clientHeight-1;
</script>
</head>
<body>
	<form action="" method="post" id="id_workflow_form">
	    <input type="hidden" id='id_form_workflow_id'          name="id"/>
	    <input type="hidden" id='id_form_workflow_name'        name="workflow_name"/>
	    <input type="hidden" id='id_form_workflow_version'     name="workflow_version"/>
	    <input type="hidden" id='id_form_workflow_jpdlVersion' name="workflow_jpdlVersion"/>
	    <input type="hidden" id='id_form_workflow_description' name="workflow_description"/>
	    <input type="hidden" id='id_form_workflow_isDeployed'  name="workflow_isDeployed"/>
	    <input type="hidden" id='id_form_workflow_designerWorkflowJsonString'        name="workflow_designerWorkflowJsonString"/>
	</form>

    <div id="id_processDefinitionsContainer"></div>
    <script type="text/javascript" language="javascript">
          function startProcess_(processDefinitionId,attachmentParams)
          {
              if(window.confirm("确认发起新流程?"))
              {
            	  startProcess(processDefinitionId,attachmentParams);
            	  //window.open("${pageContext.request.contextPath}/jbpm/startDeployedProcess.action?processDefinitionId="+escape(encodeURIComponent(processDefinitionId))+"&"+attachmentParams,"_blank");
              }
          }
    </script>
</body>
<script type="text/javascript">
	
	var globalRowIndex = -1;
	var loadMask = null;//new tracywindyLoadMask(document.body,"操作进行中，请稍等...");
	var form =$("#id_workflow_form")[0];
	//显示流程图
	function toProcessActivePicture(deployId,processInstanceId)
	{
	   //var sheight = 600;
	   //var swidth = 850;
	   var winoption =null;//"height="+sheight+"px;width="+swidth+"px;status=no;scroll=yes;resizable=yes";
	   window.open("${pageContext.request.contextPath}/workflow/jbpm/getActivedRects.action?deployId="+deployId+"&processInstanceId="+escape(encodeURIComponent(processInstanceId))+"&randomNumber="+Math.random(),"_blank");
	}
</script>
<c:set var="deployedIds" value="${applicationScope['userOwnedWorkflowStartSqlIds'][sessionScope['login_username']]}"></c:set>
<script type="text/javascript">
		var processDefinitionsDatas = [];
		var processDefinitionsColumns = [
		                                 {header:'设计编号',name:'id',hidden:true},
		                                 {header:'部署编号',name:'deployid',hidden:true},
		                                 {header:'流程名称',name:'processdefinitionkey'},
		                                 {header:'流程描述',name:'processdefinitiondescription'},
		                                 {header:'流程图',  name:'diagram',
			                                  renderer:function(value,tableObj,columnName,columnIndex,rowData){
		                                	 		return '<a href="javascript:void(0);" onclick="toProcessActivePicture(\''+rowData.deployid+'\',\''+(-1)+'\')">显示流程图</a>';
			                                  }
			                             },
		                                 {header:'流程发起',name:'oper',renderer:function(value,tableObj,columnName,columnIndex,rowData){
		                                	 var deploy = "";
		                                	 var workflowname = rowData.processdefinitionkey;
		                                	 var attachmentParams = "";
			                                 if(rowData.deployid)
			                                 {
			                                	 deploy+='&nbsp;&nbsp;&nbsp;&nbsp;'+'<a href="javascript:void(0);" onclick="startProcess_(\''+rowData.processdefinitionid+'\',\''+attachmentParams+'\')">发起流程</a>';
				                             }
		                                	return deploy;
		                                 }}
		                              ];
		
		     var deployedIds = ("<mini:param  name='deployedIds' />");
			 var tableActivityDetail= new tracywindyTable({
		          renderTo:'id_processDefinitionsContainer',
		          width:all_width,
		          height:all_height,
		          isCheck:false,
		          isRank:false,
		          isExcel:false,
		          title:'可启动的流程',
			      border:true,
		          toolsLeftMargin:20,
				  tools:[{
			    	  isHtml:true,
			    	  html:'全局搜索：<input type="text" style="margin-right:4px;border:1px solid #DDD;" id="id_queryWorkflowsTableInput" value="<mini:param  name='queryText'/>" />'
			        }],
		          id:'id_workflows_table',
		          showHeader:true,
		          isPage:false,
		          isFit:true,
		          xmlFileName:'/jbpm/queryAllDesignedWorkflows.xml',
		          loadMode:'ajax',
		          columns:processDefinitionsColumns,
		          params:{
				      deployedIds: (!deployedIds ? "null" : deployedIds)
		          }
		       });
			 document.getElementById("id_queryWorkflowsTableInput").onkeypress = function(evt){
				 var e  = getEvent(evt);
			     var code = e.keyCode||e.charCode;
			     if(13 == code){
			         var workflowsTable = getTracywindyTable("id_workflows_table");
			         workflowsTable.setParams({
			                queryText:this.value.toUpperCase()
			         });
			         workflowsTable.reload();
			     }
			 };
</script>
</html>