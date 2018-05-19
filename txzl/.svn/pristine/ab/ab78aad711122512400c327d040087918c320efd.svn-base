<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>资产巡视计划审核</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript">
	var currentClientWidth = document.documentElement.clientWidth;
	var currentClientHeight = document.documentElement.clientHeight;
	jQuery(function() {
		tenwa
				.createTable({
					id : "asset_mng",
					renderTo : "id_table_container_asset_mng",
					width : currentClientWidth,
					height : currentClientHeight,
					lazyLoad : false,
					title : "资产巡视计划审核",
					remoteOper : false,
					showPager : true,
					pageSize : 20,
					multiSelect :true,
					tools : [
							 
							{
								html : '审核通过',
								plain : true,
								iconCls : 'icon-ok',
								handler : function(miniTable, buttonText) {
									var rows = miniTable.getSelecteds();
									if (rows.length == 0) {
										mini.alert("请选择要操作的数据内容！");
										return false;
									}

									var url = getRootPath()+ "/acl/okAssetsReview.acl";
									var params = {};
									var nums =[];
									for(var i=0;i<rows.length;i++){
										nums.push(rows[i].apply_number);
									}
									var x = nums.join(",");
									params["applynumbers"] = x;
									$.ajax({
										url : url,
										data : params,
										type : 'post',
										async : false,
										success : function(e) {
											if(e=="yes")
												mini.alert("操作成功!");
											miniTable.reload();
											miniTable.deselectAll(false);
										}
									});
								}
							},'-',
							{
								html : '退回',
								plain : true,
								iconCls : 'icon-exportExcel',
								handler : function(miniTable, buttonText) 
								{
									var rows = miniTable.getSelecteds();
									if (rows.length == 0) {
										mini.alert("请选择要操作的数据内容！");
										return false;
									}
									var url = getRootPath()+ "/acl/backAssetsReview.acl";
									var params = {};
									var nums =[];
									for(var i=0;i<rows.length;i++){
										nums.push(rows[i].apply_number);
									}
									var x = nums.join(",");
									
									params["applynumbers"] = x;
									$.ajax({
										url : url,
										data : params,
										type : 'post',
										async : false,
										success : function(e) {
											if(e=="yes")
												mini.alert("操作成功!");
											miniTable.reload();
											miniTable.deselectAll(false);
										}
									});
								 }
							}],
					queryButtonColSpan: 8,
					queryButtonNewLine:true,
					xmlFileName : '/eleasing/jsp/asset/assets_mng_list.xml',
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
								width:55,
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
								width:55
							}, {
								field : 'applyusername',
								header : '资产主管',
								width:55,
								queryConfig:{}
							}, {
								field : 'counts',
								header : '申请客户数',
								width:55
							}, {
								field : 'apply_date',
								header : '申请日期',
								width:55,
								queryConfig:{type:'date',format:'yyyy-MM'}
							}, {
								field : 'apply_status',
								header : '申请状态',
								width:55,
								queryConfig:{type:'combobox',
									readOnly : false,
									data : localEnabled,
									type : 'combobox',	
								}
							},
							{
								field : 'apply_memo',
								header : '备注'
							},]
				});
	});
var localEnabled =[
                   {id : '',text : ''},
                   {id : '未审核',text : '未审核'},
                   {id : '审核中',text : '审核中'},
                   {id : '审核通过',text : '审核通过'}
                   ];
	function showDetail(id) {
		var url = getRootPath()
				+ "/acl/showAssetsMng.acl?applyid="
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

<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
</head>
<body>
	<div id="id_table_container_asset_mng"></div>
</body>
</html>