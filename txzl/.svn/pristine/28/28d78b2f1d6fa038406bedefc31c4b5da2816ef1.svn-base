<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>合格供应商名录</title>
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
		//mini.get("id_minitableimport").reload();
		mini.unmask(document.body);
		mini.get(tableid).reload();
		
		var tabledate = info.tabledate;
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
										id : 'table_qualified_supplier_info_id',
										renderTo : "id_table_render_table1",
										width : globalClientWidth,
										height : globalClientHeight,
										iconCls : 'icon-node',
										hiddenQueryArea : false,
										multiSelect : true,
										importTargetClass : 'com.tenwa.leasing.entity.qualifiedsupplier.QualifiedSupplier',
										importDataIndex : '2',
										importHeaderIndex : '1',
										isClickLoad : false,
										editFormPopupWindowWidth : 800,
										editFormPopupWindowHeight : 300,
										title : '合格供应商名录',
										remoteOper : true,
										pageSize : 15,
										showPager : true,
										lazyLoad : false,
										loadMode : 'ajax',
										queryButtonColSpan : 4,
										queryButtonNewLine : false,
										xmlFileName : '/eleasing/jsp/qualified_supplier/qualified_supplier.xml',
										tools : [ 'add', '-', 'edit', '-',
												'remove', '-', 'importExcel',
												'-' ],
										entityClassName : 'com.tenwa.leasing.entity.qualifiedsupplier.QualifiedSupplier',
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
											width : 50,
											allowSort : true,
											visible : false,
											formEditorConfig : {
												align:'center',
												readOnly : true,
												fieldVisible : false
											}
										},  {
											field : 'manname',
											header : '厂家名称',
											visible : true,
											width : 100,
											align:'left',
											queryConfig : {},
											formEditorConfig : {
												required : true,
												fieldVisible : true
											}
										}, {
											field : 'proname',
											header : '产品名称',
											visible : true,
											width : 80,
											align:'center',
											queryConfig : {},
											formEditorConfig : {
												required : false,											
												fieldVisible : true
											}
										},{
											field : 'ranking',
											header : '排名',
											visible : true,
											width : 50,
											align:'center',
											formEditorConfig : {
												required : false,
												newLine: true,
												fieldVisible : true
											}
										}, {
											field : 'classification',
											header : '供应商评级',
											visible : true,
											width : 80,
											align:'center',
											queryConfig : {},
											formEditorConfig : {
												required : false,
												fieldVisible : true
											}
										}, {
											field : 'totalscore',
											header : '总分',
											width : 50,
											align:'center',
											visible : true,
											formEditorConfig : {
												required : false,
												newLine: true,
												fieldVisible : true
											}
										}, {
											field : 'ratingtime',
											header : '评级时间',
											visible : true,
											width : 80,
											align:'center',
											dateFormat : "yyyy-MM-dd",
											formEditorConfig : {
												type : 'date',
												required : false,
												fieldVisible : true,
												labelWidth : 100,
												width : '100%',
												format : 'yyyy-MM-dd'

											}
										},{
											field : 'area',
											header : '所在省市',
											visible : true,
											width : 80,
											align:'center',
											queryConfig : {
												newLine:true
											},
											formEditorConfig : {
												required : false,
												newLine: true,
												fieldVisible : true
											}
										}, {
											field : 'detailedinfo',
											header : '详细信息地址',
											width : 150,
											visible : true,
											formEditorConfig : {												
												width : "100%",												
												required : false,
												fieldVisible : true
											}
										},  {
											field : 'comunit',
											header : '编制单位',
											align:'center',
											width : 50,
											visible : true,
											formEditorConfig : {
												required : false,
												newLine: true,
												fieldVisible : true
											}
										}, {
											field : 'comperson',
											header : '编制人员',
											visible : true,
											width : 50,
											align:'center',
											formEditorConfig : {
												newLine: false,
												required : false,
												fieldVisible : true
											}
										} ]
									});
						});
	});
</script>
</head>
<body>
	<div id="mini_test_form">
		<div id="id_table_render_table1"></div>
	</div>
</body>
</html>
