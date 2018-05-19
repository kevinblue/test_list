<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.tenwa.kernal.utils.FileUtil,com.tenwa.kernal.utils.WebUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统后台管理</title>
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">  
	<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
	<jsp:include page="/base/mini.jsp" flush="true"></jsp:include>
	<!--css sheet-->
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<!--javascript libray-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindySerializeFormToJsonObject.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree/dtree.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyCommonUserSelection.js"></script>
	<script type="text/javascript">
		var loadMask = null;
		function xmlDataSynchronized(){
			mini.confirm("确认同步XML数据么？", "同步？", function(action){
				mini.mask({
					el: document.body,
					cls: 'mini-mask-loading',
					html: '数据加载中，请稍后...'
				});
				if (action == "ok") {
					ajaxRequest({
						url:'${pageContext.request.contextPath}/acl/updateXmlData.acl',
						timeout:30*60*1000,
						success:function(response){
							mini.unmask(document.body);
							mini.alert("XML数据同步成功！");
						},
						failure:function(response){
							mini.unmask(document.body);
							mini.alert("XML数据同步失败！");
						}
					});
				} else {
					mini.unmask(document.body);
				}
			});
			return false;
		}
		
		
		
		function permissionSynchronized(){
			mini.confirm("确认同步权限么？", "同步？", function(action){
				mini.mask({
					el: document.body,
					cls: 'mini-mask-loading',
					html: '数据加载中，请稍后...'
				});
				if (action == "ok") {
					ajaxRequest({
						url:'${pageContext.request.contextPath}/acl/updatePermission.acl',
						timeout:30*60*1000,
						success:function(response){
							mini.unmask(document.body);
							mini.alert("权限同步成功！", '提示', function(){
								window.location.href = window.location.href;
							});
						},
						failure:function(response){
							mini.unmask(document.body);
							mini.alert("权限同步失败！");
						}
					});
				} else {
					mini.unmask(document.body);
				}
			});
			return false;
		}
		
		function tabLanguage(){
			mini.confirm("确认切换语言么？", "切换？", function(action){
				mini.mask({
					el: document.body,
					cls: 'mini-mask-loading',
					html: '切换语言中，请稍后...'
				});
				if (action == "ok") {
					ajaxRequest({
						url:'${pageContext.request.contextPath}/acl/updateLocalDefaut.acl',
						timeout:30*60*1000,
						success:function(response){
							mini.unmask(document.body);
							mini.alert("切换语言！", '提示', function(){
								window.location.reload();
							});
						},
						failure:function(response){
							mini.unmask(document.body);
							mini.alert("切换语言失败！");
						}
					});
				} else {
					mini.unmask(document.body);
				}
			});
			return false;
		}
		
		
		function changeSystemModel(){
			mini.confirm("确认切换系统模式么？", "切换？", function(action){
				mini.mask({
					el: document.body,
					cls: 'mini-mask-loading',
					html: '数据加载中，请稍后...'
				});
				if (action == "ok") {
					ajaxRequest({
						url:'${pageContext.request.contextPath}/acl/updateSystemMode.acl',
						timeout:30*60*1000,
						success:function(response){
							mini.unmask(document.body);
							mini.alert("系统模式切换成功！", '提示', function(){
								window.location.href = window.location.href;
							});
						},
						failure:function(response){
							mini.unmask(document.body);
							mini.alert("系统模式切换失败！");
						}
					});
				} else {
					mini.unmask(document.body);
				}
			});
			return false;
		}
		
		
		function __workflowSynchronized_true(){
			workflowSynchronized(true);
		}
		function __workflowSynchronized_false(){
			workflowSynchronized(false);
		}
		
		function workflowSynchronized(flag){
			if(!Validator.Validate(jQuery("#id_workflowSynchronizedContainer")[0],1,false)){
				return;
			}
			var flagComment = flag ? "选中" : "全部";
			mini.confirm("确认同步" + flagComment + "流程么？", "切换？", function(action){
				mini.mask({
					el: document.body,
					cls: 'mini-mask-loading',
					html: '数据加载中，请稍后...'
				});
				if (action == "ok") {
					var params = jQuery("#id_workflowSynchronizedContainer").tracywindySerializeFormToJsonObject(true);
					if(!flag){
						delete params["updateWorkflowIds"];
					} else {
						mini.unmask(document.body);
						if(!jQuery("#id_updateWorkflowIds").val()){
							jQuery("#id_updateWorkflowIds").focus();
							mini.alert("请选择需要同步的流程！");
							return false;
						}
					}
					ajaxRequest({
						url:'${pageContext.request.contextPath}/jbpm/updateWorkflowConfigSynchronized.action',
						timeout:30*60*1000,
						method:"post",
						success:function(response){
							mini.unmask(document.body);
							mini.alert("流程同步成功！", "提示", function(){
								window.location.href = window.location.href ;
							});
						},
						failure:function(response){
							mini.unmask(document.body);
							mini.alert("流程同步失败！");
						},
						params:params
					});
				} else {
					mini.unmask(document.body);
				}
			});
			return false;
		}
		
		function removerTemplate(){
			mini.confirm("确认模板移位么？", "移位？", function(action){
				mini.mask({
					el: document.body,
					cls: 'mini-mask-loading',
					html: '数据加载中，请稍后...'
				});
				if (action == "ok") {
					ajaxRequest({
						async:false,
						url:'${pageContext.request.contextPath}/leasing/template/removeTempalate.action',
						timeout:30*60*1000,
						success:function(response){
							mini.unmask(document.body);
							mini.alert("系统模板移位成功！");
							window.location.href = window.location.href;
						},
						failure:function(response){
							mini.unmask(document.body);
							mini.alert("系统模板移位失败！");
						}
					});
				} else {
					mini.unmask(document.body);
				}
			});
			return false;
		} 
		
		jQuery(function(){
			mini.parse(document.body);
		});
	</script>
	<style type="text/css">
		body fieldset{
			text-align:center;
			float:left;
			margin-left:20px;
			border:1px solid silver;
			padding-top:10px;
			padding-left:20px;
			width:200px;
			height: 70px;
		}
		
		body fieldset font{
			color: red;
			font-weight:BOLD;
		}
	
		body fieldset a.btn{
			width:auto;
			margin-top:10px;
			min-width: 100px;
			font-size: 12px;
		}
		
		.fl{
			float: left;
		}
		
		.data-source-config input,.data-source-config textarea{
			width:200px;
			border:1px solid #DDD;
			overflow:auto;
		}
		
		.data-source-config font{
			width:100px;
			text-align:center;
			margin-left:10px;
			color:red;
		}
		
		.data-source-config td{
			text-align: right;
		}
		
		body{
			overflow-x:hidden;
		}
		#id_updateWorkflowIds_display{
			height:80px;
		}
	</style>
