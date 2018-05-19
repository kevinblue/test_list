<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>项目预览</title>
		<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css?version=3" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAttachmentFileUpload.js"></script>
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
<body style="overflow:hidden;"> 
   <div id="id_tasksContainer2" style="width: 100%;height: 100%;"></div>
   <script type="text/javascript" defer>
   jQuery(function(){
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id: "id_tasks_table1",
				renderTo: "id_tasksContainer2",
				width: '100%',
				height: '100%',
				showPager : false,//分页按钮是否显示
				title:'项目预览',
				remoteOper : false,
				multiSelect: false,
				data: datas_attachment,
				columns: [
					{type : 'indexcolumn'},
					{field: 'attachmentType', header: '附件类型'},
					{field: 'attachmentName', header: '附件名字'
					},
					{field: 'attachmentSize', header: '附件大小'
					},
					{field: 'attachmentUploadTime', header: '附件上传时间'
					},
					{field: 'attachmentUploadUser', header: '附件上传人',dateFormat : 'yyyy-MM-dd HH:mm:ss' 
					},
					{field: 'attachmentDownloadCount', header: '附件下载次数',dateFormat : 'yyyy-MM-dd HH:mm:ss' 
					},
					{header:'操作',field:'attachmentId',align:"center",
						renderer:function(e){
							var rowData = e.record;
							return "<a href='javascript:void(0);' onclick='downloadUploadFile(\""+rowData['attachmentId']+"\")'>下载</a>";
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
	function __getDocId(){
		
	}
</script>
</body>
</html>