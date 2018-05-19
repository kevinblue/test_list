<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>工商局特殊要求</title>
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
										id : 'table_special_request_list',
										renderTo : "id_table_special_request_list",
										width : globalClientWidth,
										height : globalClientHeight,
										iconCls : 'icon-node',
										pageSize : 20,
										showPager : true,
										queryButtonColSpan : 6,
										hiddenQueryArea : false,
										multiSelect : true,
										editFormPopupWindowWidth : 800,
										editFormPopupWindowHeight : 280,
										title : '工商局特殊要求',
										remoteOper : true,
										showPager : true,
										lazyLoad : false,
										loadMode : 'ajax',
										frozenStartColumn : 0,
										frozenEndColumn : 2,
										entityClassName : 'com.tenwa.leasing.entity.industrialandcommercialbureau.SpecialRequest',
										xmlFileName : '/eleasing/jsp/industrial_and_commercial_bureau/special_request_list.xml',
										tools : [ 'add', '-', 'edit', '-','remove'],
										columns : [
												{
													type : 'indexcolumn',
													headerAlign : 'center',
													header : '序号',
													width:10
												},
												{
													type : 'checkcolumn',
													headerAlign : 'center',
													width:10
												},
												{
													field : 'id',
													header : '编号',
													headerAlign : 'center',
													width : 20,
													allowSort : true,
													visible : false,
													formEditorConfig : {
														width:200,
														readOnly : true,
														fieldVisible : false
													}
												},
												{
													field : 'province',
													header : '省份',
													headerAlign : 'center',
													width:20,
													visible : true,
													allowSort:'true',
													queryConfig:{
														 width : 180
								                   	},
													formEditorConfig : {
														width:200,
													    labelWidth:100,
														type : 'combobox',
														valueField : 'value',
														textField : 'value',
														required : true,
														params : {
															xmlFileName : 'eleasing/jsp/base/t_district.xml',
															PID : 'CHN'
														},
													}
												},
												{
													field : 'city',
													header : '市、区（县）',
													headerAlign : 'center',
													width:50,
													visible : true,
													allowSort:'true',
													queryConfig:{
														 width : 180
								                   	},
								                   	formEditorConfig : {
														required : true,
														}
												},
												{
													field : 'specialrequest',
													header : '特殊要求',
													headerAlign : 'center',
													visible : true,
													allowSort:'true',
													queryConfig:{
														 width : 180
								                   	},
													formEditorConfig : {
														type:'textarea',
														colspan: 4,							
											            width:'100%',
														height:60,
														newLine : true,
														allowInput : true,
														required : true,
														fieldVisible : true
													}
												},
												{
													field : 'realname',
													header : '登记人',
													headerAlign : 'center',
													width:20,
													visible : true,
													allowSort:'true',
													queryConfig:{
														newLine : true,
														 width : 180
								                   	},
													formEditorConfig : {
														type : 'text',
														newLine : true,
														width:200,
														readonly:true,
														value:"${sessionScope.loginUser.realname}",
														required : true,
														fieldVisible : true
													}
												},
												{
													field : 'createdate',
													header : '登记日期',
													dateFormat:'yyyy-MM-dd HH:mm:ss',
													width:30,
													headerAlign : 'center',
													visible : true,
													allowSort:'true',
													queryConfig : {
														width:100,
														type : 'date',
														range : true,
														format : 'yyyy-MM-dd',
														newLine : false
													},
													formEditorConfig : {
														type : 'date',
														width:200,
														readonly:true,
														defaultValue:mini.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"),
														format:'yyyy-MM-dd HH:mm:ss',
														required : true,
														fieldVisible : true
													}
												}]
									});
						});
	});
</script>
</head>
<body>
	<div id="mini_test_form">
		<div id="id_table_special_request_list"></div>
	</div>
</body>
</html>
