<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>保函管理</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
	jQuery(function() {
		seajs
				.use(
						[ "js/apcomponent/aptable/aptable.js" ],
						function(ApTable) {
							new ApTable(
									{
										id : 'table_guarantee_info_id',
										renderTo : "id_table_render_table1",
										width : globalClientWidth,
										height : globalClientHeight,
										iconCls : 'icon-node',
										hiddenQueryArea : false,
										multiSelect : true,
										queryButtonColSpan : 4,
										queryButtonNewLine : false,
										editFormPopupWindowWidth : 800,
										editFormPopupWindowHeight : 450,
										title : '保函管理',
										remoteOper : true,
										pageSize : 15,
										showPager : true,
										lazyLoad : false,
										loadMode : 'ajax',
										queryButtonColSpan : 6,
										queryButtonNewLine : true,
										entityClassName : 'com.tenwa.leasing.entity.guaranteemanagement.GuaranteeInfo',
										frozenStartColumn : 0,
										frozenEndColumn : 2,
										xmlFileName : '/eleasing/jsp/guarantee_manage/guarantee_manage_info_list.xml',
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
														//onSelect : function(
														//		$me,
														//		queryInput,
														//		rowData) {
														//},
														params : {
															xmlFileName : '/eleasing/jsp/guarantee_manage/guarantee_manage_proj.xml'
														}
													}
												},
												{
													field : 'guaranteeid',
													header : '保函编号',
													visible : true,
													formEditorConfig : {
														fieldVisible : true
													}
												},
												/*
												{           
													field : 'guaranteetype',
													header : '保函类型',
													headerAlign : 'center',
													visible : true,
													width : 150,
													formEditorConfig : {
														type : 'text',
														newLine : true,
														labelWidth : 100,
														width : 200,
														required : false
													},
													queryConfig : {}
												},
												 */
												{
													field : 'guaranteetype',
													header : '保函类型',
													headerAlign : 'center',
													visible : true,
													width : 100,
													queryConfig : {},
													formEditorConfig : {
														newLine : true,
														labelWidth : 100,
														width : 200,
														required : false,
														type : 'text'
													}
												},
												{
													field : 'money',
													header : '金额',
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
													field : 'openbank',
													header : '开户银行',
													headerAlign : 'center',
													visible : true,
													width : 100,
													queryConfig : {},
													formEditorConfig : {
														labelWidth : 100,
														newLine : true,
														width : 200,
														//	textField: 'text',
														required : false,
														//    valueField: 'id',                           
														type : 'text'
													}
												},

												{
													field : 'relatedequipment',
													header : '相关设备',
													headerAlign : 'center',
													visible : true,
													width : 100,
													formEditorConfig : {
														newLine : false,
														type : 'text',
														labelWidth : 100,
														width : '100%',
														required : false
													}
												},

												{
													field : 'effectivedate',
													header : '生效日期',
													headerAlign : 'center',
													visible : true,
													width : 100,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														newLine : true,
														type : 'date',
														labelWidth : 100,
														width : '100%',
														format : 'yyyy-MM-dd',
														required : false
													},
													queryConfig : {
														type : 'date',
														format : 'yyyy-MM-dd',
														newLine : true
													}
												},
												{
													field : 'expirydate',
													header : '失效日期',
													headerAlign : 'center',
													visible : true,
													width : 100,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														newLine : false,
														type : 'date',
														labelWidth : 100,
														width : '100%',
														format : 'yyyy-MM-dd',
														required : false
													},
													queryConfig : {
														type : 'date',
														format : 'yyyy-MM-dd',
														newLine : false
													}
												},

												{
													field : 'guaranteestate',
													header : '保函状态',
													visible : true,
													formEditorConfig : {
														fieldVisible : false,
														fillFromFieldName : 'guaranteestateid',
														fillProperty : 'name'
													}
												},
												{
													field : 'guaranteestateid',
													header : '保函状态',
													visible : false,
													formEditorConfig : {
														newLine : true,
														type : 'combobox',
														textField : 'name',
														required : true,
														valueField : 'value',
														fieldVisible : true,
														params : {
															pid : 'guaranteestate',
															xmlFileName : '/combos/comboDict.xml'
														}
													}
												},
												{
													field : 'applyrefund',
													header : '是否申请退还',
													headerAlign : 'center',
													visible : true,
													width : 100,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														newLine : false,
														required : true,
														labelWidth : 100,
														width : 200,
														type : 'combobox',//表单域类型
														valueField : 'value',
														textField : 'name',
														allowInput : false,
														showNullItem : false,
														defaultValue : '是',
														data : [ {
															name : "是",
															value : "是"
														}, {
															name : "否",
															value : "否"
														} ],

													}
												},

												{
													field : 'guaranteename',
													header : '保函名称',
													visible : true,
													queryConfig : {
														newLine : false
													},
													formEditorConfig : {
														fieldVisible : false,
														fillFromFieldName : 'guaranteenameid',

														fillProperty : 'name'

													}
												},
												{
													field : 'guaranteenameid',
													header : '保函名称',
													visible : false,
													formEditorConfig : {
														newLine : true,
														type : 'combobox',
														textField : 'name',
														required : false,
														showNullItem : "true",
														nullItemText : "",
														valueField : 'value',
														fieldVisible : true,
														params : {
															pid : 'guaranteename',
															xmlFileName : '/combos/comboDict.xml'
														}
													}
												},
												{
													field : 'suppliername',
													header : '供应商名称',
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
												},
												{
													field : 'relevantcontract',
													header : '相关合同',
													headerAlign : 'center',
													visible : true,
													width : 100,
													formEditorConfig : {
														newLine : true,
														type : 'text',
														required : false,
														labelWidth : 100,
														width : 200
													}
												},
												{
													field : 'page',
													header : '页数',
													headerAlign : 'center',
													visible : true,
													width : 100,
													formEditorConfig : {
														type : 'text',
														labelWidth : 100,
														width : 200,
														readonly : false
													}
												},
												{
													field : 'guaranteeposition',
													header : '保函位置',
													headerAlign : 'center',
													visible : true,
													width : 100,
													formEditorConfig : {
														newLine : true,
														required : false,
														type : 'text',
														labelWidth : 100,
														width : 200
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
