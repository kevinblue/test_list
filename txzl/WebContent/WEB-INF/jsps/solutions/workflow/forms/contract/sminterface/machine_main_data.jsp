<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>机组主数据</title>
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
		title: "机组主数据",
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
					url : '${pageContext.request.contextPath}/acl/updateMachineMainData.acl',
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
		xmlFileName: '/eleasing/workflow/contract/SM_interface/machine_main_data.xml',
		columns: [
			{type: 'indexcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field:'风场编号', header:'风场编号',width:170,queryConfig:{}},	
			{field:'proj_name', header:'项目名称',width:130,queryConfig:{}},
			{field:'机组编号', header:'机组编号',width:150,queryConfig:{}},	
			{field:'风场名称', header:'风场名称',width:200,queryConfig:{}},	
			{field:'片区', header:'片区'},	
			{field:'项目部', header:'项目部',width:200},	
			{field:'项目经理', header:'项目经理'},	
			{field:'机组状态', header:'机组状态'},	
			{field:'运行机位号', header:'运行机位号'},	
			{field:'SCADA风机编号', header:'SCADA风机编号'},	
			{field:'SCADA风场编号', header:'SCADA风场编号'},	
			{field:'机组容量', header:'机组容量'},	
			{field:'机型', header:'机型'},	
			{field:'轮毂高度', header:'轮毂高度'},	
			{field:'IP地址', header:'IP地址'},	
			{field:'子网掩码', header:'子网掩码'},	
			{field:'基础开挖时间', header:'基础开挖时间'},	
			{field:'基础浇筑时间', header:'基础浇筑时间'},	
			{field:'箱变安装时间', header:'箱变安装时间'},	
			{field:'接货开始时间', header:'接货开始时间'},	
			{field:'接货结束时间', header:'接货结束时间'},	
			{field:'吊装开始时间', header:'吊装开始时间'},	
			{field:'吊装结束时间', header:'吊装结束时间'},	
			{field:'上电时间', header:'上电时间'},	
			{field:'静调开始时间', header:'静调开始时间'},	
			{field:'静调结束时间', header:'静调结束时间'},	
			{field:'动调开始时间', header:'动调开始时间'},	
			{field:'动调结束时间', header:'动调结束时间'},	
			{field:'试运行开始时间', header:'试运行开始时间'},	
			{field:'试运行结束时间', header:'试运行结束时间'},	
			{field:'预验收通过时间', header:'预验收通过时间'},	
			{field:'在建转质保时间', header:'在建转质保时间'},	
			{field:'主控程序版本号', header:'主控程序版本号'},	
			{field:'变流版本号', header:'变流版本号'},	
			{field:'变桨版本号', header:'变桨版本号'},	
			{field:'初始化文件版本号', header:'初始化文件版本号'},	
			{field:'变桨类型', header:'变桨类型'},	
			{field:'变流类型', header:'变流类型'},	
			{field:'冷却', header:'冷却'},	
			{field:'叶片', header:'叶片'},	
			{field:'总线类型', header:'总线类型'},	
			{field:'塔筒', header:'塔筒'},	
			{field:'基础形式', header:'基础形式'},	
			{field:'塔架连接方式', header:'塔架连接方式'},	
			{field:'塔架图号', header:'塔架图号'},	
			{field:'预计出质保时间', header:'预计出质保时间'},	
			{field:'最终交接结束时间', header:'最终交接结束时间'},	
			{field:'吊装过程验收开始时间', header:'吊装过程验收开始时间'},	
			{field:'吊装过程验收结束时间', header:'吊装过程验收结束时间'},
			{field:'力矩验收开始时间', header:'力矩验收开始时间'},	
			{field:'力矩验收结束时间', header:'力矩验收结束时间'},	
			{field:'接线开始时间', header:'接线开始时间'},	
			{field:'接线结束时间', header:'接线结束时间'},	
			{field:'整体验收开始时间', header:'整体验收开始时间'},	
			{field:'整体验收结束时间', header:'整体验收结束时间'},	
			{field:'内部验收开始时间', header:'内部验收开始时间'},	
			{field:'内部验收结束时间', header:'内部验收结束时间'},	
			{field:'预验收开始时间', header:'预验收开始时间'},	
			{field:'最终交接开始时间', header:'最终交接开始时间'},
			{field:'最终交接验收开始时间', header:'最终交接验收开始时间'},
			{field:'最终交接验收结束时间', header:'最终交接验收结束时间'},
			{field:'最终出质保时间', header:'最终出质保时间'},
			{field:'系统创建时间', header:'系统创建时间'}
		]
	});
});

</script>
</head>
<body>
<div id="id_table_container_contract_sign"></div>
</body>
</html>