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
										id : 'equip_cost',
										width : globalClientWidth - 20,
										height : 420,
										exportTitle : '设备购置费',
										iconCls : 'icon-node',
										renderTo : 'main_equip_cost',
										hiddenQueryArea : true,
										importTargetClass :'com.tenwa.leasing.entity.proj.WpEquipCost',
										importDataIndex : '4',
										importHeaderIndex : '1',
										importprojDevelop :projid,
										importOrExPortCallBack:'SetequipCost',
										editFormPopupWindowWidth : 700,
										//showToolbar : showTools,
										remoteOper : true,
										entityClassName : 'com.tenwa.leasing.entity.proj.WpEquipCost',
										showPager : true,
										lazyLoad : false,
										xmlFileName : '/eleasing/workflow/proj/proj_wind/equip_cost.xml',
										params : {
											projid : projid
										},
										showToolbar: showTools,
										tools : [ 'add', '-', 'edit', '-',
													'remove', '-', 'exportExcel','-','importExcel'],
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
													field : 'code',
													header : '编号',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : true,
													formEditorConfig : {
														newLine :true,
														readOnly : false,
														fieldVisible : true,
													}
												},
												{
													field : 'nametype',
													header : '设备名称',
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
													field : 'type',
													header : '规格型号',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : true,
													formEditorConfig : {
														newLine :true,
														readOnly : false,
														fieldVisible : true,
													}
												},
												{
													field : 'unit',
													header : '单位',
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
													field : 'amount',
													header : '数量',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : true,
													formEditorConfig : {
														newLine :true,
														readOnly : false,
														fieldVisible : true,
													}
												},
												{
													field : 'price',
													header : '单价/元',
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
													field : 'total',
													header : '合计/元',
													headerAlign : 'center',
													summary : true,
													width : 100,
													allowSort : true,
													visible : true,
													formEditorConfig : {
														newLine :true,	
														readOnly : false,
														fieldVisible : true,
													}
												},
												{
													field : 'remark',
													header : '备注',
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
<div id='main_equip_cost'></div>
		  