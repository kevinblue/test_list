<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>不良资产报送</title>
<!-- 引入mini.jsp -->
<%@include file="/base/mini.jsp"%>
<!-- 引入js资源文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
<!-- 获取整个页面的宽高 -->
var currentClientWidth = document.documentElement.clientWidth;
var currentClientHeight = document.documentElement.clientHeight;
var tools = [];
<!-- 向数组中加入数据 -->
tools.push({
	html:'不良资产报送流程',
	plain:false,
	<!-- 显示在表格外层penal的title的图标 -->
	iconCls:'icon-addfolder',
	handler:function(miniTable, buttonText){
		var row = miniTable.getSelected();
		if(row){
			var attachmentParams = "contract_id="+row.id;
			startProcess("不良资产报送-1",attachmentParams); 
		}else{
			mini.alert("请选中要操作的数据！");
		}
	}
});

<!-- 通过jquery创建表，JSON格式 -->
jQuery(function(){
	tenwa.createTable({
		id:"contract_rentchange_irregular",
		<!-- 读取的表数据放置何处-->
		renderTo:"id_table_container_contract_rentchange_irregular",
		width:currentClientWidth,
		height:currentClientHeight,
		lazyLoad:false,
		title:"不良资产报送",
		<!--lazyLoad=true时需要调用miniTable.reload()或者miniTable.load()方法才能显示数据，只对xml数据有效-->
		remoteOper :false,
		<!--查询区的按钮占的列数-->
		queryButtonColSpan :6,
		queryButtonNewLine:true,
		showPager:true,
		<!--配置每页默认显示数据条数，默认20条-->
		pageSize:20,
		<!--定义一些自定义按钮盒封装好的按钮-->
		tools:tools,
		<!--表格数据来源，配置xml路径,刚进入页面的数据-->
		xmlFileName:'eleasing/workflow/contract/badassets_submitted/badassets_submitted_openlist.xml',
		columns:[
			{type:'indexcolumn', header:'序号'},
			{type:'checkcolumn'},
			{field:'id', header:'id', visible:false},
			{field:'contract_id', header:'合同编号'},
			{field:'contract_number', header:'业务合同编号',queryConfig:{}},
			{field:'project_name', header:'项目名称',queryConfig:{}},
			{field:'cust_name', header:'客户名称',queryConfig:{newLine:false}},
			{field:'card_no', header:'身份证/组织机构代码'},
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