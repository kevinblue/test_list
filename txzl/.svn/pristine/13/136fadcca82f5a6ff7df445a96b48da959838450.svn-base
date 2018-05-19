<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>项目主数据</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<script type="text/javascript">
var currentClientWidth = document.documentElement.clientWidth;
var currentClientHeight = document.documentElement.clientHeight;
var loadMask = null;
jQuery(function(){
	tenwa.createTable({
		id: "pickup_wind_details",
		renderTo: "id_pickup_wind_details",
		width: currentClientWidth,
		height: currentClientHeight,
		lazyLoad: false,
		title: "接货主数据",
		
		showPager: true,
		loadMode : 'ajax',
		pageSize: 20,
		params:{projid:projid},
		entityClassName : 'com.tenwa.leasing.entity.SM.PickUpMainData',
		remoteOper : true,
		tools: ['exportExcel'],
		queryButtonColSpan: 4,
		queryButtonNewLine:false,
		xmlFileName: '/eleasing/workflow/contract/SM_interface/pickup_detail.xml',
		columns: [
			{type: 'indexcolumn'},
			{field: 'id_', header: 'id_', visible: false},
			{field:'id', header:'ID',visible: false},	 
			{field:'sysmodtime', header:'SYSMODTIME',visible: false},	
			{field:'存放机位', header:'存放机位',formEditorConfig : {}},	
			{field:'存放位置', header:'存放位置',formEditorConfig : {}},	
			{field:'物料编码', header:'物料编码',formEditorConfig : {newLine:true}},	
			{field:'物料名称', header:'物料名称',formEditorConfig : {}},	
			{field:'批次号', header:'批次号',formEditorConfig : {newLine:true}},	
			{field:'序列号', header:'序列号',formEditorConfig : {}},	
			{field:'数量', header:'数量',formEditorConfig : {newLine:true}},	
			{field:'单位', header:'单位',formEditorConfig : {}},	
			{field:'接货工单号', header:'接货工单号',visible: false},	
			{field:'片区', header:'片区',formEditorConfig : {newLine:true}},
			{field:'风电场编号', header:'风电场编号',formEditorConfig : {}},
			{field:'接货开始时间', header:'接货开始时间',formEditorConfig : {newLine:true}},
			{field:'接货结束时间', header:'接货结束时间',formEditorConfig : {}}
		]
	});
});

</script>
</head>
<body>
<div id="id_pickup_wind_details"></div>
</body>
</html>