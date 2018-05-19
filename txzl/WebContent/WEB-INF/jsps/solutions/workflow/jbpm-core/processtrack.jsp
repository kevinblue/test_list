<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>流程跟踪</title>
		<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css?version=3" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
		<script type="text/javascript">
			var all_width = (document.documentElement || document.body).clientWidth - 2;
			var all_height = (document.documentElement || document.body).clientHeight - 2;
			var currenttaskid = "";
		</script>
		<style type="text/css">
		html, body {
			overflow: hidden;
		}
		</style>
</head>
<%@include file="/base/mini.jsp"%>
<body style="overflow:hidden;"> 
	<div id="id_tasksContainer"></div>
	<script type="text/javascript" defer>
	var loadMask = null;
	var datas=[{text:'全部',value:'全部'},{text:'Pending[进行中]',value:'Pending[进行中]'},{text:'Finish[结束]',value:'Finish[结束]'}];
	jQuery(function () {
		seajs.use(["js/apcomponent/aptable/aptable.js"], function (ApTable) {
			new ApTable({
				id : "id_tasks_table",
				renderTo : "id_tasksContainer",
				width : globalClientWidth,
				height : globalClientHeight,
				showPager : true, //分页按钮是否显示
				title : '流程跟踪',
				multiSelect : false,
				isExcel : true,
				/* queryButtonColSpan : 6,
				queryButtonNewLine : true, */
				remoteOper : true,
				xmlFileName : "/jbpm/processtrack.xml",
				columns : [ {
						field : 'serialno',
						header : '关键字一'
					}, {
						field : 'approval',
						header : '发起人',
						queryConfig:{}
					},{
						field : 'projectname',
						header : '关键字二',
						queryConfig:{}
					}, {
						field : 'workflowname',
						header : '流程名称',
						queryConfig:{}
					}, {
						field : 'taskid',
						header : '当前任务',
						align : 'center',
						renderer : function (e) {
							var rowData = e.record;
							var taskids = rowData['taskid'].split(",");
							var tasknames = rowData['taskname'].split(",");
							var rs = "";
							for (var i = 0; i < taskids.length; i++) {
								var taskid = taskids[i];
								var taskname = "";
								for (var j = 0; j < tasknames.length; j++) {
									var tempTaskName = tasknames[j];
									var tempTaskId = tempTaskName.split("@@_@@")[0];
									if (tempTaskId == taskid) {
										taskname = tempTaskName.substring(taskid.length + "@@_@@".length, tempTaskName.length);
										break;
									}
								}
								if (i > 0) {
									rs += '<br>&nbsp;';
								}
								rs += '<a href="javascript:void(0);" onclick="toProcessForm(' + taskid + ')">' + taskname + '</a>';
							}
							return rs;
						}
					},/*  {
						field : 'viewdiagram',
						header : '流程图',
						align : 'center',
						renderer : function (e) {
							var rowData = e.record;
							return '<a href="javascript:void(0);" onclick="toProcessActivePicture(\'' + rowData.deployid + '\',\'' + ((rowData.processinstanceid)) + '\')">查看</a>';
						}
					}, {
						field : 'changeoper',
						header : '流程状态',
						align : 'center',
						renderer : function (e) {
							var rowData = e.record;
							//任务ID
							var taskid = rowData["taskid"];
							//流程是否结束
							var isfinish = rowData["processend"];
							if (!isfinish) {
								//return '<a href="javascript:showchangedateview(' + taskid + ')">修改</a>'
								return 'Pending[进行中]';
							} else {
								//return '<a href="javascript:showchangedateview(' + taskid + ')">修改</a>'
								return 'Finish[结束]';
							}

						},
					}, */{
						field:'processstatus',
						header : '流程状态',
						align : 'center',
						/* queryConfig:{
							type : 'combobox',
							data:[{id:'',text:''},{id:'Pending[进行中]',text:'Pending[进行中]'},{id:'Finish[结束]',text:'Finish[结束]'}]
						},  */
					}, {
						field : 'processstart',
						header : '流程开始时间',
						allowSort : true,
						dateFormat : 'yyyy-MM-dd HH:mm:ss'
					}, {
						field : 'processend',
						header : '流程结束时间',
						allowSort : true,
						dateFormat : 'yyyy-MM-dd HH:mm:ss',
						renderer : function (e) {
							var rowData = e.record;
							if (rowData["processend"]) {
								return mini.formatDate(rowData["processend"], 'yyyy-MM-dd HH:mm:ss'); //+" < 已结束  > ";
							}
							return "进行中...";
						}
					}
				],
				tools : ['globalQuery', '_', 'exportExcel','-',{
					isHtml:'搜索',
					html: '<input class="mini-combobox" id="processstu" textField="text" valueField="value" data="datas" allowInput="false" text="全部" value="全部" onvaluechanged="onprocesschange()"/> '
			        
				} ],
				params : {
					USERID : "${sessionScope['login_userid']}"
				}

			});
		});
	});
	 function onprocesschange(e){
		var processstatus=mini.get("processstu").getValue();
		var grid=mini.get("id_tasks_table");
		var processsta="";
		if(processstatus=="全部"){
			processsta +="or 1=1";
			grid.setParams({processstatus:processstatus,processsta:processsta});
			grid.reload();
		}else{
			processsta +="and 1=1";
			grid.setParams({processstatus:processstatus,processsta:processsta});
			grid.reload();
		}
		
	} 
	$("id_queryWorkflowsTableInput").onkeypress = function (evt) {
		var e = getEvent(evt);
		var code = e.keyCode || e.charCode;
		if (13 == code) {
			var workflowsTable = getTracywindyTable("id_tasks_table");
			workflowsTable.setParams({
				queryText : this.value.toUpperCase()
			});
			workflowsTable.reload();
		}
	};
	//显示流程图
	function toProcessActivePicture(deployId, processInstanceId, planActorId) {
		var url = "${pageContext.request.contextPath}/workflow/jbpm/getActivedRects.action?deployId=" + deployId + "&processInstanceId=" + escape(encodeURIComponent(processInstanceId)) + "&jbpmWorkflowHistoryInfoUserId=" + planActorId + "&randomNumber=" + Math.random();
		openFullScreenWindow(url);
	}
	function doQueryByText_pending() {
		var contentText = document.all['id_contextText_pending'].value;
		var tableContact = getTracywindyTable("pendingRequestTable");
		tableContact.params['proj_id'] = contentText.toUpperCase();
		tableContact.reload();
	}
