<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>项目建设进度</title>
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
		id: "projProgress",
		renderTo: "id_projProgress",
		width: currentClientWidth,
		height: currentClientHeight,
		lazyLoad: false,
		title: "",
		remoteOper : true,
		showPager: true,
		multiSelect : true,
		pageSize: 20,
		entityClassName : 'com.tenwa.leasing.entity.proj.ProjectProgress',
		params:{projid:projid},
		tools: ['add','-','edit','-','remove', '-', 'exportExcel','-','importExcel'],
		queryButtonColSpan: 4,
		queryButtonNewLine:false,
		importTargetClass :'com.tenwa.leasing.entity.proj.ProjectProgress',
		importDataIndex : '2',
		importHeaderIndex : '1',
		importprojDevelop :projid,
		importOrExPortCallBack:'SetDevelopProgress',
		xmlFileName: '/eleasing/workflow/proj/proj_progress/project_progress.xml',
		columns: [
			
			{type : 'indexcolumn'},
			{type : 'checkcolumn'},
			{
				field : 'id',
				header : '编号',
				headerAlign : 'center',
				visible : false,
				formEditorConfig : {
					readOnly : true,
					fieldVisible : false
				}
			},
			{field : 'projid',
				header : '项目ID',
				headerAlign : 'center',
				visible : false,
				formEditorConfig : {
					readOnly : true,
					fieldVisible : false,
					value : projid
				}
			},	
			 {field:'',header:'操作',
		  		   formEditorConfig:{
                 fieldVisible:false},
			     renderer:function(e){
				          var id = e.record.id;
			              return "<a href='javascript:void(0);' class='action' onclick='showanduploadfile(\"" + id + "\",\"one\")'>查看/上传资料 </a>";
			               }},
			{field:'reportdate', header:'报告日期',dateFormat:"yyyy-MM-dd",
			            	   formEditorConfig:{
										type:'date',
										format:'yyyy-MM-dd',
										allowInput:false}},
			{field:'accessroad', header:'进场道路（公里）', formEditorConfig : {}},	
			{field:'pitroad', header:'场内道路（公里）', formEditorConfig : {newLine:true}},	
			{field:'civil', header:'升压站土建', formEditorConfig : {}},
			{field:'equipmentinstall', header:'升压站设备安装',formEditorConfig : {newLine:true}},
			{field:'collectpowerlineroad', header:'集电线路基础（基）',formEditorConfig : {}},
			{field:'collectpowerlineroadlay', header:'集电线路敷设（公里）',formEditorConfig : {newLine:true}},
			{field:'emptylineroad', header:'送出线路（公里）', formEditorConfig : {}},	
			{field:'windmachinebasic', header:'风机基础（基）', formEditorConfig : {newLine:true}},
			{field:'windmachinehoisting', header:'风机吊装（套）', formEditorConfig : {}},
			{field:'boxfoundation', header:'箱变基础（基）', formEditorConfig : {newLine:true}},
			{field:'boxchangeinstallation', header:'箱变安装（台）', formEditorConfig : {}},
			{field:'elecequipment', header:'风机到货（套）',formEditorConfig : {newLine:true}},	
			{field:'tower', header:'塔架到货（套）',formEditorConfig : {}}
		]
	});
});

</script>
</head>
<body>
<div id="id_projProgress"></div>
</body>
</html>