<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>流程部署</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.fileDownload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/ajaxfileupload.js"></script>

<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">

<style type="text/css">
	html,body{overflow:hidden;}
	td.td-label{
		font-weight:BOLD;
		width:200px;
		padding-left:20px;
	}
	td.td-input input{
		border:1px solid silver;
		width:200px;
	}
	td.td-input select{
		border:1px solid silver;
		width:124px;
	}
	input[name='isNeedAdvise']{
		position:relative;
		top:2px;
	}
	.td-content-readonly {
		background: #F6F6F6;
		border: 1px solid silver;
		height:16px;
	}
	#id_workflow_activity_setting textarea{
		font-size: 12px;
		line-height: 20px;
	}
	#id_workflow_activity_setting input{
		font-size: 12px;
		line-height: 20px;
	}
 
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyCommonUserSelection.js"></script>
<script type="text/javascript">
	var all_width = document.documentElement.clientWidth;
	var all_height = document.documentElement.clientHeight-2;
	
	jQuery(function(){
		//加载流程类别
		new tracywindyComboBox({
			renderTo:'id_workflowTypeComboContainer',
			id:'id_workflowType_combo',
			width:191,
			isContainEmpty:false,
			xmlFileName:'\\combos\\comboDict.xml',
			loadMode:'ajax',
			readOnly:true,
			displayField:'name',
			valueField:'code',
			isForceModify:true,
			params:{
				pid:'root.workflowType'
			},
			onSelect:function(combo,rowData){}
		});
		
		new tracywindyComboBox({
			renderTo:'id_relatedInstanceContainer',
			id:'id_relatedInstance_combo',
			width:191,
			isContainEmpty:false,
			xmlFileName:'/jbpm/flowDesigners.xml',
			loadMode:'ajax',
			readOnly:true,
			displayField:'name',
			valueField:'id',
			isForceModify:true,
			isCheck : true,
			onSelect:function(combo,rowData){}
		});
		//加载排序
		new tracywindyComboBox({
			dropListHeight:200,
			isContainEmpty:false,
			id:'id_comboMenuPosition',
			width:190,
			name:'currentPosition',
			renderTo:'id_menuPositionContainer',
			loadMode:'ajax',
			readOnly:false,
			xmlFileName:'\\combos\\comboWorkflowTypePosition.xml',
			displayField:'name',
			valueField:'name',
			isCheck:false,
			leftAdd:1,
			params:{},
			rawValue:'最后',
			onSelect:function(combo){
				var currentRawValue = combo.getRawValue();
				if(("第一位" == currentRawValue)||("最后" == currentRawValue)) {
					jQuery("#id_constantLabel").hide();
				} else {
					jQuery("#id_constantLabel").show();
				}
			}
		});
	});
