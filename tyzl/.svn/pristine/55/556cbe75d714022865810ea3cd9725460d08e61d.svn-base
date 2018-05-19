<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>流程发起互斥管理</title>

<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js?version=1,1,1,1"></script>

<!--css sheet-->
<link href="${pageContext.request.contextPath}/css/dtree/dtree.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/dtree/dtree.js"></script>
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
		table.setParams({sourceDesignerId:currentMenuSelectNode.id,workflownameparent:trim(currentMenuSelectNode.text.replace(/[\s]*-[\s]*1/g,''))});
		table.frozenColumns(0,4);
		table.reload();
	}
	
	function distribute(workFlowNameA, workFlowNameB, checked){
		var url = "${pageContext.request.contextPath}/acl/addOrRemoveReject.acl";
		var params = {
			workFlowNameA : workFlowNameA,
			workFlowNameB : workFlowNameB
		};
		jQuery.ajax({
			url:url,
			data:params,
			timeout:30*1000,
			dataType:'json',
			type:'POST',
			success:function(resultJson){
				var returnState = resultJson.returnType;
				switch (returnState) {
				case "SUCCESS": {
					mini.get("id_table").reload();
					mini.unmask(document.body);
					mini.get("id_table").reload();
					break;
				}
				case "FAILURE": {
					mini.unmask(document.body);
					mini.get("id_table").reload();
					break;
				}
				}

			},error:function(res){
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
			pageSize:50,
			columns:[
				{type:'indexcolumn'},
				{header:'记录编号',field:'id',visible : false},
				{
					header:'互斥流程分配',
					field:'isgrant',
					visible : false,
					align:'center',
					renderer:function(e){
					var checked = "";
					var queryCondition = "";
					var isreject = e.record.isreject;
					var workflownameparent = trim(currentMenuSelectNode.text.replace(/[\s]*-[\s]*1/g,''));
					var workflownameselect = e.record.key_
					if(isreject == '0'){
						var html = "<input style='cursor:pointer;' type='checkbox'  onclick=\"distribute('" + workflownameparent + "','" + workflownameselect + "',this.checked);\"/>";
					}else{
						var html = "<input style='cursor:pointer;' type='checkbox' checked='checked'  onclick=\"distribute('" + workflownameparent + "','" + workflownameselect + "',this.checked);\"/>";
					}
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
	
</script>
</head>
<body>
	<div id='menu_main_content' class="mini-panel" title="流程列表" style="float:left;width:270px;overflow:hidden;">
		<div id="id_menu_tabs_menu" style="height: 100%;position: relative;width: 100%;overflow: auto;"></div>
	</div>
	<div id='id_menu_main_content_div' style="border:1px solid #DDD;border-top:0px;overflow:hidden;margin-left: 5px;"></div>
	<script type='text/javascript'>
		jQuery("#menu_main_content").css("height",globalClientHeight+"px");
		jQuery("#id_menu_main_content_div").css("height",globalClientHeight+"px");
		jQuery("#id_menu_main_content_div").css("width",(globalClientWidth - widthAdd)+"px");
	</script>
</body>
</html>
