<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>租金计划修改</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var currentClientWidth = document.documentElement.clientWidth;
var currentClientHeight = document.documentElement.clientHeight;
var tools = [];
tools.push({
	html:'不规则调息流程',
	plain:false,
	iconCls:'icon-addfolder',
	handler:function(miniTable, buttonText){
		var row = miniTable.getSelected();
		if(row){
			var attachmentParams = "contract_id="+row.id;
			startProcess("不规则调息-1",attachmentParams); 
		}else{
			mini.alert("请选中要操作的数据！");
		}
	}
});
jQuery(function(){
	tenwa.createTable({
		id:"contract_rentchange_irregular",
		renderTo:"id_table_container_contract_rentchange_irregular",
		width:currentClientWidth,
		height:currentClientHeight,
		lazyLoad:false,
		title:"不规则调息",
		remoteOper :false,
		queryButtonColSpan :2,
		showPager:true,
		pageSize:20,
		tools:tools,
		xmlFileName:'/eleasing/workflow/contract/contract_planchange/contract_planchange_open_list.xml',
		columns:[
			{type:'indexcolumn'},
			{type:'checkcolumn'},
			{field:'id', header:'id', visible:false},
			{field:'contract_id', header:'合同编号',queryConfig:{}},
			{field:'contract_number', header:'业务合同编号',queryConfig:{}},
			{field:'project_name', header:'项目名称',queryConfig:{}},
			{field:'cust_name', header:'客户名称',queryConfig:{newLine:true}},
			{field:'card_no', header:'身份证/组织机构代码',queryConfig:{}},
			{field:'industry_type', header:'内部行业'},
			{field:'projmanagename', header:'项目经理'},
			{field:'department', header:'出单部门'},
			{field:'proj_status', header:'项目状态'}
		]
	});
});
</script>
</head>
<body>
<div id="id_table_container_contract_rentchange_irregular"></div>
</body>
</html>