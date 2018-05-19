<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>风电项目核准计划</title>
<%@include file="/base/mini.jsp"%>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
	function importMiniTableCallBack(message, tableid) {
		var info = eval("(" + message + ")");
		mini.alert(info.message);
		mini.get("id_minitableimport").hide();
		mini.unmask(document.body);
		mini.get(tableid).reload();
		
		var tabledate = info.tabledate;
		//console.info(info);
		if ("" != tabledate) {
			var grid = mini.get(tableid);
			grid.set({
				data : mini.decode(tabledate)
			});
		}		
	}
	jQuery(function() {
		seajs
				.use(
						[ "js/apcomponent/aptable/aptable.js" ],
						function(ApTable) {
							new ApTable(
									{
										id : 'table_wind_project_approval_info_id',
										renderTo : "id_table_render_table1",
										width : globalClientWidth,
										height : globalClientHeight,
										iconCls : 'icon-node',
										hiddenQueryArea : false,
										multiSelect : true,
										importTargetClass : 'com.tenwa.leasing.entity.windprojectapproval.WindProjectApproval',
										importDataIndex : '2',
										importHeaderIndex : '1',
										isClickLoad : false,
										editFormPopupWindowWidth : 800,
										editFormPopupWindowHeight : 300,
										title : '风电项目核准计划',
										remoteOper : true,
										pageSize : 15,
										showPager : true,
										lazyLoad : false,
										loadMode : 'ajax',
										queryButtonColSpan : 6,
										queryButtonNewLine : true,
										xmlFileName : '/eleasing/jsp/wind_project_approval/wind_project_approval_list.xml',
										tools : [ 'add', '-', 'edit', '-','remove', '-', 'importExcel','-', 'exportExcel' ],
										entityClassName : 'com.tenwa.leasing.entity.windprojectapproval.WindProjectApproval',
									    frozenStartColumn : 0,
										frozenEndColumn : 2, 
										columns : [ {
											type : 'indexcolumn'
										}, {
											type : 'checkcolumn'
										}, {
											field : 'id',
											header : '编号',
											headerAlign : 'center',
											width : 100,
											allowSort : true,
											visible : false,
											formEditorConfig : {
												/* readOnly : true, */
												fieldVisible : false
											}
										}, {
											field : 'proname',
											header : '项目名称',
											visible : true,
											width:300,
											queryConfig : {width:200},
											formEditorConfig : {
												required : true,
												fieldVisible : true
											}
										}, {
											field : 'scale',
											header : '规模（万千瓦）',
											visible : true,
											summary : true,
											formEditorConfig : {
												required : true,
												fieldVisible : true
											}
										}, {
											field : 'prounit',
											header : '项目单位',
											width:300,
											queryConfig:{width:200},
											visible : true,
											formEditorConfig : {
												newLine : true,
												required : true,
												fieldVisible : true
											}
										}, {
											field : 'provinces',
											header : '所在省市',
											queryConfig:{width:200,newLine:false},
											visible : true,
											formEditorConfig : {
												required : true,
												fieldVisible : true
											}
										}, {
											field : 'proadd',
											header : '项目地址',
											queryConfig:{width:200,newLine:true},
											visible : true,
											formEditorConfig : {
												newLine : true,
												required : true,
												fieldVisible : true
											}
										},
										{
											field : 'planappdate',
											header : '计划核准时间',
											headerAlign : 'center',
											visible : true,
											width : 100,
											//dateFormat : "yyyy-MM-dd",
											formEditorConfig : {
												type : 'text',
												labelWidth : 100,
												width : '100%',
												//format : 'yyyy-MM-dd',
												required : true
											}
										}, {
											field : 'planproddate',
											header : '计划投产时间',
											headerAlign : 'center',
											visible : true,
											width : 100,
										//	dateFormat : "yyyy-MM-dd",
											formEditorConfig : {
												newLine : true,
												type : 'text',
												labelWidth : 100,
												width : '100%',
											//	format : 'yyyy-MM-dd',
												required : false
											}
										},
										{
											field : 'approvalbatch',
											header : '核准批次',
											visible : true,
											queryConfig:{width:200,newLine:false},
											formEditorConfig : {
												required : true,
												fieldVisible : true
											}
										},
										{
											field : 'note',
											header : '备注',
											headerAlign : 'center',
											queryConfig : {width:200},
											visible : true,
											width : 100,
											formEditorConfig : {
												newLine : true,
												colspan : 3,
												width : "100%",
												type : 'textarea'
											}
										} ]
									});
						});
	});
</script>

</head>
<body>
	
		<div id="id_table_render_table1"></div>

</body>
</html>
