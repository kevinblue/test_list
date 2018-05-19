<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>合同审批</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
jQuery(function(){
	tenwa.createTable({
		id:"contract_approval",
		renderTo:"id_table_container_contract_approval",
		width:'100%',
		height:'100%',
		iconCls :'icon-node',
		lazyLoad:false,
		title:"合同审批",
		remoteOper :false,
		showPager:true,
		pageSize:20,
		tools:[{
			html:'合同审批',
			plain:true,
			iconCls:'icon-addfolder',
			handler:function(miniTable, buttonText){
				var row = miniTable.getSelected();
				if(row){
					var attachmentParams = "proj_id="+row.id;
					startProcess("合同审批流程-1",attachmentParams); 
				}else{
					mini.alert("请选中要操作的数据！");
				}
			}
		  }],
		queryButtonColSpan:2,
		xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_approval_list.xml',
		columns:[
			{type:'indexcolumn'},
			{type:'checkcolumn'},
			{field:'id', header:'id', visible:false},
			{field:'proj_id', header:'项目编号',queryConfig:{width:160}},
			{field:'project_name', header:'项目名称',queryConfig:{width:160}},
			{field:'cust_name', header:'客户名称',queryConfig:{width:160}},
			{field:'card_no', header:'身份证/组织机构代码'},
			{field:'industry_type', header:'内部行业',
			    queryConfig:{
				      width:160,
				    newLine:true,
				    colspan:1,
				       type:'combobox',
				 valueField:'value',
				  textField:'name',
	   			     params:{xmlFileName:'combos/comboDict.xml',pid:'cust_kind'},
			   showNullItem:true}},
			{field:'projmanagename', header:'项目经理',queryConfig:{width:160}},
			{field:'department', header:'出单部门'},
			{field:'proj_status', header:'项目状态'}
		]
	});
});
</script>
</head>
<body>
<div id="id_table_container_contract_approval" style="height:100%;"></div>
</body>
</html>