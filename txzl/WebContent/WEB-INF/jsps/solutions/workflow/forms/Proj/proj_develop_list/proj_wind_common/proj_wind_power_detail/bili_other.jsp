<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<script type="text/javascript">
	jQuery(function() {
		var showTools = true;
		if('${param.isView}' == 'true'){showTools = false};
		seajs.use(
						[ "js/apcomponent/aptable/aptable.js" ],
						function(ApTable) {
							new ApTable(
									{
										id : 'bili_other',
										width : globalClientWidth - 20,
										height : 420,
										exportTitle : '各项时间占比',
										iconCls : 'icon-node',
										renderTo : 'main_bili_other',
										hiddenQueryArea : true,
										editFormPopupWindowWidth : 700,
										//showToolbar : showTools,
										remoteOper : true,
										multiSelect : true,
										entityClassName : 'com.tenwa.leasing.entity.proj.WindPowerInvestmentDetails',
										pageSize : 1000,
										showPager : true,
										lazyLoad : false,
										xmlFileName : '/eleasing/workflow/proj/proj_wind/bili_other.xml',
										params : {
											projid : projid
										},
										showToolbar: showTools,
										tools : [ 'add', '-', 'edit', '-',
													'remove', '-', 'exportExcel'],
										columns : [
												{
													type : 'indexcolumn'
												},
												{
													type : 'checkcolumn'
												},
												{
													field : 'id',
													header : '记录编号',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : false,
													formEditorConfig : {
														readOnly : false,
														fieldVisible : false
													}
												},
												{
													field : 'projid',
													header : '项目ID',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : false,
													formEditorConfig : {
														readOnly : false,
														fieldVisible : false,
														value : projid
													}
												},
												{
													field : 'codeid',
													header : '编码',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : false,
													formEditorConfig : {
														readOnly : false,
														fieldVisible : false,
													}
												},
												{
													field : 'equipname',
													header : '设备/劳务名称',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : true,
													formEditorConfig : {
														readOnly : false,
														fieldVisible : true,
													}
												},
												{
													field : 'typename',
													header : '所占比例',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : true,
													formEditorConfig : {
														readOnly : false,
														fieldVisible : true,
													}
												}
										]
									});
						});
	});
</script>
<div id='main_bili_other'></div>