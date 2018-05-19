<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>利息计提表</title>
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
										id : 'table_interest_provision_id',
										renderTo : "id_table_render_table1",
										width : globalClientWidth,
										height : globalClientHeight,
										iconCls : 'icon-node',
										hiddenQueryArea : false,
										multiSelect : true,
										editFormPopupWindowWidth : 800,
										editFormPopupWindowHeight : 200,
										title : '利息计提表',
										//新增弹出窗口调用该方法返回AJAX
										afterShowWindowCallBack : function(
												miniTable, miniForm, operType) {
											if (operType == 'add') {//如果是新增就获取流水号
												$
														.ajax({
															url : getRootPath()
																	+ "/acl/getRunningWaterInterest.acl",
															type : "post",
															cache : false,
															data : {},
															async : false,
															success : function(
																	date) {
																mini
																		.getbyName(
																				"sn")
																		.setValue(
																				date);
															}
														});
											}
										},
										remoteOper : true,
										pageSize : 15,
										showPager : true,
										lazyLoad : false,
										loadMode : 'ajax',

										entityClassName : 'com.tenwa.leasing.entity.finacial.InterestProvision',
										frozenStartColumn : 0,
										frozenEndColumn : 2,
										queryButtonColSpan : 6,
										queryButtonNewLine : true,
										xmlFileName : '/eleasing/jsp/finacial/interest_provision/interest_provision_list.xml',
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
													field : 'sn',
													header : '流水号',
													headerAlign : 'center',
													align : 'center',
													visible : true,
													width : 100,
													queryConfig : {},
													formEditorConfig : {
														readonly : true,
														allowinput : false,
														newLine : true,
														labelWidth : 100,
														width : 200,
														required : false,
														type : 'text'
													}
												},
												{
													field : 'didate',
													header : '日期',
													align : 'center',
													headerAlign : 'center',
													visible : true,
													width : 100,
													dateFormat : "yyyy-MM",
													formEditorConfig : {
														newLine : false,
														type : 'date',
														labelWidth : 100,
														width : '100%',
														format : 'yyyy-MM',
														required : true
													},
													queryConfig : {
														type : 'date',
														range : true,
														format : 'yyyy-MM',
														newLine : false
													},
													renderer : function(e) {
														var row = e.record;
														return "<a href='javascript:showcustinfo(\""
																+ row.id
																+ "\")'>"
																+ row.didate
																+ "</a>";
													}
												},
												{
													field : 'isbillingname',
													header : '是否开票',
													headerAlign : 'center',
													visible : true,
													width : 100,
													queryConfig : {
														type : 'combobox',
														textField : 'name',
														newLine : false,
														valueField : 'value',
														fillFromFieldName : 'value',
														showNullItem : "true",
														nullItemText : "", //显示空值
														params : {
															pid : 'IsOverdateExecute',
															xmlFileName : '/combos/comboDict.xml'
														}
													},
													formEditorConfig : {
														fieldVisible : false
													}
												},
												{
													field : 'isbilling',
													header : '是否开票',
													headerAlign : 'center',
													visible : false,
													width : 100,
													formEditorConfig : {
														fieldVisible : true,
														readonly : false,
														newLine : true,
														labelWidth : 100,
														width : 200,
														required : false,
														type : 'combobox',//表单域类型
														valueField : 'value',
														textField : 'name',
														allowInput : false,
														showNullItem : true,
														defaultValue : '',
														params : {
															pid : 'IsOverdateExecute',
															xmlFileName : '/combos/comboDict.xml'
														}
													}
												},
												{
													field : '',
													header : '操作',
													width : 200,
													formEditorConfig : {
														fieldVisible : false
													},
													renderer : function(e) {
														var id = e.record.id;
														return "<a href='javascript:void(0);' onclick='showanduploadfile(\""
																+ id
																+ "\",\"one\")'>查看/上传资料 /附件个数："
																+ e.record.attachmentnum
																+ "</a>";
													}
												}, {
													field : 'attachmentnum',
													header : '附件个数',
													visible : false,
													queryConfig : {},
													formEditorConfig : {
														newLine : false,
														readOnly : true,
														required : true,
														fieldVisible : true
													}
												}, {
													field : 'monthsum',
													header : '合计',
													headerAlign : 'center',
													visible : true,
													width : 100,
													formEditorConfig : {
														fieldVisible : false
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

	function showcustinfo(id, name) {
		var params = "id=" + id;
		var url = getRootPath()
				+ "/leasing/financial/interest_provision/interest_provision_info_list.bi?"
				+ params;

		var sheight = window.screen.availHeight - 30;
		var swidth = window.screen.availWidth - 10;
		var winoption = "left=0px,top=0px,height="
				+ sheight
				+ "px,width="
				+ swidth
				+ "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
		window.open(url, '_blank', winoption);
	}
	function showanduploadfile(id, type) {
		var urlFlag = getRootPath()
				+ "/leasing/financial/interest_provision/interest_provision_file_list.bi?id="
				+ id + "&type=" + type;
		mini.open({
			url : urlFlag,
			title : "利息计提资料",
			width : 800,
			height : 455,
			onload : function() {
				mini.get("table_interest_provision_id").reload();
				mini.unmask(document.body);
			},
			ondestroy : function(action) {
				if ("savesuccess" == action) {
					window.location.reload();
				}
			}
		});

	}
</script>
</head>
<body>
	<div id="mini_test_form">
		<div id="id_table_render_table1"></div>
	</div>
</body>
</html>
