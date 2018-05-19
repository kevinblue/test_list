<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>资金预实明细表</title>
<%
	String dateid = request.getParameter("id");
%>
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
	}
	mini.get("id_minitableimport").hide();
	mini.unmask(document.body);
	mini.get(tableid).reload();
}

var dateid ="<%=dateid%>";
	jQuery(function() {
		seajs
				.use(
						[ "js/apcomponent/aptable/aptable.js" ],
						function(ApTable) {
							new ApTable(
									{
										id : 'table_deposit_interest_info_id',
										renderTo : "id_table_render_table1",
										width : globalClientWidth,
										height : globalClientHeight,
										iconCls : 'icon-node',
										hiddenQueryArea : true,
										multiSelect : true,

										editFormPopupWindowWidth : 800,
										editFormPopupWindowHeight : 250,
										title : '资金预实明细表',
										remoteOper : true,
										pageSize : 500,
										showPager : false,
										lazyLoad : false,
										loadMode : 'ajax',
										importTargetClass : 'com.tenwa.leasing.entity.finacial.DepositInterestInfo',
										importDataIndex : '3',
										importHeaderIndex : '2',
										importDateid:dateid,
										importOrExPortCallBack:'SetDateid',
										entityClassName : 'com.tenwa.leasing.entity.finacial.DepositInterestInfo',
										frozenStartColumn : 0,
										frozenEndColumn : 0,

										xmlFileName : '/eleasing/jsp/finacial/deposit_interest/deposit_interest_info_list.xml',
										params : {
											dateid : dateid
										},
										tools : [ 'add', '-', 'edit', '-',
												'remove', '-', 'importExcel',
												'-', 'exportExcel' ],
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

												fieldVisible : false
											}
										},
										{
											field : 'dateid',
											header : '月份',
											visible : false,
											formEditorConfig : {
												width : 200,
												fieldVisible : false,
												value : dateid
											}
										},

										{
											field : 'serialid',
											header : '序号',
											headerAlign : 'center',
											visible : true,
											width : 100,

											formEditorConfig : {
												newLine : true,
												labelWidth : 100,
												width : 200,
												required : false,
												type : 'text'
											}
										}, {
											field : 'item',
											header : '项目',
											headerAlign : 'center',
											visible : true,
											width : 100,

											formEditorConfig : {
												newLine : false,
												labelWidth : 100,
												width : 200,
												required : false,
												type : 'text'
											}
										}, {
											field : 'monthlybudget',
											header : '月度预算',
											headerAlign : 'center',
											visible : true,
											width : 100,

											formEditorConfig : {
												newLine : true,
												labelWidth : 100,
												width : 200,
												required : false,
												type : 'text'
											}
										}, {
											field : 'actualamount',
											header : '实际金额',
											headerAlign : 'center',
											visible : true,
											width : 100,

											formEditorConfig : {
												newLine : false,
												labelWidth : 100,
												width : 200,
												required : false,
												type : 'text'
											}
										}, {
											field : 'variance',
											header : '差异数',
											headerAlign : 'center',
											visible : true,
											width : 100,

											formEditorConfig : {
												newLine : true,
												labelWidth : 100,
												width : 200,
												required : false,
												type : 'text'
											}
										}, {
											field : 'imprate',
											header : '执行率',
											headerAlign : 'center',
											visible : true,
											width : 100,

											formEditorConfig : {
												newLine : false,
												labelWidth : 100,
												width : 200,
												required : false,
												type : 'text'
											}
										}, {
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