</head>
<body>
	<div class="mini-panel" title="后台控制" showCollapseButton="true" style="width: 100%;position: relative;height: 100%;">
		<div class="fl">
			<fieldset>
			<legend>模式变更</legend>
			<%if(!com.tenwa.kernal.utils.ResourceUtil.isDebug()){%> 
				<font>上线模式</font><br/><a class="mini-button" onclick="changeSystemModel" style="width: 150px;">转为 &nbsp;&lt;<font>开发</font>&gt;&nbsp;模式</a>
			<%}else{%> 
				<font>开发模式</font><br/><a class="mini-button" onclick="changeSystemModel" style="width: 150px;">转为 &nbsp;&lt;<font>上线 </font>&gt;&nbsp;模式</a>
			<%}%>
			</fieldset>
		</div>
		<div class="fl">
			<fieldset>
			<legend>XML数据同步</legend>
			<font>XML数据同步&nbsp;</font><br/>
			<a class="mini-button" onclick="xmlDataSynchronized" style="width: 150px;">同&nbsp;步&nbsp;变&nbsp;动 </a>
			</fieldset>
		</div>
		<div class="fl">
			<fieldset>
			<legend>切换语言</legend>
			<%if(com.tenwa.kernal.utils.WebUtil.getDefaultLocal().getLanguage().equals("en")){%> 
				<font>英文环境&nbsp;</font><br/><a class="mini-button" onclick="tabLanguage" style="width: 150px;">切&nbsp;换&nbsp;语&nbsp;言 </a>
			<%}else{%> 
				<font>中文环境&nbsp;</font><br/><a class="mini-button" onclick="tabLanguage" style="width: 150px;">切&nbsp;换&nbsp;语&nbsp;言 </a>
			<%}%>
			
			</fieldset>
		</div>
		<div class="fl">
			<fieldset>
			<legend>模板移位1</legend>
			<font>模板移位&nbsp;</font><br/>
			<a class="mini-button" onclick="removerTemplate" style="width: 150px;">模&nbsp;板&nbsp;移&nbsp;位 </a>
			</fieldset>
		</div>
		<div class="fl">
			<fieldset>
			<legend>权限同步</legend>
			<%if(com.tenwa.kernal.utils.ResourceUtil.isNeedUpdatePermissionCache()){%> 
				<font>存在权限变动&nbsp;</font><br/><a class="mini-button" onclick="permissionSynchronized" style="width: 150px;">同&nbsp;步&nbsp;变&nbsp;动 </a>
			<%}else{%> 
				<font>不存在权限变动</font>
			<%}%>
			</fieldset>
		</div>
		
		<div style="clear: both;"></div>
		<div class="data-source-config" style="width: 100%;position: relative;">
			<fieldset style="width:auto;height: auto;">
				<legend>流程数据库同步</legend>
				<table id="id_workflowSynchronizedContainer">
					<tr><td>源数据库类型：</td><td><select label="源数据库类型" Require="true" name="type" style="width:204px;;border:1px solid #DDD;"><option value="oracle" selected>oracle</option><option value="sqlserver">sqlserver</option></select><font>*</font></td></tr>
					<tr><td>源数据库地址：</td><td><input label="源数据库地址" Require="true" value="" name="host"></input><font>*</font></td></tr>
					<tr><td>源数据库端口：</td><td><input label="源数据库端口" Require="true" value="1521" name="port"></input><font>*</font></td></tr>
					<tr><td>源数据库库名/服务名：</td><td><input label="源数据库库名/服务名" Require="true" value="orcl" name="dbname"></input><font>*</font></td></tr>
					<tr><td>源数据库用户名：</td><td><input label="源数据库用户名" Require="true" value="" name="user"></input><font>*</font></td></tr>
					<tr><td>源数据库密码：</td><td><input type="password" label="源数据库密码" Require="true" value="" name="password"></input><font>*</font></td></tr>
					<tr><td>需要同步的流程：</td><td><textarea readonly onclick="getUpdateWorkflow();" id="id_updateWorkflowIds_display" name="updateWorkflowIds_display" label="需要同步的流程" Require="false" value=""></textarea><font>*</font></td></tr>
					<tr style="display:none" ><td>需要同步的流程：</td><td><textarea readonly id="id_updateWorkflowIds" name="updateWorkflowIds" label="需要同步的流程" Require="false" value="" ></textarea><font>*</font></td></tr>
					<tr>
						<td></td>
						<td style="text-align: left;">
							<a class="mini-button"  onclick="__workflowSynchronized_true">同步选中流程</a>
							<a class="mini-button"  onclick="__workflowSynchronized_false">同步全部流程</a>
						</td>
					</tr>
				</table>
			</fieldset>
		</div>
	</div>
	<script type='text/javascript'>
		
		var selectionUpdateWorkflowIds = null;
		function getUpdateWorkflow() {
			
			//判断必填项是否填写
			var checkInfo = "";
			jQuery('#id_workflowSynchronizedContainer').find('select,input').each(function(i,e){
				var $obj = jQuery(e);
				if($obj.attr('require') == 'true' && !$obj.val()){
					checkInfo += $obj.attr('label') +"不能为空！！！！"+'<br/>';
				}
			});
			if(checkInfo){
				mini.alert(checkInfo);
				return ;
			}
			var params = jQuery("#id_workflowSynchronizedContainer").tracywindySerializeFormToJsonObject(true);
			if(null == selectionUpdateWorkflowIds){
				selectionUpdateWorkflowIds = new tracywindyCommonUserSelection({
					hiddenInput:"id_updateWorkflowIds",
					displayInput:"id_updateWorkflowIds_display",
					isMultiSelect:true,
					draggable:false,
					windowTop:1,
					id:"id_selectionUpdateWorkflowIds",
					type:'synchronizedWorkflowDesigners',
					params:params
				});
			} else {
				selectionUpdateWorkflowIds.setParams(params);
				selectionUpdateWorkflowIds.reload();
			}
			selectionUpdateWorkflowIds.show();
		}
	</script>
</body>
</html>