<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tenwa.kernal.utils.FileUtil,com.tenwa.kernal.utils.WebUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>新增Ad域用户</title>
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
    var constantFlagTable = "User";
	var pageWidth  = document.documentElement.clientWidth-2;
	var pageHeight = document.documentElement.clientHeight-2;
	jQuery(function(){
   	 var table = new tracywindyTable({
   	     url:'${pageContext.request.contextPath}/acl/getAdUserInfoByOU.acl',
   	     border:true,
         renderTo:'id_tableContainer',
         title:'AD域用户列表',
         width:pageWidth,
         height:pageHeight-75,
         id:'id_table',
         loadMode:'ajax',
         isPage:false,
         isFit:true,
         border:true,
         isCheck:false,
         columns:[
		            {header:'AD域账号'  ,name:'samaccountname'},
		            {header:'AD域名称',name:'name'},
		            {header:'部门',name:'department'},
		            {header:'手机号码',name:'mobile'},
		            {header:'邮箱',name:'mail'},
		            {header:'操作',name:'oper',renderer: function(value, tableObj, columnName, columnIndex, rowData) {
	                    var base = "<a href='javascript:void(0);' onclick='addAdUser("+rowData.rowIndex+");'>添加进系统</a>";
                        return base;
			        }}
	        ]
   	 });
   });
   function addAdUser(rowIndex){
	   if(window.confirm("确认添加此Ad域用户到系统表么？")){
		   ajaxRequest({
			   url:'${pageContext.request.contextPath}/acl/addAdUserToSystem.acl',
			   params:{
				   userInfo:JsonUtil.encode(getTracywindyObject("id_table").getRowDataAt(rowIndex))
			   },
			   success:function(res){
				   alert("添加Ad域用户成功！");
				   getTracywindyObject("id_table").reload();
			   },
			   failure:function(res){
				   alert("添加Ad域用户失败！");
			   }
		   });
	   }
   }
   function doOUQuery(){
	  var table = getTracywindyObject("id_table"); 
	  table.setParam("ou",document.getElementById("id_OUQuery").value);
	  table.reload();
   }
   function doOUReset(){
	   document.getElementById("id_OUQuery").value = "";
  }
</script>
</head>
<body>
    <div style="padding:5px;height:60px;text-align:center;line-height:30px;margin-bottom:5px;">
      <table style="border:1px solid silver;" align="center">
	       <tr>
		       <td >OU：<input id="id_OUQuery" type="text" style="width:400px;height:20px;border:1px solid #DDD;"/></td>
		       <td style="width:60px"><a href="javascript:void(0);" onclick="doOUQuery();" class="btn btn-primary">查询</a></td>
		       <td style="width:60px"><a href="javascript:void(0);" onclick="doOUReset();" class="btn btn-primary">清空</a></td>
	       </tr>
      </table>
      <label style="color:red;font-weight:BOLD;">（注意：OU需要按照组织机构从根到底填写，如：越秀集团,下属集团,金融集团,下属公司,融资租赁公司，用半角逗号隔开）</label>
    </div>
    <div id="id_tableContainer"></div>
</body>
</html>