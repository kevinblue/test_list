<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>集团存款</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
	function importMiniTableCallBack(message, tableid) {
		var info = eval("(" + message + ")");
		alert(info.message);
		var tabledate = info.tabledate;
		if ("" != tabledate) {
			var grid = mini.get(tableid);
			grid.set({
				data : mini.decode(tabledate)
			});
			//console.info(mini.decode(tabledate));
		}
		//mini.getbyName("balance").setValue("123");
		mini.get("id_minitableimport").hide();
		mini.get("table_capital_plan_id").reload();
		mini.unmask(document.body);
	}

	jQuery(function() {
		seajs
				.use(
						[ "js/apcomponent/aptable/aptable.js" ],
						function(ApTable) {
							new ApTable(
									{
										id : 'table_capital_plan_id',
										renderTo : "id_table_render_table1",
										width : globalClientWidth,
										height : globalClientHeight,
										iconCls : 'icon-node',
										hiddenQueryArea : false,
										multiSelect : true,
										importTargetClass : 'com.tenwa.leasing.entity.finacial.CapitalPlan',
										importDataIndex : '2',
										importHeaderIndex : '1',
										editFormPopupWindowWidth : 800,
										editFormPopupWindowHeight : 250,
										title : '集团存款',
										remoteOper : true,
										pageSize : 10,
										showPager : true,
										lazyLoad : false,
										loadMode : 'ajax',
										queryButtonColSpan : 1,
										queryButtonNewLine : false,
										entityClassName : 'com.tenwa.leasing.entity.finacial.CapitalPlan',
										frozenStartColumn : 0,
										frozenEndColumn : 2,
										xmlFileName : '/eleasing/jsp/finacial/capital_plan/capital_deposit_list.xml',
										tools : [ 'add', '-','edit', '-',
												'remove', '-', 'exportExcel' ],
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
												readonly : true,
												fieldVisible : false
											}
										}, {
											field : 'serialnum',
											header : '序号',
											visible : false,
											formEditorConfig : {
												fieldVisible : false
											}
										}, {
											field : 'amount',
											header : '金额（元）',
											visible : true,
											dataType:"currency",
											formEditorConfig : {
												width : "100%",
												vtype:'float',
												fieldVisible : true
											}
										}, {
											field : 'depstirdate',
											header : '上存/下拨日期',
											headerAlign : 'center',
											visible : true,
											width : 100,
											dateFormat : "yyyy-MM-dd",
											formEditorConfig : {
												labelWidth : 100,
												type : 'date',
												width : '100%',
												format : 'yyyy-MM-dd',
												required : false
											},
											queryConfig : {
												type : 'date',
												range : true,
												format : 'yyyy-MM-dd',
												newLine : false
											}
										},

										{
											field : 'type',
											header : '类型',
											headerAlign : 'center',
											visible : true,
											width : 100,
											formEditorConfig : {
												newLine : true,
												labelWidth : 100,
												width : 200,
												required : false,
												type : 'combobox',
												valueField : 'value',
												textField : 'name',
												allowInput : false,
												showNullItem : true,
												defaultValue : '',
												data : [ {
													name : "上存",
													value : "上存"
												}, {
													name : "下拨",
													value : "下拨"
												} ]
											}
										}, {
											field : 'interestrates',
											header : '存款利率(%)',
											headerAlign : 'center',
											visible : true,
											width : 100,
											formEditorConfig : {
												labelWidth : 100,
												width : 200,
												vtype:'float',
												required : false,
												type : 'text'
											}
										},

										{
											field : 'balance',
											header : '存款余额',
											headerAlign : 'center',
											visible : true,
											width : 100,
											dataType:"currency",
											queryConfig : {},
											formEditorConfig : {
												labelWidth : 100,
												newLine : true,
												width : 200,
												vtype:'float',
												//	textField: 'text',
												required : false,
												//    valueField: 'id',                           
												type : 'text'
											}
										},
										{
											field : 'interest',
											header : '存款利息',
											headerAlign : 'center',
											visible : true,
											width : 100,
											dataType:"currency",
											formEditorConfig : {
												labelWidth : 100,
												readOnly : true,
												vtype:'float',
												width : 200,
												//	textField: 'text',
												required : false,
												//    valueField: 'id',                           
												type : 'text'
											}
										},

										{
											field : 'note',
											header : '备注',
											headerAlign : 'center',
											visible : true,
											width : 100,
											formEditorConfig : {
												newLine : true,
												colspan : 3,
												width : "100%",
												type : 'textarea'
											}
										}

										]
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