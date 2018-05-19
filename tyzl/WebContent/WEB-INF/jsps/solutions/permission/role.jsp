<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tenwa.kernal.utils.FileUtil,com.tenwa.kernal.utils.WebUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色信息管理</title>
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
   		 tableComment:'角色',
   		 constantFlagTable:'Role',
         renderTo:'id_tableContainer',
         isNeedEnabled:true,
         operButtons:'新增|修改|删除|全局搜索',
         title:'角色信息列表',
         width:pageWidth,
         height:pageHeight,
         id:'id_table',
         xmlFileName:'/acl/queryAllRole.xml',
         loadMode:'ajax',
         isPage:true,
         isFit:true,
         border:true,
         isRank:true,
         columns:[
		            {header:'记录编号',name:'id',hidden:true},
		            {header:'角色名称',name:'name',renderer:function(value,tableObj,columnName,columnIndex,rowData){
		                return "<a href='javascript:void(0);' onclick='viewUsers("+rowData.rowIndex+")'>"+value+"</a>";
		            }},
		            {header:'角色编号',name:'code'},
		            {header:'角色描述',name:'description'}
	        ]
   	 });
   });
   function viewUsers(rowIndex){
	   if(!window.loadMask){
		   window.loadMask = new tracywindyLoadMask(document.body,"数据加载中  请稍后...");  
	   }
	   window.loadMask.show();
	   var rowData = getTracywindyTable("id_table").getRowDataAt(rowIndex);
	   ajaxRequest({
            url:"${pageContext.request.contextPath}/acl/queryRoleUsers.acl?id="+rowData['id'],
            timeout:30*1000,
            success:function(res){
                if(res.responseText){
                	jQuery("#id_detailInfoUsersContainer").html(res.responseText);
                }else{
                	jQuery("#id_detailInfoUsersContainer").html("<font color='red'>该角色尚未分配用户</font>");
                }
                jQuery("#id_detailInfoUsersWindowContainer").show();
                jQuery("#id_detailInfoUsersWindowContainer").window({
                    title:'{0}用户列表'.replace("{0}",rowData["name"]+"（"+rowData["code"]+"）"),
                    top:20
                });
                jQuery("#id_detailInfoUsersWindowContainer").window('open');
            	window.loadMask.hide();
            },
            failure:function(res){alert("请求失败!!!");window.loadMask.hide();}
	   });
  } 
</script>
</head>
<body>
    <div id="id_tableContainer"></div>
	<div id="id_detailInfoWindowContainer"  closed="true" modal="true" title="角色信息管理" style="display:none;width:500px;height:300px;padding-top:20px;">
	        <center>
		        <form id="id_detailInfoForm">
			        <table style="width:90%">
			            <tr style="display:none"><td><input name="id" type="hidden" value=""/></td></tr>
			            <tr><td class="input_label_desc">角色名称:  </td>  <td class="input_value"><input name="name" require="true" label="角色名称"   type="text" /><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">角色编号:</td>  <td class="input_value"><input name="code" require="true" label="角色编号"   type="text" /><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">角色描述:     </td><td  class="input_value"><textarea name="description"></textarea><span class="content-required">&nbsp;&nbsp;</span></td></tr>
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