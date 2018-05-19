<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<script type="text/javascript">
	jQuery(function() {
		var showTools = true;
		if('${param.isView}' == 'true' || isViewHistoryTask ==true){showTools = false};
		seajs.use(
				
						[ "js/apcomponent/aptable/aptable.js" ],
						function(ApTable) {
							new ApTable(
									{
										id : 'time_task',
										width : globalClientWidth - 20,
										height : 420,
										exportTitle : '各项任务所占时间',
										iconCls : 'icon-node',
										renderTo : 'main_time_task',
										hiddenQueryArea : true,
										editFormPopupWindowWidth : 700,
										//showToolbar : showTools,
										remoteOper : true,
										entityClassName : 'com.tenwa.leasing.entity.proj.TimeOfTask',
										pageSize : 15,
										showPager : true,
										lazyLoad : false,
										xmlFileName : '/eleasing/workflow/proj/proj_wind/time_of_task.xml',
										params : {
											projid : projid
										},
										showToolbar: showTools,
										tools : [ 'add', '-', 'edit', '-',
													'remove'],
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
													field : 'taskname',
													header : '任务名称',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : true,
													formEditorConfig : {
														newLine:true,
														readOnly : false,
														fieldVisible : true,
													}
												},
												{
													field : 'starttime',
													header : '开始时间',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : true,
													formEditorConfig : {
														readOnly : false,
														fieldVisible : true,
														type:'date',
													    format:'yyyy-MM-dd'
													}
												},
												{
													field : 'endtime',
													header : '结束时间',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : true,
													formEditorConfig : {
														newLine:true,
														readOnly : false,
														fieldVisible : true,
														type:'date',
													    format:'yyyy-MM-dd'
													}
												},
												{
													field : 'duartiontime',
													header : '持续时间',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : true,
													formEditorConfig : {
														newLine: true,
														readOnly : false,
														fieldVisible : false
													}
												}
										]
									});
						});
	});
</script>
<div id='main_time_task'></div>
	  