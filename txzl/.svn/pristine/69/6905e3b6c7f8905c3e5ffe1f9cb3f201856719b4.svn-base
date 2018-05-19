<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>流程APP分组管理</title>

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
<!--javascript libray-->
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
					table.reload();
				} else {
					currentMenuSelectNode = node;
					reloadSelectUsersTable();
				}
			}
		});
	});

		
	//加载分组选择
	var currentMenuSelectNode = null;
	function reloadSelectUsersTable(){
		var table = mini.get("id_table");
		table.showColumn('isgrant');
		table.setParams({xmlFileName:"/jbpm/queryFlowPhoneGroup.xml"});
		table.setParams({sourceDesignerId:currentMenuSelectNode.id,workflownameparent:trim(currentMenuSelectNode.text.replace(/[\s]*-[\s]*1/g,''))});
		table.reload();
	}
	
	function distribute(phonegroupId, designerId){
		var url = "${pageContext.request.contextPath}/acl/addOrRemovePhoneGroup.acl";
		var params = {
			phonegroupId : phonegroupId,
			designerId : designerId
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
			title:'分组列表',
			id:'id_table',
			renderTo:'id_menu_main_content_div',
			width : globalClientWidth - widthAdd,//表格高度，整型数值
			height : globalClientHeight,//表格宽度，整型数值
			tools:['globalQuery'],
			xmlFileName:'/jbpm/flowPhoneGroup.xml',
			loadMode:'ajax',
			showPager:true,
			pageSize:50,
			columns:[
				{type:'indexcolumn'},
				{header:'记录编号',field:'id',visible : false},
				{
					header:'分组分配',
					field:'isgrant',
					visible : false,
					align:'center',
					renderer:function(e){
						var checked = "";
						var queryCondition = "";
						var ischeck = e.record.ischeck;
						var phonegroupId = e.record.id;
						var designerId=e.record.sourcedesignerid;
						if(ischeck == '0'){
	
							var html = "<input style='cursor:pointer;' type='checkbox'  onclick=\"distribute('" + phonegroupId+ "','" + designerId  + "');\"/>";
						}else{
	
							var html = "<input style='cursor:pointer;' type='checkbox' checked='checked'  onclick=\"distribute('" + phonegroupId+ "','" + designerId  + "');\"/>";
						}
						return queryCondition+html;
				}
				},
				{header:'111',field:'sourcedesignerid',visible : false},
				{header:'222',field:'ischeck',visible : false},
				{header:'分组名称',field:'groupname'},
				{header:'分组描述',field:'memo'},
				{header:'科目配置',field:'peizhi',
					 renderer:function(e){
						 var id = e.record.id;
							var islist=e.record.islist;
							return "<a href='javascript:void(0);' onclick='showFormValue(\"" + id+"\",\""+islist + "\")'>数据配置表 </a>";}}
			],
			params:{
				enabled : 1
			}
		});
	});
	function showFormValue(id,islist){
		var url=getRootPath()+"/workflow/jbpm-core/workflowFormValue.bi"; 
		if(islist==1){
			url=getRootPath()+"/workflow/jbpm-core/workflowListValue.bi"; 
		}
		mini.open({
	        url: url+"?id="+id+"&islist="+islist,
	        title: "数据表配置", width: 800, height: 500,
	        showModal: true,
	        showMaxButton: true
	    });
	}
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
