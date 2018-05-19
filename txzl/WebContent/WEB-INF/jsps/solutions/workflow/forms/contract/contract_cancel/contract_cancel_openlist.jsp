<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>合同撤销</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
jQuery(function(){
	tenwa.createTable({
		id: "contract_cancel",
		renderTo: "id_table_container_contract_cancel",
		width: '100%',
		height: '100%',
		lazyLoad: false,
		title: "合同撤销",
		remoteOper : false,
		showPager: true,
		pageSize: 20,
		tools: [{
			html: '签约撤销',
			plain: true,
			iconCls: 'icon-addfolder',
			handler: function(miniTable, buttonText){
				var row = miniTable.getSelected();
				if(row){
					var attachmentParams = "contract_id="+row.id;
					startProcess("签约撤销流程-1",attachmentParams);
				}else{
					mini.alert("请选中要操作的数据！");
				}
			}
		}],
		queryButtonColSpan: 6,
		queryButtonNewLine:true,
		xmlFileName: '/eleasing/workflow/contract/contract_cancel/contract_cancel_list.xml',
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			 {field:'projid', header:'项目编号'},	
			{field: 'cust_name', header: '客户名称',width:135,queryConfig:{width: 165,newLine: false}},
			{field: 'project_name', header: '项目名称',queryConfig:{width: 165}},
			{field: 'contract_number', header: '业务合同编号',width:110,queryConfig:{width:165,newLine: false}},			
			{field: 'contract_id', header: '合同编号',width:110},
			{field:'cleanleasemoney',header:'合同金额'},
			{field: 'industry_type', header: '内部行业'},
			{field: 'projmanagename', header: '项目经理'},
			{field: 'contractstatus', header: '项目状态'},
			{field: 'card_no', header: '身份证/注册号/统一社会信用代码',width:175},			
			{field: 'department', header: '出单部门'}
			
		]
	});
});
</script>
</head>
<body>
<div id="id_table_container_contract_cancel" style="width: 100%;height: 100%;"></div>
</body>
</html>