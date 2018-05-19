<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>应收账款质押</title>
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
										id : 'table_accountrecple_info_id',
										renderTo : "id_table_render_table1",
										width : globalClientWidth,
										height : globalClientHeight,
										iconCls : 'icon-node',
										hiddenQueryArea : false,
										multiSelect : true,
										
										editFormPopupWindowWidth : 800,
										editFormPopupWindowHeight : 450,
										title : '应收账款质押',
										remoteOper : true,
										pageSize : 15,
										showPager : true,
										lazyLoad : false,
										loadMode : 'ajax',
										
										entityClassName : 'com.tenwa.leasing.entity.accountrecple.AccountRecPle',
										frozenStartColumn : 0,
										frozenEndColumn : 2,
										xmlFileName : '/eleasing/jsp/accountrecple/accountrecple_info_list.xml',
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
															xmlFileName : '/eleasing/jsp/guarantee_manage/guarantee_manage_proj.xml'
														}
													}
												},
												{
													field : 'financialconnum',
													header : '融资租赁合同编号',
													headerAlign : 'center',
													visible : true,
													width : 150,
													formEditorConfig : {
														fieldVisible : true
													}
												},

												{
													field : 'accountsrecnum',
													header : '应收账款质押登记合同编号',
													headerAlign : 'center',
													visible : true,
													width : 150,
													queryConfig : {},
													formEditorConfig : {
														newLine : true,
														labelWidth : 110,
														width : 200,
														required : false,
														type : 'text'
													}
												},
												{
													field : 'preparernum',
													header : '填表人归档号',
													headerAlign : 'center',
													visible : true,
													width : 100,
													formEditorConfig : {
														newLine : false,
														labelWidth : 110,
														width : 200,
														required : false,
														type : 'text'
													}
												},

												{
													field : 'amount',
													header : '合同金额/租金总额',
													headerAlign : 'center',
													visible : true,
													width : 150,
													
													formEditorConfig : {
														labelWidth : 110,
														newLine : true,
														width : 200,
														//	textField: 'text',
														required : false,
														//    valueField: 'id',                           
														type : 'text'
													}
												},

												{
													field : 'propertydes',
													header : '财产描述',
													headerAlign : 'center',
													visible : true,
													width : 100,
													formEditorConfig : {
														newLine : false,
														type : 'text',
														labelWidth : 110,
														width : '100%',
														required : false
													}
												},
												{
													field : 'registypename',
													header : '登记类型',
													visible : true,
													formEditorConfig : {
														fieldVisible : false,
														fillFromFieldName : 'registypeid',
														fillProperty : 'name'
													}
												},
												{
													field : 'registypeid',
													header : '登记类型',
													visible : false,
													formEditorConfig : {
														newLine : true,
														type : 'combobox',
														textField : 'name',
														required : true,
														valueField : 'value',
														fieldVisible : true,
														params : {
															pid : 'regis_type_id',
															xmlFileName : '/combos/comboDict.xml'
														}
													}
												}, {
													field : 'regisnum',
													header : '登记证明编号',
													headerAlign : 'center',
													visible : true,
													width : 100,
													
													formEditorConfig : {
														labelWidth : 100,
														newLine : false,
														width : 200,
														//	textField: 'text',
														required : false,
														//    valueField: 'id',                           
														type : 'text'
													}
												}, {
													field : 'registime',
													header : '登记时间',
													headerAlign : 'center',
													visible : true,
													width : 100,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														newLine : true,
														type : 'date',
														labelWidth : 110,
														width : '100%',
														format : 'yyyy-MM-dd',
														required : false
													}
												}, {
													field : 'preparer',
													header : '填表人',
													headerAlign : 'center',
													visible : true,
													width : 100,
													formEditorConfig : {
														newLine : false,
														type : 'text',
														labelWidth : 110,
														width : '100%',
														required : false
													}
												}, {
													field : 'debtstart',
													header : '债务履行期限（起）',
													headerAlign : 'center',
													visible : true,
													width : 150,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														newLine : true,
														type : 'date',
														labelWidth : 110,
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
														labelWidth : 110,
														width : '100%',
														format : 'yyyy-MM-dd',
														required : false
													}
												}, {
													field : 'registdate',
													header : '登记到期日',
													headerAlign : 'center',
													visible : true,
													width : 100,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														newLine : true,
														type : 'date',
														labelWidth : 110,
														
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
