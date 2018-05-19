<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true||reportShowFlag==false){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "proj_report_main_product",
			renderTo: "id_table_proj_report_main_product",
			width: globalClientWidth - 20,
			height: 200,
			lazyLoad: false,
			title: "主要产品及产销量表",
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: false,
			showToolbar: showTools,
			tools: ['add', '-', 'edit', '-', 'remove'],
			data: JsonUtil.decode('${empty proj_report_main_product_str ? "[]" : proj_report_main_product_str}'),
			columns: [
				{type:'indexcolumn'},
				{type:'checkcolumn'},
				{field:'id', header:'id', visible:false},
				{field:'product', header:'产品',formEditorConfig:{labelWidth:100,required:true}},
				{field:'saleradio', header:'营收比例(%)',formEditorConfig:{labelWidth:100,required:true}},
				{field:'exportsaleradio', header:'外销比例(%)',formEditorConfig:{newLine:true}},
				{field:'importsaleradio', header:'内销比例(%)',formEditorConfig:{}}
			]	
		});
	});
});

</script>

<div id="id_table_proj_report_main_product"></div>


