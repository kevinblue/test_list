<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Sheet单元格配置</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
jQuery(function(){
	tenwa.createTable({
		id: "excel_cell_config",
		renderTo: "id_table_excel_cell_config_container",
		width: '100%',
		height: '100%',
		lazyLoad: false,
		entityClassName : 'com.tenwa.leasing.entity.excel.ExcelCellConfig',
		title: "",
		remoteOper : true,
		showPager: true,
		pageSize: 20,
		editFormPopupWindowWidth:500,
		tools : ['add', '-', 'edit', '-', 'remove'],
		xmlFileName: '/eleasing/jsp/sysmgr/sysdatamgr/excel_cell_config.xml',
		params : {
			sheetconfigid : "${param.sheetconfigid}"
		},
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
				field : 'sheetconfigid',
				header : 'Sheet配置ID',
				visible : false,
				formEditorConfig : {
					defaultValue : "${param.sheetconfigid}"
				}
			}, {
				field : 'cellname',
				header : 'cell名称',
				formEditorConfig : {
					labelWidth : 120
				}
			}, {
				field : 'cellcode',
				header : 'cell编号',
				formEditorConfig : {
					labelWidth : 120
				}
			}, {
				field : 'rowindex',
				header : '行坐标',
				formEditorConfig : {
					newLine : true,
					vtype : 'int'
				}
			}, {
				field : 'columnindex',
				header : '列坐标',
				formEditorConfig : {
					vtype : 'int'
				}
			}, {
				field : 'celldisplayformat',
				header : '数字显示格式',
				formEditorConfig : {
					newLine:true,
					type:'combobox',
					data:[{text:'percent'},{text:'currency'},{text:'int'}],
					valueField:'text',
					textField:'text',
					showNullItem:true
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
<div id="id_table_excel_cell_config_container" style="height: 100%;"></div>
</body>
</html>