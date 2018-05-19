<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true||reportShowFlag==false){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "proj_person_incomesource",
			renderTo: "id_table_proj_person_incomesource",
			width: globalClientWidth - 20,
			height: 360,
			lazyLoad: false,
			title: "收入来源",
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: false,
			showToolbar: showTools,
			tools: ['add', '-', 'edit', '-', 'remove'],
			data: JsonUtil.decode('${empty proj_report_person_incomesource_str ? "[]" : proj_report_person_incomesource_str }'),
			columns: [
				{type:'indexcolumn'},
				{type:'checkcolumn'},
				{field:'id', header: 'id', visible: false},
				{field:'asset', header: '金额',formEditorConfig:{ required:true,labelWidth:100, maxLength:120}},
				{field:'occurdate', header: '发生时间',formEditorConfig:{type:'date',  required:true,format:'yyyy-MM-dd'}},
				{field:'remark',header:'备注', formEditorConfig:{colspan:3,  type:'textarea', newLine:true,  required:true,height:70,width:487}}
			]	
		});
	});
});
</script>
<div id="id_table_proj_person_incomesource"></div>