</script>
</head>
<body>
	<div id="div_workflowImport" closed="true" modal="true" title="导入流程" style="display:none;width:500px;height:180px">
        <table style="margin:10px;width:97%">
            <tr id="import_input">
                <td>选择导入的流程</td>
                <td><input type="file" id="input_file_upload" name="input_file_upload"></td>
            </tr>
            <tr id="import_msg" style="display:none">
                <td colspan="2">正在导入流程</td>
            </tr>
            <tr>
                <td colspan="2" style="height:50px">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="2" style="text-align:right;">
                    <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary"onclick="toImportWorkflow();"><span>导入</span></a>
                    <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='jQuery("#div_workflowImport").window("close");'><span>取消</span></a>
                </td>
            </tr>
        </table>
    </div>
	<div id="id_createNewWorkflowWindow" class="mini-window" title="创建新流程" style="display:none;width:400px;height: 310px;">
		<table align="center" style="width:100%;position: relative;">
			<tr><td class="input_label_desc">流程分类:</td><td class="input_value"><div style="float:left;" id="id_workflowTypeComboContainer"></div><span class="content-required">*</span></td></tr>
			<tr><td class="input_label_desc">显示名称:</td><td class="input_value"><input type="text" id="id_displayName"/><span class="content-required">*</span></td></tr>
			<tr><td class="input_label_desc">流程编号:</td><td class="input_value"><input type="text" id="id_worflowCode"/><span class="content-required">*</span></td></tr>
			<tr><td class="input_label_desc">流程关键字:</td><td class="input_value"><input type="text" id="id_name"/><span class="content-required">*</span></td></tr>
			<tr><td class="input_label_desc">版本号:</td><td class="input_value"><input type="text" id="id_version"/><span class="content-required">*</span></td></tr>
			<tr style="display:none;"><td class="input_label_desc">设计器版本:</td><td class="input_value">
				<select id="id_jpdlVersion" style="width:194px;">
					<option value="4.4" selected>jbpm4.4</option>
					<option value="4.3">jbpm4.3</option>
				</select>
				<span class="content-required">*</span></td>
			</tr>
			<tr><td class="input_label_desc">流程描述:</td><td class="input_value"><input type="text" id="id_description"/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			<tr><td colspan=3><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
			<tr><td colspan=3><div style="width:100%;border-top:1px solid #DDD;"></div></td></tr>
			<tr><td colspan=3><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
			<tr><td class="input_label_desc" colspan=3><span style="float:left;margin-left:100px;">排在&nbsp;&nbsp;</span><span style="float:left;" id="id_menuPositionContainer"></span><span style="float:left;" id="id_constantLabel">&nbsp;&nbsp;之后</span></td></tr>
			<tr class='content-separator'>
				<td colspan='2'>
					<a class="mini-button" onclick="toCreateNewWorkflowPage"><span>确定</span></a>
					<a class="mini-button" onclick='__toCreateNewWorkflowPageClose'><span>取消</span></a>
				</td>
			</tr>
		</table>
	</div>
	
	
	<form action="" method="post" id="id_workflow_form">
		<input type="hidden" id='id_form_workflow_id'			name="id"/>
		<input type="hidden" id='id_form_workflow_code'		name="code"/>
		<input type="hidden" id='id_form_workflow_type'		name="type"/>
		<input type="hidden" id='id_form_workflow_position'	name="position"/>
		<input type="hidden" id='id_form_display_name'			name="display_name"/>
		<input type="hidden" id='id_form_workflow_name'		name="workflow_name"/>
		<input type="hidden" id='id_form_workflow_version'	name="workflow_version"/>
		<input type="hidden" id='id_form_workflow_jpdlVersion' name="workflow_jpdlVersion"/>
		<input type="hidden" id='id_form_workflow_description' name="workflow_description"/>
		<input type="hidden" id='id_form_workflow_isDeployed' name="workflow_isDeployed"/>
		<input type="hidden" id='id_form_workflow_designerWorkflowJsonString'		name="workflow_designerWorkflowJsonString"/>
	</form>
	<div id="id_processDefinitionsContainer"></div>
	<div id="id_ajax_debug" style="display: none;"></div>
	<script type="text/javascript" language="javascript">
		var globalDesignerId = {};
		function createNewWorkflow() {
			resetCreateNewWorkflowPage(); 
			mini.get("id_createNewWorkflowWindow").show();
		}
		
		function resetCreateNewWorkflowPage() {
			$("#id_form_workflow_id").val("");
			$("#id_form_display_name").val("");
			$("#id_form_workflow_name").val("");
			$("#id_form_workflow_type").val("");
			$("#id_form_workflow_code").val("");
			$("#id_form_workflow_position").val("");
			$("#id_form_workflow_version").val("");
			$("#id_form_workflow_jpdlVersion").val("");
			$("#id_form_workflow_description").val("");
			$("#id_form_workflow_designerWorkflowJsonString").val("{}");
			$("#id_form_workflow_isDeployed").val("");
			
			$("#id_displayName").val("");
			$("#id_worflowCode").val("");
			$("#id_name").val("");
			$("#id_version").val("");
			$("#id_jpdlVersion").val("4.4");
			$("#id_description").val("");

			getTracywindyObject('id_workflowType_combo').setValue("");
			getTracywindyObject('id_comboMenuPosition').setRawValue("最后");
		}
		
		
		function __toCreateNewWorkflowPageClose() {
			mini.get("id_createNewWorkflowWindow").hide();
		}
		function toCreateNewWorkflowPage() {
			var displayName = $("#id_displayName").val();
			var name = $("#id_name").val();
			var version = $("#id_version").val();
			var type = getTracywindyObject('id_workflowType_combo').getValue();
			var code = $("#id_worflowCode").val();
			var jpdlVersion = $("#id_jpdlVersion").val();
			var description = $("#id_description").val();
			if(!type){mini.alert("流程分类不能为空");return;}
			if(!displayName){mini.alert("流程显示名称不能为空");return;}
			if(!code){mini.alert("流程编号不能为空");return;}
			if(!name){mini.alert("流程关键字不能为空");return;}
			if(!version){mini.alert("流程版本号不能为空");return;}
			if(!jpdlVersion){mini.alert("设计器版本不能为空");return;}
			if(!/^\d+$/.test(version)) {
				mini.alert("流程版本号必须为数字");return;
			}
			mini.mask({ el: document.body, cls: 'mini-mask-loading', html: '加载中...' });
			$("#id_form_queryText_id").val(mini.get('id_workflows_table').getGlobalQueryText());
			$("#id_form_display_name").val(displayName);
			$("#id_form_workflow_name").val(name);
			$("#id_form_workflow_type").val(type);
			$("#id_form_workflow_code").val(code);
			$("#id_form_workflow_position").val(getTracywindyObject('id_comboMenuPosition').getValue());
			$("#id_form_workflow_version").val(parseInt(version)+"");
			$("#id_form_workflow_jpdlVersion").val(jpdlVersion);
			$("#id_form_workflow_description").val(description);
			submitForm(jpdlVersion);
		}
		
		
		function submitForm(jpdlVersion) {
			var formAction = "${pageContext.request.contextPath}/jbpm/getDesignerSavedDiagramInfo.action";
			formAction+="?pageStart="+mini.get("id_workflows_table").start;
			//打开窗体，并post提交页面	
			var toDay = new Date();	
			windowname="win"+toDay.getTime(); 
			var sheight = window.screen.availHeight-30;
			var swidth = window.screen.availWidth-10;
			window.open(formAction,windowname,"left=0px,top=0px,height="+sheight+"px,width="+swidth+"px,scrollbars=yes,fullscreen=yes,status=yes,toolbar=no,menubar=no,location=no");	
			var form =$("#id_workflow_form")[0];
			with(form) {
				target=windowname;
				action = formAction;
			} 
			form.submit();
			mini.unmask(document.body);
			mini.get("id_createNewWorkflowWindow").hide();
			return false;
		}
		
		function modifyWorkflow(rowData) {
			var isDeployed = (rowData.deployid ? 'true' : 'false');
			mini.mask({ el: document.body, cls: 'mini-mask-loading', html: '加载中...' });
			resetCreateNewWorkflowPage();
			setFormFieldValue(rowData);
			var jpdlVersion = rowData['processdefinitionjpdlversion'];
			if('true'==isDeployed) {
				$("#id_form_workflow_isDeployed").val("true");
			}
			submitForm(jpdlVersion);
		} 
			
		function setFormFieldValue(rowData) {
			var id = rowData['id'];
			//var designerWorkflowJsonString = rowData['designerworkflowjsonstring'];
			//$("#id_form_workflow_designerWorkflowJsonString").val(designerWorkflowJsonString);
			$("#id_form_workflow_id").val(id);
			$("#id_form_queryText_id").val(mini.get('id_workflows_table').getGlobalQueryText());
			var jpdlVersion = rowData['processdefinitionjpdlversion'];
			$("#id_form_display_name").val(rowData['processdefinitiondisplayname']);
			$("#id_form_workflow_name").val(rowData['processdefinitionkey']);
			$("#id_form_workflow_type").val(rowData['type']);
			$("#id_form_workflow_code").val(rowData['code']);
			//$("#id_form_workflow_position").val(rowData['position']);
			$("#id_form_workflow_position").val(rowData['processdefinitionkey']+"-"+rowData['processdefinitionversion']);
			$("#id_form_workflow_version").val(rowData['processdefinitionversion']);
			$("#id_form_workflow_jpdlVersion").val(jpdlVersion);
			$("#id_form_workflow_description").val(rowData['processdefinitiondescription']);
		}
		
			
		//显示流程图
		function toProcessActivePicture(rowData) {
			var deployId = rowData.id;
			var processInstanceId = '-1'; 
			var maxDotX = rowData.maxdotx; 
			var maxDotY = rowData.maxdoty;
			maxDotX = (maxDotX+"")||"0";
			maxDotY = (maxDotY+"")||"0";
			window.open("${pageContext.request.contextPath}/workflow/jbpm/getActivedRects.action?deployId="+deployId+"&maxDotX="+maxDotX+"&maxDotY="+maxDotY+"&processInstanceId="+processInstanceId+"&randomNumber="+Math.random(),"_blank");
		}
		
		
		function publishDeploy(designedId) {
			mini.mask({ el: document.body, cls: 'mini-mask-loading', html: '加载中...' });
			tenwa.ajaxRequest({
				method:'post',
				url:'${pageContext.request.contextPath}/jbpm/deployWorkflow.action',
				success:function(res){
					mini.unmask(document.body);
					mini.alert("发布成功",'提示',function(){
						mini.get("id_workflows_table").reload();
					});
				},
				failure:function(res){
					mini.unmask(document.body);
					mini.alert("发布失败",'提示',function(){
						mini.get("id_workflows_table").reload();
					});
				},
				params:{
					designedId:designedId
				}
			});
		}
			
		function removeDeploy(designedId, deployId) {
			mini.confirm("确定删除该流程相关的所有信息么？", '提示', function(action) {
				if(action == 'ok'){
					mini.mask({ el: document.body, cls: 'mini-mask-loading', html: '加载中...' });
					tenwa.ajaxRequest({
						method:'post',
						url:'${pageContext.request.contextPath}/jbpm/removeDeployedWorkflow.action',
						success:function(res){
							mini.unmask(document.body);
							mini.alert("删除成功",'提示',function(){
								var workflowsTable = mini.get("id_workflows_table");
								workflowsTable.setParams({
									queryText:'${param.queryText}'
								});
								workflowsTable.reload();
							});
						},
						failure:function(res){
							mini.unmask(document.body);
							mini.alert("删除失败",'提示',function(){
								var workflowsTable = mini.get("id_workflows_table");
								workflowsTable.setParams({
									queryText:'${param.queryText}'
								});
								workflowsTable.reload();
							});
						},
						params:{
							designedId:designedId,
							deployId:deployId
						}
					});
				}
			});
		}
		
		function removeDesignedWorkflow(designedId) {
			mini.confirm("确定删除该流程设计么？",'提示',function(action) {
				if(action == 'ok'){
					//mini.mask({ el: document.body, cls: 'mini-mask-loading', html: '加载中...' });
					tenwa.ajaxRequest({
						method:'post',
						url:'${pageContext.request.contextPath}/jbpm/removeDesignedWorkflow.action',
						success:function(res){
							var workflowsTable = mini.get("id_workflows_table");
							workflowsTable.setParams({
								queryText:unescape(decodeURIComponent("${param.queryText}"))
							});
							workflowsTable.reload();
						},
						failure:function(res){
							var workflowsTable = mini.get("id_workflows_table");
							workflowsTable.setParams({
								queryText:unescape(decodeURIComponent("${param.queryText}"))
							});
							workflowsTable.reload();
						},
						params:{
							designedId:designedId
						}
					});
				}
			});
		}
	</script>
	<!-- 流程设置 -->
	<div id="id_processDefinitionsContainer"></div>
	
	
	<div id="id_workflow_setting" class="mini-window" title="流程节点设置" style="display:none;width:825px;height:420px;">
		<div id="id_workflow_activity_detail" style="width: 100%;height: 100%;position: relative;">
		</div>
	</div>
	
	
	<div id="id_workflow_key_setting" class="mini-window" title="流程关键字设置" style="display:none;width:450px;height:350px;">
		<table width="100%;" style="table-layout:fixed;">
			<tr>
				<td class="td-label">关键字一：</td>
				<td class="td-input" ><input id="id_keyOne"/></td>
			</tr>
			<tr>
				<td class="td-label">关键字二：</td>
				<td class="td-input" ><input id="id_keyTwo"/></td>
			</tr>
			<tr>
				<td class="td-label">关键字三：</td>
				<td class="td-input" ><input id="id_keyThree"/></td>
			</tr>
			<tr>
				<td class="td-label">关键字四：</td>
				<td class="td-input" ><input id="id_keyFour"/></td>
			</tr>
			<tr>
				<td class="td-label">关键字五：</td>
				<td class="td-input" ><input id="id_keyFive"/></td>
			</tr>
			<tr>
				<td class="td-label">关键字六：</td>
				<td class="td-input" ><input id="id_keySix"/></td>
			</tr>
			<tr>
				<td class="td-label">关键字七<font color="red">(付款单号)</font>：</td>
				<td class="td-input" ><input id="id_keySeven"/></td>
			</tr>
			<tr>
				<td class="td-label">关键字八<font color="red">(合同流水号)</font>：</td>
				<td class="td-input" ><input id="id_keyEight"/></td>
			</tr>
			<tr>
				<td class="td-label">关键字九<font color="red">(项目立项流水号)</font>：</td>
				<td class="td-input" ><input id="id_keyNine"/></td>
			</tr>
			<tr>
				<td class="td-label">关键字十<font color="red">(互斥标识)</font>：</td>
				<td class="td-input" ><input id="id_keyTen"/></td>
			</tr>
			<tr style="text-align:center;">
				<td colspan="2" style="padding-top:10px;border-top:1px solid silver;">
					<a class="mini-button" onclick="saveKeyToWorkflow">保存</a>
					<a class="mini-button" onclick="__saveKeyToWorkflowClose">取消</a>
				</td>
			</tr>
		</table>
	</div>
	
	<div id="id_workflow_attachment_setting" class="mini-window" title="流程附件关联" style="display:none;width:450px;height:120px;">
		<table width="100%;" style="table-layout:fixed;">
			<tr>
				<td class="td-label">关联流程：</td>
				<td class="td-input" ><div style="float:left;" id="id_relatedInstanceContainer"></div></td>
			</tr>
			<tr style="text-align:center;">
				<td colspan="2" style="padding-top:10px;border-top:1px solid silver;">
					<a class="mini-button" onclick="saveRalatedAttachmentToWorkflow">保存</a>
					<a class="mini-button" onclick="__saveRalatedAttachmentToWorkflowClose">取消</a>
				</td>
			</tr>
		</table>
	</div>
	
	
	<div id="id_workflow_activity_setting" class="mini-window" title="流程节点明细设置" style="display:none;width:700px;height:385px;">
		<form id="id_workflow_activity_setting_form">
			<table width="100%;" style="table-layout:fixed;">
				<tr>
					<td style="text-align:left;padding-left:20px;height:30px;line-height:30px;border-bottom:1px solid silver;" colspan=4>
						节点类型：&nbsp;&lt;&nbsp;<label style="color:red;font-weight:BOLD;" id="id_activityType"></label>&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						节点名称：&nbsp;&lt;&nbsp;<label style="color:red;font-weight:BOLD;" id="id_activityName"></label>&nbsp;&gt;&nbsp;
					</td>
				</tr>
			</table>
			<!-- 标签栏 -->
			<div class="mini-tabs" style="height:260px;" >
				<div title="基本信息" style="overflow:hidden;">
					<table width="100%;" style="table-layout:fixed;line-height:20px;text-align: left;">
						<tr style="display:none;">
							<td class="td-label" style="width: 120px;">记录编号：</td>
							<td><input type="hidden" id="id_recordId" value=""/></td>
						</tr>
						<tr style="display:none;">
							<td class="td-label">部署编号：</td>
							<td><input type="hidden" id="id_deployId" value=""/></td>
						</tr>
						<tr>
							<td class="td-label" style="width: 120px;">表单标题：</td>
							<td class="td-input" colspan=3><input style="width:400px;border:1px solid silver;" id="id_formTitle"/></td>
						</tr>
						<tr>
							<td class="td-label">表单路径：</td>
							<td colspan=3><input style="width:400px;border:1px solid silver;" type="text" value="" id="id_formPath"/></td>
						</tr>
						<tr>
							<td class="td-label">触发动作：</td>
							<td colspan=3 id="id_activityActionContainer"></td>
						</tr>
						<tr>
							<td class="td-label">附件分类：</td>
							<td colspan=3 id="id_attachmentTypeContainer"></td>
						</tr>
						
						<tr><td class="td-label">提交按钮：</td>
							<td colspan=3>
							<input style="position:relative;top:3px;" value="save" type="checkbox" name="submitButtons" />&nbsp;保存
							<input style="position:relative;top:3px;" value="read" type="checkbox" name="submitButtons" />&nbsp;传阅
							<input style="position:relative;top:3px;" value="assignment" type="checkbox" name="submitButtons" />&nbsp;工作指派
							<input style="position:relative;top:3px;" value="signature" type="checkbox" name="submitButtons" />&nbsp;会签
							<input style="position:relative;top:3px;" value="back" type="checkbox" name="submitButtons" />&nbsp;退回
						</td></tr>
						<tr><td class="td-label">退回路由：</td><td colspan=3 id="id_backRoutesContainer"></td></tr>
						<tr><td class="td-label">意见互斥：</td><td colspan=3 style="text-align:left;padding-left:10px;">是<input type="radio" name="isNeedAdviseExclude" value="1"/>&nbsp;&nbsp;&nbsp;&nbsp;否<input type="radio" name="isNeedAdviseExclude" value="0"/></td></tr>
						<tr><td class="td-label">意见互斥路由：</td><td colspan=3 id="id_excludeMessageActivitiesContainer"></td></tr>
						<tr><td class="td-label">填写意见：</td><td colspan=3 style="text-align:left;padding-left:10px;">是<input type="radio" name="isNeedAdvise" value="1"/>&nbsp;&nbsp;&nbsp;&nbsp;否<input type="radio" name="isNeedAdvise" value="0"/></td></tr>
						<tr><td class="td-label">检查附件：</td><td colspan=3 style="text-align:left;padding-left:10px;">是<input type="radio" name="isAttachmentChecked" value="true"/>&nbsp;&nbsp;&nbsp;&nbsp;否<input type="radio" name="isAttachmentChecked" value="false"/></td></tr>
						<tr><td class="td-label">自动处理：</td><td colspan=3 style="text-align:left;padding-left:10px;">是<input type="radio" name="isAutoActivity" value="1"/>&nbsp;&nbsp;&nbsp;&nbsp;否<input type="radio" name="isAutoActivity" value="0"/></td></tr>
						<tr><td class="td-label">手机审批：</td><td colspan=3 style="text-align:left;padding-left:10px;">是<input type="radio" name="isShowOnApp" value="1"/>&nbsp;&nbsp;&nbsp;&nbsp;否<input type="radio" name="isShowOnApp" value="0"/></td></tr>
					</table>
				</div>
				<div title="待办" style="overflow:hidden;">
					<table width="100%;" style="table-layout:fixed;line-height:20px;text-align: left;">
						<!-- 节点执行人 -->
						<tr>
							<td class="td-label" style="width: 120px;">参与人类型：</td>
							<td class="td-input" id="id_initiatorTypeContainer"></td>
							<td class="td-input">&nbsp;&nbsp;</td>
						</tr>
						<tr class="need-set-value-cls-">
							<td class="td-label">参与人：</td>
							<td class="td-input" colspan=3>
								<textarea id="id_initiator_display" style="width:300px;margin-left:0px;height:50px;overflow:auto;border:1px solid silver;">
								</textarea>
								<textarea id="id_initiator" style="display:none;width:300px;margin-left:0px;height:50px;overflow:auto;border:1px solid silver;">
								</textarea>
							</td>
							<td class="td-input"><a style="display:none;width:80px;margin-left:-35px;" class="mini-button" href="javascript:void(0);" id="id_initiator_button">选择{0}</a></td>
						</tr>
						<tr>
							<td class="td-label">处理方式：</td>
							<td class="td-input" id="id_dealMethodContainer" >
							</td>
							<td class="td-label n-passed-cls" style="display:none" >处理人数：</td><td style="display:none" class="td-input n-passed-cls" ><input type="text" value="" id="id_passedCount"/>
							</td>
						</tr>
						<!-- 待办提醒-->
						<tr>
							<td class="td-label">提醒方式：</td>
							<td colspan=3>
								<!-- --> 
								<input style="position:relative;top:3px;" value="waitToDo" type="checkbox" disabled="disabled" checked/>&nbsp;待办
								<input style="position:relative;top:3px;" value="sms" type="checkbox" name="messageTypes" />&nbsp;短信
								<input style="position:relative;top:3px;" value="mail" type="checkbox" name="messageTypes" />&nbsp;邮件
								<!-- 
								<input style="position:relative;top:3px;" value="stationMessage" type="checkbox" name="messageTypes" />&nbsp;站内信
							-->
							</td>
						</tr>
						<tr>
							<td class="td-label">提醒内容：</td>
							<td class="td-input" colspan=3>
								<textarea id="id_messageContent" name="messageContent" style="width:300px;margin-left:0px;height:50px;overflow:auto;border:1px solid silver;">
								</textarea>
							</td>
						</tr>
					</table>
				</div>
				<div title="条件路由">
					<table width="100%;" style="table-layout:fixed;line-height:20px;text-align: left;">
						<!-- 条件路由"配置 -->
						<tr>
							<td class="td-label" style="width: 120px;">条件路由类型：</td>
							<td class="td-input" id="id_conditionRouteTypeContainer"></td>
							<td class="td-input">&nbsp;&nbsp;</td>
						</tr>
						<tr class="need-set-value-cls-conditionRoute">
							<td class="td-label" >条件路由数值：</td>
							<td class="td-input" colspan=2>
								<textarea value="workflowNextRouteCallBack" id="id_conditionRouteValue" style="width:300px;margin-left:0px;height:50px;overflow:auto;border:1px solid silver;">
								</textarea>
							</td>
						</tr>
						<tr class="need-set-value-cls-conditionRoute-label" style="display:none;">
							<td colspan=3 >
								<div style="text-indent:20px;font-size:12px;"> 
									条件路由数值将被忽略,将会调用页面中的回调函数&nbsp;<font style="color:red">workflowNextRouteCallBack(buttonText,requestNextRoute)</font>&nbsp;<br/>
									通过&nbsp;<font style="color:red">requestNextRoute.value="指定的路由路径"</font>&nbsp;来指定路由路径
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div title="传阅 " style="overflow:hidden;">
					<table width="100%;" style="table-layout:fixed;line-height:20px;text-align: left;">
						<tr>
							<td class="td-label" style="width: 120px;">传阅参与人类型：</td>
							<td class="td-input" id="id_autoReadInitiatorTypeContainer"></td>
							<td class="td-input">&nbsp;&nbsp;</td>
						</tr>
						<tr class="need-set-value-cls-read">
							<td class="td-label" >传阅参与人：</td>
							<td class="td-input" colspan=3>
								<textarea id="id_autoReadInitiator_display" style="width:300px;margin-left:0px;height:50px;overflow:auto;border:1px solid silver;">
								</textarea>
								<textarea id="id_autoReadInitiator" style="display:none;width:300px;margin-left:0px;height:50px;overflow:auto;border:1px solid silver;">
								</textarea>
							</td>
							<td class="td-input"><a style="display:none;width:80px;margin-left:-35px;" class="mini-button" href="javascript:void(0);" id="id_autoReadInitiator_button">选择{0}</a></td>
						</tr>
						<!-- 传阅提醒 -->
						<tr>
							<td class="td-label">提醒方式：</td>
							<td colspan=3>
								<input style="position:relative;top:3px;" value="waitToDo" type="checkbox" disabled="disabled" checked/>&nbsp;待办
								<input style="position:relative;top:3px;" value="sms" type="checkbox" name="readMessageTypes" />&nbsp;短信
								<input style="position:relative;top:3px;" value="mail" type="checkbox" name="readMessageTypes" />&nbsp;邮件
								<!-- 
								<input style="position:relative;top:3px;" value="stationMessage" type="checkbox" name="readMessageTypes" />&nbsp;站内信
							-->
							</td>
						</tr>
						<tr>
							<td class="td-label">提醒内容：</td>
							<td class="td-input" colspan=3>
								<textarea id="id_readMessageContent" name="readMessageContent" style="width:300px;margin-left:0px;height:50px;overflow:auto;border:1px solid silver;">
								</textarea>
							</td>
						</tr>
					</table>
				</div>
				<!----> 
				<div title="会签" style="overflow:hidden;">
					<table width="100%;" style="table-layout:fixed;line-height:20px;text-align: left;">
						<tr>
							<td class="td-label" style="width: 120px;">会签参与人类型：</td>
							<td class="td-input" id="id_autoSignatureInitiatorTypeContainer" >
							</td>
							<td class="td-input">&nbsp;&nbsp;</td>
						</tr>
						<tr class="need-set-value-cls-signature">
							<td class="td-label">会签参与人：</td>
							<td class="td-input" colspan=3>
								<textarea id="id_autoSignatureInitiator_display" style="width:300px;margin-left:0px;height:50px;overflow:auto;border:1px solid silver;">
								</textarea>
								<textarea id="id_autoSignatureInitiator" style="display:none;width:300px;margin-left:0px;height:50px;overflow:auto;border:1px solid silver;">
								</textarea>
							</td>
							<td class="td-input"><a style="display:none;width:80px;margin-left:-35px;" class="mini-button" href="javascript:void(0);" id="id_autoSignatureInitiator_button">选择{0}</a></td>
						</tr>
						<!-- 会签提醒-->
						<tr>
							<td class="td-label">提醒方式：</td>
							<td colspan=3>
								<input style="position:relative;top:3px;" value="waitToDo" type="checkbox" disabled="disabled" checked/>&nbsp;待办
								<input style="position:relative;top:3px;" value="sms" type="checkbox" name="signatureMessageTypes" />&nbsp;短信
								<input style="position:relative;top:3px;" value="mail" type="checkbox" name="signatureMessageTypes" />&nbsp;邮件
								<!-- 
								<input style="position:relative;top:3px;" value="stationMessage" type="checkbox" name="signatureMessageTypes" />&nbsp;站内信
							-->
							</td>
						</tr>
						<tr>
							<td class="td-label">提醒内容：</td>
							<td class="td-input" colspan=3>
								<textarea id="id_signatureMessageContent" name="signatureMessageContent" style="width:300px;margin-left:0px;height:50px;overflow:auto;border:1px solid silver;">
								</textarea>
							</td>
						</tr> 
					</table>
				</div>
				
				<!--
				<div title="待办提醒" style="overflow:hidden;">
					<table width="100%;" style="table-layout:fixed;line-height:20px;text-align: left;">
						<tr>
							<td class="td-label" style="width: 120px;">信息类型：</td>
							<td colspan=3>
								<input style="position:relative;top:3px;" value="waitToDo" type="checkbox" disabled="disabled" checked/>&nbsp;待办
								<input style="position:relative;top:3px;" value="sms" type="checkbox" name="messageTypes" />&nbsp;短信
								<input style="position:relative;top:3px;" value="mail" type="checkbox" name="messageTypes" />&nbsp;邮件
								<input style="position:relative;top:3px;" value="stationMessage" type="checkbox" name="messageTypes" />&nbsp;站内信
							</td>
						</tr>
						<tr>
							<td class="td-label">信息内容：</td>
							<td class="td-input" colspan=3>
								<textarea id="id_messageContent" name="messageContent" style="width:300px;margin-left:0px;height:50px;overflow:auto;border:1px solid silver;">
								</textarea>
							</td>
						</tr>
					</table>
				</div>
				-->
			</div>
		</form>
		<table width="100%;">
			<tr style="text-align:center;">
				<td style="padding: 10px;">
					<a class="mini-button" onclick="saveChangeToActivity">保存</a>
					<a class="mini-button" onclick="__saveChangeToActivityClose">取消</a>
				</td>
			</tr>
		</table>
	</div>
	
	<script type="text/javascript">
		$(function(){
			mini.parse(document.body);
		});
		
		function workflowActivitiesSetting(rowData) {
			globalDesignerId = rowData.id;
			var table_activities = mini.get('id_workflow_activity_detail_table');
			table_activities.setParams(
				{
					deployId: rowData.deployid/* ,
					TableRemoteSortField:"(CASE ACTIVITYTYPE WHEN 'start' THEN '00' WHEN 'end' THEN '99' ELSE '10' END),ACTIVITYNAME" */
				}
			);
			table_activities.reload();
			mini.get("id_workflow_setting").show();
		}
		
		
		function workflowInfoSetting(rowData,deployId,deployDesc) {
			globalDesignerId = rowData.id;
			mini.get("id_workflow_info_setting").show();
		}
		
		
		function workflowKeySetting(rowData) {
			globalDesignerId = rowData.id;
			var keyOne = rowData['keyone'];
			var keyTwo = rowData['keytwo'];
			var keyThree = rowData['keythree'];
			var keyFour = rowData['keyfour'];
			var keyFive = rowData['keyfive'];
			var keySix = rowData['keysix'];
			var keySeven = rowData['keyseven'];
			var keyEight = rowData['keyeight'];
			var keyNine = rowData['keynine'];
			var keyTen = rowData['keyten'];
			$("#id_keyOne").val(keyOne);
			$("#id_keyTwo").val(keyTwo);
			$("#id_keyThree").val(keyThree);
			$("#id_keyFour").val(keyFour);
			$("#id_keyFive").val(keyFive);
			$("#id_keySix").val(keySix);
			$("#id_keySeven").val(keySeven);
			$("#id_keyEight").val(keyEight);
			$("#id_keyNine").val(keyNine);
			$("#id_keyTen").val(keyTen);
			mini.get("id_workflow_key_setting").show();
		}
		
		
		function workflowAttachmentSetting(rowData) {
			globalDesignerId = rowData.id;
			var instance_ids_ = rowData.instance_ids_;
			getTracywindyObject('id_relatedInstance_combo').setValue(instance_ids_);
			mini.get("id_workflow_attachment_setting").show();
		}
		
		
		
		function __saveRalatedAttachmentToWorkflowClose() {
			mini.get('id_workflow_attachment_setting').hide();
		}
		
		function saveRalatedAttachmentToWorkflow() {
			var instanceids =getTracywindyObject('id_relatedInstance_combo').getValue();
			mini.mask({ el: document.body, cls: 'mini-mask-loading', html: '加载中...' });
			var url = "${pageContext.request.contextPath}/jbpm/saveRalatedAttachmentToWorkflow.action";
			tenwa.ajaxRequest({
				url:url,
				type:'POST',
				params:{
					designerId:globalDesignerId,
					instanceids:instanceids
				},
				success:function(data){
					mini.unmask(document.body);
					mini.alert("附件关联设置成功",'提示',function(){
						var workflowsTable = mini.get("id_workflows_table");
						workflowsTable.setParams({
							queryText:'${param.queryText}'
						});
						workflowsTable.reload();
					});
					mini.get('id_workflow_attachment_setting').hide();
				},
				failure:function(data){
					mini.unmask(document.body);
				}
			});
		}
		
		function __saveKeyToWorkflowClose() {
			mini.get('id_workflow_key_setting').hide();
		}
		
		function saveKeyToWorkflow() {
			var keyOne = $("#id_keyOne").val();
			var keyTwo = $("#id_keyTwo").val();
			var keyThree = $("#id_keyThree").val();
			var keyFour = $("#id_keyFour").val();
			var keyFive = $("#id_keyFive").val();
			var keySix = $("#id_keySix").val();
			var keySeven = $("#id_keySeven").val();
			var keyEight = $("#id_keyEight").val();
			var keyNine = $("#id_keyNine").val();
			var keyTen = $("#id_keyTen").val();
			mini.mask({ el: document.body, cls: 'mini-mask-loading', html: '加载中...' });
			var url = "${pageContext.request.contextPath}/jbpm/saveKeyToWorkflow.action";
			tenwa.ajaxRequest({
				url:url,
				type:'POST',
				params:{
					designerId:globalDesignerId,
					keyOne:keyOne,
					keyTwo:keyTwo,
					keyThree:keyThree,
					keyFour:keyFour,
					keyFive:keyFive,
					keySix:keySix,
					keySeven:keySeven,
					keyEight:keyEight,
					keyNine:keyNine,
					keyTen:keyTen
				},
				success:function(data){
					mini.unmask(document.body);
					mini.alert("关键字设置成功",'提示',function(){
						var workflowsTable = mini.get("id_workflows_table");
						workflowsTable.setParams({
							queryText:'${param.queryText}'
						});
						workflowsTable.reload();
					});
					mini.get('id_workflow_key_setting').hide();
				},
				failure:function(data){
					mini.unmask(document.body);
				}
			});
		}
		
		
		
		function startProcess(processDefinitionId) {
			mini.confirm("确认发起新流程?",'',function(action) {
				if(action == 'ok'){
					window.open("${pageContext.request.contextPath}/jbpm/startDeployedProcess.action?processDefinitionId="+escape(encodeURIComponent(processDefinitionId)),"_blank");
				}
			});
		}
		
		
		function workflowSynchronized(deployId) {
			mini.mask({ el: document.body, cls: 'mini-mask-loading', html: '加载中...' });
			var url = "${pageContext.request.contextPath}/jbpm/workflowSynchronized.action";
			tenwa.ajaxRequest({
				url:url,
				type:'POST',
				params:{
					deployId:deployId
				},
				success:function(res){
					mini.unmask(document.body);
					mini.alert(res.responseText);
					mini.get("id_workflows_table").reload();
				}
			});
		}
	</script>
	<script type="text/javascript">
		function getChineseActorType(actorType) {
			var chineseActorType = "";
			switch(actorType){
				case 'requestInitiator': { chineseActorType = '流程发起人';break;}
				case 'user': { chineseActorType = '指定人员';break;}
				case 'requestInitiatorRelation': { chineseActorType = '发起人关系';break;}
				case 'submitRelation': { chineseActorType = '提交人关系';break;}
				case  'requestInitiatorDefinedRelation':{chineseActorType='预定义的发起人关系';break;}
                case  'submitDefinedRelation': { chineseActorType = '预定义的提交人关系';break;}
				case 'dept': { chineseActorType = '部门';break;}
				case 'deptRole': { chineseActorType = '角色';break;}
				case 'group': { chineseActorType = '群组';break;}
				case 'step': { chineseActorType = '历史步骤';break;}
				case 'formField': { chineseActorType = '表单域';break;}
				case 'sql': { chineseActorType = '自定义';break;}
				default:{chineseActorType = "";}
			}
			return chineseActorType;
		}
		
		function getChineseConditionRouteType(conditionRouteType){
				var value = "";
				switch(conditionRouteType){
					case 'pageCallBack' : { value = "页面回调函数";break;}
					case 'field': { value = "表单域";break;}
					case 'expression': { value = "表达式";break;}
					case 'sql': { value = "自定义";break;}
					default:{
						value = "";
					}
				}
				return value;
		}
		
		function renderActorValue(actorType,displayValue,hiddenValue){
			var value = "";
			switch(actorType){
				case 'formField': 
				case 'sql': { value = hiddenValue;break;}
				default:{
					value = displayValue;
				}
			}
			return value;
		}
		
		jQuery(function(){
			tenwa.createTable({
				renderTo:'id_workflow_activity_detail',
				displayToggleContainer:'id_workflow_setting',
				width:810,
				height:380,
				id:'id_workflow_activity_detail_table',
				checkType:'checkbox',
				xmlFileName:'\\jbpm\\queryWorkflowActivities.xml',
				loadMode:'ajax',
				multiSelect:false,
				params:{deployId:'-1'},
				frozenStartColumn:2,
				frozenEndColumn:2,
				pageSize:9999,
				tools:[ {
		    	    html:'修改',
					iconCls:'icon-edit',
					handler:function(miniTable, buttonText){
						var selectedRowDatas = miniTable.getSelecteds();
						if(selectedRowDatas.length == 1) {
							modifyActivity(selectedRowDatas[0]);
						} else {
							mini.alert('请选择您好修改的数据。');
						}
					}
		        } ],
				columns:[
					{type : 'indexcolumn' },
					{type : 'checkcolumn' },
					{header:'记录编号', field:'id', visible : false},
					//{header:'部署编号',field:'deploymentimpl',visible:false},
					{header:'流程名称',field:'displayworkflowname'},
					{header:'流程关键字',field:'workflowname'},
					{header:'节点类型',field:'activitytype'},
					{header:'节点名称',field:'activityname'},
					//{header:'是否检查附件',field:'isattachmentchecked',visible:false},
					{header:'是否检查附件',field:'isattachmentcheckeddesc'},
					{header:'参与人类型',field:'actortype',width:160,renderer:
						function(e){
							var rowData = e.record;
							var actorType = rowData['actortype'];
							var chineseActorType = getChineseActorType(actorType);
							return chineseActorType;
						}
					},
					{header:'参与人',field:'actorvalue',visible : false},
					{header:'参与人',field:'displayactorvalue',renderer:
						function(e){
							var rowData = e.record;
							var actorType = rowData['actortype'];
							var displayValue = rowData['displayactorvalue'];
							var hiddenValue = rowData['actorvalue'];
							return renderActorValue(actorType,displayValue,hiddenValue);
						}
					},
					{header:'条件路由类型',field:'conditionroutetype',renderer:
						function(e){
							var rowData = e.record;
							if(("start" == rowData.activitytype) || ("end" == rowData.activitytype)){
								return "";
							}
							var conditionRouteType = rowData['conditionroutetype'];
							var chineseActorType = getChineseConditionRouteType(conditionRouteType);
							return chineseActorType;
						}
					},
					{header:'条件路由数值',field:'conditionroutevalue'},
					{header:'参与人信息类型',field:'messagetypes',renderer:
						function(e){
							var chineseValues = [];
							var value = e.record.messagetypes;
							if(value){
								var values = value.split(",");
								for(var i= 0;i < values.length;i++){
									var englishValue = values[i];
									var chineseValue = "";
									switch(englishValue){
										case "sms":{
											chineseValue = "短信";
											break;
										}
										case "mail":{
											chineseValue = "邮件";
											break;
										}
										case "stationMessage":{
											chineseValue = "站内信";
											break;
										}
									}
									chineseValues.push(chineseValue);
								}
							}
							return chineseValues.join(",");
						}
					},
					{header:'参与人发送内容',field:'messagecontent'},
					{header:'条件路由数值',field:'conditionroutevalue',renderer:
						function(e){
							var rowData = e.record;
							if(("start" == rowData.activitytype) || ("end" == rowData.activitytype)){
								return "";
							}
							var conditionRouteValue = rowData['conditionroutevalue'];
							var conditionRouteType = rowData['conditionroutetype'];
							if("pageCallBack" == conditionRouteType){
								return "workflowNextRouteCallBack(buttonText,requestNextRoute)";
							}
							return conditionRouteValue;
						}
					},
					{header:'传阅参与人类型',field:'autoreadactortype',width:160,renderer:
						function(e){
							var rowData = e.record;
							var actorType = rowData['autoreadactortype'];
							var chineseActorType = getChineseActorType(actorType);
							return chineseActorType;
						}
					},
					{header:'传阅参与人',field:'autoreadactorvalue',visible : false},
					{header:'传阅参与人',field:'autoreaddisplayactorvalue',width:160,renderer:
						function(e){
							var rowData = e.record;
							var actorType = rowData['autoreadactortype'];
							var displayValue = rowData['autoreaddisplayactorvalue'];
							var hiddenValue = rowData['autoreadactorvalue'];
							return renderActorValue(actorType,displayValue,hiddenValue);
						}
					},
					{header:'传阅人信息类型',field:'readmessagetypes',renderer:
						function(e){
							var chineseValues = [];
							var value = e.record.readmessagetypes;
							if(value){
								var values = value.split(",");
								for(var i= 0;i < values.length;i++){
									var englishValue = values[i];
									var chineseValue = "";
									switch(englishValue){
										case "sms":{
											chineseValue = "短信";
											break;
										}
										case "mail":{
											chineseValue = "邮件";
											break;
										}
										case "stationMessage":{
											chineseValue = "站内信";
											break;
										}
									}
									chineseValues.push(chineseValue);
								}
							}
							return chineseValues.join(",");
						}
					},
					{header:'传阅人发送内容',field:'readmessagecontent'},
					{header:'会签参与人类型',field:'autosignatureactortype',width:160,renderer:
						function(e){
							var rowData = e.record;
							var actorType = rowData['autosignatureactortype'];
							var chineseActorType = getChineseActorType(actorType);
							return chineseActorType;
						}
					},
					{header:'会签参与人',field:'autosignatureactorvalue',visible : false},
					{header:'会签参与人',field:'autosignaturedisplayactorvalue',width:160,renderer:
						function(e){
							var rowData = e.record;
							var actorType = rowData['autosignatureactortype'];
							var displayValue = rowData['autosignaturedisplayactorvalue'];
							var hiddenValue = rowData['autosignatureactorvalue'];
							return renderActorValue(actorType,displayValue,hiddenValue);
						}
					},
					{header:'会签人信息类型',field:'signaturemessagetypes',renderer:
						function(e){
							var chineseValues = [];
							var value = e.record.signaturemessagetypes;
							if(value){
								var values = value.split(",");
								for(var i= 0;i < values.length;i++){
									var englishValue = values[i];
									var chineseValue = "";
									switch(englishValue){
										case "sms":{
											chineseValue = "短信";
											break;
										}
										case "mail":{
											chineseValue = "邮件";
											break;
										}
										case "stationMessage":{
											chineseValue = "站内信";
											break;
										}
									}
									chineseValues.push(chineseValue);
								}
							}
							return chineseValues.join(",");
						}
					},
					{header:'会签人发送内容',field:'signaturemessagecontent'},
					{header:'处理方式',field:'dealmethod',renderer:
						function(e){
							var dealMethod = e.record.dealmethod;
							var dealMethodChineseName = "";
							if("Single"==(dealMethod)){dealMethodChineseName="单人处理";};
							if("AllPassed"==(dealMethod)){dealMethodChineseName="多人全部";};
							if("OnePassed"==(dealMethod)){dealMethodChineseName="多人任一";};
							if("NPassed"==(dealMethod)){dealMethodChineseName="N人处理";};
							return dealMethodChineseName;
						}
					},
					{header:'通过人数',field:'passedcount'},
					{header:'应用表单',field:'formpath'},
					{header:'表单标题',field:'formtitle'},
					{header:'是否填写意见',field:'isneedadvise',renderer:
						function(e){
							var realValue = "";
							if("1" == e.record.isneedadvise){realValue="是";}
							else if("0" == e.record.isneedadvise){realValue="否";}
							return realValue;
						}
					},
					{header:'手机审批',field:'isshowonapp',renderer:
						function(e){
							var realValue = "";
							if("1" == e.record.isshowonapp){realValue="是";}
							else if("0" == e.record.isshowonapp){realValue="否";}
							return realValue;
						}
					},
					{header:'是否自动节点',field:'isautoactivity',renderer:
						function(e){
							var realValue = "";
							if("1" == e.record.isautoactivity){realValue="是";}
							else if("0" == e.record.isautoactivity){realValue="否";}
							return realValue;
						}
					},
					{header:'操作按钮',field:'operationbuttons'},
					{header:'节点动作',field:'activityaction'},
					{header:'附件类型',field:'attachmenttype'},
					{header:'附件类型编号',field:'attachmenttypeids',visible : false},
					{header:'退回路由节点',field:'backactivities'},
					{header:'是否意见互斥',field:'isneedadviseexcludedesc'},
					{header:'意见互斥路由节点',field:'excludemessageactivitiesdesc'}
				]
			});
			//选择action
			new tracywindyComboBox({
				dropListHeight:200,
				id:'combo_activityAction',
				width:352,
				renderTo:'id_activityActionContainer',
				url:'${pageContext.request.contextPath}/jbpm/getWorkflowActions.action',
				loadMode:'ajax',
				readOnly:true,
				value:'',
				displayField:'name',
				valueField:'value',
				params:{
					paramWorkflowName:'-1'
				}
			}); 
			//选择附件分类
			new tracywindyComboBox({
				dropListHeight:200,
				id:'combo_attachmentType',
				width:352,
				renderTo:'id_attachmentTypeContainer',
				xmlFileName:'\\jbpm\\queryAttachmentTypes.xml',
				loadMode:'ajax',
				readOnly:true,
				isCheck:true,
				value:'',
				displayField:'name',
				valueField:'value'
			}); 
			//选择退后路由 
			new tracywindyComboBox({
				dropListHeight:200,
				id:'combo_backRoutes',
				width:352,
				renderTo:'id_backRoutesContainer',
				xmlFileName:'\\jbpm\\queryWorkflowActivities.xml',
				loadMode:'ajax',
				readOnly:true,
				isCheck:true,
				value:'',
				displayField:'activityname',
				valueField:'activityname',
				params:{
					deployId:'-1'
				}
			});
			//选择退后路由 
			new tracywindyComboBox({
				dropListHeight:200,
				id:'combo_excludeMessageActivities',
				width:352,
				renderTo:'id_excludeMessageActivitiesContainer',
				xmlFileName:'\\jbpm\\queryWorkflowActivities.xml',
				loadMode:'ajax',
				readOnly:true,
				isCheck:true,
				value:'',
				displayField:'activityname',
				valueField:'id',
				params:{
					deployId:'-1'
				}
			});
			
			
			//选择处理方式
			new tracywindyComboBox({
				dropListHeight:200,
				id:'combo_dealMethod',
				width:160,
				renderTo:'id_dealMethodContainer',
				loadMode:'local',
				readOnly:true,
				isContainEmpty:false,
				datas:[
					{ name:'单人处理',value:'Single'},
					{ name:'多人全部',value:'AllPassed'},
					{ name:'多人任一',value:'OnePassed'},
					{ name:'N人处理',value:'NPassed'}
				],
				value:'',
				displayField:'name',
				valueField:'value',
				onSelect:function(combo,rowData){
					var value = combo.getValue();
					if("NPassed" == value){
						$("td.n-passed-cls").show();
					}else{
						$("#id_passedCount").val("");
						$("td.n-passed-cls").hide();
					}
				}
			}); 
			//条件路由
			new tracywindyComboBox({
				dropListHeight:200,
				id:'combo_conditionRouteType',
				width:160,
				renderTo:'id_conditionRouteTypeContainer',
				loadMode:'local',
				readOnly:true,
				//isContainEmpty:false,
				datas:[
					{ name:'页面回调函数',value:'pageCallBack'},
					{ name:'表单域',value:'field'},
					{ name:'表达式',value:'expression'},
					{ name:'自定义',value:'sql'}
				],
				value:'pageCallBack',
				displayField:'name',
				valueField:'value',
				onSelect:function(combo,rowData){
					var conditionRouteType = combo.getValue();
					if("pageCallBack" == conditionRouteType){
						jQuery(".need-set-value-cls-conditionRoute").hide();
						jQuery(".need-set-value-cls-conditionRoute-label").show();
					} else {
						jQuery(".need-set-value-cls-conditionRoute").show();
						jQuery(".need-set-value-cls-conditionRoute-label").hide();
					}
				}
			}); 
			//选择参与人
			initNodeContainer("id_initiatorTypeContainer","id_initiator_display","id_initiator","id_initiator_button","");
			//选择结束时传阅参与人类型
			initNodeContainer("id_autoReadInitiatorTypeContainer","id_autoReadInitiator_display","id_autoReadInitiator","id_autoReadInitiator_button","read");
			//选择结束时会签参与人类型 
			initNodeContainer("id_autoSignatureInitiatorTypeContainer","id_autoSignatureInitiator_display","id_autoSignatureInitiator","id_autoSignatureInitiator_button","signature");
			
			jQuery("input[type='radio'][name='isNeedAdviseExclude']").bind('click',function(){
				var combo_excludeMessageActivities = getTracywindyObject('combo_excludeMessageActivities');
				if(this.value == '0'){
					combo_excludeMessageActivities.setValue('');
					combo_excludeMessageActivities.readOnlyData = true;
					combo_excludeMessageActivities.reload();
				}else{
					combo_excludeMessageActivities.readOnlyData = false;
					combo_excludeMessageActivities.reload();
				}
			});
		});
		
		
		function initNodeContainer(initiatorTypeContainer,initiatorDisplayId,initiatorHiddenId,choseButtonId,attachmentId) {
			new tracywindyCommonUserSelection({
				hiddenInput:initiatorHiddenId||'id_initiator_display',
				displayInput:initiatorDisplayId||'id_initiator',
				isMultiSelect:true,
				draggable:false,
				windowTop:1,
				id:'id_user'+attachmentId,
				type:'user'
			});
			new tracywindyCommonUserSelection({
				hiddenInput:initiatorHiddenId||'id_initiator_display',
				displayInput:initiatorDisplayId||'id_initiator',
				isMultiSelect:true,
				draggable:false,
				windowTop:1,
				id:'id_requestInitiatorRelation'+attachmentId,
				type:'relation'
			});
			new tracywindyCommonUserSelection({
				hiddenInput:initiatorHiddenId||'id_initiator_display',
				displayInput:initiatorDisplayId||'id_initiator',
				isMultiSelect:true,
				draggable:false,
				windowTop:1,
				id:'id_submitRelation'+attachmentId,
				type:'relation'
			});
			//关系2，从已定义的关系的中选择
            new tracywindyCommonUserSelection({
                 hiddenInput:initiatorHiddenId||'id_initiator_display',
                 displayInput:initiatorDisplayId||'id_initiator',
                 isMultiSelect:true,
                 draggable:false,
                 windowTop:1,
                 id:'id_requestInitiatorDefinedRelation'+attachmentId,
                 type:'definedRelation'
            });
           new tracywindyCommonUserSelection({
                 hiddenInput:initiatorHiddenId||'id_initiator_display',
                 displayInput:initiatorDisplayId||'id_initiator',
                 isMultiSelect:true,
                 draggable:false,
                 windowTop:1,
                 id:'id_submitDefinedRelation'+attachmentId,
                 type:'definedRelation'
           });
			new tracywindyCommonUserSelection({
				hiddenInput:initiatorHiddenId||'id_initiator_display',
				displayInput:initiatorDisplayId||'id_initiator',
				isMultiSelect:true,
				draggable:false,
				windowTop:1,
				id:'id_dept'+attachmentId,
				type:'dept'
			});
			new tracywindyCommonUserSelection({
				hiddenInput:initiatorHiddenId||'id_initiator_display',
				displayInput:initiatorDisplayId||'id_initiator',
				isMultiSelect:true,
				draggable:false,
				windowTop:1,
				id:'id_deptRole'+attachmentId,
				type:'deptRole'
			});
			new tracywindyCommonUserSelection({
				hiddenInput:initiatorHiddenId||'id_initiator_display',
				displayInput:initiatorDisplayId||'id_initiator',
				isMultiSelect:true,
				draggable:false,
				windowTop:1,
				id:'id_group'+attachmentId,
				type:'group'
			});
			new tracywindyCommonUserSelection({
				hiddenInput:initiatorHiddenId||'id_initiator_display',
				displayInput:initiatorDisplayId||'id_initiator',
				isMultiSelect:true,
				draggable:false,
				windowTop:1,
				id:'id_step'+attachmentId,
				type:'step',
				params:{
					deployId:'-1',
					currentActivityDetailId:'-1'
				}
			});
			
			//选择节点参与人类型
			new tracywindyComboBox({
				dropListHeight:280,
				id:'combo_initiatorType'+attachmentId,
				width:160,
				renderTo:initiatorTypeContainer||'id_initiatorTypeContainer',
				loadMode:'local',
				readOnly:true,
				isContainEmpty:!!attachmentId,
				datas:[
					{ name:'流程发起人',value:'requestInitiator'},
					{ name:'指定人员',value:'user'},
					{ name:'发起人关系',value:'requestInitiatorRelation'},
					{ name:'提交人关系',value:'submitRelation'},
					{ name:'预定义的发起人关系', value:'requestInitiatorDefinedRelation'},
                    { name:'预定义的提交人关系', value:'submitDefinedRelation'},
					{ name:'部门',value:'dept'},
					{ name:'角色',value:'deptRole'},
					{ name:'群组',value:'group'},
					{ name:'历史步骤',value:'step'},
					{ name:'表单域',value:'formField'},
					{ name:'自定义',value:'sql'}
				],
				value:'',
				displayField:'name',
				valueField:'value',
				onSelect:function(combo,rowData){
					var value = combo.getValue();
					var rawValue = combo.getRawValue();
					$("#"+initiatorHiddenId).val("");
					$("#"+initiatorDisplayId).val("");
					initNodeInitiator(value,rawValue,initiatorTypeContainer,initiatorDisplayId,initiatorHiddenId,choseButtonId,attachmentId);
				}
			}); 
		}
		function initNodeInitiator(value,rawValue,initiatorTypeContainer,initiatorDisplayId,initiatorHiddenId,choseButtonId,attachmentId){
			var $choseButton = $("#"+(choseButtonId||"id_initiator_button"));
			switch(value){
				case "requestInitiator":
				case "user":
				case "requestInitiatorRelation":
				case "submitRelation":
				case "requestInitiatorDefinedRelation":
                case "submitDefinedRelation":
				case "dept":
				case "deptRole":
				case "group":
				case "step":{
					$("#"+initiatorHiddenId).hide();
					$("#"+initiatorDisplayId).attr("readOnly",true);
					$("#"+initiatorDisplayId).show();
					$("tr.need-set-value-cls-"+attachmentId).show();
					if("requestInitiator"==value){
						$("tr.need-set-value-cls-"+attachmentId).hide();
					}
					$choseButton.show();
					$choseButton.html("选择 "+rawValue);
					$choseButton[0].onclick=(function(value){
						return function(e){
							getTracywindyObject('id_'+value+attachmentId).show();
						};
					})(value);
					break;
				}
				default:{
					$("tr.need-set-value-cls-"+attachmentId).show();
					$("#"+initiatorHiddenId).show();
					$("#"+initiatorDisplayId).attr("readOnly",false);
					$("#"+initiatorDisplayId).hide();
					$choseButton.hide();
				}
			}
		}
		
		
		function modifyActivity(rowData) {
			var id = rowData['id'];
			var deployid = rowData['deploymentimpl'];
			var workflowName = rowData['workflowname'];
			var activityType = rowData['activitytype'];
			var activityName = rowData['activityname'];
			//var initiatorType = rowData['actortype'];
			//执行人
			var initiator = (rowData['actorvalue']||"").replace(/\\n/g,"\r\n");
			var displayInitiator = rowData['displayactorvalue'];
			var initiatorType = rowData['actortype'];
			//自动传阅
			var autoSignatureInitiator = (rowData['autosignatureactorvalue']||"").replace(/\\n/g,"\r\n");
			var autoSignatureDisplayInitiator = rowData['autosignaturedisplayactorvalue'];
			var autoSignatureInitiatorType = rowData['autosignatureactortype'];
			//条件路由
			var conditionRouteType = rowData['conditionroutetype'];
			var conditionRouteValue = rowData['conditionroutevalue'];
			//信息
			var messageTypes = rowData['messagetypes'];
			var messageContent = rowData['messagecontent'];
			var readMessageTypes = rowData['readmessagetypes'];
			var readMessageContent = rowData['readmessagecontent'];
			var signatureMessageTypes = rowData['signaturemessagetypes'];
			var signatureMessageContent = rowData['signaturemessagecontent'];
			
			//自动会签
			var autoReadInitiator = (rowData['autoreadactorvalue']||"").replace(/\\n/g,"\r\n");
			var autoReadDisplayInitiator = rowData['autoreaddisplayactorvalue'];
			var autoReadInitiatorType = rowData['autoreadactortype'];
			//表单
			var formPath = rowData['formpath'];
			var formTitle = rowData['formtitle'];
			//Action
			var activityAction = rowData['activityaction'];
			//附件
			var attachmentType = rowData['attachmenttype'];
			//提交按钮
			var submitButtons = rowData['operationbuttons'];
			//是否需要填写意见
			var isNeedAdvise = rowData['isneedadvise'];
			//是否在手机上审批
			var isShowOnApp = rowData['isshowonapp'];
			//是否意见互斥
			var isNeedAdviseExclude = rowData['isneedadviseexclude'];
			//手否检查附件
			var isAttachmentChecked = rowData['isattachmentchecked'];
			//是否是自动节点
			var isAutoActivity = rowData['isautoactivity'];
			//退后按钮
			var backRoutesNodes = rowData['backactivities'];
			//节点审批意见互斥
			var excludeMessageActivitiesNodes = rowData['excludemessageactivitiesdesc'];
			//处理方式
			var dealMethod = rowData['dealmethod'];
			var passedCount = rowData['passedcount'];
			
			var currentCheckedRadio = jQuery("input[type='radio'][name='isNeedAdvise'][value='"+isNeedAdvise+"']")[0];
			if(currentCheckedRadio) {
				currentCheckedRadio.checked = true;
			} else {
				jQuery("input[type='radio'][name='isNeedAdvise']")[1].checked=true;
			}
			
			var currentCheckedRadioApp = jQuery("input[type='radio'][name='isShowOnApp'][value='"+isShowOnApp+"']")[0];
			if(currentCheckedRadioApp) {
				currentCheckedRadioApp.checked = true;
			} else {
				jQuery("input[type='radio'][name='isShowOnApp']")[1].checked=true;
			}
			
			var currentCheckedRadio2 = jQuery("input[type='radio'][name='isNeedAdviseExclude'][value='"+isNeedAdviseExclude+"']")[0];
			if(currentCheckedRadio2) {
				currentCheckedRadio2.checked = true;
			} else {
				jQuery("input[type='radio'][name='isNeedAdviseExclude']")[1].checked=true;
			}
			
			
			var currentCheckedAttachmentRadio = jQuery("input[type='radio'][name='isAttachmentChecked'][value='"+isAttachmentChecked+"']")[0];
			if(currentCheckedAttachmentRadio) {
				currentCheckedAttachmentRadio.checked = true;
			} else {
				jQuery("input[type='radio'][name='isAttachmentChecked']")[1].checked=true;
			}
			
			var currentCheckedAutoActivityRadio = jQuery("input[type='radio'][name='isAutoActivity'][value='"+isAutoActivity+"']")[0];
			if(currentCheckedAutoActivityRadio) {
				currentCheckedAutoActivityRadio.checked = true;
			} else {
				jQuery("input[type='radio'][name='isAutoActivity']")[1].checked=true;
			}
			
			jQuery("#id_recordId").val(id);
			jQuery("#id_deployId").val(deployid);
			jQuery("#id_activityType").html(activityType);
			jQuery("#id_activityName").html(activityName);
			//参与人
			jQuery("#id_initiator").val(initiator);
			jQuery("#id_initiator_display").val(displayInitiator);
			//条件路由
			jQuery("#id_conditionRouteValue").val(conditionRouteValue);
			//信息
			jQuery("#id_messageContent").val(messageContent);
			setMessageTypes(messageTypes);
			jQuery("#id_readMessageContent").val(readMessageContent);
			setReadMessageTypes(readMessageTypes);
			jQuery("#id_signatureMessageContent").val(signatureMessageContent);
			setSignatureMessageTypes(signatureMessageTypes);
			//自动会签
			jQuery("#id_autoSignatureInitiator").val(autoSignatureInitiator);
			jQuery("#id_autoSignatureInitiator_display").val(autoSignatureDisplayInitiator);
			//自动传阅
			jQuery("#id_autoReadInitiator").val(autoReadInitiator);
			jQuery("#id_autoReadInitiator_display").val(autoReadDisplayInitiator);

			//表单
			jQuery("#id_formPath").val(formPath);
			jQuery("#id_formTitle").val(formTitle);
			setOperationButtons(submitButtons);
			//设置处理方式
			jQuery("#id_passedCount").val(passedCount);
			
			var combo_activityAction = getTracywindyObject('combo_activityAction');
			var combo_attachmentType = getTracywindyObject('combo_attachmentType');
			var combo_backRoutes = getTracywindyObject('combo_backRoutes');
			var combo_excludeMessageActivities = getTracywindyObject('combo_excludeMessageActivities');
			
			var combo_initiatorType = getTracywindyObject('combo_initiatorType');
			var combo_autoReadInitiatorType = getTracywindyObject('combo_initiatorTyperead');
			var combo_autoSignatureInitiatorType = getTracywindyObject('combo_initiatorTypesignature');
			var combo_dealMethod = getTracywindyObject('combo_dealMethod');
			
			//条件路由
			var combo_conditionRouteType = getTracywindyObject('combo_conditionRouteType');
			//信息
			if(("start"==activityType)||(("end"==activityType))) {
				jQuery("#id_initiator_display")[0].disabled = true;
				jQuery("#id_autoSignatureInitiator")[0].disabled = true;
				jQuery("#id_autoSignatureInitiator_display")[0].disabled = true;
				if("end"==activityType){
					jQuery("#id_autoReadInitiator")[0].disabled = false;
					jQuery("#id_autoReadInitiator_display")[0].disabled = false;
				}else{
					jQuery("#id_autoReadInitiator")[0].disabled = true;
					jQuery("#id_autoReadInitiator_display")[0].disabled = true;
				}
				jQuery("#id_formPath")[0].disabled = true;
				jQuery("#id_formTitle")[0].disabled = true;
				jQuery("#id_passedCount")[0].disabled = true;
				jQuery("input[name='submitButtons']").attr("disabled","disabled");
				jQuery("input[type='radio'][name='isNeedAdvise']").attr("disabled","disabled");
				jQuery("input[type='radio'][name='isShowOnApp']").attr("disabled","disabled");
				jQuery("input[type='radio'][name='isNeedAdviseExclude']").attr("disabled","disabled");
				jQuery("input[type='radio'][name='isAttachmentChecked']").attr("disabled","disabled");
				jQuery("input[type='radio'][name='isAutoActivity']").attr("disabled","disabled");
				//信息
				jQuery("#id_messageContent")[0].disabled = true;
				jQuery("input[name='messageTypes']").attr("disabled","disabled");
				jQuery("#id_readMessageContent")[0].disabled = true;
				jQuery("input[name='readMessageTypes']").attr("disabled","disabled");
				jQuery("#id_signatureMessageContent")[0].disabled = true;
				jQuery("input[name='signatureMessageTypes']").attr("disabled","disabled");
				
				combo_attachmentType.readOnlyData = true;
				combo_backRoutes.readOnlyData = true;
				combo_excludeMessageActivities.readOnlyData = true;
				combo_initiatorType.readOnlyData = true;
				if("end"==activityType){
					combo_autoReadInitiatorType.readOnlyData = false;
				} else {
					combo_autoReadInitiatorType.readOnlyData = true;
				}
				combo_autoSignatureInitiatorType.readOnlyData = true;
				combo_dealMethod.readOnlyData = true;
				combo_conditionRouteType.readOnlyData = true;
			} else {
				jQuery("#id_initiator")[0].disabled = false;
				jQuery("#id_initiator_display")[0].disabled = false;
				jQuery("#id_autoSignatureInitiator")[0].disabled = false;
				jQuery("#id_autoSignatureInitiator_display")[0].disabled = false;
				jQuery("#id_autoReadInitiator")[0].disabled = false;
				jQuery("#id_autoReadInitiator_display")[0].disabled = false;
				jQuery("#id_formPath")[0].disabled = false;
				jQuery("#id_formTitle")[0].disabled = false;
				jQuery("#id_passedCount")[0].disabled = false;
				jQuery("input[name='submitButtons']").removeAttr("disabled");
				//信息
				jQuery("#id_messageContent")[0].disabled = false;
				jQuery("input[name='messageTypes']").removeAttr("disabled");
				jQuery("#id_readMessageContent")[0].disabled = false;
				jQuery("input[name='readMessageTypes']").removeAttr("disabled");
				jQuery("#id_signatureMessageContent")[0].disabled = false;
				jQuery("input[name='signatureMessageTypes']").removeAttr("disabled");
				
				jQuery("input[type='radio'][name='isNeedAdvise']").removeAttr("disabled");
				jQuery("input[type='radio'][name='isShowOnApp']").removeAttr("disabled");
				jQuery("input[type='radio'][name='isNeedAdviseExclude']").removeAttr("disabled");
				jQuery("input[type='radio'][name='isAttachmentChecked']").removeAttr("disabled");
				jQuery("input[type='radio'][name='isAutoActivity']").removeAttr("disabled");
				combo_attachmentType.readOnlyData = false;
				combo_backRoutes.readOnlyData = false;
				if(isNeedAdviseExclude == '0'){
					combo_excludeMessageActivities.readOnlyData = true;
				}else{
					combo_excludeMessageActivities.readOnlyData = false;
				}
				combo_initiatorType.readOnlyData = false;
				combo_autoReadInitiatorType.readOnlyData = false;
				combo_autoSignatureInitiatorType.readOnlyData = false;
				combo_dealMethod.readOnlyData = false;
				combo_conditionRouteType.readOnlyData = false;
			}
			//设置参与人
			combo_initiatorType.setValue(initiatorType);
			combo_autoReadInitiatorType.setValue(autoReadInitiatorType);
			combo_autoSignatureInitiatorType.setValue(autoSignatureInitiatorType);
			combo_dealMethod.setValue(dealMethod);
			combo_initiatorType.reload();
			combo_autoReadInitiatorType.reload();
			combo_autoSignatureInitiatorType.reload();
			combo_dealMethod.reload();
			//设置条件路由
			combo_conditionRouteType.setValue(conditionRouteType);
			combo_conditionRouteType.reload();
			if("pageCallBack" == conditionRouteType){
				jQuery(".need-set-value-cls-conditionRoute").hide();
				jQuery(".need-set-value-cls-conditionRoute-label").show();
			} else {
				jQuery(".need-set-value-cls-conditionRoute").show();
				jQuery(".need-set-value-cls-conditionRoute-label").hide();
			}
			//设置节点Action
			combo_activityAction.setParams({
				paramWorkflowName:workflowName
			});
			combo_activityAction.setRawValue('');
			combo_activityAction.loadComplete=function(){
				this.setValue(activityAction);
			};
			combo_activityAction.reload();
			//设置附件类型
			combo_attachmentType.setRawValue('');
			combo_attachmentType.loadComplete=function(){
				this.setRawValue(attachmentType);
			};
			combo_attachmentType.reload();
			//设置退回路由
			combo_backRoutes.setParams({
				deployId:deployid
			});
			combo_excludeMessageActivities.setParams({
				deployId:deployid
			});
			combo_backRoutes.loadComplete=function(){
				this.setRawValue(backRoutesNodes);
			};
			combo_excludeMessageActivities.loadComplete=function(){
				this.setRawValue(excludeMessageActivitiesNodes);
			};
			combo_backRoutes.reload();
			combo_excludeMessageActivities.reload();
			//初始化设置
			if("NPassed" == dealMethod){
				$("td.n-passed-cls").show();
			} else {
				$("td.n-passed-cls").hide();
			}
			
			//选择参与人
			initNodeInitiator(combo_initiatorType.getValue(),combo_initiatorType.getRawValue(),"id_initiatorTypeContainer","id_initiator_display","id_initiator","id_initiator_button","");
			//选择结束时传阅参与人类型
			initNodeInitiator(combo_autoReadInitiatorType.getValue(),combo_autoReadInitiatorType.getRawValue(),"id_autoReadInitiatorTypeContainer","id_autoReadInitiator_display","id_autoReadInitiator","id_autoReadInitiator_button","read");
			//选择结束时会签参与人类型 
			initNodeInitiator(combo_autoSignatureInitiatorType.getValue(),combo_autoSignatureInitiatorType.getRawValue(),"id_autoSignatureInitiatorTypeContainer","id_autoSignatureInitiator_display","id_autoSignatureInitiator","id_autoSignatureInitiator_button","signature");
			//设置选择历史步骤
			var params = {
				deployId:deployid,
				currentActivityDetailId:id
			};
			var stepSelection = getTracywindyObject('id_step');
			stepSelection.setParams(params);
			stepSelection.reload();
			var stepSelectionRead = getTracywindyObject('id_step'+"read");
			stepSelectionRead.setParams(params);
			stepSelectionRead.reload();
			var stepSelectionSignature = getTracywindyObject('id_step'+"signature");
			stepSelectionSignature.setParams(params);
			stepSelectionSignature.reload();
			
			mini.get("id_workflow_activity_setting").show();
		}
		
		
		function __saveChangeToActivityClose() {
			mini.get('id_workflow_activity_setting').hide();
		}
		function saveChangeToActivity() {
			var activityName = jQuery("#id_activityName").html();
			mini.confirm("确认修改节点（ "+activityName+"）的信息么？",'提示',function(action) {
				if(action == 'ok'){
					mini.mask({ el: document.body, cls: 'mini-mask-loading', html: '加载中...' });
					var id = jQuery("#id_recordId").val();
	
					var combo_initiatorType = getTracywindyObject('combo_initiatorType');
					var combo_autoReadInitiatorType = getTracywindyObject('combo_initiatorTyperead');
					var combo_autoSignatureInitiatorType = getTracywindyObject('combo_initiatorTypesignature');
					var combo_dealMethod = getTracywindyObject('combo_dealMethod');
					var combo_conditionRouteType = getTracywindyObject('combo_conditionRouteType');
					
					var initiatorType = combo_initiatorType.getValue();
					var initiator = jQuery("#id_initiator").val();
					var conditionRouteType = combo_conditionRouteType.getValue();
					var conditionRouteValue = jQuery("#id_conditionRouteValue").val();
					var displayInitiator = jQuery("#id_initiator_display").val();
					var autoSignatureInitiatorType = combo_autoSignatureInitiatorType.getValue();
					var autoSignatureInitiator = jQuery("#id_autoSignatureInitiator").val();
					var autoSignatureDisplayInitiator = jQuery("#id_autoSignatureInitiator_display").val();
					var autoReadInitiatorType = combo_autoReadInitiatorType.getValue();
					var autoReadInitiator = jQuery("#id_autoReadInitiator").val();
					var autoReadDisplayInitiator = jQuery("#id_autoReadInitiator_display").val();
					
					var dealMethod = combo_dealMethod.getValue();
					var passedCount = jQuery("#id_passedCount").val();
					var activityAction = getTracywindyObject('combo_activityAction').getValue();
					var attachmentType = getTracywindyObject('combo_attachmentType').getRawValue();
					var attachmentTypeIds = getTracywindyObject('combo_attachmentType').getValue();
					var formPath = jQuery("#id_formPath").val();
					var formTitle = jQuery("#id_formTitle").val();
					var submitButtons = getOperationButtons();
					var isNeedAdvise = jQuery("input[type='radio'][name='isNeedAdvise']:checked").val();
					var isShowOnApp = jQuery("input[type='radio'][name='isShowOnApp']:checked").val();
					var isNeedAdviseExcludeStr = jQuery("input[type='radio'][name='isNeedAdviseExclude']:checked").val();
					var isNeedAdviseExclude =  isNeedAdviseExcludeStr == '1' ? true : false;
					var isAttachmentChecked = jQuery("input[type='radio'][name='isAttachmentChecked']:checked").val();
					
					var isAutoActivity = jQuery("input[type='radio'][name='isAutoActivity']:checked").val();
					var backRoutesNodes = getTracywindyObject('combo_backRoutes').getRawValue();
					var excludeMessageActivitiesDesc = getTracywindyObject('combo_excludeMessageActivities').getRawValue();
					var excludeMessageActivities = getTracywindyObject('combo_excludeMessageActivities').getValue();
					//信息
					var messageTypes = getMessageTypes();
					var messageContent = jQuery("#id_messageContent").val();
					var readMessageTypes = getReadMessageTypes();
					var readMessageContent = jQuery("#id_readMessageContent").val();
					var signatureMessageTypes = getSignatureMessageTypes();
					var signatureMessageContent = jQuery("#id_signatureMessageContent").val();
					
					jQuery("input[name='messageTypes']").removeAttr("disabled");
					var params={
						id:id,
						actorType:initiatorType,
						actorValue:initiator,
						conditionRouteType:conditionRouteType,
						conditionRouteValue:conditionRouteValue,
						messageTypes:messageTypes,
						messageContent:messageContent,
						readMessageTypes:readMessageTypes,
						readMessageContent:readMessageContent,
						signatureMessageTypes:signatureMessageTypes,
						signatureMessageContent:signatureMessageContent,
						displayActorValue:displayInitiator,
						autoSignatureActorType:autoSignatureInitiatorType,
						autoSignatureActorValue:autoSignatureInitiator,
						autoSignatureDisplayActorValue:autoSignatureDisplayInitiator,
						autoReadActorType:autoReadInitiatorType,
						autoReadActorValue:autoReadInitiator,
						autoReadDisplayActorValue:autoReadDisplayInitiator,
						activityAction:activityAction,
						attachmentType:attachmentType,
						attachmentTypeIds:attachmentTypeIds,
						formPath:formPath,
						formTitle:formTitle,
						operationButtons:submitButtons	,
						isNeedAdvise:isNeedAdvise,
						isShowOnApp:isShowOnApp,
						isNeedAdviseExclude:isNeedAdviseExclude,
						isAttachmentChecked:isAttachmentChecked,
						isAutoActivity:isAutoActivity,
						backActivities:backRoutesNodes,
						excludeMessageActivitiesDesc:excludeMessageActivitiesDesc,
						excludeMessageActivities:excludeMessageActivities,
						dealMethod:dealMethod,
						passedCount:passedCount
					};
					tenwa.ajaxRequest({
						url:'${pageContext.request.contextPath}/jbpm/saveChangeToActivity.action',
						method:'POST',
						success:function(res){
							mini.get('id_workflow_activity_detail_table').reload();
							mini.unmask(document.body);
							mini.get("id_workflow_activity_setting").hide();
						},
						failure:function(res){mini.unmask(document.body);},
						params:params
					}); 
				}
			});
		}
		
		function setDataChecks(buttonsValue,checkName){
			$("input[name='"+checkName+"']").attr("checked",false);
			if(buttonsValue)
			{
				var splitButtonsArr = buttonsValue.split(",");
				for(var i=0;i<splitButtonsArr.length;i++)
				{
					$("input[name='"+checkName+"'][value='"+splitButtonsArr[i]+"']").click();//attr("checked",true);
				}
			}
		}
		
		function getDataChecks(checkName){
			var operationButtonsCheckBox = $("input[name='"+checkName+"']:checked");
			var operationButionsArr = [];
			operationButtonsCheckBox.each(function(){
				operationButionsArr.push(this.value);
			});
			return operationButionsArr.join(",");
		}
		
		function setMessageTypes(buttonsValue){
			setDataChecks(buttonsValue,"messageTypes");
		}
		
		function getMessageTypes(){
			return getDataChecks("messageTypes");
		}
		
		function setReadMessageTypes(buttonsValue){
			setDataChecks(buttonsValue,"readMessageTypes");
		}
		
		function getReadMessageTypes(){
			return getDataChecks("readMessageTypes");
		}
		
		function setSignatureMessageTypes(buttonsValue){
			setDataChecks(buttonsValue,"signatureMessageTypes");
		}
		
		function getSignatureMessageTypes(){
			return getDataChecks("signatureMessageTypes");
		}
		
		function setOperationButtons(buttonsValue) {
			setDataChecks(buttonsValue,"submitButtons");
			/*$("input[name='submitButtons']").attr("checked",false);
			if(buttonsValue)
			{
				var splitButtonsArr = buttonsValue.split(",");
				for(var i=0;i<splitButtonsArr.length;i++)
				{
					$("input[name='submitButtons'][value='"+splitButtonsArr[i]+"']").attr("checked",true);
				}
			}*/
		}
		
		function getOperationButtons() {
			return getDataChecks("submitButtons");
			/*var operationButtonsCheckBox = $("input[name='submitButtons']:checked");
			var operationButionsArr = [];
			operationButtonsCheckBox.each(function(){
				operationButionsArr.push(this.value);
			});
			return operationButionsArr.join(",");*/
		}
	</script>
	<script type="text/javascript">
		//processDefinitionsDatas.sort(function(a,b){return a.processdefinitionkey>b.processdefinitionkey?1:-1;});
		
	var processDefinitionsColumns = [ 
		{type : 'indexcolumn' }, 
		{type : 'checkcolumn' },
		{header:'流程发布',field:'operdeploy',align:"left",width:150,
			renderer:function(e){
				var rowData = e.record;
				var deploy = "";
				if(rowData.deployid) {
					deploy += '<a href="javascript:void(0);" onclick="removeDeploy(\''+rowData.id+'\',\''+rowData.deployid+'\')">撤销发布</a>';
					if(rowData.processdefinitiondeployedtime < rowData.processdefinitionupdatetime) {
						deploy+='/<a href="javascript:void(0);" onclick="workflowSynchronized(\''+rowData.deployid+'\')">流程同步</a>';
					}
					
					//deploy+='/<a href="javascript:void(0);" onclick="workflowInfoSetting('+rowData.index+',\''+rowData.deployid+'\',\''+rowData.processdefinitionid+'\')">修改</a>';
					//deploy+='/<a href="javascript:void(0);" onclick="startProcess(\''+rowData.processdefinitionid+'\')">发起流程</a>';
				} else {
					deploy += '<a href="javascript:void(0);" onclick="publishDeploy(\''+rowData.id+'\')">发布</a>';
					deploy += '/<a href="javascript:void(0);" onclick="removeDesignedWorkflow(\''+rowData.id+'\')">删除</a>';
				}
				return deploy;
			}
		},
		{header:'设计编号',field:'id',visible:false},
		{header:'部署编号',field:'deployid',visible:false},
		{header:'流程分类编号',field:'type',visible:false},
		{header:'排序',field:'position',visible:false},
		{header:'流程分类',field:'displaytype'},
		{header:'显示名称',field:'processdefinitiondisplayname',width:200},
		{header:'流程编号',field:'code'},
		{header:'流程名称',field:'processdefinitionkey',width:200},
		{header:'流程版本号',field:'processdefinitionversion',width:80},
		{header:'设计器版本',field:'processdefinitionjpdlversion',width:80},
		{header:'流程描述',field:'processdefinitiondescription',width:100},
		{header:'关键字段一',field:'keyone',width:150},
		{header:'关键字段二',field:'keytwo',width:150},
		{header:'关键字段三',field:'keythree',width:150},
		{header:'关键字段四',field:'keyfour',width:150},
		{header:'关键字段五',field:'keyfive',width:150},
		{header:'关键字段六',field:'keysix',width:150},
		{header:'关键字段七<font color="red">(付款单号)</font>',field:'keyseven',width:150},
		{header:'关键字段八<font color="red">(合同流水号)</font>',field:'keyeight',width:150},
		{header:'关键字段九<font color="red">(项目立项流水号)</font>',field:'keynine',width:150},
		{header:'关键字段十<font color="red">(互斥标识)</font>',field:'keyten',width:150}
	];
		
		
	tenwa.createTable({
		lockedNames:['diagram','operdesign','operdeploy'],
		renderTo:'id_processDefinitionsContainer',
		width:all_width-2,
		height:all_height,
		pageSize:15,
		title:'流程设计',
		tools:[ {
				html:'创建新流程',
				iconCls:'icon-new',
				handler:function(){
					createNewWorkflow();
				}
			},'-',{
				html:'流程设计',
				iconCls:'icon-edit',
				handler:function(miniTable, buttonText){
					var selectedRowDatas = miniTable.getSelecteds();
					if(selectedRowDatas.length == 1) {
						modifyWorkflow(selectedRowDatas[0]);
					} else {
						mini.alert('请选择您好修改的数据。');
					}
				}
			},'-',{
				html:'节点设置',
				iconCls:'icon-node',
				handler:function(miniTable, buttonText){
					var selectedRowDatas = miniTable.getSelecteds();
					if(selectedRowDatas.length == 1) {
						var rowData = selectedRowDatas[0];
						if(rowData.deployid){
							workflowActivitiesSetting(rowData);
						} else {
							mini.alert('请先发布该该流程。');
						}
					} else {
						mini.alert('请选择您好修改的数据。');
					}
				}
			},'-',{
				html:'附件关联',
				iconCls:'icon-node',
				handler:function(miniTable, buttonText){
					var selectedRowDatas = miniTable.getSelecteds();
					if(selectedRowDatas.length == 1) {
						var rowData = selectedRowDatas[0];
						workflowAttachmentSetting(rowData);
					} else {
						mini.alert('请选择您好修改的数据。');
					}
				}
			},'-',{
				html:'关键字设置',
				iconCls:'icon-node',
				handler:function(miniTable, buttonText){
					var selectedRowDatas = miniTable.getSelecteds();
					if(selectedRowDatas.length == 1) {
						var rowData = selectedRowDatas[0];
						workflowKeySetting(rowData);
					} else {
						mini.alert('请选择您好修改的数据。');
					}
				}
			},'-',{
				html:'查看流程图',
				iconCls:'icon-find',
				handler:function(miniTable, buttonText){
					var selectedRowDatas = miniTable.getSelecteds();
					if(selectedRowDatas.length == 1) {
						var rowData = selectedRowDatas[0];
						toProcessActivePicture(rowData);
					} else {
						mini.alert('请选择您好修改的数据。');
					}
				}
			},'-',{
				html:'单个导出',
				iconCls:'icon-exportExcel',
				handler:function(miniTable, buttonText){
					var selectedRowDatas = miniTable.getSelecteds();
					if(selectedRowDatas.length == 1) {
						//var rowData = selectedRowDatas[0];
						//toProcessActivePicture(rowData);
						exportWorkflowUnique(selectedRowDatas[0].id);
					} else {
						mini.alert("<spring:message code='A2D03752-E01B-45B1-BD59-22A95F129C2E' text='请选择您好修改的数据。'/>");
					}
				}
			},'-',{
				html:'导出',
				iconCls:'icon-exportExcel',
				handler:function(miniTable, buttonText){
					exportWorkflow();
				}
			},'-',{
				html:'导入',
				iconCls:'icon-importExcel',
				handler:function(miniTable, buttonText){
					var selectedRowDatas = miniTable.getSelecteds();
						importWorkflow();
				}
			}, '-', 'exportExcel', '-', 'globalQuery'
		],
		id:'id_workflows_table',
		showPager:true,
		start:parseInt("${empty param.pageStart ? 0 : param.pageStart }"),
		params:{
			queryText:unescape(decodeURIComponent("${param.queryText}"))/* ,
			TableRemoteSortField : 'POSITION_'  */
		},
		xmlFileName:'/jbpm/queryAllDesignedWorkflows.xml',
		columns:processDefinitionsColumns,
		callBack:function(){
			this.setHideColumn('processdefinitionversion');
			this.setHideColumnHidden('processdefinitionjpdlversion');
		}
	});
	
	function exportWorkflow(){
        $.fileDownload('${pageContext.request.contextPath}/jbpm/exportWorkflowConfig.action?browser=' + SysBrowser.getBrowser() ,{
            successCallback:function(url){
                
            },
            failCallback:function(html,url){
                
            }
        });
    }
	
	function exportWorkflowUnique(id){
        $.fileDownload('${pageContext.request.contextPath}/jbpm/exportWorkflowConfigUnique.action?id='+id+'&browser=' + SysBrowser.getBrowser() ,{
            successCallback:function(url){
                
            },
            failCallback:function(html,url){
                
            }
        });
    }
	
	function importWorkflow(){
        jQuery('#import_input').show();
        jQuery('#import_msg').hide();
        jQuery("#div_workflowImport").show();
        jQuery("#div_workflowImport").window({top:20,draggable:false});
        jQuery("#div_workflowImport").window("open");
    }
	function toImportWorkflow(){
        jQuery('#import_input').hide();
        jQuery('#import_msg').show();
        $.ajaxFileUpload({
            url: '${pageContext.request.contextPath}/jbpm/importWorkflowConfig.action',           
            fileElementId: 'input_file_upload',
            dataType:'text',
            success:function(data){
                if(data == 'SUCCESS'){
                    jQuery("#div_workflowImport").window("close");
                    var workflowsTable = getTracywindyTable("id_workflows_table");
                    mini.get("id_workflows_table").reload();
                    /* workflowsTable.setParams({
                            queryText:'${param.queryText}'
                    }); */
                    
                    //doLocalTablePageReload( workflowsTable.params, workflowsTable.start, workflowsTable.pageSize);
                }
                
            }
        });
    }
</script>
</body>
</html>