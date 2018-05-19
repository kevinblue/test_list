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
	jQuery(function () {
		seajs.use(["js/apcomponent/aptable/aptable.js"], function (ApTable) {
			new ApTable({
				id : "seal_workflowlist_r",
				renderTo : "id_seal_workflowlist_r_config",
				width : globalClientWidth,
				height : globalClientHeight,
				queryButtonColSpan:6,
				queryButtonNewLine:false,
				showPager : true, //分页按钮是否显示
				title : '流程信息',
				multiSelect:true,
				isExcel : true,
				remoteOper : true,
				//data: JsonUtil.decode('${empty json_proj_equip_str ? "[]" : json_proj_equip_str }'),
				//data: mini.decode('${empty json_seal_workflowlist_str ? "[]" : json_seal_workflowlist_str }'),
				xmlFileName : "/eleasing/workflow/contract/seal_registration/seal_registration_workflowlist.xml",
				columns : [{
						type : 'checkcolumn'
					}, {
						field : 'serialno',
						header : '流水号',
						align : 'center',
						visible:false
					},
					{
						field : 'projectname',
						header : '项目名称111',
						align : 'center',
						queryConfig:{},
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
								rs += '<a href="javascript:void(0);" onclick="toProcessFormHusiling(' + taskid + ')">' + projectname +'</a>';
							}
							return rs;
						}
					}, {
						field : 'workflowname',
						header : '流程名称',
						align : 'center',
						queryConfig:{}
						
					}, 
					 {
						field : 'processinstancedbid',
						header : '流程号',
						align : 'center',
						queryConfig:{}
						
					}, 
					{
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
						/* queryConfig:{
							type:'date',	  		  									      
  		  			  		range:true,
							defaultValue:mini.formatDate(new Date(),"yyyy-MM-dd"),
						   format:'yyyy-MM-dd HH:mm:ss'
							
						} */
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
				tools : 
					[
						{
							html : '选择需要的流程',
							plain : true,
							iconCls : 'icon-ok',
							handler : function(miniTable, buttonText) 
							{
								var currentArr = mini.get("seal_workflowlist").getData();//本次选择的流程								
								var rows = miniTable.getSelecteds();
						        if (!rows.length)
								{
									mini.alert("请勾选数据在操作!");
									return false;
								}
						        getSealWorkflowStr(currentArr,rows);
						        
						      }
						}],
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
			var workflowsTable = getTracywindyTable("seal_workflowlist_r");
			workflowsTable.setParams({
				queryText : this.value.toUpperCase()
			});
			workflowsTable.reload();
		}
	};
	function getSealWorkflowStr(currentArr,rows){
		var rowData=JSON.parse(mini.encode(rows));
		for(var i=0;i<currentArr.length;i++){
			for(var j=0;j<rowData.length;j++){
				var rowObj=rowData[j];//判断唯一性，相同则返回退出提示错误信息
				var flag1=rowObj.processinstancedbid==currentArr[i].processinstancedbid;
				var flag2=rowObj.projectname==currentArr[i].projectname;
				var flag3=rowObj.workflowname==currentArr[i].workflowname;
				if(flag1&&flag2&&flag3){
					mini.alert("流程号为："+rowObj.processinstancedbid+"流程已选择，请选择其他流程");
					return false;
				}
			}

		}
		var currentStr=JSON.stringify(currentArr);
	  	$.ajax({
	        url:  getRootPath()+"/leasing/getSealWorkflowStr.acl",
	        type: "post",
	        data:  {currentStr:currentStr,rowData:rowData},
	        dataType: 'JSON',
	        cache: false, 
	        success: function (text) {	        	
	        	//mini.get("id_json_seal_workflowlist_str").setValue(currentStr);
	        	var currentObj=mini.get("seal_workflowlist");
	        	currentObj.addRows(rowData,0);
	        	currentObj.saveDataToInput();
	       mini.alert("请在本次盖章流程中查看！");
	        return true; 
	        },
	        error: function (jqXHR, textStatus, errorThrown) {
	            mini.alert("服务器繁忙！");
	        } 
	    });  
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

	function toProcessFormHusiling(currentTaskId) {
		//var url = "${pageContext.request.contextPath}/jbpm/viewHistoryProcessForm.action?flag=projSummary&currentTaskId=" + currentTaskId;
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

	<div id="id_seal_workflowlist_r_config"></div>
</body>