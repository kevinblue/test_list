<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>tracywindyTable测试</title>
<!--css sheet-->
<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">
<style type="text/css">
html,bode{
overflow:hidden;
}
.biTian{ color:#ff0000;}
</style>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree/dtree.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/commonAttachmentFileUpload.js"></script>
<script type="text/javascript">
var all_width = document.documentElement.clientWidth;
var all_height = document.documentElement.clientHeight;

function showAddFormTable(){
	var sUrl="${pageContext.request.contextPath}/jmcs/building/form.bi?op=insert";
	var windowReturnValue = showModelFullScreenWindow(sUrl,"_blank");
}

function showModifyFormTable(){
	//alert('showModifyFormTable');
	var tableObj = getTracywindyObject("id_table_list_demo")
	var rowData = tableObj.getCheckedRowDatas()
	//alert(JsonUtil.encode(rowData));
	var czid=rowData[0].id;
	var sUrl="${pageContext.request.contextPath}/jmcs/building/form.bi?op=update&czid="+czid;
	var windowReturnValue = showModelFullScreenWindow(sUrl,"_blank");
}

function removeSelectedFormTable(){
	//alert('removeSelectedFormTable');
	var tableObj = getTracywindyObject("id_table_list_demo")
	var rowData = tableObj.getCheckedRowDatas()
	alert(JsonUtil.encode(rowData));
	var czid=rowData[0].id;
	alert(czid);
}
function doQuery()
{
	var queryText = trim($("#id_queryText").val());
	alert(queryText);
	var params = {
		queryText:queryText
	};
	testTable.setParams(params);
	testTable.reload();
}

jQuery(function(){
	
	var modify_op = "<a href='javascript:void(0);' onclick='showModifyFormTable();'>修改</a>";
	var separator_op ="&nbsp;&nbsp;&nbsp;&nbsp;"
	var del_op = "<a href='javascript:void(0);' onclick='deleteFormTable();'>删除</a>"
	var op_bar = modify_op +separator_op+del_op;
	
	var table_list = new tracywindyTable({
			renderTo:'id_table_list_demo_container',
			id:'id_table_list_demo',
			width:all_width,
			height:all_height,
			title:'数据字典',
			isCheck:true,
			isRank:true,
			isExcel:true,
			pageSize:10,
			checkOnly:true,
			border:false,
			//toolsLeftMargin:all_width-90,
			/*columns:[
				{name:'nodeCode',col_mapping:'nodecode',header:'节点代码',align:'left'},
				{name:'nodeTitle',col_mapping:'nodetitle',header:'名称',align:'left'},
				{name:'parentid',col_mapping:'parentid',header:'父节点id',align:'left'},
				{name:'orderflag',col_mapping:'orderflag',header:'排序标记',align:'left'},
				{name:'create_date',col_mapping:'create_date',header:'创建日期',align:'left'},
				{name:'creator',col_mapping:'creator',header:'创建者',align:'left'},
				{name:'modify_date',col_mapping:'modify_date',header:'修改日期',align:'left'},
				{name:'modificator',col_mapping:'modificator',header:'修改者',align:'left'},
				{name:'操作',header:'修改',align:'left',
					renderer:function(value,tableObj,columnName,columnIndex,rowData)
					{return op_bar;
					}
				}
			],*/
			xmlFileName:'/jmcs/mis/dict_list.xml',
			isFit:false,
			showHeader:true,
			loadMode:'ajax',
			isPage:true,
			checkType:'radio',
			queryArea:'名称：<input id="id_queryText" value=""/><input type="button" value="查询" onclick="doQuery();"/>',
			tools:[{
					html:'新增1',
					iconCls:'icon-add',
					handler:function(){
						showAddFormTable();
					}
				},{
					html:'新增2',
					iconCls:'icon-add',
					handler:function(){
						showAddFormTable2();
					}
				},{
					html:'修改',
					iconCls:'icon-mod',
					handler:function(){
						showModifyFormTable();
					}
				},{
					html:'复制',
					iconCls:'icon-add',
					handler:function(){
						copySelectedFormTable();
					}
				},{
					html:'删除',
					iconCls:'icon-remove',
					handler:function(){
						removeSelectedFormTable();
					}
				}
			],
			callBack:function(){
				/*
				this.setColumnHidden(true,'hassub');
				this.setColumnHidden(true,'iscategory');
				this.setColumnHidden(true,'isproperty');
				this.setColumnHidden(true,'orderflag');
				this.setColumnHidden(true,'useflag');
				this.setColumnHidden(true,'nodeType');
				this.setColumnHidden(true,'nodeType');
				this.setColumnHidden(true,'nodeType');
				this.setColumnHidden(true,'nodeType');
				this.setColumnHidden(true,'nodeType');
				this.setColumnRenderer(function(value,tableObj,columnName,columnIndex,rowData){
					var oper = "<a href='javascript:void(0);' onclick='doRowClick(\""+tableObj.id+"\","+rowData.rowIndex+")'>操作渲染</a>";
					return oper;
				},'operation_bar');
				*/
			},
			params:{
				parentid:""
			}
		 });
	
	function doRowClick(tableId,rowIndex)
	{
		var currentTable = getTracywindyObject(tableId);
		//var currentTable = getTracywindyTable(tableId);
		var rowData = currentTable.getRowDataAt(rowIndex);
		alert(JsonUtil.encode(rowData));
	}
});  
</script>
</head>
<body>
<div id="id_table_list_demo_container"></div>

<div id="id_table_add_demo_container" class="easyui-window add_form_class" closed="true" modal="true" title="添加数据" style="display:none;width:600px;height:500px;padding-top:10px;text-align:center;padding:0px;">
	<center><div style="width:90%;">
	<table align="center">
	<tr style='height:20px;'><td></td></tr>
	<tr class="tr-odd">
		<td class="td-content-title"><span class="biTian">*</span>租赁物名称：</td>
		<td class="td-content"><div id="id_leasing_product_detail"></div></td>
	</tr>
	<tr class="tr-even">
		<td class="td-content-title"><span class="biTian">*</span>型号：</td>
		<td class="td-content"><input type="text" id="id_patternNumber" class="td-content-input td-content-readonly" /></td>
	</tr>

	<tr style='text-align:right;padding-top:30px;height:40px;'>
		<td colspan='2'>
		<input class="adviseButton" type="button" value="确定" onclick='addOrModifyLeasingProductDetail();' style="cursor:pointer;width:60px;font-size: 12px; background: #CCCCFF; border-width: thin thin thin thin;"/>
		<input class="adviseButton" type="button" value="取消" onclick='jQuery("#id_table_add_demo_container").window("close");' style="cursor:pointer;margin-left:20px;width:60px;font-size: 12px; background: #CCCCFF; border-width: thin thin thin thin;"/>
		</td>
	</tr>
	</table>
	</div></center>
</div>
</body>
</html>