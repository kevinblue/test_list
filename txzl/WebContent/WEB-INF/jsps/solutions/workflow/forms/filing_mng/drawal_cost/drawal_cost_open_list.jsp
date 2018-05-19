<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>撤销诉讼流程</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var currentClientWidth = document.documentElement.clientWidth;
var currentClientHeight = document.documentElement.clientHeight;
jQuery(function(){
	tenwa.createTable({
		id: "table_id1",
		renderTo: "id_table_container_onhire_contract_change",
		width: currentClientWidth,
		height: currentClientHeight,
		lazyLoad: false,
		title: "撤销诉讼流程",
		remoteOper : false,
		showPager: true,
		pageSize: 20,
		tools: [
		{html:'撤销诉讼',
			 plain:true,
			 iconCls:'icon-addfolder',
			 handler:function(miniTable, buttonText) {
					var row = miniTable.getSelected();
					if(row){	
						if(row.susongstatu=="已撤销"){
							mini.alert('该流程已撤销，请重新选择！');
							return;
						}; 
						var attachmentParams = "contract_id="+row.id+"&lawnum="+row.lawnum+"&filingid="+row.filingid;
						startProcess("撤销诉讼-1",attachmentParams); 
					}else{
						mini.alert('请你选择需要发起的流程数据！！！');
					}						
				}
			} 
		],
		queryButtonColSpan: 2,
		xmlFileName: '/eleasing/jsp/drawal_mng/drawal_cost_list.xml',
		params:{contractstatus:"31"},
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'lawnum', header: '法务编号',queryConfig:{}},
			{field: 'filingid', header: '立案id',visible:false},
			{field: 'filingno', header: '案号'},
			{field: 'filinggact', header: '受理法院'},
			{field: 'filingdate', header: '立案日期'},
			{field: 'contract_id', header: '合同编号',queryConfig:{}},
			{field: 'contract_number', header: '业务合同编号',queryConfig:{}},
			{field: 'project_name', header: '项目名称',queryConfig:{newLine: true}},
			{field: 'cust_name', header: '客户名称',queryConfig:{}},
			{field: 'card_no', header: '身份证/组织机构代码',queryConfig:{}},
			{field: 'industry_type', header: '内部行业'},
			{field: 'projmanage', header: '项目经理'},
			{field: 'department', header: '出单部门'},
			{field: 'contractstatus', header: '合同状态'},
			{field: 'susongstatu', header: '诉讼状态'}
		]
	});
});
</script>
</head>
<body>
<div id="id_table_container_onhire_contract_change"></div>
</body>
</html>