<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('<mini:param  name="isView"/>' == 'true'||isViewHistoryTask==true||reportShowFlag==false){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "proj_debt_survey",
			renderTo: "id_table_proj_debt_survey",
			width: globalClientWidth - 20,
			height: 360,
			lazyLoad: false,
			title: "债权调查",
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: false,
			showToolbar: showTools,
			tools: ['add', '-', 'edit', '-', 'remove'],
			data: JsonUtil.decode('<mini:param  name="proj_report_debt_survey_str" defaultValue="[]"/>'),
			columns: [
				{type:'indexcolumn'},
				{type:'checkcolumn'},
				{field:'id',header:'id', visible: false},
				{field:'content',header:'内容',formEditorConfig:{required: true,maxLength:120,width:516,colspan:3}},
				{field:'asset',header:'金额',formEditorConfig:{newLine:true,required: true,maxLength:120}},
				{field:'occurdate',header:'发生时间',formEditorConfig:{type: 'date',required: true,colspan:3,format: 'yyyy-MM-dd'}},
				{field:'remark',header:'备注',formEditorConfig:{type:'textarea',required: true,newLine:true,colspan:3,height:70,width:516}}
			]	
		});
	});
});
</script>
<div id="id_table_proj_debt_survey"></div>




