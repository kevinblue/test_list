<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tenwa.kernal.utils.FileUtil,com.tenwa.kernal.utils.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息管理</title>
    <!--css sheet-->
	<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">
	<!--javascript libray-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
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
    var constantFlagTable = "User";
	var pageWidth  = document.documentElement.clientWidth-2;
	var pageHeight = document.documentElement.clientHeight-2;
	jQuery(function(){
	//alert("${sessionScope['login_userlanguage']}");
	dictOnSelect('id_language','userlocal','language',"${sessionScope['login_userlanguage']}",null,' label="用户语言"',false,null,null,null,null);
   	 var table = new tracywindyOperationTable({
   		 tableComment:'用户',
   		 constantFlagTable:'User',
   	     enabledPromit:"在职",
   	     disabledPromit:"离职",
   	     isNeedEnabled:true,
   	     operButtons:'新增|修改|删除|全局搜索|状态',
   	     border:true,
         renderTo:'id_tableContainer',
         title:'用户信息列表',
         width:pageWidth,
         height:pageHeight,
         id:'id_table',
         xmlFileName:'/acl/queryAllUser.xml',
         loadMode:'ajax',
         isPage:true,
         isExcel:true,
         isFit:true,
         border:true,
         resetFormCallback:function(table,$form,operFlag){
			if("add" == operFlag){
				getTracywindyObject('id_combo_id_language').setValue("${sessionScope['login_userlanguage']}");
			}  
			if ("update" == operFlag){
				
			}		
		},
		loadFormDataCallBack:function(table,$form,rowIndex){
			var rowData = table.getRowDataAt(rowIndex);
			getTracywindyObject('id_combo_id_language').setValue(rowData.userlocal);
		},
         submitReturnCallBack:function(table,defaultAlertText,responseText,windowContainerIdTable){
            if(responseText){
                alert(responseText);
                return;
            }else{
                alert(defaultAlertText);
	   	        try {
		             jQuery("#" + windowContainerIdTable).dialog('close');
		        } catch(e) {}
                table.reload();
            }
         },
         isNeedPasswordReset:true,
         columns:[
		            {header:'记录编号',name:'id',hidden:true},
		            {header:'登录名',name:'username'},
		            {header:'员工编号',name:'code'},
		            {header:'真实姓名',name:'realname'},
		            {header:'联系电话',name:'telephone'},
		            {header:'用户邮箱',name:'email'},
		            {header:'语言',name:'userlocalname'},
		            {header:'userlocal',name:'userlocal',hidden:true},
		            {hidden:true,header:'Ad域部门',name:'deptname'},
		            {hidden:true,header:'是否与AD域同步',name:'operad',renderer: function(value, tableObj, columnName, columnIndex, rowData) {
	                    var base = "<font color='red'>{1}</font>&nbsp;&nbsp;<a href='javascript:void(0);' onclick='changeIsException(\""+rowData["id"]+"\");'>{2}</a>";
	                    if("false" == rowData["isexcepted"]){
	                    	base = base.replace("{1}","同步").replace("{2}","更改为不同步");
		                }else{
		                	base = base.replace("{1}","不同步").replace("{2}","更改为同步");
			            }
                        return base;
			        }}
	        ]
   	 });
   });
   function changeIsException(userId){
	   if(window.confirm("确认更改与AD同步状态么？")){
		   ajaxRequest({
			   url:'${pageContext.request.contextPath}/acl/updateChangeIsException.acl',
			   params:{
				   userId:userId
			   },
			   success:function(res){
				   alert("更改与AD同步状态成功！");
				   getTracywindyObject("id_table").reload();
			   },
			   failure:function(res){
				   alert("更改与AD同步状态失败！");
			   }
		   });
	   }
   }
</script>
</head>
<body>
    <div id="id_tableContainer"></div>
	<div id="id_detailInfoWindowContainer"  closed="true" modal="true" title="用户信息管理" style="display:none;width:500px;padding-top:20px;">
	        <center>
		        <form id="id_detailInfoForm">
			        <table style="width:90%">
			            <tr style="display:none"><td><input name="id" type="hidden" value=""/></td></tr>
			            <tr><td class="input_label_desc">登录名:  </td>  <td class="input_value"><input name="username" require="true" label="登录名"   type="text" /><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">员工编号:</td>  <td class="input_value"><input name="code" require="true" label="员工编号"   type="text" /><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">真实姓名:</td>  <td class="input_value"><input name="realname" require="true" label="真实姓名" type="text" /><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">联系电话:</td>  <td class="input_value"><input name="telephone" type="text" /><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr><td class="input_label_desc">用户邮箱:</td>  <td class="input_value"><input name="email" type="text" /><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr><td class="input_label_desc"><spring:message code="Language" text="语言:"></spring:message></td><td class="input_value"><div style="float:left" class="leftComboContainer" id="id_language" label="语言"></div></div><font class="required-content">*</font></tr>
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
</body>
</html>