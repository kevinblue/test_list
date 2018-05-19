<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if(isViewHistoryTask){showTools = false};
	tenwa.createTable({
		id: "rent_red_detail",
		renderTo: "id_table_rent_red_detail",
		width: '100%',
		height: '100%',
		title: "",
		remoteOper : false,
		showPager: false,
		multiSelect: true,
		lazyLoad: true,
		showToolbar: showTools,
		data: JsonUtil.decode('<mini:param  name="json_rent_red_detail_str" defaultValue="[]"/>'),
		tools: ['edit', '-', {
			html: '撤销',
			plain: true,
			iconCls: 'icon-ok',
			handler: function(miniTable, buttonText){
				var selectedDatas = miniTable.getSelecteds();
				if(selectedDatas.length == 0){
					mini.alert("请先选择要操作的数据！");
					return false;
				}
				miniTable.removeRows(selectedDatas);
			}
		}],
		columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'planlist', header: '计划期项',formEditorConfig:{labelWidth:100,readOnly:true}},
				{field: 'ebdataid', header: '网银编号',formEditorConfig:{labelWidth:100,readOnly:true}},
				{field: 'hirelist', header: '回笼期项',formEditorConfig:{newLine:true,readOnly:true}},
				{field: 'balancemode', header: '结算方式',visible: false,formEditorConfig:{readOnly:true}},
				{field: 'balancemodename', header: '结算方式',formEditorConfig:{readOnly:true}},
				{field: 'hiredate', header: '回笼日期',type:"date",format:"yyyy-MM-dd",formEditorConfig:{newLine:true,readOnly:true}},
				{field: 'accountingdate', header: '会计处理日',type:"date",format:"yyyy-MM-dd",formEditorConfig:{
					type: 'date',
					vtype: 'date',
					format: 'yyyy-MM-dd'
				}},
				{field: 'rent', header: '回笼租金',summary : true,dataType : "currency",formEditorConfig:{newLine:true,readOnly:true}},
				{field: 'corpus', header: '回笼本金',summary : true,dataType : "currency",formEditorConfig:{readOnly:true}},
				{field: 'interest', header: '回笼租息',summary : true,dataType : "currency",formEditorConfig:{newLine:true,readOnly:true}},
				{field: 'penalty', header: '回笼罚息',summary : true,dataType : "currency",formEditorConfig:{readOnly:true}},
				{field: 'rentadjust', header: '租金调整',summary : true,dataType : "currency",formEditorConfig:{newLine:true,readOnly:true}},
				{field: 'corpusadjust', header: '本金调整',summary : true,visible:false,dataType : "currency"},
				{field: 'interestadjust', header: '租息调整',summary : true,dataType : "currency",formEditorConfig:{readOnly:true}},
				{field: 'penaltyadjust', header: '罚息调整',summary : true,dataType : "currency",formEditorConfig:{newLine:true,readOnly:true}},
				{field: 'invoiceno', header: '单据号',formEditorConfig:{readOnly:true}},	
				{field: 'ownbank', header: '本方银行',formEditorConfig:{newLine:true,readOnly:true}},
				{field: 'ownaccount', header: '本方银行账户',formEditorConfig:{readOnly:true}},
				{field: 'ownnumber', header: '本方银行账号',formEditorConfig:{newLine:true,readOnly:true}},
				{field: 'hireobject', header: '付款人',formEditorConfig:{readOnly:true}},
				{field: 'hirebank', header: '对方银行',formEditorConfig:{newLine:true,readOnly:true}},
				{field: 'hireaccout', header: '对方银行账户',formEditorConfig:{readOnly:true}},
				{field: 'hirenumbr', header: '对方银行账号',formEditorConfig:{newLine:true,readOnly:true,colspan:3}},
				{field: 'memo', header: '备注',formEditorConfig:{colspan : 3,
					width: 400,
					height:70, type: 'textarea',newLine:true}}		
		]
	});
});
</script>
<div id="id_table_rent_red_detail" style="width: 100%;height: 100%;"></div>