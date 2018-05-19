<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	jQuery(function() {
		var showTools = true;
		if ('${param.isView}' == 'true' || isViewHistoryTask == true) {
			showTools = false;
		}
		;
		var buttons = [ 'add', '-', 'edit', '-', 'copy', '-', 'remove', '-',
				'exportExcel', '-', 'importExcel' ];
		if ('${param.buttons}' != '') {
			var s_buttons = '${param.buttons}';
			var v_buttons = s_buttons.split(",");
			buttons = [];
			for (var i = 0; i < v_buttons.length; i++) {
				buttons.push(v_buttons[i]);
			}
		}
		//获取父页面中保存在hidden域的值
		seajs
				.use(
						[ "js/apcomponent/aptable/aptable.js" ],
						function(ApTable) {
							new ApTable(
									{
										id : "contract_equip",
										renderTo : "id_table_contract_equip_detail",
										width : globalClientWidth - 20,
										height : 360,
										lazyLoad : false,
										title : "",
										isClickLoad : false,
										remoteOper : false,
										showPager : false,
										multiSelect : true,
										title : '',
										importTargetClass : '',//导入EXCEL对应的类
										importOrExPortCallBack : '',//导入回调方法
										importHeaderIndex : '3',//标题行
										importDataIndex : '4',//数据行
										showToolbar : showTools,
										virtualScroll : true,
										tools : buttons,
										data : JsonUtil
												.decode('${empty json_contract_equip_str ? "[]" : json_contract_equip_str }'),
										columns : [ {
											type : 'indexcolumn'
										}, {
											type : 'checkcolumn'
										}, {
											field : 'id',
											header : 'item',
											visible : false
										}, {
											field : 'partno',
											header : 'Part No.',
											formEditorConfig : {
												required : true,
												labelWidth : 100,
												maxLength : 120,
												colspan : 3,
												width : '100%'
											}
										}, {
											field : 'description',
											header : 'Material Description',
											formEditorConfig : {
												newLine : true,
												required : true,
												labelWidth : 100,
												maxLength : 120,
												colspan : 3,
												width : '100%'
											}
										},
										
										{
											field : 'procurementtype',
											header : 'Procurement Type',
											formEditorConfig : {
												colspan : 3,
												maxLength : 120
											}
										}, {
											field : 'newtp',
											header : 'New TP(USD)',
											formEditorConfig : {
												colspan : 3,
												maxLength : 120
											}
										},
										{
											field : 'cost',
											header : 'Cost(CNY)',
											formEditorConfig : {
												maxLength : 120
											}
										}, {
											field : 'ackagecost',
											header : 'Package cost',
											formEditorConfig : {
												newLine : true,
												maxLength : 120,
												colspan : 3,
												width : '100%',
												maxLength : 120
											}
										}, {
											field : 'formula',
											header : 'Formula',
											formEditorConfig : {
												maxLength : 120,
												newLine : true,
												colspan : 3,
												height : 70,
												width : '100%'
											}
										}
										]
									});
						});
	});
	
</script>

<div id="id_table_contract_equip_detail"
	style="width: 100%; height: 100%;"></div>