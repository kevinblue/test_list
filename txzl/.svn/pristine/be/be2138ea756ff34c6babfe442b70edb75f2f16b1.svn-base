<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>股权出质设立登记</title>
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
										id : 'table_equity_pledge_info_id',
										renderTo : "id_table_render_table1",
										width : globalClientWidth,
										height : globalClientHeight,
										iconCls : 'icon-node',
										hiddenQueryArea : false,
										multiSelect : true,

										editFormPopupWindowWidth : 800,
										editFormPopupWindowHeight : 300,
										title : '股权出质设立登记',
										remoteOper : true,
										pageSize : 15,
										showPager : true,
										lazyLoad : false,
										loadMode : 'ajax',

										entityClassName : 'com.tenwa.leasing.entity.equitypledge.EquityPledge',
										frozenStartColumn : 0,
										frozenEndColumn : 2,
										xmlFileName : '/eleasing/jsp/equity_pledge/equity_pledge_info_list.xml',
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
															xmlFileName : '/eleasing/jsp/chattel_mortgage/chattel_mortgage_proj.xml'
														}
													}
												}, {
													field : 'financialnumber',
													header : '融资租赁合同编号',
													width : 150,
													
													visible : true,
													queryConfig : {},
													formEditorConfig : {
														labelWidth : 110,
														width : 200,
														fieldVisible : true
													}
												}, {
													field : 'equitynumber',
													header : '股权质押合同编号',
													headerAlign : 'center',
													visible : true,
													width : 150,

													formEditorConfig : {
														newLine : true,
														labelWidth : 110,
														width : 200,
														required : false,
														type : 'text'
													}
												}, {
													field : 'registertime',
													header : '登记时间',
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
													}
												},

												{
													field : 'pledgeregnum',
													header : '质权登记编号',
													headerAlign : 'center',
													visible : true,
													width : 100,
													formEditorConfig : {
														newLine : true,
														type : 'text',
														required : false,
														labelWidth : 100,
														width : 200,
														allowInput : 'true'
													}
												}, {
													field : 'pledgeequcom',
													header : '出质股权所在公司',
													headerAlign : 'center',
													visible : true,
													width : 200,
													formEditorConfig : {
														newLine : false,
														type : 'text',
														required : false,
														labelWidth : 100,
														width : 200,
														allowInput : 'true'
													}
												}, {
													field : 'pledgor',
													header : '出质人',
													headerAlign : 'center',
													visible : true,
													width : 100,
													formEditorConfig : {
														newLine : true,
														type : 'text',
														required : false,
														labelWidth : 100,
														width : 200,
														allowInput : 'true'
													}
												}, {
													field : 'pledgedequamount',
													header : '出质股权数额',
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
													field : 'pledgetermnote',
													header : '质押期限/备注',
													headerAlign : 'center',
													visible : true,
													width : 100,
													formEditorConfig : {
														newLine : true,
														type:'textarea',						
														required : false,
														 width:"100%",
														colspan: 3,	
														allowInput : 'true'
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