</script>
<script type="text/javascript">

	function toProcessForm(currentTaskId) {
		var url = "${pageContext.request.contextPath}/jbpm/viewHistoryProcessForm.action?currentTaskId=" + currentTaskId;
		openFullScreenWindow(url);
	}
	function showchangedateview(taskid) {
		var multiform = new mini.Form("multiform");
		var multieditWindow = mini.get("multieditWindow");
		currenttaskid = taskid;
		multieditWindow.show();
		multiform.clear();
		isMulti = true;
	}
	function submitMultiData(e) {
		var multieditWindow = mini.get("multieditWindow");
		var URL = "${pageContext.request.contextPath}/leasing/FillFlowDataByParam.action";
		var currentid = currenttaskid;
		var param = {};
		param.currentTaskId = currentid;
		param.changefield = mini.getbyName("changefield").value;
		param.changedate = mini.getbyName("changedate").value;
		if (mini.getbyName("changefield").value && mini.getbyName("changedate").value) {
			$.ajax({
				url : URL,
				type : "post",
				cache : false,
				async : false,
				data : param,
				success : function (text) {
					mini.alert("修改完成！");
				}
			});
			multieditWindow.hide();
		} else {
			mini.alert("修改域和值不能为空");
		}

	}
	//取消
	function clearMultiData() {
		var multieditWindow = mini.get("multieditWindow");
		multieditWindow.hide();
	}
</script>
<!-- 修改小窗口 -->
	<div id="multieditWindow" class="mini-window" title="修改内容"
		style="width: 600px; height: 200px;" showModal="true" allowResize="true" allowDrag="true">
		<div id="multiform">
			<table>
				<tr>
					<td class="td-content-title" style="width: 30PX;">修改域：</td>
					<td class="td-content" style="width: 90%"><input
						name="changefield" label="修改域" class="mini-textbox"
						required="true" style="width: 500px;" /></td>
				</tr>
				<tr>
					<td class="td-content-title">修改值：</td>
					<td class="td-content"><input
						style="width: 500px; height: 100px;" name="changedate"
						class="mini-textarea" label="修改值" textField="name" required="true" /></td>
				</tr>
				<tr>
					<td style="text-align: center; width: 100%;" colspan="2"><a
						class="mini-button " onclick="submitMultiData">&nbsp;&nbsp;确定&nbsp;&nbsp;</a>
						<a class="mini-button " onclick="clearMultiData">&nbsp;&nbsp;取消&nbsp;&nbsp;</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>