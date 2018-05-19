<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>不动产抵押登记</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript"
	·
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
	jQuery(function() {
		seajs
				.use(
						[ "js/apcomponent/aptable/aptable.js" ],
						function(ApTable) {
							new ApTable(
									{
										id : 'real_estate_mortgage_info_id',
										renderTo : "id_table_render_table1",
										width : globalClientWidth,
										height : globalClientHeight,
										iconCls : 'icon-node',
										hiddenQueryArea : false,
										multiSelect : true,

										editFormPopupWindowWidth : 800,
										editFormPopupWindowHeight : 300,
										title : '不动产抵押登记',
										remoteOper : true,
										pageSize : 15,
										showPager : true,
										lazyLoad : false,
										loadMode : 'ajax',

										entityClassName : 'com.tenwa.leasing.entity.realestatemortgage.RealEstateMortgage',
										frozenStartColumn : 0,
										frozenEndColumn : 2,
										xmlFileName : '/eleasing/jsp/real_estate_mortgage/real_estate_mortgage_info_list.xml',
										tools : [ 'add', '-', 'edit', '-',
												'remove', '-', 'exportExcel' ],
										columns : [
												{
													type : 'indexcolumn'
												},
												{
													type : 'checkcolumn'
												},
												{
													field : 'id',
													header : '编号',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : false,
													formEditorConfig : {
														readOnly : true,
														fieldVisible : false
													}
												},
												{
													field : 'projectname',
													header : '项目名称',
													visible : true,
													width : 200,
													queryConfig : {},
													formEditorConfig : {
														fieldVisible : false,
														fillFromFieldName : 'projnameid'
													}
												},

												{
													field : 'projnameid',
													header : '项目名称',
													visible : false,
													formEditorConfig : {
														newLine : true,
														width : 200,
														type : 'queryinput',
														required : true,
														textField : 'name',
														valueField : 'value',
														allowInput : false,
														fieldVisible : true,
														params : {
															xmlFileName : '/eleasing/jsp/real_estate_mortgage/real_estate_mortgage_proj.xml'
														}
													}
												},
												{
													field : 'mortgagor',
													header : '抵押人名称',
													visible : true,
													queryConfig : {},
													formEditorConfig : {
														fieldVisible : true
													}
												},
												{
													field : 'registercompany',
													header : '登记单位',
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
												},
												{
													field : 'registernumber',
													header : '登记编号',
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
												},
												{
													field : 'typename',
													header : '种类',
													visible : true,
													formEditorConfig : {
														fieldVisible : false,
														fillFromFieldName : 'typeid',
														fillProperty : 'name'
													}
												},
												{
													field : 'typeid',
													header : '种类',
													visible : false,
													formEditorConfig : {
														newLine : true,
														type : 'combobox',
														textField : 'name',
														required : true,
														valueField : 'value',
														fieldVisible : true,
														params : {

															pid : 'chattel_mortgage_type',
															xmlFileName : '/combos/comboDict.xml'
														}
													}
												}, {
													field : 'amount',
													header : '数额',
													headerAlign : 'center',
													visible : true,
													width : 100,
													formEditorConfig : {
														newLine : false,
														type : 'text',
														required : false,
														labelWidth : 100,
														width : 200,
														allowInput : 'true'
													}
												}, {
													field : 'mortgagebegin',
													header : '抵押登记期限（起）',
													headerAlign : 'center',
													visible : true,
													width : 150,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														newLine : true,
														type : 'date',
														labelWidth : 100,
														width : '100%',
														format : 'yyyy-MM-dd',
														required : false
													}
												}, {
													field : 'mortgageend',
													header : '抵押登记期限（止）',
													headerAlign : 'center',
													visible : true,
													width : 150,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														newLine : false,
														type : 'date',
														labelWidth : 100,
														width : '100%',
														format : 'yyyy-MM-dd',
														required : false
													}
												}, {
													field : 'debtbegin',
													header : '债务履行期限（起）',
													headerAlign : 'center',
													visible : true,
													width : 150,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														newLine : true,
														type : 'date',
														labelWidth : 100,
														width : '100%',
														format : 'yyyy-MM-dd',
														required : false
													}
												}, {
													field : 'debtend',
													header : '债务履行期限（止）',
													headerAlign : 'center',
													visible : true,
													width : 150,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														newLine : false,
														type : 'date',
														labelWidth : 100,
														width : '100%',
														format : 'yyyy-MM-dd',
														required : false
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
