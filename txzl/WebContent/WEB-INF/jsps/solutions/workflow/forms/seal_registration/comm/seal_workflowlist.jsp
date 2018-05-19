<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
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
<body style="overflow:hidden;"> 
	<div id="id_tasksContainer"></div>
	<script type="text/javascript" defer>
	var loadMask = null;
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask ==true){showTools = false};
	jQuery(function () {
		seajs.use(["js/apcomponent/aptable/aptable.js"], function (ApTable) {
			new ApTable({
				id : "seal_workflowlist",
				renderTo : "id_seal_workflowlist_config",
				width : globalClientWidth,
				height : globalClientHeight,
				queryButtonColSpan:6,
				lazyLoad:false,
				isClickLoad:false,
				remoteOper :false,
				multiSelect:true,
				showPager:true,
				showToolbar: showTools,
				data: mini.decode('${empty json_seal_workflowlist_str ? "[]" : json_seal_workflowlist_str }'),
	/* 			completeCallBack: function(miniTable){
					miniTable.setData(mini.decode('${empty json_seal_workflowlist_str ? "[]" : json_seal_workflowlist_str }'));
				},  */
				removeOperCallBack:removeJsonData,
				columns : [{
						type : 'checkcolumn'
					}, {
						field : 'serialno',
						header : '流水号',
						visible:false,
						align : 'center'
					},{
						field : 'projectname',
						header : '项目名称',
						align : 'center',
					 	renderer : function (e) {
							var rowData = e.record;
							var taskids = rowData['taskid'].split(",");
							var tasknames = rowData['taskname'].split(",");
							var projectname=rowData['projectname'];
							var rs = "";
							for (var i = 0; i < taskids.length; i++) {
								var taskid = taskids[i];
								var taskname = "";
								for (var j = 0; j < tasknames.length; j++) {
									var tempTaskName = tasknames[j];
									var tempTaskId = tempTaskName.split("##_##")[0];
									if (tempTaskId == taskid) {
										taskname = tempTaskName.substring(taskid.length + "##_##".length, tempTaskName.length);
										break;
									}
								}
								if (i > 0) {
									rs += '<br>&nbsp;';
								}
								rs += '<a href="javascript:void(0);" onclick="toProcessFormHu(' + taskid + ')">' + projectname + '</a>';
							}
							return rs;
						}
					},{
						field : 'workflowname',
						header : '流程名称',
						align : 'center'
					},
					{
						field : 'processinstancedbid',
						header : '流程号',
						align : 'center'
						
					},{
						field : 'taskid',
						header : '当前任务',
						align : 'center',
						visible:false,
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
									var tempTaskId = tempTaskName.split("##_##")[0];
									if (tempTaskId == taskid) {
										taskname = tempTaskName.substring(taskid.length + "##_##".length, tempTaskName.length);
										break;
									}
								}
								if (i > 0) {
									rs += '<br>&nbsp;';
								}
								rs +=taskname;
							}
							return rs;
						} 
					}, {
						field : 'viewdiagram',
						header : '流程图',
						align : 'center',
						visible:false,
						renderer : function (e) {
							var rowData = e.record;
							return '<a href="javascript:void(0);" onclick="toProcessActivePicture(\'' + rowData.deployid + '\',\'' + ((rowData.processinstanceid)) + '\')">查看</a>';
						}
					}, {
						field : 'changeoper',
						header : '修改数据',
						align : 'center',
						visible:false,
						renderer : function (e) {
							var rowData = e.record;
							//任务ID
							var taskid = rowData["taskid"];
							//流程是否结束
							var isfinish = rowData["processend"];
							if (!isfinish) {
								return '<a href="javascript:showchangedateview(' + taskid + ')">修改</a>'
							} else {
								return '<a href="javascript:showchangedateview(' + taskid + ')">修改</a>'
								//return '流程已结束';
							}

						}
					}, {
						field : 'processstart',
						header : '流程开始时间',
						allowSort : true,
					 	dateFormat : 'yyyy-MM-dd HH:mm:ss'
					}, {
						field : 'processend',
						header : '流程结束时间',
						allowSort : true,
					 	dateFormat : 'yyyy-MM-dd HH:mm:ss'
					}
				],
				tools : ['remove'],
				params : {
					USERID : "${sessionScope['login_userid']}"
				}

			});
		});
	});
	$("id_queryWorkflowsTableInput").onkeypress = function (evt) {
		var e = getEvent(evt);
		var code = e.keyCode || e.charCode;
		if (13 == code) {
			var workflowsTable = getTracywindyTable("seal_workflowlist");
			workflowsTable.setParams({
				queryText : this.value.toUpperCase()
			});
			workflowsTable.reload();
		}
	};
	function removeJsonData(miniTable,selectedRowDatas){
		mini.get("id_json_seal_workflowlist_str").setValue(mini.encode(mini.get("seal_workflowlist").getData()));
		
	}
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

	function toProcessFormHu(currentTaskId) {
		//var url = "${pageContext.request.contextPath}/jbpm/viewHistoryProcessForm.action?currentTaskId=" + currentTaskId;
		//openFullScreenWindow(url);
		window.open("${pageContext.request.contextPath}/jbpm/viewHistoryProcessForm.action?flag=projSummary&currentTaskId="+currentTaskId,"_blank");
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

	<div id="id_seal_workflowlist_config"></div>
</body>