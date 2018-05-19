<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>项目名称变更</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>

<script type="text/javascript">
	jQuery(function() {
		seajs
				.use(
						[ "js/apcomponent/aptable/aptable.js" ],
						function(ApTable) {
							new ApTable(
									{
										id : 'table_id1',
										renderTo: "id_table_id1",
										width : globalClientWidth,
										height : globalClientHeight,
										title : '项目名称变更',
										iconCls : 'icon-node',
										multiSelect : false,
										queryButtonColSpan : 4,
										queryButtonNewLine : false,
										showPager : true,
										//params:{projmanager:'${sessionScope.login_userid}'},
										xmlFileName : '/eleasing/workflow/proj/proj_name_change/proj_name_change_list.xml',
										tools : [ {
											html : '项目名称变更',
											plain : true,
											iconCls : 'icon-addfolder',
											handler : function(miniTable,
													buttonText) {
												var row = miniTable
														.getSelected();
												if (row) {
													var multiform = new mini.Form(
															"multiform");
													var multieditWindow = mini.get("multieditWindow");
													multiform.clear();
													multieditWindow.show();
													
												} else {
													mini
															.alert('请你选择需要修改项目名称的项目！');
													return false;
												}
											}
										} ],
										columns : [ {
											type : 'indexcolumn'
										}, {
											type : 'checkcolumn'
										}, {
											field : 'pdiid',
											header : 'pdiid',
											visible : false
										}, {
											field : 'pdiname',
											header : '项目名称(项目开发)'
										}, {
											field : 'piid',
											header : 'piid',
											visible : false
										}, {
											field : 'piname',
											header : '项目名称(项目审批)',
											queryConfig : {}
										}, {
											field : 'ciid',
											header : 'ciid',
											visible : false
										}, {
											field : 'cinumber',
											header : '合同编号',
											queryConfig : {}
										}, {
											field : 'ciname',
											header : '项目名称(合同审批)'
										} ]
									});
						});
	});
</script>
</head>

<body>
	<div id="id_table_id1" style="height: 100%"></div>
	<div id="multieditWindow" class="mini-window" title="项目名称修改"
		style="width: 350px; height: 100px;" showModal="true"
		allowResize="true" allowDrag="true">
		<div id="multiform">
			<table>
				<tr class="tr-projectsss-info tr-even">
					<td class="td-content-title" width="20%">项目名称：</td>
					<td class="td-content" width="30%"><input
						name="contract_info.project_name"  id="projectname"
						class="mini-textbox" label="项目名称" type="text"></td>
						<td><a class="mini-button" 
						onclick="submitMultiDataWindReport">&nbsp;&nbsp;确定&nbsp;&nbsp;</a>
					</td>
				</tr>

				<!-- <tr>
					
					<td><a class="mini-button" onclick='__userOperationClose'>&nbsp;&nbsp;取消&nbsp;&nbsp;</a>
					</td>
				</tr> -->
			</table>
		</div>
	</div>

</body>
<script>
	function submitMultiDataWindReport(e) {
		var miniTable=mini.get("table_id1");
		var tablevalue = mini.get("table_id1");
		var row = tablevalue.getSelected();
		var url = getRootPath() + "/acl/saveProjName.acl";
		var projectname = mini.get("projectname").getValue();
		var params = {};
		params["pdiid"] = row.pdiid;
		params["piid"] = row.piid;
		params["ciid"] = row.ciid;
		params["projectname"] = projectname;
		$.ajax({
			url : url,
			data : params,
			type : 'post',
			async : false,
			success : function(e) {
				mini.get(multieditWindow).hide();
				miniTable.reload();
			}
		});
		
		//__userOperationClose();
	};
	/* function __userOperationClose() {
		
	} */
</script> 
</html>
