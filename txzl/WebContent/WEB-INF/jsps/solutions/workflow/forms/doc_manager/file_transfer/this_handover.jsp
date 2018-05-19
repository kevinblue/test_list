<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	jQuery(function() {
		var contractid = "${requestScope['contract_info.id']}";
		var showTools = true;
		if ('${param.isView}' == 'true' || isViewHistoryTask == true) {
			showTools = false;
		}
		;
		seajs
				.use(
						[ "js/apcomponent/aptable/aptable.js" ],
						function(ApTable) {
							new ApTable(
									{
										id : "this_handover",
										renderTo : "id_table_this_handover",
										width : globalClientWidth - 30,
										height : 365,
										title : "",
										isClickLoad : false,
										remoteOper : false,
										showPager : true,
										multiSelect : true,
										showToolbar : showTools,
										tools : [ 'add', '-', 'edit', '-',
												'remove' ],
										params : {
											"contract_id" : contractid
										},
										data : JsonUtil
												.decode('${empty json_this_handover_str ? "[]" : json_this_handover_str }'),
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
													visible : false
												},
												{
													field : 'docsubname',
													header : '子合同号',
													formEditorConfig : {
														labelWidth : 105,
														maxLength : 120,
														colspan : 3,
														width : '100%'
													}
												},
												{
													field : 'docnumber',
													header : '档案编号',
													formEditorConfig : {
														labelWidth : 100,
														maxLength : 120,
														colspan : 3,
														width : '100%'
													}
												},
												{
													field : 'docname',
													header : '档案名称',
													formEditorConfig : {
														newLine : true,
														labelWidth : 100,
														maxLength : 120,
														colspan : 3,
														width : '100%'
													}
												},
												{
													field : 'handoverdate',
													header : '交接日期',
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														type : 'date',
														vtype : 'date',
														format : 'yyyy-MM-dd',
														allowInput : false
													}
												},
												/* {field:'handoverman', header:'交接人',
															formEditorConfig:{
																newLine: true,
																labelWidth:100,
																maxLength:120,
																colspan: 3,
																width: '100%'}}, */
												{
													field : 'handovermanname',
													header : '交接人',
													formEditorConfig : {
														colspan : 6,
														fieldVisible : false,
														fillFromFieldName : 'handoverman',
														fillProperty : 'name',
														entityClassName : 'com.tenwa.leasing.entity.cust.User',
														entityHeaderFieldName : 'custName'
													}
												},
												{
													field : 'handoverman',
													header : '交接人',
													visible : false,
													formEditorConfig : {
														newLine : true,
														colspan : 3,
														width : '100%',
														type : 'queryinput',
														textField : 'name',
														valueField : 'value',
														allowInput : false,
														fieldVisible : true,
														params:{xmlFileName:'/eleasing/workflow/proj/proj_common/creator_combox.xml'}
													}
												},
												{field:'filing',header:'归档位置',width:150,formEditorConfig:{newLine:false,width : 200,labelWidth:100,type : 'combobox',
													textField : 'name',
													valueField : 'value',
													allowInput : false,
													showNullItem : false,
													defaultValue : '档案柜',
													data : [ {
														name : "",
														value : ""
													},{
														name : "档案柜",
														value : "档案柜"
													}, {
														name : "保险柜",
														value : "保险柜"
													} ]}}, 
												 {
													field : 'docmemo',
													header : '备注',
													formEditorConfig : {
														maxLength : 1000,
														newLine : true,
														type : 'textarea',
														colspan : 6,
														height : 70,
														width : '100%'
													}
												} ]
									});
						});
	});
</script>
<div id="id_table_this_handover"></div>
