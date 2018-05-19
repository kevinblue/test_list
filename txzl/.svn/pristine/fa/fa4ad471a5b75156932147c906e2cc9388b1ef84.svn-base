<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程发起互斥管理</title>

	<%@include file="/base/mini.jsp"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js?version=1,1,1,1"></script>
	
	<!--css sheet-->
	<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
	<!--javascript libray-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree/dtree.js"></script>
	<style type="text/css">
		html,body{
		overflow:hidden;
		}
	</style>
<script type="text/javascript">
	var pageWidth = document.documentElement.clientWidth;
	var widthAdd = 275;
	
	jQuery(function(){
		mini.mask({
			el: document.body,
			cls: 'mini-mask-loading',
			html: '加载中...'
		});
		//加载树形
		$('#id_menu_tabs_menu').tree({
			url:'${pageContext.request.contextPath}/jbpm/getDeployedWorkflowsTreeData.action',
			onBeforeLoad:function(node,params){
				mini.mask({
					el: document.body,
					cls: 'mini-mask-loading',
					html: '加载中...'
				});
				if(!node){
					params['init'] = true; 
				} else{
					params['typeId'] = node.id;
				}
			},
			onLoadSuccess:function(a,b){
				mini.unmask(document.body);
			},
			onClick:function(node){
				var isType = false;
				if(!node.attributes)isType=true;
				if(node.attributes){
					if(!node.attributes["typeId"]) {
						isType=true;
					}
				}
				var table = mini.get("id_table");
				if(isType){
					table.hideColumn('isgrant');
					table.hideColumn('rejectinfo');
					table.hideColumn('query_conditions_');
					table.reload();
				} else {
					currentMenuSelectNode = node;
					reloadSelectUsersTable();
				}
			}
		});
	});

		
	//加载人员选择
	var currentMenuSelectNode = null;
	var currentMenuRemoveUserNode = null;
	var currentSelectedUserIds = [];
	var rejectJsonArray = null;
	function reloadSelectUsersTable(){
		var table = mini.get("id_table");
		table.showColumn('isgrant');
		table.showColumn('rejectinfo');
		table.showColumn('query_conditions_');
		table.setParams({xmlFileName:"/jbpm/queryRejectWorkflowDesigners.xml"});
		table.setParams({sourceDesignerId:currentMenuSelectNode.id});
		table.frozenColumns(0,4);
		table.reload();
	}
	
	function distribute(leftId, rightId, checked){
		var entityClassName = "WorkflowDesignerReject";
		var leftFieldName = "sourceJbpmWorkflowDesigner";
		var rightFieldName = "rejectJbpmWorkflowDesigner";
		var url = "${pageContext.request.contextPath}/acl/addOrRemoveDistribute.acl";
		var params = {
			entityClassName:entityClassName,
			leftFieldName :leftFieldName,
			leftId		:leftId,
			rightFieldName :rightFieldName,
			rightId		:rightId
		};
		tenwa.ajaxRequest({
			url:url,
			params:params,
			timeout:30*1000,
			success:function(res){
				mini.unmask(document.body);
				mini.get("id_table").reload();
			},failure:function(res){
				mini.unmask(document.body);
				mini.get("id_table").reload();
			}
		});
	}
	jQuery(function(){
		tenwa.createTable({
			title:'互斥流程列表',
			id:'id_table',
			renderTo:'id_menu_main_content_div',
			width : globalClientWidth - widthAdd,//表格高度，整型数值
			height : globalClientHeight,//表格宽度，整型数值
			tools:['globalQuery'],
			xmlFileName:'/jbpm/queryAllDesignedWorkflows.xml',
			loadMode:'ajax',
			showPager:true,
			pageSize:15,
			columns:[
				{type:'indexcolumn'},
				{header:'记录编号',field:'id',visible : false},
				{header:'关键字一对应参数名',field:'query_conditions_',visible : false
				}, {
					header:'自定义互斥CLASS',
					field:'rejectinfo',
					visible : false, 
					renderer:
						function(e){
							if(!rejectJsonArray){
								tenwa.ajaxRequest({
									url:'${pageContext.request.contextPath}/jbpm/getWorkflowRejectBeanNames.action',
									timeout:30*10*1000,
									async:false,
									success:function(res){
										rejectJsonArray = JsonUtil.decode(res.responseText);
									}
								});
							}
							var rowData_sourceWorkflowName = currentMenuSelectNode.attributes["key"];
							var rowData_rejectWorkflowName = e.record.key_;
							for(var i = 0;i<rejectJsonArray.length;i++){
								var rejectJson = rejectJsonArray[i];
								var sourceWorkflowName = rejectJson.sourceWorkflowName;
								var rejectWorkflowName = rejectJson.rejectWorkflowName;
								if((rowData_sourceWorkflowName == sourceWorkflowName) && (rowData_rejectWorkflowName == rejectWorkflowName)){
									return rejectJson.name;
								}
							}
							return "";
						}
				}, {
					header:'互斥流程分配',
					field:'isgrant',
					visible : false,
					renderer:function(e){
						var checked = "";
						var queryCondition = "";
						
						if('1' == e.record.isgrant ) {
							var id = e.record.id_;
							var displayName = e.record.display_name_;
							var queryConditions = e.record.query_conditions_;
							queryCondition = "<a href='javascript:void(0);' onclick='showRejectConditionsWindow(\"" + id + "\",\"" + displayName + "\",\"" + queryConditions + "\");'>"+"互斥条件"+"</a>&nbsp;";
							checked = "checked";
						}
						
						var sourcedesignerid = e.record.sourcedesignerid;
						var rejectdesignerid = e.record.rejectdesignerid;
						var html = "<input style='cursor:pointer;' type='checkbox' "+checked +" onclick=\"distribute('" + sourcedesignerid + "','" + rejectdesignerid + "',this.checked);\"/>";
						return queryCondition+html;
					}
				},
				{header:'111',field:'sourcedesignerid',visible : false},
				{header:'222',field:'rejectdesignerid',visible : false},
				{header:'流程分类',field:'typename_'},
				{header:'显示名称',field:'display_name_'},
				{header:'流程编号',field:'code_'},
				{header:'流程名称',field:'key_'}/*,
				{header:'流程版本',field:'version_'},
				{header:'流程描述',field:'description_'}*/
			],
			params:{
				enabled : 1
			}
		});
	});
	
	var globalId = '';
	function showRejectConditionsWindow(id, displayName, queryConditions) {
		globalId = id;
		$("#id_rejectQueryConditions").val(queryConditions);
		mini.get('id_detailInfoWindowContainer').setTitle(' < '+displayName+' > 互斥条件');
		mini.get('id_detailInfoWindowContainer').show();
	}
	
	function __detailInfoWindowContainerClose(){
		mini.get('id_detailInfoWindowContainer').hide();
	}
	
	function setRejectConditions() {
		var queryConditions = $("#id_rejectQueryConditions").val();
		if(!queryConditions.replace(/\s/g,"")){
			mini.alert("流程互斥条件不能为空!");
			return ;
		}
		mini.get("id_detailInfoWindowContainer").hide();
		mini.mask({
			el: document.body,
			cls: 'mini-mask-loading',
			html: '加载中...'
		});
		var url = "${pageContext.request.contextPath}/acl/updateRejectQueryCondition.acl";
		var params = {
			id : globalId,
			queryConditions:queryConditions
		};
		tenwa.ajaxRequest({
			url:url,
			params:params,
			timeout:30*1000,
			success:function(res){
				mini.unmask(document.body);
				mini.alert("设置成功");
				mini.get("id_table").reload();
			},
			failure:function(res){
				mini.unmask(document.body);
				mini.alert("设置失败");
				mini.get("id_table").reload();
			}
		});
	}
