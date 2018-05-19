<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>可再生能源补助目录</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>


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
	mini.get(tableid).reload();
	mini.unmask(document.body);
}
	jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ],
						function(ApTable) {
							new ApTable(
									{
										id : 'table_renewable_energy_info_id',
										renderTo : "id_table_render_table1",
										width : globalClientWidth,
										height : globalClientHeight,
										iconCls : 'icon-node',
										hiddenQueryArea : false,
										multiSelect : true,
										editFormPopupWindowWidth : 800,
										editFormPopupWindowHeight : 300,
										title : '可再生能源补助目录',
										remoteOper : true,
										pageSize : 15,
										showPager : true,
										isClickLoad : false,
										importTargetClass : 'com.tenwa.leasing.entity.renewableenergy.RenewableEnergy',
										importDataIndex : '3',
										importHeaderIndex : '2',
										lazyLoad : false,
										loadMode : 'ajax',
										entityClassName : 'com.tenwa.leasing.entity.renewableenergy.RenewableEnergy',
										frozenStartColumn : 0,
										frozenEndColumn : 2,
										virtualScroll : false,
										xmlFileName : '/eleasing/jsp/renewable_energy/renewable_energy_list.xml',
										tools : [ 'add', '-', 'edit', '-',
												'remove', '-' ,'importExcel',
												'-', 'exportExcel'],
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
													field : 'protype',
													header : '项目类别',
													width : 70, 
													visible : true,
													formEditorConfig : {
														required : true,
														fieldVisible : true
													}
												},
												{
													field : 'provinces',
													header : '所在省市',
													visible : true,
													queryConfig : {},
													formEditorConfig : {
														required : true,
														fieldVisible : true
													}
												}
												,
												/*备注字段改成项目所在名称  */
												{
													field : 'note',
													header : '项目所在地',
													visible : true,
													width : 100,
													formEditorConfig : {
														newLine : true,
														colspan : 3,
														width : "100%",
														type : 'textarea',
														fieldVisible : true
													}
												},
												{
													field : 'proname',
													header : '项目名称',
													visible : true,
													width : 330,
													queryConfig : {},
													formEditorConfig : {
														newLine:true,
														required : true,
														fieldVisible : true
													}
												},
												{
													field : 'procom',
													header : '项目公司',
													width : 250,
													visible : true,
													formEditorConfig : {
														required : true,
														fieldVisible : true
													}
												},
												{
													field : 'procapacity',
													header : '项目容量（MW）',
													visible : true,
													summary : true,
													formEditorConfig : {
														newLine:true,
														vtype:"float",
														required : true,
														fieldVisible : true,
														labelWidth : 120
													}
												},
												{
													field : 'batch',
													header : '批次',
													width: 70,
													visible : true,
													formEditorConfig : {
														required : true,
														fieldVisible : true
													}
												},
												{
													field : 'docissuedate',
													header : '文件下发时间',
													visible : true,
													width : 100,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														newLine : true,
														type : 'date',
														labelWidth : 100,
														width : '100%',
														format : 'yyyy-MM-dd',
														required : true
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
