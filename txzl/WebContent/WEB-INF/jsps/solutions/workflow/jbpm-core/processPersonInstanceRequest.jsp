<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix="permission" uri="/WEB-INF/tlds/permission.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程实例</title>
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
   <script type="text/javascript" >
	var processDefinitionsColumns = [
                                     {header:'发起人',name:'approval'},
	                                 {header:'流水号',name:'serialno'},
	                                 {header:'项目名称',name:'projectname'},
	                                 {header:'流程名称',name:'workflowname'},
	                                 {header:'当前任务',align:'center',name:'taskid',renderer:function(value,tableObj,columnName,columnIndex,rowData){
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
		                             {header:'流程图',name:'viewdiagram',align:'center',renderer:function(value,tableObj,columnName,columnIndex,rowData){
		                            	 return '<a href="javascript:void(0);" onclick="toProcessActivePicture(\''+rowData.deployid+'\',\''+((rowData.processinstanceid))+'\')">查看</a>';
			                         }},
	                                 {header:'流程开始时间',name:'processstart'},
	                         		 {header:'流程结束时间',name:'processend',renderer:function(value,tableObj,columnName,columnIndex,rowData){
	                         			if(rowData["processend"]){
	                         				return rowData["processend"];//+" < 已结束  > ";
	                         		    }
	                                     return  "进行中...";
	                         	     }}
	                              ];
   </script>
   <permission:action code="admin_action">
      <script  type="text/javascript">
	    processDefinitionsColumns.push({header:'操作',name:'oper',align:'center',renderer:function(value,tableObj,columnName,columnIndex,rowData){
		     return '<a href="javascript:void(0);" onclick="removeProcessInstance(\''+rowData.processinstancedbid+'\',\''+rowData.processinstanceid+'\');">删除流程实例</a>';
	    }});
	  </script>
   </permission:action>

	<script type="text/javascript" defer>
		 var tableActivityDetail= new tracywindyTable({
	          renderTo:'id_tasksContainer',
	          width:all_width,
	          height:all_height,
	          isCheck:false,
	          isExcel:true,
	          isRank:false,
	  		  toolsLeftMargin:20,
			  tools:[{
		    	  isHtml:true,
		    	  html:'全局搜索：<input type="text" style="margin-right:4px;border:1px solid #DDD;" id="id_queryWorkflowsTableInput" value="${param.queryText}" />'
		        }],
	          id:'id_tasks_table',
	          showHeader:true,
	          xmlFileName:'/jbpm/queryAllProcessInstances.xml',
	          isPage:true,
	          border:true,
	          isFit:true,
	          title:'流程信息',
	          loadMode:'ajax',
	          columns:processDefinitionsColumns,
	          params:{
	          requestInitiatorUserId:'${sessionScope["login_userid"]}'}
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
			var sheight = 600;
	        var swidth = 850;
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
	   var loadMask = null;
	   <permission:action code="admin_action">
	   function removeProcessInstance(processInstanceDBID,processInstanceId)
	   {
		   if(!confirm("确认删除该流程实例么？"))
		   {
			   return ;
		   }
		   if(!loadMask)
		   {
			   loadMask = new tracywindyLoadMask(document.body,"正在执行操作  请稍等...");
		   }
		   loadMask.show();
           ajaxRequest({
               method:'post',
               url:'${pageContext.request.contextPath}/jbpm/removeProcessInstance.action',
               success:function(res){alert("删除成功");window.location.reload();},
               failure:function(res){alert("删除失败");window.location.reload();},
               params:{
            	   processInstanceDBID  : processInstanceDBID,
            	   processInstanceId    : processInstanceId
               }
            });
	   }
	   </permission:action>
   </script>
      <script type="text/javascript">
        function toProcessForm(currentTaskId){
    		var URL = "${pageContext.request.contextPath}/jbpm/viewHistoryProcessForm.action?currentTaskId="+currentTaskId;
    		openFullScreenWindow(URL);
    	}
      </script>
</body>
</html>