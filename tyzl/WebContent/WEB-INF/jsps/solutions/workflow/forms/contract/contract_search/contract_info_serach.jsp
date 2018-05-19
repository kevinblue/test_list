<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>合同信息查询</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
</head>
<%@include file="/base/mini.jsp"%>
<body style="overflow:hidden;"> 
   <div id="id_tasksContainer"></div>
   <script type="text/javascript" defer>
   var deployedIds = ("${applicationScope['userOwnedWorkflowStartSqlIds'][sessionScope['login_username']]}");
   jQuery(function(){
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable){ 
					
			var contractstatusnamedata=[{id:'合同审批',text:'合同审批'},{id:'合同起租 ',text:'合同起租 '},{id:'合同撤销',text:'合同撤销'},{id:'合同结束 ',text:'合同结束 '}];
			new ApTable({
				id:"id_tasks_table",
				renderTo:"id_tasksContainer",
				width:globalClientWidth,
				height:globalClientHeight,
				showPager :true,
				title:'合同信息查询',
				queryButtonColSpan :6,
				queryButtonNewLine:true,
				multiSelect:false,
				isExcel:true,
				remoteOper :true,
				xmlFileName:"/eleasing/workflow/contract/contract_search/contract_info_query.xml",
				columns:[
					  {field:'cid', header:'项目信息查询',visible:false},
					  {field:'projid', header:'项目编号',queryConfig:{},
		            	         renderer:function(e){
		            		               var rowData = e.record; 
			                               return "<a href='javascript:void(0);' onclick='viewProjSummary(\""+rowData["projid"]+"\")'>"+rowData["projid"]+"</a>";}},	
					{field:'contractid', header:'合同编号',visible:false,queryConfig:{}},
					{field:'contractputnumber', header:'投放编号',queryConfig:{}},
					{field:'custname', header:'承租客户',queryConfig:{}},
					{field:'industrytypename',header:'内部行业',   
						       queryConfig:{
				    	              type:'combobox',
				    	           newLine:true,
				    	            params:{xmlFileName:'combos/comboDict.xml',pid:'cust_kind'},
				                  readOnly:false,
				                 textField:'name',
				                valueField:'value',
				              showNullItem:true}},
                  	{field:'actualstartdate',header:'实际起租日'},
                  	{field:'contractstatusname',header:'合同状态',
                      	       queryConfig:{
						              type:'combobox', 
						          required:true, 
						       multiSelect:false, 
						        valueField:'id', 
						         textField:'text', 
						      fieldVisible:true,
						              data:contractstatusnamedata}},
                  	{field:'projdate',header:'项目时间',allowSort:true},
                  	{field:'cleanleasemoney',header:'净融资额'},
 		            {field:'projmanagename',header:'客户经理',queryConfig:{}},
 		            {field:'projassistname',header:'客户助理'},
 		            {field:'projregistrarname',header:'录入人员'},
 		            {field:'projdeptname',header:'出单部门',queryConfig:{newLine:true}},
 		            {field:'leasformname',header:'租赁形式',
				    	        queryConfig:{
				    	               type:'combobox',
				    	            newLine:false,
				    	             params:{pid:'leas_form',xmlFileName:'combos/comboDict.xml'},
				                   readOnly:false,
				                  textField:'name',
				                 valueField:'value'}},
				   {field:'seller',header:'供应商(用","分隔)',queryConfig:{}},
				   {field:'oper',header:'操作',allowSort: false,renderer:function(e){
	            		 var rowData = e.record; 
	            		 var str="";
	            		 str="<a href='javascript:void(0);' onclick='zijinshoufu(\""+rowData['cid']+"\",\""+rowData['pid']+"\")'>查看详情</a>";
		                return str;}}
                 ],
		        params:{
		        	 //condition:getFuncRightManage("${sessionScope['login_userid']}","pro.proj_dept","pro.proj_manage")
			  	}
				
			});
		});
	});
	 $("id_queryWorkflowsTableInput").onkeypress = function(evt){
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
	function toProcessActivePicture(deployId,processInstanceId,planActorId)
	{
		var url = "${pageContext.request.contextPath}/workflow/jbpm/getActivedRects.action?deployId="+deployId+"&processInstanceId="+escape(encodeURIComponent(processInstanceId))+"&jbpmWorkflowHistoryInfoUserId="+planActorId+"&randomNumber="+Math.random();
		openFullScreenWindow(url);
	}
	//查看详情
	function zijinshoufu(cid,pid)
	{
		var url = getRootPath()+"/acl/queryContractInfoDetail.acl?contractid="+cid;
		openFullScreenWindow(url);
	}
/* 	function shangwutiaojian(cid)
	{
		 $.ajax({
			 method:"post",
             url:this.getRootPath() + "/table/getTableData.action",
 			 async:false,
             data:{username:$("#username").val(), content:$("#content").val()},
             dataType:"json",
             success:function(data){
             	
             }
         });
		
		var url = getRootPath()+"/workflow/forms/contract/contract_search/fund_fund_plan_base.bi?contractid="+cid;
		openFullScreenWindow(url);
	} */
	function doQueryByText_pending()
	{
	  var contentText = document.all['id_contextText_pending'].value;
	  var tableContact = getTracywindyTable("pendingRequestTable");
	  tableContact.params['proj_id'] = contentText.toUpperCase();
	  tableContact.reload();
	}
	//流程历史信息
	function viewProjSummary(keyOne){
	    var URL = "${pageContext.request.contextPath}/jbpm/getProjSummaryHistoryInfos.action?keyOne="+keyOne;
	    openFullScreenWindow(URL);
	}
	
</script>
</body>
</html>