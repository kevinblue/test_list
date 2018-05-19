<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>项目信息查询</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>	
</head>
<%@include file="/base/mini.jsp"%>
<body style="overflow:hidden;"> 
   <div id="id_proj_search"></div>
   <script type="text/javascript" defer>
   var deployedIds = ("${applicationScope['userOwnedWorkflowStartSqlIds'][sessionScope['login_username']]}");
   jQuery(function(){
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			var statusnamedate=[{id:'项目立项',text:'项目立项'},{id:'项目信审(审批通过)',text:'项目信审(审批通过)'},{id:'项目复议',text:'项目复议'},{id:'项目否决',text:'项目否决'},{id:'项目撤销',text:'项目撤销'}];
			new ApTable({
				id:"proj_search",
				renderTo:"id_proj_search",
				width:globalClientWidth,
				height:globalClientHeight,
				showPager :true,
				title:'项目信息查询',
				queryButtonColSpan:2,
				multiSelect:false,
				isExcel:true,
				remoteOper :true,
				xmlFileName:"/eleasing/workflow/proj/proj_search/proj_info_query.xml",
				columns:[
					{field:'projid', header:'项目编号',queryConfig:{},
		            	        renderer:function(e){
		            		           var rowData = e.record; 
			                           return "<a href='javascript:void(0);' onclick='viewProjSummary(\""+rowData["projid"]+"\")'>"+rowData["projid"]+"</a>";}},	
					{field:'custname', header:'承租客户',queryConfig:{}},
					{field:'industrytypename',header:'内部行业',queryConfig:{}},
                  	{field:'statusname',header:'项目状态',
	                  	             queryConfig:{
                  		                 newLine:true,
						                    type:'combobox',
						              valueField:'id',
						               textField:'text',
						             fieldVisible:true,
						                     data:statusnamedate}},
                  	{field:'projdate',header:'项目时间',allowSort:true},
 		            {field:'projmanagename',header:'项目经理',queryConfig:{}},
 		            {field:'projassistname',header:'项目协办'},
 		            {field:'projregistrarname',header:'经办人'},
 		            {field:'projdeptname',header:'项目出单部门',queryConfig:{}},
 		            {field:'leasformname',header:'租赁形式',
				    	             queryConfig:{
				    	                    type:'combobox',
				    	                 newLine:true,
				    	                  params:{pid:'leas_form',xmlFileName:'combos/comboDict.xml'},
				                        readOnly:false,
				                       textField:'name',
				                      valueField:'value'}},
				    {field:'seller',header:'供应商(用","分隔)',width:200,queryConfig:{}}
                 ],
		        params:{
		        	 //condition:getFuncRightManage("${sessionScope['login_userid']}","pro.proj_dept","pro.proj_manage")
			  	}
				
			});
		});
	});
	//流程历史信息
	function viewProjSummary(keyOne){
	    var URL = "${pageContext.request.contextPath}/jbpm/getProjSummaryHistoryInfos.action?keyOne="+keyOne;
	    openFullScreenWindow(URL);
	}
</script>
</body>
</html>