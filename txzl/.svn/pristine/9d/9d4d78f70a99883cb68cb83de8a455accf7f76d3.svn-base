<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>五级分类申请</title>
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
	var statusdata = [ {name : '',value : ''},{name : '未申请',value : '未申请'},{name : '审核中',value : "审核中"},{name : '审核通过',value : '审核通过'},{name : '已退回',value : '已退回'}];

	jQuery(function() {
		tenwa
				.createTable({
					id : "five_category_apply",
					renderTo : "id_table_container_five_category_apply",
					width : currentClientWidth,
					height : currentClientHeight,
					lazyLoad : false,
					title : "五级分类申请",
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
											+ "/acl/showFiveCategoryApplication.acl?opertype=add"
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

									var buttonFlag = 'edit';
									var url = getRootPath()
											+ "/acl/showFiveCategoryApplication.acl?opertype=edit&applyid="
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
											+ "/acl/deleteFiveCategoryApplication.acl";
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
								html : '提交审核',
								plain : true,
								iconCls : 'icon-ok',
								handler : function(miniTable, buttonText) 
								{
									var rows = miniTable.getSelected();
									if (rows==null) {
										mini.alert("请选择要操作的数据内容！");
										return false;
									};
									if(rows.counts=='0'){
										mini.alert("申请客户数为0，不允许提交审核！");
										return false;
									}; 
									var url = getRootPath()+ "/acl/showFiveCategoryApplicationOk.acl";
									var params = {};
									var nums =[];
									nums.push(rows.apply_number);
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
							}
							],
					queryButtonColSpan : 6,
					xmlFileName : '/eleasing/jsp/other/five_category_apply_detail_list.xml',
					
					params:{
						username:"${sessionScope.loginUser.realname}",
					},
					columns : [
							{type : 'indexcolumn'},
							{type : 'checkcolumn'},
							{field : 'id',header : 'id',visible : false},
							{field : 'viewdetails',
								header : '查看明细',
								renderer : function(e) {
								var id = e.record.id;
								return "<a href='javascript:showDetail(\""
										+ id + "\")'>"
										+ "查看明细" + "</a>";}
								},
							{field : 'apply_number',
								header : '申请编号',
								queryConfig:{}
								}, 
											{field : 'applyusername',header : '申请人',queryConfig:{}}, 
											{field : 'counts',header : '申请客户数'}, 
											{field : 'apply_date',header : '申请日期',queryConfig:{}}, 
											{field : 'apply_memo',header : '备注'}, 
											{field : 'apply_status',header : '申请状态',queryConfig:{
												
												newLine: true,
												valueField : 'value',
												textField : 'name',
												type:'combobox',
												data : statusdata
												}
											}]
				});
	});

	function showDetail(id) {
		var url = getRootPath()
				+ "/acl/showFiveCategoryApplication.acl?applyid="
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
	<div id="id_table_container_five_category_apply"></div>
</body>
</html>