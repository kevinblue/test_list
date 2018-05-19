<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>本方信息</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
jQuery(function(){
	tenwa.createTable({
		id: "own_info",
		renderTo: "id_table_container_owninfo",
		width: '100%',
		height: '100%',
		lazyLoad: false,
		entityClassName : 'com.tenwa.leasing.entity.base.OwnInfo',
		title: "本方信息",
		remoteOper : true,
		showPager: true,
		pageSize: 20,
		editFormPopupWindowWidth:500,
		tools: ['add','-','edit','-','remove'],
		xmlFileName: '/eleasing/jsp/sysmgr/sysdatamgr/owninfo.xml',
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'ownname', header: '出租人',formEditorConfig:{lableWidth:20}},
			{field: 'ownnumber', header: '出租人编号',formEditorConfig:{lableWidth:20}},
			{field: 'leaseconsigner', header: '委托代理人',formEditorConfig:{newLine:true}},
			{field: 'leaseregisteraddr', header: '注册地址'},
			{field: 'leaseaddr', header: '通讯地址',formEditorConfig:{newLine:true}},
			{field: 'leaseperson', header: '总经理'},
			{field: 'leasepostcode', header: '邮编',formEditorConfig:{newLine:true}},
			{field: 'leaselinkman', header: '联系人'},
			{field: 'leasetel', header: '电话',formEditorConfig:{newLine:true}},
			{field: 'leasefax', header: '传真'},
			{field: 'leaseemail', header: '电子邮件',formEditorConfig:{newLine:true,colspan:3}}
		]
	});
});
</script>
</head>
<body>
<div id="id_table_container_owninfo" style="height: 100%;"></div>
</body>
</html>