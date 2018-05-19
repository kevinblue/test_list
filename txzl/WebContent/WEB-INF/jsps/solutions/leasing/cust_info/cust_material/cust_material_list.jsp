<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
	var tools1 = [];
	<permission:action code="archives">
	tools1.push('add');
	</permission:action>
	<permission:action code="archives">
	tools1.length > 0 ? tools1.push('-', 'edit') : tools1.push('edit');
	</permission:action>
	<permission:action code="archives">
	tools1.length > 0 ? tools1.push('-', "remove", '-') : tools1.push('remove');
	</permission:action>
	<permission:action code="archives">
	tools1.push({
		html : '查看所有档案资料',//自定义按钮的名字
		plain : true,//背景透明，按钮是否有立体感
		iconCls : 'icon-search',//按钮图标类
		handler : function(miniTable, buttonText) {//按钮点击的响应函数
			showanduploadfile("xx", "all");
		}
	});
	</permission:action>

	var custcontactData;
	var showTools = true;
	if ('${param.isView}' == 'true') {
		showTools = false;
	}
	jQuery(function() {
		seajs.use(
						[ "js/apcomponent/aptable/aptable.js" ],
						function(ApTable) {
							new ApTable(
									{
										id : 'table_id3',
										renderTo : 'content_table_id3',
										width : globalClientWidth,
										height : globalClientHeight,
										iconCls : 'icon-node',
										hiddenQueryArea : false,//是否将查询区域折叠起来，true为隐藏
										showToolbar : showTools,//是否显示表格按钮行
										title : '档案信息查询',
										queryButtonColSpan : 4,//查询区的按钮占的列行数

										//新增弹出窗口调用该方法返回AJAX
										afterShowWindowCallBack : function(
												miniTable, miniForm, operType) {
											if (operType == 'add') {//如果是新增就获取流水号
												$
														.ajax({
															url : getRootPath()
																	+ "/acl/getRunningWater.acl",
															type : "post",
															cache : false,
															data : {},
															async : false,
															success : function(
																	date) {
																mini
																		.getbyName(
																				"rownumber")
																		.setValue(
																				date);
															}
														});
											}
										},
										remoteOper : true,
										entityClassName : 'com.tenwa.leasing.entity.cust.CustMaterial',
										pageSize : 15,
										showPager : true,
										lazyLoad : false,
										xmlFileName : '/eleasing/jsp/cust_info/cust_material/get_cust_material.xml',
										//params:{custid:custid},
										/* tools:[ 'add', '-', 'edit', '-', 'remove','-',
										        {
										         html:'查看所有走访资料',plain:true,iconCls:'icon-search',
										         handler:function(miniTable, buttonText) {
											          showanduploadfile("xx","all");
										  }}], */
										tools : tools1,
										columns : [
												{
													type : 'indexcolumn'
												},
												{
													type : 'checkcolumn'
												},
												{
													field : 'id',
													header : 'id',
													headerAlign : 'center',
													visible : false
												},
												{
													field : 'rownumber',
													header : '序号',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													queryConfig : {},
													formEditorConfig : {
														type : 'text',
														labelWidth : 100,
														width : 200,
														readonly : true
													}
												},
												{
													field : 'archivestitle',
													header : '档案标题',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : true,
													queryConfig : {},
													//在配置中引用定义的数组
													formEditorConfig : {
														newLine : true,
														type : 'text',
														required:true,
														labelWidth : 100,
														width : 200
													}
												},
												{
													field : 'archivesnumber',
													header : '档案编号',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : true,
													queryConfig : {},
													formEditorConfig : {
														newLine : true,
														type : 'text',
														labelWidth : 100,
														width : 200
													}
												},
												{
													field : 'archivestypename',
													header : '档案类型',
													headerAlign : 'center',
													width : 100,
													visible : true,
													queryConfig : {
														newLine:true,
														type : 'combobox',
														params : {
															pid : 'material_type',
															xmlFileName : '/combos/comboDict.xml'
														},
														textField : 'name',
														valueField : 'value'
													},
													formEditorConfig : {
														newLine : true,
														fieldVisible : false,
														fillFromFieldName : 'name',
														fillProperty : 'name'
													}
												},
												{
													field : 'archivestype',
													header : '档案类型',
													headerAlign : 'center',
													width : 100,
													visible : false,
													formEditorConfig : {
														type : 'combobox',
														fieldVisible : true,
														labelWidth : 100,
														width : 200,
														params : {
															pid : 'material_type',
															xmlFileName : '/combos/comboDict.xml'
														},
														textField : 'name',
														valueField : 'value'
													}
												},

												{
													field : 'archivesexplain',
													header : '档案说明',
													headerAlign : 'center',
													width : 100,
													visible : true,
													formEditorConfig : {
														newLine : true,
														width : 200,
														type : 'textarea',
														allowCellWrap : "true"
													}
												},
												{
													field : '',
													header : '操作',
													formEditorConfig : {
														fieldVisible : false
													},
													renderer : function(e) {
														var id = e.record.id;
														return "<a href='javascript:void(0);' onclick='showanduploadfile(\""
																+ id
																+ "\",\"one\")'>查看/上传资料 </a>";
													}
												} ]
									});
						});

	});
	function showanduploadfile(id, type) {

		/*  
		
		 var custid='${sessionScope.loginUser.id}';
		 if(custid==""||custid.length<0){
		 mini.alert("没有获取到当前登录人,请重新登录系统");
		 return false;  
		 } */

		var urlFlag = getRootPath()
				+ "/leasing/cust_info/cust_material/cust_material_file_list.bi?id="
				+ id + "&type=" + type;
		mini.open({
			url : urlFlag,
			title : "档案管理",
			width : 800,
			height : 455,
			onload : function() {
			},
			ondestroy : function(action) {
				if ("savesuccess" == action) {
					window.location.reload();
				}
			}
		});

	}
</script>
<div id='content_table_id3'></div>