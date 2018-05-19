<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Sheet表格配置</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript">
jQuery(function(){
	tenwa.createTable({
		id: "excel_table_config",
		renderTo: "id_table_excel_table_config_container",
		width: '100%',
		height: '100%',
		lazyLoad: false,
		entityClassName : 'com.tenwa.leasing.entity.excel.ExcelTableConfig',
		title: "",
		remoteOper : true,
		showPager: true,
		pageSize: 20,
		top : 50,
		tools : ['add', '-', 'edit', '-', 'remove'],
		xmlFileName: '/eleasing/jsp/sysmgr/sysdatamgr/excel_table_config.xml',
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
				field : 'tablename',
				header : 'table名',
				formEditorConfig : {
					labelWidth : 150
				}
			}, {
				field : 'tablecode',
				header : 'table编号',
				formEditorConfig : {
					labelWidth : 150
				}
			}, {
				field : 'startrownum',
				header : '表格开始行',
				formEditorConfig : {
					newLine : true,
					vtype : 'int'
				}
			}, {
				field : 'endrownum',
				header : '表格结束行',
				formEditorConfig : {
					vtype : 'int'
				}
			}, {
				field : 'startcolumnnum',
				header : '表格开始列',
				formEditorConfig : {
					newLine : true,
					vtype : 'int'
				}
			}, {
				field : 'endcolumnnum',
				header : '表格结束列',
				formEditorConfig : {
					vtype : 'int'
				}
			}, {
				field : 'tablewidth',
				header : '表格宽度',
				formEditorConfig : {
					newLine : true,
					colspan : 3
				}
			}, {
				field : 'columnsindex',
				header : '列索引配置',
				formEditorConfig : {
					labelText : "列索引配置<font color=red>(数字以逗号隔开，此项配置之后表格开始、结束列配置将不起效)</font>",
					newLine : true,
					type : 'textarea',
					width : '100%',
					height : 70,
					colspan : 3
				}
			}, {
				field : 'columnsconfig',
				header : '表格列配置',
				formEditorConfig : {
					type : 'textarea',
					colspan : 3,
					newLine : true,
					//labelText : "表格列配置<font color=red>(双击文本域编辑)</font>",
					width : '80%',
					height : 80,
					appendHtml : '<a class="mini-button" style="margin-left:20px;" plain="true" iconCls="icon-ok" onclick="editColumnsConfig">编辑</a>'
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
function editColumnsConfig(){
	var columnsconfigVal = mini.getbyName("columnsconfig").getValue();
	if(columnsconfigVal == ""){
		columnsconfigVal = "[]";
	}
	//console.info(row.columnsconfig);return;
	if(!mini.get("columns_config")){
		tenwa.createTable({
			id: "columns_config",
			renderTo: "id_table_columns_config_container",
			width: '100%',
			height: '100%',
			lazyLoad: false,
			title: "",
			pageSize: 20,
			editFormPopupWindowWidth:500,
			tools : ['add', '-', 'edit', '-', 'remove'],
			data : mini.decode(columnsconfigVal),
			columns: [
				{
					type : 'indexcolumn'
				}, {
					type : 'checkcolumn'
				}, {
					field : 'field',
					header : '字段标识',
					formEditorConfig : {
					}
				}, {
					field : 'header',
					header : '字段名字',
					formEditorConfig : {
					}
				}, {
					field : 'width',
					header : '字段所在列宽度',
					formEditorConfig : {
						newLine : true
					}
				}, {
					field : 'align',
					header : '数据对齐',
					formEditorConfig : {
						type : 'combobox',
						textField : 'text',
						valueField : 'value',
						data : [{text:'左对齐',value:'left'},{text:'居中',value:'center'},{text:'右对齐',value:'right'}]
					}
				}
			]
		});
	}else{
		var columns_config = mini.get("columns_config");
		columns_config.setData(mini.decode(columnsconfigVal));
		//columns_config.reload();
	}
	mini.get("id_win_columns_config").show();
}
function setColumnsConfigValue(){
	var columns_config_data = mini.get("columns_config").getData();
	mini.getbyName("columnsconfig").setValue(mini.encode(columns_config_data));
	hideMiniWindow('id_win_columns_config');
}
</script>
</head>
<body>
	<div id="id_table_excel_table_config_container" style="height: 100%;"></div>
	<div id="id_win_columns_config" class="mini-window" title="表格列配置" style="width:700px;height:500px;" showModal="true" showFooter="true" allowResize="true" allowDrag="true">
		<div id="id_table_columns_config_container" style="height: 90%;"></div>
		<div property="footer" style="text-align:center;padding:5px;padding-right:15px;">
			<a class="mini-button" plain="true" iconCls="icon-ok" onclick="setColumnsConfigValue()">确定</a>
			&nbsp;&nbsp;
			<a class="mini-button" plain="true" iconCls="icon-cancel" onclick="hideMiniWindow('id_win_columns_config')">取消</a>
		</div>
	</div>
</body>
</html>