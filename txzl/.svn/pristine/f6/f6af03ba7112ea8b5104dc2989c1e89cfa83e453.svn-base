<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>租金红冲</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var currentClientWidth = document.documentElement.clientWidth;
var currentClientHeight = document.documentElement.clientHeight;
var tools = [];
tools.push({
	html: '保理买方收款红冲',
	plain: false,
	iconCls: 'icon-addfolder',
	handler: function(miniTable, buttonText){
		var row = miniTable.getSelected();
		if(row){
			var attachmentParams = "contract_id=" + row.id;
			startProcess("保理买方收款红冲流程-1",attachmentParams);
		}else{
			mini.alert("请选中要操作的数据！");
		}
	}
});
jQuery(function(){
	tenwa.createTable({
		id: "rent_red",
		renderTo: "id_table_container_rent_red",
		width: currentClientWidth,
		height: currentClientHeight,
		lazyLoad: false,
		title: "保理买方收款红冲",
		remoteOper : false,
		showPager: true,
		pageSize: 20,
		tools: tools,
		queryButtonColSpan: 6,
		queryButtonNewLine:true,
		xmlFileName: '/eleasing/workflow/factoring/factoring_red/factoring_buyer_receipt_red.xml',
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'contract_id', header: '合同编号'},
			{field: 'contract_number', header: '业务合同编号',queryConfig:{width: 165}},
			{field: 'project_name', header: '项目名称',queryConfig:{width: 165}},
			{field: 'incomerent', header: '债权实收',summary : true,dataType : "currency"},
			{field: 'cust_name', header: '保理申请人',queryConfig:{width: 165}},
			{field: 'industry_type', header: '内部行业',visible:false},
			{field: 'projmanagename', header: '项目经理'},
			{field: 'department', header: '出单部门'},
			{field: 'contractstatus', header: '项目状态'}
		]
	});
});
</script>
</head>
<body>
<div id="id_table_container_rent_red"></div>
</body>
</html>