<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>重大事项跟踪流程</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var currentClientWidth = document.documentElement.clientWidth;
var currentClientHeight = document.documentElement.clientHeight;
jQuery(function(){
	tenwa.createTable({
		id: "onhire_contract_change",
		renderTo: "id_table_container_onhire_contract_change",
		width: currentClientWidth,
		height: currentClientHeight,
		lazyLoad: false,
		title: "重大事项跟踪流程",
		remoteOper : false,
		showPager: true,
		pageSize: 20,
		tools: [{html:'发起重大事项跟踪流程',
					 plain:true,
					 iconCls:'icon-addfolder',
					 handler:function(miniTable, buttonText) {
							var row = miniTable.getSelected();
							if(row){
								//var attachmentParams = "contract_id="+row.id+"&dnum="+row.dnum;
				        		var attachmentParams = "contract_id="+row.id+"&custid="+row.custid+"&dnum="+row.dnum;
				        		startProcess("重大事项跟踪流程-1",attachmentParams); 
							}else{
								mini.alert('请你选择需要发起的流程数据！！！');
							}						
						}
					}],
		queryButtonColSpan: 2,
		xmlFileName: '/eleasing/jsp/other/thing_follow_open_list.xml',
		//params:{contractstatus:"'31'"},
		//xmlFileName: '/eleasing/workflow/contract/contract_patrol/contract_patrol_list.xml',
		columns:[
			{type:'indexcolumn'},
			{type:'checkcolumn'},
			{field:'id',header:'id',visible:false},
			{field: 'dnum', header: 'dnum', visible: false},
			{field:'custid', header:'custid', visible: false},
		   	{field:'custname',header:'客户名称',allowSort:true,queryConfig:{}},
		   	{field:'custclass',header:'客户性质'},
		   	{field:'custkind',header:'内部行业',
		   		           queryConfig:{
                               colspan:1,
					              type:'combobox', 
					        valueField:'value',
					         textField:'name',
					            params:{xmlFileName:'combos/comboDict.xml',pid:'cust_kind'},
					        allowInput:true, 
					      showNullItem:true}},
		   	{field:'orgcodecardno',header:'身份证号/组织机构代码',queryConfig:{newLine:true}},
		   	{field:'creator',header:'登记人'},
		   	{field:'createdate',header:'登记时间',headerAlign:'center',renderer:"miniextonDateRenderer"}
		]
		/* columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'dnum', header: 'dnum', visible: false},
			{field: 'contract_id', header: '合同编号',queryConfig:{}},
			{field: 'contract_number', header: '业务合同编号',queryConfig:{}},
			{field: 'project_name', header: '项目名称',queryConfig:{}},
			{field: 'cust_name', header: '客户名称',queryConfig:{newLine: true}},
			{field: 'card_no', header: '身份证/组织机构代码',queryConfig:{}},
			{field: 'industry_type', header: '内部行业'},
			{field: 'projmanagename', header: '项目经理'},
			{field: 'department', header: '出单部门'},
			{field: 'contractstatus', header: '合同状态'}
		] */
	});
});
</script>
</head>
<body>
<div id="id_table_container_onhire_contract_change"></div>
</body>
</html>