<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

<script type="text/javascript">
var id="<%= request.getParameter("id")%>";

jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:'table_id1',
			renderTo: 'id_table_container_1',
			width:globalClientWidth-9,
			height:globalClientHeight-200,
			iconCls:'icon-node',
			multiSelect:false,
			hiddenQueryArea:false,//是否将查询区域折叠起来
			queryButtonColSpan:2,
			queryButtonNewLine:false,
			showPager:true,
			xmlFileName:'/docadd/fundput_file_list.xml',
			allowCellEdit:true,
			allowCellSelect:true,
			params:{
				contractid:id
			},
/* 			tools:[{
				html : '保存',
				plain : true,
				iconCls : 'icon-ok',
				handler : function(miniTable, buttonText) {
					var checkedRowDatas = miniTable.getSelecteds();
					
				}
			}], */
			columns:[ 
			    {type:'indexcolumn'},
				{type:'checkcolumn'},  
				{field:'id',header:'id',visible:false},
				{field:'doctype',header:'文件类型'},
				{field:'docname',header:'文件名称'},
				{field:'issubmit',header:'是否提交',	
					editor:{
						width:'100%', 
						type:'combobox',
						showNullItem:true,
						data:[{'text':'√'},{'text':'N/A'}],
						textField:'text',
						valueField:'text',
						allowInput:false
					}
				},
				{field:'docnum',header:'份数',
					editor:{
						type:'textbox'
					}},
				{field:'pagenum',header:'张数',
					editor:{
						type:'textbox'
					}},
				{field:'docmemo',header:'备注',
					editor:{
						type:'textbox'
					}},
				{field:'statusname',header:'状态'}
			]
		});
	});
});
</script>
<div id="id_table_container_1"></div>

