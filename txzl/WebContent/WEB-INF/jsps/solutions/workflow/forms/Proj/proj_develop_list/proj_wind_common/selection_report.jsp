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
										id : 'table_id9',
										width : globalClientWidth - 20,
										height : 420,
										exportTitle : '机组选型报告',
										iconCls : 'icon-node',
										frozenStartColumn : 0,
										frozenEndColumn : 1,
										renderTo : 'content_table_id9',
										hiddenQueryArea : true,
										frozenStartColumn : 1,
										frozenEndColumn : 3,
										editFormPopupWindowWidth : 700,
										//showToolbar : showTools,
										remoteOper : true,
										entityClassName : 'com.tenwa.leasing.entity.proj.SelectionReport',
										pageSize : 15,
										showPager : true,
										lazyLoad : false,
										xmlFileName : '/eleasing/workflow/proj/proj_search/selection_report.xml',
										params : {
											projid : projid
										},
										showToolbar: showTools,
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
													header : '记录编号',
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
													field : 'projid',
													header : '项目ID',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : false,
													formEditorConfig : {
														readOnly : true,
														fieldVisible : false,
														value : projid
													}
												},
												{
													field : 'fanmodel',
													header : '风机型号',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : true,
													formEditorConfig : {
														readOnly : false,
														width : "100%",
														required:true,
														newLine : true,
														fieldVisible : true
													}
												},
												{field:'',header:'操作',
						  					  		   formEditorConfig:{
						                                   fieldVisible:false},
												               renderer:function(e){
													                   var id = e.record.id;
												                       return "<a href='javascript:void(0);' onclick='showanduploadfile(\"" + id + "\",\"one\")'>查看/上传资料 </a>";
												               }},
												{
													field : 'hubheight',
													header : '轮毂高度(m)',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : true,
													formEditorConfig : {
														readOnly : false,
														width : "100%",
														required:true,
														newLine : false,
														fieldVisible : true
													}
												},
												{
													field : 'fanstations',
													header : '风机台数(台)',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : true,
													formEditorConfig : {
														readOnly : false,
														width : "100%",
														newLine : true,
														fieldVisible : true
													}
												},
												{
													field : 'theroygeneration',
													header : '项目理论发电量(万度)',
													headerAlign : 'center',
													width : 150,
													allowSort : true,
													visible : true,
													formEditorConfig : {
														readOnly : false,
														width : "100%",
														newLine : false,
														fieldVisible : true
													}
												},
												{
													field : 'wakelosses',
													header : '尾流损失(%)',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : true,
													formEditorConfig : {
														readOnly : false,
														width : "100%",
														newLine : true,
														fieldVisible : true
													}
												},
												{
													field : 'reductionfactor',
													header : '折减系数(%)',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : true,
													formEditorConfig : {
														readOnly : false,
														required:true,
														width : "100%",
														newLine : false,
														fieldVisible : true
													}
												},
												{
													field : 'totalpower',
													header : '总上网电量(万度)',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : true,
													formEditorConfig : {
														readOnly : false,
														width : "100%",
														newLine : true,
														fieldVisible : true
													}
												},												
												{
													field : 'pninetyhours',
													header : 'P90小时数(h)',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : true,
													formEditorConfig : {
														required:true,
														readOnly : false,
														width : "100%",
														newLine : false,
														fieldVisible : true
													}
												},
												{
													field : 'pseventyfivehours',
													header : 'P75小时数(h)',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : true,
													formEditorConfig : {
														required:true,
														readOnly : false,
														width : "100%",
														newLine : true,
														fieldVisible : true
													}
												},
												{
													field : 'pfiftyhours',
													header : 'P50小时数(h)',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : true,
													formEditorConfig : {
														required:true,
														readOnly : false,
														width : "100%",
														newLine : false,
														fieldVisible : true
													}
												},
												{
													field : 'avestandard',
													header : '标准小时数平均值(h)',
													headerAlign : 'center',
													width : 150,
													allowSort : true,
													visible : true,
													formEditorConfig : {
														required:true,
														readOnly : false,
														width : "100%",
														newLine : true,
														fieldVisible : true
													}
												},
												{
													field : 'mindistance',
													header : '机组最小间距(m)',
													headerAlign : 'center',
													width : 150,
													allowSort : true,
													visible : true,
													formEditorConfig : {
														readOnly : false,
														width : "100%",
														newLine : false,
														fieldVisible : true
													}
												},
												{
													field : 'severeweathername',
													header : '风电场涉及的灾害性天气',
													visible : true,
													width : 200,
													formEditorConfig : {
														type : 'text',
														labelWidth : 100,
														width : 200,
														fieldVisible : false,
														newLine : true
													}
												},
												{
													field : 'severeweather',
													header : '风电场涉及的灾害性天气',
													visible : false,
													formEditorConfig : {
														type : 'combobox',
														textField : 'name',
														valueField : 'value',
														newLine:false,
														fieldVisible : true,
														params : {
															pid : 'severeweather',
															xmlFileName : '/combos/comboDict.xml'
														}
													}
												},
												{
													field : 'sensitivityfactorsname',
													header : '风电场涉及的敏感性因素',
													visible : true,
													width : 200,
													formEditorConfig : {
														type : 'text',
														labelWidth : 100,
														width : 200,
														fieldVisible : false,
													}
												},
												{
													field : 'sensitivityfactors',
													header : '风电场涉及的敏感性因素',
													visible : false,
													formEditorConfig : {
														type : 'combobox',
														textField : 'name',
														valueField : 'value',
														newLine : false,
														fieldVisible : true,
														params : {
															pid : 'sensitivityfactors',
															xmlFileName : '/combos/comboDict.xml'
														}
													}
												}

										]
									});
						});
	});
	function showanduploadfile(id,type){
			var urlFlag = getRootPath()+"/leasing/financial/loan_account/loan_account_file_list.bi?id="+id+"&type="+type;
			mini.open({
		        url: urlFlag,
		        title: "机组选型资料", width: 800, height: 455,
		        onload: function () {},
		        ondestroy: function (action) {
		        	if("savesuccess" == action){
		        		window.location.reload();
		        	}
		        }
		    });

	  	}
</script>
<div id='content_table_id9'></div>