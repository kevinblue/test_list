<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>罚息减免</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var currentClientWidth = document.documentElement.clientWidth;
var currentClientHeight = document.documentElement.clientHeight;
var tools = [];
tools.push({
	html: '罚息减免',
	plain: false,
	iconCls: 'icon-addfolder',
	handler: function(miniTable, buttonText){
		var rentRowData = miniTable.getSelected();
		if(rentRowData){
			var attachmentParams = "contract_id=" + rentRowData.id;
			startProcess("罚息减免流程-1",attachmentParams);
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
		title: "罚息减免",
		remoteOper : false,
		showPager: true,
		pageSize: 20,
		tools: tools,
		queryButtonColSpan: 4,
		xmlFileName: '/eleasing/workflow/rent/rent_income/rent_income_list.xml',
		params:{ismaymoney:'and vcp.penalty>0'},
		columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				/* {field: 'contractnumber', header: '业务合同编号',queryConfig:{width: 165}}, */
				{field: 'contract_put_number', header: '投放编号',queryConfig:{width: 165}},
				{field: 'projectname', header: '项目名称',queryConfig:{width: 165}},
				{field: 'rentlist', header: '计划期次'},
				{field: 'plandate', header: '收款日期',queryConfig:{type:'date',vtype:'date',range:true,format:'yyyy-MM-dd',width: 165}},
				{field: 'rent', header: '租金',summary:true,dataType:"currency"},
				{field: 'corpus', header: '本金',summary:true,dataType:"currency"},
				{field: 'interest', header: '利息',summary:true,dataType:"currency"},
				{field: 'planpenalty', header: '罚息',summary:true,dataType:"currency"},
				{field: 'curcorpusoverage', header: '剩余本金',summary:true,dataType:"currency"},
				{field: 'curinterestoverage', header: '剩余利息',summary:true,dataType:"currency"},
				{field: 'penalty', header: '剩余罚息',summary:true,dataType:"currency"},
				{field: 'custname', header: '承租人',queryConfig:{width: 165,newLine: true}},
				{field: 'projmanagename', header: '项目经理'},
				{field: 'department', header: '出单部门'}
		]
	});
});
</script>
</head>
<body>
<div id="id_table_container_rent_income"></div>
</body>
</html>