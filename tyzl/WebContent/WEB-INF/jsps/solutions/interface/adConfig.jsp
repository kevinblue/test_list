<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tenwa.kernal.utils.FileUtil,com.tenwa.kernal.utils.WebUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>AD域接口配置</title>
    <!--css sheet-->
	<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindySerializeFormToJsonObject.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyOperationTable.js"></script>
    <style type="text/css">
	   html,body{
	     overflow:hidden;
	   }
	</style>
<script type="text/javascript">
	var pageWidth  = document.documentElement.clientWidth-2;
	var pageHeight = document.documentElement.clientHeight-2;
	jQuery(function(){
   	 var table = new tracywindyOperationTable({
   		 tableComment:'AD域配置',
   		 constantFlagTable:'AdConfig',
         renderTo:'id_tableContainer',
         operButtons:'修改',
         title:'AD域配置列表',
         width:pageWidth,
         height:pageHeight,
         isAutoBreakContent:true,
         id:'id_table',
         xmlFileName:'/interface/queryADConfig.xml',
         loadMode:'ajax',
         isPage:true,
         isFit:true,
         border:true,
         isRank:true,
         columns:[
		            {header:'记录编号',name:'id',hidden:true},
		            {header:'AD域主机',name:'host'},
		            {header:'AD域端口',name:'port'},
		            {header:'AD域登录用户',name:'adminname'},
		            {header:'AD域登录密码',name:'adminkey'},
		            {header:'AD域域名',name:'domainname'},
		            {header:'AD域同步用户OU',name:'ou'}
	        ]
   	 });
   });
</script>
</head>
<body>
    <div id="id_tableContainer"></div>
	<div id="id_detailInfoWindowContainer"  closed="true" modal="true" title="AD域配置信息" style="display:none;width:500px;padding-top:20px;height:340px;">
	        <center>
		        <form id="id_detailInfoForm">
			        <table style="width:90%">
			            <tr style="display:none"><td><input name="id" type="hidden" value=""/></td></tr>
			            <tr><td class="input_label_desc">AD域主机:  </td>  <td class="input_value"><input name="host" require="true" label="AD域主机"   type="text" /><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">AD域端口:</td>  <td class="input_value"><input name="port" require="true" label="AD域端口"   type="text" /><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">AD域登录用户:</td>  <td class="input_value"><input name="adminname" require="true" label="AD域登录用户"   type="text" /><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">AD域登录密码:</td>  <td class="input_value"><input name="adminkey" require="true" label="AD域登录密码"   type="text" /><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">AD域域名:</td>  <td class="input_value"><input name="domainname" require="true" label="AD域域名"   type="text" /><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">AD域同步用户OU:</td>  <td class="input_value"><textarea style="height:100px;" name="ou" require="true" label="AD域同步用户OU" ></textarea><span class="content-required">*</span></td></tr>
			            <tr style="display:none"><td><input name="enabled" type="hidden" value="true"/></td></tr>
			            <tr class="content-separator">
				            <td colspan='2'>
				                 <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary"onclick='getTracywindyObject("id_table").operationTable();'><span>确定</span></a>
						         <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='jQuery("#id_detailInfoWindowContainer").window("close");'><span>取消</span></a>
				            </td>
			            </tr>
			        </table>
		        </form>
	        </center>
	</div>
	<div id="id_detailInfoUsersWindowContainer"  closed="true" modal="true"  style="display:none;width:500px;height:300px;padding:10px;overflow:auto;">
	        <center>
		        <div id="id_detailInfoUsersContainer" style="line-height:24px;"></div>
	        </center>
	</div>
</body>
</html>