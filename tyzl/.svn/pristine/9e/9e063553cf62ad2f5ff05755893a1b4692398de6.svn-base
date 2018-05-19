<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>附件上传测试</title>
    <!--css sheet-->
	<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">
	<style type="text/css">
	  html,bode{
	    overflow:hidden;
	  }
	</style>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree/dtree.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAttachmentFileUpload.js"></script>
    <script type="text/javascript">
	//上传函数结束
     jQuery(function(){
		   var  renderToContainer = "id_attachment_table_container";//渲染到哪个div的id
		   var  attachmentType="附件上传";//附件分类
		   var  serialNo ="aaa";//流水号唯一标识
		   var  isReadOnly = false;//是否只能下载，不能上传和删除附件
		   var  width =1000;//上传列表宽度
		   var  height = 500;//上传列表高度
		   var  title  ="测试附件上传";//列表标题
    	   new tracywindyAttachmentFileUpload(renderToContainer,//渲染到哪个div的id
    			    attachmentType,//附件分类
    			    serialNo,//流水号唯一标识
    			    isReadOnly,//是否只能下载，不能上传和删除附件
    			    width,//上传列表宽度
    			    height,//上传列表高度
    			    title//列表标题
    	    );
     });        
   </script>
</head>
<body>
     <div id="id_attachment_table_container"></div>
</body>
</html>