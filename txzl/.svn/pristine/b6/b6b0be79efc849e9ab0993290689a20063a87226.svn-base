<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>合同信息查询</title>
	<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/my97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyOperationTable.js"></script>
	<style type="text/css">
	   html,body{
	     overflow:hidden;
	   }
	</style>
<script type="text/javascript">
//流程历史信息
function viewProjSummary(keyOne){
    var URL = "${pageContext.request.contextPath}/jbpm/getProjSummaryHistoryInfos.action?keyOne="+keyOne;
    openFullScreenWindow(URL);
}
function viewShowCondition(contractId){
	var URL = "${pageContext.request.contextPath}/leasing/selectContractCondition.action?contractid="+contractId;
    openFullScreenWindow(URL);
}
	var pageWidth  = document.documentElement.clientWidth-2;
	var pageHeight = document.documentElement.clientHeight-2;
	jQuery(function(){
   	 var table = new tracywindyOperationTable({   		 
   		 windowTop:20,
   	     border:true,
         renderTo:'id_tableContainer',
         title:'合同信息查询',
         width:parseInt("${param.tableWidth}")||pageWidth,
         height:parseInt("${param.tableHeight}")||pageHeight,
         xmlFileName:'/eleasing/jsp/query/contract_info_query.xml',
         loadMode:'ajax',
         operButtons:' ',
         isPage:true,
         isAutoBreakContent:true,
         //isFit:true,
         params:{
             condition:getFuncRightManage("${sessionScope['login_userid']}","pro.proj_dept","pro.proj_manage")
             },
         columns:[
                    {header:'合同主键',name:'cid',hidden:true},
		            {header:'项目编号',name:'projid',queryConfig:{},
		            	renderer:function(value,tableObj,columnName,columnIndex,rowData){
		                return "<a href='javascript:void(0);' onclick='viewProjSummary(\""+rowData["projid"]+"\")'>"+value+"</a>";
		    	       }
			         },
		            //{header:'项目名称',name:'projectname',queryConfig:{},width:300},
		            {header:'合同编号',name:'contractid',queryConfig:{},
		            	renderer:function(value,tableObj,columnName,columnIndex,rowData){
		                return "<a href='javascript:void(0);' onclick='viewProjSummary(\""+rowData["contractid"]+"\")'>"+value+"</a>";
		    	       }
		    	     },
		    	    //{header:'业务合同号',name:'contractnumber',queryConfig:{}},
		            {header:'承租客户',name:'custname',queryConfig:{}},
		            //{header:'信审日期',name:''},
		            {header:'内部行业',name:'industrytypename',queryConfig:{isNewLine:true}},
		            //{header:'预计起租日',name:'startdate'},
		            {header:'实际起租日',name:'actualstartdate'},
		            //{header:'预计结束日',name:'enddata'},
		            //{header:'实际结束日',name:'actualenddate'},
		            //{header:'财务起租日',name:'accountingstartdate'},
		            {header:'合作状态',name:'contractstatusname',queryConfig:{}},
		            {header:'净融资额',name:'cleanleasemoney'},
		            {header:'客户经理',name:'projmanagename',queryConfig:{}},
		            {header:'客户助理',name:'projassistname'},
		            {header:'录入人员',name:'projregistrarname'},
		            {header:'出单部门',name:'projdeptname',width:200,queryConfig:{isNewLine:true}},
		            {header:'案件类别',name:'leasformname',type:'combobox',
				    	   queryConfig:{
				    	   isNewLine:false,
				    	   xmlFileName:'\\combos\\comboDict.xml',
				           loadMode:'ajax',
				           readOnly:true,
				           displayField:'name',
				           valueField:'code',
				           params:{
				              pid:'leas_form'
				           }
				    	   } },
		            //{header:'回笼信息',name:''},
		            {header:'供应商(用","分隔)',name:'seller',queryConfig:{}}
		            
		            /*{header:'项目规模',name:'projscalename'},
		            {header:'是否第三方支付',name:'isthirdpay'},
		            {header:'租赁形式',name:'leastypename'},
		            {header:'业务类型',name:'businesstypename'},
		            {header:'业务模式',name:'businessmodename'},
		            {header:'操作',name:'assurors',
		            	renderer:function(value,tableObj,columnName,columnIndex,rowData){
		                return "<a href='javascript:void(0);' onclick='viewShowCondition(\""+rowData["cid"]+"\")'>当前商务条件查看</a>";
		    	       }
			        }*/
	        ]
   	 });
   });

</script>

</head>
<body>
	<div id="id_tableContainer"></div>
</body>
</html>