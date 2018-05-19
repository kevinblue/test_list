<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>接货主数据</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<script type="text/javascript">
var currentClientWidth = document.documentElement.clientWidth;
var currentClientHeight = document.documentElement.clientHeight;
var loadMask = null;
jQuery(function(){
	tenwa.createTable({
		id: "contract_sign",
		renderTo: "id_table_container_contract_sign",
		width: currentClientWidth,
		height: currentClientHeight,
		lazyLoad: false,
		title: "接货主数据",
		remoteOper : false,
		showPager: true,
		pageSize: 20,
		tools: [{
			html : '同步',
			plain : true,//背景透明
			iconCls : 'icon-addfolder',//按钮图标类
			handler : function (miniTable, buttonText) {
				mini.confirm("确认同步么？","同步", function(action){
					mini.mask({
						el: document.body,
						cls: 'mini-mask-loading',
						html: '数据加载中，请稍后...'
					});
					if(action=="ok"){
				$.ajax({
					url : '${pageContext.request.contextPath}/acl/updatePickupMainData.acl',
					type: "post",
				    cache: false, 
				    async : false,
					success:function(res){
						if(res=='成功'){
							mini.alert("同步成功！");
							mini.unmask(document.body);
							miniTable.reload();
						}else{
							mini.alert("刷新失败！");
							miniTable.reload();
						}
					   },
					failure : function(res) {
						mini.unmask(document.body);
						mini.alert("权限同步失败！");
						miniTable.reload();
					   }
				     });
					}else{
						mini.unmask(document.body);
						miniTable.reload();
						
					}
						})
					}
		}],
		queryButtonColSpan: 4,
		queryButtonNewLine:false,
		xmlFileName: '/eleasing/workflow/contract/SM_interface/pickup_main_data.xml',
		columns: [
			{type: 'indexcolumn'},
			{field: 'id_', header: 'id_', visible: false},
			{field:'风电场编号', header:'风场编号',width:170,queryConfig:{}},
			{field:'proj_name', header:'项目名称',width:130,queryConfig:{}},
			{field:'存放机位', header:'存放机位',width:130},	
			{field:'存放位置', header:'存放位置'},	
			{field:'物料编码', header:'物料编码'},	
			{field:'物料名称', header:'物料名称',queryConfig:{}},	
			{field:'批次号', header:'批次号'},	
			{field:'序列号', header:'序列号'},	
			{field:'数量', header:'数量'},	
			{field:'单位', header:'单位'},	
			{field:'接货工单号', header:'接货工单号'},	
			{field:'片区', header:'片区'},
			{field:'接货开始时间', header:'接货开始时间'},
			{field:'接货结束时间', header:'接货结束时间'},
			{field:'id', header:'ID'},	 
			{field:'sysmodtime', header:'SYSMODTIME'},
		]
	});
});

</script>
</head>
<body>
<div id="id_table_container_contract_sign"></div>
</body>
</html>