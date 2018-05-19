<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>发起资产网络监控上报</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/seajs/sea.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
	var currentClientWidth = document.documentElement.clientWidth;
	var currentClientHeight = document.documentElement.clientHeight;

	jQuery(function() {
		tenwa
				.createTable({
					id : "onhire_contract_change",
					renderTo : "id_table_container_onhire_contract_change",
					width : currentClientWidth,
					height : currentClientHeight,
					lazyLoad : false,
					title : "资产网络监控申请",
					remoteOper : false,
					showPager : true,
					pageSize : 20,
					tools : [
							{
								html : '新增',
								plain : true,
								iconCls : 'icon-addfolder',
								handler : function(miniTable, buttonText) {
									var buttonFlag = 'add';
									var url = getRootPath()
											+ "/acl/showAssetsNetMonitorApplication.acl?opertype=add"
									var sheight = window.screen.availHeight - 30;
									var swidth = window.screen.availWidth - 10;
									var winoption = "left=0px,top=0px,height="
											+ sheight
											+ "px,width="
											+ swidth
											+ "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
									window.open(url, '_blank', winoption);
								}
							},
							"-",
							{
								html : '修改',
								plain : true,
								iconCls : 'icon-edit',
								handler : function(miniTable, buttonText) {
									var rows = miniTable.getSelecteds();
									if (rows.length == 0) {
										mini.alert("请选择要修改的条目！");
										return false;
									}
									var applystatus=rows[0].apply_status;
									if (applystatus!='未审核') {
										mini.alert("该申请" + rows[0].apply_status
												+ "无法修改！");
										return false;
									}

									var buttonFlag = 'add';
									var url = getRootPath()
											+ "/acl/showAssetsNetMonitorApplication.acl?opertype=edit&applyid="
											+ rows[0].id;
									var sheight = window.screen.availHeight - 30;
									var swidth = window.screen.availWidth - 10;
									var winoption = "left=0px,top=0px,height="
											+ sheight
											+ "px,width="
											+ swidth
											+ "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
									window.open(url, '_blank', winoption);
								}
							},
							"-",
							{
								html : '删除',
								plain : true,
								iconCls : 'icon-remove',
								handler : function(miniTable, buttonText) {
									var rows = miniTable.getSelecteds();
									if (rows.length == 0) {
										mini.alert("请选择要删除的条目！");
										return false;
									}
									if (rows[0].apply_status != "未审核") {
										mini.alert("该申请" + rows[0].apply_status
												+ "无法删除！");
										return false;
									}

									var url = getRootPath()
											+ "/acl/deleteAssetsNetMonitorApplication.acl";
									var params = {};
									params["applyid"] = rows[0].id;
									$.ajax({
										url : url,
										data : params,
										type : 'post',
										async : false,
										success : function(e) {
											miniTable.reload();
										}
									});
								}
							},
							'-',
							{
								html : '发起资产网络监控上报',
								plain : true,
								iconCls : 'icon-addfolder',
								handler : function(miniTable, buttonText) {
									var row = miniTable.getSelected();
								    if(row.apply_status!="未审核"){
											mini.alert("该申请"+row.apply_status+"无法发起流程！");
											return false;
									} 
									if (row) {										
										var attachmentParams = "applyid="+row.id+"&opertype=edit";
										startProcess("网络资产监控-1",attachmentParams);
									} else {
										mini.alert('请你选择需要发起的流程数据！！！');
									}
								}
							}, ],
					queryButtonColSpan : 2,
					xmlFileName : '/eleasing/jsp/other/get_assets_apply_detail_list.xml',
					columns : [
							{
								type : 'indexcolumn'
							},
							{
								type : 'checkcolumn'
							},
							{
								field : 'id',
								header : 'id',
								visible : false
							},
							{
								field : 'viewdetails',
								header : '查看明细',
								renderer : function(e) {
									var id = e.record.id;
									return "<a href='javascript:showDetail(\""
											+ id + "\")'>"
											+ "查看明细" + "</a>";
								}
							},
							{
								field : 'apply_number',
								header : '申请编号',
								queryConfig:{}
							}, {
								field : 'applyusername',
								header : '资产专员'
								
							}, {
								field : 'counts',
								header : '异常客户数',
									
							}, {
								field : 'apply_date',
								header : '申请日期',
								queryConfig:{}
							}, {
								field : 'apply_memo',
								header : '备注'
							}, {
								field : 'apply_status',
								header : '申请状态',
								queryConfig:{
									type : 'combobox',
									data:[{id:'',text:''},{id:'未审核',text:'未审核'},{id:'审核中',text:'审核中'}]
								}
							} ]
				});
	});

	function showDetail(id) {
		var url = getRootPath()
				+ "/acl/showAssetsNetMonitorApplication.acl?applyid="
				+ id + "&opertype=view"
		var sheight = window.screen.availHeight - 30;
		var swidth = window.screen.availWidth - 10;
		var winosption = "left=0px,top=0px,height="
				+ sheight
				+ "px,width="
				+ swidth
				+ "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
		window.open(url, '_blank', winosption);
	}
</script>
</head>
<body>
	<div id="id_table_container_onhire_contract_change"></div>
</body>
</html>