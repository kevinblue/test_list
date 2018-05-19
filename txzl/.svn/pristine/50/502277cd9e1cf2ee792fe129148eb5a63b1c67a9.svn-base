<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true||reportShowFlag==false){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "report_energy",
			renderTo: "id_table_report_energy",
			width: globalClientWidth - 20,
			height: 200,
			lazyLoad: false,
			title: "能源消耗",
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: false,
			showToolbar: showTools,
			tools: ['add', '-', 'edit', '-', 'remove'],
			data: JsonUtil.decode('${empty proj_report_report_energy ? "[]" : proj_report_report_energy }'),
			columns: [
				{type:'indexcolumn'},
				{type:'checkcolumn'},
				{field:'id', header:'id', visible:false},
				{field:'projname', header:'项目名',formEditorConfig:{}},
				{field:'month', header:'月份',formEditorConfig:{required:true}},
				{field:'energymount', header:'用量',formEditorConfig:{newLine :true,colspan:3}}
			]	
		});
	});
});

</script>

<div id="id_table_report_energy"></div>


