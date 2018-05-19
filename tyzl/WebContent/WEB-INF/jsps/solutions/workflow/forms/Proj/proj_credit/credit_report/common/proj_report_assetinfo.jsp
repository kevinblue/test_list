<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('<mini:param name="isView"/>' == 'true'||isViewHistoryTask==true||reportShowFlag==false){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "report_assetinfo",
			renderTo: "id_table_report_assetinfo",
			width: globalClientWidth - 20,
			height: 360,
			lazyLoad: false,
			title: "资产信息",
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: false,
			showToolbar: showTools,
			tools: ['add', '-', 'edit', '-', 'remove'],
			data: JsonUtil.decode('<mini:param  name="proj_report_assetinfo_str" defaultValue="[]"/>'),
			columns: [
				{type:'indexcolumn'},
				{type:'checkcolumn'},
				{field:'id', header:'id', visible:false},
				{field: 'editor', header:'资产名称',
					     formEditorConfig:{
				               required:true,
				             labelWidth:100,
				              maxLength:120}},
				{field:'explain',header:'发票号',
				  		 formEditorConfig:{
							   required:true,
							 labelWidth:100,
							  maxLength:120}},
				{field:'confirmtype',header:'证明类型',
						 formEditorConfig:{
							    newLine: true}},
				{field:'buyyear',header:'购买年限',
						 formEditorConfig:{}},
				{field:'asset',header:'金额',
						 formEditorConfig:{
							     newLine:true,
							     colspan:3}},
				{field:'bond',header:'负债情况',
						 formEditorConfig:{
									type:'textarea',
								 newLine:true,
								 colspan:3,
					              height:70,
					               width:487}},
				{field:'print',header:'陪同客户打印',
					   	formEditorConfig:{
								 newLine:true,
								   width:487,
								 colspan:3}},
				{field:'remark',header:'备注',
						formEditorConfig:{
									type:'textarea',
							     newLine:true,
							     colspan:3,
					              height:70,
					               width:487}}
			]	
		});
	});
});

</script>

<div id="id_table_report_assetinfo"></div>


