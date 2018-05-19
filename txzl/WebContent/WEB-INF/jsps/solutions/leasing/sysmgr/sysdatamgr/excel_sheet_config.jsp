<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Excel 导入配置</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
jQuery(function(){
	tenwa.createTable({
		id: "excel_sheet_config",
		renderTo: "id_table_excel_sheet_config_container",
		width: '100%',
		height: '100%',
		lazyLoad: false,
		entityClassName : 'com.tenwa.leasing.entity.excel.ExcelSheetConfig',
		title: "Excel 导入配置",
		remoteOper : true,
		showPager: true,
		pageSize: 20,
		editFormPopupWindowWidth:500,
		tools : ['add', '-', 'edit', '-', 'remove', '-', {
				html : 'Sheet单元格配置',
				plain : true,
				iconCls : 'icon-addfolder',
				handler : function(miniTable, buttonText){
					var row = miniTable.getSelected();
					if(row){
						/* mini.open({
							url : "excel_cell_config.bi?sheetconfigid=" + row.id,
							title : "Sheet单元格配置",
							width : 900,
							height : 600,
							showMaxButton : true,
							ondestroy : function(action){
								mini.get("excel_sheet_config").reload();
							}
						}); */
						openFullScreenWindow("excel_cell_config.bi?sheetconfigid=" + row.id);
					}else{
						mini.alert("请选择要维护单元格配置的数据！");
					}
				}
			}, '-', {
				html : 'Sheet表格配置',
				plain : true,
				iconCls : 'icon-addfolder',
				handler : function(miniTable, buttonText){
					var row = miniTable.getSelected();
					if(row){
						/* mini.open({
							url : "excel_table_config.bi?sheetconfigid=" + row.id,
							title : "Sheet表格配置",
							width : 900,
							height : 600,
							showMaxButton : true,
							ondestroy : function(action){
								mini.get("excel_sheet_config").reload();
							}
						}); */
						openFullScreenWindow("excel_table_config.bi?sheetconfigid=" + row.id);
					}else{
						mini.alert("请选择要维护表格配置的数据！");
					}
				}
		}],
		xmlFileName: '/eleasing/jsp/sysmgr/sysdatamgr/excel_sheet_config.xml',
		columns: [
			{
				type : 'indexcolumn'
			}, {
				type : 'checkcolumn'
			}, {
				field : 'id',
				header : 'id',
				visible : false
			}, {
				field : 'importtype',
				header : '导入类型',
				formEditorConfig : {
					lableWidth : 20
				}
			}, {
				field : 'sheetname',
				header : 'sheet名',
				formEditorConfig : {
					lableWidth : 20
				}
			}, {
				field : 'sheetcode',
				header : 'sheet编号',
				formEditorConfig : {
					newLine : true
				}
			}, {
				field : 'orderindex',
				header : '排序位置',
				formEditorConfig : {
					vtype : 'int'
				}
			}, {
				field : 'creatorname',
				header : '创建人',
				formEditorConfig : {
					newLine : true,
					readonly : true
				}
			}, {
				field : 'createdate',
				header : '创建时间',
				dateFormat : 'yyyy-MM-dd hh:mm:ss',
				formEditorConfig : {
					type : 'text',
					readonly : true
				}
			}
		]
	});
});
</script>
</head>
<body>
<div id="id_table_excel_sheet_config_container" style="height: 100%;"></div>
</body>
</html>