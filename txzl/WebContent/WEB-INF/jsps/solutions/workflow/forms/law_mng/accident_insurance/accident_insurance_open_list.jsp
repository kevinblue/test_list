<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>出险流程</title>
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
		title: "出险流程",
		remoteOper : false,
		showPager: true,
		pageSize: 20,
		tools: [{html:'发起出险流程',
					 plain:true,
					 iconCls:'icon-addfolder',
					 handler:function(miniTable, buttonText) {
							var row = miniTable.getSelected();
							if(row){
								var attachmentParams = "contract_id="+row.id+"&custid="+row.custid;
				        		startProcess("出险流程-1",attachmentParams); 
							}else{
								mini.alert('请你选择需要发起的流程数据！！！');
							}						
						}
					}],
		queryButtonColSpan: 2,
		xmlFileName: '/eleasing/workflow/contract/contract_insurance/contract_insurance.xml',
	//	params:{contractstatus:"'31'"},
		columns:[
					{type:'indexcolumn'},
					{type:'checkcolumn'},
					{field:'id',header:'id',visible:false},
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
	});
});
</script>
</head>
<body>
<div id="id_table_container_onhire_contract_change"></div>
</body>
</html>