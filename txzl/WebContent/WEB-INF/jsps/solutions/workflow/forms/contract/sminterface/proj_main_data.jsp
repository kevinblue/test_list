<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>项目主数据</title>
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
		id: "proj_main_data",
		renderTo: "id_proj_main_data",
		width: currentClientWidth,
		height: currentClientHeight,
		lazyLoad: false,
		title: "项目主数据",
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
					url : '${pageContext.request.contextPath}/acl/updateProjMainData.acl',
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
		xmlFileName: '/eleasing/workflow/contract/SM_interface/proj_main_data.xml',
		columns: [
			{type: 'indexcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field:'项目编号', header:'项目编号',width:170},	
			{field:'proj_name', header:'项目名称',width:130,queryConfig:{}},
			{field:'项目状态', header:'项目状态'},
			{field:'项目阶段', header:'项目阶段'},
			{field:'项目容量', header:'项目容量(KW)'},
			{field:'机组数量', header:'机组数量'},
			{field:'维度类型', header:'维度类型'},
			{field:'纬度', header:'纬度'},
			{field:'经度类型', header:'经度类型'},
			{field:'经度', header:'经度'},
			{field:'片区', header:'片区'},
			{field:'项目名称', header:'风场名称',width:200,queryConfig:{}},
			{field:'项目经理', header:'项目经理'},
			{field:'业主单位', header:'业主单位',width:200,queryConfig:{}},
			{field:'合同主体', header:'合同主体'},
			{field:'合同签订日期', header:'合同签订日期'},
			{field:'质保期开始日期', header:'质保期开始日期'},
			{field:'质保期时长', header:'质保期时长'},
			{field:'风场可用率', header:'风场可用率'},
			{field:'机组可用率', header:'机组可用率'},
			{field:'单台功率曲线', header:'单台功率曲线'},
			{field:'风场功率曲线', header:'风场功率曲线'},
			{field:'功率曲线说明', header:'功率曲线说明'}
		]
	});
});

</script>
</head>
<body>
<div id="id_proj_main_data"></div>
</body>
</html>