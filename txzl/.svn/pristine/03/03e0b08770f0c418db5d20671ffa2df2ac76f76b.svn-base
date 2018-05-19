<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>罚息通知书</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var currentClientWidth = document.documentElement.clientWidth;
var currentClientHeight = document.documentElement.clientHeight;
var tools = [];
tools.push({
	html: '罚息通知书',
	plain: false,
	iconCls: 'icon-addfolder',
	handler: function(miniTable, buttonText){
		var rentRowData = miniTable.getSelected();
		if(rentRowData){
			var attachmentParams = "contract_id=" + rentRowData.id;
			startProcess("罚息通知书-1",attachmentParams);
		}else{
			mini.alert("请选中要操作的数据！");
		}
	}
});
jQuery(function(){
	//流程列表
	tenwa.createTable({
		id: "rent_income",
		renderTo: "id_table_container_rent_income",
		width: currentClientWidth,
		height: currentClientHeight,
		lazyLoad: false,
		title: "罚息支付通知书",
		remoteOper : false,
		showPager: true,
		pageSize: 20,
		tools: tools,
		queryButtonColSpan: 10,
		queryButtonNewLine:true,
		xmlFileName: '/eleasing/workflow/rent/rent_income/rent_income_list.xml',
		params:{ismaymoney:'and PENALTYOVERAGE<>0'},
		columns: [
		      	{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'contract_id', header: '合同编号'},
				{field: 'contract_number', header: '业务合同编号',queryConfig:{width: 165}},
				{field: 'project_name', header: '项目名称',queryConfig:{width: 165}},
				{field: 'currentoverage', header: '租金余额',summary : true,dataType : "currency"},
				{field: 'penaltyoverage', header: '罚息余额',summary : true,dataType : "currency"},
				{field: 'cust_name', header: '客户名称',queryConfig:{width: 165,newLine: false}},
				{field: 'industry_type', header: '内部行业'},
				{field: 'projmanagename', header: '项目经理'},
				{field: 'department', header: '出单部门'},
				{field: 'contractstatus', header: '项目状态'}
		]
	});
});
</script>
</head>
<body>
<div id="id_table_container_rent_income"></div>
</body>
</html>