</script>
</head>
<body>
	<div id='menu_main_content' class="mini-panel" title="流程列表" style="float:left;width:270px;overflow:hidden;">
		<div id="id_menu_tabs_menu" style="height: 100%;position: relative;width: 100%;overflow: auto;">
		
		</div>
	</div>
	<div id='id_menu_main_content_div' style="border:1px solid #DDD;border-top:0px;overflow:hidden;margin-left: 5px;">
	
	</div>
	
	<div id="id_detailInfoWindowContainer" title="互斥条件" class="mini-window" style="display:none;width:425px;height:200px;">
		<form id="id_detailInfoForm">
			<table style="width:100%;position: relative;">
				<tr><td class="input_label_desc">互斥条件：</td></tr>
				<tr><td class="input_value">
					<textarea requir="true" id="id_rejectQueryConditions" style="height:70px;width:400px;overflow:auto;"></textarea>
				</td></tr>
				<tr><td colspan='2' style="text-align: center;padding-top: 10px;">
					<a class="mini-button" onclick='setRejectConditions'><span>确定</span></a>
					<a class="mini-button" onclick='__detailInfoWindowContainerClose'><span>取消</span></a>
				</td></tr>
			</table>
		</form>
	</div>
	<script type='text/javascript'>
		jQuery("#menu_main_content").css("height",globalClientHeight+"px");
		jQuery("#id_menu_main_content_div").css("height",globalClientHeight+"px");
		jQuery("#id_menu_main_content_div").css("width",(globalClientWidth - widthAdd)+"px");
	</script>
</body>
</html>
